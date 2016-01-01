package org.erp.distribution.kontrolstok.stockopname.windowitem;

import java.util.Date;
import java.util.Set;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FtOpnamedJpaService;
import org.erp.distribution.jpaservice.FtOpnamehJpaService;
import org.erp.distribution.jpaservice.FtPurchasedJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.FtStocktransferdJpaService;
import org.erp.distribution.jpaservice.FtStocktransferhJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FtOpnamed;
import org.erp.distribution.model.FtOpnamedPK;
import org.erp.distribution.model.FtOpnameh;
import org.erp.distribution.model.FtPurchased;
import org.erp.distribution.model.FtPurchasedPK;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtStocktransferd;
import org.erp.distribution.model.FtStocktransferdPK;
import org.erp.distribution.model.FtStocktransferh;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class StockOpnameItemModel extends CustomComponent {
	
	//1. DAO SERVICE
		private SysvarJpaService sysvarJpaService;
		private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	
		private FtOpnamehJpaService ftOpnamehJpaService;
		private FtOpnamedJpaService ftOpnamedJpaService;
		private FVendorJpaService fVendorJpaService;
		private FProductJpaService fProductJpaService;
		private FStockJpaService fStockJpaService;
		
	//2. ENTITY		
		protected FtOpnameh itemHeader = new FtOpnameh();
		protected FtOpnamed itemDetil = new FtOpnamed();
		protected FtOpnamedPK ftOpnamedPK = new FtOpnamedPK();		
//		protected MenuAccessTemp menuAccessTemp;
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FProduct> beanItemContainerProduct = 
				new BeanItemContainer<FProduct>(FProduct.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FtOpnamed> binderItemDetail = 
				new BeanFieldGroup<FtOpnamed>(FtOpnamed.class);
				
		//OTHERS
		protected String OperationStatus = "OPEN";

		public String getOperationStatus() {
			return OperationStatus;
		}

		public void setOperationStatus(String operationStatus) {
			OperationStatus = operationStatus;
		}

		public StockOpnameItemModel(){
			initVariable();
			initVariableData();
			
			
		}
		
		public void initVariable(){
			setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//			setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
			
			setFtOpnamehJpaService((((DashboardUI) getUI().getCurrent()).getFtOpnamehJpaService()));
			setFtOpnamedJpaService((((DashboardUI) getUI().getCurrent()).getFtOpnamedJpaService()));
			setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
			setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
			setfStockJpaService((((DashboardUI) getUI().getCurrent()).getfStockJpaService()));
		}

		public void initVariableData(){
			reload();			
		}
		
		public void reload(){
			beanItemContainerProduct.removeAllContainerFilters();
			beanItemContainerProduct.removeAllItems();
			
			beanItemContainerProduct.addAll(fProductJpaService.findAllActive());
			
		}

		public SysvarJpaService getSysvarJpaService() {
			return sysvarJpaService;
		}

		public TransaksiHelper getTransaksiHelper() {
			return transaksiHelper;
		}

		public FtOpnamehJpaService getFtOpnamehJpaService() {
			return ftOpnamehJpaService;
		}

		public FtOpnamedJpaService getFtOpnamedJpaService() {
			return ftOpnamedJpaService;
		}

		public FVendorJpaService getfVendorJpaService() {
			return fVendorJpaService;
		}

		public FProductJpaService getfProductJpaService() {
			return fProductJpaService;
		}

		public FStockJpaService getfStockJpaService() {
			return fStockJpaService;
		}

		public FtOpnameh getItemHeader() {
			return itemHeader;
		}

		public FtOpnamed getItemDetil() {
			return itemDetil;
		}

		public FtOpnamedPK getFtOpnamedPK() {
			return ftOpnamedPK;
		}

		public BeanItemContainer<FProduct> getBeanItemContainerProduct() {
			return beanItemContainerProduct;
		}

		public BeanFieldGroup<FtOpnamed> getBinderItemDetail() {
			return binderItemDetail;
		}

		public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
			this.sysvarJpaService = sysvarJpaService;
		}

		public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
			this.transaksiHelper = transaksiHelper;
		}

		public void setFtOpnamehJpaService(FtOpnamehJpaService ftOpnamehJpaService) {
			this.ftOpnamehJpaService = ftOpnamehJpaService;
		}

		public void setFtOpnamedJpaService(FtOpnamedJpaService ftOpnamedJpaService) {
			this.ftOpnamedJpaService = ftOpnamedJpaService;
		}

		public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
			this.fVendorJpaService = fVendorJpaService;
		}

		public void setfProductJpaService(FProductJpaService fProductJpaService) {
			this.fProductJpaService = fProductJpaService;
		}

		public void setfStockJpaService(FStockJpaService fStockJpaService) {
			this.fStockJpaService = fStockJpaService;
		}

		public void setItemHeader(FtOpnameh itemHeader) {
			this.itemHeader = itemHeader;
		}

		public void setItemDetil(FtOpnamed itemDetil) {
			this.itemDetil = itemDetil;
		}

		public void setFtOpnamedPK(FtOpnamedPK ftOpnamedPK) {
			this.ftOpnamedPK = ftOpnamedPK;
		}

		public void setBeanItemContainerProduct(
				BeanItemContainer<FProduct> beanItemContainerProduct) {
			this.beanItemContainerProduct = beanItemContainerProduct;
		}

		public void setBinderItemDetail(BeanFieldGroup<FtOpnamed> binderItemDetail) {
			this.binderItemDetail = binderItemDetail;
		}

	
}
