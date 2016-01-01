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
import org.erp.distribution.model.FtSalesh;

public class FtSalesdJpaServiceImpl extends GenericJpaServiceImpl<FtSalesd, Serializable> implements FtSalesdJpaService{

	@Override
	public List<FtSalesd> findAll(FWarehouse fWarehouse, FProduct fProduct,
			Date trDate, String tipefaktur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesd a WHERE a.ftsaleshBean.fwarehouseBean.id LIKE :fWarehouse"
	            		+ " AND a.ftsaleshBean.invoicedate = :trdate"
	            		+ " AND a.ftsaleshBean.tipefaktur LIKE :tipefaktur AND a.fproductBean.id = :fProduct"
	            		+ " ORDER BY  a.ftsaleshBean.invoicedate ASC, a.ftsaleshBean.orderno ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesd> list = em.createQuery(query)
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
	@Override
	public List<FtSalesd> findAll(String fWarehouseId, String pcode,
			Date trDateFrom, Date trDateTo, String tipefaktur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesd a WHERE a.ftsaleshBean.fwarehouseBean.id LIKE :fWarehouseId"
	            		+ " AND a.ftsaleshBean.invoicedate >= :trdateFrom AND a.ftsaleshBean.invoicedate <= :trdateTo "
	            		+ " AND a.ftsaleshBean.tipefaktur LIKE :tipefaktur AND a.fproductBean.pcode LIKE :pcode "
	            		+ " ORDER BY  a.ftsaleshBean.invoicedate ASC, a.ftsaleshBean.orderno ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesd> list = em.createQuery(query)
	            		.setParameter("fWarehouseId", fWarehouseId)
	            		.setParameter("trdateFrom", trDateFrom)
	            		.setParameter("trdateTo", trDateTo)
	            		.setParameter("tipefaktur", tipefaktur)
	            		.setParameter("pcode", pcode)
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
	public void setNullFreegoodAsFalse() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "UPDATE FtSalesd a SET a.id.freegood = false WHERE a.id.freegood=NULL";
	            
	            em.createQuery(query).executeUpdate();
	            em.getTransaction().commit();
	            
	        } catch (PersistenceException exception) {
	            em.getTransaction().rollback();
	            throw exception;
	        } finally {
	            em.close();
	        }    
		
	}
	@Override
	public List<FtSalesd> findAllById(Long refno, Long id, boolean freegood) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesd a WHERE a.id.refno = :refno "
	            		+ " AND a.id.id = :id AND a.id.freegood = :freegood ";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesd> list = em.createQuery(query)
	            		.setParameter("refno", refno)
	            		.setParameter("id", id)
	            		.setParameter("freegood", freegood)
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
	public List<FtSalesd> findAllById(Long id, boolean freegood) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesd a WHERE a.id.id = :id "
	            		+ " AND a.id.freegood = :freegood ";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesd> list = em.createQuery(query)
	            		.setParameter("id", id)
	            		.setParameter("freegood", freegood)
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
