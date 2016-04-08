package org.erp.distribution.salesorder.salesorder.printinvoice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.ZLapTemplate2;
import org.erp.distribution.salesorder.salesorder.retur.SalesOrderReturModel;
import org.erp.distribution.salesorder.salesorder.retur.SalesOrderReturView;
import org.erp.distribution.salesorder.salesorder.retur.windowitem.SalesOrderReturItemModel;
import org.erp.distribution.salesorder.salesorder.sales.SalesOrderModel;
import org.erp.distribution.salesorder.salesorder.sales.SalesOrderView;
import org.erp.distribution.util.HeaderDetilSalesHelper;
import org.erp.distribution.util.HeaderDetilSalesHelperImpl;
import org.erp.distribution.util.KonversiProductAndStock;
import org.erp.distribution.util.KonversiProductAndStockImpl;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;

public class SalesOrderPrintHelper {
	private SalesOrderModel salesOrderModel;
	private SalesOrderView salesOrderView;
	
	private SalesOrderReturModel salesOrderReturModel;
	private SalesOrderReturView salesOrderReturView;
	
	private String jenisFaktur="";
	private int formatFakturPenjualan=0;
	
	public SalesOrderPrintHelper(SalesOrderModel model, SalesOrderView view){		
		this.salesOrderModel = model;
		this.salesOrderView = view;
	}
	public SalesOrderPrintHelper(SalesOrderReturModel returModel, SalesOrderReturView returView){		
		this.salesOrderReturModel = returModel;
		this.salesOrderReturView = returView;
	}
	
	public void previewInvoice(){
		if (getJenisFaktur().equals("PENJUALAN")){
			if (salesOrderModel.getSysvarHelper().getFormatFaktur()>=21 && salesOrderModel.getSysvarHelper().getFormatFaktur()<=100){
				previewInvoicePenjualanNew(salesOrderModel.getSysvarHelper().getFormatFaktur());
			}else {
				previewInvoicePenjualan();
			}
		}else if (getJenisFaktur().equals("RETUR")){
			previewInvoiceReturPenjualanNew(salesOrderReturModel.getSysvarHelper().getFormatFaktur());
//			previewInvoiceReturPenjualanNew(22);
			
		}	
	}
	
	public void previewInvoicePenjualanNew(int formatFaktur){
//		model.getItemHeader().setRefno((long) 273889);
		fillDatabaseReport();
		//2. PREVIEW LAPORAN
		if (formatFaktur==21) {
			
			showPreview("/erp/distribution/reports/salesorder/invoicestd/invoicestd21/invoicestd21Ds.jasper", "notanew");			
		} else if (formatFaktur==22){
			showPreview("/erp/distribution/reports/salesorder/invoicestd/invoicestd22/invoicestd22Ds.jasper", "notanew");			
			
		}
		
	}

	public void previewInvoiceReturPenjualanNew(int formatFaktur){
//		model.getItemHeader().setRefno((long) 273889);
		fillDatabaseReturReport();
		//2. PREVIEW LAPORAN
		if (formatFaktur==21) {
			
			showPreview("/erp/distribution/reports/salesorder/invoicestd/invoicestd21/invoicestd21Ds.jasper", "notanew");			
		} else if (formatFaktur==22){
			showPreview("/erp/distribution/reports/salesorder/invoicestd/invoicestd22/invoicestd22Ds.jasper", "notanew");			
			
		}
		
	}
	
