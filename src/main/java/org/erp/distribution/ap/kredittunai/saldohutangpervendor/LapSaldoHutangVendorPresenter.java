package org.erp.distribution.ap.kredittunai.saldohutangpervendor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
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
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FDivision;
import org.erp.distribution.model.FSalesman;
import org.erp.distribution.model.FSubarea;
import org.erp.distribution.model.FVendor;
import org.erp.distribution.model.FtAppaymentd;
import org.erp.distribution.model.FtArpaymentd;
import org.erp.distribution.model.FtArpaymentdPK;
import org.erp.distribution.model.FtArpaymenth;
import org.erp.distribution.model.FtPurchased;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.ZLapPackingList;
import org.erp.distribution.model.ZLapSJPenagihanList;
import org.erp.distribution.model.ZLapTemplate1;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.util.FormatAndConvertionUtil;
import org.erp.distribution.util.ReportJdbcConfigHelper;
import org.vaadin.dialogs.ConfirmDialog;

import javassist.compiler.NoFieldException;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.filter.And;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.filter.Or;
import com.vaadin.data.util.filter.SimpleStringFilter;
import com.vaadin.event.Action;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutListener;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout.Area;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table.HeaderClickEvent;
import com.vaadin.ui.Table.HeaderClickListener;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.Window.CloseListener;

