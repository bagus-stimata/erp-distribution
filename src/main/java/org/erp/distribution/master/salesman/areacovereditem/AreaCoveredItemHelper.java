package org.erp.distribution.master.salesman.areacovereditem;

import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FVendor;

public class AreaCoveredItemHelper{
	
	private AreaCoveredItemModel model;
	private AreaCoveredItemView view;
	
	FArea fArea = new FArea();
	
	public AreaCoveredItemHelper(AreaCoveredItemModel model, AreaCoveredItemView view){
		this.model = model;
		this.view = view;
	}

	public Double getParamPpn(){
		return model.getTransaksiHelper().getParamPpn();
	}
	public void updateAndCalulateItemDetilProduct(){
		
		fArea = new FArea();
		fArea = (FArea) view.getComboArea().getValue();
		
		model.itemDetil = fArea;
	}
	public void updateAndCalculateItemDetil(){
		try{
//			model.itemDetil.setPpriceafterppn((double) Math.round(priceAfterppn));
	
			model.getBinderItemDetail().setItemDataSource(model.itemDetil);
			view.bindAndBuildFieldGroupComponent();
		} catch(Exception ex){}
		
		
	}

	
}
