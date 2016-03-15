package org.erp.distribution.ar.kredittunai.paylist;

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
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.model.FtArpaymentd;
import org.erp.distribution.model.FtArpaymenth;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ArPaymentCustomerModel extends CustomComponent implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final String persistenceUnit = "financePU";
	
	public FtArpaymenth itemHeader;
	public FtArpaymentd itemDetail;
	public FtSalesh itemInvoice;

	private FtSaleshJpaService arInvoiceService;
	private FtArpaymenthJpaService arPaymentHeaderService;
	private FtArpaymentdJpaService arPaymentDetailService;
	
	private BukugiroJpaService bukugiroService;
	private BukutransferJpaService bukutransferService;
	
//KITA PAKE BEAN ITEM CONTAINER - JPA
//	public JPAContainer<Arpaymentheader> tableJpaContainer;	
//	public JPAContainer<Arpaymentdetail> tableDetailJpaContainer;
	
	public BeanItem<FtSalesh> beanItemInvoice;
	public BeanItem<FtArpaymenth> beanItemHeader;
	public BeanItem<FtArpaymentd> beanItemDetail;
	
	public BeanItemContainer<FtArpaymenth> beanItemContainerHeader;
	public BeanItemContainer<FtArpaymentd> beanItemContainerDetail;
	
	//Bean ItemContainer Pendukung
	//TIDAK ADA
	
	private String operationStatus;
	
	private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();
	private Date tanggalPembayaranManual =null;
	
	public ArPaymentCustomerModel(){
		initVariable();
		//TIDAK MENGIRIM VARIABLE MAKA TIDAK ADA NILAI AWAL
//		initVariableData();
	}
	public ArPaymentCustomerModel(FtSalesh arInvoice){
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
		itemInvoice = new FtSalesh();
		itemHeader = new FtArpaymenth();
		itemDetail = new FtArpaymentd();

//		setTransaksiHelper(((DashboardUI) getUI().getCurrent()).getTransaksiHelper());
		
		setArInvoiceService(((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService());
		setArPaymentHeaderService(((DashboardUI) getUI().getCurrent()).getFtArpaymenthJpaService());
		setArPaymentDetailService(((DashboardUI) getUI().getCurrent()).getFtArpaymentdJpaService());
		
		setBukugiroService(((DashboardUI) getUI().getCurrent()).getBukugiroJpaService());
		setBukutransferService(((DashboardUI) getUI().getCurrent()).getBukutransferJpaService());
		
		
		beanItemContainerHeader = new BeanItemContainer<FtArpaymenth>(FtArpaymenth.class);
		beanItemContainerDetail = new BeanItemContainer<FtArpaymentd>(FtArpaymentd.class);
		
	};

	public void initVariableData(){		
			//JANGAN SAMPAI NULL	
			beanItemInvoice = new BeanItem<FtSalesh>(itemInvoice);
			
			if (itemInvoice!=null){
				
				//MENGHASILKAN HEADER DARI DETAIL
				//Get List refno Transaksi yang ada INVOICE dan DIVISI 
				List<FtArpaymentd> listDetail = new ArrayList<FtArpaymentd>();
				Long refnosales = itemInvoice.getRefno();
//				String div = itemInvoice.getId().getDivision();
				listDetail = arPaymentDetailService.findAllByInvoiceAndDiv(refnosales, null);
				beanItemContainerDetail.addAll(listDetail);
				
				List<FtArpaymenth> listHeader = new ArrayList<FtArpaymenth>();				
				for (FtArpaymentd item: listDetail){
					//JIKA SAMA >> HEADER UPDATE/TIMPA
					listHeader.add(item.getFtarpaymenthBean());
				}			
				beanItemContainerHeader = new BeanItemContainer<FtArpaymenth>(FtArpaymenth.class);
				beanItemContainerHeader.addAll(listHeader);
				
			}
		
//			//CALCULASI ULANG AMOUNTPAID
//			beanItemInvoice.getItemProperty("amountpay").setValue(getHitungAmountDetailNow());
			//COBA LANGSUNG DARI DATABASE
//			beanItemInvoice.getItemProperty("amountpay").setValue(getHitungAmountDetailAllFromDatabase());
			getItemInvoice().setAmountpay(getHitungAmountDetailAllFromDatabase());
			//UPDATE ARINVOICE
			getArInvoiceService().updateObject(getItemInvoice());
			
//			//DI NOLKAN LAGI SUPAYA DETAIL TABLE DETAIL KOSONG DAHULU
			beanItemContainerDetail.removeAllItems();

	}
	
	public double getHitungAmountDetailAllFromDatabase(){
		List<FtArpaymentd> listDetail = new ArrayList<FtArpaymentd>();
		Long refnosales = itemInvoice.getRefno();
//		String div = itemInvoice.getId().getDivision();
		listDetail = arPaymentDetailService.findAllByInvoiceAndDiv(refnosales, null);
		
		double amountInvoiceDetailAll = 0.0;		
		for (FtArpaymentd item: listDetail){
			amountInvoiceDetailAll += item.getCashamountpay() + item.getGiroamountpay() + 
					item.getReturamountpay() + item.getTransferamountpay() + item.getPotonganamount() + item.getKelebihanbayaramount();
		}
		
		return amountInvoiceDetailAll;
	}
	public double getHitungAmountDetailNow(){
		
		double amountInvoiceDetailAll = 0.0;
		Collection itemIds = getBeanItemContainerDetail().getItemIds();
		for (Object itemId: itemIds){
			FtArpaymentd item = getBeanItemContainerDetail().getItem(itemId).getBean();
			amountInvoiceDetailAll += item.getCashamountpay() + item.getGiroamountpay() + 
					item.getReturamountpay() + item.getTransferamountpay() + item.getPotonganamount() + item.getKelebihanbayaramount();
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
	public FtArpaymenth getItemHeader() {
		return itemHeader;
	}
	public FtArpaymentd getItemDetail() {
		return itemDetail;
	}
	public FtSalesh getItemInvoice() {
		return itemInvoice;
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
	public BukugiroJpaService getBukugiroService() {
		return bukugiroService;
	}
	public BukutransferJpaService getBukutransferService() {
		return bukutransferService;
	}
	public BeanItem<FtSalesh> getBeanItemInvoice() {
		return beanItemInvoice;
	}
	public BeanItem<FtArpaymenth> getBeanItemHeader() {
		return beanItemHeader;
	}
	public BeanItem<FtArpaymentd> getBeanItemDetail() {
		return beanItemDetail;
	}
	public BeanItemContainer<FtArpaymenth> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FtArpaymentd> getBeanItemContainerDetail() {
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
	public void setItemHeader(FtArpaymenth itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setItemDetail(FtArpaymentd itemDetail) {
		this.itemDetail = itemDetail;
	}
	public void setItemInvoice(FtSalesh itemInvoice) {
		this.itemInvoice = itemInvoice;
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
	public void setBeanItemInvoice(BeanItem<FtSalesh> beanItemInvoice) {
		this.beanItemInvoice = beanItemInvoice;
	}
	public void setBeanItemHeader(BeanItem<FtArpaymenth> beanItemHeader) {
		this.beanItemHeader = beanItemHeader;
	}
	public void setBeanItemDetail(BeanItem<FtArpaymentd> beanItemDetail) {
		this.beanItemDetail = beanItemDetail;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FtArpaymenth> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerDetail(
			BeanItemContainer<FtArpaymentd> beanItemContainerDetail) {
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
