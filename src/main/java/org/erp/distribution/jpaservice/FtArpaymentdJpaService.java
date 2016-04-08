package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FtArpaymentd;

public interface FtArpaymentdJpaService extends GenericJpaService<FtArpaymentd, Serializable>{
	public List<FtArpaymentd> findAllByInvoiceAndDiv(Long refnosales, String div);
	public List<FtArpaymentd> findAllByRefnoAndInvAndDiv(Long refnopayment, Long refnosales, String div);

	public List<FtArpaymentd> findAllByArpaydateAndSJ(Date arPaymentDateFrom, Date arPaymentDateTo,
			String SJPengiriman, String SJPenagihan);
	public List<FtArpaymentd> findAllByArpaydateAndSJ(Date arPaymentDateFrom, Date arPaymentDateTo,
			String SJPengiriman, String SJPenagihan, String tunaikredit,  String spcode, String custno);
	public List<FtArpaymentd> findAllByInvdateAndInvnoAndArpaydateAndSJ(Date invDateFrom, Date invDateTo, String InvoiceNo,
			Date arPaymentDateFrom, Date arPaymentDateTo, String SJPengiriman, String SJPenagihan);
	

}
