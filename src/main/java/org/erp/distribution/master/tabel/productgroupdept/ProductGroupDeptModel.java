package org.erp.distribution.master.tabel.productgroupdept;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FProductgroupdeptJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FProductgroupdept;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ProductGroupDeptModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private FProductgroupdeptJpaService fProductgroupdeptJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected FProductgroupdept itemHeader = new FProductgroupdept();
		protected FProductgroupdept newItemHeader = new FProductgroupdept();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FProductgroupdept> beanItemContainerHeader = 
				new BeanItemContainer<FProductgroupdept>(FProductgroupdept.class);
		private BeanItemContainer<FProductgroupdept> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FProductgroupdept>(FProductgroupdept.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FProductgroupdept> binderHeader = 
				new BeanFieldGroup<FProductgroupdept>(FProductgroupdept.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public ProductGroupDeptModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
		
		setfProductgroupdeptJpaService((((DashboardUI) getUI().getCurrent()).getfProductgroupdeptJpaService()));
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(fProductgroupdeptJpaService.findAll());
		
	}
	public FProductgroupdeptJpaService getfProductgroupdeptJpaService() {
		return fProductgroupdeptJpaService;
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
	public FProductgroupdept getItemHeader() {
		return itemHeader;
	}
	public FProductgroupdept getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FProductgroupdept> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FProductgroupdept> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<FProductgroupdept> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setfProductgroupdeptJpaService(
			FProductgroupdeptJpaService fProductgroupdeptJpaService) {
		this.fProductgroupdeptJpaService = fProductgroupdeptJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelperImpl transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(FProductgroupdept itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FProductgroupdept newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FProductgroupdept> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FProductgroupdept> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBinderHeader(BeanFieldGroup<FProductgroupdept> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
	
}
