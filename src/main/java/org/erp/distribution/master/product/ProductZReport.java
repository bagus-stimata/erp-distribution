package org.erp.distribution.master.product;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.erp.distribution.model.FProduct;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.CustomComponent;

public class ProductZReport extends CustomComponent{
	private List<FProduct> listFproduct = new ArrayList<FProduct>();
	
	public ProductZReport(){
		
	}
	
	public void exportToExel(){
	
		showPreview("/erp/distribution/reports/kontrolstock/saldostok/saldostock.jasper", "master_product");		
	}
	
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
			final Map parameters=new HashMap();
			parameters.put("CompanyName","");
	
			
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
			
			String fileName = "master_product" + outputFilePath + "_" +System.currentTimeMillis()+".xls";
			StreamResource resource = new StreamResource( source, fileName);
			resource.setMIMEType("application/pdf");
			resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
			
			getUI().getPage().open(resource, "_master_product_" + outputFilePath, false);
	
	} catch (JRException e) {
		e.printStackTrace();
	}
	
}
	
}
