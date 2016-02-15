package org.erp.distribution.master.producthargaalternatif;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.erp.distribution.kontrolstok.lapmutasistok.LapMutasiStockModel;
import org.erp.distribution.kontrolstok.lapmutasistok.LapMutasiStockPresenter;
import org.erp.distribution.kontrolstok.lapmutasistok.LapMutasiStockView;
import org.erp.distribution.kontrolstok.lapstockopname.LapStockOpnameModel;
import org.erp.distribution.kontrolstok.lapstockopname.LapStockOpnamePresenter;
import org.erp.distribution.kontrolstok.lapstockopname.LapStockOpnameView;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FStock;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.model.FtOpnamed;
import org.erp.distribution.model.FtOpnamedPK;
import org.erp.distribution.model.FtOpnameh;
import org.erp.distribution.model.FtPriceAltd;
import org.erp.distribution.model.FtPriceAltdPK;
import org.erp.distribution.model.FtPriceAlth;
import org.erp.distribution.model.ZLapStockOpname;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.util.ReportJdbcConfigHelper;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class HargaAlternatifPresenter implements ClickListener, ValueChangeListener, ItemClickListener, BlurListener{
	private HargaAlternatifModel model;
	private HargaAlternatifView view;
	
	HargaAlternatifHelper helper;
	
	public HargaAlternatifPresenter(HargaAlternatifModel model, HargaAlternatifView view){
		this.model = model;
		this.view = view;
		helper = new HargaAlternatifHelper(model, view);
		
		initListener();
		
	}
	
	public void initListener(){
		view.getBtnNewForm().addClickListener(this);
		view.getBtnEditForm().addClickListener(this);
		view.getBtnDeleteForm().addClickListener(this);
		view.getBtnHelp().addClickListener(this);
		view.getBtnPosting().addClickListener(this);
		view.getBtnPostingBatal().addClickListener(this);
		view.getBtnSearch().addClickListener(this);
		
		view.getBtnSaveForm().addClickListener(this);
		view.getBtnCancelForm().addClickListener(this);

		view.getBtnAddItem().addClickListener(this);
		view.getBtnEditItem().addClickListener(this);
		view.getBtnRemoveItem().addClickListener(this);
		
		view.getTableList().addItemClickListener(this);
		view.getTableDetil().addItemClickListener(this);
		
		

	}

	@Override
	public void valueChange(ValueChangeEvent event) {
	}
	
	@Override
	public void itemClick(ItemClickEvent event) {
		if (event.getComponent()==view.getTableDetil()){
			Object itemId = event.getItemId();
			
			Item item = view.getTableDetil().getItem(itemId);
			boolean entitySelected = item != null;
			
			if (entitySelected) {
				
				model.itemDetil = new FtPriceAltd();
				model.itemDetil = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				model.getBinderDetil().setItemDataSource(model.itemDetil);

				//FOR DELETE STOK
				model.itemDetilBookmark = new FtPriceAltd();
				FtPriceAltdPK idBookmark = new FtPriceAltdPK();
				idBookmark.setId(model.getItemDetil().getId().getId());
				idBookmark.setRefno(model.getItemDetil().getId().getRefno());
				model.itemDetilBookmark.setId(idBookmark);				
				model.itemDetilBookmark.setFproductBean(model.getItemDetil().getFproductBean());
				model.itemDetilBookmark.setFtpricealthBean(model.itemDetil.getFtpricealthBean());				
//				model.itemDetilBookmark.setQty(model.getItemDetil().getQty());
				
				if (event.isDoubleClick()){
					view.getBtnEditItem().click();
				}
				
				view.setDisplayTableFooterDetil();
			}
			
		
		} else if (event.getComponent()==view.getTableList()){
			
			Object itemId = event.getItemId();
			Item item = view.getTableList().getItem(itemId);
			boolean entitySelected = item != null;
			
			if (entitySelected) {
				
				model.itemHeader = new FtPriceAlth();
				model.itemHeader = model.getBeanItemContainerHeader().getItem(itemId).getBean();
				model.getBinderHeader().setItemDataSource(model.itemHeader);
				
				if (event.isDoubleClick()) {
					view.getTabSheet().setSelectedTab(view.getPanelUtamaDetil());
				}
				
			}
			
			view.bindAndBuildFieldGroupComponentDetilHeader();		
			view.fillComponentDetilItem();
			
			view.setDisplayTableFooterDetil();			
			helper.updateAndCalculateHeaderByItemDetil();
			
			view.setFormButtonAndTextState();
			
		}
	}
	@Override
	public void blur(BlurEvent event) {
		if (event.getComponent() == view.getFieldDisc1()) {
			helper.updateAndCalculateHeaderByItemDetil();
			
		}else if (event.getComponent() == view.getFieldDisc2()) {
			helper.updateAndCalculateHeaderByItemDetil();			
		}else if (event.getComponent() == view.getFieldDisc()) {
			helper.updateAndCalculateHeaderByItemDetil();
		}else if (event.getComponent() == view.getFieldPpnpercent()) {
			helper.updateAndCalculateHeaderByItemDetil();
		}
		
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (model.getOperationStatus()==null) model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		if (model.getOperationStatus().equals("")) model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		
		if (event.getButton() == view.getBtnNewForm()) {
			addItemHeader();
			resetDetil();

			model.setOperationStatus(EnumOperationStatus.ADDING.getStrCode());
			view.setFormButtonAndTextState();
			
			
		} else if (event.getButton() == view.getBtnEditForm()) {
			if (helper.isValidEditForm()==true) {
				//JIKA KONDISI NOPO=NEW ::MAKA MODE ADD NEW
				try{
					if (model.itemHeader.getNorek().equals("New")) {
						model.setOperationStatus(EnumOperationStatus.ADDING.getStrCode());
					} else {				
						
						model.setOperationStatus(EnumOperationStatus.EDITING.getStrCode());
					}
					view.setFormButtonAndTextState();
				} catch(Exception ex){}
			}
			
		} else if (event.getButton() == view.getBtnDeleteForm()) {
			deleteForm();
		} else if (event.getButton() == view.getBtnSaveForm()){		
			if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
				saveFormAdding();
			} else if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
				saveFormEditing();
			}	
			
		} else if(event.getButton() == view.getBtnCancelForm()) {
			if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
				cancelFormAdding();
			} else if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
				cancelFormEditing();
			}				
			model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
			view.setFormButtonAndTextState();
		}else if (event.getButton() == view.getBtnAddItem()){
			addItemDetil();
		}else if (event.getButton() == view.getBtnEditItem()){			
			
			if (model.getItemDetil().getId()!=null){
				editItemDetil();
			}
			
		}else if (event.getButton() == view.getBtnRemoveItem()){
			if (model.getItemDetil().getId()!=null){
				removeItemDetil();
			}
		} else if (event.getButton() == view.getBtnSearch()) {
			searchForm();
		} else if (event.getButton() == view.getBtnPosting()){			
			postingForm();
		} else if (event.getButton() == view.getBtnPostingBatal()){			
			postingBatalForm();
		} else if (event.getButton() == view.getBtnLapselisih()){			
			previewLapSelisih();
		}else if (event.getButton() == view.getBtnHelp()){
			helpForm();
		}else if (event.getButton() == view.getItemDetilView().getBtnAddAndSave()){
			//PERBEDAAN ANTARA ADD DAN EDIT FORM ADALAH:: 
			//ADDFORM -> STOK UPDATE AFTER SAVE ::: EDIT FORM -> STOK UPDATE AUTOMATIC/DIRECT
			if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())) {
				addOrUpdateItemFromNewForm();
			} else if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
				addOrUpdateItemFromEditForm();
			}
		}else if (event.getButton() == view.getItemDetilView().getBtnClose()){
			view.closeWindowForm();
		}
		//Tidak semua akan di refresh container nya >> Jadi refresh container tidak bisa di taruh disini
		
	}

	public void addItemHeader(){
		model.itemHeader = new FtPriceAlth();

		resetNewHeader();
		model.itemHeader.setNorek("New");
		
		//2. SET NEW DATA ITEM TO BINDER
		model.getFtPriceAlthJpaService().createObject(model.itemHeader);
		model.getBinderHeader().setItemDataSource(model.itemHeader);
		
		model.getBeanItemContainerHeader().addItem(model.itemHeader);
		
		//3. REFRESH VIEW AND SHOW FORM LAYOUT
		view.bindAndBuildFieldGroupComponentDetilHeader();			
		
		//3.SET FORM STATE AND BUTTON STATE
		model.setOperationStatus(EnumOperationStatus.ADDING.getStrCode());
		view.setFormButtonAndTextState();
		
		//PENJUMLAHAN FOOTER
		helper.updateAndCalculateHeaderByItemDetil();			
		
		
		
	}
	public void addItemDetil(){	
//		///*******TAMBAHAN JIKA BELUM PERNAH ADA TANGGAL STOCK PADA FSTOCK MAKA BUATKAN
//		Date stockDate = model.getItemHeader().getTrdate();
//		if (! model.getProductAndStockHelper().isStockTransactionExist(stockDate)) {
//			model.getProductAndStockHelper().transferSaldoStokAwalFromBefore(stockDate);
//		}
		//1. TAMPILKAN WINDOW FORM :: ADDITEM DETIL
		view.showWindowForm();
		//2. INIT LISTENER
		view.getItemDetilView().getBtnAddAndSave().addClickListener(this);
		view.getItemDetilView().getBtnClose().addClickListener(this);
		//3. STATE FORM
		view.getItemDetilModel().setOperationStatus(EnumOperationStatus.ADDING.getStrCode());
		//4. INIT VALUE
		view.getItemDetilModel().setItemHeader(model.itemHeader);		
		
		model.itemDetil = new FtPriceAltd();
		FtPriceAltdPK id = new FtPriceAltdPK();
		id.setRefno(model.getItemHeader().getRefno());
		id.setId((long) 0);
		model.itemDetil.setId(id);		
		view.getItemDetilModel().setItemDetil(model.getItemDetil());
		
		view.getItemDetilPresenter().addItemDetil();;		
		view.getItemDetilView().focustIdOrDesc();
//		//PENJUMLAHAN FOOTER
//		helper.updateAndCalculateHeaderByItemDetil();
		//SUPAYA COMBO PRODUCT PERTAMA KALI KOSONG
		view.getItemDetilView().getComboProduct().setValue(null);
		
	}
	
	public void editItemDetil(){
		//1. TAMPILKAN WINDOW FORM :: EDIT DETIL
		view.showWindowForm();
		//2. INIT LISTENER
		view.getItemDetilView().getBtnAddAndSave().addClickListener(this);
		view.getItemDetilView().getBtnClose().addClickListener(this);
		//3. INIT STATE FORM
		view.getItemDetilModel().setOperationStatus(EnumOperationStatus.EDITING.getStrCode());
		//4. INIT VALUE::ITEM DETIL
		view.getItemDetilModel().setItemHeader(model.getItemHeader());
		view.getItemDetilModel().setItemDetil(model.getItemDetil());
		
		view.getItemDetilPresenter().editItemdetil();	
		view.getItemDetilView().focustIdOrDesc();
		
	}
	
	public void addOrUpdateItemFromNewForm(){
		//VALIDASI DULU SEBELUM SAVE
		if (view.getItemDetilModel().getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
			if (view.helper.isValidAddOrUpdateItemAdd()) {
				saveAddOrUpdateItemAddFromNewForm();
			}
		} else if (view.getItemDetilModel().getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())) {
			if (view.helper.isValidAddOrUpdateItemEdit()) {
				saveAddOrUpdateItemEditFromNewForm();
			}
			
		}
	}
	
	public void saveAddOrUpdateItemAddFromNewForm(){
		
		//1. BUAT ITEM BARU
		model.itemDetil = new FtPriceAltd();
		model.itemDetil = view.getItemDetilModel().getItemDetil();
		
		//2. LANGSUNG DIMASUKKAN KE DATABASE :: LALU TAMBAH ITEM PADA TABEL
		model.getFtPriceAltdJpaService().updateObject(model.itemDetil);
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		
		view.getItemDetilView().getBtnClose().click();
		view.getBtnAddItem().click();
		
		//PENJUMLAHAN FOOTER
		view.setDisplayTableFooterDetil();
		helper.updateAndCalculateHeaderByItemDetil();
		
		//NEW FORM UPDATE STOCK SAAT TOMBOL SAVE
		
	}
	
	public void saveAddOrUpdateItemEditFromNewForm(){
		
		//1. BUAT BARU DENGAN ITEM YANG SAMA NAMUN
		model.itemDetil = new FtPriceAltd();
		model.itemDetil = view.getItemDetilModel().getItemDetil();

		//2. ANTISIPASI
		FtPriceAltdPK id = new FtPriceAltdPK();
		id.setRefno(model.getItemHeader().getRefno());
		id.setId(view.getItemDetilModel().getItemDetil().getFproductBean().getId());
		model.itemDetil.setId(id);
		
		//3. UPDATE ITEM YANG SAMA
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		model.getFtPriceAltdJpaService().updateObject(model.itemDetil);
		
		view.getItemDetilView().getBtnClose().click();
		view.fillComponentDetilItem();
		helper.updateAndCalculateHeaderByItemDetil();
		
		//NEW FORM UPDATE STOCK SAAT TOMBOL SAVE
		
	}
	
	public void addOrUpdateItemFromEditForm(){
		
		if (view.getItemDetilModel().getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
			if (view.helper.isValidAddOrUpdateItemAdd()) {
				saveAddOrUpdateItemAddFromEditForm();
			}
		} else if (view.getItemDetilModel().getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())) {
			if (view.helper.isValidAddOrUpdateItemEdit()) {
				saveAddOrUpdateItemEditFromEditForm();
			}
		}
	}
	
	public void saveAddOrUpdateItemAddFromEditForm(){
		
		//1. BUAT ITEM BARU
		model.itemDetil = new FtPriceAltd();
		model.itemDetil = view.getItemDetilModel().getItemDetil();
		
		//2. LANGSUNG DIMASUKKAN KE DATABASE :: LALU TAMBAH ITEM PADA TABEL
		model.getFtPriceAltdJpaService().updateObject(model.itemDetil);
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
//		//3. UPDATE STOK ::ADD
//		List<FtPriced> listForStockAdd = new ArrayList<FtPriced>();
//		listForStockAdd.add(model.getItemDetil());
		
		view.setDisplayTableFooterDetil();
		
		view.getItemDetilView().getBtnClose().click();
		view.getBtnAddItem().click();
		
		helper.updateAndCalculateHeaderByItemDetil();
		
	}
	
	public void saveAddOrUpdateItemEditFromEditForm(){
		
		//1. HAPUS STOK
		List<FtPriceAltd> listForStockRemove = new ArrayList<FtPriceAltd>();
		listForStockRemove.add(model.itemDetilBookmark);
		
		//2. ANTISIPASI
		FtPriceAltdPK id = new FtPriceAltdPK();
		id.setRefno(model.getItemHeader().getRefno());
		id.setId(view.getItemDetilModel().getItemDetil().getFproductBean().getId());
		model.itemDetil.setId(id);	
		
		//3. UPDATE ITEM YANG SAMA
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		model.getFtPriceAltdJpaService().updateObject(model.itemDetil);
		
		//4. UPDATE STOK ::EDIT
		List<FtPriceAltd> listForStockAdd = new ArrayList<FtPriceAltd>();
		listForStockAdd.add(model.getItemDetil());	
		
		view.getItemDetilView().getBtnClose().click();
		view.fillComponentDetilItem();
		helper.updateAndCalculateHeaderByItemDetil();
		
	}
	
	public void removeItemDetil(){
		
		if (model.itemDetil != null){			
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        // Confirmed to continue
                    	try {
                    		//1.VALIDASI :: ADA DIATAS
                    		
                    		//2. HAPUS ITEM DETIL
                    			model.getFtPriceAltdJpaService().removeObject(model.getItemDetil());
                    		
                    		//3. TAMPILAN KEADAAN KOSONG
                    			model.getBeanItemContainerDetil().removeItem(model.getItemDetil());
                    			
                    		//4. UPDATE STOCK::JIKA KONDISI EDIT
                    		if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())) {
                    			List<FtPriceAltd> listForStock = new ArrayList<FtPriceAltd>();
                    			listForStock.add(model.getItemDetil());       
                    			
//                    			model.getProductAndStockHelper().incomingStockRemove(model.getItemHeader().getFwarehouseBeanFrom(), listForStock, model.getItemHeader().getTrdate());
                    		}
                    		
                    		view.bindAndBuildFieldGroupComponentDetilHeader();			
                    		
                			Notification.show("Delete Sukses", Notification.TYPE_TRAY_NOTIFICATION);		                        		
                    	} catch(Exception ex) {
                			Notification.show("Error Delete!!", Notification.TYPE_TRAY_NOTIFICATION);		                        		
                    	}
                    } else {
                    // User did not confirm
                    }
            		view.setDisplayTableFooterDetil();
					view.getTableDetil().focus();
				}
			};
			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi Hapus Item", "Yakin akan hapus item \n" 
			 + model.getItemDetil().getFproductBean().getPname() + " " + model.getItemDetil().getFproductBean().getPackaging(), 
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
	
	public void saveFormAdding(){
		//1. VALIDASI
		if (helper.isValidSaveFormAdding()) {
	
			//2. TERBITKAN NOMOR PO
			model.itemHeader.setNorek(model.getTransaksiHelper().getNextFtPriceAlthRefno());
			//3. SAVE ULANG HEADER
			model.getFtPriceAlthJpaService().updateObject(model.itemHeader);
			
			//4. UPDATE STOCK :: UPDATE STOK TERJADI SETELAH DI SAVE ATAU DITERBITKANNYA NOMOR PO			
			Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
			List<FtPriceAltd> listForStock = new ArrayList<FtPriceAltd>();
			for (Object itemId: itemIds) {
				FtPriceAltd domain = new FtPriceAltd();
				domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				listForStock.add(domain);
			}
//			model.getProductAndStockHelper().incomingStockAdd(model.getItemHeader().getFwarehouseBeanFrom(), listForStock, model.getItemHeader().getTrdate());

			//REFINE TAMPILAN
			model.getBinderHeader().setItemDataSource(model.itemHeader);
			model.getBeanItemContainerHeader().addItem(model.itemHeader);

			
			view.bindAndBuildFieldGroupComponentDetilHeader();		
			view.fillComponentDetilItem();
			
			model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
			view.setDisplayTableFooterList();
			view.setFormButtonAndTextState();
			
		}
	}
	
	public void saveFormEditing(){		
		if (helper.isValidSaveFormEditing()) {
			//KITA MENGGUNAKAN AUTO UPDATE STOK UNTUK EDITING
			model.getFtPriceAlthJpaService().updateObject(model.getItemHeader());
			
			//REFINE TAMPILAN
			model.getBinderHeader().setItemDataSource(model.getItemHeader());
			model.getBeanItemContainerHeader().addItem(model.getItemHeader());
			
			view.bindAndBuildFieldGroupComponentDetilHeader();		
			view.fillComponentDetilItem();

			model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
			view.setDisplayTableFooterList();
			view.setFormButtonAndTextState();			
			//UPDATE STOK EDITING LANGSUNG SAAT TAMBAH			
		}		
	}
	
	public void cancelFormAdding(){
		//1. HAPUS DETIL DAN HEADER
		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
		for (Object itemId: itemIds){
			FtPriceAltd domain = new FtPriceAltd();
			try{
				domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				model.getFtPriceAltdJpaService().removeObject(domain);
			} catch(Exception ex){}
		}
		model.getFtPriceAlthJpaService().removeObject(model.itemHeader);
		model.getBeanItemContainerHeader().removeItem(model.itemHeader);
		
		//2. TAMPILAN KEADAAN KOSONG
		resetNewHeader();
		resetDetil();
		view.bindAndBuildFieldGroupComponentDetilHeader();			

		model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		view.setDisplayTableFooterList();
		view.setFormButtonAndTextState();
	}
	
	public void cancelFormEditing(){
		model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		view.setDisplayTableFooterList();
		view.setFormButtonAndTextState();
			
	}
	
	public void resetNewHeader(){
		
		model.itemHeader.setNorek("");
		model.itemHeader.setDescription("");
		model.itemHeader.setTrdate(model.getTransaksiHelper().getCurrentTransDate());
		model.itemHeader.setEntrydate(new Date());
		model.itemHeader.setPrintcounter(0);
		model.itemHeader.setEndofday(false);
		model.itemHeader.setPosting(false);
		
	}
	
	public void resetHeader(){
		
		model.itemHeader.setNorek("");
		model.itemHeader.setTrdate(model.getTransaksiHelper().getCurrentTransDate());
		model.itemHeader.setEntrydate(new Date());
		model.itemHeader.setPrintcounter(0);		
	}
	
	public void resetDetil(){
		model.getBeanItemContainerDetil().removeAllItems();
	}
	
	public void deleteForm(){
		
		if (model.itemHeader != null){			
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        // Confirmed to continue
                    	try {
                    		//1.VALIDASI
                    		
                    		//2. HAPUS DETIL :: HAPUS HEADER
                    		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
                			List<FtPriceAltd> listForStock = new ArrayList<FtPriceAltd>();
                    		for (Object itemId: itemIds){
                    			FtPriceAltd domain = new FtPriceAltd();
                    			domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
                    			model.getFtPriceAltdJpaService().removeObject(domain);

                				domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
                				listForStock.add(domain);
                    			
                    		}
                    		
                    		model.getFtPriceAlthJpaService().removeObject(model.getItemHeader());
                    		model.getBeanItemContainerHeader().removeItem(model.getItemHeader());
                    		
//                    		//3. UPDATE STOCK
//                    		if (! model.getItemHeader().getNorek().equalsIgnoreCase("New")) {
//                    			model.getProductAndStockHelper().stockOpnameRemove(model.getItemHeader().getFwarehouseBean(), listForStock, model.getItemHeader().getTrdate());
//                    		}
                    		
                    		//4. TAMPILAN KEADAAN KOSONG
                    		resetHeader();;
                    		resetDetil();
                    		
                    		model.getBinderHeader().setItemDataSource(model.getItemHeader());
                    		view.bindAndBuildFieldGroupComponentDetilHeader();			
                    		
                			model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
                			view.setDisplayTableFooterList();
                			view.setFormButtonAndTextState();
                			
                			Notification.show("Delete Sukses", Notification.TYPE_TRAY_NOTIFICATION);		                        		
                    	} catch(Exception ex) {
                			Notification.show("Error Delete!!", Notification.TYPE_TRAY_NOTIFICATION);		                        		
                    	}
                    } else {
                    // User did not confirm
                    }
					view.getTableDetil().focus();
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
	
	public void searchForm(){
		model.getBeanItemContainerHeader().removeAllContainerFilters();
		
		//2. Baru Kasih Filter Lagi
		String strSearch1 = view.getFieldSearch1().getValue().toString().trim();
		String strSearch2 = view.getFieldSearch2().getValue().toString().trim();	
		
		Filter filter1 = new Or(new SimpleStringFilter("nopo", strSearch1, true, false));
		Filter filter2 = new Or(new SimpleStringFilter("invoiceno", strSearch2, true, false));
		model.getBeanItemContainerHeader().addContainerFilter(filter1);
		model.getBeanItemContainerHeader().addContainerFilter(filter2);
		
	}
	
	public void postingForm(){	
        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
			@Override	
			public void onClose(ConfirmDialog dialog) {
                if (dialog.isConfirmed()) {
                	
        			//2.REFINE 
                	model.getItemHeader().setPosting(true);
                	model.getFtPriceAlthJpaService().updateObject(model.getItemHeader());
                	
                	//1. POSTING PARSIAL :: POSTING PERUBAHAN HARGA DILAKUKAN SAAT TRANSAKSI TANGGAL TRANSAKSI SAMA DENGAN TANGGAL SYSTEM
                	helper.postingPerubahanHarga();
                	
                	//3. PERBAIKIN TAMPILAN
                	model.getBeanItemContainerHeader().addBean(model.getItemHeader());
                	view.bindAndBuildFieldGroupComponentDetilHeader();
                	view.setFormButtonAndTextState();
            		
            		Notification.show("Posting Selesai", Notification.TYPE_TRAY_NOTIFICATION);
            		
                } else {
                }
                
				view.getTableDetil().focus();
			}
		};
		
		 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi Posting Opname", "Yakin Proses Posting Hasil Opname?", 
				 "Oke Proses Posting", "Cancel", konfirmDialogListener);
		 
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
		
		
	}
	public void postingBatalForm(){	
        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
			@Override	
			public void onClose(ConfirmDialog dialog) {
                if (dialog.isConfirmed()) {
            		//2. REFINE 
                	model.getItemHeader().setPosting(false);
                	model.getFtPriceAlthJpaService().updateObject(model.getItemHeader());
                	//1. POSTING PARSIAL ::BATALKAN:: :: POSTING PERUBAHAN HARGA DILAKUKAN SAAT TRANSAKSI TANGGAL TRANSAKSI SAMA DENGAN TANGGAL SYSTEM
                	helper.postingPerubahanHargaPembatalan();
                	//3. PERBAIKIN TAMPILAN
                	model.getBeanItemContainerHeader().addBean(model.getItemHeader());
                	view.bindAndBuildFieldGroupComponentDetilHeader();
                	view.setFormButtonAndTextState();
            		
            		Notification.show("Posting Selesai", Notification.TYPE_TRAY_NOTIFICATION);
            		
                } else {
                	
                }
                
				view.getTableDetil().focus();
			}
		};
		
		 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi Pembatalan Posting", "Yakin BATALKAN Posting Hasil Opname?", 
				 "Oke Batalkan", "Cancel", konfirmDialogListener);
		 
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
		
		
	}
	
	public void previewLapSelisih(){
		if (model.getBeanItemContainerDetil().getItemIds().size()>0) {
			previewLengkap();
		}
	}
	
	//**FOR PRINT PREVIEW
	private String strParamNorek = "";
	private String strParamWarehouse = "";
	private Date dateParamTrdate = null;
	
	public void resetParameters(){
		strParamNorek= "";
		strParamWarehouse = "";
		dateParamTrdate = new Date();
	}
	
	public void reloadParameter(){		
		strParamNorek = model.getItemHeader().getNorek();
//		strParamWarehouse = " (" + model.getItemHeader().getFwarehouseBean().getId() + ") " +  model.getItemHeader().getFwarehouseBean().getDescription();
		dateParamTrdate = model.getItemHeader().getTrdate();
		
	}
	
	
	public void previewLengkap(){
		try{
			if (model.getItemHeader().getPosting().equals(true)) {
				resetParameters();
				reloadParameter();
				
				//1. ISI DATABASE UNTUK TEMP
				fillDatabaseReportLengkap();
				//2. PREVIEW LAPORAN
				showPreview("/erp/distribution/reports/kontrolstock/lapstockopname/lapstockopname1.jasper", "lapstockopname1");
			} else {
				Notification.show("Dokumen belum diposting atau di lakukan kalkulasi stok!", Notification.TYPE_TRAY_NOTIFICATION);
			}
		} catch(Exception ex){}
	}
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
		parameters.put("CompanyName","");
		
		parameters.put("paramNorek", strParamNorek);
		parameters.put("paramWarehouse", strParamWarehouse);		
		parameters.put("paramTrdate", dateParamTrdate);
		
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
		
		String fileName = "ar_kas_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new_stock_opname_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	
	}
	
	public void fillDatabaseReportLengkap(){
		
 	}
	
	public void helpForm(){
	}
	public HargaAlternatifModel getModel() {
		return model;
	}
	public void setModel(HargaAlternatifModel model) {
		this.model = model;
	}
	public HargaAlternatifView getView() {
		return view;
	}
	public void setView(HargaAlternatifView view) {
		this.view = view;
	}

	
	
}
