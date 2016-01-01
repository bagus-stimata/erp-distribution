package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.Bukugiro;
import org.erp.distribution.model.FArea;

public class BukugiroJpaServiceImpl extends GenericJpaServiceImpl<Bukugiro, Serializable> implements BukugiroJpaService{

	@Override
	public List<Bukugiro> findAllAvailableGiro(Date tgltransaksi) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bukugiro a WHERE a.amount>a.amountused AND a.giroduedate>= :tgltransaksi";
	            
	            List<Bukugiro> list = em.createQuery(query)
	            		.setParameter("tgltransaksi", tgltransaksi)
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
	public List<Bukugiro> findAllAvailableGiro() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bukugiro a WHERE (a.amount>a.amountused OR a.amountused IS NULL) ";
	            
	            List<Bukugiro> list = em.createQuery(query)
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
	public List<Bukugiro> findAllAvalilableGiro(Date tgltransaksi,
			Long exceptGiro) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a From Bukugiro a WHERE (a.amount>a.amountused  OR a.amountused IS NULL) AND a.giroduedate>= :tgltransaksi "
	            		+ " OR a.refno = :exceptrefno";
	            
	            List<Bukugiro> list = em.createQuery(query)
	            		.setParameter("tgltransaksi", tgltransaksi)
	            		.setParameter("exceptrefno", exceptGiro)
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
	public List<Bukugiro> findAllAvalilableGiro(Long exceptGiro) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	        	
	            em.getTransaction().begin();
	            String query = "SELECT a From Bukugiro a WHERE (a.amount>a.amountused  OR a.amountused IS NULL) OR a.refno = :exceptrefno";
	            
	            List<Bukugiro> list = em.createQuery(query)
	            		.setParameter("exceptrefno", exceptGiro)
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
