package org.erp.distribution.salesorder.salesorder.windowitem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FParamDiskonItemJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FPromoJpaService2;
import org.erp.distribution.jpaservice.FSalesmanJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FtPriceAltdJpaService;
import org.erp.distribution.jpaservice.FtPurchasedJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FParamDiskonItem;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FtPurchased;
import org.erp.distribution.model.FtPurchasedPK;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesdPK;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.SysvarHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class SalesOrderItemModel extends CustomComponent {
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private SysvarJpaService sysvarJpaService;
		private SysvarHelper sysvarHelper;
	
		private FtSaleshJpaService ftSaleshJpaService;
		private FtSalesdJpaService ftSalesdJpaService;

		private FSalesmanJpaService fSalesmanJpaService;
		private FCustomerJpaService fCustomerJpaService;
		private FProductJpaService fProductJpaService;
		private FStockJpaService fStockJpaService;
		
		private FPromoJpaService2 fPromoJpaService2;
		private FParamDiskonItemJpaService fParamDiskonItemJpaService;
		
		private FtPriceAltdJpaService ftPriceAltdJpaService;
		
	//2. ENTITY		
		protected FtSalesh itemHeader = new FtSalesh();
		protected FtSalesd itemDetil = new FtSalesd();
		protected FtSalesdPK ftPK = new FtSalesdPK();		
//		protected MenuAccessTemp menuAccessTemp;
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FProduct> beanItemContainerProduct = 
				new BeanItemContainer<FProduct>(FProduct.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FtSalesd> binderItemDetail = 
				new BeanFieldGroup<FtSalesd>(FtSalesd.class);
				
		//OTHERS
		protected String OperationStatus = "OPEN";

		public String getOperationStatus() {
			return OperationStatus;
		}

		public void setOperationStatus(String operationStatus) {
			OperationStatus = operationStatus;
		}

		public SalesOrderItemModel(){
			initVariable();
			initVariableData();
		}
		public SalesOrderItemModel(FtSalesh itemHeader){
			this.itemHeader = itemHeader;
			initVariable();
			initVariableData();
		}
		
		
		public void initVariable(){
			setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
			setSysvarHelper((((DashboardUI) getUI().getCurrent()).getSysvarHelper()));
			
			setFtSalesdJpaService((((DashboardUI) getUI().getCurrent()).getFtSalesdJpaService()));
			setFtSaleshJpaService((((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService()));
			
			setfSalesmanJpaService((((DashboardUI) getUI().getCurrent()).getfSalesmanJpaService()));
			setfCustomerJpaService((((DashboardUI) getUI().getCurrent()).getfCustomerJpaService()));
			setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
			setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
			
			setfPromoJpaService2((((DashboardUI) getUI().getCurrent()).getFpromoJpaService()));
			setfParamDiskonItemJpaService((((DashboardUI) getUI().getCurrent()).getfParamDiskonItemJpaService()));
			
			setFtPriceAltdJpaService((((DashboardUI) getUI().getCurrent()).getFtPriceAltdJpaService()));
			
			
			
		}

		public void initVariableData(){
			reload();			
		}
		
		public void reload(){
			beanItemContainerProduct.removeAllContainerFilters();
			beanItemContainerProduct.removeAllItems();
			
			//CEK Salesman Covered
			//1. Jika Dia Meng Cover Vendor Tertentu maka harus di list Product Didalam Vendor TErsebut
			//Jika tidak maka semua
			
			beanItemContainerProduct.addAll(fProductJpaService.findAllActive());
			
		}

		public SysvarJpaService getSysvarJpaService() {
			return sysvarJpaService;
		}

		public TransaksiHelper getTransaksiHelper() {
			return transaksiHelper;
		}

		public FtSaleshJpaService getFtSaleshJpaService() {
			return ftSaleshJpaService;
		}

		public FtSalesdJpaService getFtSalesdJpaService() {
			return ftSalesdJpaService;
		}

		public FCustomerJpaService getfCustomerJpaService() {
			return fCustomerJpaService;
		}

		public FProductJpaService getfProductJpaService() {
			return fProductJpaService;
		}

		public FStockJpaService getfStockJpaService() {
			return fStockJpaService;
		}

		public FtSalesh getItemHeader() {
			return itemHeader;
		}

		public FtSalesd getItemDetil() {
			return itemDetil;
		}

		public FtSalesdPK getFtPK() {
			return ftPK;
		}

		public BeanItemContainer<FProduct> getBeanItemContainerProduct() {
			return beanItemContainerProduct;
		}

		public BeanFieldGroup<FtSalesd> getBinderItemDetail() {
			return binderItemDetail;
		}

		public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
			this.sysvarJpaService = sysvarJpaService;
		}

		public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
			this.transaksiHelper = transaksiHelper;
		}

		public void setFtSaleshJpaService(FtSaleshJpaService ftSaleshJpaService) {
			this.ftSaleshJpaService = ftSaleshJpaService;
		}

		public void setFtSalesdJpaService(FtSalesdJpaService ftSalesdJpaService) {
			this.ftSalesdJpaService = ftSalesdJpaService;
		}

		public void setfCustomerJpaService(FCustomerJpaService fCustomerJpaService) {
			this.fCustomerJpaService = fCustomerJpaService;
		}

		public void setfProductJpaService(FProductJpaService fProductJpaService) {
			this.fProductJpaService = fProductJpaService;
		}

		public void setfStockJpaService(FStockJpaService fStockJpaService) {
			this.fStockJpaService = fStockJpaService;
		}

		public void setItemHeader(FtSalesh itemHeader) {
			this.itemHeader = itemHeader;
		}

		public void setItemDetil(FtSalesd itemDetil) {
			this.itemDetil = itemDetil;
		}

		public void setFtPK(FtSalesdPK ftPK) {
			this.ftPK = ftPK;
		}

		public void setBeanItemContainerProduct(
				BeanItemContainer<FProduct> beanItemContainerProduct) {
			this.beanItemContainerProduct = beanItemContainerProduct;
		}

		public void setBinderItemDetail(BeanFieldGroup<FtSalesd> binderItemDetail) {
			this.binderItemDetail = binderItemDetail;
		}

		public SysvarHelper getSysvarHelper() {
			return sysvarHelper;
		}

		public void setSysvarHelper(SysvarHelper sysvarHelper) {
			this.sysvarHelper = sysvarHelper;
		}

		public FPromoJpaService2 getfPromoJpaService2() {
			return fPromoJpaService2;
		}

		public void setfPromoJpaService2(FPromoJpaService2 fPromoJpaService2) {
			this.fPromoJpaService2 = fPromoJpaService2;
		}

		public FParamDiskonItemJpaService getfParamDiskonItemJpaService() {
			return fParamDiskonItemJpaService;
		}

		public void setfParamDiskonItemJpaService(
				FParamDiskonItemJpaService fParamDiskonItemJpaService) {
			this.fParamDiskonItemJpaService = fParamDiskonItemJpaService;
		}

		public FSalesmanJpaService getfSalesmanJpaService() {
			return fSalesmanJpaService;
		}

		public void setfSalesmanJpaService(FSalesmanJpaService fSalesmanJpaService) {
			this.fSalesmanJpaService = fSalesmanJpaService;
		}

		public FtPriceAltdJpaService getFtPriceAltdJpaService() {
			return ftPriceAltdJpaService;
		}

		public void setFtPriceAltdJpaService(FtPriceAltdJpaService ftPriceAltdJpaService) {
			this.ftPriceAltdJpaService = ftPriceAltdJpaService;
		}

			
	
	
}
