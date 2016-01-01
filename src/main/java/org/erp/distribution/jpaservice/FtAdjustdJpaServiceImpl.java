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
import org.erp.distribution.model.FtAdjustd;
import org.erp.distribution.model.FtOpnamed;

public class FtAdjustdJpaServiceImpl extends GenericJpaServiceImpl<FtAdjustd, Serializable> implements FtAdjustdJpaService{

	@Override
	public List<FtAdjustd> findAll(FWarehouse fWarehouse, FProduct fProduct,
			Date trDate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtAdjustd a WHERE a.ftadjusthBean.fwarehouseBean.id LIKE :fWarehouse"
	            		+ " AND a.ftadjusthBean.trdate = :trdate"
	            		+ " AND a.fproductBean.id = :fProduct"
	            		+ " ORDER BY a.ftadjusthBean.trdate ASC, a.ftadjusthBean.norek ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtAdjustd> list = em.createQuery(query)
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

}
