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
import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.Productcomponents;
import com.sivotek.crm.persistent.dao.entities.Contenttypes;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Componentattachments;
import com.sivotek.crm.persistent.dao.entities.ComponentattachmentsPK;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.PreexistingEntityException;
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
public class ComponentattachmentsJpaController implements Serializable {

    public ComponentattachmentsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public ComponentattachmentsJpaController(){
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

    public void create(Componentattachments componentattachments) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (componentattachments.getComponentattachmentsPK() == null) {
            componentattachments.setComponentattachmentsPK(new ComponentattachmentsPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = componentattachments.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                componentattachments.setCompany(company);
            }
            Productcomponents productcomponents = componentattachments.getProductcomponents();
            if (productcomponents != null) {
                productcomponents = em.getReference(productcomponents.getClass(), productcomponents.getProductcomponentsPK());
                componentattachments.setProductcomponents(productcomponents);
            }
            Contenttypes contenttype = componentattachments.getContenttype();
            if (contenttype != null) {
                contenttype = em.getReference(contenttype.getClass(), contenttype.getId());
                componentattachments.setContenttype(contenttype);
            }
            Companyemployee companyemployee = componentattachments.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                componentattachments.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = componentattachments.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                componentattachments.setCompanyemployee1(companyemployee1);
            }
            em.persist(componentattachments);
            if (company != null) {
                company.getComponentattachmentsCollection().add(componentattachments);
                company = em.merge(company);
            }
            if (productcomponents != null) {
                productcomponents.getComponentattachmentsCollection().add(componentattachments);
                productcomponents = em.merge(productcomponents);
            }
            if (contenttype != null) {
                contenttype.getComponentattachmentsCollection().add(componentattachments);
                contenttype = em.merge(contenttype);
            }
            if (companyemployee != null) {
                companyemployee.getComponentattachmentsCollection().add(componentattachments);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getComponentattachmentsCollection().add(componentattachments);
                companyemployee1 = em.merge(companyemployee1);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findComponentattachments(componentattachments.getComponentattachmentsPK()) != null) {
                throw new PreexistingEntityException("Componentattachments " + componentattachments + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Componentattachments componentattachments) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Componentattachments persistentComponentattachments = em.find(Componentattachments.class, componentattachments.getComponentattachmentsPK());
            Company companyOld = persistentComponentattachments.getCompany();
            Company companyNew = componentattachments.getCompany();
            Productcomponents productcomponentsOld = persistentComponentattachments.getProductcomponents();
            Productcomponents productcomponentsNew = componentattachments.getProductcomponents();
            Contenttypes contenttypeOld = persistentComponentattachments.getContenttype();
            Contenttypes contenttypeNew = componentattachments.getContenttype();
            Companyemployee companyemployeeOld = persistentComponentattachments.getCompanyemployee();
            Companyemployee companyemployeeNew = componentattachments.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentComponentattachments.getCompanyemployee1();
            Companyemployee companyemployee1New = componentattachments.getCompanyemployee1();
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                componentattachments.setCompany(companyNew);
            }
            if (productcomponentsNew != null) {
                productcomponentsNew = em.getReference(productcomponentsNew.getClass(), productcomponentsNew.getProductcomponentsPK());
                componentattachments.setProductcomponents(productcomponentsNew);
            }
            if (contenttypeNew != null) {
                contenttypeNew = em.getReference(contenttypeNew.getClass(), contenttypeNew.getId());
                componentattachments.setContenttype(contenttypeNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                componentattachments.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                componentattachments.setCompanyemployee1(companyemployee1New);
            }
            componentattachments = em.merge(componentattachments);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getComponentattachmentsCollection().remove(componentattachments);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getComponentattachmentsCollection().add(componentattachments);
                companyNew = em.merge(companyNew);
            }
            if (productcomponentsOld != null && !productcomponentsOld.equals(productcomponentsNew)) {
                productcomponentsOld.getComponentattachmentsCollection().remove(componentattachments);
                productcomponentsOld = em.merge(productcomponentsOld);
            }
            if (productcomponentsNew != null && !productcomponentsNew.equals(productcomponentsOld)) {
                productcomponentsNew.getComponentattachmentsCollection().add(componentattachments);
                productcomponentsNew = em.merge(productcomponentsNew);
            }
            if (contenttypeOld != null && !contenttypeOld.equals(contenttypeNew)) {
                contenttypeOld.getComponentattachmentsCollection().remove(componentattachments);
                contenttypeOld = em.merge(contenttypeOld);
            }
            if (contenttypeNew != null && !contenttypeNew.equals(contenttypeOld)) {
                contenttypeNew.getComponentattachmentsCollection().add(componentattachments);
                contenttypeNew = em.merge(contenttypeNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getComponentattachmentsCollection().remove(componentattachments);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getComponentattachmentsCollection().add(componentattachments);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getComponentattachmentsCollection().remove(componentattachments);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getComponentattachmentsCollection().add(componentattachments);
                companyemployee1New = em.merge(companyemployee1New);
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
                ComponentattachmentsPK id = componentattachments.getComponentattachmentsPK();
                if (findComponentattachments(id) == null) {
                    throw new NonexistentEntityException("The componentattachments with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ComponentattachmentsPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Componentattachments componentattachments;
            try {
                componentattachments = em.getReference(Componentattachments.class, id);
                componentattachments.getComponentattachmentsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The componentattachments with id " + id + " no longer exists.", enfe);
            }
            Company company = componentattachments.getCompany();
            if (company != null) {
                company.getComponentattachmentsCollection().remove(componentattachments);
                company = em.merge(company);
            }
            Productcomponents productcomponents = componentattachments.getProductcomponents();
            if (productcomponents != null) {
                productcomponents.getComponentattachmentsCollection().remove(componentattachments);
                productcomponents = em.merge(productcomponents);
            }
            Contenttypes contenttype = componentattachments.getContenttype();
            if (contenttype != null) {
                contenttype.getComponentattachmentsCollection().remove(componentattachments);
                contenttype = em.merge(contenttype);
            }
            Companyemployee companyemployee = componentattachments.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getComponentattachmentsCollection().remove(componentattachments);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = componentattachments.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getComponentattachmentsCollection().remove(componentattachments);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(componentattachments);
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

    public List<Componentattachments> findComponentattachmentsEntities() {
        return findComponentattachmentsEntities(true, -1, -1);
    }

    public List<Componentattachments> findComponentattachmentsEntities(int maxResults, int firstResult) {
        return findComponentattachmentsEntities(false, maxResults, firstResult);
    }

    private List<Componentattachments> findComponentattachmentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Componentattachments.class));
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

    public Componentattachments findComponentattachments(ComponentattachmentsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Componentattachments.class, id);
        } finally {
            em.close();
        }
    }

    public int getComponentattachmentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Componentattachments> rt = cq.from(Componentattachments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
