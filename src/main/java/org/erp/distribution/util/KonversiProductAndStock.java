package org.erp.distribution.util;

import org.erp.distribution.model.FProduct;

public interface KonversiProductAndStock {
	public int getBesFromPcs();
	public int getSedFromPcs();
	public int getKecFromPcs();
	public String getBesSedKecString();
	public String getBesSedKecStringUom();
	
}
