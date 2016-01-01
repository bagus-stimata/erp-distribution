package org.erp.distribution.master.systemsetting.parametersistem;


import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.Sysvar;
import org.erp.distribution.util.SysvarHelper;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ParameterSystemModel extends CustomComponent{
//1. DAO SERVICE
	private SysvarJpaService sysvarService;
//	private DivisionJpaService divisionService;
//2. ENTITY
	protected Sysvar sysvar;
	protected Sysvar newSysvar;
	
	private SysvarHelper sysvarHelper;
//3. LIST >> JIKA PERLU
//4. BeanItemContainer, Jpa Container
	private BeanItemContainer<Sysvar> beanItemContainerSysvar;
	private BeanItemContainer<Sysvar> beanItemContainerSysvarSearch;
	
//	private BeanItemContainer<Division> beanItemContainerDivision;
//5. Binder (BeanFieldGroup)
	private BeanFieldGroup<Sysvar> binderSysvar;
			
//OTHERS
	protected String OperationStatus = "OPEN";

	public ParameterSystemModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
		setSysvarHelper(((DashboardUI) getUI().getCurrent()).getSysvarHelper());
		setSysvarService(((DashboardUI) getUI().getCurrent()).getSysvarJpaService());
//		setDivisionService(((DashboardUI) getUI().getCurrent()).getDivisionService());
		
		sysvar = new Sysvar();
		
		beanItemContainerSysvar = new BeanItemContainer<Sysvar>(Sysvar.class);
		beanItemContainerSysvarSearch = new BeanItemContainer<Sysvar>(Sysvar.class);
//		beanItemContainerDivision = new BeanItemContainer<Division>(Division.class);
		
		binderSysvar = new BeanFieldGroup<Sysvar>(Sysvar.class);
		
	}
	public void initVariableData(){
		reload();
		
	}
	public void reload(){
		beanItemContainerSysvar.removeAllContainerFilters();
		beanItemContainerSysvar.removeAllItems();		
//		beanItemContainerDivision.removeAllContainerFilters();
//		beanItemContainerDivision.removeAllItems();
		
		beanItemContainerSysvar.addAll(sysvarService.findAll());
//		beanItemContainerDivision.addAll(divisionService.findAll());
	}

	public SysvarJpaService getSysvarService() {
		return sysvarService;
	}

	public void setSysvarService(SysvarJpaService sysvarService) {
		this.sysvarService = sysvarService;
	}

	public Sysvar getSysvar() {
		return sysvar;
	}

	public void setSysvar(Sysvar sysvar) {
		this.sysvar = sysvar;
	}

	public Sysvar getNewSysvar() {
		return newSysvar;
	}

	public void setNewSysvar(Sysvar newSysvar) {
		this.newSysvar = newSysvar;
	}

	public BeanItemContainer<Sysvar> getBeanItemContainerSysvar() {
		return beanItemContainerSysvar;
	}

	public void setBeanItemContainerSysvar(
			BeanItemContainer<Sysvar> beanItemContainerSysvar) {
		this.beanItemContainerSysvar = beanItemContainerSysvar;
	}

	public BeanItemContainer<Sysvar> getBeanItemContainerSysvarSearch() {
		return beanItemContainerSysvarSearch;
	}

	public void setBeanItemContainerSysvarSearch(
			BeanItemContainer<Sysvar> beanItemContainerSysvarSearch) {
		this.beanItemContainerSysvarSearch = beanItemContainerSysvarSearch;
	}

	public BeanFieldGroup<Sysvar> getBinderSysvar() {
		return binderSysvar;
	}

	public void setBinderSysvar(BeanFieldGroup<Sysvar> binderSysvar) {
		this.binderSysvar = binderSysvar;
	}

	public String getOperationStatus() {
		return OperationStatus;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}

	public SysvarHelper getSysvarHelper() {
		return sysvarHelper;
	}

	public void setSysvarHelper(SysvarHelper sysvarHelper) {
		this.sysvarHelper = sysvarHelper;
	}
	

	
	
}
