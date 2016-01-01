package org.erp.distribution.jpaservice;

import java.io.Serializable;
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

}
