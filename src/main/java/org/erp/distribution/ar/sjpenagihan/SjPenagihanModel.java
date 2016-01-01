package org.erp.distribution.ar.sjpenagihan;

import java.io.Serializable;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FDivisionJpaService;
import org.erp.distribution.jpaservice.FSalesmanJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.FtArpaymentdJpaService;
import org.erp.distribution.jpaservice.FtArpaymenthJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.FtSaleshRekapTampunganJpaService;
import org.erp.distribution.jpaservicerep.LapSJPenagihanListJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FDivision;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FSubarea;
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

public class SjPenagihanModel extends CustomComponent implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private static final String persistenceUnit = "financePU";
	
	public FtSalesh item = new FtSalesh();

//	private JPAContainer<Arinvoice> tableJpaContainer;	
	
	public BeanItem<FtSalesh> formBeanItem;
	private BeanItemContainer<FtSalesh> tableBeanItemContainer = new BeanItemContainer<FtSalesh>(FtSalesh.class);

	private FtSaleshJpaService ftSaleshJpaService;; 
	private FtArpaymenthJpaService ftArpaymenthJpaService ;
	private FtArpaymentdJpaService ftArpaymentdJpaService;
	private FDivisionJpaService fDivisionJpaService;
	private FtSaleshRekapTampunganJpaService FtSaleshRekapTampunganJpaService;
	private LapSJPenagihanListJpaService lapSJPenagihanListJpaService;
	
	private FAreaJpaService fAreaJpaService;
	private FSubareaJpaService fSubareaJpaService;
	private FSalesmanJpaService fSalesmanJpaService;

	
	private String operationStatus="";	
	private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();
	
	//CHECK BOX BUAT TABLE PADA HEADER
	private boolean selectAllInvoice=false;
	
	private BeanItemContainer<FDivision> beanItemContainerDivision;

	private BeanItemContainer<FArea> beanItemContainerArea = new BeanItemContainer<FArea>(FArea.class);
	private BeanItemContainer<FSubarea> beanItemContainerSubArea = new BeanItemContainer<FSubarea>(FSubarea.class);
	private BeanItemContainer<FSalesman> beanItemContainerSalesman = new BeanItemContainer<FSalesman>(FSalesman.class);

	
	public SjPenagihanModel(){
		initData();
		setFreshDataTable();
	}
	
	public void initData(){
		
		beanItemContainerDivision = new BeanItemContainer<FDivision>(FDivision.class);
		
//		setTransaksiHelper(((DashboardUI) getUI().getCurrent()).getTransaksiHelper());
		
		setFtSaleshJpaService(((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService());
		setFtArpaymenthJpaService(((DashboardUI) getUI().getCurrent()).getFtArpaymenthJpaService());
		setFtArpaymentdJpaService(((DashboardUI) getUI().getCurrent()).getFtArpaymentdJpaService());
		setFtSaleshRekapTampunganJpaService(((DashboardUI) getUI().getCurrent()).getFtSaleshRekapTampunganJpaService());
		
		setfDivisionJpaService(((DashboardUI) getUI().getCurrent()).getfDivisionJpaService());

		setfAreaJpaService(((DashboardUI) getUI().getCurrent()).getfAreaJpaService());
		setfSubareaJpaService(((DashboardUI) getUI().getCurrent()).getfSubareaJpaService());
		setfSalesmanJpaService(((DashboardUI) getUI().getCurrent()).getfSalesmanJpaService());
		
		setLapSJPenagihanListJpaService(((DashboardUI) getUI().getCurrent()).getLapSJPenagihanListJpaService());
		
		beanItemContainerDivision.addAll(fDivisionJpaService.findAll());
		
//		tableBeanItemContainer.addNestedContainerProperty("id.tipefaktur");
	};

	public void setFreshDataTable(){		
		try{
			tableBeanItemContainer.removeAllItems();
			tableBeanItemContainer.removeAllContainerFilters();
			
//			tableBeanItemContainer.addAll(arInvoiceService.findAll());
			
			setFilterDefaultBeanItemContainer();

			
			//COMBOBOX DIVISION
			beanItemContainerDivision.addAll(fDivisionJpaService.findAll());

			beanItemContainerArea.addAll(fAreaJpaService.findAll());
			beanItemContainerSubArea.addAll(fSubareaJpaService.findAll());
			beanItemContainerSalesman.addAll(fSalesmanJpaService.findAll());
			
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

	
	public LapSJPenagihanListJpaService getLapSJPenagihanListJpaService() {
		return lapSJPenagihanListJpaService;
	}

	public void setLapSJPenagihanListJpaService(
			LapSJPenagihanListJpaService lapSJPenagihanListJpaService) {
		this.lapSJPenagihanListJpaService = lapSJPenagihanListJpaService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static String getPersistenceunit() {
		return persistenceUnit;
	}

	public FtSalesh getItem() {
		return item;
	}

	public BeanItem<FtSalesh> getFormBeanItem() {
		return formBeanItem;
	}

	public BeanItemContainer<FtSalesh> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public FtSaleshJpaService getFtSaleshJpaService() {
		return ftSaleshJpaService;
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

	public void setItem(FtSalesh item) {
		this.item = item;
	}

	public void setFormBeanItem(BeanItem<FtSalesh> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<FtSalesh> tableBeanItemContainer) {
		this.tableBeanItemContainer = tableBeanItemContainer;
	}

	public void setFtSaleshJpaService(FtSaleshJpaService ftSaleshJpaService) {
		this.ftSaleshJpaService = ftSaleshJpaService;
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

	public FtSaleshRekapTampunganJpaService getFtSaleshRekapTampunganJpaService() {
		return FtSaleshRekapTampunganJpaService;
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

	public FAreaJpaService getfAreaJpaService() {
		return fAreaJpaService;
	}

	public FSubareaJpaService getfSubareaJpaService() {
		return fSubareaJpaService;
	}

	public FSalesmanJpaService getfSalesmanJpaService() {
		return fSalesmanJpaService;
	}

	public BeanItemContainer<FArea> getBeanItemContainerArea() {
		return beanItemContainerArea;
	}

	public BeanItemContainer<FSubarea> getBeanItemContainerSubArea() {
		return beanItemContainerSubArea;
	}

	public BeanItemContainer<FSalesman> getBeanItemContainerSalesman() {
		return beanItemContainerSalesman;
	}

	public void setfAreaJpaService(FAreaJpaService fAreaJpaService) {
		this.fAreaJpaService = fAreaJpaService;
	}

	public void setfSubareaJpaService(FSubareaJpaService fSubareaJpaService) {
		this.fSubareaJpaService = fSubareaJpaService;
	}

	public void setfSalesmanJpaService(FSalesmanJpaService fSalesmanJpaService) {
		this.fSalesmanJpaService = fSalesmanJpaService;
	}

	public void setBeanItemContainerArea(
			BeanItemContainer<FArea> beanItemContainerArea) {
		this.beanItemContainerArea = beanItemContainerArea;
	}

	public void setBeanItemContainerSubArea(
			BeanItemContainer<FSubarea> beanItemContainerSubArea) {
		this.beanItemContainerSubArea = beanItemContainerSubArea;
	}

	public void setBeanItemContainerSalesman(
			BeanItemContainer<FSalesman> beanItemContainerSalesman) {
		this.beanItemContainerSalesman = beanItemContainerSalesman;
	}


	
	
}
