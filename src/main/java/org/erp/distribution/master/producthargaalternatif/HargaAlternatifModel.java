package org.erp.distribution.master.producthargaalternatif;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtOpnamedJpaService;
import org.erp.distribution.jpaservice.FtOpnamehJpaService;
import org.erp.distribution.jpaservice.FtPriceAltdJpaService;
import org.erp.distribution.jpaservice.FtPriceAlthJpaService;
import org.erp.distribution.jpaservice.FtPricedJpaService;
import org.erp.distribution.jpaservice.FtPricehJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservicerep.LapStockOpanameJpaService;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtOpnamed;
import org.erp.distribution.model.FtOpnameh;
import org.erp.distribution.model.FtPriceAltd;
import org.erp.distribution.model.FtPriceAlth;
import org.erp.distribution.model.FtPriced;
import org.erp.distribution.model.FtPriceh;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class HargaAlternatifModel extends CustomComponent{
	
	//1. DAO SERVICE
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();
	
		private FtPriceAlthJpaService ftPriceAlthJpaService;
		private FtPriceAltdJpaService ftPriceAltdJpaService;
		private FVendorJpaService fVendorJpaService;
		private FWarehouseJpaService fWarehouseJpaService;
		
		private SysvarJpaService sysvarJpaService;
		private FProductJpaService fProductJpaService;
		private FStockJpaService fStockJpaService;
		
	//2. ENTITY
		protected FtPriceAlth itemHeader = new FtPriceAlth();
		
		protected FtPriceAltd itemDetil = new FtPriceAltd();
		protected FtPriceAltd itemDetilBookmark = new FtPriceAltd();
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FtPriceAlth> beanItemContainerHeader = 
				new BeanItemContainer<FtPriceAlth>(FtPriceAlth.class);
		private BeanItemContainer<FtPriceAlth> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FtPriceAlth>(FtPriceAlth.class);

		private BeanItemContainer<FtPriceAltd> beanItemContainerDetil = 
				new BeanItemContainer<FtPriceAltd>(FtPriceAltd.class);

		private BeanItemContainer<FVendor> beanItemContainerVendor = 
				new BeanItemContainer<FVendor>(FVendor.class);
		private BeanItemContainer<FWarehouse> beanItemContainerWarehouseFrom = 
				new BeanItemContainer<FWarehouse>(FWarehouse.class);
		private BeanItemContainer<FWarehouse> beanItemContainerWarehouseTo = 
				new BeanItemContainer<FWarehouse>(FWarehouse.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FtPriceAlth> binderHeader = 
				new BeanFieldGroup<FtPriceAlth>(FtPriceAlth.class);
		private BeanFieldGroup<FtPriceAltd> binderDetil = 
				new BeanFieldGroup<FtPriceAltd>(FtPriceAltd.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	
	
	public HargaAlternatifModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
//		setProductAndStockHelper((((DashboardUI) getUI().getCurrent()).getProductAndStockHelper()));
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
		
		setFtPriceAlthJpaService((((DashboardUI) getUI().getCurrent()).getFtPriceAlthJpaService()));
		setFtPriceAltdJpaService((((DashboardUI) getUI().getCurrent()).getFtPriceAltdJpaService()));
		setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		
	}
	public void initVariableData(){
		reload();				
	}
	
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(ftPriceAlthJpaService.findAll());

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

	public FtPriceAlthJpaService getFtPriceAlthJpaService() {
		return ftPriceAlthJpaService;
	}

	public FtPriceAltdJpaService getFtPriceAltdJpaService() {
		return ftPriceAltdJpaService;
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

	public FtPriceAlth getItemHeader() {
		return itemHeader;
	}

	public FtPriceAltd getItemDetil() {
		return itemDetil;
	}

	public FtPriceAltd getItemDetilBookmark() {
		return itemDetilBookmark;
	}

	public BeanItemContainer<FtPriceAlth> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}

	public BeanItemContainer<FtPriceAlth> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}

	public BeanItemContainer<FtPriceAltd> getBeanItemContainerDetil() {
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

	public BeanFieldGroup<FtPriceAlth> getBinderHeader() {
		return binderHeader;
	}

	public BeanFieldGroup<FtPriceAltd> getBinderDetil() {
		return binderDetil;
	}

	public String getOperationStatus() {
		return OperationStatus;
	}

	public void setFtPriceAlthJpaService(FtPriceAlthJpaService ftPriceAlthJpaService) {
		this.ftPriceAlthJpaService = ftPriceAlthJpaService;
	}

	public void setFtPriceAltdJpaService(FtPriceAltdJpaService ftPriceAltdJpaService) {
		this.ftPriceAltdJpaService = ftPriceAltdJpaService;
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

	public void setItemHeader(FtPriceAlth itemHeader) {
		this.itemHeader = itemHeader;
	}

	public void setItemDetil(FtPriceAltd itemDetil) {
		this.itemDetil = itemDetil;
	}

	public void setItemDetilBookmark(FtPriceAltd itemDetilBookmark) {
		this.itemDetilBookmark = itemDetilBookmark;
	}

	public void setBeanItemContainerHeader(
			BeanItemContainer<FtPriceAlth> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}

	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FtPriceAlth> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}

	public void setBeanItemContainerDetil(
			BeanItemContainer<FtPriceAltd> beanItemContainerDetil) {
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

	public void setBinderHeader(BeanFieldGroup<FtPriceAlth> binderHeader) {
		this.binderHeader = binderHeader;
	}

	public void setBinderDetil(BeanFieldGroup<FtPriceAltd> binderDetil) {
		this.binderDetil = binderDetil;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}

	
}
