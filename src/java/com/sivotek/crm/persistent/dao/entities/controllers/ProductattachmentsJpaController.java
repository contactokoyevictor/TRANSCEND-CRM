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
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Contenttypes;
import com.sivotek.crm.persistent.dao.entities.Productattachments;
import com.sivotek.crm.persistent.dao.entities.ProductattachmentsPK;
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
public class ProductattachmentsJpaController implements Serializable {

    public ProductattachmentsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public ProductattachmentsJpaController(){
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

    public void create(Productattachments productattachments) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (productattachments.getProductattachmentsPK() == null) {
            productattachments.setProductattachmentsPK(new ProductattachmentsPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = productattachments.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                productattachments.setCompany(company);
            }
            Companyemployee companyemployee = productattachments.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                productattachments.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = productattachments.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                productattachments.setCompanyemployee1(companyemployee1);
            }
            Companyemployee companyemployee2 = productattachments.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2 = em.getReference(companyemployee2.getClass(), companyemployee2.getCompanyemployeePK());
                productattachments.setCompanyemployee2(companyemployee2);
            }
            Contenttypes contenttype = productattachments.getContenttype();
            if (contenttype != null) {
                contenttype = em.getReference(contenttype.getClass(), contenttype.getId());
                productattachments.setContenttype(contenttype);
            }
            em.persist(productattachments);
            if (company != null) {
                company.getProductattachmentsCollection().add(productattachments);
                company = em.merge(company);
            }
            if (companyemployee != null) {
                companyemployee.getProductattachmentsCollection().add(productattachments);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getProductattachmentsCollection().add(productattachments);
                companyemployee1 = em.merge(companyemployee1);
            }
            if (companyemployee2 != null) {
                companyemployee2.getProductattachmentsCollection().add(productattachments);
                companyemployee2 = em.merge(companyemployee2);
            }
            if (contenttype != null) {
                contenttype.getProductattachmentsCollection().add(productattachments);
                contenttype = em.merge(contenttype);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProductattachments(productattachments.getProductattachmentsPK()) != null) {
                throw new PreexistingEntityException("Productattachments " + productattachments + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productattachments productattachments) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Productattachments persistentProductattachments = em.find(Productattachments.class, productattachments.getProductattachmentsPK());
            Company companyOld = persistentProductattachments.getCompany();
            Company companyNew = productattachments.getCompany();
            Companyemployee companyemployeeOld = persistentProductattachments.getCompanyemployee();
            Companyemployee companyemployeeNew = productattachments.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentProductattachments.getCompanyemployee1();
            Companyemployee companyemployee1New = productattachments.getCompanyemployee1();
            Companyemployee companyemployee2Old = persistentProductattachments.getCompanyemployee2();
            Companyemployee companyemployee2New = productattachments.getCompanyemployee2();
            Contenttypes contenttypeOld = persistentProductattachments.getContenttype();
            Contenttypes contenttypeNew = productattachments.getContenttype();
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                productattachments.setCompany(companyNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                productattachments.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                productattachments.setCompanyemployee1(companyemployee1New);
            }
            if (companyemployee2New != null) {
                companyemployee2New = em.getReference(companyemployee2New.getClass(), companyemployee2New.getCompanyemployeePK());
                productattachments.setCompanyemployee2(companyemployee2New);
            }
            if (contenttypeNew != null) {
                contenttypeNew = em.getReference(contenttypeNew.getClass(), contenttypeNew.getId());
                productattachments.setContenttype(contenttypeNew);
            }
            productattachments = em.merge(productattachments);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getProductattachmentsCollection().remove(productattachments);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getProductattachmentsCollection().add(productattachments);
                companyNew = em.merge(companyNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getProductattachmentsCollection().remove(productattachments);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getProductattachmentsCollection().add(productattachments);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getProductattachmentsCollection().remove(productattachments);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getProductattachmentsCollection().add(productattachments);
                companyemployee1New = em.merge(companyemployee1New);
            }
            if (companyemployee2Old != null && !companyemployee2Old.equals(companyemployee2New)) {
                companyemployee2Old.getProductattachmentsCollection().remove(productattachments);
                companyemployee2Old = em.merge(companyemployee2Old);
            }
            if (companyemployee2New != null && !companyemployee2New.equals(companyemployee2Old)) {
                companyemployee2New.getProductattachmentsCollection().add(productattachments);
                companyemployee2New = em.merge(companyemployee2New);
            }
            if (contenttypeOld != null && !contenttypeOld.equals(contenttypeNew)) {
                contenttypeOld.getProductattachmentsCollection().remove(productattachments);
                contenttypeOld = em.merge(contenttypeOld);
            }
            if (contenttypeNew != null && !contenttypeNew.equals(contenttypeOld)) {
                contenttypeNew.getProductattachmentsCollection().add(productattachments);
                contenttypeNew = em.merge(contenttypeNew);
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
                ProductattachmentsPK id = productattachments.getProductattachmentsPK();
                if (findProductattachments(id) == null) {
                    throw new NonexistentEntityException("The productattachments with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProductattachmentsPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Productattachments productattachments;
            try {
                productattachments = em.getReference(Productattachments.class, id);
                productattachments.getProductattachmentsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productattachments with id " + id + " no longer exists.", enfe);
            }
            Company company = productattachments.getCompany();
            if (company != null) {
                company.getProductattachmentsCollection().remove(productattachments);
                company = em.merge(company);
            }
            Companyemployee companyemployee = productattachments.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getProductattachmentsCollection().remove(productattachments);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = productattachments.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getProductattachmentsCollection().remove(productattachments);
                companyemployee1 = em.merge(companyemployee1);
            }
            Companyemployee companyemployee2 = productattachments.getCompanyemployee2();
            if (companyemployee2 != null) {
                companyemployee2.getProductattachmentsCollection().remove(productattachments);
                companyemployee2 = em.merge(companyemployee2);
            }
            Contenttypes contenttype = productattachments.getContenttype();
            if (contenttype != null) {
                contenttype.getProductattachmentsCollection().remove(productattachments);
                contenttype = em.merge(contenttype);
            }
            em.remove(productattachments);
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

    public List<Productattachments> findProductattachmentsEntities() {
        return findProductattachmentsEntities(true, -1, -1);
    }

    public List<Productattachments> findProductattachmentsEntities(int maxResults, int firstResult) {
        return findProductattachmentsEntities(false, maxResults, firstResult);
    }

    private List<Productattachments> findProductattachmentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productattachments.class));
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

    public Productattachments findProductattachments(ProductattachmentsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productattachments.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductattachmentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productattachments> rt = cq.from(Productattachments.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
