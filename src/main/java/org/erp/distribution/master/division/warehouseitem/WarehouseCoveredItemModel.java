package org.erp.distribution.master.division.warehouseitem;

import java.util.Date;
import java.util.Set;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FVendorJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtOpnamedJpaService;
import org.erp.distribution.jpaservice.FtOpnamehJpaService;
import org.erp.distribution.jpaservice.FtPurchasedJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.FtStocktransferdJpaService;
import org.erp.distribution.jpaservice.FtStocktransferhJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
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

public class WarehouseCoveredItemModel extends CustomComponent {
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
		private SysvarJpaService sysvarJpaService;
	
		private FWarehouseJpaService fWarehouseJpaService;
		
	//2. ENTITY		
		protected FWarehouse itemDetil = new FWarehouse();

		//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FWarehouse> beanItemContainerWarehouse = 
				new BeanItemContainer<FWarehouse>(FWarehouse.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FWarehouse> binderItemDetail = 
				new BeanFieldGroup<FWarehouse>(FWarehouse.class);
				
		//OTHERS
		protected String OperationStatus = "OPEN";

		public String getOperationStatus() {
			return OperationStatus;
		}

		public void setOperationStatus(String operationStatus) {
			OperationStatus = operationStatus;
		}

		public WarehouseCoveredItemModel(){
			initVariable();
			initVariableData();
			
			
		}
		
		public void initVariable(){
			setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//			setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
			
			setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		}

		public void initVariableData(){
			reload();			
		}
		
		public void reload(){
			beanItemContainerWarehouse.removeAllContainerFilters();
			beanItemContainerWarehouse.removeAllItems();
			
			beanItemContainerWarehouse.addAll(fWarehouseJpaService.findAll());
			
		}

		public TransaksiHelper getTransaksiHelper() {
			return transaksiHelper;
		}

		public SysvarJpaService getSysvarJpaService() {
			return sysvarJpaService;
		}

		public FWarehouseJpaService getfWarehouseJpaService() {
			return fWarehouseJpaService;
		}

		public FWarehouse getItemDetil() {
			return itemDetil;
		}

		public BeanItemContainer<FWarehouse> getBeanItemContainerWarehouse() {
			return beanItemContainerWarehouse;
		}

		public BeanFieldGroup<FWarehouse> getBinderItemDetail() {
			return binderItemDetail;
		}

		public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
			this.transaksiHelper = transaksiHelper;
		}

		public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
			this.sysvarJpaService = sysvarJpaService;
		}

		public void setfWarehouseJpaService(FWarehouseJpaService fWarehouseJpaService) {
			this.fWarehouseJpaService = fWarehouseJpaService;
		}

		public void setItemDetil(FWarehouse itemDetil) {
			this.itemDetil = itemDetil;
		}

		public void setBeanItemContainerWarehouse(
				BeanItemContainer<FWarehouse> beanItemContainerWarehouse) {
			this.beanItemContainerWarehouse = beanItemContainerWarehouse;
		}

		public void setBinderItemDetail(BeanFieldGroup<FWarehouse> binderItemDetail) {
			this.binderItemDetail = binderItemDetail;
		}


	
}
