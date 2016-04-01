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
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesh;

public class FtSaleshJpaServiceImpl extends GenericJpaServiceImpl<FtSalesh, Serializable> implements FtSaleshJpaService{

	@Override
	public List<FtSalesh> findAllTipeFaktur(String tipefaktur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a WHERE a.tipefaktur LIKE :tipefaktur"
	            		+ " ORDER BY a.orderno DESC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesh> list = em.createQuery(query)
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
	public List<FtSalesh> findAllByOrderdate(Date orderdate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a WHERE a.orderdate = :orderdate"
	            		+ " ORDER BY a.orderno DESC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesh> list = em.createQuery(query)
	            		.setParameter("orderdate", orderdate)
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
	public List<FtSalesh> findAllByInvoicedate(Date invoicedate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a WHERE a.invoicedate = :invoicedate"
	            		+ " ORDER BY a.orderno DESC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesh> list = em.createQuery(query)
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
	public List<FtSalesh> findAllByInvoicedate(Date invoicedate,
			String tipefaktur, boolean yangTerbitSaja) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query ="";
	            if (yangTerbitSaja) {
		            query = "SELECT a FROM FtSalesh a WHERE a.invoicedate = :invoicedate "
		            		+ "AND a.tipefaktur LIKE :tipefaktur "
		            		+ "AND NOT a.invoiceno LIKE '' "
		            		+ " ORDER BY a.orderno DESC";
	            } else {
		            query = "SELECT a FROM FtSalesh a WHERE a.invoicedate = :invoicedate "
		            		+ "AND a.tipefaktur LIKE :tipefaktur "
		            		+ " ORDER BY a.orderno DESC";	            	
	            }
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesh> list = em.createQuery(query)
	            		.setParameter("invoicedate", invoicedate)
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
	public List<FtSalesh> findAllByOrderno(String orderno, String tipefaktur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a WHERE a.orderno LIKE :orderno "
	            		+ " AND a.tipefaktur LIKE :tipefaktur"
	            		+ " ORDER BY a.orderno DESC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesh> list = em.createQuery(query)
	            		.setParameter("orderno", orderno)
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
	public List<FtSalesh> findAllByInvoiceno(String invoiceno,
			String tipefaktur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a WHERE a.invoiceno LIKE :invoiceno "
	            		+ " AND a.tipefaktur LIKE :tipefaktur"
	            		+ " ORDER BY a.orderno DESC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesh> list = em.createQuery(query)
	            		.setParameter("invoiceno", invoiceno)
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
	public List<FtSalesh> findAllBySuratjalanNo(String suratjalanno,
			String tipefaktur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a WHERE a.suratjalanno LIKE :suratjalanno "
	            		+ " AND a.tipefaktur LIKE :tipefaktur"
	            		+ " ORDER BY a.orderno DESC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesh> list = em.createQuery(query)
	            		.setParameter("suratjalanno", suratjalanno)
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
	public List<FtSalesh> findAll(FWarehouse fWarehouse, 
			Date trDate, String tipefaktur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a WHERE a.fwarehouseBean.id LIKE :fWarehouse AND a.invoicedate = :trdate"
	            		+ " AND a.tipefaktur LIKE :tipefaktur "
	            		+ " ORDER BY a.orderno ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesh> list = em.createQuery(query)
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
	public List<FtSalesh> findAllFakturAndReturBelumLunas(FCustomer fCustomer) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a WHERE a.fcustomerBean.id =:customerId "
	            		+ " AND (a.amountafterdiscafterppn > a.amountpay OR a.amountpay IS NULL) ";
	            
	            List<FtSalesh> list = em.createQuery(query)
	            		.setParameter("customerId", fCustomer.getId())
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
	public List<FtSalesh> findAllReturBelumLunas() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a WHERE a.tipefaktur LIKE 'R' AND (a.amountafterdiscafterppn > a.amountpay OR a.amountpay IS NULL) ";
	            
	            List<FtSalesh> list = em.createQuery(query)
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
	public List<FtSalesh> findAllReturBelumLunas(FtSalesh exceptRetur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a WHERE (a.tipefaktur LIKE 'R' AND (a.amountafterdiscafterppn > a.amountpay  OR a.amountpay IS NULL)) "
	            		+ " OR (a.refno = :exReturNo )";
	            
	            List<FtSalesh> list = em.createQuery(query)
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
	public List<FtSalesh> findAllByInvoicedate(Date invoicedateFrom,
			Date invoicedateTo, String tipefaktur, String spcode, String custno) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a WHERE a.invoicedate >= :invoicedateFrom AND a.invoicedate <= :invoicedateTo "
	            		+ " AND a.tipefaktur LIKE :tipefaktur "
	            		+ " AND a.fsalesmanBean.spcode LIKE :spcode "
	            		+ " AND a.fcustomerBean.custno LIKE :custno "
	            		+ " ORDER BY a.orderno DESC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesh> list = em.createQuery(query)
	            		.setParameter("invoicedateFrom", invoicedateFrom)
	            		.setParameter("invoicedateTo", invoicedateTo)
	            		.setParameter("tipefaktur", tipefaktur)
	            		.setParameter("spcode", spcode)
	            		.setParameter("custno", custno)
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
	public List<FtSalesh> findAllByInvoicedateOrderBySalesman(Date invoicedateFrom,
			Date invoicedateTo, String tipefaktur, String spcode, String custno) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a WHERE a.invoicedate >= :invoicedateFrom AND a.invoicedate <= :invoicedateTo "
	            		+ " AND a.tipefaktur LIKE :tipefaktur "
	            		+ " AND a.fsalesmanBean.spcode LIKE :spcode "
	            		+ " AND a.fcustomerBean.custno LIKE :custno "
	            		+ " ORDER BY a.fsalesmanBean.spcode, a.orderno";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesh> list = em.createQuery(query)
	            		.setParameter("invoicedateFrom", invoicedateFrom)
	            		.setParameter("invoicedateTo", invoicedateTo)
	            		.setParameter("tipefaktur", tipefaktur)
	            		.setParameter("spcode", spcode)
	            		.setParameter("custno", custno)
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
	public List<FtSalesh> findAllOpenInvoice(FCustomer fCustomer) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a "
	            		+ " WHERE "
	            		+ " a.fcustomerBean.id = :idCustomer "
	            		+ " AND a.fcustomerBean.openinvoice >  0 "
	            		+ " AND a.amountafterdiscafterppn >  a.amountpay "
	            		+ " ORDER BY a.orderno ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesh> list = em.createQuery(query)
	            		.setParameter("idCustomer", fCustomer.getId())
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
	public List<FtSalesh> findAllPengiriman(String sjpengirimanno, String strInvoiceNo, String strTipeFaktur,
			String strTunaiKredit, String strTipeJual,
			Date dateInvoicedateFrom, Date dateInvoicedateTo,
			boolean bolLunasFrom, boolean bolLunasTo, String spcode, String spname, 
			String custno, String custname, String strArea, String strSubArea) {
		
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            List<FtSalesh> list  = new ArrayList<FtSalesh>();
	            String query ="";
	            if (sjpengirimanno.trim().equals("%%")) {
		            query = "SELECT a FROM FtSalesh a WHERE "
		            		+ " NOT a.invoiceno LIKE '' "
		            		+ "	AND  a.invoiceno LIKE :invoiceno "
		            		+ "	AND  a.tipefaktur LIKE :tipefaktur "
		            		+ "	AND  a.tunaikredit LIKE :tunaikredit "
		            		+ "	AND  a.tipejual LIKE :tipejual "
		            		+ "	AND a.lunas BETWEEN :lunasFrom AND :lunasTo "
		            		+ "	AND a.invoicedate >= :invoicedateFrom AND a.invoicedate <= :invoicedateTo "
		            		+ "	AND  a.fsalesmanBean.spcode LIKE :spcode "
		            		+ "	AND  a.fcustomerBean.fsubareaBean.id LIKE :subarea "
		            		+ "	AND  a.fcustomerBean.fsubareaBean.fareaBean.id LIKE :area "
		            		+ " ORDER BY a.orderno DESC";
			            list = em.createQuery(query)
			            		.setParameter("invoiceno", strInvoiceNo)
				            	.setParameter("tipefaktur", strTipeFaktur)
			            		.setParameter("tunaikredit", strTunaiKredit)
			            		.setParameter("tipejual", strTipeJual)
			            		.setParameter("lunasFrom", bolLunasFrom)
			            		.setParameter("lunasTo", bolLunasTo)
			            		.setParameter("invoicedateFrom", dateInvoicedateFrom)
			            		.setParameter("invoicedateTo", dateInvoicedateTo)
			            		.setParameter("spcode", spcode)
			            		.setParameter("subarea", strSubArea)
			            		.setParameter("area", strArea)
			            		.setHint(QueryHints.MAINTAIN_CACHE, HintValues.TRUE)
			            		 .getResultList();
	            }else {
		            query = "SELECT a FROM FtSalesh a WHERE "
		            		+ " NOT a.invoiceno LIKE '' "
		            		+ "	AND  a.suratjalanno LIKE :suratjalanno "
		            		+ "	AND  a.invoiceno LIKE :invoiceno "
		            		+ "	AND  a.tipefaktur LIKE :tipefaktur "
		            		+ "	AND  a.tunaikredit LIKE :tunaikredit "
		            		+ "	AND  a.tipejual LIKE :tipejual "
		            		+ "	AND a.lunas BETWEEN :lunasFrom AND :lunasTo "
		            		+ "	AND a.invoicedate >= :invoicedateFrom AND a.invoicedate <= :invoicedateTo "
		            		+ "	AND  a.fsalesmanBean.spcode LIKE :spcode "
		            		+ "	AND  a.fcustomerBean.fsubareaBean.id LIKE :subarea "
		            		+ "	AND  a.fcustomerBean.fsubareaBean.fareaBean.id LIKE :area "
		            		+ " ORDER BY a.orderno DESC";	            	
		            list = em.createQuery(query)
		            		.setParameter("invoiceno", strInvoiceNo)
		            		.setParameter("suratjalanno", sjpengirimanno)
			            	.setParameter("tipefaktur", strTipeFaktur)
		            		.setParameter("tunaikredit", strTunaiKredit)
		            		.setParameter("tipejual", strTipeJual)
		            		.setParameter("lunasFrom", bolLunasFrom)
		            		.setParameter("lunasTo", bolLunasTo)
		            		.setParameter("invoicedateFrom", dateInvoicedateFrom)
		            		.setParameter("invoicedateTo", dateInvoicedateTo)
		            		.setParameter("spcode", spcode)
		            		.setParameter("subarea", strSubArea)
		            		.setParameter("area", strArea)
		            		.setHint(QueryHints.MAINTAIN_CACHE, HintValues.TRUE)
		            		 .getResultList();
	            }
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
	public List<FtSalesh> findAllPenagihan(String sjpenagihanno, String strInvoiceNo, String strTipeFaktur,
			String strTunaiKredit, String strTipeJual,
			Date dateInvoicedateFrom, Date dateInvoicedateTo,
			boolean bolLunasFrom, boolean bolLunasTo, String spcode, String spname, 
			String custno, String custname, String strArea, String strSubArea) {
		
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            List<FtSalesh> list  = new ArrayList<FtSalesh>();
	            String query ="";
	            if (sjpenagihanno.trim().equals("%%")) {
		            query = "SELECT a FROM FtSalesh a WHERE "
		            		+ " NOT a.invoiceno LIKE '' "
		            		+ "	AND  a.invoiceno LIKE :invoiceno "
		            		+ "	AND  a.tipefaktur LIKE :tipefaktur "
		            		+ "	AND  a.tunaikredit LIKE :tunaikredit "
		            		+ "	AND  a.tipejual LIKE :tipejual "
		            		+ "	AND a.lunas BETWEEN :lunasFrom AND :lunasTo "
		            		+ "	AND a.invoicedate >= :invoicedateFrom AND a.invoicedate <= :invoicedateTo "
		            		+ "	AND  a.fsalesmanBean.spcode LIKE :spcode "
		            		+ "	AND  a.fcustomerBean.custno LIKE :custno "
		            		+ "	AND  a.fcustomerBean.fsubareaBean.id LIKE :subarea "
		            		+ "	AND  a.fcustomerBean.fsubareaBean.fareaBean.id LIKE :area "
		            		+ " ORDER BY a.orderno DESC";
			            list = em.createQuery(query)
			            		.setParameter("invoiceno", strInvoiceNo)
				            	.setParameter("tipefaktur", strTipeFaktur)
			            		.setParameter("tunaikredit", strTunaiKredit)
			            		.setParameter("tipejual", strTipeJual)
			            		.setParameter("lunasFrom", bolLunasFrom)
			            		.setParameter("lunasTo", bolLunasTo)
			            		.setParameter("invoicedateFrom", dateInvoicedateFrom)
			            		.setParameter("invoicedateTo", dateInvoicedateTo)
			            		.setParameter("spcode", spcode)
			            		.setParameter("custno", custno)
			            		.setParameter("subarea", strSubArea)
			            		.setParameter("area", strArea)
			            		.setHint(QueryHints.MAINTAIN_CACHE, HintValues.TRUE)
			            		 .getResultList();
	            }else {
		            query = "SELECT a FROM FtSalesh a WHERE "
		            		+ " NOT a.invoiceno LIKE '' "
		            		+ "	AND  a.sjpenagihanno LIKE :sjpenagihanno "
		            		+ "	AND  a.invoiceno LIKE :invoiceno "
		            		+ "	AND  a.tipefaktur LIKE :tipefaktur "
		            		+ "	AND  a.tunaikredit LIKE :tunaikredit "
		            		+ "	AND  a.tipejual LIKE :tipejual "
		            		+ "	AND a.lunas BETWEEN :lunasFrom AND :lunasTo "
		            		+ "	AND a.invoicedate >= :invoicedateFrom AND a.invoicedate <= :invoicedateTo "
		            		+ "	AND  a.fsalesmanBean.spcode LIKE :spcode "
		            		+ "	AND  a.fcustomerBean.fsubareaBean.id LIKE :subarea "
		            		+ "	AND  a.fcustomerBean.fsubareaBean.fareaBean.id LIKE :area "
		            		+ " ORDER BY a.orderno DESC";	            	
		            list = em.createQuery(query)
		            		.setParameter("invoiceno", strInvoiceNo)
		            		.setParameter("sjpenagihanno", sjpenagihanno)
			            	.setParameter("tipefaktur", strTipeFaktur)
		            		.setParameter("tunaikredit", strTunaiKredit)
		            		.setParameter("tipejual", strTipeJual)
		            		.setParameter("lunasFrom", bolLunasFrom)
		            		.setParameter("lunasTo", bolLunasTo)
		            		.setParameter("invoicedateFrom", dateInvoicedateFrom)
		            		.setParameter("invoicedateTo", dateInvoicedateTo)
		            		.setParameter("spcode", spcode)
		            		.setParameter("subarea", strSubArea)
		            		.setParameter("area", strArea)
		            		.setHint(QueryHints.MAINTAIN_CACHE, HintValues.TRUE)
		            		 .getResultList();
	            }
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
	public List<FtSalesh> findAllBySJPenagihanNo(String sjpenagihanno,
			String tipefaktur) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesh a WHERE a.sjpenagihanno LIKE :sjpenagihanno "
	            		+ " AND a.tipefaktur LIKE :tipefaktur"
	            		+ " ORDER BY a.orderno DESC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesh> list = em.createQuery(query)
	            		.setParameter("sjpenagihanno", sjpenagihanno)
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
