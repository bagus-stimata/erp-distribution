package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesh;

public interface FtSaleshJpaService extends GenericJpaService<FtSalesh, Serializable>{
	public List<FtSalesh> findAllTipeFaktur(String tipefaktur);
	public List<FtSalesh> findAllByOrderdate(Date orderdate);
	public List<FtSalesh> findAllByInvoicedate(Date invoicedate);
	public List<FtSalesh> findAllByInvoicedate(Date invoicedateFrom, Date invoicedateTo, String tipefaktur, String spcode, String custno);
	
	public List<FtSalesh> findAllByInvoicedate(Date invoicedate, String tipefaktur, boolean yangTerbitSaja);
	public List<FtSalesh> findAllByOrderno(String orderno, String tipefaktur);
	public List<FtSalesh> findAllByInvoiceno(String invoiceno, String tipefaktur);
	public List<FtSalesh> findAllBySuratjalanNo(String suratjalanno, String tipefaktur);
	public List<FtSalesh> findAllBySJPenagihanNo(String sjpenagihanno, String tipefaktur);
	public List<FtSalesh> findAll(FWarehouse fWarehouse, Date trDate, String tipefaktur);
	
	public List<FtSalesh> findAllReturBelumLunas();
	public List<FtSalesh> findAllReturBelumLunas(FtSalesh exceptRetur);
	
	public List<FtSalesh> findAllOpenInvoice(FCustomer fCustomer);

	public List<FtSalesh> findAllPengiriman(String sjpengirimanno, String strInvoiceNo, String strTipeFaktur, String strTunaiKredit, String strTipeJual, 
			Date dateInvoicedateFrom, Date dateInvoicedateTo, boolean bolLunasFrom, boolean bolLunasTo, 
			String spcode, String spname, String custno, String custname, String strArea, String strSubArea);
	public List<FtSalesh> findAllPenagihan(String sjpenagihanno, String strInvoiceNo, String strTipeFaktur, String strTunaiKredit, String strTipeJual, 
			Date dateInvoicedateFrom, Date dateInvoicedateTo, boolean bolLunasFrom, boolean bolLunasTo, 
			String spcode, String spname, String custno, String custname, String strArea, String strSubArea);
	

}
