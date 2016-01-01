package org.erp.distribution.master.servicehp.steknisi;

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
import org.erp.distribution.jpaservicehp.STeknisiJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FtPriceAlth;
import org.erp.distribution.model.SCustomer;
import org.erp.distribution.model.STeknisi;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class STeknisiModel extends CustomComponent{
	
	//1. DAO SERVICE
		private STeknisiJpaService sTeknisiJpaService;
    //	private FCustomersubgroupJpaService fCustomersubgroupJpaService;
		
		private SysvarJpaService sysvarJpaService;
		private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
		
	//2. ENTITY
		protected STeknisi itemHeader = new STeknisi();
		protected STeknisi newItemHeader = new STeknisi();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<STeknisi> beanItemContainerHeader = 
				new BeanItemContainer<STeknisi>(STeknisi.class);
		private BeanItemContainer<STeknisi> beanItemContainerHeaderSearch = 
				new BeanItemContainer<STeknisi>(STeknisi.class);

//		private BeanItemContainer<FCustomersubgroup> beanItemContainerCustomergroup = 
//				new BeanItemContainer<FCustomersubgroup>(FCustomersubgroup.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<STeknisi> binderHeader = 
				new BeanFieldGroup<STeknisi>(STeknisi.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public STeknisiModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setsTeknisiJpaService((((DashboardUI) getUI().getCurrent()).getsTeknisiJpaService()));
//		setfCustomersubgroupJpaService((((DashboardUI) getUI().getCurrent()).getfCustomersubgroupJpaService()));
		
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(sTeknisiJpaService.findAll());
//		beanItemContainerHeader.addNestedContainerProperty("fcustomersubgroupBean.id");
//		beanItemContainerHeader.addNestedContainerProperty("fcustomersubgroupBean.description");
		
		
	}
	public STeknisiJpaService getsTeknisiJpaService() {
		return sTeknisiJpaService;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public STeknisi getItemHeader() {
		return itemHeader;
	}
	public STeknisi getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<STeknisi> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<STeknisi> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<STeknisi> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setsTeknisiJpaService(STeknisiJpaService sTeknisiJpaService) {
		this.sTeknisiJpaService = sTeknisiJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(STeknisi itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(STeknisi newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<STeknisi> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<STeknisi> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBinderHeader(BeanFieldGroup<STeknisi> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
	
	
}
