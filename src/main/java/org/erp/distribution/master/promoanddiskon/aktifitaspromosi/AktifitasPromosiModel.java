package org.erp.distribution.master.promoanddiskon.aktifitaspromosi;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FCustomergroupJpaService;
import org.erp.distribution.jpaservice.FCustomersubgroupJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FSubareaJpaService;
import org.erp.distribution.jpaservice.FPromoJpaService2;
import org.erp.distribution.jpaservice.FtPriceAltdJpaService;
import org.erp.distribution.jpaservice.FtPriceAlthJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FPromo;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FtPriceAlth;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.SysvarHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class AktifitasPromosiModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private FPromoJpaService2 fpromoJpaService;

		private FCustomersubgroupJpaService fCustomersubgroupJpaService;
		private FProductJpaService fProductJpaService;
		private FProductgroupJpaService fProductgroupJpaService;
		
		private SysvarHelper sysvarHelper;
		
	//2. ENTITY
		protected FPromo itemHeader = new FPromo();
		protected FPromo newItemHeader = new FPromo();
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FPromo> beanItemContainerHeader = 
				new BeanItemContainer<FPromo>(FPromo.class);
		private BeanItemContainer<FPromo> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FPromo>(FPromo.class);

		private BeanItemContainer<FProduct> beanItemContainerFproduct = 
				new BeanItemContainer<FProduct>(FProduct.class);
		private BeanItemContainer<FProductgroup> beanItemContainerFproductgroup = 
				new BeanItemContainer<FProductgroup>(FProductgroup.class);
		private BeanItemContainer<FCustomersubgroup> beanItemContainerFcustomersubgroup = 
				new BeanItemContainer<FCustomersubgroup>(FCustomersubgroup.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FPromo> binderHeader = 
				new BeanFieldGroup<FPromo>(FPromo.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public AktifitasPromosiModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarHelper((((DashboardUI) getUI().getCurrent()).getSysvarHelper()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setFpromoJpaService((((DashboardUI) getUI().getCurrent()).getFpromoJpaService()));
		
		setfProductgroupJpaService((((DashboardUI) getUI().getCurrent()).getfProductgroupJpaService()));
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		setfCustomersubgroupJpaService((((DashboardUI) getUI().getCurrent()).getfCustomersubgroupJpaService()));
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(fpromoJpaService.findAll());
		
		beanItemContainerFproduct.addAll(fProductJpaService.findAll());
		beanItemContainerFproductgroup.addAll(fProductgroupJpaService.findAll());
		
		beanItemContainerFcustomersubgroup.addAll(fCustomersubgroupJpaService.findAll());
		
	}
	public FPromoJpaService2 getFpromoJpaService() {
		return fpromoJpaService;
	}
	public FCustomersubgroupJpaService getfCustomersubgroupJpaService() {
		return fCustomersubgroupJpaService;
	}
	public FProductJpaService getfProductJpaService() {
		return fProductJpaService;
	}
	public FProductgroupJpaService getfProductgroupJpaService() {
		return fProductgroupJpaService;
	}
	public SysvarHelper getSysvarHelper() {
		return sysvarHelper;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public FPromo getItemHeader() {
		return itemHeader;
	}
	public FPromo getNewItemHeader() {
		return newItemHeader;
	}
	public BeanItemContainer<FPromo> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}
	public BeanItemContainer<FPromo> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}
	public BeanItemContainer<FProduct> getBeanItemContainerFproduct() {
		return beanItemContainerFproduct;
	}
	public BeanItemContainer<FProductgroup> getBeanItemContainerFproductgroup() {
		return beanItemContainerFproductgroup;
	}
	public BeanItemContainer<FCustomersubgroup> getBeanItemContainerFcustomersubgroup() {
		return beanItemContainerFcustomersubgroup;
	}
	public BeanFieldGroup<FPromo> getBinderHeader() {
		return binderHeader;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setFpromoJpaService(FPromoJpaService2 fpromoJpaService) {
		this.fpromoJpaService = fpromoJpaService;
	}
	public void setfCustomersubgroupJpaService(
			FCustomersubgroupJpaService fCustomersubgroupJpaService) {
		this.fCustomersubgroupJpaService = fCustomersubgroupJpaService;
	}
	public void setfProductJpaService(FProductJpaService fProductJpaService) {
		this.fProductJpaService = fProductJpaService;
	}
	public void setfProductgroupJpaService(
			FProductgroupJpaService fProductgroupJpaService) {
		this.fProductgroupJpaService = fProductgroupJpaService;
	}
	public void setSysvarHelper(SysvarHelper sysvarHelper) {
		this.sysvarHelper = sysvarHelper;
	}
	public void setTransaksiHelper(TransaksiHelperImpl transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setItemHeader(FPromo itemHeader) {
		this.itemHeader = itemHeader;
	}
	public void setNewItemHeader(FPromo newItemHeader) {
		this.newItemHeader = newItemHeader;
	}
	public void setBeanItemContainerHeader(
			BeanItemContainer<FPromo> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}
	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FPromo> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}
	public void setBeanItemContainerFproduct(
			BeanItemContainer<FProduct> beanItemContainerFproduct) {
		this.beanItemContainerFproduct = beanItemContainerFproduct;
	}
	public void setBeanItemContainerFproductgroup(
			BeanItemContainer<FProductgroup> beanItemContainerFproductgroup) {
		this.beanItemContainerFproductgroup = beanItemContainerFproductgroup;
	}
	public void setBeanItemContainerFcustomersubgroup(
			BeanItemContainer<FCustomersubgroup> beanItemContainerFcustomersubgroup) {
		this.beanItemContainerFcustomersubgroup = beanItemContainerFcustomersubgroup;
	}
	public void setBinderHeader(BeanFieldGroup<FPromo> binderHeader) {
		this.binderHeader = binderHeader;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	
}
