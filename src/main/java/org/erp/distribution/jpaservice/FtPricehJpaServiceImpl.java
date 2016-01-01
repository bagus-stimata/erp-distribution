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
import org.erp.distribution.model.FtPriceAltd;
import org.erp.distribution.model.FtPriceh;

public class FtPricehJpaServiceImpl extends GenericJpaServiceImpl<FtPriceh, Serializable> implements FtPricehJpaService{

	@Override
	public List<FtPriceh> findAllByTrDate(Date trDate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtPriceh a WHERE a.trdate = :trdate "
	            		+ " ";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtPriceh> list = em.createQuery(query)
	            		.setParameter("trdate", trDate)
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
