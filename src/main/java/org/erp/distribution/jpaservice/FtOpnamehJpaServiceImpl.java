package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.FtOpnameh;

public class FtOpnamehJpaServiceImpl extends GenericJpaServiceImpl<FtOpnameh, Serializable> implements FtOpnamehJpaService{
	@Override
	public List<FtOpnameh> findAllByTrdate(Date trdate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtOpnameh a WHERE a.trdate = :trdate"
	            		+ " ORDER BY a.norek ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtOpnameh> list = em.createQuery(query)
	            		.setParameter("trdate", trdate)
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
	public List<FtOpnameh> findAllByTrdate(Date trdateFrom, Date trdateTo) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtOpnameh a WHERE a.trdate >= :trdateFrom AND a.trdate<= :trdateTo"
	            		+ " ORDER BY a.norek ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtOpnameh> list = em.createQuery(query)
	            		.setParameter("trdateFrom", trdateFrom)
	            		.setParameter("trdateTo", trdateTo)
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
