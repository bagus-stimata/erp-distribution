package org.erp.distribution.util;

import java.util.List;

import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FStockJpaServiceImpl;
import org.erp.distribution.jpaservice.FtArpaymenthJpaService;
import org.erp.distribution.jpaservice.FtArpaymenthJpaServiceImpl;
import org.erp.distribution.jpaservice.FtSalesdJpaService;
import org.erp.distribution.jpaservice.FtSalesdJpaServiceImpl;
import org.erp.distribution.model.FStock;

public class UpdateSystemHelper {
	private FtSalesdJpaService ftSalesdJpaService = new FtSalesdJpaServiceImpl();
	private FStockJpaService fStockJpaService = new FStockJpaServiceImpl();
	
	public UpdateSystemHelper(){
		
	}
	public void updateVersion11(){
		ftSalesdJpaService.setNullFreegoodAsFalse();
	}
	public void updateVersion12(){
		fStockJpaService.setZerroIfNull();
//		List<FStock> listFStock = fSalesd
	}
	
}
