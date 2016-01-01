package org.erp.distribution.master.salesman;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FSalesmanJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class SalesmanModel extends CustomComponent{

	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	
	//1. DAO SERVICE
		private FSalesmanJpaService fSalesmanJpaService;
		private FVendorJpaService fVendorJpaService;
		private FAreaJpaService fAreaJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected FSalesman itemHeader = new FSalesman();
		protected FSalesman newItemHeader = new FSalesman();
		
		protected FVendor fVendorSelected = new FVendor();
		protected FArea fAreaSelected = new FArea();
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FSalesman> beanItemContainerHeader = 
				new BeanItemContainer<FSalesman>(FSalesman.class);
		private BeanItemContainer<FSalesman> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FSalesman>(FSalesman.class);

		private BeanItemContainer<FVendor> beanItemContainerVendorCovered = 
				new BeanItemContainer<FVendor>(FVendor.class);
		private BeanItemContainer<FArea> beanItemContainerAreaCovered = 
				new BeanItemContainer<FArea>(FArea.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FSalesman> binderHeader = 
				new BeanFieldGroup<FSalesman>(FSalesman.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public SalesmanModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfSalesmanJpaService((((DashboardUI) getUI().getCurrent()).getfSalesmanJpaService()));
		setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
		setfAreaJpaService((((DashboardUI) getUI().getCurrent()).getfAreaJpaService()));
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		try{
			beanItemContainerHeader.addAll(fSalesmanJpaService.findAll());
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public FSalesmanJpaService getfSalesmanJpaService() {
		return fSalesmanJpaService;
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
	public FSalesman getItemHeader() {
		return itemHeader;
	}
	public FSalesman getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FSalesman> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FSalesman> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<FSalesman> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setfSalesmanJpaService(FSalesmanJpaService fSalesmanJpaService) {
		this.fSalesmanJpaService = fSalesmanJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelperImpl transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(FSalesman itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FSalesman newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FSalesman> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FSalesman> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBinderHeader(BeanFieldGroup<FSalesman> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	public BeanItemContainer<FVendor> getBeanItemContainerVendorCovered() {
		return beanItemContainerVendorCovered;
	}
	public void setBeanItemContainerVendorCovered(
			BeanItemContainer<FVendor> beanItemContainerVendorCovered) {
		this.beanItemContainerVendorCovered = beanItemContainerVendorCovered;
	}
	public FVendor getfVendorSelected() {
		return fVendorSelected;
	}
	public void setfVendorSelected(FVendor fVendorSelected) {
		this.fVendorSelected = fVendorSelected;
	}
	public FArea getfAreaSelected() {
		return fAreaSelected;
	}
	public BeanItemContainer<FArea> getBeanItemContainerAreaCovered() {
		return beanItemContainerAreaCovered;
	}
	public void setfAreaSelected(FArea fAreaSelected) {
		this.fAreaSelected = fAreaSelected;
	}
	public void setBeanItemContainerAreaCovered(
			BeanItemContainer<FArea> beanItemContainerAreaCovered) {
		this.beanItemContainerAreaCovered = beanItemContainerAreaCovered;
	}
	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
	}
	public FAreaJpaService getfAreaJpaService() {
		return fAreaJpaService;
	}
	public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
		this.fVendorJpaService = fVendorJpaService;
	}
	public void setfAreaJpaService(FAreaJpaService fAreaJpaService) {
		this.fAreaJpaService = fAreaJpaService;
	}
	
	
	
}
