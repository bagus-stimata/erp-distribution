package org.erp.distribution.kontrolstok.lapmutasisaldostok;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FProductgroupdivisiJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservicerep.LapMutasiStockJpaService;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FProductgroupdivisi;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesdPK;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.ZLapMutasiStock;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class LapMutasiSaldoStockModel extends CustomComponent{
	//1. DAO SERVICE
	private SysvarJpaService sysvarJpaService;
	private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();

	private FProductJpaService fProductJpaService;
	private FWarehouseJpaService fWarehouseJpaService;
	private FProductgroupJpaService fProductgroupJpaService;
	private FVendorJpaService fVendorJpaService;
	private FProductgroupdivisiJpaService fProductgroupdivisiJpaService;

	private FStockJpaService fStockJpaService;
	private LapMutasiStockJpaService lapMutasiStockJpaService;
	
	private ProductAndStockHelper productAndStockHelper =new ProductAndStockHelper();
	
//2. ENTITY		
//	protected MenuAccessTemp menuAccessTemp;
	private BeanItemContainer<FWarehouse> beanItemContainerWarehouse = 
			new BeanItemContainer<FWarehouse>(FWarehouse.class);
	private BeanItemContainer<FProductgroup> beanItemContainerProductgroup = 
			new BeanItemContainer<FProductgroup>(FProductgroup.class);
	private BeanItemContainer<FVendor> beanItemContainerVendor = 
			new BeanItemContainer<FVendor>(FVendor.class);
	private BeanItemContainer<FProductgroupdivisi> beanItemContainerProductDivisi = 
			new BeanItemContainer<FProductgroupdivisi>(FProductgroupdivisi.class);
	
	
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	
//5. Binder (BeanFieldGroup)
			
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public LapMutasiSaldoStockModel(){
		initVariable();
		initVariableData();
		
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
//		setProductAndStockHelper((((DashboardUI) getUI().getCurrent()).getProductAndStockHelper()));
		
		setfProductgroupJpaService((((DashboardUI) getUI().getCurrent()).getfProductgroupJpaService()));
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));

		setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
		setfProductgroupdivisiJpaService(((DashboardUI) getUI().getCurrent()).getfProductgroupdivisiJpaService());
		
		setLapMutasiStockJpaService((((DashboardUI) getUI().getCurrent()).getLapMutasiStockJpaService()));
		
	}

	public void initVariableData(){
		reload();			
	}
	
	public void reload(){
		beanItemContainerProductgroup.removeAllContainerFilters();
		beanItemContainerProductgroup.removeAllItems();
		beanItemContainerWarehouse.removeAllContainerFilters();
		beanItemContainerWarehouse.removeAllItems();
		beanItemContainerVendor.removeAllContainerFilters();
		beanItemContainerVendor.removeAllItems();
		
		beanItemContainerProductgroup.addAll(fProductgroupJpaService.findAll());
		beanItemContainerWarehouse.addAll(fWarehouseJpaService.findAll());
		
		beanItemContainerProductDivisi.addAll(fProductgroupdivisiJpaService.findAll());
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
	public ProductAndStockHelper getProductAndStockHelper() {
		return productAndStockHelper;
	}
	public void setProductAndStockHelper(ProductAndStockHelper productAndStockHelper) {
		this.productAndStockHelper = productAndStockHelper;
	}
	public LapMutasiStockJpaService getLapMutasiStockJpaService() {
		return lapMutasiStockJpaService;
	}
	public void setLapMutasiStockJpaService(
			LapMutasiStockJpaService lapMutasiStockJpaService) {
		this.lapMutasiStockJpaService = lapMutasiStockJpaService;
	}
	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
	}
	public BeanItemContainer<FVendor> getBeanItemContainerVendor() {
		return beanItemContainerVendor;
	}
	public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
		this.fVendorJpaService = fVendorJpaService;
	}
	public void setBeanItemContainerVendor(
			BeanItemContainer<FVendor> beanItemContainerVendor) {
		this.beanItemContainerVendor = beanItemContainerVendor;
	}
	public FProductgroupdivisiJpaService getfProductgroupdivisiJpaService() {
		return fProductgroupdivisiJpaService;
	}
	public BeanItemContainer<FProductgroupdivisi> getBeanItemContainerProductDivisi() {
		return beanItemContainerProductDivisi;
	}
	public void setfProductgroupdivisiJpaService(
			FProductgroupdivisiJpaService fProductgroupdivisiJpaService) {
		this.fProductgroupdivisiJpaService = fProductgroupdivisiJpaService;
	}
	public void setBeanItemContainerProductDivisi(
			BeanItemContainer<FProductgroupdivisi> beanItemContainerProductDivisi) {
		this.beanItemContainerProductDivisi = beanItemContainerProductDivisi;
	}

	
	
}
