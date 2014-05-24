package springController;

import entities.Categoria;
import entities.Pedido;
import entities.Producto;
import entities.RegistroPedidos;
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
//                Pedido p = new Pedido(1,false,"Carrito","luisin","maijaus","10101");
//                Usuario oscar=dao.getUsuario("o.miranda").get(0);
//                p.setUsuario(oscar);
//                dao.insertarPedido(p);
                Pedido p = (Pedido) session.getAttribute("carrito");
                Pedido usuarioP = dao.getPedidosUsuarioCarrito(usuario).get(0);               
                usuarioP.setRegistroPedidosCollection(p.getRegistroPedidosCollection());
                session.setAttribute("carrito", usuarioP);
                dao.actualizarPedido(usuarioP, usuarioP.getRegistroPedidosCollection());
                dao.insertarPedido(usuarioP);
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
