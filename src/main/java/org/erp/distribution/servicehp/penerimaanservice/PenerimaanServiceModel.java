package org.erp.distribution.servicehp.penerimaanservice;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FCustomergroupJpaService;
import org.erp.distribution.jpaservice.FCustomersubgroupJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.FtPriceAltdJpaService;
import org.erp.distribution.jpaservice.FtPriceAlthJpaService;
import org.erp.distribution.jpaservice.SMerkJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservicehp.SCustomerJpaService;
import org.erp.distribution.jpaservicehp.STeknisiJpaService;
import org.erp.distribution.jpaservicehp.StServiceJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FtPriceAlth;
import org.erp.distribution.model.SCustomer;
import org.erp.distribution.model.SMerk;
import org.erp.distribution.model.STeknisi;
import org.erp.distribution.model.StService;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.SysvarHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperHp;
import org.erp.distribution.util.TransaksiHelperHpImpl;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class PenerimaanServiceModel extends CustomComponent{
	
	//1. DAO SERVICE
		private StServiceJpaService stServiceJpaService;
		
		private SCustomerJpaService sCustomerJpaService;
		private STeknisiJpaService sTeknisiJpaService;
		private SMerkJpaService sMerkJpaService;
		
		private SysvarJpaService sysvarJpaService;
		private TransaksiHelper transaksiHelper = new TransaksiHelperImpl();
		private TransaksiHelperHp transaksiHelperHp = new TransaksiHelperHpImpl();
		private SysvarHelper sysvarHelper;
		
		private String paramAdminOrTeknisi = "";
		
	//2. ENTITY
		protected StService itemHeader = new StService();
		protected StService newItemHeader = new StService();
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<StService> beanItemContainerHeader = 
				new BeanItemContainer<StService>(StService.class);
		private BeanItemContainer<StService> beanItemContainerHeaderSearch = 
				new BeanItemContainer<StService>(StService.class);

		private BeanItemContainer<SCustomer> beanItemContainerCustomer = 
				new BeanItemContainer<SCustomer>(SCustomer.class);
		private BeanItemContainer<STeknisi> beanItemContainerTeknisi = 
				new BeanItemContainer<STeknisi>(STeknisi.class);
		private BeanItemContainer<SMerk> beanItemContainerMerkHp = 
				new BeanItemContainer<SMerk>(SMerk.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<StService> binderHeader = 
				new BeanFieldGroup<StService>(StService.class);

//CUSTOMER		
		protected SCustomer itemHeaderCustomer = new SCustomer();
		protected SCustomer newItemHeaderCustomer = new SCustomer();		
		private BeanItemContainer<SCustomer> beanItemContainerHeaderCustomer = 
				new BeanItemContainer<SCustomer>(SCustomer.class);
		
		private BeanFieldGroup<SCustomer> binderHeaderCustomer = 
				new BeanFieldGroup<SCustomer>(SCustomer.class);
		
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public PenerimaanServiceModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
		setSysvarHelper((((DashboardUI) getUI().getCurrent()).getSysvarHelper()));
		
		setStServiceJpaService((((DashboardUI) getUI().getCurrent()).getStServiceJpaService()));

		setsCustomerJpaService((((DashboardUI) getUI().getCurrent()).getsCustomerJpaService()));
		setsTeknisiJpaService((((DashboardUI) getUI().getCurrent()).getsTeknisiJpaService()));
		setsMerkJpaService((((DashboardUI) getUI().getCurrent()).getsMerkJpaService()));
		
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(stServiceJpaService.findAll());
		beanItemContainerHeader.addNestedContainerProperty("scustomerBean.custno");
		beanItemContainerHeader.addNestedContainerProperty("scustomerBean.custname");
		beanItemContainerHeader.addNestedContainerProperty("scustomerBean.address1");
		beanItemContainerHeader.addNestedContainerProperty("smerkBean.id");
		beanItemContainerHeader.addNestedContainerProperty("smerkBean.description");
		beanItemContainerHeader.addNestedContainerProperty("steknisiBean.nip");
		beanItemContainerHeader.addNestedContainerProperty("steknisiBean.name");

		beanItemContainerCustomer.addAll(sCustomerJpaService.findAll());
		beanItemContainerTeknisi.addAll(sTeknisiJpaService.findAll());
		beanItemContainerMerkHp.addAll(sMerkJpaService.findAll());
		
		
	}
	public StServiceJpaService getStServiceJpaService() {
		return stServiceJpaService;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public StService getItemHeader() {
		return itemHeader;
	}
	public StService getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<StService> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<StService> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanFieldGroup<StService> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setStServiceJpaService(StServiceJpaService stServiceJpaService) {
		this.stServiceJpaService = stServiceJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(StService itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(StService newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<StService> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<StService> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBinderHeader(BeanFieldGroup<StService> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	public SCustomerJpaService getsCustomerJpaService() {
		return sCustomerJpaService;
	}
	public BeanItemContainer<SCustomer> getBeanItemContainerCustomer() {
		return beanItemContainerCustomer;
	}
	public void setsCustomerJpaService(SCustomerJpaService sCustomerJpaService) {
		this.sCustomerJpaService = sCustomerJpaService;
	}
	public void setBeanItemContainerCustomer(
			BeanItemContainer<SCustomer> beanItemContainerCustomer) {
		this.beanItemContainerCustomer = beanItemContainerCustomer;
	}
	public TransaksiHelperHp getTransaksiHelperHp() {
		return transaksiHelperHp;
	}
	public void setTransaksiHelperHp(TransaksiHelperHp transaksiHelperHp) {
		this.transaksiHelperHp = transaksiHelperHp;
	}
	public STeknisiJpaService getsTeknisiJpaService() {
		return sTeknisiJpaService;
	}
	public void setsTeknisiJpaService(STeknisiJpaService sTeknisiJpaService) {
		this.sTeknisiJpaService = sTeknisiJpaService;
	}
	public SMerkJpaService getsMerkJpaService() {
		return sMerkJpaService;
	}
	public BeanItemContainer<STeknisi> getBeanItemContainerTeknisi() {
		return beanItemContainerTeknisi;
	}
	public BeanItemContainer<SMerk> getBeanItemContainerMerkHp() {
		return beanItemContainerMerkHp;
	}
	public void setsMerkJpaService(SMerkJpaService sMerkJpaService) {
		this.sMerkJpaService = sMerkJpaService;
	}
	public void setBeanItemContainerTeknisi(
			BeanItemContainer<STeknisi> beanItemContainerTeknisi) {
		this.beanItemContainerTeknisi = beanItemContainerTeknisi;
	}
	public void setBeanItemContainerMerkHp(
			BeanItemContainer<SMerk> beanItemContainerMerkHp) {
		this.beanItemContainerMerkHp = beanItemContainerMerkHp;
	}
	public BeanItemContainer<SCustomer> getBeanItemContainerHeaderCustomer() {
		return beanItemContainerHeaderCustomer;
	}
	public BeanFieldGroup<SCustomer> getBinderHeaderCustomer() {
		return binderHeaderCustomer;
	}
	public void setBeanItemContainerHeaderCustomer(
			BeanItemContainer<SCustomer> beanItemContainerHeaderCustomer) {
		this.beanItemContainerHeaderCustomer = beanItemContainerHeaderCustomer;
	}
	public void setBinderHeaderCustomer(
			BeanFieldGroup<SCustomer> binderHeaderCustomer) {
		this.binderHeaderCustomer = binderHeaderCustomer;
	}
	public SCustomer getItemHeaderCustomer() {
		return itemHeaderCustomer;
	}
	public SCustomer getNewItemHeaderCustomer() {
		return newItemHeaderCustomer;
	}
	public void setItemHeaderCustomer(SCustomer itemHeaderCustomer) {
		this.itemHeaderCustomer = itemHeaderCustomer;
	}
	public void setNewItemHeaderCustomer(SCustomer newItemHeaderCustomer) {
		this.newItemHeaderCustomer = newItemHeaderCustomer;
	}
	public SysvarHelper getSysvarHelper() {
		return sysvarHelper;
	}
	public void setSysvarHelper(SysvarHelper sysvarHelper) {
		this.sysvarHelper = sysvarHelper;
	}
	public String getParamAdminOrTeknisi() {
		return paramAdminOrTeknisi;
	}
	public void setParamAdminOrTeknisi(String paramAdminOrTeknisi) {
		this.paramAdminOrTeknisi = paramAdminOrTeknisi;
	}
	
	
	
	
	
}
