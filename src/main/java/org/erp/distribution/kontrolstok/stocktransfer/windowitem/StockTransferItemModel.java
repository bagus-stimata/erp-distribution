package org.erp.distribution.kontrolstok.stocktransfer.windowitem;

import java.util.Date;
import java.util.Set;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FtPurchasedJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.FtStocktransferdJpaService;
import org.erp.distribution.jpaservice.FtStocktransferhJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FProduct;
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

public class StockTransferItemModel extends CustomComponent {
	
	//1. DAO SERVICE
		private SysvarJpaService sysvarJpaService;
		private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	
		private FtStocktransferhJpaService ftStocktransferhJpaService;
		private FtStocktransferdJpaService ftStocktransferdJpaService;
		private FVendorJpaService fVendorJpaService;
		private FProductJpaService fProductJpaService;
		private FStockJpaService fStockJpaService;
		
	//2. ENTITY		
		protected FtStocktransferh itemHeader = new FtStocktransferh();
		protected FtStocktransferd itemDetil = new FtStocktransferd();
		protected FtStocktransferdPK ftStocktransferdPK = new FtStocktransferdPK();		
//		protected MenuAccessTemp menuAccessTemp;
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FProduct> beanItemContainerProduct = 
				new BeanItemContainer<FProduct>(FProduct.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FtStocktransferd> binderItemDetail = 
				new BeanFieldGroup<FtStocktransferd>(FtStocktransferd.class);
				
		//OTHERS
		protected String OperationStatus = "OPEN";

		public String getOperationStatus() {
			return OperationStatus;
		}

		public void setOperationStatus(String operationStatus) {
			OperationStatus = operationStatus;
		}

		public StockTransferItemModel(){
			initVariable();
			initVariableData();
			
			
		}
		
		public void initVariable(){
			setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//			setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
			
			setFtStocktransferhJpaService((((DashboardUI) getUI().getCurrent()).getFtStocktransferhJpaService()));
			setFtStocktransferdJpaService((((DashboardUI) getUI().getCurrent()).getFtStocktransferdJpaService()));
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
			
//			beanItemContainerProduct.addAll(fProductJpaService.findAllActive());
			
		}

		public SysvarJpaService getSysvarJpaService() {
			return sysvarJpaService;
		}

		public TransaksiHelper getTransaksiHelper() {
			return transaksiHelper;
		}

		public FtStocktransferhJpaService getFtStocktransferhJpaService() {
			return ftStocktransferhJpaService;
		}

		public FtStocktransferdJpaService getFtStocktransferdJpaService() {
			return ftStocktransferdJpaService;
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

		public FtStocktransferh getItemHeader() {
			return itemHeader;
		}

		public FtStocktransferd getItemDetil() {
			return itemDetil;
		}

		public FtStocktransferdPK getFtStocktransferdPK() {
			return ftStocktransferdPK;
		}

		public BeanItemContainer<FProduct> getBeanItemContainerProduct() {
			return beanItemContainerProduct;
		}

		public BeanFieldGroup<FtStocktransferd> getBinderItemDetail() {
			return binderItemDetail;
		}

		public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
			this.sysvarJpaService = sysvarJpaService;
		}

		public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
			this.transaksiHelper = transaksiHelper;
		}

		public void setFtStocktransferhJpaService(
				FtStocktransferhJpaService ftStocktransferhJpaService) {
			this.ftStocktransferhJpaService = ftStocktransferhJpaService;
		}

		public void setFtStocktransferdJpaService(
				FtStocktransferdJpaService ftStocktransferdJpaService) {
			this.ftStocktransferdJpaService = ftStocktransferdJpaService;
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

		public void setItemHeader(FtStocktransferh itemHeader) {
			this.itemHeader = itemHeader;
		}

		public void setItemDetil(FtStocktransferd itemDetil) {
			this.itemDetil = itemDetil;
		}

		public void setFtStocktransferdPK(FtStocktransferdPK ftStocktransferdPK) {
			this.ftStocktransferdPK = ftStocktransferdPK;
		}

		public void setBeanItemContainerProduct(
				BeanItemContainer<FProduct> beanItemContainerProduct) {
			this.beanItemContainerProduct = beanItemContainerProduct;
		}

		public void setBinderItemDetail(
				BeanFieldGroup<FtStocktransferd> binderItemDetail) {
			this.binderItemDetail = binderItemDetail;
		}

	
	
}
