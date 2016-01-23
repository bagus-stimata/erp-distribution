package org.erp.distribution.purchaseorder.retur.windowitem;

import java.util.Date;
import java.util.Set;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FtPurchasedJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FtPurchased;
import org.erp.distribution.model.FtPurchasedPK;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class IncomingStockReturItemModel extends CustomComponent {
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private SysvarJpaService sysvarJpaService;
		private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();
	
		private FtPurchasehJpaService ftPurchasehJpaService;
		private FtPurchasedJpaService ftPurchasedJpaService;
		private FVendorJpaService fVendorJpaService;
		private FProductJpaService fProductJpaService;
		private FStockJpaService fStockJpaService;
		
	//2. ENTITY		
		protected FtPurchaseh itemHeader = new FtPurchaseh();
		protected FtPurchased itemDetil = new FtPurchased();
		protected FtPurchasedPK ftPurchasedPK = new FtPurchasedPK();		
//		protected MenuAccessTemp menuAccessTemp;
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FProduct> beanItemContainerProduct = 
				new BeanItemContainer<FProduct>(FProduct.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FtPurchased> binderItemDetail = 
				new BeanFieldGroup<FtPurchased>(FtPurchased.class);
				
		//OTHERS
		protected String OperationStatus = "OPEN";

		public String getOperationStatus() {
			return OperationStatus;
		}

		public void setOperationStatus(String operationStatus) {
			OperationStatus = operationStatus;
		}

		public IncomingStockReturItemModel(){
			initVariable();
			initVariableData();
			
			
		}
		
		public void initVariable(){
			setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
			
			setFtPurchasedJpaService((((DashboardUI) getUI().getCurrent()).getFtPurchasedJpaService()));
			setFtPurchasehJpaService((((DashboardUI) getUI().getCurrent()).getFtPurchasehJpaService()));
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

		public FtPurchasehJpaService getFtPurchasehJpaService() {
			return ftPurchasehJpaService;
		}

		public FtPurchasedJpaService getFtPurchasedJpaService() {
			return ftPurchasedJpaService;
		}

		public FVendorJpaService getfVendorJpaService() {
			return fVendorJpaService;
		}

		public FProductJpaService getfProductJpaService() {
			return fProductJpaService;
		}

		public SysvarJpaService getSysvarJpaService() {
			return sysvarJpaService;
		}

		public FtPurchaseh getItemHeader() {
			return itemHeader;
		}

		public FtPurchased getItemDetil() {
			return itemDetil;
		}

		public BeanItemContainer<FProduct> getBeanItemContainerProduct() {
			return beanItemContainerProduct;
		}

		public BeanFieldGroup<FtPurchased> getBinderItemDetail() {
			return binderItemDetail;
		}

		public void setFtPurchasehJpaService(FtPurchasehJpaService ftPurchasehJpaService) {
			this.ftPurchasehJpaService = ftPurchasehJpaService;
		}

		public void setFtPurchasedJpaService(FtPurchasedJpaService ftPurchasedJpaService) {
			this.ftPurchasedJpaService = ftPurchasedJpaService;
		}

		public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
			this.fVendorJpaService = fVendorJpaService;
		}

		public void setfProductJpaService(FProductJpaService fProductJpaService) {
			this.fProductJpaService = fProductJpaService;
		}

		public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
			this.sysvarJpaService = sysvarJpaService;
		}

		public void setItemHeader(FtPurchaseh itemHeader) {
			this.itemHeader = itemHeader;
		}

		public void setItemDetil(FtPurchased itemDetil) {
			this.itemDetil = itemDetil;
		}

		public void setBeanItemContainerProduct(
				BeanItemContainer<FProduct> beanItemContainerProduct) {
			this.beanItemContainerProduct = beanItemContainerProduct;
		}

		public void setBinderItemDetail(BeanFieldGroup<FtPurchased> binderItemDetail) {
			this.binderItemDetail = binderItemDetail;
		}

		public FStockJpaService getfStockJpaService() {
			return fStockJpaService;
		}

		public void setfStockJpaService(FStockJpaService fStockJpaService) {
			this.fStockJpaService = fStockJpaService;
		}

		public TransaksiHelper getTransaksiHelper() {
			return transaksiHelper;
		}

		public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
			this.transaksiHelper = transaksiHelper;
		}

		public FtPurchasedPK getFtPurchasedPK() {
			return ftPurchasedPK;
		}

		public void setFtPurchasedPK(FtPurchasedPK ftPurchasedPK) {
			this.ftPurchasedPK = ftPurchasedPK;
		}

		public ProductAndStockHelper getProductAndStockHelper() {
			return productAndStockHelper;
		}

		public void setProductAndStockHelper(ProductAndStockHelper productAndStockHelper) {
			this.productAndStockHelper = productAndStockHelper;
		}

		
	
	
}
