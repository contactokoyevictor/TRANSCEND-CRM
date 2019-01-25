/*
 * ENTERPRISE RESOURCE PLANNING AND CUSTOMER RELATIONSHIP MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LLC.
 * ALL RIGHT RESERVED 2014
 */


package com.sivotek.crm.persistent.dao.entities.controllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.crm.persistent.dao.entities.Productattachments;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Componentattachments;
import com.sivotek.crm.persistent.dao.entities.Contenttypes;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.RollbackFailureException;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author okoyevictor
 */
public class ContenttypesJpaController implements Serializable {

    public ContenttypesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ContenttypesJpaController(){
        try{  
             emf = Persistence.createEntityManagerFactory("TRANSCEND-CRM_CLOUD_TOMEEPU");
             getUserTransaction();
        }
        catch(Exception ex){
        System.out.println("Error occoured during JNDI Lookup : "+ex.getMessage());
       }
        
    }
    private UserTransaction getUserTransaction() throws NamingException{
        Context context = new InitialContext ();
        if(utx == null){
            utx = (UserTransaction) context.lookup ("java:comp/TransactionManager");
         }
        return utx;
      }

    public void create(Contenttypes contenttypes) throws RollbackFailureException, Exception {
        if (contenttypes.getProductattachmentsCollection() == null) {
            contenttypes.setProductattachmentsCollection(new ArrayList<Productattachments>());
        }
        if (contenttypes.getComponentattachmentsCollection() == null) {
            contenttypes.setComponentattachmentsCollection(new ArrayList<Componentattachments>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Productattachments> attachedProductattachmentsCollection = new ArrayList<Productattachments>();
            for (Productattachments productattachmentsCollectionProductattachmentsToAttach : contenttypes.getProductattachmentsCollection()) {
                productattachmentsCollectionProductattachmentsToAttach = em.getReference(productattachmentsCollectionProductattachmentsToAttach.getClass(), productattachmentsCollectionProductattachmentsToAttach.getProductattachmentsPK());
                attachedProductattachmentsCollection.add(productattachmentsCollectionProductattachmentsToAttach);
            }
            contenttypes.setProductattachmentsCollection(attachedProductattachmentsCollection);
            Collection<Componentattachments> attachedComponentattachmentsCollection = new ArrayList<Componentattachments>();
            for (Componentattachments componentattachmentsCollectionComponentattachmentsToAttach : contenttypes.getComponentattachmentsCollection()) {
                componentattachmentsCollectionComponentattachmentsToAttach = em.getReference(componentattachmentsCollectionComponentattachmentsToAttach.getClass(), componentattachmentsCollectionComponentattachmentsToAttach.getComponentattachmentsPK());
                attachedComponentattachmentsCollection.add(componentattachmentsCollectionComponentattachmentsToAttach);
            }
            contenttypes.setComponentattachmentsCollection(attachedComponentattachmentsCollection);
            em.persist(contenttypes);
            for (Productattachments productattachmentsCollectionProductattachments : contenttypes.getProductattachmentsCollection()) {
                Contenttypes oldContenttypeOfProductattachmentsCollectionProductattachments = productattachmentsCollectionProductattachments.getContenttype();
                productattachmentsCollectionProductattachments.setContenttype(contenttypes);
                productattachmentsCollectionProductattachments = em.merge(productattachmentsCollectionProductattachments);
                if (oldContenttypeOfProductattachmentsCollectionProductattachments != null) {
                    oldContenttypeOfProductattachmentsCollectionProductattachments.getProductattachmentsCollection().remove(productattachmentsCollectionProductattachments);
                    oldContenttypeOfProductattachmentsCollectionProductattachments = em.merge(oldContenttypeOfProductattachmentsCollectionProductattachments);
                }
            }
            for (Componentattachments componentattachmentsCollectionComponentattachments : contenttypes.getComponentattachmentsCollection()) {
                Contenttypes oldContenttypeOfComponentattachmentsCollectionComponentattachments = componentattachmentsCollectionComponentattachments.getContenttype();
                componentattachmentsCollectionComponentattachments.setContenttype(contenttypes);
                componentattachmentsCollectionComponentattachments = em.merge(componentattachmentsCollectionComponentattachments);
                if (oldContenttypeOfComponentattachmentsCollectionComponentattachments != null) {
                    oldContenttypeOfComponentattachmentsCollectionComponentattachments.getComponentattachmentsCollection().remove(componentattachmentsCollectionComponentattachments);
                    oldContenttypeOfComponentattachmentsCollectionComponentattachments = em.merge(oldContenttypeOfComponentattachmentsCollectionComponentattachments);
                }
            }
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

    public void edit(Contenttypes contenttypes) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Contenttypes persistentContenttypes = em.find(Contenttypes.class, contenttypes.getId());
            Collection<Productattachments> productattachmentsCollectionOld = persistentContenttypes.getProductattachmentsCollection();
            Collection<Productattachments> productattachmentsCollectionNew = contenttypes.getProductattachmentsCollection();
            Collection<Componentattachments> componentattachmentsCollectionOld = persistentContenttypes.getComponentattachmentsCollection();
            Collection<Componentattachments> componentattachmentsCollectionNew = contenttypes.getComponentattachmentsCollection();
            Collection<Productattachments> attachedProductattachmentsCollectionNew = new ArrayList<Productattachments>();
            for (Productattachments productattachmentsCollectionNewProductattachmentsToAttach : productattachmentsCollectionNew) {
                productattachmentsCollectionNewProductattachmentsToAttach = em.getReference(productattachmentsCollectionNewProductattachmentsToAttach.getClass(), productattachmentsCollectionNewProductattachmentsToAttach.getProductattachmentsPK());
                attachedProductattachmentsCollectionNew.add(productattachmentsCollectionNewProductattachmentsToAttach);
            }
            productattachmentsCollectionNew = attachedProductattachmentsCollectionNew;
            contenttypes.setProductattachmentsCollection(productattachmentsCollectionNew);
            Collection<Componentattachments> attachedComponentattachmentsCollectionNew = new ArrayList<Componentattachments>();
            for (Componentattachments componentattachmentsCollectionNewComponentattachmentsToAttach : componentattachmentsCollectionNew) {
                componentattachmentsCollectionNewComponentattachmentsToAttach = em.getReference(componentattachmentsCollectionNewComponentattachmentsToAttach.getClass(), componentattachmentsCollectionNewComponentattachmentsToAttach.getComponentattachmentsPK());
                attachedComponentattachmentsCollectionNew.add(componentattachmentsCollectionNewComponentattachmentsToAttach);
            }
            componentattachmentsCollectionNew = attachedComponentattachmentsCollectionNew;
            contenttypes.setComponentattachmentsCollection(componentattachmentsCollectionNew);
            contenttypes = em.merge(contenttypes);
            for (Productattachments productattachmentsCollectionOldProductattachments : productattachmentsCollectionOld) {
                if (!productattachmentsCollectionNew.contains(productattachmentsCollectionOldProductattachments)) {
                    productattachmentsCollectionOldProductattachments.setContenttype(null);
                    productattachmentsCollectionOldProductattachments = em.merge(productattachmentsCollectionOldProductattachments);
                }
            }
            for (Productattachments productattachmentsCollectionNewProductattachments : productattachmentsCollectionNew) {
                if (!productattachmentsCollectionOld.contains(productattachmentsCollectionNewProductattachments)) {
                    Contenttypes oldContenttypeOfProductattachmentsCollectionNewProductattachments = productattachmentsCollectionNewProductattachments.getContenttype();
                    productattachmentsCollectionNewProductattachments.setContenttype(contenttypes);
                    productattachmentsCollectionNewProductattachments = em.merge(productattachmentsCollectionNewProductattachments);
                    if (oldContenttypeOfProductattachmentsCollectionNewProductattachments != null && !oldContenttypeOfProductattachmentsCollectionNewProductattachments.equals(contenttypes)) {
                        oldContenttypeOfProductattachmentsCollectionNewProductattachments.getProductattachmentsCollection().remove(productattachmentsCollectionNewProductattachments);
                        oldContenttypeOfProductattachmentsCollectionNewProductattachments = em.merge(oldContenttypeOfProductattachmentsCollectionNewProductattachments);
                    }
                }
            }
            for (Componentattachments componentattachmentsCollectionOldComponentattachments : componentattachmentsCollectionOld) {
                if (!componentattachmentsCollectionNew.contains(componentattachmentsCollectionOldComponentattachments)) {
                    componentattachmentsCollectionOldComponentattachments.setContenttype(null);
                    componentattachmentsCollectionOldComponentattachments = em.merge(componentattachmentsCollectionOldComponentattachments);
                }
            }
            for (Componentattachments componentattachmentsCollectionNewComponentattachments : componentattachmentsCollectionNew) {
                if (!componentattachmentsCollectionOld.contains(componentattachmentsCollectionNewComponentattachments)) {
                    Contenttypes oldContenttypeOfComponentattachmentsCollectionNewComponentattachments = componentattachmentsCollectionNewComponentattachments.getContenttype();
                    componentattachmentsCollectionNewComponentattachments.setContenttype(contenttypes);
                    componentattachmentsCollectionNewComponentattachments = em.merge(componentattachmentsCollectionNewComponentattachments);
                    if (oldContenttypeOfComponentattachmentsCollectionNewComponentattachments != null && !oldContenttypeOfComponentattachmentsCollectionNewComponentattachments.equals(contenttypes)) {
                        oldContenttypeOfComponentattachmentsCollectionNewComponentattachments.getComponentattachmentsCollection().remove(componentattachmentsCollectionNewComponentattachments);
                        oldContenttypeOfComponentattachmentsCollectionNewComponentattachments = em.merge(oldContenttypeOfComponentattachmentsCollectionNewComponentattachments);
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
                Integer id = contenttypes.getId();
                if (findContenttypes(id) == null) {
                    throw new NonexistentEntityException("The contenttypes with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Contenttypes contenttypes;
            try {
                contenttypes = em.getReference(Contenttypes.class, id);
                contenttypes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contenttypes with id " + id + " no longer exists.", enfe);
            }
            Collection<Productattachments> productattachmentsCollection = contenttypes.getProductattachmentsCollection();
            for (Productattachments productattachmentsCollectionProductattachments : productattachmentsCollection) {
                productattachmentsCollectionProductattachments.setContenttype(null);
                productattachmentsCollectionProductattachments = em.merge(productattachmentsCollectionProductattachments);
            }
            Collection<Componentattachments> componentattachmentsCollection = contenttypes.getComponentattachmentsCollection();
            for (Componentattachments componentattachmentsCollectionComponentattachments : componentattachmentsCollection) {
                componentattachmentsCollectionComponentattachments.setContenttype(null);
                componentattachmentsCollectionComponentattachments = em.merge(componentattachmentsCollectionComponentattachments);
            }
            em.remove(contenttypes);
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

    public List<Contenttypes> findContenttypesEntities() {
        return findContenttypesEntities(true, -1, -1);
    }

    public List<Contenttypes> findContenttypesEntities(int maxResults, int firstResult) {
        return findContenttypesEntities(false, maxResults, firstResult);
    }

    private List<Contenttypes> findContenttypesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contenttypes.class));
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

    public Contenttypes findContenttypes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contenttypes.class, id);
        } finally {
            em.close();
        }
    }

    public int getContenttypesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contenttypes> rt = cq.from(Contenttypes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
