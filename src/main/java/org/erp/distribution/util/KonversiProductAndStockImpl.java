package org.erp.distribution.util;

import org.erp.distribution.model.FProduct;

public class KonversiProductAndStockImpl implements KonversiProductAndStock{
	FProduct fProduct = new FProduct();
	int pcs = 0;
	
	public KonversiProductAndStockImpl(){		
	}
	public KonversiProductAndStockImpl(int pcs, FProduct fProduct){
		this.pcs = pcs;
		this.fProduct = fProduct;
	}
	
	public int getBesFromPcs(){
		int valueBes =0;
		try{
			valueBes = pcs / fProduct.getConvfact1();
		} catch(Exception ex){}
		
		return valueBes;
	}
	public int getSedFromPcs(){
		int valueSed = 0;
		try{
			int sisaQtyBes = pcs % fProduct.getConvfact1();		
			valueSed = sisaQtyBes / fProduct.getConvfact2();
		} catch(Exception ex){}
		
		return valueSed;
	}
	public int getKecFromPcs(){
		int valueKec =0;
		try{
			int sisaQtyBes = pcs % fProduct.getConvfact1();
			int sisaQtySed = sisaQtyBes % fProduct.getConvfact2();
			valueKec = sisaQtySed;
		} catch(Exception ex){}
		return valueKec;
	}
	@Override
	public String getBesSedKecString() {
		String strBes = String.valueOf(getBesFromPcs());
		String strSed = String.valueOf(getSedFromPcs());
		String strKec = String.valueOf(getKecFromPcs());		
		
		String strBesSedKec = "";
		
		if (getSedFromPcs()<10) {
			strSed = "0" + strSed;
		}
		if (getKecFromPcs()<10) {
			strKec = "0" + strKec;
		}
		
		return strBes + "." + strSed + "." +strKec;
	}
	@Override
	public String getBesSedKecStringUom() {
		String strBes = String.valueOf(getBesFromPcs());
		String strSed = String.valueOf(getSedFromPcs());
		String strKec = String.valueOf(getKecFromPcs());		
		
		if (getBesFromPcs()==0) {
			strBes="";
		}else {
			strBes += fProduct.getUom1().toLowerCase();
		}
		if (getSedFromPcs()==0){
			strSed="";
		}else {
			strSed += fProduct.getUom2().toLowerCase();			
		}
		if (getKecFromPcs()==0){
			strKec="";			
		}else {
			strKec +=  fProduct.getUom3().toLowerCase();
		}
		
		return  strBes + " " + strSed + " " + strKec;
	}
	

}
