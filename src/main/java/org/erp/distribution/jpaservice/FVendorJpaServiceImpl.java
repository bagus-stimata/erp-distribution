package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FVendor;

public class FVendorJpaServiceImpl extends GenericJpaServiceImpl<FVendor, Serializable> implements FVendorJpaService{

	@Override
	public List<FVendor> findAllActive(String vcode) {
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "";
			query = "SELECT a FROM FVendor a WHERE a.vcode LIKE :vcode "
					+ " AND a.statusactive = true "
					+ " ORDER BY a.custno ASC";

			List<FVendor> list = em.createQuery(query)
					.setParameter("vcode", vcode)
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
