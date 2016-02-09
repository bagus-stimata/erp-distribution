package org.erp.distribution.kontrolstok.lappricelist;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesdPK;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class LapPriceListModel extends CustomComponent{
	//1. DAO SERVICE
	private SysvarJpaService sysvarJpaService;
	private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();

	private FProductJpaService fProductJpaService;
	private FWarehouseJpaService fWarehouseJpaService;
	private FProductgroupJpaService fProductgroupJpaService;
	private FStockJpaService fStockJpaService;
	private FVendorJpaService fVendorJpaService;
	
//2. ENTITY		
//	protected MenuAccessTemp menuAccessTemp;
	private BeanItemContainer<FWarehouse> beanItemContainerWarehouse = 
			new BeanItemContainer<FWarehouse>(FWarehouse.class);
	private BeanItemContainer<FProductgroup> beanItemContainerProductgroup = 
			new BeanItemContainer<FProductgroup>(FProductgroup.class);
	private BeanItemContainer<FVendor> beanItemContainerVendor = 
			new BeanItemContainer<FVendor>(FVendor.class);
	
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	
//5. Binder (BeanFieldGroup)
			
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public LapPriceListModel(){
		initVariable();
		initVariableData();
		
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfProductgroupJpaService((((DashboardUI) getUI().getCurrent()).getfProductgroupJpaService()));
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
		setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
	}

	public void initVariableData(){
		reload();			
	}
	
	public void reload(){
		beanItemContainerProductgroup.removeAllContainerFilters();
		beanItemContainerProductgroup.removeAllItems();
		beanItemContainerWarehouse.removeAllContainerFilters();
		beanItemContainerWarehouse.removeAllItems();
		
		beanItemContainerProductgroup.addAll(fProductgroupJpaService.findAll());
		beanItemContainerWarehouse.addAll(fWarehouseJpaService.findAll());
		beanItemContainerVendor.addAll(fVendorJpaService.findAll());
		
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public FProductJpaService getfProductJpaService() {
		return fProductJpaService;
	}
	public FWarehouseJpaService getfWarehouseJpaService() {
		return fWarehouseJpaService;
	}
	public FProductgroupJpaService getfProductgroupJpaService() {
		return fProductgroupJpaService;
	}
	public FStockJpaService getfStockJpaService() {
		return fStockJpaService;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setfProductJpaService(FProductJpaService fProductJpaService) {
		this.fProductJpaService = fProductJpaService;
	}
	public void setfWarehouseJpaService(FWarehouseJpaService fWarehouseJpaService) {
		this.fWarehouseJpaService = fWarehouseJpaService;
	}
	public void setfProductgroupJpaService(
			FProductgroupJpaService fProductgroupJpaService) {
		this.fProductgroupJpaService = fProductgroupJpaService;
	}
	public void setfStockJpaService(FStockJpaService fStockJpaService) {
		this.fStockJpaService = fStockJpaService;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	public BeanItemContainer<FWarehouse> getBeanItemContainerWarehouse() {
		return beanItemContainerWarehouse;
	}
	public BeanItemContainer<FProductgroup> getBeanItemContainerProductgroup() {
		return beanItemContainerProductgroup;
	}
	public void setBeanItemContainerWarehouse(
			BeanItemContainer<FWarehouse> beanItemContainerWarehouse) {
		this.beanItemContainerWarehouse = beanItemContainerWarehouse;
	}
	public void setBeanItemContainerProductgroup(
			BeanItemContainer<FProductgroup> beanItemContainerProductgroup) {
		this.beanItemContainerProductgroup = beanItemContainerProductgroup;
	}
	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
	}
	public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
		this.fVendorJpaService = fVendorJpaService;
	}
	public BeanItemContainer<FVendor> getBeanItemContainerVendor() {
		return beanItemContainerVendor;
	}
	public void setBeanItemContainerVendor(
			BeanItemContainer<FVendor> beanItemContainerVendor) {
		this.beanItemContainerVendor = beanItemContainerVendor;
	}

	
	
}
