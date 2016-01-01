package org.erp.distribution.utility.pembatalanpenyesuaian;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FStock;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtOpnamed;
import org.erp.distribution.model.FtOpnameh;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.util.ReportJdbcConfigHelper;
import org.vaadin.dialogs.ConfirmDialog;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class PembatalanPenyesuaianPresenter implements ClickListener{
	private PembatalanPenyesuaianModel model;
	private PembatalanPenyesuaianView view;

	public PembatalanPenyesuaianPresenter(PembatalanPenyesuaianModel model, PembatalanPenyesuaianView view){
		this.model = model;
		this.view = view;
		initListener();
	}
	
	public void initListener(){
		view.getBtnProcess().addClickListener(this);
		view.getBtnClose().addClickListener(this);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnProcess()){
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                    	
                    	pembatalanPostingPertanggal();
                    	
            			model.getProductAndStockHelper().recalculateSaldoStock(null, view.getDateField1From().getValue(), view.getDateField1To().getValue());
            			Notification.show("PERBAIKAN STOCK SELESAI!", Notification.TYPE_TRAY_NOTIFICATION);
            			
                    } else {
                    // User did not confirm
                    }
				}
			};
			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Pembatalan Adjustment", "Pembatalan Adjustment Pertanggal! Lanjutkan Pembatalan?", 
					 "Oke", "Cancel", konfirmDialogListener);
			 
			   final ShortcutListener enter = new ShortcutListener("Oke",
		                KeyCode.ENTER, null) {
		            @Override
		            public void handleAction(Object sender, Object target) {
		            	d.close();
		            }
		        };
			
			 d.setStyleName("dialog");
			 d.getOkButton().setStyleName("small");
			 d.getCancelButton().setStyleName("small");
			 d.focus();
			
			
		} else if (event.getButton()==view.getBtnClose()){
		}
	}
	
	
	public void pembatalanPostingPertanggal(){
		
		Date trdateFrom = view.getDateField1From().getValue();
		Date trdateTo = view.getDateField1To().getValue();
		Set<FWarehouse> fWareHouseSet = new HashSet<FWarehouse>();
		
		//1. AMBIL STOK OPNAME PER TANGGAL
		List<FtOpnameh> listFtOpnameh = new ArrayList<FtOpnameh>();
		listFtOpnameh = model.getFtOpnamehJpaService().findAllByTrdate(trdateFrom, trdateTo);
		for (FtOpnameh ftOpnameh: listFtOpnameh) {
			Date trdateTransaksi = ftOpnameh.getTrdate();
			fWareHouseSet.add(ftOpnameh.getFwarehouseBean());
			
			List<FtOpnamed> listFtOpnamed = new ArrayList<FtOpnamed>(ftOpnameh.getFtopnamedSet());
			for (FtOpnamed ftOpnamed: listFtOpnamed) {
				//1. AMBIL SALDO AKHIR DAN SESUAIKAN
				Integer qtyTeori = 0;
				Integer qtyPenyesuaian =0;
				Integer newQtySaldoStockAkhir =0;
				FStock fStock = new FStock();			
				fStock = model.getfStockJpaService().findAll(ftOpnamed.getFtopnamehBean().getFwarehouseBean(), ftOpnamed.getFproductBean(), trdateTransaksi).get(0);
				
				//2. HITUNG PENYESUAIAN :: DISERAHKAN PADA RECALKULASI SALDO STOK

				fStock.setQtyadjust(0);
				fStock.setSaldoakhir(0);
				
				ftOpnamed.setQtyteori(0);
				ftOpnamed.setQtyadjust(0);

				
				//3. MASUKKAN KE DALAM DATABASE ITEM DETIL SEBAGAI PENYESUAIAN TANGGAL TERSEBUT			
				model.getfStockJpaService().updateObject(fStock);
				model.getFtOpnamedJpaService().updateObject(ftOpnamed);				
				
			}
			ftOpnameh.setPosting(false);
			model.getFtOpnamehJpaService().updateObject(ftOpnameh);
		}
		
    	//3. REKALKULASI SALDO STOK :: AFTER POSTING
		List<FWarehouse> listFWarehouse = new ArrayList<FWarehouse>(fWareHouseSet);
		for (FWarehouse fWarehouse: listFWarehouse) {
			model.getProductAndStockHelper().recalculateSaldoStock(fWarehouse, trdateFrom, trdateTo);
		}
		
		Notification.show("Pembatalan Selesai", Notification.TYPE_TRAY_NOTIFICATION);
		
		
	}
	
	
}
