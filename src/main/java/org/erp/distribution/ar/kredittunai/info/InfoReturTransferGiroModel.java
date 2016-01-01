package org.erp.distribution.ar.kredittunai.info;


import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.BukugiroJpaService;
import org.erp.distribution.jpaservice.BukutransferJpaService;
import org.erp.distribution.jpaservice.FtArpaymentdJpaService;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.model.Bukugiro;
import org.erp.distribution.model.Bukutransfer;
import org.erp.distribution.model.FtArpaymentd;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class InfoReturTransferGiroModel extends CustomComponent {
	private FtSaleshJpaService arInvoiceService;
	private BukugiroJpaService bukuGiroService;
	private BukutransferJpaService bukuTransferService;
	private FtArpaymentdJpaService arPaymentDetailService;

	public FtSalesh arinvoiceRetur=null;
	public Bukugiro bukuGiro=null;
	public Bukutransfer bukuTransfer=null;

	private BeanItemContainer<FtArpaymentd> beanItemContainerArpaymentdetail;
	
	public InfoReturTransferGiroModel(){
		initVariable();
	}
	public InfoReturTransferGiroModel(FtSalesh arInvoiceRetur){
		initVariable();
		this.arinvoiceRetur = new FtSalesh();		
		this.arinvoiceRetur = arInvoiceRetur;
		initVariableData();
		
	}
	public InfoReturTransferGiroModel(Bukugiro bukuGiro){
		initVariable();
		this.bukuGiro = new Bukugiro();		
		this.bukuGiro = bukuGiro;
		initVariableData();
		
	}
	public InfoReturTransferGiroModel(Bukutransfer bukuTransfer){
		initVariable();
		this.bukuTransfer = new Bukutransfer();		
		this.bukuTransfer = bukuTransfer;
		initVariableData();
	}
	
	public void initVariable(){		
		setArInvoiceService(((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService());
		setBukuGiroService(((DashboardUI) getUI().getCurrent()).getBukugiroJpaService());
		setBukuTransferService(((DashboardUI) getUI().getCurrent()).getBukutransferJpaService());
		setArPaymentDetailService(((DashboardUI) getUI().getCurrent()).getFtArpaymentdJpaService());
		beanItemContainerArpaymentdetail = new BeanItemContainer<FtArpaymentd>(FtArpaymentd.class);
		beanItemContainerArpaymentdetail.addNestedContainerProperty("id.refno");
		beanItemContainerArpaymentdetail.addNestedContainerProperty("id.number");
		beanItemContainerArpaymentdetail.addNestedContainerProperty("id.invoiceno");
		beanItemContainerArpaymentdetail.addNestedContainerProperty("id.division");
	}
	public void initVariableData(){
		if (arinvoiceRetur !=null){
			beanItemContainerArpaymentdetail.addAll(arPaymentDetailService.
					findAllByInvoiceAndDiv((long)-1234, null));
		} else if (bukuGiro !=null){
			
		} else if (bukuTransfer !=null){
			
		}
	}
	public FtSaleshJpaService getArInvoiceService() {
		return arInvoiceService;
	}
	public BukugiroJpaService getBukuGiroService() {
		return bukuGiroService;
	}
	public BukutransferJpaService getBukuTransferService() {
		return bukuTransferService;
	}
	public FtArpaymentdJpaService getArPaymentDetailService() {
		return arPaymentDetailService;
	}
	public FtSalesh getArinvoiceRetur() {
		return arinvoiceRetur;
	}
	public Bukugiro getBukuGiro() {
		return bukuGiro;
	}
	public Bukutransfer getBukuTransfer() {
		return bukuTransfer;
	}
	public BeanItemContainer<FtArpaymentd> getBeanItemContainerArpaymentdetail() {
		return beanItemContainerArpaymentdetail;
	}
	public void setArInvoiceService(FtSaleshJpaService arInvoiceService) {
		this.arInvoiceService = arInvoiceService;
	}
	public void setBukuGiroService(BukugiroJpaService bukuGiroService) {
		this.bukuGiroService = bukuGiroService;
	}
	public void setBukuTransferService(BukutransferJpaService bukuTransferService) {
		this.bukuTransferService = bukuTransferService;
	}
	public void setArPaymentDetailService(
			FtArpaymentdJpaService arPaymentDetailService) {
		this.arPaymentDetailService = arPaymentDetailService;
	}
	public void setArinvoiceRetur(FtSalesh arinvoiceRetur) {
		this.arinvoiceRetur = arinvoiceRetur;
	}
	public void setBukuGiro(Bukugiro bukuGiro) {
		this.bukuGiro = bukuGiro;
	}
	public void setBukuTransfer(Bukutransfer bukuTransfer) {
		this.bukuTransfer = bukuTransfer;
	}
	public void setBeanItemContainerArpaymentdetail(
			BeanItemContainer<FtArpaymentd> beanItemContainerArpaymentdetail) {
		this.beanItemContainerArpaymentdetail = beanItemContainerArpaymentdetail;
	}
	
	
	
}
