

package springController;

import entities.Categoria;
import entities.Pedido;
import entities.Producto;
import entities.ProductoTieneImagen;
import entities.tiendaDAO;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Shkire
 *
 */
@Controller
@RequestMapping("/Administracion")
public class ControllerAdministracion {

    tiendaDAO dao = new tiendaDAO();
//    @RequestMapping(value="/Inicio", method = RequestMethod.GET)
//    public String Inicio(ModelMap model, HttpSession session) {
//      return "Inicio";
//   }

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model, HttpSession session) {

        List<Producto> productosListados = new ArrayList<Producto>();
        productosListados = dao.getTodosProductos();
        session.setAttribute("productosListados", productosListados);

        List<Categoria> categoriasListadas = new ArrayList<Categoria>();
        categoriasListadas = dao.getTodasCategorias();
        session.setAttribute("categoriasListadas", categoriasListadas);

        List<Pedido> pedidosListados = new ArrayList<Pedido>();
        pedidosListados = dao.getTododosPedidos();
        session.setAttribute("pedidosListados", pedidosListados);

        return "LogAdministrador";
    }

    @RequestMapping(value = "/Autenticacion", method = RequestMethod.POST)
    public String Autenticacion(@RequestParam("usuario") String usuario, @RequestParam("pwd") String pwd, ModelMap model, HttpSession session) {

        if (!dao.getAdministrador(usuario, pwd).isEmpty()) {
            session.setAttribute("admin", usuario);
            return "Administrador";
        } else {
            String error = "Usuario o contraseña incorrectos";
            session.setAttribute("error", error);
            return "LogAdministrador";
        }
    }

    @RequestMapping(value = "/Administrador", method = RequestMethod.GET)
    public String yaRegistrado(ModelMap model, HttpSession session) {

        return "Administrador";
    }

    @RequestMapping(value = "/AdminProductos", method = RequestMethod.GET)
    public String AdminProducto(ModelMap model, HttpSession session) {

        List<Producto> productosListados = new ArrayList<Producto>();
        productosListados = dao.getTodosProductos();
        session.setAttribute("productosListados", productosListados);

        return "AdministrarProductos";
    }

    @RequestMapping(value = "/AdminPedidos", method = RequestMethod.GET)
    public String AdminPedidos(ModelMap model, HttpSession session) {

        return "AdministrarPedidos";
    }

    @RequestMapping(value = "/AdminCategorias", method = RequestMethod.GET)
    public String AdminCategorias(ModelMap model, HttpSession session) {
        List<Categoria> categoriasListadas = new ArrayList<Categoria>();
        categoriasListadas = dao.getTodasCategorias();
        session.setAttribute("categoriasListadas", categoriasListadas);
        return "AdministrarCategorias";
    }

    @RequestMapping(value = "/InsertarProducto", method = RequestMethod.POST)
    public String InsertarProducto(@RequestParam("nombre") String nombre, @RequestParam("cantidad") String cantidad, @RequestParam("precio") String precio, @RequestParam("descripcion") String descripcion, @RequestParam("categorias") String categoria, @RequestParam("name") String name, @RequestParam("file") MultipartFile file, ModelMap model, HttpSession session) {
        String nuevaImagen;
        //Imagen
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String realPath = session.getServletContext().getRealPath("/Recursos/productos-img");
                realPath = realPath.replaceAll("build/web/Recursos/productos-img", "") + "web/Recursos/productos-img";
//                File dir = new File(rootPath + File.separator + "tmpFiles");
//                if (!dir.exists()){
//                    dir.mkdirs();
//                }
                // Create the file on server
                File serverFile = new File(realPath + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                //Añadir producto
                nuevaImagen = "/Recursos/productos-img" + File.separator + name;

                Collection<ProductoTieneImagen> imagenes = new ArrayList<ProductoTieneImagen>();
                Collection<Categoria> categorias = new ArrayList<Categoria>();
                categorias.add(dao.getCategoria(categoria));
                Producto p = new Producto(nombre, Double.parseDouble(precio), Integer.parseInt(cantidad), descripcion, categorias);
                ProductoTieneImagen img = new ProductoTieneImagen(p.getNombre(), nuevaImagen);
                img.setPrincipal(Boolean.TRUE);
                img.setProducto1(p);
                imagenes.add(img);
                p.setProductoTieneImagenCollection(imagenes);
                dao.insertarProducto(p);
                session.setAttribute("productosListados", dao.getTodosProductos());
                return "Administrador";

            } catch (Exception e) {
                throw new RuntimeException("You failed to upload " + name + " => " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("You failed to upload " + name + " because the file was empty.");

        }

    }

    @RequestMapping(value = "/EliminarProducto", method = RequestMethod.GET)
    public String EliminarProducto(@RequestParam("contador") String contador, ModelMap model, HttpSession session) {

        List<Producto> lista = (List<Producto>) session.getAttribute("productosListados");
        Producto p = lista.get(Integer.parseInt(contador));
        dao.eliminarProducto(p);
        lista = dao.getTodosProductos();
        session.setAttribute("productosListados", lista);
        return "Administrador";
    }
    
    @RequestMapping(value="/EditarProducto", method=RequestMethod.POST)
    public String EditarProducto(@RequestParam("cantidad") String cantidad, @RequestParam("precio") String precio, @RequestParam("descripcion") String descripcion, @RequestParam("categorias") String categoria, @RequestParam("categorias2") String categoria2, @RequestParam("contador") String contador,@RequestParam(value="name", required = false) String name, @RequestParam(value= "file", required = false) MultipartFile file,  ModelMap model, HttpSession session){
        String nuevaImagen;
        //Imagen
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String realPath = session.getServletContext().getRealPath("/Recursos/productos-img");
                realPath = realPath.replaceAll("build/web/Recursos/productos-img", "") + "web/Recursos/productos-img";
//                File dir = new File(rootPath + File.separator + "tmpFiles");
//                if (!dir.exists()){
//                    dir.mkdirs();
//                }
                // Create the file on server
                File serverFile = new File(realPath + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                //Añadir producto
                nuevaImagen = "/Recursos/productos-img" + File.separator + name;

                Collection<ProductoTieneImagen> imagenes = new ArrayList<ProductoTieneImagen>();
                List<Producto> lista = (List<Producto>) session.getAttribute("productosListados");
                Producto p = lista.get(Integer.parseInt(contador));
                if(!"".equals(categoria2)){
                    p.getCategoriaCollection().add(dao.getCategoria(categoria2));
                    dao.actualizarProductoAñadirCategoria(p.getNombre(), categoria2);
                }
                ProductoTieneImagen img =new ProductoTieneImagen(p.getNombre(), nuevaImagen);
                img.setPrincipal(true);
                img.setProducto1(p);
                imagenes.add(img);
                p.setProductoTieneImagenCollection(imagenes);
                if(!"".equals(categoria)){
                    dao.actualizarProductoEliminarCategoria(p.getNombre(), categoria);
                    p.getCategoriaCollection().remove(dao.getCategoria(categoria));
                }
                dao.actualizarProducto(p.getNombre(), Double.parseDouble(precio), Integer.parseInt(cantidad), descripcion, p.getCategoriaCollection(), p.getProductoTieneImagenCollection());
                lista = dao.getTodosProductos();
                session.setAttribute("productosListados", lista);
                return "Administrador";

            } catch (Exception e) {
                throw new RuntimeException("You failed to upload " + name + " => " + e.getMessage(), e);
            }
        } else {
            List<Producto> lista = (List<Producto>) session.getAttribute("productosListados");
            Producto p = lista.get(Integer.parseInt(contador));
            if(!"".equals(categoria2)){
                p.getCategoriaCollection().add(dao.getCategoria(categoria2));
                dao.actualizarProductoAñadirCategoria(p.getNombre(), categoria2);
            }
            if(!"".equals(categoria)){
                dao.actualizarProductoEliminarCategoria(p.getNombre(), categoria);
                p.getCategoriaCollection().remove(dao.getCategoria(categoria));
            }
            dao.actualizarProducto(p.getNombre(), Double.parseDouble(precio), Integer.parseInt(cantidad), descripcion, p.getCategoriaCollection(), p.getProductoTieneImagenCollection());
            lista = dao.getTodosProductos();
            session.setAttribute("productosListados", lista);
            return "Administrador";

        }

    }

    @RequestMapping(value = "/InsertarCategoria", method = RequestMethod.GET)
    public String InsertarCategoria(@RequestParam("nombreCategoria") String nombre, @RequestParam(value = "claveSuper", required = false) String claveSuper, ModelMap model, HttpSession session) {
        String clave = "cat";
        int cont = dao.getTodasCategorias().size();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if (!(clave + cont).equals(dao.getTodasCategorias().get(i).getClave())) {
                break;
            }
            cont++;
        }
        cont++;
        clave = "clave" + cont;
        if (claveSuper != null) {
            Categoria c = new Categoria(clave, nombre, false);
            Categoria madre = dao.getCategoria(claveSuper);
            dao.insertarCategoria(c);
            dao.añadirMadre(madre, c);
            dao.añadirHija(madre, c);

        } else {
            Categoria c = new Categoria(clave, nombre, true);
            dao.insertarCategoria(c);
        }
        session.setAttribute("categoriasListadas", dao.getTodasCategorias());
        return "Administrador";
    }

    @RequestMapping(value = "/BorrarCategoria", method = RequestMethod.GET)
    public String EliminarCategoria(@RequestParam("clave") String clave, @RequestParam(value="clavePadre", required = false) String clavePadre, ModelMap model, HttpSession session) {
        Categoria c = dao.getCategoria(clave);  
        dao.eliminarCategoria(c);
        if(!(clavePadre==null)){
            dao.getCategoria(clavePadre).getCategoriaCollection1().remove(c);
        }
        session.setAttribute("categoriasListadas", dao.getTodasCategorias());
        return "Administrador";
    }

    @RequestMapping(value = "/EditarCategoria", method = RequestMethod.GET)
    public String EditarCategoria(@RequestParam("clave") String clave, @RequestParam("nombreCategoria") String nombre, ModelMap model, HttpSession session) {

        dao.actualizarCategoria(clave, nombre);
        session.setAttribute("categoriasListadas", dao.getTodasCategorias());
        return "Administrador";
    }

    @RequestMapping(value = "/EstadoPedido", method = RequestMethod.GET)
    public String EstadoPedido(@RequestParam("estado") String estado, @RequestParam("contador") String contador, ModelMap model, HttpSession session) {

        List<Pedido> pedidos = (List<Pedido>) session.getAttribute("pedidosListados");
        Pedido p = pedidos.get(Integer.parseInt(contador));
        dao.actualizarPedidos(p, estado);
        session.setAttribute("pedidosListados", dao.getTododosPedidos());
        return "Administrador";
    }

    @RequestMapping(value = "/PedidoPreparado", method = RequestMethod.GET)
    public String PedidoPreparado(@RequestParam("contador") String contador, ModelMap model, HttpSession session) {

        List<Pedido> pedidos = (List<Pedido>) session.getAttribute("pedidosListados");
        Pedido p = pedidos.get(Integer.parseInt(contador));
        dao.actualizarPreparado(p);
        return "Administrador";
    }

    @RequestMapping(value = "/cerrarSesionAdmin", method = RequestMethod.GET)
    public String cerrarSesionAdmin(ModelMap model, HttpSession session) {
        //cierra la sesion;
        session.invalidate();
        //crea el mensaje de confirmacion;
        model.addAttribute("confirmacion", "Se ha cerrado la sesión");
        //redirige a LogAdministracion.jsp;
        return "LogAdministrador";
    }

}
