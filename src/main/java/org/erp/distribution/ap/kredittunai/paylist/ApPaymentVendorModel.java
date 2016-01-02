package org.erp.distribution.ap.kredittunai.paylist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.BukugiroJpaService;
import org.erp.distribution.jpaservice.BukutransferJpaService;
import org.erp.distribution.jpaservice.FtArpaymentdJpaService;
import org.erp.distribution.jpaservice.FtArpaymenthJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.FtappaymentdJpaService;
import org.erp.distribution.jpaservice.FtappaymenthJpaService;
import org.erp.distribution.model.FtAppaymentd;
import org.erp.distribution.model.FtAppaymenth;
import org.erp.distribution.model.FtArpaymentd;
import org.erp.distribution.model.FtArpaymenth;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ApPaymentVendorModel extends CustomComponent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	public FtAppaymenth itemHeader = new FtAppaymenth();
	public FtAppaymentd itemDetail = new FtAppaymentd();
	public FtPurchaseh itemInvoice = new FtPurchaseh();

	private FtPurchasehJpaService ftPurchasehJpaService;
	private FtappaymenthJpaService apPaymentHeaderService;
	private FtappaymentdJpaService apPaymentDetailService;
	
	private BukugiroJpaService bukugiroService;
	private BukutransferJpaService bukutransferService;
	
