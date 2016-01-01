package org.erp.distribution.kontrolstok.stockopname;

import java.util.ArrayList;
import java.util.Iterator;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtOpnamedJpaService;
import org.erp.distribution.jpaservice.FtOpnamehJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservicerep.LapStockOpanameJpaService;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtOpnamed;
import org.erp.distribution.model.FtOpnameh;
import org.erp.distribution.model.User;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class StockOpnameModel extends CustomComponent{
	
	//1. DAO SERVICE
	private SysvarJpaService sysvarJpaService;
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();
	

	private FtOpnamehJpaService ftOpnamehJpaService;
		private FtOpnamedJpaService ftOpnamedJpaService;
		private FVendorJpaService fVendorJpaService;
		private FWarehouseJpaService fWarehouseJpaService;
		private LapStockOpanameJpaService lapStockOpanameJpaService;
		
		private FProductJpaService fProductJpaService;
		private FStockJpaService fStockJpaService;
		
	//2. ENTITY
		protected User userActive = new User();
		
		protected FtOpnameh itemHeader = new FtOpnameh();
		
		protected FtOpnamed itemDetil = new FtOpnamed();
		protected FtOpnamed itemDetilBookmark = new FtOpnamed();
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FtOpnameh> beanItemContainerHeader = 
				new BeanItemContainer<FtOpnameh>(FtOpnameh.class);
		private BeanItemContainer<FtOpnameh> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FtOpnameh>(FtOpnameh.class);

		private BeanItemContainer<FtOpnamed> beanItemContainerDetil = 
				new BeanItemContainer<FtOpnamed>(FtOpnamed.class);

		private BeanItemContainer<FVendor> beanItemContainerVendor = 
				new BeanItemContainer<FVendor>(FVendor.class);
		private BeanItemContainer<FWarehouse> beanItemContainerWarehouseFrom = 
				new BeanItemContainer<FWarehouse>(FWarehouse.class);
		private BeanItemContainer<FWarehouse> beanItemContainerWarehouseTo = 
				new BeanItemContainer<FWarehouse>(FWarehouse.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FtOpnameh> binderHeader = 
				new BeanFieldGroup<FtOpnameh>(FtOpnameh.class);
		private BeanFieldGroup<FtOpnamed> binderDetil = 
				new BeanFieldGroup<FtOpnamed>(FtOpnamed.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	
	
	public StockOpnameModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
//		setProductAndStockHelper((((DashboardUI) getUI().getCurrent()).getProductAndStockHelper()));
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
		setLapStockOpanameJpaService((((DashboardUI) getUI().getCurrent()).getLapStockOpanameJpaService()));
		
		setFtOpnamehJpaService((((DashboardUI) getUI().getCurrent()).getFtOpnamehJpaService()));
		setFtOpnamedJpaService((((DashboardUI) getUI().getCurrent()).getFtOpnamedJpaService()));
		setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		
		userActive = ((DashboardUI) getUI().getCurrent()).getUserActive();
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(ftOpnamehJpaService.findAll());
		beanItemContainerHeader.addNestedContainerProperty("fwarehouseBean.id");
		beanItemContainerHeader.addNestedContainerProperty("fwarehouseBean.description");

		beanItemContainerHeader.sort(new String[]{"norek"}, new boolean[] {false});

//		beanItemContainerDetil.addAll(ftPurchasedJpaService.findAll());
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pcode");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pname");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.packaging");
		
		beanItemContainerVendor.addAll(fVendorJpaService.findAll());

		
		//USER DENGAN DIVISI HANYA BISA WAREHOUSENYA SAJA
		if (userActive.getFdivisionBean()==null){
			beanItemContainerWarehouseFrom.addAll(fWarehouseJpaService.findAllMainWareHouseActive());
			beanItemContainerWarehouseTo.addAll(fWarehouseJpaService.findAllMainWareHouseActive());
		} else {
			Iterator<FWarehouse> iterFWarehouse = new ArrayList<FWarehouse>(userActive.getFdivisionBean().getFwarehouseSet()).iterator();
			while (iterFWarehouse.hasNext()) {
				FWarehouse fWarehouseBean = new FWarehouse();
				fWarehouseBean = iterFWarehouse.next();
				beanItemContainerWarehouseFrom.addBean(fWarehouseBean);
				beanItemContainerWarehouseTo.addBean(fWarehouseBean);
			}			
		}
		
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

	public FtOpnameh getItemHeader() {
		return itemHeader;
	}

	public FtOpnamed getItemDetil() {
		return itemDetil;
	}

	public FtOpnamed getItemDetilBookmark() {
		return itemDetilBookmark;
	}

	public BeanItemContainer<FtOpnameh> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}

	public BeanItemContainer<FtOpnameh> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}

	public BeanItemContainer<FtOpnamed> getBeanItemContainerDetil() {
		return beanItemContainerDetil;
	}

	public BeanItemContainer<FVendor> getBeanItemContainerVendor() {
		return beanItemContainerVendor;
	}

	public BeanItemContainer<FWarehouse> getBeanItemContainerWarehouseFrom() {
		return beanItemContainerWarehouseFrom;
	}

	public BeanItemContainer<FWarehouse> getBeanItemContainerWarehouseTo() {
		return beanItemContainerWarehouseTo;
	}

	public BeanFieldGroup<FtOpnameh> getBinderHeader() {
		return binderHeader;
	}

	public BeanFieldGroup<FtOpnamed> getBinderDetil() {
		return binderDetil;
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

	public void setItemHeader(FtOpnameh itemHeader) {
		this.itemHeader = itemHeader;
	}

	public void setItemDetil(FtOpnamed itemDetil) {
		this.itemDetil = itemDetil;
	}

	public void setItemDetilBookmark(FtOpnamed itemDetilBookmark) {
		this.itemDetilBookmark = itemDetilBookmark;
	}

	public void setBeanItemContainerHeader(
			BeanItemContainer<FtOpnameh> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}

	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FtOpnameh> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}

	public void setBeanItemContainerDetil(
			BeanItemContainer<FtOpnamed> beanItemContainerDetil) {
		this.beanItemContainerDetil = beanItemContainerDetil;
	}

	public void setBeanItemContainerVendor(
			BeanItemContainer<FVendor> beanItemContainerVendor) {
		this.beanItemContainerVendor = beanItemContainerVendor;
	}

	public void setBeanItemContainerWarehouseFrom(
			BeanItemContainer<FWarehouse> beanItemContainerWarehouseFrom) {
		this.beanItemContainerWarehouseFrom = beanItemContainerWarehouseFrom;
	}

	public void setBeanItemContainerWarehouseTo(
			BeanItemContainer<FWarehouse> beanItemContainerWarehouseTo) {
		this.beanItemContainerWarehouseTo = beanItemContainerWarehouseTo;
	}

	public void setBinderHeader(BeanFieldGroup<FtOpnameh> binderHeader) {
		this.binderHeader = binderHeader;
	}

	public void setBinderDetil(BeanFieldGroup<FtOpnamed> binderDetil) {
		this.binderDetil = binderDetil;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}

	public FProductJpaService getfProductJpaService() {
		return fProductJpaService;
	}

	public void setfProductJpaService(FProductJpaService fProductJpaService) {
		this.fProductJpaService = fProductJpaService;
	}

	public FStockJpaService getfStockJpaService() {
		return fStockJpaService;
	}

	public void setfStockJpaService(FStockJpaService fStockJpaService) {
		this.fStockJpaService = fStockJpaService;
	}

	public LapStockOpanameJpaService getLapStockOpanameJpaService() {
		return lapStockOpanameJpaService;
	}

	public void setLapStockOpanameJpaService(
			LapStockOpanameJpaService lapStockOpanameJpaService) {
		this.lapStockOpanameJpaService = lapStockOpanameJpaService;
	}

	
	
	
}
