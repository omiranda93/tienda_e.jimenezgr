package springController;

import entities.Categoria;
import entities.Pedido;
import entities.RegistroPedidos;
import entities.RegistroPedidosPK;
import entities.tiendaDAO;
import java.util.ArrayList;
import java.util.List;
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
@RequestMapping("/Login")
public class ControllerLogin {

    tiendaDAO dao = new tiendaDAO();

    @RequestMapping(value = "/Logueo", method = RequestMethod.POST)
    public String Logueo(@RequestParam("usuario") String usuario, @RequestParam("pwd") String pwd, ModelMap model, HttpSession session) {

        if (!dao.getUsuario(usuario, pwd).isEmpty()) {
            session.setAttribute("usuario", usuario);

            session.setMaxInactiveInterval(1800);

            if (session.getAttribute("carrito") != null) {
                Pedido p = (Pedido) session.getAttribute("carrito");
                Pedido usuarioP = dao.getPedidosUsuarioCarrito(dao.getUsuario(usuario).get(0).getNombre()).get(0);
                for (RegistroPedidos ped : p.getRegistroPedidosCollection()) {
                        RegistroPedidosPK pk = new RegistroPedidosPK(usuarioP.getNumero(), ped.getProducto1().getNombre());
                        ped.setRegistroPedidosPK(pk);
                        ped.setPedido(usuarioP);
                        if(dao.getTodosRegistroPedido().contains(ped)){
                            dao.modificarRegistroPedido(ped, ped.getCantidad()+dao.getRegistroPedidos(ped).get(0).getCantidad());
                        }else{
                            dao.insertarRegistroPedido(ped);
                        }
                }
                dao.actualizarPedido(usuarioP, usuarioP.getRegistroPedidosCollection());
                Pedido usuarioP2 = dao.getPedidosUsuarioCarrito(dao.getUsuario(usuario).get(0).getNombre()).get(0);
                session.setAttribute("carrito", usuarioP2);
            }else{
                Pedido usuarioP = dao.getPedidosUsuarioCarrito(dao.getUsuario(usuario).get(0).getNombre()).get(0);
                session.setAttribute("carrito", usuarioP);
            }

            //comprobar cesta
            return "index";
        } else {
            String error = "Usuario o contraseña incorrectos";
            session.setAttribute("error", error);
            return "Autenticacion";
        }
    }

    @RequestMapping(value = "/cerrarSesionUser", method = RequestMethod.GET)
    public String cerrarSesionUser(ModelMap model, HttpSession session) {
        //cierra la sesion;
        session.invalidate();
        //crea el mensaje de confirmacion;
        model.addAttribute("confirmacion", "Se ha cerrado la sesión");
        //se cargan las categorias para el menú
        List<Categoria> categorias = new ArrayList<Categoria>();
        categorias = dao.getTodasCategorias();
        model.addAttribute("categorias", categorias);
        //redirige a Autenticacion.jsp;
        return "Autenticacion";
    }

}
