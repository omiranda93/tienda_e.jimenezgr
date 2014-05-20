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
import clases.Categoria;
import java.util.ArrayList;
import java.util.Collection;
import clases.Producto;
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
public class CategoriaJpaController implements Serializable {

    public CategoriaJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categoria categoria) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (categoria.getCategoriaCollection() == null) {
            categoria.setCategoriaCollection(new ArrayList<Categoria>());
        }
        if (categoria.getCategoriaCollection1() == null) {
            categoria.setCategoriaCollection1(new ArrayList<Categoria>());
        }
        if (categoria.getProductoCollection() == null) {
            categoria.setProductoCollection(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Categoria> attachedCategoriaCollection = new ArrayList<Categoria>();
            for (Categoria categoriaCollectionCategoriaToAttach : categoria.getCategoriaCollection()) {
                categoriaCollectionCategoriaToAttach = em.getReference(categoriaCollectionCategoriaToAttach.getClass(), categoriaCollectionCategoriaToAttach.getClave());
                attachedCategoriaCollection.add(categoriaCollectionCategoriaToAttach);
            }
            categoria.setCategoriaCollection(attachedCategoriaCollection);
            Collection<Categoria> attachedCategoriaCollection1 = new ArrayList<Categoria>();
            for (Categoria categoriaCollection1CategoriaToAttach : categoria.getCategoriaCollection1()) {
                categoriaCollection1CategoriaToAttach = em.getReference(categoriaCollection1CategoriaToAttach.getClass(), categoriaCollection1CategoriaToAttach.getClave());
                attachedCategoriaCollection1.add(categoriaCollection1CategoriaToAttach);
            }
            categoria.setCategoriaCollection1(attachedCategoriaCollection1);
            Collection<Producto> attachedProductoCollection = new ArrayList<Producto>();
            for (Producto productoCollectionProductoToAttach : categoria.getProductoCollection()) {
                productoCollectionProductoToAttach = em.getReference(productoCollectionProductoToAttach.getClass(), productoCollectionProductoToAttach.getNombre());
                attachedProductoCollection.add(productoCollectionProductoToAttach);
            }
            categoria.setProductoCollection(attachedProductoCollection);
            em.persist(categoria);
            for (Categoria categoriaCollectionCategoria : categoria.getCategoriaCollection()) {
                categoriaCollectionCategoria.getCategoriaCollection().add(categoria);
                categoriaCollectionCategoria = em.merge(categoriaCollectionCategoria);
            }
            for (Categoria categoriaCollection1Categoria : categoria.getCategoriaCollection1()) {
                categoriaCollection1Categoria.getCategoriaCollection().add(categoria);
                categoriaCollection1Categoria = em.merge(categoriaCollection1Categoria);
            }
            for (Producto productoCollectionProducto : categoria.getProductoCollection()) {
                productoCollectionProducto.getCategoriaCollection().add(categoria);
                productoCollectionProducto = em.merge(productoCollectionProducto);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCategoria(categoria.getClave()) != null) {
                throw new PreexistingEntityException("Categoria " + categoria + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categoria categoria) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getClave());
            Collection<Categoria> categoriaCollectionOld = persistentCategoria.getCategoriaCollection();
            Collection<Categoria> categoriaCollectionNew = categoria.getCategoriaCollection();
            Collection<Categoria> categoriaCollection1Old = persistentCategoria.getCategoriaCollection1();
            Collection<Categoria> categoriaCollection1New = categoria.getCategoriaCollection1();
            Collection<Producto> productoCollectionOld = persistentCategoria.getProductoCollection();
            Collection<Producto> productoCollectionNew = categoria.getProductoCollection();
            Collection<Categoria> attachedCategoriaCollectionNew = new ArrayList<Categoria>();
            for (Categoria categoriaCollectionNewCategoriaToAttach : categoriaCollectionNew) {
                categoriaCollectionNewCategoriaToAttach = em.getReference(categoriaCollectionNewCategoriaToAttach.getClass(), categoriaCollectionNewCategoriaToAttach.getClave());
                attachedCategoriaCollectionNew.add(categoriaCollectionNewCategoriaToAttach);
            }
            categoriaCollectionNew = attachedCategoriaCollectionNew;
            categoria.setCategoriaCollection(categoriaCollectionNew);
            Collection<Categoria> attachedCategoriaCollection1New = new ArrayList<Categoria>();
            for (Categoria categoriaCollection1NewCategoriaToAttach : categoriaCollection1New) {
                categoriaCollection1NewCategoriaToAttach = em.getReference(categoriaCollection1NewCategoriaToAttach.getClass(), categoriaCollection1NewCategoriaToAttach.getClave());
                attachedCategoriaCollection1New.add(categoriaCollection1NewCategoriaToAttach);
            }
            categoriaCollection1New = attachedCategoriaCollection1New;
            categoria.setCategoriaCollection1(categoriaCollection1New);
            Collection<Producto> attachedProductoCollectionNew = new ArrayList<Producto>();
            for (Producto productoCollectionNewProductoToAttach : productoCollectionNew) {
                productoCollectionNewProductoToAttach = em.getReference(productoCollectionNewProductoToAttach.getClass(), productoCollectionNewProductoToAttach.getNombre());
                attachedProductoCollectionNew.add(productoCollectionNewProductoToAttach);
            }
            productoCollectionNew = attachedProductoCollectionNew;
            categoria.setProductoCollection(productoCollectionNew);
            categoria = em.merge(categoria);
            for (Categoria categoriaCollectionOldCategoria : categoriaCollectionOld) {
                if (!categoriaCollectionNew.contains(categoriaCollectionOldCategoria)) {
                    categoriaCollectionOldCategoria.getCategoriaCollection().remove(categoria);
                    categoriaCollectionOldCategoria = em.merge(categoriaCollectionOldCategoria);
                }
            }
            for (Categoria categoriaCollectionNewCategoria : categoriaCollectionNew) {
                if (!categoriaCollectionOld.contains(categoriaCollectionNewCategoria)) {
                    categoriaCollectionNewCategoria.getCategoriaCollection().add(categoria);
                    categoriaCollectionNewCategoria = em.merge(categoriaCollectionNewCategoria);
                }
            }
            for (Categoria categoriaCollection1OldCategoria : categoriaCollection1Old) {
                if (!categoriaCollection1New.contains(categoriaCollection1OldCategoria)) {
                    categoriaCollection1OldCategoria.getCategoriaCollection().remove(categoria);
                    categoriaCollection1OldCategoria = em.merge(categoriaCollection1OldCategoria);
                }
            }
            for (Categoria categoriaCollection1NewCategoria : categoriaCollection1New) {
                if (!categoriaCollection1Old.contains(categoriaCollection1NewCategoria)) {
                    categoriaCollection1NewCategoria.getCategoriaCollection().add(categoria);
                    categoriaCollection1NewCategoria = em.merge(categoriaCollection1NewCategoria);
                }
            }
            for (Producto productoCollectionOldProducto : productoCollectionOld) {
                if (!productoCollectionNew.contains(productoCollectionOldProducto)) {
                    productoCollectionOldProducto.getCategoriaCollection().remove(categoria);
                    productoCollectionOldProducto = em.merge(productoCollectionOldProducto);
                }
            }
            for (Producto productoCollectionNewProducto : productoCollectionNew) {
                if (!productoCollectionOld.contains(productoCollectionNewProducto)) {
                    productoCollectionNewProducto.getCategoriaCollection().add(categoria);
                    productoCollectionNewProducto = em.merge(productoCollectionNewProducto);
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
                String id = categoria.getClave();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getClave();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            Collection<Categoria> categoriaCollection = categoria.getCategoriaCollection();
            for (Categoria categoriaCollectionCategoria : categoriaCollection) {
                categoriaCollectionCategoria.getCategoriaCollection().remove(categoria);
                categoriaCollectionCategoria = em.merge(categoriaCollectionCategoria);
            }
            Collection<Categoria> categoriaCollection1 = categoria.getCategoriaCollection1();
            for (Categoria categoriaCollection1Categoria : categoriaCollection1) {
                categoriaCollection1Categoria.getCategoriaCollection().remove(categoria);
                categoriaCollection1Categoria = em.merge(categoriaCollection1Categoria);
            }
            Collection<Producto> productoCollection = categoria.getProductoCollection();
            for (Producto productoCollectionProducto : productoCollection) {
                productoCollectionProducto.getCategoriaCollection().remove(categoria);
                productoCollectionProducto = em.merge(productoCollectionProducto);
            }
            em.remove(categoria);
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

    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
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

    public Categoria findCategoria(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
