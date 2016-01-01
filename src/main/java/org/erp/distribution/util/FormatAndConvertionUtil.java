package org.erp.distribution.util;

public class FormatAndConvertionUtil {

	public Double convertStringToDouble(String stringValue){
		Double doubleValue = 0.0;
		//1. Fixing Proses
//		String pattern = "[123456789.]"; //Akan Diganti >>123456789.
		String pattern = "[^0123456789.]"; //Selain >>123456789.		
	
		stringValue = stringValue.replaceAll(pattern, "");
		//2. Format
		try{
			doubleValue = Double.valueOf(stringValue);
		} catch(Exception ex){}
		
		return doubleValue;
	}
	
	public static void main(String [] args){
		
		FormatAndConvertionUtil f = new FormatAndConvertionUtil();
		f.convertStringToDouble("1121,888a.333");
	}
}
