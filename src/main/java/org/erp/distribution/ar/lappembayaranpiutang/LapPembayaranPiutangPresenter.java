package org.erp.distribution.ar.lappembayaranpiutang;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtArpaymentd;
import org.erp.distribution.model.ZLapTemplate2;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LapPembayaranPiutangPresenter implements ClickListener{
	private LapPembayaranPiutangModel model;
	private LapPembayaranPiutangView view;

	public LapPembayaranPiutangPresenter(LapPembayaranPiutangModel model, LapPembayaranPiutangView view){
		this.model = model;
		this.view = view;
		initListener();
	}
	
	public void initListener(){
		view.getBtnPreview().addClickListener(this);
		view.getBtnPreviewInExel().addClickListener(this);
		view.getBtnClose().addClickListener(this);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnPreview()){
			printForm("PDF");
		} else if (event.getButton()==view.getBtnPreviewInExel()){
			printForm("EXEL");
		} else if (event.getButton()==view.getBtnClose()){
		}
	}
	
	private String strParamSJPenagihan = "";
	private String strParamTunaiKredit = "";
	private Date dateArpaymentdateFrom = null;
	private Date dateArpaymentdateTo = null;
	
	private String strParamSalesman = "";
	private String strParamCustomer = "";

	private String strParamProductgroup = "";
	private String strParamWarehouseId = "";
	private Date dateParamStockdate = null;
	private Integer paramSumSaldostock = 0;
	
	private String paramCompanyName = "W-DES";
	private String paramCompanyAddress = "Jl. Kauman Gang IV/A Malang";
	private String paramCompanyPhone = "Telp.082143574692";
	private String paramJudulLaporan = "LAPORAN";
		
	private String paramDateFromDateTo = "";
	public void resetParameters(){
		strParamSJPenagihan = "%";
		strParamTunaiKredit = "%";

		strParamSalesman = "%";
		strParamCustomer = "%";
		
		strParamProductgroup= "%";
		strParamWarehouseId = "%";
		dateParamStockdate = model.getTransaksiHelper().getCurrentTransDate();
		paramSumSaldostock = 0;
	}
	public void reloadParameter(){
		if (view.getComboGroup1().getValue().equals("T")) {
			strParamTunaiKredit  = "T";
		} else if (view.getComboGroup1().getValue().equals("K")) {
			strParamTunaiKredit  = "K";			
		}else {
			strParamTunaiKredit = "%";
		}
		
		strParamSalesman = "%";
		try{
			strParamSalesman = ((FSalesman) view.getComboGroup3().getConvertedValue()).getSpcode();
		} catch(Exception ex){}
		strParamCustomer = "%";
		try{
			strParamCustomer = ((FCustomer) view.getComboGroup4().getConvertedValue()).getCustno();
		} catch(Exception ex){}
		
		try{
			FWarehouse fWarehouse = new FWarehouse();
			fWarehouse = (FWarehouse) view.getComboGroup1().getConvertedValue();
			strParamWarehouseId = fWarehouse.getId().trim();
		} catch(Exception ex){}
		
		try{
			strParamSJPenagihan = "%" + view.getFieldSuratJalanPenagihan().getValue().trim() + "%";
		} catch(Exception ex){}
		try{
			dateArpaymentdateFrom = view.getDateField1From().getValue();
			dateArpaymentdateTo = view.getDateField1To().getValue();
		} catch(Exception ex){}
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
			dateParamStockdate = view.getDateField1From().getValue();
		} catch(Exception ex){}
		//JIKA HANYA YANG ADA MUTASI
		if (view.getCheckBox1().getValue()==true) {			
			paramSumSaldostock=0;
		}else {
			paramSumSaldostock=-100000000;			
		}
		
	}

	public void printForm(String tipeOutputFile){
		paramCompanyName = model.getSysvarHelper().getCompanyNameFaktur();
		paramCompanyAddress = model.getSysvarHelper().getCompanyAddressFaktur();
		paramCompanyPhone = model.getSysvarHelper().getCompanyPhoneFaktur();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		paramDateFromDateTo = sdf.format(view.getDateField1From().getValue()) + " S.D " + sdf.format(view.getDateField1To().getValue());
		
		//1. ISI DATABASE UNTUK TEMP
			fillDatabaseReport(1);
			//2. PREVIEW LAPORAN
			paramJudulLaporan = "BUKU PEMBAYARAN PIUTANG";
			
			if (tipeOutputFile.equals("PDF")){
				showPreview("/erp/distribution/reports/arpayment/pembayaranpiutang/bukuarpayment1LengkapDs.jasper", "bukuarpayment1LengkapDs");
			} else if (tipeOutputFile.equals("EXEL")){
				jasperToExel("/erp/distribution/reports/arpayment/pembayaranpiutang/bukuarpayment1LengkapDs.jasper", "bukuarpayment1LengkapDs");
			}
	}
	
	private String paramSalesman = "Semua Salesman";
	private List<ZLapTemplate2> listLapTemplate2 = new ArrayList<ZLapTemplate2>();
	public void fillDatabaseReport(int laporan){

		String paramSpcode = "%";
		try{
			paramSpcode = "%" + ((FSalesman) view.getComboGroup3().getValue()).getSpcode() + "%";
		} catch(Exception ex){}
		
		String paramCustno = "%";
		try{
			paramCustno = "%" + ((FCustomer) view.getComboGroup4().getValue()).getCustno() + "%";
		} catch(Exception ex){}
		
		String paramTunaikredit="%";
		try{
			paramTunaikredit = ((String) view.getComboGroup1().getConvertedValue()) ;
			if (paramTunaikredit.equals("A")){
				paramTunaikredit="%";
			}
		} catch(Exception ex){}
		
		Date arPaymentDateFrom = view.getDateField1From().getValue();
		Date arPaymentDateTo = view.getDateField1To().getValue();
		String SJPengiriman = "%";
		String SJPenagihan = "%";
		try{
			SJPenagihan = "%" + view.getFieldSuratJalanPenagihan().getValue().trim() + "%";
		}catch(Exception ex){}
		
		List<FtArpaymentd> listFtArpaymentd = new ArrayList<FtArpaymentd>();
		if (laporan==1){
			listFtArpaymentd = model.getFtArpaymentdJpaService()
					.findAllByArpaydateAndSJ(arPaymentDateFrom, arPaymentDateTo, SJPengiriman, SJPenagihan, paramTunaikredit, paramSpcode, paramCustno);
		}
		
		fillIntoTemplateReport(laporan, listFtArpaymentd);
	}		
	
	public void fillIntoTemplateReport(int laporan, List<FtArpaymentd> listFtArpaymentd){

		//MENGHINDARI DOUBLE
		listLapTemplate2 = new ArrayList<ZLapTemplate2>();		
		List<ZLapTemplate2> lapTemplate = new LinkedList<ZLapTemplate2>();		
		
				
		for (FtArpaymentd ftArpaymentd: listFtArpaymentd){			
				//JIKA RETUR MAKA DI MINUSKAN
				
				ZLapTemplate2 domain = new ZLapTemplate2();
				
				String salesmanCode  = ftArpaymentd.getFtsaleshBean().getFsalesmanBean().getSpcode();
				String salesmanDesc  = ftArpaymentd.getFtsaleshBean().getFsalesmanBean().getSpname();
				String salesmanName  = salesmanCode + "-" + salesmanDesc;

				String tipeCustomerId = ftArpaymentd.getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getId();
				String tipeCustomerName = tipeCustomerId + "-" + ftArpaymentd.getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getDescription();
				
				String custNo = ftArpaymentd.getFtsaleshBean().getFcustomerBean().getCustno() ;
				String custDesc=ftArpaymentd.getFtsaleshBean().getFcustomerBean().getCustname();
				String custName = custNo +"-" + custDesc;
				
				
				String address1 = ftArpaymentd.getFtsaleshBean().getFcustomerBean().getAddress1();
				String city1 = ftArpaymentd.getFtsaleshBean().getFcustomerBean().getCity1();
				
				String tunaiKredit = ftArpaymentd.getFtsaleshBean().getTunaikredit();
				String tipeFaktur = ftArpaymentd.getFtsaleshBean().getTipefaktur();
				String tipeJual = ftArpaymentd.getFtsaleshBean().getTipejual();
				
				String invoiceno = ftArpaymentd.getFtsaleshBean().getInvoiceno();
				String invoiceRefno = String.valueOf(ftArpaymentd.getFtsaleshBean().getRefno());
				Date invoiceTrdate = ftArpaymentd.getFtsaleshBean().getInvoicedate();
				Date invoiceDuedate = ftArpaymentd.getFtsaleshBean().getDuedate();
				
				
				double totalAmountAmountAfterPpn = ftArpaymentd.getFtsaleshBean().getAmountafterdiscafterppn();
				
				double amountCashPay = ftArpaymentd.getCashamountpay();
				double amountReturPay = ftArpaymentd.getReturamountpay();
				double amountGiroPay = ftArpaymentd.getGiroamountpay();
				double amountTransferPay = ftArpaymentd.getTransferamountpay();
				double amountPotonganPay = ftArpaymentd.getPotonganamount();
				
				if (laporan==1){
					domain.setGrup1("GRUP1");						
					domain.setGrup2("GRUP2");						
					
					if (( (String) view.getOptionGroup1().getValue()).equalsIgnoreCase("Salesman")) {
						domain.setGrup3(salesmanCode);						
					}else if (( (String) view.getOptionGroup1().getValue()).equalsIgnoreCase("Customer")) {
						domain.setGrup3(custNo);												
					}else {
						domain.setGrup3(ftArpaymentd.getFtsaleshBean().getSjpenagihanno().trim());																		
					}
					
					if (domain.getGrup3()==null) {
						domain.setGrup3("");
					}
					//KARNA SORTIR TIDAK BISA LEBIH DARI 7 KARKATER lihat bawah
					if (domain.getGrup3().length()>7){
						domain.setGrup3(domain.getGrup3()
								.substring(domain.getGrup3().length()-7, domain.getGrup3().length()));
					}
					
					domain.setString1(salesmanCode);
					domain.setString2(custName);
					domain.setString3(tunaiKredit.trim() + " " + invoiceno);
					domain.setString4(ftArpaymentd.getFtsaleshBean().getSjpenagihanno());  

					domain.setDate1(invoiceTrdate);
					domain.setDate2(invoiceDuedate);
					
					domain.setDouble1(totalAmountAmountAfterPpn);
					
					domain.setDouble2(amountCashPay);
					domain.setDouble3(amountReturPay);
					domain.setDouble4(amountGiroPay);
					domain.setDouble5(amountTransferPay);
					domain.setDouble6(amountPotonganPay);
					
					lapTemplate.add(domain);
					
				}
		}
		
		//TIDAK BISA UNTUK STRING LENGHT LEBIH BESAR DARI 7 --> LIHAT ATAS
		Collections.sort(lapTemplate, new Comparator<ZLapTemplate2>() {
			@Override
			public int compare(ZLapTemplate2 o1, ZLapTemplate2 o2) {
				//JANGAN ADA NULL
				String str1 = o1.getGrup3().toUpperCase();
				String str2 = o2.getGrup3().toUpperCase();
				return str1.compareTo(str2);
			}
		});

		listLapTemplate2.addAll(lapTemplate);
	}

	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
		
			final Map parameters=new HashMap();
			
			parameters.put("paramJudulLaporan", paramJudulLaporan);
			parameters.put("paramCompanyName", paramCompanyName);
			parameters.put("paramCompanyAddress", paramCompanyAddress);
			parameters.put("paramCompanyPhone", paramCompanyPhone);
			parameters.put("paramSalesman", paramSalesman);
			
			parameters.put("paramDate1", view.getDateField1From().getValue());
			parameters.put("paramDate2", view.getDateField1To().getValue());
	
			parameters.put("paramString1", paramDateFromDateTo);
			
			//CONNECTION
	//		final Connection con = new ReportJdbcConfigHelper().getConnection();
			final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listLapTemplate2);
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
			
			String fileName = "ar_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
			StreamResource resource = new StreamResource( source, fileName);
			resource.setMIMEType("application/pdf");
			resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
			
			view.getUI().getPage().open(resource, "_new_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	public void jasperToExel(String inputFilePath, String outputFilePath){
		try {			
		
			final Map parameters=new HashMap();
			
			parameters.put("paramJudulLaporan", paramJudulLaporan);
			parameters.put("paramCompanyName", paramCompanyName);
			parameters.put("paramCompanyAddress", paramCompanyAddress);
			parameters.put("paramCompanyPhone", paramCompanyPhone);
			parameters.put("paramSalesman", paramSalesman);
			
			parameters.put("paramDate1", view.getDateField1From().getValue());
			parameters.put("paramDate2", view.getDateField1To().getValue());
	
			parameters.put("paramString1", paramDateFromDateTo);
			
			
			//CONNECTION
	//		final Connection con = new ReportJdbcConfigHelper().getConnection();
			final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listLapTemplate2);
			InputStream reportPathStream = getClass().getResourceAsStream(inputFilePath);
			final JasperPrint jasperPrint = JasperFillManager.fillReport(reportPathStream, parameters, dataSource);
			
			
	        //OUTPUT KE FILE
	//		File xlsx = new File("/Users/yhawin/sample.xls");
	       final ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			JRXlsExporter xlsExporter = new JRXlsExporter();
			xlsExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
			xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, true);
			xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, true);
			xlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			xlsExporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, true);
			xlsExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, true);
			xlsExporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, true);
			xlsExporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, new String[]{"Sheet1"});
			xlsExporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, true);
			xlsExporter.exportReport();
	
			
	 		StreamResource.StreamSource source = new StreamSource() {			
				@Override
				public InputStream getStream() {
					byte[] b = null;
						b = out.toByteArray();
					return new ByteArrayInputStream(b);
				}
			};
			
			String fileName = "exelar"  +System.currentTimeMillis() +".xls";
			StreamResource resource = new StreamResource( source, fileName);
			resource.setMIMEType("application/vnd.ms-excel");
			resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
			
			view.getUI().getPage().open(resource, "_new_" , false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}

//class ListZLapTemplate2Comp implements Comparator<ZLapTemplate2>{
//
//	@Override
//	public int compare(ZLapTemplate2 o1, ZLapTemplate2 o2) {
//		if (o1.getGrup3().toString() < o2.getGrup3().toString()){
//			return 1;
//		}else {
//			return -1;
//		}
//	}
//	
//}

