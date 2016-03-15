package org.erp.distribution.salesorder.salesorder.printinvoice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.erp.distribution.model.FDivision;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FtArpaymentd;
import org.erp.distribution.model.FtArpaymentdPK;
import org.erp.distribution.model.FtArpaymenth;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.ZLapTemplate2;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.util.FormatAndConvertionUtil;
import org.erp.distribution.util.HeaderDetilSalesHelper;
import org.erp.distribution.util.HeaderDetilSalesHelperImpl;
import org.erp.distribution.util.KonversiProductAndStock;
import org.erp.distribution.util.KonversiProductAndStockImpl;
import org.erp.distribution.util.ReportJdbcConfigHelper;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Not;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.Action;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table.HeaderClickEvent;
import com.vaadin.ui.Table.HeaderClickListener;

public class PrintInvoicePresenter implements ClickListener, ValueChangeListener, Handler, ItemClickListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PrintInvoiceModel model;
	private PrintInvoiceView view;
	
	FtSalesh item = new FtSalesh();
	
	
	public PrintInvoicePresenter(PrintInvoiceModel model, PrintInvoiceView view){
		this.model = model;
		this.view = view;
		initListener();		
		
		initDisplayFirst();
//		initDisplay();
		
		//INITIAL RELOAD
//		view.getBtnSearch().click();
	}
	
	public void initListener(){
		view.getBtnHelp().addClickListener(this);
		view.getBtnPrint().addClickListener(this);
		view.getBtnReload().addClickListener(this);
		view.getBtnSearch().addClickListener(this);

		view.getBtnSelectRekapNo().addClickListener(this);
		
		view.getBtnPay().addClickListener(this);
		view.getBtnLunaskan().addClickListener(this);
		view.getBtnSelisihPlusMinus().addClickListener(this);
		
//		view.getTable().addValueChangeListener(this);
		view.getTable().addItemClickListener(this);
		
		view.getPanelTop().addActionHandler(this);
		
		

		ValueChangeListener listenerCheckLihatSemua = new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				if (view.getCheckLihatSemua().getValue()==true){
					//JANGAN DIBALIK URUTANNYA
					view.getBtnPay().setEnabled(false);
					view.getBtnLunaskan().setEnabled(false);
					view.getBtnSelisihPlusMinus().setEnabled(false);									
				}else {
					view.getBtnPay().setEnabled(true);
					view.getBtnLunaskan().setEnabled(true);
					view.getBtnSelisihPlusMinus().setEnabled(true);									
				}
				view.getTable().removeAllItems();
				
			}
		};
		view.getCheckLihatSemua().addValueChangeListener(listenerCheckLihatSemua);
		
		HeaderClickListener listenerHeaderTableUtama = new HeaderClickListener() {			
			@Override
			public void headerClick(HeaderClickEvent event) {
				// TODO Auto-generated method stub
				//zoom table
				try{
					if (event.isDoubleClick()){
						if (view.getPanelTop().getContent() == null) {
							view.getPanelTop().setContent(view.getLayoutTop());
						}else {
							view.getPanelTop().setContent(null);
						}
					}
				} catch(Exception ex){}
				
				try{
					if (event.getPropertyId().equals("selected")){
						if (model.isSelectAllInvoice()==true) {
							
							view.getTable().setColumnHeader("selected", "<input type='checkbox'  checked />");		
							model.setSelectAllInvoice(false);
							
							Collection itemIds = model.getTableBeanItemContainer().getItemIds();
							for (Object itemId: itemIds){
								model.getTableBeanItemContainer().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getTableBeanItemContainer().getItem(itemId).getBean().getSelected().setValue(true);
							}
							
						} else {
							
							view.getTable().setColumnHeader("selected", "<input type='checkbox' />");		
							model.setSelectAllInvoice(true);
							
							Collection itemIds = model.getTableBeanItemContainer().getItemIds();
							for (Object itemId: itemIds){
								model.getTableBeanItemContainer().getItem(itemId).getBean().getSelected().setReadOnly(false);
								model.getTableBeanItemContainer().getItem(itemId).getBean().getSelected().setValue(false);
							}
							
						}	
						
//						//BUAT UP-TO-DATE DATA
						boolean hapusLunas = true;
						if (view.getFieldSearchComboLunas().getValue().equals("B")){
							//Jika belum maka yang terkirim harus dihapus
							hapusLunas = true;					
						} else if (view.getFieldSearchComboLunas().getValue().equals("L")){
							hapusLunas = false;					
						}
//						cekAndDeleteProcessedByOthers(hapusLunas);
						
						
						//KASIH SELEKSI >> buat SELECTED READONLY(TRUE) LAGI				
						view.setDisplayFooter();

					}
				} catch(Exception ex){}
			}
				
		};
		view.getTable().addHeaderClickListener(listenerHeaderTableUtama);

		
	}
	public void initDisplayFirst(){
		view.setDisplay();
	}
	public void initDisplay(){
		//1. Table
		model.setFreshDataTable();
		view.setDisplay();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		//Antisipasi
		if (view.getOperationStatus()==null) view.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		if (view.getOperationStatus().equals("")) view.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		
			
		if (event.getButton() == view.getBtnSearch()) {
//			searchFormJpa();
			searchForm();
		} else if (event.getButton() == view.getBtnReload()){
			reloadForm();			
			if (view.getOperationStatus().equals(EnumOperationStatus.OPEN.getStrCode())){
				view.getTable().focus();
			}
		} else if (event.getButton() == view.getBtnPrint()){		
	        ConfirmDialog.Listener konfirmDialogListener = new ConfirmDialog.Listener() {					
				@Override
				public void onClose(ConfirmDialog dialog) {
                    if (dialog.isConfirmed()) {
            			printForm();
                    } else {
                    // User did not confirm
                    }
				}
			};
			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi Print", "Terbitkan Invoice dan Cetak?", 
					 "Oke Print", "Cancel", konfirmDialogListener);
			 
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

		}else if (event.getButton() == view.getBtnHelp()){
				
			
		} else if (event.getButton() == view.getBtnSelectRekapNo()){
		} 
		
	}

																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																									public void setFormButtonAndText(){
		if (view.getOperationStatus().equals(EnumOperationStatus.OPEN.getStrCode())){
			view.getForm().setVisible(false);
			view.getTable().setSelectable(true);
			view.getForm().getField("id").setReadOnly(true);			
		} else if (view.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
			view.getForm().setVisible(true);
			view.getTable().setSelectable(false);
			view.getForm().getField("id").setReadOnly(false);
		}else if (view.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
			view.getForm().setVisible(true);
			view.getTable().setSelectable(true);
			view.getForm().getField("id").setReadOnly(true);
			
		}		
	}
	
	public void searchForm(){
		//1. Remove filter dan Refresh container dalulu dahulu
		model.getTableBeanItemContainer().removeAllItems();
		model.getTableBeanItemContainer().removeAllContainerFilters();		
		
		//2. AMBAIL DARI DATABASE dengan Primary Key dan 
		String invoiceNo = view.getFieldSearchByInvoice().getValue().toString().trim();
		//FIND ALL PAKAI Ini
		model.getTableBeanItemContainer().addAll(model.getFtSaleshJpaService().findAll());
		//3. Filter Default

//		Filter filter0 = new And(new Not(new SimpleStringFilter("orderno", "New", true, false)));
//		model.getTableBeanItemContainer().addContainerFilter(filter0);
		
		
		model.setFilterDefaultBeanItemContainer();
		//NOMER ORDER BUKAN YANG NEW
		Filter filter0 = new Not(new SimpleStringFilter("orderno", "New", true, false));
		model.getTableBeanItemContainer().addContainerFilter(filter0);
		
		String invoiceno = view.getFieldSearchByInvoice().getValue().toString().trim();
		Filter filter1 = new And(new SimpleStringFilter("invoiceno", invoiceno, true, false));
		model.getTableBeanItemContainer().addContainerFilter(filter1);
		
		if (view.getCheckBelumTerbitInvoice().getValue()==true){
			Filter filter2 = new And(new Compare.Equal("invoiceno", ""));
			model.getTableBeanItemContainer().addContainerFilter(filter2);
		}
		//TANGGAL
		try{
			//TANGGAL INVOICE
			long tglInvoiceFromLong = 0;
			try{
				tglInvoiceFromLong = view.getFieldSearchByDateInvoiceFrom().getValue().getTime();
			} catch (Exception ex){}
			long tglInvoiceToLong = 0;
			try{
				tglInvoiceToLong = view.getFieldSearchByDateInvoiceTo().getValue().getTime();
			} catch(Exception ex){}
			
			Filter filter8 = new And(new Compare.GreaterOrEqual("invoicedate", view.getFieldSearchByDateInvoiceFrom().getValue()));
			Filter filter9 = new And(new Compare.LessOrEqual("invoicedate", view.getFieldSearchByDateInvoiceTo().getValue()));
			//Jika semua maka tidak ada filter
			
			if (tglInvoiceFromLong !=0){
				model.getTableBeanItemContainer().addContainerFilter(filter8);
			}	
			if (tglInvoiceToLong !=0){
				model.getTableBeanItemContainer().addContainerFilter(filter9);
			}	
		} catch(Exception ex){}
			
		
//		//JIKA TIDAK DIPILIH YA KOSONG
//		String strDivision="";
//		FDivision div = new FDivision();
//		try{
//			div = model.getBeanItemContainerDivision().getItem(view.getFieldSearchComboDivisi().getValue()).getBean();
//			strDivision = div.getId();		
//		} catch(Exception ex){}
//		try{
//			if (! strDivision.trim().equals("")){
//				Filter filter11 = new And(new Compare.Equal("divisionBean", div));
//				model.getTableBeanItemContainer().addContainerFilter(filter11);
//			} 
//		}catch(Exception ex){}
		
		//3. Refresh container dengan kondisi filter
//		model.setFreshDataTable();
		view.setDisplay();
		//Focus
		view.getTable().focus();
		
	}
	
	public void searchFormJpa(){
		//1. Remove filter dan Refresh container dalulu dahulu
		model.getTableBeanItemContainer().removeAllItems();
		model.getTableBeanItemContainer().removeAllContainerFilters();		
		
		//2. AMBAIL DARI DATABASE dengan Primary Key dan 
		String invoiceNo = "%" + view.getFieldSearchByInvoice().getValue().toString().trim() + "%";

		
		//JIKA TIDAK DIPILIH YA KOSONG
		String strDivision="%";
		FDivision div = new FDivision();
		try{
			div = model.getBeanItemContainerDivision().getItem(view.getFieldSearchComboDivisi().getValue()).getBean();
			strDivision = "%" + div.getId().trim() + "%";		
		} catch(Exception ex){}

		String spcode ="%";
		FSalesman salesman = new FSalesman();
		spcode = "%" + view.getFieldSearchByIdSalesman().getConvertedValue() + "%";
		String spname = "%" + view.getFieldSearchByNamaSalesman().getConvertedValue() + "%";
//		try{
//			salesman = model.getBeanItemContainerSalesman().getItem(view.getFieldSearchComboSalesman().getValue()).getBean();
//			spcode = "%" + salesman.getId().getSpcode() + "%";				
//		} catch(Exception ex){}
//
		String custno = "%" + view.getFieldSearchByIdCustomer().getConvertedValue() + "%";
		String custname = "%" + view.getFieldSearchByNamaCustomer().getConvertedValue() + "%";
		
		boolean terkirimFrom = true;
		boolean terkirimTo = true;
//		try{
//			if (view.getFieldSearchComboTerkirim().getValue().equals("B")){
//				terkirimFrom = false;
//				terkirimTo = false;
//			} else if (view.getFieldSearchComboTerkirim().getValue().equals("K")){
//				terkirimFrom = true;
//				terkirimTo = true;
//			}
//		} catch(Exception ex){
//			ex.printStackTrace();
//		}
		//JIKA LIHAT SEMUA MAKA TERKIRM MAUPUN TIDAK BISA TERLIHAT
		if (view.getCheckLihatSemua().getValue()==true){
			terkirimFrom = false;
			terkirimTo = true;		
		}
		
		Date invoicedateFrom = null;
		Date invoicedateTo =null;
		Date invoicedatekirimFrom = null;
		Date invoicedatekirimTo = null;
				
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try{
			invoicedateFrom = sdf.parse("01-01-2000");
			invoicedateTo= sdf.parse("01-01-3000");
			invoicedatekirimFrom = sdf.parse("01-01-2000");
			invoicedatekirimTo= sdf.parse("01-01-3000");
		} catch(Exception ex){}
		try{
			//TANGGAL INVOICE
			long tglInvoiceFromLong = 0;
			try{
				tglInvoiceFromLong = view.getFieldSearchByDateInvoiceFrom().getValue().getTime();
			} catch (Exception ex){}
			long tglInvoiceToLong = 0;
			try{
				tglInvoiceToLong = view.getFieldSearchByDateInvoiceTo().getValue().getTime();
			} catch(Exception ex){}
			
			
			if (tglInvoiceFromLong !=0){
				invoicedateFrom = view.getFieldSearchByDateInvoiceFrom().getValue();
				invoicedateTo = view.getFieldSearchByDateInvoiceTo().getValue();
			}	
			if (tglInvoiceToLong !=0){
				invoicedateTo = view.getFieldSearchByDateInvoiceTo().getValue();
			}	
		} catch(Exception ex){}
		
		//TERTUNDA
		boolean tertundaFrom =false;
		boolean tertundaTo = true;
		int tertundacounterFrom = 0;
		int tertundacounterTo = 100;
		
		String tipeJual = "%";
		String tipeFaktur = "%";
		try{
			if (view.getFieldSearchComboToCanvas().getValue().equals("TO")){				
				tipeJual= "TO";
				tipeFaktur = "F";
			} else if (view.getFieldSearchComboToCanvas().getValue().equals("C")){
				tipeJual = "C";
				tipeFaktur = "F";
			} else if (view.getFieldSearchComboToCanvas().getValue().equals("R")){
				tipeFaktur = "R";
			} else if (view.getFieldSearchComboToCanvas().getValue().equals("SNR")){
				tipeFaktur = "F";
			} 
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		boolean lunasFrom = false; boolean lunasTo = true;
		try{
			if (view.getFieldSearchComboLunas().getValue().equals("B")){				
				lunasFrom = false;
				lunasTo = false;
			} else if (view.getFieldSearchComboLunas().getValue().equals("L")){
				lunasFrom = true;
				lunasTo = true;
			}	
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		//FIND ALL PAKAI Ini
		String tunaiKredit = "%";
		try{
			if (view.getFieldSearchComboTunaiKredit().getValue().equals("T")){
				tunaiKredit = "T";
			} else if (view.getFieldSearchComboTunaiKredit().getValue().equals("K")){
				tunaiKredit = "K";
			}
		} catch(Exception ex){
			ex.printStackTrace();
		}
		
		//JIKA RECAP NO CUMA SATU
		String recapNoCumaSatu = view.getFieldSearchByRekap().getValue().toString().trim();
		
//		List<FtSalesh> list = model.getFtSaleshJpaService().findAll(recapNoCumaSatu, strDivision, invoiceNo, tipeFaktur, tunaiKredit, tipeJual, 
//				invoicedateFrom, invoicedateTo, invoicedatekirimFrom, invoicedatekirimTo, 
//				terkirimFrom, terkirimTo, tertundaFrom, tertundaTo, tertundacounterFrom, 
//				tertundacounterTo, lunasFrom, lunasTo, spcode, spname, custno, custname);		
		List<FtSalesh> list = model.getFtSaleshJpaService().findAll();
		
		model.getTableBeanItemContainer().addAll(list);
		//3. Filter Default
		model.setFilterDefaultBeanItemContainer();
		
		
		//SEMENTARA TANGGAL JPA MASIH ERROR
		try{
			//TANGGAL INVOICE
			long tglInvoiceFromLong = 0;
			try{
				tglInvoiceFromLong = view.getFieldSearchByDateInvoiceFrom().getValue().getTime();
			} catch (Exception ex){}
			long tglInvoiceToLong = 0;
			try{
				tglInvoiceToLong = view.getFieldSearchByDateInvoiceTo().getValue().getTime();
			} catch(Exception ex){}
			
			Filter filter8 = new And(new Compare.GreaterOrEqual("invoicedate", view.getFieldSearchByDateInvoiceFrom().getValue()));
			Filter filter9 = new And(new Compare.LessOrEqual("invoicedate", view.getFieldSearchByDateInvoiceTo().getValue()));
			//Jika semua maka tidak ada filter
			
			if (tglInvoiceFromLong !=0){
				model.getTableBeanItemContainer().addContainerFilter(filter8);
			}	
			if (tglInvoiceToLong !=0){
				model.getTableBeanItemContainer().addContainerFilter(filter9);
			}	
		} catch(Exception ex){}
		
		try{
			//TANGGAL INVOICE KIRIM :: INGAT CUMA PER HARI
			long tglInvoicekirimFromLong = 0;
			try{
				tglInvoicekirimFromLong = view.getFieldSearchByDateInvoiceKirimFrom().getValue().getTime();
			} catch (Exception ex){}
			Filter filter81 = new And(new Compare.Equal("invoicedatekirim", view.getFieldSearchByDateInvoiceKirimFrom().getValue()));
			//Jika semua maka tidak ada filter
			
			if (tglInvoicekirimFromLong !=0){
				model.getTableBeanItemContainer().addContainerFilter(filter81);
			}	
		} catch(Exception ex){}
		
		view.setDisplay();
		//Focus
		view.getTable().focus();
		
		
	}
	
	public void reloadForm(){		
		model.setFreshDataTable();
		view.setDisplay();
		
	}
	public void printForm(){
		
		//0. HITUNG
		int jumlahMaxSelected = 40;
		int hitungJumlahSelected = 0;
		Collection itemIdsHitung = model.getTableBeanItemContainer().getItemIds();
		for (Object itemId: itemIdsHitung){
			FtSalesh itemFtSalesh = new FtSalesh();
			itemFtSalesh = model.getTableBeanItemContainer().getItem(itemId).getBean();
			if (itemFtSalesh.getSelected().getValue()==true){
				hitungJumlahSelected++;
			}
		}	
		
		if(hitungJumlahSelected < jumlahMaxSelected){
			//1. TERBITKAN INVOICE
			Collection itemIds = model.getTableBeanItemContainer().getItemIds();
			for (Object itemId: itemIds){
				FtSalesh itemFtSalesh = new FtSalesh();
				itemFtSalesh = model.getTableBeanItemContainer().getItem(itemId).getBean();
				if (itemFtSalesh.getSelected().getValue()==true){
					terbitkanInvoice(itemFtSalesh);
				}
				
			}
			
			//2. CETAK
			List<FtSalesh> listFtSaleshPreview = new ArrayList<FtSalesh>();
			Collection itemIdsPreview = model.getTableBeanItemContainer().getItemIds();
			for (Object itemId: itemIdsPreview){
				FtSalesh itemFtSalesh = new FtSalesh();
				itemFtSalesh = model.getTableBeanItemContainer().getItem(itemId).getBean();
				if (itemFtSalesh.getSelected().getValue()==true){
					listFtSaleshPreview.add(itemFtSalesh);
				}
			}	
			if (model.getSysvarHelper().getFormatFaktur()>=21 && model.getSysvarHelper().getFormatFaktur()<=100){
				previewInvoicePenjualanNew(model.getSysvarHelper().getFormatFaktur(), listFtSaleshPreview);
			}else {
				previewInvoice(listFtSaleshPreview);
			}
			
		}else {
			Notification.show("JUMLAH YANG BISA DI CETAK MAXIMAL " + String.valueOf(jumlahMaxSelected), Notification.TYPE_TRAY_NOTIFICATION);
		}
		
		
	}
	
	public void terbitkanInvoice(FtSalesh itemHeader){
		try{
			if (itemHeader.getInvoiceno().trim().equals("")) {
				itemHeader.setInvoiceno(model.getTransaksiHelper().getNextFtSaleshInvoiceno());
		
				//3. SAVE ULANG HEADER
				model.getFtSaleshJpaService().updateObject(itemHeader);
				
				//####*** HITUNG APAKAH STOK MENCUKUPI JIKA TIDAK MAKA STOK TIDAK MENCUKUPI**BELUM
				
				//4. UPDATE STOCK :: UPDATE STOK TERJADI SETELAH DI SAVE ATAU DITERBITKANNYA NOMOR PO			
				List<FtSalesd> listForStock = new ArrayList<FtSalesd>();
				listForStock = model.getFtSalesdJpaService().findAllDetilByRefno(itemHeader.getRefno());
				model.getProductAndStockHelper().soStockAdd(itemHeader.getFwarehouseBean(), listForStock, itemHeader.getInvoicedate());
				
				//REFINE TAMPILAN
				model.getTableBeanItemContainer().addItem(itemHeader);
				view.getTable().refreshRowCache();
				
				//JIKA PARAMETER SISTEM LANGSUNG LUNASKAN
				if (model.getSysvarHelper().isFakturTunaiLangsungLunas()) {
					if (itemHeader.getTunaikredit().equals("T")){
						lunaskan(itemHeader);
					}
				}
				
			}
			
		} catch(Exception ex) {}
		
	}
	public void lunaskan(FtSalesh itemHeader){
		
		FormatAndConvertionUtil myFormatUtil = new FormatAndConvertionUtil();
//		Double totalKurangLebihBayar = myFormatUtil.convertStringToDouble((String) view.getFieldAmountPaySum().getConvertedValue());

		try{
			
			//SYARAT INVOICE DILUNASKAN
			//1. Diseleksi dari Table
			//2. Belum Lunas
				
				FtSalesh itemArinvoice = new FtSalesh();
				itemArinvoice  = itemHeader;

//					String newRefno = model.getManagerTransaksi().getNewNomorUrutArPayment(division);
					String newNorek = model.getTransaksiHelper().getNextFtArpaymenthRefno();
					//TABLE PAYMENT:: BUAT HEADER
					FtArpaymenth itemArpaymentHeader = new FtArpaymenth();
//					ArpaymentheaderPK id = new ArpaymentheaderPK();
//					id.setRefno(newRefno);
//					id.setDivision(itemArinvoice.getDivisionBean().getId());
//					itemArpaymentHeader.setId(id);				
					itemArpaymentHeader.setNorek(newNorek);
//					itemArpaymentHeader.setDivisionBean(itemArinvoice.getDivisionBean());
					
					//JIKA PAKE TANGGAL TRANSAKSI MANUAL MAKA
					itemArpaymentHeader.setTrdate(itemHeader.getInvoicedate());
					
					itemArpaymentHeader.setUserid("admin");
					itemArpaymentHeader.setClosing(false);			
					itemArpaymentHeader.setEndofday(false);
					//****UPDATE HEADER
					model.getFtArpaymenthJpaService().createObject(itemArpaymentHeader);

					//TABLE PAYMENT:: DETAIL
					FtArpaymentd itemArpaymentDetail = new FtArpaymentd();
					
					FtArpaymentdPK itemDetailPK = new FtArpaymentdPK();
					FtArpaymentdPK arpaymentdetailPK = new FtArpaymentdPK();
					arpaymentdetailPK.setRefnopayment(itemArpaymentHeader.getRefno());
					arpaymentdetailPK.setRefnosales(itemArinvoice.getRefno());
		//			arpaymentdetailPK.setDivision(divisionBean.getId());
					itemArpaymentDetail.setId(arpaymentdetailPK);				
		//			model.arPaymentDetail.setDivisionBean(divisionBean);
					
					
					itemArpaymentDetail.setFtarpaymenthBean(itemArpaymentHeader);
					itemArpaymentDetail.setFtsaleshBean(itemArinvoice);
					
//					itemArpaymentDetail.setDivisionBean(itemArinvoice.getDivisionBean());
					itemArpaymentDetail.setFtsaleshBean(itemArinvoice);
					
					double jumlahBayar = itemArinvoice.getAmountafterdiscafterppn() + itemArinvoice.getAmountrevisi()
							- itemArinvoice.getAmountpay()  -itemArinvoice.getAmountreturtampung();
					itemArpaymentDetail.setCashamountpay(jumlahBayar);
					itemArpaymentDetail.setSubtotalpay(jumlahBayar);
					
					itemArpaymentDetail.setGiroamountpay(0.0);
					itemArpaymentDetail.setTransferamountpay(0.0);
					itemArpaymentDetail.setReturamountpay(0.0);
					itemArpaymentDetail.setPotonganamount(0.0);
					itemArpaymentDetail.setKelebihanbayaramount(0.0);
					//****UPDATE DETAIL
					model.getFtArpaymentdJpaService().updateObject(itemArpaymentDetail);
					
					//UPDATE INVOICE
					double returTampung = 0.0;
					if (itemArinvoice.isReturtampunglunas()==false){
						returTampung = itemArinvoice.getAmountreturtampung();
					}
					itemArinvoice.setAmountpay(itemArinvoice.getAmountafterdiscafterppn() + itemArinvoice.getAmountrevisi() - returTampung);
					
            		if (itemArinvoice.getAmountafterdiscafterppn() + itemArinvoice.getAmountrevisi() > itemArinvoice.getAmountpay()){
            			itemArinvoice.setLunas(false);
            		}else{
            			itemArinvoice.setLunas(true);
            		}
            		
					model.getFtSaleshJpaService().updateObject(itemArinvoice);
					
		} catch(Exception ex){
			Notification.show("Error Pelunasan!!");		                        		
			ex.printStackTrace();
		}
		
	}

	public void previewInvoice(List<FtSalesh> listFtSalesh){
		String inputFilePath = "";
		
		inputFilePath = "/erp/distribution/reports/invoicestd1/invoicestd1all.jasper";
		
		if (model.getSysvarHelper().getFormatFaktur()==10) {
			inputFilePath = "/erp/distribution/reports/invoicestd1/invoicestd1all.jasper";
		} else if (model.getSysvarHelper().getFormatFaktur()==11) {
			inputFilePath = "/erp/distribution/reports/invoicestd2/invoicestd2all.jasper";
		} else if (model.getSysvarHelper().getFormatFaktur()==12) {
			inputFilePath = "/erp/distribution/reports/invoicestd3/invoicestd3all.jasper";
		} else if (model.getSysvarHelper().getFormatFaktur()==13) {
			inputFilePath = "/erp/distribution/reports/invoicestd4/invoicestd4all.jasper";
		} else if (model.getSysvarHelper().getFormatFaktur()==14) {
			inputFilePath = "/erp/distribution/reports/invoicestd5/invoicestd5all.jasper";
		} else if (model.getSysvarHelper().getFormatFaktur()==15) {
			inputFilePath = "/erp/distribution/reports/salesorder/invoicestd/invoicestd1/invoicestd1all.jasper";
		}
		
		String outputFilePath = "invoicestd1";
		
		try {			
			final JasperReport report;
			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
//		if (model.getItemHeader().getTipefaktur().equals("R")) {
//			parameters.put("paramJudulFaktur", model.getSysvarHelper().getJudulFakturRetur());
//		}else if (model.getItemHeader().getTunaikredit().equals("T")) {
//			parameters.put("paramJudulFaktur", model.getSysvarHelper().getJudulFakturTunai());
//			parameters.put("paramJudulFaktur", model.getSysvarHelper().getJudulFakturKredit());			
//		} else {
//			parameters.put("paramJudulFaktur", "FAKTUR");						
//		}
		parameters.put("paramJudulFakturTunai", model.getSysvarHelper().getJudulFakturTunai());
		parameters.put("paramJudulFakturKredit", model.getSysvarHelper().getJudulFakturKredit());			
		parameters.put("paramJudulFakturRetur", model.getSysvarHelper().getJudulFakturRetur());		
		
		parameters.put("paramTipefaktur","F");

		parameters.put("paramCompanyName", model.getSysvarHelper().getCompanyNameFaktur());
		parameters.put("paramCompanyAddress", model.getSysvarHelper().getCompanyAddressFaktur());
		parameters.put("paramCompanyPhone", model.getSysvarHelper().getCompanyPhoneFaktur());
		parameters.put("paramCompanyNpwp", model.getSysvarHelper().getCompanyNpwpFaktur());

		parameters.put("paramProductShortname", model.getSysvarHelper().isShortNamePadaFaktur());
		
//		parameters.put("paramInvoicedateFrom",model.itemHeader.getInvoicedate());
//		parameters.put("paramInvoicedateTo",model.itemHeader.getInvoicedate());
//		parameters.put("paramRefnoFrom",model.itemHeader.getRefno());
//		parameters.put("paramRefnoTo",model.itemHeader.getRefno());
//		parameters.put("paramInvoiceno","%");
//		
//		parameters.put("paramRefno", model.itemHeader.getRefno());
		int i=1;
		for (FtSalesh domain: listFtSalesh) {
			try{
				//TAMBAH COUNTER ITEM PRINTE
				if (domain.getPrintcounter()==null) domain.setPrintcounter(1);
				domain.setPrintcounter(domain.getPrintcounter()+1);        			
				model.getFtSaleshJpaService().updateObject(domain);
			} catch(Exception ex){}
			
			if (i==1) {
				parameters.put("paramRefno1_1", domain.getRefno());				
			}else if (i==2) {
				parameters.put("paramRefno1_2", domain.getRefno());								
			}else if (i==3) {
				parameters.put("paramRefno1_3", domain.getRefno());								
			}else if (i==4) {
				parameters.put("paramRefno1_4", domain.getRefno());								
			}else if (i==5) {
				parameters.put("paramRefno1_5", domain.getRefno());								
			}else if (i==6) {
				parameters.put("paramRefno1_6", domain.getRefno());								
			}else if (i==7) {
				parameters.put("paramRefno1_7", domain.getRefno());								
			}else if (i==8) {
				parameters.put("paramRefno1_8", domain.getRefno());								
			}else if (i==9) {
				parameters.put("paramRefno1_9", domain.getRefno());								
			}else if (i==10) {
				parameters.put("paramRefno1_10", domain.getRefno());								
			}else if (i==11) {
				parameters.put("paramRefno1_11", domain.getRefno());								
			}else if (i==12) {
				parameters.put("paramRefno1_12", domain.getRefno());								
			}else if (i==13) {
				parameters.put("paramRefno1_13", domain.getRefno());								
			}else if (i==14) {
				parameters.put("paramRefno1_14", domain.getRefno());								
			}else if (i==15) {
				parameters.put("paramRefno1_15", domain.getRefno());								
			}else if (i==16) {
				parameters.put("paramRefno1_16", domain.getRefno());								
			}else if (i==17) {
				parameters.put("paramRefno1_17", domain.getRefno());								
			}else if (i==18) {
				parameters.put("paramRefno1_18", domain.getRefno());								
			}else if (i==19) {
				parameters.put("paramRefno1_19", domain.getRefno());								
			}else if (i==20) {
				parameters.put("paramRefno1_20", domain.getRefno());								
			}else if (i==21) {
				parameters.put("paramRefno1_21", domain.getRefno());								
			}else if (i==22) {
				parameters.put("paramRefno1_22", domain.getRefno());								
			}else if (i==23) {
				parameters.put("paramRefno1_23", domain.getRefno());								
			}else if (i==24) {
				parameters.put("paramRefno1_24", domain.getRefno());								
			}else if (i==25) {
				parameters.put("paramRefno1_25", domain.getRefno());								
			}else if (i==26) {
				parameters.put("paramRefno1_26", domain.getRefno());								
			}else if (i==27) {
				parameters.put("paramRefno1_27", domain.getRefno());								
			}else if (i==28) {
				parameters.put("paramRefno1_28", domain.getRefno());								
			}else if (i==29) {
				parameters.put("paramRefno1_29", domain.getRefno());								
			}else if (i==30) {
				parameters.put("paramRefno1_30", domain.getRefno());								
			}else if (i==31) {
				parameters.put("paramRefno1_31", domain.getRefno());								
			}else if (i==32) {
				parameters.put("paramRefno1_32", domain.getRefno());								
			}else if (i==33) {
				parameters.put("paramRefno1_33", domain.getRefno());								
			}else if (i==34) {
				parameters.put("paramRefno1_34", domain.getRefno());								
			}else if (i==35) {
				parameters.put("paramRefno1_35", domain.getRefno());								
			}else if (i==36) {
				parameters.put("paramRefno1_36", domain.getRefno());								
			}else if (i==37) {
				parameters.put("paramRefno1_37", domain.getRefno());								
			}else if (i==38) {
				parameters.put("paramRefno1_38", domain.getRefno());								
			}else if (i==39) {
				parameters.put("paramRefno1_39", domain.getRefno());								
			}else if (i==40) {
				parameters.put("paramRefno1_40", domain.getRefno());								
			}
			i++;
		}

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
		
		String fileName = "invoice_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
		
	}
	
	private Item itemTableSelected=null;
	
	@Override
	public void valueChange(ValueChangeEvent event) {
	}
	
	@Override
	public void itemClick(ItemClickEvent event) {
		try{
			view.setDisplayFooter();
		} catch (Exception ex){}
		
	}
	
	
	public void previewInvoicePenjualanNew(int formatFaktur, List<FtSalesh> listFtSalesh){
//		model.getItemHeader().setRefno((long) 273889);
		fillDatabaseReport(listFtSalesh);
		//2. PREVIEW LAPORAN
		if (formatFaktur==21) {
			
			showPreview("/erp/distribution/reports/salesorder/invoicestd/invoicestd21/invoicestd21Ds.jasper", "notanew");			
		} else if (formatFaktur==22){
			showPreview("/erp/distribution/reports/salesorder/invoicestd/invoicestd22/invoicestd22Ds.jasper", "notanew");			
			
		}
		
	}
	private List<ZLapTemplate2> lapTemplate2 = new ArrayList<ZLapTemplate2>();

	public void fillDatabaseReport(List<FtSalesh> listFtSalesh){
		List<FtSalesd> listFtSalesd = new ArrayList<FtSalesd>();
		for (FtSalesh ftSalesh: listFtSalesh) {
			long paramRefno=0;
			try{
				paramRefno = ftSalesh.getRefno();
			}catch(Exception ex){}
			
			List<FtSalesd> listFtSalesd1 = new ArrayList<FtSalesd>();	
			listFtSalesd1 = model.getFtSalesdJpaService()
						.findAllByRefno(paramRefno);			
			listFtSalesd.addAll(listFtSalesd1);
		}
		fillIntoTemplateReport(listFtSalesd);
	}		
	
	public void fillIntoTemplateReport(List<FtSalesd> listFtSalesd){

		//MENGHINDARI DOUBLE
		lapTemplate2 = new ArrayList<ZLapTemplate2>();		
				
		for (FtSalesd ftSalesd: listFtSalesd){			
				FtSalesd newFtSalesd = new FtSalesd();
				HeaderDetilSalesHelper headerDetilHelper = new HeaderDetilSalesHelperImpl(ftSalesd);
				headerDetilHelper.setRoundedTotal(true);
				newFtSalesd = headerDetilHelper.getFillFtSalesd();
				
				ZLapTemplate2 domain = new ZLapTemplate2();		
				
				String vendorName  = "";
				try{
					vendorName  = ftSalesd.getFproductBean().getFvendorBean().getVcode()  + "-" + ftSalesd.getFproductBean().getFvendorBean().getVname();
				} catch(Exception ex){}
				
				String areaName  = ftSalesd.getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getId()  
						+ "-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getFsubareaBean().getFareaBean().getDescription();
				String tipeName = ftSalesd.getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getId()
						+ "-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getFcustomersubgroupBean().getDescription();
				String custName = ftSalesd.getFtsaleshBean().getFcustomerBean().getCustname() + " (" + 
						"-" + ftSalesd.getFtsaleshBean().getFcustomerBean().getCustno() + ")";
				String productGroupName  = ftSalesd.getFproductBean().getFproductgroupBean().getId() 
						+ "-" + ftSalesd.getFproductBean().getFproductgroupBean().getDescription();
				
				String custAddress1 = ftSalesd.getFtsaleshBean().getFcustomerBean().getAddress1();
				String custPhone1 = "";
				if (ftSalesd.getFtsaleshBean().getFcustomerBean().getPhone1()!=null) {
					if (! ftSalesd.getFtsaleshBean().getFcustomerBean().getPhone1().trim().equals("")){
						custPhone1 = " Telp " + ftSalesd.getFtsaleshBean().getFcustomerBean().getPhone1().trim();
					}
				} 
				String custCity1 = "";
				if (ftSalesd.getFtsaleshBean().getFcustomerBean().getCity1()!=null) {
					if (! ftSalesd.getFtsaleshBean().getFcustomerBean().getCity1().trim().equals("")){
						custCity1 = " Telp " + ftSalesd.getFtsaleshBean().getFcustomerBean().getCity1().trim();
					}
				} 
				custCity1 += custPhone1;
				
				String tunaiKredit = ftSalesd.getFtsaleshBean().getTunaikredit();
				String tipeFaktur = ftSalesd.getFtsaleshBean().getTipefaktur();
				String tipeJual = ftSalesd.getFtsaleshBean().getTipejual();
				
				String invoiceno = ftSalesd.getFtsaleshBean().getInvoiceno();
				String invoiceRefno = String.valueOf(ftSalesd.getFtsaleshBean().getRefno());
				Date invoiceTrdate = ftSalesd.getFtsaleshBean().getInvoicedate();
				Date invoiceDuedate = ftSalesd.getFtsaleshBean().getDuedate();
				String productName = ftSalesd.getFproductBean().getPcode() + "-" +ftSalesd.getFproductBean().getPname();
				
				String salesmanName = ftSalesd.getFtsaleshBean().getFsalesmanBean().getSpcode() + "-" + ftSalesd.getFtsaleshBean().getFsalesmanBean().getSpname();
				String nomorOrder = ftSalesd.getFtsaleshBean().getTunaikredit() + "/" + ftSalesd.getFtsaleshBean().getOrderno();
				
				double totalBrutoDpp = newFtSalesd.getSubtotal();
				double totalBrutoAfterPPn = newFtSalesd.getSubtotalafterppn();
				double totalDisc1BarangAfterPpn = newFtSalesd.getDisc1rpafterppn();
				double totalDisc2BarangAfterPpn = newFtSalesd.getDisc2rpafterppn();
				double totalSubTotalAfterPpn = newFtSalesd.getSubtotalafterdisc2afterppn();
				
				double totalDisc1NotaAfterPpn = newFtSalesd.getDiscNota1RpAfterPpn() ;
				double totalDiscNotaAfterPpn = newFtSalesd.getDiscNotaRpAfterPpn();
				
				double totalDpp = newFtSalesd.getSubTotalAfterDiscNota();
				double totalAfterDisc2AfterPpn = newFtSalesd.getSubTotalAfterDiscNotaAfterPpn();
				
				domain.setGrup1(invoiceno);
				domain.setGrup2("-");
				domain.setGrup3(invoiceno);			
			
			
				String judulFaktur = "FAKTUR PENJUALAN";
				if (ftSalesd.getFtsaleshBean().getTipefaktur().equals("R")) {
					judulFaktur = model.getSysvarHelper().getJudulFakturRetur();
				}else if (ftSalesd.getFtsaleshBean().getTunaikredit().equals("T")) {
					judulFaktur= model.getSysvarHelper().getJudulFakturTunai();
				}else if (ftSalesd.getFtsaleshBean().getTunaikredit().equals("K"))  {
					judulFaktur = model.getSysvarHelper().getJudulFakturKredit();			
				} else {
					judulFaktur = "FAKTUR";						
				}
				String companyInvoiceName =  model.getSysvarHelper().getCompanyNameFaktur();
				String companyInvoiceAddress =  model.getSysvarHelper().getCompanyAddressFaktur();
				String companyInvoicePhone =  model.getSysvarHelper().getCompanyPhoneFaktur();
				String companyInvoiceNpwp =  model.getSysvarHelper().getCompanyNpwpFaktur();
				
				boolean isShortNameAndPackaging = model.getSysvarHelper().isShortNamePadaFaktur();
				String kodeProduct = "";
				String namaProduct ="";
				if (isShortNameAndPackaging) {
					kodeProduct = ftSalesd.getFproductBean().getShortcode();
					namaProduct = ftSalesd.getFproductBean().getShortname() + " " + ftSalesd.getFproductBean().getShortpackaging();
				}else {
					kodeProduct = ftSalesd.getFproductBean().getPcode();
					namaProduct = ftSalesd.getFproductBean().getPname() + " " + ftSalesd.getFproductBean().getPackaging();					
				}
				
				domain.setString1(judulFaktur);
				domain.setString2(companyInvoiceName);
				domain.setString3(companyInvoiceAddress);
				domain.setString4(companyInvoicePhone);  
				domain.setString5(companyInvoiceNpwp);  
				
				domain.setDate1(invoiceTrdate);
				domain.setDate2(invoiceDuedate);
				
				domain.setString6(custName);  
				domain.setString7(custAddress1);  
				domain.setString8(custCity1);  
	
				domain.setString9(invoiceno);  
				domain.setString10(salesmanName);  
				domain.setString11(nomorOrder);
				
				KonversiProductAndStock kps = new KonversiProductAndStockImpl(ftSalesd.getQty(), ftSalesd.getFproductBean());						
				int qtyKrt = kps.getBesFromPcs();
				
				String qtyReguler = "";
				String qtyFreeGood = "";
				if (model.getSysvarHelper().getFormatFaktur()==22) {
					qtyReguler = kps.getBesSedKecStringUom();					
					qtyFreeGood = "*" + kps.getBesSedKecStringUom();								
				}else {
					qtyReguler = kps.getBesSedKecString();					
					qtyFreeGood = "*" + kps.getBesSedKecString();								
				}
				
				if (! ftSalesd.getId().getFreegood()){
					qtyFreeGood="";
				}else {
					qtyReguler="";
					namaProduct += "(Bonus)";
				}
				
				domain.setString12(kodeProduct);  
				
				domain.setString13(namaProduct);  
				domain.setString14(qtyReguler);  
				domain.setString15(qtyFreeGood);  
				
				domain.setDouble1(newFtSalesd.getSpriceafterppn());					
				domain.setDouble2(totalBrutoAfterPPn);	
				
				domain.setDouble3(ftSalesd.getDisc1());
				domain.setDouble4(totalDisc1BarangAfterPpn);
				domain.setDouble5(ftSalesd.getDisc2());
				domain.setDouble6(totalDisc2BarangAfterPpn);

				domain.setDouble7(totalSubTotalAfterPpn);
				
				domain.setDouble8(ftSalesd.getFtsaleshBean().getDisc1());
				domain.setDouble9(totalDisc1NotaAfterPpn);
				domain.setDouble10(ftSalesd.getFtsaleshBean().getDisc());
				domain.setDouble11(totalDiscNotaAfterPpn);
				domain.setDouble12(totalDpp);
				domain.setDouble13(totalAfterDisc2AfterPpn);
									
				domain.setInt2(ftSalesd.getFtsaleshBean().getPrintcounter());
				lapTemplate2.add(domain);						
			
		}
		
	}
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
//			final JasperReport report;
//			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
		final Map parameters=new HashMap();
		parameters.put("CompanyName","");
		
		
		//CONNECTION
//		final Connection con = new ReportJdbcConfigHelper().getConnection();
		final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lapTemplate2);
		InputStream reportPathStream = getClass().getResourceAsStream(inputFilePath);
		final JasperPrint jasperPrint = JasperFillManager.fillReport(reportPathStream, parameters, dataSource);
		
		
		StreamResource.StreamSource source = new StreamSource() {			
			@Override
			public InputStream getStream() {
				byte[] b = null;
				try {
//					b = JasperRunManager.runReportToPdf(report, parameters, con);
					b = JasperExportManager.exportReportToPdf(jasperPrint);
				} catch (JRException ex) {
					System.out.println(ex);
				}
				return new ByteArrayInputStream(b);
			}
		};
		
		String fileName = "sales_" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new_" + outputFilePath, false);
	
		} catch (JRException e) {
			e.printStackTrace();
		}
	
		
	}
	
	
	private static final ShortcutAction ENTER = new ShortcutAction("Enter",
			KeyCode.ENTER, null);

	private static final ShortcutAction ENTER_SEARCH= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ENTER_TABLE= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ENTER_FORM= new ShortcutAction("Enter",
			KeyCode.ENTER, null);

	private static final ShortcutAction ESCAPE = new ShortcutAction("Escape",
			KeyCode.ESCAPE, null);
	private static final ShortcutAction ESCAPE_SEARCH = new ShortcutAction("Escape",
			KeyCode.ESCAPE, null);
	private static final ShortcutAction ESCAPE_TABLE = new ShortcutAction("Escape",
			KeyCode.ESCAPE, null);
	private static final ShortcutAction ESCAPE_FORM = new ShortcutAction("Escape",
			KeyCode.ESCAPE, null);
	
	//Key Code Baru
	private static final ShortcutAction INSERT= new ShortcutAction("Insert",
			KeyCode.INSERT, null);
	private static final ShortcutAction DELETE = new ShortcutAction("Del",
			KeyCode.DELETE, new int[] { ShortcutAction.ModifierKey.SHIFT});
	
	private static final ShortcutAction ALTS = new ShortcutAction("Save",
			KeyCode.S, new int[] { ShortcutAction.ModifierKey.ALT });
	private static final ShortcutAction ALTC = new ShortcutAction("Cancel",
			KeyCode.C, new int[] { ShortcutAction.ModifierKey.ALT });
	
	
	
	private static final ShortcutAction REFRESH = new ShortcutAction("Refresh",
			KeyCode.F5, null);

	private static final ShortcutAction HELP = new ShortcutAction("Help",
			KeyCode.F1, null);
	private static final ShortcutAction EDITMODE = new ShortcutAction("Edit Mode",
			KeyCode.F2, null);
	private static final ShortcutAction FINDMODE = new ShortcutAction("Find Mode",
			KeyCode.F3, null);
	private static final ShortcutAction FIND = new ShortcutAction("Find ",
			KeyCode.F4, null);
	
	private static final ShortcutAction ADD = new ShortcutAction("Add",
			KeyCode.A, new int[] { ShortcutAction.ModifierKey.ALT });
	private static final ShortcutAction DEL = new ShortcutAction("Del",
			KeyCode.D, new int[] { ShortcutAction.ModifierKey.ALT });
	
	private static final Action[] ACTIONS_SEARCH = new Action[] {ENTER_SEARCH, ESCAPE_SEARCH, INSERT, DELETE,  
		ALTS, ALTC, REFRESH, HELP, EDITMODE, FINDMODE, FIND, ADD, DEL};
	private static final Action[] ACTIONS_TABLE = new Action[] {ENTER_TABLE, ESCAPE_TABLE, INSERT, DELETE,  
		ALTS, ALTC, REFRESH, HELP, EDITMODE, FINDMODE, FIND, ADD, DEL};
	private static final Action[] ACTIONS_FORM = new Action[] {ENTER_FORM, ESCAPE_FORM, INSERT, DELETE,  
		ALTS, ALTC, REFRESH, HELP, EDITMODE, FINDMODE, FIND, ADD, DEL};
	
	private static final Action[] ACTIONS = new Action[] {};
	private static final Action[] SHORTCUT_ACTIONS = new Action[] { INSERT, DELETE, ESCAPE, 
		ALTS, ALTC, ENTER, REFRESH, HELP, EDITMODE, FINDMODE, FIND, ADD, DEL};
	
	@Override
	public Action[] getActions(Object target, Object sender) {
		if (sender == view.getPanelTop()) {
			return ACTIONS_SEARCH;
		} else if (sender== view.getPanelTabel()){
			return ACTIONS_TABLE;
		} else if (sender== view.getPanelForm()){
			return ACTIONS_FORM;
		}
		return ACTIONS;
	}
	@Override
	public void handleAction(Action action, Object sender, Object target) {		
		if (action==ENTER_SEARCH){
			view.getBtnSearch().click();
			view.getTable().focus();
		}else if (action==ENTER_TABLE){
			view.getForm().focus();
		}else if (action==ENTER_FORM){
		}else if (action==ESCAPE_SEARCH){
			view.getTable().focus();
		}else if (action==ESCAPE_TABLE){
		}else if (action==ALTC){
		}else if (action==ENTER){
			
		}else if (action==EDITMODE){
			view.getForm().focus();			
		}else if (action==FINDMODE){
			view.getFieldSearchById().focus();
		}else if (action==FIND){			
//			searchForm();
			view.getBtnSearch().click();
		}else if (action==REFRESH){
			view.getBtnReload().click();
		}else if (action==HELP){
//			helpForm();
			view.getBtnHelp().click();
		}else if (action==ADD){		
			
		}
	}

	
	
}
