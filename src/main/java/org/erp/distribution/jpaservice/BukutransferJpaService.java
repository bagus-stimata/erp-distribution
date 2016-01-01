package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.Bukutransfer;
import org.erp.distribution.model.FArea;

public interface BukutransferJpaService extends GenericJpaService<Bukutransfer, Serializable>{
	public List<Bukutransfer> findAllAvailableTransfer();
	public List<Bukutransfer> findAllAvailabelTransfer(Long exceptTransfer);

}
