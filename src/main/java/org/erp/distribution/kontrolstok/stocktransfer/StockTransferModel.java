package org.erp.distribution.kontrolstok.stocktransfer;

import java.util.ArrayList;
import java.util.Iterator;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtPurchasedJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.FtStocktransferdJpaService;
import org.erp.distribution.jpaservice.FtStocktransferhJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservicerep.LapTemplate1JpaService;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtPurchased;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtStocktransferd;
import org.erp.distribution.model.FtStocktransferh;
import org.erp.distribution.model.User;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.SysvarHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class StockTransferModel extends CustomComponent{
	
	//1. DAO SERVICE
		private FtStocktransferhJpaService ftStocktransferhJpaService;
		private FtStocktransferdJpaService ftStocktransferdJpaService;
		private FWarehouseJpaService fWarehouseJpaService;
		
		private SysvarJpaService sysvarJpaService;
		private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
		private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();

		private SysvarHelper sysvarHelper;
		private LapTemplate1JpaService lapTemplate1JpaService;
		
		
	//2. ENTITY
		protected User userActive = new User();
		
		protected FtStocktransferh itemHeader = new FtStocktransferh();
		
		protected FtStocktransferd itemDetil = new FtStocktransferd();
		protected FtStocktransferd itemDetilBookmark = new FtStocktransferd();
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FtStocktransferh> beanItemContainerHeader = 
				new BeanItemContainer<FtStocktransferh>(FtStocktransferh.class);
		private BeanItemContainer<FtStocktransferh> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FtStocktransferh>(FtStocktransferh.class);

		private BeanItemContainer<FtStocktransferd> beanItemContainerDetil = 
				new BeanItemContainer<FtStocktransferd>(FtStocktransferd.class);

		private BeanItemContainer<FWarehouse> beanItemContainerWarehouseFrom = 
				new BeanItemContainer<FWarehouse>(FWarehouse.class);
		private BeanItemContainer<FWarehouse> beanItemContainerWarehouseTo = 
				new BeanItemContainer<FWarehouse>(FWarehouse.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FtStocktransferh> binderHeader = 
				new BeanFieldGroup<FtStocktransferh>(FtStocktransferh.class);
		private BeanFieldGroup<FtStocktransferd> binderDetil = 
				new BeanFieldGroup<FtStocktransferd>(FtStocktransferd.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	
	
	public StockTransferModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
		setLapTemplate1JpaService((((DashboardUI) getUI().getCurrent()).getLapTemplate1JpaService()));
		setSysvarHelper((((DashboardUI) getUI().getCurrent()).getSysvarHelper()));
		
		setFtStocktransferhJpaService((((DashboardUI) getUI().getCurrent()).getFtStocktransferhJpaService()));
		setFtStocktransferdJpaService((((DashboardUI) getUI().getCurrent()).getFtStocktransferdJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		
		userActive = ((DashboardUI) getUI().getCurrent()).getUserActive();
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(ftStocktransferhJpaService.findAll());
		beanItemContainerHeader.addNestedContainerProperty("fwarehouseBeanFrom.id");
		beanItemContainerHeader.addNestedContainerProperty("fwarehouseBeanFrom.description");
		beanItemContainerHeader.addNestedContainerProperty("fwarehouseBeanTo.id");
		beanItemContainerHeader.addNestedContainerProperty("fwarehouseBeanTo.description");

		beanItemContainerHeader.sort(new String[]{"norek"}, new boolean[] {false});
//		beanItemContainerDetil.addAll(ftPurchasedJpaService.findAll());
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pcode");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pname");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.packaging");
		
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

	public FtStocktransferhJpaService getFtStocktransferhJpaService() {
		return ftStocktransferhJpaService;
	}

	public FtStocktransferdJpaService getFtStocktransferdJpaService() {
		return ftStocktransferdJpaService;
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

	public FtStocktransferh getItemHeader() {
		return itemHeader;
	}

	public FtStocktransferd getItemDetil() {
		return itemDetil;
	}

	public FtStocktransferd getItemDetilBookmark() {
		return itemDetilBookmark;
	}

	public BeanItemContainer<FtStocktransferh> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}

	public BeanItemContainer<FtStocktransferh> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}

	public BeanItemContainer<FtStocktransferd> getBeanItemContainerDetil() {
		return beanItemContainerDetil;
	}


	public BeanItemContainer<FWarehouse> getBeanItemContainerWarehouseFrom() {
		return beanItemContainerWarehouseFrom;
	}

	public BeanItemContainer<FWarehouse> getBeanItemContainerWarehouseTo() {
		return beanItemContainerWarehouseTo;
	}

	public BeanFieldGroup<FtStocktransferh> getBinderHeader() {
		return binderHeader;
	}

	public BeanFieldGroup<FtStocktransferd> getBinderDetil() {
		return binderDetil;
	}

	public String getOperationStatus() {
		return OperationStatus;
	}

	public void setFtStocktransferhJpaService(
			FtStocktransferhJpaService ftStocktransferhJpaService) {
		this.ftStocktransferhJpaService = ftStocktransferhJpaService;
	}

	public void setFtStocktransferdJpaService(
			FtStocktransferdJpaService ftStocktransferdJpaService) {
		this.ftStocktransferdJpaService = ftStocktransferdJpaService;
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

	public void setItemHeader(FtStocktransferh itemHeader) {
		this.itemHeader = itemHeader;
	}

	public void setItemDetil(FtStocktransferd itemDetil) {
		this.itemDetil = itemDetil;
	}

	public void setItemDetilBookmark(FtStocktransferd itemDetilBookmark) {
		this.itemDetilBookmark = itemDetilBookmark;
	}

	public void setBeanItemContainerHeader(
			BeanItemContainer<FtStocktransferh> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}

	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FtStocktransferh> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}

	public void setBeanItemContainerDetil(
			BeanItemContainer<FtStocktransferd> beanItemContainerDetil) {
		this.beanItemContainerDetil = beanItemContainerDetil;
	}

	public void setBeanItemContainerWarehouseFrom(
			BeanItemContainer<FWarehouse> beanItemContainerWarehouseFrom) {
		this.beanItemContainerWarehouseFrom = beanItemContainerWarehouseFrom;
	}

	public void setBeanItemContainerWarehouseTo(
			BeanItemContainer<FWarehouse> beanItemContainerWarehouseTo) {
		this.beanItemContainerWarehouseTo = beanItemContainerWarehouseTo;
	}

	public void setBinderHeader(BeanFieldGroup<FtStocktransferh> binderHeader) {
		this.binderHeader = binderHeader;
	}

	public void setBinderDetil(BeanFieldGroup<FtStocktransferd> binderDetil) {
		this.binderDetil = binderDetil;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}

	public LapTemplate1JpaService getLapTemplate1JpaService() {
		return lapTemplate1JpaService;
	}

	public User getUserActive() {
		return userActive;
	}

	public void setLapTemplate1JpaService(
			LapTemplate1JpaService lapTemplate1JpaService) {
		this.lapTemplate1JpaService = lapTemplate1JpaService;
	}

	public void setUserActive(User userActive) {
		this.userActive = userActive;
	}

	public SysvarHelper getSysvarHelper() {
		return sysvarHelper;
	}

	public void setSysvarHelper(SysvarHelper sysvarHelper) {
		this.sysvarHelper = sysvarHelper;
	}

	
	
	
}
