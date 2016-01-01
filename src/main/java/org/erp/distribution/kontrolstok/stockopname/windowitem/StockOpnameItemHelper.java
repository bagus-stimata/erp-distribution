package org.erp.distribution.kontrolstok.stockopname.windowitem;

import org.erp.distribution.model.FProduct;

public class StockOpnameItemHelper{
	
	private StockOpnameItemModel model;
	private StockOpnameItemView view;
	
	FProduct fProduct = new FProduct();
	
	public StockOpnameItemHelper(StockOpnameItemModel model, StockOpnameItemView view){
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
		model.itemDetil.setPprice(fProduct.getPprice());
	}
	public void updateAndCalculateItemDetil(){
		try{
			//PRICE+PPN ::: PRICE AFTER PPN DIBULATKAN LANGSUNG
			Double amountPpnPrice = model.itemDetil.getFproductBean().getPprice() * getParamPpn()/100;					
			Double priceAfterppn = (double) Math.round(model.itemDetil.getFproductBean().getPprice() + amountPpnPrice);
//			Double priceAfterppn = model.itemDetil.getPprice() + amountPpnPrice;
//			model.itemDetil.setPpriceafterppn((double) Math.round(priceAfterppn));
	
			//QTY :: QTY(BES,SED,KEC) -> PCS
			Integer qty = model.itemDetil.getQty1() * fProduct.getConvfact1() 
					+ model.itemDetil.getQty2() * fProduct.getConvfact2() 
					+ model.getItemDetil().getQty3();
			model.itemDetil.setQty(qty);
			
			//QTY1
			Integer qty1 = model.itemDetil.getQty()/fProduct.getConvfact1();
			model.itemDetil.setQty1(qty1);
			//QTY2
			Integer sisaqty1 = model.itemDetil.getQty() % fProduct.getConvfact1();
			Integer qty2 = sisaqty1/fProduct.getConvfact2();
			model.itemDetil.setQty2(qty2);
			//QTY3
			Integer sisaqty2 = sisaqty1 % fProduct.getConvfact2();
			Integer qty3 = sisaqty2;
			model.itemDetil.setQty3(qty3);
			
			//PRICE PCS
			Double pricePcsNoPpn = model.itemDetil.getFproductBean().getPprice() / fProduct.getConvfact1();
			Double pricePcsAfterPpn = priceAfterppn / fProduct.getConvfact1();
			
			//SUBTOTAL BEFORE DISC
			Double subtotalBeforedisc = pricePcsNoPpn * model.itemDetil.getQty();			
			Double subtotalBeforediscafterppn = pricePcsAfterPpn * model.itemDetil.getQty();
			model.itemDetil.setSubtotal((double) Math.round(subtotalBeforedisc));
			model.itemDetil.setSubtotalafterppn((double) Math.round(subtotalBeforediscafterppn));

		} catch(Exception ex){}
		
		model.getBinderItemDetail().setItemDataSource(model.itemDetil);
		
	}

	
}
