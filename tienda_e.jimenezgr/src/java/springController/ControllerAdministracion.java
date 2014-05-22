/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springController;

import static com.sun.j3d.internal.UtilFreelistManager.MAXINT;
import entities.Categoria;
import entities.Pedido;
import entities.Producto;
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
        
        List <Producto> productosListados = new ArrayList <Producto>();
        productosListados = dao.getTodosProductos();
        session.setAttribute("productosListados", productosListados);
        
        List <Categoria> categoriasListadas = new ArrayList <Categoria>();
        categoriasListadas = dao.getTodasCategorias();
        session.setAttribute("categoriasListadas", categoriasListadas);
        
        List <Pedido> pedidosListados = new ArrayList <Pedido>();
        pedidosListados = dao.getTododosPedidos();
        session.setAttribute("pedidosListados", pedidosListados);
        
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
    
    
    @RequestMapping(value="/AdminProductos", method=RequestMethod.GET)
    public String AdminProducto(ModelMap model, HttpSession session){
        
        List <Producto> productosListados = new ArrayList <Producto>();
        productosListados = dao.getTodosProductos();
        session.setAttribute("productosListados", productosListados);
        
        return "AdministrarProductos";
    }
    
    @RequestMapping(value="/AdminPedidos", method=RequestMethod.GET)
    public String AdminPedidos(ModelMap model, HttpSession session){
        
        return "AdministrarPedidos";
    }
    
    @RequestMapping(value="/AdminCategorias", method=RequestMethod.GET)
    public String AdminCategorias(ModelMap model, HttpSession session){
        List <Categoria> categoriasListadas = new ArrayList <Categoria>();
        categoriasListadas = dao.getTodasCategorias();
        session.setAttribute("categoriasListadas", categoriasListadas);
        return "AdministrarCategorias";
    }
    
    
    @RequestMapping(value="/InsertarProducto", method=RequestMethod.GET)
    public String InsertarProducto(@RequestParam("nombre") String nombre, @RequestParam("cantidad") String cantidad, @RequestParam("precio") String precio, @RequestParam("descripcion") String descripcion,  @RequestParam("categorias") String categoria, ModelMap model, HttpSession session){
        Collection <Categoria> categorias = new ArrayList <Categoria>();
        categorias.add(dao.getCategoria(categoria));
        Producto p = new Producto(nombre, Double.parseDouble(precio), Integer.parseInt(cantidad), descripcion, categorias);
        dao.insertarProducto(p);
        session.setAttribute("productosListados", dao.getTodosProductos());
        return "Administrador";
    }
    
    @RequestMapping(value="/EliminarProducto", method=RequestMethod.GET)
    public String EliminarProducto(@RequestParam("contador") String contador, ModelMap model, HttpSession session){
        
        List <Producto>lista = (List <Producto>) session.getAttribute("productosListados");
        Producto p = lista.get(Integer.parseInt(contador));
        dao.eliminarProducto(p);
        lista = dao.getTodosProductos();
        session.setAttribute("productosListados", lista);
        return "Administrador";
    }
    
    @RequestMapping(value="/EditarProducto", method=RequestMethod.GET)
    public String EditarProducto(@RequestParam("cantidad") String cantidad, @RequestParam("precio") String precio, @RequestParam("descripcion") String descripcion, @RequestParam("categorias") String categoria, @RequestParam("contador") String contador, ModelMap model, HttpSession session){
        List <Producto>lista = (List <Producto>) session.getAttribute("productosListados");
        Producto p = lista.get(Integer.parseInt(contador));
        p.getCategoriaCollection().add(dao.getCategoria(categoria));
        
        
        dao.actualizarProducto(p.getNombre(), Double.parseDouble(precio), Integer.parseInt(cantidad), descripcion, p.getCategoriaCollection(), p.getProductoTieneImagenCollection());
        lista = dao.getTodosProductos();
        session.setAttribute("productosListados", lista);
        return "Administrador";
    }
    
    
    
    
    
    
    @RequestMapping(value="/InsertarCategoria", method=RequestMethod.GET)
    public String InsertarCategoria(@RequestParam("nombreCategoria") String nombre, @RequestParam(value="claveSuper", required = false) String claveSuper  , ModelMap model, HttpSession session){
        String clave = "cat";
        int cont = dao.getTodasCategorias().size();
        for(int i = 0; i<Integer.MAX_VALUE;i++){
            if (!(clave+cont).equals(dao.getTodasCategorias().get(i).getClave())){
                break;
            }
            cont++;
        }
        clave = "clave"+cont;
        if (claveSuper != null){
            Categoria c = new Categoria(clave, nombre, false);
            Categoria madre = dao.getCategoria(claveSuper);
            dao.insertarCategoria(c);
            dao.añadirMadre(madre, c);
            dao.añadirHija(madre, c);
            
        }else{
            Categoria c = new Categoria(clave, nombre, true);
            dao.insertarCategoria(c);
        }
        session.setAttribute("categoriasListadas", dao.getTodasCategorias());
        return "Administrador";
    }
    
    @RequestMapping(value="/BorrarCategoria", method=RequestMethod.GET)
    public String EliminarCategoria(@RequestParam("clave") String clave, ModelMap model, HttpSession session){
        Categoria c = dao.getCategoria(clave);
        dao.eliminarCategoria(c);
        session.setAttribute("categoriasListadas", dao.getTodasCategorias());
        return "Administrador";
    }
    
    @RequestMapping(value="/EditarCategoria", method=RequestMethod.GET)
    public String EditarCategoria(@RequestParam("clave") String clave, @RequestParam("nombreCategoria") String nombre ,ModelMap model, HttpSession session){
        
        dao.actualizarCategoria(clave, nombre);
        session.setAttribute("categoriasListadas", dao.getTodasCategorias());
        return "Administrador";
    }
    
    
    
    
    
    @RequestMapping(value="/EstadoPedido", method=RequestMethod.GET)
    public String EstadoPedido(ModelMap model, HttpSession session){
        
        return "Administrador";
    }
}
