
package springController;

import entities.Categoria;
import entities.Pedido;
import entities.Producto;
import entities.RegistroPedidos;
import entities.RegistroPedidosPK;
import entities.Usuario;
import entities.tiendaDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author eduas
 */
@Controller
@RequestMapping("/Inicio")
public class ControllerInicio {

    tiendaDAO dao = new tiendaDAO();

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model, HttpSession session) {
        dao = new tiendaDAO();
        List<Producto> productosRandom = new ArrayList<Producto>();
        Random rand = new Random();
        for (int i = 0; i <= 5; i++) {
            int posAleatoria = rand.nextInt(dao.getTodosProductos().size() - 1);
            if (productosRandom.contains(dao.getTodosProductos().get(posAleatoria))) {
                i = i - 1;
            } else {
                productosRandom.add(dao.getTodosProductos().get(posAleatoria));
            }
        }
        session.setAttribute("productosAleatorios", productosRandom);

        List<Categoria> categorias = new ArrayList<Categoria>();
        categorias = dao.getTodasCategorias();
        session.setAttribute("categorias", categorias);
        return "index";
    }

    @RequestMapping(value = "/Autenticacion", method = RequestMethod.GET)
    public String Autenticacion(ModelMap model, HttpSession session) {
        return "Autenticacion";
    }

    @RequestMapping(value = "/Registro", method = RequestMethod.GET)
    public String registro(ModelMap model, HttpSession session, String email) {
        model.addAttribute("email", email);
        return "Registro";
    }

    @RequestMapping(value = "/MuestraProductos", method = RequestMethod.GET)
    public String muestraProductos(@RequestParam("clave") String clave, ModelMap model, HttpSession session) {

        List<Producto> productosListados = new ArrayList<Producto>();
        productosListados = (List<Producto>) dao.getCategoria(clave).getProductoCollection();
        session.setAttribute("productosListados", productosListados);
        String categoria = dao.getCategoria(clave).getNombre();
        session.setAttribute("categoriaActual", categoria);
        return "muestraProductos";
    }

    @RequestMapping(value = "/VerProducto", method = RequestMethod.GET)
    public String VerProducto(@RequestParam("nombre") String nombre, ModelMap model, HttpSession session) {

        Producto p = dao.getNombre(nombre);
        session.setAttribute("producto", p);

        return "VerProducto";
    }

    @RequestMapping(value = "/BuscaProductos", method = RequestMethod.GET)
    public String busca(@RequestParam("nombreProd") String nombreProd, ModelMap model, HttpSession session) {

        List<Producto> productosListados = new ArrayList<Producto>();
        productosListados = dao.getNombreLike(nombreProd);
        session.setAttribute("productosListados", productosListados);
        return "muestraProductos";
    }

    @RequestMapping(value = "/Registrarse", method = RequestMethod.POST)
    public String Registro(@RequestParam("password") String password, @RequestParam("usuario") String usuario, @RequestParam("nomape") String nomape, @RequestParam(value = "telefono", required = false) String telefono, @RequestParam(value = "direccion", required = false) String direccion, ModelMap model, HttpSession session) {
        Usuario u = new Usuario(usuario, password, nomape);
        String direccionP = "aaa";
        String telefonoP = "bbb";
        if (direccion != null) {
            u.setDireccion(direccion);
            direccionP = u.getDireccion();
        }
        if (telefono != null) {
            u.setTelefono(telefono);
            telefonoP = u.getTelefono();
        }
        dao.insertarUsuario(u);

        int numero = 0;
        List <Pedido> pedidos = dao.getTododosPedidos();
        for(int i =0; i<pedidos.size(); i++){
            if (i==pedidos.size()-1){
                numero = pedidos.get(i).getNumero();
            }
        }
        numero++;

        Pedido p = new Pedido(numero, false, "Carrito", nomape, direccionP, telefonoP, u);

        dao.insertarPedido(p);

        session.setAttribute(usuario, usuario);

        return "index";
    }

    @RequestMapping(value = "/Carrito", method = RequestMethod.GET)
    public String Carrito(@RequestParam("nombreProd") String nombreProd, ModelMap model, HttpSession session) {

//      si no hay carro usuario
        Pedido carro = (Pedido) session.getAttribute("carrito");
        if (session.getAttribute("usuario") == null) {
            if (carro == null) {
                carro = new Pedido();
                carro.setEstado("Carrito");
                List<RegistroPedidos> productos = new ArrayList<RegistroPedidos>();
                carro.setRegistroPedidosCollection(productos);
            }
            int i = 0;
            boolean encontrado = false;
            int pos = -1;
            ArrayList<RegistroPedidos> rp = (ArrayList) carro.getRegistroPedidosCollection();
            while (i < rp.size() && !encontrado) {
                if (rp.get(i).getProducto1().getNombre().equals(nombreProd)) {
                    encontrado = true;
                    pos = i;
                }
                i++;
            }
            if (encontrado) {
                int can = rp.get(pos).getCantidad() + 1;
                rp.get(pos).setCantidad(can);
//                carro.setRegistroPedidosCollection(rp);

            } else {
                RegistroPedidos producto = new RegistroPedidos();
                producto.setCantidad(1);
                producto.setProducto1(dao.getNombre(nombreProd));
                producto.setPedido(carro);
                carro.getRegistroPedidosCollection().add(producto);
            }
            session.setAttribute("carrito", carro);
        } else {
            Producto p = dao.getProductosNombre(nombreProd).get(0);
            String usuario = (String) session.getAttribute("usuario");
            Usuario u = dao.getUsuario(usuario).get(0);
            Pedido usuarioP = dao.getPedidosUsuarioCarrito(dao.getUsuario(usuario).get(0).getNombre()).get(0);
            RegistroPedidosPK pk = new RegistroPedidosPK(usuarioP.getNumero(), p.getNombre());
            RegistroPedidos pedido = new RegistroPedidos(pk, 1);
            pedido.setPedido(usuarioP);
            pedido.setProducto1(p);
            dao.insertarRegistroPedido(pedido);
            carro.getRegistroPedidosCollection().add(pedido);
            session.setAttribute("carrito", carro);
        }        
        return "carrito";
    }

    @RequestMapping(value = "/CarritoVer", method = RequestMethod.GET)
    public String CarritoVer(ModelMap model, HttpSession session) {
        return "carrito";
    }

    @RequestMapping(value = "/CarritoNuevaCantidad", method = RequestMethod.GET)
    public String CarritoNuevaCantidad(@RequestParam("cantidad") int cantidad, @RequestParam("nombreProd") String nombreProd, ModelMap model, HttpSession session) {
        Pedido carro = (Pedido) (session.getAttribute("carrito"));
        if (cantidad > 0) {
            for (RegistroPedidos prod : carro.getRegistroPedidosCollection()) {
                if (prod.getProducto1().getNombre().equals(nombreProd)) {
                    if(session.getAttribute("usuario")!=null){
                        dao.modificarRegistroPedido(prod, cantidad);
                    }
                    prod.setCantidad(cantidad);
                }
            }
        } else {
           List<RegistroPedidos>rp=(List<RegistroPedidos>) carro.getRegistroPedidosCollection();
            int i = 0;
            while (!rp.get(i).getProducto1().getNombre().equals(nombreProd)) {
                i++;
            }
            if (rp.get(i).getProducto1().getNombre().equals(nombreProd)) {
                RegistroPedidos p = rp.get(i);
                if(session.getAttribute("usuario")!=null){
                   dao.eliminarRegistroPedido(p); 
                }
                rp.remove(i);
            }
            if (rp.isEmpty()) {
                carro = null;
            }
        }
        
        
        session.setAttribute("carrito", carro);
        return "carrito";
    }
    
    @RequestMapping(value = "/Pedir", method = RequestMethod.GET)
    public String pedir(ModelMap model, HttpSession session) {
        Pedido carro = (Pedido) session.getAttribute("carrito");
        if (session.getAttribute("usuario") == null) {
            return "Autenticacion";
        }else{
            List <Pedido> pedidos = dao.getTododosPedidos();
            int numero = 0;
            for(int i =0; i<pedidos.size(); i++){
            if (i==pedidos.size()-1){
                numero = pedidos.get(i).getNumero();
            }
            }
            numero++;
            
            Pedido nuevo = new Pedido(carro,numero);
            String usuario = (String) session.getAttribute("usuario");
            dao.insertarPedido(nuevo);
            dao.actualizarPedidos(carro, "En tramite");
            carro = dao.getPedidosUsuarioCarrito(dao.getUsuario(usuario).get(0).getNombre()).get(0);
            session.setAttribute("carrito", carro);
            return "index";
        }
    }
    
    @RequestMapping(value = "/VerPedidos", method = RequestMethod.GET)
    public String VerPedidos(ModelMap model, HttpSession session) {
        String usuario = (String) session.getAttribute("usuario");
        usuario=dao.getUsuario(usuario).get(0).getNombre();
        session.setAttribute("pedidosUser", dao.getTodosPedidosUser(usuario));
        return "MisPedidos";
    }

}
