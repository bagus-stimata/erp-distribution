package org.erp.distribution.salesorder.salesorder.windowitem;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ognl.ListPropertyAccessor;

import org.apache.poi.hssf.record.common.FtrHeader;
import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FParamDiskonItem;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FPromo;
import org.erp.distribution.model.FtPriceAltd;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesdPK;
import org.erp.distribution.model.FtSalesdPromoTprb;
import org.erp.distribution.model.FtSalesdPromoTpruCb;
import org.erp.distribution.model.FtSalesdPromoTpruDisc;
import org.erp.distribution.util.HeaderDetilSalesHelper;
import org.erp.distribution.util.HeaderDetilSalesHelperImpl;
import org.erp.distribution.util.TransaksiHelper;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.AbstractSelect.ItemDescriptionGenerator;
import com.vaadin.ui.Notification;

public class SalesOrderItemHelper{
	
	private SalesOrderItemModel model;
	private SalesOrderItemView view;
	
	FProduct fProduct = new FProduct();
	
	public SalesOrderItemHelper(SalesOrderItemModel model, SalesOrderItemView view){
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
		
		//PRODUCT INFO DAN STOK
		Date invoiceDate = model.getItemHeader().getInvoicedate();
		int jumlahSaldoStock = model.getProductAndStockHelper().getSaldoStock(model.getItemHeader().getFwarehouseBean(), fProduct, invoiceDate);
		int qtyBes = model.getProductAndStockHelper().getBesFromPcs(jumlahSaldoStock, fProduct);
		int qtySed = model.getProductAndStockHelper().getSedFromPcs(jumlahSaldoStock, fProduct);
		int qtyKec = model.getProductAndStockHelper().getKecFromPcs(jumlahSaldoStock, fProduct);
		if (fProduct.getPcode()!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
			view.getBtnProductInfo().setCaption(sdf.format(invoiceDate) +" ->> "+fProduct.getPcode()+"-"+fProduct.getPname()+
					" "+fProduct.getPackaging()+" ->> "+ qtyBes+ fProduct.getUom1()+" "+qtySed+ fProduct.getUom2()+" "+qtyKec +fProduct.getUom3());
		} else {
			view.getBtnProductInfo().setCaption("Product Info");
		}

	}

	double pecahanPpn = 0.0;
	Double amountPpnPrice = 0.0;
	Double itemPriceAfterPpn = 0.0;
	Double itemPriceBeforePpn = 0.0;
	Double itemPricePcsAfterPpn = 0.0;
	Double itemSubtotalBeforeDiscAfterPpn = 0.0;
	
