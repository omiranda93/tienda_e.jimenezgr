package entities;

import entities.Administrador;
import entities.Categoria;
import entities.Pedido;
import entities.Producto;
import entities.Usuario;
import java.util.ArrayList;
import java.util.Collection;
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
        for(Categoria c : producto.getCategoriaCollection()){
            Categoria cat = manager.find(Categoria.class, c.getClave());
            if(cat.getProductoCollection()==null){
            }else{
            if(!cat.getProductoCollection().contains(producto)){
                cat.getProductoCollection().add(producto);
            }}
        }
        tx.commit();
        //manager.flush();
    }

    public Producto getNombre(String nombre) {
        return manager.find(Producto.class, nombre);
    }

    public List<Producto> getNombreLike(String nombre) {
        String where = "upper(p.nombre) like upper('%" + nombre + "%')";
        return getProductosQuery(where);
    }

    public void eliminarProducto(Producto p) {
        manager.getTransaction().begin();
        manager.remove(p);
        manager.getTransaction().commit();
    }

    public void actualizarProducto(String nombre, double precio, int cantidad, String descripcion, Collection<Categoria> categorias, Collection<ProductoTieneImagen> img, String categoria2) {
        manager.getTransaction().begin();
        Producto p = manager.find(Producto.class, nombre);
        p.setCantidad(cantidad);
        p.setCategoriaCollection(categorias);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setProductoTieneImagenCollection(img);
        for(Categoria c : p.getCategoriaCollection()){
            Categoria cat = manager.find(Categoria.class, c.getClave());
            if(cat.getProductoCollection()==null){
            }else{
            if(!cat.getProductoCollection().contains(p)){
                cat.getProductoCollection().add(p);
            }}
        }
        Categoria cat = manager.find(Categoria.class, categoria2);
        if(cat.getCategoriaCollection()==null){
        }else{
            cat.getProductoCollection().remove(p);
        }
        manager.getTransaction().commit();
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

    public void eliminarCategoria(Categoria c) {
        manager.getTransaction().begin();
        manager.remove(c);
        manager.getTransaction().commit();
    }

    public void actualizarCategoria(String clave, String nombre) {
        manager.getTransaction().begin();
        Categoria c = manager.find(Categoria.class, clave);
        c.setNombre(nombre);
        manager.getTransaction().commit();
    }

    public void actualizarPedido(int num, Pedido pedido) {
        manager.getTransaction().begin();
        Pedido p = manager.find(Pedido.class, num);
        p = pedido;
        manager.getTransaction().commit();
    }

    public void añadirHija(Categoria madre, Categoria hija) {
        manager.getTransaction().begin();
        Categoria madree = manager.find(Categoria.class, madre.getClave());
        madree.getCategoriaCollection1().add(hija);
        manager.getTransaction().commit();
    }

    public void añadirMadre(Categoria madre, Categoria hija) {
        manager.getTransaction().begin();
        Categoria hijaa = manager.find(Categoria.class, hija.getClave());
        hijaa.getCategoriaCollection().add(madre);
        manager.getTransaction().commit();
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

    public void actualizarPedidos(Pedido p, String estado) {
        manager.getTransaction().begin();
        Pedido pedido = manager.find(Pedido.class, p.getNumero());
        pedido.setEstado(estado);
        manager.getTransaction().commit();
    }

    public void actualizarPreparado(Pedido p) {
        manager.getTransaction().begin();
        Pedido pedido = manager.find(Pedido.class, p.getNumero());
        pedido.setPendiente(true);
        manager.getTransaction().commit();
    }
     
    public List<Pedido> getPedidosUsuario(String usuario) {
        String where = "p.nombre = '"+usuario+"'";
        return getPedidosQuery(where);
    }
    
    public void modificarPeidoCollection(Pedido antiguo, Pedido nuevo) {
        manager.getTransaction().begin();
        Pedido pedidoAntiguo = manager.find(Pedido.class, antiguo.getNumero());
        pedidoAntiguo.getRegistroPedidosCollection().addAll(nuevo.getRegistroPedidosCollection());
        manager.getTransaction().commit();
    }

    //Ususarios
    public List<Usuario> getTodosUsuarios() {
        return getUsuariosQuery(null);
    }

    public List<Usuario> getUsuario(String usuario) {
        String where = "u.credencialusuario = '" + usuario+"'";
        return getUsuariosQuery(where);
    }

    public List<Usuario> getUsuario(String usuario, String pwd) {
        String where = "u.credencialusuario = '" + usuario + "'" + "and u.credencialcontrasenia = '" + pwd + "'";
        return getUsuariosQuery(where);
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
        String where = "a.credencialusuario = '" + usuario + "'" + "and a.credencialcontrasenia = '" + pwd + "'";
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
