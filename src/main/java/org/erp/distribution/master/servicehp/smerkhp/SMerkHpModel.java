package org.erp.distribution.master.servicehp.smerkhp;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FCustomergroupJpaService;
import org.erp.distribution.jpaservice.FCustomersubgroupJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.FtPriceAltdJpaService;
import org.erp.distribution.jpaservice.FtPriceAlthJpaService;
import org.erp.distribution.jpaservice.SMerkJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservicehp.SCustomerJpaService;
import org.erp.distribution.jpaservicehp.STeknisiJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FtPriceAlth;
import org.erp.distribution.model.SCustomer;
import org.erp.distribution.model.SMerk;
import org.erp.distribution.model.STeknisi;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class SMerkHpModel extends CustomComponent{
	
	//1. DAO SERVICE
		private SMerkJpaService sMerkJpaService;
    //	private FCustomersubgroupJpaService fCustomersubgroupJpaService;
		
		private SysvarJpaService sysvarJpaService;
		private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
		
	//2. ENTITY
		protected SMerk itemHeader = new SMerk();
		protected SMerk newItemHeader = new SMerk();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<SMerk> beanItemContainerHeader = 
				new BeanItemContainer<SMerk>(SMerk.class);
		private BeanItemContainer<SMerk> beanItemContainerHeaderSearch = 
				new BeanItemContainer<SMerk>(SMerk.class);

//		private BeanItemContainer<FCustomersubgroup> beanItemContainerCustomergroup = 
//				new BeanItemContainer<FCustomersubgroup>(FCustomersubgroup.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<SMerk> binderHeader = 
				new BeanFieldGroup<SMerk>(SMerk.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public SMerkHpModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setsMerkJpaService((((DashboardUI) getUI().getCurrent()).getsMerkJpaService()));
//		setfCustomersubgroupJpaService((((DashboardUI) getUI().getCurrent()).getfCustomersubgroupJpaService()));
		
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(sMerkJpaService.findAll());
//		beanItemContainerHeader.addNestedContainerProperty("fcustomersubgroupBean.id");
//		beanItemContainerHeader.addNestedContainerProperty("fcustomersubgroupBean.description");
		
		
	}
	public SMerkJpaService getsMerkJpaService() {
		return sMerkJpaService;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public SMerk getItemHeader() {
		return itemHeader;
	}
	public SMerk getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<SMerk> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<SMerk> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<SMerk> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setsMerkJpaService(SMerkJpaService sMerkJpaService) {
		this.sMerkJpaService = sMerkJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(SMerk itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(SMerk newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<SMerk> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<SMerk> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBinderHeader(BeanFieldGroup<SMerk> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
	
	
}
