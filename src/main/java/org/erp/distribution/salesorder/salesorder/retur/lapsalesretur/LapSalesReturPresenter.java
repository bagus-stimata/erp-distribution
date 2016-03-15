package org.erp.distribution.salesorder.salesorder.retur.lapsalesretur;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.ZLapTemplate2;
import org.erp.distribution.util.HeaderDetilSalesHelper;
import org.erp.distribution.util.HeaderDetilSalesHelperImpl;
import org.erp.distribution.util.KonversiProductAndStock;
import org.erp.distribution.util.KonversiProductAndStockImpl;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LapSalesReturPresenter implements ClickListener{
	private LapSalesReturModel model;
	private LapSalesReturView view;

	public LapSalesReturPresenter(LapSalesReturModel model, LapSalesReturView view){
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
		
	private String paramCompanyName = "W-DES";
	private String paramCompanyAddress = "Jl. Kauman Gang IV/A Malang";
	private String paramCompanyPhone = "Telp.082143574692";
	private String paramJudulLaporan = "LAPORAN SALES";
	
	public void printForm(){
		paramCompanyName = model.getSysvarHelper().getCompanyNameFaktur();
		paramCompanyAddress = model.getSysvarHelper().getCompanyAddressFaktur();
		paramCompanyPhone = model.getSysvarHelper().getCompanyPhoneFaktur();
		
		//1. ISI DATABASE UNTUK TEMP
		if (view.getCheckBoxOutput1().getValue()==true){
			fillDatabaseReport(1);
			//2. PREVIEW LAPORAN
			paramJudulLaporan = "Laporan Penjualan Customer-Per Area";
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderpernota/lapsalesorderperareapernota1LengkapDs.jasper", "lapsalesorderperareapernota1Lengkap");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderpernota/lapsalesorderperareapernota1Ds.jasper", "lapsalesorderperareapernota1");
			}
		}
		if (view.getCheckBoxOutput2().getValue()==true){
			fillDatabaseReport(2);
			paramJudulLaporan = "Laporan Penjualan Customer-Per Tipe Outlet";
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderpernota/lapsalesorderpertipepernota1LengkapDs.jasper", "lapsalesorderpertipepernota1Lengkap");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderpernota/lapsalesorderpertipepernota1Ds.jasper", "lapsalesorderpertipepernota1");
			}
		}
		
		if (view.getCheckBoxOutput3().getValue()==true){
			fillDatabaseReport(3);
			paramJudulLaporan = "Laporan Penjualan Per Barang-Per Supplier";
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalesvendorperbarang/lapsalesvendorperbarang1LengkapDs.jasper", "lapsalesvendorperbaranglengkap1");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalesvendorperbarang/lapsalesvendorperbarang1Ds.jasper", "lapsalesvendorperbarang1");
			}
		}
		if (view.getCheckBoxOutput4().getValue()==true){
			fillDatabaseReport(4);
			paramJudulLaporan = "Laporan Penjualan Per Barang-Per Area";
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalesareaperbarang/lapsalesareaperbarang1LengkapDs.jasper", "lapsalesareaperbaranglengkap1");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalesareaperbarang/lapsalesareaperbarang1Ds.jasper", "lapsalesareaperbarang1");
			}
		}
		if (view.getCheckBoxOutput5().getValue()==true){
			fillDatabaseReport(5);
			paramJudulLaporan = "Laporan Penjualan Per Barang-Per Tipe Customer";
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalestipecustperbarang/lapsalestipecustperbarang1LengkapDs.jasper", "lapsalestipecustperbaranglengkap1");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalestipecustperbarang/lapsalestipecustperbarang1Ds.jasper", "lapsalestipecustperbarang1");
			}
		}
		if (view.getCheckBoxOutput6().getValue()==true){
			fillDatabaseReport(6);
			paramJudulLaporan = "Laporan Penjualan Per Barang-Per Grup Barang";
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalesproductgroupperbarang/lapsalesproductgroupperbarang1LengkapDs.jasper", "lapsalesproductgroupperbaranglengkap1");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalesproductgroupperbarang/lapsalesproductgroupperbarang1Ds.jasper", "lapsalesproductgroupperbarang1");
			}
		}
		
		if (view.getCheckBoxOutput10().getValue()==true){
			fillDatabaseReport(10);
			paramJudulLaporan = "Laporan Penjualan Total Supplier";
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesordertotal/rupiahsaja/lapsalesordertotal1LengkapDs.jasper", "lappenjulantotalsupplierlengkap");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesordertotal/rupiahsaja/lapsalesordertotal1Ds.jasper", "lappenjualansupplier");
			}
		}
		if (view.getCheckBoxOutput11().getValue()==true){
			fillDatabaseReport(11);
			paramJudulLaporan = "Laporan Penjualan Total Customer";
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesordertotal/rupiahsaja/lapsalesordertotal1LengkapDs.jasper", "lappenjulantotalsupplierlengkap");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesordertotal/rupiahsaja/lapsalesordertotal1Ds.jasper", "lappenjualansupplier");
			}
		}
		if (view.getCheckBoxOutput12().getValue()==true){
			fillDatabaseReport(12);
			paramJudulLaporan = "Laporan Penjualan Total Barang";
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesordertotal/denganbarang/lapsalesordertotal1LengkapDs.jasper", "lapsalesordertotal1LengkapDs");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesordertotal/denganbarang/lapsalesordertotal1Ds.jasper", "lapsalesordertotal1Ds");
			}
		}
		if (view.getCheckBoxOutput13().getValue()==true){
			fillDatabaseReport(13);
			paramJudulLaporan = "Laporan Penjualan Total Salesman";
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesordertotal/rupiahsaja/lapsalesordertotal1LengkapDs.jasper", "lappenjulantotalsupplierlengkap");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesordertotal/rupiahsaja/lapsalesordertotal1Ds.jasper", "lappenjualansupplier");
			}
		}
		
		
	}
	private String paramSalesman = "Semua Salesman";
	private List<ZLapTemplate2> lapTemplate2 = new ArrayList<ZLapTemplate2>();
	public void fillDatabaseReport(int laporan){

		String paramSpcode = "%";
		paramSalesman = "Semua Salesman";
		try{
			paramSpcode = "%" + ((FSalesman) view.getComboGroup0().getValue()).getSpcode() + "%";
			paramSalesman = paramSpcode + "-" + ((FSalesman) view.getComboGroup0().getValue()).getSpname();
		} catch(Exception ex){}
		String paramVcode = "%";
		try{
			paramVcode = "%" + ((FVendor) view.getComboGroup1().getValue()).getVcode() + "%";
		} catch(Exception ex){}
		String paramCustno = "%";
		try{
			paramCustno = "%" + ((FCustomer) view.getComboGroup2().getValue()).getCustno() + "%";
		} catch(Exception ex){}
		String paramArea = "%";
		try{
			paramArea = "%" + ((FArea) view.getComboGroup3().getValue()).getId() + "%";
		} catch(Exception ex){}
		String paramSubArea = "%";
		try{
			paramSubArea = "%" + ((FSubarea) view.getComboGroup4().getValue()).getId() + "%";
		} catch(Exception ex){}
		
		String paramPcode = "%";
		try{
			paramPcode = "%" + view.getTextField1().getValue() + "%";
		} catch(Exception ex){}
		String paramPname = "%";
		try{
			paramPname = "%" + view.getTextField2().getValue() + "%";
		} catch(Exception ex){}
		String paramProductGroup = "%";
		try{
			paramProductGroup = "%" + ((FProductgroup) view.getComboGroup5().getValue()).getId() + "%";
		} catch(Exception ex){}
		
		Date paramTrdate = view.getDateField1From().getValue();
		Date paramDuedate = view.getDateField1To().getValue();
		String paramTipeFaktur = "%";
		
		List<FtSalesd> listFtSalesd = new ArrayList<FtSalesd>();
		if (laporan==1){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByAreaAndInvoice(paramSpcode, paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);			
		}else if (laporan==2){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByTipeAndInvoice(paramSpcode, paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);
			
		}else if (laporan==3){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByVendor(paramSpcode, paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);
		}else if (laporan==4){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByArea(paramSpcode, paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);			
		}else if (laporan==5){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByTipeCust(paramSpcode, paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);			
		}else if (laporan==6){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByProductGroup(paramSpcode, paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);		
			
			
		}else if (laporan==12){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByForTotalBarang(paramSpcode, paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);			

	
		}else if (laporan==10){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByForTotalSupplier(paramSpcode, paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);			
		}else if (laporan==11){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByForTotalCustomer(paramSpcode, paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);			
		}else if (laporan==13){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByForTotalSalesman(paramSpcode, paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);			
		
		}
		
		fillIntoTemplateReport(laporan, listFtSalesd);
	}		
	
	public void fillIntoTemplateReport(int laporan, List<FtSalesd> listFtSalesd){

		//MENGHINDARI DOUBLE
		lapTemplate2 = new ArrayList<ZLapTemplate2>();		
				
		for (FtSalesd ftSalesd: listFtSalesd){			
				FtSalesd newFtSalesd = new FtSalesd();
				//JIKA RETUR MAKA DI MINUSKAN
//				if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("R")) {
//					ftSalesd.setSprice(- ftSalesd.getSprice());
//					ftSalesd.setQty(-ftSalesd.getQty());
//				}
				HeaderDetilSalesHelper headerDetilHelper = new HeaderDetilSalesHelperImpl(ftSalesd);
				newFtSalesd = headerDetilHelper.getFillFtSalesd();
				
				ZLapTemplate2 domain = new ZLapTemplate2();

				String vendorCode  = "";
				String vendorName  = "";
				try{
					vendorCode  = ftSalesd.getFproductBean().getFvendorBean().getVcode();
					vendorName  = vendorCode  + "-" + ftSalesd.getFproductBean().getFvendorBean().getVname();
				} catch(Exception ex){}
				
				String salesmanCode  = ftSalesd.getFtsaleshBean().getFsalesmanBean().getSpcode();
				String salesmanDesc  = ftSalesd.getFtsaleshBean().getFsalesmanBean().getSpname();
				String salesmanName  = salesmanCode + "-" + salesmanDesc;

				String areaId  = ftSalesd.getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getId();
				String areaName  = areaId + "-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getDescription();
				String tipeCustomerId = ftSalesd.getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getId();
				String tipeCustomerName = tipeCustomerId + "-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getDescription();
				
				String custNo = ftSalesd.getFtsaleshBean().getFcustomerBean().getCustno() ;
				String custDesc=ftSalesd.getFtsaleshBean().getFcustomerBean().getCustname();
				String custName = custNo +"-" + custDesc;
				
				String productGroupId  = ftSalesd.getFproductBean().getFproductgroupBean().getId();
				String productGroupName  = productGroupId + "-" + ftSalesd.getFproductBean().getFproductgroupBean().getDescription();
				
				String address1 = ftSalesd.getFtsaleshBean().getFcustomerBean().getAddress1();
				String city1 = ftSalesd.getFtsaleshBean().getFcustomerBean().getCity1();
				
				String tunaiKredit = ftSalesd.getFtsaleshBean().getTunaikredit();
				String tipeFaktur = ftSalesd.getFtsaleshBean().getTipefaktur();
				String tipeJual = ftSalesd.getFtsaleshBean().getTipejual();
				
				String invoiceno = ftSalesd.getFtsaleshBean().getInvoiceno();
				String invoiceRefno = String.valueOf(ftSalesd.getFtsaleshBean().getRefno());
				Date invoiceTrdate = ftSalesd.getFtsaleshBean().getInvoicedate();
				Date invoiceDuedate = ftSalesd.getFtsaleshBean().getDuedate();
				
				String productCode = ftSalesd.getFproductBean().getPcode();
				String productDesc = ftSalesd.getFproductBean().getPname();
				String productName =  productCode + "-" + productDesc;
				String productPackaging =  ftSalesd.getFproductBean().getPackaging();
				
				double totalBrutoDpp = newFtSalesd.getSubtotal();
				double totalDiscBarangDpp = newFtSalesd.getDisc1rp() + newFtSalesd.getDisc2rp();
				double totalDiscNotaDpp = newFtSalesd.getDiscNota1Rp() + newFtSalesd.getDiscNotaRp();
				double totalDpp = newFtSalesd.getSubTotalAfterDiscNota();
				double totalAfterDisc2AfterPpn = newFtSalesd.getSubTotalAfterDiscNotaAfterPpn();
				
				if (laporan==1){
					domain.setGrup1("GRAND TOTAL");
					domain.setGrup2(areaName);
					domain.setString1(areaId);		
					domain.setGrup3(invoiceno);			
				} else if (laporan==2){
					domain.setGrup1("GRAND TOTAL");
					domain.setGrup2(tipeCustomerName);
					domain.setString1(tipeCustomerId);		
					domain.setGrup3(invoiceno);			

				}else if (laporan==3){
					domain.setGrup1(vendorName);
					domain.setGrup2(invoiceno);
					domain.setGrup3(invoiceno);			
				} else if (laporan==4){
					domain.setGrup1(areaName);					
					domain.setGrup2(invoiceno);
					domain.setGrup3(invoiceno);			
				}else if (laporan==5){
					domain.setGrup1(tipeCustomerName);					
					domain.setGrup2(invoiceno);
					domain.setGrup3(invoiceno);			
				}else if (laporan==6){
					domain.setGrup1(productGroupName);					
					domain.setGrup2(invoiceno);
					domain.setGrup3(invoiceno);			

				}else if (laporan==110){
					domain.setGrup1("-");					
					domain.setGrup2("-");			
					domain.setGrup3(vendorCode);
				}else if (laporan==11){
					domain.setGrup1("-");					
					domain.setGrup2("-");			
					domain.setGrup3(custNo);
				}else if (laporan==12){
					domain.setGrup1("-");					
					domain.setGrup2("-");			
					domain.setGrup3(productCode);
				}else if (laporan==13){
					domain.setGrup1("-");					
					domain.setGrup2("-");			
					domain.setGrup3(salesmanCode);
				}
				
				
				if (laporan<=2){
					domain.setString2(custName);
					domain.setString3(invoiceno);
					domain.setDate1(invoiceTrdate);
					domain.setDate2(invoiceDuedate);
					
					domain.setString4(address1);  
					domain.setString5(city1);  
					domain.setString6(tunaiKredit);  
					domain.setString7(tipeFaktur);  
					domain.setString8(tipeJual);  
					
					
					domain.setDouble2(totalBrutoDpp);
					domain.setDouble3(totalDiscBarangDpp);
					domain.setDouble4(totalDiscNotaDpp);
					domain.setDouble5(totalDpp);
					
					domain.setDouble1(totalAfterDisc2AfterPpn);
				}else if (laporan>=3 && laporan<=9){
					domain.setString1(vendorCode);					
					domain.setString2(custName);
					domain.setString3(invoiceno);
					domain.setDate1(invoiceTrdate);
					domain.setDate2(invoiceDuedate);
					domain.setString4(productName + " " + productPackaging);  
					
					KonversiProductAndStock kps = new KonversiProductAndStockImpl(ftSalesd.getQty(), ftSalesd.getFproductBean());		
					domain.setInt1(kps.getBesFromPcs());
					domain.setString5(kps.getBesSedKecString());
					
					domain.setDouble2(totalBrutoDpp);
					domain.setDouble3(totalDiscBarangDpp);
					domain.setDouble4(totalDiscNotaDpp);
					domain.setDouble5(totalDpp);
					
					domain.setDouble1(totalAfterDisc2AfterPpn);
				}else if (laporan == 10){
					domain.setString1("-");					
					domain.setString2("-");
					domain.setString3(vendorCode);
//					domain.setDate1(invoiceTrdate);
//					domain.setDate2(invoiceDuedate);
					domain.setString4(vendorName);  
					
					
					domain.setDouble2(totalBrutoDpp);
					domain.setDouble3(totalDiscBarangDpp);
					domain.setDouble4(totalDiscNotaDpp);
					domain.setDouble5(totalDpp);
					
					domain.setDouble1(totalAfterDisc2AfterPpn);
				}else if (laporan == 11){
					domain.setString1(areaName);					
					domain.setString2(tipeCustomerId);
					domain.setString3(custNo);
//					domain.setDate1(invoiceTrdate);
//					domain.setDate2(invoiceDuedate);
					domain.setString4(custDesc);  
					
					domain.setDouble2(totalBrutoDpp);
					domain.setDouble3(totalDiscBarangDpp);
					domain.setDouble4(totalDiscNotaDpp);
					domain.setDouble5(totalDpp);
					
					domain.setDouble1(totalAfterDisc2AfterPpn);
				}else if (laporan == 12){
					domain.setString1(vendorCode);					
					domain.setString2(productGroupId);
					domain.setString3(productCode);
//					domain.setDate1(invoiceTrdate);
//					domain.setDate2(invoiceDuedate);
					domain.setString4(productDesc + " " + productPackaging);  
					
					KonversiProductAndStock kps = new KonversiProductAndStockImpl(ftSalesd.getQty(), ftSalesd.getFproductBean());		
					domain.setInt1(kps.getBesFromPcs());
					domain.setString5(kps.getBesSedKecString());
					
					domain.setDouble2(totalBrutoDpp);
					domain.setDouble3(totalDiscBarangDpp);
					domain.setDouble4(totalDiscNotaDpp);
					domain.setDouble5(totalDpp);
					
					domain.setDouble1(totalAfterDisc2AfterPpn);
					
				}else if (laporan == 13){
					domain.setString1(ftSalesd.getFtsaleshBean().getFsalesmanBean().getSalestype());					
					domain.setString2(tipeCustomerId);
					domain.setString3(salesmanCode);
//					domain.setDate1(invoiceTrdate);
//					domain.setDate2(invoiceDuedate);
					domain.setString4(salesmanDesc);  
					
					domain.setDouble2(totalBrutoDpp);
					domain.setDouble3(totalDiscBarangDpp);
					domain.setDouble4(totalDiscNotaDpp);
					domain.setDouble5(totalDpp);
					
					domain.setDouble1(totalAfterDisc2AfterPpn);
				}
				
				//JIKA DIPOTONG RETUR MAKA RETUR MASUK SEMUA --> JIKA TIDAK MAKA YANG DI ADD YANG FAKTUR SAJA
				if (view.getCheckBoxFaktur().getValue()==true){
					if (ftSalesd.getFtsaleshBean().getTipefaktur().equalsIgnoreCase("F")) {
						lapTemplate2.add(domain);						
					}
				}
				if (view.getCheckBoxRetur().getValue()==true){
					if (ftSalesd.getFtsaleshBean().getTipefaktur().equalsIgnoreCase("R")) {
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
		
		parameters.put("paramJudulLaporan", paramJudulLaporan);
		parameters.put("paramCompanyName", paramCompanyName);
		parameters.put("paramCompanyAddress", paramCompanyAddress);
		parameters.put("paramCompanyPhone", paramCompanyPhone);
		parameters.put("paramSalesman", paramSalesman);
		
		parameters.put("paramDate1", view.getDateField1From().getValue());
		parameters.put("paramDate2", view.getDateField1To().getValue());

		parameters.put("paramDipotongRetur", "");
		
//		if (view.getCheckBoxRetur().getValue()==true){
//			parameters.put("paramDipotongRetur", "*Dipotong Retur");
//		}else {
//			parameters.put("paramDipotongRetur", "*Tidak Dipotong Retur");
//			
//		}
		
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
		
		view.getUI().getPage().open(resource, "_new_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	
		
	}
	
	
}
