package org.erp.distribution.master.salesman.areacovereditem;

import java.util.Date;
import java.util.Set;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FAreaJpaService;
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
import org.erp.distribution.model.FArea;
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

public class AreaCoveredItemModel extends CustomComponent {
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private SysvarJpaService sysvarJpaService;
	
		private FAreaJpaService fAreaJpaService;
		
	//2. ENTITY		
		protected FArea itemDetil = new FArea();

		//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FArea> beanItemContainerArea = 
				new BeanItemContainer<FArea>(FArea.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FArea> binderItemDetail = 
				new BeanFieldGroup<FArea>(FArea.class);
				
		//OTHERS
		protected String OperationStatus = "OPEN";

		public String getOperationStatus() {
			return OperationStatus;
		}

		public void setOperationStatus(String operationStatus) {
			OperationStatus = operationStatus;
		}

		public AreaCoveredItemModel(){
			initVariable();
			initVariableData();
			
			
		}
		
		public void initVariable(){
			setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//			setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
			
			setfAreaJpaService((((DashboardUI) getUI().getCurrent()).getfAreaJpaService()));
		}

		public void initVariableData(){
			reload();			
		}
		
		public void reload(){
			beanItemContainerArea.removeAllContainerFilters();
			beanItemContainerArea.removeAllItems();
			
			beanItemContainerArea.addAll(fAreaJpaService.findAll());
			
		}

		public SysvarJpaService getSysvarJpaService() {
			return sysvarJpaService;
		}

		public TransaksiHelper getTransaksiHelper() {
			return transaksiHelper;
		}

		public FAreaJpaService getfAreaJpaService() {
			return fAreaJpaService;
		}

		public FArea getItemDetil() {
			return itemDetil;
		}

		public BeanItemContainer<FArea> getBeanItemContainerArea() {
			return beanItemContainerArea;
		}

		public BeanFieldGroup<FArea> getBinderItemDetail() {
			return binderItemDetail;
		}

		public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
			this.sysvarJpaService = sysvarJpaService;
		}

		public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
			this.transaksiHelper = transaksiHelper;
		}

		public void setfAreaJpaService(FAreaJpaService fAreaJpaService) {
			this.fAreaJpaService = fAreaJpaService;
		}

		public void setItemDetil(FArea itemDetil) {
			this.itemDetil = itemDetil;
		}

		public void setBeanItemContainerArea(
				BeanItemContainer<FArea> beanItemContainerArea) {
			this.beanItemContainerArea = beanItemContainerArea;
		}

		public void setBinderItemDetail(BeanFieldGroup<FArea> binderItemDetail) {
			this.binderItemDetail = binderItemDetail;
		}


	
}
