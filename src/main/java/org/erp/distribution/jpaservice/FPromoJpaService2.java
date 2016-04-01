package org.erp.distribution.jpaservice;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.erp.distribution.jpaservice.generic.GenericJpaService;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FPromo;

public interface FPromoJpaService2 extends GenericJpaService<FPromo, Serializable>{
	public List<FPromo> findAllById(Long id);
	public List<FPromo> findAllPromoActiveByProduct(FProduct fProductBean);
	public List<FPromo> findAllPromoActiveByProductGroup(FProductgroup fProductGroupBean);
	public List<FPromo> findAllPromoActiveByProductGroup();
	
	public List<FPromo> findAllPromoBerjalan(Date periode);
	public List<FPromo> findAllPromoBerjalanItemBarangActive(Date periode);
	public List<FPromo> findAllPromoBerjalanGrupBarangActive(Date periode);
	public List<FPromo> findAllPromoBerjalanGrupBarangAkumulasiActive(Date periode);
	
}
