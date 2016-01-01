package org.erp.distribution.master.tabel.productgroupdivisi;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FProductgroupdeptJpaService;
import org.erp.distribution.jpaservice.FProductgroupdivisiJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FProductgroupdept;
import org.erp.distribution.model.FProductgroupdivisi;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ProductGroupDivisiModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private FProductgroupdivisiJpaService fProductgroupdivisiJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected FProductgroupdivisi itemHeader = new FProductgroupdivisi();
		protected FProductgroupdivisi newItemHeader = new FProductgroupdivisi();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FProductgroupdivisi> beanItemContainerHeader = 
				new BeanItemContainer<FProductgroupdivisi>(FProductgroupdivisi.class);
		private BeanItemContainer<FProductgroupdivisi> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FProductgroupdivisi>(FProductgroupdivisi.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FProductgroupdivisi> binderHeader = 
				new BeanFieldGroup<FProductgroupdivisi>(FProductgroupdivisi.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public ProductGroupDivisiModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfProductgroupdivisiJpaService((((DashboardUI) getUI().getCurrent()).getfProductgroupdivisiJpaService()));
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(fProductgroupdivisiJpaService.findAll());
		
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
	public FProductgroupdivisi getItemHeader() {
		return itemHeader;
	}
	public FProductgroupdivisi getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FProductgroupdivisi> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FProductgroupdivisi> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<FProductgroupdivisi> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
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
	public void setItemHeader(FProductgroupdivisi itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FProductgroupdivisi newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FProductgroupdivisi> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FProductgroupdivisi> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBinderHeader(BeanFieldGroup<FProductgroupdivisi> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
	
}
