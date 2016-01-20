package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;
import org.erp.distribution.jpaservice.generic.GenericJpaServiceImpl;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FtStocktransferd;

public class FProductJpaServiceImpl extends GenericJpaServiceImpl<FProduct, Serializable> implements FProductJpaService{

	@Override
	public List<FProduct> findAllActive() {
	       EntityManager em = getFactory().createEntityManager();
	        try {
	            em.getTransaction().begin();
	            String query = "SELECT a FROM FProduct a WHERE a.statusactive = true"
	            		+ " ORDER BY a.pcode ASC";
	            //ORDER BY BERPERAN SANGAT PENTING
	            
	            List<FProduct> list = em.createQuery(query)
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
	public List<FProduct> findAll(boolean isAktif) {
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "";

			if (isAktif==true){
				query = "SELECT a FROM FProduct a WHERE a.statusactive = true "
						+ " ORDER BY a.pcode ASC";
			}else if (isAktif==false){
				query = "SELECT a FROM FProduct a "
						+ " ORDER BY a.pcode ASC";
			}

			List<FProduct> list = em.createQuery(query)
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
	public List<FProduct> findAllByPcode(String pcode) {
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "";
			query = "SELECT a FROM FProduct a WHERE a.pcode LIKE :pcode "
					+ " ORDER BY a.pcode ASC";

			List<FProduct> list = em.createQuery(query)
					.setParameter("pcode", pcode)
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
	public List<FProduct> findAll(String pcode, String pname, long longVendorFrom,
			long longVendorTo, String productDivisi, boolean includeNullVendor) {
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "";
			if (includeNullVendor) {
			query = "SELECT a FROM FProduct a WHERE a.pcode LIKE :pcode "
					+ " AND a.pname LIKE :pname "
					+ " AND ((a.fvendorBean.id  BETWEEN :longVendorFrom AND :longVendorTo) OR (a.fvendorBean=NULL) )"
					+ " AND a.fproductgroupBean.fproductgroupdivisiBean.id LIKE :productDivisi "
					+ " ORDER BY a.pcode ASC";
			} else {
				query = "SELECT a FROM FProduct a WHERE a.pcode LIKE :pcode "
						+ " AND a.pname LIKE :pname "
						+ " AND ((a.fvendorBean.id  BETWEEN :longVendorFrom AND :longVendorTo)  )"
						+ " AND a.fproductgroupBean.fproductgroupdivisiBean.id LIKE :productDivisi "
						+ " ORDER BY a.pcode ASC";
				
			}
			List<FProduct> list = em.createQuery(query)
					.setParameter("pcode", pcode)
					.setParameter("pname", pname)
					.setParameter("longVendorFrom", longVendorFrom)
					.setParameter("longVendorTo", longVendorTo)
					.setParameter("productDivisi", productDivisi)
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
	public List<FProduct> findAllForMutasi(long longVendor, String productDivisi, 
			String strProductGroup, boolean isAktif) {
		
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "";
			if (isAktif) {
				query = "SELECT a FROM FProduct a WHERE a.fproductgroupBean.id LIKE :productGroup "
						+ " AND a.fvendorBean.id = :longVendor "
						+ " AND a.fproductgroupBean.fproductgroupdivisiBean.id LIKE :productDivisi "
						+ " AND a.statusactive = true"
						+ " ORDER BY a.pcode ASC";
			} else {
				query = "SELECT a FROM FProduct a WHERE a.fproductgroupBean.id LIKE :productGroup "
						+ " AND a.fvendorBean.id = :longVendor "
						+ " AND a.fproductgroupBean.fproductgroupdivisiBean.id LIKE :productDivisi "
						+ " ORDER BY a.pcode ASC";
			}
			List<FProduct> list = em.createQuery(query)
					.setParameter("productGroup", strProductGroup)
					.setParameter("longVendor", longVendor)
					.setParameter("productDivisi", productDivisi)
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
	public List<FProduct> findAllForMutasi(String productDivisi, String strProductGroup,
			boolean isAktif) {
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "";
			if (isAktif) {
				query = "SELECT a FROM FProduct a WHERE a.fproductgroupBean.id LIKE :productGroup  "
						+ " AND a.statusactive = true"
						+ " AND a.fproductgroupBean.fproductgroupdivisiBean.id LIKE :productDivisi "
						+ " ORDER BY a.pcode ASC";
			} else {
				query = "SELECT a FROM FProduct a WHERE a.fproductgroupBean.id LIKE :productGroup  "
						+ " AND a.fproductgroupBean.fproductgroupdivisiBean.id LIKE :productDivisi "
						+ " ORDER BY a.pcode ASC";
			}
			List<FProduct> list = em.createQuery(query)
					.setParameter("productGroup", strProductGroup)
					.setParameter("productDivisi", productDivisi)
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
	public List<FProduct> findAllByPnameAktif(String pname) {
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "";
			query = "SELECT a FROM FProduct a WHERE a.pname LIKE :pname "
					+ " AND a.statusactive = true "
					+ " ORDER BY a.pcode ASC";

			List<FProduct> list = em.createQuery(query)
					.setParameter("pname", pname)
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
	public List<FProduct> findAllByPcodeAktif(String pcode) {
		EntityManager em = getFactory().createEntityManager();
		try {
			em.getTransaction().begin();
			String query = "";
			query = "SELECT a FROM FProduct a WHERE a.pcode LIKE :pcode "
					+ " AND a.statusactive = true "
					+ " ORDER BY a.pcode ASC";

			List<FProduct> list = em.createQuery(query)
					.setParameter("pcode", pcode)
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
