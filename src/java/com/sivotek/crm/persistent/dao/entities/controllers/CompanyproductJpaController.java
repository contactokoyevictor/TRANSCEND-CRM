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
import com.sivotek.crm.persistent.dao.entities.Companyproducttype;
import com.sivotek.crm.persistent.dao.entities.Companyproductcategory;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Companyproduct;
import com.sivotek.crm.persistent.dao.entities.CompanyproductPK;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignposition;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import com.sivotek.crm.persistent.dao.entities.Productcomponents;
import com.sivotek.crm.persistent.dao.entities.controllers.exceptions.IllegalOrphanException;
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
public class CompanyproductJpaController implements Serializable {

    public CompanyproductJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public CompanyproductJpaController(){
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

    public void create(Companyproduct companyproduct) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companyproduct.getCompanyproductPK() == null) {
            companyproduct.setCompanyproductPK(new CompanyproductPK());
        }
        if (companyproduct.getCrmcampaignpositionCollection() == null) {
            companyproduct.setCrmcampaignpositionCollection(new ArrayList<Crmcampaignposition>());
        }
        if (companyproduct.getCrmworkorderCollection() == null) {
            companyproduct.setCrmworkorderCollection(new ArrayList<Crmworkorder>());
        }
        if (companyproduct.getProductcomponentsCollection() == null) {
            companyproduct.setProductcomponentsCollection(new ArrayList<Productcomponents>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = companyproduct.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                companyproduct.setCompany(company);
            }
            Companyproducttype companyproducttype = companyproduct.getCompanyproducttype();
            if (companyproducttype != null) {
                companyproducttype = em.getReference(companyproducttype.getClass(), companyproducttype.getCompanyproducttypePK());
                companyproduct.setCompanyproducttype(companyproducttype);
            }
            Companyproductcategory companyproductcategory = companyproduct.getCompanyproductcategory();
            if (companyproductcategory != null) {
                companyproductcategory = em.getReference(companyproductcategory.getClass(), companyproductcategory.getCompanyproductcategoryPK());
                companyproduct.setCompanyproductcategory(companyproductcategory);
            }
            Companyemployee companyemployee = companyproduct.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                companyproduct.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = companyproduct.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                companyproduct.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmcampaignposition> attachedCrmcampaignpositionCollection = new ArrayList<Crmcampaignposition>();
            for (Crmcampaignposition crmcampaignpositionCollectionCrmcampaignpositionToAttach : companyproduct.getCrmcampaignpositionCollection()) {
                crmcampaignpositionCollectionCrmcampaignpositionToAttach = em.getReference(crmcampaignpositionCollectionCrmcampaignpositionToAttach.getClass(), crmcampaignpositionCollectionCrmcampaignpositionToAttach.getCrmcampaignpositionPK());
                attachedCrmcampaignpositionCollection.add(crmcampaignpositionCollectionCrmcampaignpositionToAttach);
            }
            companyproduct.setCrmcampaignpositionCollection(attachedCrmcampaignpositionCollection);
            Collection<Crmworkorder> attachedCrmworkorderCollection = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionCrmworkorderToAttach : companyproduct.getCrmworkorderCollection()) {
                crmworkorderCollectionCrmworkorderToAttach = em.getReference(crmworkorderCollectionCrmworkorderToAttach.getClass(), crmworkorderCollectionCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollection.add(crmworkorderCollectionCrmworkorderToAttach);
            }
            companyproduct.setCrmworkorderCollection(attachedCrmworkorderCollection);
            Collection<Productcomponents> attachedProductcomponentsCollection = new ArrayList<Productcomponents>();
            for (Productcomponents productcomponentsCollectionProductcomponentsToAttach : companyproduct.getProductcomponentsCollection()) {
                productcomponentsCollectionProductcomponentsToAttach = em.getReference(productcomponentsCollectionProductcomponentsToAttach.getClass(), productcomponentsCollectionProductcomponentsToAttach.getProductcomponentsPK());
                attachedProductcomponentsCollection.add(productcomponentsCollectionProductcomponentsToAttach);
            }
            companyproduct.setProductcomponentsCollection(attachedProductcomponentsCollection);
            em.persist(companyproduct);
            if (company != null) {
                company.getCompanyproductCollection().add(companyproduct);
                company = em.merge(company);
            }
            if (companyproducttype != null) {
                companyproducttype.getCompanyproductCollection().add(companyproduct);
                companyproducttype = em.merge(companyproducttype);
            }
            if (companyproductcategory != null) {
                companyproductcategory.getCompanyproductCollection().add(companyproduct);
                companyproductcategory = em.merge(companyproductcategory);
            }
            if (companyemployee != null) {
                companyemployee.getCompanyproductCollection().add(companyproduct);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getCompanyproductCollection().add(companyproduct);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmcampaignposition crmcampaignpositionCollectionCrmcampaignposition : companyproduct.getCrmcampaignpositionCollection()) {
                Companyproduct oldCompanyproductOfCrmcampaignpositionCollectionCrmcampaignposition = crmcampaignpositionCollectionCrmcampaignposition.getCompanyproduct();
                crmcampaignpositionCollectionCrmcampaignposition.setCompanyproduct(companyproduct);
                crmcampaignpositionCollectionCrmcampaignposition = em.merge(crmcampaignpositionCollectionCrmcampaignposition);
                if (oldCompanyproductOfCrmcampaignpositionCollectionCrmcampaignposition != null) {
                    oldCompanyproductOfCrmcampaignpositionCollectionCrmcampaignposition.getCrmcampaignpositionCollection().remove(crmcampaignpositionCollectionCrmcampaignposition);
                    oldCompanyproductOfCrmcampaignpositionCollectionCrmcampaignposition = em.merge(oldCompanyproductOfCrmcampaignpositionCollectionCrmcampaignposition);
                }
            }
            for (Crmworkorder crmworkorderCollectionCrmworkorder : companyproduct.getCrmworkorderCollection()) {
                Companyproduct oldCompanyproductOfCrmworkorderCollectionCrmworkorder = crmworkorderCollectionCrmworkorder.getCompanyproduct();
                crmworkorderCollectionCrmworkorder.setCompanyproduct(companyproduct);
                crmworkorderCollectionCrmworkorder = em.merge(crmworkorderCollectionCrmworkorder);
                if (oldCompanyproductOfCrmworkorderCollectionCrmworkorder != null) {
                    oldCompanyproductOfCrmworkorderCollectionCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionCrmworkorder);
                    oldCompanyproductOfCrmworkorderCollectionCrmworkorder = em.merge(oldCompanyproductOfCrmworkorderCollectionCrmworkorder);
                }
            }
            for (Productcomponents productcomponentsCollectionProductcomponents : companyproduct.getProductcomponentsCollection()) {
                Companyproduct oldCompanyproductOfProductcomponentsCollectionProductcomponents = productcomponentsCollectionProductcomponents.getCompanyproduct();
                productcomponentsCollectionProductcomponents.setCompanyproduct(companyproduct);
                productcomponentsCollectionProductcomponents = em.merge(productcomponentsCollectionProductcomponents);
                if (oldCompanyproductOfProductcomponentsCollectionProductcomponents != null) {
                    oldCompanyproductOfProductcomponentsCollectionProductcomponents.getProductcomponentsCollection().remove(productcomponentsCollectionProductcomponents);
                    oldCompanyproductOfProductcomponentsCollectionProductcomponents = em.merge(oldCompanyproductOfProductcomponentsCollectionProductcomponents);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanyproduct(companyproduct.getCompanyproductPK()) != null) {
                throw new PreexistingEntityException("Companyproduct " + companyproduct + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Companyproduct companyproduct) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyproduct persistentCompanyproduct = em.find(Companyproduct.class, companyproduct.getCompanyproductPK());
            Company companyOld = persistentCompanyproduct.getCompany();
            Company companyNew = companyproduct.getCompany();
            Companyproducttype companyproducttypeOld = persistentCompanyproduct.getCompanyproducttype();
            Companyproducttype companyproducttypeNew = companyproduct.getCompanyproducttype();
            Companyproductcategory companyproductcategoryOld = persistentCompanyproduct.getCompanyproductcategory();
            Companyproductcategory companyproductcategoryNew = companyproduct.getCompanyproductcategory();
            Companyemployee companyemployeeOld = persistentCompanyproduct.getCompanyemployee();
            Companyemployee companyemployeeNew = companyproduct.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentCompanyproduct.getCompanyemployee1();
            Companyemployee companyemployee1New = companyproduct.getCompanyemployee1();
            Collection<Crmcampaignposition> crmcampaignpositionCollectionOld = persistentCompanyproduct.getCrmcampaignpositionCollection();
            Collection<Crmcampaignposition> crmcampaignpositionCollectionNew = companyproduct.getCrmcampaignpositionCollection();
            Collection<Crmworkorder> crmworkorderCollectionOld = persistentCompanyproduct.getCrmworkorderCollection();
            Collection<Crmworkorder> crmworkorderCollectionNew = companyproduct.getCrmworkorderCollection();
            Collection<Productcomponents> productcomponentsCollectionOld = persistentCompanyproduct.getProductcomponentsCollection();
            Collection<Productcomponents> productcomponentsCollectionNew = companyproduct.getProductcomponentsCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmcampaignposition crmcampaignpositionCollectionOldCrmcampaignposition : crmcampaignpositionCollectionOld) {
                if (!crmcampaignpositionCollectionNew.contains(crmcampaignpositionCollectionOldCrmcampaignposition)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignposition " + crmcampaignpositionCollectionOldCrmcampaignposition + " since its companyproduct field is not nullable.");
                }
            }
            for (Crmworkorder crmworkorderCollectionOldCrmworkorder : crmworkorderCollectionOld) {
                if (!crmworkorderCollectionNew.contains(crmworkorderCollectionOldCrmworkorder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkorder " + crmworkorderCollectionOldCrmworkorder + " since its companyproduct field is not nullable.");
                }
            }
            for (Productcomponents productcomponentsCollectionOldProductcomponents : productcomponentsCollectionOld) {
                if (!productcomponentsCollectionNew.contains(productcomponentsCollectionOldProductcomponents)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Productcomponents " + productcomponentsCollectionOldProductcomponents + " since its companyproduct field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                companyproduct.setCompany(companyNew);
            }
            if (companyproducttypeNew != null) {
                companyproducttypeNew = em.getReference(companyproducttypeNew.getClass(), companyproducttypeNew.getCompanyproducttypePK());
                companyproduct.setCompanyproducttype(companyproducttypeNew);
            }
            if (companyproductcategoryNew != null) {
                companyproductcategoryNew = em.getReference(companyproductcategoryNew.getClass(), companyproductcategoryNew.getCompanyproductcategoryPK());
                companyproduct.setCompanyproductcategory(companyproductcategoryNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                companyproduct.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                companyproduct.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmcampaignposition> attachedCrmcampaignpositionCollectionNew = new ArrayList<Crmcampaignposition>();
            for (Crmcampaignposition crmcampaignpositionCollectionNewCrmcampaignpositionToAttach : crmcampaignpositionCollectionNew) {
                crmcampaignpositionCollectionNewCrmcampaignpositionToAttach = em.getReference(crmcampaignpositionCollectionNewCrmcampaignpositionToAttach.getClass(), crmcampaignpositionCollectionNewCrmcampaignpositionToAttach.getCrmcampaignpositionPK());
                attachedCrmcampaignpositionCollectionNew.add(crmcampaignpositionCollectionNewCrmcampaignpositionToAttach);
            }
            crmcampaignpositionCollectionNew = attachedCrmcampaignpositionCollectionNew;
            companyproduct.setCrmcampaignpositionCollection(crmcampaignpositionCollectionNew);
            Collection<Crmworkorder> attachedCrmworkorderCollectionNew = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionNewCrmworkorderToAttach : crmworkorderCollectionNew) {
                crmworkorderCollectionNewCrmworkorderToAttach = em.getReference(crmworkorderCollectionNewCrmworkorderToAttach.getClass(), crmworkorderCollectionNewCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollectionNew.add(crmworkorderCollectionNewCrmworkorderToAttach);
            }
            crmworkorderCollectionNew = attachedCrmworkorderCollectionNew;
            companyproduct.setCrmworkorderCollection(crmworkorderCollectionNew);
            Collection<Productcomponents> attachedProductcomponentsCollectionNew = new ArrayList<Productcomponents>();
            for (Productcomponents productcomponentsCollectionNewProductcomponentsToAttach : productcomponentsCollectionNew) {
                productcomponentsCollectionNewProductcomponentsToAttach = em.getReference(productcomponentsCollectionNewProductcomponentsToAttach.getClass(), productcomponentsCollectionNewProductcomponentsToAttach.getProductcomponentsPK());
                attachedProductcomponentsCollectionNew.add(productcomponentsCollectionNewProductcomponentsToAttach);
            }
            productcomponentsCollectionNew = attachedProductcomponentsCollectionNew;
            companyproduct.setProductcomponentsCollection(productcomponentsCollectionNew);
            companyproduct = em.merge(companyproduct);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getCompanyproductCollection().remove(companyproduct);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getCompanyproductCollection().add(companyproduct);
                companyNew = em.merge(companyNew);
            }
            if (companyproducttypeOld != null && !companyproducttypeOld.equals(companyproducttypeNew)) {
                companyproducttypeOld.getCompanyproductCollection().remove(companyproduct);
                companyproducttypeOld = em.merge(companyproducttypeOld);
            }
            if (companyproducttypeNew != null && !companyproducttypeNew.equals(companyproducttypeOld)) {
                companyproducttypeNew.getCompanyproductCollection().add(companyproduct);
                companyproducttypeNew = em.merge(companyproducttypeNew);
            }
            if (companyproductcategoryOld != null && !companyproductcategoryOld.equals(companyproductcategoryNew)) {
                companyproductcategoryOld.getCompanyproductCollection().remove(companyproduct);
                companyproductcategoryOld = em.merge(companyproductcategoryOld);
            }
            if (companyproductcategoryNew != null && !companyproductcategoryNew.equals(companyproductcategoryOld)) {
                companyproductcategoryNew.getCompanyproductCollection().add(companyproduct);
                companyproductcategoryNew = em.merge(companyproductcategoryNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getCompanyproductCollection().remove(companyproduct);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getCompanyproductCollection().add(companyproduct);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getCompanyproductCollection().remove(companyproduct);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getCompanyproductCollection().add(companyproduct);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmcampaignposition crmcampaignpositionCollectionNewCrmcampaignposition : crmcampaignpositionCollectionNew) {
                if (!crmcampaignpositionCollectionOld.contains(crmcampaignpositionCollectionNewCrmcampaignposition)) {
                    Companyproduct oldCompanyproductOfCrmcampaignpositionCollectionNewCrmcampaignposition = crmcampaignpositionCollectionNewCrmcampaignposition.getCompanyproduct();
                    crmcampaignpositionCollectionNewCrmcampaignposition.setCompanyproduct(companyproduct);
                    crmcampaignpositionCollectionNewCrmcampaignposition = em.merge(crmcampaignpositionCollectionNewCrmcampaignposition);
                    if (oldCompanyproductOfCrmcampaignpositionCollectionNewCrmcampaignposition != null && !oldCompanyproductOfCrmcampaignpositionCollectionNewCrmcampaignposition.equals(companyproduct)) {
                        oldCompanyproductOfCrmcampaignpositionCollectionNewCrmcampaignposition.getCrmcampaignpositionCollection().remove(crmcampaignpositionCollectionNewCrmcampaignposition);
                        oldCompanyproductOfCrmcampaignpositionCollectionNewCrmcampaignposition = em.merge(oldCompanyproductOfCrmcampaignpositionCollectionNewCrmcampaignposition);
                    }
                }
            }
            for (Crmworkorder crmworkorderCollectionNewCrmworkorder : crmworkorderCollectionNew) {
                if (!crmworkorderCollectionOld.contains(crmworkorderCollectionNewCrmworkorder)) {
                    Companyproduct oldCompanyproductOfCrmworkorderCollectionNewCrmworkorder = crmworkorderCollectionNewCrmworkorder.getCompanyproduct();
                    crmworkorderCollectionNewCrmworkorder.setCompanyproduct(companyproduct);
                    crmworkorderCollectionNewCrmworkorder = em.merge(crmworkorderCollectionNewCrmworkorder);
                    if (oldCompanyproductOfCrmworkorderCollectionNewCrmworkorder != null && !oldCompanyproductOfCrmworkorderCollectionNewCrmworkorder.equals(companyproduct)) {
                        oldCompanyproductOfCrmworkorderCollectionNewCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionNewCrmworkorder);
                        oldCompanyproductOfCrmworkorderCollectionNewCrmworkorder = em.merge(oldCompanyproductOfCrmworkorderCollectionNewCrmworkorder);
                    }
                }
            }
            for (Productcomponents productcomponentsCollectionNewProductcomponents : productcomponentsCollectionNew) {
                if (!productcomponentsCollectionOld.contains(productcomponentsCollectionNewProductcomponents)) {
                    Companyproduct oldCompanyproductOfProductcomponentsCollectionNewProductcomponents = productcomponentsCollectionNewProductcomponents.getCompanyproduct();
                    productcomponentsCollectionNewProductcomponents.setCompanyproduct(companyproduct);
                    productcomponentsCollectionNewProductcomponents = em.merge(productcomponentsCollectionNewProductcomponents);
                    if (oldCompanyproductOfProductcomponentsCollectionNewProductcomponents != null && !oldCompanyproductOfProductcomponentsCollectionNewProductcomponents.equals(companyproduct)) {
                        oldCompanyproductOfProductcomponentsCollectionNewProductcomponents.getProductcomponentsCollection().remove(productcomponentsCollectionNewProductcomponents);
                        oldCompanyproductOfProductcomponentsCollectionNewProductcomponents = em.merge(oldCompanyproductOfProductcomponentsCollectionNewProductcomponents);
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
                CompanyproductPK id = companyproduct.getCompanyproductPK();
                if (findCompanyproduct(id) == null) {
                    throw new NonexistentEntityException("The companyproduct with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(CompanyproductPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Companyproduct companyproduct;
            try {
                companyproduct = em.getReference(Companyproduct.class, id);
                companyproduct.getCompanyproductPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyproduct with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmcampaignposition> crmcampaignpositionCollectionOrphanCheck = companyproduct.getCrmcampaignpositionCollection();
            for (Crmcampaignposition crmcampaignpositionCollectionOrphanCheckCrmcampaignposition : crmcampaignpositionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyproduct (" + companyproduct + ") cannot be destroyed since the Crmcampaignposition " + crmcampaignpositionCollectionOrphanCheckCrmcampaignposition + " in its crmcampaignpositionCollection field has a non-nullable companyproduct field.");
            }
            Collection<Crmworkorder> crmworkorderCollectionOrphanCheck = companyproduct.getCrmworkorderCollection();
            for (Crmworkorder crmworkorderCollectionOrphanCheckCrmworkorder : crmworkorderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyproduct (" + companyproduct + ") cannot be destroyed since the Crmworkorder " + crmworkorderCollectionOrphanCheckCrmworkorder + " in its crmworkorderCollection field has a non-nullable companyproduct field.");
            }
            Collection<Productcomponents> productcomponentsCollectionOrphanCheck = companyproduct.getProductcomponentsCollection();
            for (Productcomponents productcomponentsCollectionOrphanCheckProductcomponents : productcomponentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Companyproduct (" + companyproduct + ") cannot be destroyed since the Productcomponents " + productcomponentsCollectionOrphanCheckProductcomponents + " in its productcomponentsCollection field has a non-nullable companyproduct field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = companyproduct.getCompany();
            if (company != null) {
                company.getCompanyproductCollection().remove(companyproduct);
                company = em.merge(company);
            }
            Companyproducttype companyproducttype = companyproduct.getCompanyproducttype();
            if (companyproducttype != null) {
                companyproducttype.getCompanyproductCollection().remove(companyproduct);
                companyproducttype = em.merge(companyproducttype);
            }
            Companyproductcategory companyproductcategory = companyproduct.getCompanyproductcategory();
            if (companyproductcategory != null) {
                companyproductcategory.getCompanyproductCollection().remove(companyproduct);
                companyproductcategory = em.merge(companyproductcategory);
            }
            Companyemployee companyemployee = companyproduct.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getCompanyproductCollection().remove(companyproduct);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = companyproduct.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getCompanyproductCollection().remove(companyproduct);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(companyproduct);
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

    public List<Companyproduct> findCompanyproductEntities() {
        return findCompanyproductEntities(true, -1, -1);
    }

    public List<Companyproduct> findCompanyproductEntities(int maxResults, int firstResult) {
        return findCompanyproductEntities(false, maxResults, firstResult);
    }

    private List<Companyproduct> findCompanyproductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Companyproduct.class));
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

    public Companyproduct findCompanyproduct(CompanyproductPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Companyproduct.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyproductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Companyproduct> rt = cq.from(Companyproduct.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
