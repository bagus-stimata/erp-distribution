package org.erp.distribution.master.tabel.customersubgroup;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FCustomergroupJpaService;
import org.erp.distribution.jpaservice.FCustomersubgroupJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.FtPriceAlthJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FtPriceAlth;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class CustomerSubGroupModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private FCustomersubgroupJpaService fCustomersubgroupJpaService;
		private FCustomergroupJpaService fCustomergroupJpaService;
		private FtPriceAlthJpaService ftPriceAlthJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected FCustomersubgroup itemHeader = new FCustomersubgroup();
		protected FCustomersubgroup newItemHeader = new FCustomersubgroup();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FCustomersubgroup> beanItemContainerHeader = 
				new BeanItemContainer<FCustomersubgroup>(FCustomersubgroup.class);
		private BeanItemContainer<FCustomersubgroup> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FCustomersubgroup>(FCustomersubgroup.class);

		private BeanItemContainer<FtPriceAlth> beanItemContainerFtPriceAlth = 
				new BeanItemContainer<FtPriceAlth>(FtPriceAlth.class);
		
		private BeanItemContainer<FCustomergroup> beanItemContainerGroup = 
				new BeanItemContainer<FCustomergroup>(FCustomergroup.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FCustomersubgroup> binderHeader = 
				new BeanFieldGroup<FCustomersubgroup>(FCustomersubgroup.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public CustomerSubGroupModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfCustomersubgroupJpaService((((DashboardUI) getUI().getCurrent()).getfCustomersubgroupJpaService()));
		setfCustomergroupJpaService((((DashboardUI) getUI().getCurrent()).getfCustomergroupJpaService()));
		setFtPriceAlthJpaService((((DashboardUI) getUI().getCurrent()).getFtPriceAlthJpaService()));
		
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(fCustomersubgroupJpaService.findAll());
		beanItemContainerHeader.addNestedContainerProperty("fcustomergroupBean.id");
		beanItemContainerHeader.addNestedContainerProperty("fcustomergroupBean.description");
		
		beanItemContainerGroup.addAll(fCustomergroupJpaService.findAll());
		
		try{
			beanItemContainerFtPriceAlth.addAll(ftPriceAlthJpaService.findAll());
		} catch(Exception ex){}
	}
	public FCustomersubgroupJpaService getfCustomersubgroupJpaService() {
		return fCustomersubgroupJpaService;
	}
	public FCustomergroupJpaService getfCustomergroupJpaService() {
		return fCustomergroupJpaService;
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
	public FCustomersubgroup getItemHeader() {
		return itemHeader;
	}
	public FCustomersubgroup getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FCustomersubgroup> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FCustomersubgroup> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<FCustomergroup> getBeanItemContainerGroup() {
		return beanItemContainerGroup;
	}
	public BeanFieldGroup<FCustomersubgroup> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setfCustomersubgroupJpaService(
			FCustomersubgroupJpaService fCustomersubgroupJpaService) {
		this.fCustomersubgroupJpaService = fCustomersubgroupJpaService;
	}
	public void setfCustomergroupJpaService(
			FCustomergroupJpaService fCustomergroupJpaService) {
		this.fCustomergroupJpaService = fCustomergroupJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelperImpl transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(FCustomersubgroup itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FCustomersubgroup newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FCustomersubgroup> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FCustomersubgroup> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerGroup(
			BeanItemContainer<FCustomergroup> beanItemContainerGroup) {
		this.beanItemContainerGroup = beanItemContainerGroup;
	}
	public void setBinderHeader(BeanFieldGroup<FCustomersubgroup> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	public FtPriceAlthJpaService getFtPriceAlthJpaService() {
		return ftPriceAlthJpaService;
	}
	public void setFtPriceAlthJpaService(FtPriceAlthJpaService ftPriceAlthJpaService) {
		this.ftPriceAlthJpaService = ftPriceAlthJpaService;
	}
	public BeanItemContainer<FtPriceAlth> getBeanItemContainerFtPriceAlth() {
		return beanItemContainerFtPriceAlth;
	}
	public void setBeanItemContainerFtPriceAlth(
			BeanItemContainer<FtPriceAlth> beanItemContainerFtPriceAlth) {
		this.beanItemContainerFtPriceAlth = beanItemContainerFtPriceAlth;
	}
	
	
	
	
}
