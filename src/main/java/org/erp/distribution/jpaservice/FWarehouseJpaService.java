package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FWarehouse;

public interface FWarehouseJpaService extends GenericJpaService<FWarehouse, Serializable>{
	public List<FWarehouse> findAllMainWareHouseActive();
}
