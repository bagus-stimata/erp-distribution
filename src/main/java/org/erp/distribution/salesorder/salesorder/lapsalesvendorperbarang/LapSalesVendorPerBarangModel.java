package org.erp.distribution.salesorder.salesorder.lapsalesvendorperbarang;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FPromoJpaService2;
import org.erp.distribution.jpaservice.FSalesmanJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservicehp.STeknisiJpaService;
import org.erp.distribution.jpaservicehp.StServiceJpaService;
import org.erp.distribution.jpaservicerep.LapTemplate1JpaService;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesdPK;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.STeknisi;
import org.erp.distribution.model.ZLapTemplate1;
import org.erp.distribution.util.HeaderDetilHelper;
import org.erp.distribution.util.HeaderDetilHelperImpl;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class LapSalesVendorPerBarangModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
	private SysvarJpaService sysvarJpaService;
	private FtSaleshJpaService ftSaleshJpaService;
	private FtSalesdJpaService ftSalesdJpaService;
	private FVendorJpaService fVendorJpaService;
	private FCustomerJpaService fCustomerJpaService;
	private FProductJpaService fProductJpaService;
	
//	private LapTemplate1JpaService lapTemplate1JpaService;
	
//2. ENTITY		
//	protected MenuAccessTemp menuAccessTemp;
	private BeanItemContainer<FVendor> beanItemContainerFVendor = 
			new BeanItemContainer<FVendor>(FVendor.class);
	private BeanItemContainer<FCustomer> beanItemContainerFCustomer = 
			new BeanItemContainer<FCustomer>(FCustomer.class);
	private BeanItemContainer<FProduct> beanItemContainerFProduct = 
			new BeanItemContainer<FProduct>(FProduct.class);
	
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	
//5. Binder (BeanFieldGroup)
			
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public LapSalesVendorPerBarangModel(){
		initVariable();
		initVariableData();
		
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setFtSaleshJpaService((((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService()));
		setFtSalesdJpaService((((DashboardUI) getUI().getCurrent()).getFtSalesdJpaService()));
		setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
		setfCustomerJpaService((((DashboardUI) getUI().getCurrent()).getfCustomerJpaService()));
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		
		
	}

	public void initVariableData(){
		reload();			
	}
	
	public void reload(){
//		beanItemContainerProductgroup.removeAllContainerFilters();
//		beanItemContainerProductgroup.removeAllItems();
		beanItemContainerFVendor.addAll(fVendorJpaService.findAll());
		beanItemContainerFCustomer.addAll(fCustomerJpaService.findAll());
		
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public FtSaleshJpaService getFtSaleshJpaService() {
		return ftSaleshJpaService;
	}
	public FtSalesdJpaService getFtSalesdJpaService() {
		return ftSalesdJpaService;
	}
	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
	}
	public FCustomerJpaService getfCustomerJpaService() {
		return fCustomerJpaService;
	}
	public FProductJpaService getfProductJpaService() {
		return fProductJpaService;
	}
	public BeanItemContainer<FVendor> getBeanItemContainerFVendor() {
		return beanItemContainerFVendor;
	}
	public BeanItemContainer<FCustomer> getBeanItemContainerFCustomer() {
		return beanItemContainerFCustomer;
	}
	public BeanItemContainer<FProduct> getBeanItemContainerFProduct() {
		return beanItemContainerFProduct;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setFtSaleshJpaService(FtSaleshJpaService ftSaleshJpaService) {
		this.ftSaleshJpaService = ftSaleshJpaService;
	}
	public void setFtSalesdJpaService(FtSalesdJpaService ftSalesdJpaService) {
		this.ftSalesdJpaService = ftSalesdJpaService;
	}
	public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
		this.fVendorJpaService = fVendorJpaService;
	}
	public void setfCustomerJpaService(FCustomerJpaService fCustomerJpaService) {
		this.fCustomerJpaService = fCustomerJpaService;
	}
	public void setfProductJpaService(FProductJpaService fProductJpaService) {
		this.fProductJpaService = fProductJpaService;
	}
	public void setBeanItemContainerFVendor(
			BeanItemContainer<FVendor> beanItemContainerFVendor) {
		this.beanItemContainerFVendor = beanItemContainerFVendor;
	}
	public void setBeanItemContainerFCustomer(
			BeanItemContainer<FCustomer> beanItemContainerFCustomer) {
		this.beanItemContainerFCustomer = beanItemContainerFCustomer;
	}
	public void setBeanItemContainerFProduct(
			BeanItemContainer<FProduct> beanItemContainerFProduct) {
		this.beanItemContainerFProduct = beanItemContainerFProduct;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
		
	
}
