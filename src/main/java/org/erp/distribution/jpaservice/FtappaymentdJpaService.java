package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.Bank;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FtAppaymentd;
import org.erp.distribution.model.FtArpaymentd;

public interface FtappaymentdJpaService extends GenericJpaService<FtAppaymentd, Serializable>{
	public List<FtAppaymentd> findAllByInvoiceAndDiv(Long refnopurchaseh, String div);
	public List<FtAppaymentd> findAllByRefnoAndInvAndDiv(Long refnopayment, Long refnopurchaseh, String div);

}
