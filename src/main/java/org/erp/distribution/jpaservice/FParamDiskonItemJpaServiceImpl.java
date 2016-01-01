package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FParamDiskonItem;
import org.erp.distribution.model.FParamDiskonNota;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FtSalesh;

public class FParamDiskonItemJpaServiceImpl extends GenericJpaServiceImpl<FParamDiskonItem, Serializable> implements FParamDiskonItemJpaService{

	@Override
	public List<FParamDiskonItem> findAllByVendorAndSubgroupActive(
			FVendor fVendorBean,  FProductgroup fProductgroupBean, 
				FCustomersubgroup fCustomersubgroupBean) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FParamDiskonItem a WHERE"
	            		+ " (a.fvendorBean.id = :fVendorBeanId OR a.allvendor=1) "
	            		+ " AND (a.fcustomersubgroupBean.id LIKE :fCustomersubgroupBeanId OR a.allsubgrup=1) "
	            		+ " AND (a.fproductgroupBean.id LIKE :fproductgroupBeanId OR a.allproductgrup=1) "
	            		+ " AND a.statusActive=1"
	            		+ " ORDER BY a.id DESC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FParamDiskonItem> list = em.createQuery(query)
	            		.setParameter("fVendorBeanId", fVendorBean.getId())
	            		.setParameter("fCustomersubgroupBeanId", fCustomersubgroupBean.getId())
	            		.setParameter("fproductgroupBeanId", fProductgroupBean.getId())
	            		.setHint(QueryHints.MAINTAIN_CACHE, HintValues.TRUE)
	            		 .getResultList();
	            em.getTransaction().commit();
	            return list;
	        } catch (PersistenceException exception) {
	            em.getTransaction().rollback();
	            throw exception;
	        } finally {
	            em.close();
	        }    
	}

	@Override
	public List<FParamDiskonItem> findAllByVendorAndSubgroup(FVendor fVendorBean) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FParamDiskonItem a WHERE"
	            		+ " a.fvendorBean.id = :fVendorBeanId "
	            		+ " ORDER BY a.id DESC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FParamDiskonItem> list = em.createQuery(query)
	            		.setParameter("fVendorBeanId", fVendorBean.getId())
	            		.setHint(QueryHints.MAINTAIN_CACHE, HintValues.TRUE)
	            		 .getResultList();
	            em.getTransaction().commit();
	            return list;
	        } catch (PersistenceException exception) {
	            em.getTransaction().rollback();
	            throw exception;
	        } finally {
	            em.close();
	        }    
	}

	@Override
	public List<FParamDiskonItem> findAllByVendorAndSubgroup(
			FCustomersubgroup fCustomersubgroupBean) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FParamDiskonItem a WHERE"
	            		+ " a.fcustomersubgroupBean.id LIKE :fCustomersubgroupBeanId"
	            		+ " ORDER BY a.id DESC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FParamDiskonItem> list = em.createQuery(query)
	            		.setParameter("fCustomersubgroupBeanId", fCustomersubgroupBean.getId())
	            		.setHint(QueryHints.MAINTAIN_CACHE, HintValues.TRUE)
	            		 .getResultList();
	            em.getTransaction().commit();
	            return list;
	        } catch (PersistenceException exception) {
	            em.getTransaction().rollback();
	            throw exception;
	        } finally {
	            em.close();
	        }    
	}

	@Override
	public List<FParamDiskonItem> findAllByVendorAndSubgroup() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FParamDiskonItem a "
	            		+ " ORDER BY a.id DESC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FParamDiskonItem> list = em.createQuery(query)
	            		.setHint(QueryHints.MAINTAIN_CACHE, HintValues.TRUE)
	            		 .getResultList();
	            em.getTransaction().commit();
	            return list;
	        } catch (PersistenceException exception) {
	            em.getTransaction().rollback();
	            throw exception;
	        } finally {
	            em.close();
	        }    
	}

}
