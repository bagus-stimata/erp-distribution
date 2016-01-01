package org.erp.distribution.jpaservicerep;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.FStock;
import org.erp.distribution.model.ZLapMutasiStock;
import org.erp.distribution.model.ZLapPrestasiKerja;

public class LapMutasiStockJpaServiceImpl extends GenericJpaServiceImpl<ZLapMutasiStock, Serializable> implements LapMutasiStockJpaService{

	@Override
	public void deleteAll() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "DELETE FROM ZLapMutasiStock";
	            em.createQuery(query).executeUpdate();
	            em.getTransaction().commit();
	        } catch (PersistenceException exception) {
	            em.getTransaction().rollback();
	            throw exception;
	        } finally {
	            em.close();
	        }    
		
	}

}
