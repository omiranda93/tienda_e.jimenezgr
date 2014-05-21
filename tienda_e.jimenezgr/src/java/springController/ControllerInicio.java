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
 * @author eduas
 */

@Controller
@RequestMapping("/Inicio")
public class ControllerInicio {
    
    
    tiendaDAO dao = new tiendaDAO();
    
    
    @RequestMapping(method=RequestMethod.GET)
    public String index(ModelMap model, HttpSession session){
        
        List <Producto> carrito = new ArrayList <Producto>();
        session.setAttribute("carrito", carrito);
        
        List <Producto> productosRandom = new ArrayList <Producto>();
        Random rand = new Random();
        for (int i = 0; i<=5; i++){
            int posAleatoria = rand.nextInt(dao.getTodosProductos().size()-1);
            productosRandom.add(dao.getTodosProductos().get(posAleatoria));
        }
        session.setAttribute("productosAleatorios", productosRandom);
        
        List <Categoria> categorias = new ArrayList <Categoria>();
        categorias = dao.getTodasCategorias();
        session.setAttribute("categorias", categorias);
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
   public String muestraProductos(@RequestParam("clave")String clave, ModelMap model, HttpSession session) {
      
       List <Producto> productosListados = new ArrayList<Producto>();
       productosListados = (List<Producto>) dao.getCategoria(clave).getProductoCollection();
       session.setAttribute("productosListados", productosListados);
       return "muestraProductos";
   }
   
   @RequestMapping(value="/VerProducto", method = RequestMethod.GET)
   public String VerProducto(@RequestParam("nombre") String nombre, ModelMap model, HttpSession session) {
       
      Producto p = dao.getNombre(nombre);
      session.setAttribute("producto", p);
      
      return "VerProducto";
   }
   
   @RequestMapping(value="/BuscaProductos", method = RequestMethod.GET)
   public String busca(@RequestParam("nombreProd")String nombreProd, ModelMap model, HttpSession session) {
      
       List <Producto> productosListados = new ArrayList<Producto>();
       productosListados = dao.getNombreLike(nombreProd);
       session.setAttribute("productosListados", productosListados);
       return "muestraProductos";
   }
   
   @RequestMapping(value="/Carrito", method = RequestMethod.GET)
   public String Carrito(@RequestParam("nombreProd")String nombreProd, ModelMap model, HttpSession session) {

       List <Producto> productos = new ArrayList<Producto>();
       productos = (List<Producto>) session.getAttribute("carrito");
       productos.add(dao.getNombre(nombreProd));
       session.setAttribute("carrito", productos);
       return "carrito";
   }
   
   @RequestMapping(value="/CarritoVer", method = RequestMethod.GET)
   public String CarritoVer(ModelMap model, HttpSession session) {
      return "carrito";
   }
   
}
