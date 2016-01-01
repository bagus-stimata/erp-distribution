package org.erp.distribution.salesorder.salesorder.retur.windowitem;

import java.util.ArrayList;
import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FtPriceAltd;
import org.erp.distribution.util.TransaksiHelper;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.AbstractSelect.ItemDescriptionGenerator;

public class SalesOrderReturItemHelper{
	
	private SalesOrderReturItemModel model;
	private SalesOrderReturItemView view;

	
	FProduct fProduct = new FProduct();
	
	public SalesOrderReturItemHelper(SalesOrderReturItemModel model, SalesOrderReturItemView view){
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
		
		//JIKA FREEGOOD :: MAKA HARGA 0
		boolean freegood = false;
		if (model.getItemDetil().getId().getFreegood()==true){
			freegood=true;
		}
		
		double pecahanPpn = (getParamPpn()+100) /100;
		
		if (freegood==true){
			model.itemDetil.setSprice(0.0);			
			model.itemDetil.setSpriceafterppn(0.0);			
		}else {
			//JIKA NULL MAKA TIDAK BISA
			try{
				model.itemDetil.setSprice(fProduct.getSprice());
				model.itemDetil.setSpriceafterppn(fProduct.getSprice() * pecahanPpn);
			} catch(Exception ex){}
		}
		
		
		
		//HARGA ALTERNATIF
		try{
			//JIKA BUKAN FREE DAN ADA HARGA ALTERNATIF MAKA PAKAI HARGA ALTERNATIF
			if (freegood==false){
				if (model.getItemHeader().getFcustomerBean().getFtPriceAlthBean()!=null) {
					List<FtPriceAltd> listHargaAlternatif = new ArrayList<FtPriceAltd>();
					listHargaAlternatif = model.getFtPriceAltdJpaService()
							.findAllByHeaderIdAndProduct(model.getItemHeader().getFcustomerBean().getFtPriceAlthBean().getRefno(),
									model.itemDetil.getFproductBean().getId());
					for (FtPriceAltd ftPriceAltd: listHargaAlternatif){
						model.itemDetil.setSprice(ftPriceAltd.getSpricealt());
						model.itemDetil.setSpriceafterppn(ftPriceAltd.getSpricealt() * pecahanPpn);
					}
				}else if (model.getItemHeader().getFcustomerBean().getFcustomersubgroupBean().getFtPriceAlthBean() !=null) {
					List<FtPriceAltd> listHargaAlternatif = new ArrayList<FtPriceAltd>();
					listHargaAlternatif = model.getFtPriceAltdJpaService()
							.findAllByHeaderIdAndProduct(model.getItemHeader().getFcustomerBean()
									.getFcustomersubgroupBean().getFtPriceAlthBean().getRefno(),
									model.itemDetil.getFproductBean().getId());
					for (FtPriceAltd ftPriceAltd: listHargaAlternatif){
						model.itemDetil.setSprice(ftPriceAltd.getSpricealt());
						model.itemDetil.setSpriceafterppn(ftPriceAltd.getSpricealt() * pecahanPpn);
					}
					
				}
			}
		} catch(Exception ex){}
		
		
	}
	public void updateAndCalculateItemDetil(){
		try{
			//PRICE+PPN
			Double amountPpnPrice = model.itemDetil.getSprice() * getParamPpn()/100;
			Double priceAfterppn = (double) Math.round(model.itemDetil.getSprice() + amountPpnPrice);
			model.itemDetil.setSpriceafterppn((double) Math.round(priceAfterppn));
	
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
			Double pricePcsNoPpn = model.itemDetil.getSprice() / fProduct.getConvfact1();
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
