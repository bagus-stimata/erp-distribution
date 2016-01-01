package org.erp.distribution.salesorder.salesorder.lapsalestoexel;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

public class LapSalesExelPresenter implements ClickListener{
	private LapSalesExelModel model;
	private LapSalesExelView view;

	public LapSalesExelPresenter(LapSalesExelModel model, LapSalesExelView view){
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
		String basepath = VaadinService.getCurrent()
	            .getBaseDirectory().getAbsolutePath();
		String filePathDestination = basepath + "/SalesAndRetur.xls";
		
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("SalesAndRetur");

        Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
        data.put(1, new Object[] {"SALESMANID","SALESMAN", "CUSTID", "CUSTOMER", "INVOICENO", "INVOICEDATE", "FAKTUR/RETUR", "TIPE JUAL",
        		"WAREHOUSEID", "KODE BRG", "NAMA BRG","PACKAGING", "CONVFACT1", "CONVFACT2", "QTY_IN_PCS",
        		"HRG JUAL-PPN", "DISC1", "DISC2+", "SUB TOTAL BEFORE DISC-PPN"});
        
        Date trDateFrom = view.getDateField1From().getValue();
        Date trDateTo = view.getDateField1To().getValue();
       Iterator<FtSalesd> iterFSalesd = model.getFtSalesdJpaService().findAll("%", "%", trDateFrom, trDateTo, "%").iterator();
       
       int lastRow = 1;
       while (iterFSalesd.hasNext()) {
       		lastRow++;
       		FtSalesd domain = new FtSalesd();
       		domain = iterFSalesd.next();
       		
       		Double subtotal = 0.0;
       		if (domain.getFtsaleshBean().getTipefaktur().equals("F")){
       			subtotal = domain.getSprice()/domain.getFproductBean().getConvfact1() * domain.getQty();
       		}else if(domain.getFtsaleshBean().getTipefaktur().equals("R")){
       			subtotal = -(domain.getSprice()/domain.getFproductBean().getConvfact1() * domain.getQty());
       		}
	        data.put(lastRow, new Object[] {domain.getFtsaleshBean().getFsalesmanBean().getSpcode(), domain.getFtsaleshBean().getFsalesmanBean().getSpname(),
	        		domain.getFtsaleshBean().getFcustomerBean().getCustno(), domain.getFtsaleshBean().getFcustomerBean().getCustname(), 
	        		domain.getFtsaleshBean().getInvoiceno(), domain.getFtsaleshBean().getInvoicedate(), domain.getFtsaleshBean().getTipefaktur(), 
	        		domain.getFtsaleshBean().getTipejual(), domain.getFtsaleshBean().getFwarehouseBean().getId(),
	        		domain.getFproductBean().getPcode(), domain.getFproductBean().getPname(), domain.getFproductBean().getPackaging(),
	        		domain.getFproductBean().getConvfact1(), domain.getFproductBean().getConvfact2(),
	        		domain.getQty(), domain.getSprice(), domain.getDisc1(), domain.getDisc2(), 
	        		subtotal
	        		});
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
		
		fd.extend(view.getBtnPreview());
		
	}
	
	
}
