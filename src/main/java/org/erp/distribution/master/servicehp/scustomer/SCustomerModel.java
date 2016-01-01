package org.erp.distribution.master.servicehp.scustomer;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FCustomergroupJpaService;
import org.erp.distribution.jpaservice.FCustomersubgroupJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.FtPriceAltdJpaService;
import org.erp.distribution.jpaservice.FtPriceAlthJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservicehp.SCustomerJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FtPriceAlth;
import org.erp.distribution.model.SCustomer;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class SCustomerModel extends CustomComponent{
	
	//1. DAO SERVICE
		private SCustomerJpaService sCustomerJpaService;
		
		private SysvarJpaService sysvarJpaService;
		private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
		
	//2. ENTITY
		protected SCustomer itemHeader = new SCustomer();
		protected SCustomer newItemHeader = new SCustomer();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<SCustomer> beanItemContainerHeader = 
				new BeanItemContainer<SCustomer>(SCustomer.class);
		private BeanItemContainer<SCustomer> beanItemContainerHeaderSearch = 
				new BeanItemContainer<SCustomer>(SCustomer.class);

//		private BeanItemContainer<FCustomersubgroup> beanItemContainerCustomergroup = 
//				new BeanItemContainer<FCustomersubgroup>(FCustomersubgroup.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<SCustomer> binderHeader = 
				new BeanFieldGroup<SCustomer>(SCustomer.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public SCustomerModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setsCustomerJpaService((((DashboardUI) getUI().getCurrent()).getsCustomerJpaService()));
//		setfCustomersubgroupJpaService((((DashboardUI) getUI().getCurrent()).getfCustomersubgroupJpaService()));
		
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(sCustomerJpaService.findAll());
//		beanItemContainerHeader.addNestedContainerProperty("fcustomersubgroupBean.id");
//		beanItemContainerHeader.addNestedContainerProperty("fcustomersubgroupBean.description");
		
		
	}
	public SCustomerJpaService getsCustomerJpaService() {
		return sCustomerJpaService;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public SCustomer getItemHeader() {
		return itemHeader;
	}
	public SCustomer getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<SCustomer> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<SCustomer> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<SCustomer> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setsCustomerJpaService(SCustomerJpaService sCustomerJpaService) {
		this.sCustomerJpaService = sCustomerJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(SCustomer itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(SCustomer newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<SCustomer> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<SCustomer> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBinderHeader(BeanFieldGroup<SCustomer> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
	
}