//KITA PAKE BEAN ITEM CONTAINER - JPA
//	public JPAContainer<Arpaymentheader> tableJpaContainer;	
//	public JPAContainer<Arpaymentdetail> tableDetailJpaContainer;
	
	public BeanItem<FtPurchaseh> beanItemInvoice;
	public BeanItem<FtAppaymenth> beanItemHeader;
	public BeanItem<FtAppaymentd> beanItemDetail;
	
	public BeanItemContainer<FtAppaymenth> beanItemContainerHeader = new BeanItemContainer<FtAppaymenth>(FtAppaymenth.class);
	public BeanItemContainer<FtAppaymentd> beanItemContainerDetail = new BeanItemContainer<FtAppaymentd>(FtAppaymentd.class);

	//Bean ItemContainer Pendukung
	//TIDAK ADA
	
	private String operationStatus;
	
	private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();
	private Date tanggalPembayaranManual =null;
	
	public ApPaymentVendorModel(){
		initVariable();
		//TIDAK MENGIRIM VARIABLE MAKA TIDAK ADA NILAI AWAL
//		initVariableData();
	}
	public ApPaymentVendorModel(FtPurchaseh arInvoice){
		initVariable();
		this.itemInvoice = arInvoice;
		initVariableData();
	}
	
	public void setBukugiroService(BukugiroJpaService bukugiroService) {
		this.bukugiroService = bukugiroService;
	}
	public void setBukutransferService(BukutransferJpaService bukutransferService) {
		this.bukutransferService = bukutransferService;
	}
	public void initVariable(){

//		setTransaksiHelper(((DashboardUI) getUI().getCurrent()).getTransaksiHelper());
		
		setFtPurchasehJpaService(((DashboardUI) getUI().getCurrent()).getFtPurchasehJpaService());
		setApPaymentHeaderService(((DashboardUI) getUI().getCurrent()).getFtappaymenthJpaService());
		setApPaymentDetailService(((DashboardUI) getUI().getCurrent()).getFtappaymentdJpaService());
		
		setBukugiroService(((DashboardUI) getUI().getCurrent()).getBukugiroJpaService());
		setBukutransferService(((DashboardUI) getUI().getCurrent()).getBukutransferJpaService());
		
		
		
	};

	public void initVariableData(){		
			//JANGAN SAMPAI NULL	
			beanItemInvoice = new BeanItem<FtPurchaseh>(itemInvoice);
			
			if (itemInvoice!=null){
				
				//MENGHASILKAN HEADER DARI DETAIL
				//Get List refno Transaksi yang ada INVOICE dan DIVISI 
				List<FtAppaymentd> listDetail = new ArrayList<FtAppaymentd>();
				Long refnosales = itemInvoice.getRefno();
//				String div = itemInvoice.getId().getDivision();
				listDetail = apPaymentDetailService.findAllByInvoiceAndDiv(refnosales, null);
				beanItemContainerDetail.addAll(listDetail);
				
				List<FtAppaymenth> listHeader = new ArrayList<FtAppaymenth>();				
				for (FtAppaymentd item: listDetail){
					//JIKA SAMA >> HEADER UPDATE/TIMPA
					listHeader.add(item.getFtappaymenthBean());
				}			
				beanItemContainerHeader = new BeanItemContainer<FtAppaymenth>(FtAppaymenth.class);
				beanItemContainerHeader.addAll(listHeader);
				
			}
		
//			//CALCULASI ULANG AMOUNTPAID
//			beanItemInvoice.getItemProperty("amountpay").setValue(getHitungAmountDetailNow());
			//COBA LANGSUNG DARI DATABASE
//			beanItemInvoice.getItemProperty("amountpay").setValue(getHitungAmountDetailAllFromDatabase());
			getItemInvoice().setAmountpay(getHitungAmountDetailAllFromDatabase());
			//UPDATE ARINVOICE
			getFtPurchasehJpaService().updateObject(getItemInvoice());
			
//			//DI NOLKAN LAGI SUPAYA DETAIL TABLE DETAIL KOSONG DAHULU
			beanItemContainerDetail.removeAllItems();

	}
	
	public double getHitungAmountDetailAllFromDatabase(){
		List<FtAppaymentd> listDetail = new ArrayList<FtAppaymentd>();
		Long refnosales = itemInvoice.getRefno();
//		String div = itemInvoice.getId().getDivision();
		listDetail = apPaymentDetailService.findAllByInvoiceAndDiv(refnosales, null);
		
		double amountInvoiceDetailAll = 0.0;		
		for (FtAppaymentd item: listDetail){
			amountInvoiceDetailAll += item.getCashamountpay() + item.getGiroamountpay() + 
					item.getMrvamountpay() + item.getTransferamountpay() + item.getDcvamountpay() + item.getKelebihanbayaramount();
		}
		
		return amountInvoiceDetailAll;
	}
	public double getHitungAmountDetailNow(){
		
		double amountInvoiceDetailAll = 0.0;
		Collection itemIds = getBeanItemContainerDetail().getItemIds();
		for (Object itemId: itemIds){
			FtAppaymentd item = getBeanItemContainerDetail().getItem(itemId).getBean();
			amountInvoiceDetailAll += item.getCashamountpay() + item.getGiroamountpay() + 
					item.getMrvamountpay() + item.getTransferamountpay() + item.getDcvamountpay() + item.getKelebihanbayaramount();
		}
		
		return amountInvoiceDetailAll;		
	}
	
	public void removeContainerFiltersTableUtama(){		
		try{
		} catch(Exception ex){}
		
	}
	public void removeContainerFiltersTablePendukung(){
		try{
		} catch(Exception ex){}
	}
	public void setFreshDataForm(){
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getPersistenceunit() {
		return persistenceUnit;
	}
	public FtAppaymenth getItemHeader() {
		return itemHeader;
	}
	public FtAppaymentd getItemDetail() {
		return itemDetail;
	}
	public FtPurchaseh getItemInvoice() {
		return itemInvoice;
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
	public BukugiroJpaService getBukugiroService() {
		return bukugiroService;
	}
	public BukutransferJpaService getBukutransferService() {
		return bukutransferService;
	}
	public BeanItem<FtPurchaseh> getBeanItemInvoice() {
		return beanItemInvoice;
	}
	public BeanItem<FtAppaymenth> getBeanItemHeader() {
		return beanItemHeader;
	}
	public BeanItem<FtAppaymentd> getBeanItemDetail() {
		return beanItemDetail;
	}
	public BeanItemContainer<FtAppaymenth> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FtAppaymentd> getBeanItemContainerDetail() {
		return beanItemContainerDetail;
	}
	public String getOperationStatus() {
		return operationStatus;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public Date getTanggalPembayaranManual() {
		return tanggalPembayaranManual;
	}
	public void setItemHeader(FtAppaymenth itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setItemDetail(FtAppaymentd itemDetail) {
		this.itemDetail = itemDetail;
	}
	public void setItemInvoice(FtPurchaseh itemInvoice) {
		this.itemInvoice = itemInvoice;
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
	public void setBeanItemInvoice(BeanItem<FtPurchaseh> beanItemInvoice) {
		this.beanItemInvoice = beanItemInvoice;
	}
	public void setBeanItemHeader(BeanItem<FtAppaymenth> beanItemHeader) {
		this.beanItemHeader = beanItemHeader;
	}
	public void setBeanItemDetail(BeanItem<FtAppaymentd> beanItemDetail) {
		this.beanItemDetail = beanItemDetail;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FtAppaymenth> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerDetail(
			BeanItemContainer<FtAppaymentd> beanItemContainerDetail) {
		this.beanItemContainerDetail = beanItemContainerDetail;
	}
	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setTanggalPembayaranManual(Date tanggalPembayaranManual) {
		this.tanggalPembayaranManual = tanggalPembayaranManual;
	}
	
	
	
}
