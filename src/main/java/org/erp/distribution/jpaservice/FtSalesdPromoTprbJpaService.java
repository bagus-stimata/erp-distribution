package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FtPriceAltd;
import org.erp.distribution.model.FtPriced;
import org.erp.distribution.model.FtSalesdPromoTprb;
import org.erp.distribution.model.FtSalesdPromoTpruDisc;

public interface FtSalesdPromoTprbJpaService extends GenericJpaService<FtSalesdPromoTprb, Serializable>{
	public List<FtSalesdPromoTprb> findAllByFtSaleshRefno(long ftSaleshRefno);
	public List<FtSalesdPromoTprb> findAllByFPromoId(long fPromoId);
}
