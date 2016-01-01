package org.erp.distribution.master.promoanddiskon.aktifitaspromosi.reports;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FPromoJpaService2;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSalesdPromoTprbJpaService;
import org.erp.distribution.jpaservice.FtSalesdPromoTpruDiscJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservicerep.LapAktifitasPromoListJpaService;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FPromo;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesdPK;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class LapAktifitasPromosiModel extends CustomComponent{
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();
	//1. DAO SERVICE
	private SysvarJpaService sysvarJpaService;

	private FProductJpaService fProductJpaService;
	private FProductgroupJpaService fProductgroupJpaService;
	private FPromoJpaService2 fPromoJpaService;
	
	private FtSalesdPromoTprbJpaService ftSalesdPromoTprbJpaService;
	private FtSalesdPromoTpruDiscJpaService ftSalesdPromoTpruDiscJpaService;

	private LapAktifitasPromoListJpaService lapAktifitasPromoListJpaService;

	
	
//2. ENTITY		
//	protected MenuAccessTemp menuAccessTemp;
	private BeanItemContainer<FPromo> beanItemContainerFPromo = 
			new BeanItemContainer<FPromo>(FPromo.class);
	
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	
//5. Binder (BeanFieldGroup)
			
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public LapAktifitasPromosiModel(){
		initVariable();
		initVariableData();
		
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
//		setProductAndStockHelper((((DashboardUI) getUI().getCurrent()).getProductAndStockHelper()));
		
		setfProductgroupJpaService((((DashboardUI) getUI().getCurrent()).getfProductgroupJpaService()));
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		setfPromoJpaService((((DashboardUI) getUI().getCurrent()).getFpromoJpaService()));
		
		setFtSalesdPromoTprbJpaService((((DashboardUI) getUI().getCurrent()).getFtSalesdPromoTprbJpaService()));
		setFtSalesdPromoTpruDiscJpaService((((DashboardUI) getUI().getCurrent()).getFtSalesdPromoTpruDiscJpaService()));
		
		setLapAktifitasPromoListJpaService((((DashboardUI) getUI().getCurrent()).getLapAktifitasPromoListJpaService()));
		
		
	}

	public void initVariableData(){
		reload();			
	}
	
	public void reload(){
		beanItemContainerFPromo.removeAllContainerFilters();
		
		beanItemContainerFPromo.addAll(fPromoJpaService.findAll());
		
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public FProductJpaService getfProductJpaService() {
		return fProductJpaService;
	}
	public FProductgroupJpaService getfProductgroupJpaService() {
		return fProductgroupJpaService;
	}
	public FPromoJpaService2 getfPromoJpaService() {
		return fPromoJpaService;
	}
	public ProductAndStockHelper getProductAndStockHelper() {
		return productAndStockHelper;
	}
	public BeanItemContainer<FPromo> getBeanItemContainerFPromo() {
		return beanItemContainerFPromo;
	}
	public String getOperationStatus() {
		return OperationStatus;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setfProductJpaService(FProductJpaService fProductJpaService) {
		this.fProductJpaService = fProductJpaService;
	}
	public void setfProductgroupJpaService(
			FProductgroupJpaService fProductgroupJpaService) {
		this.fProductgroupJpaService = fProductgroupJpaService;
	}
	public void setfPromoJpaService(FPromoJpaService2 fPromoJpaService) {
		this.fPromoJpaService = fPromoJpaService;
	}
	public void setProductAndStockHelper(ProductAndStockHelper productAndStockHelper) {
		this.productAndStockHelper = productAndStockHelper;
	}
	public void setBeanItemContainerFPromo(
			BeanItemContainer<FPromo> beanItemContainerFPromo) {
		this.beanItemContainerFPromo = beanItemContainerFPromo;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	public LapAktifitasPromoListJpaService getLapAktifitasPromoListJpaService() {
		return lapAktifitasPromoListJpaService;
	}
	public void setLapAktifitasPromoListJpaService(
			LapAktifitasPromoListJpaService lapAktifitasPromoListJpaService) {
		this.lapAktifitasPromoListJpaService = lapAktifitasPromoListJpaService;
	}
	public FtSalesdPromoTprbJpaService getFtSalesdPromoTprbJpaService() {
		return ftSalesdPromoTprbJpaService;
	}
	public FtSalesdPromoTpruDiscJpaService getFtSalesdPromoTpruDiscJpaService() {
		return ftSalesdPromoTpruDiscJpaService;
	}
	public void setFtSalesdPromoTprbJpaService(
			FtSalesdPromoTprbJpaService ftSalesdPromoTprbJpaService) {
		this.ftSalesdPromoTprbJpaService = ftSalesdPromoTprbJpaService;
	}
	public void setFtSalesdPromoTpruDiscJpaService(
			FtSalesdPromoTpruDiscJpaService ftSalesdPromoTpruDiscJpaService) {
		this.ftSalesdPromoTpruDiscJpaService = ftSalesdPromoTpruDiscJpaService;
	}
	
	
	
}
