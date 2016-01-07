package org.erp.distribution.salesorder.salesorder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.tools.ant.types.selectors.modifiedselector.EqualComparator;
import org.erp.distribution.model.FCustomer;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FStock;
import org.erp.distribution.model.FtPurchased;
import org.erp.distribution.model.FtPurchasedPK;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesdPK;
import org.erp.distribution.model.FtSalesdPromoTprb;
import org.erp.distribution.model.FtSalesdPromoTpruCb;
import org.erp.distribution.model.FtSalesdPromoTpruDisc;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.salesorder.salesorder.windowitem.SalesOrderItemHelper;
import org.erp.distribution.util.ProductAndStockHelper;
import org.erp.distribution.util.ReportJdbcConfigHelper;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.Compare;
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

public class SalesOrderPresenter implements ClickListener, ValueChangeListener, ItemClickListener, BlurListener{
	private SalesOrderModel model;
	private SalesOrderView view;
	
	SalesOrderHelper helper;
	
	public SalesOrderPresenter(SalesOrderModel model, SalesOrderView view){
		this.model = model;
		this.view = view;
		helper = new SalesOrderHelper(model, view);
		
		initListener();
		
	}
	
	public void initListener(){
		view.getBtnNewForm().addClickListener(this);
		view.getBtnEditForm().addClickListener(this);
		view.getBtnDeleteForm().addClickListener(this);
		view.getBtnHelp().addClickListener(this);
		view.getBtnPrint().addClickListener(this);
		view.getBtnPrintRetail().addClickListener(this);
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
				view.getDateFieldInvoicedate().setValue(view.getDateFieldOrderdate().getValue());
				try{
					tunaiKreditChange();
				} catch(Exception ex){}
			}
		};
		view.getDateFieldOrderdate().addValueChangeListener(listenerValueChangeOrderDate);

		ValueChangeListener listenerValueChangeInvoiceDate = new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				try{
					if (view.getDateFieldInvoicedate().getValue().getTime() < view.getDateFieldOrderdate().getValue().getTime()) {
						view.getDateFieldInvoicedate().setValue(view.getDateFieldOrderdate().getValue());					
						Notification.show("Tanggal Invoice lebih kecil dari pada tanggal Order", Notification.TYPE_TRAY_NOTIFICATION);
						
					} 
				} catch(Exception ex){}
				try{
					tunaiKreditChange();
				} catch(Exception ex){}
				
			}
		};
		view.getDateFieldInvoicedate().addValueChangeListener(listenerValueChangeInvoiceDate);
		
		ValueChangeListener listenerCheckSearch1 = new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				view.getBtnSearch().click();
			}
		};
		view.getCheckSearch1().addValueChangeListener(listenerCheckSearch1);
		
		ValueChangeListener listenerComboTunaiKredit = new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				tunaiKreditChange();
			}
		};
		view.getComboTunaikredit().addValueChangeListener(listenerComboTunaiKredit);

		ValueChangeListener listenerComboTop = new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				topChange();
			}
		};
		view.getComboTop().addValueChangeListener(listenerComboTop);
		
		//PENGECEKAN KREDIT LIMIT DAN OPEN INVOICE
		ValueChangeListener listenerComboCustomer = new ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {				
				//1. CARI TRANSAKSI CUSTOMER YANG BELUM LUNAS
				//2. TAMPILKAN PESAN FAKTUR YANG BELUM KEBAYAR
				//TRY-CATCH UNTUK MENGHINDARI ERROR PADA SAAT NEW
				try{
					FCustomer fCustomer = new FCustomer();
					fCustomer = (FCustomer) view.getComboCustomer().getValue();
					List<FtSalesh> listFakturBelumTerbayar = model.getFtSaleshJpaService().findAllOpenInvoice(fCustomer);
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					
					if (listFakturBelumTerbayar.size()>0){
						if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode()) || 
								model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
							view.getBtnSaveForm().setEnabled(false);							
							view.getBtnPrint().setEnabled(false);
							view.getBtnPrintRetail().setEnabled(false);
						}
						
						String messageFakturBelumTerbayar = "NOTA BELUM TERBAYAR: \n";
						int i=0;
						for (FtSalesh domain: listFakturBelumTerbayar){
							messageFakturBelumTerbayar += "NOTA: " + domain.getInvoiceno() + 
									", TGL: " + sdf.format(domain.getInvoicedate()) + 
									", JUMLAH: " + domain.getAmountafterdiscafterppn() + 
									", BAYAR: " + domain.getAmountpay() +", \n";
						}
						
						Notification.show(messageFakturBelumTerbayar, Notification.TYPE_ERROR_MESSAGE);
						
					} else {
						if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode()) || 
								model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
							view.getBtnSaveForm().setEnabled(true);			
							if (! model.getItemHeader().getInvoiceno().equals("")){
								view.getBtnPrint().setEnabled(true);
								view.getBtnPrintRetail().setEnabled(false);
							}
						}
					}
				} catch(Exception ex){}
				
				
			}
		};
		view.getComboCustomer().addValueChangeListener(listenerComboCustomer);
		
		
	}

	@Override
	public void valueChange(ValueChangeEvent event) {
	}
	
	public void tunaiKreditChange(){
		Calendar calDueDate = Calendar.getInstance();
		calDueDate.setTime(view.getDateFieldInvoicedate().getValue());

		if (view.getComboTunaikredit().getValue().equals("T")) {
			view.getComboTop().setValue(0);
		} else if (view.getComboTunaikredit().getValue().equals("K")) {
			view.getComboTop().setValue(model.getSysvarHelper().getDueDate());
			
		}
		calDueDate.add(Calendar.DATE, (Integer) view.getComboTop().getValue());
		view.getDateFieldDuedate().setValue(calDueDate.getTime());
		
		
	}
	public void topChange(){
		Calendar calDueDate = Calendar.getInstance();
		calDueDate.setTime(view.getDateFieldInvoicedate().getValue());
		calDueDate.add(Calendar.DATE, (Integer) view.getComboTop().getValue());
		view.getDateFieldDuedate().setValue(calDueDate.getTime());
		
	}
	@Override
	public void itemClick(ItemClickEvent event) {
		if (event.getComponent()==view.getTableDetil()){
			Object itemId = event.getItemId();
			
			Item item = view.getTableDetil().getItem(itemId);
			boolean entitySelected = item != null;
			
			if (entitySelected) {
				
				model.itemDetil = new FtSalesd();
				model.itemDetil = model.getBeanItemContainerDetil().getItem(itemId).getBean();
				model.getBinderDetil().setItemDataSource(model.itemDetil);

				//FOR DELETE STOK
				model.itemDetilBookmark = new FtSalesd();
				FtSalesdPK idBookmark = new FtSalesdPK();
				idBookmark.setId(model.getItemDetil().getId().getId());
				idBookmark.setRefno(model.getItemDetil().getId().getRefno());
				model.itemDetilBookmark.setId(idBookmark);				
				model.itemDetilBookmark.setFproductBean(model.getItemDetil().getFproductBean());
				model.itemDetilBookmark.setFtsaleshBean(model.itemDetil.getFtsaleshBean());				
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
				
				model.itemHeader = new FtSalesh();
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
				//HAPUS SEMUA  FROMO
				helper.deleteNonPromoItem();
				//JIKA KONDISI NOPO=NEW ::MAKA MODE ADD NEW
				try{
					if (model.itemHeader.getOrderno().equals("New")) {
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
			if (view.getComboTipeJual().getValue() ==null){				
				Notification.show("Tipe Jual belum dipilih!", Notification.TYPE_TRAY_NOTIFICATION);
				view.getComboTipeJual().focus();
			}else if (view.getComboSalesman().getValue() ==null){				
				Notification.show("Salesman belum dipilih!", Notification.TYPE_TRAY_NOTIFICATION);
				view.getComboSalesman().focus();
			}else if(view.getComboCustomer().getValue() ==null){				
				Notification.show("Customer belum dipilih!", Notification.TYPE_TRAY_NOTIFICATION);
				view.getComboCustomer().focus();
			}else if (view.getComboWarehouse().getValue()==null) {
				Notification.show("Warehouse belum dipilih!", Notification.TYPE_TRAY_NOTIFICATION);
				view.getComboWarehouse().focus();
			}else {
				addItemDetil();
				view.getItemDetilView().setFormButtonAndTextState();
			}
		}else if (event.getButton() == view.getBtnEditItem()){			
			
			if (model.getItemDetil().getId()!=null){
				if (view.getComboWarehouse().getValue()!=null) {
					editItemDetil();
					view.getItemDetilView().setFormButtonAndTextState();
				}else {
					Notification.show("Warehouse belum dipilih!", Notification.TYPE_TRAY_NOTIFICATION);
					view.getComboWarehouse().focus();					
				}
			}
			
		}else if (event.getButton() == view.getBtnRemoveItem()){
			if (model.getItemDetil().getId()!=null){
				if (view.getComboWarehouse().getValue()!=null) {
					removeItemDetil();
				}else {
					Notification.show("Warehouse belum dipilih!", Notification.TYPE_TRAY_NOTIFICATION);
					view.getComboWarehouse().focus();					
				}
			}
		} else if (event.getButton() == view.getBtnSearch()) {
			searchForm();
		} else if (event.getButton() == view.getBtnPrint()){			
			printForm();
		} else if (event.getButton() == view.getBtnPrintRetail()){			
			printFormRetail();
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
			try{
				helper.calculateParamDiskonNota();
				helper.updateAndCalculateHeaderByItemDetil();
			} catch(Exception ex){}
		}else if (event.getButton() == view.getItemDetilView().getBtnClose()){
			view.closeWindowForm();
			
			helper.calculateParamDiskonNota();
			helper.updateAndCalculateHeaderByItemDetil();
		}
		//Tidak semua akan di refresh container nya >> Jadi refresh container tidak bisa di taruh disini
		
	}

	public void addItemHeader(){
		//TEEMPORER
		model.itemHeaderTemp = new FtSalesh();
		model.itemHeaderTemp.setFsalesmanBean(model.itemHeader.getFsalesmanBean());
		model.itemHeaderTemp.setFwarehouseBean(model.itemHeader.getFwarehouseBean());
		model.itemHeaderTemp.setOrderdate(model.itemHeader.getOrderdate());
		model.itemHeaderTemp.setInvoicedate(model.itemHeader.getInvoicedate());
		model.itemHeaderTemp.setDuedate(model.itemHeader.getDuedate());
		model.itemHeaderTemp.setTipejual(model.itemHeader.getTipejual());
		
		model.itemHeader = new FtSalesh();

		resetNewHeader();
		model.itemHeader.setOrderno("New");
		
		//2. SET NEW DATA ITEM TO BINDER
		model.getFtSaleshJpaService().createObject(model.itemHeader);
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
		
		model.itemDetil = new FtSalesd();
		FtSalesdPK id = new FtSalesdPK();
		id.setRefno(model.getItemHeader().getRefno());
		id.setId((long) 0);
		id.setFreegood(false);
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
		
		//1. BUAT ITEM DETIL BARU
		model.itemDetil = new FtSalesd();
		model.itemDetil = view.getItemDetilModel().getItemDetil();
		//2. ANTISIPASI
		FtSalesdPK id = new FtSalesdPK();
		id.setRefno(model.getItemHeader().getRefno());
		id.setId(view.getItemDetilModel().getItemDetil().getFproductBean().getId());
		id.setFreegood(view.getItemDetilModel().getItemDetil().getId().getFreegood());
		model.itemDetil.setId(id);	
	
		model.getFtSalesdJpaService().updateObject(model.itemDetil);
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		view.getTableDetil().addItem(model.itemDetil);
		
		try{
			view.getItemDetilView().getBtnClose().click();
		} catch(Exception ex){}
		view.getBtnAddItem().click();
		
		//PENJUMLAHAN FOOTER
		view.setDisplayTableFooterDetil();
		helper.updateAndCalculateHeaderByItemDetil();
		
	}
	public void saveAddOrUpdateItemEditFromNewForm(){
		//1. HAPUS TPRB, TPRUDISC, TPRUCB
//		Collection itemIdsForDelete = model.getBeanItemContainerDetil().getItemIds();
//		for (Object itemId: itemIdsForDelete){
//			FtSalesd domain = new FtSalesd();
//			domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
//			List<FtSalesdPromoTprb> listFtSalesdPromoTprb = new ArrayList<FtSalesdPromoTprb>(domain.getFtsalesdPromoTprbList());
//			for (FtSalesdPromoTprb domain2: listFtSalesdPromoTprb) {
//
//				System.out.println("TPRB: " + domain2.getRefnoPromo());
//				model.getFtSalesdPromoTprbJpaService().removeObject(domain2);
//			}
//			List<FtSalesdPromoTpruDisc> listFtSalesdPromoTpruDisc = new ArrayList<FtSalesdPromoTpruDisc>(domain.getFtsalesdPromoTpruDiscList());
//			for (FtSalesdPromoTpruDisc domain2: listFtSalesdPromoTpruDisc) {
//				model.getFtSalesdPromoTpruDiscJpaService().removeObject(domain2);
//				System.out.println("TPRU: " + domain2.getRefnoPromo());
//			}
//			
//		}
//		model.itemDetil.setFtsalesdPromoTprbList(new ArrayList<FtSalesdPromoTprb>());
//		model.itemDetil.setFtsalesdPromoTpruCbList(new ArrayList<FtSalesdPromoTpruCb>());
//		model.itemDetil.setFtsalesdPromoTpruDiscList(new ArrayList<FtSalesdPromoTpruDisc>());
//		List<FtSalesd> listFtSalesdTable = new ArrayList<FtSalesd>(
//				model.getFtSalesdJpaService().findAllDetilByRefno(model.itemHeader.getRefno()));
//		for (FtSalesd domain: listFtSalesdTable) {
//			List<FtSalesdPromoTprb> listFtSalesdTprb = new ArrayList<FtSalesdPromoTprb>(domain.getFtsalesdPromoTprbList());
//			System.out.println("UKURANG: " +listFtSalesdTprb.size());
//			for (FtSalesdPromoTprb domain2: listFtSalesdTprb) {
//				System.out.println("abcd");
////				model.getFtSalesdPromoTprbJpaService().removeObject(domain2);
////				System.out.println(domain2.getRefnoPromo() + ":" + domain2.getfProductBean().getId());
//			}
//		}
		//1.1 HAPUS ITEM YANG ADA DI DALAMNYA
		//2. HITUNG BONUS LAGI
		//2.1 TAMBAHKAN LAGI
		
		//1. HAPUS DULU PROMONYA
//		List<FtSalesd> listFtSalesdForDeleteFind = 
//				new ArrayList<FtSalesd>(model.getFtSalesdJpaService().findAllById(model.getItemDetil().getId().getRefno(),
//						model.getItemDetil().getId().getId(), false));
//		List<FtSalesd> listFtSalesdForDelete = 
//				new ArrayList<FtSalesd>();
//
//		if (listFtSalesdForDeleteFind.size() > 0) {		
//			for (FtSalesd ftSalesd: listFtSalesdForDeleteFind) {
//				List<FtSalesdPromoTprb> listFtSalesdPromoTprb = new ArrayList<FtSalesdPromoTprb>(ftSalesd.getFtsalesdPromoTprbList());
//				for (FtSalesdPromoTprb ftSalesdPromoTprb: listFtSalesdPromoTprb) {
//					Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
//					try{
//						for (Object itemId: itemIds){
//							FtSalesd ftSalesdPromo = new FtSalesd();
//							ftSalesdPromo = model.getBeanItemContainerDetil().getItem(itemId).getBean();
//							if (ftSalesdPromo.getId().getId()==ftSalesdPromoTprb.getfProductBean().getId()){
//								model.getFtSalesdJpaService().removeObject(ftSalesd);
//								model.getBeanItemContainerDetil().removeItem(itemId);
//							}
//						}
//					} catch(Exception ex){}
//					
//				}
//			}
//		} else {
//			listFtSalesdForDeleteFind = 
//					new ArrayList<FtSalesd>(model.getFtSalesdJpaService().findAllById(
//							model.getItemDetil().getId().getId(), false));			
//			for (FtSalesd ftSalesd: listFtSalesdForDeleteFind) {
//				List<FtSalesdPromoTprb> listFtSalesdPromoTprb = new ArrayList<FtSalesdPromoTprb>(ftSalesd.getFtsalesdPromoTprbList());
//				for (FtSalesdPromoTprb ftSalesdPromoTprb: listFtSalesdPromoTprb) {
//					Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
//					try{
//						for (Object itemId: itemIds){
//							FtSalesd ftSalesdPromo = new FtSalesd();
//							ftSalesdPromo = model.getBeanItemContainerDetil().getItem(itemId).getBean();
//							if (ftSalesdPromo.getId().getId()==ftSalesdPromoTprb.getfProductBean().getId()){
//								model.getFtSalesdJpaService().removeObject(ftSalesd);
//								model.getBeanItemContainerDetil().removeItem(itemId);
//							}
//						}
//					} catch(Exception ex){}
//					
//				}
//				
//			}
//		}
		
		//2. BERIKAN BONUS BARANG YANG BARU JIKA MASUK BARU
		
//		//1. BUAT BARU DENGAN ITEM YANG SAMA NAMUN
//		model.itemDetil = new FtSalesd();
//		model.itemDetil = view.getItemDetilModel().getItemDetil();
//		//REFRESH DARI DATABASE
//		List<FtSalesd> listFtSalesdForDeleteForRefresh = 
//				new ArrayList<FtSalesd>(model.getFtSalesdJpaService().findAllById(model.getItemDetil().getId().getRefno(),
//						model.getItemDetil().getId().getId(), false));
//		if (listFtSalesdForDeleteForRefresh.size()>0){
//			model.itemDetil = listFtSalesdForDeleteForRefresh.get(0);
//		}else {
//			listFtSalesdForDeleteForRefresh = 
//					new ArrayList<FtSalesd>(model.getFtSalesdJpaService().findAllById(
//							model.getItemDetil().getId().getId(), false));
//			if (listFtSalesdForDeleteForRefresh.size() > 0 ){
//				model.itemDetil = listFtSalesdForDeleteForRefresh.get(0);				
//			}
//			
//		}
	
		
//		//2. ANTISIPASI
//		FtSalesdPK id = new FtSalesdPK();
//		id.setRefno(model.getItemHeader().getRefno());
//		id.setId(view.getItemDetilModel().getItemDetil().getFproductBean().getId());
//		id.setFreegood(view.getItemDetilModel().getItemDetil().getId().getFreegood());
//		model.itemDetil.setId(id);
		
		//3. UPDATE ITEM YANG SAMA
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		model.getFtSalesdJpaService().updateObject(model.itemDetil);
		
//		//TAMBAHKAN BONUS LAGI DI BAWAH
//		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
//		for (Object itemId: itemIds) {
//			FtSalesd ftSalesd = new FtSalesd();
//			ftSalesd = (FtSalesd) model.getBeanItemContainerDetil().getItem(itemId).getBean();
//					
//		}
		
		
//		List<FtSalesdPromoTprb> listBonusBarang = new ArrayList<FtSalesdPromoTprb>(model.itemDetil.getFtsalesdPromoTprbList());
//		
//		for(FtSalesdPromoTprb ftSalesdPromoTprb: listBonusBarang) {
//			FtSalesd freeBonusBarang = new FtSalesd();
//			generateResetFsalesd(freeBonusBarang);
//			
//			
//			FtSalesdPK freeId = new FtSalesdPK();
//			freeId.setFreegood(true);
//			freeId.setRefno(model.itemDetil.getId().getRefno());
//			freeId.setId(ftSalesdPromoTprb.getfProductBean().getId());
//			
//			freeBonusBarang.setId(freeId);
//			freeBonusBarang.setFproductBean(ftSalesdPromoTprb.getfProductBean());
//			freeBonusBarang.setFtsaleshBean(model.itemDetil.getFtsaleshBean());
//			
//			freeBonusBarang.setQty(ftSalesdPromoTprb.getTprbqty());
//						
//			freeBonusBarang.setNourut(200);
//			//UPDATE KE DATABSE SECARA MANDIRI
//			//JIKA SUDAH ADA :: CEK DARI CONTAINER SAJA:: MAKA TAMBAHKAN
//			Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
//			for (Object itemId: itemIds) {
//				FtSalesd ftSalesd = new FtSalesd();
//				ftSalesd = (FtSalesd) model.getBeanItemContainerDetil().getItem(itemId).getBean();
//				if (ftSalesd.getId().equals(freeBonusBarang.getId())){
//					freeBonusBarang.setQty(freeBonusBarang.getQty() + ftSalesd.getQty());
//
//					model.getBeanItemContainerDetil().removeItem(ftSalesd);
//					
//				}
//						
//			}
//			//PERBAIKI BESAR SEDANG KECIL
//			freeBonusBarang.setQty1(model.getProductAndStockHelper().getBesFromPcs(
//					freeBonusBarang.getQty(), freeBonusBarang.getFproductBean()));
//			freeBonusBarang.setQty2(model.getProductAndStockHelper().getSedFromPcs(
//					freeBonusBarang.getQty(), freeBonusBarang.getFproductBean()));
//			freeBonusBarang.setQty3(model.getProductAndStockHelper().getKecFromPcs(
//					freeBonusBarang.getQty(), freeBonusBarang.getFproductBean()));
//			
//			model.getFtSalesdJpaService().updateObject(freeBonusBarang);
//			model.getBeanItemContainerDetil().addItem(freeBonusBarang);
//			
//			System.out.println("DIA: " + ftSalesdPromoTprb.getfProductBean() + ":" + ftSalesdPromoTprb.getFtSalesdBean().getFproductBean());
//
//		}
//		

		
		//NEW FORM UPDATE STOCK SAAT TOMBOL SAVE
		view.getItemDetilView().getBtnClose().click();
		view.fillComponentDetilItem();
		helper.updateAndCalculateHeaderByItemDetil();
		
		
		
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
		model.itemDetil = new FtSalesd();
		model.itemDetil = view.getItemDetilModel().getItemDetil();
		
		//2. LANGSUNG DIMASUKKAN KE DATABASE :: LALU TAMBAH ITEM PADA TABEL
		model.getFtSalesdJpaService().updateObject(model.itemDetil);
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		
		//3. UPDATE STOK ::TAMBAH
		List<FtSalesd> listForStockAdd = new ArrayList<FtSalesd>();
		listForStockAdd.add(model.getItemDetil());
		
		model.getProductAndStockHelper().soStockAdd(model.getItemHeader().getFwarehouseBean(), listForStockAdd, model.getItemHeader().getInvoicedate());
		
		view.setDisplayTableFooterDetil();
		
		view.getItemDetilView().getBtnClose().click();
		view.getBtnAddItem().click();
		
		helper.updateAndCalculateHeaderByItemDetil();
		
		
		
	}
	
	public void saveAddOrUpdateItemEditFromEditForm(){
		
		//1. HAPUS STOK
		List<FtSalesd> listForStockRemove = new ArrayList<FtSalesd>();
		listForStockRemove.add(model.itemDetilBookmark);
		model.getProductAndStockHelper().soStockRemove(model.getItemHeader().getFwarehouseBean(),
				listForStockRemove, model.getItemHeader().getInvoicedate());	

		//2. ANTISIPASI
		FtSalesdPK id = new FtSalesdPK();
		id.setRefno(model.getItemHeader().getRefno());
		id.setId(view.getItemDetilModel().getItemDetil().getFproductBean().getId());
		id.setFreegood(view.getItemDetilModel().getItemDetil().getId().getFreegood());
		model.itemDetil.setId(id);	
		
		//3. UPDATE ITEM YANG SAMA
		model.getBeanItemContainerDetil().addItem(model.itemDetil);
		model.getFtSalesdJpaService().updateObject(model.itemDetil);
		
		
		
		//4. UPDATE STOK ::EDIT
		List<FtSalesd> listForStockAdd = new ArrayList<FtSalesd>();
		listForStockAdd.add(model.getItemDetil());
		
		model.getProductAndStockHelper().soStockAdd(model.getItemHeader().getFwarehouseBean(), listForStockAdd, model.getItemHeader().getInvoicedate());
		
		view.getItemDetilView().getBtnClose().click();
		view.fillComponentDetilItem();
		helper.updateAndCalculateHeaderByItemDetil();
		
		
	}
	
	
	public FtSalesd generateResetFsalesd(FtSalesd ftSalesd){
//		FtSalesd ftSalesd = new FtSalesd();
		FtSalesdPK freeId = new FtSalesdPK();
		freeId.setFreegood(false);
		freeId.setRefno((long) 0);
		freeId.setId((long) 0);
		
		ftSalesd.setId(freeId);

//		freeBonusBarang.setFproductBean(new FProduct());
//		freeBonusBarang.setFtsaleshBean(new FtSalesh());
		
		ftSalesd.setQty(0);
		ftSalesd.setQty1(0);
		ftSalesd.setQty2(0);
		ftSalesd.setQty3(0);
		
		
		ftSalesd.setDisc1(0.0);
		ftSalesd.setDisc1rp(0.0);			
		ftSalesd.setDisc1rpafterppn(0.0);
		ftSalesd.setDisc2(0.0);
		ftSalesd.setDisc2rp(0.0);
		ftSalesd.setDisc2rpafterppn(0.0);
		ftSalesd.setSprice(0.0);
		ftSalesd.setSpriceafterppn(0.0);
		
		ftSalesd.setSubtotal(0.0);
		ftSalesd.setSubtotalafterdisc(0.0);
		ftSalesd.setSubtotalafterdiscafterppn(0.0);
		ftSalesd.setSubtotalafterppn(0.0);
		
		ftSalesd.setTprb(0.0);
		ftSalesd.setTprucashback(0.0);
		ftSalesd.setTprudisc(0.0);
		
		ftSalesd.setNourut(0);
		
		return ftSalesd;
		
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
                    		//HAPUS DULU CHILD TABLE DARI ftSalesd
                    		List<FtSalesdPromoTprb> listFtSalesdPromoTprb = 
                    				new ArrayList<FtSalesdPromoTprb>(model.getItemDetil().getFtsalesdPromoTprbList());
                    		List<FtSalesd> listFtSalesdToDeleteOrUpdate = new ArrayList<FtSalesd>();
                    		for (FtSalesdPromoTprb domain: listFtSalesdPromoTprb){
                    			
                    			model.getFtSalesdPromoTprbJpaService().removeObject(domain);
                    			
                    			Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
                    			for (Object itemId: itemIds){
                    				FtSalesd ftSalesd = new FtSalesd();
                    				ftSalesd = model.getBeanItemContainerDetil().getItem(itemId).getBean();
                    				Integer newFtSalesdFreeQty=ftSalesd.getQty();

                    				//JIKA ADA
                    				if (ftSalesd.getId().getFreegood()==null) {
                    					ftSalesd.getId().setFreegood(false);
                    				}
                    				if (ftSalesd.getFproductBean().getId()==domain.getfProductBean().getId() && 
                						ftSalesd.getId().getFreegood()==true){  
                    					
                						newFtSalesdFreeQty = ftSalesd.getQty()-domain.getTprbqty();
                						ftSalesd.setQty(newFtSalesdFreeQty);
                						
                						ftSalesd.setQty1(model.getProductAndStockHelper()
                								.getBesFromPcs(ftSalesd.getQty(), ftSalesd.getFproductBean()));
                						ftSalesd.setQty2(model.getProductAndStockHelper()
                								.getSedFromPcs(ftSalesd.getQty(), ftSalesd.getFproductBean()));
                						ftSalesd.setQty3(model.getProductAndStockHelper()
                								.getKecFromPcs(ftSalesd.getQty(), ftSalesd.getFproductBean()));
                						
                						listFtSalesdToDeleteOrUpdate.add(ftSalesd);
                    				}
                    			}
                    					
                    		}
                    		List<FtSalesdPromoTpruDisc> listFtSalesdPromoTpruDisc = 
                    				new ArrayList<FtSalesdPromoTpruDisc>(model.getItemDetil().getFtsalesdPromoTpruDiscList());
                    		for (FtSalesdPromoTpruDisc domain: listFtSalesdPromoTpruDisc){
                    			model.getFtSalesdPromoTpruDiscJpaService().removeObject(domain);
                    		}
                    		List<FtSalesdPromoTpruCb> listFtSalesdPromoTpruCb = 
                    				new ArrayList<FtSalesdPromoTpruCb>(model.getItemDetil().getFtsalesdPromoTpruCbList());
                    		for (FtSalesdPromoTpruCb domain: listFtSalesdPromoTpruCb){
                    			model.getFtSalesdPromoTpruCbJpaService().removeObject(domain);
                    		}
                    		
                    		//2. HAPUS ITEM DETIL::BONUS DULU TAPI AKAN DITAMBAHKAN LAGI
                    		if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode()) &&
                    				(! model.getItemHeader().getInvoiceno().trim().equals(""))) {

                    			model.getProductAndStockHelper().soStockRemove(model.getItemHeader().getFwarehouseBean(),
                    					listFtSalesdToDeleteOrUpdate, model.getItemHeader().getInvoicedate());
                    		}
                    		//UPDATE TABEL DAN DATABASE
                    		for (FtSalesd ftSalesdToDeleteOrUpdate: listFtSalesdToDeleteOrUpdate){
                    			model.getBeanItemContainerDetil().removeItem(ftSalesdToDeleteOrUpdate);
                    			if (ftSalesdToDeleteOrUpdate.getQty() <= 0 ){
                        			model.getFtSalesdJpaService().removeObject(ftSalesdToDeleteOrUpdate);                    				
                    			}else {
                        			model.getFtSalesdJpaService().updateObject(ftSalesdToDeleteOrUpdate);                    				                    				
                        			model.getBeanItemContainerDetil().addItem(ftSalesdToDeleteOrUpdate);
                    			}
                    		}
                    		//UPDATE STOK JIKA EDITING:: SUDAH BERLAKU STOK
                			if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode()) &&
                    				(! model.getItemHeader().getInvoiceno().trim().equals(""))) {
                    			model.getProductAndStockHelper().soStockAdd(model.getItemHeader().getFwarehouseBean(),
                    					listFtSalesdToDeleteOrUpdate, model.getItemHeader().getInvoicedate());                            			
                    		}	
                    		
                			model.getFtSalesdJpaService().removeObject(model.getItemDetil());                    		
                    		//3. TAMPILAN KEADAAN KOSONG
                			model.getBeanItemContainerDetil().removeItem(model.getItemDetil());
                    			
                    		//4. UPDATE STOCK :: JIKA EDITING BERARTI SUDAH BERLAKU STOK
                    		if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode()) &&
                    				(! model.getItemHeader().getInvoiceno().trim().equals(""))) {
                    			
                    			List<FtSalesd> listForStock = new ArrayList<FtSalesd>();
                    			listForStock.add(model.getItemDetil());                    			
                    			model.getProductAndStockHelper().soStockRemove(model.getItemHeader().getFwarehouseBean(),
                    					listForStock, model.getItemHeader().getInvoicedate());
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
	
			//CEK DISKON DAN UPDATE DETIL PER SUPPLIER
			helper.cekAndUpdateParamDiskonItemByVendor();
			//0. Cek and Update Aktifitas Promo Detail			
			helper.cekAktifitasPromoItem();
			
			//1.. RECEK  and CALCULATE header
			helper.updateAndCalculateHeaderByItemDetil();
			
			//2. TERBITKAN NOMOR PO
			model.itemHeader.setOrderno(model.getTransaksiHelper().getNextFtSaleshRefno());
			//3. SAVE ULANG HEADER
			model.getFtSaleshJpaService().updateObject(model.itemHeader);
			
			//4. UPDATE STOCK :: UPDATE STOK TERJADI SETELAH DI SAVE ATAU DITERBITKANNYA NOMOR PO			
			//****update stock saat print dan terbitkan invoice****
			//REFINE TAMPILAN 			
			model.getBinderHeader().setItemDataSource(model.itemHeader);
			model.getBeanItemContainerHeader().addItem(model.itemHeader);
			
			view.bindAndBuildFieldGroupComponentDetilHeader();		
			view.fillComponentDetilItem();
			
			model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
			view.setDisplayTableFooterList();
			view.setFormButtonAndTextState();
			
			
			//#####***SEMENTARA:: TIDAK DISARANKAN BANGET
			view.getBtnEditForm().click();
			view.getBtnSaveForm().click();
			
			System.out.println("tambahan");
		}
	}
	public void saveFormEditing(){
		if (helper.isValidSaveFormEditing()) {
			//0. Cek and Update Aktifitas Promo Detail
			helper.cekAktifitasPromoItem();
			
			//##MUNGKIN INI SOLUSINYA
			helper.updateAndCalculateHeaderByItemDetil();
			
			//KITA MENGGUNAKAN AUTO UPDATE STOK UNTUK EDITING
			model.getFtSaleshJpaService().updateObject(model.getItemHeader());
			
			//REFINE TAMPILAN
			model.getBinderHeader().setItemDataSource(model.getItemHeader());
			model.getBeanItemContainerHeader().addItem(model.getItemHeader());
			
			view.bindAndBuildFieldGroupComponentDetilHeader();		
			view.fillComponentDetilItem();

			model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
			view.setDisplayTableFooterList();
			view.setFormButtonAndTextState();
			
		}
	}
	
	
	public void cancelFormAdding(){
		//1. HAPUS DETIL DAN HEADER
		deleteFtSaleshAndFtSalesd();
		
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
		
		model.itemHeader.setOrderno("");
		model.itemHeader.setInvoiceno("");
		
		if (model.itemHeaderTemp.getOrderdate()==null) {
			model.itemHeader.setOrderdate(model.getTransaksiHelper().getCurrentTransDate());
		} else {
			model.itemHeader.setOrderdate(model.itemHeaderTemp.getOrderdate());			
		}
		if (model.itemHeaderTemp.getInvoicedate()==null) {
			model.itemHeader.setInvoicedate(model.getTransaksiHelper().getCurrentTransDate());
		} else {
			model.itemHeader.setInvoicedate(model.itemHeaderTemp.getInvoicedate());			
		}
		
		model.itemHeader.setTunaikredit("T");
		model.itemHeader.setTop(0);
		
		if (model.itemHeaderTemp.getDuedate()==null) {
			model.itemHeader.setDuedate(model.getTransaksiHelper().getCurrentTransDate());
		}else {
			model.itemHeader.setDuedate(model.itemHeaderTemp.getDuedate());			
		}
		
		model.itemHeader.setAmount(0.0);
		model.itemHeader.setAmountpay(0.0);
		model.itemHeader.setDisc(0.0);
		model.itemHeader.setDisc1(0.0);
		model.itemHeader.setDisc2(0.0);
		model.itemHeader.setPpnpercent(10.0);
		model.itemHeader.setSaldo(false);
		model.itemHeader.setPrintcounter(0);
		model.itemHeader.setTipefaktur("F");
		model.itemHeader.setSuratjalanno("");
		model.itemHeader.setSjpenagihanno("");

		//KASIH NILAI AWAL DARI NOTA SEBELUMNYA
		model.itemHeader.setFsalesmanBean(model.itemHeaderTemp.getFsalesmanBean());
		model.itemHeader.setFwarehouseBean(model.itemHeaderTemp.getFwarehouseBean());
		
		model.itemHeader.setTipejual(model.itemHeaderTemp.getTipejual());
		
		
		
	}
	public void resetHeader(){
		
		model.itemHeader.setOrderno("");
		model.itemHeader.setInvoiceno("");
		model.itemHeader.setOrderdate(model.getTransaksiHelper().getCurrentTransDate());
		model.itemHeader.setInvoicedate(model.getTransaksiHelper().getCurrentTransDate());
		model.itemHeader.setAmount(0.0);
		model.itemHeader.setAmountpay(0.0);
		model.itemHeader.setDisc(0.0);
		model.itemHeader.setDisc1(0.0);
		model.itemHeader.setDisc2(0.0);
		model.itemHeader.setPpnpercent(10.0);
		model.itemHeader.setSaldo(false);
		model.itemHeader.setSuratjalan(false);
		model.itemHeader.setSuratjalanno("");
		model.itemHeader.setSjpenagihanno("");
		model.itemHeader.setTipejual("");
		
		
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
                    			model.getFtSaleshJpaService().updateObject(model.itemHeader);
                    		}
                    		if (model.itemHeader.getEndofday() != true) {                    		
                    		
	                    		//2. DELETE STOK DAN HEADER
                    			List<FtSalesd> listForStock = new ArrayList<FtSalesd>();
                    			listForStock = deleteFtSaleshAndFtSalesd();
	                    		//3. UPDATE STOCK
	                    		if (! model.getItemHeader().getOrderno().equalsIgnoreCase("New")) {
	                    			model.getProductAndStockHelper().soStockRemove(model.getItemHeader().getFwarehouseBean(), listForStock, model.getItemHeader().getInvoicedate());
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
                			ex.printStackTrace();
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
	
	private List<FtSalesd> deleteFtSaleshAndFtSalesd(){
		//2. HAPUS DETIL :: HAPUS HEADER
		Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
		List<FtSalesd> listForStock = new ArrayList<FtSalesd>();		
		for (Object itemId: itemIds){
			FtSalesd domain = new FtSalesd();	                    			
			domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
			
			//HAPUS ANAK DETIL
			deleteFtSalesdChild(domain);
			//HAPUS DETIL
			model.getFtSalesdJpaService().removeObject(domain);

			domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
			listForStock.add(domain);
			
		}
		
		model.getFtSaleshJpaService().removeObject(model.getItemHeader());
		model.getBeanItemContainerHeader().removeItem(model.getItemHeader());
		
		return listForStock;
		
	}
	private void deleteFtSalesdChild(FtSalesd ftSalesd){
		FtSalesd ftSalesdFromDatabase = new FtSalesd();
		ftSalesdFromDatabase = ftSalesd;
				
		List<FtSalesdPromoTprb> listFtSalesdPromoTprb =
				new ArrayList<FtSalesdPromoTprb>(ftSalesdFromDatabase.getFtsalesdPromoTprbList());
		for (FtSalesdPromoTprb childDomain: listFtSalesdPromoTprb){
			model.getFtSalesdPromoTprbJpaService().removeObject(childDomain);
		}
		List<FtSalesdPromoTpruDisc> listFtSalesdPromoTpruDisc =
				new ArrayList<FtSalesdPromoTpruDisc>(ftSalesdFromDatabase.getFtsalesdPromoTpruDiscList() );
		for (FtSalesdPromoTpruDisc childDomain: listFtSalesdPromoTpruDisc){
			model.getFtSalesdPromoTpruDiscJpaService().removeObject(childDomain);
		}
		List<FtSalesdPromoTpruCb> listFtSalesdPromoTpruCb =
				new ArrayList<FtSalesdPromoTpruCb>(ftSalesdFromDatabase.getFtsalesdPromoTpruCbList());
		for (FtSalesdPromoTpruCb childDomain: listFtSalesdPromoTpruCb){
			model.getFtSalesdPromoTpruCbJpaService().removeObject(childDomain);
		}
		
	}
	public void searchForm(){
		model.getBeanItemContainerHeader().removeAllContainerFilters();
		
		//2. Baru Kasih Filter Lagi
		String strSearch1 = view.getFieldSearch1().getValue().toString().trim();
		String strSearch2 = view.getFieldSearch2().getValue().toString().trim();
		
		Filter filter1 = new Or(new SimpleStringFilter("orderno", strSearch1, true, false));
		Filter filter2 = new Or(new SimpleStringFilter("invoiceno", strSearch2, true, false));
		
		model.getBeanItemContainerHeader().addContainerFilter(filter1);
		model.getBeanItemContainerHeader().addContainerFilter(filter2);

		if (view.getCheckSearch1().getValue()==true){
			Filter filter3 = new Or(new Compare.Equal("invoiceno", ""));
			model.getBeanItemContainerHeader().addContainerFilter(filter3);
		}
		
	}
	
	public void printForm(){		
		try{
			//TAMBAH COUNTER ITEM PRINTE
			if (model.itemHeader.getPrintcounter()==null) model.itemHeader.setPrintcounter(1);
			model.itemHeader.setPrintcounter(model.getItemHeader().getPrintcounter()+1);        			
			model.getFtSaleshJpaService().updateObject(model.itemHeader);
		} catch(Exception ex){}
		
		if (model.getItemHeader().getInvoiceno().trim().equals("")){
			
			if (helper.isValidPrintAndInvoice()) {
				
		        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
					@Override
					public void onClose(ConfirmDialog dialog) {
	                    if (dialog.isConfirmed()) {
	            			//2. TERBITKAN NOMOR INVOICE
	                    	
	            			try{
	            				if (model.getItemHeader().getInvoiceno().trim().equals("")) {
	            					model.itemHeader.setInvoiceno(model.getTransaksiHelper().getNextFtSaleshInvoiceno());
	            					//3. SAVE ULANG HEADER
	            					model.getFtSaleshJpaService().updateObject(model.itemHeader);
	            					
	            					//4. UPDATE STOCK :: UPDATE STOK TERJADI SETELAH DI SAVE ATAU DITERBITKANNYA NOMOR PO			
	            					Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
	            					List<FtSalesd> listForStock = new ArrayList<FtSalesd>();
	            					for (Object itemId: itemIds) {
	            						FtSalesd domain = new FtSalesd();
	            						domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
	            						listForStock.add(domain);
	            					}
	            					model.getProductAndStockHelper().soStockAdd(model.getItemHeader().getFwarehouseBean(), listForStock, model.getItemHeader().getInvoicedate());
	            					
	            					//REFINE TAMPILAN
	            					model.getBinderHeader().setItemDataSource(model.itemHeader);
	            					model.getBeanItemContainerHeader().addItem(model.itemHeader);
	            			
	            					model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
	            					view.setDisplayTableFooterList();
	            					view.setFormButtonAndTextState();
	            					
	            					//JIKA PARAMETER SISTEM LANGSUNG LUNASKAN
	            					if (model.getSysvarHelper().isFakturTunaiLangsungLunas()) {
	            						if (model.itemHeader.getTunaikredit().equals("T")){
	            							helper.lunaskan();
	            						}
	            					}
	            					
	            					Notification.show("INVOICE AND STOK UPDATED!", Notification.TYPE_TRAY_NOTIFICATION );
	            				}
	            				
	            				//PREVIEW INVOICE
	        					previewInvoice();
	        					
	        					//####TAMBAHAN:: AGAR YANG SUDAH CETAK HILANG
	        					if (view.getCheckSearch1().getValue()==true) {
	        						view.getBtnSearch().click();
	        					}
	            				
	            			} catch(Exception ex) {}
	                    } else {
	                    // User did not confirm
	                    }
						view.getTableDetil().focus();
					}
				};
				
				 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi INVOICE", "TERBITKAN INVOICE DAN CETAK?", 
						 "OKE", "Cancel", konfirmDialogListener);
				 
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
		}

		 //PRINT INVOICE
		if (! model.getItemHeader().getInvoiceno().trim().equals("") ) {
			//PREVIEW INVOICE
			previewInvoice();
		}
	}

	public void printFormRetail(){		
		try{
			//TAMBAH COUNTER ITEM PRINTE
			if (model.itemHeader.getPrintcounter()==null) model.itemHeader.setPrintcounter(1);
			model.itemHeader.setPrintcounter(model.getItemHeader().getPrintcounter()+1);        			
			model.getFtSaleshJpaService().updateObject(model.itemHeader);
		} catch(Exception ex){}
		
		if (model.getItemHeader().getInvoiceno().trim().equals("")){
			
			if (helper.isValidPrintAndInvoice()) {
				
		        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
					@Override
					public void onClose(ConfirmDialog dialog) {
	                    if (dialog.isConfirmed()) {
	            			//2. TERBITKAN NOMOR INVOICE
	            			try{
	            				if (model.getItemHeader().getInvoiceno().trim().equals("")) {
	            					model.itemHeader.setInvoiceno(model.getTransaksiHelper().getNextFtSaleshInvoiceno());

	            					//3. SAVE ULANG HEADER
	            					model.getFtSaleshJpaService().updateObject(model.itemHeader);
	            					
	            					//4. UPDATE STOCK :: UPDATE STOK TERJADI SETELAH DI SAVE ATAU DITERBITKANNYA NOMOR PO			
	            					Collection itemIds = model.getBeanItemContainerDetil().getItemIds();
	            					List<FtSalesd> listForStock = new ArrayList<FtSalesd>();
	            					for (Object itemId: itemIds) {
	            						FtSalesd domain = new FtSalesd();
	            						domain = model.getBeanItemContainerDetil().getItem(itemId).getBean();
	            						listForStock.add(domain);
	            					}
	            					model.getProductAndStockHelper().soStockAdd(model.getItemHeader().getFwarehouseBean(), listForStock, model.getItemHeader().getInvoicedate());
	            					
	            					//REFINE TAMPILAN
	            					model.getBinderHeader().setItemDataSource(model.itemHeader);
	            					model.getBeanItemContainerHeader().addItem(model.itemHeader);
	            			
	            					model.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
	            					view.setDisplayTableFooterList();
	            					view.setFormButtonAndTextState();
	            					
	            					//JIKA PARAMETER SISTEM LANGSUNG LUNASKAN
	            					if (model.getSysvarHelper().isFakturTunaiLangsungLunas()) {
	            						if (model.itemHeader.getTunaikredit().equals("T")){
	            							helper.lunaskan();
	            						}
	            					}
	            					
	            					Notification.show("INVOICE AND STOK UPDATED!", Notification.TYPE_TRAY_NOTIFICATION );
	            				}
	            				
	            				previewInvoiceRetail();
	        					
	        					//####TAMBAHAN:: AGAR YANG SUDAH CETAK HILANG
	        					if (view.getCheckSearch1().getValue()==true) {
	        						view.getBtnSearch().click();
	        					}
	            				
	            			} catch(Exception ex) {}
	                    } else {
	                    // User did not confirm
	                    }
						view.getTableDetil().focus();
					}
				};
				
				 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi FAKTUR RETAIL", "TERBITKAN FAKTUR RETAIL DAN CETAK?", 
						 "OKE", "Cancel", konfirmDialogListener);
				 
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
		}

		 //PRINT INVOICE
		if (! model.getItemHeader().getInvoiceno().trim().equals("") ) {
			//PREVIEW INVOICE
			previewInvoiceRetail();
		}
	}
	
	public void previewInvoice(){
		String inputFilePath = "";
		
		if (model.getSysvarHelper().getFormatFaktur()==1) {
			inputFilePath = "/erp/distribution/reports/salesorder/invoice1/standart1.jasper";
		} else if (model.getSysvarHelper().getFormatFaktur()==2) {
			inputFilePath = "/erp/distribution/reports/salesorder/invoice2/standart2.jasper";
		} else if (model.getSysvarHelper().getFormatFaktur()==10) {
			inputFilePath = "/erp/distribution/reports/invoicestd1/invoicestd1.jasper";
		} else if (model.getSysvarHelper().getFormatFaktur()==11) {
			inputFilePath = "/erp/distribution/reports/invoicestd2/invoicestd2.jasper";
		} else if (model.getSysvarHelper().getFormatFaktur()==12) {
			inputFilePath = "/erp/distribution/reports/invoicestd3/invoicestd3.jasper";
		} else if (model.getSysvarHelper().getFormatFaktur()==13) {
			inputFilePath = "/erp/distribution/reports/invoicestd4/invoicestd4.jasper";
		} else if (model.getSysvarHelper().getFormatFaktur()==14) {
			inputFilePath = "/erp/distribution/reports/invoicestd5/invoicestd5.jasper";
		} else if (model.getSysvarHelper().getFormatFaktur()==15) {
			inputFilePath = "/erp/distribution/reports/salesorder/invoicestd/invoicestd1/invoicestd1.jasper";
		}
		
		String outputFilePath = "sales_invoice";
		
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
		if (model.getItemHeader().getTipefaktur().equals("R")) {
			parameters.put("paramJudulFaktur", model.getSysvarHelper().getJudulFakturRetur());
		}else if (model.getItemHeader().getTunaikredit().equals("T")) {
			parameters.put("paramJudulFaktur", model.getSysvarHelper().getJudulFakturTunai());
		}else if (model.getItemHeader().getTunaikredit().equals("K"))  {
			parameters.put("paramJudulFaktur", model.getSysvarHelper().getJudulFakturKredit());			
		} else {
			parameters.put("paramJudulFaktur", "FAKTUR");						
		}
		parameters.put("paramJudulFakturTunai", model.getSysvarHelper().getJudulFakturTunai());
		parameters.put("paramJudulFakturKredit", model.getSysvarHelper().getJudulFakturKredit());			
		parameters.put("paramJudulFakturRetur", model.getSysvarHelper().getJudulFakturRetur());			

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
	public void previewInvoiceRetail(){
		String inputFilePath = "";
		
		if (model.getSysvarHelper().getFormatFakturRetail()==101) {
			inputFilePath = "/erp/distribution/reports/invoiceretail1/invoicestd1.jasper";
		} else if (model.getSysvarHelper().getFormatFakturRetail()==102) {
			inputFilePath = "/erp/distribution/reports/invoiceretail2/invoicestd2.jasper";
		} else if (model.getSysvarHelper().getFormatFakturRetail()==103) {
			inputFilePath = "/erp/distribution/reports/invoiceretail3/invoicestd3.jasper";
		} else if (model.getSysvarHelper().getFormatFakturRetail()==104) {
			inputFilePath = "/erp/distribution/reports/invoiceretail4/invoicestd4.jasper";
		} else if (model.getSysvarHelper().getFormatFakturRetail()==105) {
			inputFilePath = "/erp/distribution/reports/invoiceretail5/invoicestd5.jasper";
		} else if (model.getSysvarHelper().getFormatFakturRetail()==115) {
			inputFilePath = "/erp/distribution/reports/salesorder/invoiceret/invoiceret1/invoiceret1.jasper";
		}
		
		String outputFilePath = "sls_inv_r";
		
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
		if (model.getItemHeader().getTipefaktur().equals("R")) {
			parameters.put("paramJudulFaktur", model.getSysvarHelper().getJudulFakturRetail());
		}else if (model.getItemHeader().getTunaikredit().equals("T")) {
			parameters.put("paramJudulFaktur", model.getSysvarHelper().getJudulFakturRetail());
		}else if (model.getItemHeader().getTunaikredit().equals("K"))  {
			parameters.put("paramJudulFaktur", model.getSysvarHelper().getJudulFakturRetail());			
		} else {
			parameters.put("paramJudulFaktur", "FAKTUR");						
		}
		parameters.put("paramJudulFakturTunai", model.getSysvarHelper().getJudulFakturRetail());
		parameters.put("paramJudulFakturKredit", model.getSysvarHelper().getJudulFakturRetail());			
		parameters.put("paramJudulFakturRetur", model.getSysvarHelper().getJudulFakturRetail());			

		parameters.put("paramTipefaktur","F");

		parameters.put("paramCompanyName", model.getSysvarHelper().getCompanyNameFakturRetail());
		parameters.put("paramCompanyAddress", model.getSysvarHelper().getCompanyAddressFakturRetail());
		parameters.put("paramCompanyPhone", model.getSysvarHelper().getCompanyPhoneFakturRetail());
		parameters.put("paramCompanyNpwp", model.getSysvarHelper().getCompanyNpwpFakturRetail());

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
		
		String fileName = "so_r_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new_notar_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
		
	}
	public void helpForm(){
	}
	
	
	public SalesOrderModel getModel() {
		return model;
	}
	public void setModel(SalesOrderModel model) {
		this.model = model;
	}
	public SalesOrderView getView() {
		return view;
	}
	public void setView(SalesOrderView view) {
		this.view = view;
	}


	
	
}
