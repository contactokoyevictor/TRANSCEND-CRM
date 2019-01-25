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
import com.sivotek.crm.persistent.dao.entities.Crmvisitor;
import com.sivotek.crm.persistent.dao.entities.Crmvisitorcontacts;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmvisitorcontactsaddress;
import com.sivotek.crm.persistent.dao.entities.CrmvisitorcontactsaddressPK;
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
public class CrmvisitorcontactsaddressJpaController implements Serializable {

    public CrmvisitorcontactsaddressJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmvisitorcontactsaddressJpaController(){
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

    public void create(Crmvisitorcontactsaddress crmvisitorcontactsaddress) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmvisitorcontactsaddress.getCrmvisitorcontactsaddressPK() == null) {
            crmvisitorcontactsaddress.setCrmvisitorcontactsaddressPK(new CrmvisitorcontactsaddressPK());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmvisitor crmvisitor = crmvisitorcontactsaddress.getCrmvisitor();
            if (crmvisitor != null) {
                crmvisitor = em.getReference(crmvisitor.getClass(), crmvisitor.getCrmvisitorPK());
                crmvisitorcontactsaddress.setCrmvisitor(crmvisitor);
            }
            Crmvisitorcontacts crmvisitorcontacts = crmvisitorcontactsaddress.getCrmvisitorcontacts();
            if (crmvisitorcontacts != null) {
                crmvisitorcontacts = em.getReference(crmvisitorcontacts.getClass(), crmvisitorcontacts.getCrmvisitorcontactsPK());
                crmvisitorcontactsaddress.setCrmvisitorcontacts(crmvisitorcontacts);
            }
            Companyemployee companyemployee = crmvisitorcontactsaddress.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmvisitorcontactsaddress.setCompanyemployee(companyemployee);
            }
            em.persist(crmvisitorcontactsaddress);
            if (crmvisitor != null) {
                crmvisitor.getCrmvisitorcontactsaddressCollection().add(crmvisitorcontactsaddress);
                crmvisitor = em.merge(crmvisitor);
            }
            if (crmvisitorcontacts != null) {
                crmvisitorcontacts.getCrmvisitorcontactsaddressCollection().add(crmvisitorcontactsaddress);
                crmvisitorcontacts = em.merge(crmvisitorcontacts);
            }
            if (companyemployee != null) {
                companyemployee.getCrmvisitorcontactsaddressCollection().add(crmvisitorcontactsaddress);
                companyemployee = em.merge(companyemployee);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmvisitorcontactsaddress(crmvisitorcontactsaddress.getCrmvisitorcontactsaddressPK()) != null) {
                throw new PreexistingEntityException("Crmvisitorcontactsaddress " + crmvisitorcontactsaddress + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmvisitorcontactsaddress crmvisitorcontactsaddress) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmvisitorcontactsaddress persistentCrmvisitorcontactsaddress = em.find(Crmvisitorcontactsaddress.class, crmvisitorcontactsaddress.getCrmvisitorcontactsaddressPK());
            Crmvisitor crmvisitorOld = persistentCrmvisitorcontactsaddress.getCrmvisitor();
            Crmvisitor crmvisitorNew = crmvisitorcontactsaddress.getCrmvisitor();
            Crmvisitorcontacts crmvisitorcontactsOld = persistentCrmvisitorcontactsaddress.getCrmvisitorcontacts();
            Crmvisitorcontacts crmvisitorcontactsNew = crmvisitorcontactsaddress.getCrmvisitorcontacts();
            Companyemployee companyemployeeOld = persistentCrmvisitorcontactsaddress.getCompanyemployee();
            Companyemployee companyemployeeNew = crmvisitorcontactsaddress.getCompanyemployee();
            if (crmvisitorNew != null) {
                crmvisitorNew = em.getReference(crmvisitorNew.getClass(), crmvisitorNew.getCrmvisitorPK());
                crmvisitorcontactsaddress.setCrmvisitor(crmvisitorNew);
            }
            if (crmvisitorcontactsNew != null) {
                crmvisitorcontactsNew = em.getReference(crmvisitorcontactsNew.getClass(), crmvisitorcontactsNew.getCrmvisitorcontactsPK());
                crmvisitorcontactsaddress.setCrmvisitorcontacts(crmvisitorcontactsNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmvisitorcontactsaddress.setCompanyemployee(companyemployeeNew);
            }
            crmvisitorcontactsaddress = em.merge(crmvisitorcontactsaddress);
            if (crmvisitorOld != null && !crmvisitorOld.equals(crmvisitorNew)) {
                crmvisitorOld.getCrmvisitorcontactsaddressCollection().remove(crmvisitorcontactsaddress);
                crmvisitorOld = em.merge(crmvisitorOld);
            }
            if (crmvisitorNew != null && !crmvisitorNew.equals(crmvisitorOld)) {
                crmvisitorNew.getCrmvisitorcontactsaddressCollection().add(crmvisitorcontactsaddress);
                crmvisitorNew = em.merge(crmvisitorNew);
            }
            if (crmvisitorcontactsOld != null && !crmvisitorcontactsOld.equals(crmvisitorcontactsNew)) {
                crmvisitorcontactsOld.getCrmvisitorcontactsaddressCollection().remove(crmvisitorcontactsaddress);
                crmvisitorcontactsOld = em.merge(crmvisitorcontactsOld);
            }
            if (crmvisitorcontactsNew != null && !crmvisitorcontactsNew.equals(crmvisitorcontactsOld)) {
                crmvisitorcontactsNew.getCrmvisitorcontactsaddressCollection().add(crmvisitorcontactsaddress);
                crmvisitorcontactsNew = em.merge(crmvisitorcontactsNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmvisitorcontactsaddressCollection().remove(crmvisitorcontactsaddress);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmvisitorcontactsaddressCollection().add(crmvisitorcontactsaddress);
                companyemployeeNew = em.merge(companyemployeeNew);
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
                CrmvisitorcontactsaddressPK id = crmvisitorcontactsaddress.getCrmvisitorcontactsaddressPK();
                if (findCrmvisitorcontactsaddress(id) == null) {
                    throw new NonexistentEntityException("The crmvisitorcontactsaddress with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmvisitorcontactsaddressPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmvisitorcontactsaddress crmvisitorcontactsaddress;
            try {
                crmvisitorcontactsaddress = em.getReference(Crmvisitorcontactsaddress.class, id);
                crmvisitorcontactsaddress.getCrmvisitorcontactsaddressPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmvisitorcontactsaddress with id " + id + " no longer exists.", enfe);
            }
            Crmvisitor crmvisitor = crmvisitorcontactsaddress.getCrmvisitor();
            if (crmvisitor != null) {
                crmvisitor.getCrmvisitorcontactsaddressCollection().remove(crmvisitorcontactsaddress);
                crmvisitor = em.merge(crmvisitor);
            }
            Crmvisitorcontacts crmvisitorcontacts = crmvisitorcontactsaddress.getCrmvisitorcontacts();
            if (crmvisitorcontacts != null) {
                crmvisitorcontacts.getCrmvisitorcontactsaddressCollection().remove(crmvisitorcontactsaddress);
                crmvisitorcontacts = em.merge(crmvisitorcontacts);
            }
            Companyemployee companyemployee = crmvisitorcontactsaddress.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmvisitorcontactsaddressCollection().remove(crmvisitorcontactsaddress);
                companyemployee = em.merge(companyemployee);
            }
            em.remove(crmvisitorcontactsaddress);
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

    public List<Crmvisitorcontactsaddress> findCrmvisitorcontactsaddressEntities() {
        return findCrmvisitorcontactsaddressEntities(true, -1, -1);
    }

    public List<Crmvisitorcontactsaddress> findCrmvisitorcontactsaddressEntities(int maxResults, int firstResult) {
        return findCrmvisitorcontactsaddressEntities(false, maxResults, firstResult);
    }

    private List<Crmvisitorcontactsaddress> findCrmvisitorcontactsaddressEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmvisitorcontactsaddress.class));
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

    public Crmvisitorcontactsaddress findCrmvisitorcontactsaddress(CrmvisitorcontactsaddressPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmvisitorcontactsaddress.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmvisitorcontactsaddressCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmvisitorcontactsaddress> rt = cq.from(Crmvisitorcontactsaddress.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
