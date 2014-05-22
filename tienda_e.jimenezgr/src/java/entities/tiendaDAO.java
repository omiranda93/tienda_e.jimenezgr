package entities;

import entities.Administrador;
import entities.Categoria;
import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import java.util.List;
import javax.persistence.*;

public class tiendaDAO {

    private EntityManager manager;
    private EntityManagerFactory factory;

    public tiendaDAO() {
        factory = Persistence.createEntityManagerFactory("tienda_e.jimenezgrPU");
        manager = factory.createEntityManager();
    }

    public void close() {

        if (manager != null) {
            manager.close();
        }

        if (factory != null) {
            factory.close();
        }
    }

    public List<Producto> getProductosNombre(String nombre) {
        return getProductosQuery("p.nombre = \"" + nombre + "\"");
    }

    //Productos
    public List<Producto> getTodosProductos() {
        return getProductosQuery(null);
    }

    private List<Producto> getProductosQuery(String where) {

        String sql = "select p from Producto p";
        if (where != null) {
            sql += " where " + where;
        }

        Query query = manager.createQuery(sql);
        return (List<Producto>) query.getResultList();
    }
    
    public void insertarProducto(Producto producto) {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(producto);
        tx.commit();
        //manager.flush();
    }
    public Producto getNombre(String nombre) {
        return manager.find(Producto.class, nombre);
    }
    
    public List<Producto> getNombreLike(String nombre) {
        String where = "upper(p.nombre) like upper('%"+nombre+"%')";
        return getProductosQuery(where);
    }
    

//Categorias:
    public List<Categoria> getTodasCategorias() {
        return getCategoriasQuery(null);
    }

    private List<Categoria> getCategoriasQuery(String where) {

        String sql = "SELECT c FROM Categoria c";
        if (where != null) {
            sql += " WHERE " + where;
        }

        Query query = manager.createQuery(sql);
        return (List<Categoria>) query.getResultList();
    }
    
    public void insertarCategoria(Categoria categoria) {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(categoria);
        tx.commit();
        //manager.flush();
    }
    public Categoria getCategoria(String clave) {
        return manager.find(Categoria.class, clave);
    }
    
    //Pedidos:
    public List<Pedido> getTododosPedidos() {
        return getPedidosQuery(null);
    }

    private List<Pedido> getPedidosQuery(String where) {

        String sql = "select p from Pedido p";
        if (where != null) {
            sql += " where " + where;
        }

        Query query = manager.createQuery(sql);
        return (List<Pedido>) query.getResultList();
    }
    
    public void insertarPedido(Pedido pedido) {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(pedido);
        tx.commit();
        //manager.flush();
    }

    //Ususarios
    public List<Usuario> getTodosUsuarios() {
        return getUsuariosQuery(null);
    }

    private List<Usuario> getUsuariosQuery(String where) {

        String sql = "select u from Usuario u";
        if (where != null) {
            sql += " where " + where;
        }

        Query query = manager.createQuery(sql);
        return (List<Usuario>) query.getResultList();
    }
    
    public void insertarUsuario(Usuario u) {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(u);
        tx.commit();
        //manager.flush();
    }
    
    //Administrador
    public List<Administrador> getAdministrador(String usuario, String pwd) {
        String where = "a.credencialusuario = '"+usuario+"'"+"and a.credencialcontrasenia = '"+pwd+"'";
        return getAdministradoresQuery(where);
    }
    
    public List<Administrador> getTodosAdministradores() {
        return getAdministradoresQuery(null);
    }

    private List<Administrador> getAdministradoresQuery(String where) {

        String sql = "select a from Administrador a";
        if (where != null) {
            sql += " where " + where;
        }

        Query query = manager.createQuery(sql);
        return (List<Administrador>) query.getResultList();
    }
    
    public void insertarAdministrador(Administrador a) {
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        manager.persist(a);
        tx.commit();
        //manager.flush();
    }
    
    

    

//    public Autor getAutor(int codigoAutor) {
//        //String sql = "select a from Autor a where a.idautor = "+codigoAutor;
//        //Query query = manager.createQuery(sql);
//        //return (Autor) query.getResultList().get(0);
//        return manager.find(Autor.class, codigoAutor);
//    }
}
