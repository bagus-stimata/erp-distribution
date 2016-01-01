package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FCustomergroup;

public interface FCustomerJpaService extends GenericJpaService<FCustomer, Serializable>{
	public List<FCustomer> findAllByCustno(String custno);
	public void updateCustomerOpenInvoice(String stringCustomergroup, int jumlahNota);
}
