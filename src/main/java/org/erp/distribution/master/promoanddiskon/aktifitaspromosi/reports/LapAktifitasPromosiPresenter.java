package org.erp.distribution.master.promoanddiskon.aktifitaspromosi.reports;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
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
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnPreview()){
			preview();
		} else if (event.getButton()==view.getBtnClose()){
		}
	}
	
	
	public void preview(){
		//1. ISI DATABASE UNTUK TEMP
		fillDatabaseReportLengkap();
		//2. PREVIEW LAPORAN
		showPreview("/erp/distribution/reports/setupmaster/diskonpromosi/"
				+ "lapaktifitaspromo1/lapaktifitaspromo1.jasper", "lapaktipromo1");
		
	}

	public void fillDatabaseReportLengkap(){
		
		//1. HAPUS DATA
		Iterator<ZLapAktifitasPromoList> iterZLapListDelete = model.getLapAktifitasPromoListJpaService().findAll().iterator();
		while (iterZLapListDelete.hasNext()) {
			model.getLapAktifitasPromoListJpaService().removeObject(iterZLapListDelete.next());
		}

		//2. MASUKKAN YANG DISELEKSI KE DALAM TABLE REPORT TEMPORER TAHAP1
		Date invoicedateFrom = view.getDateField1From().getValue();
		Date invoicedateTo = view.getDateField1To().getValue();
		
		FPromo fPromoSelected = new FPromo();
		fPromoSelected = (FPromo) view.getComboGroup1().getValue();
			//BONUS BARANG
			List<FtSalesdPromoTprb> listFtSalesdPromoTprb = new ArrayList<FtSalesdPromoTprb>(
					model.getFtSalesdPromoTprbJpaService().findAllByFPromoId(fPromoSelected.getId()));
			for (FtSalesdPromoTprb  ftSalesdPromoTprb: listFtSalesdPromoTprb) {
				if (ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getInvoicedate().getTime() <= invoicedateTo.getTime() && 
						ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getInvoicedate().getTime() >= invoicedateFrom.getTime() && 
						! ftSalesdPromoTprb.getFtSalesdBean().getFtsaleshBean().getInvoiceno().trim().equals("")) {
					
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
					
					model.getLapAktifitasPromoListJpaService().createObject(domain);
										
				}
				
			}
			
			//BONUS UANG DISKON
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
					
					model.getLapAktifitasPromoListJpaService().createObject(domain);
					
					
				}
				
		}
		
		
	}
	

	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
		parameters.put("CompanyName","");
		
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
		
		view.getUI().getPage().open(resource, "_new_packinglistpersj_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	
		
	}
	
	
}
