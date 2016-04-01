package org.erp.distribution.master.product;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ProductModel extends CustomComponent{
	
	//1. DAO SERVICE
		private FProductJpaService fProductJpaService;
		private FProductgroupJpaService fProductgroupJpaService;
		private FVendorJpaService fVendorJpaService;
		
		private SysvarJpaService sysvarJpaService;
		private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
		
	//2. ENTITY
		protected FProduct itemHeader = new FProduct();
		protected FProduct newItemHeader = new FProduct();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FProduct> beanItemContainerHeader = 
				new BeanItemContainer<FProduct>(FProduct.class);
		private BeanItemContainer<FProduct> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FProduct>(FProduct.class);

		private BeanItemContainer<FProductgroup> beanItemContainerGroup = 
				new BeanItemContainer<FProductgroup>(FProductgroup.class);
		private BeanItemContainer<FVendor> beanItemContainerSupplier = 
				new BeanItemContainer<FVendor>(FVendor.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FProduct> binderHeader = 
				new BeanFieldGroup<FProduct>(FProduct.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public ProductModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		setfProductgroupJpaService((((DashboardUI) getUI().getCurrent()).getfProductgroupJpaService()));
		setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));

		beanItemContainerHeader.addNestedContainerProperty("fvendorBean.vcode");
		beanItemContainerHeader.addNestedContainerProperty("fvendorBean.vname");
		
		beanItemContainerHeader.addNestedContainerProperty("fproductgroupBean.id");
		beanItemContainerHeader.addNestedContainerProperty("fproductgroupBean.description");
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(fProductJpaService.findAll());
		beanItemContainerHeader.addNestedContainerProperty("fproductgroupBean.id");
		beanItemContainerHeader.addNestedContainerProperty("fproductgroupBean.description");
		
		beanItemContainerGroup.addAll(fProductgroupJpaService.findAll());
		beanItemContainerSupplier.addAll(fVendorJpaService.findAll());
		
	}
	public FProductJpaService getfProductJpaService() {
		return fProductJpaService;
	}
	public FProductgroupJpaService getfProductgroupJpaService() {
		return fProductgroupJpaService;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public FProduct getItemHeader() {
		return itemHeader;
	}
	public FProduct getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FProduct> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FProduct> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<FProductgroup> getBeanItemContainerGroup() {
		return beanItemContainerGroup;
	}
	public BeanFieldGroup<FProduct> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setfProductJpaService(FProductJpaService fProductJpaService) {
		this.fProductJpaService = fProductJpaService;
	}
	public void setfProductgroupJpaService(
			FProductgroupJpaService fProductgroupJpaService) {
		this.fProductgroupJpaService = fProductgroupJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelperImpl transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(FProduct itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FProduct newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FProduct> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FProduct> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerGroup(
			BeanItemContainer<FProductgroup> beanItemContainerGroup) {
		this.beanItemContainerGroup = beanItemContainerGroup;
	}
	public void setBinderHeader(BeanFieldGroup<FProduct> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
	}
	public BeanItemContainer<FVendor> getBeanItemContainerSupplier() {
		return beanItemContainerSupplier;
	}
	public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
		this.fVendorJpaService = fVendorJpaService;
	}
	public void setBeanItemContainerSupplier(
			BeanItemContainer<FVendor> beanItemContainerSupplier) {
		this.beanItemContainerSupplier = beanItemContainerSupplier;
	}
	
	
	
	
}
