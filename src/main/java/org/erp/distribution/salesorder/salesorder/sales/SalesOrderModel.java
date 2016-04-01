package org.erp.distribution.salesorder.salesorder.sales;

import java.util.ArrayList;
import java.util.Iterator;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.ar.util.SaldoPiutangCust;
import org.erp.distribution.ar.util.SaldoPiutangCustImpl;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FParamDiskonItemVendorJpaService;
import org.erp.distribution.jpaservice.FParamDiskonJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FPromoJpaService2;
import org.erp.distribution.jpaservice.FSalesmanJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtArpaymentdJpaService;
import org.erp.distribution.jpaservice.FtArpaymenthJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSalesdPromoTprbJpaService;
import org.erp.distribution.jpaservice.FtSalesdPromoTpruCbJpaService;
import org.erp.distribution.jpaservice.FtSalesdPromoTpruDiscJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FParamDiskonNota;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.User;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.SysvarHelper;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class SalesOrderModel extends CustomComponent{
	
	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	private ProductAndStockHelper productAndStockHelper= new ProductAndStockHelper();
	//1. DAO SERVICE
		private FtSaleshJpaService ftSaleshJpaService;
		private FtSalesdJpaService ftSalesdJpaService;
			private FtSalesdPromoTprbJpaService ftSalesdPromoTprbJpaService;
			private FtSalesdPromoTpruDiscJpaService ftSalesdPromoTpruDiscJpaService ;
			private FtSalesdPromoTpruCbJpaService ftSalesdPromoTpruCbJpaService;
		private FSalesmanJpaService fSalesmanJpaService;
		private FCustomerJpaService fCustomerJpaService;
		private FWarehouseJpaService fWarehouseJpaService;
		private FPromoJpaService2 fPromoJpaService2;
		
		private SysvarJpaService sysvarJpaService;
		private SysvarHelper sysvarHelper;
		private FParamDiskonJpaService fParamDiskonJpaService;
		private FParamDiskonItemVendorJpaService fParamDiskonItemVendorJpaService;

		private FtArpaymenthJpaService ftArpaymenthJpaService ;
		private FtArpaymentdJpaService ftArpaymentdJpaService;
		private SaldoPiutangCust saldoPiutangCust = new SaldoPiutangCustImpl();
		
	//2. ENTITY
		protected User userActive = new User();
		
		protected FtSalesh itemHeader = new FtSalesh();
		protected FtSalesh itemHeaderTemp = new FtSalesh();
		
		protected FParamDiskonNota paramDiskonNota = new FParamDiskonNota();
		
		protected FtSalesd itemDetil = new FtSalesd();
		protected FtSalesd itemDetilBookmark = new FtSalesd();
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FtSalesh> beanItemContainerHeader = 
				new BeanItemContainer<FtSalesh>(FtSalesh.class);
		private BeanItemContainer<FtSalesh> beanItemContainerHeaderSearch = 
				new BeanItemContainer<FtSalesh>(FtSalesh.class);

		private BeanItemContainer<FtSalesd> beanItemContainerDetil = 
				new BeanItemContainer<FtSalesd>(FtSalesd.class);

		private BeanItemContainer<FSalesman> beanItemContainerSalesman = 
				new BeanItemContainer<FSalesman>(FSalesman.class);
		private BeanItemContainer<FCustomer> beanItemContainerCustomer = 
				new BeanItemContainer<FCustomer>(FCustomer.class);
		private BeanItemContainer<FWarehouse> beanItemContainerWarehouse = 
				new BeanItemContainer<FWarehouse>(FWarehouse.class);
		
		private FProductJpaService fProductJpaService;
		private BeanItemContainer<FProduct> beanItemContainerProduct = 
				new BeanItemContainer<FProduct>(FProduct.class);
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FtSalesh> binderHeader = 
				new BeanFieldGroup<FtSalesh>(FtSalesh.class);
		private BeanFieldGroup<FtSalesd> binderDetil = 
				new BeanFieldGroup<FtSalesd>(FtSalesd.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public SalesOrderModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
//		setProductAndStockHelper((((DashboardUI) getUI().getCurrent()).getProductAndStockHelper()));
		setSysvarHelper((((DashboardUI) getUI().getCurrent()).getSysvarHelper()));
		
		setFtSaleshJpaService((((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService()));
		setFtSalesdJpaService((((DashboardUI) getUI().getCurrent()).getFtSalesdJpaService()));
			setFtSalesdPromoTprbJpaService((((DashboardUI) getUI().getCurrent()).getFtSalesdPromoTprbJpaService()));
			setFtSalesdPromoTpruDiscJpaService((((DashboardUI) getUI().getCurrent()).getFtSalesdPromoTpruDiscJpaService()));
			setFtSalesdPromoTpruCbJpaService((((DashboardUI) getUI().getCurrent()).getFtSalesdPromoTpruCbJpaService()));

		setfPromoJpaService2((((DashboardUI) getUI().getCurrent()).getFpromoJpaService()));
		
		
		setfSalesmanJpaService((((DashboardUI) getUI().getCurrent()).getfSalesmanJpaService()));
		setfCustomerJpaService((((DashboardUI) getUI().getCurrent()).getfCustomerJpaService()));
		setfWarehouseJpaService((((DashboardUI) getUI().getCurrent()).getfWarehouseJpaService()));
		
		setFtArpaymenthJpaService(((DashboardUI) getUI().getCurrent()).getFtArpaymenthJpaService());
		setFtArpaymentdJpaService(((DashboardUI) getUI().getCurrent()).getFtArpaymentdJpaService());
		setfParamDiskonJpaService((((DashboardUI) getUI().getCurrent()).getfParamDiskonJpaService()));
		setfParamDiskonItemVendorJpaService((((DashboardUI) getUI().getCurrent()).getfParamDiskonItemVendorJpaService()));

		
		setfProductJpaService((((DashboardUI) getUI().getCurrent()).getfProductJpaService()));
		userActive = ((DashboardUI) getUI().getCurrent()).getUserActive();
		
		
		
	}
	public void initVariableData(){
		reload();		
		
	}
	public void reload(){
		beanItemContainerHeader.removeAllContainerFilters();
		beanItemContainerHeader.removeAllItems();
		
		beanItemContainerHeader.addAll(ftSaleshJpaService.findAllTipeFaktur("F"));
		beanItemContainerHeader.addNestedContainerProperty("fsalesmanBean.spcode");
		beanItemContainerHeader.addNestedContainerProperty("fsalesmanBean.spname");
		beanItemContainerHeader.addNestedContainerProperty("fcustomerBean.custno");
		beanItemContainerHeader.addNestedContainerProperty("fcustomerBean.custname");
		
		beanItemContainerHeader.addNestedContainerProperty("fwarehouseBean.id");
		beanItemContainerHeader.addNestedContainerProperty("fwarehouseBean.description");

		beanItemContainerHeader.sort(new String[]{"orderno", "invoiceno"}, new boolean[] {false, false});
		
//		beanItemContainerDetil.addAll(ftPurchasedJpaService.findAll());
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pcode");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.pname");
		beanItemContainerDetil.addNestedContainerProperty("fproductBean.packaging");
		
		beanItemContainerDetil.addNestedContainerProperty("id.freegood");
		
		try{
			beanItemContainerSalesman.addAll(fSalesmanJpaService.findAll());
		} catch(Exception ex){}
		
		beanItemContainerCustomer.addAll(fCustomerJpaService.findAll());
		
		//USER DENGAN DIVISI HANYA BISA WAREHOUSENYA SAJA
		if (userActive.getFdivisionBean()==null){
			beanItemContainerWarehouse.addAll(fWarehouseJpaService.findAllMainWareHouseActive());
		} else {
			Iterator<FWarehouse> iterFWarehouse = new ArrayList<FWarehouse>(userActive.getFdivisionBean().getFwarehouseSet()).iterator();
			while (iterFWarehouse.hasNext()) {
				beanItemContainerWarehouse.addBean(iterFWarehouse.next());
			}			
		}
		
		beanItemContainerProduct.addAll(fProductJpaService.findAllActive());
		
		
		//JIKA MASIH KOSONG MAKA ADD
		if (fParamDiskonJpaService.findAll().size() == 0) {
			FParamDiskonNota item = new FParamDiskonNota();

			item.setNominal1(0.0);
			item.setDiskon1(0.0);
			item.setDiskon1plus(0.0);
			
			item.setNominal2(0.0);
			item.setDiskon2(0.0);
			item.setDiskon2plus(0.0);
			
			item.setNominal3(0.0);
			item.setDiskon3(0.0);
			item.setDiskon3plus(0.0);
			
			item.setNominal4(0.0);
			item.setDiskon4(0.0);
			item.setDiskon4plus(0.0);
			
			item.setNominal5(0.0);
			item.setDiskon5(0.0);
			item.setDiskon5plus(0.0);
			
			item.setAllsubgrup(true);
			item.setAlltunaikredit(true);
			
			fParamDiskonJpaService.createObject(item);
			
		}		
		paramDiskonNota = fParamDiskonJpaService.findAll().get(0);
	}

	public FtSaleshJpaService getFtSaleshJpaService() {
		return ftSaleshJpaService;
	}

	public FtSalesdJpaService getFtSalesdJpaService() {
		return ftSalesdJpaService;
	}

	public FSalesmanJpaService getfSalesmanJpaService() {
		return fSalesmanJpaService;
	}

	public FCustomerJpaService getfCustomerJpaService() {
		return fCustomerJpaService;
	}

	public FWarehouseJpaService getfWarehouseJpaService() {
		return fWarehouseJpaService;
	}

	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}

	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}

	public ProductAndStockHelper getProductAndStockHelper() {
		return productAndStockHelper;
	}

	public FtSalesh getItemHeader() {
		return itemHeader;
	}

	public FtSalesd getItemDetil() {
		return itemDetil;
	}

	public FtSalesd getItemDetilBookmark() {
		return itemDetilBookmark;
	}

	public BeanItemContainer<FtSalesh> getBeanItemContainerHeader() {
		return beanItemContainerHeader;
	}

	public BeanItemContainer<FtSalesh> getBeanItemContainerHeaderSearch() {
		return beanItemContainerHeaderSearch;
	}

	public BeanItemContainer<FtSalesd> getBeanItemContainerDetil() {
		return beanItemContainerDetil;
	}

	public BeanItemContainer<FSalesman> getBeanItemContainerSalesman() {
		return beanItemContainerSalesman;
	}

	public BeanItemContainer<FCustomer> getBeanItemContainerCustomer() {
		return beanItemContainerCustomer;
	}

	public BeanItemContainer<FWarehouse> getBeanItemContainerWarehouse() {
		return beanItemContainerWarehouse;
	}

	public BeanFieldGroup<FtSalesh> getBinderHeader() {
		return binderHeader;
	}

	public BeanFieldGroup<FtSalesd> getBinderDetil() {
		return binderDetil;
	}

	public String getOperationStatus() {
		return OperationStatus;
	}

	public void setFtSaleshJpaService(FtSaleshJpaService ftSaleshJpaService) {
		this.ftSaleshJpaService = ftSaleshJpaService;
	}

	public void setFtSalesdJpaService(FtSalesdJpaService ftSalesdJpaService) {
		this.ftSalesdJpaService = ftSalesdJpaService;
	}

	public void setfSalesmanJpaService(FSalesmanJpaService fSalesmanJpaService) {
		this.fSalesmanJpaService = fSalesmanJpaService;
	}

	public void setfCustomerJpaService(FCustomerJpaService fCustomerJpaService) {
		this.fCustomerJpaService = fCustomerJpaService;
	}

	public void setfWarehouseJpaService(FWarehouseJpaService fWarehouseJpaService) {
		this.fWarehouseJpaService = fWarehouseJpaService;
	}

	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}

	public void setTransaksiHelper(TransaksiHelper transaksiHelper) {
		this.transaksiHelper = transaksiHelper;
	}

	public void setProductAndStockHelper(ProductAndStockHelper productAndStockHelper) {
		this.productAndStockHelper = productAndStockHelper;
	}

	public void setItemHeader(FtSalesh itemHeader) {
		this.itemHeader = itemHeader;
	}

	public void setItemDetil(FtSalesd itemDetil) {
		this.itemDetil = itemDetil;
	}

	public void setItemDetilBookmark(FtSalesd itemDetilBookmark) {
		this.itemDetilBookmark = itemDetilBookmark;
	}

	public void setBeanItemContainerHeader(
			BeanItemContainer<FtSalesh> beanItemContainerHeader) {
		this.beanItemContainerHeader = beanItemContainerHeader;
	}

	public void setBeanItemContainerHeaderSearch(
			BeanItemContainer<FtSalesh> beanItemContainerHeaderSearch) {
		this.beanItemContainerHeaderSearch = beanItemContainerHeaderSearch;
	}

	public void setBeanItemContainerDetil(
			BeanItemContainer<FtSalesd> beanItemContainerDetil) {
		this.beanItemContainerDetil = beanItemContainerDetil;
	}

	public void setBeanItemContainerSalesman(
			BeanItemContainer<FSalesman> beanItemContainerSalesman) {
		this.beanItemContainerSalesman = beanItemContainerSalesman;
	}

	public void setBeanItemContainerCustomer(
			BeanItemContainer<FCustomer> beanItemContainerCustomer) {
		this.beanItemContainerCustomer = beanItemContainerCustomer;
	}

	public void setBeanItemContainerWarehouse(
			BeanItemContainer<FWarehouse> beanItemContainerWarehouse) {
		this.beanItemContainerWarehouse = beanItemContainerWarehouse;
	}

	public void setBinderHeader(BeanFieldGroup<FtSalesh> binderHeader) {
		this.binderHeader = binderHeader;
	}

	public void setBinderDetil(BeanFieldGroup<FtSalesd> binderDetil) {
		this.binderDetil = binderDetil;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}

	public SysvarHelper getSysvarHelper() {
		return sysvarHelper;
	}

	public FtSalesh getItemHeaderTemp() {
		return itemHeaderTemp;
	}

	public void setSysvarHelper(SysvarHelper sysvarHelper) {
		this.sysvarHelper = sysvarHelper;
	}

	public void setItemHeaderTemp(FtSalesh itemHeaderTemp) {
		this.itemHeaderTemp = itemHeaderTemp;
	}

	public FtArpaymenthJpaService getFtArpaymenthJpaService() {
		return ftArpaymenthJpaService;
	}

	public FtArpaymentdJpaService getFtArpaymentdJpaService() {
		return ftArpaymentdJpaService;
	}

	public void setFtArpaymenthJpaService(
			FtArpaymenthJpaService ftArpaymenthJpaService) {
		this.ftArpaymenthJpaService = ftArpaymenthJpaService;
	}

	public void setFtArpaymentdJpaService(
			FtArpaymentdJpaService ftArpaymentdJpaService) {
		this.ftArpaymentdJpaService = ftArpaymentdJpaService;
	}

	public FParamDiskonJpaService getfParamDiskonJpaService() {
		return fParamDiskonJpaService;
	}

	public void setfParamDiskonJpaService(
			FParamDiskonJpaService fParamDiskonJpaService) {
		this.fParamDiskonJpaService = fParamDiskonJpaService;
	}

	public FParamDiskonNota getParamDiskonNota() {
		return paramDiskonNota;
	}

	public void setParamDiskonNota(FParamDiskonNota paramDiskonNota) {
		this.paramDiskonNota = paramDiskonNota;
	}

	public FtSalesdPromoTprbJpaService getFtSalesdPromoTprbJpaService() {
		return ftSalesdPromoTprbJpaService;
	}

	public FtSalesdPromoTpruDiscJpaService getFtSalesdPromoTpruDiscJpaService() {
		return ftSalesdPromoTpruDiscJpaService;
	}

	public FtSalesdPromoTpruCbJpaService getFtSalesdPromoTpruCbJpaService() {
		return ftSalesdPromoTpruCbJpaService;
	}

	public void setFtSalesdPromoTprbJpaService(
			FtSalesdPromoTprbJpaService ftSalesdPromoTprbJpaService) {
		this.ftSalesdPromoTprbJpaService = ftSalesdPromoTprbJpaService;
	}

	public void setFtSalesdPromoTpruDiscJpaService(
			FtSalesdPromoTpruDiscJpaService ftSalesdPromoTpruDiscJpaService) {
		this.ftSalesdPromoTpruDiscJpaService = ftSalesdPromoTpruDiscJpaService;
	}

	public void setFtSalesdPromoTpruCbJpaService(
			FtSalesdPromoTpruCbJpaService ftSalesdPromoTpruCbJpaService) {
		this.ftSalesdPromoTpruCbJpaService = ftSalesdPromoTpruCbJpaService;
	}

	public FPromoJpaService2 getfPromoJpaService2() {
		return fPromoJpaService2;
	}

	public void setfPromoJpaService2(FPromoJpaService2 fPromoJpaService2) {
		this.fPromoJpaService2 = fPromoJpaService2;
	}

	public User getUserActive() {
		return userActive;
	}

	public void setUserActive(User userActive) {
		this.userActive = userActive;
	}

	public FParamDiskonItemVendorJpaService getfParamDiskonItemVendorJpaService() {
		return fParamDiskonItemVendorJpaService;
	}

	public void setfParamDiskonItemVendorJpaService(
			FParamDiskonItemVendorJpaService fParamDiskonItemVendorJpaService) {
		this.fParamDiskonItemVendorJpaService = fParamDiskonItemVendorJpaService;
	}

	public FProductJpaService getfProductJpaService() {
		return fProductJpaService;
	}

	public BeanItemContainer<FProduct> getBeanItemContainerProduct() {
		return beanItemContainerProduct;
	}

	public void setfProductJpaService(FProductJpaService fProductJpaService) {
		this.fProductJpaService = fProductJpaService;
	}

	public void setBeanItemContainerProduct(
			BeanItemContainer<FProduct> beanItemContainerProduct) {
		this.beanItemContainerProduct = beanItemContainerProduct;
	}

	public SaldoPiutangCust getSaldoPiutangCust() {
		return saldoPiutangCust;
	}

	public void setSaldoPiutangCust(SaldoPiutangCust saldoPiutangCust) {
		this.saldoPiutangCust = saldoPiutangCust;
	}

	
	
	
}
