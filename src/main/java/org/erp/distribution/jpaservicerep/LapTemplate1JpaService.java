package org.erp.distribution.jpaservicerep;

import java.io.Serializable;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.ZLapMutasiStock;
import org.erp.distribution.model.ZLapPrestasiKerja;
import org.erp.distribution.model.ZLapStockOpname;
import org.erp.distribution.model.ZLapTemplate1;

public interface LapTemplate1JpaService extends GenericJpaService<ZLapTemplate1, Serializable>{
	public void deleteAll();
}
