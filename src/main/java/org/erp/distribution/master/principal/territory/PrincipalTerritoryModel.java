package org.erp.distribution.master.principal.territory;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.PAreaJpaService;
import org.erp.distribution.jpaservice.PDistributorJpaService;
import org.erp.distribution.jpaservice.PRegionJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.PArea;
import org.erp.distribution.model.PDistributor;
import org.erp.distribution.model.PRegion;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class PrincipalTerritoryModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private PAreaJpaService pAreaJpaService;
		private PDistributorJpaService pDistributorJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected PDistributor itemHeader = new PDistributor();
		protected PDistributor newItemHeader = new PDistributor();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<PDistributor> beanItemContainerHeader = 
				new BeanItemContainer<PDistributor>(PDistributor.class);
		private BeanItemContainer<PDistributor> beanItemContainerHeaderSearch = 
				new BeanItemContainer<PDistributor>(PDistributor.class);

		private BeanItemContainer<PArea> beanItemContainerPArea = 
				new BeanItemContainer<PArea>(PArea.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<PDistributor> binderHeader = 
				new BeanFieldGroup<PDistributor>(PDistributor.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public PrincipalTerritoryModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setpAreaJpaService((((DashboardUI) getUI().getCurrent()).getpAreaJpaService()));
		setpDistributorJpaService((((DashboardUI) getUI().getCurrent()).getpDistributorJpaService()));
		
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(pDistributorJpaService.findAll());
		beanItemContainerHeader.addNestedContainerProperty("pareaBean.id");
		beanItemContainerHeader.addNestedContainerProperty("pareaBean.parea");
		
		beanItemContainerPArea.addAll(pAreaJpaService.findAll());
		
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public PAreaJpaService getpAreaJpaService() {
		return pAreaJpaService;
	}
	public PDistributorJpaService getpDistributorJpaService() {
		return pDistributorJpaService;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public PDistributor getItemHeader() {
		return itemHeader;
	}
	public PDistributor getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<PDistributor> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<PDistributor> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<PArea> getBeanItemContainerPArea() {
		return beanItemContainerPArea;
	}
	public BeanFieldGroup<PDistributor> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setpAreaJpaService(PAreaJpaService pAreaJpaService) {
		this.pAreaJpaService = pAreaJpaService;
	}
	public void setpDistributorJpaService(
			PDistributorJpaService pDistributorJpaService) {
		this.pDistributorJpaService = pDistributorJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setItemHeader(PDistributor itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(PDistributor newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<PDistributor> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<PDistributor> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerPArea(
			BeanItemContainer<PArea> beanItemContainerPArea) {
		this.beanItemContainerPArea = beanItemContainerPArea;
	}
	public void setBinderHeader(BeanFieldGroup<PDistributor> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
	
}
