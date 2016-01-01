package org.erp.distribution.proses.akhirhari;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.erp.distribution.DashboardUI;
import org.vaadin.dialogs.ConfirmDialog;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;


public class ProsesAkhirHariPresenter implements ClickListener{

	private ProsesAkhirHariModel model;
	private ProsesAkhirHariView view;
	
	public ProsesAkhirHariPresenter(ProsesAkhirHariModel model, ProsesAkhirHariView view){
		this.model = model;
		this.view = view;
		initListener();		
		initDisplay();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date tanggalTransaksiDivision = 
				model.getTransaksiHelper().getCurrentTransDate();

		view.getLabelTanggalTransaksiDivisi().setContentMode(ContentMode.HTML);
		view.getLabelTanggalTransaksiDivisi().setValue("<h3><font color='BLUE'> TGL TRANSAKSI: " + 
				sdf.format(tanggalTransaksiDivision) + "</font></h3>");
		
	}
	public void initListener(){
		view.getBtnProsesAkhirHari().addClickListener(this);
		
		
		ValueChangeListener listenerComboDivision = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//				Division divisionBean = new Division();
				try{
					
//					divisionBean = (Division) view.getComboDivision().getConvertedValue();
					Date tanggalTransaksiDivision = 
							model.getTransaksiHelper().getCurrentTransDate();
			
					view.getLabelTanggalTransaksiDivisi().setContentMode(ContentMode.HTML);
					view.getLabelTanggalTransaksiDivisi().setValue("<h3><font color='BLUE'> TGL TRANSAKSI: " + 
							sdf.format(tanggalTransaksiDivision) + "</font></h3>");
				} catch(Exception ex){
					view.getLabelTanggalTransaksiDivisi().setValue("<h3><font color='BLUE'> TGL TRANSAKSI:</font></h3>");					
				}
			}
		};
		view.getComboDivision().setImmediate(true);
		view.getComboDivision().addValueChangeListener(listenerComboDivision);
		
		
		
	}
	public void initDisplay(){
		
	}
	
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton() == view.getBtnProsesAkhirHari()){
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi PROSES AKHIR HARI", "YAKIN PROSES AKHIR HARI?", 
						 "OKE", "Cancel", konfirmDialogProsesAkhirHariListener);
				 
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
		}
		
	}
	
    ConfirmDialog.Listener konfirmDialogProsesAkhirHariListener = new ConfirmDialog.Listener() {					
		//@Override
		public void onClose(ConfirmDialog dialog) {
			
            if (dialog.isConfirmed()) {
                // Confirmed to continue
            	prosesAkhirHari();
            } else {
            // User did not confirm
            }
		}
	};
	
	public void prosesAkhirHari(){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{			
			//1. REKALKULASI SALDO STOK HARI INI DAN BESOK
			Date tanggalTransaksi = 
					model.getTransaksiHelper().getCurrentTransDate();	
			
			Calendar calDateYesterday = Calendar.getInstance();
			Calendar calDateNow = Calendar.getInstance();
			Calendar calDateTomorrow = Calendar.getInstance();

			calDateYesterday.setTime(tanggalTransaksi);
			calDateNow.setTime(tanggalTransaksi);
			calDateTomorrow.setTime(tanggalTransaksi);

			calDateYesterday.add(Calendar.DATE, -1);
			//TOMORROW 2 HARI SESUDAH SAJA BIAR TIDAK TERLALU LAMA
			calDateTomorrow.add(Calendar.DATE, 2);
			
			
			model.getProductAndStockHelper().recalculateSaldoStock(null, calDateYesterday.getTime(), calDateTomorrow.getTime());
			//2. CLOSING TRANSAKSI DAN MEMAJUKAN TANGGAL
			model.getTransaksiHelper().prosesAkhirHari();
			
			//3. HAPUS TRANSAKSI YANG MASIH NEW :: IKUT DI PROSES AKHIR HARI DIATASNYA
			
			Date newTanggalTransaksiDivision = 
					model.getTransaksiHelper().getCurrentTransDate();	
			
			view.getLabelTanggalTransaksiDivisi().setContentMode(ContentMode.HTML);
			view.getLabelTanggalTransaksiDivisi().setValue("<h3><font color='GREEN'> TGL TRANSAKSI: " +
					sdf.format(newTanggalTransaksiDivision) + "(NEW DATE) </font></h3>");
			
			String strTanggalSistem = "";
			try{
				strTanggalSistem = sdf.format(newTanggalTransaksiDivision);
			} catch(Exception ex){}
			
			((DashboardUI) view.getUI()).getBtnSystemCalender().setCaption("Tanggal Sistem: " + strTanggalSistem);
			//UPDATE TRANSAKSI
			
			Notification.show("PROSES AKHIR HARI SUKSES", Notification.TYPE_TRAY_NOTIFICATION);
		} catch(Exception ex){
			Notification.show("GAGAL PROSES AKHIR HARI/DIVISION BELUM DIPILIH", Notification.TYPE_TRAY_NOTIFICATION);
			
		}
	}
	
}
