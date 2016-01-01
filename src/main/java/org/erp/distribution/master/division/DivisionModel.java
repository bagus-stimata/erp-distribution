package org.erp.distribution.master.division;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FDivisionJpaService;
import org.erp.distribution.jpaservice.FSalesmanJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FDivision;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class DivisionModel extends CustomComponent{

	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	
	//1. DAO SERVICE
		private FDivisionJpaService fDivisionJpaService;
		private FWarehouseJpaService fWarehouseJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected FDivision itemHeader = new FDivision();
		protected FDivision newItemHeader = new FDivision();
		
		protected FWarehouse fWarehouseSelected = new FWarehouse();
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FDivision> beanItemContainerHeader = 
				new BeanItemContainer<FDivision>(FDivision.class);
		private BeanItemContainer<FDivision> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FDivision>(FDivision.class);

		private BeanItemContainer<FWarehouse> beanItemContainerWarehouseCovered = 
				new BeanItemContainer<FWarehouse>(FWarehouse.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FDivision> binderHeader = 
				new BeanFieldGroup<FDivision>(FDivision.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public DivisionModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfDivisionJpaService((((DashboardUI) getUI().getCurrent()).getfDivisionJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		try{
			beanItemContainerHeader.addAll(fDivisionJpaService.findAll());
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public FDivisionJpaService getfDivisionJpaService() {
		return fDivisionJpaService;
	}
	public FWarehouseJpaService getfWarehouseJpaService() {
		return fWarehouseJpaService;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public FDivision getItemHeader() {
		return itemHeader;
	}
	public FDivision getNewItemHeader() {
		return newItemHeader;
	}
	public FWarehouse getfWarehouseSelected() {
		return fWarehouseSelected;
	}
	public BeanItemContainer<FDivision> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FDivision> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<FWarehouse> getBeanItemContainerWarehouseCovered() {
		return beanItemContainerWarehouseCovered;
	}
	public BeanFieldGroup<FDivision> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setfDivisionJpaService(FDivisionJpaService fDivisionJpaService) {
		this.fDivisionJpaService = fDivisionJpaService;
	}
	public void setfWarehouseJpaService(FWarehouseJpaService fWarehouseJpaService) {
		this.fWarehouseJpaService = fWarehouseJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setItemHeader(FDivision itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FDivision newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setfWarehouseSelected(FWarehouse fWarehouseSelected) {
		this.fWarehouseSelected = fWarehouseSelected;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FDivision> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FDivision> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerWarehouseCovered(
			BeanItemContainer<FWarehouse> beanItemContainerWarehouseCovered) {
		this.beanItemContainerWarehouseCovered = beanItemContainerWarehouseCovered;
	}
	public void setBinderHeader(BeanFieldGroup<FDivision> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
	
}
