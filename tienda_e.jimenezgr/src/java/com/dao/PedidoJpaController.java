/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;

import com.dao.exceptions.IllegalOrphanException;
import com.dao.exceptions.NonexistentEntityException;
import com.dao.exceptions.PreexistingEntityException;
import com.dao.exceptions.RollbackFailureException;
import entities.Pedido;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Usuario;
import entities.RegistroPedidos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Shkire
 */
public class PedidoJpaController implements Serializable {

    public PedidoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedido pedido) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (pedido.getRegistroPedidosCollection() == null) {
            pedido.setRegistroPedidosCollection(new ArrayList<RegistroPedidos>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuario usuario = pedido.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getCredencialusuario());
                pedido.setUsuario(usuario);
            }
            Collection<RegistroPedidos> attachedRegistroPedidosCollection = new ArrayList<RegistroPedidos>();
            for (RegistroPedidos registroPedidosCollectionRegistroPedidosToAttach : pedido.getRegistroPedidosCollection()) {
                registroPedidosCollectionRegistroPedidosToAttach = em.getReference(registroPedidosCollectionRegistroPedidosToAttach.getClass(), registroPedidosCollectionRegistroPedidosToAttach.getRegistroPedidosPK());
                attachedRegistroPedidosCollection.add(registroPedidosCollectionRegistroPedidosToAttach);
            }
            pedido.setRegistroPedidosCollection(attachedRegistroPedidosCollection);
            em.persist(pedido);
            if (usuario != null) {
                usuario.getPedidoCollection().add(pedido);
                usuario = em.merge(usuario);
            }
            for (RegistroPedidos registroPedidosCollectionRegistroPedidos : pedido.getRegistroPedidosCollection()) {
                Pedido oldPedidoOfRegistroPedidosCollectionRegistroPedidos = registroPedidosCollectionRegistroPedidos.getPedido();
                registroPedidosCollectionRegistroPedidos.setPedido(pedido);
                registroPedidosCollectionRegistroPedidos = em.merge(registroPedidosCollectionRegistroPedidos);
                if (oldPedidoOfRegistroPedidosCollectionRegistroPedidos != null) {
                    oldPedidoOfRegistroPedidosCollectionRegistroPedidos.getRegistroPedidosCollection().remove(registroPedidosCollectionRegistroPedidos);
                    oldPedidoOfRegistroPedidosCollectionRegistroPedidos = em.merge(oldPedidoOfRegistroPedidosCollectionRegistroPedidos);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPedido(pedido.getNumero()) != null) {
                throw new PreexistingEntityException("Pedido " + pedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedido pedido) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getNumero());
            Usuario usuarioOld = persistentPedido.getUsuario();
            Usuario usuarioNew = pedido.getUsuario();
            Collection<RegistroPedidos> registroPedidosCollectionOld = persistentPedido.getRegistroPedidosCollection();
            Collection<RegistroPedidos> registroPedidosCollectionNew = pedido.getRegistroPedidosCollection();
            List<String> illegalOrphanMessages = null;
            for (RegistroPedidos registroPedidosCollectionOldRegistroPedidos : registroPedidosCollectionOld) {
                if (!registroPedidosCollectionNew.contains(registroPedidosCollectionOldRegistroPedidos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain RegistroPedidos " + registroPedidosCollectionOldRegistroPedidos + " since its pedido field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getCredencialusuario());
                pedido.setUsuario(usuarioNew);
            }
            Collection<RegistroPedidos> attachedRegistroPedidosCollectionNew = new ArrayList<RegistroPedidos>();
            for (RegistroPedidos registroPedidosCollectionNewRegistroPedidosToAttach : registroPedidosCollectionNew) {
                registroPedidosCollectionNewRegistroPedidosToAttach = em.getReference(registroPedidosCollectionNewRegistroPedidosToAttach.getClass(), registroPedidosCollectionNewRegistroPedidosToAttach.getRegistroPedidosPK());
                attachedRegistroPedidosCollectionNew.add(registroPedidosCollectionNewRegistroPedidosToAttach);
            }
            registroPedidosCollectionNew = attachedRegistroPedidosCollectionNew;
            pedido.setRegistroPedidosCollection(registroPedidosCollectionNew);
            pedido = em.merge(pedido);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getPedidoCollection().remove(pedido);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getPedidoCollection().add(pedido);
                usuarioNew = em.merge(usuarioNew);
            }
            for (RegistroPedidos registroPedidosCollectionNewRegistroPedidos : registroPedidosCollectionNew) {
                if (!registroPedidosCollectionOld.contains(registroPedidosCollectionNewRegistroPedidos)) {
                    Pedido oldPedidoOfRegistroPedidosCollectionNewRegistroPedidos = registroPedidosCollectionNewRegistroPedidos.getPedido();
                    registroPedidosCollectionNewRegistroPedidos.setPedido(pedido);
                    registroPedidosCollectionNewRegistroPedidos = em.merge(registroPedidosCollectionNewRegistroPedidos);
                    if (oldPedidoOfRegistroPedidosCollectionNewRegistroPedidos != null && !oldPedidoOfRegistroPedidosCollectionNewRegistroPedidos.equals(pedido)) {
                        oldPedidoOfRegistroPedidosCollectionNewRegistroPedidos.getRegistroPedidosCollection().remove(registroPedidosCollectionNewRegistroPedidos);
                        oldPedidoOfRegistroPedidosCollectionNewRegistroPedidos = em.merge(oldPedidoOfRegistroPedidosCollectionNewRegistroPedidos);
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
                Integer id = pedido.getNumero();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getNumero();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<RegistroPedidos> registroPedidosCollectionOrphanCheck = pedido.getRegistroPedidosCollection();
            for (RegistroPedidos registroPedidosCollectionOrphanCheckRegistroPedidos : registroPedidosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedido (" + pedido + ") cannot be destroyed since the RegistroPedidos " + registroPedidosCollectionOrphanCheckRegistroPedidos + " in its registroPedidosCollection field has a non-nullable pedido field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuario = pedido.getUsuario();
            if (usuario != null) {
                usuario.getPedidoCollection().remove(pedido);
                usuario = em.merge(usuario);
            }
            em.remove(pedido);
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

    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
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

    public Pedido findPedido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
