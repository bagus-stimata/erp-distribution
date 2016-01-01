package org.erp.distribution.master.productperubahanharga.windowitem;

import org.erp.distribution.model.FProduct;

public class PerubahanHargaItemHelper{
	
	private PerubahanHargaItemModel model;
	private PerubahanHargaItemView view;
	
	FProduct fProduct = new FProduct();
	
	public PerubahanHargaItemHelper(PerubahanHargaItemModel model, PerubahanHargaItemView view){
		this.model = model;
		this.view = view;
	}

	public Double getParamPpn(){
		return model.getTransaksiHelper().getParamPpn();
	}
	public void updateAndCalulateItemDetilProduct(){
		
		fProduct = new FProduct();
		fProduct = (FProduct) view.getComboProduct().getValue();
		
		model.itemDetil.setFproductBean(fProduct);
		try{
			model.itemDetil.setPpricebefore(fProduct.getPprice());
			model.itemDetil.setPpricebefore(fProduct.getPprice()/fProduct.getConvfact1());
			model.itemDetil.setSpricebefore(fProduct.getSprice());
			model.itemDetil.setSpricebefore(fProduct.getSprice()/fProduct.getConvfact1());
		} catch(Exception ex){}
		
//		model.itemDetil.setPprice(fProduct.getPprice());
//		model.itemDetil.setPprice2(fProduct.getPprice()/fProduct.getConvfact1());		
//		model.itemDetil.setSprice(fProduct.getSprice());
//		model.itemDetil.setSprice2(fProduct.getSprice2()/fProduct.getConvfact1());
		
		
	}
	public void updateAndCalculateItemDetil(){
		try{
			//PPRICE+PPN ::: PRICE AFTER PPN DIBULATKAN LANGSUNG
			Double amountPpnPPrice = model.itemDetil.getPprice() * getParamPpn()/100;					
			Double amountPpnPPricePcs = model.itemDetil.getPprice2() * getParamPpn()/100;					
			
			Double ppriceAfterppn = model.itemDetil.getPprice() + amountPpnPPrice;
			Double ppriceAfterppnPcs = model.itemDetil.getPprice2() + amountPpnPPrice;
			
			// ####TIDAK ADA YANG BOLEH DI ROUND###
			Double ppriceAfterppnRounded = (double) Math.round(ppriceAfterppn);
//			model.itemDetil.setPpriceafterppn((double) ppriceAfterppn);
			Double ppriceAfterppnPcsRounded = (double) Math.round(ppriceAfterppnPcs);
//			model.itemDetil.setPprice2afterppn((double) ppriceAfterppnPcs);
	

			//PPRICE+PPN ::: PRICE AFTER PPN DIBULATKAN LANGSUNG
			Double amountPpnSPrice = model.itemDetil.getSprice() * getParamPpn()/100;					
			Double amountPpnSPricePcs = model.itemDetil.getSprice2() * getParamPpn()/100;					
			
			Double spriceAfterppn = model.itemDetil.getPprice() + amountPpnSPrice;
			Double spriceAfterppnPcs = model.itemDetil.getPprice2() + amountPpnSPrice;
			
			Double spriceAfterppnRounded = (double) Math.round(spriceAfterppn);
//			model.itemDetil.setSpriceafterppn((double) spriceAfterppn);
			Double spriceAfterppnPcsRounded = (double) Math.round(spriceAfterppnPcs);
//			model.itemDetil.setSprice2afterppn((double) spriceAfterppnPcs);
			

		} catch(Exception ex){}
		
		model.getBinderItemDetail().setItemDataSource(model.itemDetil);
		
	}
	//:::FUNCTION UMUM
	public double getPriceBeforePPN(double priceAfterPPN) {
		double pecahanPpn = (getParamPpn()+100) /100;
		double priceBeforePPN = priceAfterPPN / pecahanPpn;		
		return priceBeforePPN;
	}
	public double getPriceAfterPPN(double priceBeforePPN){
		double pecahanPpn = (getParamPpn()+100) /100;
		double priceAfterPPN = priceBeforePPN * pecahanPpn;
	
		return priceAfterPPN;
		
	}
	
	public void updateAndCalculatePPrice(double ppriceBeforePPN, int convfact1){
		
		try{
			double pecahanPpn = (getParamPpn()+100) /100;
			Double ppriceAfterppn = ppriceBeforePPN * pecahanPpn;

			model.itemDetil.setPprice(ppriceBeforePPN);
			model.itemDetil.setPprice2((double) ppriceBeforePPN/convfact1); 
			
			model.itemDetil.setPpriceafterppn(ppriceAfterppn);
			model.itemDetil.setPprice2afterppn((double) ppriceAfterppn/convfact1);
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		model.getBinderItemDetail().setItemDataSource(model.itemDetil);
//		view.bindAndBuildFieldGroupComponent();
	}
	
	public void updateAndCalculateSPrice(double spriceBeforePPN, int convfact1){
		try{
			double pecahanPpn = (getParamPpn()+100) /100;
			Double spriceAfterppn = spriceBeforePPN * pecahanPpn;
			
			model.itemDetil.setSprice(spriceBeforePPN);
			model.itemDetil.setSprice2((double) spriceBeforePPN/convfact1); 
			
			model.itemDetil.setSpriceafterppn(spriceAfterppn);
			model.itemDetil.setSprice2afterppn((double) spriceAfterppn/convfact1);

		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		model.getBinderItemDetail().setItemDataSource(model.itemDetil);
//		view.bindAndBuildFieldGroupComponent();
		
	}
	
}
