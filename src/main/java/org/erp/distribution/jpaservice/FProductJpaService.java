package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;

public interface FProductJpaService extends GenericJpaService<FProduct, Serializable>{
	public List<FProduct> findAllActive();
	public List<FProduct> findAllByPcode(String pcode);
	public List<FProduct> findAll(boolean isAktif);
	
	public List<FProduct> findAll(String pcode, String pname, long longVendorFrom, long longVendorTo, String productDivisi, boolean includeNull);

	public List<FProduct> findAllForMutasi(long longVendor, String productDivisi, String strProductGroup,  boolean isAktif);
	public List<FProduct> findAllForMutasi(String productDivisi, String strProductGroup,  boolean isAktif);
	
}
