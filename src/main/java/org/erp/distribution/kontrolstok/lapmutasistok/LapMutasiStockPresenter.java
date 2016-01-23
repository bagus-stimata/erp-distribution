package org.erp.distribution.kontrolstok.lapmutasistok;

import java.io.ByteArrayInputStream;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FProductgroupdivisi;
import org.erp.distribution.model.FStock;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.ZLapMutasiStock;
import org.erp.distribution.model.ZLapPrestasiKerja;
import org.erp.distribution.modeltest.TestFactory;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class LapMutasiStockPresenter implements ClickListener{
	private LapMutasiStockModel model;
	private LapMutasiStockView view;

	private JasperReport reportLengkap;
	private JasperReport reportRingkas;
	
	public LapMutasiStockPresenter(LapMutasiStockModel model, LapMutasiStockView view){
		this.model = model;
		this.view = view;
		initListener();
		try{
		reportLengkap = (JasperReport) JRLoader.loadObject(getClass()
				.getResourceAsStream("/erp/distribution/reports/kontrolstock/mutasistoklengkap/lapmutasistoklengkap1.jasper"));
		reportRingkas = (JasperReport) JRLoader.loadObject(getClass()
				.getResourceAsStream("/erp/distribution/reports/kontrolstock/mutasistok/mutasistock.jasper"));
	
		} catch(Exception ex){}
	}
	
	public void initListener(){
		view.getBtnExtractToExel().addClickListener(this);
		view.getBtnPreview().addClickListener(this);
		view.getBtnClose().addClickListener(this);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnPreview()){
			if(view.getCheckBox2().getValue()==true) {
				previewLengkap();
			}else {
				previewRingkas();				
			}
			
//			showPreviewTest();
			
		} else if (event.getButton()==view.getBtnExtractToExel()){
			extractToExel();
		} else if (event.getButton()==view.getBtnClose()){
		}
	}
	
	private String strParamProductgroup = "";
	private String strParamWarehouseId = "";
	private String strParamWarehouse = "";
	private long longParamVendor = 0;
	private String strPramaProductDivisi = "";
	private Date dateParamStockdateFrom = null;
	private Date dateParamStockdateTo = null;
	private Integer paramSumQtyinQtyout = 0;
	
	public void resetParameters(){
		strParamProductgroup= "%";
		strParamWarehouseId = "%";
		strParamWarehouse = "";
		longParamVendor = 0;
		strPramaProductDivisi = "%";
		dateParamStockdateFrom = model.getTransaksiHelper().getCurrentTransDate();
		dateParamStockdateTo = model.getTransaksiHelper().getCurrentTransDate();
		paramSumQtyinQtyout = 0;
	}
	public void reloadParameter(){
		try{
			FWarehouse fWarehouse = new FWarehouse();
			fWarehouse = (FWarehouse) view.getComboGroup1().getConvertedValue();
			strParamWarehouseId = fWarehouse.getId().trim();
			
			strParamWarehouse = "(" +fWarehouse.getId() + ") " + fWarehouse.getDescription();
		} catch(Exception ex){}
		try{
			FProductgroup fProductgroup = new FProductgroup();
			fProductgroup = (FProductgroup) view.getComboGroup2().getConvertedValue();
			strParamProductgroup = fProductgroup.getId().trim();
		} catch(Exception ex){}
		try{
			FProductgroupdivisi fProductgroupdivisi = new FProductgroupdivisi();
			fProductgroupdivisi = (FProductgroupdivisi) view.getComboGroup3().getConvertedValue();
			strPramaProductDivisi = fProductgroupdivisi.getId().trim();
		} catch(Exception ex){}
		try{
			FVendor fVendor = new FVendor();
			fVendor = (FVendor) view.getComboGroup4().getConvertedValue();
			longParamVendor = fVendor.getId();
		} catch(Exception ex){}
		try{
			dateParamStockdateFrom = view.getDateField1From().getValue();
		} catch(Exception ex){}
		try{
			dateParamStockdateTo = view.getDateField1To().getValue();
		} catch(Exception ex){}
		
		//JIKA HANYA YANG ADA MUTASI
		if (view.getCheckBox1().getValue()==true) {			
			paramSumQtyinQtyout=0;
		}else {
			paramSumQtyinQtyout=-100000000;			
		}
	}
	public void previewRingkas(){

		model.getProductAndStockHelper().transferSaldoStokAwalFromBefore(dateParamStockdateFrom);
		
		resetParameters();
		reloadParameter();
		
		//PERBAIKI SALDO STOCK SEBELUM DI TAYANGKAN REPORTNYA
		if (view.getCheckBox4().getValue()==true){
			model.getProductAndStockHelper().recalculateSaldoStock(null, dateParamStockdateFrom, dateParamStockdateTo);
		}
		showPreview("/erp/distribution/reports/kontrolstock/mutasistok/mutasistock.jasper", "mutasi_stock");
		
	}
	
	public void showPreviewTest(){
		try{
		final JasperReport reportLengkapx = (JasperReport) JRLoader.loadObject(getClass()
				.getResourceAsStream("/erp/distribution/reports/xtest/BeanASDatasource.jasper"));
	
		
		final Map parameters=new HashMap();
		parameters.put("CompanyName","");
		
		final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(TestFactory.generateCollection());
		final JRBeanArrayDataSource dataSourceArray = new JRBeanArrayDataSource(TestFactory.generateBeanArray());
			
//		String reportPath = "/erp/distribution/reports/xtest/BeanASDatasource.jasper";		
		InputStream reportPathStream = getClass().getResourceAsStream("/erp/distribution/reports/xtest/BeanASDatasource.jasper");
		final JasperPrint jasperPrint = JasperFillManager.fillReport(reportPathStream, new HashMap(), dataSource);
		
		StreamResource.StreamSource source = new StreamSource() {			
			@Override
			public InputStream getStream() {
				byte[] b = null;
				try {
//					b = JasperRunManager
//							.runReportToPdf(reportLengkapx, parameters, new JRBeanCollectionDataSource(TestFactory.generateCollection()));
					b = JasperExportManager.exportReportToPdf(jasperPrint);
				} catch (JRException ex) {
					System.out.println(ex);
				}
				return new ByteArrayInputStream(b);
			}
		};
		
		String fileName = "test_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new_kontrol_stok_", false);
	
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	
	public void showPreview(String inputFilePath, final String outputFilePath){
		try{	
			final Map parameters=new HashMap();
			parameters.put("CompanyName","");
	
			
			parameters.put("paramStockdateFrom", dateParamStockdateFrom);
			parameters.put("paramStockdateTo", dateParamStockdateTo);
			
			parameters.put("paramWarehouseId", "%" +  strParamWarehouseId  + "%");
			parameters.put("paramProductgroup", "%" +  strParamProductgroup  + "%");
	
	
			parameters.put("paramSumQtyinQtyout", paramSumQtyinQtyout);
	
			//LAPORAN MUTASI STOK LENGKAP:: buat deskripsi saja
			parameters.put("paramGudang", strParamWarehouse );
	
			final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listLapMutasiStockToCreateObject);
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
			
			String fileName = "ar_kas_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
			StreamResource resource = new StreamResource( source, fileName);
			resource.setMIMEType("application/pdf");
			resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
			
			view.getUI().getPage().open(resource, "_new_kontrol_stok_" + outputFilePath, false);
	
		} catch(Exception ex){}
		
	}
	
	public void previewLengkap(){
		resetParameters();
		reloadParameter();
		
		//1. ISI DATABASE UNTUK TEMP
		fillDatabaseReportLengkap();
		//2. PREVIEW LAPORAN
		showPreview("/erp/distribution/reports/kontrolstock/mutasistoklengkap/lapmutasistoklengkap1Ds.jasper", "lapmutasistoklengkap1");
		
	}
	
	List<ZLapMutasiStock> listLapMutasiStockToCreateObject = new ArrayList<ZLapMutasiStock>();
	public void fillDatabaseReportLengkap(){
//		//HAPUS DATA
//		Iterator<ZLapMutasiStock> iterZLapMutasiStockDelete = model.getLapMutasiStockJpaService().findAll().iterator();
//		while (iterZLapMutasiStockDelete.hasNext()) {
//			model.getLapMutasiStockJpaService().removeObject(iterZLapMutasiStockDelete.next());
//		}
		
		Iterator<FProduct> iterFProduct = null;
		if (longParamVendor==0) {
			iterFProduct = model.getfProductJpaService().findAllForMutasi(strPramaProductDivisi, strParamProductgroup, view.getCheckBox3().getValue()).iterator();			
		}else {
			iterFProduct = model.getfProductJpaService().findAllForMutasi(longParamVendor,  strPramaProductDivisi, strParamProductgroup, view.getCheckBox3().getValue()).iterator();			
		}
		
//		List<ZLapMutasiStock> listLapMutasiStockToCreateObject = new ArrayList<ZLapMutasiStock>();
		listLapMutasiStockToCreateObject = new ArrayList<ZLapMutasiStock>();
		while (iterFProduct.hasNext()) {
			FProduct fProduct = new FProduct();
			fProduct = iterFProduct.next();

			ZLapMutasiStock domain = new ZLapMutasiStock();
//			domain.setId(0);
			domain.setGrup1("G1");
			domain.setGrup2("G2");
			domain.setGrup3("G3");
			domain.setPcode(fProduct.getPcode());
			domain.setPname(fProduct.getPname() + " " + fProduct.getPackaging());

			Iterator<FStock> iterStock = model.getfStockJpaService().findAll(strParamWarehouseId, fProduct, dateParamStockdateFrom, dateParamStockdateTo).iterator();			
			

			int penerimaanPembelianPcs =0 ;
			int penerimaanReturjualPcs =0;
			int penerimaanTransferinPcs =0;

			int pengeluaranReturpembelianPcs =0 ;
			int pengeluaranPenjualanPcs =0;
			int pengeluaranTransferoutPcs =0;

			int adjustPcs =0;
			
			if (iterStock.hasNext()) {
				
				FStock fStock = new FStock();
				fStock = iterStock.next();

				//::SALDO AWAL
				domain.setSaldoAwalPcs(fStock.getSaldoawal());
				domain.setSaldoAwalBes(model.getProductAndStockHelper().getBesFromPcs(fStock.getSaldoawal(), fProduct));
				domain.setSaldoAwalSed(model.getProductAndStockHelper().getSedFromPcs(fStock.getSaldoawal(), fProduct));
				domain.setSaldoAwalKec(model.getProductAndStockHelper().getKecFromPcs(fStock.getSaldoawal(), fProduct));
				
				domain.setSaldoAwalNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(fStock.getSaldoawal(), fProduct));
				domain.setSaldoAwalNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(fStock.getSaldoawal(), fProduct));
				
				//1. *PENERIMAAN PEMBELIAN
					penerimaanPembelianPcs += model.getProductAndStockHelper().getStockInPurchase(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
				//2. *RETUR PENJUALAN
					penerimaanReturjualPcs += model.getProductAndStockHelper().getStockInSalesReturn(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
				//3. *TRANSFER IN
					penerimaanTransferinPcs += model.getProductAndStockHelper().getStockInStocktransferIn(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());

				//1. #RETUR PEMBELIAN
					pengeluaranReturpembelianPcs += model.getProductAndStockHelper().getStockOutPurchaseReturn(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
				//2. #PENJUALAN
					pengeluaranPenjualanPcs += model.getProductAndStockHelper().getStockOutSalesOrder(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
				//3. #TRANSFER OUT
					pengeluaranTransferoutPcs += model.getProductAndStockHelper().getStockOutStocktransferOut(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
				
					
				//*#ADJUST
					adjustPcs += model.getProductAndStockHelper().getStockPenyesuaian(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
					adjustPcs +=  model.getProductAndStockHelper().getStockPenyesuaianStockopname(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
					
				//::SALDO AKHIR :: AKAN DIUBAHUBAH SAMPAI YANG TERAKHIR
				domain.setSaldoAkhirPcs(fStock.getSaldoakhir());
				domain.setSaldoAkhirBes(model.getProductAndStockHelper().getBesFromPcs(fStock.getSaldoakhir(), fProduct));
				domain.setSaldoAkhirSed(model.getProductAndStockHelper().getSedFromPcs(fStock.getSaldoakhir(), fProduct));
				domain.setSaldoAkhirKec(model.getProductAndStockHelper().getKecFromPcs(fStock.getSaldoakhir(), fProduct));

				domain.setSaldoAkhirNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(fStock.getSaldoakhir(), fProduct));
				domain.setSaldoAkhirNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(fStock.getSaldoakhir(), fProduct));
				
			}
			while (iterStock.hasNext()) {
				FStock fStock = new FStock();
				fStock = iterStock.next();
				
				
			//1. *PENERIMAAN PEMBELIAN
				penerimaanPembelianPcs += model.getProductAndStockHelper().getStockInPurchase(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
			//2. *RETUR PENJUALAN
				penerimaanReturjualPcs += model.getProductAndStockHelper().getStockInSalesReturn(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
			//3. *TRANSFER IN
				penerimaanTransferinPcs += model.getProductAndStockHelper().getStockInStocktransferIn(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());

			//1. #RETUR PEMBELIAN
				pengeluaranReturpembelianPcs += model.getProductAndStockHelper().getStockOutPurchaseReturn(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
			//2. #PENJUALAN
				pengeluaranPenjualanPcs += model.getProductAndStockHelper().getStockOutSalesOrder(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
			//3. #TRANSFER OUT
				pengeluaranTransferoutPcs += model.getProductAndStockHelper().getStockOutStocktransferOut(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
				
				//*#ADJUST
				adjustPcs += model.getProductAndStockHelper().getStockPenyesuaian(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
				adjustPcs +=  model.getProductAndStockHelper().getStockPenyesuaianStockopname(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
				
				//::SALDO AKHIR :: AKAN DIUBAHUBAH SAMPAI YANG TERAKHIR
				domain.setSaldoAkhirPcs(fStock.getSaldoakhir());
				domain.setSaldoAkhirBes(model.getProductAndStockHelper().getBesFromPcs(fStock.getSaldoakhir(), fProduct));
				domain.setSaldoAkhirSed(model.getProductAndStockHelper().getSedFromPcs(fStock.getSaldoakhir(), fProduct));
				domain.setSaldoAkhirKec(model.getProductAndStockHelper().getKecFromPcs(fStock.getSaldoakhir(), fProduct));

				domain.setSaldoAkhirNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(fStock.getSaldoakhir(), fProduct));
				domain.setSaldoAkhirNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(fStock.getSaldoakhir(), fProduct));

			}
			
		//1. *PENERIMAAN PEMBELIAN
			domain.setPembelianPcs(penerimaanPembelianPcs);
			domain.setPembelianBes(model.getProductAndStockHelper().getBesFromPcs(penerimaanPembelianPcs, fProduct));
			domain.setPembelianSed(model.getProductAndStockHelper().getSedFromPcs(penerimaanPembelianPcs, fProduct));
			domain.setPembelianKec(model.getProductAndStockHelper().getKecFromPcs(penerimaanPembelianPcs, fProduct));
			
			domain.setPembelianNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(penerimaanPembelianPcs, fProduct));
			domain.setPembelianNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(penerimaanPembelianPcs, fProduct));
		//2. *RETUR PENJUALAN
			domain.setReturPenjualanPcs(penerimaanReturjualPcs);
			domain.setReturPenjualanBes(model.getProductAndStockHelper().getBesFromPcs(penerimaanReturjualPcs, fProduct));
			domain.setReturPenjualanSed(model.getProductAndStockHelper().getSedFromPcs(penerimaanReturjualPcs, fProduct));
			domain.setReturPenjualanKec(model.getProductAndStockHelper().getKecFromPcs(penerimaanReturjualPcs, fProduct));
			
			domain.setReturPenjualanNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(penerimaanReturjualPcs, fProduct));
			domain.setReturPenjualanNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(penerimaanReturjualPcs, fProduct));
		//3. *TRANSFER IN
			domain.setTransferInPcs(penerimaanTransferinPcs);
			domain.setTransferInBes(model.getProductAndStockHelper().getBesFromPcs(penerimaanTransferinPcs, fProduct));
			domain.setTransferInSed(model.getProductAndStockHelper().getSedFromPcs(penerimaanTransferinPcs, fProduct));
			domain.setTransferInKec(model.getProductAndStockHelper().getKecFromPcs(penerimaanTransferinPcs, fProduct));
			
			domain.setTransferInNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(penerimaanTransferinPcs, fProduct));
			domain.setTransferInNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(penerimaanTransferinPcs, fProduct));

		//1. #RETUR PEMBELIAN
			domain.setReturPembelianPcs(pengeluaranReturpembelianPcs);
			domain.setReturPembelianBes(model.getProductAndStockHelper().getBesFromPcs(pengeluaranReturpembelianPcs, fProduct));
			domain.setReturPembelianSed(model.getProductAndStockHelper().getSedFromPcs(pengeluaranReturpembelianPcs, fProduct));
			domain.setReturPembelianKec(model.getProductAndStockHelper().getKecFromPcs(pengeluaranReturpembelianPcs, fProduct));
			
			domain.setReturPembelianNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(pengeluaranReturpembelianPcs, fProduct));
			domain.setReturPembelianNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(pengeluaranReturpembelianPcs, fProduct));			
		//2. #PENJUALAN
			domain.setPenjualanPcs(pengeluaranPenjualanPcs);
			domain.setPenjualanBes(model.getProductAndStockHelper().getBesFromPcs(pengeluaranPenjualanPcs, fProduct));
			domain.setPenjualanSed(model.getProductAndStockHelper().getSedFromPcs(pengeluaranPenjualanPcs, fProduct));
			domain.setPenjualanKec(model.getProductAndStockHelper().getKecFromPcs(pengeluaranPenjualanPcs, fProduct));
			
			domain.setPenjualanNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(pengeluaranPenjualanPcs, fProduct));
			domain.setPenjualanNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(pengeluaranPenjualanPcs, fProduct));			
		//3. #TRANSFER OUT
			domain.setTransferOutPcs(pengeluaranTransferoutPcs);
			domain.setTransferOutBes(model.getProductAndStockHelper().getBesFromPcs(pengeluaranTransferoutPcs, fProduct));
			domain.setTransferOutSed(model.getProductAndStockHelper().getSedFromPcs(pengeluaranTransferoutPcs, fProduct));
			domain.setTransferOutKec(model.getProductAndStockHelper().getKecFromPcs(pengeluaranTransferoutPcs, fProduct));
			
			domain.setTransferOutNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(pengeluaranTransferoutPcs, fProduct));
			domain.setTransferOutNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(pengeluaranTransferoutPcs, fProduct));			
			
		//*#PENYESUAIAN/ADJUST
			domain.setPenyesuaianPcs(adjustPcs);
			domain.setPenyesuaianBes(model.getProductAndStockHelper().getBesFromPcs(adjustPcs, fProduct));
			domain.setPenyesuaianSed(model.getProductAndStockHelper().getSedFromPcs(adjustPcs, fProduct));
			domain.setPenyesuaianKec(model.getProductAndStockHelper().getKecFromPcs(adjustPcs, fProduct));
			
			domain.setPenyesuaianNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(adjustPcs, fProduct));
			domain.setPenyesuaianNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(adjustPcs, fProduct));			
			
			//::SIMPAN::
			listLapMutasiStockToCreateObject.add(domain);
			
			
			//Jika hanya yang ada mutasi dan saldo awal sama dengan saldo akhir maka(tidak ada mutasi
