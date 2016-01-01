package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtPurchased;
import org.erp.distribution.model.FtSalesd;

public interface FtPurchasedJpaService extends GenericJpaService<FtPurchased, Serializable>{
	public List<FtPurchased> findAll(FWarehouse fWarehouse, FProduct fProduct, Date trDate, String tipefaktur);
	
}
