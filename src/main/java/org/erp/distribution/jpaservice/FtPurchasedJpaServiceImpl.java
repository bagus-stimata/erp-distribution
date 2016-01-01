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
import org.erp.distribution.model.FtPurchased;
import org.erp.distribution.model.FtSalesd;

public class FtPurchasedJpaServiceImpl extends GenericJpaServiceImpl<FtPurchased, Serializable> implements FtPurchasedJpaService{

	@Override
	public List<FtPurchased> findAll(FWarehouse fWarehouse, FProduct fProduct,
			Date trDate, String tipefaktur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtPurchased a WHERE a.ftpurchasehBean.fwarehouseBean.id LIKE :fWarehouse"
	            		+ " AND a.ftpurchasehBean.invoicedate = :trdate"
	            		+ " AND a.ftpurchasehBean.tipefaktur LIKE :tipefaktur AND a.fproductBean.id = :fProduct"
	            		+ " ORDER BY a.ftpurchasehBean.invoicedate ASC, a.ftpurchasehBean.nopo ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtPurchased> list = em.createQuery(query)
	            		.setParameter("fWarehouse", fWarehouse.getId())
	            		.setParameter("trdate", trDate)
	            		.setParameter("tipefaktur", tipefaktur)
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