	public void previewInvoicePenjualan(){
		String inputFilePath = "";
		
		if (salesOrderModel.getSysvarHelper().getFormatFaktur()==1) {
			inputFilePath = "/erp/distribution/reports/salesorder/invoice1/standart1.jasper";
		} else if (salesOrderModel.getSysvarHelper().getFormatFaktur()==2) {
			inputFilePath = "/erp/distribution/reports/salesorder/invoice2/standart2.jasper";
		} else if (salesOrderModel.getSysvarHelper().getFormatFaktur()==10) {
			inputFilePath = "/erp/distribution/reports/invoicestd1/invoicestd1.jasper";
		} else if (salesOrderModel.getSysvarHelper().getFormatFaktur()==11) {
			inputFilePath = "/erp/distribution/reports/invoicestd2/invoicestd2.jasper";
		} else if (salesOrderModel.getSysvarHelper().getFormatFaktur()==12) {
			inputFilePath = "/erp/distribution/reports/invoicestd3/invoicestd3.jasper";
		} else if (salesOrderModel.getSysvarHelper().getFormatFaktur()==13) {
			inputFilePath = "/erp/distribution/reports/invoicestd4/invoicestd4.jasper";
		} else if (salesOrderModel.getSysvarHelper().getFormatFaktur()==14) {
			inputFilePath = "/erp/distribution/reports/invoicestd5/invoicestd5.jasper";
		} else if (salesOrderModel.getSysvarHelper().getFormatFaktur()==15) {
			inputFilePath = "/erp/distribution/reports/salesorder/invoicestd/invoicestd1/invoicestd1.jasper";
		}
		
		String outputFilePath = "sales_invoice";
		
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
		if (salesOrderModel.getItemHeader().getTipefaktur().equals("R")) {
			parameters.put("paramJudulFaktur", salesOrderModel.getSysvarHelper().getJudulFakturRetur());
		}else if (salesOrderModel.getItemHeader().getTunaikredit().equals("T")) {
			parameters.put("paramJudulFaktur", salesOrderModel.getSysvarHelper().getJudulFakturTunai());
		}else if (salesOrderModel.getItemHeader().getTunaikredit().equals("K"))  {
			parameters.put("paramJudulFaktur", salesOrderModel.getSysvarHelper().getJudulFakturKredit());			
		} else {
			parameters.put("paramJudulFaktur", "FAKTUR");						
		}
		parameters.put("paramJudulFakturTunai", salesOrderModel.getSysvarHelper().getJudulFakturTunai());
		parameters.put("paramJudulFakturKredit", salesOrderModel.getSysvarHelper().getJudulFakturKredit());			
		parameters.put("paramJudulFakturRetur", salesOrderModel.getSysvarHelper().getJudulFakturRetur());			

		parameters.put("paramTipefaktur","F");

		parameters.put("paramCompanyName", salesOrderModel.getSysvarHelper().getCompanyNameFaktur());
		parameters.put("paramCompanyAddress", salesOrderModel.getSysvarHelper().getCompanyAddressFaktur());
		parameters.put("paramCompanyPhone", salesOrderModel.getSysvarHelper().getCompanyPhoneFaktur());
		parameters.put("paramCompanyNpwp", salesOrderModel.getSysvarHelper().getCompanyNpwpFaktur());

		parameters.put("paramInvoicedateFrom",salesOrderModel.getItemHeader().getInvoicedate());
		parameters.put("paramInvoicedateTo", salesOrderModel.getItemHeader().getInvoicedate());
		parameters.put("paramRefnoFrom", salesOrderModel.getItemHeader().getRefno());
		parameters.put("paramRefnoTo", salesOrderModel.getItemHeader().getRefno());
		parameters.put("paramInvoiceno","%");

		
		parameters.put("paramRefno", salesOrderModel.getItemHeader().getRefno());
		
		parameters.put("paramProductShortname", salesOrderModel.getSysvarHelper().isShortNamePadaFaktur());


		//CONNECTION
		final Connection con = new ReportJdbcConfigHelper().getConnection();
		
		
		StreamResource.StreamSource source = new StreamSource() {			
			@Override
			public InputStream getStream() {
				byte[] b = null;
				try {
					b = JasperRunManager.runReportToPdf(report, parameters, con);
				} catch (JRException ex) {
//					System.out.println(ex);
				}
				return new ByteArrayInputStream(b);
			}
		};
		
		String fileName = "salesorder_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		salesOrderView.getUI().getPage().open(resource, "_new_print_nota_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
		
	}
	
