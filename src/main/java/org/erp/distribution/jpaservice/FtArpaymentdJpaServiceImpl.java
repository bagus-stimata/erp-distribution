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
import org.erp.distribution.model.FtArpaymentd;

public class FtArpaymentdJpaServiceImpl extends GenericJpaServiceImpl<FtArpaymentd, Serializable> implements FtArpaymentdJpaService{

	@Override
	public List<FtArpaymentd> findAllByInvoiceAndDiv(Long refnosales,
			String div) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtArpaymentd a WHERE a.id.refnosales LIKE :refnosales "
	            		+ " ";
	            
	            List<FtArpaymentd> list = em.createQuery(query)
	            		 .setParameter("refnosales", refnosales)
	            		 .setHint(QueryHints.REFRESH, HintValues.TRUE)
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
	public List<FtArpaymentd> findAllByRefnoAndInvAndDiv(Long refnopayment,
			Long refnosales, String div) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtArpaymentd a WHERE "
	            		+ "	a.id.refnopayment = :refnopayment "
	            		+ " AND a.id.refnosales = :refnosales "
	            		+ " ";
	            
	            List<FtArpaymentd> list = em.createQuery(query)
	            		.setParameter("refnopayment", refnopayment)
	            		.setParameter("refnosales", refnosales)
	            		.setHint(QueryHints.REFRESH, HintValues.TRUE)
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
	public List<FtArpaymentd> findAllByArpaydateAndSJ(Date arPaymentDateFrom,
			Date arPaymentDateTo, String SJPengiriman, String SJPenagihan) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtArpaymentd a WHERE "
	            		+ "	a.ftarpaymenthBean.trdate >= :arPaymentDateFrom "
	            		+ " AND a.ftarpaymenthBean.trdate <= :arPaymentDateTo "
	            		+ " AND a.ftsaleshBean.suratjalanno LIKE :suratjalanno "
	            		+ " AND a.ftsaleshBean.sjpenagihanno LIKE :sjpenagihanno "
	            		+ " ";
	            
	            List<FtArpaymentd> list = em.createQuery(query)
	            		.setParameter("arPaymentDateFrom", arPaymentDateFrom)
	            		.setParameter("arPaymentDateTo", arPaymentDateTo)
	            		.setParameter("suratjalanno", SJPengiriman)
	            		.setParameter("sjpenagihanno", SJPenagihan)
	            		.setHint(QueryHints.REFRESH, HintValues.TRUE)
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
	public List<FtArpaymentd> findAllByArpaydateAndSJ(Date arPaymentDateFrom,
			Date arPaymentDateTo, String SJPengiriman, String SJPenagihan,
			String tunaikredit, String spcode, String custno) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtArpaymentd a WHERE "
	            		+ "	a.ftarpaymenthBean.trdate >= :arPaymentDateFrom "
	            		+ " AND a.ftarpaymenthBean.trdate <= :arPaymentDateTo "
	            		+ " AND a.ftsaleshBean.suratjalanno LIKE :suratjalanno "
	            		+ " AND a.ftsaleshBean.sjpenagihanno LIKE :sjpenagihanno "
	            		+ " AND a.ftsaleshBean.tunaikredit LIKE :tunaikredit "
	            		+ " AND a.ftsaleshBean.fsalesmanBean.spcode LIKE :spcode "
	            		+ " AND a.ftsaleshBean.fcustomerBean.custno LIKE :custno "
	            		+ " ";
	            
	            List<FtArpaymentd> list = em.createQuery(query)
	            		.setParameter("arPaymentDateFrom", arPaymentDateFrom)
	            		.setParameter("arPaymentDateTo", arPaymentDateTo)
	            		.setParameter("suratjalanno", SJPengiriman)
	            		.setParameter("sjpenagihanno", SJPenagihan)
	            		.setParameter("tunaikredit", tunaikredit)
	            		.setParameter("spcode", spcode)
	            		.setParameter("custno", custno)
	            		.setHint(QueryHints.REFRESH, HintValues.TRUE)
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
	public List<FtArpaymentd> findAllByInvdateAndInvnoAndArpaydateAndSJ(
			Date invDateFrom, Date invDateTo, String InvoiceNo,
			Date arPaymentDateFrom, Date arPaymentDateTo, String SJPengiriman,
			String SJPenagihan) {
		// TODO Auto-generated method stub
		return null;
	}


}
