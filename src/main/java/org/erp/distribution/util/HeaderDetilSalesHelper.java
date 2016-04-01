package org.erp.distribution.util;

import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;

public interface HeaderDetilSalesHelper {
	//##FTSALESD
	public double getDetilSpriceAfterPpn();
	//QTY --> SUBTOTAL
	public int getDetilQtyBes();
	public int getDetilQtySed();
	public int getDetilQtyKec();
	
	
	public double getDetilSubtotal();
	public double getDetilSubtotalAfterPpn();

	public double getDetilDisc1Rp();
	public double getDetilDisc1RpAfterPpn();
	
	public double getDetilSubtotalAfterDisc1();
	public double getDetilSubtotalAfterDisc1AfterPpn();
	
	public double getDetilDisc2Rp();
	public double getDetilDisc2RpAfterPpn();
	
	public double getDetilSubtotalAfterDisc2();
	public double getDetilSubtotalAfterDisc2AfterPpn();

	//AFTER DISC NOTA
	public double getDetilDiscNota1Rp();
	public double getDetilDiscNota1RpAfterPpn();
	
	public double getDetilSubtotalAfterDiscNota1();
	public double getDetilSubtotalAfterDiscNota1AfterPpn();
	
	public double getDetilDiscNotaRp();
	public double getDetilDiscNotaRpAfterPpn();
	
	public double getDetilSubtotalAfterDiscNota();
	public double getDetilSubtotalAfterDiscNotaAfterPpn();
	
	//###FTSALESH
	public double getHeaderAmount();
	public double getHeaderAmountAfterPpn();
	
	public double getHeaderDisc1Rp();
	public double getHeaderDisc1RpAfterPpn();
	public double getHeaderDisc2Rp();
	public double getHeaderDisc2RpAfterPpn();
	
	public double getHeaderSubtotalAfterDisc12();
	public double getHeaderSubtotalAfterDisc12AfterPpn();
	
	public double getHeaderDiscRp();
	public double getHeaderDiscRpAfterPpn();
	
	public double getHeaderSubtotalAfterDisc();
	public double getHeaderSubtotalAfterDiscAfterPpn();
	
	//GET FIELD AND UPDATE
	public FtSalesh getFillFtSalesh();
	public FtSalesd getFillFtSalesd();
	public FtSalesd getFillFtSalesdOnly();
	
	public boolean isRoundedDiscRp();
	public boolean isRoundedTotal();
	public void setRoundedDiscRp(boolean roundedDiscRp);
	public void setRoundedTotal(boolean roundedTotal);
	
	public void setSprice(double newSPrice);
	
	
}
