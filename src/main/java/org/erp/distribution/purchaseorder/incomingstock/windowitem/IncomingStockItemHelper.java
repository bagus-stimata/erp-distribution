package org.erp.distribution.purchaseorder.incomingstock.windowitem;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.util.TransaksiHelper;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.AbstractSelect.ItemDescriptionGenerator;

public class IncomingStockItemHelper{
	
	private IncomingStockItemModel model;
	private IncomingStockItemView view;
	
	FProduct fProduct = new FProduct();
	
	public IncomingStockItemHelper(IncomingStockItemModel model, IncomingStockItemView view){
		this.model = model;
		this.view = view;
	}

	public Double getParamPpn(){
		return model.getTransaksiHelper().getParamPpn();
	}
	public void updateAndCalulateItemDetilProduct(){
		
		fProduct = new FProduct();
		fProduct = (FProduct) view.getComboProduct().getValue();
		
		double pecahanPpn = (getParamPpn()+100) /100;
		model.itemDetil.setFproductBean(fProduct);
		model.itemDetil.setPprice(fProduct.getPprice());
		model.itemDetil.setPpriceafterppn(fProduct.getPprice() * pecahanPpn);
	}
	public void updateAndCalculateItemDetil(){
		try{
			Double priceBeforeppn = 0.0;
			Double amountPpnPrice = 0.0;
			Double priceAfterppn = 0.0;
			double pecahanPpn = (getParamPpn()+100) /100;

			//PRICE+PPN
			if (model.getSysvarHelper().isEntryItemPembelianHargaSelelahPPN()==true) {		
				priceBeforeppn = model.getItemDetil().getPpriceafterppn() / pecahanPpn;
				model.itemDetil.setPprice(priceBeforeppn);
			}else {
				amountPpnPrice = model.itemDetil.getPprice() * getParamPpn()/100;
				//##PERHITUNGAN BARU
//				priceAfterppn = (double) Math.round(model.itemDetil.getSprice() + amountPpnPrice);
				priceAfterppn = model.itemDetil.getPprice() * pecahanPpn;
				model.itemDetil.setPpriceafterppn((double) Math.round(priceAfterppn));
			}
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
			Double pricePcsNoPpn = model.itemDetil.getPprice() / fProduct.getConvfact1();
			Double pricePcsAfterPpn = priceAfterppn / fProduct.getConvfact1();
			
			//SUBTOTAL BEFORE DISC
			Double subtotalBeforedisc = pricePcsNoPpn * model.itemDetil.getQty();			
			Double subtotalBeforediscafterppn = pricePcsAfterPpn * model.itemDetil.getQty();
			model.itemDetil.setSubtotal((double) Math.round(subtotalBeforedisc));
			model.itemDetil.setSubtotalafterppn((double) Math.round(subtotalBeforediscafterppn));
			
			//DISCOUNT
			Double disc1persen = (double) model.itemDetil.getDisc1() /100.0;
			Double disc1rp = subtotalBeforedisc * disc1persen;
			Double disc1rpafterppn = subtotalBeforediscafterppn * disc1persen;
			Double subtotalAfterdisc1 =  subtotalBeforedisc-disc1rp;
			Double subtotalAfterdisc1afterppn =  subtotalBeforediscafterppn-disc1rpafterppn;
			model.itemDetil.setDisc1rp((double) Math.round(disc1rp));
			model.itemDetil.setDisc1rpafterppn((double) Math.round(disc1rpafterppn));
			
			Double disc2persen = (double) model.itemDetil.getDisc2() /100.0;
			Double disc2rp = subtotalAfterdisc1 * disc2persen;
			Double disc2rpafterppn = subtotalAfterdisc1afterppn * disc2persen;
			Double subtotalAfterdisc2 =  subtotalAfterdisc1-disc2rp;
			Double subtotalAfterdisc2afterppn =  subtotalAfterdisc1afterppn-disc2rpafterppn;
			model.itemDetil.setDisc2rp((double) Math.round(disc2rp));
			model.itemDetil.setDisc2rpafterppn((double) Math.round(disc2rpafterppn));
			
			//SUB TOTAL AFTER DISC
			Double subtotalAfterdisc = subtotalAfterdisc2;
			Double subtotalAfterdiscafterppn = subtotalAfterdisc2afterppn;
			
			model.itemDetil.setSubtotalafterdisc((double) Math.round(subtotalAfterdisc) );
			model.itemDetil.setSubtotalafterdiscafterppn((double) Math.round(subtotalAfterdiscafterppn));

		} catch(Exception ex){}
		
		model.getBinderItemDetail().setItemDataSource(model.itemDetil);
		
	}

	
}
