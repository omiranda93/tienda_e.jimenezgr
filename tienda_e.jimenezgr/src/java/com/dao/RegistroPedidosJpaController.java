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
import clases.Producto;
import clases.Pedido;
import clases.RegistroPedidos;
import clases.RegistroPedidosPK;
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
public class RegistroPedidosJpaController implements Serializable {

    public RegistroPedidosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(RegistroPedidos registroPedidos) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (registroPedidos.getRegistroPedidosPK() == null) {
            registroPedidos.setRegistroPedidosPK(new RegistroPedidosPK());
        }
        registroPedidos.getRegistroPedidosPK().setNumero(registroPedidos.getPedido().getNumero());
        registroPedidos.getRegistroPedidosPK().setProducto(registroPedidos.getProducto1().getNombre());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Producto producto1 = registroPedidos.getProducto1();
            if (producto1 != null) {
                producto1 = em.getReference(producto1.getClass(), producto1.getNombre());
                registroPedidos.setProducto1(producto1);
            }
            Pedido pedido = registroPedidos.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getNumero());
                registroPedidos.setPedido(pedido);
            }
            em.persist(registroPedidos);
            if (producto1 != null) {
                producto1.getRegistroPedidosCollection().add(registroPedidos);
                producto1 = em.merge(producto1);
            }
            if (pedido != null) {
                pedido.getRegistroPedidosCollection().add(registroPedidos);
                pedido = em.merge(pedido);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findRegistroPedidos(registroPedidos.getRegistroPedidosPK()) != null) {
                throw new PreexistingEntityException("RegistroPedidos " + registroPedidos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RegistroPedidos registroPedidos) throws NonexistentEntityException, RollbackFailureException, Exception {
        registroPedidos.getRegistroPedidosPK().setNumero(registroPedidos.getPedido().getNumero());
        registroPedidos.getRegistroPedidosPK().setProducto(registroPedidos.getProducto1().getNombre());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            RegistroPedidos persistentRegistroPedidos = em.find(RegistroPedidos.class, registroPedidos.getRegistroPedidosPK());
            Producto producto1Old = persistentRegistroPedidos.getProducto1();
            Producto producto1New = registroPedidos.getProducto1();
            Pedido pedidoOld = persistentRegistroPedidos.getPedido();
            Pedido pedidoNew = registroPedidos.getPedido();
            if (producto1New != null) {
                producto1New = em.getReference(producto1New.getClass(), producto1New.getNombre());
                registroPedidos.setProducto1(producto1New);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getNumero());
                registroPedidos.setPedido(pedidoNew);
            }
            registroPedidos = em.merge(registroPedidos);
            if (producto1Old != null && !producto1Old.equals(producto1New)) {
                producto1Old.getRegistroPedidosCollection().remove(registroPedidos);
                producto1Old = em.merge(producto1Old);
            }
            if (producto1New != null && !producto1New.equals(producto1Old)) {
                producto1New.getRegistroPedidosCollection().add(registroPedidos);
                producto1New = em.merge(producto1New);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getRegistroPedidosCollection().remove(registroPedidos);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getRegistroPedidosCollection().add(registroPedidos);
                pedidoNew = em.merge(pedidoNew);
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
                RegistroPedidosPK id = registroPedidos.getRegistroPedidosPK();
                if (findRegistroPedidos(id) == null) {
                    throw new NonexistentEntityException("The registroPedidos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(RegistroPedidosPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            RegistroPedidos registroPedidos;
            try {
                registroPedidos = em.getReference(RegistroPedidos.class, id);
                registroPedidos.getRegistroPedidosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The registroPedidos with id " + id + " no longer exists.", enfe);
            }
            Producto producto1 = registroPedidos.getProducto1();
            if (producto1 != null) {
                producto1.getRegistroPedidosCollection().remove(registroPedidos);
                producto1 = em.merge(producto1);
            }
            Pedido pedido = registroPedidos.getPedido();
            if (pedido != null) {
                pedido.getRegistroPedidosCollection().remove(registroPedidos);
                pedido = em.merge(pedido);
            }
            em.remove(registroPedidos);
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

    public List<RegistroPedidos> findRegistroPedidosEntities() {
        return findRegistroPedidosEntities(true, -1, -1);
    }

    public List<RegistroPedidos> findRegistroPedidosEntities(int maxResults, int firstResult) {
        return findRegistroPedidosEntities(false, maxResults, firstResult);
    }

    private List<RegistroPedidos> findRegistroPedidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RegistroPedidos.class));
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

    public RegistroPedidos findRegistroPedidos(RegistroPedidosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RegistroPedidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getRegistroPedidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RegistroPedidos> rt = cq.from(RegistroPedidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
