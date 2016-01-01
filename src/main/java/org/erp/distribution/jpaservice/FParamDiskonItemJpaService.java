package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FParamDiskonItem;
import org.erp.distribution.model.FParamDiskonNota;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FVendor;

public interface FParamDiskonItemJpaService extends GenericJpaService<FParamDiskonItem, Serializable>{
	public List<FParamDiskonItem> findAllByVendorAndSubgroupActive(FVendor fVendorBean, FProductgroup fProductgroupBean, FCustomersubgroup fCustomersubgroupBean);
	public List<FParamDiskonItem> findAllByVendorAndSubgroup(FVendor fVendorBean);
	public List<FParamDiskonItem> findAllByVendorAndSubgroup(FCustomersubgroup fCustomersubgroupBean);
	public List<FParamDiskonItem> findAllByVendorAndSubgroup();

}
