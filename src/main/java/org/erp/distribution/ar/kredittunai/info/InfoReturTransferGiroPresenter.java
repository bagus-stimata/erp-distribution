package org.erp.distribution.ar.kredittunai.info;

import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window;

public class InfoReturTransferGiroPresenter implements ClickListener {
	private InfoReturTransferGiroModel model;
	private InfoReturTransferGiroView view;
	
	public InfoReturTransferGiroPresenter(InfoReturTransferGiroModel model, InfoReturTransferGiroView view){
		this.model = model;
		this.view = view;
		
		initListener();
		
	}
	public void initListener(){
		view.getBtnSaveForm().addClickListener(this);
		view.getBtnCancelForm().addClickListener(this);
		
	}
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnSaveForm()){
			try {				
				saveFormAndValidate();	
			} catch (Exception e) {
				Notification.show("Gagal Simpan!!");
			}
			
		} else if (event.getButton()==view.getBtnCancelForm()){
			view.getBinderArinvoiceRetur().discard();
			
		}

		
	}
	
	public void saveFormAndValidate(){
		
	}
	
	
	
}
