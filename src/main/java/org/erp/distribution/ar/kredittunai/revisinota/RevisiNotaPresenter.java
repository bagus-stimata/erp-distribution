package org.erp.distribution.ar.kredittunai.revisinota;

import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Window;

public class RevisiNotaPresenter implements ClickListener {
	private RevisiNotaModel model;
	private RevisiNotaView view;
	
	public RevisiNotaPresenter(RevisiNotaModel model, RevisiNotaView view){
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
			view.getBinderArinvoice().discard();
		}

		
	}
	
	public void saveFormAndValidate(){
		try {
			view.getBinderArinvoice().commit();
			model.getFtSaleshJpaService().updateObject(model.getFtSalesh());
		} catch (CommitException e) {
			Notification.show("Gagal Commit binder", Notification.TYPE_TRAY_NOTIFICATION);
		}
		
	}
	
	
	
}
