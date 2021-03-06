package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtOpnamed;
import org.erp.distribution.model.FtPurchased;

public class FtOpnamedJpaServiceImpl extends GenericJpaServiceImpl<FtOpnamed, Serializable> implements FtOpnamedJpaService{

	@Override
	public List<FtOpnamed> findAll(FWarehouse fWarehouse, FProduct fProduct,
			Date trDate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtOpnamed a WHERE a.ftopnamehBean.fwarehouseBean.id LIKE :fWarehouse"
	            		+ " AND a.ftopnamehBean.trdate = :trdate"
	            		+ " AND a.fproductBean.id = :fProduct"
	            		+ " ORDER BY a.ftopnamehBean.trdate ASC, a.ftopnamehBean.norek ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtOpnamed> list = em.createQuery(query)
	            		.setParameter("fWarehouse", fWarehouse.getId())
	            		.setParameter("trdate", trDate)
	            		.setParameter("fProduct", fProduct.getId())
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
	public List<FtOpnamed> findAllByRefno(long refno) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtOpnamed a WHERE a.id.refno= :refno "
	            		+ " ORDER BY a.id.refno ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtOpnamed> list = em.createQuery(query)
	            		.setParameter("refno", refno)
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
