package org.erp.distribution.ap.lappembayaranhutang;

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
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class LapPembayaranHutangModel extends CustomComponent{
	//1. DAO SERVICE
	private SysvarJpaService sysvarJpaService;
	private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();

	private FProductJpaService fProductJpaService;
	private FWarehouseJpaService fWarehouseJpaService;
	private FVendorJpaService fVendorJpaService;
	private FStockJpaService fStockJpaService;

	private ProductAndStockHelper productAndStockHelper = new ProductAndStockHelper();
	
	
//2. ENTITY		
//	protected MenuAccessTemp menuAccessTemp;
	private BeanItemContainer<FWarehouse> beanItemContainerWarehouse = 
			new BeanItemContainer<FWarehouse>(FWarehouse.class);
	private BeanItemContainer<FVendor> beanItemContainerVendor = 
			new BeanItemContainer<FVendor>(FVendor.class);
	
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	
//5. Binder (BeanFieldGroup)
			
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public LapPembayaranHutangModel(){
		initVariable();
		initVariableData();
		
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
////		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
//		setProductAndStockHelper((((DashboardUI) getUI().getCurrent()).getProductAndStockHelper()));
		
		setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
	}

	public void initVariableData(){
		reload();			
	}
	
	public void reload(){
		beanItemContainerVendor.removeAllContainerFilters();
		beanItemContainerVendor.removeAllItems();
		beanItemContainerWarehouse.removeAllContainerFilters();
		beanItemContainerWarehouse.removeAllItems();
		
		beanItemContainerVendor.addAll(fVendorJpaService.findAll());
		beanItemContainerWarehouse.addAll(fWarehouseJpaService.findAll());
		
		
		
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
	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
	}
	public FStockJpaService getfStockJpaService() {
		return fStockJpaService;
	}
	public ProductAndStockHelper getProductAndStockHelper() {
		return productAndStockHelper;
	}
	public BeanItemContainer<FWarehouse> getBeanItemContainerWarehouse() {
		return beanItemContainerWarehouse;
	}
	public BeanItemContainer<FVendor> getBeanItemContainerVendor() {
		return beanItemContainerVendor;
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
	public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
		this.fVendorJpaService = fVendorJpaService;
	}
	public void setfStockJpaService(FStockJpaService fStockJpaService) {
		this.fStockJpaService = fStockJpaService;
	}
	public void setProductAndStockHelper(ProductAndStockHelper productAndStockHelper) {
		this.productAndStockHelper = productAndStockHelper;
	}
	public void setBeanItemContainerWarehouse(
			BeanItemContainer<FWarehouse> beanItemContainerWarehouse) {
		this.beanItemContainerWarehouse = beanItemContainerWarehouse;
	}
	public void setBeanItemContainerVendor(
			BeanItemContainer<FVendor> beanItemContainerVendor) {
		this.beanItemContainerVendor = beanItemContainerVendor;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
}
