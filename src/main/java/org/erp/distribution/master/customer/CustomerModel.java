package org.erp.distribution.master.customer;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FChannelJpaService;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FCustomergroupJpaService;
import org.erp.distribution.jpaservice.FCustomersubgroupJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.FtPriceAltdJpaService;
import org.erp.distribution.jpaservice.FtPriceAlthJpaService;
import org.erp.distribution.jpaservice.PDistributorJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FChannel;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FtPriceAlth;
import org.erp.distribution.model.PDistributor;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class CustomerModel extends CustomComponent{
	
	//1. DAO SERVICE
		private FCustomerJpaService fCustomerJpaService;
		private FCustomersubgroupJpaService fCustomersubgroupJpaService;
		private FSubareaJpaService fSubareaJpaService;
		private FtPriceAlthJpaService ftPriceAlthJpaService;

		private PDistributorJpaService pDistributorJpaService;
		private FChannelJpaService fChannelJpaService;
		
		
		private SysvarJpaService sysvarJpaService;
		private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
		
	//2. ENTITY
		protected FCustomer itemHeader = new FCustomer();
		protected FCustomer newItemHeader = new FCustomer();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FCustomer> beanItemContainerHeader = 
				new BeanItemContainer<FCustomer>(FCustomer.class);
		private BeanItemContainer<FCustomer> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FCustomer>(FCustomer.class);

		private BeanItemContainer<FCustomersubgroup> beanItemContainerCustomergroup = 
				new BeanItemContainer<FCustomersubgroup>(FCustomersubgroup.class);
		private BeanItemContainer<FSubarea> beanItemContainerSubarea = 
				new BeanItemContainer<FSubarea>(FSubarea.class);
		private BeanItemContainer<FtPriceAlth> beanItemContainerFtPriceAlth = 
				new BeanItemContainer<FtPriceAlth>(FtPriceAlth.class);

		
		private BeanItemContainer<PDistributor> beanItemContainerPTerritory = 
				new BeanItemContainer<PDistributor>(PDistributor.class);
		private BeanItemContainer<FChannel> beanItemContainerFChannel = 
				new BeanItemContainer<FChannel>(FChannel.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FCustomer> binderHeader = 
				new BeanFieldGroup<FCustomer>(FCustomer.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public CustomerModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfCustomerJpaService((((DashboardUI) getUI().getCurrent()).getfCustomerJpaService()));
		setfCustomersubgroupJpaService((((DashboardUI) getUI().getCurrent()).getfCustomersubgroupJpaService()));
		setfSubareaJpaService((((DashboardUI) getUI().getCurrent()).getfSubareaJpaService()));
		setFtPriceAlthJpaService((((DashboardUI) getUI().getCurrent()).getFtPriceAlthJpaService()));
		
		setpDistributorJpaService((((DashboardUI) getUI().getCurrent()).getpDistributorJpaService()));
		setfChannelJpaService((((DashboardUI) getUI().getCurrent()).getfChannelJpaService()));
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(fCustomerJpaService.findAll());
		beanItemContainerHeader.addNestedContainerProperty("fcustomersubgroupBean.id");
		beanItemContainerHeader.addNestedContainerProperty("fcustomersubgroupBean.description");
		beanItemContainerHeader.addNestedContainerProperty("fsubareaBean.id");
		beanItemContainerHeader.addNestedContainerProperty("fsubareaBean.description");
		
		beanItemContainerCustomergroup.addAll(fCustomersubgroupJpaService.findAll());
		beanItemContainerSubarea.addAll(fSubareaJpaService.findAll());
		
		beanItemContainerFtPriceAlth.addAll(ftPriceAlthJpaService.findAll());

		beanItemContainerPTerritory.addAll(pDistributorJpaService.findAll());
		beanItemContainerFChannel.addAll(fChannelJpaService.findAll());
		
	}
	public FCustomerJpaService getfCustomerJpaService() {
		return fCustomerJpaService;
	}
	public FCustomersubgroupJpaService getfCustomersubgroupJpaService() {
		return fCustomersubgroupJpaService;
	}
	public FSubareaJpaService getfSubareaJpaService() {
		return fSubareaJpaService;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}

	public FCustomer getItemHeader() {
		return itemHeader;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public FCustomer getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FCustomer> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FCustomer> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<FCustomersubgroup> getBeanItemContainerCustomergroup() {
		return beanItemContainerCustomergroup;
	}
	public BeanItemContainer<FSubarea> getBeanItemContainerSubarea() {
		return beanItemContainerSubarea;
	}
	public BeanFieldGroup<FCustomer> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setfCustomerJpaService(FCustomerJpaService fCustomerJpaService) {
		this.fCustomerJpaService = fCustomerJpaService;
	}
	public void setfCustomersubgroupJpaService(
			FCustomersubgroupJpaService fCustomersubgroupJpaService) {
		this.fCustomersubgroupJpaService = fCustomersubgroupJpaService;
	}
	public void setfSubareaJpaService(FSubareaJpaService fSubareaJpaService) {
		this.fSubareaJpaService = fSubareaJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelperImpl transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(FCustomer itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FCustomer newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FCustomer> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FCustomer> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerCustomergroup(
			BeanItemContainer<FCustomersubgroup> beanItemContainerCustomergroup) {
		this.beanItemContainerCustomergroup = beanItemContainerCustomergroup;
	}
	public void setBeanItemContainerSubarea(
			BeanItemContainer<FSubarea> beanItemContainerSubarea) {
		this.beanItemContainerSubarea = beanItemContainerSubarea;
	}
	public void setBinderHeader(BeanFieldGroup<FCustomer> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	public FtPriceAlthJpaService getFtPriceAlthJpaService() {
		return ftPriceAlthJpaService;
	}
	public BeanItemContainer<FtPriceAlth> getBeanItemContainerFtPriceAlth() {
		return beanItemContainerFtPriceAlth;
	}
	public void setFtPriceAlthJpaService(FtPriceAlthJpaService ftPriceAlthJpaService) {
		this.ftPriceAlthJpaService = ftPriceAlthJpaService;
	}
	public void setBeanItemContainerFtPriceAlth(
			BeanItemContainer<FtPriceAlth> beanItemContainerFtPriceAlth) {
		this.beanItemContainerFtPriceAlth = beanItemContainerFtPriceAlth;
	}
	public FChannelJpaService getfChannelJpaService() {
		return fChannelJpaService;
	}
	public BeanItemContainer<PDistributor> getBeanItemContainerPTerritory() {
		return beanItemContainerPTerritory;
	}
	public BeanItemContainer<FChannel> getBeanItemContainerFChannel() {
		return beanItemContainerFChannel;
	}
	public void setfChannelJpaService(FChannelJpaService fChannelJpaService) {
		this.fChannelJpaService = fChannelJpaService;
	}
	public void setBeanItemContainerPTerritory(
			BeanItemContainer<PDistributor> beanItemContainerPTerritory) {
		this.beanItemContainerPTerritory = beanItemContainerPTerritory;
	}
	public void setBeanItemContainerFChannel(
			BeanItemContainer<FChannel> beanItemContainerFChannel) {
		this.beanItemContainerFChannel = beanItemContainerFChannel;
	}
	public PDistributorJpaService getpDistributorJpaService() {
		return pDistributorJpaService;
	}
	public void setpDistributorJpaService(
			PDistributorJpaService pDistributorJpaService) {
		this.pDistributorJpaService = pDistributorJpaService;
	}
	
	
}
