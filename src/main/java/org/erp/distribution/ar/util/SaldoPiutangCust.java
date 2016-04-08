package org.erp.distribution.ar.util;

import org.erp.distribution.model.FCustomer;

public interface SaldoPiutangCust {
	public double getSaldoPiutangPerCustomerMinRetur(FCustomer fCustomer);
	public double getSaldoPiutangPerCustomerNoRetur(FCustomer fCustomer);
	public int getOpenInvoicePerCustomer(FCustomer fCustomer);
	
	
}
