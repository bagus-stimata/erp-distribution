package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FWarehouse;

public class FWarehouseJpaServiceImpl extends GenericJpaServiceImpl<FWarehouse, Serializable> implements FWarehouseJpaService{

	@Override
	public List<FWarehouse> findAllMainWareHouseActive() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FWarehouse a WHERE a.gudangutama = :isGudangUtama AND a.statusactive = :isStatusActive";
	            
	            List<FWarehouse> list = em.createQuery(query)
	            		 .setParameter("isGudangUtama", true)
	            		 .setParameter("isStatusActive", true)
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
	public List<FWarehouse> findAllActive() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FWarehouse a WHERE a.statusactive = :isStatusActive";
	            
	            List<FWarehouse> list = em.createQuery(query)
	            		 .setParameter("isStatusActive", true)
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
