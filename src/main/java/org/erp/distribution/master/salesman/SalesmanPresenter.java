package org.erp.distribution.master.salesman;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.vaadin.dialogs.ConfirmDialog;

import com.google.gwt.dev.js.rhino.ObjToIntMap.Iterator;
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

public class SalesmanPresenter implements ClickListener, ValueChangeListener, ItemClickListener{
	private SalesmanModel model;
	private SalesmanView view;
	
	public SalesmanPresenter(SalesmanModel model, SalesmanView view){
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
		
		//##VENDOR COVERED
		view.getBtnAddVendor().addClickListener(this);
		view.getBtnRemoveVendor().addClickListener(this);

		ItemClickListener tabelVendorCoveredDetail = new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
				Object itemId = event.getItemId();
				Item item = view.getTableVendorCoverDetail().getItem(itemId);
				boolean entitySelected = item != null;
				
				if (entitySelected) {
					model.fVendorSelected = model.getBeanItemContainerVendorCovered().getItem(itemId).getBean();
				}	
			}
		};
		view.getTableVendorCoverDetail().addItemClickListener(tabelVendorCoveredDetail);

		//##AREA COVERED
		view.getBtnAddArea().addClickListener(this);
		view.getBtnRemoveArea().addClickListener(this);
	
		ItemClickListener tabelAreaCoveredDetail = new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
				Object itemId = event.getItemId();
				Item item = view.getTableAreaCoverDetail().getItem(itemId);
				boolean entitySelected = item != null;
				
				if (entitySelected) {
					model.fAreaSelected = model.getBeanItemContainerAreaCovered().getItem(itemId).getBean();
				}	
			}
		};
		view.getTableAreaCoverDetail().addItemClickListener(tabelAreaCoveredDetail);
		
	}
	public void initListenerWindowVendorCovered(){
		view.getItemVendorCoveredView().getBtnAddAndSave().addClickListener(this);
		view.getItemVendorCoveredView().getBtnReset().addClickListener(this);
		view.getItemVendorCoveredView().getBtnClose().addClickListener(this);
		
	}
	public void initListenerWindowAreaCovered(){
		view.getItemAreaCoveredView().getBtnAddAndSave().addClickListener(this);
		view.getItemAreaCoveredView().getBtnReset().addClickListener(this);
		view.getItemAreaCoveredView().getBtnClose().addClickListener(this);
		
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
			
			model.itemHeader = new FSalesman();
			model.itemHeader = model.getBeanItemContainerHeader().getItem(itemId).getBean();
			model.getBinderHeader().setItemDataSource(model.itemHeader);
			//MASUKKAN DATA KE TABLE
			model.getBeanItemContainerVendorCovered().removeAllItems();
			model.getBeanItemContainerVendorCovered().addAll(model.itemHeader.getfVendorSet());
			model.getBeanItemContainerAreaCovered().removeAllItems();
			model.getBeanItemContainerAreaCovered().addAll(model.itemHeader.getfAreaSet());
			
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
	                    		try {			
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
			
//			discardForm(operationStatus);CECEP
		} else if (event.getButton() == view.getBtnSearch()) {
			searchForm();
		} else if (event.getButton() == view.getBtnPrint()){			
			printForm();
		}else if (event.getButton() == view.getBtnHelp()){
			helpForm();
		}else if (event.getButton() == view.getBtnAddVendor()){
			view.showWindowVendorCovered();			
			initListenerWindowVendorCovered();
			
		}else if (event.getButton() == view.getBtnRemoveVendor()){
			model.getBeanItemContainerVendorCovered().removeItem(model.fVendorSelected);
			view.setDisplayVendorCovered();
			
		}else if (event.getButton() == view.getBtnAddArea()){
			view.showWindowAreaCovered();			
			initListenerWindowAreaCovered();
			
		}else if (event.getButton() == view.getBtnRemoveArea()){
			model.getBeanItemContainerAreaCovered().removeItem(model.fAreaSelected);
			view.setDisplayAreaCovered();
			
		}else if (event.getButton() == view.getItemVendorCoveredView().getBtnAddAndSave()){
			model.getBeanItemContainerVendorCovered().addItem(view.getItemVendorCoveredModel().getItemDetil());
		}else if (event.getButton() == view.getItemVendorCoveredView().getBtnReset()){
		}else if (event.getButton() == view.getItemVendorCoveredView().getBtnClose()){
			view.closeWindowVendorCovered();
			
		}else if (event.getButton() == view.getItemAreaCoveredView().getBtnAddAndSave()){
			model.getBeanItemContainerAreaCovered().addItem(view.getItemAreaCoveredModel().getItemDetil());
		}else if (event.getButton() == view.getItemAreaCoveredView().getBtnReset()){
		}else if (event.getButton() == view.getItemAreaCoveredView().getBtnClose()){
			view.closeWindowAreaCovered();
		}
		//Tidak semua akan di refresh container nya >> Jadi refresh container tidak bisa di taruh disini
		
	}
	
	public void addItem(){
		try {
			
			//1. Deklarasikan object awal
			model.newItemHeader = new FSalesman();
			//Karena tidak pisa pake null representation
			
			model.newItemHeader.setSpcode("New");
			model.newItemHeader.setSpname("");
			model.newItemHeader.setAddress1("");
			model.newItemHeader.setCity1("");
			model.newItemHeader.setState1("");
			model.newItemHeader.setPhone("");
			model.newItemHeader.setMobile("");
			model.newItemHeader.setEmail("");
			model.newItemHeader.setJoindate(new Date());
			model.newItemHeader.setLasttrans(new Date());
			model.newItemHeader.setBornplace("");
			model.newItemHeader.setBorndate(new Date());
			model.newItemHeader.setStatusactive(true);
			
			
			
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
                    		model.getfSalesmanJpaService().removeObject(model.getItemHeader());
                    		
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
		boolean kodeValid = true;
		boolean inputValid = true;
		String theId = (String) view.getFieldId().getConvertedValue();
		
		if (theId.trim().equalsIgnoreCase("New")){
			kodeValid = false;
			Notification.show("ID tidak boleh New!!", Notification.TYPE_TRAY_NOTIFICATION);
			view.focustIdOrDesc();			
			return 1;			
		}
		
		
		
		//2. Additional Validasi input di handle oleh form Validator
		//3. Aksi Save
		if (kodeValid && inputValid) {
			try {				
				Collection itemIdsVendor = model.getBeanItemContainerVendorCovered().getItemIds();
				Set<FVendor> setFVendor = new HashSet<FVendor>();
				for (Object itemId : itemIdsVendor) {
					FVendor domain = new FVendor();
					domain = model.getBeanItemContainerVendorCovered().getItem(itemId).getBean();
					domain.setFsalesmanBean(model.newItemHeader);				
					setFVendor.add(domain);
				}
				model.newItemHeader.setfVendorSet(setFVendor);
				
				Collection itemIdsArea = model.getBeanItemContainerAreaCovered().getItemIds();
				Set<FArea> setFArea = new HashSet<FArea>();
				for (Object itemId : itemIdsArea) {
					FArea domain = new FArea();
					domain = model.getBeanItemContainerAreaCovered().getItem(itemId).getBean();
					domain.setFsalesmanBean(model.newItemHeader);
					setFArea.add(domain);
				}
				model.newItemHeader.setfAreaSet(setFArea);
				
				//3.1 commit form >> MASUKKAN KE DATABASE
				model.getBinderHeader().commit(); //COMMIT TETAP PAKE NEW TAPI CLIENT SIDE
				
				model.getfSalesmanJpaService().createObject(model.getNewItemHeader());
				
				//UPDATE MANUAL WAJIB HUKUMNYA
				for (FVendor fVendor: setFVendor) {
					FVendor domain = new FVendor();
					domain = fVendor;
					domain.setFsalesmanBean(model.newItemHeader);
					model.getfVendorJpaService().updateObject(domain);
				}
				for (FArea fArea: setFArea) {
					FArea domain = new FArea();
					domain = fArea;
					domain.setFsalesmanBean(model.newItemHeader);
					model.getfAreaJpaService().updateObject(domain);
				}
				
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
		try{
			Collection itemIdsVendor = model.getBeanItemContainerVendorCovered().getItemIds();
			Set<FVendor> setFVendor = new HashSet<FVendor>();
			for (Object itemId : itemIdsVendor) {
				FVendor domain = new FVendor();
				domain = model.getBeanItemContainerVendorCovered().getItem(itemId).getBean();
				domain.setFsalesmanBean(model.itemHeader);				
				setFVendor.add(domain);
			}
			model.itemHeader.setfVendorSet(setFVendor);
			
			Collection itemIdsArea = model.getBeanItemContainerAreaCovered().getItemIds();
			Set<FArea> setFArea = new HashSet<FArea>();
			for (Object itemId : itemIdsArea) {
				FArea domain = new FArea();
				domain = model.getBeanItemContainerAreaCovered().getItem(itemId).getBean();
				domain.setFsalesmanBean(model.itemHeader);
				setFArea.add(domain);
			}
			model.itemHeader.setfAreaSet(setFArea);
			

			//3.2 Masukkan nilai form yang valid(commit) ke dalam tabel >> dan database
			model.getBinderHeader().commit();
			
			
			model.getfSalesmanJpaService().updateObject(model.getItemHeader());
			
			//UPDATE MANUAL WAJIB HUKUMNYA
			for (FVendor fVendor: setFVendor) {
				FVendor domain = new FVendor();
				domain = fVendor;
				domain.setFsalesmanBean(model.itemHeader);
				model.getfVendorJpaService().updateObject(domain);
			}
			for (FArea fArea: setFArea) {
				FArea domain = new FArea();
				domain = fArea;
				domain.setFsalesmanBean(model.itemHeader);
				model.getfAreaJpaService().updateObject(domain);
			}
			
			
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
		Filter filter2 = new Or(new SimpleStringFilter("spcode", theId, true, false));
		Filter filter3 = new Or(new SimpleStringFilter("spname", theDesc, true, false));
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
	public SalesmanModel getModel() {
		return model;
	}
	public void setModel(SalesmanModel model) {
		this.model = model;
	}
	public SalesmanView getView() {
		return view;
	}
	public void setView(SalesmanView view) {
		this.view = view;
	}

	
	
}
