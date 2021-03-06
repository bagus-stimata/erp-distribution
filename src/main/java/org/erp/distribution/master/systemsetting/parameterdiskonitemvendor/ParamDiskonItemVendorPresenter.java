package org.erp.distribution.master.systemsetting.parameterdiskonitemvendor;

import java.util.Date;

import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomersubgroup;
import org.erp.distribution.model.FParamDiskonItem;
import org.erp.distribution.model.FParamDiskonItemVendor;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class ParamDiskonItemVendorPresenter implements ClickListener, ValueChangeListener, ItemClickListener{
	private ParamDiskonItemVendorModel model;
	private ParamDiskonItemVendorView view;
	
	public ParamDiskonItemVendorPresenter(ParamDiskonItemVendorModel model, ParamDiskonItemVendorView view){
		this.model = model;
		this.view = view;
		
		initListener();
	}
	
	public void initListener(){
		view.getBtnNewForm().addClickListener(this);
		view.getBtnEditForm().addClickListener(this);
		view.getBtnCancelForm().addClickListener(this);
		view.getBtnDeleteForm().addClickListener(this);
		view.getBtnHelp().addClickListener(this);
		view.getBtnPrint().addClickListener(this);
		view.getBtnSaveForm().addClickListener(this);
		view.getBtnSearch().addClickListener(this);

		view.getTable().addItemClickListener(this);
	}
	

	@Override
	public void valueChange(ValueChangeEvent event) {
		
		
		
	}
	@Override
	public void itemClick(ItemClickEvent event) {
		Object itemId = event.getItemId();
		Item item = view.getTable().getItem(itemId);
		boolean entitySelected = item != null;
		// modify visibility of form and delete button if an item is selected
		view.getFormLayout().setVisible(entitySelected);
		view.getBtnDeleteForm().setEnabled(entitySelected);
		
		if (entitySelected) {
			
			model.itemHeader = new FParamDiskonItemVendor();
			model.itemHeader = model.getBeanItemContainerHeader().getItem(itemId).getBean();
			model.getBinderHeader().setItemDataSource(model.itemHeader);
			
			if (event.isDoubleClick()){
				view.showWindowForm();
			}
		}
		
		view.bindAndBuildFieldGroupComponent();		
		model.setOperationStatus(EnumOperationStatus.EDITING.getStrCode());		
		view.setFormButtonAndTextState();
		
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		// TODO Auto-generated method stub
		//Antisipasi
		if (model.getOperationStatus()==null) model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		if (model.getOperationStatus().equals("")) model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		
		if (event.getButton() == view.getBtnNewForm()) {
			addItem();				
			view.showWindowForm();
		} else if (event.getButton() == view.getBtnEditForm()) {
			view.showWindowForm();
		} else if (event.getButton() == view.getBtnDeleteForm()) {
			deleteForm();
		} else if (event.getButton() == view.getBtnSaveForm()){
			 ConfirmDialog commitDialog = ConfirmDialog.show(view.getUI(), "Konfirmasi Simpan", "Simpan Data?", "Save", "No",
	                new ConfirmDialog.Listener() {
	                    public void onClose(ConfirmDialog dialog) {
	                        if (dialog.isConfirmed()) {
	                            // Confirmed to continue
	                    			if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
	                    				if (insertAndValidateForm()==0){		                    					
	                    					view.getTable().focus();
	        	                        	view.closeWindowForm();
	                    				}else {
	                    					view.focustIdOrDesc();
	                    				}
	                    			} else if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
	                    				if (updateAndValidateForm()==0){				                    			
	                    					view.getTable().focus();		                    					
	        	                        	view.closeWindowForm();
	                    				}else {
	                    					view.focustIdOrDesc();
	                    				}
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
		}
		//Tidak semua akan di refresh container nya >> Jadi refresh container tidak bisa di taruh disini
		
	}
	
	public void addItem(){
		try {
			
			//1. Deklarasikan object awal
			model.newItemHeader = new FParamDiskonItemVendor();
			//Karena tidak pisa pake null representation
			
			model.newItemHeader.setId((long) 0);
			
			model.newItemHeader.setAllvendor(false);
			model.newItemHeader.setStatusActive(true);
			
			model.newItemHeader.setDiskon1(0.0);
			model.newItemHeader.setDiskon1plus(0.0);
			model.newItemHeader.setDiskon2(0.0);
			model.newItemHeader.setDiskon2plus(0.0);
			model.newItemHeader.setDiskon3(0.0);
			model.newItemHeader.setDiskon3plus(0.0);
			model.newItemHeader.setDiskon4(0.0);
			model.newItemHeader.setDiskon4plus(0.0);
			model.newItemHeader.setDiskon5(0.0);
			model.newItemHeader.setDiskon5plus(0.0);
			
			model.newItemHeader.setNominal1(0.0);
			model.newItemHeader.setNominal2(0.0);
			model.newItemHeader.setNominal3(0.0);
			model.newItemHeader.setNominal4(0.0);
			model.newItemHeader.setNominal5(0.0);
			
			//2. SET NEW DATA ITEM TO BINDER
			model.getBinderHeader().setItemDataSource(model.newItemHeader);
			
			//3. REFRESH VIEW AND SHOW FORM LAYOUT
			view.bindAndBuildFieldGroupComponent();			
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
	                    			model.getBinderHeader().discard();
	                    			model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
	                    			view.setFormButtonAndTextState();
	                    			//2. focust ke table
	                    			view.getTable().focus();
	                    			
	                    		} catch(Exception ex) {
	                    			Notification.show("Terjadi Kesalahan Discard", Notification.TYPE_TRAY_NOTIFICATION);
	                    		}
	                        	view.closeWindowForm();
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
                    		model.getfParamDiskonItemVendorJpaService().removeObject(model.getItemHeader());
                    		
                    		model.getBeanItemContainerHeader().removeItem(itemId);
                    		
                    		view.getTable().refreshRowCache();
                    		view.setDisplay();
                			Notification.show("Delete Sukses", Notification.TYPE_TRAY_NOTIFICATION);		                        		
                    	} catch(Exception ex) {
                			Notification.show("Error Delete!!", Notification.TYPE_TRAY_NOTIFICATION);		                        		
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
			Notification.show("Tidak ada yang dipilih!!!", Notification.TYPE_TRAY_NOTIFICATION);
		}
	}

	public int insertAndValidateForm(){
		if (model.newItemHeader.getDiskon1()==null)
			model.newItemHeader.setDiskon1(0.0);
		if (model.newItemHeader.getDiskon1plus()==null)
			model.newItemHeader.setDiskon1plus(0.0);
		if (model.newItemHeader.getDiskon2()==null)
			model.newItemHeader.setDiskon2(0.0);
		if (model.newItemHeader.getDiskon2plus()==null)
			model.newItemHeader.setDiskon2plus(0.0);
		if (model.newItemHeader.getDiskon3()==null)
			model.newItemHeader.setDiskon3(0.0);
		if (model.newItemHeader.getDiskon3plus()==null)
			model.newItemHeader.setDiskon3plus(0.0);
		if (model.newItemHeader.getDiskon4()==null)
			model.newItemHeader.setDiskon4(0.0);
		if (model.newItemHeader.getDiskon4plus()==null)
			model.newItemHeader.setDiskon4plus(0.0);
		if (model.newItemHeader.getDiskon5()==null)
			model.newItemHeader.setDiskon5(0.0);
		if (model.newItemHeader.getDiskon5plus()==null)
			model.newItemHeader.setDiskon5plus(0.0);
		
		if (model.newItemHeader.getNominal1()==null)
			model.newItemHeader.setNominal1(0.0);
		if (model.newItemHeader.getNominal2()==null)
			model.newItemHeader.setNominal2(0.0);
		if (model.newItemHeader.getNominal3()==null)
			model.newItemHeader.setNominal3(0.0);
		if (model.newItemHeader.getNominal4()==null)
			model.newItemHeader.setNominal4(0.0);
		if (model.newItemHeader.getNominal5()==null)
			model.newItemHeader.setNominal5(0.0);

		
		boolean kodeValid = true;
		boolean inputValid = true;

//		String theId = (String) view.getFieldId().getConvertedValue();
//		
//		if (theId.trim().equalsIgnoreCase("New")){
//			kodeValid = false;
//			Notification.show("ID tidak boleh New!!", Notification.TYPE_TRAY_NOTIFICATION);
//			view.focustIdOrDesc();			
//			return 1;			
//		}
		
		
//		//VALIDASI KODE TIDAK DIPERLUKAN LAGI 
//		if (model.getfAreaJpaService().findAll(theId).size()>0){
//			kodeValid = false;
//			Notification.show("Kode Sudah Terpakai!!.");	
//			view.focustIdOrDesc();
//			return 1;			
//		}
		
		//2. Additional Validasi input di handle oleh form Validator
		//3. Aksi Save
		if (kodeValid && inputValid) {
			try {				
				//3.1 commit form >> MASUKKAN KE DATABASE
				model.getBinderHeader().commit(); //COMMIT TETAP PAKE NEW TAPI CLIENT SIDE
				
				model.getfParamDiskonItemVendorJpaService().createObject(model.getNewItemHeader());
				
				//3.2 Masukkan nilai form yang valid(commit) ke dalam tabel
				model.getBeanItemContainerHeader().addItem(model.getNewItemHeader());
				view.getTable().refreshRowCache();
				
				//3.3. atur kondisi status form
				view.setDisplay();
				model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
				view.setFormButtonAndTextState();
				//3.4. Refresh lagi container >> SUDAH TIDAK DIPERLUKAN LAGI OLEH KARENA OPERSI //3.2
				
				Notification.show("Penambahan Sukses!!!", Notification.TYPE_TRAY_NOTIFICATION);
				try{
					view.getTable().focus();				
				} catch(Exception ex){}
				
			} catch(Exception ex) {
				Notification.show("Terjadi Kesalahan Insert data!!!", Notification.TYPE_TRAY_NOTIFICATION);
				view.focustIdOrDesc();
				ex.printStackTrace();
				return 1;
			}
		} else {
			return 2;
		}
		
		return 0;
	}
	
	public int updateAndValidateForm(){
		
		if (model.itemHeader.getDiskon1()==null)
			model.itemHeader.setDiskon1(0.0);
		if (model.itemHeader.getDiskon1plus()==null)
			model.itemHeader.setDiskon1plus(0.0);
		if (model.itemHeader.getDiskon2()==null)
			model.itemHeader.setDiskon2(0.0);
		if (model.itemHeader.getDiskon2plus()==null)
			model.itemHeader.setDiskon2plus(0.0);
		if (model.itemHeader.getDiskon3()==null)
			model.itemHeader.setDiskon3(0.0);
		if (model.itemHeader.getDiskon3plus()==null)
			model.itemHeader.setDiskon3plus(0.0);
		if (model.itemHeader.getDiskon4()==null)
			model.itemHeader.setDiskon4(0.0);
		if (model.itemHeader.getDiskon4plus()==null)
			model.itemHeader.setDiskon4plus(0.0);
		if (model.itemHeader.getDiskon5()==null)
			model.itemHeader.setDiskon5(0.0);
		if (model.itemHeader.getDiskon5plus()==null)
			model.itemHeader.setDiskon5plus(0.0);
		
		if (model.itemHeader.getNominal1()==null)
			model.itemHeader.setNominal1(0.0);
		if (model.itemHeader.getNominal2()==null)
			model.itemHeader.setNominal2(0.0);
		if (model.itemHeader.getNominal3()==null)
			model.itemHeader.setNominal3(0.0);
		if (model.itemHeader.getNominal4()==null)
			model.itemHeader.setNominal4(0.0);
		if (model.itemHeader.getNominal5()==null)
			model.itemHeader.setNominal5(0.0);

		try{
			//3.2 Masukkan nilai form yang valid(commit) ke dalam tabel >> dan database
			model.getBinderHeader().commit();
			model.getfParamDiskonItemVendorJpaService().updateObject(model.getItemHeader());
			
			view.getTable().refreshRowCache();
			
			//3.3. atur kondisi status form
			view.setDisplay();
			model.setOperationStatus(EnumOperationStatus.EDITING.getStrCode());
			view.setFormButtonAndTextState();
			//3.4. Refresh lagi container >> SUDAH TIDAK DIPERLUKAN LAGI OLEH KARENA OPERSI //3.2
			
			Notification.show("UPDATE Sukses!!!", Notification.TYPE_TRAY_NOTIFICATION);
			try{
				view.getTable().focus();				
			} catch(Exception ex){}
			
		} catch(Exception ex){
			view.focustIdOrDesc();
			return 1;
		}
		return 0;
	}
	
	public int searchForm(){
		//1. Remove filter dan Refresh container dalulu dahulu
		model.reload();
		model.getBeanItemContainerHeader().removeAllContainerFilters();
		
		//2. Baru Kasih Filter Lagi
//		String theRefno = view.getFieldSearchByRefno().getValue().toString().trim();
		String theId = view.getFieldSearchById().getValue().toString().trim();
		String theDesc = view.getFieldSearchByDesc().getValue().toString().trim();
		
		
//		Filter filter1 = new SimpleStringFilter("refno", theRefno, true, false);
		Filter filter2 = new Or(new SimpleStringFilter("id", theId, true, false));
		Filter filter3 = new Or(new SimpleStringFilter("description", theDesc, true, false));
//		model.getBeanItemContainerBukugiro().addContainerFilter(filter1);
		model.getBeanItemContainerHeader().addContainerFilter(filter2);
		model.getBeanItemContainerHeader().addContainerFilter(filter3);
		
		//3. Refresh TABLE
		view.setDisplay();
		view.getTable().refreshRowCache();
		//4. Focus KE TABLE
		view.getTable().focus();
		
		return 0;
		
	}
	
	public void printForm(){
		
	}
	
	public void helpForm(){
	}
	public ParamDiskonItemVendorModel getModel() {
		return model;
	}
	public void setModel(ParamDiskonItemVendorModel model) {
		this.model = model;
	}
	public ParamDiskonItemVendorView getView() {
		return view;
	}
	public void setView(ParamDiskonItemVendorView view) {
		this.view = view;
	}

	
	
}
