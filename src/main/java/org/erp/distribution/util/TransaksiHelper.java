package org.erp.distribution.util;

import java.util.Date;

public interface TransaksiHelper {
	public Date getCurrentTransDate();
	
	public String getCurrentFtArpaymenthRefno();
	public String getCurrentFtOpnamehRefno();
	public String getCurrentFtPurchasehRefno();
	public String getCurrentFtPurchasehRefnoRetur();
	public String getCurrentFtSaleshRefno();
	public String getCurrentFtSaleshRefnoRetur();
	public String getCurrentFtSaleshInvoiceno();
	public String getCurrentFtSaleshInvoicenoRetur();
	public String getCurrentFtSaleshSuratJalan();
	public String getCurrentFtSaleshSJPenagihan();
	
	public String getCurrentFtSpricehRefno();
	public String getCurrentFtStocktransferhRefno();

	public String getCurrentFtPricehRefno();
	public String getCurrentFtPriceAlthRefno();

	public String getCurrentFtStockAdjusthRefno();
	public String getCurrentFPromo();
	
	public String getNextFtArpaymenthRefno();
	public String getNextFtOpnamehRefno();
	public String getNextFtPurchasehRefno();
	public String getNextFtPurchasehRefnoRetur();
	public String getNextFtSaleshRefno();
	public String getNextFtSaleshRefnoRetur();
	public String getNextFtSaleshInvoiceno();
	public String getNextFtSaleshSuratJalan();
	public String getNextFtSaleshSJPenagihan();
	public String getNextFtSaleshInvoicenoRetur();
	public String getNextFtSpricehRefno();
	public String getNextFtStocktransferhRefno();

	public String getNextFtPricehRefno();
	public String getNextFtPriceAlthRefno();
	
	public String getNextFtStockAdjusthRefno();
	public String getNextFPromo();
	
	public Double getParamPpn();
	
	public void prosesAkhirHari();
}
