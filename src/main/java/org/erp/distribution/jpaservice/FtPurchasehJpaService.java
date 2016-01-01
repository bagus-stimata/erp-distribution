package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesh;

public interface FtPurchasehJpaService extends GenericJpaService<FtPurchaseh, Serializable>{
	public List<FtPurchaseh> findAllTipeFaktur(String tipefaktur);
	public List<FtPurchaseh> findAllByOrderdate(Date podate);
	public List<FtPurchaseh> findAllByInvoicedate(Date invoicedate);
	public List<FtPurchaseh> findAllByNopo(String nopo, String tipefaktur);
	public List<FtPurchaseh> findAll(FWarehouse fWarehouse, Date trDate, String tipefaktur);

}
