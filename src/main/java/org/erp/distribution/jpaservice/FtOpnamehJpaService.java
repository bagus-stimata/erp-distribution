package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FtOpnameh;

public interface FtOpnamehJpaService extends GenericJpaService<FtOpnameh, Serializable>{
	public List<FtOpnameh> findAllByTrdate(Date trdate);
	public List<FtOpnameh> findAllByTrdate(Date trdateFrom, Date trdateTo);
}
