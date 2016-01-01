package org.erp.distribution.jpaservicehp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.Bank;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.StService;

public class StServiceJpaServiceImpl extends GenericJpaServiceImpl<StService, Serializable> implements StServiceJpaService{

	@Override
	public List<StService> findAllByTglPenerimaan(Date tglPenerimaanFrom, Date tglPenerimaanTo,
			Long longTeknisiId) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "";
	            if (longTeknisiId<0){
		            query = "SELECT a FROM StService a WHERE a.tanggalmasuk >= :tglPenerimaanFrom "
		            		+ " AND a.tanggalmasuk <= :tglPenerimaanTo "
		            		+ " AND a.steknisiBean.id > :longTeknisiId "
		            		+ " ORDER BY a.refno DESC";
	            } else {
		            query = "SELECT a FROM StService a WHERE a.tanggalmasuk >= :tglPenerimaanFrom "
		            		+ " AND a.tanggalmasuk <= :tglPenerimaanTo "
		            		+ " AND a.steknisiBean.id = :longTeknisiId "
		            		+ " ORDER BY a.refno DESC";
	            	
	            }
	            List<StService> list = em.createQuery(query)
	            		.setParameter("tglPenerimaanFrom", tglPenerimaanFrom)
	            		.setParameter("tglPenerimaanTo", tglPenerimaanTo)
	            		.setParameter("longTeknisiId", longTeknisiId)
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
	public List<StService> findAllByTglPengambilan(Date telahDiambilTanggalFrom, Date telahDiambilTanggalTo,
			Long longTeknisiId) {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "";
	            if (longTeknisiId<0){
	            	query = "SELECT a FROM StService a WHERE a.telahDiambilTanggal >= :telahDiambilTanggalFrom "
	            		+ " AND a.telahDiambilTanggal <= :telahDiambilTanggalTo"
	            		+ " AND a.steknisiBean.id > :longTeknisiId "
	            		+ " ORDER BY a.refno DESC";
	            } else {
	            	query = "SELECT a FROM StService a WHERE a.telahDiambilTanggal >= :telahDiambilTanggalFrom "
		            		+ " AND a.telahDiambilTanggal <= :telahDiambilTanggalTo"
		            		+ " AND a.steknisiBean.id = :longTeknisiId "
		            		+ " ORDER BY a.refno DESC";	            	
	            }
	            
	            List<StService> list = em.createQuery(query)
	            		.setParameter("telahDiambilTanggalFrom", telahDiambilTanggalFrom)
	            		.setParameter("telahDiambilTanggalTo", telahDiambilTanggalTo)
	            		.setParameter("longTeknisiId", longTeknisiId)
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
