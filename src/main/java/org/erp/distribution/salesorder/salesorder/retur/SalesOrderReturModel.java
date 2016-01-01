package org.erp.distribution.salesorder.salesorder.retur;

import java.util.ArrayList;
import java.util.Iterator;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FSalesmanJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.User;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.SysvarHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class SalesOrderReturModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();
	//1. DAO SERVICE
		private FtSaleshJpaService ftSaleshJpaService;
		private FtSalesdJpaService ftSalesdJpaService;
		private FSalesmanJpaService fSalesmanJpaService;
		private FCustomerJpaService fCustomerJpaService;
		private FWarehouseJpaService fWarehouseJpaService;
		
		private SysvarJpaService sysvarJpaService;
		private SysvarHelper sysvarHelper;
		
	//2. ENTITY
		protected User userActive = new User();
		
		protected FtSalesh itemHeader = new FtSalesh();
		protected FtSalesh itemHeaderTemp = new FtSalesh();
		
		protected FtSalesd itemDetil = new FtSalesd();
		protected FtSalesd itemDetilBookmark = new FtSalesd();
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FtSalesh> beanItemContainerHeader = 
				new BeanItemContainer<FtSalesh>(FtSalesh.class);
		private BeanItemContainer<FtSalesh> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FtSalesh>(FtSalesh.class);

		private BeanItemContainer<FtSalesd> beanItemContainerDetil = 
				new BeanItemContainer<FtSalesd>(FtSalesd.class);

		private BeanItemContainer<FSalesman> beanItemContainerSalesman = 
				new BeanItemContainer<FSalesman>(FSalesman.class);
		private BeanItemContainer<FCustomer> beanItemContainerCustomer = 
				new BeanItemContainer<FCustomer>(FCustomer.class);
		private BeanItemContainer<FWarehouse> beanItemContainerWarehouse = 
				new BeanItemContainer<FWarehouse>(FWarehouse.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FtSalesh> binderHeader = 
				new BeanFieldGroup<FtSalesh>(FtSalesh.class);
		private BeanFieldGroup<FtSalesd> binderDetil = 
				new BeanFieldGroup<FtSalesd>(FtSalesd.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public SalesOrderReturModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
//		setProductAndStockHelper((((DashboardUI) getUI().getCurrent()).getProductAndStockHelper()));
		setSysvarHelper((((DashboardUI) getUI().getCurrent()).getSysvarHelper()));
		
		setFtSaleshJpaService((((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService()));
		setFtSalesdJpaService((((DashboardUI) getUI().getCurrent()).getFtSalesdJpaService()));
		setfSalesmanJpaService((((DashboardUI) getUI().getCurrent()).getfSalesmanJpaService()));
		setfCustomerJpaService((((DashboardUI) getUI().getCurrent()).getfCustomerJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		
		userActive = ((DashboardUI) getUI().getCurrent()).getUserActive();
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(ftSaleshJpaService.findAllTipeFaktur("R"));
		beanItemContainerHeader.addNestedContainerProperty("fsalesmanBean.spcode");
		beanItemContainerHeader.addNestedContainerProperty("fsalesmanBean.spname");
		beanItemContainerHeader.addNestedContainerProperty("fcustomerBean.custno");
		beanItemContainerHeader.addNestedContainerProperty("fcustomerBean.custname");
		
		beanItemContainerHeader.addNestedContainerProperty("fwarehouseBean.id");
		beanItemContainerHeader.addNestedContainerProperty("fwarehouseBean.description");

		beanItemContainerHeader.sort(new String[]{"orderno", "invoiceno"}, new boolean[] {false, false});

//		beanItemContainerDetil.addAll(ftPurchasedJpaService.findAll());
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pcode");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pname");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.packaging");
		
		beanItemContainerSalesman.addAll(fSalesmanJpaService.findAll());
		beanItemContainerCustomer.addAll(fCustomerJpaService.findAll());
		
		
		//USER DENGAN DIVISI HANYA BISA WAREHOUSENYA SAJA
		if (userActive.getFdivisionBean()==null){
			beanItemContainerWarehouse.addAll(fWarehouseJpaService.findAllMainWareHouseActive());
		} else {
			Iterator<FWarehouse> iterFWarehouse = new ArrayList<FWarehouse>(userActive.getFdivisionBean().getFwarehouseSet()).iterator();
			while (iterFWarehouse.hasNext()) {
				beanItemContainerWarehouse.addBean(iterFWarehouse.next());
			}
			
		}
		
	}

	public FtSaleshJpaService getFtSaleshJpaService() {
		return ftSaleshJpaService;
	}

	public FtSalesdJpaService getFtSalesdJpaService() {
		return ftSalesdJpaService;
	}

	public FSalesmanJpaService getfSalesmanJpaService() {
		return fSalesmanJpaService;
	}

	public FCustomerJpaService getfCustomerJpaService() {
		return fCustomerJpaService;
	}

	public FWarehouseJpaService getfWarehouseJpaService() {
		return fWarehouseJpaService;
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

	public FtSalesh getItemHeader() {
		return itemHeader;
	}

	public FtSalesd getItemDetil() {
		return itemDetil;
	}

	public FtSalesd getItemDetilBookmark() {
		return itemDetilBookmark;
	}

	public BeanItemContainer<FtSalesh> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}

	public BeanItemContainer<FtSalesh> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}

	public BeanItemContainer<FtSalesd> getBeanItemContainerDetil() {
		return beanItemContainerDetil;
	}

	public BeanItemContainer<FSalesman> getBeanItemContainerSalesman() {
		return beanItemContainerSalesman;
	}

	public BeanItemContainer<FCustomer> getBeanItemContainerCustomer() {
		return beanItemContainerCustomer;
	}

	public BeanItemContainer<FWarehouse> getBeanItemContainerWarehouse() {
		return beanItemContainerWarehouse;
	}

	public BeanFieldGroup<FtSalesh> getBinderHeader() {
		return binderHeader;
	}

	public BeanFieldGroup<FtSalesd> getBinderDetil() {
		return binderDetil;
	}

	public String getOperationStatus() {
		return OperationStatus;
	}

	public void setFtSaleshJpaService(FtSaleshJpaService ftSaleshJpaService) {
		this.ftSaleshJpaService = ftSaleshJpaService;
	}

	public void setFtSalesdJpaService(FtSalesdJpaService ftSalesdJpaService) {
		this.ftSalesdJpaService = ftSalesdJpaService;
	}

	public void setfSalesmanJpaService(FSalesmanJpaService fSalesmanJpaService) {
		this.fSalesmanJpaService = fSalesmanJpaService;
	}

	public void setfCustomerJpaService(FCustomerJpaService fCustomerJpaService) {
		this.fCustomerJpaService = fCustomerJpaService;
	}

	public void setfWarehouseJpaService(FWarehouseJpaService fWarehouseJpaService) {
		this.fWarehouseJpaService = fWarehouseJpaService;
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

	public void setItemHeader(FtSalesh itemHeader) {
		this.itemHeader = itemHeader;
	}

	public void setItemDetil(FtSalesd itemDetil) {
		this.itemDetil = itemDetil;
	}

	public void setItemDetilBookmark(FtSalesd itemDetilBookmark) {
		this.itemDetilBookmark = itemDetilBookmark;
	}

	public void setBeanItemContainerHeader(
			BeanItemContainer<FtSalesh> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}

	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FtSalesh> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}

	public void setBeanItemContainerDetil(
			BeanItemContainer<FtSalesd> beanItemContainerDetil) {
		this.beanItemContainerDetil = beanItemContainerDetil;
	}

	public void setBeanItemContainerSalesman(
			BeanItemContainer<FSalesman> beanItemContainerSalesman) {
		this.beanItemContainerSalesman = beanItemContainerSalesman;
	}

	public void setBeanItemContainerCustomer(
			BeanItemContainer<FCustomer> beanItemContainerCustomer) {
		this.beanItemContainerCustomer = beanItemContainerCustomer;
	}

	public void setBeanItemContainerWarehouse(
			BeanItemContainer<FWarehouse> beanItemContainerWarehouse) {
		this.beanItemContainerWarehouse = beanItemContainerWarehouse;
	}

	public void setBinderHeader(BeanFieldGroup<FtSalesh> binderHeader) {
		this.binderHeader = binderHeader;
	}

	public void setBinderDetil(BeanFieldGroup<FtSalesd> binderDetil) {
		this.binderDetil = binderDetil;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}

	public SysvarHelper getSysvarHelper() {
		return sysvarHelper;
	}

	public FtSalesh getItemHeaderTemp() {
		return itemHeaderTemp;
	}

	public void setSysvarHelper(SysvarHelper sysvarHelper) {
		this.sysvarHelper = sysvarHelper;
	}

	public void setItemHeaderTemp(FtSalesh itemHeaderTemp) {
		this.itemHeaderTemp = itemHeaderTemp;
	}

	
	
	
}
