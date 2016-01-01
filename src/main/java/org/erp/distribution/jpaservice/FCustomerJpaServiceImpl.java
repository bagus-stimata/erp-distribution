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
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.model.FProduct;

public class FCustomerJpaServiceImpl extends GenericJpaServiceImpl<FCustomer, Serializable> implements FCustomerJpaService{

	@Override
	public List<FCustomer> findAllByCustno(String custno) {
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "";
			query = "SELECT a FROM FCustomer a WHERE a.custno LIKE :custno "
					+ " ORDER BY a.custno ASC";

			List<FCustomer> list = em.createQuery(query)
					.setParameter("custno", custno)
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
	public void updateCustomerOpenInvoice(String stringCustomergroup, int jumlahNota) {
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "UPDATE FCustomer a SET a.openinvoice=:jumlahNota WHERE a.fcustomersubgroupBean.id LIKE :customergroupid "
					+ " ";

			em.createQuery(query)
					.setParameter("jumlahNota", jumlahNota)
					.setParameter("customergroupid", stringCustomergroup)
					.executeUpdate();
			
			em.getTransaction().commit();
		} catch (PersistenceException exception) {
			em.getTransaction().rollback();
			throw exception;
		} finally {
			em.close();
		}
		
	}

}
