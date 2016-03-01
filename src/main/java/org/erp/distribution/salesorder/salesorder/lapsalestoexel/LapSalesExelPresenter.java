package org.erp.distribution.salesorder.salesorder.lapsalestoexel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.ZLapTemplate2;
import org.erp.distribution.util.HeaderDetilSalesHelper;
import org.erp.distribution.util.HeaderDetilSalesHelperImpl;
import org.erp.distribution.util.KonversiProductAndStock;
import org.erp.distribution.util.KonversiProductAndStockImpl;
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
			extractToExel();
		} else if (event.getButton()==view.getBtnClose()){
		}
	}
	
	
	
	public void extractToExel(){
		//INISIALISASI DAN HEADER
	       String [] hari = {"Minggu", "Senin", "Selasa", "Rabo", "Kamis", "Jumat", "Sabtu"};
	       String [] bulan = {"JAN", "FEB", "MAR", "APR", "MEI", "JUN", "JUL", "AGU", "SEP", "OKT", "NOP", "DES"};
		  		
	
		String basepath = VaadinService.getCurrent()
	            .getBaseDirectory().getAbsolutePath();
		String filePathDestination = basepath + "/SalesAndRetur.xls";
		
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sales");

        Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
        data.put(1, new Object[] {"SALESMANID","SALESMAN", 
        		"Area", "SubArea/Market",
        		"CUSTID", "CUSTOMER", "ALAMAT1", "KOTA1", 
        		"INVOICENO", "INVOICEDATE", "JTH TEMPO" ,"FAKTUR/RETUR", "TIPE JUAL",
        		"WAREHOUSEID", 
        		"Grup Barang", "Departement", "Divisi Barang",
        		"KODE BRG", "NAMA BRG","PACKAGING", "CONVFACT1", "CONVFACT2", "QTY IN PCS","KRT UTUH",
        		"HRG JUAL-PPN", "TOTAL BRUTO", "DISC BARANG RP", "DISC NOTA RP", "TOTAL DPP", "HARI", "BULAN", "TAHUN"});
		
        //ISI
		List<FtSalesd> listFtSalesd = new ArrayList<FtSalesd>();
	
		String paramVcode = "%";
		String paramCustno = "%";
		String paramArea = "%";
		String paramSubArea = "%";
		String paramPcode = "%";
		String paramPname = "%";
		String paramProductGroup = "%";
		
		Date paramTrdate = view.getDateField1From().getValue();
		Date paramDuedate = view.getDateField1To().getValue();
		String paramTipeFaktur = "%";
		listFtSalesd = model.getFtSalesdJpaService()
				.findAllByVendor(paramVcode, paramArea, paramSubArea, paramCustno, 
						paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);
				
       int lastRow = 1;
		for (FtSalesd ftSalesd: listFtSalesd){			
				FtSalesd newFtSalesd = new FtSalesd();
				//JIKA RETUR MAKA DI MINUSKAN
				if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("R")) {
					ftSalesd.setSprice(- ftSalesd.getSprice());
					ftSalesd.setQty(-ftSalesd.getQty());
				}
				HeaderDetilSalesHelper headerDetilHelper = new HeaderDetilSalesHelperImpl(ftSalesd);
				newFtSalesd = headerDetilHelper.getFillFtSalesd();

				String productGroup  = ftSalesd.getFproductBean().getFproductgroupBean().getId()  + "-" + ftSalesd.getFproductBean().getFproductgroupBean().getId();
				String custName = ftSalesd.getFtsaleshBean().getFcustomerBean().getCustno() + "-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getCustname();
				String invoiceno = ftSalesd.getFtsaleshBean().getInvoiceno();
				Date invoiceTrdate = ftSalesd.getFtsaleshBean().getInvoicedate();
				Date invoiceDuedate = ftSalesd.getFtsaleshBean().getDuedate();
				String productName = ftSalesd.getFproductBean().getPcode() + "-" +ftSalesd.getFproductBean().getPname();
				
				double totalBrutoDpp = newFtSalesd.getSubtotal();
				double totalDiscBarangDpp = newFtSalesd.getDisc1rp() + newFtSalesd.getDisc2rp();
				double totalDiscNotaDpp = newFtSalesd.getDiscNota1Rp() + newFtSalesd.getDiscNotaRp();
				double totalDpp = newFtSalesd.getSubTotalAfterDiscNota();
				double totalAfterDisc2AfterPpn = newFtSalesd.getSubTotalAfterDiscNotaAfterPpn();
				
				Calendar cal = Calendar.getInstance();
				cal.setTime(ftSalesd.getFtsaleshBean().getInvoicedate());
				
				//JIKA DIPOTONG RETUR MAKA RETUR MASUK SEMUA --> JIKA TIDAK MAKA YANG DI ADD YANG FAKTUR SAJA
      			boolean tulis = false;
				if (view.getCheckBox1().getValue()==true) {
					tulis=true;
				}else {
					if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("F")) {
						tulis =true;
					}					
				}
			
				if (tulis){
		      		lastRow++;	      		 
	      			data.put(lastRow, new Object[] {ftSalesd.getFtsaleshBean().getFsalesmanBean().getSpcode(), ftSalesd.getFtsaleshBean().getFsalesmanBean().getSpname(),
	      					ftSalesd.getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getDescription(), ftSalesd.getFtsaleshBean().getFcustomerBean().getFsubareaBean().getDescription(),
	      					ftSalesd.getFtsaleshBean().getFcustomerBean().getCustno(), ftSalesd.getFtsaleshBean().getFcustomerBean().getCustname(), 
			        		ftSalesd.getFtsaleshBean().getFcustomerBean().getAddress1(),ftSalesd.getFtsaleshBean().getFcustomerBean().getCity1(),
			        		ftSalesd.getFtsaleshBean().getInvoiceno(), ftSalesd.getFtsaleshBean().getInvoicedate(), ftSalesd.getFtsaleshBean().getDuedate(),
			        		ftSalesd.getFtsaleshBean().getTipefaktur(), 
			        		ftSalesd.getFtsaleshBean().getTipejual(), ftSalesd.getFtsaleshBean().getFwarehouseBean().getId(),
			        		ftSalesd.getFproductBean().getFproductgroupBean().getDescription(), 
			        		ftSalesd.getFproductBean().getFproductgroupBean().getFproductgroupdeptBean().getDescription(),
			        		ftSalesd.getFproductBean().getFproductgroupBean().getFproductgroupdivisiBean().getDescription(),
				    		ftSalesd.getFproductBean().getPcode(), ftSalesd.getFproductBean().getPname(), ftSalesd.getFproductBean().getPackaging(),
				    		ftSalesd.getFproductBean().getConvfact1(), ftSalesd.getFproductBean().getConvfact2(),
				    		ftSalesd.getQty(), newFtSalesd.getQty1(), ftSalesd.getSprice(), totalBrutoDpp, totalDiscBarangDpp, totalDiscNotaDpp, 
				    		totalDpp, hari[cal.get(Calendar.DAY_OF_WEEK)-1], bulan[cal.get(Calendar.MONTH)], cal.get(Calendar.YEAR)
			    		});
				}
				
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

        //OUTPUT KE FILE
       final ByteArrayOutputStream out = new ByteArrayOutputStream();
       try {
            workbook.write(out);
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
 		StreamResource.StreamSource source = new StreamSource() {			
			@Override
			public InputStream getStream() {
				byte[] b = null;
					b = out.toByteArray();
				return new ByteArrayInputStream(b);
			}
		};
		
		String fileName = "salesdetil"  +System.currentTimeMillis() +".xls";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/xls");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new_salesdetil_" , false);
		
	}
	
	
}
