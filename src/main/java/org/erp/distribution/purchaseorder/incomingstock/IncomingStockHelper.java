package org.erp.distribution.purchaseorder.incomingstock;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.formula.functions.Subtotal;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FtPurchased;
import org.erp.distribution.model.FtPurchasedPK;
import org.erp.distribution.util.HeaderDetilHelper;
import org.erp.distribution.util.HeaderDetilHelperImpl;

import com.vaadin.ui.Notification;

public class IncomingStockHelper {
	
	private IncomingStockModel model;
	private IncomingStockView view;
	
	public IncomingStockHelper(IncomingStockModel model, IncomingStockView view){
		this.model = model;
		this.view = view;
	}

	public Double getParamPpn(){
		double paramPPn = 10.0;
		try{
			if (model.getTransaksiHelper().getParamPpn()>0) {
				paramPPn = model.getTransaksiHelper().getParamPpn();
			}
		} catch(Exception ex){}
		return paramPPn;
	}

	public void updateAndCalculateHeaderByItemDetil(){
		
		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
		double sumDiscrp1 = 0;
		double sumDiscrp1afterppn = 0;		
		double sumDiscrp2 = 0 ;
		double sumDiscrp2afterppn = 0 ;
		
		double sumTotalNoPpn =0;
		double sumTotalWithPpn = 0;
		double sumTotalAfterdiscNoPpn =0;
		double sumTotalAfterdiscWithPpn = 0;
		double record = 0; 
		for (Object itemId : itemIds) {
			FtPurchased item = new FtPurchased();
			item = model.getBeanItemContainerDetil().getItem(itemId).getBean();
			
			//antisipasi
			if (item.getDisc1()==null) item.setDisc1(0.0);
			if (item.getDisc2()==null) item.setDisc2(0.0);
			
			record++;
			sumDiscrp1 += item.getDisc1rp();
			sumDiscrp1afterppn += item.getDisc1rpafterppn();
			sumDiscrp2 += item.getDisc2rp();
			sumDiscrp2afterppn += item.getDisc2rpafterppn();
			
			sumTotalNoPpn += item.getSubtotal();
			sumTotalWithPpn += item.getSubtotalafterppn();
			sumTotalAfterdiscNoPpn += item.getSubtotalafterdisc();
			sumTotalAfterdiscWithPpn += item.getSubtotalafterdiscafterppn();
		}

		model.itemHeader.setAmount(sumTotalNoPpn);
		model.itemHeader.setAmountafterppn(sumTotalWithPpn);
		
		//DETIL
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
		nf.setMinimumIntegerDigits(0);
		try{
			view.getTableDetil().setColumnFooter("disc1rp", nf.format(sumDiscrp1));
			view.getTableDetil().setColumnFooter("disc1rpafterppn", nf.format(sumDiscrp1afterppn));
			view.getTableDetil().setColumnFooter("disc2rp", nf.format(sumDiscrp2));
			view.getTableDetil().setColumnFooter("disc2rpfaterppn", nf.format(sumDiscrp2afterppn));
			
			view.getTableDetil().setColumnFooter("subtotal", nf.format(sumTotalNoPpn));
			view.getTableDetil().setColumnFooter("subtotalafterppn", nf.format(sumTotalWithPpn));
			view.getTableDetil().setColumnFooter("subtotalafterdisc", nf.format(sumTotalAfterdiscNoPpn));
			view.getTableDetil().setColumnFooter("subtotalafterdiscafterppn", nf.format(sumTotalAfterdiscWithPpn	));
		} catch(Exception ex){}
		//HEADER
		Double disc1persen = model.itemHeader.getDisc1()/100;
		Double disc1rp = sumTotalAfterdiscNoPpn * disc1persen;
		Double disc1rpafterppn = sumTotalAfterdiscWithPpn * disc1persen;
		Double disc2persen = model.itemHeader.getDisc2()/100;
		Double disc2rp = sumTotalAfterdiscNoPpn * disc2persen;
		Double disc2rpafterppn = sumTotalAfterdiscWithPpn * disc2persen;
		
		model.itemHeader.setDisc1rp((double) Math.round(disc1rp));
		model.itemHeader.setDisc2rp((double) Math.round(disc2rp));
		model.itemHeader.setDisc1rpafterppn((double) Math.round(disc1rpafterppn) );
		model.itemHeader.setDisc2rpafterppn((double) Math.round(disc2rpafterppn));

		//AKUMULASI DIS 1+2
		Double sumTotalAfterdisc12Noppn = sumTotalAfterdiscNoPpn - disc1rp - disc2rp;
		Double sumTotalAfterdisc12Withppn = sumTotalAfterdiscWithPpn - disc1rpafterppn - disc2rpafterppn;
		
		Double discpersen = model.itemHeader.getDisc()/100;
		Double discrp = sumTotalAfterdisc12Noppn * discpersen;
		Double discrpafterppn = sumTotalAfterdisc12Withppn * discpersen;
		model.itemHeader.setDiscrp((double) Math.round(discrp));
		model.itemHeader.setDiscrpafterppn((double) Math.round(discrpafterppn));
		
		//AKUMULAS DDPP DAN PPN
		Double sumTotalDpp = sumTotalAfterdisc12Noppn - discrp;
		Double sumTotalPpn = (sumTotalAfterdisc12Noppn - discrp)* getParamPpn()/100;
		
		model.itemHeader.setAmountafterdisc((double) Math.round(sumTotalDpp));
		model.itemHeader.setPpnpercent(getParamPpn());
		model.itemHeader.setPpnrp((double) Math.round(sumTotalPpn));

		Double sumTotalAfterdiscAfterppn = sumTotalAfterdisc12Withppn - discrpafterppn;		
		model.itemHeader.setAmountafterdiscafterppn((double) Math.round(sumTotalAfterdiscAfterppn));
		
		//::REFRESH
		model.getBinderHeader().setItemDataSource(model.getItemHeader());

	}
	
