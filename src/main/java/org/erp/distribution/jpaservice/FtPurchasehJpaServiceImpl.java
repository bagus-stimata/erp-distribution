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
import org.erp.distribution.model.FStock;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesh;

public class FtPurchasehJpaServiceImpl extends GenericJpaServiceImpl<FtPurchaseh, Serializable> implements FtPurchasehJpaService{

	@Override
	public List<FtPurchaseh> findAllTipeFaktur(String tipefaktur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtPurchaseh a WHERE a.tipefaktur LIKE :tipefaktur"
	            		+ " ORDER BY a.nopo ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtPurchaseh> list = em.createQuery(query)
	            		.setParameter("tipefaktur", tipefaktur)
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
	public List<FtPurchaseh> findAllByOrderdate(Date podate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtPurchaseh a WHERE a.podate = :podate"
	            		+ " ORDER BY a.nopo ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtPurchaseh> list = em.createQuery(query)
	            		.setParameter("podate", podate)
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
	public List<FtPurchaseh> findAllByInvoicedate(Date invoicedate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtPurchaseh a WHERE a.invoicedate = :invoicedate"
	            		+ " ORDER BY a.nopo ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtPurchaseh> list = em.createQuery(query)
	            		.setParameter("invoicedate", invoicedate)
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
	public List<FtPurchaseh> findAllByNopo(String nopo, String tipefaktur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtPurchaseh a WHERE a.nopo LIKE :nopo "
	            		+ "AND a.tipefaktur LIKE :tipefaktur"
	            		+ " ORDER BY a.nopo ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtPurchaseh> list = em.createQuery(query)
	            		.setParameter("nopo", nopo)
	            		.setParameter("tipefaktur", tipefaktur)
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
	public List<FtPurchaseh> findAll(FWarehouse fWarehouse, 
			Date trDate, String tipefaktur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtPurchaseh a WHERE a.fwarehouseBean.id LIKE :fWarehouse AND a.invoicedate = :trdate"
	            		+ " AND a.tipefaktur LIKE :tipefaktur "
	            		+ " ORDER BY a.nopo ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtPurchaseh> list = em.createQuery(query)
	            		.setParameter("fWarehouse", fWarehouse.getId())
	            		.setParameter("trdate", trDate)
	            		.setParameter("tipefaktur", tipefaktur)
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
