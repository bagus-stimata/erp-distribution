package org.erp.distribution.ap.kredittunai.paylist.payform;

import java.util.Date;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.BukugiroJpaService;
import org.erp.distribution.jpaservice.BukutransferJpaService;
import org.erp.distribution.jpaservice.FtArpaymentdJpaService;
import org.erp.distribution.jpaservice.FtArpaymenthJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.FtappaymentdJpaService;
import org.erp.distribution.jpaservice.FtappaymenthJpaService;
import org.erp.distribution.model.Bukugiro;
import org.erp.distribution.model.Bukutransfer;
import org.erp.distribution.model.FtAppaymentd;
import org.erp.distribution.model.FtAppaymenth;
import org.erp.distribution.model.FtArpaymentd;
import org.erp.distribution.model.FtArpaymenth;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ApPaymentVendorPembayaranModel extends CustomComponent{
	
	private static final String persistenceUnit = "financePU";
	//DAO
	private FtPurchasehJpaService ftPurchasehJpaService;
	private FtappaymenthJpaService apPaymentHeaderService;
	private FtappaymentdJpaService apPaymentDetailService;
	
	//DAO --> combobox
	private BukugiroJpaService bukuGiroService;
	private BukutransferJpaService bukuTransferService;
	
	//DAO PENDUKUNG	
	public FtPurchaseh apInvoice = new FtPurchaseh();
	public FtAppaymenth apPaymentHeader = new FtAppaymenth();
	public FtAppaymentd apPaymentDetail = new FtAppaymentd();
	
	//BeanItem utama
	public BeanItem<FtPurchaseh> beanItemArInvoice ;
	public BeanItem<FtAppaymenth> beanItemArPaymentHeader;
	public BeanItem<FtAppaymentd> beanItemArPaymentDetail;
	//FOR COMBOBOX
	
	//######TIDAK DIGUNAKAN: BeanItemContainer Utama
	public BeanItemContainer<FtPurchaseh> beanItemContainerArInvoice = new BeanItemContainer<FtPurchaseh>(FtPurchaseh.class);
	public BeanItemContainer<FtAppaymenth> beanItemContainerArPaymentHeader = new BeanItemContainer<FtAppaymenth>(FtAppaymenth.class);
	public BeanItemContainer<FtAppaymentd> beanItemContainerArPaymentDetail = new BeanItemContainer<FtAppaymentd>(FtAppaymentd.class);

	
	//for combobox
	public BeanItemContainer<Bukugiro> beanItemContainerBukuGiro = new BeanItemContainer<Bukugiro>(Bukugiro.class);
	public BeanItemContainer<Bukutransfer> beanItemContainerBukuTransfer = new BeanItemContainer<Bukutransfer>(Bukutransfer.class);
	public BeanItemContainer<FtPurchaseh> beanitemContainerReturBelumLunas = new BeanItemContainer<FtPurchaseh>(FtPurchaseh.class);
	
	//VARIABLE PENDUKUNG
	private boolean allowCloseWindow;
	private String formOperationStatus = "OPEN";
	private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();
	private Date tanggalPembayaranManual =null;
	
	double amountForOtherDetail =0.0;
	double amountForThisDetail =0.0;
	double amountPenambahan =0.0;
	
	
	
	public ApPaymentVendorPembayaranModel() {
		initVariable();
	}
	
	public ApPaymentVendorPembayaranModel(FtPurchaseh apinvoice){		
		initVariable();
		this.apInvoice = apinvoice;
		initVariableData();
	}
	public ApPaymentVendorPembayaranModel(FtAppaymentd appaymentdetail){
		initVariable();
		this.apPaymentDetail = appaymentdetail;
		initVariableData();
	}
	public ApPaymentVendorPembayaranModel(FtPurchaseh apinvoice, FtAppaymentd appaymentdetail){
		initVariable();
		this.apInvoice = apinvoice;
		this.apPaymentDetail = appaymentdetail;
		initVariableData();
	}
	public void initVariable(){
		
//		setTransaksiHelper(((DashboardUI) getUI().getCurrent()).getTransaksiHelper());
		//DAO UTAMA
		setFtPurchasehJpaService(((DashboardUI) getUI().getCurrent()).getFtPurchasehJpaService());
		setApPaymentHeaderService(((DashboardUI) getUI().getCurrent()).getFtappaymenthJpaService());
		setApPaymentDetailService(((DashboardUI) getUI().getCurrent()).getFtappaymentdJpaService());
		
		//DAO --> COMBO
		setBukuGiroService(((DashboardUI) getUI().getCurrent()).getBukugiroJpaService());
		setBukuTransferService(((DashboardUI) getUI().getCurrent()).getBukutransferJpaService());
		
		allowCloseWindow = true;
		
	}
	
	public void initVariableData(){	
		
		beanItemArInvoice = new BeanItem<FtPurchaseh>(apInvoice);
		beanItemArPaymentHeader = new BeanItem<FtAppaymenth>(apPaymentHeader);
		beanItemArPaymentDetail = new BeanItem<FtAppaymentd>(apPaymentDetail);
		
//		if (apPaymentDetail.getBukugiroBean() !=null) {			
//			beanItemContainerBukuGiro.addAll(bukuGiroService.findAllAvalilableGiro(apPaymentDetail.getBukugiroBean().getRefno()));			
//		}else {
//			beanItemContainerBukuGiro.addAll(bukuGiroService.findAllAvailableGiro());
//		}
		
//		if (arPaymentDetail.getBukutransferBean() !=null){
//			beanItemContainerBukuTransfer.addAll(bukuTransferService.findAllAvailabelTransfer(arPaymentDetail.getBukutransferBean().getRefno()));			
//			beanItemContainerBukuTransfer.addAll(bukuTransferService.findAllAvailableTransfer());			
//		}else {
//			beanItemContainerBukuTransfer.addAll(bukuTransferService.findAllAvailableTransfer());			
//		}
		
//		if (arPaymentDetail.getReturBean() != null){
//			beanitemContainerReturBelumLunas.addAll(arInvoiceService.findAllReturBelumLunas(arPaymentDetail.getReturBean()));
//		} else {
//			beanitemContainerReturBelumLunas.addAll(arInvoiceService.findAllReturBelumLunas());
//		}
		
		
		//UNTUK MENGETAHUI BAHWA INI ADD ATAU EDIT >> 
		if (apPaymentDetail.getId().getRefnopayment()== -1234){
			formOperationStatus =  EnumOperationStatus.ADDING.getStrCode();
		} else {
			formOperationStatus =  EnumOperationStatus.EDITING.getStrCode();
		}
		
	}

	public static String getPersistenceunit() {
		return persistenceUnit;
	}

	public FtPurchasehJpaService getFtPurchasehJpaService() {
		return ftPurchasehJpaService;
	}

	public FtappaymenthJpaService getApPaymentHeaderService() {
		return apPaymentHeaderService;
	}

	public FtappaymentdJpaService getApPaymentDetailService() {
		return apPaymentDetailService;
	}

	public BukugiroJpaService getBukuGiroService() {
		return bukuGiroService;
	}

	public BukutransferJpaService getBukuTransferService() {
		return bukuTransferService;
	}

	public FtPurchaseh getApInvoice() {
		return apInvoice;
	}

	public FtAppaymenth getApPaymentHeader() {
		return apPaymentHeader;
	}

	public FtAppaymentd getApPaymentDetail() {
		return apPaymentDetail;
	}

	public BeanItem<FtPurchaseh> getBeanItemArInvoice() {
		return beanItemArInvoice;
	}

	public BeanItem<FtAppaymenth> getBeanItemArPaymentHeader() {
		return beanItemArPaymentHeader;
	}

	public BeanItem<FtAppaymentd> getBeanItemArPaymentDetail() {
		return beanItemArPaymentDetail;
	}

	public BeanItemContainer<FtPurchaseh> getBeanItemContainerArInvoice() {
		return beanItemContainerArInvoice;
	}

	public BeanItemContainer<FtAppaymenth> getBeanItemContainerArPaymentHeader() {
		return beanItemContainerArPaymentHeader;
	}

	public BeanItemContainer<FtAppaymentd> getBeanItemContainerArPaymentDetail() {
		return beanItemContainerArPaymentDetail;
	}

	public BeanItemContainer<Bukugiro> getBeanItemContainerBukuGiro() {
		return beanItemContainerBukuGiro;
	}

	public BeanItemContainer<Bukutransfer> getBeanItemContainerBukuTransfer() {
		return beanItemContainerBukuTransfer;
	}

	public BeanItemContainer<FtPurchaseh> getBeanitemContainerReturBelumLunas() {
		return beanitemContainerReturBelumLunas;
	}

	public boolean isAllowCloseWindow() {
		return allowCloseWindow;
	}

	public String getFormOperationStatus() {
		return formOperationStatus;
	}

	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}

	public Date getTanggalPembayaranManual() {
		return tanggalPembayaranManual;
	}

	public double getAmountForOtherDetail() {
		return amountForOtherDetail;
	}

	public double getAmountForThisDetail() {
		return amountForThisDetail;
	}

	public double getAmountPenambahan() {
		return amountPenambahan;
	}

	public void setFtPurchasehJpaService(FtPurchasehJpaService ftPurchasehJpaService) {
		this.ftPurchasehJpaService = ftPurchasehJpaService;
	}

	public void setApPaymentHeaderService(
			FtappaymenthJpaService apPaymentHeaderService) {
		this.apPaymentHeaderService = apPaymentHeaderService;
	}

	public void setApPaymentDetailService(
			FtappaymentdJpaService apPaymentDetailService) {
		this.apPaymentDetailService = apPaymentDetailService;
	}

	public void setBukuGiroService(BukugiroJpaService bukuGiroService) {
		this.bukuGiroService = bukuGiroService;
	}

	public void setBukuTransferService(BukutransferJpaService bukuTransferService) {
		this.bukuTransferService = bukuTransferService;
	}

	public void setApInvoice(FtPurchaseh apInvoice) {
		this.apInvoice = apInvoice;
	}

	public void setApPaymentHeader(FtAppaymenth apPaymentHeader) {
		this.apPaymentHeader = apPaymentHeader;
	}

	public void setApPaymentDetail(FtAppaymentd apPaymentDetail) {
		this.apPaymentDetail = apPaymentDetail;
	}

	public void setBeanItemArInvoice(BeanItem<FtPurchaseh> beanItemArInvoice) {
		this.beanItemArInvoice = beanItemArInvoice;
	}

	public void setBeanItemArPaymentHeader(
			BeanItem<FtAppaymenth> beanItemArPaymentHeader) {
		this.beanItemArPaymentHeader = beanItemArPaymentHeader;
	}

	public void setBeanItemArPaymentDetail(
			BeanItem<FtAppaymentd> beanItemArPaymentDetail) {
		this.beanItemArPaymentDetail = beanItemArPaymentDetail;
	}

	public void setBeanItemContainerArInvoice(
			BeanItemContainer<FtPurchaseh> beanItemContainerArInvoice) {
		this.beanItemContainerArInvoice = beanItemContainerArInvoice;
	}

	public void setBeanItemContainerArPaymentHeader(
			BeanItemContainer<FtAppaymenth> beanItemContainerArPaymentHeader) {
		this.beanItemContainerArPaymentHeader = beanItemContainerArPaymentHeader;
	}

	public void setBeanItemContainerArPaymentDetail(
			BeanItemContainer<FtAppaymentd> beanItemContainerArPaymentDetail) {
		this.beanItemContainerArPaymentDetail = beanItemContainerArPaymentDetail;
	}

	public void setBeanItemContainerBukuGiro(
			BeanItemContainer<Bukugiro> beanItemContainerBukuGiro) {
		this.beanItemContainerBukuGiro = beanItemContainerBukuGiro;
	}

	public void setBeanItemContainerBukuTransfer(
			BeanItemContainer<Bukutransfer> beanItemContainerBukuTransfer) {
		this.beanItemContainerBukuTransfer = beanItemContainerBukuTransfer;
	}

	public void setBeanitemContainerReturBelumLunas(
			BeanItemContainer<FtPurchaseh> beanitemContainerReturBelumLunas) {
		this.beanitemContainerReturBelumLunas = beanitemContainerReturBelumLunas;
	}

	public void setAllowCloseWindow(boolean allowCloseWindow) {
		this.allowCloseWindow = allowCloseWindow;
	}

	public void setFormOperationStatus(String formOperationStatus) {
		this.formOperationStatus = formOperationStatus;
	}

	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}

	public void setTanggalPembayaranManual(Date tanggalPembayaranManual) {
		this.tanggalPembayaranManual = tanggalPembayaranManual;
	}

	public void setAmountForOtherDetail(double amountForOtherDetail) {
		this.amountForOtherDetail = amountForOtherDetail;
	}

	public void setAmountForThisDetail(double amountForThisDetail) {
		this.amountForThisDetail = amountForThisDetail;
	}

	public void setAmountPenambahan(double amountPenambahan) {
		this.amountPenambahan = amountPenambahan;
	}
	
	
	
	
}