	public void previewInvoiceRetail(){
		String inputFilePath = "";
		
		if (salesOrderModel.getSysvarHelper().getFormatFakturRetail()==101) {
			inputFilePath = "/erp/distribution/reports/invoiceretail1/invoicestd1.jasper";
		} else if (salesOrderModel.getSysvarHelper().getFormatFakturRetail()==102) {
			inputFilePath = "/erp/distribution/reports/invoiceretail2/invoicestd2.jasper";
		} else if (salesOrderModel.getSysvarHelper().getFormatFakturRetail()==103) {
			inputFilePath = "/erp/distribution/reports/invoiceretail3/invoicestd3.jasper";
		} else if (salesOrderModel.getSysvarHelper().getFormatFakturRetail()==104) {
			inputFilePath = "/erp/distribution/reports/invoiceretail4/invoicestd4.jasper";
		} else if (salesOrderModel.getSysvarHelper().getFormatFakturRetail()==105) {
			inputFilePath = "/erp/distribution/reports/invoiceretail5/invoicestd5.jasper";
		} else if (salesOrderModel.getSysvarHelper().getFormatFakturRetail()==115) {
			inputFilePath = "/erp/distribution/reports/salesorder/invoiceret/invoiceret1/invoiceret1.jasper";
		}
		
		String outputFilePath = "sls_inv_r";
		
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
		if (salesOrderModel.getItemHeader().getTipefaktur().equals("R")) {
			parameters.put("paramJudulFaktur", salesOrderModel.getSysvarHelper().getJudulFakturRetail());
		}else if (salesOrderModel.getItemHeader().getTunaikredit().equals("T")) {
			parameters.put("paramJudulFaktur", salesOrderModel.getSysvarHelper().getJudulFakturRetail());
		}else if (salesOrderModel.getItemHeader().getTunaikredit().equals("K"))  {
			parameters.put("paramJudulFaktur", salesOrderModel.getSysvarHelper().getJudulFakturRetail());			
		} else {
			parameters.put("paramJudulFaktur", "FAKTUR");						
		}
		parameters.put("paramJudulFakturTunai", salesOrderModel.getSysvarHelper().getJudulFakturRetail());
		parameters.put("paramJudulFakturKredit", salesOrderModel.getSysvarHelper().getJudulFakturRetail());			
		parameters.put("paramJudulFakturRetur", salesOrderModel.getSysvarHelper().getJudulFakturRetail());			

		parameters.put("paramTipefaktur","F");

		parameters.put("paramCompanyName", salesOrderModel.getSysvarHelper().getCompanyNameFakturRetail());
		parameters.put("paramCompanyAddress", salesOrderModel.getSysvarHelper().getCompanyAddressFakturRetail());
		parameters.put("paramCompanyPhone", salesOrderModel.getSysvarHelper().getCompanyPhoneFakturRetail());
		parameters.put("paramCompanyNpwp", salesOrderModel.getSysvarHelper().getCompanyNpwpFakturRetail());

		parameters.put("paramInvoicedateFrom", salesOrderModel.getItemHeader().getInvoicedate());
		parameters.put("paramInvoicedateTo", salesOrderModel.getItemHeader().getInvoicedate());
		parameters.put("paramRefnoFrom", salesOrderModel.getItemHeader().getRefno());
		parameters.put("paramRefnoTo", salesOrderModel.getItemHeader().getRefno());
		parameters.put("paramInvoiceno","%");

		parameters.put("paramRefno", salesOrderModel.getItemHeader().getRefno());
		
		parameters.put("paramProductShortname", salesOrderModel.getSysvarHelper().isShortNamePadaFaktur());

		//CONNECTION
		final Connection con = new ReportJdbcConfigHelper().getConnection();
		
		StreamResource.StreamSource source = new StreamSource() {			
			@Override
			public InputStream getStream() {
				byte[] b = null;
				try {
					b = JasperRunManager.runReportToPdf(report, parameters, con);
				} catch (JRException ex) {
//					System.out.println(ex);
				}
				return new ByteArrayInputStream(b);
			}
		};
		
		String fileName = "so_r_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		salesOrderView.getUI().getPage().open(resource, "_new_notar_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
		
	}
	
