package org.erp.distribution.master.tabel.area;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class CustomerAreaModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private FAreaJpaService fAreaJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected FArea itemHeader = new FArea();
		protected FArea newItemHeader = new FArea();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FArea> beanItemContainerHeader = 
				new BeanItemContainer<FArea>(FArea.class);
		private BeanItemContainer<FArea> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FArea>(FArea.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FArea> binderHeader = 
				new BeanFieldGroup<FArea>(FArea.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public CustomerAreaModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfAreaJpaService((((DashboardUI) getUI().getCurrent()).getfAreaJpaService()));
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(fAreaJpaService.findAll());
		
	}
	public FAreaJpaService getfAreaJpaService() {
		return fAreaJpaService;
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
	public FArea getItemHeader() {
		return itemHeader;
	}
	public FArea getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FArea> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FArea> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<FArea> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setfAreaJpaService(FAreaJpaService fAreaJpaService) {
		this.fAreaJpaService = fAreaJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelperImpl transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(FArea itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FArea newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FArea> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FArea> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBinderHeader(BeanFieldGroup<FArea> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
	
}
