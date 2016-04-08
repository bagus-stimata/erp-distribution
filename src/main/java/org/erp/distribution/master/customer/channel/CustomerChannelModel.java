package org.erp.distribution.master.customer.channel;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FChannelJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.PAreaJpaService;
import org.erp.distribution.jpaservice.PDistributorJpaService;
import org.erp.distribution.jpaservice.PRegionJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FChannel;
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

public class CustomerChannelModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private FChannelJpaService fChannelJpaService;
//		private PDistributorJpaService pDistributorJpaService;
		
		private SysvarJpaService sysvarJpaService;
		
	//2. ENTITY
		protected FChannel itemHeader = new FChannel();
		protected FChannel newItemHeader = new FChannel();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FChannel> beanItemContainerHeader = 
				new BeanItemContainer<FChannel>(FChannel.class);
		private BeanItemContainer<FChannel> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FChannel>(FChannel.class);

		private BeanItemContainer<PArea> beanItemContainerPArea = 
				new BeanItemContainer<PArea>(PArea.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FChannel> binderHeader = 
				new BeanFieldGroup<FChannel>(FChannel.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public CustomerChannelModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfChannelJpaService((((DashboardUI) getUI().getCurrent()).getfChannelJpaService()));
		
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(fChannelJpaService.findAll());
//		beanItemContainerHeader.addNestedContainerProperty("pareaBean.id");
//		beanItemContainerHeader.addNestedContainerProperty("pareaBean.parea");
		
//		beanItemContainerPArea.addAll(pAreaJpaService.findAll());
		
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public FChannelJpaService getfChannelJpaService() {
		return fChannelJpaService;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public FChannel getItemHeader() {
		return itemHeader;
	}
	public FChannel getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FChannel> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FChannel> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<PArea> getBeanItemContainerPArea() {
		return beanItemContainerPArea;
	}
	public BeanFieldGroup<FChannel> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setfChannelJpaService(FChannelJpaService fChannelJpaService) {
		this.fChannelJpaService = fChannelJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setItemHeader(FChannel itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FChannel newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FChannel> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FChannel> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerPArea(
			BeanItemContainer<PArea> beanItemContainerPArea) {
		this.beanItemContainerPArea = beanItemContainerPArea;
	}
	public void setBinderHeader(BeanFieldGroup<FChannel> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}

	
	
}
