package org.erp.distribution.master.tabel.subarea;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class CustomerSubAreaModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private FSubareaJpaService fSubareaJpaService;
		private FAreaJpaService fAreaJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected FSubarea itemHeader = new FSubarea();
		protected FSubarea newItemHeader = new FSubarea();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FSubarea> beanItemContainerHeader = 
				new BeanItemContainer<FSubarea>(FSubarea.class);
		private BeanItemContainer<FSubarea> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FSubarea>(FSubarea.class);

		private BeanItemContainer<FArea> beanItemContainerArea = 
				new BeanItemContainer<FArea>(FArea.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FSubarea> binderHeader = 
				new BeanFieldGroup<FSubarea>(FSubarea.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public CustomerSubAreaModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfSubareaJpaService((((DashboardUI) getUI().getCurrent()).getfSubareaJpaService()));
		setfAreaJpaService((((DashboardUI) getUI().getCurrent()).getfAreaJpaService()));
		
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(fSubareaJpaService.findAll());
		beanItemContainerHeader.addNestedContainerProperty("fareaBean.id");
		beanItemContainerHeader.addNestedContainerProperty("fareaBean.description");
		
		beanItemContainerArea.addAll(fAreaJpaService.findAll());
		
	}
	public FSubareaJpaService getfSubareaJpaService() {
		return fSubareaJpaService;
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
	public FSubarea getItemHeader() {
		return itemHeader;
	}
	public FSubarea getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FSubarea> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FSubarea> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<FSubarea> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setfSubareaJpaService(FSubareaJpaService fSubareaJpaService) {
		this.fSubareaJpaService = fSubareaJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelperImpl transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(FSubarea itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FSubarea newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FSubarea> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FSubarea> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBinderHeader(BeanFieldGroup<FSubarea> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	public FAreaJpaService getfAreaJpaService() {
		return fAreaJpaService;
	}
	public void setfAreaJpaService(FAreaJpaService fAreaJpaService) {
		this.fAreaJpaService = fAreaJpaService;
	}
	public BeanItemContainer<FArea> getBeanItemContainerArea() {
		return beanItemContainerArea;
	}
	public void setBeanItemContainerArea(
			BeanItemContainer<FArea> beanItemContainerArea) {
		this.beanItemContainerArea = beanItemContainerArea;
	}
	
	
	
}
