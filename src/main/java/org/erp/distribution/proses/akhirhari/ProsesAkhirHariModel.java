package org.erp.distribution.proses.akhirhari;


import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FtArpaymenthJpaService;
import org.erp.distribution.jpaservice.FtOpnamehJpaService;
import org.erp.distribution.jpaservice.FtPurchasehJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.FtPricehJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ProsesAkhirHariModel extends CustomComponent{

	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();

	private SysvarJpaService sysvarJpaService;
	
	private FtPurchasehJpaService ftPurchasehJpaService;
	private FtSaleshJpaService ftSaleshJpaService;
	private FtArpaymenthJpaService ftArpaymenthJpaService;
	private FtOpnamehJpaService ftOpnamehJpaService;
	private FtPricehJpaService ftSpricehJpaService;
	
	
	
	public ProsesAkhirHariModel(){
		initVariable();		
		initVariableData();
		
	}
	public void initVariable(){

		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
//		setProductAndStockHelper((((DashboardUI) getUI().getCurrent()).getProductAndStockHelper()));

		setFtPurchasehJpaService((((DashboardUI) getUI().getCurrent()).getFtPurchasehJpaService()));
		setFtSaleshJpaService((((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService()));
		setFtArpaymenthJpaService((((DashboardUI) getUI().getCurrent()).getFtArpaymenthJpaService()));
		setFtOpnamehJpaService((((DashboardUI) getUI().getCurrent()).getFtOpnamehJpaService()));
		
		
	}
	public void initVariableData(){
		
		
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public FtPurchasehJpaService getFtPurchasehJpaService() {
		return ftPurchasehJpaService;
	}
	public FtSaleshJpaService getFtSaleshJpaService() {
		return ftSaleshJpaService;
	}
	public FtArpaymenthJpaService getFtArpaymenthJpaService() {
		return ftArpaymenthJpaService;
	}
	public FtOpnamehJpaService getFtOpnamehJpaService() {
		return ftOpnamehJpaService;
	}
	public FtPricehJpaService getFtSpricehJpaService() {
		return ftSpricehJpaService;
	}
	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}
	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}
	public void setFtPurchasehJpaService(FtPurchasehJpaService ftPurchasehJpaService) {
		this.ftPurchasehJpaService = ftPurchasehJpaService;
	}
	public void setFtSaleshJpaService(FtSaleshJpaService ftSaleshJpaService) {
		this.ftSaleshJpaService = ftSaleshJpaService;
	}
	public void setFtArpaymenthJpaService(
			FtArpaymenthJpaService ftArpaymenthJpaService) {
		this.ftArpaymenthJpaService = ftArpaymenthJpaService;
	}
	public void setFtOpnamehJpaService(FtOpnamehJpaService ftOpnamehJpaService) {
		this.ftOpnamehJpaService = ftOpnamehJpaService;
	}
	public void setFtSpricehJpaService(FtPricehJpaService ftSpricehJpaService) {
		this.ftSpricehJpaService = ftSpricehJpaService;
	}
	public ProductAndStockHelper getProductAndStockHelper() {
		return productAndStockHelper;
	}
	public void setProductAndStockHelper(ProductAndStockHelper productAndStockHelper) {
		this.productAndStockHelper = productAndStockHelper;
	}
	
	
}
