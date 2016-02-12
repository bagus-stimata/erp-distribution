package org.erp.distribution.master.promoanddiskon.aktifitaspromosi.daftarpromoberjalan;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FPromoJpaService2;
import org.erp.distribution.jpaservice.FSalesmanJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservicehp.STeknisiJpaService;
import org.erp.distribution.jpaservicehp.StServiceJpaService;
import org.erp.distribution.jpaservicerep.LapTemplate1JpaService;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesdPK;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.STeknisi;
import org.erp.distribution.model.ZLapTemplate1;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class DaftarPromoBerjalanModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
	private SysvarJpaService sysvarJpaService;

	private FPromoJpaService2 fPromoJpaService;
//	private FProductJpaService fProductJpaService;
	
//	private LapTemplate1JpaService lapTemplate1JpaService;
	
//2. ENTITY		
//	protected MenuAccessTemp menuAccessTemp;
//	private BeanItemContainer<FWarehouse> beanItemContainerWarehouse = 
//			new BeanItemContainer<FWarehouse>(FWarehouse.class);
	
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	
//5. Binder (BeanFieldGroup)
			
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public DaftarPromoBerjalanModel(){
		initVariable();
		initVariableData();
		
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfPromoJpaService((((DashboardUI) getUI().getCurrent()).getFpromoJpaService()));
		
		
	}

	public void initVariableData(){
		reload();			
	}
	
	public void reload(){
//		beanItemContainerProductgroup.removeAllContainerFilters();
//		beanItemContainerProductgroup.removeAllItems();
		
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	public FPromoJpaService2 getfPromoJpaService() {
		return fPromoJpaService;
	}
	public void setfPromoJpaService(FPromoJpaService2 fPromoJpaService) {
		this.fPromoJpaService = fPromoJpaService;
	}
	
	
	
}
