package org.erp.distribution.salesorder.salesorder.lapsalesorder;

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

public class LapSalesOrderPresenter implements ClickListener{
	private LapSalesOrderModel model;
	private LapSalesOrderView view;

	public LapSalesOrderPresenter(LapSalesOrderModel model, LapSalesOrderView view){
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
		if (view.getCheckBoxOutput1().getValue()==true){
			fillDatabaseReport(1);
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderpernota/lapsalesorderperareapernota1LengkapDs.jasper", "lapsalesorderperareapernota1Lengkap");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderpernota/lapsalesorderperareapernota1Ds.jasper", "lapsalesorderperareapernota1");
			}
		}
		if (view.getCheckBoxOutput2().getValue()==true){
			fillDatabaseReport(2);
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderpernota/lapsalesorderpertipepernota1LengkapDs.jasper", "lapsalesorderpertipepernota1Lengkap");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderpernota/lapsalesorderpertipepernota1Ds.jasper", "lapsalesorderpertipepernota1");
			}
		}
		
		if (view.getCheckBoxOutput3().getValue()==true){
			fillDatabaseReport(3);
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalesvendorperbarang/lapsalesvendorperbarang1LengkapDs.jasper", "lapsalesvendorperbaranglengkap1");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalesvendorperbarang/lapsalesvendorperbarang1Ds.jasper", "lapsalesvendorperbarang1");
			}
		}
		if (view.getCheckBoxOutput4().getValue()==true){
			fillDatabaseReport(4);
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalesareaperbarang/lapsalesareaperbarang1LengkapDs.jasper", "lapsalesareaperbaranglengkap1");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalesareaperbarang/lapsalesareaperbarang1Ds.jasper", "lapsalesareaperbarang1");
			}
		}
		if (view.getCheckBoxOutput5().getValue()==true){
			fillDatabaseReport(5);
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalestipecustperbarang/lapsalestipecustperbarang1LengkapDs.jasper", "lapsalestipecustperbaranglengkap1");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalestipecustperbarang/lapsalestipecustperbarang1Ds.jasper", "lapsalestipecustperbarang1");
			}
		}
		if (view.getCheckBoxOutput6().getValue()==true){
			fillDatabaseReport(6);
			//2. PREVIEW LAPORAN
			if (view.getCheckBox2().getValue()==true){
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalesproductgroupperbarang/lapsalesproductgroupperbarang1LengkapDs.jasper", "lapsalesproductgroupperbaranglengkap1");			
			}else {
				showPreview("/erp/distribution/reports/salesorder/lapsalesorderperbarang/lapsalesproductgroupperbarang/lapsalesproductgroupperbarang1Ds.jasper", "lapsalesproductgroupperbarang1");
			}
		}
		
	}
	
	private List<ZLapTemplate2> lapTemplate2 = new ArrayList<ZLapTemplate2>();
	public void fillDatabaseReport(int laporan){

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
					.findAllByAreaAndInvoice(paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);			
		}else if (laporan==2){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByTipeAndInvoice(paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);
			
		}else if (laporan==3){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByVendor(paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);
		}else if (laporan==4){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByArea(paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);			
		}else if (laporan==5){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByTipeCust(paramVcode, paramArea, paramSubArea, paramCustno, 
							paramTrdate, paramDuedate, paramTipeFaktur, paramPcode, paramPname, paramProductGroup);			
		}else if (laporan==6){
			listFtSalesd = model.getFtSalesdJpaService()
					.findAllByProductGroup(paramVcode, paramArea, paramSubArea, paramCustno, 
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
				if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("R")) {
//					ftSalesd.setSprice(- ftSalesd.getSprice());
					ftSalesd.setQty(-ftSalesd.getQty());
				}
				HeaderDetilSalesHelper headerDetilHelper = new HeaderDetilSalesHelperImpl(ftSalesd);
				newFtSalesd = headerDetilHelper.getFillFtSalesd();
				
				ZLapTemplate2 domain = new ZLapTemplate2();

				String vendorName  = ftSalesd.getFproductBean().getFvendorBean().getVcode()  + "-" + ftSalesd.getFproductBean().getFvendorBean().getVname();
				String areaName  = ftSalesd.getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getId()  
						+ "-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getDescription();
				String tipeName = ftSalesd.getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getId()
						+ "-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getDescription();
				String custName = ftSalesd.getFtsaleshBean().getFcustomerBean().getCustno() + 
						"-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getCustname();
				String productGroupName  = ftSalesd.getFproductBean().getFproductgroupBean().getId() 
						+ "-" + ftSalesd.getFproductBean().getFproductgroupBean().getDescription();
				
				String address1 = ftSalesd.getFtsaleshBean().getFcustomerBean().getAddress1();
				String city1 = ftSalesd.getFtsaleshBean().getFcustomerBean().getCity1();
				
				String tunaiKredit = ftSalesd.getFtsaleshBean().getTunaikredit();
				String tipeFaktur = ftSalesd.getFtsaleshBean().getTipefaktur();
				String tipeJual = ftSalesd.getFtsaleshBean().getTipejual();
				
				String invoiceno = ftSalesd.getFtsaleshBean().getInvoiceno();
				String invoiceRefno = String.valueOf(ftSalesd.getFtsaleshBean().getRefno());
				Date invoiceTrdate = ftSalesd.getFtsaleshBean().getInvoicedate();
				Date invoiceDuedate = ftSalesd.getFtsaleshBean().getDuedate();
				String productName = ftSalesd.getFproductBean().getPcode() + "-" +ftSalesd.getFproductBean().getPname();
				
				double totalBrutoDpp = newFtSalesd.getSubtotal();
				double totalDiscBarangDpp = newFtSalesd.getDisc1rp() + newFtSalesd.getDisc2rp();
				double totalDiscNotaDpp = newFtSalesd.getDiscNota1Rp() + newFtSalesd.getDiscNotaRp();
				double totalDpp = newFtSalesd.getSubTotalAfterDiscNota();
				double totalAfterDisc2AfterPpn = newFtSalesd.getSubTotalAfterDiscNotaAfterPpn();
				
				if (laporan==1){
					domain.setGrup1("GRAND TOTAL");
					domain.setGrup2(areaName);
					domain.setString1(ftSalesd.getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getId());		
				} else if (laporan==2){
					domain.setGrup1("GRAND TOTAL");
					domain.setGrup2(tipeName);
					domain.setString1(ftSalesd.getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getId());		

				}else if (laporan==3){
					domain.setGrup1(vendorName);
					domain.setGrup2(invoiceno);
				} else if (laporan==4){
					domain.setGrup1(areaName);					
					domain.setGrup2(invoiceno);
				}else if (laporan==5){
					domain.setGrup1(tipeName);					
					domain.setGrup2(invoiceno);
				}else if (laporan==6){
					domain.setGrup1(productGroupName);					
					domain.setGrup2(invoiceno);
				}
				
				domain.setGrup3(invoiceno);			
				
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
				}else {
					domain.setString1(ftSalesd.getFproductBean().getFvendorBean().getVcode());					
					domain.setString2(custName);
					domain.setString3(invoiceno);
					domain.setDate1(invoiceTrdate);
					domain.setDate2(invoiceDuedate);
					domain.setString4(productName);  
					
					KonversiProductAndStock kps = new KonversiProductAndStockImpl(ftSalesd.getQty(), ftSalesd.getFproductBean());		
					domain.setInt1(kps.getBesFromPcs());
					domain.setString5(kps.getBesSedKecString());
					
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
		parameters.put("CompanyName","");
		
		parameters.put("paramDate1", view.getDateField1From().getValue());
		parameters.put("paramDate2", view.getDateField1To().getValue());
		if (view.getCheckBoxRetur().getValue()==true){
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
