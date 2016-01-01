package org.erp.distribution.master.systemsetting.parameterdiskon;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FCustomerJpaService;
import org.erp.distribution.jpaservice.FCustomergroupJpaService;
import org.erp.distribution.jpaservice.FCustomersubgroupJpaService;
import org.erp.distribution.jpaservice.FParamDiskonJpaService;
import org.erp.distribution.jpaservice.FProductJpaService;
import org.erp.distribution.jpaservice.FProductgroupJpaService;
import org.erp.distribution.jpaservice.FSalesmanJpaService;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FWarehouseJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FParamDiskonNota;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSalesman;
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

public class ParameterDiskonModel extends CustomComponent{

	private TransaksiHelper transaksiHelper =new TransaksiHelperImpl();
	//1. DAO SERVICE
	private SysvarJpaService sysvarJpaService;

	private FParamDiskonJpaService fParamDiskonJpaService;
	private FCustomersubgroupJpaService fCustomersubgroupJpaService;
	
	
//2. ENTITY		
//	protected MenuAccessTemp menuAccessTemp;
	private BeanItemContainer<FCustomersubgroup> beanItemContainerCustomersubgroup = 
			new BeanItemContainer<FCustomersubgroup>(FCustomersubgroup.class);
	
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	protected FParamDiskonNota itemHeader = new FParamDiskonNota();
	
//5. Binder (BeanFieldGroup)
	private BeanFieldGroup<FParamDiskonNota> binderFParamDiskonNota = 
			new BeanFieldGroup<FParamDiskonNota>(FParamDiskonNota.class);
			
	//OTHERS
	protected String OperationStatus = "OPEN";
	
	public ParameterDiskonModel(){
		initVariable();
		initVariableData();
		
	}
	public void initVariable(){
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
//		setTransaksiHelper((((DashboardUI) getUI().getCurrent()).getTransaksiHelper()));
		
		setfParamDiskonJpaService((((DashboardUI) getUI().getCurrent()).getfParamDiskonJpaService()));
		setfCustomersubgroupJpaService((((DashboardUI) getUI().getCurrent()).getfCustomersubgroupJpaService()));
		
	}

	public void initVariableData(){
		reload();			
		
	}
	
	public void reload(){
		beanItemContainerCustomersubgroup.removeAllContainerFilters();
		beanItemContainerCustomersubgroup.removeAllItems();
		
		beanItemContainerCustomersubgroup.addAll(fCustomersubgroupJpaService.findAll());
		
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
		
		itemHeader = new FParamDiskonNota();
		itemHeader = fParamDiskonJpaService.findAll().get(0);
		
	}
	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}
	public TransaksiHelper getTransaksiHelper() {
		return transaksiHelper;
	}
	public FCustomersubgroupJpaService getfCustomersubgroupJpaService() {
		return fCustomersubgroupJpaService;
	}
	public BeanItemContainer<FCustomersubgroup> getBeanItemContainerCustomersubgroup() {
		return beanItemContainerCustomersubgroup;
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
	public void setfCustomersubgroupJpaService(
			FCustomersubgroupJpaService fCustomersubgroupJpaService) {
		this.fCustomersubgroupJpaService = fCustomersubgroupJpaService;
	}
	public void setBeanItemContainerCustomersubgroup(
			BeanItemContainer<FCustomersubgroup> beanItemContainerCustomersubgroup) {
		this.beanItemContainerCustomersubgroup = beanItemContainerCustomersubgroup;
	}
	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}
	
	public BeanFieldGroup<FParamDiskonNota> getBinderFParamDiskonNota() {
		return binderFParamDiskonNota;
	}
	public void setBinderFParamDiskonNota(
			BeanFieldGroup<FParamDiskonNota> binderFParamDiskonNota) {
		this.binderFParamDiskonNota = binderFParamDiskonNota;
	}
	public FParamDiskonJpaService getfParamDiskonJpaService() {
		return fParamDiskonJpaService;
	}
	public void setfParamDiskonJpaService(
			FParamDiskonJpaService fParamDiskonJpaService) {
		this.fParamDiskonJpaService = fParamDiskonJpaService;
	}
	public FParamDiskonNota getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(FParamDiskonNota itemHeader) {
		this.itemHeader = itemHeader;
	}
	
	
	
}
