package org.erp.distribution.salesorder.salesorder.printinvoice;

import java.io.Serializable;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FDivisionJpaService;
import org.erp.distribution.jpaservice.FParamDiskonJpaService;
import org.erp.distribution.jpaservice.FtArpaymentdJpaService;
import org.erp.distribution.jpaservice.FtArpaymenthJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.FtSaleshRekapTampunganJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FDivision;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.salesorder.salesorder.sales.SalesOrderHelper;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.SysvarHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class PrintInvoiceModel extends CustomComponent implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private static final String persistenceUnit = "financePU";
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();

	public FtSalesh item = new FtSalesh();

//	private JPAContainer<Arinvoice> tableJpaContainer;	
	
	public BeanItem<FtSalesh> formBeanItem;
	private BeanItemContainer<FtSalesh> tableBeanItemContainer = new BeanItemContainer<FtSalesh>(FtSalesh.class);

	private FtSaleshJpaService ftSaleshJpaService; 
	private FtSalesdJpaService ftSalesdJpaService; 
	private FtArpaymenthJpaService ftArpaymenthJpaService ;
	private FtArpaymentdJpaService ftArpaymentdJpaService;
	private FDivisionJpaService fDivisionJpaService;
	private FtSaleshRekapTampunganJpaService FtSaleshRekapTampunganJpaService;

	private SysvarJpaService sysvarJpaService;
	private SysvarHelper sysvarHelper;
	
	private SalesOrderHelper salesOrderHelper;
	
	private String operationStatus="";	
	
	//CHECK BOX BUAT TABLE PADA HEADER
	private boolean selectAllInvoice=false;
	
	private BeanItemContainer<FDivision> beanItemContainerDivision;

	
	public PrintInvoiceModel(){
		initData();
	}
	
	public void initData(){
		
		beanItemContainerDivision = new BeanItemContainer<FDivision>(FDivision.class);
		
		setFtSaleshJpaService(((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService());
		setFtSalesdJpaService(((DashboardUI) getUI().getCurrent()).getFtSalesdJpaService());
		setFtArpaymenthJpaService(((DashboardUI) getUI().getCurrent()).getFtArpaymenthJpaService());
		setFtArpaymentdJpaService(((DashboardUI) getUI().getCurrent()).getFtArpaymentdJpaService());
		setFtSaleshRekapTampunganJpaService(((DashboardUI) getUI().getCurrent()).getFtSaleshRekapTampunganJpaService());
		
		setfDivisionJpaService(((DashboardUI) getUI().getCurrent()).getfDivisionJpaService());

		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
//		setProductAndStockHelper((((DashboardUI) getUI().getCurrent()).getProductAndStockHelper()));
		setSysvarHelper((((DashboardUI) getUI().getCurrent()).getSysvarHelper()));
		
		salesOrderHelper = new SalesOrderHelper();
		
		beanItemContainerDivision.addAll(fDivisionJpaService.findAll());
	//BIAR RINGAN	
//		tableBeanItemContainer.addAll(ftSaleshJpaService.findAllTipeFaktur("F"));
		tableBeanItemContainer.addNestedContainerProperty("fsalesmanBean.spcode");
		tableBeanItemContainer.addNestedContainerProperty("fsalesmanBean.spname");
		tableBeanItemContainer.addNestedContainerProperty("fcustomerBean.custno");
		tableBeanItemContainer.addNestedContainerProperty("fcustomerBean.custname");
		
		tableBeanItemContainer.addNestedContainerProperty("fwarehouseBean.id");
		tableBeanItemContainer.addNestedContainerProperty("fwarehouseBean.description");
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

	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}

	public SysvarHelper getSysvarHelper() {
		return sysvarHelper;
	}

	public ProductAndStockHelper getProductAndStockHelper() {
		return productAndStockHelper;
	}

	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}

	public void setSysvarHelper(SysvarHelper sysvarHelper) {
		this.sysvarHelper = sysvarHelper;
	}

	public void setProductAndStockHelper(ProductAndStockHelper productAndStockHelper) {
		this.productAndStockHelper = productAndStockHelper;
	}

	public SalesOrderHelper getSalesOrderHelper() {
		return salesOrderHelper;
	}

	public void setSalesOrderHelper(SalesOrderHelper salesOrderHelper) {
		this.salesOrderHelper = salesOrderHelper;
	}

	public FtSalesdJpaService getFtSalesdJpaService() {
		return ftSalesdJpaService;
	}

	public void setFtSalesdJpaService(FtSalesdJpaService ftSalesdJpaService) {
		this.ftSalesdJpaService = ftSalesdJpaService;
	}



	
	
}