	public List<FtPurchased> updateAndCalculateItemDetilFromList(List<FtPurchased> list) {
		List<FtPurchased> newList = new ArrayList<FtPurchased>();
		Iterator<FtPurchased> iter = list.iterator();
		
		while (iter.hasNext()) {			
			model.itemDetil = new FtPurchased();
			model.itemDetil  = iter.next();
			
			FProduct fProduct = new FProduct();
			fProduct = model.itemDetil .getFproductBean();

//			FtPurchasedPK id = new FtPurchasedPK();
//			id.setRefno(model.getItemHeader().getRefno());
//			id.setId(fProduct.getId());
//			item.setId(id);
//			
//			item.setFproductBean(fProduct);
//			item.setFtpurchasehBean(model.getItemHeader());
			
			try{
				//PRICE+PPN ::: PRICE AFTER PPN DIBULATKAN LANGSUNG
				Double amountPpnPrice = model.itemDetil.getPprice() * getParamPpn()/100;					
				Double priceAfterppn = (double) Math.round(model.itemDetil.getPprice() + amountPpnPrice);
				model.itemDetil.setPpriceafterppn((double) Math.round(priceAfterppn));
				 
				//QTY :: PCS -> QTY(BES,SED,KEC)
//				Integer qty = item.getQty1() * fProduct.getConvfact1() 
//						+ item.getQty2() * fProduct.getConvfact2() 
//						+ item.getQty3();
//				item.setQty(qty);
				
				//QTY1
				Integer qty1 = model.itemDetil .getQty()/fProduct.getConvfact1();
				model.itemDetil .setQty1(qty1);
				//QTY2
				Integer sisaqty1 = model.itemDetil .getQty() % fProduct.getConvfact1();
				Integer qty2 = sisaqty1/fProduct.getConvfact2();
				model.itemDetil .setQty2(qty2);
				//QTY3
				Integer sisaqty2 = sisaqty1 % fProduct.getConvfact2();
				Integer qty3 = sisaqty2;
				model.itemDetil .setQty3(qty3);
				
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

				newList.add(model.itemDetil);
				
			} catch(Exception ex){}
			
		}
		return newList;
	}
	
	public boolean isValidAddOrUpdateItemAdd(){
		boolean isValid = true;
		String message = "";
		//QTY TIDAK BOLEH KOSONG
		if (view.getItemDetilModel().getItemDetil().getQty()==0) {
			isValid = false;
			message += "\n::Qty tidak boleh kosong!";
		}
		//KODE BARANG TIDAK BOLEH SAMA
		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
		for (Object itemId: itemIds) {
			FtPurchased item = new FtPurchased();
			item = model.getBeanItemContainerDetil().getItem(itemId).getBean();
			if (item.getFproductBean().getId() == view.getItemDetilModel().getItemDetil().getFproductBean().getId()) {
				isValid = false;
				message += "\n::Product tidak boleah sama!";
				
			}
		}
		
		
		if (isValid==false) {
			Notification.show(message, Notification.TYPE_TRAY_NOTIFICATION);
		}
		return isValid;
	}
	
	public boolean isValidAddOrUpdateItemEdit(){
		boolean isValid = true;
		String message = "";
		//QTY TIDAK BOLEH KOSONG
		if (view.getItemDetilModel().getItemDetil().getQty()==0) {
			isValid = false;
			message += "\n::Qty tidak boleh kosong!";
		}
		//KODE BARANG TIDAK BOLEH SAMA
//		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
//		for (Object itemId: itemIds) {
//			FtPurchased item = new FtPurchased();
//			item = model.getBeanItemContainerDetil().getItem(itemId).getBean();
//			if (item.getFproductBean().getId() == view.getItemDetilModel().getItemDetil().getFproductBean().getId()) {
//				isValid = false;
//				message += "\n::Product tidak boleah sama!";
//				
//			}
//		}
		
		
		if (isValid==false) {
			Notification.show(message, Notification.TYPE_TRAY_NOTIFICATION);
		}
		return isValid;
	}
	