	private List<ZLapTemplate2> lapTemplate2 = new ArrayList<ZLapTemplate2>();
	public void fillDatabaseReport(){

		long paramRefno=0;
		try{
			paramRefno = salesOrderModel.getItemHeader().getRefno();
		}catch(Exception ex){}
		
		List<FtSalesd> listFtSalesd = new ArrayList<FtSalesd>();
		listFtSalesd = salesOrderModel.getFtSalesdJpaService()
				.findAllByRefno(paramRefno);			
		
		fillIntoTemplateReport(listFtSalesd);
	}		
	
	public void fillIntoTemplateReport(List<FtSalesd> listFtSalesd){

		//MENGHINDARI DOUBLE
		lapTemplate2 = new ArrayList<ZLapTemplate2>();		
				
		for (FtSalesd ftSalesd: listFtSalesd){			
				FtSalesd newFtSalesd = new FtSalesd();
				HeaderDetilSalesHelper headerDetilHelper = new HeaderDetilSalesHelperImpl(ftSalesd);
				headerDetilHelper.setRoundedTotal(true);
				newFtSalesd = headerDetilHelper.getFillFtSalesd();
				
				ZLapTemplate2 domain = new ZLapTemplate2();

				String vendorName  = ftSalesd.getFproductBean().getFvendorBean().getVcode()  + "-" + ftSalesd.getFproductBean().getFvendorBean().getVname();
				String areaName  = ftSalesd.getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getId()  
						+ "-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getDescription();
				String tipeName = ftSalesd.getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getId()
						+ "-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getDescription();
				String custName = ftSalesd.getFtsaleshBean().getFcustomerBean().getCustname() + " (" + 
						"-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getCustno() + ")";
				String productGroupName  = ftSalesd.getFproductBean().getFproductgroupBean().getId() 
						+ "-" + ftSalesd.getFproductBean().getFproductgroupBean().getDescription();
				
				String custAddress1 = ftSalesd.getFtsaleshBean().getFcustomerBean().getAddress1();
				String custPhone1 = "";
				if (ftSalesd.getFtsaleshBean().getFcustomerBean().getPhone1()!=null) {
					if (! ftSalesd.getFtsaleshBean().getFcustomerBean().getPhone1().trim().equals("")){
						custPhone1 = " Telp " + ftSalesd.getFtsaleshBean().getFcustomerBean().getPhone1().trim();
					}
				} 
				String custCity1 = "";
				if (ftSalesd.getFtsaleshBean().getFcustomerBean().getCity1()!=null) {
					if (! ftSalesd.getFtsaleshBean().getFcustomerBean().getCity1().trim().equals("")){
						custCity1 = " Telp " + ftSalesd.getFtsaleshBean().getFcustomerBean().getCity1().trim();
					}
				} 
				custCity1 += custPhone1;
				
				String tunaiKredit = ftSalesd.getFtsaleshBean().getTunaikredit();
				String tipeFaktur = ftSalesd.getFtsaleshBean().getTipefaktur();
				String tipeJual = ftSalesd.getFtsaleshBean().getTipejual();
				
				String invoiceno = ftSalesd.getFtsaleshBean().getInvoiceno();
				String invoiceRefno = String.valueOf(ftSalesd.getFtsaleshBean().getRefno());
				Date invoiceTrdate = ftSalesd.getFtsaleshBean().getInvoicedate();
				Date invoiceDuedate = ftSalesd.getFtsaleshBean().getDuedate();
				String productName = ftSalesd.getFproductBean().getPcode() + "-" +ftSalesd.getFproductBean().getPname();
				
				String salesmanName = ftSalesd.getFtsaleshBean().getFsalesmanBean().getSpcode() + "-" + ftSalesd.getFtsaleshBean().getFsalesmanBean().getSpname();
				String nomorOrder = ftSalesd.getFtsaleshBean().getTunaikredit() + "/" + ftSalesd.getFtsaleshBean().getOrderno();
				
				double totalBrutoDpp = newFtSalesd.getSubtotal();
				double totalBrutoAfterPPn = newFtSalesd.getSubtotalafterppn();
				double totalDisc1BarangAfterPpn = newFtSalesd.getDisc1rpafterppn();
				double totalDisc2BarangAfterPpn = newFtSalesd.getDisc2rpafterppn();
				double totalSubTotalAfterPpn = newFtSalesd.getSubtotalafterdisc2afterppn();
				
				double totalDisc1NotaAfterPpn = newFtSalesd.getDiscNota1RpAfterPpn() ;
				double totalDiscNotaAfterPpn = newFtSalesd.getDiscNotaRpAfterPpn();
				
				double totalDpp = newFtSalesd.getSubTotalAfterDiscNota();
				double totalAfterDisc2AfterPpn = newFtSalesd.getSubTotalAfterDiscNotaAfterPpn();
				
				domain.setGrup1(invoiceno);
				domain.setGrup2("-");
				domain.setGrup3(invoiceno);			
			
			
				String judulFaktur = "FAKTUR PENJUALAN";
				if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("R")) {
					judulFaktur = salesOrderModel.getSysvarHelper().getJudulFakturRetur();
				}else if (ftSalesd.getFtsaleshBean().getTunaikredit().equals("T")) {
					judulFaktur= salesOrderModel.getSysvarHelper().getJudulFakturTunai();
				}else if (ftSalesd.getFtsaleshBean().getTunaikredit().equals("K"))  {
					judulFaktur = salesOrderModel.getSysvarHelper().getJudulFakturKredit();			
				} else {
					judulFaktur = "FAKTUR";						
				}
				String companyInvoiceName =  salesOrderModel.getSysvarHelper().getCompanyNameFaktur();
				String companyInvoiceAddress =  salesOrderModel.getSysvarHelper().getCompanyAddressFaktur();
				String companyInvoicePhone =  salesOrderModel.getSysvarHelper().getCompanyPhoneFaktur();
				String companyInvoiceNpwp =  salesOrderModel.getSysvarHelper().getCompanyNpwpFaktur();
				
