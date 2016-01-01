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
import org.erp.distribution.model.FtOpnameh;
import org.erp.distribution.model.FtStocktransferh;

public class FtStocktransferhJpaServiceImpl extends GenericJpaServiceImpl<FtStocktransferh, Serializable> implements FtStocktransferhJpaService{
	@Override
	public List<FtStocktransferh> findAllByTrdate(Date trdate) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtStocktransferh a WHERE a.trdate = :trdate"
	            		+ " ORDER BY a.norek ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtStocktransferh> list = em.createQuery(query)
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

}
