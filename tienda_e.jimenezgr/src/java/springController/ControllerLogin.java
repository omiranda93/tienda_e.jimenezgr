package springController;

import entities.Categoria;
import entities.Pedido;
import entities.Producto;
import entities.RegistroPedidos;
import entities.RegistroPedidosPK;
import entities.Usuario;
import entities.tiendaDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
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
            
            if(session.getAttribute("carrito")!=null){
                Pedido p = (Pedido) session.getAttribute("carrito");
                Pedido usuarioP = dao.getPedidosUsuarioCarrito(dao.getUsuario(usuario).get(0).getNombre()).get(0);
                for(RegistroPedidos ped : p.getRegistroPedidosCollection()){
                    RegistroPedidosPK pk = new RegistroPedidosPK(usuarioP.getNumero(), ped.getProducto1().getNombre());
                    ped.setRegistroPedidosPK(pk);
                    ped.setPedido(usuarioP);
                    dao.insertarRegistroPedido(ped);
                }
                session.setAttribute("carrito", usuarioP);
                dao.actualizarPedido(usuarioP, usuarioP.getRegistroPedidosCollection());
            }
           
            //comprobar cesta
            return "index";
        } else {
            String error = "Usuario o contraseña incorrectos";
            session.setAttribute("error", error);
            return "Autenticacion";
        }
    }
    
    
    @RequestMapping(value="/cerrarSesionUser", method=RequestMethod.GET)
    public String cerrarSesionUser(ModelMap model, HttpSession session){
        //cierra la sesion;
            session.invalidate();
            //crea el mensaje de confirmacion;
            model.addAttribute("confirmacion","Se ha cerrado la sesión");
            //redirige a Autenticacion.jsp;
        return "Autenticacion";
    }
    
}
