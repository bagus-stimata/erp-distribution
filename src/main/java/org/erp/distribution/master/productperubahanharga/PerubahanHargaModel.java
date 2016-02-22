package org.erp.distribution.master.productperubahanharga;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtOpnamedJpaService;
import org.erp.distribution.jpaservice.FtOpnamehJpaService;
import org.erp.distribution.jpaservice.FtPricedJpaService;
import org.erp.distribution.jpaservice.FtPricehJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservicerep.LapStockOpanameJpaService;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtOpnamed;
import org.erp.distribution.model.FtOpnameh;
import org.erp.distribution.model.FtPriced;
import org.erp.distribution.model.FtPriceh;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class PerubahanHargaModel extends CustomComponent{
	
	//1. DAO SERVICE
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();

	private FtPricehJpaService ftPricehJpaService;
		private FtPricedJpaService ftPricedJpaService;
		private FVendorJpaService fVendorJpaService;
		private FWarehouseJpaService fWarehouseJpaService;
		
		private SysvarJpaService sysvarJpaService;
		private FProductJpaService fProductJpaService;
		private FStockJpaService fStockJpaService;
		
	//2. ENTITY
		protected FtPriceh itemHeader = new FtPriceh();
		
		protected FtPriced itemDetil = new FtPriced();
		protected FtPriced itemDetilBookmark = new FtPriced();
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FtPriceh> beanItemContainerHeader = 
				new BeanItemContainer<FtPriceh>(FtPriceh.class);
		private BeanItemContainer<FtPriceh> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FtPriceh>(FtPriceh.class);

		private BeanItemContainer<FtPriced> beanItemContainerDetil = 
				new BeanItemContainer<FtPriced>(FtPriced.class);

		private BeanItemContainer<FVendor> beanItemContainerVendor = 
				new BeanItemContainer<FVendor>(FVendor.class);
		private BeanItemContainer<FWarehouse> beanItemContainerWarehouseFrom = 
				new BeanItemContainer<FWarehouse>(FWarehouse.class);
		private BeanItemContainer<FWarehouse> beanItemContainerWarehouseTo = 
				new BeanItemContainer<FWarehouse>(FWarehouse.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FtPriceh> binderHeader = 
				new BeanFieldGroup<FtPriceh>(FtPriceh.class);
		private BeanFieldGroup<FtPriced> binderDetil = 
				new BeanFieldGroup<FtPriced>(FtPriced.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	
	
	public PerubahanHargaModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
//		setProductAndStockHelper((((DashboardUI) getUI().getCurrent()).getProductAndStockHelper()));
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
		
		setFtPricehJpaService((((DashboardUI) getUI().getCurrent()).getFtPricehJpaService()));
		setFtPricedJpaService((((DashboardUI) getUI().getCurrent()).getFtPricedJpaService()));
		setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		
	}
	public void initVariableData(){
		reload();				
	}
	
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(ftPricehJpaService.findAll());

//		beanItemContainerDetil.addAll(ftPurchasedJpaService.findAll());
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pcode");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pname");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.packaging");

		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pprice");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.ppriceafterppn");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pprice2");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pprice2afterppn");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.sprice");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.spriceafterppn");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.sprice2");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.sprice2afterppn");
		
		beanItemContainerVendor.addAll(fVendorJpaService.findAll());
		beanItemContainerWarehouseFrom.addAll(fWarehouseJpaService.findAllMainWareHouseActive());
		beanItemContainerWarehouseTo.addAll(fWarehouseJpaService.findAllMainWareHouseActive());
		
	}

	public FtPricehJpaService getFtPricehJpaService() {
		return ftPricehJpaService;
	}

	public FtPricedJpaService getFtPricedJpaService() {
		return ftPricedJpaService;
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

	public FProductJpaService getfProductJpaService() {
		return fProductJpaService;
	}

	public FStockJpaService getfStockJpaService() {
		return fStockJpaService;
	}

	public FtPriceh getItemHeader() {
		return itemHeader;
	}

	public FtPriced getItemDetil() {
		return itemDetil;
	}

	public FtPriced getItemDetilBookmark() {
		return itemDetilBookmark;
	}

	public BeanItemContainer<FtPriceh> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}

	public BeanItemContainer<FtPriceh> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}

	public BeanItemContainer<FtPriced> getBeanItemContainerDetil() {
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

	public BeanFieldGroup<FtPriceh> getBinderHeader() {
		return binderHeader;
	}

	public BeanFieldGroup<FtPriced> getBinderDetil() {
		return binderDetil;
	}

	public String getOperationStatus() {
		return OperationStatus;
	}

	public void setFtPricehJpaService(FtPricehJpaService ftPricehJpaService) {
		this.ftPricehJpaService = ftPricehJpaService;
	}

	public void setFtPricedJpaService(FtPricedJpaService ftPricedJpaService) {
		this.ftPricedJpaService = ftPricedJpaService;
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

	public void setfProductJpaService(FProductJpaService fProductJpaService) {
		this.fProductJpaService = fProductJpaService;
	}

	public void setfStockJpaService(FStockJpaService fStockJpaService) {
		this.fStockJpaService = fStockJpaService;
	}

	public void setItemHeader(FtPriceh itemHeader) {
		this.itemHeader = itemHeader;
	}

	public void setItemDetil(FtPriced itemDetil) {
		this.itemDetil = itemDetil;
	}

	public void setItemDetilBookmark(FtPriced itemDetilBookmark) {
		this.itemDetilBookmark = itemDetilBookmark;
	}

	public void setBeanItemContainerHeader(
			BeanItemContainer<FtPriceh> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}

	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FtPriceh> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}

	public void setBeanItemContainerDetil(
			BeanItemContainer<FtPriced> beanItemContainerDetil) {
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

	public void setBinderHeader(BeanFieldGroup<FtPriceh> binderHeader) {
		this.binderHeader = binderHeader;
	}

	public void setBinderDetil(BeanFieldGroup<FtPriced> binderDetil) {
		this.binderDetil = binderDetil;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
}
