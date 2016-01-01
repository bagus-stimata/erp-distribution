package org.erp.distribution.master.division.warehouseitem;

import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;

public class WarehouseCoveredItemHelper{
	
	private WarehouseCoveredItemModel model;
	private WarehouseCoveredItemView view;
	
	FWarehouse fWarehouse = new FWarehouse();
	
	public WarehouseCoveredItemHelper(WarehouseCoveredItemModel model, WarehouseCoveredItemView view){
		this.model = model;
		this.view = view;
	}

	public Double getParamPpn(){
		return model.getTransaksiHelper().getParamPpn();
	}
	public void updateAndCalulateItemDetilProduct(){
		
		fWarehouse = new FWarehouse();
		fWarehouse = (FWarehouse) view.getComboWarehouse().getValue();
		
		model.itemDetil = fWarehouse;
	}
	public void updateAndCalculateItemDetil(){
		try{
//			model.itemDetil.setPpriceafterppn((double) Math.round(priceAfterppn));
	
			model.getBinderItemDetail().setItemDataSource(model.itemDetil);
			view.bindAndBuildFieldGroupComponent();
		} catch(Exception ex){}
		
		
	}

	
}
