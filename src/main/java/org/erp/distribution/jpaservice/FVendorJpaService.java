package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FVendor;

public interface FVendorJpaService extends GenericJpaService<FVendor, Serializable>{
	public List<FVendor> findAllActive(String vcode);
	
}
