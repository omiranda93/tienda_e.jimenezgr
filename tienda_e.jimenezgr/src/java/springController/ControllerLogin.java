/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springController;

import entities.tiendaDAO;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Shkire
 *
 */

@Controller
@RequestMapping("/Login")
public class ControllerLogin {
    
    tiendaDAO dao = new tiendaDAO();
    
//    @RequestMapping(value="/Inicio", method = RequestMethod.GET)
//    public String Inicio(ModelMap model, HttpSession session) {
//      return "Inicio";
//   }
}
