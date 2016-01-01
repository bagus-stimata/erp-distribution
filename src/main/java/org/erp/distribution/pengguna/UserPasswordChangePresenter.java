package org.erp.distribution.pengguna;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class UserPasswordChangePresenter implements ClickListener{
	private UserPasswordChangeModel model;
	private UserPasswordChangeView view;
	public UserPasswordChangePresenter(UserPasswordChangeModel model, UserPasswordChangeView view){
		this.model=model;
		this.view = view;
		initListener();
		
	}

	public void initListener(){
		
		view.getBtnSaveForm().addClickListener(this);
		view.getBtnPrint().addClickListener(this);
		view.getBtnClose().addClickListener(this);
		
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		
		if (event.getButton() == view.getBtnPrint()) {
		}else if (event.getButton() == view.getBtnSaveForm()){
			updateForm();			
		}else if (event.getButton() == view.getBtnClose()){
		}
	} 
	
	public void updateForm(){
		boolean inputValid = true;
		//VALIDASI USER 
		String oldPassword = view.getFieldOldPassword().getValue();
		if (oldPassword.equals(null)) {
			oldPassword = "";
			inputValid = false;
		}

		String newPassword = view.getFieldNewPassword().getValue();
		if (newPassword.equals(null)) {
			newPassword = "";
			inputValid = false;
		}
		
		String retypePassword = view.getFieldRetypePassword().getValue();
		if (retypePassword.equals(null)) {
			retypePassword = "";
			inputValid = false;
		}
		
		
		if (! newPassword.trim().equals(retypePassword.trim())) {
			Notification.show("Password baru dan tulis ulang password tidak match!", Notification.TYPE_TRAY_NOTIFICATION);
			inputValid = false;
		}else if (newPassword.trim().length() < 3){
			Notification.show("Panjang password harus lebih besar dari 3 karakter!", Notification.TYPE_TRAY_NOTIFICATION);
			inputValid = false;			
		} else if (! oldPassword.equals(model.getItemHeader().getUserPassword())){
			Notification.show("Password lama salah!", Notification.TYPE_TRAY_NOTIFICATION);
			inputValid = false;			
			
		}
		
		if (inputValid){
			try {
				model.getBinderHeader().commit();
				model.getItemHeader().setUserPassword(newPassword);
				model.getUserService().updateObject(model.getItemHeader());
				
				Notification.show("User Password Sukses Update!", Notification.TYPE_TRAY_NOTIFICATION);				
			} catch (CommitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
}
