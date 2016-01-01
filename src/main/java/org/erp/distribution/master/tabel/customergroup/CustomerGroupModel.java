package org.erp.distribution.master.tabel.customergroup;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FCustomergroupJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class CustomerGroupModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private FCustomergroupJpaService fCustomergroupJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected FCustomergroup itemHeader = new FCustomergroup();
		protected FCustomergroup newItemHeader = new FCustomergroup();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FCustomergroup> beanItemContainerHeader = 
				new BeanItemContainer<FCustomergroup>(FCustomergroup.class);
		private BeanItemContainer<FCustomergroup> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FCustomergroup>(FCustomergroup.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FCustomergroup> binderHeader = 
				new BeanFieldGroup<FCustomergroup>(FCustomergroup.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public CustomerGroupModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfCustomergroupJpaService((((DashboardUI) getUI().getCurrent()).getfCustomergroupJpaService()));
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(fCustomergroupJpaService.findAll());
		
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
	public FCustomergroup getItemHeader() {
		return itemHeader;
	}
	public FCustomergroup getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FCustomergroup> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FCustomergroup> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<FCustomergroup> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
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
	public void setItemHeader(FCustomergroup itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FCustomergroup newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FCustomergroup> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FCustomergroup> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBinderHeader(BeanFieldGroup<FCustomergroup> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
}
