package org.erp.distribution.kontrolstok.lapstockopname;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FStock;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.ZLapMutasiStock;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class LapStockOpnamePresenter implements ClickListener{
	private LapStockOpnameModel model;
	private LapStockOpnameView view;

	public LapStockOpnamePresenter(LapStockOpnameModel model, LapStockOpnameView view){
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
			if(view.getCheckBox2().getValue()==true) {
				previewLengkap();
			}
		} else if (event.getButton()==view.getBtnClose()){
		}
	}
	
	private String strParamNorek = "";
	private String strParamWarehouse = "";
	private Date dateParamTrdate = null;
	
	public void resetParameters(){
		strParamNorek= "";
		strParamWarehouse = "";
		dateParamTrdate = new Date();
	}
	public void reloadParameter(){
	}
	
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
		parameters.put("CompanyName","");
		
		parameters.put("paramNorek", strParamNorek);
		parameters.put("paramWarehouse", strParamWarehouse);		
		parameters.put("paramTrdate", dateParamTrdate);
		
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
		
		view.getUI().getPage().open(resource, "_new_stock_opname_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	
	}
	
	public void previewLengkap(){
		resetParameters();
		reloadParameter();
		
		//1. ISI DATABASE UNTUK TEMP
		fillDatabaseReportLengkap();
		//2. PREVIEW LAPORAN
		showPreview("/erp/distribution/reports/kontrolstock/lapstockopname/lapstockopname1.jasper", "lapstockopname1");
		
	}

	public void fillDatabaseReportLengkap(){
//		//HAPUS DATA
//		Iterator<ZLapMutasiStock> iterZLapMutasiStockDelete = model.getLapMutasiStockJpaService().findAll().iterator();
//		while (iterZLapMutasiStockDelete.hasNext()) {
//			model.getLapMutasiStockJpaService().removeObject(iterZLapMutasiStockDelete.next());
//		}
//		
//		Iterator<FProduct> iterFProduct = model.getfProductJpaService().findAll(view.getCheckBox3().getValue()).iterator();
//		while (iterFProduct.hasNext()) {
//			FProduct fProduct = new FProduct();
//			fProduct = iterFProduct.next();
//
//			ZLapMutasiStock domain = new ZLapMutasiStock();
////			domain.setId(0);
//			domain.setGrup1("G1");
//			domain.setGrup2("G2");
//			domain.setGrup3("G3");
//			domain.setPcode(fProduct.getPcode());
//			domain.setPname(fProduct.getPname() + " " + fProduct.getPackaging());
//
//			Iterator<FStock> iterStock = model.getfStockJpaService().findAll(strParamWarehouseId, fProduct,dateParamStockdateFrom, dateParamStockdateTo).iterator();			
//			
//
//			int penerimaanPembelianPcs =0 ;
//			int penerimaanReturjualPcs =0;
//			int penerimaanTransferinPcs =0;
//
//			int pengeluaranReturpembelianPcs =0 ;
//			int pengeluaranPenjualanPcs =0;
//			int pengeluaranTransferoutPcs =0;
//
//			int adjustPcs =0;
//			
//			if (iterStock.hasNext()) {
//				
//				FStock fStock = new FStock();
//				fStock = iterStock.next();
//
//				//::SALDO AWAL
//				domain.setSaldoAwalPcs(fStock.getSaldoawal());
//				domain.setSaldoAwalBes(model.getProductAndStockHelper().getBesFromPcs(fStock.getSaldoawal(), fProduct));
//				domain.setSaldoAwalSed(model.getProductAndStockHelper().getSedFromPcs(fStock.getSaldoawal(), fProduct));
//				domain.setSaldoAwalKec(model.getProductAndStockHelper().getKecFromPcs(fStock.getSaldoawal(), fProduct));
//				
//				domain.setSaldoAwalNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(fStock.getSaldoawal(), fProduct));
//				domain.setSaldoAwalNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(fStock.getSaldoawal(), fProduct));
//				
//				//1. *PENERIMAAN PEMBELIAN
//					penerimaanPembelianPcs += model.getProductAndStockHelper().getStockInPurchase(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//				//2. *RETUR PENJUALAN
//					penerimaanReturjualPcs += model.getProductAndStockHelper().getStockInSalesReturn(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//				//3. *TRANSFER IN
//					penerimaanTransferinPcs += model.getProductAndStockHelper().getStockInStocktransferIn(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//
//				//1. #RETUR PEMBELIAN
//					pengeluaranReturpembelianPcs += model.getProductAndStockHelper().getStockOutPurchaseReturn(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//				//2. #PENJUALAN
//					pengeluaranPenjualanPcs += model.getProductAndStockHelper().getStockOutSalesOrder(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//				//3. #TRANSFER OUT
//					pengeluaranTransferoutPcs += model.getProductAndStockHelper().getStockOutStocktransferOut(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//				
//					
//				//*#ADJUST
//					adjustPcs += model.getProductAndStockHelper().getStockPenyesuaian(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//					adjustPcs +=  model.getProductAndStockHelper().getStockPenyesuaianStockopname(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//					
//				//::SALDO AKHIR :: AKAN DIUBAHUBAH SAMPAI YANG TERAKHIR
//				domain.setSaldoAkhirPcs(fStock.getSaldoakhir());
//				domain.setSaldoAkhirBes(model.getProductAndStockHelper().getBesFromPcs(fStock.getSaldoakhir(), fProduct));
//				domain.setSaldoAkhirSed(model.getProductAndStockHelper().getSedFromPcs(fStock.getSaldoakhir(), fProduct));
//				domain.setSaldoAkhirKec(model.getProductAndStockHelper().getKecFromPcs(fStock.getSaldoakhir(), fProduct));
//
//				domain.setSaldoAkhirNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(fStock.getSaldoakhir(), fProduct));
//				domain.setSaldoAkhirNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(fStock.getSaldoakhir(), fProduct));
//				
//			}
//			while (iterStock.hasNext()) {
//				FStock fStock = new FStock();
//				fStock = iterStock.next();
//				
//				
//			//1. *PENERIMAAN PEMBELIAN
//				penerimaanPembelianPcs += model.getProductAndStockHelper().getStockInPurchase(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//			//2. *RETUR PENJUALAN
//				penerimaanReturjualPcs += model.getProductAndStockHelper().getStockInSalesReturn(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//			//3. *TRANSFER IN
//				penerimaanTransferinPcs += model.getProductAndStockHelper().getStockInStocktransferIn(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//
//			//1. #RETUR PEMBELIAN
//				pengeluaranReturpembelianPcs += model.getProductAndStockHelper().getStockOutPurchaseReturn(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//			//2. #PENJUALAN
//				pengeluaranPenjualanPcs += model.getProductAndStockHelper().getStockOutSalesOrder(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//			//3. #TRANSFER OUT
//				pengeluaranTransferoutPcs += model.getProductAndStockHelper().getStockOutStocktransferOut(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//				
//				//*#ADJUST
//				adjustPcs += model.getProductAndStockHelper().getStockPenyesuaian(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//				adjustPcs +=  model.getProductAndStockHelper().getStockPenyesuaianStockopname(fStock.getFwarehouseBean(), fProduct, fStock.getStockdate());
//				
//				//::SALDO AKHIR :: AKAN DIUBAHUBAH SAMPAI YANG TERAKHIR
//				domain.setSaldoAkhirPcs(fStock.getSaldoakhir());
//				domain.setSaldoAkhirBes(model.getProductAndStockHelper().getBesFromPcs(fStock.getSaldoakhir(), fProduct));
//				domain.setSaldoAkhirSed(model.getProductAndStockHelper().getSedFromPcs(fStock.getSaldoakhir(), fProduct));
//				domain.setSaldoAkhirKec(model.getProductAndStockHelper().getKecFromPcs(fStock.getSaldoakhir(), fProduct));
//
//				domain.setSaldoAkhirNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(fStock.getSaldoakhir(), fProduct));
//				domain.setSaldoAkhirNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(fStock.getSaldoakhir(), fProduct));
//
//			}
//			
//			
//		//1. *PENERIMAAN PEMBELIAN
//			domain.setPembelianPcs(penerimaanPembelianPcs);
//			domain.setPembelianBes(model.getProductAndStockHelper().getBesFromPcs(penerimaanPembelianPcs, fProduct));
//			domain.setPembelianSed(model.getProductAndStockHelper().getSedFromPcs(penerimaanPembelianPcs, fProduct));
//			domain.setPembelianKec(model.getProductAndStockHelper().getKecFromPcs(penerimaanPembelianPcs, fProduct));
//			
//			domain.setPembelianNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(penerimaanPembelianPcs, fProduct));
//			domain.setPembelianNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(penerimaanPembelianPcs, fProduct));
//		//2. *RETUR PENJUALAN
//			domain.setReturPenjualanPcs(penerimaanReturjualPcs);
//			domain.setReturPenjualanBes(model.getProductAndStockHelper().getBesFromPcs(penerimaanReturjualPcs, fProduct));
//			domain.setReturPenjualanSed(model.getProductAndStockHelper().getSedFromPcs(penerimaanReturjualPcs, fProduct));
//			domain.setReturPenjualanKec(model.getProductAndStockHelper().getKecFromPcs(penerimaanReturjualPcs, fProduct));
//			
//			domain.setReturPenjualanNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(penerimaanReturjualPcs, fProduct));
//			domain.setReturPenjualanNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(penerimaanReturjualPcs, fProduct));
//		//3. *TRANSFER IN
//			domain.setTransferInPcs(penerimaanTransferinPcs);
//			domain.setTransferInBes(model.getProductAndStockHelper().getBesFromPcs(penerimaanTransferinPcs, fProduct));
//			domain.setTransferInSed(model.getProductAndStockHelper().getSedFromPcs(penerimaanTransferinPcs, fProduct));
//			domain.setTransferInKec(model.getProductAndStockHelper().getKecFromPcs(penerimaanTransferinPcs, fProduct));
//			
//			domain.setTransferInNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(penerimaanTransferinPcs, fProduct));
//			domain.setTransferInNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(penerimaanTransferinPcs, fProduct));
//
//		//1. #RETUR PEMBELIAN
//			domain.setReturPembelianPcs(pengeluaranReturpembelianPcs);
//			domain.setReturPembelianBes(model.getProductAndStockHelper().getBesFromPcs(pengeluaranReturpembelianPcs, fProduct));
//			domain.setReturPembelianSed(model.getProductAndStockHelper().getSedFromPcs(pengeluaranReturpembelianPcs, fProduct));
//			domain.setReturPembelianKec(model.getProductAndStockHelper().getKecFromPcs(pengeluaranReturpembelianPcs, fProduct));
//			
//			domain.setReturPembelianNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(pengeluaranReturpembelianPcs, fProduct));
//			domain.setReturPembelianNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(pengeluaranReturpembelianPcs, fProduct));			
//		//2. #PENJUALAN
//			domain.setPenjualanPcs(pengeluaranPenjualanPcs);
//			domain.setPenjualanBes(model.getProductAndStockHelper().getBesFromPcs(pengeluaranPenjualanPcs, fProduct));
//			domain.setPenjualanSed(model.getProductAndStockHelper().getSedFromPcs(pengeluaranPenjualanPcs, fProduct));
//			domain.setPenjualanKec(model.getProductAndStockHelper().getKecFromPcs(pengeluaranPenjualanPcs, fProduct));
//			
//			domain.setPenjualanNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(pengeluaranPenjualanPcs, fProduct));
//			domain.setPenjualanNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(pengeluaranPenjualanPcs, fProduct));			
//		//3. #TRANSFER OUT
//			domain.setTransferOutPcs(pengeluaranTransferoutPcs);
//			domain.setTransferOutBes(model.getProductAndStockHelper().getBesFromPcs(pengeluaranTransferoutPcs, fProduct));
//			domain.setTransferOutSed(model.getProductAndStockHelper().getSedFromPcs(pengeluaranTransferoutPcs, fProduct));
//			domain.setTransferOutKec(model.getProductAndStockHelper().getKecFromPcs(pengeluaranTransferoutPcs, fProduct));
//			
//			domain.setTransferOutNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(pengeluaranTransferoutPcs, fProduct));
//			domain.setTransferOutNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(pengeluaranTransferoutPcs, fProduct));			
//			
//		//*#PENYESUAIAN/ADJUST
//			domain.setPenyesuaianPcs(adjustPcs);
//			domain.setPenyesuaianBes(model.getProductAndStockHelper().getBesFromPcs(adjustPcs, fProduct));
//			domain.setPenyesuaianSed(model.getProductAndStockHelper().getSedFromPcs(adjustPcs, fProduct));
//			domain.setPenyesuaianKec(model.getProductAndStockHelper().getKecFromPcs(adjustPcs, fProduct));
//			
//			domain.setPenyesuaianNilaiBeli(model.getProductAndStockHelper().getPPriceBeforePpnFromFProduct(adjustPcs, fProduct));
//			domain.setPenyesuaianNilaiJual(model.getProductAndStockHelper().getSPriceBeforePpnFromFProduct(adjustPcs, fProduct));			
//			
//			//::SIMPAN::
//			model.getLapMutasiStockJpaService().createObject(domain);
//			
//		}
		
		
		
 	}
	
}
