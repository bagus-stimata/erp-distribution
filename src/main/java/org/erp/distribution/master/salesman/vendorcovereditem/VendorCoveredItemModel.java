package org.erp.distribution.master.salesman.vendorcovereditem;

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
import org.erp.distribution.model.FVendor;
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

public class VendorCoveredItemModel extends CustomComponent {
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private SysvarJpaService sysvarJpaService;
	
		private FVendorJpaService fVendorJpaService;
		
	//2. ENTITY		
		protected FVendor itemDetil = new FVendor();

		//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FVendor> beanItemContainerVendor = 
				new BeanItemContainer<FVendor>(FVendor.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FVendor> binderItemDetail = 
				new BeanFieldGroup<FVendor>(FVendor.class);
				
		//OTHERS
		protected String OperationStatus = "OPEN";

		public String getOperationStatus() {
			return OperationStatus;
		}

		public void setOperationStatus(String operationStatus) {
			OperationStatus = operationStatus;
		}

		public VendorCoveredItemModel(){
			initVariable();
			initVariableData();
			
			
		}
		
		public void initVariable(){
			setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//			setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
			
			setfVendorJpaService((((DashboardUI) getUI().getCurrent()).getfVendorJpaService()));
		}

		public void initVariableData(){
			reload();			
		}
		
		public void reload(){
			beanItemContainerVendor.removeAllContainerFilters();
			beanItemContainerVendor.removeAllItems();
			
			beanItemContainerVendor.addAll(fVendorJpaService.findAll());
			
		}

		public SysvarJpaService getSysvarJpaService() {
			return sysvarJpaService;
		}

		public TransaksiHelper getTransaksiHelper() {
			return transaksiHelper;
		}

		public FVendorJpaService getfVendorJpaService() {
			return fVendorJpaService;
		}

		public FVendor getItemDetil() {
			return itemDetil;
		}

		public BeanItemContainer<FVendor> getBeanItemContainerVendor() {
			return beanItemContainerVendor;
		}

		public BeanFieldGroup<FVendor> getBinderItemDetail() {
			return binderItemDetail;
		}

		public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
			this.sysvarJpaService = sysvarJpaService;
		}

		public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
			this.transaksiHelper = transaksiHelper;
		}

		public void setfVendorJpaService(FVendorJpaService fVendorJpaService) {
			this.fVendorJpaService = fVendorJpaService;
		}

		public void setItemDetil(FVendor itemDetil) {
			this.itemDetil = itemDetil;
		}

		public void setBeanItemContainerVendor(
				BeanItemContainer<FVendor> beanItemContainerVendor) {
			this.beanItemContainerVendor = beanItemContainerVendor;
		}

		public void setBinderItemDetail(BeanFieldGroup<FVendor> binderItemDetail) {
			this.binderItemDetail = binderItemDetail;
		}


	
}
