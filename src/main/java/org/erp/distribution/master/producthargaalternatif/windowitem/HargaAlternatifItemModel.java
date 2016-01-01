package org.erp.distribution.master.producthargaalternatif.windowitem;

import java.util.Date;
import java.util.Set;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FtOpnamedJpaService;
import org.erp.distribution.jpaservice.FtOpnamehJpaService;
import org.erp.distribution.jpaservice.FtPriceAltdJpaService;
import org.erp.distribution.jpaservice.FtPriceAlthJpaService;
import org.erp.distribution.jpaservice.FtPricedJpaService;
import org.erp.distribution.jpaservice.FtPricehJpaService;
import org.erp.distribution.jpaservice.FtPurchasedJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.FtStocktransferdJpaService;
import org.erp.distribution.jpaservice.FtStocktransferhJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FtOpnamed;
import org.erp.distribution.model.FtOpnamedPK;
import org.erp.distribution.model.FtOpnameh;
import org.erp.distribution.model.FtPriceAltd;
import org.erp.distribution.model.FtPriceAltdPK;
import org.erp.distribution.model.FtPriceAlth;
import org.erp.distribution.model.FtPriced;
import org.erp.distribution.model.FtPricedPK;
import org.erp.distribution.model.FtPriceh;
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

public class HargaAlternatifItemModel extends CustomComponent {
	
	//1. DAO SERVICE
		private SysvarJpaService sysvarJpaService;
		private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	
		private FtPriceAlthJpaService ftPriceAlthJpaService;
		private FtPriceAltdJpaService ftPriceAltdJpaService;
		private FVendorJpaService fVendorJpaService;
		private FProductJpaService fProductJpaService;
		private FStockJpaService fStockJpaService;
		
	//2. ENTITY		
		protected FtPriceAlth itemHeader = new FtPriceAlth();
		protected FtPriceAltd itemDetil = new FtPriceAltd();
		protected FtPriceAltdPK itemDetilPK = new FtPriceAltdPK();		
//		protected MenuAccessTemp menuAccessTemp;
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FProduct> beanItemContainerProduct = 
				new BeanItemContainer<FProduct>(FProduct.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FtPriceAltd> binderItemDetail = 
				new BeanFieldGroup<FtPriceAltd>(FtPriceAltd.class);
				
		//OTHERS
		protected String OperationStatus = "OPEN";

		public String getOperationStatus() {
			return OperationStatus;
		}

		public void setOperationStatus(String operationStatus) {
			OperationStatus = operationStatus;
		}

		public HargaAlternatifItemModel(){
			initVariable();
			initVariableData();
			
			
		}
		
		public void initVariable(){
			setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//			setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
			
			setFtPriceAlthJpaService((((DashboardUI) getUI().getCurrent()).getFtPriceAlthJpaService()));
			setFtPriceAltdJpaService((((DashboardUI) getUI().getCurrent()).getFtPriceAltdJpaService()));
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

		public FtPriceAlthJpaService getFtPriceAlthJpaService() {
			return ftPriceAlthJpaService;
		}

		public FtPriceAltdJpaService getFtPriceAltdJpaService() {
			return ftPriceAltdJpaService;
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

		public FtPriceAlth getItemHeader() {
			return itemHeader;
		}

		public FtPriceAltd getItemDetil() {
			return itemDetil;
		}

		public FtPriceAltdPK getItemDetilPK() {
			return itemDetilPK;
		}

		public BeanItemContainer<FProduct> getBeanItemContainerProduct() {
			return beanItemContainerProduct;
		}

		public BeanFieldGroup<FtPriceAltd> getBinderItemDetail() {
			return binderItemDetail;
		}

		public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
			this.sysvarJpaService = sysvarJpaService;
		}

		public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
			this.transaksiHelper = transaksiHelper;
		}

		public void setFtPriceAlthJpaService(FtPriceAlthJpaService ftPriceAlthJpaService) {
			this.ftPriceAlthJpaService = ftPriceAlthJpaService;
		}

		public void setFtPriceAltdJpaService(FtPriceAltdJpaService ftPriceAltdJpaService) {
			this.ftPriceAltdJpaService = ftPriceAltdJpaService;
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

		public void setItemHeader(FtPriceAlth itemHeader) {
			this.itemHeader = itemHeader;
		}

		public void setItemDetil(FtPriceAltd itemDetil) {
			this.itemDetil = itemDetil;
		}

		public void setItemDetilPK(FtPriceAltdPK itemDetilPK) {
			this.itemDetilPK = itemDetilPK;
		}

		public void setBeanItemContainerProduct(
				BeanItemContainer<FProduct> beanItemContainerProduct) {
			this.beanItemContainerProduct = beanItemContainerProduct;
		}

		public void setBinderItemDetail(BeanFieldGroup<FtPriceAltd> binderItemDetail) {
			this.binderItemDetail = binderItemDetail;
		}


	
}
