package springController;

import entities.Categoria;
import entities.Pedido;
import entities.Producto;
import entities.RegistroPedidos;
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
                List <Pedido> usuarioP = dao.getPedidosUsuario(usuario);
                Pedido usuarioCarrito = new Pedido(1, false, "Carrito", "", "", "");
                usuarioCarrito.setRegistroPedidosCollection(new ArrayList<RegistroPedidos> ());
                for(int i = 0; i<usuarioP.size(); i++){
                    if ("Carrito".equalsIgnoreCase(usuarioP.get(i).getEstado())){
                        usuarioCarrito = usuarioP.get(i);
                    }
                }
                
                usuarioCarrito.getRegistroPedidosCollection().addAll(p.getRegistroPedidosCollection());
                session.setAttribute("carrito", usuarioCarrito);
                dao.modificarPeidoCollection(usuarioCarrito, p);
            }
           
            //comprobar cesta
            return "index";
        } else {
            String error = "Usuario o contraseña incorrectos";
            session.setAttribute("error", error);
            return "Autenticacion";
        }
    }
}
