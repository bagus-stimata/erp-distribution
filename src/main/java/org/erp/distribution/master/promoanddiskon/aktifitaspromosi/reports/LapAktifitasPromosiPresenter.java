package org.erp.distribution.master.promoanddiskon.aktifitaspromosi.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.erp.distribution.model.FPromo;
import org.erp.distribution.model.FtSalesdPromoTprb;
import org.erp.distribution.model.FtSalesdPromoTpruDisc;
import org.erp.distribution.model.ZLapAktifitasPromoList;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LapAktifitasPromosiPresenter implements ClickListener{

	private LapAktifitasPromosiModel model;
	private LapAktifitasPromosiView view;

	public LapAktifitasPromosiPresenter(LapAktifitasPromosiModel model, LapAktifitasPromosiView view){
		this.model = model;
		this.view = view;
		initListener();
	}
	
	public void initListener(){
		view.getBtnPreview().addClickListener(this);
		view.getBtnClose().addClickListener(this);
		view.getBtnExtractToExel().addClickListener(this);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnPreview()){
			preview();
		} else if (event.getButton()==view.getBtnClose()){
		}else if (event.getButton()==view.getBtnExtractToExel()){
			previewExel();
		}
	}
	
	private String paramCompanyName = "W-DES";
	private String paramCompanyAddress = "Jl. Kauman Gang IV/A Malang";
	private String paramCompanyPhone = "Telp.082143574692";
	private String paramJudulLaporan = "LAPORAN SALES";
	
	public void preview(){
		paramCompanyName = model.getSysvarHelper().getCompanyNameFaktur();
		paramCompanyAddress = model.getSysvarHelper().getCompanyAddressFaktur();
		paramCompanyPhone = model.getSysvarHelper().getCompanyPhoneFaktur();
		
		//1. ISI DATABASE UNTUK TEMP
		fillDatabaseReportLengkap();
		
		//2. PREVIEW LAPORAN
		if (view.getCheckBox1().getValue()==false){
			showPreview("/erp/distribution/reports/setupmaster/diskonpromosi/"
					+ "lapaktifitaspromo1/lapaktifitaspromo1Ds.jasper", "lapaktipromo1");
		} else {
			showPreview("/erp/distribution/reports/setupmaster/diskonpromosi/"
					+ "lapaktifitaspromo1/lapaktifitaspromo1LengkapDs.jasper", "lapaktipromo1");			
		}
	}
	public void previewExel(){
		paramCompanyName = model.getSysvarHelper().getCompanyNameFaktur();
		paramCompanyAddress = model.getSysvarHelper().getCompanyAddressFaktur();
		paramCompanyPhone = model.getSysvarHelper().getCompanyPhoneFaktur();
		
		//1. ISI DATABASE UNTUK TEMP
		fillDatabaseReportLengkap();
		//2. PREVIEW LAPORAN
		if (view.getCheckBox1().getValue()==false){
			jasperToExel("/erp/distribution/reports/setupmaster/diskonpromosi/"
				+ "lapaktifitaspromo1/lapaktifitaspromo1Ds.jasper", "lapaktipromo1");
		} else {
			jasperToExel("/erp/distribution/reports/setupmaster/diskonpromosi/"
				+ "lapaktifitaspromo1/lapaktifitaspromo1LengkapDs.jasper", "lapaktipromolenkap1");			
		}
	}
	
	private List<ZLapAktifitasPromoList> lapAktifitasPromoList = new ArrayList<ZLapAktifitasPromoList>();
	public void fillDatabaseReportLengkap(){
		
		//1. MASUKKAN YANG DISELEKSI KE DALAM TABLE REPORT TEMPORER TAHAP1
		lapAktifitasPromoList = new ArrayList<ZLapAktifitasPromoList>();
		
		Date invoicedateFrom = view.getDateField1From().getValue();
		Date invoicedateTo = view.getDateField1To().getValue();
		
		FPromo fPromoSelected = new FPromo();
		fPromoSelected = (FPromo) view.getComboGroup1().getValue();
			//BONUS BARANG
			List<FtSalesdPromoTprb> listFtSalesdPromoTprb = new ArrayList<FtSalesdPromoTprb>(
					model.getFtSalesdPromoTprbJpaService().findAllByFPromoId(fPromoSelected.getId()));
			for (FtSalesdPromoTprb  ftSalesdPromoTprb: listFtSalesdPromoTprb) {
				if (ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getInvoicedate().getTime() <= invoicedateTo.getTime() 
						&& ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getInvoicedate().getTime() >= invoicedateFrom.getTime() 
						&& ! ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getInvoiceno().trim().equals("")
						) {
					
					ZLapAktifitasPromoList domain = new ZLapAktifitasPromoList();
					domain.setGrup1("Grup1");
					domain.setGrup2("Grup2");
					domain.setGrup3("Grup3");
					
					domain.setPromoid(fPromoSelected.getNorek());
					domain.setPromodesc(fPromoSelected.getDescription());
					
					domain.setInvoiceno(ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getInvoiceno());
					domain.setInvoicedate(ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getInvoicedate());
					domain.setSubtotalafterdiscafterppn(0.0);
										
					domain.setCustarea(ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getId());
					domain.setCustsubarea(ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getFsubareaBean().getId());
					domain.setCustgroup(ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getFcustomergroupBean().getId());
					domain.setCustsubgroup(ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getId());
					domain.setCustno(ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getCustno());
					domain.setCustname(ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getCustname());
					domain.setAddress(ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getAddress1());
					domain.setCity(ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getCity1());
					
					domain.setFreebonuspcode(ftSalesdPromoTprb.getfProductBean().getPcode());
					domain.setFreebonuspname(ftSalesdPromoTprb.getfProductBean().getPname() + " " +
							ftSalesdPromoTprb.getfProductBean().getPackaging() );
					
					domain.setFreebonusqtypcs(ftSalesdPromoTprb.getTprbqty());
					domain.setFreebonusqtybes(model.getProductAndStockHelper().getBesFromPcs(domain.getFreebonusqtypcs(),
							ftSalesdPromoTprb.getfProductBean()));
					domain.setFreebonusqtysed(model.getProductAndStockHelper().getSedFromPcs(domain.getFreebonusqtypcs(),
							ftSalesdPromoTprb.getfProductBean()));
					domain.setFreebonusqtykec(model.getProductAndStockHelper().getKecFromPcs(domain.getFreebonusqtypcs(),
							ftSalesdPromoTprb.getfProductBean()));
					
					domain.setFreebonusafterppn(ftSalesdPromoTprb.getTprbafterppn());
					
					domain.setDisc1(0.0);
					domain.setDisc1afterppn(0.0);
					domain.setDisc2(0.0);
					domain.setDisc2afterppn(0.0);
					
					domain.setCashbackafterppn(0.0);
					
//					model.getLapAktifitasPromoListJpaService().createObject(domain);
					lapAktifitasPromoList.add(domain);
				}
				
			}
			
			//BONUS UANG & DISKON
			List<FtSalesdPromoTpruDisc> listFtSalesdPromoTprudisc = new ArrayList<FtSalesdPromoTpruDisc>(
					model.getFtSalesdPromoTpruDiscJpaService().findAllByFPromoId(fPromoSelected.getId()));
			for (FtSalesdPromoTpruDisc  ftSalesdPromoTprudisc: listFtSalesdPromoTprudisc) {
				if (ftSalesdPromoTprudisc.getFtSalesdBean().getFtsaleshBean().getInvoicedate().getTime() <= invoicedateTo.getTime() && 
						ftSalesdPromoTprudisc.getFtSalesdBean().getFtsaleshBean().getInvoicedate().getTime() >= invoicedateFrom.getTime() && 
						! ftSalesdPromoTprudisc.getFtSalesdBean().getFtsaleshBean().getInvoiceno().trim().equals("")) {
					
					ZLapAktifitasPromoList domain = new ZLapAktifitasPromoList();
					domain.setGrup1("Grup1");
					domain.setGrup2("Grup2");
					domain.setGrup3("Grup3");

					domain.setPromoid(fPromoSelected.getNorek());
					domain.setPromodesc(fPromoSelected.getDescription());
					
					domain.setInvoiceno(ftSalesdPromoTprudisc.getFtSalesdBean().getFtsaleshBean().getInvoiceno());
					domain.setInvoicedate(ftSalesdPromoTprudisc.getFtSalesdBean().getFtsaleshBean().getInvoicedate());
					domain.setSubtotalafterdiscafterppn(0.0);
					
					domain.setCustarea(ftSalesdPromoTprudisc.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getId());
					domain.setCustsubarea(ftSalesdPromoTprudisc.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getFsubareaBean().getId());
					domain.setCustgroup(ftSalesdPromoTprudisc.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getFcustomergroupBean().getId());
					domain.setCustsubgroup(ftSalesdPromoTprudisc.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getId());
					domain.setCustno(ftSalesdPromoTprudisc.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getCustno());
					domain.setCustname(ftSalesdPromoTprudisc.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getCustname());
					domain.setAddress(ftSalesdPromoTprudisc.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getAddress1());
					domain.setCity(ftSalesdPromoTprudisc.getFtSalesdBean().getFtsaleshBean().getFcustomerBean().getCity1());
					
					domain.setFreebonuspcode("");
					domain.setFreebonuspname("");
					
					domain.setFreebonusqtypcs(0);
					domain.setFreebonusqtybes(0);
					domain.setFreebonusqtysed(0);
					domain.setFreebonusqtykec(0);
					
					domain.setFreebonusafterppn(0.0);
					
					domain.setDisc1(ftSalesdPromoTprudisc.getTprudiscpersen());
					domain.setDisc1afterppn(ftSalesdPromoTprudisc.getTprudiscafterppn());
					domain.setDisc2(0.0);
					domain.setDisc2afterppn(0.0);
					
					domain.setCashbackafterppn(0.0);
					
//					model.getLapAktifitasPromoListJpaService().createObject(domain);
					lapAktifitasPromoList.add(domain);
					
				}
				
		}
		
		
	}
	

	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			final Map parameters=new HashMap();		
			parameters.put("paramJudulLaporan", paramJudulLaporan);
			parameters.put("paramCompanyName", paramCompanyName);
			parameters.put("paramCompanyAddress", paramCompanyAddress);
			parameters.put("paramCompanyPhone", paramCompanyPhone);
			//parameters.put("paramSalesman", paramSalesman);
			
			//CONNECTION
	//		final Connection con = new ReportJdbcConfigHelper().getConnection();
			
			final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lapAktifitasPromoList);
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
			
			String fileName = "promo_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
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
//		parameters.put("paramSalesman", paramSalesman);
		
		parameters.put("paramDate1", view.getDateField1From().getValue());
		parameters.put("paramDate2", view.getDateField1To().getValue());
		
		//CONNECTION
//		final Connection con = new ReportJdbcConfigHelper().getConnection();
		final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lapAktifitasPromoList);
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
		
		String fileName = "exel"  +System.currentTimeMillis() +".xls";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/vnd.ms-excel");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new_" , false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
}
