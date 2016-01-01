package org.erp.distribution.jpaservicerep;

import java.io.Serializable;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.ZLapPrestasiKerja;

public interface LapPrestasiKerjaJpaService extends GenericJpaService<ZLapPrestasiKerja, Serializable>{
	public void deleteAll();
}
