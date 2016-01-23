package org.erp.distribution.salesorder.salesorder.lapprestasikerja;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.ZLapPrestasiKerja;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class LapPrestasiKerjaPresenter implements ClickListener{
	private LapPrestasiKerjaModel model;
	private LapPrestasiKerjaView view;

	public LapPrestasiKerjaPresenter(LapPrestasiKerjaModel model, LapPrestasiKerjaView view){
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
	
	private String strParamProductgroup = "";
	private String strParamWarehouseId = "";
	private Date dateParamInvoicedateFrom = null;
	private Date dateParamInvoicedateTo = null;
	private String strTipefaktur = "";
	private String strSpcode = "";
	private String strCustno = "";
	private String strSalesman = "";
	
	public void resetParameters(){
		strParamProductgroup= "%";
		strParamWarehouseId = "%";
		dateParamInvoicedateFrom = model.getTransaksiHelper().getCurrentTransDate();
		dateParamInvoicedateTo = model.getTransaksiHelper().getCurrentTransDate();
		strTipefaktur = "%";
		strSpcode = "%";
		strCustno = "%";
		strSalesman = "SEMUA SALESMAN";
		
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
			dateParamInvoicedateFrom = view.getDateField1From().getValue();
		} catch(Exception ex){}
		try{
			dateParamInvoicedateTo = view.getDateField1To().getValue();
		} catch(Exception ex){}
		
		try{
			FSalesman fSalesman = new FSalesman();
			fSalesman = (FSalesman) view.getComboGroup1().getValue();
			strSpcode = "%" + fSalesman.getSpcode().trim() + "%";
			strSalesman = "(" + fSalesman.getSpcode() + ") " + fSalesman.getSpname();
		} catch(Exception ex){}
		
		
	}
	public void preview(){
		resetParameters();
		reloadParameter();
		
		//1. ISI DATABASE UNTUK TEMP
		fillDatabaseReport();
		//2. PREVIEW LAPORAN
		showPreview("/erp/distribution/reports/salesorder/lapprestasikerja/lapprestasikerja1Ds.jasper", "lapprestasikrj1");
		
	}
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			
			final Map parameters=new HashMap();
			parameters.put("CompanyName","");
			
			parameters.put("paramInvoicedateFrom", dateParamInvoicedateFrom);
			parameters.put("paramInvoicedateTo", dateParamInvoicedateTo);

			parameters.put("paramSalesman", strSalesman);
			
			parameters.put("paramProductGroup", "%" +  strParamProductgroup  + "%");

			final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listLapPrestasiKerja);
			InputStream reportPathStream = getClass().getResourceAsStream(inputFilePath);
			final JasperPrint jasperPrint = JasperFillManager.fillReport(reportPathStream, parameters, dataSource);
			
	
			//CONNECTION
			final Connection con = new ReportJdbcConfigHelper().getConnection();
			
			StreamResource.StreamSource source = new StreamSource() {			
				@Override
				public InputStream getStream() {
					byte[] b = null;
					try {
						b = JasperExportManager.exportReportToPdf(jasperPrint);
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
			
			view.getUI().getPage().open(resource, "_new_lap_prestasikerja_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	
	}

	private List<ZLapPrestasiKerja> listLapPrestasiKerja = new ArrayList<ZLapPrestasiKerja>();
	public void fillDatabaseReport(){
//		//HAPUS DATA
//		Iterator<ZLapPrestasiKerja> iterZLapPrestasiKerja = model.getLapPrestasiKerjaJpaService().findAll().iterator();
//		while (iterZLapPrestasiKerja.hasNext()) {
//			model.getLapPrestasiKerjaJpaService().removeObject(iterZLapPrestasiKerja.next());
//		}
		String [] hari = {"Minggu", "Senin", "Selasa", "Rabo", "Kamis", "Jumat", "Sabtu"};
		
		listLapPrestasiKerja = new ArrayList<ZLapPrestasiKerja>();		
		//CALCULASI PER TANGGAL
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateParamInvoicedateFrom);
		while (cal.getTime().getTime() <= dateParamInvoicedateTo.getTime()) {
			List<FtSalesh> listFtSalesh = new ArrayList<FtSalesh>();			
			listFtSalesh =  model.getFtSaleshJpaService().findAllByInvoicedate(cal.getTime(), cal.getTime(), strTipefaktur, strSpcode, strCustno);
			
			Iterator<FtSalesh> iterFtSalesh = listFtSalesh.iterator();
			
			Integer jumlahToko=0;
			Integer efectiveCall =0;
			Integer skuSold =0;
			double totalBruto=0.0;
			double totalAfterDiscafterppn=0.0;
			double totalDiscPerBarang = 0.0;
			double totalDiscNota = 0.0;
			
			while (iterFtSalesh.hasNext()) {
				FtSalesh itemHeader = new FtSalesh();
				itemHeader = iterFtSalesh.next();
				if (itemHeader.getTipefaktur().equalsIgnoreCase("F") && (! itemHeader.getInvoiceno().trim().equals(""))){
					efectiveCall++;
					totalAfterDiscafterppn += itemHeader.getAmountafterdiscafterppn();
				
					Iterator<FtSalesd> iterFtSalesd = itemHeader.getFtsalesdSet().iterator();
					while (iterFtSalesd.hasNext()) {
						FtSalesd itemDetil = new FtSalesd();
						itemDetil = iterFtSalesd.next();
						skuSold++;
						
						Double hargaPerPcs = itemDetil.getSprice() / itemDetil.getFproductBean().getConvfact1();
						totalBruto += hargaPerPcs * itemDetil.getQty();
						
						Double disc1Rp = (hargaPerPcs * itemDetil.getQty())  * (itemDetil.getDisc1()/100);
						Double disc2plusRp = ((hargaPerPcs * itemDetil.getQty())-disc1Rp) * itemDetil.getDisc2()/100;
						
						totalDiscPerBarang += disc1Rp + disc2plusRp;
						
					}	
				}
				jumlahToko++;
				//FILL NEW DATA
				
				double discnota1Rp = (totalBruto - totalDiscPerBarang) * (itemHeader.getDisc1()/100); 
				double discnota2plusRp = ((totalBruto - totalDiscPerBarang)-discnota1Rp) * itemHeader.getDisc()/100;
				totalDiscNota += discnota1Rp + discnota2plusRp;
			}
			double rataRata = ((double) skuSold) / ((double) efectiveCall);
			if (Double.isNaN(rataRata)) {
				rataRata = 0.0;
			}
			double dpp = totalAfterDiscafterppn/1.1;
			double ppn = totalAfterDiscafterppn-dpp;
			
			ZLapPrestasiKerja item1 = new ZLapPrestasiKerja();
			
			item1.setGrup1("grup1");
			item1.setGrup2("grup2");
			item1.setHari(hari[cal.get(Calendar.DAY_OF_WEEK)-1]);
			item1.setTanggal(cal.getTime());
			item1.setJumlahToko(jumlahToko);
			item1.setEfectiveCall(efectiveCall);
			item1.setSkuSold(skuSold);
			item1.setRataRata(rataRata);
			
			item1.setTotalBeforediscBeforeppn(totalBruto);
			item1.setDiscPerbarang(totalDiscPerBarang);
			item1.setDiscNota(totalDiscNota);
			
			item1.setDpp(dpp);
			item1.setPpn(ppn);			
			item1.setTotalAfterdiscAfterppn(totalAfterDiscafterppn);
			
//			System.out.println(":::" + item1.getHari() + ":" + item1.getTanggal() + ":" + 
//			item1.getJumlahToko() + ":" + item1.getEfectiveCall() + ":" + item1.getSkuSold() + 
//			":" + item1.getRataRata() + ":" + item1.getRataRata() + ":" + item1.getTotalBeforediscBeforeppn() + 
//			":" + item1.getDiscPerbarang() + ":" + item1.getDiscNota() + ":" + item1.getDpp() + ":" + item1.getPpn() + ":" + item1.getTotalAfterdiscAfterppn());
			
//			model.getLapPrestasiKerjaJpaService().createObject(item1);
			listLapPrestasiKerja.add(item1);
			
			//NAIKKAN SATU HARI
			cal.add(Calendar.DATE, 1);
		}
		
		
 	}
	
	
}
