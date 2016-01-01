package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FStock;
import org.erp.distribution.model.FWarehouse;

public interface FStockJpaService extends GenericJpaService<FStock, Serializable>{
	public List<FStock> findAll(Date stockDate);
	public List<FStock> findAll(String strWarehouse, Date stockDate);
	public List<FStock> findAll(FProduct fProduct, Date trdate);
	public List<FStock> findAll(String strFWarehouse, FProduct fProduct, Date stockDateFrom, Date stockDateTo);
	public List<FStock> findAll(FWarehouse fWarehouse, FProduct fProduct, Date trdate);
	public List<FStock> findAllBefore(FWarehouse fWarehouse, FProduct fProduct, Date trdate);
	public List<FStock> findAllAfter(FWarehouse fWarehouse, FProduct fProduct, Date trdate);
	
	//##FOR UPGRADE
	public void setZerroIfNull();
	

}
