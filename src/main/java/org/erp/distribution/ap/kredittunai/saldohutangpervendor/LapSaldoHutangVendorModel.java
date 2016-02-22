package org.erp.distribution.ap.kredittunai.saldohutangpervendor;

import java.io.Serializable;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FDivisionJpaService;
import org.erp.distribution.jpaservice.FSalesmanJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FtArpaymentdJpaService;
import org.erp.distribution.jpaservice.FtArpaymenthJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.FtSaleshRekapTampunganJpaService;
import org.erp.distribution.jpaservice.FtappaymentdJpaService;
import org.erp.distribution.jpaservice.FtappaymenthJpaService;
import org.erp.distribution.jpaservicerep.LapTemplate1JpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FDivision;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.FtSaleshRekapTampungan;
import org.erp.distribution.model.ZLapTemplate1;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Not;
import com.vaadin.data.util.filter.Or;
import com.vaadin.ui.CustomComponent;

public class LapSaldoHutangVendorModel extends CustomComponent implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private static final String persistenceUnit = "financePU";
	
	public FtPurchaseh item = new FtPurchaseh();

//	private JPAContainer<Arinvoice> tableJpaContainer;	
	
	public BeanItem<FtPurchaseh> formBeanItem;
	private BeanItemContainer<FtPurchaseh> tableBeanItemContainer = new BeanItemContainer<FtPurchaseh>(FtPurchaseh.class);

	private FtPurchasehJpaService ftPurchasehJpaService;; 
	private FtappaymenthJpaService ftappaymenthJpaService ;
	private FtappaymentdJpaService ftappaymentdJpaService;
	private FDivisionJpaService fDivisionJpaService;
	
	private FVendorJpaService fVendorJpaService;

	private LapTemplate1JpaService lapTemplate1JpaService;
	
	private String operationStatus="";	
	private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();
	
	//CHECK BOX BUAT TABLE PADA HEADER
	private boolean selectAllInvoice=false;
	
	private BeanItemContainer<FDivision> beanItemContainerDivision;

	private BeanItemContainer<FVendor> beanItemContainerVendor = new BeanItemContainer<FVendor>(FVendor.class);

	
	public LapSaldoHutangVendorModel(){
		initData();
		initVariableData();
	}
	
	public void initData(){
		
		beanItemContainerDivision = new BeanItemContainer<FDivision>(FDivision.class);
		
//		setTransaksiHelper(((DashboardUI) getUI().getCurrent()).getTransaksiHelper());
		
		setFtPurchasehJpaService(((DashboardUI) getUI().getCurrent()).getFtPurchasehJpaService());
		setFtappaymenthJpaService(((DashboardUI) getUI().getCurrent()).getFtappaymenthJpaService());
		setFtappaymentdJpaService(((DashboardUI) getUI().getCurrent()).getFtappaymentdJpaService());
		
		setfDivisionJpaService(((DashboardUI) getUI().getCurrent()).getfDivisionJpaService());

		setfVendorJpaService(((DashboardUI) getUI().getCurrent()).getfVendorJpaService());
		
		beanItemContainerDivision.addAll(fDivisionJpaService.findAll());
		
//		tableBeanItemContainer.addNestedContainerProperty("id.tipefaktur");
	};

	public void initVariableData(){		
		try{
			tableBeanItemContainer.removeAllItems();
			tableBeanItemContainer.removeAllContainerFilters();
			
			
			//COMBOBOX DIVISION
			beanItemContainerDivision.addAll(fDivisionJpaService.findAll());

			beanItemContainerVendor.addAll(fVendorJpaService.findAll());
			
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

	public FtappaymenthJpaService getFtappaymenthJpaService() {
		return ftappaymenthJpaService;
	}

	public FtappaymentdJpaService getFtappaymentdJpaService() {
		return ftappaymentdJpaService;
	}

	public FDivisionJpaService getfDivisionJpaService() {
		return fDivisionJpaService;
	}

	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
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

	public BeanItemContainer<FVendor> getBeanItemContainerVendor() {
		return beanItemContainerVendor;
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

	public void setFtappaymenthJpaService(
			FtappaymenthJpaService ftappaymenthJpaService) {
		this.ftappaymenthJpaService = ftappaymenthJpaService;
	}

	public void setFtappaymentdJpaService(
			FtappaymentdJpaService ftappaymentdJpaService) {
		this.ftappaymentdJpaService = ftappaymentdJpaService;
	}

	public void setfDivisionJpaService(FDivisionJpaService fDivisionJpaService) {
		this.fDivisionJpaService = fDivisionJpaService;
	}

	public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
		this.fVendorJpaService = fVendorJpaService;
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

	public void setBeanItemContainerVendor(
			BeanItemContainer<FVendor> beanItemContainerVendor) {
		this.beanItemContainerVendor = beanItemContainerVendor;
	}

	public LapTemplate1JpaService getLapTemplate1JpaService() {
		return lapTemplate1JpaService;
	}

	public void setLapTemplate1JpaService(
			LapTemplate1JpaService lapTemplate1JpaService) {
		this.lapTemplate1JpaService = lapTemplate1JpaService;
	}

	
}
