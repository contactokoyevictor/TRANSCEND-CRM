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
import com.sivotek.crm.persistent.dao.entities.Crmworkordertype;
import com.sivotek.crm.persistent.dao.entities.Crmprojectticketmanagement;
import com.sivotek.crm.persistent.dao.entities.Companycustomer;
import com.sivotek.crm.persistent.dao.entities.Companyproduct;
import com.sivotek.crm.persistent.dao.entities.Productcomponents;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import com.sivotek.crm.persistent.dao.entities.CrmworkorderPK;
import com.sivotek.crm.persistent.dao.entities.Crmworkorderresolution;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.Collection;
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
public class CrmworkorderJpaController implements Serializable {

    public CrmworkorderJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CrmworkorderJpaController(){
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

    public void create(Crmworkorder crmworkorder) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (crmworkorder.getCrmworkorderPK() == null) {
            crmworkorder.setCrmworkorderPK(new CrmworkorderPK());
        }
        if (crmworkorder.getCrmworkorderresolutionCollection() == null) {
            crmworkorder.setCrmworkorderresolutionCollection(new ArrayList<Crmworkorderresolution>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = crmworkorder.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                crmworkorder.setCompany(company);
            }
            Crmworkordertype crmworkordertype = crmworkorder.getCrmworkordertype();
            if (crmworkordertype != null) {
                crmworkordertype = em.getReference(crmworkordertype.getClass(), crmworkordertype.getCrmworkordertypePK());
                crmworkorder.setCrmworkordertype(crmworkordertype);
            }
            Crmprojectticketmanagement crmprojectticketmanagement = crmworkorder.getCrmprojectticketmanagement();
            if (crmprojectticketmanagement != null) {
                crmprojectticketmanagement = em.getReference(crmprojectticketmanagement.getClass(), crmprojectticketmanagement.getCrmprojectticketmanagementPK());
                crmworkorder.setCrmprojectticketmanagement(crmprojectticketmanagement);
            }
            Companycustomer companycustomer = crmworkorder.getCompanycustomer();
            if (companycustomer != null) {
                companycustomer = em.getReference(companycustomer.getClass(), companycustomer.getCompanycustomerPK());
                crmworkorder.setCompanycustomer(companycustomer);
            }
            Companyproduct companyproduct = crmworkorder.getCompanyproduct();
            if (companyproduct != null) {
                companyproduct = em.getReference(companyproduct.getClass(), companyproduct.getCompanyproductPK());
                crmworkorder.setCompanyproduct(companyproduct);
            }
            Productcomponents productcomponents = crmworkorder.getProductcomponents();
            if (productcomponents != null) {
                productcomponents = em.getReference(productcomponents.getClass(), productcomponents.getProductcomponentsPK());
                crmworkorder.setProductcomponents(productcomponents);
            }
            Companyemployee companyemployee = crmworkorder.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                crmworkorder.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = crmworkorder.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                crmworkorder.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmworkorderresolution> attachedCrmworkorderresolutionCollection = new ArrayList<Crmworkorderresolution>();
            for (Crmworkorderresolution crmworkorderresolutionCollectionCrmworkorderresolutionToAttach : crmworkorder.getCrmworkorderresolutionCollection()) {
                crmworkorderresolutionCollectionCrmworkorderresolutionToAttach = em.getReference(crmworkorderresolutionCollectionCrmworkorderresolutionToAttach.getClass(), crmworkorderresolutionCollectionCrmworkorderresolutionToAttach.getCrmworkorderresolutionPK());
                attachedCrmworkorderresolutionCollection.add(crmworkorderresolutionCollectionCrmworkorderresolutionToAttach);
            }
            crmworkorder.setCrmworkorderresolutionCollection(attachedCrmworkorderresolutionCollection);
            em.persist(crmworkorder);
            if (company != null) {
                company.getCrmworkorderCollection().add(crmworkorder);
                company = em.merge(company);
            }
            if (crmworkordertype != null) {
                crmworkordertype.getCrmworkorderCollection().add(crmworkorder);
                crmworkordertype = em.merge(crmworkordertype);
            }
            if (crmprojectticketmanagement != null) {
                crmprojectticketmanagement.getCrmworkorderCollection().add(crmworkorder);
                crmprojectticketmanagement = em.merge(crmprojectticketmanagement);
            }
            if (companycustomer != null) {
                companycustomer.getCrmworkorderCollection().add(crmworkorder);
                companycustomer = em.merge(companycustomer);
            }
            if (companyproduct != null) {
                companyproduct.getCrmworkorderCollection().add(crmworkorder);
                companyproduct = em.merge(companyproduct);
            }
            if (productcomponents != null) {
                productcomponents.getCrmworkorderCollection().add(crmworkorder);
                productcomponents = em.merge(productcomponents);
            }
            if (companyemployee != null) {
                companyemployee.getCrmworkorderCollection().add(crmworkorder);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCrmworkorderCollection().add(crmworkorder);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmworkorderresolution crmworkorderresolutionCollectionCrmworkorderresolution : crmworkorder.getCrmworkorderresolutionCollection()) {
                Crmworkorder oldCrmworkorderOfCrmworkorderresolutionCollectionCrmworkorderresolution = crmworkorderresolutionCollectionCrmworkorderresolution.getCrmworkorder();
                crmworkorderresolutionCollectionCrmworkorderresolution.setCrmworkorder(crmworkorder);
                crmworkorderresolutionCollectionCrmworkorderresolution = em.merge(crmworkorderresolutionCollectionCrmworkorderresolution);
                if (oldCrmworkorderOfCrmworkorderresolutionCollectionCrmworkorderresolution != null) {
                    oldCrmworkorderOfCrmworkorderresolutionCollectionCrmworkorderresolution.getCrmworkorderresolutionCollection().remove(crmworkorderresolutionCollectionCrmworkorderresolution);
                    oldCrmworkorderOfCrmworkorderresolutionCollectionCrmworkorderresolution = em.merge(oldCrmworkorderOfCrmworkorderresolutionCollectionCrmworkorderresolution);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCrmworkorder(crmworkorder.getCrmworkorderPK()) != null) {
                throw new PreexistingEntityException("Crmworkorder " + crmworkorder + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Crmworkorder crmworkorder) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmworkorder persistentCrmworkorder = em.find(Crmworkorder.class, crmworkorder.getCrmworkorderPK());
            Company companyOld = persistentCrmworkorder.getCompany();
            Company companyNew = crmworkorder.getCompany();
            Crmworkordertype crmworkordertypeOld = persistentCrmworkorder.getCrmworkordertype();
            Crmworkordertype crmworkordertypeNew = crmworkorder.getCrmworkordertype();
            Crmprojectticketmanagement crmprojectticketmanagementOld = persistentCrmworkorder.getCrmprojectticketmanagement();
            Crmprojectticketmanagement crmprojectticketmanagementNew = crmworkorder.getCrmprojectticketmanagement();
            Companycustomer companycustomerOld = persistentCrmworkorder.getCompanycustomer();
            Companycustomer companycustomerNew = crmworkorder.getCompanycustomer();
            Companyproduct companyproductOld = persistentCrmworkorder.getCompanyproduct();
            Companyproduct companyproductNew = crmworkorder.getCompanyproduct();
            Productcomponents productcomponentsOld = persistentCrmworkorder.getProductcomponents();
            Productcomponents productcomponentsNew = crmworkorder.getProductcomponents();
            Companyemployee companyemployeeOld = persistentCrmworkorder.getCompanyemployee();
            Companyemployee companyemployeeNew = crmworkorder.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCrmworkorder.getCompanyemployee1();
            Companyemployee companyemployee1New = crmworkorder.getCompanyemployee1();
            Collection<Crmworkorderresolution> crmworkorderresolutionCollectionOld = persistentCrmworkorder.getCrmworkorderresolutionCollection();
            Collection<Crmworkorderresolution> crmworkorderresolutionCollectionNew = crmworkorder.getCrmworkorderresolutionCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmworkorderresolution crmworkorderresolutionCollectionOldCrmworkorderresolution : crmworkorderresolutionCollectionOld) {
                if (!crmworkorderresolutionCollectionNew.contains(crmworkorderresolutionCollectionOldCrmworkorderresolution)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkorderresolution " + crmworkorderresolutionCollectionOldCrmworkorderresolution + " since its crmworkorder field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                crmworkorder.setCompany(companyNew);
            }
            if (crmworkordertypeNew != null) {
                crmworkordertypeNew = em.getReference(crmworkordertypeNew.getClass(), crmworkordertypeNew.getCrmworkordertypePK());
                crmworkorder.setCrmworkordertype(crmworkordertypeNew);
            }
            if (crmprojectticketmanagementNew != null) {
                crmprojectticketmanagementNew = em.getReference(crmprojectticketmanagementNew.getClass(), crmprojectticketmanagementNew.getCrmprojectticketmanagementPK());
                crmworkorder.setCrmprojectticketmanagement(crmprojectticketmanagementNew);
            }
            if (companycustomerNew != null) {
                companycustomerNew = em.getReference(companycustomerNew.getClass(), companycustomerNew.getCompanycustomerPK());
                crmworkorder.setCompanycustomer(companycustomerNew);
            }
            if (companyproductNew != null) {
                companyproductNew = em.getReference(companyproductNew.getClass(), companyproductNew.getCompanyproductPK());
                crmworkorder.setCompanyproduct(companyproductNew);
            }
            if (productcomponentsNew != null) {
                productcomponentsNew = em.getReference(productcomponentsNew.getClass(), productcomponentsNew.getProductcomponentsPK());
                crmworkorder.setProductcomponents(productcomponentsNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                crmworkorder.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                crmworkorder.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmworkorderresolution> attachedCrmworkorderresolutionCollectionNew = new ArrayList<Crmworkorderresolution>();
            for (Crmworkorderresolution crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach : crmworkorderresolutionCollectionNew) {
                crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach = em.getReference(crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach.getClass(), crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach.getCrmworkorderresolutionPK());
                attachedCrmworkorderresolutionCollectionNew.add(crmworkorderresolutionCollectionNewCrmworkorderresolutionToAttach);
            }
            crmworkorderresolutionCollectionNew = attachedCrmworkorderresolutionCollectionNew;
            crmworkorder.setCrmworkorderresolutionCollection(crmworkorderresolutionCollectionNew);
            crmworkorder = em.merge(crmworkorder);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCrmworkorderCollection().remove(crmworkorder);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCrmworkorderCollection().add(crmworkorder);
                companyNew = em.merge(companyNew);
            }
            if (crmworkordertypeOld != null && !crmworkordertypeOld.equals(crmworkordertypeNew)) {
                crmworkordertypeOld.getCrmworkorderCollection().remove(crmworkorder);
                crmworkordertypeOld = em.merge(crmworkordertypeOld);
            }
            if (crmworkordertypeNew != null && !crmworkordertypeNew.equals(crmworkordertypeOld)) {
                crmworkordertypeNew.getCrmworkorderCollection().add(crmworkorder);
                crmworkordertypeNew = em.merge(crmworkordertypeNew);
            }
            if (crmprojectticketmanagementOld != null && !crmprojectticketmanagementOld.equals(crmprojectticketmanagementNew)) {
                crmprojectticketmanagementOld.getCrmworkorderCollection().remove(crmworkorder);
                crmprojectticketmanagementOld = em.merge(crmprojectticketmanagementOld);
            }
            if (crmprojectticketmanagementNew != null && !crmprojectticketmanagementNew.equals(crmprojectticketmanagementOld)) {
                crmprojectticketmanagementNew.getCrmworkorderCollection().add(crmworkorder);
                crmprojectticketmanagementNew = em.merge(crmprojectticketmanagementNew);
            }
            if (companycustomerOld != null && !companycustomerOld.equals(companycustomerNew)) {
                companycustomerOld.getCrmworkorderCollection().remove(crmworkorder);
                companycustomerOld = em.merge(companycustomerOld);
            }
            if (companycustomerNew != null && !companycustomerNew.equals(companycustomerOld)) {
                companycustomerNew.getCrmworkorderCollection().add(crmworkorder);
                companycustomerNew = em.merge(companycustomerNew);
            }
            if (companyproductOld != null && !companyproductOld.equals(companyproductNew)) {
                companyproductOld.getCrmworkorderCollection().remove(crmworkorder);
                companyproductOld = em.merge(companyproductOld);
            }
            if (companyproductNew != null && !companyproductNew.equals(companyproductOld)) {
                companyproductNew.getCrmworkorderCollection().add(crmworkorder);
                companyproductNew = em.merge(companyproductNew);
            }
            if (productcomponentsOld != null && !productcomponentsOld.equals(productcomponentsNew)) {
                productcomponentsOld.getCrmworkorderCollection().remove(crmworkorder);
                productcomponentsOld = em.merge(productcomponentsOld);
            }
            if (productcomponentsNew != null && !productcomponentsNew.equals(productcomponentsOld)) {
                productcomponentsNew.getCrmworkorderCollection().add(crmworkorder);
                productcomponentsNew = em.merge(productcomponentsNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCrmworkorderCollection().remove(crmworkorder);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCrmworkorderCollection().add(crmworkorder);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCrmworkorderCollection().remove(crmworkorder);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCrmworkorderCollection().add(crmworkorder);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmworkorderresolution crmworkorderresolutionCollectionNewCrmworkorderresolution : crmworkorderresolutionCollectionNew) {
                if (!crmworkorderresolutionCollectionOld.contains(crmworkorderresolutionCollectionNewCrmworkorderresolution)) {
                    Crmworkorder oldCrmworkorderOfCrmworkorderresolutionCollectionNewCrmworkorderresolution = crmworkorderresolutionCollectionNewCrmworkorderresolution.getCrmworkorder();
                    crmworkorderresolutionCollectionNewCrmworkorderresolution.setCrmworkorder(crmworkorder);
                    crmworkorderresolutionCollectionNewCrmworkorderresolution = em.merge(crmworkorderresolutionCollectionNewCrmworkorderresolution);
                    if (oldCrmworkorderOfCrmworkorderresolutionCollectionNewCrmworkorderresolution != null && !oldCrmworkorderOfCrmworkorderresolutionCollectionNewCrmworkorderresolution.equals(crmworkorder)) {
                        oldCrmworkorderOfCrmworkorderresolutionCollectionNewCrmworkorderresolution.getCrmworkorderresolutionCollection().remove(crmworkorderresolutionCollectionNewCrmworkorderresolution);
                        oldCrmworkorderOfCrmworkorderresolutionCollectionNewCrmworkorderresolution = em.merge(oldCrmworkorderOfCrmworkorderresolutionCollectionNewCrmworkorderresolution);
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
                CrmworkorderPK id = crmworkorder.getCrmworkorderPK();
                if (findCrmworkorder(id) == null) {
                    throw new NonexistentEntityException("The crmworkorder with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CrmworkorderPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Crmworkorder crmworkorder;
            try {
                crmworkorder = em.getReference(Crmworkorder.class, id);
                crmworkorder.getCrmworkorderPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The crmworkorder with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmworkorderresolution> crmworkorderresolutionCollectionOrphanCheck = crmworkorder.getCrmworkorderresolutionCollection();
            for (Crmworkorderresolution crmworkorderresolutionCollectionOrphanCheckCrmworkorderresolution : crmworkorderresolutionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Crmworkorder (" + crmworkorder + ") cannot be destroyed since the Crmworkorderresolution " + crmworkorderresolutionCollectionOrphanCheckCrmworkorderresolution + " in its crmworkorderresolutionCollection field has a non-nullable crmworkorder field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = crmworkorder.getCompany();
            if (company != null) {
                company.getCrmworkorderCollection().remove(crmworkorder);
                company = em.merge(company);
            }
            Crmworkordertype crmworkordertype = crmworkorder.getCrmworkordertype();
            if (crmworkordertype != null) {
                crmworkordertype.getCrmworkorderCollection().remove(crmworkorder);
                crmworkordertype = em.merge(crmworkordertype);
            }
            Crmprojectticketmanagement crmprojectticketmanagement = crmworkorder.getCrmprojectticketmanagement();
            if (crmprojectticketmanagement != null) {
                crmprojectticketmanagement.getCrmworkorderCollection().remove(crmworkorder);
                crmprojectticketmanagement = em.merge(crmprojectticketmanagement);
            }
            Companycustomer companycustomer = crmworkorder.getCompanycustomer();
            if (companycustomer != null) {
                companycustomer.getCrmworkorderCollection().remove(crmworkorder);
                companycustomer = em.merge(companycustomer);
            }
            Companyproduct companyproduct = crmworkorder.getCompanyproduct();
            if (companyproduct != null) {
                companyproduct.getCrmworkorderCollection().remove(crmworkorder);
                companyproduct = em.merge(companyproduct);
            }
            Productcomponents productcomponents = crmworkorder.getProductcomponents();
            if (productcomponents != null) {
                productcomponents.getCrmworkorderCollection().remove(crmworkorder);
                productcomponents = em.merge(productcomponents);
            }
            Companyemployee companyemployee = crmworkorder.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCrmworkorderCollection().remove(crmworkorder);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = crmworkorder.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCrmworkorderCollection().remove(crmworkorder);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(crmworkorder);
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

    public List<Crmworkorder> findCrmworkorderEntities() {
        return findCrmworkorderEntities(true, -1, -1);
    }

    public List<Crmworkorder> findCrmworkorderEntities(int maxResults, int firstResult) {
        return findCrmworkorderEntities(false, maxResults, firstResult);
    }

    private List<Crmworkorder> findCrmworkorderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Crmworkorder.class));
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

    public Crmworkorder findCrmworkorder(CrmworkorderPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Crmworkorder.class, id);
        } finally {
            em.close();
        }
    }

    public int getCrmworkorderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Crmworkorder> rt = cq.from(Crmworkorder.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
