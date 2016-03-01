package org.erp.distribution.master.systemsetting.parametersistem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.erp.distribution.model.Sysvar;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Item;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Compare.Equal;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.Action;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;


public class ParameterSystemPresenter implements ClickListener, Handler, ValueChangeListener {
	private ParameterSystemModel model;
	private ParameterSystemView view;
	
	public ParameterSystemPresenter(ParameterSystemModel model, ParameterSystemView view){
		this.model = model;
		this.view = view;

		initListener();
		initDisplay();
		
	}
	
	public void initListener(){
		view.getBtnAddForm().addClickListener(this);
		view.getBtnCancelForm().addClickListener(this);
		view.getBtnDeleteForm().addClickListener(this);
		view.getBtnHelp().addClickListener(this);
		view.getBtnPrint().addClickListener(this);
		view.getBtnSaveForm().addClickListener(this);
		view.getBtnSearch().addClickListener(this);
		view.getBtnPemeliharaanParameter().addClickListener(this);

		view.getTable().addValueChangeListener(this);
		
		view.getPanelTop().addActionHandler(this);
	
		ValueChangeListener listenerComboTypeData = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				view.bindAndBuildFieldGroupComponentVisibility();
			}
		};
		view.getComboTypeData().setImmediate(true);
		view.getComboTypeData().addValueChangeListener(listenerComboTypeData);
		
	}
	
	public void initDisplay(){
		//SUDAH DIHANDLE OLEH VIEW
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
		// TODO Auto-generated method stub
		Object itemId = event.getProperty().getValue();
		Item item = view.getTable().getItem(itemId);
		boolean entitySelected = item != null;
		// modify visibility of form and delete button if an item is selected
		view.getFormLayout().setVisible(entitySelected);
		view.getBtnDeleteForm().setEnabled(entitySelected);
		
		if (entitySelected) {
			
			model.sysvar = new Sysvar();
			model.sysvar = model.getBeanItemContainerSysvar().getItem(itemId).getBean();
			model.getBinderSysvar().setItemDataSource(model.sysvar);
			
		}
		
		view.bindAndBuildFieldGroupComponent();		
		view.bindAndBuildFieldGroupComponentVisibility();
		
		model.setOperationStatus(EnumOperationStatus.EDITING.getStrCode());		
		view.setFormButtonAndTextState();
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		//Antisipasi
		if (model.getOperationStatus()==null) model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		if (model.getOperationStatus().equals("")) model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		
		if (event.getButton() == view.getBtnAddForm()) {
			
			if (model.getOperationStatus().equals(EnumOperationStatus.OPEN.getStrCode())){
				addItem();				
			}else if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
				discardForm();
			}
		} else if (event.getButton() == view.getBtnDeleteForm()) {
			deleteForm();
		} else if (event.getButton() == view.getBtnSaveForm()){
			 ConfirmDialog commitDialog = ConfirmDialog.show(view.getUI(), "Konfirmasi Simpan", "Simpan Data?", "Save", "No",
		                new ConfirmDialog.Listener() {
		                    public void onClose(ConfirmDialog dialog) {
		                        if (dialog.isConfirmed()) {
		                            // Confirmed to continue
		                    		try {			
		                    			if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
		                    				if (insertAndValidateForm()==0){		                    					
		                    					view.getTable().focus();
		                    				}else {
		                    					view.focustIdOrDesc();
		                    				}
		                    			} else if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
		                    				if (updateAndValidateForm()==0){				                    			
		                    					//refreshContainer(); //Sudah dihandle oleh method AndValidateForm()
		                    					view.getTable().focus();		                    					
		                    				}else {
		                    					view.focustIdOrDesc();
		                    				}
		                    			}
		                    		} catch(Exception ex) {
		                    			Notification.show("Terjadi Kesalahan Simpan");
		                    		}
		                        	
		                        } else {
		                        	view.focustIdOrDesc();
		                        }
		                    }
		                });	        
			 commitDialog.setStyleName("dialog");
			 commitDialog.getOkButton().setStyleName("small");
			 commitDialog.getCancelButton().setStyleName("small");
			 //Jangan lupa
			 commitDialog.focus();
			
		} else if(event.getButton() == view.getBtnCancelForm()) {
			if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
				discardForm();
			} else if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode()) || 
					model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
				if (view.getFormLayout().isVisible()){
					discardForm();
				}				
			}else{	
				view.getTable().focus();
			}		
			
