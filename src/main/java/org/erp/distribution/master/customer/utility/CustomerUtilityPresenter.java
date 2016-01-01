package org.erp.distribution.master.customer.utility;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.erp.distribution.model.FCustomergroup;
import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.util.ReportJdbcConfigHelper;
import org.vaadin.dialogs.ConfirmDialog;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class CustomerUtilityPresenter implements ClickListener{
	private CustomerUtilityModel model;
	private CustomerUtilityView view;

	public CustomerUtilityPresenter(CustomerUtilityModel model, CustomerUtilityView view){
		this.model = model;
		this.view = view;
		initListener();
	}
	
	public void initListener(){
		view.getBtnExtractToExel().addClickListener(this);
		view.getBtnPreview().addClickListener(this);
		view.getBtnClose().addClickListener(this);
		
		view.getBtnSetOpenInvoice().addClickListener(this);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnPreview()){
		} else if (event.getButton()==view.getBtnClose()){
		}else if (event.getButton()==view.getBtnExtractToExel()){
		}else if (event.getButton()==view.getBtnSetOpenInvoice()){
			setOpenInvoice();
			
		}
	}
	
	String stringCustomergroup = "%";
	public void setOpenInvoice(){
		stringCustomergroup = "%";
		try{
			FCustomergroup fCustomergroup = new FCustomergroup();
			fCustomergroup = (FCustomergroup) view.getComboCustomerGroup().getValue();
			stringCustomergroup = fCustomergroup.getId();
		} catch(Exception ex){}
		
		 ConfirmDialog commitDialog = ConfirmDialog.show(view.getUI(), "Konfirmasi Set Open Invoice", "LatnjutkanSet Open Invoice?", "Yes", "No",
	                new ConfirmDialog.Listener() {
	                    public void onClose(ConfirmDialog dialog) {
	                        if (dialog.isConfirmed()) {
	                            // Confirmed to continue
	                    		model.getfCustomerJpaService().updateCustomerOpenInvoice(stringCustomergroup,(Integer) view.getComboJumlahNota().getValue());
	                    		Notification.show("Setting Open Invoice Selesai..", Notification.TYPE_TRAY_NOTIFICATION);
	                        } else {
	                        }
	                    }
	                });	        
			 commitDialog.setStyleName("dialog");
			 commitDialog.getOkButton().setStyleName("small");
			 commitDialog.getCancelButton().setStyleName("small");
			 //Jangan lupa
			 commitDialog.focus();
		
	}
	
}