	public void updateAndCalculateItemDetil(){
		try{
			itemPriceBeforePpn = 0.0;
			amountPpnPrice = 0.0;
			itemPriceAfterPpn = 0.0;
			pecahanPpn = (getParamPpn()+100) /100;
			
			//PRICE+PPN
			if (model.getSysvarHelper().isEntryItemPenjualanHargaSelelahPPN()==true) {		
				itemPriceBeforePpn = model.getItemDetil().getSpriceafterppn() / pecahanPpn;
				model.itemDetil.setSprice(itemPriceBeforePpn);
			}else {
				amountPpnPrice = model.itemDetil.getSprice() * getParamPpn()/100;
				//##PERHITUNGAN BARU
				itemPriceAfterPpn = model.itemDetil.getSprice() * pecahanPpn;
				model.itemDetil.setSpriceafterppn((double) Math.round(itemPriceAfterPpn));
			}
			
			//QTY :: QTY(BES,SED,KEC) -> PCS
			Integer qty = model.itemDetil.getQty1() * fProduct.getConvfact1() 
					+ model.itemDetil.getQty2() * fProduct.getConvfact2() 
					+ model.getItemDetil().getQty3();
			model.itemDetil.setQty(qty);
			
			//PRICE2 PCS
			Double pricePcsNoPpn = model.itemDetil.getSprice() / fProduct.getConvfact1();
			itemPricePcsAfterPpn = itemPriceAfterPpn / fProduct.getConvfact1();
	
//			//CEK AKTIFITAS PROMO:: METHOD ADA DI BAWAHNYA::CEK AKTIFITAS PROMO DI TARUH DI SALESORDER PRESENTER
			//CEK DETIL CUMA BISA DILAKUKAN UNTUK NGECEK BERAPA PERSEN DISKON YANG DIBERIKAN
			//TIDAK BOLEH DIBALIK 
			double diskon1FromParam = 0;
			double diskon1FromAktivitasPromo = 0;
			try{
				diskon1FromParam = cekParamDiskonItem(model.getItemDetil().getSubtotalafterppn());
			} catch(Exception ex){}

//			try{
//				diskon1FromAktivitasPromo = cekAktifitasPromo(model.getItemDetil().getFproductBean());
//			} catch(Exception ex){}
			
			if (model.itemDetil.getDisc1()==0){
				model.itemDetil.setDisc1(diskon1FromParam + diskon1FromAktivitasPromo);				
			}
			
			HeaderDetilSalesHelper headerDetilSalesHelper = new HeaderDetilSalesHelperImpl(model.itemDetil);		
			headerDetilSalesHelper.setRoundedTotal(true);
			model.itemDetil = new FtSalesd();
			model.itemDetil = headerDetilSalesHelper.getFillFtSalesdOnly();
			
		} catch(Exception ex){}
		
		model.getBinderItemDetail().setItemDataSource(model.itemDetil);
		
	
		
	}

