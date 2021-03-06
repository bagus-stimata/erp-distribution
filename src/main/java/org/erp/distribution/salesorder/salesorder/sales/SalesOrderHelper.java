package org.erp.distribution.salesorder.salesorder.sales;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.erp.distribution.ar.util.SaldoPiutangCust;
import org.erp.distribution.ar.util.SaldoPiutangCustImpl;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FParamDiskonItemVendor;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FPromo;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtArpaymentd;
import org.erp.distribution.model.FtArpaymentdPK;
import org.erp.distribution.model.FtArpaymenth;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesdPK;
import org.erp.distribution.model.FtSalesdPromoTprb;
import org.erp.distribution.model.FtSalesdPromoTpruCb;
import org.erp.distribution.model.FtSalesdPromoTpruDisc;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.model.modelenum.EnumProteksiStatus;
import org.erp.distribution.util.FormatAndConvertionUtil;
import org.erp.distribution.util.HeaderDetilSalesHelper;
import org.erp.distribution.util.HeaderDetilSalesHelperImpl;
import org.erp.distribution.util.KonversiProductAndStock;

import com.vaadin.ui.Notification;

public class SalesOrderHelper {
	
	private SalesOrderModel model;
	private SalesOrderView view;
	
	public SalesOrderHelper(SalesOrderModel model, SalesOrderView view){
		this.model = model;
		this.view = view;
	}
	
	public SalesOrderHelper(){
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

	private double detailTotalAfterdiscWithPpn;
	private double detailTotalAfterdiscNoPpn;
	
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
			FtSalesd item = new FtSalesd();
			item = model.getBeanItemContainerDetil().getItem(itemId).getBean();
			
			//antisipasi
			if (item.getDisc1()==null) item.setDisc1(0.0);
			if (item.getDisc2()==null) item.setDisc2(0.0);
			
			HeaderDetilSalesHelper headerDetilHelper = new HeaderDetilSalesHelperImpl(item);
			headerDetilHelper.setRoundedTotal(true);
			item = new FtSalesd();
			item = headerDetilHelper.getFillFtSalesd();
			
			if (item.getDisc1rp()==0 && item.getDisc1()>0) item.setDisc1rp(headerDetilHelper.getDetilDisc1Rp());
			if (item.getDisc1rpafterppn()==0 && item.getDisc1()>0) item.setDisc1rpafterppn(headerDetilHelper.getDetilDisc1RpAfterPpn());
			if (item.getDisc2rp()==0 && item.getDisc2()>0) item.setDisc2rp(headerDetilHelper.getDetilDisc2Rp());
			if (item.getDisc2rpafterppn()==0 && item.getDisc2()>0) item.setDisc2rpafterppn(headerDetilHelper.getDetilDisc2RpAfterPpn());
			
			//end antisipasi
			
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

		
		//DETIL
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
		nf.setMinimumIntegerDigits(0);
		
		try{
			view.getTableDetil().setColumnFooter("disc1rp", nf.format(sumDiscrp1));
			view.getTableDetil().setColumnFooter("disc1rpafterppn", nf.format(sumDiscrp1afterppn));
			view.getTableDetil().setColumnFooter("disc2rp", nf.format(sumDiscrp2));
			view.getTableDetil().setColumnFooter("disc2rpafterppn", nf.format(sumDiscrp2afterppn));
			
			view.getTableDetil().setColumnFooter("subtotal", nf.format(sumTotalNoPpn));
			view.getTableDetil().setColumnFooter("subtotalafterppn", nf.format(sumTotalWithPpn));
			view.getTableDetil().setColumnFooter("subtotalafterdisc", nf.format(sumTotalAfterdiscNoPpn));
			view.getTableDetil().setColumnFooter("subtotalafterdiscafterppn", nf.format(sumTotalAfterdiscWithPpn));
		} catch(Exception ex){}
		try{
			detailTotalAfterdiscNoPpn = sumTotalAfterdiscNoPpn;
			detailTotalAfterdiscWithPpn = sumTotalAfterdiscWithPpn;
		} catch(Exception ex){}
		
//		## ANTISIPASI HEADER
		if (model.itemHeader.getDisc1()==null) model.itemHeader.setDisc1(Double.parseDouble(view.getFieldDisc1().getValue()));
		if (model.itemHeader.getDisc1()==null) model.itemHeader.setDisc1(0.0);
		if (model.itemHeader.getDisc2()==null) model.itemHeader.setDisc2(Double.parseDouble(view.getFieldDisc2().getValue()));
		if (model.itemHeader.getDisc2()==null) model.itemHeader.setDisc2(0.0);
		if (model.itemHeader.getDisc()==null) model.itemHeader.setDisc(Double.parseDouble(view.getFieldDisc().getValue()));
		if (model.itemHeader.getDisc()==null) model.itemHeader.setDisc(0.0);

		//NEW AMOUNT DISC AFTER RECALCULATE FROM DETIL::KEMUNGKINAN KESALAHAN KETEM
		model.itemHeader.setAmount(detailTotalAfterdiscNoPpn);
		model.itemHeader.setAmountafterppn(detailTotalAfterdiscWithPpn);
		
		//## hitung HEADER
		HeaderDetilSalesHelper headerDetilSalesHelper = new HeaderDetilSalesHelperImpl(model.itemHeader);		
		headerDetilSalesHelper.setRoundedTotal(true);
		model.itemHeader = new FtSalesh();
		model.itemHeader = headerDetilSalesHelper.getFillFtSalesh();
		
		
		//::REFRESH
		model.getBinderHeader().setItemDataSource(model.getItemHeader());

	}
	