//			if (view.getCheckBox1().getValue().equals(true) && domain.getSaldoAwalPcs()  == domain.getSaldoAkhirPcs() ) {
//				listLapMutasiStockToCreateObject.remove(domain);
//			}
//			//Jika hanya yang ada stok dan ternyata saldo stok awal dan akhir sama sama 0
//			if (view.getCheckBox5().getValue().equals(true)  && domain.getSaldoAwalPcs()==0 && domain.getSaldoAkhirPcs() ==0  ) {
//					listLapMutasiStockToCreateObject.remove(domain);
//			}
			
			
		}
		
//		//MASUKKAN KE DATA BASE
//		for (ZLapMutasiStock domain: listLapMutasiStockToCreateObject) {
//			
//			boolean mustCreate = true;
//
//			//ANTISIASI
//			if (view.getCheckBox1().getValue().equals(true) && domain.getSaldoAwalPcs()  == domain.getSaldoAkhirPcs() ) {
////				model.getLapMutasiStockJpaService().removeObject(domain);						
//				mustCreate=false;
//			}
//			
//			if (view.getCheckBox5().getValue().equals(true)  && domain.getSaldoAwalPcs()==0 && domain.getSaldoAkhirPcs() ==0  ) {
////					model.getLapMutasiStockJpaService().removeObject(domain);						
//				mustCreate=false;
//			}
//			
//			
//			if (mustCreate==true) {
//				model.getLapMutasiStockJpaService().createObject(domain);			
//			}
//			
//		}

		
 	}
	
	public void extractToExel(){
		resetParameters();
		reloadParameter();
		
		//1. ISI DATABASE UNTUK TEMP
		fillDatabaseReportLengkap();

		//####CREATE FILE EXEL####
		String basepath = VaadinService.getCurrent()
	            .getBaseDirectory().getAbsolutePath();
		String filePathDestination = basepath + "/MutasiStock.xls";
		
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("MutasiStock");

        Map<Integer, Object[]> data = new HashMap<Integer, Object[]>();
        data.put(1, new Object[] {"STOCKDATE_FROM","STOCKDATE_TO", "KODEBRG", "NAMABARANG", 
        		"AWAL_BES", "AWAL_SED", "AWAL_KEC", "AWAL_PCS", "AWAL_NILAIBELI", "AWAL_NILAIJUAL",
        		"AKHIR_BES", "AKHIR_SED", "AKHIR_KEC", "AKHIR_PCS", "AKHIR_NILAIBELI", "AKHIR_NILAIJUAL"
        		});
        
        Date trDateFrom = view.getDateField1From().getValue();
        Date trDateTo = view.getDateField1To().getValue();
        Iterator<ZLapMutasiStock> iterLapMutasiStock = model.getLapMutasiStockJpaService().findAll().iterator();
       int lastRow = 1;
       while (iterLapMutasiStock.hasNext()) {
       		lastRow++;
       		ZLapMutasiStock domain = new ZLapMutasiStock();
       		domain = iterLapMutasiStock.next();
       		
       		try{
		        data.put(lastRow, new Object[] {dateParamStockdateFrom, dateParamStockdateTo, domain.getPcode(), domain.getPname(), 
		        		domain.getSaldoAwalBes(), domain.getSaldoAwalSed(), domain.getSaldoAwalKec(), domain.getSaldoAwalPcs(),  domain.getSaldoAwalNilaiBeli(), domain.getSaldoAwalNilaiJual(),
		        		domain.getSaldoAkhirBes(), domain.getSaldoAkhirSed(), domain.getSaldoAkhirKec(), domain.getSaldoAkhirPcs(), domain.getSaldoAkhirNilaiBeli(), domain.getSaldoAkhirNilaiJual(),		        		
		        		});
       		} catch(Exception ex){}
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

        try {
            FileOutputStream out =
                    new FileOutputStream(new File(filePathDestination));
            workbook.write(out);
            out.close();
            System.out.println("Excel written successfully..");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        
		Resource res = new FileResource(new File(filePathDestination));		
		FileDownloader fd = new FileDownloader(res);
		
		fd.extend(view.getBtnExtractToExel());
		
	}
	
	
}

