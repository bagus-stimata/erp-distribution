package org.erp.distribution.master.principal.region;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.PRegionJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.PRegion;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class PrincipalRegionModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private PRegionJpaService pRegionJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected PRegion itemHeader = new PRegion();
		protected PRegion newItemHeader = new PRegion();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<PRegion> beanItemContainerHeader = 
				new BeanItemContainer<PRegion>(PRegion.class);
		private BeanItemContainer<PRegion> beanItemContainerHeaderSearch = 
				new BeanItemContainer<PRegion>(PRegion.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<PRegion> binderHeader = 
				new BeanFieldGroup<PRegion>(PRegion.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public PrincipalRegionModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setpRegionJpaService((((DashboardUI) getUI().getCurrent()).getpRegionJpaService()));
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(pRegionJpaService.findAll());
		
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public PRegionJpaService getpRegionJpaService() {
		return pRegionJpaService;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public PRegion getItemHeader() {
		return itemHeader;
	}
	public PRegion getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<PRegion> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<PRegion> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<PRegion> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setpRegionJpaService(PRegionJpaService pRegionJpaService) {
		this.pRegionJpaService = pRegionJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setItemHeader(PRegion itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(PRegion newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<PRegion> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<PRegion> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBinderHeader(BeanFieldGroup<PRegion> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
}
