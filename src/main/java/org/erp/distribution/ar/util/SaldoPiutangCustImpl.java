package org.erp.distribution.ar.util;

import java.util.List;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FtSalesh;

import com.vaadin.ui.CustomComponent;

public class SaldoPiutangCustImpl extends CustomComponent implements SaldoPiutangCust{
	private FtSaleshJpaService ftSaleshJpaService;
	
	public SaldoPiutangCustImpl() {
		setFtSaleshJpaService((((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService()));
	}
	public FtSaleshJpaService getFtSaleshJpaService() {
		return ftSaleshJpaService;
	}
	public void setFtSaleshJpaService(FtSaleshJpaService ftSaleshJpaService) {
		this.ftSaleshJpaService = ftSaleshJpaService;
	}
	
	@Override
	public double getSaldoPiutangPerCustomerMinRetur(FCustomer fCustomer) {
		double sum=0;
		List<FtSalesh> list = ftSaleshJpaService.findAllFakturAndReturBelumLunas(fCustomer);
		for (FtSalesh ftSalesh: list){
			if (! ftSalesh.getInvoiceno().trim().equals("")){
				if (ftSalesh.getTipefaktur().equals("F")) {
					sum += ftSalesh.getAmountafterdiscafterppn() - ftSalesh.getAmountpay();
				}else if (ftSalesh.getTipefaktur().equals("R")){
					sum -= ftSalesh.getAmountafterdiscafterppn() - ftSalesh.getAmountpay();				
				}
			}
		}
		return sum;
	}
	
	

	@Override
	public double getSaldoPiutangPerCustomerNoRetur(FCustomer fCustomer) {
		double sum=0;
		List<FtSalesh> list = ftSaleshJpaService.findAllFakturAndReturBelumLunas(fCustomer);
		for (FtSalesh ftSalesh: list){
			if (! ftSalesh.getInvoiceno().trim().equals("")){
				if (ftSalesh.getTipefaktur().equals("F")) {
					sum += ftSalesh.getAmountafterdiscafterppn() - ftSalesh.getAmountpay();
				}
			}
		}
		return sum;
	}

	@Override
	public int getOpenInvoicePerCustomer(FCustomer fCustomer) {
		int sum=0;
		List<FtSalesh> list = ftSaleshJpaService.findAllFakturAndReturBelumLunas(fCustomer);
		for (FtSalesh ftSalesh: list){
			if (! ftSalesh.getInvoiceno().trim().equals("")){
				if (ftSalesh.getTipefaktur().equals("F")) {
					sum += 1;
				}
			}
		}
		return sum;
	}
	
	
	

}