	public boolean isValidSaveFormAdding(){
		boolean isValid = true;
		String message = "";
		
		if (model.getItemHeader().getFvendorBean()==null) {
			isValid = false;
			message += "::Vendor/Supplier masih kosong!";
			
		}
		if (model.getItemHeader().getFwarehouseBean()==null) {
			isValid = false;
			message += "\n::GUDANG masih kosong!";			
		}
		
		//ITEM DETIL TIDAK BOLEH KOSONG
		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
		if (itemIds.size()==0) {
			isValid = false;
			message += "\n::Item masih kosong!";			
		}
		//TANGGAL TRANSAKSI TIDAK BOLEH KURANG DARI TANGGAL BERJALAN
		if (model.getTransaksiHelper().getCurrentTransDate().getTime()> view.getDateFieldInvoicedate().getValue().getTime()) {
			isValid = false;
			message += " \n::Tanggal INVOICE tidak boleh lebih kecil dari tanggal transaksi berjalan!\n" +
			"TANGAL TRANSAKSI BERJALAN: " + new SimpleDateFormat("dd/MM/yyyy").format(model.getTransaksiHelper().getCurrentTransDate());						
		}
		
		if (isValid==false) {
			Notification.show(message, Notification.TYPE_TRAY_NOTIFICATION);
		}
		return isValid;
		
	}
	public boolean isValidSaveFormEditing(){
		boolean isValid = true;
		String message = "";
		
		if (model.getItemHeader().getFvendorBean()==null) {
			isValid = false;
			message += "::Vendor/Supplier masih kosong!";
			
		}
		if (model.getItemHeader().getFwarehouseBean()==null) {
			isValid = false;
			message += "\n::GUDANG masih kosong!";			
		}
		
		//ITEM DETIL TIDAK BOLEH KOSONG
		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
		if (itemIds.size()==0) {
			isValid = false;
			message += "\n::Item masih kosong!";
			
		}
		//TANGGAL TRANSAKSI TIDAK BOLEH KURANG DARI TANGGAL BERJALAN
		if (model.getTransaksiHelper().getCurrentTransDate().getTime()> view.getDateFieldPodate().getValue().getTime()) {
			isValid = false;
			message += " \n::Tanggal PO tidak boleh lebih kecil dari tanggal transaksi berjalan!\n" +
			"TANGAL TRANSAKSI BERJALAN: " + new SimpleDateFormat("dd/MM/yyyy").format(model.getTransaksiHelper().getCurrentTransDate());						
		}
		
		
		
		if (isValid==false) {
			Notification.show(message, Notification.TYPE_TRAY_NOTIFICATION);
		}
		return isValid;
		
	}
	public boolean isValidEditForm(){
		boolean isValid = true;
		String message = "";
		
		
		if (model.getItemHeader()==null) {
			isValid = false;
			message += "::BELUM ADA YANG DIPILIH!";						
		}
		try{
			if (model.getItemHeader().getEndofday()==true){
				isValid = false;
				message += "\n::TIDAK BISA EDIT, SUDAH PROSES AKHIR HARI!";			
			}
		} catch(Exception ex){}
		
		if (isValid==false) {
			Notification.show(message, Notification.TYPE_TRAY_NOTIFICATION);
		}
		return isValid;
	}
	
	public boolean isValidPrintAndInvoice(){
		boolean isValid = true;
		String message = "";
		
		
		if (model.getItemHeader()==null) {
			isValid = false;
			message += "::BELUM ADA YANG DIPILIH!";						
		}
		try{
			if (model.getItemHeader().getInvoiceno().trim().equals("")){
				isValid = false;
				message += "\n::BELUM TERBIT NOMOR ORDER";			
			}
		} catch(Exception ex){}
		
		//TANGGAL TRANSAKSI TIDAK BOLEH KURANG DARI TANGGAL BERJALAN
		try{
			if (model.getTransaksiHelper().getCurrentTransDate().getTime()> view.getDateFieldInvoicedate().getValue().getTime()) {
				isValid = false;
				message += " \n::Tanggal INVOICE tidak boleh lebih kecil dari tanggal transaksi berjalan!\n" +
				"TANGAL TRANSAKSI BERJALAN: " + new SimpleDateFormat("dd/MM/yyyy").format(model.getTransaksiHelper().getCurrentTransDate());						
			}
		} catch(Exception ex){}
		
		if (isValid==false) {
			Notification.show(message, Notification.TYPE_TRAY_NOTIFICATION);
		}
		return isValid;
		
	}
	
	
}
