package org.erp.distribution.servicehp.lapservicerekap;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.STeknisi;
import org.erp.distribution.model.StService;
import org.erp.distribution.model.ZLapPackingList;
import org.erp.distribution.model.ZLapTemplate1;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import com.vaadin.data.Item;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LapServiceRekapPresenter implements ClickListener{
	private LapServiceRekapModel model;
	private LapServiceRekapView view;

	public LapServiceRekapPresenter(LapServiceRekapModel model, LapServiceRekapView view){
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
			printForm();
		} else if (event.getButton()==view.getBtnClose()){
		}
	}
	
	
	public void printForm(){
		//1. ISI DATABASE UNTUK TEMP
		fillDatabaseReportLengkap();
		//2. PREVIEW LAPORAN
		showPreview("/erp/distribution/reports/servicehp/rekapservice1/rekapservice1.jasper", "rekapservice1");

	}
	
	public void fillDatabaseReportLengkap(){
		//1. HAPUS DATA
		Iterator<ZLapTemplate1> iterZLapTemplate1Delete = model.getLapTemplate1JpaService().findAll().iterator();
		while (iterZLapTemplate1Delete.hasNext()) {
			model.getLapTemplate1JpaService().removeObject(iterZLapTemplate1Delete.next());
		}
		//MENGHINDARI DOUBLE
		Set<String> setSuratJalanList = new LinkedHashSet<String>();
		Set<String> setInvoiceList = new LinkedHashSet<String>();

		//2. MASUKKAN YANG DISELEKSI KE DALAM TABLE REPORT TEMPORER TAHAP1
		List<StService> listStService = new ArrayList<StService>();
		Long longTeknisiId = (long) -1;
		try{
			longTeknisiId = ((STeknisi)view.getComboGroup1().getValue()).getId();
		} catch(Exception ex){}
		listStService = model.getStServiceJpaService()
				.findAllByTglPengambilan(view.getDateField1From().getValue(), view.getDateField1To().getValue(), longTeknisiId);
		for (StService itemId: listStService){			
			StService itemStService = new StService();
			itemStService = itemId;
			
			if (itemStService.getTelahDiambil()==true){
				
					ZLapTemplate1 domain = new ZLapTemplate1();
					
					domain.setGrup1(String.valueOf(itemStService.getSteknisiBean().getNip()
							+ "  " + itemStService.getSteknisiBean().getName()));
					domain.setGrup2("-");
					domain.setGrup3("-");
					
					domain.setString1(itemStService.getNojob());
					domain.setString2(itemStService.getScustomerBean().getCustname());
					domain.setString3(itemStService.getSmerkBean().getDescription());
					domain.setString4(itemStService.getTipeHp());
					domain.setString5(itemStService.getKeluhan());
					
					domain.setDouble1(itemStService.getBiaya());
					domain.setDouble2(itemStService.getBiayaSparePart());
					
					model.getLapTemplate1JpaService().createObject(domain);
					
				
			}
			
		}
		
	}		
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
		parameters.put("CompanyName","");
		
		parameters.put("paramDate1", view.getDateField1From().getValue());
		parameters.put("paramDate2", view.getDateField1To().getValue());
		
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
		
		String fileName = "service_rekap_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new__" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	
		
	}
	
	
}
