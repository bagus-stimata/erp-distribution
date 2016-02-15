package org.erp.distribution.util;

import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;

public interface HeaderDetilHelper {
	
	public double getDetilSpriceAfterPpn();
	//QTY --> SUBTOTAL
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
	
	public FtSalesd getFillFtSalesh();
	public FtSalesd getFillFtSalesd();
	public FtSalesd getFillFtSalesdOnly();
	
}
