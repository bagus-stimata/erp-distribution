package org.erp.distribution.purchaseorder.retur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtPurchasedJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtPurchased;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.User;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.SysvarHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class IncomingStockReturModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();
	//1. DAO SERVICE
		private FtPurchasehJpaService ftPurchasehJpaService;
		private FtPurchasedJpaService ftPurchasedJpaService;
		private FVendorJpaService fVendorJpaService;
		private FWarehouseJpaService fWarehouseJpaService;
		
		private SysvarJpaService sysvarJpaService;
		private SysvarHelper sysvarHelper;
		
	//2. ENTITY
		protected User userActive = new User();

		protected FtPurchaseh itemHeader = new FtPurchaseh();
		
		protected FtPurchased itemDetil = new FtPurchased();
		protected FtPurchased itemDetilBookmark = new FtPurchased();
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FtPurchaseh> beanItemContainerHeader = 
				new BeanItemContainer<FtPurchaseh>(FtPurchaseh.class);
		private BeanItemContainer<FtPurchaseh> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FtPurchaseh>(FtPurchaseh.class);

		private BeanItemContainer<FtPurchased> beanItemContainerDetil = 
				new BeanItemContainer<FtPurchased>(FtPurchased.class);

		private BeanItemContainer<FVendor> beanItemContainerVendor = 
				new BeanItemContainer<FVendor>(FVendor.class);
		private BeanItemContainer<FWarehouse> beanItemContainerWarehouse = 
				new BeanItemContainer<FWarehouse>(FWarehouse.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FtPurchaseh> binderHeader = 
				new BeanFieldGroup<FtPurchaseh>(FtPurchaseh.class);
		private BeanFieldGroup<FtPurchased> binderDetil = 
				new BeanFieldGroup<FtPurchased>(FtPurchased.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	
	
	public IncomingStockReturModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
//		setProductAndStockHelper((((DashboardUI) getUI().getCurrent()).getProductAndStockHelper()));
		
		setFtPurchasehJpaService((((DashboardUI) getUI().getCurrent()).getFtPurchasehJpaService()));
		setFtPurchasedJpaService((((DashboardUI) getUI().getCurrent()).getFtPurchasedJpaService()));
		setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		setSysvarHelper((((DashboardUI) getUI().getCurrent()).getSysvarHelper()));
		
		userActive = ((DashboardUI) getUI().getCurrent()).getUserActive();
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(ftPurchasehJpaService.findAllTipeFaktur("R"));
		beanItemContainerHeader.addNestedContainerProperty("fvendorBean.vcode");
		beanItemContainerHeader.addNestedContainerProperty("fvendorBean.vname");
		beanItemContainerHeader.addNestedContainerProperty("fwarehouseBean.id");
		beanItemContainerHeader.addNestedContainerProperty("fwarehouseBean.description");
		beanItemContainerHeader.sort(new String[]{"nopo", "invoiceno"}, new boolean[] {false, false});

//		beanItemContainerDetil.addAll(ftPurchasedJpaService.findAll());
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pcode");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pname");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.packaging");
		
		beanItemContainerVendor.addAll(fVendorJpaService.findAll());
		
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

	public FtPurchasehJpaService getFtPurchasehJpaService() {
		return ftPurchasehJpaService;
	}

	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
	}

	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}


	public FtPurchaseh getItemHeader() {
		return itemHeader;
	}


	public BeanItemContainer<FtPurchaseh> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}

	public BeanItemContainer<FtPurchaseh> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}


	public BeanFieldGroup<FtPurchaseh> getBinderHeader() {
		return binderHeader;
	}

	public String getOperationStatus() {
		return OperationStatus;
	}

	public void setFtPurchasehJpaService(FtPurchasehJpaService ftPurchasehJpaService) {
		this.ftPurchasehJpaService = ftPurchasehJpaService;
	}

	public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
		this.fVendorJpaService = fVendorJpaService;
	}

	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}

	public void setTransaksiHelper(TransaksiHelperImpl transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}

	public void setItemHeader(FtPurchaseh itemHeader) {
		this.itemHeader = itemHeader;
	}

	public void setBeanItemContainerHeader(
			BeanItemContainer<FtPurchaseh> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}

	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FtPurchaseh> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}

	public void setBinderHeader(BeanFieldGroup<FtPurchaseh> binderHeader) {
		this.binderHeader = binderHeader;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}

	public BeanItemContainer<FVendor> getBeanItemContainerVendor() {
		return beanItemContainerVendor;
	}

	public void setBeanItemContainerVendor(
			BeanItemContainer<FVendor> beanItemContainerVendor) {
		this.beanItemContainerVendor = beanItemContainerVendor;
	}

	public BeanItemContainer<FtPurchased> getBeanItemContainerDetil() {
		return beanItemContainerDetil;
	}

	public BeanFieldGroup<FtPurchased> getBinderDetil() {
		return binderDetil;
	}

	public void setBeanItemContainerDetil(
			BeanItemContainer<FtPurchased> beanItemContainerDetil) {
		this.beanItemContainerDetil = beanItemContainerDetil;
	}

	public void setBinderDetil(BeanFieldGroup<FtPurchased> binderDetil) {
		this.binderDetil = binderDetil;
	}

	public FtPurchasedJpaService getFtPurchasedJpaService() {
		return ftPurchasedJpaService;
	}

	public void setFtPurchasedJpaService(FtPurchasedJpaService ftPurchasedJpaService) {
		this.ftPurchasedJpaService = ftPurchasedJpaService;
	}

	public FtPurchased getItemDetil() {
		return itemDetil;
	}


	public void setItemDetil(FtPurchased itemDetil) {
		this.itemDetil = itemDetil;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}

	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}

	public ProductAndStockHelper getProductAndStockHelper() {
		return productAndStockHelper;
	}

	public void setProductAndStockHelper(ProductAndStockHelper productAndStockHelper) {
		this.productAndStockHelper = productAndStockHelper;
	}

	public BeanItemContainer<FWarehouse> getBeanItemContainerWarehouse() {
		return beanItemContainerWarehouse;
	}

	public void setBeanItemContainerWarehouse(
			BeanItemContainer<FWarehouse> beanItemContainerWarehouse) {
		this.beanItemContainerWarehouse = beanItemContainerWarehouse;
	}

	public FWarehouseJpaService getfWarehouseJpaService() {
		return fWarehouseJpaService;
	}

	public void setfWarehouseJpaService(FWarehouseJpaService fWarehouseJpaService) {
		this.fWarehouseJpaService = fWarehouseJpaService;
	}

	public SysvarHelper getSysvarHelper() {
		return sysvarHelper;
	}

	public FtPurchased getItemDetilBookmark() {
		return itemDetilBookmark;
	}

	public void setSysvarHelper(SysvarHelper sysvarHelper) {
		this.sysvarHelper = sysvarHelper;
	}

	public void setItemDetilBookmark(FtPurchased itemDetilBookmark) {
		this.itemDetilBookmark = itemDetilBookmark;
	}

	
	
	
	
}
