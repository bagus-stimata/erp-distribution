package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FtPriceAltd;
import org.erp.distribution.model.FtPriced;
import org.erp.distribution.model.FtSalesd;

public class FtPriceAltdJpaServiceImpl extends GenericJpaServiceImpl<FtPriceAltd, Serializable> implements FtPriceAltdJpaService{

	@Override
	public List<FtPriceAltd> findAllByHeaderIdAndProduct(long headerId,
			long productId) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtPriceAltd a WHERE a.ftpricealthBean.refno = :headerId "
	            		+ " AND a.fproductBean.id = :productId"
	            		+ " ";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtPriceAltd> list = em.createQuery(query)
	            		.setParameter("headerId", headerId)
	            		.setParameter("productId", productId)
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
