package org.erp.distribution.master.principal.area;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.PAreaJpaService;
import org.erp.distribution.jpaservice.PRegionJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.PArea;
import org.erp.distribution.model.PRegion;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class PrincipalAreaModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private PRegionJpaService pRegionJpaService;
		private PAreaJpaService pAreaJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected PArea itemHeader = new PArea();
		protected PArea newItemHeader = new PArea();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<PArea> beanItemContainerHeader = 
				new BeanItemContainer<PArea>(PArea.class);
		private BeanItemContainer<PArea> beanItemContainerHeaderSearch = 
				new BeanItemContainer<PArea>(PArea.class);

		private BeanItemContainer<PRegion> beanItemContainerPRegion = 
				new BeanItemContainer<PRegion>(PRegion.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<PArea> binderHeader = 
				new BeanFieldGroup<PArea>(PArea.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public PrincipalAreaModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setpRegionJpaService((((DashboardUI) getUI().getCurrent()).getpRegionJpaService()));
		setpAreaJpaService((((DashboardUI) getUI().getCurrent()).getpAreaJpaService()));
		
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(pAreaJpaService.findAll());
		beanItemContainerHeader.addNestedContainerProperty("pregionBean.id");
		beanItemContainerHeader.addNestedContainerProperty("pregionBean.pregion");
		
		beanItemContainerPRegion.addAll(pRegionJpaService.findAll());
		
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public PRegionJpaService getpRegionJpaService() {
		return pRegionJpaService;
	}
	public PAreaJpaService getpAreaJpaService() {
		return pAreaJpaService;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public PArea getItemHeader() {
		return itemHeader;
	}
	public PArea getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<PArea> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<PArea> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<PRegion> getBeanItemContainerPRegion() {
		return beanItemContainerPRegion;
	}
	public BeanFieldGroup<PArea> getBinderHeader() {
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
	public void setpAreaJpaService(PAreaJpaService pAreaJpaService) {
		this.pAreaJpaService = pAreaJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setItemHeader(PArea itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(PArea newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<PArea> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<PArea> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerPRegion(
			BeanItemContainer<PRegion> beanItemContainerPRegion) {
		this.beanItemContainerPRegion = beanItemContainerPRegion;
	}
	public void setBinderHeader(BeanFieldGroup<PArea> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
	
}
