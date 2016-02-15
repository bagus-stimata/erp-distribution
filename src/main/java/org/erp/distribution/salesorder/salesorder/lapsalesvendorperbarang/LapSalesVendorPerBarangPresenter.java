package org.erp.distribution.salesorder.salesorder.lapsalesvendorperbarang;

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

import ognl.ListPropertyAccessor;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FPromo;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.STeknisi;
import org.erp.distribution.model.StService;
import org.erp.distribution.model.ZLapPackingList;
import org.erp.distribution.model.ZLapTemplate1;
import org.erp.distribution.model.ZLapTemplate2;
import org.erp.distribution.util.HeaderDetilHelper;
import org.erp.distribution.util.HeaderDetilHelperImpl;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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

public class LapSalesVendorPerBarangPresenter implements ClickListener{
	private LapSalesVendorPerBarangModel model;
	private LapSalesVendorPerBarangView view;

	public LapSalesVendorPerBarangPresenter(LapSalesVendorPerBarangModel model, LapSalesVendorPerBarangView view){
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
		showPreview("/erp/distribution/reports/salesorder/lapsalesvendorperbarang/lapsalesvendorperbarang1Ds.jasper", "lapsalesvendorperbarang1");

	}
	
	private List<ZLapTemplate2> lapTemplate2 = new ArrayList<ZLapTemplate2>();
	public void fillDatabaseReportLengkap(){
		//MENGHINDARI DOUBLE
		lapTemplate2 = new ArrayList<ZLapTemplate2>();		
//		Set<String> setSuratJalanList = new LinkedHashSet<String>();

		List<FtSalesd> listFtSalesd = new ArrayList<FtSalesd>();
		String paramVcode = "%";
		try{
			paramVcode = "%" + ((FVendor) view.getComboGroup1().getValue()).getVcode() + "%";
		} catch(Exception ex){}
		String paramCustno = "%";
		try{
			paramCustno = "%" + ((FCustomer) view.getComboGroup2().getValue()).getCustno() + "%";
		} catch(Exception ex){}
		Date paramTrdate = view.getDateField1From().getValue();
		Date paramDuedate = view.getDateField1To().getValue();
		String paramTipeFaktur = "%";
		String paramPcode = "%";
		listFtSalesd = model.getFtSalesdJpaService()
				.findAllByVendor(paramVcode, paramCustno, paramTrdate, paramDuedate, paramTipeFaktur, paramPcode);
				
		
		for (FtSalesd ftSalesd: listFtSalesd){			
				FtSalesd newFtSalesd = new FtSalesd();
				//JIKA RETUR MAKA DI MINUSKAN
				if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("R")) {
					ftSalesd.setSprice(- ftSalesd.getSprice());
				}
				HeaderDetilHelper headerDetilHelper = new HeaderDetilHelperImpl(ftSalesd);
				newFtSalesd = headerDetilHelper.getFillFtSalesd();
				
				ZLapTemplate2 domain = new ZLapTemplate2();

				String vendorName  = ftSalesd.getFproductBean().getFvendorBean().getVcode()  + "-" + ftSalesd.getFproductBean().getFvendorBean().getVname();
				String custName = ftSalesd.getFtsaleshBean().getFcustomerBean().getCustno() + "-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getCustname();
				String invoiceno = ftSalesd.getFtsaleshBean().getInvoiceno();
				Date invoiceTrdate = ftSalesd.getFtsaleshBean().getInvoicedate();
				Date invoiceDuedate = ftSalesd.getFtsaleshBean().getDuedate();
				String productName = ftSalesd.getFproductBean().getPcode() + "-" +ftSalesd.getFproductBean().getPname();
				
				double totalAfterDisc2AfterPpn = newFtSalesd.getSubTotalAfterDiscNotaAfterPpn();
				
				domain.setGrup1(vendorName);
				domain.setGrup2("-");
				domain.setGrup3("-");
				
				domain.setString1(ftSalesd.getFproductBean().getFvendorBean().getVcode());					
				domain.setString2(custName);
				domain.setString3(invoiceno);
				domain.setDate1(invoiceTrdate);
				domain.setDate2(invoiceDuedate);
				domain.setString4(productName);
				domain.setDouble1(totalAfterDisc2AfterPpn);
				
				//JIKA DIPOTONG RETUR MAKA RETUR MASUK SEMUA --> JIKA TIDAK MAKA YANG DI ADD YANG FAKTUR SAJA
				if (view.getCheckBox1().getValue()==true) {
					lapTemplate2.add(domain);
				}else {
					if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("F")) {
						lapTemplate2.add(domain);
					}					
				}
				
			
		}
		
	}		
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
//			final JasperReport report;
//			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
		final Map parameters=new HashMap();
		parameters.put("CompanyName","");
		
		parameters.put("paramDate1", view.getDateField1From().getValue());
		parameters.put("paramDate2", view.getDateField1To().getValue());
		if (view.getCheckBox1().getValue()==true){
			parameters.put("paramDipotongRetur", "*Dipotong Retur");
		}else {
			parameters.put("paramDipotongRetur", "*Tidak Dipotong Retur");
			
		}
		
		//CONNECTION
//		final Connection con = new ReportJdbcConfigHelper().getConnection();
		final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lapTemplate2);
		InputStream reportPathStream = getClass().getResourceAsStream(inputFilePath);
		final JasperPrint jasperPrint = JasperFillManager.fillReport(reportPathStream, parameters, dataSource);
		
		
		StreamResource.StreamSource source = new StreamSource() {			
			@Override
			public InputStream getStream() {
				byte[] b = null;
				try {
//					b = JasperRunManager.runReportToPdf(report, parameters, con);
					b = JasperExportManager.exportReportToPdf(jasperPrint);
				} catch (JRException ex) {
					System.out.println(ex);
				}
				return new ByteArrayInputStream(b);
			}
		};
		
		String fileName = "sales_vendor_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	
		
	}
	
	
}
