package org.erp.distribution.jpaservicerep;

import java.io.Serializable;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.ZLapMutasiStock;
import org.erp.distribution.model.ZLapPrestasiKerja;
import org.erp.distribution.model.ZLapStockOpname;

public interface LapStockOpanameJpaService extends GenericJpaService<ZLapStockOpname, Serializable>{
	public void deleteAll();
}
