package org.erp.distribution.salesorder.salesorder.lapsalesorder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LapSalesOrderPresenter implements ClickListener{
	private LapSalesOrderModel model;
	private LapSalesOrderView view;

	public LapSalesOrderPresenter(LapSalesOrderModel model, LapSalesOrderView view){
		this.model = model;
		this.view = view;
		initListener();
	}
	
	public void initListener(){
		view.getBtnExtractToExel().addClickListener(this);
		view.getBtnPreview().addClickListener(this);
		view.getBtnClose().addClickListener(this);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnPreview()){
			preview();
		} else if (event.getButton()==view.getBtnClose()){
		}else if (event.getButton()==view.getBtnExtractToExel()){
			extractToExel();
		}
	}
	
	private String strParamProductgroup = "";
	private String strParamWarehouseId = "";
	private Date dateParamInvoicedate = null;
	private Boolean potongRetur=false;
	private String potongReturString="TIDAK DIPOTONG RETUR";
	
	public void resetParameters(){
		strParamProductgroup= "%";
		strParamWarehouseId = "%";
		dateParamInvoicedate = model.getTransaksiHelper().getCurrentTransDate();
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
			dateParamInvoicedate = view.getDateField1From().getValue();
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
		
		showPreview("/erp/distribution/reports/salesorder/iph1/iph1.jasper", "iph1");
		
		try{
			showPreview("/erp/distribution/reports/salesorder/iph1/iph2.jasper", "iph2");
		} catch(Exception ex){}
		
	}
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
			final Map parameters=new HashMap();
			parameters.put("CompanyName","");
	
			
			parameters.put("paramInvoicedate", dateParamInvoicedate);
			//UNTUK PER BARANG
			parameters.put("paramInvoicedateFrom", dateParamInvoicedate);
			parameters.put("paramInvoicedateTo", dateParamInvoicedate);
			
			parameters.put("paramWarehouseId",  strParamWarehouseId);
			parameters.put("paramProductgroup", "%" +  strParamProductgroup  + "%");
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
	
	public void extractToExel(){
		String basepath = VaadinService.getCurrent()
	            .getBaseDirectory().getAbsolutePath();
		String filePathDestination = basepath + "/SalesAndReturHarian.xls";
		
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("SalesAndReturHarian");

        Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
        data.put(1, new Object[] {"SALESMANID","SALESMAN", "CUSTID", "CUSTOMER", "ADDRESS", "INVOICENO", "INVOICEDATE", "FAKTUR/RETUR", "TUNAI/KREDIT", "TIPE JUAL",
        		"TGL JATUH TEMPO", "NO SJ", "TGL SJ","SUB TOTAL AFTER DISC AFTER PPN"});
        
	    Date trDateFrom = view.getDateField1From().getValue();
	    Date trDateTo = view.getDateField1To().getValue();
	    Iterator<FtSalesh> iterFSalesh = model.getFtSaleshJpaService().findAllByInvoicedate(trDateFrom).iterator();
       
       int lastRow = 1;
       while (iterFSalesh.hasNext()) {
       		lastRow++;
       		FtSalesh domain = new FtSalesh();
       		domain = iterFSalesh.next();
       		
       		Double subtotal = 0.0;
       		if (domain.getTipefaktur().equals("F")){
       			subtotal = domain.getAmountafterdiscafterppn();
       		}else if(domain.getTipefaktur().equals("R")){
       			subtotal = -domain.getAmountafterdiscafterppn();
       		}
       		try{
		        data.put(lastRow, new Object[] {domain.getFsalesmanBean().getSpcode(), domain.getFsalesmanBean().getSpname(),
		        		domain.getFcustomerBean().getCustno(), domain.getFcustomerBean().getCustname(), domain.getFcustomerBean().getAddress1(),
		        		domain.getInvoiceno(), domain.getInvoicedate(), domain.getTipefaktur(), domain.getTunaikredit(), 
		        		domain.getTipejual(),domain.getDuedate(), 
		        		domain.getSuratjalanno(), domain.getSjdate(),  
		        		subtotal
		        		});
       		} catch(Exception ex){}
       }  
        
        Set<Integer> keyset = data.keySet();
        int rownum = 0;
        for (Integer key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof Date)
                    cell.setCellValue((Date)obj);
                else if(obj instanceof Boolean)
                    cell.setCellValue((Boolean)obj);
                else if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }

        try {
            FileOutputStream out =
                    new FileOutputStream(new File(filePathDestination));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        
		Resource res = new FileResource(new File(filePathDestination));		
		FileDownloader fd = new FileDownloader(res);
		
		fd.extend(view.getBtnExtractToExel());
		
	}
	
}
