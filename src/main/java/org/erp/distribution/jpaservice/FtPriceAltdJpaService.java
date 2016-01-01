package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FtPriceAltd;
import org.erp.distribution.model.FtPriced;

public interface FtPriceAltdJpaService extends GenericJpaService<FtPriceAltd, Serializable>{
	public List<FtPriceAltd> findAllByHeaderIdAndProduct(long headerId, long productId);

}
