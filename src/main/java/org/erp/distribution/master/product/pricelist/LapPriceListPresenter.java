package org.erp.distribution.master.product.pricelist;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LapPriceListPresenter implements ClickListener{
	private LapPriceListModel model;
	private LapPriceListView view;

	public LapPriceListPresenter(LapPriceListModel model, LapPriceListView view){
		this.model = model;
		this.view = view;
		initListener();
	}
	
	public void initListener(){
		view.getBtnPreview().addClickListener(this);
		view.getBtnClose().addClickListener(this);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnPreview()){
			preview();
		} else if (event.getButton()==view.getBtnClose()){
		}
	}
	
	private String strParamProductgroup = "";
	private String strParamWarehouseId = "";
	private Date dateParamStockdate = null;
	private String strParamVendorId = "";
	private String strParamVendorName = "";
	
	
	public void resetParameters(){
		strParamProductgroup= "%";
		strParamWarehouseId = "%";
		dateParamStockdate = model.getTransaksiHelper().getCurrentTransDate();
		strParamVendorId = "";
		strParamVendorName = "";
	}
	public void reloadParameter(){
		try{
			FWarehouse fWarehouse = new FWarehouse();
			fWarehouse = (FWarehouse) view.getComboGroup1().getConvertedValue();
			strParamWarehouseId = fWarehouse.getId().trim();
		} catch(Exception ex){}
		try{
			FProductgroup fProductgroup = new FProductgroup();
			fProductgroup = (FProductgroup) view.getComboGroup2().getConvertedValue();
			strParamProductgroup = fProductgroup.getId().trim();
		} catch(Exception ex){}
		try{
			FVendor fVendor = new FVendor();
			fVendor = (FVendor) view.getComboGroup3().getConvertedValue();
			strParamVendorId = fVendor.getVcode().trim();
			strParamVendorName = strParamVendorId + " - " + fVendor.getVname();
		} catch(Exception ex){}
		try{
			dateParamStockdate = view.getDateField1From().getValue();
		} catch(Exception ex){}
	}
	public void preview(){
		resetParameters();
		reloadParameter();
		
		showPreview("/erp/distribution/reports/setupmaster/product/pricelist/pricelist.jasper", "pricelist");
		
	}
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
					
		final Map parameters=new HashMap();
		parameters.put("CompanyName","");
		
		parameters.put("paramTanggal", dateParamStockdate);
		
//		parameters.put("paramWarehouseId", "%" +  strParamWarehouseId  + "%");
		parameters.put("paramProductgroup", "%" +  strParamProductgroup  + "%");

		parameters.put("paramVendorId", "%" +  strParamVendorId  + "%");
		parameters.put("paramVendorName", strParamVendorName);

		//CONNECTION
		final Connection con = new ReportJdbcConfigHelper().getConnection();
		
		
		StreamResource.StreamSource source = new StreamSource() {			
			@Override
			public InputStream getStream() {
				byte[] b = null;
				try {
					b = JasperRunManager.runReportToPdf(report, parameters, con);
				} catch (JRException ex) {
					System.out.println(ex);
				}
				return new ByteArrayInputStream(b);
			}
		};
		
		String fileName = "pricelist_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new_pricelist_" + outputFilePath, false);
	
	} catch (JRException e) {
		e.printStackTrace();
	}
	
}
	
	
}
