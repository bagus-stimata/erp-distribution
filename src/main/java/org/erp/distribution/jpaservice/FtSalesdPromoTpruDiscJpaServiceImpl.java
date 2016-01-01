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
import org.erp.distribution.model.FtSalesdPromoTprb;
import org.erp.distribution.model.FtSalesdPromoTpruDisc;

public class FtSalesdPromoTpruDiscJpaServiceImpl extends GenericJpaServiceImpl<FtSalesdPromoTpruDisc, Serializable> implements FtSalesdPromoTpruDiscJpaService{

	@Override
	public List<FtSalesdPromoTpruDisc> findAllByFtSaleshRefno(long ftSaleshRefno) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesdPromoTpruDisc a WHERE a.ftSalesdBean.id.refno =:refno ";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesdPromoTpruDisc> list = em.createQuery(query)
	            		.setParameter("refno", ftSaleshRefno)
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
	public List<FtSalesdPromoTpruDisc> findAllByFPromoId(long fPromoId) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FtSalesdPromoTpruDisc a WHERE a.fPromoBean.id =:fPromoId ";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FtSalesdPromoTpruDisc> list = em.createQuery(query)
	            		.setParameter("fPromoId", fPromoId)
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