	public List<FtSalesd> updateAndCalculateItemDetilFromList(List<FtSalesd> list) {
		List<FtSalesd> newList = new ArrayList<FtSalesd>();
		Iterator<FtSalesd> iter = list.iterator();

		while (iter.hasNext()) {			
			model.itemDetil = new FtSalesd();
			model.itemDetil = iter.next();
			
			FProduct fProduct = new FProduct();
			fProduct = model.itemDetil.getFproductBean();

			try{
				HeaderDetilSalesHelper headerDetilSalesHelper = new HeaderDetilSalesHelperImpl(model.itemDetil);		
				headerDetilSalesHelper.setRoundedTotal(true);
				model.itemDetil = new FtSalesd();
				model.itemDetil = headerDetilSalesHelper.getFillFtSalesd();

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
			FtSalesd item = new FtSalesd();
			item = model.getBeanItemContainerDetil().getItem(itemId).getBean();

			if (item.getFproductBean().getId() == view.getItemDetilModel().getItemDetil().getFproductBean().getId() &&
					item.getId().getFreegood() ==view.getItemDetilModel().getItemDetil().getId().getFreegood()) {
				isValid = false;
				message += "\n::Product tidak boleh sama!";
				
			}
			
		}
		
		//CEK STOK
		if (! isStockmencukupi()) {
			if (model.getSysvarHelper().isAllowStockMinus()==false) {
				isValid =false;
			}
			message += "\n::KUANTITAS STOK BARANG TIDAK MENCUKUPI!";
		}
		
		if (isValid==false) {
			Notification.show(message, Notification.TYPE_TRAY_NOTIFICATION);
		}
		return isValid;
	}
	
	public boolean isStockmencukupi(){
		boolean isStockCukup=true;
		
		Date tanggalStock=view.getDateFieldInvoicedate().getValue();
		FWarehouse fWarehouse = new FWarehouse();
		fWarehouse = model.getItemHeader().getFwarehouseBean();
		FProduct fProduct = new FProduct();
		fProduct = model.getItemDetil().getFproductBean();
		
		int jumlahSaldoStock = model.getProductAndStockHelper().getSaldoStock(fWarehouse, fProduct, tanggalStock);
		if (jumlahSaldoStock<model.getItemDetil().getQty()) {
			isStockCukup = false;
			
			String strJumlahStock = "";
			Integer qty1 = jumlahSaldoStock/fProduct.getConvfact1();
			strJumlahStock += String.valueOf(qty1) + fProduct.getUom1()+ " " ;
			//QTY2
			Integer sisaqty1 = jumlahSaldoStock % fProduct.getConvfact1();
			Integer qty2 = sisaqty1/fProduct.getConvfact2();
			strJumlahStock += String.valueOf(qty2)+fProduct.getUom2()+ " " ;
			//QTY3
			Integer sisaqty2 = sisaqty1 % fProduct.getConvfact2();
			Integer qty3 = sisaqty2;
			strJumlahStock += String.valueOf(qty3)+fProduct.getUom3();
			
			Notification.show("Saldo Stock: " + strJumlahStock, Notification.TYPE_TRAY_NOTIFICATION);
		}
		
		return isStockCukup;
	}
	
	public boolean isValidAddOrUpdateItemEdit(){
		boolean isValid = true;
		String message = "";
		//QTY TIDAK BOLEH KOSONG
		if (view.getItemDetilModel().getItemDetil().getQty()==0) {
			isValid = false;
			message += "\n::Qty tidak boleh kosong!";
		}
		
		//CEK STOK
		if (! isStockmencukupi()) {
			if (model.getSysvarHelper().isAllowStockMinus()==false) {
				isValid =false;
			}
			message += "\n::KUANTITAS STOK BARANG TIDAK MENCUKUPI!";
		}
		
		if (isValid==false) {
			Notification.show(message, Notification.TYPE_TRAY_NOTIFICATION);
		}
		return isValid;
	}
	
	public boolean isValidSaveFormAdding(){
		boolean isValid = true;
		String message = "";
		
		if (model.getItemHeader().getTipejual()==null) {
			isValid = false;
			message += "::Tipe Jual masih kosong";
			
		}
		if (model.getItemHeader().getFsalesmanBean()==null) {
			isValid = false;
			message += "::SALESMAN masih kosong!";			
		}
		if (model.getItemHeader().getFcustomerBean()==null) {
			isValid = false;
			message += "::CUSTOMER masih kosong!";			
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
		
		if (model.getItemHeader().getFsalesmanBean()==null) {
			isValid = false;
			message += "::Salesman masih kosong!";
			
		}
		if (model.getItemHeader().getTipejual()==null) {
			isValid = false;
			message += "::Tipe Jual masih kosong";
			
		}
		if (model.getItemHeader().getFcustomerBean()==null) {
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
		if ( ! model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
			if (model.getTransaksiHelper().getCurrentTransDate().getTime()> view.getDateFieldInvoicedate().getValue().getTime()) {
				isValid = false;
				message += " \n::Tanggal INVOICE tidak boleh lebih kecil dari tanggal transaksi berjalan!\n" +
				"TANGAL TRANSAKSI BERJALAN: " + new SimpleDateFormat("dd/MM/yyyy").format(model.getTransaksiHelper().getCurrentTransDate());						
			}
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
			if (model.getSysvarHelper().isRepairMode()==false){
				if (model.getItemHeader().getEndofday()==true){
					isValid = false;
					message += "\n::TIDAK BISA EDIT, SUDAH PROSES AKHIR HARI!";			
				}
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
			if (model.getItemHeader().getOrderno().trim().equals("")){
				isValid = false;
				message += "\n::BELUM TERBIT NOMOR ORDER";			
			}
		} catch(Exception ex){}
		
		//TANGGAL TRANSAKSI TIDAK BOLEH KURANG DARI TANGGAL BERJALAN
		try{
			if ( ! model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
				if (model.getTransaksiHelper().getCurrentTransDate().getTime()> view.getDateFieldInvoicedate().getValue().getTime()) {
					isValid = false;
					message += " \n::Tanggal INVOICE tidak boleh lebih kecil dari tanggal transaksi berjalan!\n" +
					"TANGAL TRANSAKSI BERJALAN: " + new SimpleDateFormat("dd/MM/yyyy").format(model.getTransaksiHelper().getCurrentTransDate());						
				}
			}
		} catch(Exception ex){}
		
		if (isValid==false) {
			Notification.show(message, Notification.TYPE_TRAY_NOTIFICATION);
		}
		return isValid;
		
	}
	
	public void lunaskan(){
		lunaskan(model.getItemHeader());
	}
	
	public void lunaskan(FtSalesh itemHeader){
		
		FormatAndConvertionUtil myFormatUtil = new FormatAndConvertionUtil();
//		Double totalKurangLebihBayar = myFormatUtil.convertStringToDouble((String) view.getFieldAmountPaySum().getConvertedValue());

		try{
			
			//SYARAT INVOICE DILUNASKAN
			//1. Diseleksi dari Table
			//2. Belum Lunas
				
				FtSalesh itemArinvoice = new FtSalesh();
				itemArinvoice  = itemHeader;

//					String newRefno = model.getManagerTransaksi().getNewNomorUrutArPayment(division);
					String newNorek = model.getTransaksiHelper().getNextFtArpaymenthRefno();
					//TABLE PAYMENT:: BUAT HEADER
					FtArpaymenth itemArpaymentHeader = new FtArpaymenth();
//					ArpaymentheaderPK id = new ArpaymentheaderPK();
//					id.setRefno(newRefno);
//					id.setDivision(itemArinvoice.getDivisionBean().getId());
//					itemArpaymentHeader.setId(id);				
					itemArpaymentHeader.setNorek(newNorek);
//					itemArpaymentHeader.setDivisionBean(itemArinvoice.getDivisionBean());
					
					//JIKA PAKE TANGGAL TRANSAKSI MANUAL MAKA
					itemArpaymentHeader.setTrdate(itemHeader.getInvoicedate());
					
					itemArpaymentHeader.setUserid("admin");
					itemArpaymentHeader.setClosing(false);			
					itemArpaymentHeader.setEndofday(false);
					//****UPDATE HEADER
					model.getFtArpaymenthJpaService().createObject(itemArpaymentHeader);

					//TABLE PAYMENT:: DETAIL
					FtArpaymentd itemArpaymentDetail = new FtArpaymentd();
					
					FtArpaymentdPK itemDetailPK = new FtArpaymentdPK();
					FtArpaymentdPK arpaymentdetailPK = new FtArpaymentdPK();
					arpaymentdetailPK.setRefnopayment(itemArpaymentHeader.getRefno());
					arpaymentdetailPK.setRefnosales(itemArinvoice.getRefno());
		//			arpaymentdetailPK.setDivision(divisionBean.getId());
					itemArpaymentDetail.setId(arpaymentdetailPK);				
		//			model.arPaymentDetail.setDivisionBean(divisionBean);
					
					
					itemArpaymentDetail.setFtarpaymenthBean(itemArpaymentHeader);
					itemArpaymentDetail.setFtsaleshBean(itemArinvoice);
					
//					itemArpaymentDetail.setDivisionBean(itemArinvoice.getDivisionBean());
					itemArpaymentDetail.setFtsaleshBean(itemArinvoice);
					
					double jumlahBayar = itemArinvoice.getAmountafterdiscafterppn() + itemArinvoice.getAmountrevisi()
							- itemArinvoice.getAmountpay()  -itemArinvoice.getAmountreturtampung();
					itemArpaymentDetail.setCashamountpay(jumlahBayar);
					itemArpaymentDetail.setSubtotalpay(jumlahBayar);
					
					itemArpaymentDetail.setGiroamountpay(0.0);
					itemArpaymentDetail.setTransferamountpay(0.0);
					itemArpaymentDetail.setReturamountpay(0.0);
					itemArpaymentDetail.setPotonganamount(0.0);
					itemArpaymentDetail.setKelebihanbayaramount(0.0);
					//****UPDATE DETAIL
					model.getFtArpaymentdJpaService().updateObject(itemArpaymentDetail);
					
					//UPDATE INVOICE
					double returTampung = 0.0;
					if (itemArinvoice.isReturtampunglunas()==false){
						returTampung = itemArinvoice.getAmountreturtampung();
					}
					itemArinvoice.setAmountpay(itemArinvoice.getAmountafterdiscafterppn() + itemArinvoice.getAmountrevisi() - returTampung);
					
            		if (itemArinvoice.getAmountafterdiscafterppn() + itemArinvoice.getAmountrevisi() > itemArinvoice.getAmountpay()){
            			itemArinvoice.setLunas(false);
            		}else{
            			itemArinvoice.setLunas(true);
            		}
            		
					model.getFtSaleshJpaService().updateObject(itemArinvoice);
					
		} catch(Exception ex){
			Notification.show("Error Pelunasan!!");		                        		
			ex.printStackTrace();
		}
		
	}
	
	public void calculateParamDiskonNota(){

		if (! model.getParamDiskonNota().equals(null) 
				&& (model.getParamDiskonNota().getAllsubgrup()==true 
						|| (model.getParamDiskonNota().getAllsubgrup()==false 
							&& model.getItemHeader().getFcustomerBean().getFcustomersubgroupBean().equals(model.getParamDiskonNota().getFcustomersubgroupBean())))){
			try{
				if (model.getParamDiskonNota().getTunaikredit().equals("A") 
						|| (model.getParamDiskonNota().getTunaikredit().equals("T") && model.getItemHeader().getTunaikredit().equals("T")) 
						|| (model.getParamDiskonNota().getTunaikredit().equals("K") && model.getItemHeader().getTunaikredit().equals("K"))){
					
					if (detailTotalAfterdiscWithPpn > model.getParamDiskonNota().getNominal5() && model.getParamDiskonNota().getNominal5()>0) {
						model.itemHeader.setDisc1(model.getParamDiskonNota().getDiskon5());
						model.itemHeader.setDisc(model.getParamDiskonNota().getDiskon5plus());
					} else if (detailTotalAfterdiscWithPpn > model.getParamDiskonNota().getNominal4() && model.getParamDiskonNota().getNominal4()>0) {
						model.itemHeader.setDisc1(model.getParamDiskonNota().getDiskon4());
						model.itemHeader.setDisc(model.getParamDiskonNota().getDiskon4plus());
						
					} else if (detailTotalAfterdiscWithPpn > model.getParamDiskonNota().getNominal3() && model.getParamDiskonNota().getNominal3()>0) {
						model.itemHeader.setDisc1(model.getParamDiskonNota().getDiskon3());
						model.itemHeader.setDisc(model.getParamDiskonNota().getDiskon3plus());
						
					} else if (detailTotalAfterdiscWithPpn > model.getParamDiskonNota().getNominal2() && model.getParamDiskonNota().getNominal2()>0) {
						model.itemHeader.setDisc1(model.getParamDiskonNota().getDiskon2());
						model.itemHeader.setDisc(model.getParamDiskonNota().getDiskon2plus());
						
					} else if (detailTotalAfterdiscWithPpn > model.getParamDiskonNota().getNominal1() && model.getParamDiskonNota().getNominal1()>0) {
						model.itemHeader.setDisc1(model.getParamDiskonNota().getDiskon1());
						model.itemHeader.setDisc(model.getParamDiskonNota().getDiskon1plus());
					}
				}
			} catch(Exception ex){
//				ex.printStackTrace();
			}
		}
	}
	
	List<FtSalesd> listTempForTprb = new ArrayList<FtSalesd>();
	List<FtSalesdPromoTprb> listFtSalesdPromoTprb = new ArrayList<FtSalesdPromoTprb>();
	List<FtSalesdPromoTpruDisc> listFtSalesdPromoTpruDisc = new ArrayList<FtSalesdPromoTpruDisc>();
	List<FtSalesdPromoTpruCb> listFtSalesdPromoTpruCb = new ArrayList<FtSalesdPromoTpruCb>();
	
	public void cekAktifitasPromo(){
		listTempForTprb = new ArrayList<FtSalesd>();
		listFtSalesdPromoTprb = new ArrayList<FtSalesdPromoTprb>();
		listFtSalesdPromoTpruDisc = new ArrayList<FtSalesdPromoTpruDisc>();
		listFtSalesdPromoTpruCb = new ArrayList<FtSalesdPromoTpruCb>();
		
		//1. Ambil Daftar Promo
		List<FPromo> listFPromoBerjalanItem = new ArrayList<FPromo>();
		listFPromoBerjalanItem = model.getfPromoJpaService2().findAllPromoBerjalanItemBarangActive(view.getDateFieldInvoicedate().getValue());

		for (FPromo fPromo: listFPromoBerjalanItem){
			promoBarangPerPromo(fPromo);			
		}
		
		List<FPromo> listFPromoBerjalanGrup = new ArrayList<FPromo>();
		listFPromoBerjalanGrup = model.getfPromoJpaService2().findAllPromoBerjalanGrupBarangActive(view.getDateFieldInvoicedate().getValue());
		for (FPromo fPromo: listFPromoBerjalanGrup){
			promoGrupBarangPerPromo(fPromo);
		}
		
		
//		###****SANGAT BERBEDA SEKALI DENGAN DIATAS
		List<FPromo> listFPromoBerjalanGrupAkumulasi = new ArrayList<FPromo>();
		listFPromoBerjalanGrupAkumulasi = model.getfPromoJpaService2().findAllPromoBerjalanGrupBarangAkumulasiActive(view.getDateFieldInvoicedate().getValue());
		for (FPromo fPromo: listFPromoBerjalanGrupAkumulasi){
			promoGrupBarangAkumulasiPerPromo(fPromo);
		}
		
		//2. Cek Per Daftar Promo pada --> Container
		//3. Jika Masuk Taruh pada Penampungan Sementara List Item TPRB, Diskon Barang, CashBack
		//4. Masukkan ke FtSalesdTprb, FtSalesdTprDisc, FtSalesdTprCb
		
		//Masukkan ke database dan itemDetil
		
	}
	public void promoBarangPerPromo(FPromo fPromoBean){
		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
		for (Object itemId: itemIds){
			FtSalesd ftSalesd = new FtSalesd();
			ftSalesd = model.getBeanItemContainerDetil().getItem(itemId).getBean();	
			//TPRBARANG
			List<FtSalesd> listFtSalesdBonusTobeAddedToDetil = new ArrayList<FtSalesd>();
			listFtSalesdBonusTobeAddedToDetil = getPromoBonusBarangPerItem(fPromoBean, ftSalesd);
			if (listFtSalesdBonusTobeAddedToDetil.size()>0){
				for (FtSalesd ftSalesdBonus:listFtSalesdBonusTobeAddedToDetil){
					if (ftSalesdBonus.getId().getFreegood()==true){
						FtSalesd newFtSalesdForTemp = new FtSalesd();
						HeaderDetilSalesHelper headerDetilSalesHelper = new HeaderDetilSalesHelperImpl(ftSalesdBonus);
						headerDetilSalesHelper.setRoundedTotal(true);
						newFtSalesdForTemp = headerDetilSalesHelper.getFillFtSalesdOnly();
						listTempForTprb.add(newFtSalesdForTemp);

						//DATA PROMO
						FtSalesdPromoTprb ftSalesdPromoTprb = new FtSalesdPromoTprb(); 
						ftSalesdPromoTprb.setRefnoPromo((long)0);
						ftSalesdPromoTprb.setNourut(0);
						
						ftSalesdPromoTprb.setfProductBean(newFtSalesdForTemp.getFproductBean());
						ftSalesdPromoTprb.setfPromoBean(fPromoBean);
						ftSalesdPromoTprb.setFtSalesdBean(ftSalesd);
						
						//Hitung Sub total
						double hargaJualPcs 
							= newFtSalesdForTemp.getFproductBean().getSprice()/newFtSalesdForTemp.getFproductBean().getConvfact1();
						double subTotalBeforePpn = hargaJualPcs * newFtSalesdForTemp.getQty();
						double subTotalAfterPpn = subTotalBeforePpn * 1.1;
						ftSalesdPromoTprb.setTprbafterppn(subTotalAfterPpn);
						ftSalesdPromoTprb.setTprbqty(newFtSalesdForTemp.getQty());
						listFtSalesdPromoTprb.add(ftSalesdPromoTprb);
					}
				}
			}
			
			//TPRDISC
			double disc1Persen = getPromoDiscItemBarang(fPromoBean, ftSalesd);
			if (disc1Persen>0){
				
				ftSalesd.setDisc1(ftSalesd.getDisc1() + disc1Persen);
				model.getFtSalesdJpaService().updateObject(ftSalesd);
				
				FtSalesdPromoTpruDisc ftSalesdPromoTprDisc = new FtSalesdPromoTpruDisc(); 
				ftSalesdPromoTprDisc.setRefnoPromo((long) 0);
				ftSalesdPromoTprDisc.setNourut(0);
				
				ftSalesdPromoTprDisc.setfPromoBean(fPromoBean);
				ftSalesdPromoTprDisc.setFtSalesdBean(ftSalesd);
				
				//Hitung Sub total
				ftSalesdPromoTprDisc.setTprudiscpersen(disc1Persen);
				//JUMLAH DISKON
				double disc1Rp = ftSalesd.getSubtotal() * disc1Persen * 1.1;
				ftSalesdPromoTprDisc.setTprudiscafterppn(disc1Rp);
				
				listFtSalesdPromoTpruDisc.add(ftSalesdPromoTprDisc);
				
			}
			
//			getPromoCb(fPromoBean);
			
		}
		List<FtSalesd> listFtSalesdBonusToAdd = new ArrayList<FtSalesd>();
		for (FtSalesd ftSalesdBonusDihitung: listTempForTprb){			
			if (listFtSalesdBonusToAdd.indexOf(ftSalesdBonusDihitung)>=0) {
				FtSalesd newFtSalesd = new FtSalesd();
				int index = listFtSalesdBonusToAdd.indexOf(ftSalesdBonusDihitung);
				newFtSalesd = listFtSalesdBonusToAdd.get(index);
				newFtSalesd.setQty(newFtSalesd.getQty()+ftSalesdBonusDihitung.getQty());
				listFtSalesdBonusToAdd.set(index, newFtSalesd);				
				model.getFtSalesdJpaService().updateObject(newFtSalesd);
			}else {
				listFtSalesdBonusToAdd.add(ftSalesdBonusDihitung);
				model.getFtSalesdJpaService().updateObject(ftSalesdBonusDihitung);
			}
			
		}
		model.getBeanItemContainerDetil().addAll(listFtSalesdBonusToAdd);
				
		
		//Save data base TPRB, TPRDisc, TPRCb
		int nourutTprb=1;
		for (FtSalesdPromoTprb promoItem: listFtSalesdPromoTprb){
			promoItem.setNourut(nourutTprb++);
			try{
				model.getFtSalesdPromoTprbJpaService().createObject(promoItem);
			} catch(Exception ex){}
		}
		
		int nourutTprDisc=1;
		for (FtSalesdPromoTpruDisc promoItem: listFtSalesdPromoTpruDisc){
			promoItem.setNourut(nourutTprDisc++);
			try{
				model.getFtSalesdPromoTpruDiscJpaService().createObject(promoItem);
			} catch(Exception ex){}
		}
		
		
	}
	public void promoGrupBarangPerPromo(FPromo fPromoBean){
		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
		for (Object itemId: itemIds){
			FtSalesd ftSalesd = new FtSalesd();
			ftSalesd = model.getBeanItemContainerDetil().getItem(itemId).getBean();	
			//TPRBARANG
			List<FtSalesd> listFtSalesdBonusTobeAddedToDetil = new ArrayList<FtSalesd>();
			listFtSalesdBonusTobeAddedToDetil = getPromoBonusGrupBarangPerItem(fPromoBean, ftSalesd);
			if (listFtSalesdBonusTobeAddedToDetil.size()>0){
				for (FtSalesd ftSalesdBonus:listFtSalesdBonusTobeAddedToDetil){
					if (ftSalesdBonus.getId().getFreegood()==true){
						FtSalesd newFtSalesdForTemp = new FtSalesd();
						HeaderDetilSalesHelper headerDetilSalesHelper = new HeaderDetilSalesHelperImpl(ftSalesdBonus);
						headerDetilSalesHelper.setRoundedTotal(true);
						newFtSalesdForTemp = headerDetilSalesHelper.getFillFtSalesdOnly();
						listTempForTprb.add(newFtSalesdForTemp);

						//DATA PROMO
						FtSalesdPromoTprb ftSalesdPromoTprb = new FtSalesdPromoTprb(); 
						ftSalesdPromoTprb.setRefnoPromo((long)0);
						ftSalesdPromoTprb.setNourut(0);
						
						ftSalesdPromoTprb.setfProductBean(newFtSalesdForTemp.getFproductBean());
						ftSalesdPromoTprb.setfPromoBean(fPromoBean);
						ftSalesdPromoTprb.setFtSalesdBean(ftSalesd);
						
						//Hitung Sub total
						double hargaJualPcs 
							= newFtSalesdForTemp.getFproductBean().getSprice()/newFtSalesdForTemp.getFproductBean().getConvfact1();
						double subTotalBeforePpn = hargaJualPcs * newFtSalesdForTemp.getQty();
						double subTotalAfterPpn = subTotalBeforePpn * 1.1;
						ftSalesdPromoTprb.setTprbafterppn(subTotalAfterPpn);
						ftSalesdPromoTprb.setTprbqty(newFtSalesdForTemp.getQty());
						listFtSalesdPromoTprb.add(ftSalesdPromoTprb);
					}
				}
			}
			
			//TPRDISC
			double disc1Persen = getPromoDiscGrupBarang(fPromoBean, ftSalesd);
			if (disc1Persen>0){
				ftSalesd.setDisc1(ftSalesd.getDisc1() + disc1Persen);
				model.getFtSalesdJpaService().updateObject(ftSalesd);
				
				FtSalesdPromoTpruDisc ftSalesdPromoTprDisc = new FtSalesdPromoTpruDisc(); 
				ftSalesdPromoTprDisc.setRefnoPromo((long) 0);
				ftSalesdPromoTprDisc.setNourut(0);
				
				ftSalesdPromoTprDisc.setfPromoBean(fPromoBean);
				ftSalesdPromoTprDisc.setFtSalesdBean(ftSalesd);
				
				//Hitung Sub total
				ftSalesdPromoTprDisc.setTprudiscpersen(disc1Persen);
				//JUMLAH DISKON
				double disc1Rp = ftSalesd.getSubtotal() * disc1Persen * 1.1;
				ftSalesdPromoTprDisc.setTprudiscafterppn(disc1Rp);
				
				listFtSalesdPromoTpruDisc.add(ftSalesdPromoTprDisc);
				
			}
			
//			getPromoCb(fPromoBean);
			
		}
		List<FtSalesd> listFtSalesdBonusToAdd = new ArrayList<FtSalesd>();
		for (FtSalesd ftSalesdBonusDihitung: listTempForTprb){
			
			if (listFtSalesdBonusToAdd.indexOf(ftSalesdBonusDihitung)>=0) {
				FtSalesd newFtSalesd = new FtSalesd();
				int index = listFtSalesdBonusToAdd.indexOf(ftSalesdBonusDihitung);
				newFtSalesd = listFtSalesdBonusToAdd.get(index);
				newFtSalesd.setQty(newFtSalesd.getQty()+ftSalesdBonusDihitung.getQty());
				listFtSalesdBonusToAdd.set(index, newFtSalesd);				
				model.getFtSalesdJpaService().updateObject(newFtSalesd);
			}else {
				listFtSalesdBonusToAdd.add(ftSalesdBonusDihitung);
				model.getFtSalesdJpaService().updateObject(ftSalesdBonusDihitung);
			}
		}
		model.getBeanItemContainerDetil().addAll(listFtSalesdBonusToAdd);
		
		//Save data base TPRB, TPRDisc, TPRCb
		int nourutTprb=1;
		for (FtSalesdPromoTprb promoItem: listFtSalesdPromoTprb){
			promoItem.setNourut(nourutTprb++);
			try{
				model.getFtSalesdPromoTprbJpaService().createObject(promoItem);
			} catch(Exception ex){}
		}
		
		int nourutTprDisc=1;
		for (FtSalesdPromoTpruDisc promoItem: listFtSalesdPromoTpruDisc){
			promoItem.setNourut(nourutTprDisc++);
			try{
				model.getFtSalesdPromoTpruDiscJpaService().createObject(promoItem);
			} catch(Exception ex){}
		}
		
	}
	public void promoGrupBarangAkumulasiPerPromo(FPromo fPromoBean){
		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
		Set<FProductgroup> setFProductGroup = new HashSet<FProductgroup>();
		List<FtSalesd> listFtSalesdPerGroup = new ArrayList<FtSalesd>();
		for (Object itemId: itemIds){
			FtSalesd ftSalesd = new FtSalesd();
			ftSalesd = model.getBeanItemContainerDetil().getItem(itemId).getBean();	
			//HITUNG BARANG YANG MASUK KE GRUP
			List<FtSalesd> listFtSalesdPerGroupItem = new ArrayList<FtSalesd>();
			listFtSalesdPerGroupItem = getPromoBonusGrupBarangAkumulasiPerItem(fPromoBean, ftSalesd);			
	
			if (listFtSalesdPerGroupItem.size()>0){
				listFtSalesdPerGroup.add(ftSalesd);
				setFProductGroup.add(ftSalesd.getFproductBean().getFproductgroupBean());
			}
			
			//TPRDISC
//			double disc1Persen = getPromoDiscGrupBarang(fPromoBean, ftSalesd);
//			if (disc1Persen>0){
//				ftSalesd.setDisc1(ftSalesd.getDisc1() + disc1Persen);
//				model.getBeanItemContainerDetil().addBean(ftSalesd);
//				model.getFtSalesdJpaService().updateObject(ftSalesd);
//				
//				FtSalesdPromoTpruDisc ftSalesdPromoTprDisc = new FtSalesdPromoTpruDisc(); 
//				ftSalesdPromoTprDisc.setRefnoPromo((long) 0);
//				ftSalesdPromoTprDisc.setNourut(0);
//				
//				ftSalesdPromoTprDisc.setfPromoBean(fPromoBean);
//				ftSalesdPromoTprDisc.setFtSalesdBean(ftSalesd);
//				
//				//Hitung Sub total
//				ftSalesdPromoTprDisc.setTprudiscpersen(disc1Persen);
//				//JUMLAH DISKON
//				double disc1Rp = ftSalesd.getSubtotal() * disc1Persen * 1.1;
//				ftSalesdPromoTprDisc.setTprudiscafterppn(disc1Rp);
//				
//				listFtSalesdPromoTpruDisc.add(ftSalesdPromoTprDisc);
//				
//			}
			
			
		}
		
		List<FtSalesd> listFtSalesdBonusTobeAddedToDetil = new ArrayList<FtSalesd>();			
		listFtSalesdBonusTobeAddedToDetil = 
				getPromoBonusGrupBarangAkumulasiPerGroup(fPromoBean, setFProductGroup, listFtSalesdPerGroup);
		
		for (FtSalesd ftSalesdBonus: listFtSalesdBonusTobeAddedToDetil){
			FtSalesd newFtSalesdForTemp = new FtSalesd();
			HeaderDetilSalesHelper headerDetilSalesHelper = new HeaderDetilSalesHelperImpl(ftSalesdBonus);
			headerDetilSalesHelper.setRoundedTotal(true);
			newFtSalesdForTemp = headerDetilSalesHelper.getFillFtSalesdOnly();
			listTempForTprb.add(newFtSalesdForTemp);
			
		}
		
		
		List<FtSalesd> listFtSalesdBonusToAdd = new ArrayList<FtSalesd>();
		for (FtSalesd ftSalesdBonusDihitung: listTempForTprb){
			
			if (listFtSalesdBonusToAdd.indexOf(ftSalesdBonusDihitung)>=0) {
				FtSalesd newFtSalesd = new FtSalesd();
				int index = listFtSalesdBonusToAdd.indexOf(ftSalesdBonusDihitung);
				newFtSalesd = listFtSalesdBonusToAdd.get(index);
				newFtSalesd.setQty(newFtSalesd.getQty()+ftSalesdBonusDihitung.getQty());
				listFtSalesdBonusToAdd.set(index, newFtSalesd);				
				model.getFtSalesdJpaService().updateObject(newFtSalesd);
			}else {
				listFtSalesdBonusToAdd.add(ftSalesdBonusDihitung);
				model.getFtSalesdJpaService().updateObject(ftSalesdBonusDihitung);
			}
		}
		model.getBeanItemContainerDetil().addAll(listFtSalesdBonusToAdd);
				
		//###** TIDAK DI PAKE KARENA SUDAH DI DEFINISIAN DI SUB
//		//Save data base TPRB, TPRDisc, TPRCb
//		int nourutTprb=1;
//		for (FtSalesdPromoTprb promoItem: listFtSalesdPromoTprb){
//			promoItem.setNourut(nourutTprb++);
//			try{
//				model.getFtSalesdPromoTprbJpaService().createObject(promoItem);
//			} catch(Exception ex){}
//		}
		
//		int nourutTprDisc=1;
//		for (FtSalesdPromoTpruDisc promoItem: listFtSalesdPromoTpruDisc){
//			promoItem.setNourut(nourutTprDisc++);
//			try{
//				model.getFtSalesdPromoTpruDiscJpaService().createObject(promoItem);
//			} catch(Exception ex){}
//		}
		
		
	}
	
	public List<FtSalesd> getPromoBonusBarangPerItem(FPromo fPromoBean, FtSalesd ftSalesd){
		List<FtSalesd> listFtSalesdTobeAdded = new ArrayList<FtSalesd>();
		if (fPromoBean.getfProductBean().getId()==ftSalesd.getFproductBean().getId()){
			if (fPromoBean.getForFcustomersubgroup().equals("ALL") 
					|| 
					( fPromoBean.getForFcustomersubgroup().equals("IN") && 
							ftSalesd.getFtsaleshBean().getFcustomerBean()
							.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
					|| 
					( fPromoBean.getForFcustomersubgroup().equals("EX") && 
							! ftSalesd.getFtsaleshBean().getFcustomerBean()
							.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
							){
				
					int tprbItem = 0;
					//:: JIKA FREE GOOD MAKA YA TIDAK BERLAKU
					if (! ftSalesd.getId().getFreegood()==true){
	
						//::PROMO BARANG TPRB
						if (ftSalesd.getQty() > fPromoBean.getFreeQty4() && fPromoBean.getFreeQty4()>0) {
							if (fPromoBean.getFreeKelipatan()==true){
								tprbItem = fPromoBean.getFreeQtyGet4() * Math.round(ftSalesd.getQty()/fPromoBean.getFreeQty4());
							}else {
								tprbItem = fPromoBean.getFreeQtyGet4();	
							}
						}else if (ftSalesd.getQty() > fPromoBean.getFreeQty3() && fPromoBean.getFreeQty3()>0) {						
							if (fPromoBean.getFreeKelipatan()==true){
								tprbItem = fPromoBean.getFreeQtyGet3() * Math.round(ftSalesd.getQty()/fPromoBean.getFreeQty3());
							}else {
								tprbItem = fPromoBean.getFreeQtyGet3();	
							}
						}else if (ftSalesd.getQty() > fPromoBean.getFreeQty2() && fPromoBean.getFreeQty2()>0) {
							if (fPromoBean.getFreeKelipatan()==true){
								tprbItem = fPromoBean.getFreeQtyGet2() * Math.round(ftSalesd.getQty()/fPromoBean.getFreeQty2());
							}else {
								tprbItem = fPromoBean.getFreeQtyGet2();	
							}
						}else if (ftSalesd.getQty() > fPromoBean.getFreeQty1() && fPromoBean.getFreeQty1()>0) {
							if (fPromoBean.getFreeKelipatan()==true){
								tprbItem = fPromoBean.getFreeQtyGet1() * Math.round(ftSalesd.getQty()/fPromoBean.getFreeQty1());
							}else {
								tprbItem = fPromoBean.getFreeQtyGet1();	
							}
						}
						
					}
					
					//BERARTI JIKA DAPAT DISKON
					if (tprbItem>0){
						FtSalesd ftSalesdTobeAdded = new FtSalesd();					
						ftSalesdTobeAdded = resetFtSalesd(ftSalesdTobeAdded);
						//PERBAIKI ID
						FtSalesdPK id = new FtSalesdPK();
						id.setRefno(ftSalesd.getFtsaleshBean().getRefno());
						id.setFreegood(true);
						id.setId(fPromoBean.getFreeFproductBean().getId());					
						ftSalesdTobeAdded.setId(id);
						
						ftSalesdTobeAdded.setPromo(true);
						
						ftSalesdTobeAdded.setFproductBean(fPromoBean.getFreeFproductBean());
						ftSalesdTobeAdded.setFtsaleshBean(ftSalesd.getFtsaleshBean());
						ftSalesdTobeAdded.setQty(tprbItem);
						
						listFtSalesdTobeAdded.add(ftSalesdTobeAdded);
						
					}
					
			}
		}
		return listFtSalesdTobeAdded;		
	}
	public List<FtSalesd> getPromoBonusGrupBarangPerItem(FPromo fPromoBean, FtSalesd ftSalesd){
		List<FtSalesd> listFtSalesdTobeAdded = new ArrayList<FtSalesd>();
		if (fPromoBean.getFproductgroupBean().getId().equals(ftSalesd.getFproductBean().getFproductgroupBean().getId())){
			if (fPromoBean.getForFcustomersubgroup().equals("ALL") 
					|| 
					( fPromoBean.getForFcustomersubgroup().equals("IN") && 
							ftSalesd.getFtsaleshBean().getFcustomerBean()
							.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
					|| 
					( fPromoBean.getForFcustomersubgroup().equals("EX") && 
							! ftSalesd.getFtsaleshBean().getFcustomerBean()
							.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
							){
				
					int tprbItem = 0;
					//:: JIKA FREE GOOD MAKA YA TIDAK BERLAKU
					if (! ftSalesd.getId().getFreegood()==true){
	
						//::PROMO BARANG TPRB
						if (ftSalesd.getQty() > fPromoBean.getFreeQty4() && fPromoBean.getFreeQty4()>0) {
							if (fPromoBean.getFreeKelipatan()==true){
								tprbItem = fPromoBean.getFreeQtyGet4() * Math.round(ftSalesd.getQty()/fPromoBean.getFreeQty4());
							}else {
								tprbItem = fPromoBean.getFreeQtyGet4();	
							}
						}else if (ftSalesd.getQty() > fPromoBean.getFreeQty3() && fPromoBean.getFreeQty3()>0) {						
							if (fPromoBean.getFreeKelipatan()==true){
								tprbItem = fPromoBean.getFreeQtyGet3() * Math.round(ftSalesd.getQty()/fPromoBean.getFreeQty3());
							}else {
								tprbItem = fPromoBean.getFreeQtyGet3();	
							}
						}else if (ftSalesd.getQty() > fPromoBean.getFreeQty2() && fPromoBean.getFreeQty2()>0) {
							if (fPromoBean.getFreeKelipatan()==true){
								tprbItem = fPromoBean.getFreeQtyGet2() * Math.round(ftSalesd.getQty()/fPromoBean.getFreeQty2());
							}else {
								tprbItem = fPromoBean.getFreeQtyGet2();	
							}
						}else if (ftSalesd.getQty() > fPromoBean.getFreeQty1() && fPromoBean.getFreeQty1()>0) {
							if (fPromoBean.getFreeKelipatan()==true){
								tprbItem = fPromoBean.getFreeQtyGet1() * Math.round(ftSalesd.getQty()/fPromoBean.getFreeQty1());
							}else {
								tprbItem = fPromoBean.getFreeQtyGet1();	
							}
						}
						
					}
					
					//BERARTI JIKA DAPAT DISKON
					if (tprbItem>0){
						FtSalesd ftSalesdTobeAdded = new FtSalesd();					
						ftSalesdTobeAdded = resetFtSalesd(ftSalesdTobeAdded);
						//PERBAIKI ID
						FtSalesdPK id = new FtSalesdPK();
						id.setRefno(ftSalesd.getFtsaleshBean().getRefno());
						id.setFreegood(true);
						id.setId(fPromoBean.getFreeFproductBean().getId());					
						ftSalesdTobeAdded.setId(id);
						
						ftSalesdTobeAdded.setPromo(true);
						ftSalesdTobeAdded.setFproductBean(fPromoBean.getFreeFproductBean());
						ftSalesdTobeAdded.setFtsaleshBean(ftSalesd.getFtsaleshBean());
						ftSalesdTobeAdded.setQty(tprbItem);
						
						listFtSalesdTobeAdded.add(ftSalesdTobeAdded);
						
					}
					
			}
		}
		return listFtSalesdTobeAdded;		
	}
	public List<FtSalesd> getPromoBonusGrupBarangAkumulasiPerItem(FPromo fPromoBean, FtSalesd ftSalesd){
		List<FtSalesd> listFtSalesdTobeAdded = new ArrayList<FtSalesd>();
		if (fPromoBean.getFproductgroupBean().getId().equals(ftSalesd.getFproductBean().getFproductgroupBean().getId())){
			if (fPromoBean.getForFcustomersubgroup().equals("ALL") 
					|| 
					( fPromoBean.getForFcustomersubgroup().equals("IN") && 
							ftSalesd.getFtsaleshBean().getFcustomerBean()
							.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
					|| 
					( fPromoBean.getForFcustomersubgroup().equals("EX") && 
							! ftSalesd.getFtsaleshBean().getFcustomerBean()
							.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
							){
						if (! ftSalesd.getId().getFreegood()==true){
							listFtSalesdTobeAdded.add(ftSalesd);
						}
						
			}
					
		}
		return listFtSalesdTobeAdded;		
	}
	public List<FtSalesd> getPromoBonusGrupBarangAkumulasiPerGroup(FPromo fPromoBean, 
				Set<FProductgroup> setFProductGroup, List<FtSalesd> listFtSalesdGroup){
		
		List<FtSalesd> listFtSalesdTobeAdded = new ArrayList<FtSalesd>();
		
		for (FProductgroup fProductGroup: setFProductGroup){
			//HITUNG JUMLAH QTY PER GROUP
			int qtyFtSalesdSum = 0;
			double subTotalAfterPpnSum = 0;
			FtSalesd ftSalesdTobeToGivePromo = new FtSalesd();
			for (FtSalesd ftSalesd:listFtSalesdGroup){
				if (ftSalesd.getFproductBean().getFproductgroupBean().equals(fPromoBean.getFproductgroupBean())){
					//:: JIKA FREE GOOD MAKA YA TIDAK BERLAKU
					qtyFtSalesdSum += ftSalesd.getQty();
					//DIAMBIL URUTAN YANG PALING AKHIR
					ftSalesdTobeToGivePromo = new FtSalesd();	
					ftSalesdTobeToGivePromo = ftSalesd;
					
					//Untuk Dikon
					subTotalAfterPpnSum += ftSalesd.getSubtotalafterppn();
				}
			}
			//HITUNG JUMLAH FREE QTYNYA
			int tprbItem = 0;
			//::PROMO BARANG TPRB
			if (qtyFtSalesdSum > fPromoBean.getFreeQty4() && fPromoBean.getFreeQty4()>0) {
				if (fPromoBean.getFreeKelipatan()==true){
					tprbItem = fPromoBean.getFreeQtyGet4() * Math.round(qtyFtSalesdSum/fPromoBean.getFreeQty4());
				}else {
					tprbItem = fPromoBean.getFreeQtyGet4();	
				}
			}else if (qtyFtSalesdSum > fPromoBean.getFreeQty3() && fPromoBean.getFreeQty3()>0) {						
				if (fPromoBean.getFreeKelipatan()==true){
					tprbItem = fPromoBean.getFreeQtyGet3() * Math.round(qtyFtSalesdSum/fPromoBean.getFreeQty3());
				}else {
					tprbItem = fPromoBean.getFreeQtyGet3();	
				}
			}else if (qtyFtSalesdSum > fPromoBean.getFreeQty2() && fPromoBean.getFreeQty2()>0) {
				if (fPromoBean.getFreeKelipatan()==true){
					tprbItem = fPromoBean.getFreeQtyGet2() * Math.round(qtyFtSalesdSum/fPromoBean.getFreeQty2());
					
				}else {
					tprbItem = fPromoBean.getFreeQtyGet2();	
				}
			}else if (qtyFtSalesdSum > fPromoBean.getFreeQty1() && fPromoBean.getFreeQty1()>0) {
				if (fPromoBean.getFreeKelipatan()==true){
					tprbItem = fPromoBean.getFreeQtyGet1() * Math.round(qtyFtSalesdSum/fPromoBean.getFreeQty1());
				}else {
					tprbItem = fPromoBean.getFreeQtyGet1();	
				}
			}
			
			//BERARTI JIKA DAPAT DISKON
			if (tprbItem>0){
				FtSalesd ftSalesdTobeAdded = new FtSalesd();					
				ftSalesdTobeAdded = resetFtSalesd(ftSalesdTobeAdded);
				//PERBAIKI ID
				FtSalesdPK id = new FtSalesdPK();
				id.setRefno(model.getItemHeader().getRefno());
				id.setFreegood(true);
				id.setId(fPromoBean.getFreeFproductBean().getId());					
				ftSalesdTobeAdded.setId(id);
				
				ftSalesdTobeAdded.setPromo(true);
				ftSalesdTobeAdded.setFproductBean(fPromoBean.getFreeFproductBean());
				ftSalesdTobeAdded.setFtsaleshBean(model.getItemHeader());
				ftSalesdTobeAdded.setQty(tprbItem);
				
				//###NILAI BALIK
				listFtSalesdTobeAdded.add(ftSalesdTobeAdded);
				
//				//simpan langsung ke database
				//DATA PROMO
				FtSalesdPromoTprb ftSalesdPromoTprb = new FtSalesdPromoTprb(); 
				ftSalesdPromoTprb.setRefnoPromo((long)0);
				ftSalesdPromoTprb.setNourut(0);
				
				ftSalesdPromoTprb.setfProductBean(fPromoBean.getFreeFproductBean());
				ftSalesdPromoTprb.setfPromoBean(fPromoBean);
				
				ftSalesdPromoTprb.setFtSalesdBean(ftSalesdTobeToGivePromo);
				
				//Hitung Sub total
				double hargaJualPcs 
					= fPromoBean.getFreeFproductBean().getSprice()/fPromoBean.getFreeFproductBean().getConvfact1();
				double subTotalBeforePpn = hargaJualPcs * tprbItem;
				double subTotalAfterPpn = subTotalBeforePpn * 1.1;
				ftSalesdPromoTprb.setTprbafterppn(subTotalAfterPpn);
				ftSalesdPromoTprb.setTprbqty(tprbItem);
				
				listFtSalesdPromoTprb.add(ftSalesdPromoTprb);
				
			}
			//Save data base TPRB, TPRDisc, TPRCb
			int nourutTprb=1;
			for (FtSalesdPromoTprb promoItem: listFtSalesdPromoTprb){
				promoItem.setNourut(nourutTprb++);
				try{
					model.getFtSalesdPromoTprbJpaService().createObject(promoItem);
				} catch(Exception ex){}
			}
	
			double disc1Persen=0;
			//::PROMO DISKON TPRUDISC
			if (subTotalAfterPpnSum > fPromoBean.getDiscValue4() && fPromoBean.getDiscValue4()>0) {
				disc1Persen = fPromoBean.getDiscPercentGet4();
			}else if (subTotalAfterPpnSum>fPromoBean.getDiscValue3() && fPromoBean.getDiscValue3()>0){
				disc1Persen = fPromoBean.getDiscPercentGet3();						
			}else if (subTotalAfterPpnSum>fPromoBean.getDiscValue2() && fPromoBean.getDiscValue2()>0){
				disc1Persen = fPromoBean.getDiscPercentGet2();
			}else if (subTotalAfterPpnSum>fPromoBean.getDiscValue1() && fPromoBean.getDiscValue1()>0){
				disc1Persen = fPromoBean.getDiscPercentGet1();
			}
			
			List<FtSalesdPromoTpruDisc> listFtSalesdPromoTpruDisc = new ArrayList<FtSalesdPromoTpruDisc>();
			if (disc1Persen>0) {
				for (FtSalesd ftSalesd:listFtSalesdGroup){
					if (ftSalesd.getFproductBean().getFproductgroupBean().equals(fPromoBean.getFproductgroupBean())){
//						TPRDISC
						ftSalesd.setDisc1(ftSalesd.getDisc1() + disc1Persen);
						model.getFtSalesdJpaService().updateObject(ftSalesd);
						
						FtSalesdPromoTpruDisc ftSalesdPromoTprDisc = new FtSalesdPromoTpruDisc(); 
						ftSalesdPromoTprDisc.setRefnoPromo((long) 0);
						ftSalesdPromoTprDisc.setNourut(0);
						
						ftSalesdPromoTprDisc.setfPromoBean(fPromoBean);
						ftSalesdPromoTprDisc.setFtSalesdBean(ftSalesd);
						
						//Hitung Sub total
						ftSalesdPromoTprDisc.setTprudiscpersen(disc1Persen);
						//JUMLAH DISKON
						double disc1Rp = ftSalesd.getSubtotal() * disc1Persen * 1.1;
						ftSalesdPromoTprDisc.setTprudiscafterppn(disc1Rp);
						
						listFtSalesdPromoTpruDisc.add(ftSalesdPromoTprDisc);
						
					
					}
				}
				
				int nourutTprDisc=1;
				for (FtSalesdPromoTpruDisc promoItem: listFtSalesdPromoTpruDisc){
					promoItem.setNourut(nourutTprDisc++);
					try{
						model.getFtSalesdPromoTpruDiscJpaService().createObject(promoItem);
					} catch(Exception ex){}
				}
				
			}
			
			
			
		}
		
		return listFtSalesdTobeAdded;				
	}

	public double getPromoDiscGrupBarang(FPromo fPromoBean, FtSalesd ftSalesd){
		double disc1PromoPercent=0;
		if (fPromoBean.getFproductgroupBean().getId().equals(ftSalesd.getFproductBean().getFproductgroupBean().getId())){
			if (fPromoBean.getForFcustomersubgroup().equals("ALL") 
					|| 
					( fPromoBean.getForFcustomersubgroup().equals("IN") && 
							ftSalesd.getFtsaleshBean().getFcustomerBean()
							.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
					|| 
					( fPromoBean.getForFcustomersubgroup().equals("EX") && 
							! ftSalesd.getFtsaleshBean().getFcustomerBean()
							.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
							){
				
				//::PROMO DISKON TPRUDISC
				if (ftSalesd.getSubtotalafterppn() > fPromoBean.getDiscValue4() && fPromoBean.getDiscValue4()>0) {
					disc1PromoPercent = fPromoBean.getDiscPercentGet4();
				}else if (ftSalesd.getSubtotalafterppn()>fPromoBean.getDiscValue3() && fPromoBean.getDiscValue3()>0){
					disc1PromoPercent = fPromoBean.getDiscPercentGet3();						
				}else if (ftSalesd.getSubtotalafterppn()>fPromoBean.getDiscValue2() && fPromoBean.getDiscValue2()>0){
					disc1PromoPercent = fPromoBean.getDiscPercentGet2();
				}else if (ftSalesd.getSubtotalafterppn()>fPromoBean.getDiscValue1() && fPromoBean.getDiscValue1()>0){
					disc1PromoPercent = fPromoBean.getDiscPercentGet1();
				}
			}
		}
		return disc1PromoPercent;
	}
	public double getPromoDiscItemBarang(FPromo fPromoBean, FtSalesd ftSalesd){
		double disc1PromoPercent=0;
		if (fPromoBean.getfProductBean().getId()==ftSalesd.getFproductBean().getId()){
			if (fPromoBean.getForFcustomersubgroup().equals("ALL") 
					|| 
					( fPromoBean.getForFcustomersubgroup().equals("IN") && 
							ftSalesd.getFtsaleshBean().getFcustomerBean()
							.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
					|| 
					( fPromoBean.getForFcustomersubgroup().equals("EX") && 
							! ftSalesd.getFtsaleshBean().getFcustomerBean()
							.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
							){
				
				//::PROMO DISKON TPRUDISC
				if (ftSalesd.getSubtotalafterppn() > fPromoBean.getDiscValue4() && fPromoBean.getDiscValue4()>0) {
					disc1PromoPercent = fPromoBean.getDiscPercentGet4();
				}else if (ftSalesd.getSubtotalafterppn()>fPromoBean.getDiscValue3() && fPromoBean.getDiscValue3()>0){
					disc1PromoPercent = fPromoBean.getDiscPercentGet3();						
				}else if (ftSalesd.getSubtotalafterppn()>fPromoBean.getDiscValue2() && fPromoBean.getDiscValue2()>0){
					disc1PromoPercent = fPromoBean.getDiscPercentGet2();
				}else if (ftSalesd.getSubtotalafterppn()>fPromoBean.getDiscValue1() && fPromoBean.getDiscValue1()>0){
					disc1PromoPercent = fPromoBean.getDiscPercentGet1();
				}
			}
		}
		return disc1PromoPercent;
	}
	public double getPromoCb(FPromo fPromo){
		return 0;
	}

	public FtSalesd resetFtSalesd(FtSalesd ftSalesd){
		ftSalesd.setPromo(false);
		
		ftSalesd.setFproductBean(new FProduct());
		ftSalesd.setFtsaleshBean(model.getItemHeader());
		
		ftSalesd.setDisc1(0.0);
		ftSalesd.setDisc1rp(0.0);
		ftSalesd.setDisc2(0.0);
		ftSalesd.setDisc2rp(0.0);
		
		ftSalesd.setNourut(0);
		ftSalesd.setSprice(0.0);
		ftSalesd.setSpriceafterppn(0.0);
		ftSalesd.setQty(0);
		ftSalesd.setQty1(0);
		ftSalesd.setQty2(0);
		ftSalesd.setQty3(0);
		ftSalesd.setSubtotal(0.0);
		ftSalesd.setSubtotalafterppn(0.0);
		
		ftSalesd.setTprb(0.0);
		ftSalesd.setTprudisc(0.0);
		ftSalesd.setTprucashback(0.0);
		
		return ftSalesd;
	}

	//AKTIFITAS PROMO

	//PARAMETER DISKON ITEM BY VENDOR
	public void cekAndUpdateParamDiskonItemByVendor(){
		/*
		 * 1. Cari Promo Pada Vendor
		 * 2. --> Cari Item pada item detil dengan vendor tersebut :: Jumlahkan :: Jika memenuhi syarat :: Tambahkan diskon pada masing-masing itemnya
		 * */
		double discItem1 = 0.0;
		//1. CEK PARAMETER DISKON VENDOR YANG AKTIF
		List<FParamDiskonItemVendor> listParamDiskonItem = new ArrayList<FParamDiskonItemVendor>();
		try{
			listParamDiskonItem = model.getfParamDiskonItemVendorJpaService().findAllActive();
		} catch(Exception ex){}
		
		//3. CARI PADA MASING-MASING DISKON VENDOR UNTUK BARANG TERSEBUT
		Iterator<FParamDiskonItemVendor> fParamDiskonItemVendorIter = listParamDiskonItem.iterator();		
		while (fParamDiskonItemVendorIter.hasNext()) {
			FParamDiskonItemVendor fParamDiskonItem = new FParamDiskonItemVendor();
			fParamDiskonItem = fParamDiskonItemVendorIter.next();
			
			FVendor fVendorBean = new FVendor();
			fVendorBean = fParamDiskonItem.getFvendorBean();
			double sumTotalItemDetilPerVendor =0.0;
			//3.1 # Cari Item per Vendor :: LANGSUNG CALKULASIKAN
			Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
			for (Object itemId: itemIds) {
				FtSalesd ftSalesdBean = new FtSalesd();
				ftSalesdBean = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				if (ftSalesdBean.getFproductBean().getFvendorBean().equals(fVendorBean)) {
					sumTotalItemDetilPerVendor += ftSalesdBean.getSubtotalafterppn();
				}
			}

			//3.2 # HASIL KALKULASI DIATAS :: Tentukan diskonnnya
			if (sumTotalItemDetilPerVendor > fParamDiskonItem.getNominal5() && fParamDiskonItem.getNominal5()>0){
				discItem1 = fParamDiskonItem.getDiskon5();
			}else if (sumTotalItemDetilPerVendor > fParamDiskonItem.getNominal4() && fParamDiskonItem.getNominal4()>0){
				discItem1 = fParamDiskonItem.getDiskon4();
			}else if (sumTotalItemDetilPerVendor > fParamDiskonItem.getNominal3() && fParamDiskonItem.getNominal3()>0){
				discItem1 = fParamDiskonItem.getDiskon3();
			}else if  (sumTotalItemDetilPerVendor > fParamDiskonItem.getNominal2() && fParamDiskonItem.getNominal2()>0){
				discItem1 = fParamDiskonItem.getDiskon2();
			}else if  (sumTotalItemDetilPerVendor > fParamDiskonItem.getNominal1() && fParamDiskonItem.getNominal1()>0){
				discItem1 = fParamDiskonItem.getDiskon1();
			}
			
			//3.3 # UPDATE DETIL SETELAH ADA DISKON		
			Collection itemIds2 = model.getBeanItemContainerDetil().getItemIds();
			for (Object itemId: itemIds2) {
				FtSalesd ftSalesdBean = new FtSalesd();
				ftSalesdBean = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				if (ftSalesdBean.getFproductBean().getFvendorBean().equals(fVendorBean)) {
					ftSalesdBean.setDisc1(ftSalesdBean.getDisc1() + discItem1);
					//UPDATE KE ITEM TABLE DAN DATABASE
					model.getFtSalesdJpaService().updateObject(ftSalesdBean);
					model.getBeanItemContainerDetil().addItem(ftSalesdBean);
					view.getTableDetil().addItem(ftSalesdBean);
				}
			}
			
			//3. 4# PERBAIKI TAMPILAN ITEM DETIL
			view.getTableDetil().setContainerDataSource(model.getBeanItemContainerDetil());

		}
		
		view.setDisplayDetil();

	}
		
	public void deletePromoItemAndDiskon(){
		try{
			
			List<FtSalesd> listFtSalesdNonPromo = new ArrayList<FtSalesd>();
			Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
			for (Object itemId: itemIds){
				FtSalesd ftSalesd = new FtSalesd();
				ftSalesd = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				if (ftSalesd.getPromo()==true) {
					model.getFtSalesdJpaService().removeObject(ftSalesd);
				}else {
					listFtSalesdNonPromo.add(ftSalesd);
				}
			}
	
			//HAPUS TPRB
			List<FtSalesdPromoTprb> listFtSalesdPromoTprbToDelete = model.getFtSalesdPromoTprbJpaService().findAllByFtSaleshRefno(model.getItemHeader().getRefno());
			for (FtSalesdPromoTprb domain: listFtSalesdPromoTprbToDelete){
				model.getFtSalesdPromoTprbJpaService().removeObject(domain);
				deleteItemTablePromo(domain.getfProductBean());
			}
			//HAPUS TPRUDISC
			List<FtSalesdPromoTpruDisc> listFtSalesdPromoTpruDiscToDelete = model.getFtSalesdPromoTpruDiscJpaService().findAllByFtSaleshRefno(model.getItemHeader().getRefno());
			for (FtSalesdPromoTpruDisc domain: listFtSalesdPromoTpruDiscToDelete){
				model.getFtSalesdPromoTpruDiscJpaService().removeObject(domain);
				FtSalesd ftSalesdToFind = new FtSalesd();
				FtSalesdPK id = new FtSalesdPK();
				id.setFreegood(false);
				id.setRefno(model.getItemHeader().getRefno());
				id.setId(domain.getFtSalesdBean().getFproductBean().getId());
				ftSalesdToFind.setId(id);
				
				int index = listFtSalesdNonPromo.indexOf(ftSalesdToFind);
				if (index >= 0){
					 FtSalesd ftSalesdFound = new FtSalesd();
					 ftSalesdFound = listFtSalesdNonPromo.get(index);
					 ftSalesdFound.setDisc1(ftSalesdFound.getDisc1()-domain.getTprudiscpersen());
					 listFtSalesdNonPromo.set(index, ftSalesdFound);
				}
			}
			
			model.getBeanItemContainerDetil().removeAllItems();
			model.getBeanItemContainerDetil().addAll(listFtSalesdNonPromo);
			
			Collection itemIdsToUpdate = model.getBeanItemContainerDetil().getItemIds();
			for (Object itemId: itemIdsToUpdate){
				FtSalesd ftSalesd = new FtSalesd();
				ftSalesd = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				model.getFtSalesdJpaService().updateObject(ftSalesd);
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	public void deleteItemTablePromo(FProduct fProductBean){
	}
	
	public List<FtSalesd>  getJumlahBonusPromoPerItem(FtSalesd ftSalesd){
		
			FProduct fProduct = new FProduct();
			fProduct = ftSalesd.getFproductBean();
					
			double pecahanPpn = 0.0;
			pecahanPpn = (getParamPpn()+100) /100;
			
			Date trDate = model.getTransaksiHelper().getCurrentTransDate();
			
			
			//1. CEK PROMOSI :: BY PRODUCT :: DAN CEK PROMO PADA GROUP --> HASILNYA DIGABUNG JADI SATU
			List<FPromo> listFPromoProduct = new ArrayList<FPromo>();
			listFPromoProduct = model.getfPromoJpaService2().findAllPromoActiveByProduct(fProduct);
			
			double sumTpruDisc1Persen = 0.0;
			Double sumTpruDisc1Afterppn = 0.0;
			int sumTprbItem = 0;
			Double sumTprbAfterppn = 0.0;
			
			List<FtSalesd> listFtSalesdPromo = new ArrayList<FtSalesd>();
			List<FtSalesdPromoTprb> ftSalesdPromoTprbSet = new ArrayList<FtSalesdPromoTprb>();
			List<FtSalesdPromoTpruDisc> ftSalesdPromoTpruDiscSet = new ArrayList<FtSalesdPromoTpruDisc>();			
			
//			//###POTONGAN UANG BELUM DIAKTIFKAN NANTI###
//			double sumJumlahCashBack = 0.0;
			
			int nomorUrut=0;
			
			for (FPromo fPromoBean: listFPromoProduct){ 
				
				if (fPromoBean.getfProductBean().getId()==fProduct.getId()) {
				
						if (trDate.getTime() >= fPromoBean.getPeriodeFrom().getTime() 
								&& trDate.getTime() <= fPromoBean.getPeriodeTo().getTime()) {
							if (fPromoBean.getForFcustomersubgroup().equals("ALL") 
									|| 
									( fPromoBean.getForFcustomersubgroup().equals("IN") && 
											ftSalesd.getFtsaleshBean().getFcustomerBean()
											.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
									|| 
									( fPromoBean.getForFcustomersubgroup().equals("EX") && 
											! ftSalesd.getFtsaleshBean().getFcustomerBean()
											.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
											){
								
								 
								
									double disc1PromoPercent = 0.0;
									int tprbItem = 0;
									//:: JIKA FREE GOOD MAKA YA TIDAK BERLAKU
									if (! ftSalesd.getId().getFreegood()==true){
				
										//::PROMO BARANG TPRB
										if (ftSalesd.getQty() > fPromoBean.getFreeQty4() && fPromoBean.getFreeQty4()>0) {
											if (fPromoBean.getFreeKelipatan()==true){
												tprbItem = fPromoBean.getFreeQtyGet4() * Math.round(ftSalesd.getQty()/fPromoBean.getFreeQty4());
											}else {
												tprbItem = fPromoBean.getFreeQtyGet4();	
											}
											sumTprbItem += tprbItem;
										}else if (ftSalesd.getQty() > fPromoBean.getFreeQty3() && fPromoBean.getFreeQty3()>0) {						
											if (fPromoBean.getFreeKelipatan()==true){
												tprbItem = fPromoBean.getFreeQtyGet3() * Math.round(ftSalesd.getQty()/fPromoBean.getFreeQty3());
											}else {
												tprbItem = fPromoBean.getFreeQtyGet3();	
											}
											sumTprbItem += tprbItem;
										}else if (ftSalesd.getQty() > fPromoBean.getFreeQty2() && fPromoBean.getFreeQty2()>0) {
											if (fPromoBean.getFreeKelipatan()==true){
												tprbItem = fPromoBean.getFreeQtyGet2() * Math.round(ftSalesd.getQty()/fPromoBean.getFreeQty2());
											}else {
												tprbItem = fPromoBean.getFreeQtyGet2();	
											}
											sumTprbItem += tprbItem;
										}else if (ftSalesd.getQty() > fPromoBean.getFreeQty1() && fPromoBean.getFreeQty1()>0) {
											if (fPromoBean.getFreeKelipatan()==true){
												tprbItem = fPromoBean.getFreeQtyGet1() * Math.round(ftSalesd.getQty()/fPromoBean.getFreeQty1());
											}else {
												tprbItem = fPromoBean.getFreeQtyGet1();	
											}
											sumTprbItem += tprbItem;
										}
										
										//::PROMO DISKON TPRUDISC
										if (ftSalesd.getSubtotalafterppn() > fPromoBean.getDiscValue4() && fPromoBean.getDiscValue4()>0) {
											sumTpruDisc1Persen += fPromoBean.getDiscPercentGet4();
											disc1PromoPercent = fPromoBean.getDiscPercentGet4();
										}else if (ftSalesd.getSubtotalafterppn()>fPromoBean.getDiscValue3() && fPromoBean.getDiscValue3()>0){
											sumTpruDisc1Persen += fPromoBean.getDiscPercentGet3();
											disc1PromoPercent = fPromoBean.getDiscPercentGet3();						
										}else if (ftSalesd.getSubtotalafterppn()>fPromoBean.getDiscValue2() && fPromoBean.getDiscValue2()>0){
											sumTpruDisc1Persen += fPromoBean.getDiscPercentGet2();
											disc1PromoPercent = fPromoBean.getDiscPercentGet2();
										}else if (ftSalesd.getSubtotalafterppn()>fPromoBean.getDiscValue1() && fPromoBean.getDiscValue1()>0){
											sumTpruDisc1Persen += fPromoBean.getDiscPercentGet1();
											disc1PromoPercent = fPromoBean.getDiscPercentGet1();
										}
									}
									
									nomorUrut++;
									
									//HITUNG DISKON BARANG
									if (sumTprbItem>0){
										
										FtSalesdPromoTprb ftSalesdPromoTprb = new FtSalesdPromoTprb();
										ftSalesdPromoTprb.setfProductBean(fPromoBean.getFreeFproductBean());
										ftSalesdPromoTprb.setfPromoBean(fPromoBean);
										ftSalesdPromoTprb.setFtSalesdBean(ftSalesd);
		//								ftSalesdPromoTprb.setRefnoPromo((long) nomorUrut);
										ftSalesdPromoTprb.setNourut(nomorUrut);
										
										ftSalesdPromoTprb.setTprbqty(tprbItem);
		
										double itemPriceAfterPpn = fPromoBean.getFreeFproductBean().getSprice() * pecahanPpn;
										double itemPricePcsAfterPpn = itemPriceAfterPpn / fProduct.getConvfact1();
										
										double valueTprbAfterppn = tprbItem * itemPricePcsAfterPpn;
										ftSalesdPromoTprb.setTprbafterppn(valueTprbAfterppn); 		
										
										//TAMBAHKAN KE LIST
										ftSalesdPromoTprbSet.add(ftSalesdPromoTprb);
										sumTprbAfterppn += valueTprbAfterppn;
		
										//TAMBAH ITEM KE DETAIL
										FtSalesd ftSalesdPromo = new FtSalesd();
										FtSalesdPK id = new FtSalesdPK();
										id.setFreegood(true);
										id.setRefno(model.getItemHeader().getRefno());
										id.setId(fPromoBean.getFreeFproductBean().getId());
										ftSalesdPromo.setId(id);
										
										ftSalesdPromo.setPromo(true);
										ftSalesdPromo.setFproductBean(fPromoBean.getFreeFproductBean());
										ftSalesdPromo.setFtsaleshBean(model.getItemHeader());
										
										ftSalesdPromo.setQty(tprbItem);
										ftSalesdPromo.setQty1(model.getProductAndStockHelper().getBesFromPcs(tprbItem, 
												ftSalesdPromo.getFproductBean()));
										ftSalesdPromo.setQty2(model.getProductAndStockHelper().getSedFromPcs(tprbItem, 
												ftSalesdPromo.getFproductBean()));
										ftSalesdPromo.setQty3(model.getProductAndStockHelper().getKecFromPcs(tprbItem, 
												ftSalesdPromo.getFproductBean()));
		
										ftSalesdPromo.setDisc1(0.0);
										ftSalesdPromo.setDisc1rp(0.0);
										ftSalesdPromo.setDisc1rpafterppn(0.0);
										ftSalesdPromo.setDisc2(0.0);
										ftSalesdPromo.setDisc2rp(0.0);
										ftSalesdPromo.setDisc2rpafterppn(0.0);
										ftSalesdPromo.setSprice(0.0);
										ftSalesdPromo.setSpriceafterppn(0.0);
										ftSalesdPromo.setSubtotal(0.0);
										ftSalesdPromo.setSubtotalafterppn(0.0);
										ftSalesdPromo.setSubtotalafterdisc(0.0);
										ftSalesdPromo.setSubtotalafterdiscafterppn(.0);
										
										ftSalesdPromo.setTprb(0.0);
										ftSalesdPromo.setTprucashback(0.0);
										ftSalesdPromo.setTprudisc(0.0);
										
										//TAMBAH KE CONTAINER
										listFtSalesdPromo.add(ftSalesdPromo);
										
									}
									//UPDATE JIKA MENDAPAT DISKON SAJA
									if (disc1PromoPercent > 0.0) {
										FtSalesdPromoTpruDisc ftSalesdPromoTpruDisc = new FtSalesdPromoTpruDisc();
					//					ftSalesdPromoTpruDisc.setRefnoPromo((long) nomorUrut);
										ftSalesdPromoTpruDisc.setNourut(nomorUrut);
										ftSalesdPromoTpruDisc.setFtSalesdBean(ftSalesd);
										ftSalesdPromoTpruDisc.setfPromoBean(fPromoBean);
										
										//PERSEN DAN NOMINAL
										ftSalesdPromoTpruDisc.setTprudiscpersen(disc1PromoPercent);
										Double valueOfDiscAfterPpn = ftSalesd.getSubtotalafterppn() * (ftSalesdPromoTpruDisc.getTprudiscpersen() /100);
										ftSalesdPromoTpruDisc.setTprudiscafterppn(valueOfDiscAfterPpn);
										//TAMBAH KE LIST
										ftSalesdPromoTpruDiscSet.add(ftSalesdPromoTpruDisc);
										sumTpruDisc1Afterppn += valueOfDiscAfterPpn;
									}
									
								}
						}					
				}
					
			}
								
			if (sumTprbItem >0 || sumTpruDisc1Persen>0){
				if (sumTprbItem>0) {
					ftSalesd.setTprb(sumTprbAfterppn / pecahanPpn);
					ftSalesd.setFtsalesdPromoTprbList(ftSalesdPromoTprbSet);
				}
				if (sumTpruDisc1Persen>0) {
					ftSalesd.setDisc1(sumTpruDisc1Persen);				
					ftSalesd.setTprudisc(sumTpruDisc1Afterppn / pecahanPpn);
					
					ftSalesd.setFtsalesdPromoTpruDiscList(ftSalesdPromoTpruDiscSet);
				}
				
				//::UPDATE ITEM (FtSales) ke DATABASE 
				model.getFtSalesdJpaService().updateObject(ftSalesd);
			}			
			return listFtSalesdPromo;
					
	}
		
	public List<FtSalesd>  getJumlahBonusPromoPerItemByGroup(FtSalesd ftSalesdParam){
		
			FProduct fProduct = new FProduct();
			fProduct = ftSalesdParam.getFproductBean();
			
			Date trDate = model.getTransaksiHelper().getCurrentTransDate();
			
			//1. Cek Promo Pada Produknya by Group --> HASILNYA DIGABUNG JADI SATU
			List<FPromo> listFPromo = new ArrayList<FPromo>();		
			List<FPromo> listFPromoProductGroup = new ArrayList<FPromo>();

			listFPromoProductGroup = model.getfPromoJpaService2().findAllPromoActiveByProductGroup(fProduct.getFproductgroupBean());
				
			listFPromo.addAll(listFPromoProductGroup);
			
//			System.out.println("AKTIFITAS PROMO GROUP >> UKURAN PRODUCT:GROUP = " + " : " + listFPromoProductGroup.size() + " TOTAL UKURAN: " + listFPromo.size());
			
			List<FtSalesd> listFtSalesdPromo = new ArrayList<FtSalesd>();
			
			for (FPromo fPromoBean: listFPromo){ 
//				if (fPromoBean.getFproductgroupBean().getId() ==fProduct.getFproductgroupBean().getId()) {
				
//				System.out.println("masuk sebetulnya >> DIATAS");
				
						if (trDate.getTime() >= fPromoBean.getPeriodeFrom().getTime() 
								&& trDate.getTime() <= fPromoBean.getPeriodeTo().getTime()) {
							if (fPromoBean.getForFcustomersubgroup().equals("ALL") 
									|| 
									( fPromoBean.getForFcustomersubgroup().equals("IN") && 
											ftSalesdParam.getFtsaleshBean().getFcustomerBean()
											.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
									|| 
									( fPromoBean.getForFcustomersubgroup().equals("EX") && 
											! ftSalesdParam.getFtsaleshBean().getFcustomerBean()
											.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
											){
								
		
									//TAMBAH ITEM KE DETAIL
									FtSalesd ftSalesdPromo = new FtSalesd();
									ftSalesdPromo = ftSalesdParam;
									
									//TAMBAH KE CONTAINER
									listFtSalesdPromo.add(ftSalesdPromo);
									
								}
						}
					
//				}
					
			}

								
//			System.out.println("Jumlah List FtSalesdPromo: " + listFtSalesdPromo.size());
			
			return listFtSalesdPromo;
			
		
	}
	public boolean isValidCreditLimitForNewInvoice(){
		boolean isValid=true;
		FCustomer fCustomer = new FCustomer();
		fCustomer = model.getItemHeader().getFcustomerBean();
		Double crLimit = fCustomer.getCreditlimit();
		Double saldoPiutang = model.getSaldoPiutangCust().getSaldoPiutangPerCustomerMinRetur(fCustomer);
		Double sisaKredit = crLimit-saldoPiutang;
		
		Double nilaiNota = model.getItemHeader().getAmountafterdiscafterppn();
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
		nf.setMinimumIntegerDigits(0);
		//Print Invoice baru
		if (fCustomer.getCreditlimit()!=0 &&  model.itemHeader.getInvoiceno().trim().equals("")){
			if (crLimit<=(saldoPiutang+nilaiNota)) { //HARUS DITAMBAH NOTA SEKARAN
				if (model.getSysvarHelper().getProteksiCreditLimit().equals(EnumProteksiStatus.REJECT.getStrCode())){
					Notification.show("Sisa Kredit: " + nf.format(sisaKredit)
							+ "\nCredit Limit: " + nf.format(fCustomer.getCreditlimit()), Notification.TYPE_ERROR_MESSAGE);
					isValid=false;
				}else if (model.getSysvarHelper().getProteksiCreditLimit().equals(EnumProteksiStatus.WARNING.getStrCode())){
					Notification.show("Sisa Kredit: " + nf.format(sisaKredit) 
							+ "\nCredit Limit: " + nf.format(fCustomer.getCreditlimit()), Notification.TYPE_WARNING_MESSAGE);
					
				}
			}
		}
		return isValid;
	}
	public boolean isValidOpenInvoiceForNewInvoice(){
		boolean isValid=true;
		FCustomer fCustomer = new FCustomer();
		fCustomer = model.getItemHeader().getFcustomerBean();
		int openInvoiceCustomer = fCustomer.getOpeninvoice();
		int openInvoiceNota =  model.getSaldoPiutangCust().getOpenInvoicePerCustomer(fCustomer);
		int sisaOpenInvoice = openInvoiceCustomer-openInvoiceNota;
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
		nf.setMinimumIntegerDigits(0);
		//Print Invoice baru
		if (fCustomer.getCreditlimit()!=0 &&  model.itemHeader.getInvoiceno().trim().equals("")){
			if (openInvoiceNota<=(openInvoiceNota+1)) { //HARUS DITAMBAH NOTA SEKARAN
				if (model.getSysvarHelper().getProteksiCreditLimit().equals(EnumProteksiStatus.REJECT.getStrCode())){
					Notification.show("Sisa Open Invoice: " + nf.format(sisaOpenInvoice) 
							+ "\nOpen Invoice Customer: " + nf.format(fCustomer.getOpeninvoice()), Notification.TYPE_ERROR_MESSAGE);
					isValid=false;
				}else if (model.getSysvarHelper().getProteksiCreditLimit().equals(EnumProteksiStatus.WARNING.getStrCode())){
					Notification.show("Sisa Open Invoice: " + nf.format(sisaOpenInvoice) 
							+ "\nOpen Invoice Customer: " + nf.format(fCustomer.getOpeninvoice()), Notification.TYPE_WARNING_MESSAGE);
				}
			}
		}
		return isValid;
	}
					
}