	public boolean validasiHargaBelidanHargaJual(){
		boolean isValid=true;
		
		if (model.getItemDetil().getFproductBean().getPprice() > model.getItemDetil().getSprice()) {
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(0);nf.setMaximumIntegerDigits(0);
			Notification.show("Harga Jual Lebih Kecil dari Harga Beli!!!. Harga Beli-ppn Rp." +nf.format( model.getItemDetil().getFproductBean().getPprice()),
					Notification.TYPE_WARNING_MESSAGE);
			isValid=false;
		}
	
		return isValid;
	}
	public double cekParamDiskonItem(double subtotalafterppn){		
		double discItem1 = 0.0;
		//1. CEK
		List<FParamDiskonItem> listParamDiskonItem = 
				new ArrayList<FParamDiskonItem>(model.getfParamDiskonItemJpaService()
						.findAllByVendorAndSubgroupActive(model.getItemDetil().getFproductBean().getFvendorBean(),
								model.getItemDetil().getFproductBean().getFproductgroupBean(),
								model.getItemHeader().getFcustomerBean().getFcustomersubgroupBean()));
		
		Iterator<FParamDiskonItem> fParamDiskonItemIter = listParamDiskonItem.iterator();		
		while (fParamDiskonItemIter.hasNext()) {
			FParamDiskonItem fParamDiskonItem = new FParamDiskonItem();
			fParamDiskonItem = fParamDiskonItemIter.next();
			
			if (subtotalafterppn > fParamDiskonItem.getNominal5() && fParamDiskonItem.getNominal5()>0){
				discItem1 = fParamDiskonItem.getDiskon5();
			}else if (subtotalafterppn > fParamDiskonItem.getNominal4() && fParamDiskonItem.getNominal4()>0){
				discItem1 = fParamDiskonItem.getDiskon4();
			}else if (subtotalafterppn > fParamDiskonItem.getNominal3() && fParamDiskonItem.getNominal3()>0){
				discItem1 = fParamDiskonItem.getDiskon3();
			}else if  (subtotalafterppn > fParamDiskonItem.getNominal2() && fParamDiskonItem.getNominal2()>0){
				discItem1 = fParamDiskonItem.getDiskon2();
			}else if  (subtotalafterppn > fParamDiskonItem.getNominal1() && fParamDiskonItem.getNominal1()>0){
				discItem1 = fParamDiskonItem.getDiskon1();
			}
		}
		
//		System.out.println("Jumlah Item Diskon: " + listParamDiskonItem.size() + ", DISKON: " + discItem1);
		
		return discItem1;
	}
	
	
	 // NILAI BALIK ADALAH --> JUMLAH PERSENTASE DISKON
	public double cekAktifitasPromo(FProduct fProduct){		
		double discPromo1 = 0.0;
		double sumTpruDisc1Persen = 0.0;
		Date trDate = model.getTransaksiHelper().getCurrentTransDate();
		//1. CEK PROMOSI :: BY PRODUCT
		List<FPromo> listFPromo = new ArrayList<FPromo>();		
		List<FPromo> listFPromoProduct = new ArrayList<FPromo>();
		List<FPromo> listFPromoProductgroup = new ArrayList<FPromo>();
		
		listFPromoProduct = model.getfPromoJpaService2().findAllPromoActiveByProduct(fProduct);
		try{
			listFPromoProductgroup = model.getfPromoJpaService2().findAllPromoActiveByProductGroup(fProduct.getFproductgroupBean());
		} catch(Exception ex){}
		
		listFPromo.addAll(listFPromoProduct);
		listFPromo.addAll(listFPromoProductgroup);
		
		Iterator<FPromo> fPromoIter = listFPromoProduct.iterator();
		while (fPromoIter.hasNext()){
			FPromo fPromoBean = new FPromo();
			fPromoBean = fPromoIter.next();
			if (trDate.getTime() >= fPromoBean.getPeriodeFrom().getTime() 
					&& trDate.getTime() <= fPromoBean.getPeriodeTo().getTime()) {
				
				if (fPromoBean.getForFcustomersubgroup().equals("ALL") 
						|| 
						( fPromoBean.getForFcustomersubgroup().equals("IN") && 
								model.getItemDetil().getFtsaleshBean().getFcustomerBean()
								.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
						|| 
						( fPromoBean.getForFcustomersubgroup().equals("EX") && 
								! model.getItemDetil().getFtsaleshBean().getFcustomerBean()
								.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
								){
					
						double disc1PromoPercent = 0.0;
							
							//::PROMO DISKON TPRUDISC
							if (model.getItemDetil().getSubtotalafterppn() > fPromoBean.getDiscValue4() && fPromoBean.getDiscValue4()>0) {
								sumTpruDisc1Persen += fPromoBean.getDiscPercentGet4();
								disc1PromoPercent = fPromoBean.getDiscPercentGet4();
							}else if (model.getItemDetil().getSubtotalafterppn()>fPromoBean.getDiscValue3() && fPromoBean.getDiscValue3()>0){
								sumTpruDisc1Persen += fPromoBean.getDiscPercentGet3();
								disc1PromoPercent = fPromoBean.getDiscPercentGet3();						
							}else if (model.getItemDetil().getSubtotalafterppn()>fPromoBean.getDiscValue2() && fPromoBean.getDiscValue2()>0){
								sumTpruDisc1Persen += fPromoBean.getDiscPercentGet2();
								disc1PromoPercent = fPromoBean.getDiscPercentGet2();
							}else if (model.getItemDetil().getSubtotalafterppn()>fPromoBean.getDiscValue1() && fPromoBean.getDiscValue1()>0){
								sumTpruDisc1Persen += fPromoBean.getDiscPercentGet1();
								disc1PromoPercent = fPromoBean.getDiscPercentGet1();
							}
							
//							System.out.println("TEST: " + sumTpruDisc1Persen + " : " + disc1PromoPercent);
							
						}
				
			}
		}
//		System.out.println("TEST BAWAH: " + sumTpruDisc1Persen );
		if (sumTpruDisc1Persen>0) {
			discPromo1 = sumTpruDisc1Persen;
		}

		return discPromo1;
	}
	
	public void cekAktifitasPromo2(){
		//###CEK AKTIFITAS PROMO DISKON###
		/*
		 * 1. CEK PROMO YANG SEDANG AKTIF DAN BERLAKU
		 * 2. UPDATE
		 * 		- Bonus Barang : Hapus dahulu bonus barang yang ada lalu tambahkan lagi
		 * 		- Diskon 	   : persentase diskon
		 * 3. Update 
		 * 		-ftSalesdPromo : no_promo, nominal_tprb, nominal_tpru_disc, nominal_tpru_cashback
		 * 4. Potongan Uang/tpru dipotongkan untuk nota
		 * 
		 * */
			
			Date trDate = model.getTransaksiHelper().getCurrentTransDate();
			List<FPromo> listFPromo = new ArrayList<FPromo>();		
			listFPromo = model.getfPromoJpaService2().findAllPromoActiveByProduct(fProduct);
			
			double sumTpruDisc1Persen = 0.0;
			
//			//###POTONGAN UANG DIAKTIFKAN NANTI###
//			double sumJumlahCashBack = 0.0;
			
			for (FPromo fPromoBean: listFPromo){ 
				if (trDate.getTime() >= fPromoBean.getPeriodeFrom().getTime() 
						&& trDate.getTime() <= fPromoBean.getPeriodeTo().getTime()) {
					
					if (fPromoBean.getForFcustomersubgroup().equals("ALL") 
							|| 
							( fPromoBean.getForFcustomersubgroup().equals("IN") && 
									model.getItemDetil().getFtsaleshBean().getFcustomerBean()
									.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
							|| 
							( fPromoBean.getForFcustomersubgroup().equals("EX") && 
									! model.getItemDetil().getFtsaleshBean().getFcustomerBean()
									.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
									){
						
						
					
							double disc1PromoPercent = 0.0;
								
								//::PROMO DISKON TPRUDISC
								if (model.getItemDetil().getSubtotalafterppn() > fPromoBean.getDiscValue4() && fPromoBean.getDiscValue4()>0) {
									sumTpruDisc1Persen += fPromoBean.getDiscPercentGet4();
									disc1PromoPercent = fPromoBean.getDiscPercentGet4();
								}else if (model.getItemDetil().getSubtotalafterppn()>fPromoBean.getDiscValue3() && fPromoBean.getDiscValue3()>0){
									sumTpruDisc1Persen += fPromoBean.getDiscPercentGet3();
									disc1PromoPercent = fPromoBean.getDiscPercentGet3();						
								}else if (model.getItemDetil().getSubtotalafterppn()>fPromoBean.getDiscValue2() && fPromoBean.getDiscValue2()>0){
									sumTpruDisc1Persen += fPromoBean.getDiscPercentGet2();
									disc1PromoPercent = fPromoBean.getDiscPercentGet2();
								}else if (model.getItemDetil().getSubtotalafterppn()>fPromoBean.getDiscValue1() && fPromoBean.getDiscValue1()>0){
									sumTpruDisc1Persen += fPromoBean.getDiscPercentGet1();
									disc1PromoPercent = fPromoBean.getDiscPercentGet1();
								}
							}
				}
					
			}
			
			//JIKA LEBIH BESAR DARI 0 artinya ada promo: jika tidak BIARIN AJA
			if (sumTpruDisc1Persen>0) {
				model.itemDetil.setDisc1(sumTpruDisc1Persen);				
			}
		
	}
	
	public void cekAktifitasPromoDetil2(){
		//###CEK AKTIFITAS PROMO DISKON###
		/*
		 * 1. CEK PROMO YANG SEDANG AKTIF DAN BERLAKU
		 * 2. UPDATE
		 * 		- Bonus Barang : Hapus dahulu bonus barang yang ada lalu tambahkan lagi
		 * 		- Diskon 	   : persentase diskon
		 * 3. Update 
		 * 		-ftSalesdPromo : no_promo, nominal_tprb, nominal_tpru_disc, nominal_tpru_cashback
		 * 4. Potongan Uang/tpru dipotongkan untuk nota
		 * 
		 * */
			
			Date trDate = model.getTransaksiHelper().getCurrentTransDate();
			List<FPromo> listFPromo = new ArrayList<FPromo>();		
			listFPromo = model.getfPromoJpaService2().findAllPromoActiveByProduct(fProduct);
			
			double sumTpruDisc1Persen = 0.0;
			Double sumTpruDisc1Afterppn = 0.0;
			int sumTprbItem = 0;
			Double sumTprbAfterppn = 0.0;
			
			List<FtSalesdPromoTprb> ftSalesdPromoTprbSet = new ArrayList<FtSalesdPromoTprb>();
			List<FtSalesdPromoTpruDisc> ftSalesdPromoTpruDiscSet = new ArrayList<FtSalesdPromoTpruDisc>();			
			
//			//###POTONGAN UANG DIAKTIFKAN NANTI###
//			double sumJumlahCashBack = 0.0;
			
			int nomorUrut=0;
			
			for (FPromo fPromoBean: listFPromo){ 
				if (trDate.getTime() >= fPromoBean.getPeriodeFrom().getTime() 
						&& trDate.getTime() <= fPromoBean.getPeriodeTo().getTime()) {
					if (fPromoBean.getForFcustomersubgroup().equals("ALL") 
							|| 
							( fPromoBean.getForFcustomersubgroup().equals("IN") && 
									model.getItemDetil().getFtsaleshBean().getFcustomerBean()
									.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
							|| 
							( fPromoBean.getForFcustomersubgroup().equals("EX") && 
									! model.getItemDetil().getFtsaleshBean().getFcustomerBean()
									.getFcustomersubgroupBean().equals(fPromoBean.getFcustomersubgroupBean())) 
									){
						
						
					
							double disc1PromoPercent = 0.0;
							int tprbItem = 0;
							//JIKA FREE GOOD MAKA YA TIDAK BERLAKU
							if (! model.getItemDetil().getId().getFreegood()==true){
		
								//::PROMO BARANG TPRB
								if (model.getItemDetil().getQty() > fPromoBean.getFreeQty4() && fPromoBean.getFreeQty4()>0) {
									if (fPromoBean.getFreeKelipatan()==true){
										tprbItem = fPromoBean.getFreeQtyGet4() * Math.round(model.getItemDetil().getQty()/fPromoBean.getFreeQty4());
									}else {
										tprbItem = fPromoBean.getFreeQtyGet4();	
									}
									sumTprbItem += tprbItem;
								}else if (model.getItemDetil().getQty() > fPromoBean.getFreeQty3() && fPromoBean.getFreeQty3()>0) {						
									if (fPromoBean.getFreeKelipatan()==true){
										tprbItem = fPromoBean.getFreeQtyGet3() * Math.round(model.getItemDetil().getQty()/fPromoBean.getFreeQty3());
									}else {
										tprbItem = fPromoBean.getFreeQtyGet3();	
									}
									sumTprbItem += tprbItem;
								}else if (model.getItemDetil().getQty() > fPromoBean.getFreeQty2() && fPromoBean.getFreeQty2()>0) {
									if (fPromoBean.getFreeKelipatan()==true){
										tprbItem = fPromoBean.getFreeQtyGet2() * Math.round(model.getItemDetil().getQty()/fPromoBean.getFreeQty2());
									}else {
										tprbItem = fPromoBean.getFreeQtyGet2();	
									}
									sumTprbItem += tprbItem;
								}else if (model.getItemDetil().getQty() > fPromoBean.getFreeQty1() && fPromoBean.getFreeQty1()>0) {
									if (fPromoBean.getFreeKelipatan()==true){
										tprbItem = fPromoBean.getFreeQtyGet1() * Math.round(model.getItemDetil().getQty()/fPromoBean.getFreeQty1());
									}else {
										tprbItem = fPromoBean.getFreeQtyGet1();	
									}
									sumTprbItem += tprbItem;
								}
								
								//::PROMO DISKON TPRUDISC
								if (model.getItemDetil().getSubtotalafterppn() > fPromoBean.getDiscValue4() && fPromoBean.getDiscValue4()>0) {
									sumTpruDisc1Persen += fPromoBean.getDiscPercentGet4();
									disc1PromoPercent = fPromoBean.getDiscPercentGet4();
								}else if (model.getItemDetil().getSubtotalafterppn()>fPromoBean.getDiscValue3() && fPromoBean.getDiscValue3()>0){
									sumTpruDisc1Persen += fPromoBean.getDiscPercentGet3();
									disc1PromoPercent = fPromoBean.getDiscPercentGet3();						
								}else if (model.getItemDetil().getSubtotalafterppn()>fPromoBean.getDiscValue2() && fPromoBean.getDiscValue2()>0){
									sumTpruDisc1Persen += fPromoBean.getDiscPercentGet2();
									disc1PromoPercent = fPromoBean.getDiscPercentGet2();
								}else if (model.getItemDetil().getSubtotalafterppn()>fPromoBean.getDiscValue1() && fPromoBean.getDiscValue1()>0){
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
								ftSalesdPromoTprb.setFtSalesdBean(model.getItemDetil());
			//					ftSalesdPromoTprb.setRefnoPromo((long) nomorUrut);
								ftSalesdPromoTprb.setNourut(nomorUrut);
								
								ftSalesdPromoTprb.setTprbqty(tprbItem);
								double valueTprbAfterppn = tprbItem * itemPricePcsAfterPpn;
								ftSalesdPromoTprb.setTprbafterppn(valueTprbAfterppn); 		
								
								//TAMBAHKAN KE LIST
								ftSalesdPromoTprbSet.add(ftSalesdPromoTprb);
								sumTprbAfterppn += valueTprbAfterppn;
								
							}
							
							//UPDATE JIKA MENDAPAT DISKON SAJA
							if (disc1PromoPercent > 0.0) {
								FtSalesdPromoTpruDisc ftSalesdPromoTpruDisc = new FtSalesdPromoTpruDisc();
			//					ftSalesdPromoTpruDisc.setRefnoPromo((long) nomorUrut);
								ftSalesdPromoTpruDisc.setNourut(nomorUrut);
								ftSalesdPromoTpruDisc.setFtSalesdBean(model.getItemDetil());
								ftSalesdPromoTpruDisc.setfPromoBean(fPromoBean);
								
								//PERSEN DAN NOMINAL
								ftSalesdPromoTpruDisc.setTprudiscpersen(disc1PromoPercent);
								Double valueOfDiscAfterPpn = model.getItemDetil().getSubtotalafterppn() * (ftSalesdPromoTpruDisc.getTprudiscpersen() /100);
								ftSalesdPromoTpruDisc.setTprudiscafterppn(valueOfDiscAfterPpn);
								//TAMBAH KE LIST
								ftSalesdPromoTpruDiscSet.add(ftSalesdPromoTpruDisc);
								sumTpruDisc1Afterppn += valueOfDiscAfterPpn;
							}
							
						}
				}
					
			}
			
					
			
			if (sumTprbItem>0) {
				model.itemDetil.setTprb(sumTprbAfterppn / pecahanPpn);
				model.itemDetil.setFtsalesdPromoTprbList(ftSalesdPromoTprbSet);
			}

			//JIKA LEBIH BESAR DARI 0 artinya ada promo: jika tidak BIARIN AJA
			if (sumTpruDisc1Persen>0) {
				model.itemDetil.setDisc1(sumTpruDisc1Persen);
				
				model.itemDetil.setTprudisc(sumTpruDisc1Afterppn / pecahanPpn);
				model.itemDetil.setFtsalesdPromoTpruDiscList(ftSalesdPromoTpruDiscSet);
			}
			
			
		
	}
	
	
	
}