//			discardForm(operationStatus);
		} else if (event.getButton() == view.getBtnSearch()) {
			searchForm();
		} else if (event.getButton() == view.getBtnPrint()){			
			printForm();
		}else if (event.getButton() == view.getBtnHelp()){
			helpForm();
		}else if (event.getButton() == view.getBtnPemeliharaanParameter()){
			 ConfirmDialog commitDialog = ConfirmDialog.show(view.getUI(), "Pemeliharaan parameter", 
					 "Yakin Lanjutkan Pemeliharaan Parameter System?", "Oke", "Cancel",
		                new ConfirmDialog.Listener() {
		                    public void onClose(ConfirmDialog dialog) {
		                        if (dialog.isConfirmed()) {
		                            // Confirmed to continue
		                        	model.getSysvarHelper().defaultConfigSysvar();
		                        	model.reload();
		                        	view.getTable().refreshRowCache();
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
		//Tidak semua akan di refresh container nya >> Jadi refresh container tidak bisa di taruh disini
		
	}

	
	public void addItem(){
		try {
			
			//1. Deklarasikan object awal
			model.newSysvar = new Sysvar();
			
//			sysvarPK.setDivision("");		
			model.newSysvar.setId("New");
			
//			model.newSysvar.setIdSysvar("New");
			model.newSysvar.setGroupSysvar("");
			
			model.newSysvar.setDeskripsi("");
			model.newSysvar.setNotes("");
			model.newSysvar.setLenghtData(1);
			model.newSysvar.setPrefix("");
			model.newSysvar.setTipeData("STRING1");
			
			model.newSysvar.setNilaiString1("");
			model.newSysvar.setNilaiString2("");
			
			model.newSysvar.setNilaiBol1(false);			
			model.newSysvar.setNilaiBol2(false);
			model.newSysvar.setNilaiInt1(0);
			model.newSysvar.setNilaiInt2(0);
			model.newSysvar.setNilaiDouble1(0.0);
			
			//2. SET NEW DATA ITEM TO BINDER
			model.getBinderSysvar().setItemDataSource(model.newSysvar);
			
			//3. REFRESH VIEW AND SHOW FORM LAYOUT
			view.bindAndBuildFieldGroupComponent();
			view.bindAndBuildFieldGroupComponentVisibility();
			
			view.getFormLayout().setVisible(true);
			
			//3.SET FORM STATE AND BUTTON STATE
			model.setOperationStatus(EnumOperationStatus.ADDING.getStrCode());
			view.setFormButtonAndTextState();
			
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	public int discardForm(){
		 ConfirmDialog discardDialog = ConfirmDialog.show(view.getUI(),"Konfirmasi Discard", 
				 "Yakin keluar mode editing(discard)?", "Yes", "No",
	                new ConfirmDialog.Listener() {
	                    public void onClose(ConfirmDialog dialog) {
	                        if (dialog.isConfirmed()) {
	                            // Confirmed to continue
	                    		try {			
	                    			//1. form discard
	                    			model.getBinderSysvar().discard();
	                    			model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
	                    			view.setFormButtonAndTextState();
	                    			//2. focust ke table
	                    			view.getTable().focus();
	                    			
	                    		} catch(Exception ex) {
	                    			Notification.show("Terjadi Kesalahan Discard");
	                    		}
	                        	
	                        } else {
	                        	view.focustIdOrDesc();
	                         
	                        }
	                    }
	                });	        
		 discardDialog.setStyleName("dialog");
		 discardDialog.getOkButton().setStyleName("small");
		 discardDialog.getCancelButton().setStyleName("small");
		 //Jangan lupa
		 discardDialog.focus();
		
		return 0;
		
	}
	public void deleteForm(){
		final Object itemId = view.getTable().getValue();
		
		if (itemId != null){			
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        // Confirmed to continue
                    	try {
                    		Sysvar sysvar = new Sysvar();
                    		sysvar = model.getBeanItemContainerSysvar().getItem(itemId).getBean();
                    		model.getSysvarService().removeObject(sysvar);
                    		
                    		model.getBeanItemContainerSysvar().removeItem(itemId);
                    		view.getTable().refreshRowCache();
                    		view.setDisplay();
                			Notification.show("Delete Sukses");		                        		
                    	} catch(Exception ex) {
                			Notification.show("Error Delete!!");		                        		
                    	}
                    } else {
                    // User did not confirm
                    }
					view.getTable().focus();
				}
			};
			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi Hapus", "Yakin akan hapus data?", 
					 "Oke Delete..", "Cancel", konfirmDialogListener);
			 
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
		
		}else {
			Notification.show("Tidak ada yang dipilih!!!");
		}
	
		
	}

	public int insertAndValidateForm(){
		boolean kodeValid = true;
		boolean inputValid = true;
		String theId = (String) view.getFieldIdSysvar().getConvertedValue();
//		String strDivision = "";
//		Division div = new Division();		
//		try{
//			div = model.getBeanItemContainerDivision().getItem(view.getComboDivision().getValue()).getBean();
//			strDivision = div.getId();
//		} catch(Exception ex){}
		
		//1. Validasi Kode
		if (theId.equalsIgnoreCase("New")) {
			kodeValid = false;
			Notification.show("Kode Tidak Boleh Memakai New");
			view.focustIdOrDesc();
			return 1;
		}
		
//		if (view.getComboDivision().getValue()==null){
//			kodeValid = false;
//			Notification.show("DIVISI TIDAK BOLEH KOSONG");
//			view.focustIdOrDesc();
//			return 1;			
//		}
		
//		if (model.getSysvarService().findAllById(theId, strDivision).size() > 0){
//			kodeValid = false;
//			Notification.show("Kode Sudah Terpakai!!.");	
//			view.focustIdOrDesc();
//			return 1;
//			
//		}
		

		//VALIDASI KODE TIDAK DIPERLUKAN LAGI 
		
		//2. Additional Validasi input di handle oleh form Validator
		//3. Aksi Save
		if (kodeValid && inputValid) {
			try {
				
				//3.1 commit form >> MASUKKAN KE DATABASE
				model.getBinderSysvar().commit(); //COMMIT TETAP PAKE NEW TAPI CLIENT SIDE
				//REFINE ID
				
				//KITA PAKE KODE MANUAL
//				model.getNewSysvar().setRefno(model.getTransaksiHelper().getNewNomorUrutBukuGiro());
				
				model.getSysvarService().createObject(model.getNewSysvar());
				
				//3.2 Masukkan nilai form yang valid(commit) ke dalam tabel
				model.getBeanItemContainerSysvar().addItem(model.getNewSysvar());
				view.getTable().refreshRowCache();
				
				//3.3. atur kondisi status form
				model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
				view.setFormButtonAndTextState();
				//3.4. Refresh lagi container >> SUDAH TIDAK DIPERLUKAN LAGI OLEH KARENA OPERSI //3.2
				
				Notification.show("UPDATE Sukses!!!", Notification.TYPE_TRAY_NOTIFICATION);
				try{
					view.getTable().focus();				
				} catch(Exception ex){}
			} catch(Exception ex) {
				Notification.show("Terjadi Kesalahan Insert data!!!", Notification.TYPE_TRAY_NOTIFICATION);
				
				ex.printStackTrace();
				
				view.focustIdOrDesc();
				return 1;
			}
		} else {
			return 2;
		}
		
		return 0;
	}
	
	public int updateAndValidateForm(){
		try{
			//3.2 Masukkan nilai form yang valid(commit) ke dalam tabel >> dan database
			model.getBinderSysvar().commit();
			model.getSysvarService().updateObject(model.getSysvar());
			
			view.getTable().refreshRowCache();
			
			//3.3. atur kondisi status form
			model.setOperationStatus(EnumOperationStatus.EDITING.getStrCode());
			view.setFormButtonAndTextState();
			//3.4. Refresh lagi container >> SUDAH TIDAK DIPERLUKAN LAGI OLEH KARENA OPERSI //3.2
			
			Notification.show("Update Sukses!!!", Notification.TYPE_TRAY_NOTIFICATION);
			try{
				view.getTable().focus();				
			} catch(Exception ex){}
			
		} catch(Exception ex){
			Notification.show("Error Update!!!", Notification.TYPE_TRAY_NOTIFICATION);
			view.focustIdOrDesc();
			return 1;
		}
		return 0;
	}
	
	public int searchForm(){
		//1. Remove filter dan Refresh container dalulu dahulu
		model.getBeanItemContainerSysvar().removeAllContainerFilters();
		
		//2. Baru Kasih Filter Lagi
		String theDescription = view.getFieldSearchByDescription().getValue().toString().trim();
		String theGroup = view.getFieldSearchByGroup().getValue().toString().trim();
//		Division div = new Division();
//		String theDivision = "";
//		try{
//			div = model.getBeanItemContainerDivision().getItem(view.getComboSearchByDivision().getValue()).getBean();
//			theDivision = div.getId();		
//		} catch(Exception ex){}
		
//		Filter filter1 = new SimpleStringFilter("SysvarPK.idSysvar", theId, true, false);
//		Filter filter2 = new Or(new SimpleStringFilter("groupSysvar", theGroup, true, false));
		Filter filter3 = new Or(new SimpleStringFilter("deskripsi", theDescription, true, false));
//		model.getBeanItemContainerSysvar().addContainerFilter(filter1);
//		model.getBeanItemContainerSysvar().addContainerFilter(filter2);
		model.getBeanItemContainerSysvar().addContainerFilter(filter3);
		
//		if (! theDivision.trim().equals("")){			
//			Filter filter4 = new Or(new Compare.Equal("divisionBean", div));
//			model.getBeanItemContainerSysvar().addContainerFilter(filter4);
//		}
		//3. Refresh TABLE
		view.getTable().refreshRowCache();
		//4. Focus KE TABLE
		view.getTable().focus();
		
		return 0;
		
	}
	
	public void printForm(){
		
	}
	
	public void helpForm(){
		
	}
	
	private static final ShortcutAction ENTER_FIELDSEARCHID= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	
	private static final Action[] ACTIONS = new Action[] {};
	private static final Action[] ACTIONS_FIELDSEARCHID = new Action[] { ENTER_FIELDSEARCHID };
	
	@Override
	public Action[] getActions(Object target, Object sender) {
		if (sender == view.getPanelTop()) {
			return ACTIONS_FIELDSEARCHID;
		}
		return ACTIONS;
	}

	@Override
	public void handleAction(Action action, Object sender, Object target) {
		if (action==ENTER_FIELDSEARCHID){
			view.getBtnSearch().click();
		}		
	}

	
	

}
