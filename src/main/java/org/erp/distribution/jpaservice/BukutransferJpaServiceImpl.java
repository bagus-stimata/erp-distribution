package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.Bukutransfer;
import org.erp.distribution.model.FArea;

public class BukutransferJpaServiceImpl extends GenericJpaServiceImpl<Bukutransfer, Serializable> implements BukutransferJpaService{

	@Override
	public List<Bukutransfer> findAllAvailableTransfer() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bukutransfer a WHERE (a.amount>a.amountused  OR a.amountused IS NULL) ";
	            
	            List<Bukutransfer> list = em.createQuery(query)
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
	public List<Bukutransfer> findAllAvailabelTransfer(Long exceptTransfer) {
	       EntityManager em = getFactory().createEntityManager();
        try {        	
            em.getTransaction().begin();
            String query = "SELECT a From Bukutransfer a WHERE (a.amount>a.amountused  OR a.amountused IS NULL) "
            		+ " OR a.id.refno = :exceptrefno";
            
            List<Bukutransfer> list = em.createQuery(query)
            		.setParameter("exceptrefno", exceptTransfer)
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
