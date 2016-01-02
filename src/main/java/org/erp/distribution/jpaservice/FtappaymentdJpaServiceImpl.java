package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.Bank;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FtAppaymentd;
import org.erp.distribution.model.FtArpaymentd;

public class FtappaymentdJpaServiceImpl extends GenericJpaServiceImpl<FtAppaymentd, Serializable> implements FtappaymentdJpaService{

	@Override
	public List<FtAppaymentd> findAllByInvoiceAndDiv(Long refnopurchaseh,
			String div) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtAppaymentd a WHERE a.id.refnopurchase LIKE :refnopurchase "
	            		+ " ";
	            
	            List<FtAppaymentd> list = em.createQuery(query)
	            		 .setParameter("refnopurchase", refnopurchaseh)
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
	public List<FtAppaymentd> findAllByRefnoAndInvAndDiv(Long refnopayment,
			Long refnopurchaseh, String div) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtAppaymentd a WHERE "
	            		+ "	a.id.refnopayment = :refnopayment "
	            		+ " AND a.id.refnopurchase = :refnopurchase "
	            		+ " ";
	            
	            List<FtAppaymentd> list = em.createQuery(query)
	            		.setParameter("refnopayment", refnopayment)
	            		.setParameter("refnopurchase", refnopurchaseh)
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