public class LapSaldoHutangVendorPresenter implements ClickListener, ValueChangeListener, Handler, ItemClickListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LapSaldoHutangVendorModel model;
	private LapSaldoHutangVendorView view;
	
	FtPurchaseh item = new FtPurchaseh();
	
	
	public LapSaldoHutangVendorPresenter(LapSaldoHutangVendorModel model, LapSaldoHutangVendorView view){
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
		
		view.getBtnTerbitkanSJ().addClickListener(this);
		view.getBtnPencabutanSJ().addClickListener(this);
		
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

		
		ValueChangeListener listenerComboDivision = new ValueChangeListener() {			
			@Override
			public void valueChange(ValueChangeEvent event) {
				try{
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					FDivision divisionBean = new FDivision();
					
					divisionBean = (FDivision) view.getFieldSearchComboDivisi().getConvertedValue();
//					Date tanggalTransaksiDivision = 
//							model.getTransaksiHelper().getCurrentTanggalTransaksiBerjalan(divisionBean);
					Date tanggalTransaksiDivision = 
					model.getTransaksiHelper().getCurrentTransDate();
			
					view.getLabelTanggalTransaksiDivisi().setContentMode(ContentMode.HTML);
					view.getLabelTanggalTransaksiDivisi().setValue("<h3><font color='BLUE'> (TGL KIRIM/SETOR) " + divisionBean.getDescription().toUpperCase() + " : " +
							sdf.format(tanggalTransaksiDivision) + "</font></h3>");
				} catch(Exception ex){}
			}
		};
		view.getFieldSearchComboDivisi().setImmediate(true);
		view.getFieldSearchComboDivisi().addValueChangeListener(listenerComboDivision);
		
		
	}
	public void initListenerSubWindow(){
		
	}
	
	public void initDisplayFirst(){
		view.setDisplay();
	}
	public void initDisplay(){
		//1. Table
		model.initVariableData();
		view.setDisplay();
	}

	@Override
	public void buttonClick(ClickEvent event) {
		//Antisipasi
		if (view.getOperationStatus()==null) view.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		if (view.getOperationStatus().equals("")) view.setOperationStatus(EnumOperationStatus.OPEN.getStrCode());
		
			
		if (event.getButton() == view.getBtnSearch()) {
			searchFormJpa();
//			searchForm();
		} else if (event.getButton() == view.getBtnReload()){
			reloadForm();			
			if (view.getOperationStatus().equals(EnumOperationStatus.OPEN.getStrCode())){
				view.getTable().focus();
			}
		} else if (event.getButton() == view.getBtnPrint()){			
			printSaldoHutang();
		} else if (event.getButton() == view.getBtnTerbitkanSJ()){
			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi SJ", "Yakin Terbitkan Surat Jalan Penagihan? ", 
					 "Oke Terbitkan SJ", "Cancel", konfirmDialogTerbitkanSJPenagihan);
			 
			   final ShortcutListener enter = new ShortcutListener("Oke",
		                KeyCode.ENTER, null) {
		            @Override
		            public void handleAction(Object object, Object target) {
		            	d.close();
		            }
		        };
			
			 d.setStyleName("dialog");
			 d.getOkButton().setStyleName("small");
			 d.getCancelButton().setStyleName("small");
			 d.focus();
		} else if (event.getButton() == view.getBtnPencabutanSJ()){
			
			 final ConfirmDialog d = ConfirmDialog.show(view.getUI(),"Konfirmasi SJ", "Yakin Mencabut Surat Jalan Penagihan? ", 
					 "Oke Terbitkan SJ", "Cancel", konfirmDialogPencabutanSJPenagihan);
			 
			   final ShortcutListener enter = new ShortcutListener("Oke",
		                KeyCode.ENTER, null) {
		            @Override
		            public void handleAction(Object object, Object target) {
		            	d.close();
		            }
		        };
			
			 d.setStyleName("dialog");
			 d.getOkButton().setStyleName("small");
			 d.getCancelButton().setStyleName("small");
			 d.focus();
		}else if (event.getButton() == view.getBtnHelp()){
		} else if (event.getButton() == view.getBtnPay()){
		} else if (event.getButton() == view.getBtnLunaskan()){			
		}	else if (event.getButton()==view.getBtnSelisihPlusMinus()){
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
	
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																									
    ConfirmDialog.Listener konfirmDialogTerbitkanSJPenagihan = new ConfirmDialog.Listener() {					
		//@Override
		public void onClose(ConfirmDialog dialog) {
			
            if (dialog.isConfirmed()) {
                // Confirmed to continue
            	terbitkanSJPenagihan();
            } else {
            // User did not confirm
            }
		}
	};
    ConfirmDialog.Listener konfirmDialogPencabutanSJPenagihan = new ConfirmDialog.Listener() {					
		//@Override
		public void onClose(ConfirmDialog dialog) {
			
            if (dialog.isConfirmed()) {
                // Confirmed to continue
            	pencabutanSJPenagihan();
            } else {
            // User did not confirm
            }
		}
	};

	public void terbitkanSJPenagihan(){
	}
	
	public void pencabutanSJPenagihan(){
		
	}
	
	public void searchFormJpa(){
		//1. Remove filter dan Refresh container dalulu dahulu
		model.getTableBeanItemContainer().removeAllItems();
		model.getTableBeanItemContainer().removeAllContainerFilters();		
		
		//2. AMBAIL DARI DATABASE dengan Primary Key dan 
		String nopo = "%";
		String invoiceNo = "%" + view.getFieldSearchByInvoice().getValue().toString().trim() + "%";
		
		//JIKA TIDAK DIPILIH YA KOSONG
		String strDivision="%";
		FDivision div = new FDivision();
		try{
			div = model.getBeanItemContainerDivision().getItem(view.getFieldSearchComboDivisi().getValue()).getBean();
			strDivision = "%" + div.getId().trim() + "%";		
		} catch(Exception ex){}

		
		Date invoicedateFrom = null;
		Date invoicedateTo =null;
				
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try{
			invoicedateFrom = sdf.parse("01-01-1900");
			invoicedateTo= sdf.parse("01-01-3000");
		} catch(Exception ex){}
		
		try{
			//TANGGAL INVOICE
			long tglInvoiceFromLong = 0;
			long tglInvoiceToLong = 0;
			
			try{
				tglInvoiceFromLong = view.getFieldSearchByDateInvoiceFrom().getValue().getTime();
			} catch (Exception ex){}
			
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
		
		String tipeFaktur = "%";
		tipeFaktur = "F";
//		try{
//			if (view.getFieldSearchComboToCanvas().getValue().equals("TO")){				
//				tipeJual= "TO";
//				tipeFaktur = "F";
//			} else if (view.getFieldSearchComboToCanvas().getValue().equals("C")){
//				tipeJual = "C";
//				tipeFaktur = "F";
//			} else if (view.getFieldSearchComboToCanvas().getValue().equals("R")){
//				tipeFaktur = "R";
//			} else if (view.getFieldSearchComboToCanvas().getValue().equals("SNR")){
//				tipeFaktur = "F";
//			} 
//			
//		} catch(Exception ex){
//			ex.printStackTrace();
//		}
		
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
		
		String strVcode = "%";
		try{
			strVcode = ((FVendor) view.getSearchComboVendor().getValue()).getVcode();
		} catch(Exception ex){}
		
		String strVname = "%";
		try{
			strVname = ((FVendor) view.getSearchComboVendor().getValue()).getVname();
		} catch(Exception ex){}
		
		//JIKA RECAP NO CUMA SATU
		String recapNoCumaSatu = view.getFieldSearchByRekap().getValue().toString().trim();
		
		List<FtPurchaseh> list = model.getFtPurchasehJpaService().findAllPenagihan(nopo, invoiceNo, tipeFaktur,  
				invoicedateFrom, invoicedateTo, lunasFrom, lunasTo, strVcode, strVname);		
		
		
		model.getTableBeanItemContainer().addAll(list);
		//3. Filter Default
		model.setFilterDefaultBeanItemContainer();
		
		
		
		view.setDisplay();
		//Focus
		view.getTable().focus();
		
	}
	
	
	public void cekAndDeleteProcessedByOthers(boolean bolValue){
	}
	
	
	public int reloadForm(){		
		model.initVariableData();
		view.setDisplay();
		
		return 0;
	}
	String paramSuratJalanList = "";
	String paramInvoiceList = "";
	public void resetParameters(){
		paramSuratJalanList = "";
		paramInvoiceList = "";
	}
	public void reloadParameter(){
	}	
	public void printSaldoHutang(){
		resetParameters();
		//pengisian parameter diserahkan saat :: fillDatabaseReportLengkap();
		reloadParameter();
		
		//1. ISI DATABASE UNTUK TEMP
		fillDatabaseReportLengkap();
		//2. PREVIEW LAPORAN
		showPreview("/erp/distribution/reports/appayment/saldohutangvendor/saldohutangvendor1Ds.jasper", "saldopiutangvendor1");
		
	}

	private List<ZLapTemplate1> lapTemplate1 = new ArrayList<ZLapTemplate1>();
	public void fillDatabaseReportLengkap(){
		//MENGHINDARI DOUBLE
		lapTemplate1 = new ArrayList<ZLapTemplate1>();
		Set<String> setInvoiceList = new LinkedHashSet<String>();

		//2. MASUKKAN YANG DISELEKSI KE DALAM TABLE REPORT TEMPORER TAHAP1
		Collection itemIds = view.getGrid1().getSelectionModel().getSelectedRows();
		Iterator<FtPurchaseh> iterFtPurchaseh = itemIds.iterator();
		while (iterFtPurchaseh.hasNext()){
			FtPurchaseh itemArinvoice = new FtPurchaseh();
			itemArinvoice = iterFtPurchaseh.next();
			//Menghindari null			
				List<FtPurchased> listFtSalesd = new ArrayList<FtPurchased>(itemArinvoice.getFtpurchasedSet());
				setInvoiceList.add(itemArinvoice.getInvoiceno());
				
				ZLapTemplate1 domain = new ZLapTemplate1();
//				domain.setGrup1(itemArinvoice.getSjpenagihanno());
				domain.setGrup1("Grup1");
				domain.setGrup2("Grup2");
				domain.setGrup3("Grup3");


				domain.setString1(itemArinvoice.getNopo());
				domain.setString2(itemArinvoice.getInvoiceno());
				domain.setString3(itemArinvoice.getTipefaktur());
				domain.setString4(itemArinvoice.getFvendorBean().getVcode() +
						"-" + itemArinvoice.getFvendorBean().getVname());
				
				domain.setDate1(itemArinvoice.getInvoicedate());
				domain.setDate2(itemArinvoice.getDuedate());

				domain.setDouble1(itemArinvoice.getAmountafterdiscafterppn());
				domain.setDouble2(itemArinvoice.getAmountafterdiscafterppn() - itemArinvoice.getAmountpay());
				
//				model.getLapTemplate1JpaService().createObject(domain);
				lapTemplate1.add(domain);
			
		}
		
		//:: INVOICE LIST
//		Iterator<String> iterInvoiceList = setInvoiceList.iterator();
//		while (iterInvoiceList.hasNext()){
//			paramInvoiceList += ", " + iterInvoiceList.next();
//		}
		
	}		
	public void showPreview(String inputFilePath, String outputFilePath){
		try {			
//			final JasperReport report;
//			report = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream(inputFilePath));
		
			
		final Map parameters=new HashMap();
		parameters.put("CompanyName","");
		
		//BUAT TANPA KOMA DI DEPAN
//		try{
//			paramSuratJalanList = paramSuratJalanList.trim().substring(1, paramSuratJalanList.length());
//			paramInvoiceList = paramInvoiceList.trim().substring(1, paramInvoiceList.length());
//		} catch(Exception ex){
//			ex.printStackTrace();
//		}
		
		parameters.put("paramSuratJalanList", paramSuratJalanList);
		parameters.put("paramInvoiceList", paramInvoiceList);
		
		//CONNECTION
//		final Connection con = new ReportJdbcConfigHelper().getConnection();
		final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lapTemplate1);
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
		
		String fileName = "ap_saldo" + outputFilePath + "_" +System.currentTimeMillis()+".pdf";
		StreamResource resource = new StreamResource( source, fileName);
		resource.setMIMEType("application/pdf");
		resource.getStream().setParameter("Content-Disposition","attachment; filename="+fileName);		
		
		view.getUI().getPage().open(resource, "_new_ap_saldo_" + outputFilePath, false);
	
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
