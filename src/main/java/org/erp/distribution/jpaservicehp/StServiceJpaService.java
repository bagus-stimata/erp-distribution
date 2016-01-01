package org.erp.distribution.jpaservicehp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.Bank;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.StService;

public interface StServiceJpaService extends GenericJpaService<StService, Serializable>{
	public List<StService> findAllByTglPenerimaan(Date tglPenerimaanFrom, Date tglPenerimaanTo, Long longTeknisiId);
	public List<StService> findAllByTglPengambilan(Date tglPengambilanFrom, Date tglPengambilanTo, Long longTeknisiId);
}
