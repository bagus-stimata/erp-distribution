package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FtAdjusth;
import org.erp.distribution.model.FtStocktransferh;

public interface FtStocktransferhJpaService extends GenericJpaService<FtStocktransferh, Serializable>{
	public List<FtStocktransferh> findAllByTrdate(Date trdate);
}