				boolean isShortNameAndPackaging = salesOrderModel.getSysvarHelper().isShortNamePadaFaktur();
				String kodeProduct = "";
				String namaProduct ="";
				if (isShortNameAndPackaging) {
					kodeProduct = ftSalesd.getFproductBean().getShortcode();
					namaProduct = ftSalesd.getFproductBean().getShortname() + " " + ftSalesd.getFproductBean().getShortpackaging();
				}else {
					kodeProduct = ftSalesd.getFproductBean().getPcode();
					namaProduct = ftSalesd.getFproductBean().getPname() + " " + ftSalesd.getFproductBean().getPackaging();					
				}
				
				domain.setString1(judulFaktur);
				domain.setString2(companyInvoiceName);
				domain.setString3(companyInvoiceAddress);
				domain.setString4(companyInvoicePhone);  
				domain.setString5(companyInvoiceNpwp);  
				
				domain.setDate1(invoiceTrdate);
				domain.setDate2(invoiceDuedate);
				
				domain.setString6(custName);  
				domain.setString7(custAddress1);  
				domain.setString8(custCity1);  
	
				domain.setString9(invoiceno);  
				domain.setString10(salesmanName);  
				domain.setString11(nomorOrder);
				
				KonversiProductAndStock kps = new KonversiProductAndStockImpl(ftSalesd.getQty(), ftSalesd.getFproductBean());						
				int qtyKrt = kps.getBesFromPcs();
				
				String qtyReguler = "";
				String qtyFreeGood = "";
				if (salesOrderModel.getSysvarHelper().getFormatFaktur()==22) {
					qtyReguler = kps.getBesSedKecStringUom();					
					qtyFreeGood = "*" + kps.getBesSedKecStringUom();								
				}else {
					qtyReguler = kps.getBesSedKecString();					
					qtyFreeGood = "*" + kps.getBesSedKecString();								
				}
				
