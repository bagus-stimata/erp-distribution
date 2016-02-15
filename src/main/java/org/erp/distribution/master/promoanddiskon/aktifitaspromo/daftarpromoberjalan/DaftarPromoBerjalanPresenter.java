package org.erp.distribution.master.promoanddiskon.aktifitaspromo.daftarpromoberjalan;

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
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.STeknisi;
import org.erp.distribution.model.StService;
import org.erp.distribution.model.ZLapPackingList;
import org.erp.distribution.model.ZLapTemplate1;
import org.erp.distribution.model.ZLapTemplate2;
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

public class DaftarPromoBerjalanPresenter implements ClickListener{
	private DaftarPromoBerjalanModel model;
	private DaftarPromoBerjalanView view;

	public DaftarPromoBerjalanPresenter(DaftarPromoBerjalanModel model, DaftarPromoBerjalanView view){
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
		showPreview("/erp/distribution/reports/setupmaster/diskonpromosi/daftarpromosiberjalan1/daftarpromosiberjalan1Ds.jasper", "daftarpromosiberjalan1");

	}
	
	private List<ZLapTemplate2> lapTemplate2 = new ArrayList<ZLapTemplate2>();
	public void fillDatabaseReportLengkap(){
		//MENGHINDARI DOUBLE
		lapTemplate2 = new ArrayList<ZLapTemplate2>();		
//		Set<String> setSuratJalanList = new LinkedHashSet<String>();

		List<FPromo> listFPromo = new ArrayList<FPromo>();
		listFPromo = model.getfPromoJpaService().findAllPromoBerjalan(view.getDateField1From().getValue());
		
		for (FPromo fPromo: listFPromo){			
							
					ZLapTemplate2 domain = new ZLapTemplate2();
					
					domain.setGrup1("-");
					domain.setGrup2("-");
					domain.setGrup3("-");
					

					String identitasPromo = fPromo.getNorek() + " - " + fPromo.getDescription();
					String produkPromo = "";
					try{
						produkPromo = fPromo.getfProductBean().getPcode() + "-" + fPromo.getfProductBean().getPname() 
							+ " " + fPromo.getfProductBean().getPackaging();
					} catch(Exception ex){}
					String produkGroupPromo= "";
					try{
						produkGroupPromo = fPromo.getFproductgroupBean().getId() + "-" + fPromo.getFproductgroupBean().getDescription();
					} catch(Exception ex){}
					
					Date periodeMulai = fPromo.getPeriodeFrom();
					Date periodeSampai = fPromo.getPeriodeTo();
					
					
					String jenisCustomer = "";
					if (fPromo.getForFcustomersubgroup().equals("ALL")) {
						jenisCustomer = "Untuk Semua Jenis Customer";
					}else {
						try{
							jenisCustomer = fPromo.getForFcustomersubgroup() + " " 
									+ fPromo.getFcustomersubgroupBean().getId() + " " 
									+ fPromo.getFcustomersubgroupBean().getDescription();
						} catch(Exception ex){}
					}
					
					//AKAN MENDAPAT
					String bonusBarang = fPromo.getFreeFproductBean().getPcode() + " - " 
							+ fPromo.getFreeFproductBean().getPname() + " " + fPromo.getFreeFproductBean().getPackaging();
					//Dengan Jumlah
					String produkPromoAndGroup = "";
					if (! produkPromo.trim().equals("")) {
						produkPromoAndGroup = produkPromo;
					}else { 
						produkPromoAndGroup = produkGroupPromo;
					}
					String bonusBarang1= "";
					if (fPromo.getFreeQtyGet1()>0){
						bonusBarang1 = "Pembelian " + produkPromoAndGroup + " lebih dari =" + fPromo.getFreeQty1().toString() 
							+ " PCS mendapat " + bonusBarang + " =" +  fPromo.getFreeQtyGet1().toString() + " PCS";
					}
					String bonusBarang2= "";
					if (fPromo.getFreeQtyGet2()>0){
						bonusBarang2 = "Pembelian " + produkPromoAndGroup + " lebih dari =" + fPromo.getFreeQty2().toString() 
							+ " PCS mendapat " + bonusBarang + " =" +  fPromo.getFreeQtyGet2().toString() + " PCS";
					}
					String bonusBarang3= "";
					if (fPromo.getFreeQtyGet3()>0){
						bonusBarang3 = "Pembelian " + produkPromoAndGroup + " lebih dari =" + fPromo.getFreeQty3().toString() 
							+ " PCS akan mendapat " + bonusBarang + " =" +  fPromo.getFreeQtyGet3().toString() + " PCS";
					}
					String bonusBarang4= "";
					if (fPromo.getFreeQtyGet4()>0){					
						bonusBarang4 = "Pembelian " + produkPromoAndGroup + " lebih dari =" + fPromo.getFreeQty4().toString() 
							+ " PCS mendapat " + bonusBarang + " =" +  fPromo.getFreeQtyGet4().toString() + " PCS";
					}
					Boolean berlakuKelipatanFree = fPromo.getFreeKelipatan();
					
					//DISKON
					String diskon1= "";
					if (fPromo.getDiscPercentGet1()>0){
						diskon1 =  "Pembelian " + produkPromo + " senilai Lebih besar dari " + fPromo.getDiscValue1() 
							+ " akan mendapat diskon " + fPromo.getDiscPercentGet1(); 
					}
					String diskon2 ="";
					if (fPromo.getDiscPercentGet2()>0){
						diskon2 =  "Pembelian " + produkPromo + " senilai Lebih besar dari " + fPromo.getDiscValue2() 
							+ " akan mendapat diskon " + fPromo.getDiscPercentGet2(); 
					}
					String diskon3 ="";
					if (fPromo.getDiscPercentGet3()>0){
						diskon3 =  "Pembelian " + produkPromo + " senilai Lebih besar dari " + fPromo.getDiscValue3() 
							+ " akan mendapat diskon " + fPromo.getDiscPercentGet3(); 
					}
					String diskon4 ="";
					if (fPromo.getDiscPercentGet4()>0){
						diskon4 =  "Pembelian " + produkPromo + " senilai Lebih besar dari " + fPromo.getDiscValue4() 
							+ " akan mendapat diskon " + fPromo.getDiscPercentGet4(); 
					}
					Boolean berlakuKelipatanDisc = fPromo.getDiscKelipatan();
					
					//IDENTITAS UMUM PROMO
					domain.setString1(identitasPromo);
					domain.setString2(produkPromo);
					domain.setString3(produkGroupPromo);
					domain.setDate1(periodeMulai);
					domain.setDate2(periodeSampai);
					
					domain.setString4(jenisCustomer);
					
					//BONUS BARANG
					domain.setString5(bonusBarang1);
					domain.setString6(bonusBarang2);
					domain.setString7(bonusBarang3);
					domain.setString8(bonusBarang4);
					domain.setBol1(berlakuKelipatanFree);
					
					//DISKON
					domain.setString9(diskon1);
					domain.setString10(diskon2);
					domain.setString11(diskon3);
					domain.setString12(diskon4);
					domain.setBol2(berlakuKelipatanDisc);
					
					
					lapTemplate2.add(domain);
				
			
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
		
		String fileName = "promo_berjalan_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	
		
	}
	
	
}
