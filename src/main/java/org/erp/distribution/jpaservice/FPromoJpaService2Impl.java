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
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FPromo;

public class FPromoJpaService2Impl extends GenericJpaServiceImpl<FPromo, Serializable> implements FPromoJpaService2{

	@Override
	public List<FPromo> findAllById(Long id) {
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "";
			query = "SELECT a FROM FPromo a WHERE a.id = :id "
					+ " ORDER BY a.id ASC";

			List<FPromo> list = em.createQuery(query)
					.setParameter("id", id)
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
	public List<FPromo> findAllPromoActiveByProduct(FProduct fProductBean) {
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "";
			query = "SELECT a FROM FPromo a WHERE a.fProductBean.id = :productId "
					+ " AND  a.fproductgroupBean.id = null "
					+ " AND  a.statusAktifPromo = true "
					+ " ORDER BY a.id ASC";

			List<FPromo> list = em.createQuery(query)
					.setParameter("productId", fProductBean.getId())
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
	public List<FPromo> findAllPromoActiveByProductGroup(FProductgroup fProductgroupBean) {
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "";
			query = "SELECT a FROM FPromo a WHERE NOT a.fproductgroupBean.id = :productGrouptId "
					+ " AND  a.fProductBean.id = null  "
					+ " AND  a.statusAktifPromo = true "
					+ " ORDER BY a.id ASC";

			List<FPromo> list = em.createQuery(query)
					.setParameter("productGrouptId", fProductgroupBean.getId())
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
	public List<FPromo> findAllPromoActiveByProductGroup() {
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "";
			query = "SELECT a FROM FPromo a WHERE  NOT a.fproductgroupBean.id = null"
					+ " AND  a.fProductBean.id = null  "
					+ " AND  a.statusAktifPromo = true "
					+ " ORDER BY a.id ASC";

			List<FPromo> list = em.createQuery(query)
//					.setParameter("productGrouptId", fProductgroupBean.getId())
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
