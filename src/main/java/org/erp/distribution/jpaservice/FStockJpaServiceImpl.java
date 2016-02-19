package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FStock;
import org.erp.distribution.model.FWarehouse;

public class FStockJpaServiceImpl extends GenericJpaServiceImpl<FStock, Serializable> implements FStockJpaService{
	@Override
	public List<FStock> findAll(Date trdate) {		
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FStock a WHERE a.stockdate = :stockdate"
	            		+ " ORDER BY a.stockdate ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FStock> list = em.createQuery(query)
	            		.setParameter("stockdate", trdate)
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
	public int count(Date trdate) {		
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT count(a) FROM FStock a WHERE a.stockdate = :stockdate ";	            
	            Integer list = em.createQuery(query)
	            		.setParameter("stockdate", trdate)
	            		.setHint(QueryHints.MAINTAIN_CACHE, HintValues.TRUE)
	            		 .getResultList().size();
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
	public List<FStock> findAll(FProduct fProduct, Date trdate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FStock a WHERE a.stockdate = :stockdate"
	            		+ " AND a.fproductBean.id = :idProductBean ORDER BY a.stockdate ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FStock> list = em.createQuery(query)
	            		.setParameter("stockdate", trdate)
	            		.setParameter("idProductBean", fProduct.getId())
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
	public List<FStock> findAll(FWarehouse fWarehouse, FProduct fProduct,
			Date trdate) {
		
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FStock a WHERE a.stockdate = :stockdate"
	            		+ " AND a.fwarehouseBean.id LIKE :idWarehouseBean"
	            		+ " AND a.fproductBean.id = :idProductBean ORDER BY a.stockdate ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FStock> list = em.createQuery(query)
	            		.setParameter("stockdate", trdate)
	            		.setParameter("idWarehouseBean", fWarehouse.getId())
	            		.setParameter("idProductBean", fProduct.getId())
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
	public List<FStock> findAllBefore(FWarehouse fWarehouse, FProduct fProduct,
			Date trdate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FStock a WHERE a.stockdate < :stockdate"
	            		+ " AND a.fwarehouseBean.id LIKE :idWarehouseBean"
	            		+ " AND a.fproductBean.id = :idProductBean ORDER BY a.stockdate DESC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FStock> list = em.createQuery(query)
	            		.setParameter("stockdate", trdate)
	            		.setParameter("idWarehouseBean", fWarehouse.getId())
	            		.setParameter("idProductBean", fProduct.getId())
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
	public List<FStock> findAllAfter(FWarehouse fWarehouse, FProduct fProduct,
			Date trdate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FStock a WHERE a.stockdate > :stockdate"
	            		+ " AND a.fwarehouseBean.id LIKE :idWarehouseBean"
	            		+ " AND a.fproductBean.id = :idProductBean ORDER BY a.stockdate ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FStock> list = em.createQuery(query)
	            		.setParameter("stockdate", trdate)
	            		.setParameter("idWarehouseBean", fWarehouse.getId())
	            		.setParameter("idProductBean", fProduct.getId())
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
	public List<FStock> findAll(String strFWarehouse, FProduct fProduct, Date stockDateFrom,
			Date stockDateTo) {
		
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FStock a WHERE a.stockdate >= :stockdateFrom AND a.stockdate <= :stockdateTo "
	            		+ " AND a.fwarehouseBean.id LIKE :idWarehouseBeanStr"
	            		+ " AND a.fproductBean.id = :idProductBean "
	            		+ " ORDER BY a.stockdate ASC";
	            
	            List<FStock> list = em.createQuery(query) 
	            		.setParameter("stockdateFrom", stockDateFrom)
	            		.setParameter("stockdateTo", stockDateTo)
	            		.setParameter("idWarehouseBeanStr", strFWarehouse)
	            		.setParameter("idProductBean", fProduct.getId())
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
	public void setZerroIfNull() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "UPDATE FStock a SET a.qtyadjust=0 WHERE a.qtyadjust = null";
	            
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
	public List<FStock> findAll(String strWarehouse, Date stockDate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FStock a WHERE a.stockdate = :stockdate"
	            		+ " AND a.fwarehouseBean.id LIKE :idWarehouseBeanStr"
	            		+ " ORDER BY a.stockdate ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FStock> list = em.createQuery(query)
	            		.setParameter("stockdate", stockDate)
	            		.setParameter("idWarehouseBeanStr", strWarehouse)
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
