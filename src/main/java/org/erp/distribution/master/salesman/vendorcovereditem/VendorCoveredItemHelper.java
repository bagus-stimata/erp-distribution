package org.erp.distribution.master.salesman.vendorcovereditem;

import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FVendor;

public class VendorCoveredItemHelper{
	
	private VendorCoveredItemModel model;
	private VendorCoveredItemView view;
	
	FVendor fVendor = new FVendor();
	
	public VendorCoveredItemHelper(VendorCoveredItemModel model, VendorCoveredItemView view){
		this.model = model;
		this.view = view;
	}

	public Double getParamPpn(){
		return model.getTransaksiHelper().getParamPpn();
	}
	public void updateAndCalulateItemDetilProduct(){
		
		fVendor = new FVendor();
		fVendor = (FVendor) view.getComboVendor().getValue();
		
		model.itemDetil = fVendor;
	}
	public void updateAndCalculateItemDetil(){
		try{
//			model.itemDetil.setPpriceafterppn((double) Math.round(priceAfterppn));
	
			model.getBinderItemDetail().setItemDataSource(model.itemDetil);
			view.bindAndBuildFieldGroupComponent();
		} catch(Exception ex){}
		
		
	}

	
}
