package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FParamDiskonItem;
import org.erp.distribution.model.FParamDiskonItemVendor;
import org.erp.distribution.model.FParamDiskonNota;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FVendor;

public interface FParamDiskonItemVendorJpaService extends GenericJpaService<FParamDiskonItemVendor, Serializable>{
	public List<FParamDiskonItemVendor> findAllByVendorAndSubgroupActive(FVendor fVendorBean, FProductgroup fProductgroupBean, FCustomersubgroup fCustomersubgroupBean);
	public List<FParamDiskonItemVendor> findAllByVendorAndSubgroup(FVendor fVendorBean);
	public List<FParamDiskonItemVendor> findAllByVendorAndSubgroupActive(FVendor fVendorBean);
	public List<FParamDiskonItemVendor> findAllByVendorAndSubgroup(FCustomersubgroup fCustomersubgroupBean);
	public List<FParamDiskonItemVendor> findAllByVendorAndSubgroup();
	public List<FParamDiskonItemVendor> findAllActive();

}
