package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.ArrayList;
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
	
	@Override
	public List<FtPurchaseh> findAllMrvBelumLunas() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtPurchaseh a WHERE a.tipefaktur LIKE 'R' AND (a.amountafterdiscafterppn > a.amountpay OR a.amountpay IS NULL) ";
	            
	            List<FtPurchaseh> list = em.createQuery(query)
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
	public List<FtPurchaseh> findAllMrvBelumLunas(FtPurchaseh exceptRetur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtPurchaseh a WHERE (a.tipefaktur LIKE 'R' AND (a.amountafterdiscafterppn > a.amountpay  OR a.amountpay IS NULL)) "
	            		+ " OR (a.refno = :exReturNo )";
	            
	            List<FtPurchaseh> list = em.createQuery(query)
	            		.setParameter("exReturNo", exceptRetur.getRefno())
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
	public List<FtPurchaseh> findAllPenagihan(String strNopo, String strInvoiceNo,
			String strTipeFaktur, Date dateInvoicedateFrom,
			Date dateInvoicedateTo, boolean bolLunasFrom, boolean bolLunasTo,
			String strVendorId, String strVendorName) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            List<FtPurchaseh> list  = new ArrayList<FtPurchaseh>();
	            String query ="";
	            query = "SELECT a FROM FtPurchaseh a WHERE "
	            		+ " NOT a.nopo LIKE '' "
	            		+ "	AND  a.invoiceno LIKE :invoiceno "
	            		+ "	AND  a.tipefaktur LIKE :tipefaktur "
	            		+ "	AND a.lunas BETWEEN :lunasFrom AND :lunasTo "
	            		+ "	AND a.invoicedate >= :invoicedateFrom AND a.invoicedate <= :invoicedateTo "
	            		+ "	AND  a.fvendorBean.vcode LIKE :vcode "
	            		+ "	AND  a.fvendorBean.vname LIKE :vname "
	            		+ " ORDER BY a.nopo DESC";
		            list = em.createQuery(query)
		            		.setParameter("invoiceno", strInvoiceNo)
			            	.setParameter("tipefaktur", strTipeFaktur)
		            		.setParameter("lunasFrom", bolLunasFrom)
		            		.setParameter("lunasTo", bolLunasTo)
		            		.setParameter("invoicedateFrom", dateInvoicedateFrom)
		            		.setParameter("invoicedateTo", dateInvoicedateTo)
		            		.setParameter("vcode", strVendorId)
		            		.setParameter("vname", strVendorName)
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
