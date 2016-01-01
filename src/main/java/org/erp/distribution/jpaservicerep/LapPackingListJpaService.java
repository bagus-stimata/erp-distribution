package org.erp.distribution.jpaservicerep;

import java.io.Serializable;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.ZLapMutasiStock;
import org.erp.distribution.model.ZLapPackingList;
import org.erp.distribution.model.ZLapPrestasiKerja;

public interface LapPackingListJpaService extends GenericJpaService<ZLapPackingList, Serializable>{
	public void deleteAll();
	public List<ZLapPackingList> findAllSumByProduct();
}
