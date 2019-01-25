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
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Company;
import com.sivotek.crm.persistent.dao.entities.Componenttype;
import com.sivotek.crm.persistent.dao.entities.ComponenttypePK;
import com.sivotek.crm.persistent.dao.entities.Productcomponents;
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
public class ComponenttypeJpaController implements Serializable {

    public ComponenttypeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ComponenttypeJpaController(){
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

    public void create(Componenttype componenttype) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (componenttype.getComponenttypePK() == null) {
            componenttype.setComponenttypePK(new ComponenttypePK());
        }
        if (componenttype.getProductcomponentsCollection() == null) {
            componenttype.setProductcomponentsCollection(new ArrayList<Productcomponents>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyemployee companyemployee = componenttype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                componenttype.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = componenttype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                componenttype.setCompanyemployee1(companyemployee1);
            }
            Company company = componenttype.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                componenttype.setCompany(company);
            }
            Collection<Productcomponents> attachedProductcomponentsCollection = new ArrayList<Productcomponents>();
            for (Productcomponents productcomponentsCollectionProductcomponentsToAttach : componenttype.getProductcomponentsCollection()) {
                productcomponentsCollectionProductcomponentsToAttach = em.getReference(productcomponentsCollectionProductcomponentsToAttach.getClass(), productcomponentsCollectionProductcomponentsToAttach.getProductcomponentsPK());
                attachedProductcomponentsCollection.add(productcomponentsCollectionProductcomponentsToAttach);
            }
            componenttype.setProductcomponentsCollection(attachedProductcomponentsCollection);
            em.persist(componenttype);
            if (companyemployee != null) {
                companyemployee.getComponenttypeCollection().add(componenttype);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getComponenttypeCollection().add(componenttype);
                companyemployee1 = em.merge(companyemployee1);
            }
            if (company != null) {
                company.getComponenttypeCollection().add(componenttype);
                company = em.merge(company);
            }
            for (Productcomponents productcomponentsCollectionProductcomponents : componenttype.getProductcomponentsCollection()) {
                Componenttype oldComponenttypeOfProductcomponentsCollectionProductcomponents = productcomponentsCollectionProductcomponents.getComponenttype();
                productcomponentsCollectionProductcomponents.setComponenttype(componenttype);
                productcomponentsCollectionProductcomponents = em.merge(productcomponentsCollectionProductcomponents);
                if (oldComponenttypeOfProductcomponentsCollectionProductcomponents != null) {
                    oldComponenttypeOfProductcomponentsCollectionProductcomponents.getProductcomponentsCollection().remove(productcomponentsCollectionProductcomponents);
                    oldComponenttypeOfProductcomponentsCollectionProductcomponents = em.merge(oldComponenttypeOfProductcomponentsCollectionProductcomponents);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findComponenttype(componenttype.getComponenttypePK()) != null) {
                throw new PreexistingEntityException("Componenttype " + componenttype + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Componenttype componenttype) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Componenttype persistentComponenttype = em.find(Componenttype.class, componenttype.getComponenttypePK());
            Companyemployee companyemployeeOld = persistentComponenttype.getCompanyemployee();
            Companyemployee companyemployeeNew = componenttype.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentComponenttype.getCompanyemployee1();
            Companyemployee companyemployee1New = componenttype.getCompanyemployee1();
            Company companyOld = persistentComponenttype.getCompany();
            Company companyNew = componenttype.getCompany();
            Collection<Productcomponents> productcomponentsCollectionOld = persistentComponenttype.getProductcomponentsCollection();
            Collection<Productcomponents> productcomponentsCollectionNew = componenttype.getProductcomponentsCollection();
            List<String> illegalOrphanMessages = null;
            for (Productcomponents productcomponentsCollectionOldProductcomponents : productcomponentsCollectionOld) {
                if (!productcomponentsCollectionNew.contains(productcomponentsCollectionOldProductcomponents)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Productcomponents " + productcomponentsCollectionOldProductcomponents + " since its componenttype field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                componenttype.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                componenttype.setCompanyemployee1(companyemployee1New);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                componenttype.setCompany(companyNew);
            }
            Collection<Productcomponents> attachedProductcomponentsCollectionNew = new ArrayList<Productcomponents>();
            for (Productcomponents productcomponentsCollectionNewProductcomponentsToAttach : productcomponentsCollectionNew) {
                productcomponentsCollectionNewProductcomponentsToAttach = em.getReference(productcomponentsCollectionNewProductcomponentsToAttach.getClass(), productcomponentsCollectionNewProductcomponentsToAttach.getProductcomponentsPK());
                attachedProductcomponentsCollectionNew.add(productcomponentsCollectionNewProductcomponentsToAttach);
            }
            productcomponentsCollectionNew = attachedProductcomponentsCollectionNew;
            componenttype.setProductcomponentsCollection(productcomponentsCollectionNew);
            componenttype = em.merge(componenttype);
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getComponenttypeCollection().remove(componenttype);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getComponenttypeCollection().add(componenttype);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getComponenttypeCollection().remove(componenttype);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getComponenttypeCollection().add(componenttype);
                companyemployee1New = em.merge(companyemployee1New);
            }
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getComponenttypeCollection().remove(componenttype);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getComponenttypeCollection().add(componenttype);
                companyNew = em.merge(companyNew);
            }
            for (Productcomponents productcomponentsCollectionNewProductcomponents : productcomponentsCollectionNew) {
                if (!productcomponentsCollectionOld.contains(productcomponentsCollectionNewProductcomponents)) {
                    Componenttype oldComponenttypeOfProductcomponentsCollectionNewProductcomponents = productcomponentsCollectionNewProductcomponents.getComponenttype();
                    productcomponentsCollectionNewProductcomponents.setComponenttype(componenttype);
                    productcomponentsCollectionNewProductcomponents = em.merge(productcomponentsCollectionNewProductcomponents);
                    if (oldComponenttypeOfProductcomponentsCollectionNewProductcomponents != null && !oldComponenttypeOfProductcomponentsCollectionNewProductcomponents.equals(componenttype)) {
                        oldComponenttypeOfProductcomponentsCollectionNewProductcomponents.getProductcomponentsCollection().remove(productcomponentsCollectionNewProductcomponents);
                        oldComponenttypeOfProductcomponentsCollectionNewProductcomponents = em.merge(oldComponenttypeOfProductcomponentsCollectionNewProductcomponents);
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
                ComponenttypePK id = componenttype.getComponenttypePK();
                if (findComponenttype(id) == null) {
                    throw new NonexistentEntityException("The componenttype with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ComponenttypePK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Componenttype componenttype;
            try {
                componenttype = em.getReference(Componenttype.class, id);
                componenttype.getComponenttypePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The componenttype with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Productcomponents> productcomponentsCollectionOrphanCheck = componenttype.getProductcomponentsCollection();
            for (Productcomponents productcomponentsCollectionOrphanCheckProductcomponents : productcomponentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Componenttype (" + componenttype + ") cannot be destroyed since the Productcomponents " + productcomponentsCollectionOrphanCheckProductcomponents + " in its productcomponentsCollection field has a non-nullable componenttype field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Companyemployee companyemployee = componenttype.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getComponenttypeCollection().remove(componenttype);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = componenttype.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getComponenttypeCollection().remove(componenttype);
                companyemployee1 = em.merge(companyemployee1);
            }
            Company company = componenttype.getCompany();
            if (company != null) {
                company.getComponenttypeCollection().remove(componenttype);
                company = em.merge(company);
            }
            em.remove(componenttype);
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

    public List<Componenttype> findComponenttypeEntities() {
        return findComponenttypeEntities(true, -1, -1);
    }

    public List<Componenttype> findComponenttypeEntities(int maxResults, int firstResult) {
        return findComponenttypeEntities(false, maxResults, firstResult);
    }

    private List<Componenttype> findComponenttypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Componenttype.class));
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

    public Componenttype findComponenttype(ComponenttypePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Componenttype.class, id);
        } finally {
            em.close();
        }
    }

    public int getComponenttypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Componenttype> rt = cq.from(Componenttype.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
