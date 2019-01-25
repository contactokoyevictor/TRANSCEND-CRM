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
import com.sivotek.crm.persistent.dao.entities.Componenttype;
import com.sivotek.crm.persistent.dao.entities.Companyproduct;
import com.sivotek.crm.persistent.dao.entities.Companyemployee;
import com.sivotek.crm.persistent.dao.entities.Crmcampaignposition;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.crm.persistent.dao.entities.Crmworkorder;
import com.sivotek.crm.persistent.dao.entities.Componentattachments;
import com.sivotek.crm.persistent.dao.entities.Productcomponents;
import com.sivotek.crm.persistent.dao.entities.ProductcomponentsPK;
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
public class ProductcomponentsJpaController implements Serializable {

    public ProductcomponentsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ProductcomponentsJpaController(){
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

    public void create(Productcomponents productcomponents) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (productcomponents.getProductcomponentsPK() == null) {
            productcomponents.setProductcomponentsPK(new ProductcomponentsPK());
        }
        if (productcomponents.getCrmcampaignpositionCollection() == null) {
            productcomponents.setCrmcampaignpositionCollection(new ArrayList<Crmcampaignposition>());
        }
        if (productcomponents.getCrmworkorderCollection() == null) {
            productcomponents.setCrmworkorderCollection(new ArrayList<Crmworkorder>());
        }
        if (productcomponents.getComponentattachmentsCollection() == null) {
            productcomponents.setComponentattachmentsCollection(new ArrayList<Componentattachments>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company company = productcomponents.getCompany();
            if (company != null) {
                company = em.getReference(company.getClass(), company.getCompanyPK());
                productcomponents.setCompany(company);
            }
            Componenttype componenttype = productcomponents.getComponenttype();
            if (componenttype != null) {
                componenttype = em.getReference(componenttype.getClass(), componenttype.getComponenttypePK());
                productcomponents.setComponenttype(componenttype);
            }
            Companyproduct companyproduct = productcomponents.getCompanyproduct();
            if (companyproduct != null) {
                companyproduct = em.getReference(companyproduct.getClass(), companyproduct.getCompanyproductPK());
                productcomponents.setCompanyproduct(companyproduct);
            }
            Companyemployee companyemployee = productcomponents.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee = em.getReference(companyemployee.getClass(), companyemployee.getCompanyemployeePK());
                productcomponents.setCompanyemployee(companyemployee);
            }
            Companyemployee companyemployee1 = productcomponents.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1 = em.getReference(companyemployee1.getClass(), companyemployee1.getCompanyemployeePK());
                productcomponents.setCompanyemployee1(companyemployee1);
            }
            Collection<Crmcampaignposition> attachedCrmcampaignpositionCollection = new ArrayList<Crmcampaignposition>();
            for (Crmcampaignposition crmcampaignpositionCollectionCrmcampaignpositionToAttach : productcomponents.getCrmcampaignpositionCollection()) {
                crmcampaignpositionCollectionCrmcampaignpositionToAttach = em.getReference(crmcampaignpositionCollectionCrmcampaignpositionToAttach.getClass(), crmcampaignpositionCollectionCrmcampaignpositionToAttach.getCrmcampaignpositionPK());
                attachedCrmcampaignpositionCollection.add(crmcampaignpositionCollectionCrmcampaignpositionToAttach);
            }
            productcomponents.setCrmcampaignpositionCollection(attachedCrmcampaignpositionCollection);
            Collection<Crmworkorder> attachedCrmworkorderCollection = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionCrmworkorderToAttach : productcomponents.getCrmworkorderCollection()) {
                crmworkorderCollectionCrmworkorderToAttach = em.getReference(crmworkorderCollectionCrmworkorderToAttach.getClass(), crmworkorderCollectionCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollection.add(crmworkorderCollectionCrmworkorderToAttach);
            }
            productcomponents.setCrmworkorderCollection(attachedCrmworkorderCollection);
            Collection<Componentattachments> attachedComponentattachmentsCollection = new ArrayList<Componentattachments>();
            for (Componentattachments componentattachmentsCollectionComponentattachmentsToAttach : productcomponents.getComponentattachmentsCollection()) {
                componentattachmentsCollectionComponentattachmentsToAttach = em.getReference(componentattachmentsCollectionComponentattachmentsToAttach.getClass(), componentattachmentsCollectionComponentattachmentsToAttach.getComponentattachmentsPK());
                attachedComponentattachmentsCollection.add(componentattachmentsCollectionComponentattachmentsToAttach);
            }
            productcomponents.setComponentattachmentsCollection(attachedComponentattachmentsCollection);
            em.persist(productcomponents);
            if (company != null) {
                company.getProductcomponentsCollection().add(productcomponents);
                company = em.merge(company);
            }
            if (componenttype != null) {
                componenttype.getProductcomponentsCollection().add(productcomponents);
                componenttype = em.merge(componenttype);
            }
            if (companyproduct != null) {
                companyproduct.getProductcomponentsCollection().add(productcomponents);
                companyproduct = em.merge(companyproduct);
            }
            if (companyemployee != null) {
                companyemployee.getProductcomponentsCollection().add(productcomponents);
                companyemployee = em.merge(companyemployee);
            }
            if (companyemployee1 != null) {
                companyemployee1.getProductcomponentsCollection().add(productcomponents);
                companyemployee1 = em.merge(companyemployee1);
            }
            for (Crmcampaignposition crmcampaignpositionCollectionCrmcampaignposition : productcomponents.getCrmcampaignpositionCollection()) {
                Productcomponents oldProductcomponentsOfCrmcampaignpositionCollectionCrmcampaignposition = crmcampaignpositionCollectionCrmcampaignposition.getProductcomponents();
                crmcampaignpositionCollectionCrmcampaignposition.setProductcomponents(productcomponents);
                crmcampaignpositionCollectionCrmcampaignposition = em.merge(crmcampaignpositionCollectionCrmcampaignposition);
                if (oldProductcomponentsOfCrmcampaignpositionCollectionCrmcampaignposition != null) {
                    oldProductcomponentsOfCrmcampaignpositionCollectionCrmcampaignposition.getCrmcampaignpositionCollection().remove(crmcampaignpositionCollectionCrmcampaignposition);
                    oldProductcomponentsOfCrmcampaignpositionCollectionCrmcampaignposition = em.merge(oldProductcomponentsOfCrmcampaignpositionCollectionCrmcampaignposition);
                }
            }
            for (Crmworkorder crmworkorderCollectionCrmworkorder : productcomponents.getCrmworkorderCollection()) {
                Productcomponents oldProductcomponentsOfCrmworkorderCollectionCrmworkorder = crmworkorderCollectionCrmworkorder.getProductcomponents();
                crmworkorderCollectionCrmworkorder.setProductcomponents(productcomponents);
                crmworkorderCollectionCrmworkorder = em.merge(crmworkorderCollectionCrmworkorder);
                if (oldProductcomponentsOfCrmworkorderCollectionCrmworkorder != null) {
                    oldProductcomponentsOfCrmworkorderCollectionCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionCrmworkorder);
                    oldProductcomponentsOfCrmworkorderCollectionCrmworkorder = em.merge(oldProductcomponentsOfCrmworkorderCollectionCrmworkorder);
                }
            }
            for (Componentattachments componentattachmentsCollectionComponentattachments : productcomponents.getComponentattachmentsCollection()) {
                Productcomponents oldProductcomponentsOfComponentattachmentsCollectionComponentattachments = componentattachmentsCollectionComponentattachments.getProductcomponents();
                componentattachmentsCollectionComponentattachments.setProductcomponents(productcomponents);
                componentattachmentsCollectionComponentattachments = em.merge(componentattachmentsCollectionComponentattachments);
                if (oldProductcomponentsOfComponentattachmentsCollectionComponentattachments != null) {
                    oldProductcomponentsOfComponentattachmentsCollectionComponentattachments.getComponentattachmentsCollection().remove(componentattachmentsCollectionComponentattachments);
                    oldProductcomponentsOfComponentattachmentsCollectionComponentattachments = em.merge(oldProductcomponentsOfComponentattachmentsCollectionComponentattachments);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProductcomponents(productcomponents.getProductcomponentsPK()) != null) {
                throw new PreexistingEntityException("Productcomponents " + productcomponents + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productcomponents productcomponents) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Productcomponents persistentProductcomponents = em.find(Productcomponents.class, productcomponents.getProductcomponentsPK());
            Company companyOld = persistentProductcomponents.getCompany();
            Company companyNew = productcomponents.getCompany();
            Componenttype componenttypeOld = persistentProductcomponents.getComponenttype();
            Componenttype componenttypeNew = productcomponents.getComponenttype();
            Companyproduct companyproductOld = persistentProductcomponents.getCompanyproduct();
            Companyproduct companyproductNew = productcomponents.getCompanyproduct();
            Companyemployee companyemployeeOld = persistentProductcomponents.getCompanyemployee();
            Companyemployee companyemployeeNew = productcomponents.getCompanyemployee();
            Companyemployee companyemployee1Old = persistentProductcomponents.getCompanyemployee1();
            Companyemployee companyemployee1New = productcomponents.getCompanyemployee1();
            Collection<Crmcampaignposition> crmcampaignpositionCollectionOld = persistentProductcomponents.getCrmcampaignpositionCollection();
            Collection<Crmcampaignposition> crmcampaignpositionCollectionNew = productcomponents.getCrmcampaignpositionCollection();
            Collection<Crmworkorder> crmworkorderCollectionOld = persistentProductcomponents.getCrmworkorderCollection();
            Collection<Crmworkorder> crmworkorderCollectionNew = productcomponents.getCrmworkorderCollection();
            Collection<Componentattachments> componentattachmentsCollectionOld = persistentProductcomponents.getComponentattachmentsCollection();
            Collection<Componentattachments> componentattachmentsCollectionNew = productcomponents.getComponentattachmentsCollection();
            List<String> illegalOrphanMessages = null;
            for (Crmcampaignposition crmcampaignpositionCollectionOldCrmcampaignposition : crmcampaignpositionCollectionOld) {
                if (!crmcampaignpositionCollectionNew.contains(crmcampaignpositionCollectionOldCrmcampaignposition)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmcampaignposition " + crmcampaignpositionCollectionOldCrmcampaignposition + " since its productcomponents field is not nullable.");
                }
            }
            for (Crmworkorder crmworkorderCollectionOldCrmworkorder : crmworkorderCollectionOld) {
                if (!crmworkorderCollectionNew.contains(crmworkorderCollectionOldCrmworkorder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Crmworkorder " + crmworkorderCollectionOldCrmworkorder + " since its productcomponents field is not nullable.");
                }
            }
            for (Componentattachments componentattachmentsCollectionOldComponentattachments : componentattachmentsCollectionOld) {
                if (!componentattachmentsCollectionNew.contains(componentattachmentsCollectionOldComponentattachments)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Componentattachments " + componentattachmentsCollectionOldComponentattachments + " since its productcomponents field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (companyNew != null) {
                companyNew = em.getReference(companyNew.getClass(), companyNew.getCompanyPK());
                productcomponents.setCompany(companyNew);
            }
            if (componenttypeNew != null) {
                componenttypeNew = em.getReference(componenttypeNew.getClass(), componenttypeNew.getComponenttypePK());
                productcomponents.setComponenttype(componenttypeNew);
            }
            if (companyproductNew != null) {
                companyproductNew = em.getReference(companyproductNew.getClass(), companyproductNew.getCompanyproductPK());
                productcomponents.setCompanyproduct(companyproductNew);
            }
            if (companyemployeeNew != null) {
                companyemployeeNew = em.getReference(companyemployeeNew.getClass(), companyemployeeNew.getCompanyemployeePK());
                productcomponents.setCompanyemployee(companyemployeeNew);
            }
            if (companyemployee1New != null) {
                companyemployee1New = em.getReference(companyemployee1New.getClass(), companyemployee1New.getCompanyemployeePK());
                productcomponents.setCompanyemployee1(companyemployee1New);
            }
            Collection<Crmcampaignposition> attachedCrmcampaignpositionCollectionNew = new ArrayList<Crmcampaignposition>();
            for (Crmcampaignposition crmcampaignpositionCollectionNewCrmcampaignpositionToAttach : crmcampaignpositionCollectionNew) {
                crmcampaignpositionCollectionNewCrmcampaignpositionToAttach = em.getReference(crmcampaignpositionCollectionNewCrmcampaignpositionToAttach.getClass(), crmcampaignpositionCollectionNewCrmcampaignpositionToAttach.getCrmcampaignpositionPK());
                attachedCrmcampaignpositionCollectionNew.add(crmcampaignpositionCollectionNewCrmcampaignpositionToAttach);
            }
            crmcampaignpositionCollectionNew = attachedCrmcampaignpositionCollectionNew;
            productcomponents.setCrmcampaignpositionCollection(crmcampaignpositionCollectionNew);
            Collection<Crmworkorder> attachedCrmworkorderCollectionNew = new ArrayList<Crmworkorder>();
            for (Crmworkorder crmworkorderCollectionNewCrmworkorderToAttach : crmworkorderCollectionNew) {
                crmworkorderCollectionNewCrmworkorderToAttach = em.getReference(crmworkorderCollectionNewCrmworkorderToAttach.getClass(), crmworkorderCollectionNewCrmworkorderToAttach.getCrmworkorderPK());
                attachedCrmworkorderCollectionNew.add(crmworkorderCollectionNewCrmworkorderToAttach);
            }
            crmworkorderCollectionNew = attachedCrmworkorderCollectionNew;
            productcomponents.setCrmworkorderCollection(crmworkorderCollectionNew);
            Collection<Componentattachments> attachedComponentattachmentsCollectionNew = new ArrayList<Componentattachments>();
            for (Componentattachments componentattachmentsCollectionNewComponentattachmentsToAttach : componentattachmentsCollectionNew) {
                componentattachmentsCollectionNewComponentattachmentsToAttach = em.getReference(componentattachmentsCollectionNewComponentattachmentsToAttach.getClass(), componentattachmentsCollectionNewComponentattachmentsToAttach.getComponentattachmentsPK());
                attachedComponentattachmentsCollectionNew.add(componentattachmentsCollectionNewComponentattachmentsToAttach);
            }
            componentattachmentsCollectionNew = attachedComponentattachmentsCollectionNew;
            productcomponents.setComponentattachmentsCollection(componentattachmentsCollectionNew);
            productcomponents = em.merge(productcomponents);
            if (companyOld != null && !companyOld.equals(companyNew)) {
                companyOld.getProductcomponentsCollection().remove(productcomponents);
                companyOld = em.merge(companyOld);
            }
            if (companyNew != null && !companyNew.equals(companyOld)) {
                companyNew.getProductcomponentsCollection().add(productcomponents);
                companyNew = em.merge(companyNew);
            }
            if (componenttypeOld != null && !componenttypeOld.equals(componenttypeNew)) {
                componenttypeOld.getProductcomponentsCollection().remove(productcomponents);
                componenttypeOld = em.merge(componenttypeOld);
            }
            if (componenttypeNew != null && !componenttypeNew.equals(componenttypeOld)) {
                componenttypeNew.getProductcomponentsCollection().add(productcomponents);
                componenttypeNew = em.merge(componenttypeNew);
            }
            if (companyproductOld != null && !companyproductOld.equals(companyproductNew)) {
                companyproductOld.getProductcomponentsCollection().remove(productcomponents);
                companyproductOld = em.merge(companyproductOld);
            }
            if (companyproductNew != null && !companyproductNew.equals(companyproductOld)) {
                companyproductNew.getProductcomponentsCollection().add(productcomponents);
                companyproductNew = em.merge(companyproductNew);
            }
            if (companyemployeeOld != null && !companyemployeeOld.equals(companyemployeeNew)) {
                companyemployeeOld.getProductcomponentsCollection().remove(productcomponents);
                companyemployeeOld = em.merge(companyemployeeOld);
            }
            if (companyemployeeNew != null && !companyemployeeNew.equals(companyemployeeOld)) {
                companyemployeeNew.getProductcomponentsCollection().add(productcomponents);
                companyemployeeNew = em.merge(companyemployeeNew);
            }
            if (companyemployee1Old != null && !companyemployee1Old.equals(companyemployee1New)) {
                companyemployee1Old.getProductcomponentsCollection().remove(productcomponents);
                companyemployee1Old = em.merge(companyemployee1Old);
            }
            if (companyemployee1New != null && !companyemployee1New.equals(companyemployee1Old)) {
                companyemployee1New.getProductcomponentsCollection().add(productcomponents);
                companyemployee1New = em.merge(companyemployee1New);
            }
            for (Crmcampaignposition crmcampaignpositionCollectionNewCrmcampaignposition : crmcampaignpositionCollectionNew) {
                if (!crmcampaignpositionCollectionOld.contains(crmcampaignpositionCollectionNewCrmcampaignposition)) {
                    Productcomponents oldProductcomponentsOfCrmcampaignpositionCollectionNewCrmcampaignposition = crmcampaignpositionCollectionNewCrmcampaignposition.getProductcomponents();
                    crmcampaignpositionCollectionNewCrmcampaignposition.setProductcomponents(productcomponents);
                    crmcampaignpositionCollectionNewCrmcampaignposition = em.merge(crmcampaignpositionCollectionNewCrmcampaignposition);
                    if (oldProductcomponentsOfCrmcampaignpositionCollectionNewCrmcampaignposition != null && !oldProductcomponentsOfCrmcampaignpositionCollectionNewCrmcampaignposition.equals(productcomponents)) {
                        oldProductcomponentsOfCrmcampaignpositionCollectionNewCrmcampaignposition.getCrmcampaignpositionCollection().remove(crmcampaignpositionCollectionNewCrmcampaignposition);
                        oldProductcomponentsOfCrmcampaignpositionCollectionNewCrmcampaignposition = em.merge(oldProductcomponentsOfCrmcampaignpositionCollectionNewCrmcampaignposition);
                    }
                }
            }
            for (Crmworkorder crmworkorderCollectionNewCrmworkorder : crmworkorderCollectionNew) {
                if (!crmworkorderCollectionOld.contains(crmworkorderCollectionNewCrmworkorder)) {
                    Productcomponents oldProductcomponentsOfCrmworkorderCollectionNewCrmworkorder = crmworkorderCollectionNewCrmworkorder.getProductcomponents();
                    crmworkorderCollectionNewCrmworkorder.setProductcomponents(productcomponents);
                    crmworkorderCollectionNewCrmworkorder = em.merge(crmworkorderCollectionNewCrmworkorder);
                    if (oldProductcomponentsOfCrmworkorderCollectionNewCrmworkorder != null && !oldProductcomponentsOfCrmworkorderCollectionNewCrmworkorder.equals(productcomponents)) {
                        oldProductcomponentsOfCrmworkorderCollectionNewCrmworkorder.getCrmworkorderCollection().remove(crmworkorderCollectionNewCrmworkorder);
                        oldProductcomponentsOfCrmworkorderCollectionNewCrmworkorder = em.merge(oldProductcomponentsOfCrmworkorderCollectionNewCrmworkorder);
                    }
                }
            }
            for (Componentattachments componentattachmentsCollectionNewComponentattachments : componentattachmentsCollectionNew) {
                if (!componentattachmentsCollectionOld.contains(componentattachmentsCollectionNewComponentattachments)) {
                    Productcomponents oldProductcomponentsOfComponentattachmentsCollectionNewComponentattachments = componentattachmentsCollectionNewComponentattachments.getProductcomponents();
                    componentattachmentsCollectionNewComponentattachments.setProductcomponents(productcomponents);
                    componentattachmentsCollectionNewComponentattachments = em.merge(componentattachmentsCollectionNewComponentattachments);
                    if (oldProductcomponentsOfComponentattachmentsCollectionNewComponentattachments != null && !oldProductcomponentsOfComponentattachmentsCollectionNewComponentattachments.equals(productcomponents)) {
                        oldProductcomponentsOfComponentattachmentsCollectionNewComponentattachments.getComponentattachmentsCollection().remove(componentattachmentsCollectionNewComponentattachments);
                        oldProductcomponentsOfComponentattachmentsCollectionNewComponentattachments = em.merge(oldProductcomponentsOfComponentattachmentsCollectionNewComponentattachments);
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
                ProductcomponentsPK id = productcomponents.getProductcomponentsPK();
                if (findProductcomponents(id) == null) {
                    throw new NonexistentEntityException("The productcomponents with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProductcomponentsPK id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Productcomponents productcomponents;
            try {
                productcomponents = em.getReference(Productcomponents.class, id);
                productcomponents.getProductcomponentsPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productcomponents with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Crmcampaignposition> crmcampaignpositionCollectionOrphanCheck = productcomponents.getCrmcampaignpositionCollection();
            for (Crmcampaignposition crmcampaignpositionCollectionOrphanCheckCrmcampaignposition : crmcampaignpositionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productcomponents (" + productcomponents + ") cannot be destroyed since the Crmcampaignposition " + crmcampaignpositionCollectionOrphanCheckCrmcampaignposition + " in its crmcampaignpositionCollection field has a non-nullable productcomponents field.");
            }
            Collection<Crmworkorder> crmworkorderCollectionOrphanCheck = productcomponents.getCrmworkorderCollection();
            for (Crmworkorder crmworkorderCollectionOrphanCheckCrmworkorder : crmworkorderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productcomponents (" + productcomponents + ") cannot be destroyed since the Crmworkorder " + crmworkorderCollectionOrphanCheckCrmworkorder + " in its crmworkorderCollection field has a non-nullable productcomponents field.");
            }
            Collection<Componentattachments> componentattachmentsCollectionOrphanCheck = productcomponents.getComponentattachmentsCollection();
            for (Componentattachments componentattachmentsCollectionOrphanCheckComponentattachments : componentattachmentsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Productcomponents (" + productcomponents + ") cannot be destroyed since the Componentattachments " + componentattachmentsCollectionOrphanCheckComponentattachments + " in its componentattachmentsCollection field has a non-nullable productcomponents field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Company company = productcomponents.getCompany();
            if (company != null) {
                company.getProductcomponentsCollection().remove(productcomponents);
                company = em.merge(company);
            }
            Componenttype componenttype = productcomponents.getComponenttype();
            if (componenttype != null) {
                componenttype.getProductcomponentsCollection().remove(productcomponents);
                componenttype = em.merge(componenttype);
            }
            Companyproduct companyproduct = productcomponents.getCompanyproduct();
            if (companyproduct != null) {
                companyproduct.getProductcomponentsCollection().remove(productcomponents);
                companyproduct = em.merge(companyproduct);
            }
            Companyemployee companyemployee = productcomponents.getCompanyemployee();
            if (companyemployee != null) {
                companyemployee.getProductcomponentsCollection().remove(productcomponents);
                companyemployee = em.merge(companyemployee);
            }
            Companyemployee companyemployee1 = productcomponents.getCompanyemployee1();
            if (companyemployee1 != null) {
                companyemployee1.getProductcomponentsCollection().remove(productcomponents);
                companyemployee1 = em.merge(companyemployee1);
            }
            em.remove(productcomponents);
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

    public List<Productcomponents> findProductcomponentsEntities() {
        return findProductcomponentsEntities(true, -1, -1);
    }

    public List<Productcomponents> findProductcomponentsEntities(int maxResults, int firstResult) {
        return findProductcomponentsEntities(false, maxResults, firstResult);
    }

    private List<Productcomponents> findProductcomponentsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productcomponents.class));
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

    public Productcomponents findProductcomponents(ProductcomponentsPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productcomponents.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductcomponentsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productcomponents> rt = cq.from(Productcomponents.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