				if (! ftSalesd.getId().getFreegood()){
					qtyFreeGood="";
				}else {
					qtyReguler="";
					namaProduct += "(Bonus)";
				}
				
				domain.setString12(kodeProduct);  
				
				domain.setString13(namaProduct);  
				domain.setString14(qtyReguler);  
				domain.setString15(qtyFreeGood);  
				
				domain.setDouble1(newFtSalesd.getSpriceafterppn());					
				domain.setDouble2(totalBrutoAfterPPn);	
				
				domain.setDouble3(ftSalesd.getDisc1());
				domain.setDouble4(totalDisc1BarangAfterPpn);
				domain.setDouble5(ftSalesd.getDisc2());
				domain.setDouble6(totalDisc2BarangAfterPpn);

				domain.setDouble7(totalSubTotalAfterPpn);
				
				domain.setDouble8(ftSalesd.getFtsaleshBean().getDisc1());
				domain.setDouble9(totalDisc1NotaAfterPpn);
				domain.setDouble10(ftSalesd.getFtsaleshBean().getDisc());
				domain.setDouble11(totalDiscNotaAfterPpn);
				domain.setDouble12(totalDpp);
				domain.setDouble13(totalAfterDisc2AfterPpn);
									
				domain.setInt2(ftSalesd.getFtsaleshBean().getPrintcounter());
				lapTemplate2.add(domain);						
			
		}
		
	}

	public void fillDatabaseReturReport(){

		long paramRefno=0;
		try{
			paramRefno = salesOrderReturModel.getItemHeader().getRefno();
		}catch(Exception ex){}
		
		List<FtSalesd> listFtSalesd = new ArrayList<FtSalesd>();
		listFtSalesd = salesOrderReturModel.getFtSalesdJpaService()
				.findAllByRefno(paramRefno);			
		
		fillIntoTemplateReturReport(listFtSalesd);
	}		
	
	public void fillIntoTemplateReturReport(List<FtSalesd> listFtSalesd){

		//MENGHINDARI DOUBLE
		lapTemplate2 = new ArrayList<ZLapTemplate2>();		
				
		for (FtSalesd ftSalesd: listFtSalesd){			
				FtSalesd newFtSalesd = new FtSalesd();
				HeaderDetilSalesHelper headerDetilHelper = new HeaderDetilSalesHelperImpl(ftSalesd);
				headerDetilHelper.setRoundedTotal(true);
				newFtSalesd = headerDetilHelper.getFillFtSalesd();
				
				ZLapTemplate2 domain = new ZLapTemplate2();

				String vendorName  = ftSalesd.getFproductBean().getFvendorBean().getVcode()  + "-" + ftSalesd.getFproductBean().getFvendorBean().getVname();
				String areaName  = ftSalesd.getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getId()  
						+ "-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getDescription();
				String tipeName = ftSalesd.getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getId()
						+ "-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getDescription();
				String custName = ftSalesd.getFtsaleshBean().getFcustomerBean().getCustname() + " (" + 
						"-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getCustno() + ")";
				String productGroupName  = ftSalesd.getFproductBean().getFproductgroupBean().getId() 
						+ "-" + ftSalesd.getFproductBean().getFproductgroupBean().getDescription();
				
				String custAddress1 = ftSalesd.getFtsaleshBean().getFcustomerBean().getAddress1();
				String custPhone1 = "";
				if (ftSalesd.getFtsaleshBean().getFcustomerBean().getPhone1()!=null) {
					if (! ftSalesd.getFtsaleshBean().getFcustomerBean().getPhone1().trim().equals("")){
						custPhone1 = " Telp " + ftSalesd.getFtsaleshBean().getFcustomerBean().getPhone1().trim();
					}
				} 
				String custCity1 = "";
				if (ftSalesd.getFtsaleshBean().getFcustomerBean().getCity1()!=null) {
					if (! ftSalesd.getFtsaleshBean().getFcustomerBean().getCity1().trim().equals("")){
						custCity1 = " Telp " + ftSalesd.getFtsaleshBean().getFcustomerBean().getCity1().trim();
					}
				} 
				custCity1 += custPhone1;
				
				String tunaiKredit = ftSalesd.getFtsaleshBean().getTunaikredit();
				String tipeFaktur = ftSalesd.getFtsaleshBean().getTipefaktur();
				String tipeJual = ftSalesd.getFtsaleshBean().getTipejual();
				
				String invoiceno = ftSalesd.getFtsaleshBean().getInvoiceno();
				String invoiceRefno = String.valueOf(ftSalesd.getFtsaleshBean().getRefno());
				Date invoiceTrdate = ftSalesd.getFtsaleshBean().getInvoicedate();
				Date invoiceDuedate = ftSalesd.getFtsaleshBean().getDuedate();
				String productName = ftSalesd.getFproductBean().getPcode() + "-" +ftSalesd.getFproductBean().getPname();
				
				String salesmanName = ftSalesd.getFtsaleshBean().getFsalesmanBean().getSpcode() + "-" + ftSalesd.getFtsaleshBean().getFsalesmanBean().getSpname();
//				String nomorOrder = ftSalesd.getFtsaleshBean().getTunaikredit() + "/" + ftSalesd.getFtsaleshBean().getOrderno();
				String nomorOrder = ftSalesd.getFtsaleshBean().getFwarehouseBean().getId() + "/" + ftSalesd.getFtsaleshBean().getOrderno();
				
				double totalBrutoDpp = newFtSalesd.getSubtotal();
				double totalBrutoAfterPPn = newFtSalesd.getSubtotalafterppn();
				double totalDisc1BarangAfterPpn = newFtSalesd.getDisc1rpafterppn();
				double totalDisc2BarangAfterPpn = newFtSalesd.getDisc2rpafterppn();
				double totalSubTotalAfterPpn = newFtSalesd.getSubtotalafterdisc2afterppn();
				
				double totalDisc1NotaAfterPpn = newFtSalesd.getDiscNota1RpAfterPpn() ;
				double totalDiscNotaAfterPpn = newFtSalesd.getDiscNotaRpAfterPpn();
				
				double totalDpp = newFtSalesd.getSubTotalAfterDiscNota();
				double totalAfterDisc2AfterPpn = newFtSalesd.getSubTotalAfterDiscNotaAfterPpn();
				
				domain.setGrup1(invoiceno);
				domain.setGrup2("-");
				domain.setGrup3(invoiceno);			
			
			
				String judulFaktur = "RETUR PENJUALAN";
				if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("R")) {
					judulFaktur = salesOrderReturModel.getSysvarHelper().getJudulFakturRetur();
				}else if (ftSalesd.getFtsaleshBean().getTunaikredit().equals("T")) {
					judulFaktur= salesOrderReturModel.getSysvarHelper().getJudulFakturTunai();
				}else if (ftSalesd.getFtsaleshBean().getTunaikredit().equals("K"))  {
					judulFaktur = salesOrderReturModel.getSysvarHelper().getJudulFakturKredit();			
				} else {
					judulFaktur = "RETUR";						
				}
				String companyInvoiceName =  salesOrderReturModel.getSysvarHelper().getCompanyNameFaktur();
				String companyInvoiceAddress =  salesOrderReturModel.getSysvarHelper().getCompanyAddressFaktur();
				String companyInvoicePhone =  salesOrderReturModel.getSysvarHelper().getCompanyPhoneFaktur();
				String companyInvoiceNpwp =  salesOrderReturModel.getSysvarHelper().getCompanyNpwpFaktur();
				
				boolean isShortNameAndPackaging = salesOrderReturModel.getSysvarHelper().isShortNamePadaFaktur();
				String kodeProduct = "";
				String namaProduct ="";
				if (isShortNameAndPackaging) {
					kodeProduct = ftSalesd.getFproductBean().getShortcode();
					namaProduct = ftSalesd.getFproductBean().getShortname() + " " + ftSalesd.getFproductBean().getShortpackaging();
				}else {
					kodeProduct = ftSalesd.getFproductBean().getPcode();
					namaProduct = ftSalesd.getFproductBean().getPname() + " " + ftSalesd.getFproductBean().getPackaging();					
				}
				
				domain.setString1(judulFaktur);
				domain.setString2(companyInvoiceName);
				domain.setString3(companyInvoiceAddress);
				domain.setString4(companyInvoicePhone);  
				domain.setString5(companyInvoiceNpwp);  
				
				domain.setDate1(invoiceTrdate);
				domain.setDate2(invoiceDuedate);
				
				domain.setString6(custName);  
				domain.setString7(custAddress1);  
				domain.setString8(custCity1);  
	
				domain.setString9(invoiceno);  
				domain.setString10(salesmanName);  
				domain.setString11(nomorOrder);
				
				KonversiProductAndStock kps = new KonversiProductAndStockImpl(ftSalesd.getQty(), ftSalesd.getFproductBean());						
				int qtyKrt = kps.getBesFromPcs();
				
				String qtyReguler = "";
				String qtyFreeGood = "";
				System.out.println("ISI HELPER: " + salesOrderReturModel.getSysvarHelper().getFormatFaktur());
				if (salesOrderReturModel.getSysvarHelper().getFormatFaktur()==22) {
					qtyReguler = kps.getBesSedKecStringUom();					
					qtyFreeGood = "*" + kps.getBesSedKecStringUom();								
				}else {
					qtyReguler = kps.getBesSedKecString();					
					qtyFreeGood = "*" + kps.getBesSedKecString();								
				}
				
				if (! ftSalesd.getId().getFreegood()){
					qtyFreeGood="";
				}else {
					qtyReguler="";
					namaProduct += "(Bonus)";
				}
				
				domain.setString12(kodeProduct);  
				
				domain.setString13(namaProduct);  
				domain.setString14(qtyReguler);  
				domain.setString15(qtyFreeGood);  
				
				domain.setDouble1(newFtSalesd.getSpriceafterppn());					
				domain.setDouble2(totalBrutoAfterPPn);	
				
				domain.setDouble3(ftSalesd.getDisc1());
				domain.setDouble4(totalDisc1BarangAfterPpn);
				domain.setDouble5(ftSalesd.getDisc2());
				domain.setDouble6(totalDisc2BarangAfterPpn);

				domain.setDouble7(totalSubTotalAfterPpn);
				
				domain.setDouble8(ftSalesd.getFtsaleshBean().getDisc1());
				domain.setDouble9(totalDisc1NotaAfterPpn);
				domain.setDouble10(ftSalesd.getFtsaleshBean().getDisc());
				domain.setDouble11(totalDiscNotaAfterPpn);
				domain.setDouble12(totalDpp);
				domain.setDouble13(totalAfterDisc2AfterPpn);
									
				domain.setInt2(ftSalesd.getFtsaleshBean().getPrintcounter());
				lapTemplate2.add(domain);						
			
		}
		
	}
	
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
//			final JasperReport report;
//			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
		final Map parameters=new HashMap();
		parameters.put("CompanyName","");
		
		
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
		
		String fileName = "sales_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		if (getJenisFaktur().equals("PENJUALAN")){
			salesOrderView.getUI().getPage().open(resource, "_new_" + outputFilePath, false);
		}else if (getJenisFaktur().equals("RETUR")){
			salesOrderReturView.getUI().getPage().open(resource, "_new_" + outputFilePath, false);
		}	
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	
		
	}
	

	public int getFormatFakturPenjualan() {
		return formatFakturPenjualan;
	}

	public void setFormatFakturPenjualan(int formatFakturPenjualan) {
		this.formatFakturPenjualan = formatFakturPenjualan;
	}

	public String getJenisFaktur() {
		return jenisFaktur;
	}

	public void setJenisFaktur(String jenisFaktur) {
		this.jenisFaktur = jenisFaktur;
	}


	
	

}
