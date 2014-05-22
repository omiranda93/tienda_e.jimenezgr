/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springController;

import entities.Categoria;
import entities.Producto;
import entities.tiendaDAO;
import java.util.ArrayList;
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
    
    @RequestMapping(method=RequestMethod.GET)
    public String index(ModelMap model, HttpSession session){
        
        return "LogAdministrador";
    }
    
    @RequestMapping(value="/Autenticacion", method = RequestMethod.POST)
   public String Autenticacion(@RequestParam("usuario")String usuario, @RequestParam("pwd")String pwd,ModelMap model, HttpSession session) {
       
      if(!dao.getAdministrador(usuario, pwd).isEmpty()){
          session.setAttribute("usuario", usuario);
          return "Administrador";
      }else{
          String error ="Usuario o contraseña incorrectos";
          session.setAttribute("error", error);
          return "LogAdministrador";
      }
   }
   
   @RequestMapping(value="/Administrador", method=RequestMethod.GET)
    public String yaRegistrado(ModelMap model, HttpSession session){
        
        return "Administrador";
    }
    
    @RequestMapping(value="/InsertarProducto", method=RequestMethod.GET)
    public String InsertarProducto(ModelMap model, HttpSession session){
        
        return "Administrador";
    }
    
    @RequestMapping(value="/EliminarProducto", method=RequestMethod.GET)
    public String EliminarProducto(ModelMap model, HttpSession session){
        
        return "Administrador";
    }
    
    @RequestMapping(value="/EditarProducto", method=RequestMethod.GET)
    public String EditarProducto(ModelMap model, HttpSession session){
        
        return "Administrador";
    }
    
    @RequestMapping(value="/InsertarCategoria", method=RequestMethod.GET)
    public String InsertarCategoria(ModelMap model, HttpSession session){
        
        return "Administrador";
    }
    
    @RequestMapping(value="/EliminarCategoria", method=RequestMethod.GET)
    public String EliminarCategoria(ModelMap model, HttpSession session){
        
        return "Administrador";
    }
    
    @RequestMapping(value="/EditarCategoria", method=RequestMethod.GET)
    public String EditarCategoria(ModelMap model, HttpSession session){
        
        return "Administrador";
    }
    
    @RequestMapping(value="/EstadoPedido", method=RequestMethod.GET)
    public String EstadoPedido(ModelMap model, HttpSession session){
        
        return "Administrador";
    }
}
