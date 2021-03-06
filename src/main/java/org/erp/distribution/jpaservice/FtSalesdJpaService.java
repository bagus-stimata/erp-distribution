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
	public List<FtSalesd> findAll(String fWarehouseId, String pcode, Date trDateFrom, 
			Date trDateTo, String tipefaktur);
	
	public List<FtSalesd> findAllByVendor(String vcode, String custno, Date trDateFrom, 
			Date trDateTo, String tipefaktur, String pcode);
	
	public List<FtSalesd> findAllByVendor(String spcode, String vcode, String areaId, String subAreaId, 
			String custno, Date trDateFrom, Date trDateTo, String tipefaktur,
			String pcode, String pname, String productGroup);
	public List<FtSalesd> findAllByArea(String spcode, String vcode, String areaId, String subAreaId, 
			String custno, Date trDateFrom, Date trDateTo, String tipefaktur,
			String pcode, String pname, String productGroup);
	public List<FtSalesd> findAllByTipeCust(String spcode, String vcode, String areaId, String subAreaId, 
			String custno, Date trDateFrom, Date trDateTo, String tipefaktur,
			String pcode, String pname, String productGroup);
	public List<FtSalesd> findAllByProductGroup(String spcode, String vcode, String areaId, String subAreaId, 
			String custno, Date trDateFrom, Date trDateTo, String tipefaktur,
			String pcode, String pname, String productGroup);
	
	public List<FtSalesd> findAllByForTotalSupplier(String spcode, String vcode, String areaId, String subAreaId, 
			String custno, Date trDateFrom, Date trDateTo, String tipefaktur,
			String pcode, String pname, String productGroup);
	public List<FtSalesd> findAllByForTotalCustomer(String spcode, String vcode, String areaId, String subAreaId, 
			String custno, Date trDateFrom, Date trDateTo, String tipefaktur,
			String pcode, String pname, String productGroup);
	public List<FtSalesd> findAllByForTotalBarang(String spcode, String vcode, String areaId, String subAreaId, 
			String custno, Date trDateFrom, Date trDateTo, String tipefaktur,
			String pcode, String pname, String productGroup);
	public List<FtSalesd> findAllByForTotalSalesman(String spcode, String vcode, String areaId, String subAreaId, 
			String custno, Date trDateFrom, Date trDateTo, String tipefaktur,
			String pcode, String pname, String productGroup);
	
	public List<FtSalesd> findAllByTipeAndInvoice(String spcode, String vcode, String areaId, String subAreaId, 
			String custno, Date trDateFrom, Date trDateTo, String tipefaktur,
			String pcode, String pname, String productGroup);
	public List<FtSalesd> findAllByAreaAndInvoice(String spcode, String vcode, String areaId, String subAreaId, 
			String custno, Date trDateFrom, Date trDateTo, String tipefaktur,
			String pcode, String pname, String productGroup);
	

	public List<FtSalesd> findAllByRefno(Long refno);
	
	public void setNullFreegoodAsFalse();
	public List<FtSalesd> findAllById(Long refno, Long id, boolean freegood);
	public List<FtSalesd> findAllById(Long id, boolean freegood);
	
	
}
