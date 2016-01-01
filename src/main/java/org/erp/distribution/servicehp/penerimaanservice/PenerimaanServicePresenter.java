package org.erp.distribution.servicehp.penerimaanservice;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.SCustomer;
import org.erp.distribution.model.SMerk;
import org.erp.distribution.model.STeknisi;
import org.erp.distribution.model.StService;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.model.modelenum.EnumStatusService;
import org.erp.distribution.util.ReportJdbcConfigHelper;
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
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinService;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class PenerimaanServicePresenter implements ClickListener, ValueChangeListener, ItemClickListener{
	private PenerimaanServiceModel model;
	private PenerimaanServiceView view;
	
	public PenerimaanServicePresenter(PenerimaanServiceModel model, PenerimaanServiceView view){
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
		view.getBtnPrintPenerimaan().addClickListener(this);
		view.getBtnPrintPengambilan().addClickListener(this);
		
		view.getBtnSaveForm().addClickListener(this);
		view.getBtnSearch().addClickListener(this);
		
		view.getBtnUtility().addClickListener(this);

		view.getBtnAddCustomer().addClickListener(this);
		view.getBtnSaveFormCustomer().addClickListener(this);
		view.getBtnCancelFormCustomer().addClickListener(this);
		
		
		//LISTENER CHECK BOX
		initListenerCheckBox();

		view.getTable().addItemClickListener(this);
	}
	

	public void initListenerCheckBox(){
		final String textContent = view.getFieldKelengkapan().getValue();
		
		ValueChangeListener listenerCheckKelBattery = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				try{
					if (view.getCheckKelBattery().getValue()==false){
						textContent.replace(" BATTERY", "");
						view.getFieldKelengkapan().setValue(textContent);
					}else {
						if (! view.getFieldKelengkapan().getValue().contains("BATTERY")) {
							view.getFieldKelengkapan().setValue(view.getFieldKelengkapan().getValue() + " BATTERY");
						}
					}
				} catch(Exception ex){}
			}
		};
		ValueChangeListener listenerCheckKelCharger = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				try{
					if (view.getCheckKelCharger().getValue()==false){
						textContent.replace(" CHARGER", "");
						view.getFieldKelengkapan().setValue(textContent);
					}else {
						if (! view.getFieldKelengkapan().getValue().contains("CHARGER")) {
							view.getFieldKelengkapan().setValue(view.getFieldKelengkapan().getValue() + " CHARGER");
						}
					}
				} catch(Exception ex){}
			}
		};
		ValueChangeListener listenerCheckKelDosbox = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				try{
					if (view.getCheckKelDosbox().getValue()==false){
						textContent.replace(" DOSBOX", "");
						view.getFieldKelengkapan().setValue(textContent);
					}else {
						if (! view.getFieldKelengkapan().getValue().contains("DOSBOX")) {
							view.getFieldKelengkapan().setValue(view.getFieldKelengkapan().getValue() + " DOSBOX");
						}
					}
				} catch(Exception ex){}
			}
		};
		ValueChangeListener listenerCheckKelLain = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				try{
					if (view.getCheckKelLain().getValue()==false){
						textContent.replace(" LAIN-LAIN", "");
						view.getFieldKelengkapan().setValue(textContent);
					}else {
						if (! view.getFieldKelengkapan().getValue().contains("LAIN-LAIN")) {
							view.getFieldKelengkapan().setValue(view.getFieldKelengkapan().getValue() + " LAIN-LAIN");
						}
					}
				} catch(Exception ex){}
			}
		};
		ValueChangeListener listenerKelMemorycard = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				try{
					if (view.getCheckKelMemorycard().getValue()==false){
						textContent.replace(" MEMORY CARD", "");
						view.getFieldKelengkapan().setValue(textContent);
					}else {
						if (! view.getFieldKelengkapan().getValue().contains("MEMORY CARD")) {
							view.getFieldKelengkapan().setValue(view.getFieldKelengkapan().getValue() + " MEMORY CARD");
						}
					}
				} catch(Exception ex){}
			}
		};
		ValueChangeListener listenerCheckKelSimcard = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				try{
					if (view.getCheckKelSimcard().getValue()==false){
						textContent.replace(" SIM CARD", "");
						view.getFieldKelengkapan().setValue(textContent);
					}else {
						if (! view.getFieldKelengkapan().getValue().contains("SIM CARD")) {
								view.getFieldKelengkapan().setValue(view.getFieldKelengkapan().getValue() + " SIM CARD");
						}
						
					}
				} catch(Exception ex){}
			}
		};
		view.getCheckKelBattery().addValueChangeListener(listenerCheckKelBattery);
		view.getCheckKelCharger().addValueChangeListener(listenerCheckKelCharger);
		view.getCheckKelDosbox().addValueChangeListener(listenerCheckKelDosbox);
		view.getCheckKelLain().addValueChangeListener(listenerCheckKelLain);
		view.getCheckKelMemorycard().addValueChangeListener(listenerKelMemorycard);
		view.getCheckKelSimcard().addValueChangeListener(listenerCheckKelSimcard);
		
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
			
			model.itemHeader = new StService();
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
	                    		try {			
	                    			if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
	                    				if (insertAndValidateForm()==0){		                    					
	        	                        	view.closeWindowForm();
	                    					view.getTable().focus();
	                    					
	                    					model.itemHeader = new StService();
	                    					model.itemHeader = model.newItemHeader;
	                    					model.getBinderHeader().setItemDataSource(model.itemHeader);
	                    					
                    						view.showWindowForm();
	                    					
	                    				}else {
	                    					view.focustIdOrDesc();
	                    				}
	                    			} else if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
	                    				if (updateAndValidateForm()==0){				                    			
	                    					view.getTable().focus();		                    					
//	        	                        	view.closeWindowForm();
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
			discardForm();
		} else if (event.getButton() == view.getBtnSearch()) {
			searchForm();
		} else if (event.getButton() == view.getBtnPrint()){		
			try{
//				printForm();
			} catch(Exception ex){}
			
		} else if (event.getButton() == view.getBtnPrintPenerimaan()){		
			try{
				try{
					model.getItemHeader().setPrintPenerimaanCounter(model.getItemHeader().getPrintPenerimaanCounter()+1);
					model.getStServiceJpaService().updateObject(model.getItemHeader());
				} catch(Exception ex){}
				printForm(1);
				printForm(2);
				System.out.println("REFNO: " + model.getItemHeader().getRefno());
			} catch(Exception ex){}
		} else if (event.getButton() == view.getBtnPrintPengambilan()){		
			try{
				try{
					model.getItemHeader().setPrintPengambilanCounter(model.getItemHeader().getPrintPengambilanCounter()+1);
					model.getStServiceJpaService().updateObject(model.getItemHeader());
				} catch(Exception ex){}
				printForm(3);
			} catch(Exception ex){}
		}else if (event.getButton() == view.getBtnHelp()){
			helpForm();
		}else if (event.getButton() == view.getBtnUtility()){
		}else if (event.getButton() == view.getBtnAddCustomer()){
			addItemCustomer();
			view.showWindowCustomer();
		}else if (event.getButton() == view.getBtnSaveFormCustomer()){
			insertAndValidateFormCustomer();
			view.closeWindowCustomer();
		}else if (event.getButton() == view.getBtnCancelFormCustomer()){
			view.closeWindowCustomer();
		}
		//Tidak semua akan di refresh container nya >> Jadi refresh container tidak bisa di taruh disini
		
	}
	
	public void addItem(){
		try {
			
			//1. Deklarasikan object awal
			model.newItemHeader = new StService();
			//Karena tidak pisa pake null representation
			model.newItemHeader.setNoreg("New");
			model.newItemHeader.setNojob("New");
			model.newItemHeader.setImei("");
			model.newItemHeader.setPin("");
			model.newItemHeader.setTipeHp("");
			model.newItemHeader.setTelahDiambil(false);
			model.newItemHeader.setYangMenyerahkan("");
			model.newItemHeader.setUangMuka(0.0);
			model.newItemHeader.setBiaya(0.0);
			model.newItemHeader.setBiayaSparePart(0.0);
			model.newItemHeader.setStatusService(0);
			model.newItemHeader.setLamaGaransi(0);
			model.newItemHeader.setTanggalmasuk(new Date());
			model.newItemHeader.setTanggalpengambilan(new Date());
			model.newItemHeader.setUangMuka(0.0);
			
			model.newItemHeader.setPrintPenerimaanCounter(0);
			model.newItemHeader.setPrintPengambilanCounter(0);
			
			model.newItemHeader.setStatusSelesaiService(false);
			
			model.newItemHeader.setStatusService(EnumStatusService.BELUM.getIntCode());
			view.getComboStatusService().select(EnumStatusService.BELUM.getIntCode());
			
			model.newItemHeader.setKelengkapan("");
			model.newItemHeader.setKeluhan("");
			
			model.newItemHeader.setAnalisaKerusakan("");
			
//			model.newItemHeader.setsCustomerBean(new SCustomer());
//			model.newItemHeader.setsMerkBean(new SMerk());
//			model.newItemHeader.setsTeknisiBean(new STeknisi());
			
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
	public void addItemCustomer(){
		try {
			
			//1. Deklarasikan object awal
			model.newItemHeaderCustomer = new SCustomer();
			//Karena tidak pisa pake null representation
			
			model.newItemHeaderCustomer.setCustno("New");
			model.newItemHeaderCustomer.setCustname("");
			model.newItemHeaderCustomer.setAddress1("");
			model.newItemHeaderCustomer.setAddress2("");
			model.newItemHeaderCustomer.setCity1("");
			model.newItemHeaderCustomer.setCity2("");
			model.newItemHeaderCustomer.setState1("");
			model.newItemHeaderCustomer.setState2("");
			model.newItemHeaderCustomer.setPhone1("");
			model.newItemHeaderCustomer.setPhone2("");
			model.newItemHeaderCustomer.setNpwp("");
			model.newItemHeaderCustomer.setEmail("");
			model.newItemHeaderCustomer.setStatusactive(true);
			
			//2. SET NEW DATA ITEM TO BINDER
			model.getBinderHeaderCustomer().setItemDataSource(model.newItemHeaderCustomer);
			
			//3. REFRESH VIEW AND SHOW FORM LAYOUT
			view.bindAndBuildFieldGroupComponentCustomer();		
			view.getFormLayoutCustomer().setVisible(true);
			
			//3.SET FORM STATE AND BUTTON STATE
//			model.setOperationStatus(EnumOperationStatus.ADDING.getStrCode());
//			view.setFormButtonAndTextState();
			
			
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
                    		model.getStServiceJpaService().removeObject(model.getItemHeader());
                    		
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
				//3.1 commit form >> MASUKKAN KE DATABASE
				
				model.getBinderHeader().commit(); //COMMIT TETAP PAKE NEW TAPI CLIENT SIDE
				
				model.newItemHeader.setNoreg(model.getTransaksiHelperHp().getNextRegServiceRefno());
				model.newItemHeader.setNojob(model.getTransaksiHelperHp().getNextJobServiceRefno());
				
				model.getStServiceJpaService().createObject(model.getNewItemHeader());
				
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
			//3.2 Masukkan nilai form yang valid(commit) ke dalam tabel >> dan database
			model.getBinderHeader().commit();
			model.getStServiceJpaService().updateObject(model.getItemHeader());
			
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
			Notification.show("Terjadi Kesalahan Insert data!!!", Notification.TYPE_TRAY_NOTIFICATION);
			view.focustIdOrDesc();
			return 1;
		}
		return 0;
	}
	public int insertAndValidateFormCustomer(){
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
				//3.1 commit form >> MASUKKAN KE DATABASE
				model.getBinderHeaderCustomer().commit(); //COMMIT TETAP PAKE NEW TAPI CLIENT SIDE
				
				model.getsCustomerJpaService().createObject(model.getNewItemHeaderCustomer());
				
				//3.2 Masukkan nilai form yang valid(commit) ke dalam tabel
				model.getBeanItemContainerCustomer().addItem(model.getNewItemHeaderCustomer());
				
				//PILIH SEBAGAI PILIHAN BARU
				view.getComboCustomer().setValue(model.getNewItemHeaderCustomer());
				model.getNewItemHeader().setScustomerBean(model.getNewItemHeaderCustomer());
//				view.getTable().refreshRowCache();
				
				//3.3. atur kondisi status form
//				view.setDisplay();
//				model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
//				view.setFormButtonAndTextState();
				//3.4. Refresh lagi container >> SUDAH TIDAK DIPERLUKAN LAGI OLEH KARENA OPERSI //3.2
				
				Notification.show("Penambahan Customer Sukses!!!", Notification.TYPE_TRAY_NOTIFICATION);
				try{
					view.getTable().focus();				
				} catch(Exception ex){}
				
			} catch(Exception ex) {
				Notification.show("Terjadi Kesalahan Insert data Customer!!!", Notification.TYPE_TRAY_NOTIFICATION);
				view.focustIdOrDesc();
				ex.printStackTrace();
				return 1;
			}
		} else {
			return 2;
		}
		
		return 0;
	}
	
	
	public int searchForm(){
		//1. Remove filter dan Refresh container dalulu dahulu
		model.reload();
		model.getBeanItemContainerHeader().removeAllContainerFilters();
		
		//2. Baru Kasih Filter Lagi
		String theNoreg = view.getFieldSearchByNoreg().getValue().toString().trim();
		String theNojob = view.getFieldSearchByNojob().getValue().toString().trim();
		String theNama = view.getFieldSearchByNama().getValue().toString().trim();
		String theAlamat = view.getFieldSearchByAlamat().getValue().toString().trim();
		
		
		Filter filter1 = new SimpleStringFilter("noreg", theNoreg, true, false);
		Filter filter2 = new Or(new SimpleStringFilter("nojob", theNojob, true, false));
		Filter filter3 = new Or(new SimpleStringFilter("scustomerBean.custname", theNama, true, false));
		Filter filter4 = new Or(new SimpleStringFilter("scustomerBean.address1", theAlamat, true, false));

		model.getBeanItemContainerHeader().addContainerFilter(filter1);
		model.getBeanItemContainerHeader().addContainerFilter(filter2);
		model.getBeanItemContainerHeader().addContainerFilter(filter3);
		model.getBeanItemContainerHeader().addContainerFilter(filter4);
		
		//3. Refresh TABLE
		view.setDisplay();
		view.getTable().refreshRowCache();
		//4. Focus KE TABLE
		view.getTable().focus();
		
		return 0;
		
	}
	
	public void printForm(int tipeprint){
			String inputFilePath = "";
			
			if (tipeprint==1) {
				inputFilePath = "/erp/distribution/reports/servicehp/penerimaan1/penerimaan1.jasper";
			}else if (tipeprint==2) {			
				inputFilePath = "/erp/distribution/reports/servicehp/biaya1/biaya1.jasper";
			} else if (tipeprint==3){
				inputFilePath = "/erp/distribution/reports/servicehp/pengambilan1/pengambilan1.jasper";			
			}
			
			String outputFilePath = "service_";
			
			try {			
				final JasperReport report;
				report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
			
				
			final Map parameters=new HashMap();
			if (tipeprint==1) {
				outputFilePath = "service_penerimaan";
				parameters.put("paramJudulFaktur", "PENERIMAAN SERVICE");						
			}else if (tipeprint==2){
				outputFilePath = "service_biaya";
				parameters.put("paramJudulFaktur", "BIAYA SERVICE");						
			} else if (tipeprint==3) {
				outputFilePath = "service_pengambilan";
				parameters.put("paramJudulFaktur", "PENGAMBILAN SERVICE");									
			}
	
			parameters.put("paramCompanyName", model.getSysvarHelper().getCompanyNameFakturRetail());
			parameters.put("paramCompanyDescription", model.getSysvarHelper().getCompanyNameDescription());
			parameters.put("paramCompanyAddress", model.getSysvarHelper().getCompanyAddressFakturRetail());
			parameters.put("paramCompanyPhone", model.getSysvarHelper().getCompanyPhoneFakturRetail());
			parameters.put("paramCompanyNpwp", model.getSysvarHelper().getCompanyNpwpFakturRetail());
	
			parameters.put("paramRefno", model.itemHeader.getRefno());
			parameters.put("paramNoreg", model.itemHeader.getNojob());
			parameters.put("paramNojob", model.itemHeader.getNoreg());
	
	
			//CONNECTION
			final Connection con = new ReportJdbcConfigHelper().getConnection();
			
			
			StreamResource.StreamSource source = new StreamSource() {			
				@Override
				public InputStream getStream() {
					byte[] b = null;
					try {
						b = JasperRunManager.runReportToPdf(report, parameters, con);
					} catch (JRException ex) {
						System.out.println(ex);
					}
					return new ByteArrayInputStream(b);
				}
			};
			
			String fileName = "service_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
			StreamResource resource = new StreamResource( source, fileName);
			resource.setMIMEType("application/pdf");
			resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
			
			view.getUI().getPage().open(resource, "_new_" + outputFilePath, false);
		
			} catch (JRException e) {
				e.printStackTrace();
			}
		
	}
	
	
	
	public void helpForm(){
	}
	public PenerimaanServiceModel getModel() {
		return model;
	}
	public void setModel(PenerimaanServiceModel model) {
		this.model = model;
	}
	public PenerimaanServiceView getView() {
		return view;
	}
	public void setView(PenerimaanServiceView view) {
		this.view = view;
	}

	
	
}
