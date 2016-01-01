package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtStocktransferd;

public interface FtStocktransferdJpaService extends GenericJpaService<FtStocktransferd, Serializable>{
	public List<FtStocktransferd> findAllFrom(FWarehouse fWarehouseFrom, FProduct fProduct, Date trDate);
	public List<FtStocktransferd> findAllTo(FWarehouse fWarehouseTo, FProduct fProduct, Date trDate);

}
