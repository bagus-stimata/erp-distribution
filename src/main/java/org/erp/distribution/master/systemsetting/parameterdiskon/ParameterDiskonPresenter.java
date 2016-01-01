package org.erp.distribution.master.systemsetting.parameterdiskon;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.erp.distribution.model.FProductgroup;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.util.ReportJdbcConfigHelper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class ParameterDiskonPresenter implements ClickListener{
	private ParameterDiskonModel model;
	private ParameterDiskonView view;

	public ParameterDiskonPresenter(ParameterDiskonModel model, ParameterDiskonView view){
		this.model = model;
		this.view = view;
		initListener();
	}
	
	public void initListener(){
		view.getBtnSave().addClickListener(this);
		view.getBtnClose().addClickListener(this);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnSave()){
			saveForm();
		} else if (event.getButton()==view.getBtnClose()){
		}
	}
	
	public void saveForm(){
		boolean isValid = true;
		 if (((String) view.getComboGrup2().getValue())==null) {
			 Notification.show("TUNAI/KREDIT BELUM DIPILIH", Notification.TYPE_TRAY_NOTIFICATION);
			 isValid = false;
		 }
		 if (isValid==true){
			try {
				
				model.getBinderFParamDiskonNota().commit();
				model.getfParamDiskonJpaService().updateObject(model.itemHeader);
				Notification.show("SIMPAN SUKSES!!", Notification.TYPE_TRAY_NOTIFICATION);
				
			} catch (CommitException e) {
				e.printStackTrace();
			}
		 }
	}
	
	
}
