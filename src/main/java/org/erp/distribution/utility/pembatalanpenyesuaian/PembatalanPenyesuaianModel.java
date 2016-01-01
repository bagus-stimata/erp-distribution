package org.erp.distribution.utility.pembatalanpenyesuaian;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FSalesmanJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtOpnamedJpaService;
import org.erp.distribution.jpaservice.FtOpnamehJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservicerep.LapStockOpanameJpaService;
import org.erp.distribution.kontrolstok.stockopname.StockOpnameHelper;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSalesman;
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

public class PembatalanPenyesuaianModel extends CustomComponent{

	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();
	//1. DAO SERVICE
	private FtOpnamehJpaService ftOpnamehJpaService;
	private FtOpnamedJpaService ftOpnamedJpaService;
	private FVendorJpaService fVendorJpaService;
	private LapStockOpanameJpaService lapStockOpanameJpaService;
	
	private SysvarJpaService sysvarJpaService;
	private FProductJpaService fProductJpaService;
	private FStockJpaService fStockJpaService;
	

	private FWarehouseJpaService fWarehouseJpaService;
	private FProductgroupJpaService fProductgroupJpaService;
	private FSalesmanJpaService fSalesmanJpaService;
	
//2. ENTITY		
//	protected MenuAccessTemp menuAccessTemp;
	private BeanItemContainer<FWarehouse> beanItemContainerWarehouse = 
			new BeanItemContainer<FWarehouse>(FWarehouse.class);
	private BeanItemContainer<FProductgroup> beanItemContainerProductgroup = 
			new BeanItemContainer<FProductgroup>(FProductgroup.class);
	
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	
//5. Binder (BeanFieldGroup)
			
	//OTHERS
	protected String OperationStatus = "OPEN";
	

	
	public PembatalanPenyesuaianModel(){
		initVariable();
		initVariableData();
		
	}
	public void initVariable(){
		
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
		setLapStockOpanameJpaService((((DashboardUI) getUI().getCurrent()).getLapStockOpanameJpaService()));
		
		setFtOpnamehJpaService((((DashboardUI) getUI().getCurrent()).getFtOpnamehJpaService()));
		setFtOpnamedJpaService((((DashboardUI) getUI().getCurrent()).getFtOpnamedJpaService()));
		setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		
		
		
	}

	public void initVariableData(){
		reload();			
	}
	
	public void reload(){
		
	}
	public FtOpnamehJpaService getFtOpnamehJpaService() {
		return ftOpnamehJpaService;
	}
	public FtOpnamedJpaService getFtOpnamedJpaService() {
		return ftOpnamedJpaService;
	}
	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
	}
	public LapStockOpanameJpaService getLapStockOpanameJpaService() {
		return lapStockOpanameJpaService;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public ProductAndStockHelper getProductAndStockHelper() {
		return productAndStockHelper;
	}
	public FProductJpaService getfProductJpaService() {
		return fProductJpaService;
	}
	public FStockJpaService getfStockJpaService() {
		return fStockJpaService;
	}
	public FWarehouseJpaService getfWarehouseJpaService() {
		return fWarehouseJpaService;
	}
	public FProductgroupJpaService getfProductgroupJpaService() {
		return fProductgroupJpaService;
	}
	public FSalesmanJpaService getfSalesmanJpaService() {
		return fSalesmanJpaService;
	}
	public BeanItemContainer<FWarehouse> getBeanItemContainerWarehouse() {
		return beanItemContainerWarehouse;
	}
	public BeanItemContainer<FProductgroup> getBeanItemContainerProductgroup() {
		return beanItemContainerProductgroup;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setFtOpnamehJpaService(FtOpnamehJpaService ftOpnamehJpaService) {
		this.ftOpnamehJpaService = ftOpnamehJpaService;
	}
	public void setFtOpnamedJpaService(FtOpnamedJpaService ftOpnamedJpaService) {
		this.ftOpnamedJpaService = ftOpnamedJpaService;
	}
	public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
		this.fVendorJpaService = fVendorJpaService;
	}
	public void setLapStockOpanameJpaService(
			LapStockOpanameJpaService lapStockOpanameJpaService) {
		this.lapStockOpanameJpaService = lapStockOpanameJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setProductAndStockHelper(ProductAndStockHelper productAndStockHelper) {
		this.productAndStockHelper = productAndStockHelper;
	}
	public void setfProductJpaService(FProductJpaService fProductJpaService) {
		this.fProductJpaService = fProductJpaService;
	}
	public void setfStockJpaService(FStockJpaService fStockJpaService) {
		this.fStockJpaService = fStockJpaService;
	}
	public void setfWarehouseJpaService(FWarehouseJpaService fWarehouseJpaService) {
		this.fWarehouseJpaService = fWarehouseJpaService;
	}
	public void setfProductgroupJpaService(
			FProductgroupJpaService fProductgroupJpaService) {
		this.fProductgroupJpaService = fProductgroupJpaService;
	}
	public void setfSalesmanJpaService(FSalesmanJpaService fSalesmanJpaService) {
		this.fSalesmanJpaService = fSalesmanJpaService;
	}
	public void setBeanItemContainerWarehouse(
			BeanItemContainer<FWarehouse> beanItemContainerWarehouse) {
		this.beanItemContainerWarehouse = beanItemContainerWarehouse;
	}
	public void setBeanItemContainerProductgroup(
			BeanItemContainer<FProductgroup> beanItemContainerProductgroup) {
		this.beanItemContainerProductgroup = beanItemContainerProductgroup;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
}
