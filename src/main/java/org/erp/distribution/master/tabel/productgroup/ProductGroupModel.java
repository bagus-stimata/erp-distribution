package org.erp.distribution.master.tabel.productgroup;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FProductgroupdeptJpaService;
import org.erp.distribution.jpaservice.FProductgroupdivisiJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FProductgroupdept;
import org.erp.distribution.model.FProductgroupdivisi;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ProductGroupModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private FProductgroupJpaService fProductgroupJpaService;
		private FProductgroupdeptJpaService fProductgroupdeptJpaService;
		private FProductgroupdivisiJpaService fProductgroupdivisiJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected FProductgroup itemHeader = new FProductgroup();
		protected FProductgroup newItemHeader = new FProductgroup();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FProductgroup> beanItemContainerHeader = 
				new BeanItemContainer<FProductgroup>(FProductgroup.class);
		private BeanItemContainer<FProductgroup> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FProductgroup>(FProductgroup.class);

		private BeanItemContainer<FProductgroupdept> beanItemContainerGroupDept = 
				new BeanItemContainer<FProductgroupdept>(FProductgroupdept.class);
		private BeanItemContainer<FProductgroupdivisi> beanItemContainerGroupDivisi = 
				new BeanItemContainer<FProductgroupdivisi>(FProductgroupdivisi.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FProductgroup> binderHeader = 
				new BeanFieldGroup<FProductgroup>(FProductgroup.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public ProductGroupModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfProductgroupJpaService((((DashboardUI) getUI().getCurrent()).getfProductgroupJpaService()));
		setfProductgroupdeptJpaService((((DashboardUI) getUI().getCurrent()).getfProductgroupdeptJpaService()));
		setfProductgroupdivisiJpaService((((DashboardUI) getUI().getCurrent()).getfProductgroupdivisiJpaService()));
		
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(fProductgroupJpaService.findAll());
		beanItemContainerHeader.addNestedContainerProperty("fproductgroupdeptBean.id");
		beanItemContainerHeader.addNestedContainerProperty("fproductgroupdeptBean.description");
		beanItemContainerHeader.addNestedContainerProperty("fproductgroupdivisiBean.id");
		beanItemContainerHeader.addNestedContainerProperty("fproductgroupdivisiBean.description");
		
		beanItemContainerGroupDept.addAll(fProductgroupdeptJpaService.findAll());
		beanItemContainerGroupDivisi.addAll(fProductgroupdivisiJpaService.findAll());
		
	}
	public FProductgroupJpaService getfProductgroupJpaService() {
		return fProductgroupJpaService;
	}
	public FProductgroupdeptJpaService getfProductgroupdeptJpaService() {
		return fProductgroupdeptJpaService;
	}
	public FProductgroupdivisiJpaService getfProductgroupdivisiJpaService() {
		return fProductgroupdivisiJpaService;
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
	public FProductgroup getItemHeader() {
		return itemHeader;
	}
	public FProductgroup getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FProductgroup> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FProductgroup> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<FProductgroupdept> getBeanItemContainerGroupDept() {
		return beanItemContainerGroupDept;
	}
	public BeanItemContainer<FProductgroupdivisi> getBeanItemContainerGroupDivisi() {
		return beanItemContainerGroupDivisi;
	}
	public BeanFieldGroup<FProductgroup> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setfProductgroupJpaService(
			FProductgroupJpaService fProductgroupJpaService) {
		this.fProductgroupJpaService = fProductgroupJpaService;
	}
	public void setfProductgroupdeptJpaService(
			FProductgroupdeptJpaService fProductgroupdeptJpaService) {
		this.fProductgroupdeptJpaService = fProductgroupdeptJpaService;
	}
	public void setfProductgroupdivisiJpaService(
			FProductgroupdivisiJpaService fProductgroupdivisiJpaService) {
		this.fProductgroupdivisiJpaService = fProductgroupdivisiJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelperImpl transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(FProductgroup itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FProductgroup newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FProductgroup> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FProductgroup> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerGroupDept(
			BeanItemContainer<FProductgroupdept> beanItemContainerGroupDept) {
		this.beanItemContainerGroupDept = beanItemContainerGroupDept;
	}
	public void setBeanItemContainerGroupDivisi(
			BeanItemContainer<FProductgroupdivisi> beanItemContainerGroupDivisi) {
		this.beanItemContainerGroupDivisi = beanItemContainerGroupDivisi;
	}
	public void setBinderHeader(BeanFieldGroup<FProductgroup> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
}
