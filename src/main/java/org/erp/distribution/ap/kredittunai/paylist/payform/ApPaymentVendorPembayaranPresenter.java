package org.erp.distribution.ap.kredittunai.paylist.payform;

import java.text.NumberFormat;
import java.util.Date;

import org.erp.distribution.model.Bukugiro;
import org.erp.distribution.model.Bukutransfer;
import org.erp.distribution.model.FDivision;
import org.erp.distribution.model.FtAppaymentdPK;
import org.erp.distribution.model.FtArpaymentdPK;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.modelenum.EnumOperationStatus;

import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Field.ValueChangeEvent;
import com.vaadin.ui.Notification;

public class ApPaymentVendorPembayaranPresenter implements ClickListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ApPaymentVendorPembayaranModel model;
	private ApPaymentVendorPembayaranView view;
	
	public ApPaymentVendorPembayaranPresenter(ApPaymentVendorPembayaranModel model, 
			ApPaymentVendorPembayaranView view){
		this.model = model;
		this.view = view;
		initData();
		initListener();		
		initDisplay();
		
	}
	
	public void initData(){		
		
	}
	
	public void setPenambahanPembayaran(){
		double penambahan =  (Double) view.getFieldSubTotalAmountPaid().getConvertedValue() - model.getAmountForThisDetail();
		model.setAmountPenambahan(penambahan);
		
	}
	
	public void setInvoiceTerbayar(){
		model.setAmountForThisDetail(model.getAmountPenambahan() + model.getAmountForThisDetail());
		
		view.getFieldInvoiceAmountPaid().setReadOnly(false);
		view.getFieldInvoiceAmountPaid().setValue(String.valueOf(model.getAmountPenambahan() + 
				(Double) view.getFieldInvoiceAmountPaid().getConvertedValue()));
		view.getFieldInvoiceAmountPaid().setReadOnly(true);
		
	}
	
	public void initListener(){
		//TOMBOL FORM PEMBAYARAN10
		//BUTTON EQUAL
		view.getBtnEqualCash().addClickListener(this);
		view.getBtnEqualGiro().addClickListener(this);
		view.getBtnEqualRetur().addClickListener(this);
		view.getBtnEqualTransfer().addClickListener(this);
		view.getBtnEqualKelebihanBayar().addClickListener(this);
		
		view.getBtnReturBrowse().addClickListener(this);
		view.getBtnTransferBrowse().addClickListener(this);
		view.getBtnGiroBrowse().addClickListener(this);

		TextChangeListener listenerFieldCash = new TextChangeListener() {
			@Override
			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub				
				try{
					double nilaiAfter = Double.parseDouble(event.getText().replaceAll(",", ""));
					view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("CASH", nilaiAfter)));			
//					view.getFieldInvoiceAmountPaid().setValue(String.valueOf((Double) view.getFieldInvoiceAmountPaid().getConvertedValue() + penambahanBayar()));
					setPenambahanPembayaran();
					setInvoiceTerbayar();
				} catch (Exception ex){
					Notification.show("Karakter valid = [0-9]");
				}									
			}
		};
		
		TextChangeListener listenerFieldGiro = new TextChangeListener() {
			@Override
			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub				
				try{
					double nilaiAfter = Double.parseDouble(event.getText().replaceAll(",", ""));
					view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("GIRO", nilaiAfter)));						

					setPenambahanPembayaran();
					setInvoiceTerbayar();
					
				} catch (Exception ex){
					Notification.show("Karakter valid = [0-9]");
				}									
			}
		};
		
		TextChangeListener listenerFieldRetur = new TextChangeListener() {
			@Override
			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub				
				try{
					double nilaiAfter = Double.parseDouble(event.getText().replaceAll(",", ""));
					view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("RETUR", nilaiAfter)));	

					setPenambahanPembayaran();
					setInvoiceTerbayar();
					
				} catch (Exception ex){
					Notification.show("Karakter valid = [0-9]");
				}									
			}
		};
		TextChangeListener listenerFieldTransfer = new TextChangeListener() {
			@Override
			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub				
				try{
					double nilaiAfter = Double.parseDouble(event.getText().replaceAll(",", ""));
					view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("TRANSFER", nilaiAfter)));		

					setPenambahanPembayaran();
					setInvoiceTerbayar();
					
				} catch (Exception ex){
					Notification.show("Karakter valid = [0-9]");
				}									
			}
		};
		TextChangeListener listenerFieldPotLain = new TextChangeListener() {
			@Override
			public void textChange(TextChangeEvent event) {
				// TODO Auto-generated method stub				
				try{
					double nilaiAfter = Double.parseDouble(event.getText().replaceAll(",", ""));
					view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("POTLAIN", nilaiAfter)));		

					setPenambahanPembayaran();
					setInvoiceTerbayar();
					
				} catch (Exception ex){
					Notification.show("Karakter valid = [0-9]");
				}									
			}
		};
		TextChangeListener listenerFieldKelebihanBayar = new TextChangeListener() {			
			@Override
			public void textChange(TextChangeEvent event) {
				double nilaiAfter = Double.parseDouble(event.getText().replaceAll(",", ""));
				view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("KELEBIHANBAYAR", nilaiAfter)));		

				setPenambahanPembayaran();
				setInvoiceTerbayar();
				
			}
		};
		
		view.getFieldCashPay().setTextChangeEventMode(TextChangeEventMode.LAZY);
		view.getFieldCashPay().addTextChangeListener(listenerFieldCash);
		view.getFieldGiroPay().setTextChangeEventMode(TextChangeEventMode.LAZY);
		view.getFieldGiroPay().addTextChangeListener(listenerFieldGiro);
		view.getFieldReturPay().setTextChangeEventMode(TextChangeEventMode.LAZY);
		view.getFieldReturPay().addTextChangeListener(listenerFieldRetur);
		view.getFieldTransferPay().setTextChangeEventMode(TextChangeEventMode.LAZY);
		view.getFieldTransferPay().addTextChangeListener(listenerFieldTransfer);
		view.getFieldPotLainPay().setTextChangeEventMode(TextChangeEventMode.LAZY);
		view.getFieldPotLainPay().addTextChangeListener(listenerFieldPotLain);
		view.getFieldKelebihanBayarPay().setTextChangeEventMode(TextChangeEventMode.LAZY);
		view.getFieldKelebihanBayarPay().addTextChangeListener(listenerFieldKelebihanBayar);
		
		
		view.getBtnSaveForm().addClickListener(this);
		view.getBtnCancelForm().addClickListener(this);
		
		//COMBO LISTENER		
		ValueChangeListener comboGiroValueChangeListener = new ValueChangeListener() {
			
			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				// TODO Auto-generated method stub
				Object itemId = event.getProperty().getValue();
				try{
					if (cekGiroAvailableAndPay(itemId) != 0 ) {
						//COBA REBIND
						view.getComboGiro().discard();
						view.getBinderArpaymentDetail().bind(view.getComboGiro(), "bukugiroBean");						
					}
					
				} catch(Exception ex){}
				
			}
		};

		ValueChangeListener comboTransferValueChangeListener = new ValueChangeListener() {
			
			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				// TODO Auto-generated method stub
				Object itemId = event.getProperty().getValue();
				try{
					
					if (cekTransferAvailableAndPay(itemId) != 0 ){
						//COBA REBIND
						view.getComboTransfer().discard();
						view.getBinderArpaymentDetail().bind(view.getComboTransfer(), "bukutransferBean");
					}
				
				} catch(Exception ex){}
				
			}
		};
		
		ValueChangeListener comboReturValueChangeListener = new ValueChangeListener() {
			
			@Override
			public void valueChange(com.vaadin.data.Property.ValueChangeEvent event) {
				// TODO Auto-generated method stub	
				Object itemId = event.getProperty().getValue();
				try{
					
					if (cekTransferAvailableAndPay(itemId) != 0 ){
						//COBA REBIND
						view.getComboRetur().discard();
						view.getBinderArpaymentDetail().bind(view.getComboRetur(), "returBean");
					}
					try{
						if ((Double) view.getFieldReturPay().getConvertedValue() <= 0){
							double nilaiRetur = (double) ((FtSalesh) view.getComboRetur().getConvertedValue()).getAmount();
							double nilaiReturRevisi = (double) ((FtSalesh) view.getComboRetur().getConvertedValue()).getAmountrevisi();
							view.getFieldReturPay().setConvertedValue(nilaiRetur + nilaiReturRevisi);
						}
					} catch(Exception ex){
						ex.printStackTrace();
					}
				} catch(Exception ex){}
				
			}
		};
		
		//ALASAN DITARUH SETELAH TAMBAH LISTENER ADALAH SUPAYA PADA SAAT INIT TIDAK DIJALANKAN DULU
		view.getComboGiro().setImmediate(true);		
		view.getComboGiro().addValueChangeListener(comboGiroValueChangeListener);
		
		view.getComboTransfer().setImmediate(true);
		view.getComboTransfer().addValueChangeListener(comboTransferValueChangeListener);

		view.getComboRetur().setImmediate(true);
		view.getComboRetur().addValueChangeListener(comboReturValueChangeListener);
		
		
	}
	
	public void initDisplay(){		
		view.refreshData();
	}
	
	public double penambahanBayar(){
		double totalBayarDetailNowLocal = totalBayarDetailNow("Dummy",0.0);
		double amount =0;
		double amountRevisi =0;
		double amountpaid =0;
		try{
			amountRevisi = (Double) view.getFieldInvoiceAmountRevisi().getConvertedValue();
		}catch(Exception ex){}
		amount = (Double) view.getFieldInvoiceAmount().getConvertedValue() + amountRevisi;
		amountpaid = (Double) view.getFieldInvoiceAmountPaid().getConvertedValue();
		
//		double penambahan = amount - (totalBayarDetailNowLocal + amountpaid);
		double penambahan = amount - (amountpaid);
		
		return penambahan;
	}
	
	public double totalBayarDetailNow(String tipeBayar, double amountPay){
		double amountCash =0;
		double amountGiro =0;
		double amountRetur =0;
		double amountTransfer =0;
		double amountPotLain =0;
		double amountKelebihanBayar =0;
		
		if (tipeBayar.equals("CASH")){
			amountCash = amountPay;						
		} else {
			amountCash = (Double) view.getFieldCashPay().getConvertedValue();			
		}
		if (tipeBayar.equals("GIRO")){
			amountGiro = amountPay;
		} else {
			amountGiro = (Double) view.getFieldGiroPay().getConvertedValue();			
		}
		if (tipeBayar.equals("RETUR")){
			amountRetur = amountPay;
		} else {
			amountRetur = (Double) view.getFieldReturPay().getConvertedValue();
		}
		if (tipeBayar.equals("TRANSFER")){
			amountTransfer = amountPay;
		}else {
			amountTransfer = (Double) view.getFieldTransferPay().getConvertedValue();
		}
		if (tipeBayar.equals("POTLAIN")){
			amountPotLain = amountPay;
		}else {
			amountPotLain = (Double) view.getFieldPotLainPay().getConvertedValue();
		}
		if (tipeBayar.equals("KELEBIHANBAYAR")){
			amountKelebihanBayar = amountPay;
		}else {
			amountKelebihanBayar = (Double) view.getFieldKelebihanBayarPay().getConvertedValue();
		}
		
		double sumJumlahBayar = amountCash + amountGiro + amountRetur + amountTransfer + amountPotLain + amountKelebihanBayar;
		
//		Notification.show("CASH: " + amountCash + "\t GIRO: " + amountGiro + "\t RETUR: " + amountRetur + "\t TRANSFER: " + amountTransfer);
		
		return sumJumlahBayar;
	}
	
	public void comboGiroChangeListener(){
		
	}
	public void comboTransferChangeListener(){		
	}
	
	public int cekGiroAvailableAndPay(Object itemId){
		int error =0;
		
		try {
			Bukugiro cekBukuGiro=new Bukugiro();
			double toleransiAmountAvailable = 1;
			cekBukuGiro = model.getBeanItemContainerBukuGiro().getItem(itemId).getBean();
			double amountAvailable = cekBukuGiro.getAmount() - cekBukuGiro.getAmountused();
			double amountGiroPayBefore = model.getApPaymentDetail().getGiroamountpay();
			double amountGiroPayAfter = (Double) view.getFieldGiroPay().getConvertedValue();
			
			double amountGiroPayPenambahan = amountGiroPayAfter - amountGiroPayBefore;
			
			String identitas = cekBukuGiro.getGironumber() 
					+ " - " + cekBukuGiro.getGironame();
			
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(0);
			nf.setMinimumFractionDigits(0);
			
			if ((amountAvailable+toleransiAmountAvailable) < amountGiroPayPenambahan){
				error = -1;				Notification.show("Sisa plafon GIRO " + identitas + " = Rp. " + nf.format(amountAvailable), Notification.TYPE_TRAY_NOTIFICATION);					
			}
			
		} catch (Exception ex){
				//GIRO BERARTI GAK PAKE
		}
		return error;
	}
	
	public int cekTransferAvailableAndPay(Object itemId){
		int error =0;
		
		try{
			Bukutransfer cekBukuTransfer = new Bukutransfer();
			double toleransiAmountAvailable = 1;
			cekBukuTransfer = model.getBeanItemContainerBukuTransfer().getItem(itemId).getBean();
			String identitas =  cekBukuTransfer.getNasabah();
			double amountAvailable = cekBukuTransfer.getAmount() - cekBukuTransfer.getAmountused();
			double amountTransferPayBefore = model.getApPaymentDetail().getTransferamountpay();
			double amountTransferPayAfter = (Double) view.getFieldTransferPay().getConvertedValue();
			
			double amountTransferPayPenambahan = amountTransferPayAfter - amountTransferPayBefore;
			
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumFractionDigits(0);
			nf.setMinimumFractionDigits(0);
			
			if ((amountAvailable + toleransiAmountAvailable) < amountTransferPayPenambahan){
				error = -1;
				Notification.show("Sisa plafon TRANSFER " + identitas + " = Rp. " + nf.format(amountAvailable), Notification.TYPE_TRAY_NOTIFICATION);
			}
		} catch(Exception ex){
			//BERARTI GAK PAKE
		}
		
		return error;
	}	
	
	public int cekReturAvailableAndPay(Object itemId){
		int error =0;
		
		try{
			FtPurchaseh cekRetur = new FtPurchaseh();
//			double toleransiAmountAvailable = 1;
//			cekRetur = model.getBeanitemContainerReturBelumLunas().getItem(itemId).getBean();
//			String identitas = cekRetur.getInvoiceno() + " - " +  cekRetur.getFcustomerBean().getCustname();
//			double amountAvailable = cekRetur.getAmount() + cekRetur.getAmountrevisi() - cekRetur.getAmountpay();
//			double amountReturPayBefore = model.getArPaymentDetail().getReturamountpay();
//			double amountReturPayAfter = (Double) view.getFieldReturPay().getConvertedValue();
//			
//			double amountReturPayPenambahan = amountReturPayAfter - amountReturPayBefore;
//			
//			NumberFormat nf = NumberFormat.getInstance();
//			nf.setMaximumFractionDigits(0);
//			nf.setMinimumFractionDigits(0);
//			
//			if ((amountAvailable+toleransiAmountAvailable) < amountReturPayPenambahan){
//				error = -1;
//				Notification.show("Sisa plafon RETUR " + identitas + " = Rp. " + nf.format(amountAvailable), Notification.TYPE_TRAY_NOTIFICATION);
//			}
		} catch(Exception ex){
			//BERARTI GAK PAKE
		}
		
		return error;
	}	
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnEqualCash()){
			//NEW VALUE >> penambahan + nilai sekarang			
			view.getFieldCashPay().setValue(String.valueOf(penambahanBayar() + (Double) view.getFieldCashPay().getConvertedValue()));	
			//NILAI AFTER PENAMBAHAN
			view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("Dummy",0.0)));
			//MERUBAH VARIABLE
			setPenambahanPembayaran();
			setInvoiceTerbayar();
			
		} else if (event.getButton()==view.getBtnEqualGiro()){
			//NEW VALUE >> penambahan + nilai sekaran			
			view.getFieldGiroPay().setValue(String.valueOf(penambahanBayar() + (Double) view.getFieldGiroPay().getConvertedValue()));			
			//NILAI AFTER PENAMBAHAN
			view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("Dummy",0.0)));
			//MERUBAH VARIABLE
			setPenambahanPembayaran();
			setInvoiceTerbayar();
			
		} else if (event.getButton()==view.getBtnEqualRetur()){
			//NEW VALUE >> penambahan + nilai sekaran			
			view.getFieldReturPay().setValue(String.valueOf(penambahanBayar() + (Double) view.getFieldReturPay().getConvertedValue()));
			//NILAI AFTER PENAMBAHAN
			view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("Dummy",0.0)));			
			//MERUBAH VARIABLE
			setPenambahanPembayaran();
			setInvoiceTerbayar();
			
		} else if (event.getButton()==view.getBtnEqualTransfer()){		
			//NEW VALUE >> penambahan + nilai sekaran			
			view.getFieldTransferPay().setValue(String.valueOf(penambahanBayar() + (Double) view.getFieldTransferPay().getConvertedValue()));
			//NILAI AFTER PENAMBAHAN
			view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("Dummy",0.0)));
			//MERUBAH VARIABLE
			setPenambahanPembayaran();
			setInvoiceTerbayar();
			
		} else if (event.getButton()==view.getBtnEqualPotLain()){		
			
			//NEW VALUE >> penambahan + nilai sekaran			
			view.getFieldPotLainPay().setValue(String.valueOf(penambahanBayar() + (Double) view.getFieldPotLainPay().getConvertedValue()));
			//NILAI AFTER PENAMBAHAN
			view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("Dummy",0.0)));
			//MERUBAH VARIABLE
			setPenambahanPembayaran();
			setInvoiceTerbayar();
			
		} else if (event.getButton()==view.getBtnEqualKelebihanBayar()){		
			
			//NEW VALUE >> penambahan + nilai sekaran			
			view.getFieldKelebihanBayarPay().setValue(String.valueOf(penambahanBayar() + (Double) view.getFieldKelebihanBayarPay().getConvertedValue()));
			//NILAI AFTER PENAMBAHAN
			view.getFieldSubTotalAmountPaid().setValue(String.valueOf(totalBayarDetailNow("Dummy",0.0)));
			//MERUBAH VARIABLE
			setPenambahanPembayaran();
			setInvoiceTerbayar();
		} else if (event.getButton()==view.getBtnSaveForm()){
			try {				
				saveFormAndValidate();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				Notification.show("Gagal Simpan!!", Notification.TYPE_TRAY_NOTIFICATION);
				
			}
			
		} else if (event.getButton()==view.getBtnCancelForm()){
			
			view.getBinderArpaymentDetail().discard();			
			model.setAllowCloseWindow(true);
			
		} else if (event.getButton()==view.getBtnReturBrowse()){
			FtSalesh arinvoiceRetur = null;
			try{
				arinvoiceRetur = new FtSalesh();
				arinvoiceRetur = (FtSalesh) view.getComboRetur().getConvertedValue();
			} catch(Exception ex){}
			
			if (arinvoiceRetur != null){
				view.buildWindowInfoReturTransferGiro(arinvoiceRetur, null, null);
			}
			
		} else if (event.getButton()==view.getBtnTransferBrowse()){
		} else if (event.getButton()==view.getBtnGiroBrowse()){
			
			
		}
		
	}
	public boolean isValidInputanKosong(){
		boolean isValid = true;
		//VALIDASI GIRO, TRANSFER DAN RETUR >> JIKA LEBIH BESAR DARI NOL MAKA HARUS ADA ISINYA
		if ((Double) view.getFieldGiroPay().getConvertedValue() > 0 && view.getComboGiro().getValue()==null){
			isValid = false;
			Notification.show("NOMOR GIRO TIDAK BOLEH KOSONG");
		}
		if ((Double) view.getFieldTransferPay().getConvertedValue() > 0 && view.getComboTransfer().getValue()==null){
			isValid = false;
			Notification.show("NOMOR TRANSFER TIDAK BOLEH KOSONG");
		}
		if ((Double) view.getFieldReturPay().getConvertedValue() > 0 && view.getComboRetur().getValue()==null){
			isValid = false;
			Notification.show("NOMOR RETUR TIDAK BOLEH KOSONG");
		}
		//KEBALIKAN DIATAS >> JIKA GIROPAY, TRANSFERPAY, RETURPAY ADALAH 0 maka tidak BOLEH ADA field BERISI	
		if ((Double) view.getFieldGiroPay().getConvertedValue() == 0 && view.getComboGiro().getValue()!=null){
			isValid = false;
			Notification.show("NOMINAL GIRO Rp. 0, NOMOR GIRO TIDAK TERPAKAI");
		}
		if ((Double) view.getFieldTransferPay().getConvertedValue() == 0 && view.getComboTransfer().getValue()!=null){
			isValid = false;
			Notification.show("NOMINAL TRANSFER Rp. 0, NOMOR TRANSFER TIDAK TERPAKAI");
		}
		if ((Double) view.getFieldReturPay().getConvertedValue() == 0 && view.getComboRetur().getValue()!=null){
			isValid = false;
			Notification.show("NOMINAL RETUR Rp. 0, NOMOR RETUR TIDAK TERPAKAI");
		}
		//VALIDASI NOMINAL  GIRO, TRANSFER DAN RETUR DENGAN AVAILABLE AMOUNT
		try{
			Object itemIdGiro = view.getComboGiro().getValue();
			if (cekGiroAvailableAndPay(itemIdGiro)!=0){
				isValid = false;
			}
		} catch(Exception ex){
		}

		try{
			Object itemIdTransfer = view.getComboTransfer().getValue();
			if (cekTransferAvailableAndPay(itemIdTransfer)!=0){
				isValid = false;
			}
		} catch(Exception ex){
		}
		
		try{
			Object itemIdRetur = view.getComboRetur().getValue();
			if (cekReturAvailableAndPay(itemIdRetur)!=0){
				isValid = false;
			}
		} catch(Exception ex){
		}

		return isValid;
	}
	public void saveFormAndValidate(){
		boolean fieldGiroTransferReturKosong = false;
		boolean isValid = true;
		
		//GAK BOLEH CLOSE DULU
		model.setAllowCloseWindow(false);
		
		//CEK APAKAH JUMLAH BAYAR LEBIH BESAR DARI NILAI INVOICE
		double nilaiAmountRevisi = 0;
		try{
			nilaiAmountRevisi = (Double) view.getFieldInvoiceAmountRevisi().getConvertedValue();
		} catch(Exception ex){}
		double nilaiInvoice = (Double) view.getFieldInvoiceAmount().getConvertedValue() + nilaiAmountRevisi;
		double jumlahBayar = (Double) view.getFieldInvoiceAmountPaid().getConvertedValue();
		
		
		if (isValidInputanKosong()==false) {
			isValid=false;
		}
		
		
		//VALIDASI JUMLAH PEMBAYARAN
		if (nilaiInvoice < jumlahBayar) {
//			isValid =false; //KITA PERBOLEHKAN KURANG MAUPUN LEBIH
			Notification.show("PEMBAYARAN LEBIH BESAR DARI NILAI INVOICE!!..", Notification.TYPE_TRAY_NOTIFICATION);
			
		}
		
		//CEK INPUT APA KOSONG SEMUA
		if (totalBayarDetailNow("Dummy",0.0)<= 0){
			isValid = false;
			Notification.show("TOTAL PEMBAYARAN TIDAK BOLEH 0", Notification.TYPE_TRAY_NOTIFICATION);
		}
		//BUAT UPDATE TABEL GIRO, TRANSFER DAN RETUR DIBAWAH
		double amountGiroPayBefore = model.getApPaymentDetail().getGiroamountpay();
		double amountGiroPayAfter = (Double) view.getFieldGiroPay().getConvertedValue();				
		double amountGiroPayPenambahan = amountGiroPayAfter - amountGiroPayBefore;

		double amountTransferPayBefore = model.getApPaymentDetail().getTransferamountpay();
		double amountTransferPayAfter = (Double) view.getFieldTransferPay().getConvertedValue();				
		double amountTransferPayPenambahan = amountTransferPayAfter - amountTransferPayBefore;
		
		double amountReturPayBefore = model.getApPaymentDetail().getMrvamountpay();
		double amountReturPayAfter = (Double) view.getFieldReturPay().getConvertedValue();				
		double amountReturPayPenambahan = amountReturPayAfter - amountReturPayBefore;
		
		//JIKA SELISIH MINUS BERARTI KEBANYAKAN BAYARNYA
		if ( isValid ==true){
			//Inisial
			FDivision divisionBean = new FDivision();
//			try{
//				divisionBean = model.getArInvoice().getDivisionBean();
//			} catch(Exception ex){}
			
			//*****OPERASI PENAMBAHAN  
			if (model.getFormOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){			
				
				String newNomorUrut="";
//				newNomorUrut = model.getTransaksiHelper().getNewNomorUrutArPayment(divisionBean);
				newNomorUrut = model.getTransaksiHelper().getNextFtArpaymenthRefno();
				
				//1. LENGKAPI HEADER
				model.getApPaymentHeader().setNorek(newNomorUrut);
				
				model.getApPaymentHeader().setClosing(false);
				model.getApPaymentHeader().setEndofday(false);
				model.getApPaymentHeader().setPrintcounter(0);
				model.getApPaymentHeader().setUserid("admin");
				
				model.getApPaymentHeader().setEntrydate(new Date());
				if (model.getTanggalPembayaranManual() !=null){
					model.getApPaymentHeader().setTrdate(model.getTanggalPembayaranManual());
				} else {
					model.getApPaymentHeader().setTrdate(model.getTransaksiHelper().getCurrentTransDate());
				}
				
				//JIKA TAMBAH MAKA CREATE OBJECT BARU
				if (model.getFormOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())) {
					try{
						model.getApPaymentHeaderService().createObject(model.getApPaymentHeader());
					} catch(Exception ex){
					}
				}
				
				
				
			}
			
			

			//UPDATE GIRO, TRANSFER, RETUR SEBELUM COMMIT >> MENGURANGI GIRO(pada database) DENGAN NILAI AWAL
			//SEBELUM COMMIT :: HAPUS DULU NILAI GIRO, RETUR, TRANSFER SEMULA :: BERLAKU UNTUK EDITING MAUPUN ADDING
			try{						
				Bukugiro newBukugiro = new Bukugiro();
//				newBukugiro = model.getBukuGiroService().findByIdPk(model.getArPaymentDetail().getBukugiroBean().getId());				
//				newBukugiro = model.getBukuGiroService().findById(model.getApPaymentDetail().getBukugiroBean().getRefno());
				newBukugiro.setAmountused(newBukugiro.getAmountused() - model.getApPaymentDetail().getGiroamountpay());
				model.getBukuGiroService().updateObject(newBukugiro);
				
			} catch(Exception ex){ 
			}										
			try{
				Bukutransfer newTransfer = new Bukutransfer();
//				newTransfer = model.getBukuTransferService().findByIdPk(model.getArPaymentDetail().getBukutransferBean().getId());	
//				newTransfer = model.getBukuTransferService().findById(model.getApPaymentDetail().getBukutransferBean().getRefno());	
				newTransfer.setAmountused(newTransfer.getAmountused() - model.getApPaymentDetail().getTransferamountpay());
				model.getBukuTransferService().updateObject(newTransfer);
			} catch(Exception ex){
			}
			try{
				FtPurchaseh newRetur = new FtPurchaseh();
//				newRetur = model.getFtPurchasehJpaService().findById(model.getApPaymentDetail().getReturBean().getRefno());	
				newRetur.setAmountpay(newRetur.getAmountpay() - model.getApPaymentDetail().getMrvamountpay());
				model.getFtPurchasehJpaService().updateObject(newRetur);
			} catch(Exception ex){
			}
			
			//1. Commit Binder	:: SETELAH COMMIT
			try{
				view.getBinderArpaymentDetail().commit();				
				//PERHITUNGAN LUNAS/TIDAK LUNAS ada pada >> CustomerCreditPresenter
				view.getBinderArinvoice().commit();					
			} catch(CommitException ex){
				ex.printStackTrace();
			}
			
			//2. LENGKAPI DETAIL SEBELUM SAVE:: JIKA ADDING
			if (model.getFormOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())) {
				FtAppaymentdPK arpaymentdetailPK = new FtAppaymentdPK();
				arpaymentdetailPK = model.getApPaymentDetail().getId();
				arpaymentdetailPK.setRefnopayment(model.getApPaymentHeader().getRefno());
				arpaymentdetailPK.setRefnopurchase(model.getApInvoice().getRefno());
	//			arpaymentdetailPK.setDivision(divisionBean.getId());
				model.apPaymentDetail.setId(arpaymentdetailPK);				
	//			model.arPaymentDetail.setDivisionBean(divisionBean);
				
				
				model.apPaymentDetail.setFtappaymenthBean(model.getApPaymentHeader());
				model.apPaymentDetail.setFtpurchasehBean(model.getApInvoice());
			}
			
			//2. UPDATE DETAL	
			model.getApPaymentDetailService().updateObject(model.getApPaymentDetail());
			
			//UPDATE GIRO, RETUR LAGI SETELAH COMMIT
			//UPDATE GIRO, TRANSFER, RETUR SEBELUM COMMIT SESUDAH COMMIT  >> MENAMBAH AMOUNTPAY(pada database) DENGAN NILAI SEKARANG
			try{						
				Bukugiro newBukugiro = new Bukugiro();
//				newBukugiro = model.getBukuGiroService().findById(model.getApPaymentDetail().getBukugiroBean().getRefno());				
				newBukugiro.setAmountused(newBukugiro.getAmountused() + model.getApPaymentDetail().getGiroamountpay());
				model.getBukuGiroService().updateObject(newBukugiro);
				
			} catch(Exception ex){ 
			}										
			try{
				Bukutransfer newTransfer = new Bukutransfer();
//				newTransfer = model.getBukuTransferService().findById(model.getApPaymentDetail().getBukutransferBean().getRefno());	
				newTransfer.setAmountused(newTransfer.getAmountused() + model.getApPaymentDetail().getTransferamountpay());
				model.getBukuTransferService().updateObject(newTransfer);
			} catch(Exception ex){
			}
			
			try{
				FtPurchaseh newRetur = new FtPurchaseh();
//				newRetur = model.getFtPurchasehJpaService().findById(model.getApPaymentDetail().getReturBean().getRefno());	
				newRetur.setAmountpay(newRetur.getAmountpay() + model.getApPaymentDetail().getMrvamountpay());
				model.getFtPurchasehJpaService().updateObject(newRetur);
			} catch(Exception ex){
			}
			
			
			
			//3. Update ArInvoice
			try{
				model.getFtPurchasehJpaService().updateObject(model.getApInvoice());
			} catch(Exception ex){
			}
			//UNTUK RELOAD INVOICE ADA DI FORM PEMANGGIL
			//BOLEH TUTUP JIKA CLOSE
			model.setAllowCloseWindow(true);
			
			Notification.show("Simpan Sukses..", Notification.TYPE_TRAY_NOTIFICATION);
			
		} else {
			
		}
		
		
	}
	
	
}
