package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.Bukugiro;
import org.erp.distribution.model.FArea;

public interface BukugiroJpaService extends GenericJpaService<Bukugiro, Serializable>{
	public List<Bukugiro> findAllAvailableGiro(Date tgltransaksi);
	public List<Bukugiro> findAllAvailableGiro();
	public List<Bukugiro> findAllAvalilableGiro(Date tgltransaksi, Long exceptGiro);
	public List<Bukugiro> findAllAvalilableGiro(Long exceptGiro);


}
