package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;

public interface FtSalesdJpaService extends GenericJpaService<FtSalesd, Serializable>{
	public List<FtSalesd> findAll(FWarehouse fWarehouse, FProduct fProduct, Date trDate, String tipefaktur);
	public List<FtSalesd> findAll(String fWarehouseId, String pcode, Date trDateFrom, Date trDateTo, String tipefaktur);
	public void setNullFreegoodAsFalse();
	public List<FtSalesd> findAllById(Long refno, Long id, boolean freegood);
	public List<FtSalesd> findAllById(Long id, boolean freegood);
	
	
}
