package org.erp.distribution.ar.kredittunai.paylist.payform;

import java.util.Date;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.BukugiroJpaService;
import org.erp.distribution.jpaservice.BukutransferJpaService;
import org.erp.distribution.jpaservice.FtArpaymentdJpaService;
import org.erp.distribution.jpaservice.FtArpaymenthJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.model.Bukugiro;
import org.erp.distribution.model.Bukutransfer;
import org.erp.distribution.model.FtArpaymentd;
import org.erp.distribution.model.FtArpaymenth;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ArPaymentCustPembayaranModel extends CustomComponent{
	
	private static final String persistenceUnit = "financePU";
	//DAO
	private FtSaleshJpaService arInvoiceService;
	private FtArpaymenthJpaService arPaymentHeaderService;
	private FtArpaymentdJpaService arPaymentDetailService;
	
	//DAO --> combobox
	private BukugiroJpaService bukuGiroService;
	private BukutransferJpaService bukuTransferService;
	
	//DAO PENDUKUNG	
	public FtSalesh arInvoice;
	public FtArpaymenth arPaymentHeader;
	public FtArpaymentd arPaymentDetail;
	
	//BeanItem utama
	public BeanItem<FtSalesh> beanItemArInvoice;
	public BeanItem<FtArpaymenth> beanItemArPaymentHeader;
	public BeanItem<FtArpaymentd> beanItemArPaymentDetail;
	//FOR COMBOBOX
	
	//######TIDAK DIGUNAKAN: BeanItemContainer Utama
	public BeanItemContainer<FtSalesh> beanItemContainerArInvoice;
	public BeanItemContainer<FtArpaymenth> beanItemContainerArPaymentHeader;
	public BeanItemContainer<FtArpaymentd> beanItemContainerArPaymentDetail;
	
	//for combobox
	public BeanItemContainer<Bukugiro> beanItemContainerBukuGiro;
	public BeanItemContainer<Bukutransfer> beanItemContainerBukuTransfer;
	public BeanItemContainer<FtSalesh> beanitemContainerReturBelumLunas;
	
	//VARIABLE PENDUKUNG
	private boolean allowCloseWindow;
	private String formOperationStatus = "OPEN";
	private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();
	private Date tanggalPembayaranManual =null;
	
	double amountForOtherDetail =0.0;
	double amountForThisDetail =0.0;
	double amountPenambahan =0.0;
	
	
	
	public ArPaymentCustPembayaranModel() {
		initVariable();
	}
	
	public ArPaymentCustPembayaranModel(FtSalesh arinvoice){		
		initVariable();
		this.arInvoice = arinvoice;
		initVariableData();
	}
	public ArPaymentCustPembayaranModel(FtArpaymentd arpaymentdetail){
		initVariable();
		this.arPaymentDetail = arpaymentdetail;
		initVariableData();
	}
	public ArPaymentCustPembayaranModel(FtSalesh arinvoice, FtArpaymentd arpaymentdetail){
		initVariable();
		this.arInvoice = arinvoice;
		this.arPaymentDetail = arpaymentdetail;
		initVariableData();
	}
	public void initVariable(){
		arInvoice = new FtSalesh();
		arPaymentHeader = new FtArpaymenth();
		arPaymentDetail = new FtArpaymentd();
		
//		setTransaksiHelper(((DashboardUI) getUI().getCurrent()).getTransaksiHelper());
		//DAO UTAMA
		setArInvoiceService(((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService());
		setArPaymentHeaderService(((DashboardUI) getUI().getCurrent()).getFtArpaymenthJpaService());
		setArPaymentDetailService(((DashboardUI) getUI().getCurrent()).getFtArpaymentdJpaService());
		
		//DAO --> COMBO
		setBukuGiroService(((DashboardUI) getUI().getCurrent()).getBukugiroJpaService());
		setBukuTransferService(((DashboardUI) getUI().getCurrent()).getBukutransferJpaService());
		
		beanItemContainerArPaymentHeader = new BeanItemContainer<FtArpaymenth>(FtArpaymenth.class);
		beanItemContainerArPaymentDetail = new BeanItemContainer<FtArpaymentd>(FtArpaymentd.class);
		//FOR COMBOBOX
		beanItemContainerBukuGiro = new BeanItemContainer<Bukugiro>(Bukugiro.class);
		beanItemContainerBukuTransfer = new BeanItemContainer<Bukutransfer>(Bukutransfer.class);
		beanitemContainerReturBelumLunas = new BeanItemContainer<FtSalesh>(FtSalesh.class);
		
		allowCloseWindow = true;
		
	}
	
	public void initVariableData(){	
		
		beanItemArInvoice = new BeanItem<FtSalesh>(arInvoice);
		beanItemArPaymentHeader = new BeanItem<FtArpaymenth>(arPaymentHeader);
		beanItemArPaymentDetail = new BeanItem<FtArpaymentd>(arPaymentDetail);
		
		if (arPaymentDetail.getBukugiroBean() !=null) {			
			beanItemContainerBukuGiro.addAll(bukuGiroService.findAllAvalilableGiro(arPaymentDetail.getBukugiroBean().getRefno()));			
		}else {
			beanItemContainerBukuGiro.addAll(bukuGiroService.findAllAvailableGiro());
		}
		if (arPaymentDetail.getBukutransferBean() !=null){
			beanItemContainerBukuTransfer.addAll(bukuTransferService.findAllAvailabelTransfer(arPaymentDetail.getBukutransferBean().getRefno()));			
			beanItemContainerBukuTransfer.addAll(bukuTransferService.findAllAvailableTransfer());			
		}else {
			beanItemContainerBukuTransfer.addAll(bukuTransferService.findAllAvailableTransfer());			
		}
		if (arPaymentDetail.getReturBean() != null){
			beanitemContainerReturBelumLunas.addAll(arInvoiceService.findAllReturBelumLunas(arPaymentDetail.getReturBean()));
		} else {
			beanitemContainerReturBelumLunas.addAll(arInvoiceService.findAllReturBelumLunas());
		}
		
		
		//UNTUK MENGETAHUI BAHWA INI ADD ATAU EDIT >> 
		if (arPaymentDetail.getId().getRefnopayment()== -1234){
			formOperationStatus =  EnumOperationStatus.ADDING.getStrCode();
		} else {
			formOperationStatus =  EnumOperationStatus.EDITING.getStrCode();
		}
		
	}
	
	
	public void removeContainerFiltersTableUtama(){
		
	}	
	
	public void removeContainerFiltersTablePendukung(){
	}

	public static String getPersistenceunit() {
		return persistenceUnit;
	}

	public FtSaleshJpaService getArInvoiceService() {
		return arInvoiceService;
	}

	public FtArpaymenthJpaService getArPaymentHeaderService() {
		return arPaymentHeaderService;
	}

	public FtArpaymentdJpaService getArPaymentDetailService() {
		return arPaymentDetailService;
	}

	public BukugiroJpaService getBukuGiroService() {
		return bukuGiroService;
	}

	public BukutransferJpaService getBukuTransferService() {
		return bukuTransferService;
	}

	public FtSalesh getArInvoice() {
		return arInvoice;
	}

	public FtArpaymenth getArPaymentHeader() {
		return arPaymentHeader;
	}

	public FtArpaymentd getArPaymentDetail() {
		return arPaymentDetail;
	}

	public BeanItem<FtSalesh> getBeanItemArInvoice() {
		return beanItemArInvoice;
	}

	public BeanItem<FtArpaymenth> getBeanItemArPaymentHeader() {
		return beanItemArPaymentHeader;
	}

	public BeanItem<FtArpaymentd> getBeanItemArPaymentDetail() {
		return beanItemArPaymentDetail;
	}

	public BeanItemContainer<FtSalesh> getBeanItemContainerArInvoice() {
		return beanItemContainerArInvoice;
	}

	public BeanItemContainer<FtArpaymenth> getBeanItemContainerArPaymentHeader() {
		return beanItemContainerArPaymentHeader;
	}

	public BeanItemContainer<FtArpaymentd> getBeanItemContainerArPaymentDetail() {
		return beanItemContainerArPaymentDetail;
	}

	public BeanItemContainer<Bukugiro> getBeanItemContainerBukuGiro() {
		return beanItemContainerBukuGiro;
	}

	public BeanItemContainer<Bukutransfer> getBeanItemContainerBukuTransfer() {
		return beanItemContainerBukuTransfer;
	}

	public BeanItemContainer<FtSalesh> getBeanitemContainerReturBelumLunas() {
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

	public void setArInvoiceService(FtSaleshJpaService arInvoiceService) {
		this.arInvoiceService = arInvoiceService;
	}

	public void setArPaymentHeaderService(
			FtArpaymenthJpaService arPaymentHeaderService) {
		this.arPaymentHeaderService = arPaymentHeaderService;
	}

	public void setArPaymentDetailService(
			FtArpaymentdJpaService arPaymentDetailService) {
		this.arPaymentDetailService = arPaymentDetailService;
	}

	public void setBukuGiroService(BukugiroJpaService bukuGiroService) {
		this.bukuGiroService = bukuGiroService;
	}

	public void setBukuTransferService(BukutransferJpaService bukuTransferService) {
		this.bukuTransferService = bukuTransferService;
	}

	public void setArInvoice(FtSalesh arInvoice) {
		this.arInvoice = arInvoice;
	}

	public void setArPaymentHeader(FtArpaymenth arPaymentHeader) {
		this.arPaymentHeader = arPaymentHeader;
	}

	public void setArPaymentDetail(FtArpaymentd arPaymentDetail) {
		this.arPaymentDetail = arPaymentDetail;
	}

	public void setBeanItemArInvoice(BeanItem<FtSalesh> beanItemArInvoice) {
		this.beanItemArInvoice = beanItemArInvoice;
	}

	public void setBeanItemArPaymentHeader(
			BeanItem<FtArpaymenth> beanItemArPaymentHeader) {
		this.beanItemArPaymentHeader = beanItemArPaymentHeader;
	}

	public void setBeanItemArPaymentDetail(
			BeanItem<FtArpaymentd> beanItemArPaymentDetail) {
		this.beanItemArPaymentDetail = beanItemArPaymentDetail;
	}

	public void setBeanItemContainerArInvoice(
			BeanItemContainer<FtSalesh> beanItemContainerArInvoice) {
		this.beanItemContainerArInvoice = beanItemContainerArInvoice;
	}

	public void setBeanItemContainerArPaymentHeader(
			BeanItemContainer<FtArpaymenth> beanItemContainerArPaymentHeader) {
		this.beanItemContainerArPaymentHeader = beanItemContainerArPaymentHeader;
	}

	public void setBeanItemContainerArPaymentDetail(
			BeanItemContainer<FtArpaymentd> beanItemContainerArPaymentDetail) {
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
			BeanItemContainer<FtSalesh> beanitemContainerReturBelumLunas) {
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
