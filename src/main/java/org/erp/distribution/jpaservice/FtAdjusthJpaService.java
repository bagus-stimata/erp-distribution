package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FtAdjusth;
import org.erp.distribution.model.FtOpnameh;
import org.erp.distribution.model.FtStocktransferh;

public interface FtAdjusthJpaService extends GenericJpaService<FtAdjusth, Serializable>{
	public List<FtAdjusth> findAllByTrdate(Date trdate);

}
