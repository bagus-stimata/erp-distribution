package org.erp.distribution.ap.kredittunai;

import java.io.Serializable;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FDivisionJpaService;
import org.erp.distribution.jpaservice.FtArpaymentdJpaService;
import org.erp.distribution.jpaservice.FtArpaymenthJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.FtSaleshRekapTampunganJpaService;
import org.erp.distribution.model.FDivision;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.FtSaleshRekapTampungan;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Not;
import com.vaadin.data.util.filter.Or;
import com.vaadin.ui.CustomComponent;

public class VendorCreditModel extends CustomComponent implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private static final String persistenceUnit = "financePU";
	
	public FtPurchaseh item = new FtPurchaseh();

//	private JPAContainer<Arinvoice> tableJpaContainer;	
	
	public BeanItem<FtPurchaseh> formBeanItem;
	private BeanItemContainer<FtPurchaseh> tableBeanItemContainer = new BeanItemContainer<FtPurchaseh>(FtPurchaseh.class);

	private FtPurchasehJpaService ftPurchasehJpaService;; 
	private FtArpaymenthJpaService ftArpaymenthJpaService ;
	private FtArpaymentdJpaService ftArpaymentdJpaService;
	private FDivisionJpaService fDivisionJpaService;
	private FtSaleshRekapTampunganJpaService FtSaleshRekapTampunganJpaService;

	
	private String operationStatus="";	
	private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();
	
	//CHECK BOX BUAT TABLE PADA HEADER
	private boolean selectAllInvoice=false;
	
	private BeanItemContainer<FDivision> beanItemContainerDivision;

	
	public VendorCreditModel(){
		initVariable();
		initVariableData();
	}
	
	public void initVariable(){
		
		beanItemContainerDivision = new BeanItemContainer<FDivision>(FDivision.class);
		
//		setTransaksiHelper(((DashboardUI) getUI().getCurrent()).getTransaksiHelper());
		
		setFtPurchasehJpaService(((DashboardUI) getUI().getCurrent()).getFtPurchasehJpaService());
		setFtArpaymenthJpaService(((DashboardUI) getUI().getCurrent()).getFtArpaymenthJpaService());
		setFtArpaymentdJpaService(((DashboardUI) getUI().getCurrent()).getFtArpaymentdJpaService());
		setFtSaleshRekapTampunganJpaService(((DashboardUI) getUI().getCurrent()).getFtSaleshRekapTampunganJpaService());
		
		setfDivisionJpaService(((DashboardUI) getUI().getCurrent()).getfDivisionJpaService());
				
		beanItemContainerDivision.addAll(fDivisionJpaService.findAll());
		
	};

	public void initVariableData(){		
		try{
			tableBeanItemContainer.removeAllItems();
			tableBeanItemContainer.removeAllContainerFilters();
			
//			tableBeanItemContainer.addAll(arInvoiceService.findAll());
			tableBeanItemContainer.addNestedContainerProperty("fwarehouseBean.id");
			
			setFilterDefaultBeanItemContainer();

			
			//COMBOBOX DIVISION
			beanItemContainerDivision.addAll(fDivisionJpaService.findAll());
			
		} catch(Exception ex){
		
		}
	}
	
	public void setFilterDefaultBeanItemContainer(){

		//TO DAN TUNAI selain CANVAS
//		Filter filter01 =  new SimpleStringFilter("tipejual","TO", true, false);
//		tableBeanItemContainer.addContainerFilter(filter01);
//				
//		Filter filter02 = new Or(new Compare.Equal("term", 1)); 
//		tableBeanItemContainer.addContainerFilter(filter02);
//		
//		Filter filter01 =  new Not(new Compare.Equal("tipejual", "C"));
//		tableBeanItemContainer.addContainerFilter(filter01);
		
//		Filter filter03 = new Or(new Compare.Equal("terkirim", true)); 
//		tableBeanItemContainer.addContainerFilter(filter03);
		
	}
	
	public void setFreshDataForm(){
		
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getPersistenceunit() {
		return persistenceUnit;
	}

	public FtPurchaseh getItem() {
		return item;
	}

	public BeanItem<FtPurchaseh> getFormBeanItem() {
		return formBeanItem;
	}

	public BeanItemContainer<FtPurchaseh> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public FtPurchasehJpaService getFtPurchasehJpaService() {
		return ftPurchasehJpaService;
	}

	public FtArpaymenthJpaService getFtArpaymenthJpaService() {
		return ftArpaymenthJpaService;
	}

	public FtArpaymentdJpaService getFtArpaymentdJpaService() {
		return ftArpaymentdJpaService;
	}

	public FDivisionJpaService getfDivisionJpaService() {
		return fDivisionJpaService;
	}

	public FtSaleshRekapTampunganJpaService getFtSaleshRekapTampunganJpaService() {
		return FtSaleshRekapTampunganJpaService;
	}

	public String getOperationStatus() {
		return operationStatus;
	}

	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}

	public boolean isSelectAllInvoice() {
		return selectAllInvoice;
	}

	public BeanItemContainer<FDivision> getBeanItemContainerDivision() {
		return beanItemContainerDivision;
	}

	public void setItem(FtPurchaseh item) {
		this.item = item;
	}

	public void setFormBeanItem(BeanItem<FtPurchaseh> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<FtPurchaseh> tableBeanItemContainer) {
		this.tableBeanItemContainer = tableBeanItemContainer;
	}

	public void setFtPurchasehJpaService(FtPurchasehJpaService ftPurchasehJpaService) {
		this.ftPurchasehJpaService = ftPurchasehJpaService;
	}

	public void setFtArpaymenthJpaService(
			FtArpaymenthJpaService ftArpaymenthJpaService) {
		this.ftArpaymenthJpaService = ftArpaymenthJpaService;
	}

	public void setFtArpaymentdJpaService(
			FtArpaymentdJpaService ftArpaymentdJpaService) {
		this.ftArpaymentdJpaService = ftArpaymentdJpaService;
	}

	public void setfDivisionJpaService(FDivisionJpaService fDivisionJpaService) {
		this.fDivisionJpaService = fDivisionJpaService;
	}

	public void setFtSaleshRekapTampunganJpaService(
			FtSaleshRekapTampunganJpaService ftSaleshRekapTampunganJpaService) {
		FtSaleshRekapTampunganJpaService = ftSaleshRekapTampunganJpaService;
	}

	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}

	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}

	public void setSelectAllInvoice(boolean selectAllInvoice) {
		this.selectAllInvoice = selectAllInvoice;
	}

	public void setBeanItemContainerDivision(
			BeanItemContainer<FDivision> beanItemContainerDivision) {
		this.beanItemContainerDivision = beanItemContainerDivision;
	}

	
	
}
