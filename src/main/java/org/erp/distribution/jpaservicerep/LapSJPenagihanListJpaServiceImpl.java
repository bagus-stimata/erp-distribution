package org.erp.distribution.jpaservicerep;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.FStock;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.ZLapMutasiStock;
import org.erp.distribution.model.ZLapPackingList;
import org.erp.distribution.model.ZLapPrestasiKerja;
import org.erp.distribution.model.ZLapSJPenagihanList;

public class LapSJPenagihanListJpaServiceImpl extends GenericJpaServiceImpl<ZLapSJPenagihanList, Serializable> implements LapSJPenagihanListJpaService{

	@Override
	public void deleteAll() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "DELETE FROM ZLapSJPenagihanList";
	            em.createQuery(query).executeUpdate();
	            em.getTransaction().commit();
	        } catch (PersistenceException exception) {
	            em.getTransaction().rollback();
	            throw exception;
	        } finally {
	            em.close();
	        }    
		
	}

	@Override
	public List<ZLapSJPenagihanList> findAllSumByProduct() {
       EntityManager em = getFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            String query = "SELECT a FROM ZLapSJPenagihanList a ";
            //ORDER BY BERPERAN SANGAT PENTING
            
            List<ZLapSJPenagihanList> list = em.createQuery(query)
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
