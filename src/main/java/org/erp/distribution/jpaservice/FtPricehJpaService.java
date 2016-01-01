package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FtPriceh;

public interface FtPricehJpaService extends GenericJpaService<FtPriceh, Serializable>{
	public List<FtPriceh> findAllByTrDate(Date trDate);
}
