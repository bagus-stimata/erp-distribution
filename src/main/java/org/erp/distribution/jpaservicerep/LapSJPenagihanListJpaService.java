package org.erp.distribution.jpaservicerep;

import java.io.Serializable;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.ZLapMutasiStock;
import org.erp.distribution.model.ZLapPackingList;
import org.erp.distribution.model.ZLapPrestasiKerja;
import org.erp.distribution.model.ZLapSJPenagihanList;

public interface LapSJPenagihanListJpaService extends GenericJpaService<ZLapSJPenagihanList, Serializable>{
	public void deleteAll();
	public List<ZLapSJPenagihanList> findAllSumByProduct();
}
