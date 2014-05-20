/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springController;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author eduas
 */

@Controller
@RequestMapping("/Inicio")
public class ControllerInicio {
    
    @RequestMapping(method=RequestMethod.GET)
    public String index(){
        return "index";
    }
    
    @RequestMapping(value="/Autenticacion", method = RequestMethod.GET)
   public String Autenticacion(ModelMap model, HttpSession session) {
      
      return "Autenticacion";
   }
    
    @RequestMapping(value="/Registro", method = RequestMethod.GET)
   public String registro(ModelMap model, HttpSession session, String email) {
      model.addAttribute("email", email);
      return "Registro";
   }
   
   @RequestMapping(value="/MuestraProductos", method = RequestMethod.GET)
   public String muestraProductos(ModelMap model, HttpSession session, String nombreProd) {
      
      return "muestraProductos";
   }
   
}
