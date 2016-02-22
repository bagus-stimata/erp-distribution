package org.erp.distribution.salesorder.salesorder.packinglist;

import java.io.Serializable;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FDivisionJpaService;
import org.erp.distribution.jpaservice.FtArpaymentdJpaService;
import org.erp.distribution.jpaservice.FtArpaymenthJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.FtSaleshRekapTampunganJpaService;
import org.erp.distribution.jpaservicerep.LapPackingListJpaService;
import org.erp.distribution.model.FDivision;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Not;
import com.vaadin.ui.CustomComponent;

public class PackingListModel extends CustomComponent implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private static final String persistenceUnit = "financePU";
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();
	public FtSalesh item = new FtSalesh();

//	private JPAContainer<Arinvoice> tableJpaContainer;	
	
	public BeanItem<FtSalesh> formBeanItem;
	private BeanItemContainer<FtSalesh> tableBeanItemContainer = new BeanItemContainer<FtSalesh>(FtSalesh.class);

	private FtSaleshJpaService ftSaleshJpaService;; 
	private FDivisionJpaService fDivisionJpaService;
	
	
	private String operationStatus="";	
	
	//CHECK BOX BUAT TABLE PADA HEADER
	private boolean selectAllInvoice=false;
	
	private BeanItemContainer<FDivision> beanItemContainerDivision;

	public PackingListModel(){
		initData();
	}
	
	public void initData(){
		
		beanItemContainerDivision = new BeanItemContainer<FDivision>(FDivision.class);
		
//		setTransaksiHelper(((DashboardUI) getUI().getCurrent()).getTransaksiHelper());
		
		setFtSaleshJpaService(((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService());
		
		setfDivisionJpaService(((DashboardUI) getUI().getCurrent()).getfDivisionJpaService());
//		setProductAndStockHelper(((DashboardUI) getUI().getCurrent()).getProductAndStockHelper());
				
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
			
		} catch(Exception ex){
		
		}
	}
	
	public void setFilterDefaultBeanItemContainer(){

		Filter filter02 = new Not(new Compare.Equal("invoiceno", "")); 
		tableBeanItemContainer.addContainerFilter(filter02);
		
	}
	
	public void setFreshDataForm(){
		
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

	public void setfDivisionJpaService(FDivisionJpaService fDivisionJpaService) {
		this.fDivisionJpaService = fDivisionJpaService;
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

	public ProductAndStockHelper getProductAndStockHelper() {
		return productAndStockHelper;
	}

	public void setProductAndStockHelper(ProductAndStockHelper productAndStockHelper) {
		this.productAndStockHelper = productAndStockHelper;
	}

	
	
}
