package org.erp.distribution.salesorder.salesorder.lapprestasikerja;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;	
import java.util.Map;
import java.util.Set;

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

import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.ZLapPrestasiKerja;
import org.erp.distribution.model.ZLapTemplate2;
import org.erp.distribution.util.HeaderDetilSalesHelper;
import org.erp.distribution.util.HeaderDetilSalesHelperImpl;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Reindeer;

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
		view.getBtnPreviewInExel().addClickListener(this);
		view.getBtnPilihSalesman().addClickListener(this);
		
	
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnPreview()){
			preview();
		} else if (event.getButton()==view.getBtnPreviewInExel()){
			previewInExel();
		} else if (event.getButton()==view.getBtnClose()){
		} else if (event.getButton()==view.getBtnPilihSalesman()){
			if (view.getGridSalesman().isVisible()==false){
				view.getGridSalesman().setVisible(true);
				view.getBtnPilihSalesman().setCaption("Dengan Salesman");
			}else {
				view.getGridSalesman().setVisible(false);
				view.getBtnPilihSalesman().setCaption("Semua Salesman");
			}			
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
	private String strListSalesman = "";
	
	public void resetParameters(){
		strParamProductgroup= "%";
		strParamWarehouseId = "%";
		dateParamInvoicedateFrom = model.getTransaksiHelper().getCurrentTransDate();
		dateParamInvoicedateTo = model.getTransaksiHelper().getCurrentTransDate();
		strTipefaktur = "%";
		strSpcode = "%";
		strCustno = "%";
		strSalesman = "SEMUA SALESMAN";
		strListSalesman = "";
		
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
	private String paramCompanyName = "W-DES";
	private String paramCompanyAddress = "Jl. Kauman Gang IV/A Malang";
	private String paramCompanyPhone = "Telp.082143574692";
	private String paramJudulLaporan = "LAPORAN SALES";

	public void preview(){
		paramCompanyName = model.getSysvarHelper().getCompanyNameFaktur();
		paramCompanyAddress = model.getSysvarHelper().getCompanyAddressFaktur();
		paramCompanyPhone = model.getSysvarHelper().getCompanyPhoneFaktur();
		
		resetParameters();
		reloadParameter();

		if (view.getCheckKomulatif().getValue()==true){
			fillDatabaseKomulatifLengkapNew();
			showPreview("/erp/distribution/reports/salesorder/lapprestasikerja/lapprestasikerja2LengkapDs.jasper", "lappreskrjLengkap1");
		} else if (view.getCheckLengkap().getValue()==true){
			fillDatabaseLengkapNew();
			showPreview("/erp/distribution/reports/salesorder/lapprestasikerja/lapprestasikerja2LengkapDs.jasper", "lappreskrjLengkap1");			
		} else{
			fillDatabaseNew();
			showPreview("/erp/distribution/reports/salesorder/lapprestasikerja/lapprestasikerja2Ds.jasper", "lappreskrjLengkapKomulatif1");
		}
	}

	public void previewInExel(){
		paramCompanyName = model.getSysvarHelper().getCompanyNameFaktur();
		paramCompanyAddress = model.getSysvarHelper().getCompanyAddressFaktur();
		paramCompanyPhone = model.getSysvarHelper().getCompanyPhoneFaktur();
		
		resetParameters();
		reloadParameter();

		if (view.getCheckKomulatif().getValue()==true){
			fillDatabaseKomulatifLengkapNew();
			jasperToExel("/erp/distribution/reports/salesorder/lapprestasikerja/lapprestasikerja2LengkapDs.jasper", "lappreskrjLengkap1");
		} else if (view.getCheckLengkap().getValue()==true){
			fillDatabaseLengkapNew();
			jasperToExel("/erp/distribution/reports/salesorder/lapprestasikerja/lapprestasikerja2LengkapDs.jasper", "lappreskrjLengkap1");			
		} else{
			fillDatabaseNew();
			jasperToExel("/erp/distribution/reports/salesorder/lapprestasikerja/lapprestasikerja2Ds.jasper", "lappreskrjLengkapKomulatif1");
		}
	}
	
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			
			final Map parameters=new HashMap();
			parameters.put("CompanyName","");
			
			parameters.put("paramCompanyName", paramCompanyName);
			parameters.put("paramCompanyAddress", paramCompanyAddress);
			parameters.put("paramCompanyPhone", paramCompanyPhone);
			
			parameters.put("paramInvoicedateFrom", dateParamInvoicedateFrom);
			parameters.put("paramInvoicedateTo", dateParamInvoicedateTo);

			parameters.put("paramSalesman", strSalesman);
			parameters.put("paramListSalesman", strListSalesman);
			
			parameters.put("paramProductGroup", "%" +  strParamProductgroup  + "%");

			if (view.getCheckBox1().getValue()==true){
				parameters.put("paramDipotongRetur", "*Total Dipotong Retur");
			}else {
				parameters.put("paramDipotongRetur", "**Total Tidak Dipotong Retur");
				
			}
			
			final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listLapPrestasiKerja);
			InputStream reportPathStream = getClass().getResourceAsStream(inputFilePath);
			final JasperPrint jasperPrint = JasperFillManager.fillReport(reportPathStream, parameters, dataSource);
			
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
			
			String fileName = "prestasi_kerja_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
			StreamResource resource = new StreamResource( source, fileName);
			resource.setMIMEType("application/pdf");
			resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
			
			view.getUI().getPage().open(resource, "_new_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	
	}

	private List<ZLapPrestasiKerja> listLapPrestasiKerja = new ArrayList<ZLapPrestasiKerja>();
	public void fillDatabaseNew(){
		String [] hari = {"Minggu", "Senin", "Selasa", "Rabo", "Kamis", "Jumat", "Sabtu"};
		String [] hariSingkat = {"Min", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab"};
		
		listLapPrestasiKerja = new ArrayList<ZLapPrestasiKerja>();		
		
		//1.Filter Salesman Yang akan Ditampilkan
		Set<FSalesman> salesmanSet = new HashSet<FSalesman>();
		if (view.getGridSalesman().isVisible()) {
			Collection itemIds = view.getGridSalesman().getSelectionModel().getSelectedRows();
			Iterator<FSalesman> iterSalesman = itemIds.iterator();
			while (iterSalesman.hasNext()){
				FSalesman fSalesman = iterSalesman.next();
				salesmanSet.add(fSalesman);
			}
			
		}else {
			Collection itemIds = model.getBeanItemContainerSalesman().getItemIds();
			for (Object itemId: itemIds){
				FSalesman fSalesman = new FSalesman();
				fSalesman = model.getBeanItemContainerSalesman().getItem(itemId).getBean();
				salesmanSet.add(fSalesman);
			}
		}

		strListSalesman = "SALESMAN: ";
		int counterSalesman = 1;
		//1.
		for (FSalesman domain: salesmanSet){
			strListSalesman += "(" + String.valueOf(counterSalesman++) + ")" +domain.getSpcode() + "  ";
			//2. Hitung Per Tanggal
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateParamInvoicedateFrom);
			while (calendar.getTime().getTime() <= dateParamInvoicedateTo.getTime()){
				//per salesman per tanggal satu
				ZLapPrestasiKerja item1 = new ZLapPrestasiKerja();
				item1.setGrup1("GRUP1");
				item1.setGrup2(domain.getSpcode() + " - " + domain.getSpname());
				item1.setGrup3(String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)));
				
				item1.setDate1(calendar.getTime());
				item1.setDate2(calendar.getTime());
				item1.setTanggal(calendar.getTime());
				item1.setHari(hari[calendar.get(Calendar.DAY_OF_WEEK)-1]);
				
				Set<Long> skuSoldProductId = new HashSet<Long>();
				Set<Long> jumlahTokoId = new HashSet<Long>();
				Set<Long> effectiveCallId = new HashSet<Long>();
				int jumlahNotaFaktur = 0;
				int jumlahNotaRetur = 0;
				int lines = 0;
				int jumlahToko=0;

				double totalBrutoDpp = 0.0;
				double totalDiscBarangDpp = 0.0;
				double totalDiscNotaDpp = 0.0;
				double totalReturDpp = 0.0;
				double totalReturPpn = 0.0;
				double totalDpp = 0.0;
				double totalAfterDisc2AfterPpn = 0.0;
				
				List<FtSalesh> listFtSalesh = new ArrayList<FtSalesh>();
				listFtSalesh = model.getFtSaleshJpaService()
						.findAllByInvoicedateOrderBySalesman(calendar.getTime(), calendar.getTime(), "%", "%" + domain.getSpcode() +"%", "%");
				
				for (FtSalesh ftSalesh: listFtSalesh) {
					List<FtSalesd> listFtSalesd = new ArrayList<FtSalesd>(ftSalesh.getFtsalesdSet());
					for (FtSalesd ftSalesd: listFtSalesd){

						if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("R")) {
//							ftSalesd.setSprice(- ftSalesd.getSprice());
							if (view.getCheckBox1().getValue()==true){
								ftSalesd.setQty(-ftSalesd.getQty());
							} else {
								ftSalesd.setQty(0);								
							}
						}
						FtSalesd newFtSalesd = new FtSalesd();
						HeaderDetilSalesHelper headerDetilHelper = new HeaderDetilSalesHelperImpl(ftSalesd);
						newFtSalesd = headerDetilHelper.getFillFtSalesd();
						totalBrutoDpp += newFtSalesd.getSubtotal();
						totalDiscBarangDpp += newFtSalesd.getDisc1rp() + newFtSalesd.getDisc2rp();
						totalDiscNotaDpp += newFtSalesd.getDiscNota1Rp() + newFtSalesd.getDiscNotaRp();
						
						//UNTUK KOLOM RETUR
						if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("R")) {
							totalReturDpp += -Math.abs(newFtSalesd.getSubTotalAfterDiscNota());
							totalReturPpn+= -Math.abs(newFtSalesd.getSubTotalAfterDiscNotaAfterPpn());
						}
						//PERHITUNGAN LENGKAP AGAK BEDA
						totalDpp += newFtSalesd.getSubTotalAfterDiscNota();
						totalAfterDisc2AfterPpn += newFtSalesd.getSubTotalAfterDiscNotaAfterPpn();
						
						if (ftSalesh.getTipefaktur().equals("F")){
							lines++;
							skuSoldProductId.add(newFtSalesd.getFproductBean().getId());
						}	
						
					}
					
					if (ftSalesh.getTipefaktur().equals("F")){
						effectiveCallId.add(ftSalesh.getFcustomerBean().getId());
						jumlahTokoId.add(ftSalesh.getFcustomerBean().getId());
						jumlahNotaFaktur +=1;
					}else {
						jumlahNotaRetur +=1;						
					}
					jumlahTokoId.add(ftSalesh.getFcustomerBean().getId());
				}
							
				item1.setEfectiveCall(effectiveCallId.size());
				item1.setJumlahNota(jumlahNotaFaktur);
				item1.setLines(lines);
				item1.setSkuSold(skuSoldProductId.size());

				//OUTLET ACTIVE
				item1.setJumlahToko(jumlahTokoId.size());
				item1.setJumlahToko(0);
				
				double rataRata =  ((double) effectiveCallId.size()) / ((double) item1.getLines());
				if (Double.isNaN(rataRata)) {
					rataRata = 0.0;
				}
				item1.setRataRata(rataRata);
				
				double rataRata2 =  ((double) effectiveCallId.size()) / ((double) item1.getSkuSold());				
				if (Double.isNaN(rataRata2)) {
					rataRata = 0.0;
				}
				item1.setRataRata2(rataRata);
				
				item1.setTotalBeforediscBeforeppn(totalBrutoDpp);
				item1.setDiscPerbarang(-Math.abs(totalDiscBarangDpp));
				item1.setDiscNota(-Math.abs(totalDiscNotaDpp));
				
				//FOR RETUR
				item1.setReturdpp(totalReturDpp);
				item1.setReturppn(totalReturPpn);
				
				item1.setDpp(totalDpp);
				item1.setPpn(totalAfterDisc2AfterPpn-totalDpp);			
				item1.setTotalAfterdiscAfterppn(totalAfterDisc2AfterPpn);
				
				
				listLapPrestasiKerja.add(item1);
				
				//NAIKKAN CALENDAR SATU HARI
				calendar.add(Calendar.DATE, 1);
				
				
			}
			
		}
		
	}
	
	public void fillDatabaseLengkapNew(){
		String [] hari = {"Minggu", "Senin", "Selasa", "Rabo", "Kamis", "Jumat", "Sabtu"};
		String [] hariSingkat = {"Min", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab"};
		
		listLapPrestasiKerja = new ArrayList<ZLapPrestasiKerja>();		
		
		//1.Filter Salesman Yang akan Ditampilkan
		Set<FSalesman> salesmanSet = new HashSet<FSalesman>();
		if (view.getGridSalesman().isVisible()) {
			Collection itemIds = view.getGridSalesman().getSelectionModel().getSelectedRows();
			Iterator<FSalesman> iterSalesman = itemIds.iterator();
			while (iterSalesman.hasNext()){
				FSalesman fSalesman = iterSalesman.next();
				salesmanSet.add(fSalesman);
			}
			
		}else {
			Collection itemIds = model.getBeanItemContainerSalesman().getItemIds();
			for (Object itemId: itemIds){
				FSalesman fSalesman = new FSalesman();
				fSalesman = model.getBeanItemContainerSalesman().getItem(itemId).getBean();
				salesmanSet.add(fSalesman);
			}
		}

		strListSalesman = "SALESMAN: ";
		int counterSalesman = 1;
		//1.
		for (FSalesman domain: salesmanSet){
			strListSalesman += "(" + String.valueOf(counterSalesman++) + ")" +domain.getSpcode() + "  ";
			//2. Hitung Per Tanggal
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dateParamInvoicedateFrom);
			while (calendar.getTime().getTime() <= dateParamInvoicedateTo.getTime()){
				//per salesman per tanggal satu
				ZLapPrestasiKerja item1 = new ZLapPrestasiKerja();
				item1.setGrup1("GRUP1");
				item1.setGrup2(domain.getSpcode() + " - " + domain.getSpname());
				item1.setGrup3(String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)));
				
				item1.setDate1(calendar.getTime());
				item1.setDate2(calendar.getTime());
				item1.setTanggal(calendar.getTime());
				item1.setHari(hari[calendar.get(Calendar.DAY_OF_WEEK)-1]);
				
				Set<Long> skuSoldProductId = new HashSet<Long>();
				Set<Long> jumlahTokoId = new HashSet<Long>();
				Set<Long> effectiveCallId = new HashSet<Long>();
				int jumlahNotaFaktur = 0;
				int jumlahNotaRetur = 0;
				int lines = 0;
				int jumlahToko=0;

				double totalBrutoDpp = 0.0;
				double totalDiscBarangDpp = 0.0;
				double totalDiscNotaDpp = 0.0;
				double totalReturDpp = 0.0;
				double totalReturPpn = 0.0;
				double totalDpp = 0.0;
				double totalAfterDisc2AfterPpn = 0.0;
				
				List<FtSalesh> listFtSalesh = new ArrayList<FtSalesh>();
				listFtSalesh = model.getFtSaleshJpaService()
						.findAllByInvoicedateOrderBySalesman(calendar.getTime(), calendar.getTime(), "%", "%" + domain.getSpcode() +"%", "%");
				
				for (FtSalesh ftSalesh: listFtSalesh) {
					List<FtSalesd> listFtSalesd = new ArrayList<FtSalesd>(ftSalesh.getFtsalesdSet());
					for (FtSalesd ftSalesd: listFtSalesd){

						if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("R")) {
//							ftSalesd.setSprice(- ftSalesd.getSprice());
							if (view.getCheckBox1().getValue()==true){
								ftSalesd.setQty(-ftSalesd.getQty());
							} else {
								ftSalesd.setQty(0);								
							}
						}
						FtSalesd newFtSalesd = new FtSalesd();
						HeaderDetilSalesHelper headerDetilHelper = new HeaderDetilSalesHelperImpl(ftSalesd);
						newFtSalesd = headerDetilHelper.getFillFtSalesd();
						totalBrutoDpp += newFtSalesd.getSubtotal();
						totalDiscBarangDpp += newFtSalesd.getDisc1rp() + newFtSalesd.getDisc2rp();
						totalDiscNotaDpp += newFtSalesd.getDiscNota1Rp() + newFtSalesd.getDiscNotaRp();
						
						//UNTUK KOLOM RETUR
						if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("R")) {
							totalReturDpp += -Math.abs(newFtSalesd.getSubTotalAfterDiscNota());
							totalReturPpn+= -Math.abs(newFtSalesd.getSubTotalAfterDiscNotaAfterPpn());
						}
						//PERHITUNGAN LENGKAP AGAK BEDA
						totalDpp += newFtSalesd.getSubTotalAfterDiscNota();
						totalAfterDisc2AfterPpn += newFtSalesd.getSubTotalAfterDiscNotaAfterPpn();
						
						if (ftSalesh.getTipefaktur().equals("F")){
							lines++;
							skuSoldProductId.add(newFtSalesd.getFproductBean().getId());
						}	
						
					}
					
					if (ftSalesh.getTipefaktur().equals("F")){
						effectiveCallId.add(ftSalesh.getFcustomerBean().getId());
						jumlahTokoId.add(ftSalesh.getFcustomerBean().getId());
						jumlahNotaFaktur +=1;
					}else {
						jumlahNotaRetur +=1;						
					}
					jumlahTokoId.add(ftSalesh.getFcustomerBean().getId());
				}
							
				item1.setEfectiveCall(effectiveCallId.size());
				item1.setJumlahNota(jumlahNotaFaktur);
				item1.setLines(lines);
				item1.setSkuSold(skuSoldProductId.size());

				//OUTLET ACTIVE
				item1.setJumlahToko(jumlahTokoId.size());
				item1.setJumlahToko(0);
				
				double rataRata =  ((double) effectiveCallId.size()) / ((double) item1.getLines());
				if (Double.isNaN(rataRata)) {
					rataRata = 0.0;
				}
				item1.setRataRata(rataRata);
				
				double rataRata2 =  ((double) effectiveCallId.size()) / ((double) item1.getSkuSold());				
				if (Double.isNaN(rataRata2)) {
					rataRata = 0.0;
				}
				item1.setRataRata2(rataRata);
				
				item1.setTotalBeforediscBeforeppn(totalBrutoDpp);
				item1.setDiscPerbarang(-Math.abs(totalDiscBarangDpp));
				item1.setDiscNota(-Math.abs(totalDiscNotaDpp));
				
				//FOR RETUR
				item1.setReturdpp(totalReturDpp);
				item1.setReturppn(totalReturPpn);
				
				item1.setDpp(totalDpp);
				item1.setPpn(totalAfterDisc2AfterPpn-totalDpp);			
				item1.setTotalAfterdiscAfterppn(totalAfterDisc2AfterPpn);
				
				
				listLapPrestasiKerja.add(item1);
				
				//NAIKKAN CALENDAR SATU HARI
				calendar.add(Calendar.DATE, 1);
				
				
			}
			
		}
		
	}
	
	public void fillDatabaseKomulatifLengkapNew(){
		String [] hari = {"Minggu", "Senin", "Selasa", "Rabo", "Kamis", "Jumat", "Sabtu"};
		String [] hariSingkat = {"Min", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab"};
		
		listLapPrestasiKerja = new ArrayList<ZLapPrestasiKerja>();		
		
		//1.Filter Salesman Yang akan Ditampilkan
		Set<FSalesman> salesmanSet = new HashSet<FSalesman>();
		if (view.getGridSalesman().isVisible()) {
			Collection itemIds = view.getGridSalesman().getSelectionModel().getSelectedRows();
			Iterator<FSalesman> iterSalesman = itemIds.iterator();
			while (iterSalesman.hasNext()){
				FSalesman fSalesman = iterSalesman.next();
				salesmanSet.add(fSalesman);
			}
			
		}else {
			Collection itemIds = model.getBeanItemContainerSalesman().getItemIds();
			for (Object itemId: itemIds){
				FSalesman fSalesman = new FSalesman();
				fSalesman = model.getBeanItemContainerSalesman().getItem(itemId).getBean();
				salesmanSet.add(fSalesman);
			}
		}

		
		strListSalesman = "SALESMAN: ";
		int counterSalesman = 1;
		for (FSalesman domain: salesmanSet){		
			strListSalesman += "(" + String.valueOf(counterSalesman++) + ")" +domain.getSpcode() + "-" + domain.getSpname() + "  ";
		}
		//1.
		//2. Hitung Per Tanggal
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateParamInvoicedateFrom);
		while (calendar.getTime().getTime() <= dateParamInvoicedateTo.getTime()){
			//per salesman per tanggal satu
			ZLapPrestasiKerja item1 = new ZLapPrestasiKerja();
			item1.setGrup1("GRUP1");
			item1.setGrup2("KOMULATIF");
			item1.setGrup3(String.valueOf(calendar.get(Calendar.WEEK_OF_YEAR)));
			
			item1.setDate1(calendar.getTime());
			item1.setDate2(calendar.getTime());
			item1.setTanggal(calendar.getTime());
			item1.setHari(hari[calendar.get(Calendar.DAY_OF_WEEK)-1]);
			
			Set<Long> skuSoldProductId = new HashSet<Long>();
			Set<Long> jumlahTokoId = new HashSet<Long>();
			Set<Long> effectiveCallId = new HashSet<Long>();
			int jumlahNotaFaktur = 0;
			int jumlahNotaRetur = 0;
			int lines = 0;
			int jumlahToko=0;

			double totalBrutoDpp = 0.0;
			double totalDiscBarangDpp = 0.0;
			double totalDiscNotaDpp = 0.0;
			double totalReturDpp = 0.0;
			double totalReturPpn = 0.0;
			double totalDpp = 0.0;
			double totalAfterDisc2AfterPpn = 0.0;
			
			for (FSalesman domain: salesmanSet){
				
				List<FtSalesh> listFtSalesh = new ArrayList<FtSalesh>();
				listFtSalesh = model.getFtSaleshJpaService()
						.findAllByInvoicedateOrderBySalesman(calendar.getTime(), calendar.getTime(), "%", "%" + domain.getSpcode() +"%", "%");
				
				for (FtSalesh ftSalesh: listFtSalesh) {
					List<FtSalesd> listFtSalesd = new ArrayList<FtSalesd>(ftSalesh.getFtsalesdSet());
					for (FtSalesd ftSalesd: listFtSalesd){

						if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("R")) {
//							ftSalesd.setSprice(- ftSalesd.getSprice());
							if (view.getCheckBox1().getValue()==true){
								ftSalesd.setQty(-ftSalesd.getQty());
							} else {
								ftSalesd.setQty(0);								
							}
						}
						FtSalesd newFtSalesd = new FtSalesd();
						HeaderDetilSalesHelper headerDetilHelper = new HeaderDetilSalesHelperImpl(ftSalesd);
						newFtSalesd = headerDetilHelper.getFillFtSalesd();
						totalBrutoDpp += newFtSalesd.getSubtotal();
						totalDiscBarangDpp += newFtSalesd.getDisc1rp() + newFtSalesd.getDisc2rp();
						totalDiscNotaDpp += newFtSalesd.getDiscNota1Rp() + newFtSalesd.getDiscNotaRp();
						
						//UNTUK KOLOM RETUR
						if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("R")) {
							totalReturDpp += -Math.abs(newFtSalesd.getSubTotalAfterDiscNota());
							totalReturPpn+= -Math.abs(newFtSalesd.getSubTotalAfterDiscNotaAfterPpn());
						}
						//PERHITUNGAN LENGKAP AGAK BEDA
						totalDpp += newFtSalesd.getSubTotalAfterDiscNota();
						totalAfterDisc2AfterPpn += newFtSalesd.getSubTotalAfterDiscNotaAfterPpn();
						
						if (ftSalesh.getTipefaktur().equals("F")){
							lines++;
							skuSoldProductId.add(newFtSalesd.getFproductBean().getId());
						}	
						
					}
					
					if (ftSalesh.getTipefaktur().equals("F")){
						effectiveCallId.add(ftSalesh.getFcustomerBean().getId());
						jumlahTokoId.add(ftSalesh.getFcustomerBean().getId());
						jumlahNotaFaktur +=1;
					}else {
						jumlahNotaRetur +=1;						
					}
					jumlahTokoId.add(ftSalesh.getFcustomerBean().getId());
				}
			}
			
			item1.setEfectiveCall(effectiveCallId.size());
			item1.setJumlahNota(jumlahNotaFaktur);
			item1.setLines(lines);
			item1.setSkuSold(skuSoldProductId.size());

			//OUTLET ACTIVE
			item1.setJumlahToko(jumlahTokoId.size());
			item1.setJumlahToko(0);
			
			double rataRata =  ((double) effectiveCallId.size()) / ((double) item1.getLines());
			if (Double.isNaN(rataRata)) {
				rataRata = 0.0;
			}
			item1.setRataRata(rataRata);
			
			double rataRata2 =  ((double) effectiveCallId.size()) / ((double) item1.getSkuSold());				
			if (Double.isNaN(rataRata2)) {
				rataRata = 0.0;
			}
			item1.setRataRata2(rataRata);
			
			item1.setTotalBeforediscBeforeppn(totalBrutoDpp);
			item1.setDiscPerbarang(-Math.abs(totalDiscBarangDpp));
			item1.setDiscNota(-Math.abs(totalDiscNotaDpp));
			
			//FOR RETUR
			item1.setReturdpp(totalReturDpp);
			item1.setReturppn(totalReturPpn);
			
			item1.setDpp(totalDpp);
			item1.setPpn(totalAfterDisc2AfterPpn-totalDpp);			
			item1.setTotalAfterdiscAfterppn(totalAfterDisc2AfterPpn);
			
			listLapPrestasiKerja.add(item1);			
			//NAIKKAN CALENDAR SATU HARI
			calendar.add(Calendar.DATE, 1);
			
		}
		
	}
	
	public void jasperToExel(String inputFilePath, String outputFilePath){
		try {			
			final Map parameters=new HashMap();
			parameters.put("CompanyName","");
			
			parameters.put("paramCompanyName", paramCompanyName);
			parameters.put("paramCompanyAddress", paramCompanyAddress);
			parameters.put("paramCompanyPhone", paramCompanyPhone);
			
			parameters.put("paramInvoicedateFrom", dateParamInvoicedateFrom);
			parameters.put("paramInvoicedateTo", dateParamInvoicedateTo);

			parameters.put("paramSalesman", strSalesman);
			parameters.put("paramListSalesman", strListSalesman);
			
			parameters.put("paramProductGroup", "%" +  strParamProductgroup  + "%");

			if (view.getCheckBox1().getValue()==true){
				parameters.put("paramDipotongRetur", "*Total Dipotong Retur");
			}else {
				parameters.put("paramDipotongRetur", "**Total Tidak Dipotong Retur");
				
			}
			
			final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listLapPrestasiKerja);
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
			
			String fileName = "exelp"  +System.currentTimeMillis() +".xls";
			StreamResource resource = new StreamResource( source, fileName);
			resource.setMIMEType("application/vnd.ms-excel");
			resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
			
			view.getUI().getPage().open(resource, "_new_" , false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	
}
