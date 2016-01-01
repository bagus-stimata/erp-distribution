package org.erp.distribution.master.systemsetting.parameterdiskonitem;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FCustomergroupJpaService;
import org.erp.distribution.jpaservice.FCustomersubgroupJpaService;
import org.erp.distribution.jpaservice.FParamDiskonItemJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FtPriceAlthJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FParamDiskonItem;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FtPriceAlth;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ParamDiskonItemModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private FParamDiskonItemJpaService fParamDiskonItemJpaService;
		private FCustomersubgroupJpaService fCustomersubgroupJpaService;
		private FVendorJpaService fVendorJpaService;
		private FProductgroupJpaService fProductgroupJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected FParamDiskonItem itemHeader = new FParamDiskonItem();
		protected FParamDiskonItem newItemHeader = new FParamDiskonItem();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FParamDiskonItem> beanItemContainerHeader = 
				new BeanItemContainer<FParamDiskonItem>(FParamDiskonItem.class);
		private BeanItemContainer<FParamDiskonItem> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FParamDiskonItem>(FParamDiskonItem.class);

		private BeanItemContainer<FVendor> beanItemContainerVendor = 
				new BeanItemContainer<FVendor>(FVendor.class);
		private BeanItemContainer<FProductgroup> beanItemContainerProductGroup = 
				new BeanItemContainer<FProductgroup>(FProductgroup.class);
		
		private BeanItemContainer<FCustomersubgroup> beanItemContainerSubgroup = 
				new BeanItemContainer<FCustomersubgroup>(FCustomersubgroup.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FParamDiskonItem> binderHeader = 
				new BeanFieldGroup<FParamDiskonItem>(FParamDiskonItem.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public ParamDiskonItemModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfParamDiskonItemJpaService((((DashboardUI) getUI().getCurrent()).getfParamDiskonItemJpaService()));
		setfCustomersubgroupJpaService((((DashboardUI) getUI().getCurrent()).getfCustomersubgroupJpaService()));
		setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
		setfProductgroupJpaService((((DashboardUI) getUI().getCurrent()).getfProductgroupJpaService()));
		
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(fParamDiskonItemJpaService.findAll());
//		beanItemContainerHeader.addNestedContainerProperty("fcustomergroupBean.id");
//		beanItemContainerHeader.addNestedContainerProperty("fcustomergroupBean.description");
		
		beanItemContainerSubgroup.addAll(fCustomersubgroupJpaService.findAll());
		beanItemContainerVendor.addAll(fVendorJpaService.findAll());
		beanItemContainerProductGroup.addAll(fProductgroupJpaService.findAll());
		
		
	}
	public FParamDiskonItemJpaService getfParamDiskonItemJpaService() {
		return fParamDiskonItemJpaService;
	}
	public FCustomersubgroupJpaService getfCustomersubgroupJpaService() {
		return fCustomersubgroupJpaService;
	}
	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
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
	public FParamDiskonItem getItemHeader() {
		return itemHeader;
	}
	public FParamDiskonItem getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FParamDiskonItem> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FParamDiskonItem> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<FVendor> getBeanItemContainerVendor() {
		return beanItemContainerVendor;
	}
	public BeanItemContainer<FCustomersubgroup> getBeanItemContainerSubgroup() {
		return beanItemContainerSubgroup;
	}
	public BeanFieldGroup<FParamDiskonItem> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setfParamDiskonItemJpaService(
			FParamDiskonItemJpaService fParamDiskonItemJpaService) {
		this.fParamDiskonItemJpaService = fParamDiskonItemJpaService;
	}
	public void setfCustomersubgroupJpaService(
			FCustomersubgroupJpaService fCustomersubgroupJpaService) {
		this.fCustomersubgroupJpaService = fCustomersubgroupJpaService;
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
	public void setItemHeader(FParamDiskonItem itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FParamDiskonItem newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FParamDiskonItem> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FParamDiskonItem> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerVendor(
			BeanItemContainer<FVendor> beanItemContainerVendor) {
		this.beanItemContainerVendor = beanItemContainerVendor;
	}
	public void setBeanItemContainerSubgroup(
			BeanItemContainer<FCustomersubgroup> beanItemContainerSubgroup) {
		this.beanItemContainerSubgroup = beanItemContainerSubgroup;
	}
	public void setBinderHeader(BeanFieldGroup<FParamDiskonItem> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	public FProductgroupJpaService getfProductgroupJpaService() {
		return fProductgroupJpaService;
	}
	public BeanItemContainer<FProductgroup> getBeanItemContainerProductGroup() {
		return beanItemContainerProductGroup;
	}
	public void setfProductgroupJpaService(
			FProductgroupJpaService fProductgroupJpaService) {
		this.fProductgroupJpaService = fProductgroupJpaService;
	}
	public void setBeanItemContainerProductGroup(
			BeanItemContainer<FProductgroup> beanItemContainerProductGroup) {
		this.beanItemContainerProductGroup = beanItemContainerProductGroup;
	}
	
	
	
	
}
