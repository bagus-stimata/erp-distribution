package org.erp.distribution.master.vendor;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class VendorModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private FVendorJpaService fVendorJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected FVendor itemHeader = new FVendor();
		protected FVendor newItemHeader = new FVendor();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FVendor> beanItemContainerHeader = 
				new BeanItemContainer<FVendor>(FVendor.class);
		private BeanItemContainer<FVendor> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FVendor>(FVendor.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FVendor> binderHeader = 
				new BeanFieldGroup<FVendor>(FVendor.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public VendorModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(fVendorJpaService.findAll());
		
	}
	public FVendorJpaService getfVendorJpaService() {
		return fVendorJpaService;
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
	public FVendor getItemHeader() {
		return itemHeader;
	}
	public FVendor getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FVendor> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FVendor> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<FVendor> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
		this.fVendorJpaService = fVendorJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelperImpl transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(FVendor itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FVendor newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FVendor> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FVendor> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBinderHeader(BeanFieldGroup<FVendor> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	

	
	
}
