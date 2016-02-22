package org.erp.distribution.kontrolstok.stockopname.formopname;

import java.io.Serializable;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FDivisionJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FProductgroupdivisiJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FtArpaymentdJpaService;
import org.erp.distribution.jpaservice.FtArpaymenthJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.FtSaleshRekapTampunganJpaService;
import org.erp.distribution.jpaservicerep.LapPackingListJpaService;
import org.erp.distribution.model.FDivision;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FProductgroupdivisi;
import org.erp.distribution.model.FVendor;
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

public class FormOpnameModel extends CustomComponent implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private static final String persistenceUnit = "financePU";
	
	public FProduct item = new FProduct();

//	private JPAContainer<Arinvoice> tableJpaContainer;	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();

	public BeanItem<FProduct> formBeanItem;
	private BeanItemContainer<FProduct> tableBeanItemContainer = new BeanItemContainer<FProduct>(FProduct.class);

	private BeanItemContainer<FProductgroupdivisi> beanItemContainerProductDivisi = new BeanItemContainer<FProductgroupdivisi>(FProductgroupdivisi.class);
	private BeanItemContainer<FVendor> beanItemContainerVendor = new BeanItemContainer<FVendor>(FVendor.class);
	
	private FProductJpaService fProductJpaService;; 
	
	private FProductgroupdivisiJpaService fProductgroupdivisiJpaService;
	private FVendorJpaService fVendorJpaService;
	
	private LapPackingListJpaService lapPackingListJpaService;

	
	private String operationStatus="";	
	
	//CHECK BOX BUAT TABLE PADA HEADER
	private boolean selectAllInvoice=false;
	
	private BeanItemContainer<FDivision> beanItemContainerDivision;

	
	public FormOpnameModel(){
		initData();
		setFreshDataTable();
	}
	
	public void initData(){
		
		beanItemContainerDivision = new BeanItemContainer<FDivision>(FDivision.class);
		
//		setTransaksiHelper(((DashboardUI) getUI().getCurrent()).getTransaksiHelper());
//		setProductAndStockHelper(((DashboardUI) getUI().getCurrent()).getProductAndStockHelper());
		
		setfProductJpaService(((DashboardUI) getUI().getCurrent()).getfProductJpaService());
		
		setfProductgroupdivisiJpaService(((DashboardUI) getUI().getCurrent()).getfProductgroupdivisiJpaService());
		setfVendorJpaService(((DashboardUI) getUI().getCurrent()).getfVendorJpaService());

		
//		tableBeanItemContainer.addNestedContainerProperty("id.tipefaktur");
	};

	public void setFreshDataTable(){		
		try{
			tableBeanItemContainer.removeAllItems();
			tableBeanItemContainer.removeAllContainerFilters();
			
//			tableBeanItemContainer.addAll(arInvoiceService.findAll());
			
			setFilterDefaultBeanItemContainer();

			
			//COMBOBOX DIVISION
			beanItemContainerProductDivisi.addAll(fProductgroupdivisiJpaService.findAll());
			beanItemContainerVendor.addAll(fVendorJpaService.findAll());
			
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

	public FProduct getItem() {
		return item;
	}

	public BeanItem<FProduct> getFormBeanItem() {
		return formBeanItem;
	}

	public BeanItemContainer<FProduct> getTableBeanItemContainer() {
		return tableBeanItemContainer;
	}

	public BeanItemContainer<FProductgroupdivisi> getBeanItemContainerProductDivisi() {
		return beanItemContainerProductDivisi;
	}

	public BeanItemContainer<FVendor> getBeanItemContainerVendor() {
		return beanItemContainerVendor;
	}

	public FProductJpaService getfProductJpaService() {
		return fProductJpaService;
	}

	public FProductgroupdivisiJpaService getfProductgroupdivisiJpaService() {
		return fProductgroupdivisiJpaService;
	}

	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
	}

	public LapPackingListJpaService getLapPackingListJpaService() {
		return lapPackingListJpaService;
	}

	public ProductAndStockHelper getProductAndStockHelper() {
		return productAndStockHelper;
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

	public void setItem(FProduct item) {
		this.item = item;
	}

	public void setFormBeanItem(BeanItem<FProduct> formBeanItem) {
		this.formBeanItem = formBeanItem;
	}

	public void setTableBeanItemContainer(
			BeanItemContainer<FProduct> tableBeanItemContainer) {
		this.tableBeanItemContainer = tableBeanItemContainer;
	}

	public void setBeanItemContainerProductDivisi(
			BeanItemContainer<FProductgroupdivisi> beanItemContainerProductDivisi) {
		this.beanItemContainerProductDivisi = beanItemContainerProductDivisi;
	}

	public void setBeanItemContainerVendor(
			BeanItemContainer<FVendor> beanItemContainerVendor) {
		this.beanItemContainerVendor = beanItemContainerVendor;
	}

	public void setfProductJpaService(FProductJpaService fProductJpaService) {
		this.fProductJpaService = fProductJpaService;
	}

	public void setfProductgroupdivisiJpaService(
			FProductgroupdivisiJpaService fProductgroupdivisiJpaService) {
		this.fProductgroupdivisiJpaService = fProductgroupdivisiJpaService;
	}

	public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
		this.fVendorJpaService = fVendorJpaService;
	}

	public void setLapPackingListJpaService(
			LapPackingListJpaService lapPackingListJpaService) {
		this.lapPackingListJpaService = lapPackingListJpaService;
	}

	public void setProductAndStockHelper(ProductAndStockHelper productAndStockHelper) {
		this.productAndStockHelper = productAndStockHelper;
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
