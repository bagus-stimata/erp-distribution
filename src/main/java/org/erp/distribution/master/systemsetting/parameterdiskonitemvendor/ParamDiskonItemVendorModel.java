package org.erp.distribution.master.systemsetting.parameterdiskonitemvendor;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FCustomergroupJpaService;
import org.erp.distribution.jpaservice.FCustomersubgroupJpaService;
import org.erp.distribution.jpaservice.FParamDiskonItemJpaService;
import org.erp.distribution.jpaservice.FParamDiskonItemVendorJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FtPriceAlthJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FParamDiskonItem;
import org.erp.distribution.model.FParamDiskonItemVendor;
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

public class ParamDiskonItemVendorModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private FParamDiskonItemVendorJpaService fParamDiskonItemVendorJpaService;
		private FCustomersubgroupJpaService fCustomersubgroupJpaService;
		private FVendorJpaService fVendorJpaService;
		private FProductgroupJpaService fProductgroupJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected FParamDiskonItemVendor itemHeader = new FParamDiskonItemVendor();
		protected FParamDiskonItemVendor newItemHeader = new FParamDiskonItemVendor();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FParamDiskonItemVendor> beanItemContainerHeader = 
				new BeanItemContainer<FParamDiskonItemVendor>(FParamDiskonItemVendor.class);
		private BeanItemContainer<FParamDiskonItemVendor> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FParamDiskonItemVendor>(FParamDiskonItemVendor.class);

		private BeanItemContainer<FVendor> beanItemContainerVendor = 
				new BeanItemContainer<FVendor>(FVendor.class);
		private BeanItemContainer<FProductgroup> beanItemContainerProductGroup = 
				new BeanItemContainer<FProductgroup>(FProductgroup.class);
		
		private BeanItemContainer<FCustomersubgroup> beanItemContainerSubgroup = 
				new BeanItemContainer<FCustomersubgroup>(FCustomersubgroup.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FParamDiskonItemVendor> binderHeader = 
				new BeanFieldGroup<FParamDiskonItemVendor>(FParamDiskonItemVendor.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public ParamDiskonItemVendorModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfParamDiskonItemVendorJpaService((((DashboardUI) getUI().getCurrent()).getfParamDiskonItemVendorJpaService()));
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
		
//		beanItemContainerHeader.addNestedContainerProperty("fcustomergroupBean.id");
//		beanItemContainerHeader.addAll(fParamDiskonItemVendorJpaService.findAll());
//		beanItemContainerHeader.addNestedContainerProperty("fcustomergroupBean.description");

		beanItemContainerHeader.addAll(fParamDiskonItemVendorJpaService.findAll());
		
		beanItemContainerSubgroup.addAll(fCustomersubgroupJpaService.findAll());
		beanItemContainerVendor.addAll(fVendorJpaService.findAll());
		beanItemContainerProductGroup.addAll(fProductgroupJpaService.findAll());
		
		
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public FParamDiskonItemVendorJpaService getfParamDiskonItemVendorJpaService() {
		return fParamDiskonItemVendorJpaService;
	}
	public FCustomersubgroupJpaService getfCustomersubgroupJpaService() {
		return fCustomersubgroupJpaService;
	}
	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
	}
	public FProductgroupJpaService getfProductgroupJpaService() {
		return fProductgroupJpaService;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public FParamDiskonItemVendor getItemHeader() {
		return itemHeader;
	}
	public BeanItemContainer<FParamDiskonItemVendor> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FParamDiskonItemVendor> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<FVendor> getBeanItemContainerVendor() {
		return beanItemContainerVendor;
	}
	public BeanItemContainer<FProductgroup> getBeanItemContainerProductGroup() {
		return beanItemContainerProductGroup;
	}
	public BeanItemContainer<FCustomersubgroup> getBeanItemContainerSubgroup() {
		return beanItemContainerSubgroup;
	}
	public BeanFieldGroup<FParamDiskonItemVendor> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setfParamDiskonItemVendorJpaService(
			FParamDiskonItemVendorJpaService fParamDiskonItemVendorJpaService) {
		this.fParamDiskonItemVendorJpaService = fParamDiskonItemVendorJpaService;
	}
	public void setfCustomersubgroupJpaService(
			FCustomersubgroupJpaService fCustomersubgroupJpaService) {
		this.fCustomersubgroupJpaService = fCustomersubgroupJpaService;
	}
	public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
		this.fVendorJpaService = fVendorJpaService;
	}
	public void setfProductgroupJpaService(
			FProductgroupJpaService fProductgroupJpaService) {
		this.fProductgroupJpaService = fProductgroupJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setItemHeader(FParamDiskonItemVendor itemHeader) {
		this.itemHeader = itemHeader;
	}
	public FParamDiskonItemVendor getNewItemHeader() {
		return newItemHeader;
	}
	public void setNewItemHeader(FParamDiskonItemVendor newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FParamDiskonItemVendor> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FParamDiskonItemVendor> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerVendor(
			BeanItemContainer<FVendor> beanItemContainerVendor) {
		this.beanItemContainerVendor = beanItemContainerVendor;
	}
	public void setBeanItemContainerProductGroup(
			BeanItemContainer<FProductgroup> beanItemContainerProductGroup) {
		this.beanItemContainerProductGroup = beanItemContainerProductGroup;
	}
	public void setBeanItemContainerSubgroup(
			BeanItemContainer<FCustomersubgroup> beanItemContainerSubgroup) {
		this.beanItemContainerSubgroup = beanItemContainerSubgroup;
	}
	public void setBinderHeader(BeanFieldGroup<FParamDiskonItemVendor> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
	
}
