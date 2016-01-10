package org.erp.distribution.purchaseorder.incomingstock;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.erp.distribution.model.FtPurchased;
import org.erp.distribution.model.FtPurchasedPK;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesdPK;
import org.erp.distribution.model.FtSalesdPromoTprb;
import org.erp.distribution.model.FtSalesdPromoTpruCb;
import org.erp.distribution.model.FtSalesdPromoTpruDisc;
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

public class IncomingStockPresenter implements ClickListener, ValueChangeListener, ItemClickListener, BlurListener{
	private IncomingStockModel model;
	private IncomingStockView view;
	
	IncomingStockHelper helper;
	
	public IncomingStockPresenter(IncomingStockModel model, IncomingStockView view){
		this.model = model;
		this.view = view;
		helper = new IncomingStockHelper(model, view);
		
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
		
		ValueChangeListener listenerValueChangeOrderDate = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				view.getDateFieldInvoicedate().setValue(view.getDateFieldPodate().getValue());
				view.getDateFieldDuedate().setValue(view.getDateFieldPodate().getValue());
			}
		};
		view.getDateFieldPodate().addValueChangeListener(listenerValueChangeOrderDate);

		ValueChangeListener listenerValueChangeInvoiceDate = new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				try{
					if (view.getDateFieldInvoicedate().getValue().getTime() < view.getDateFieldPodate().getValue().getTime()) {
						view.getDateFieldInvoicedate().setValue(view.getDateFieldPodate().getValue());					
						view.getDateFieldDuedate().setValue(view.getDateFieldPodate().getValue());
						Notification.show("Tanggal Invoice lebih kecil dari pada tanggal Order", Notification.TYPE_TRAY_NOTIFICATION);
						
					} 
				} catch(Exception ex){}
				
			}
		};
		view.getDateFieldInvoicedate().addValueChangeListener(listenerValueChangeInvoiceDate);
		

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
				
				model.itemDetil = new FtPurchased();
				model.itemDetil = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				model.getBinderDetil().setItemDataSource(model.itemDetil);

				//FOR DELETE STOK
				model.itemDetilBookmark = new FtPurchased();
				FtPurchasedPK idBookmark = new FtPurchasedPK();
				idBookmark.setId(model.getItemDetil().getId().getId());
				idBookmark.setRefno(model.getItemDetil().getId().getRefno());
				model.itemDetilBookmark.setId(idBookmark);				
				model.itemDetilBookmark.setFproductBean(model.getItemDetil().getFproductBean());
				model.itemDetilBookmark.setFtpurchasehBean(model.itemDetil.getFtpurchasehBean());				
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
				
				model.itemHeader = new FtPurchaseh();
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
					if (model.itemHeader.getNopo().equals("New")) {
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
		model.itemHeader = new FtPurchaseh();

		resetNewHeader();
		model.itemHeader.setNopo("New");
		
		//2. SET NEW DATA ITEM TO BINDER
		model.getFtPurchasehJpaService().createObject(model.itemHeader);
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
		///*******TAMBAHAN JIKA BELUM PERNAH ADA TANGGAL STOCK PADA FSTOCK MAKA BUATKAN
		Date stockDate = model.getItemHeader().getInvoicedate();
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
		view.getItemDetilModel().setItemHeader(model.itemHeader);		
		
		model.itemDetil = new FtPurchased();
		FtPurchasedPK id = new FtPurchasedPK();
		id.setRefno(model.getItemHeader().getRefno());
		id.setId((long) 0);
		model.itemDetil.setId(id);		
		view.getItemDetilModel().setItemDetil(model.getItemDetil());
		
		view.getItemDetilPresenter().addItemDetil();;		
		view.getItemDetilView().focustIdOrDesc();
		//PENJUMLAHAN FOOTER
		helper.updateAndCalculateHeaderByItemDetil();

		
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
		model.itemDetil = new FtPurchased();
		model.itemDetil = view.getItemDetilModel().getItemDetil();
		
		//2. ANTISIPASI
		FtPurchasedPK id = new FtPurchasedPK();
		id.setRefno(model.getItemHeader().getRefno());
		id.setId(view.getItemDetilModel().getItemDetil().getFproductBean().getId());
		model.itemDetil.setId(id);	
		
		//2. LANGSUNG DIMASUKKAN KE DATABASE :: LALU TAMBAH ITEM PADA TABEL
		model.getFtPurchasedJpaService().updateObject(model.itemDetil);
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		view.getTableDetil().addItem(model.itemDetil);
		
		view.getItemDetilView().getBtnClose().click();
		view.getBtnAddItem().click();
		
		//PENJUMLAHAN FOOTER
		view.setDisplayTableFooterDetil();
		helper.updateAndCalculateHeaderByItemDetil();
		
		//NEW FORM UPDATE STOCK SAAT TOMBOL SAVE
		//BIAR COMBO PRODUCT KOSONG LAGI
		view.getItemDetilView().getComboProduct().setValue(null);
		
	}
	
	public void saveAddOrUpdateItemEditFromNewForm(){
		
		//1. BUAT BARU DENGAN ITEM YANG SAMA NAMUN
		model.itemDetil = new FtPurchased();
		model.itemDetil = view.getItemDetilModel().getItemDetil();

//		//2. ANTISIPASI
//		FtPurchasedPK id = new FtPurchasedPK();
//		id.setRefno(model.getItemHeader().getRefno());
//		id.setId(view.getItemDetilModel().getItemDetil().getFproductBean().getId());
//		model.itemDetil.setId(id);
		
		//3. UPDATE ITEM YANG SAMA
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		model.getFtPurchasedJpaService().updateObject(model.itemDetil);
		
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
	
	public void saveAddOrUpdateItemAddFromEditForm(){
		
		//1. BUAT ITEM BARU
		model.itemDetil = new FtPurchased();
		model.itemDetil = view.getItemDetilModel().getItemDetil();
		
		//2. ANTISIPASI
		FtPurchasedPK id = new FtPurchasedPK();
		id.setRefno(model.getItemHeader().getRefno());
		id.setId(view.getItemDetilModel().getItemDetil().getFproductBean().getId());
		model.itemDetil.setId(id);	
		
		//2. LANGSUNG DIMASUKKAN KE DATABASE :: LALU TAMBAH ITEM PADA TABEL
		model.getFtPurchasedJpaService().updateObject(model.itemDetil);
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		//3. UPDATE STOK ::ADD
		List<FtPurchased> listForStockAdd = new ArrayList<FtPurchased>();
		listForStockAdd.add(model.getItemDetil());		
		model.getProductAndStockHelper().incomingStockAdd(model.getItemHeader().getFwarehouseBean(), listForStockAdd, model.getItemHeader().getPodate());
		
		view.setDisplayTableFooterDetil();
		
		view.getItemDetilView().getBtnClose().click();
		view.getBtnAddItem().click();
		
		helper.updateAndCalculateHeaderByItemDetil();

		//BIAR COMBO PRODUCT KOSONG LAGI
		view.getItemDetilView().getComboProduct().setValue(null);
		
	}
	public void saveAddOrUpdateItemEditFromEditForm(){
		
		//1. HAPUS STOK
		List<FtPurchased> listForStockRemove = new ArrayList<FtPurchased>();
		listForStockRemove.add(model.itemDetilBookmark);
		model.getProductAndStockHelper().incomingStockRemove(model.getItemHeader().getFwarehouseBean(), listForStockRemove, model.getItemHeader().getPodate());
		

//		//2. ANTISIPASI
//		FtPurchasedPK id = new FtPurchasedPK();
//		id.setRefno(model.getItemHeader().getRefno());
//		id.setId(view.getItemDetilModel().getItemDetil().getFproductBean().getId());
//		model.itemDetil.setId(id);	
		
		//3. UPDATE ITEM YANG SAMA
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		model.getFtPurchasedJpaService().updateObject(model.itemDetil);
		view.getTableDetil().addItem(model.itemDetil);
		
		//4. UPDATE STOK ::EDIT
		List<FtPurchased> listForStockAdd = new ArrayList<FtPurchased>();
		listForStockAdd.add(model.getItemDetil());		
		model.getProductAndStockHelper().incomingStockAdd(model.getItemHeader().getFwarehouseBean(), listForStockAdd, model.getItemHeader().getPodate());
		
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
                    		//1.VALIDASI :: ADA DIATAS
                    		
                    		
                    		//2. HAPUS ITEM DETIL
                    			model.getFtPurchasedJpaService().removeObject(model.getItemDetil());
                    		
                    		//3. TAMPILAN KEADAAN KOSONG
                    			model.getBeanItemContainerDetil().removeItem(model.getItemDetil());
                    			
                    		//4. UPDATE STOCK::JIKA KONDISI EDIT
                    		if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())) {
                    			List<FtPurchased> listForStock = new ArrayList<FtPurchased>();
                    			listForStock.add(model.getItemDetil());                    			
                    			model.getProductAndStockHelper().incomingStockRemove(model.getItemHeader().getFwarehouseBean(), listForStock, model.getItemHeader().getPodate());
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
			model.itemHeader.setNopo(model.getTransaksiHelper().getNextFtPurchasehRefno());
			//3. SAVE ULANG HEADER
			model.getFtPurchasehJpaService().updateObject(model.itemHeader);
			
			//4. UPDATE STOCK :: UPDATE STOK TERJADI SETELAH DI SAVE ATAU DITERBITKANNYA NOMOR PO			
			Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
			List<FtPurchased> listForStock = new ArrayList<FtPurchased>();
			for (Object itemId: itemIds) {
				FtPurchased domain = new FtPurchased();
				domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				listForStock.add(domain);
			}
			model.getProductAndStockHelper().incomingStockAdd(model.getItemHeader().getFwarehouseBean(), listForStock, model.getItemHeader().getPodate());

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
			model.getFtPurchasehJpaService().updateObject(model.getItemHeader());
			
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
			FtPurchased domain = new FtPurchased();
			try{
				domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				model.getFtPurchasedJpaService().removeObject(domain);
			} catch(Exception ex){}
		}
		model.getFtPurchasehJpaService().removeObject(model.itemHeader);
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
		
		model.itemHeader.setNopo("");
		model.itemHeader.setInvoiceno("");
		model.itemHeader.setPodate(model.getTransaksiHelper().getCurrentTransDate());
		model.itemHeader.setInvoicedate(model.getTransaksiHelper().getCurrentTransDate());
		model.itemHeader.setDuedate(model.getTransaksiHelper().getCurrentTransDate());
		model.itemHeader.setAmount(0.0);
		model.itemHeader.setAmountpay(0.0);
		model.itemHeader.setDisc(0.0);
		model.itemHeader.setDisc1(0.0);
		model.itemHeader.setDisc2(0.0);
		model.itemHeader.setPpnpercent(10.0);
		model.itemHeader.setSaldo(false);
		model.itemHeader.setPrintcounter(0);
		model.itemHeader.setTipefaktur("F");
		model.itemHeader.setTunaikredit("T");
		model.itemHeader.setLunas(false);
		
	}
	public void resetHeader(){
		
		model.itemHeader.setNopo("");
		model.itemHeader.setInvoiceno("");
		model.itemHeader.setPodate(model.getTransaksiHelper().getCurrentTransDate());
		model.itemHeader.setInvoicedate(model.getTransaksiHelper().getCurrentTransDate());
		model.itemHeader.setAmount(0.0);
		model.itemHeader.setAmountpay(0.0);
		model.itemHeader.setDisc(0.0);
		model.itemHeader.setDisc1(0.0);
		model.itemHeader.setDisc2(0.0);
		model.itemHeader.setPpnpercent(10.0);
		model.itemHeader.setSaldo(false);
		
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
                			model.getFtPurchasehJpaService().updateObject(model.itemHeader);
                		}
                   		if (model.itemHeader.getEndofday() != true) {                    		
                    		
                    			
	                    		//2. DELETE STOK DAN HEADER
                    			List<FtPurchased> listForStock = new ArrayList<FtPurchased>();
                    			listForStock = deleteFtPurchasehAndFtPurchsed();
	                    		
	                    		//3. UPDATE STOCK
	                    		if (! model.getItemHeader().getNopo().equalsIgnoreCase("New")) {
	                    			model.getProductAndStockHelper().incomingStockRemove(model.getItemHeader().getFwarehouseBean(), listForStock, model.getItemHeader().getPodate());
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

	private List<FtPurchased> deleteFtPurchasehAndFtPurchsed(){
		//2. HAPUS DETIL :: HAPUS HEADER
		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
		List<FtPurchased> listForStock = new ArrayList<FtPurchased>();		
		for (Object itemId: itemIds){
			FtPurchased domain = new FtPurchased();	                    			
			domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
			
			//HAPUS ANAK DETIL
			deleteFtSalesdChild(domain);
			//HAPUS DETIL
			model.getFtPurchasedJpaService().removeObject(domain);

			domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
			listForStock.add(domain);
			
		}
		
		model.getFtPurchasehJpaService().removeObject(model.getItemHeader());
		model.getBeanItemContainerHeader().removeItem(model.getItemHeader());
		
		return listForStock;
		
	}
	private void deleteFtSalesdChild(FtPurchased ftPurchased){
		FtPurchased ftPurchasedFromDatabase = new FtPurchased();
		ftPurchasedFromDatabase = ftPurchased;

		//GAK PAKE PROMO PEMBELIAN
//		List<FtSalesdPromoTprb> listFtSalesdPromoTprb =
//				new ArrayList<FtSalesdPromoTprb>(ftSalesdFromDatabase.getFtsalesdPromoTprbList());
//		for (FtSalesdPromoTprb childDomain: listFtSalesdPromoTprb){
//			model.getFtSalesdPromoTprbJpaService().removeObject(childDomain);
//		}
//		List<FtSalesdPromoTpruDisc> listFtSalesdPromoTpruDisc =
//				new ArrayList<FtSalesdPromoTpruDisc>(ftSalesdFromDatabase.getFtsalesdPromoTpruDiscList() );
//		for (FtSalesdPromoTpruDisc childDomain: listFtSalesdPromoTpruDisc){
//			model.getFtSalesdPromoTpruDiscJpaService().removeObject(childDomain);
//		}
//		List<FtSalesdPromoTpruCb> listFtSalesdPromoTpruCb =
//				new ArrayList<FtSalesdPromoTpruCb>(ftSalesdFromDatabase.getFtsalesdPromoTpruCbList());
//		for (FtSalesdPromoTpruCb childDomain: listFtSalesdPromoTpruCb){
//			model.getFtSalesdPromoTpruCbJpaService().removeObject(childDomain);
//		}
		
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
	
	public void printForm(){		
		if (! model.getItemHeader().getNopo().trim().equals("New") ) {
			//PREVIEW INVOICE
			previewInvoice();
		}
	}
	
	public void previewInvoice(){
		String inputFilePath = "";
		
//		if (model.getSysvarHelper().getFormatFaktur()==1) {
//			inputFilePath = "/erp/distribution/reports/salesorder/invoice1/standart1.jasper";
//		} else if (model.getSysvarHelper().getFormatFaktur()==2) {
//			inputFilePath = "/erp/distribution/reports/salesorder/invoice2/standart2.jasper";
//		} else if (model.getSysvarHelper().getFormatFaktur()==10) {
//			inputFilePath = "/erp/distribution/reports/invoicestd1/invoicestd1.jasper";
//		} else if (model.getSysvarHelper().getFormatFaktur()==11) {
//			inputFilePath = "/erp/distribution/reports/invoicestd2/invoicestd2.jasper";
//		}
		
		inputFilePath = "/erp/distribution/reports/purchase/purchasestd2/purchasestd2.jasper";
		
		String outputFilePath = "purchaseorder_invoice";
		
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
//		if (model.getItemHeader().getTipefaktur().equals("R")) {
//			parameters.put("paramJudulFaktur", model.getSysvarHelper().getJudulFakturRetur());
//		}else if (model.getItemHeader().getTunaikredit().equals("T")) {
//			parameters.put("paramJudulFaktur", model.getSysvarHelper().getJudulFakturTunai());
//		}else if (model.getItemHeader().getTunaikredit().equals("K"))  {
//			parameters.put("paramJudulFaktur", model.getSysvarHelper().getJudulFakturKredit());			
//		} else {
			parameters.put("paramJudulFaktur", "FAKTUR");						
//		}
			parameters.put("paramJudulFakturTunai", "FAKTUR PEMBELIAN");
			parameters.put("paramJudulFakturKredit", "FAKTUR PEMBELIAN");			
			parameters.put("paramJudulFakturRetur", "RETUR PEMBELIAN PABRIK");			

		parameters.put("paramTipefaktur","F");

		parameters.put("paramCompanyName", model.getSysvarHelper().getCompanyNameFaktur());
		parameters.put("paramCompanyAddress", model.getSysvarHelper().getCompanyAddressFaktur());
		parameters.put("paramCompanyPhone", model.getSysvarHelper().getCompanyPhoneFaktur());
		parameters.put("paramCompanyNpwp", model.getSysvarHelper().getCompanyNpwpFaktur());

		parameters.put("paramInvoicedateFrom",model.itemHeader.getInvoicedate());
		parameters.put("paramInvoicedateTo",model.itemHeader.getInvoicedate());
		parameters.put("paramRefnoFrom",model.itemHeader.getRefno());
		parameters.put("paramRefnoTo",model.itemHeader.getRefno());
		parameters.put("paramInvoiceno","%");

		
		parameters.put("paramRefno", model.itemHeader.getRefno());
		
		parameters.put("paramProductShortname", model.getSysvarHelper().isShortNamePadaFaktur());


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
		
		String fileName = "salesorder_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new_print_nota_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
		
	}
	
	public void helpForm(){
	}
	public IncomingStockModel getModel() {
		return model;
	}
	public void setModel(IncomingStockModel model) {
		this.model = model;
	}
	public IncomingStockView getView() {
		return view;
	}
	public void setView(IncomingStockView view) {
		this.view = view;
	}


	
	
}
