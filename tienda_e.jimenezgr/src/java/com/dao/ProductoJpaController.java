
package com.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Categoria;
import entities.Producto;
import java.util.ArrayList;
import java.util.Collection;
import entities.RegistroPedidos;
import entities.ProductoTieneImagen;
import com.dao.exceptions.IllegalOrphanException;
import com.dao.exceptions.NonexistentEntityException;
import com.dao.exceptions.PreexistingEntityException;
import com.dao.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Shkire
 */
public class ProductoJpaController implements Serializable {

    public ProductoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (producto.getCategoriaCollection() == null) {
            producto.setCategoriaCollection(new ArrayList<Categoria>());
        }
        if (producto.getRegistroPedidosCollection() == null) {
            producto.setRegistroPedidosCollection(new ArrayList<RegistroPedidos>());
        }
        if (producto.getProductoTieneImagenCollection() == null) {
            producto.setProductoTieneImagenCollection(new ArrayList<ProductoTieneImagen>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Categoria> attachedCategoriaCollection = new ArrayList<Categoria>();
            for (Categoria categoriaCollectionCategoriaToAttach : producto.getCategoriaCollection()) {
                categoriaCollectionCategoriaToAttach = em.getReference(categoriaCollectionCategoriaToAttach.getClass(), categoriaCollectionCategoriaToAttach.getClave());
                attachedCategoriaCollection.add(categoriaCollectionCategoriaToAttach);
            }
            producto.setCategoriaCollection(attachedCategoriaCollection);
            Collection<RegistroPedidos> attachedRegistroPedidosCollection = new ArrayList<RegistroPedidos>();
            for (RegistroPedidos registroPedidosCollectionRegistroPedidosToAttach : producto.getRegistroPedidosCollection()) {
                registroPedidosCollectionRegistroPedidosToAttach = em.getReference(registroPedidosCollectionRegistroPedidosToAttach.getClass(), registroPedidosCollectionRegistroPedidosToAttach.getRegistroPedidosPK());
                attachedRegistroPedidosCollection.add(registroPedidosCollectionRegistroPedidosToAttach);
            }
            producto.setRegistroPedidosCollection(attachedRegistroPedidosCollection);
            Collection<ProductoTieneImagen> attachedProductoTieneImagenCollection = new ArrayList<ProductoTieneImagen>();
            for (ProductoTieneImagen productoTieneImagenCollectionProductoTieneImagenToAttach : producto.getProductoTieneImagenCollection()) {
                productoTieneImagenCollectionProductoTieneImagenToAttach = em.getReference(productoTieneImagenCollectionProductoTieneImagenToAttach.getClass(), productoTieneImagenCollectionProductoTieneImagenToAttach.getProductoTieneImagenPK());
                attachedProductoTieneImagenCollection.add(productoTieneImagenCollectionProductoTieneImagenToAttach);
            }
            producto.setProductoTieneImagenCollection(attachedProductoTieneImagenCollection);
            em.persist(producto);
            for (Categoria categoriaCollectionCategoria : producto.getCategoriaCollection()) {
                categoriaCollectionCategoria.getProductoCollection().add(producto);
                categoriaCollectionCategoria = em.merge(categoriaCollectionCategoria);
            }
            for (RegistroPedidos registroPedidosCollectionRegistroPedidos : producto.getRegistroPedidosCollection()) {
                Producto oldProducto1OfRegistroPedidosCollectionRegistroPedidos = registroPedidosCollectionRegistroPedidos.getProducto1();
                registroPedidosCollectionRegistroPedidos.setProducto1(producto);
                registroPedidosCollectionRegistroPedidos = em.merge(registroPedidosCollectionRegistroPedidos);
                if (oldProducto1OfRegistroPedidosCollectionRegistroPedidos != null) {
                    oldProducto1OfRegistroPedidosCollectionRegistroPedidos.getRegistroPedidosCollection().remove(registroPedidosCollectionRegistroPedidos);
                    oldProducto1OfRegistroPedidosCollectionRegistroPedidos = em.merge(oldProducto1OfRegistroPedidosCollectionRegistroPedidos);
                }
            }
            for (ProductoTieneImagen productoTieneImagenCollectionProductoTieneImagen : producto.getProductoTieneImagenCollection()) {
                Producto oldProducto1OfProductoTieneImagenCollectionProductoTieneImagen = productoTieneImagenCollectionProductoTieneImagen.getProducto1();
                productoTieneImagenCollectionProductoTieneImagen.setProducto1(producto);
                productoTieneImagenCollectionProductoTieneImagen = em.merge(productoTieneImagenCollectionProductoTieneImagen);
                if (oldProducto1OfProductoTieneImagenCollectionProductoTieneImagen != null) {
                    oldProducto1OfProductoTieneImagenCollectionProductoTieneImagen.getProductoTieneImagenCollection().remove(productoTieneImagenCollectionProductoTieneImagen);
                    oldProducto1OfProductoTieneImagenCollectionProductoTieneImagen = em.merge(oldProducto1OfProductoTieneImagenCollectionProductoTieneImagen);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProducto(producto.getNombre()) != null) {
                throw new PreexistingEntityException("Producto " + producto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Producto persistentProducto = em.find(Producto.class, producto.getNombre());
            Collection<Categoria> categoriaCollectionOld = persistentProducto.getCategoriaCollection();
            Collection<Categoria> categoriaCollectionNew = producto.getCategoriaCollection();
            Collection<RegistroPedidos> registroPedidosCollectionOld = persistentProducto.getRegistroPedidosCollection();
            Collection<RegistroPedidos> registroPedidosCollectionNew = producto.getRegistroPedidosCollection();
            Collection<ProductoTieneImagen> productoTieneImagenCollectionOld = persistentProducto.getProductoTieneImagenCollection();
            Collection<ProductoTieneImagen> productoTieneImagenCollectionNew = producto.getProductoTieneImagenCollection();
            List<String> illegalOrphanMessages = null;
            for (RegistroPedidos registroPedidosCollectionOldRegistroPedidos : registroPedidosCollectionOld) {
                if (!registroPedidosCollectionNew.contains(registroPedidosCollectionOldRegistroPedidos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RegistroPedidos " + registroPedidosCollectionOldRegistroPedidos + " since its producto1 field is not nullable.");
                }
            }
            for (ProductoTieneImagen productoTieneImagenCollectionOldProductoTieneImagen : productoTieneImagenCollectionOld) {
                if (!productoTieneImagenCollectionNew.contains(productoTieneImagenCollectionOldProductoTieneImagen)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ProductoTieneImagen " + productoTieneImagenCollectionOldProductoTieneImagen + " since its producto1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Categoria> attachedCategoriaCollectionNew = new ArrayList<Categoria>();
            for (Categoria categoriaCollectionNewCategoriaToAttach : categoriaCollectionNew) {
                categoriaCollectionNewCategoriaToAttach = em.getReference(categoriaCollectionNewCategoriaToAttach.getClass(), categoriaCollectionNewCategoriaToAttach.getClave());
                attachedCategoriaCollectionNew.add(categoriaCollectionNewCategoriaToAttach);
            }
            categoriaCollectionNew = attachedCategoriaCollectionNew;
            producto.setCategoriaCollection(categoriaCollectionNew);
            Collection<RegistroPedidos> attachedRegistroPedidosCollectionNew = new ArrayList<RegistroPedidos>();
            for (RegistroPedidos registroPedidosCollectionNewRegistroPedidosToAttach : registroPedidosCollectionNew) {
                registroPedidosCollectionNewRegistroPedidosToAttach = em.getReference(registroPedidosCollectionNewRegistroPedidosToAttach.getClass(), registroPedidosCollectionNewRegistroPedidosToAttach.getRegistroPedidosPK());
                attachedRegistroPedidosCollectionNew.add(registroPedidosCollectionNewRegistroPedidosToAttach);
            }
            registroPedidosCollectionNew = attachedRegistroPedidosCollectionNew;
            producto.setRegistroPedidosCollection(registroPedidosCollectionNew);
            Collection<ProductoTieneImagen> attachedProductoTieneImagenCollectionNew = new ArrayList<ProductoTieneImagen>();
            for (ProductoTieneImagen productoTieneImagenCollectionNewProductoTieneImagenToAttach : productoTieneImagenCollectionNew) {
                productoTieneImagenCollectionNewProductoTieneImagenToAttach = em.getReference(productoTieneImagenCollectionNewProductoTieneImagenToAttach.getClass(), productoTieneImagenCollectionNewProductoTieneImagenToAttach.getProductoTieneImagenPK());
                attachedProductoTieneImagenCollectionNew.add(productoTieneImagenCollectionNewProductoTieneImagenToAttach);
            }
            productoTieneImagenCollectionNew = attachedProductoTieneImagenCollectionNew;
            producto.setProductoTieneImagenCollection(productoTieneImagenCollectionNew);
            producto = em.merge(producto);
            for (Categoria categoriaCollectionOldCategoria : categoriaCollectionOld) {
                if (!categoriaCollectionNew.contains(categoriaCollectionOldCategoria)) {
                    categoriaCollectionOldCategoria.getProductoCollection().remove(producto);
                    categoriaCollectionOldCategoria = em.merge(categoriaCollectionOldCategoria);
                }
            }
            for (Categoria categoriaCollectionNewCategoria : categoriaCollectionNew) {
                if (!categoriaCollectionOld.contains(categoriaCollectionNewCategoria)) {
                    categoriaCollectionNewCategoria.getProductoCollection().add(producto);
                    categoriaCollectionNewCategoria = em.merge(categoriaCollectionNewCategoria);
                }
            }
            for (RegistroPedidos registroPedidosCollectionNewRegistroPedidos : registroPedidosCollectionNew) {
                if (!registroPedidosCollectionOld.contains(registroPedidosCollectionNewRegistroPedidos)) {
                    Producto oldProducto1OfRegistroPedidosCollectionNewRegistroPedidos = registroPedidosCollectionNewRegistroPedidos.getProducto1();
                    registroPedidosCollectionNewRegistroPedidos.setProducto1(producto);
                    registroPedidosCollectionNewRegistroPedidos = em.merge(registroPedidosCollectionNewRegistroPedidos);
                    if (oldProducto1OfRegistroPedidosCollectionNewRegistroPedidos != null && !oldProducto1OfRegistroPedidosCollectionNewRegistroPedidos.equals(producto)) {
                        oldProducto1OfRegistroPedidosCollectionNewRegistroPedidos.getRegistroPedidosCollection().remove(registroPedidosCollectionNewRegistroPedidos);
                        oldProducto1OfRegistroPedidosCollectionNewRegistroPedidos = em.merge(oldProducto1OfRegistroPedidosCollectionNewRegistroPedidos);
                    }
                }
            }
            for (ProductoTieneImagen productoTieneImagenCollectionNewProductoTieneImagen : productoTieneImagenCollectionNew) {
                if (!productoTieneImagenCollectionOld.contains(productoTieneImagenCollectionNewProductoTieneImagen)) {
                    Producto oldProducto1OfProductoTieneImagenCollectionNewProductoTieneImagen = productoTieneImagenCollectionNewProductoTieneImagen.getProducto1();
                    productoTieneImagenCollectionNewProductoTieneImagen.setProducto1(producto);
                    productoTieneImagenCollectionNewProductoTieneImagen = em.merge(productoTieneImagenCollectionNewProductoTieneImagen);
                    if (oldProducto1OfProductoTieneImagenCollectionNewProductoTieneImagen != null && !oldProducto1OfProductoTieneImagenCollectionNewProductoTieneImagen.equals(producto)) {
                        oldProducto1OfProductoTieneImagenCollectionNewProductoTieneImagen.getProductoTieneImagenCollection().remove(productoTieneImagenCollectionNewProductoTieneImagen);
                        oldProducto1OfProductoTieneImagenCollectionNewProductoTieneImagen = em.merge(oldProducto1OfProductoTieneImagenCollectionNewProductoTieneImagen);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = producto.getNombre();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getNombre();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<RegistroPedidos> registroPedidosCollectionOrphanCheck = producto.getRegistroPedidosCollection();
            for (RegistroPedidos registroPedidosCollectionOrphanCheckRegistroPedidos : registroPedidosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the RegistroPedidos " + registroPedidosCollectionOrphanCheckRegistroPedidos + " in its registroPedidosCollection field has a non-nullable producto1 field.");
            }
            Collection<ProductoTieneImagen> productoTieneImagenCollectionOrphanCheck = producto.getProductoTieneImagenCollection();
            for (ProductoTieneImagen productoTieneImagenCollectionOrphanCheckProductoTieneImagen : productoTieneImagenCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the ProductoTieneImagen " + productoTieneImagenCollectionOrphanCheckProductoTieneImagen + " in its productoTieneImagenCollection field has a non-nullable producto1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Categoria> categoriaCollection = producto.getCategoriaCollection();
            for (Categoria categoriaCollectionCategoria : categoriaCollection) {
                categoriaCollectionCategoria.getProductoCollection().remove(producto);
                categoriaCollectionCategoria = em.merge(categoriaCollectionCategoria);
            }
            em.remove(producto);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Producto findProducto(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
