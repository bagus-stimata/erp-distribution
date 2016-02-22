package org.erp.distribution.kontrolstok.lapstockopname;

import java.util.Date;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservicerep.LapMutasiStockJpaService;
import org.erp.distribution.jpaservicerep.LapStockOpanameJpaService;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
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

public class LapStockOpnameModel extends CustomComponent{
	//1. DAO SERVICE
	private SysvarJpaService sysvarJpaService;

	private FProductJpaService fProductJpaService;
	private FWarehouseJpaService fWarehouseJpaService;
	private FProductgroupJpaService fProductgroupJpaService;
	private FStockJpaService fStockJpaService;
	private LapMutasiStockJpaService lapMutasiStockJpaService;
//	private LapStockOpanameJpaService lapStockOpanameJpaService;
	
	private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper =new ProductAndStockHelper();
	
//2. ENTITY		
//	protected MenuAccessTemp menuAccessTemp;
	private BeanItemContainer<FWarehouse> beanItemContainerWarehouse = 
			new BeanItemContainer<FWarehouse>(FWarehouse.class);
	private BeanItemContainer<FProductgroup> beanItemContainerProductgroup = 
			new BeanItemContainer<FProductgroup>(FProductgroup.class);
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	
//5. Binder (BeanFieldGroup)
			
	//PARAMTER YANG DILEWATKAN DARI FORM LAIN
	private String strParamNorekFromOther = null;
	private String strParamWarehouseFromOther = null;
	private Date dateParamTrdateFromOther = null;
	
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public LapStockOpnameModel(){
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
		
//		setLapMutasiStockJpaService((((DashboardUI) getUI().getCurrent()).getLapMutasiStockJpaService()));
//		setLapStockOpanameJpaService((((DashboardUI) getUI().getCurrent()).getLapStockOpanameJpaService()));
		
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
	public String getStrParamNorekFromOther() {
		return strParamNorekFromOther;
	}
	public String getStrParamWarehouseFromOther() {
		return strParamWarehouseFromOther;
	}
	public Date getDateParamTrdateFromOther() {
		return dateParamTrdateFromOther;
	}
	public void setStrParamNorekFromOther(String strParamNorekFromOther) {
		this.strParamNorekFromOther = strParamNorekFromOther;
	}
	public void setStrParamWarehouseFromOther(String strParamWarehouseFromOther) {
		this.strParamWarehouseFromOther = strParamWarehouseFromOther;
	}
	public void setDateParamTrdateFromOther(Date dateParamTrdateFromOther) {
		this.dateParamTrdateFromOther = dateParamTrdateFromOther;
	}

	
	
}
