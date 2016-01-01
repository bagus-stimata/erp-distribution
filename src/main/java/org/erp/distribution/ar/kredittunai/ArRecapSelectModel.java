package org.erp.distribution.ar.kredittunai;


import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FDivisionJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.model.FDivision;
import org.erp.distribution.model.FtSalesh;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class ArRecapSelectModel extends CustomComponent{
	//1. DAO SERVICE
		private FtSaleshJpaService ftSaleshJpaService;
		private SysvarJpaService sysvarJpaService;
		private FDivisionJpaService fDivisionJpaService;
		
	//2. ENTITY
		protected FtSalesh itemHeader;
		
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<FtSalesh> beanItemContainerItemHeader;
		private BeanItemContainer<FtSalesh> beanItemContainerItemHeaderSearch;
		
		private BeanItemContainer<FDivision> beanItemContainerDivision;
		
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<FtSalesh> binderItemHeader;
				
		//OTHERS
		//CHECK BOX BUAT TABLE PADA HEADER
		private boolean selectAllInvoice;
	
		public ArRecapSelectModel(){
			initVariable();
			initVariableData();
			
		}
		
		public void initVariable(){
			setSysvarJpaService(((DashboardUI) getUI().getCurrent()).getSysvarJpaService());
			setFtSaleshJpaService(((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService());
			setfDivisionJpaService(((DashboardUI) getUI().getCurrent()).getfDivisionJpaService());
			
			itemHeader = new FtSalesh();
			
			beanItemContainerItemHeader = new BeanItemContainer<FtSalesh>(FtSalesh.class);
			beanItemContainerItemHeaderSearch = new BeanItemContainer<FtSalesh>(FtSalesh.class);
			beanItemContainerDivision = new BeanItemContainer<FDivision>(FDivision.class);
			
			binderItemHeader = new BeanFieldGroup<FtSalesh>(FtSalesh.class);
			
		}
		public void initVariableData(){
			reload();
			
		}
		public void reload(){
			
			beanItemContainerItemHeader.removeAllContainerFilters();
			beanItemContainerItemHeader.removeAllItems();
			
//			beanItemContainerItemHeader.addAll(arInvoiceService.findAllForRecapSelectArTOTunai("%", "%"));
			beanItemContainerDivision.addAll(fDivisionJpaService.findAll());
		}

		public FtSaleshJpaService getFtSaleshJpaService() {
			return ftSaleshJpaService;
		}

		public SysvarJpaService getSysvarJpaService() {
			return sysvarJpaService;
		}

		public FDivisionJpaService getfDivisionJpaService() {
			return fDivisionJpaService;
		}

		public FtSalesh getItemHeader() {
			return itemHeader;
		}

		public BeanItemContainer<FtSalesh> getBeanItemContainerItemHeader() {
			return beanItemContainerItemHeader;
		}

		public BeanItemContainer<FtSalesh> getBeanItemContainerItemHeaderSearch() {
			return beanItemContainerItemHeaderSearch;
		}

		public BeanItemContainer<FDivision> getBeanItemContainerDivision() {
			return beanItemContainerDivision;
		}

		public BeanFieldGroup<FtSalesh> getBinderItemHeader() {
			return binderItemHeader;
		}

		public boolean isSelectAllInvoice() {
			return selectAllInvoice;
		}

		public void setFtSaleshJpaService(FtSaleshJpaService ftSaleshJpaService) {
			this.ftSaleshJpaService = ftSaleshJpaService;
		}

		public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
			this.sysvarJpaService = sysvarJpaService;
		}

		public void setfDivisionJpaService(FDivisionJpaService fDivisionJpaService) {
			this.fDivisionJpaService = fDivisionJpaService;
		}

		public void setItemHeader(FtSalesh itemHeader) {
			this.itemHeader = itemHeader;
		}

		public void setBeanItemContainerItemHeader(
				BeanItemContainer<FtSalesh> beanItemContainerItemHeader) {
			this.beanItemContainerItemHeader = beanItemContainerItemHeader;
		}

		public void setBeanItemContainerItemHeaderSearch(
				BeanItemContainer<FtSalesh> beanItemContainerItemHeaderSearch) {
			this.beanItemContainerItemHeaderSearch = beanItemContainerItemHeaderSearch;
		}

		public void setBeanItemContainerDivision(
				BeanItemContainer<FDivision> beanItemContainerDivision) {
			this.beanItemContainerDivision = beanItemContainerDivision;
		}

		public void setBinderItemHeader(BeanFieldGroup<FtSalesh> binderItemHeader) {
			this.binderItemHeader = binderItemHeader;
		}

		public void setSelectAllInvoice(boolean selectAllInvoice) {
			this.selectAllInvoice = selectAllInvoice;
		}

	
	
}
