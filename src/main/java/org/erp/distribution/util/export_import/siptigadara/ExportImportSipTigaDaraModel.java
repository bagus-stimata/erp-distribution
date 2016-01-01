package org.erp.distribution.util.export_import.siptigadara;

import java.util.ArrayList;
import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FSalesmanJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesdPK;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.SysvarHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ExportImportSipTigaDaraModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();
	//1. DAO SERVICE
	private SysvarJpaService sysvarJpaService;

	private FProductJpaService fProductJpaService;
	private FWarehouseJpaService fWarehouseJpaService;
	private FProductgroupJpaService fProductgroupJpaService;
	private FStockJpaService fStockJpaService;
	private FSalesmanJpaService fSalesmanJpaService;
	
	private FCustomerJpaService fCustomerJpaService;
	
	private FtSaleshJpaService ftSaleshJpaService;
	private FtSalesdJpaService ftSalesdJpaService;
	
   	public FtSalesh itemHeader = new FtSalesh();
   	
   	public List<FtSalesd> listDetail = new ArrayList<FtSalesd>();
	
   	public SysvarHelper sysvarHelper;
   	
//2. ENTITY		
//	protected MenuAccessTemp menuAccessTemp;
	private BeanItemContainer<FSalesman> beanItemContainerSalesman = 
			new BeanItemContainer<FSalesman>(FSalesman.class);
	private BeanItemContainer<FWarehouse> beanItemContainerWarehouse = 
			new BeanItemContainer<FWarehouse>(FWarehouse.class);
	private BeanItemContainer<FProductgroup> beanItemContainerProductgroup = 
			new BeanItemContainer<FProductgroup>(FProductgroup.class);
	
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	
//5. Binder (BeanFieldGroup)
			
	//OTHERS
	protected String OperationStatus = "OPEN";
	

	
	public ExportImportSipTigaDaraModel(){
		initVariable();
		initVariableData();
		
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
		
		setfProductgroupJpaService((((DashboardUI) getUI().getCurrent()).getfProductgroupJpaService()));
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
		
		setfSalesmanJpaService((((DashboardUI) getUI().getCurrent()).getfSalesmanJpaService()));

		setfCustomerJpaService((((DashboardUI) getUI().getCurrent()).getfCustomerJpaService()));
		setFtSaleshJpaService((((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService()));
		setFtSalesdJpaService((((DashboardUI) getUI().getCurrent()).getFtSalesdJpaService()));
		
		setSysvarHelper((((DashboardUI) getUI().getCurrent()).getSysvarHelper()));
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
		beanItemContainerSalesman.addAll(fSalesmanJpaService.findAll());
		
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
	public FSalesmanJpaService getfSalesmanJpaService() {
		return fSalesmanJpaService;
	}
	public void setfSalesmanJpaService(FSalesmanJpaService fSalesmanJpaService) {
		this.fSalesmanJpaService = fSalesmanJpaService;
	}
	public ProductAndStockHelper getProductAndStockHelper() {
		return productAndStockHelper;
	}
	public void setProductAndStockHelper(ProductAndStockHelper productAndStockHelper) {
		this.productAndStockHelper = productAndStockHelper;
	}
	public FCustomerJpaService getfCustomerJpaService() {
		return fCustomerJpaService;
	}
	public FtSaleshJpaService getFtSaleshJpaService() {
		return ftSaleshJpaService;
	}
	public FtSalesdJpaService getFtSalesdJpaService() {
		return ftSalesdJpaService;
	}
	public void setfCustomerJpaService(FCustomerJpaService fCustomerJpaService) {
		this.fCustomerJpaService = fCustomerJpaService;
	}
	public void setFtSaleshJpaService(FtSaleshJpaService ftSaleshJpaService) {
		this.ftSaleshJpaService = ftSaleshJpaService;
	}
	public void setFtSalesdJpaService(FtSalesdJpaService ftSalesdJpaService) {
		this.ftSalesdJpaService = ftSalesdJpaService;
	}
	public BeanItemContainer<FSalesman> getBeanItemContainerSalesman() {
		return beanItemContainerSalesman;
	}
	public void setBeanItemContainerSalesman(
			BeanItemContainer<FSalesman> beanItemContainerSalesman) {
		this.beanItemContainerSalesman = beanItemContainerSalesman;
	}
	public FtSalesh getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(FtSalesh itemHeader) {
		this.itemHeader = itemHeader;
	}
	public List<FtSalesd> getListDetail() {
		return listDetail;
	}
	public SysvarHelper getSysvarHelper() {
		return sysvarHelper;
	}
	public void setListDetail(List<FtSalesd> listDetail) {
		this.listDetail = listDetail;
	}
	public void setSysvarHelper(SysvarHelper sysvarHelper) {
		this.sysvarHelper = sysvarHelper;
	}

	
	
}
