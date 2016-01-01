package org.erp.distribution.salesorder.salesorder.packinglistperhari;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LapPackingListPresenter implements ClickListener{
	private LapPackingListModel model;
	private LapPackingListView view;

	public LapPackingListPresenter(LapPackingListModel model, LapPackingListView view){
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
	private Date dateParamInvoicedate = null;
	
	public void resetParameters(){
		strParamProductgroup= "%";
		strParamWarehouseId = "%";
		dateParamInvoicedate = model.getTransaksiHelper().getCurrentTransDate();
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
			dateParamInvoicedate = view.getDateField1From().getValue();
		} catch(Exception ex){}
	}
	public void preview(){
		resetParameters();
		reloadParameter();
		
		showPreview("/erp/distribution/reports/salesorder/packinglist/packinglist1.jasper", "packinglist1");
		
	}
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
		parameters.put("CompanyName","");

		
		parameters.put("paramInvoicedate", dateParamInvoicedate);
		
		parameters.put("paramWarehouseId",  strParamWarehouseId);
		parameters.put("paramProductgroup", "%" +  strParamProductgroup  + "%");
		
		List<FtSalesh> listFtSalesh = model.getFtSaleshJpaService().findAllByInvoicedate(dateParamInvoicedate, "F", true);
		String daftarInvoice = "";
		for (FtSalesh domain: listFtSalesh) {
			daftarInvoice += domain.getInvoiceno();
			daftarInvoice += ", ";
		}
		parameters.put("paramOutListNota", daftarInvoice);

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
