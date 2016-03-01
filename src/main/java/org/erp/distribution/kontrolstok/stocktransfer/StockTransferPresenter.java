package org.erp.distribution.kontrolstok.stocktransfer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.erp.distribution.model.FtOpnamed;
import org.erp.distribution.model.FtOpnamedPK;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.FtStocktransferd;
import org.erp.distribution.model.FtStocktransferdPK;
import org.erp.distribution.model.FtStocktransferh;
import org.erp.distribution.model.ZLapPackingList;
import org.erp.distribution.model.ZLapTemplate1;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.ReportJdbcConfigHelper;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class StockTransferPresenter implements ClickListener, ValueChangeListener, Handler, ItemClickListener, BlurListener{
	private StockTransferModel model;
	private StockTransferView view;
	
	StockTransferHelper helper;
	
	public StockTransferPresenter(StockTransferModel model, StockTransferView view){
		this.model = model;
		this.view = view;
		helper = new StockTransferHelper(model, view);
		
		initListener();
		
	}
	
	public void initListener(){
		view.getBtnNewForm().addClickListener(this);
		view.getBtnEditForm().addClickListener(this);
		view.getBtnDeleteForm().addClickListener(this);
		view.getBtnHelp().addClickListener(this);
		view.getBtnPrint().addClickListener(this);
		view.getBtnSearch().addClickListener(this);
		
		view.getBtnSaveForm().addClickListener(this);
		view.getBtnCancelForm().addClickListener(this);

		view.getBtnAddItem().addClickListener(this);
		view.getBtnEditItem().addClickListener(this);
		view.getBtnRemoveItem().addClickListener(this);
		
		view.getTableList().addItemClickListener(this);
		view.getTableDetil().addItemClickListener(this);
		
		
		view.getFieldDisc1().addBlurListener(this);
		view.getFieldDisc2().addBlurListener(this);
		view.getFieldDisc().addBlurListener(this);
		view.getFieldPpnpercent().addBlurListener(this);
		
		view.getPanelTop().addActionHandler(this);

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
				
				model.itemDetil = new FtStocktransferd();
				model.itemDetil = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				model.getBinderDetil().setItemDataSource(model.itemDetil);

				//FOR DELETE STOK
				model.itemDetilBookmark = new FtStocktransferd();
				FtStocktransferdPK idBookmark = new FtStocktransferdPK();
				idBookmark.setId(model.getItemDetil().getId().getId());
				idBookmark.setRefno(model.getItemDetil().getId().getRefno());
				model.itemDetilBookmark.setId(idBookmark);				
				model.itemDetilBookmark.setFproductBean(model.getItemDetil().getFproductBean());
				model.itemDetilBookmark.setFtstocktransferhBean(model.itemDetil.getFtstocktransferhBean());				
				model.itemDetilBookmark.setQty(model.getItemDetil().getQty());
				
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
				
				model.itemHeader = new FtStocktransferh();
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
		} else if (event.getButton() == view.getBtnPrint()){			
			printForm();
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
		model.itemHeader = new FtStocktransferh();

		resetNewHeader();
		model.itemHeader.setNorek("New");
		
		//2. SET NEW DATA ITEM TO BINDER
		model.getFtStocktransferhJpaService().createObject(model.itemHeader);
		model.getBinderHeader().setItemDataSource(model.itemHeader);
		
		model.getBeanItemContainerHeader().addItem(model.itemHeader);
		
		//3. REFRESH VIEW AND SHOW FORM LAYOUT
		view.bindAndBuildFieldGroupComponentDetilHeader();			
		
		//3.SET FORM STATE AND BUTTON STATE
		model.setOperationStatus(EnumOperationStatus.ADDING.getStrCode());
		view.setFormButtonAndTextState();
		
//		//PENJUMLAHAN FOOTER
//		helper.updateAndCalculateHeaderByItemDetil();			
		
	}
	public void addItemDetil(){	
		///*******TAMBAHAN JIKA BELUM PERNAH ADA TANGGAL STOCK PADA FSTOCK MAKA BUATKAN
		Date stockDate = model.getItemHeader().getTrdate();
		if (! model.getProductAndStockHelper().isStockTransactionExist(stockDate)) {
			model.getProductAndStockHelper().transferSaldoStokAwalFromBefore(stockDate);
		}
		//1. TAMPILKAN WINDOW FORM :: ADDITEM DETIL
		view.showWindowForm();
		//2. INIT LISTENER
		view.getItemDetilView().getBtnAddAndSave().addClickListener(this);
		view.getItemDetilView().getBtnClose().addClickListener(this);
		//3. STATE FORM
		view.getItemDetilModel().setOperationStatus(EnumOperationStatus.ADDING.getStrCode());
		//4. INIT VALUE
		addItemDetilNew();
		
//		//PENJUMLAHAN FOOTER
//		helper.updateAndCalculateHeaderByItemDetil();

		//SUPAYA COMBO PRODUCT PERTAMA KALI KOSONG
		view.getItemDetilView().getComboProduct().setValue(null);
		
	}
	public void addItemDetilNew(){
		view.getItemDetilModel().setItemHeader(model.itemHeader);		
		
		model.itemDetil = new FtStocktransferd();
		FtStocktransferdPK id = new FtStocktransferdPK();
		id.setRefno(model.getItemHeader().getRefno());
		id.setId((long) 0);
		model.itemDetil.setId(id);		
		view.getItemDetilModel().setItemDetil(model.getItemDetil());
		
		view.getItemDetilPresenter().addItemDetil();;		
		view.getItemDetilView().focustIdOrDesc();
		
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
		model.itemDetil = new FtStocktransferd();
		model.itemDetil = view.getItemDetilModel().getItemDetil();
		
		//2. ANTISIPASI
		FtStocktransferdPK id = new FtStocktransferdPK();
		id.setRefno(model.getItemHeader().getRefno());
		id.setId(view.getItemDetilModel().getItemDetil().getFproductBean().getId());
		model.itemDetil.setId(id);	
		
		//2. LANGSUNG DIMASUKKAN KE DATABASE :: LALU TAMBAH ITEM PADA TABEL
		model.getFtStocktransferdJpaService().updateObject(model.itemDetil);
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		view.getTableDetil().addItem(model.itemDetil);

		addItemDetilNew();
		//PENJUMLAHAN FOOTER
		view.setDisplayTableFooterDetil();
		helper.updateAndCalculateHeaderByItemDetil();
		
		//NEW FORM UPDATE STOCK SAAT TOMBOL SAVE
		//BIAR COMBO PRODUCT KOSONG LAGI
		view.getItemDetilView().getComboProduct().setValue(null);
		
	}	
	public void saveAddOrUpdateItemEditFromNewForm(){
		
		//1. BUAT BARU DENGAN ITEM YANG SAMA NAMUN
		model.itemDetil = new FtStocktransferd();
		model.itemDetil = view.getItemDetilModel().getItemDetil();

//		//2. ANTISIPASI
//		FtStocktransferdPK id = new FtStocktransferdPK();
//		id.setRefno(model.getItemHeader().getRefno());
//		id.setId(view.getItemDetilModel().getItemDetil().getFproductBean().getId());
//		model.itemDetil.setId(id);
//		
		//3. UPDATE ITEM YANG SAMA
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		model.getFtStocktransferdJpaService().updateObject(model.itemDetil);
		
		view.getItemDetilView().getBtnClose().click();
		view.fillComponentDetilItem();
		helper.updateAndCalculateHeaderByItemDetil();
		
		//NEW FORM UPDATE STOCK SAAT TOMBOL SAVE
		//BIAR COMBO PRODUCT KOSONG LAGI
		view.getItemDetilView().getComboProduct().setValue(null);
		
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
	
	//####PERBAIKI INI UNTUK STOK
	public void saveAddOrUpdateItemAddFromEditForm(){
		
		//1. BUAT ITEM BARU
		model.itemDetil = new FtStocktransferd();
		model.itemDetil = view.getItemDetilModel().getItemDetil();
		
		//2. ANTISIPASI
		FtStocktransferdPK id = new FtStocktransferdPK();
		id.setRefno(model.getItemHeader().getRefno());
		id.setId(view.getItemDetilModel().getItemDetil().getFproductBean().getId());
		model.itemDetil.setId(id);	
		
		//2. LANGSUNG DIMASUKKAN KE DATABASE :: LALU TAMBAH ITEM PADA TABEL
		model.getFtStocktransferdJpaService().updateObject(model.itemDetil);
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		
		//3. UPDATE STOK ::ADD
		List<FtStocktransferd> listForStockAdd = new ArrayList<FtStocktransferd>();
		listForStockAdd.add(model.getItemDetil());
		
		model.getProductAndStockHelper().stockTransferAdd(model.getItemHeader().getFwarehouseBeanFrom(), 
				model.getItemHeader().getFwarehouseBeanTo(),listForStockAdd, model.getItemHeader().getTrdate());
		
		view.setDisplayTableFooterDetil();
		
		addItemDetilNew();
		helper.updateAndCalculateHeaderByItemDetil();

		//BIAR COMBO PRODUCT KOSONG LAGI
		view.getItemDetilView().getComboProduct().setValue(null);
		
	}
	public void saveAddOrUpdateItemEditFromEditForm(){
		
		//1. HAPUS STOK
		List<FtStocktransferd> listForStockRemove = new ArrayList<FtStocktransferd>();
		listForStockRemove.add(model.itemDetilBookmark);
		
		model.getProductAndStockHelper().stockTransferRemove(model.getItemHeader().getFwarehouseBeanFrom(), 
				model.getItemHeader().getFwarehouseBeanTo(),listForStockRemove, model.getItemHeader().getTrdate());
		
//		//2. ANTISIPASI
//		FtStocktransferdPK id = new FtStocktransferdPK();
//		id.setRefno(model.getItemHeader().getRefno());
//		id.setId(view.getItemDetilModel().getItemDetil().getFproductBean().getId());
//		model.itemDetil.setId(id);	
//		
		//3. UPDATE ITEM YANG SAMA
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		model.getFtStocktransferdJpaService().updateObject(model.itemDetil);
		
		//4. UPDATE STOK ::EDIT
		List<FtStocktransferd> listForStockAdd = new ArrayList<FtStocktransferd>();
		listForStockAdd.add(model.getItemDetil());	
		
		model.getProductAndStockHelper().stockTransferAdd(model.getItemHeader().getFwarehouseBeanFrom(), 
				model.getItemHeader().getFwarehouseBeanTo(),listForStockAdd, model.getItemHeader().getTrdate());
		
		view.getItemDetilView().getBtnClose().click();
		view.fillComponentDetilItem();
		helper.updateAndCalculateHeaderByItemDetil();
		
		//BIAR COMBO PRODUCT KOSONG LAGI
		view.getItemDetilView().getComboProduct().setValue(null);
		
	}
	
	public void removeItemDetil(){
		
		if (model.itemDetil != null){			
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
                        // Confirmed to continue
                    	try {
                    		//1. VALIDASI :: ADA DIATAS
                    		
                    		
                    		//2. HAPUS ITEM DETIL
                    			model.getFtStocktransferdJpaService().removeObject(model.getItemDetil());
                    		
                    		//3. TAMPILAN KEADAAN KOSONG
                    			model.getBeanItemContainerDetil().removeItem(model.getItemDetil());
                    			
                    		//4. UPDATE STOCK::JIKA KONDISI EDIT
                    		if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())) {
                    			List<FtStocktransferd> listForStock = new ArrayList<FtStocktransferd>();
                    			listForStock.add(model.getItemDetil());       
                    			
                    			model.getProductAndStockHelper().stockTransferRemove(model.getItemHeader().getFwarehouseBeanFrom(), 
                    					model.getItemHeader().getFwarehouseBeanTo(),listForStock, model.getItemHeader().getTrdate());
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
		
	//PERBAIKI DISINI
	public void saveFormAdding(){
		//1. VALIDASI
		if (helper.isValidSaveFormAdding()) {
	
			//2. TERBITKAN NOMOR PO
			model.itemHeader.setNorek(model.getTransaksiHelper().getNextFtStocktransferhRefno());
			//3. SAVE ULANG HEADER
			model.getFtStocktransferhJpaService().updateObject(model.itemHeader);
			
			//4. UPDATE STOCK :: UPDATE STOK TERJADI SETELAH DI SAVE ATAU DITERBITKANNYA NOMOR PO			
			Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
			List<FtStocktransferd> listForStock = new ArrayList<FtStocktransferd>();
			for (Object itemId: itemIds) {
				FtStocktransferd domain = new FtStocktransferd();
				domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				listForStock.add(domain);
			}
			model.getProductAndStockHelper()
			.stockTransferAdd(model.getItemHeader().getFwarehouseBeanFrom(), model.getItemHeader().getFwarehouseBeanTo(),listForStock, model.getItemHeader().getTrdate());

			//REFINE TAMPILAN
			model.getBinderHeader().setItemDataSource(model.itemHeader);
			model.getBeanItemContainerHeader().addItem(model.itemHeader);
			
			view.bindAndBuildFieldGroupComponentDetilHeader();		
			view.fillComponentDetilItem();

			model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
			view.setDisplayTableFooterList();
			view.setFormButtonAndTextState();
			
			Notification.show("Save and Stock Update!", Notification.TYPE_TRAY_NOTIFICATION);
		}
	}
	public void saveFormEditing(){
		if (helper.isValidSaveFormEditing()) {
			//KITA MENGGUNAKAN AUTO UPDATE STOK UNTUK EDITING
			model.getFtStocktransferhJpaService().updateObject(model.getItemHeader());
			
			//REFINE TAMPILAN
			model.getBinderHeader().setItemDataSource(model.getItemHeader());
			model.getBeanItemContainerHeader().addItem(model.getItemHeader());
			
			view.bindAndBuildFieldGroupComponentDetilHeader();		
			view.fillComponentDetilItem();

			model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
			view.setDisplayTableFooterList();
			view.setFormButtonAndTextState();
			
			//UPDATE STOK EDITING LANGSUNG SAAT TAMBAH
			Notification.show("Save and Stock Update!", Notification.TYPE_TRAY_NOTIFICATION);
		}
	}
	
	public void cancelFormAdding(){
		//1. HAPUS DETIL DAN HEADER
		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
		for (Object itemId: itemIds){
			FtStocktransferd domain = new FtStocktransferd();
			try{
				domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				model.getFtStocktransferdJpaService().removeObject(domain);
			} catch(Exception ex){}
		}
		model.getFtStocktransferhJpaService().removeObject(model.itemHeader);
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
		model.itemHeader.setTipetransfer(0);
		model.itemHeader.setTrdate(model.getTransaksiHelper().getCurrentTransDate());
		model.itemHeader.setEntrydate(new Date());
		model.itemHeader.setTipetransfer(1);
		model.itemHeader.setPrintcounter(0);
		
	}
	public void resetHeader(){
		
		model.itemHeader.setNorek("");
		model.itemHeader.setTipetransfer(0);
		model.itemHeader.setTrdate(model.getTransaksiHelper().getCurrentTransDate());
		model.itemHeader.setEntrydate(new Date());
		model.itemHeader.setTipetransfer(1);
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
                    		if (model.itemHeader.getEndofday() == null)  {
                    			model.itemHeader.setEndofday(false);
                    			model.getFtStocktransferhJpaService().updateObject(model.itemHeader);
                    		}
                    		if (model.itemHeader.getEndofday() != true) {
	                    		//2. HAPUS DETIL :: HAPUS HEADER
	                    		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
	                			List<FtStocktransferd> listForStock = new ArrayList<FtStocktransferd>();
	                    		for (Object itemId: itemIds){
	                    			FtStocktransferd domain = new FtStocktransferd();
	                    			domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
	                    			model.getFtStocktransferdJpaService().removeObject(domain);
	
	                				domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
	                				listForStock.add(domain);
	                    			
	                    		}
	                    		model.getFtStocktransferhJpaService().removeObject(model.getItemHeader());
	                    		model.getBeanItemContainerHeader().removeItem(model.getItemHeader());
	                    		
	                    		//3. UPDATE STOCK
	                    		if (! model.getItemHeader().getNorek().equalsIgnoreCase("New")) {
	                    			model.getProductAndStockHelper().stockTransferRemove(model.getItemHeader().getFwarehouseBeanFrom(), 
	                    					model.getItemHeader().getFwarehouseBeanTo(), listForStock, model.getItemHeader().getTrdate());
	                    		}
	                    		//4. TAMPILAN KEADAAN KOSONG
	                    		resetHeader();;
	                    		resetDetil();
	                    		
	                    		model.getBinderHeader().setItemDataSource(model.getItemHeader());
	                    		view.bindAndBuildFieldGroupComponentDetilHeader();			
	                    		
	                			model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
	                			view.setDisplayTableFooterList();
	                			view.setFormButtonAndTextState();
	                			
	                			Notification.show("Delete Sukses", Notification.TYPE_TRAY_NOTIFICATION);	
                    		} else {
                    			Notification.show("Tidak bisa dihapus!! Sudah Closing!!", Notification.TYPE_TRAY_NOTIFICATION);                    			
                    		}
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
		
		Filter filter1 = new Or(new SimpleStringFilter("norek", strSearch1, true, false));
//		Filter filter2 = new Or(new SimpleStringFilter("invoiceno", strSearch2, true, false));
		model.getBeanItemContainerHeader().addContainerFilter(filter1);
//		model.getBeanItemContainerHeader().addContainerFilter(filter2);
		
	}
	
	String paramCompanyName = "";
	String paramString1 = "";
	String paramString2 = "";
	String paramString3 = "";
	Date paramDate1 = new Date();	
	public void resetParameters(){
		paramString1 = "";
		paramString2 = "";
		paramString3 = "";
		paramDate1 = new Date();
		
	}
	public void reloadParameter(){
		try{
			paramCompanyName = model.getSysvarHelper().getCompanyAddressFaktur();
			paramString1 = model.getItemHeader().getNorek();
			paramString2 = "(" + model.getItemHeader().getFwarehouseBeanFrom().getId() + ") " 
					+  model.getItemHeader().getFwarehouseBeanFrom().getDescription();
			paramString2 = "(" + model.getItemHeader().getFwarehouseBeanTo().getId() + ") " 
					+  model.getItemHeader().getFwarehouseBeanTo().getDescription();
		} catch(Exception ex){}
	}	
	
	public void printForm(){		
		resetParameters();
		reloadParameter();
		
		//1. ISI DATABASE UNTUK TEMP
		fillDatabaseReportLengkap();
		//2. PREVIEW LAPORAN
		showPreview("/erp/distribution/reports/kontrolstock/mutasistok/mutasistok1.jasper", "mutasistok1");
		
	}
	
	public void fillDatabaseReportLengkap(){

		if (! model.itemHeader.getNorek().equals("New") || ! model.itemHeader.getNorek().equals("")) {
			//2. MASUKKAN YANG DISELEKSI KE DALAM TABLE REPORT TEMPORER TAHAP1
			Collection itemIds =  model.getBeanItemContainerDetil().getItemIds();				
			for (Object itemId: itemIds){			
				FtStocktransferd itemDetil = new FtStocktransferd();
				itemDetil = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				//Menghindari null
					
				ZLapTemplate1 domain = new ZLapTemplate1();
				
				domain.setGrup1(itemDetil.getFproductBean().getFproductgroupBean().getId());
				domain.setGrup2("-");
				domain.setGrup3("-");
//					domain.setId(0);
				domain.setString1(itemDetil.getFproductBean().getPcode());
				domain.setString2(itemDetil.getFproductBean().getPname() + " " + itemDetil.getFproductBean().getPackaging());

				domain.setString3(itemDetil.getFproductBean().getUom1());
				domain.setString4(itemDetil.getFproductBean().getUom2());
				domain.setString5(itemDetil.getFproductBean().getUom3());
					
				domain.setInt1(itemDetil.getQty());
				domain.setInt2(model.getProductAndStockHelper().getBesFromPcs(itemDetil.getQty(), itemDetil.getFproductBean()));
				domain.setInt3(model.getProductAndStockHelper().getSedFromPcs(itemDetil.getQty(), itemDetil.getFproductBean()));
				domain.setInt4(model.getProductAndStockHelper().getKecFromPcs(itemDetil.getQty(), itemDetil.getFproductBean()));
				
						
				
			}
		}
	}		
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
		parameters.put("CompanyName", "");
		
		
		parameters.put("paramString1", paramString1);
		parameters.put("paramString2", paramString2);
		parameters.put("paramString3", paramString3);
		parameters.put("paramDate1", paramDate1);
		
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
		
		view.getUI().getPage().open(resource, "_new_packinglistpersj_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	
		
	}
	
	
	public void helpForm(){
	}
	public StockTransferModel getModel() {
		return model;
	}
	public void setModel(StockTransferModel model) {
		this.model = model;
	}
	public StockTransferView getView() {
		return view;
	}
	public void setView(StockTransferView view) {
		this.view = view;
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
