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
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtStocktransferd;

public class FtStocktransferdJpaServiceImpl extends GenericJpaServiceImpl<FtStocktransferd, Serializable> implements FtStocktransferdJpaService{

	@Override
	public List<FtStocktransferd> findAllFrom(FWarehouse fWarehouseFrom,
			FProduct fProduct, Date trDate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtStocktransferd a WHERE a.ftstocktransferhBean.fwarehouseBeanFrom.id LIKE :fWarehouse"
	            		+ " AND a.ftstocktransferhBean.trdate = :trdate"
	            		+ " AND a.fproductBean.id = :fProduct"
	            		+ " ORDER BY a.ftstocktransferhBean.trdate ASC, a.ftstocktransferhBean.refno ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtStocktransferd> list = em.createQuery(query)
	            		.setParameter("fWarehouse", fWarehouseFrom.getId())
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
	public List<FtStocktransferd> findAllTo(FWarehouse fWarehouseTo,
			FProduct fProduct, Date trDate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtStocktransferd a WHERE a.ftstocktransferhBean.fwarehouseBeanTo.id LIKE :fWarehouse"
	            		+ " AND a.ftstocktransferhBean.trdate = :trdate"
	            		+ " AND a.fproductBean.id = :fProduct"
	            		+ " ORDER BY a.ftstocktransferhBean.trdate ASC, a.ftstocktransferhBean.refno ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtStocktransferd> list = em.createQuery(query)
	            		.setParameter("fWarehouse", fWarehouseTo.getId())
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
