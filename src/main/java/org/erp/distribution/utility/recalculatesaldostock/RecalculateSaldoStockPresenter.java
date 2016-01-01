package org.erp.distribution.utility.recalculatesaldostock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FWarehouse;
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

public class RecalculateSaldoStockPresenter implements ClickListener{
	private RecalculateSaldoStockModel model;
	private RecalculateSaldoStockView view;

	public RecalculateSaldoStockPresenter(RecalculateSaldoStockModel model, RecalculateSaldoStockView view){
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
            			model.getProductAndStockHelper().recalculateSaldoStock(null, view.getDateField1From().getValue(), view.getDateField1To().getValue());
            			Notification.show("PERBAIKAN STOCK SELESAI!", Notification.TYPE_TRAY_NOTIFICATION);
                    } else {
                    // User did not confirm
                    }
				}
			};
			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Perbaikan", "Perbaikan stok akan melakukan perhitungan ulang! Lanjutkan proses kalkulasi?", 
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
	
	
	
}
