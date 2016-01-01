package org.erp.distribution.jpaservicerep;

import java.io.Serializable;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.ZLapMutasiStock;
import org.erp.distribution.model.ZLapPrestasiKerja;

public interface LapMutasiStockJpaService extends GenericJpaService<ZLapMutasiStock, Serializable>{
	public void deleteAll();
}
