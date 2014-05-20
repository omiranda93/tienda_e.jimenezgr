/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Producto;
import entities.ProductoTieneImagen;
import entities.ProductoTieneImagenPK;
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
public class ProductoTieneImagenJpaController implements Serializable {

    public ProductoTieneImagenJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductoTieneImagen productoTieneImagen) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (productoTieneImagen.getProductoTieneImagenPK() == null) {
            productoTieneImagen.setProductoTieneImagenPK(new ProductoTieneImagenPK());
        }
        productoTieneImagen.getProductoTieneImagenPK().setProducto(productoTieneImagen.getProducto1().getNombre());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Producto producto1 = productoTieneImagen.getProducto1();
            if (producto1 != null) {
                producto1 = em.getReference(producto1.getClass(), producto1.getNombre());
                productoTieneImagen.setProducto1(producto1);
            }
            em.persist(productoTieneImagen);
            if (producto1 != null) {
                producto1.getProductoTieneImagenCollection().add(productoTieneImagen);
                producto1 = em.merge(producto1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProductoTieneImagen(productoTieneImagen.getProductoTieneImagenPK()) != null) {
                throw new PreexistingEntityException("ProductoTieneImagen " + productoTieneImagen + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductoTieneImagen productoTieneImagen) throws NonexistentEntityException, RollbackFailureException, Exception {
        productoTieneImagen.getProductoTieneImagenPK().setProducto(productoTieneImagen.getProducto1().getNombre());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductoTieneImagen persistentProductoTieneImagen = em.find(ProductoTieneImagen.class, productoTieneImagen.getProductoTieneImagenPK());
            Producto producto1Old = persistentProductoTieneImagen.getProducto1();
            Producto producto1New = productoTieneImagen.getProducto1();
            if (producto1New != null) {
                producto1New = em.getReference(producto1New.getClass(), producto1New.getNombre());
                productoTieneImagen.setProducto1(producto1New);
            }
            productoTieneImagen = em.merge(productoTieneImagen);
            if (producto1Old != null && !producto1Old.equals(producto1New)) {
                producto1Old.getProductoTieneImagenCollection().remove(productoTieneImagen);
                producto1Old = em.merge(producto1Old);
            }
            if (producto1New != null && !producto1New.equals(producto1Old)) {
                producto1New.getProductoTieneImagenCollection().add(productoTieneImagen);
                producto1New = em.merge(producto1New);
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
                ProductoTieneImagenPK id = productoTieneImagen.getProductoTieneImagenPK();
                if (findProductoTieneImagen(id) == null) {
                    throw new NonexistentEntityException("The productoTieneImagen with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProductoTieneImagenPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductoTieneImagen productoTieneImagen;
            try {
                productoTieneImagen = em.getReference(ProductoTieneImagen.class, id);
                productoTieneImagen.getProductoTieneImagenPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productoTieneImagen with id " + id + " no longer exists.", enfe);
            }
            Producto producto1 = productoTieneImagen.getProducto1();
            if (producto1 != null) {
                producto1.getProductoTieneImagenCollection().remove(productoTieneImagen);
                producto1 = em.merge(producto1);
            }
            em.remove(productoTieneImagen);
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

    public List<ProductoTieneImagen> findProductoTieneImagenEntities() {
        return findProductoTieneImagenEntities(true, -1, -1);
    }

    public List<ProductoTieneImagen> findProductoTieneImagenEntities(int maxResults, int firstResult) {
        return findProductoTieneImagenEntities(false, maxResults, firstResult);
    }

    private List<ProductoTieneImagen> findProductoTieneImagenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductoTieneImagen.class));
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

    public ProductoTieneImagen findProductoTieneImagen(ProductoTieneImagenPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductoTieneImagen.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoTieneImagenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductoTieneImagen> rt = cq.from(ProductoTieneImagen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
