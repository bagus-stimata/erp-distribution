package org.erp.distribution.salesorder.salesorder.retur.lapsalesretur2;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.erp.distribution.model.FProductgroup;
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

public class LapSalesRetur2Presenter implements ClickListener{
	private LapSalesRetur2Model model;
	private LapSalesRetur2View view;

	public LapSalesRetur2Presenter(LapSalesRetur2Model model, LapSalesRetur2View view){
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
	private Date dateParamInvoicedateFrom = null;
	private Date dateParamInvoicedateTo = null;
	private Boolean potongRetur=false;
	private String potongReturString="TIDAK DIPOTONG RETUR";
	
	public void resetParameters(){
		strParamProductgroup= "%";
		strParamWarehouseId = "%";
		dateParamInvoicedateFrom = model.getTransaksiHelper().getCurrentTransDate();
		dateParamInvoicedateTo = model.getTransaksiHelper().getCurrentTransDate();
		potongRetur=false;
		potongReturString="TIDAK DIPOTONG RETUR";
		
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
			dateParamInvoicedateFrom = view.getDateField1From().getValue();
		} catch(Exception ex){}
		try{
			dateParamInvoicedateTo = view.getDateField1To().getValue();
		} catch(Exception ex){}
		
		potongRetur=view.getCheckBox1().getValue();
		if (potongRetur==true){
			potongReturString="DIPOTONG RETUR";			
		}else {
			potongReturString="TIDAK DIPOTONG RETUR";			
		}
		
	}
	public void preview(){
		resetParameters();
		reloadParameter();
		
		showPreview("/erp/distribution/reports/salesretur/lapreturperbarang/lapsalespersalesmanperbarang1.jasper", "salespersalesmanperbarang1");
		
	}
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
		parameters.put("CompanyName","");

		
		parameters.put("paramInvoicedateFrom", dateParamInvoicedateFrom);
		parameters.put("paramInvoicedateTo", dateParamInvoicedateTo);
		
		parameters.put("paramProductGroup", "%" +  strParamProductgroup  + "%");
//		parameters.put("paramProductGroup", "%" );
		parameters.put("paramPotongRetur", potongRetur);
		parameters.put("paramPotongReturString", potongReturString);


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
		
		String fileName = "ar_kas_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new_kontrol_stok_" + outputFilePath, false);
	
	} catch (JRException e) {
		e.printStackTrace();
	}
	
}
	
	
}
