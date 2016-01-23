package org.erp.distribution.ap.kredittunai.saldohutangpervendor;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.ar.kredittunai.paylist.ArPaymentCustomerModel;
import org.erp.distribution.ar.kredittunai.paylist.ArPaymentCustomerPresenter;
import org.erp.distribution.ar.kredittunai.paylist.ArPaymentCustomerView;
import org.erp.distribution.ar.kredittunai.revisinota.RevisiNotaModel;
import org.erp.distribution.ar.kredittunai.revisinota.RevisiNotaPresenter;
import org.erp.distribution.ar.kredittunai.revisinota.RevisiNotaView;
import org.erp.distribution.model.FtAppaymentd;
import org.erp.distribution.model.FtArpaymentd;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.User;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;

import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class LapSaldoHutangVendorView extends CustomComponent {
	
	private LapSaldoHutangVendorModel model;
	private VerticalLayout content = new VerticalLayout();

	private Table table;
	private Form form;
	private FieldFactory fieldFactory;

	private Class<FtSalesh> entityClass;
	
	private Object[] formPropertyIds;
	
	private String operationStatus;

    private Label labelTanggalTransaksiDivisi = new Label("TGL. SJ PENAGIHAN");
    	
	//Additional Component	private TextField fieldSearchByRekap;
	private TextField fieldSearchByRekap;
	private Button btnSelectRekapNo = new Button("F");	

	private DateField dateFieldDatePembayaranManual = new DateField();
	private CheckBox checkBoxGunakanTanggalManual = new CheckBox("GUNAKAN TGL. SJ PENAGIHAN MANUAL");
	
	private TextField fieldSearchById;
	private ComboBox fieldSearchComboDivisi;
	private TextField fieldSearchByDesc;
	private TextField fieldSearchByIdCustomer;	
	private TextField fieldSearchByNamaCustomer;
	private TextField fieldSearchByIdSalesman;
	private TextField fieldSearchByNamaSalesman;
	private TextField fieldSearchByInvoice;
	private TextField fieldSearchBySJPenagihan;
	private DateField fieldSearchByDateInvoiceFrom;
	private DateField fieldSearchByDateInvoiceTo;
	private ComboBox fieldSearchComboLunas;
	private ComboBox fieldSearchComboToCanvas;
	private ComboBox fieldSearchComboVendor = new ComboBox("SUPPLIER");
	
	private DateField fieldSearchByDateInvoiceKirimFrom;
	private DateField fieldSearchByDateInvoiceKirimTo;
	
	private ComboBox fieldSearchComboTunaiKredit = new ComboBox("T/K");
	
	private CheckBox checkLihatSemua = new CheckBox("Lihat Semua(Kirim dan Belum)", false);
	
	private ComboBox searchComboVendor = new ComboBox("Vendor");
	private ComboBox searchComboArea = new ComboBox("Area");
	private ComboBox searchComboSubArea = new ComboBox("Sub Area");
	
	
	private Button btnSearch;
	private Button btnReload;
	private Button btnPay;
	private Button btnLunaskan;
	private Button btnSelisihPlusMinus;
	
	private Button btnTerbitkanSJ = new Button("Terbitkan SJ");
	private Button btnPencabutanSJ = new Button("Pencabutan SJ");
	
	
	private Button btnPrint;
	private Button btnHelp;	
	private Button btnSeparator1;
	private Button btnSeparator2;
	
	//Panel
	private Panel panelUtama;
	private Panel panelTop;
	private Panel panelTabel;
	private Panel panelForm;

	VerticalLayout layoutTop = new VerticalLayout();
	
	
    //TEKS FIELD FOR SELECTED ROW
    private TextField fieldSelectedCount = new TextField("Rec Selected: ");
    private TextField fieldTunaiCount = new TextField("Tunai: ");
    private TextField fieldKreditCount = new TextField("Kredit: ");
    private TextField fieldTunaiSum= new TextField("Nilai Tunai: ");
    private TextField fieldKreditSum = new TextField("Nilai Kredit: ");
    private TextField fieldToCount = new TextField("TO: ");
    private TextField fieldCanvasCount = new TextField("Canvas: ");
    private TextField fieldToSum= new TextField("Nilai TO: ");
    private TextField fieldCanvasSum = new TextField("Nilai Canvas: ");
    private TextField fieldAmountSum= new TextField("Nilai Faktur(TO+CVS): ");
    private TextField fieldAmountPaySum = new TextField("TERBAYAR: ");
    private TextField fieldAmountReturTampunganFaktur = new TextField("RET. TAMP NOTA: ");
    private TextField fieldAmountReturTampungan = new TextField("RET. TAMP REK: ");
    private TextField fieldAmountTotal = new TextField("TOT HRS BYR: ");
    
	User userActive = new User();
	
	public LapSaldoHutangVendorView(LapSaldoHutangVendorModel model){
		this.model = model;		
		
		userActive = ((DashboardUI) getUI().getCurrent()).getUserActive();
		
		initComponent();
		initFieldFactory();
		buildView();
		
		initComponentFirst();
		
		setComponentStyles();
		
		setDisplaySearchComponent();
		//Set Awal Status form
		operationStatus = EnumOperationStatus.OPEN.getStrCode();
		
	}
	public void initComponent(){
//		table = new Table("Table:", model.getTableJpaContainer());		

		table = new Table() {
		    @Override
		    protected String formatPropertyValue(Object rowId,
		            Object colId, Property property) {
		        // Format by property type
		    	try{
			        if (property.getType() == Date.class && property.getValue() != null) {
			            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			            return df.format((Date)property.getValue());
			        }
		    	} catch(Exception ex){}
		    	try{
			        if (property.getType()==Boolean.class){
			        	if ((Boolean) property.getValue()==true) {
			        		return "OK";
			        	} else {
			        		return "-";
			        	}
			        }
		    	} catch(Exception ex){}
		        return super.formatPropertyValue(rowId, colId, property);
		    }
		};		
		
		dateFieldDatePembayaranManual.setDateFormat("dd/MM/yyyy");
		dateFieldDatePembayaranManual.setValue(new Date());
		
		fieldSearchByRekap = new TextField();
        fieldSearchByRekap.setInputPrompt("NO. REKAP");
		fieldSearchByRekap.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByRekap.setWidth("90px");
        
		btnSelectRekapNo.setStyleName(Reindeer.BUTTON_SMALL);
		
		//Init Komponen atas
		fieldSearchById = new TextField();
		fieldSearchById.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByDesc = new TextField();
		fieldSearchByDesc.setStyleName(Reindeer.TEXTFIELD_SMALL);

		fieldSearchComboDivisi = new ComboBox("DIVISI");
		fieldSearchComboDivisi.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboDivisi.setWidth("120px");
		
		fieldSearchByInvoice = new TextField();
		fieldSearchByInvoice.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByInvoice.setWidth("100px");
		fieldSearchByInvoice.setInputPrompt("NO. INVOICE");
		fieldSearchBySJPenagihan = new TextField();
		fieldSearchBySJPenagihan.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchBySJPenagihan.setWidth("100px");
		fieldSearchBySJPenagihan.setInputPrompt("SJ PENAGIHAN");
		
		fieldSearchByIdCustomer = new TextField();	
		fieldSearchByIdCustomer.setWidth("90px");
		fieldSearchByIdCustomer.setInputPrompt("ID CUST");
		fieldSearchByIdCustomer.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByNamaCustomer = new TextField();
		fieldSearchByNamaCustomer.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByNamaCustomer.setWidth("120px");
		fieldSearchByNamaCustomer.setInputPrompt("NAMA CUSTOMER");
		fieldSearchByIdSalesman = new TextField();
		fieldSearchByIdSalesman.setWidth("90px");
		fieldSearchByIdSalesman.setInputPrompt("ID SALESMAN");
		fieldSearchByIdSalesman.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByNamaSalesman = new TextField();
		fieldSearchByNamaSalesman.setWidth("120px");
		fieldSearchByNamaSalesman.setInputPrompt("NAMA SALESMAN");
		fieldSearchByNamaSalesman.setStyleName(Reindeer.TEXTFIELD_SMALL);
		
		fieldSearchByDateInvoiceFrom = new DateField("TGL INVOICE");
		fieldSearchByDateInvoiceFrom.setDateFormat("dd-MM-yyyy");
		fieldSearchByDateInvoiceFrom.setWidth("120px");
		fieldSearchByDateInvoiceFrom.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByDateInvoiceTo = new DateField("S.D");
		fieldSearchByDateInvoiceTo.setWidth("120px");
		fieldSearchByDateInvoiceTo.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByDateInvoiceTo.setDateFormat("dd-MM-yyyy");
		
		fieldSearchByDateInvoiceKirimFrom = new DateField("Tgl Setor/Kirim");
		fieldSearchByDateInvoiceKirimFrom.setDateFormat("dd-MM-yyyy");
		fieldSearchByDateInvoiceKirimFrom.setWidth("120px");
		fieldSearchByDateInvoiceKirimFrom.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByDateInvoiceKirimTo = new DateField("S.D");
		fieldSearchByDateInvoiceKirimTo.setWidth("120px");
		fieldSearchByDateInvoiceKirimTo.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByDateInvoiceKirimTo.setDateFormat("dd-MM-yyyy");
	
		fieldSearchComboLunas = new ComboBox("Lunas");
		fieldSearchComboLunas.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);				
		fieldSearchComboLunas.addItem("B");
		fieldSearchComboLunas.setItemCaption("B", "Belum");
		fieldSearchComboLunas.addItem("L");
		fieldSearchComboLunas.setItemCaption("L", "Lunas");
		fieldSearchComboLunas.addItem("S");
		fieldSearchComboLunas.setItemCaption("S", "Semua");
		fieldSearchComboLunas.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboLunas.setWidth("70px");
		fieldSearchComboLunas.setNullSelectionAllowed(false);
		fieldSearchComboLunas.select("B");
		
		fieldSearchComboToCanvas = new ComboBox("TO/CVS/R/S");
		fieldSearchComboToCanvas.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);				
		fieldSearchComboToCanvas.addItem("TO");
		fieldSearchComboToCanvas.setItemCaption("TO", "TO");
		fieldSearchComboToCanvas.addItem("C");
		fieldSearchComboToCanvas.setItemCaption("C", "Canvas");
		fieldSearchComboToCanvas.addItem("R");
		fieldSearchComboToCanvas.setItemCaption("R", "Retur");
		fieldSearchComboToCanvas.addItem("S");
		fieldSearchComboToCanvas.setItemCaption("S", "Semua");
		fieldSearchComboToCanvas.addItem("SNR");
		fieldSearchComboToCanvas.setItemCaption("SNR", "Semua Non Retur");
		fieldSearchComboToCanvas.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboToCanvas.setWidth("70px");
		fieldSearchComboToCanvas.setNullSelectionAllowed(false);
		fieldSearchComboToCanvas.select("SNR");
		
		//TUNAI KREDIT
		fieldSearchComboTunaiKredit.addItem("T");
		fieldSearchComboTunaiKredit.setItemCaption("T", "Tunai");
		fieldSearchComboTunaiKredit.addItem("K");
		fieldSearchComboTunaiKredit.setItemCaption("K", "Kredit");
		fieldSearchComboTunaiKredit.addItem("S");
		fieldSearchComboTunaiKredit.setItemCaption("S", "Semua");
		fieldSearchComboTunaiKredit.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboTunaiKredit.setWidth("70px");
		
		searchComboVendor.setContainerDataSource(model.getBeanItemContainerVendor());
		searchComboVendor.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		searchComboVendor.setNullSelectionAllowed(true);
		searchComboVendor.setFilteringMode(FilteringMode.CONTAINS);
		searchComboVendor.setWidth("150px");

		//DEFAULT VIEW
		fieldSearchComboTunaiKredit.select("S");
		fieldSearchComboTunaiKredit.setEnabled(true);
		fieldSearchComboTunaiKredit.setNullSelectionAllowed(false);

		//COMBO AREA SALESMAN DAN SUBAREA
		
		searchComboArea.setWidth("120px");
		searchComboVendor.setWidth("180px");
		searchComboSubArea.setWidth("120px");
		
		searchComboVendor.setCaption("");
		searchComboArea.setCaption("");
		searchComboSubArea.setCaption("");

		searchComboVendor.setInputPrompt("Supplier");
		searchComboArea.setInputPrompt("Area");
		searchComboSubArea.setInputPrompt("Sub Area");
		
		//FOOTER SELECTED
		fieldSelectedCount.setWidth("100px");
		fieldTunaiCount.setWidth("50px");
		fieldKreditCount.setWidth("50px");
		fieldTunaiSum.setWidth("110px");
		fieldKreditSum.setWidth("110px");
		fieldToCount.setWidth("50px");
		fieldCanvasCount.setWidth("50px");
		fieldToSum.setWidth("110px");
		fieldCanvasSum.setWidth("110px");
		fieldAmountSum.setWidth("120px");
		fieldAmountPaySum.setWidth("120px");
		fieldAmountReturTampungan.setWidth("120px");
		fieldAmountReturTampunganFaktur.setWidth("120px");
		fieldAmountTotal.setWidth("120px");
		
		btnSearch = new Button("Search");
		btnSearch.setStyleName(Reindeer.BUTTON_SMALL);
		btnReload = new Button("Reload");
		btnReload.setStyleName(Reindeer.BUTTON_SMALL);
		btnPay = new Button("Bayar");
		btnPay.setStyleName(Reindeer.BUTTON_SMALL);
		btnLunaskan = new Button("Lunas CASH");
		btnLunaskan.setStyleName(Reindeer.BUTTON_SMALL);
		
		btnSelisihPlusMinus = new Button("SELISIH +/- & Retur Tamp");
		btnSelisihPlusMinus.setStyleName(Reindeer.BUTTON_SMALL);
		
		btnPrint = new Button("Print Hutang");
		btnHelp = new Button("Help");
		
		btnPrint.addStyleName(Reindeer.BUTTON_SMALL);
		btnTerbitkanSJ.addStyleName(Reindeer.BUTTON_SMALL);
		btnPencabutanSJ.addStyleName(Reindeer.BUTTON_SMALL);
		
		btnSeparator1 = new Button("");
		btnSeparator1.setEnabled(false);
		btnSeparator2 = new Button("::");
		btnSeparator2.setEnabled(false);
                
        btnSearch.setIcon(new ThemeResource("../images/navigation/12x12/Find.png") );
        btnLunaskan.setIcon(new ThemeResource("../images/navigation/12x12/OK.png") );
        btnPay.setIcon(new ThemeResource("../images/navigation/12x12/Accounting.png") );
        btnTerbitkanSJ.setIcon(new ThemeResource("../images/navigation/12x12/OK.png") );
        btnPencabutanSJ.setIcon(new ThemeResource("../images/navigation/12x12/Undo.png") );
        
        btnPrint.setIcon(new ThemeResource("../images/navigation/12x12/Print.png") );
                
		form = new Form();
		form.setVisible(false);
		form.setBuffered(true);
		form.setImmediate(false);
	
		setSizeFull();
		content.setSizeFull();
        content.setMargin(true);
                
		panelUtama = new Panel(getCaption());
		panelUtama.setSizeFull();
		
		panelTop = new Panel();
		panelTop.setSizeFull();
		panelTabel = new Panel();
		panelTabel.setSizeFull();
		panelForm = new Panel();
		panelForm.setSizeFull();
                
		//Init komponen tengah
		table.setSizeFull();
		table.setSelectable(true);
//		table.addValueChangeListener(this);
		table.setImmediate(true);
		table.setBuffered(false);
//		table.addActionHandler(this);		
		table.setFooterVisible(true);
		
		fieldAmountReturTampunganFaktur.setImmediate(true);
                
	}
	public void initFieldFactory(){
//		fieldFactory = new FieldFactory();
//		customFieldFactory = new CustomFieldFactory();		
	}
	public void buildView(){
		//LAYOUT TOP
		panelTop = new Panel();
        layoutTop.setMargin(true);
        
        layoutTop = new VerticalLayout();
        
		HorizontalLayout layoutTopInner0 = new HorizontalLayout();	
//		layoutTopInner0.setMargin(true);
		HorizontalLayout layoutTopInner1 = new HorizontalLayout();	
		
		HorizontalLayout layoutTopInner2 = new HorizontalLayout();					
//		layoutTop.addComponent(layoutTopInner0);
		layoutTop.addComponent(layoutTopInner1);
		layoutTop.addComponent(layoutTopInner2);
		panelTop.setContent(layoutTop);
                
                //LAYOUT TABLE
		VerticalLayout layoutTable = new VerticalLayout();
		layoutTable.setSizeFull();
        layoutTable.addComponent(table);
                
                //LAYOUT BOTTOM
		VerticalLayout layoutBottom = new VerticalLayout();
		HorizontalLayout layoutFooter1 = new HorizontalLayout();
		HorizontalLayout layoutFooter2 = new HorizontalLayout();
		layoutBottom.addComponent(layoutFooter1);
		layoutBottom.addComponent(layoutFooter2);
		
        //LAYOUT UTAMA
        content.addComponent(panelTop);
        content.addComponent(layoutTable);
        content.addComponent(layoutBottom);
        setCompositionRoot(content);
        
        //Extended Konfigurasi Size
        table.setSizeFull();
        content.setExpandRatio(layoutTable, 1);
		
        layoutTopInner0.addComponent(labelTanggalTransaksiDivisi);

        //HANYA ADMINISTRATOR YANG BISA
//        if (userActive.getUserOtorizeType().equals(EnumUserOtorize.ADMINISTRATOR.getStrCode())) {
	        layoutTopInner0.addComponent(dateFieldDatePembayaranManual);
			layoutTopInner0.setComponentAlignment(dateFieldDatePembayaranManual, Alignment.BOTTOM_CENTER);
	        layoutTopInner0.addComponent(checkBoxGunakanTanggalManual);
//			layoutTopInner0.setComponentAlignment(checkBoxGunakanTanggalManual, Alignment.BOTTOM_CENTER);
//        }		
		layoutTopInner1.addComponent(fieldSearchByInvoice);
		layoutTopInner1.setComponentAlignment(fieldSearchByInvoice, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(fieldSearchComboLunas);
		layoutTopInner1.setComponentAlignment(fieldSearchComboLunas, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(fieldSearchByDateInvoiceFrom);
		layoutTopInner1.setComponentAlignment(fieldSearchByDateInvoiceFrom, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(fieldSearchByDateInvoiceTo);
		layoutTopInner1.setComponentAlignment(fieldSearchByDateInvoiceTo, Alignment.BOTTOM_CENTER);
		
		
		layoutTopInner2.addComponent(searchComboVendor);
		layoutTopInner2.setComponentAlignment(searchComboVendor, Alignment.BOTTOM_CENTER);
//		layoutTopInner2.addComponent(searchComboArea);
//		layoutTopInner2.setComponentAlignment(searchComboArea, Alignment.BOTTOM_CENTER);
//		layoutTopInner2.addComponent(searchComboSubArea);
//		layoutTopInner2.setComponentAlignment(searchComboSubArea, Alignment.BOTTOM_CENTER);
		
		layoutTopInner2.addComponent(btnSearch);
		layoutTopInner2.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
//		layoutTop.addComponent(btnReload);
//		layoutTop.setComponentAlignment(btnReload, Alignment.BOTTOM_CENTER);
//		layoutTopInner1.addComponent(btnSeparator1);
//		layoutTopInner1.setComponentAlignment(btnSeparator1, Alignment.BOTTOM_CENTER);
//		layoutTopInner2.addComponent(btnPay);
//		layoutTopInner2.setComponentAlignment(btnPay, Alignment.BOTTOM_CENTER);
//		layoutTopInner2.addComponent(btnLunaskan);
//		layoutTopInner2.setComponentAlignment(btnLunaskan, Alignment.BOTTOM_CENTER);
//		layoutTopInner2.addComponent(btnSelisihPlusMinus);
//		layoutTopInner2.setComponentAlignment(btnSelisihPlusMinus, Alignment.BOTTOM_CENTER);
		
//		layoutTopInner2.addComponent(btnTerbitkanSJ);
//		layoutTopInner2.addComponent(btnPencabutanSJ);
		layoutTopInner2.addComponent(btnPrint);
//		layoutTopInner2.setComponentAlignment(btnTerbitkanSJ, Alignment.BOTTOM_CENTER);
//		layoutTopInner2.setComponentAlignment(btnPencabutanSJ, Alignment.BOTTOM_CENTER);
		layoutTopInner2.setComponentAlignment(btnPrint, Alignment.BOTTOM_CENTER);
		
		layoutFooter1.addComponent(fieldSelectedCount);
		layoutFooter1.addComponent(fieldTunaiCount);
		layoutFooter1.addComponent(fieldKreditCount);
		layoutFooter1.addComponent(fieldTunaiSum);
		layoutFooter1.addComponent(fieldKreditSum);
		layoutFooter1.addComponent(fieldToCount);
		layoutFooter1.addComponent(fieldCanvasCount);
		layoutFooter1.addComponent(fieldToSum);
		layoutFooter1.addComponent(fieldCanvasSum);		
		layoutFooter1.addComponent(fieldAmountSum);
		
//		layoutFooter2.addComponent(fieldAmountPaySum);
//		layoutFooter2.addComponent(fieldAmountReturTampungan);
//		layoutFooter2.addComponent(fieldAmountReturTampunganFaktur);
//		layoutFooter2.addComponent(fieldAmountTotal);
		
	}
	public void initComponentFirst(){
		fieldSearchComboDivisi.setVisible(false);
		fieldSearchByRekap.setVisible(false);
		btnSelectRekapNo.setVisible(false);
		btnSelisihPlusMinus.setVisible(false);
		
		checkBoxGunakanTanggalManual.setValue(true);
		dateFieldDatePembayaranManual.setValue(model.getTransaksiHelper().getCurrentTransDate());
	}
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setVisibleFormProperties(Object... formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
		form.setVisibleItemProperties(formPropertyIds);
	}
	public void setTableProperties(){
		
		setVisibleTableProperties("selected", "refno", "nopo","invoiceno", "tipefaktur", "invoicedate","duedate", 
				"fvendorBean", "amountafterdiscafterppn",
				"amountpay", "lunas", "endofday");
		
		table.setColumnCollapsingAllowed(true);
		try{
			table.setColumnCollapsed("refno", true);
			table.setColumnCollapsed("recapno", true);
			table.setColumnCollapsed("suratjalanno", true);
			table.setColumnCollapsed("endofday", true);
			
		} catch(Exception ex){}
		//ALIGNMENT
		table.setColumnAlignment("selected", Align.CENTER);
		table.setColumnAlignment("endofday", Align.CENTER);
		table.setColumnAlignment("invoicedate", Align.CENTER);
		table.setColumnAlignment("duedate", Align.CENTER);
		table.setColumnAlignment("amountafterdiscafterppn", Align.RIGHT);
		table.setColumnAlignment("amountpay", Align.RIGHT);
		table.setColumnAlignment("lunas", Align.CENTER);
		table.setColumnAlignment("tipefaktur", Align.CENTER);
		
		//set header
		table.setColumnHeader("selected", "<input type='checkbox'/>");
		table.setColumnHeader("nopo", "NO.PO");
		table.setColumnHeader("invoiceno", "INVOICE");
		table.setColumnHeader("invoicedate", "TGL.INV");
		table.setColumnHeader("duedate", "JTH.TEMPO");
		table.setColumnHeader("fvendorBean", "SUPPLIER");
		table.setColumnHeader("amountafterdiscafterppn", "NOMINAL+PPN");
		table.setColumnHeader("amountpay", "TERBAYAR");
		table.setColumnHeader("lunas", "lunas");
		table.setColumnHeader("endofday", "endofday");
		table.setColumnHeader("tipefaktur", "F/R");
		
		
//		table.setColumnExpandRatio("selected", 2);
//		table.setColumnExpandRatio("recapno", 3);
//		table.setColumnExpandRatio("id", 5);
//		table.setColumnExpandRatio("tunaikredit", 1);
//		table.setColumnExpandRatio("salesmanBean", 5);
//		table.setColumnExpandRatio("nopo", 1);
//		table.setColumnExpandRatio("invoicedate", 3);
//		table.setColumnExpandRatio("term", 1);
//		table.setColumnExpandRatio("duedate", 3);
//		table.setColumnExpandRatio("terkirim", 1);
//		table.setColumnExpandRatio("tertundacounter", 1);
//		table.setColumnExpandRatio("actualduedate", 3);
//		table.setColumnExpandRatio("amount", 4);
//		table.setColumnExpandRatio("amountpay", 4);
//		table.setColumnExpandRatio("lunas", 1);
//		table.setColumnExpandRatio("ppn", 3);
//		table.setColumnExpandRatio("disc1", 3);
//		table.setColumnExpandRatio("disc2", 3);
//		table.setColumnExpandRatio("spname", 4);
//		table.setColumnExpandRatio("custname", 4);
//		table.setColumnExpandRatio("divisionBean", 4);
//		table.setColumnExpandRatio("customerBean", 4);
//		table.setColumnExpandRatio("disc3", 3);
//		table.setColumnExpandRatio("lockupdate", 1);
//		table.setColumnExpandRatio("orderdate", 3);
		
	}
	public void setFormProperties(){
//		setVisibleFormProperties("arinvoicePK.id");
	}
	public void setDisplaySearchComponent(){
		getFieldSearchComboDivisi().setContainerDataSource(model.getBeanItemContainerDivision());
		getFieldSearchComboDivisi().setNullSelectionAllowed(false);
		
		//KALAU KOSONG DIA AKAN MENAMPILKAN ERROR
		try{
			getFieldSearchComboDivisi().select(model.getBeanItemContainerDivision().getIdByIndex(0));
		} catch(Exception ex){}
	}
	public void setDisplay(){
		
		//1. Refresh Table displa
		table.setContainerDataSource(model.getTableBeanItemContainer());
		
		setTableProperties();
		setDisplayFooter();
	}
	public void setDisplayFooter(){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMinimumFractionDigits(0);
		nf.setMaximumFractionDigits(0);
		
		//3. Hitung Total & Jumlah Record dll
		double sumAmount=0 ;
		double sumAmountPay=0;
		double sumAmountRevisi=0;
		double sumAmountReturTampunganFaktur=0;
		int countTerkirim=0;
		int countLunas=0;
		int countTertunda=0;
		
		int countSelected=0;
		int countTunaiSelected=0;
		int countKreditSelected=0;
		double sumTunaiSelected=0;
		double sumKreditSelected=0;
		int countToSelected=0;
		int countCanvasSelected = 0;
		double sumToSelected=0;
		double sumCanvasSelected=0;
		double sumAmountPaySelected=0;
		
		//DARI REKAP
		double sumAmountSelected=0;
		double sumAmountSelectedPayNew=0;
		double sumAmountReturTampunganSelected=0;
		double sumAmountReturTampunganFakturSelected=0;
		double sumAmountRevisiSelected=0;
		double sumAmountDiskonKhususSelected=0;
		double sumAmountTotalSelected=0;
		
		Set<String> recapNoSet = new HashSet<String>();
		
		Collection itemIds =  model.getTableBeanItemContainer().getItemIds();
		for (Object itemId: itemIds){
			FtPurchaseh item = new FtPurchaseh();
			item = model.getTableBeanItemContainer().getItem(itemId).getBean();
			//::FOOTER TABLE
//			if (item.getId().getTipefaktur().equals("R")){
//				sumAmount -= (item.getAmount() + item.getAmountrevisi());
//				sumAmountPay -= item.getAmountpay();
//			}else {
				sumAmount += (item.getAmount() + item.getAmountrevisi());
				sumAmountPay += item.getAmountpay();	
//				if(item.isReturtampunglunas()==false){				
//					sumAmountReturTampunganFaktur += item.getAmountreturtampung();
//				}
				sumAmountRevisi += item.getAmountrevisi();
//			}	
			if (item.isLunas()==true){
				countLunas +=1;
			}
			//:::YANG TER SELEKSI SAJA
			if (item.getSelected().getValue()==true){
				countSelected += 1;
				
				//TUNAI/KREDIT
				if (item.getTunaikredit().equals("K")){
					countKreditSelected +=1;
//					if (item.getId().getTipefaktur().equals("R")){
//						sumKreditSelected -= (item.getAmount() + item.getAmountrevisi());						
//					} else {
						sumKreditSelected += (item.getAmount() + item.getAmountrevisi());												
//					}
				} else {
					countTunaiSelected += 1;				
//					if (item.getId().getTipefaktur().equals("R")){
//						sumTunaiSelected -= (item.getAmount() + item.getAmountrevisi());
//					} else {
						sumTunaiSelected += (item.getAmount() + item.getAmountrevisi());						
//					}
				}
				
//				if (item.getId().getTipefaktur().equals("R")){						
//					sumAmountSelected -= (item.getAmount() + item.getAmountrevisi());
//					sumAmountSelectedPayNew -= item.getAmountpay();
//					sumAmountPaySelected -= (item.getAmount()-item.getAmountpay());
//					sumAmountReturTampunganFakturSelected -= item.getAmountreturtampung();
//				} else {
					sumAmountSelected += (item.getAmount() + item.getAmountrevisi());					
					sumAmountSelectedPayNew += item.getAmountpay();
					sumAmountPaySelected += (item.getAmount()-item.getAmountpay());
					sumAmountRevisiSelected += item.getAmountrevisi();
//				}
				//HITUNG DISKON KHUSUS BY NOTA
				List<FtAppaymentd> listFtarpaymentd = new ArrayList<FtAppaymentd>(item.getFtappaymentdSet());
				for (FtAppaymentd itemArpaymentdetail: listFtarpaymentd){
					sumAmountDiskonKhususSelected += itemArpaymentdetail.getMrvamountpay();
				}
				
			}
			item.getSelected().setReadOnly(true);
			
			
		}

		//HITUNG DISKON DAN RETUR
//		FDivision divisionBean = new FDivision();
//		divisionBean = (FDivision) fieldSearchComboDivisi.getConvertedValue();
//		for (String itemRecap11: recapNoSet){
//			List<FtSaleshRekapTampungan> listArinvoiceRekapTampungan = new ArrayList<FtSaleshRekapTampungan>();
//			listArinvoiceRekapTampungan =  model.getArInvoiceRekapTampunganService().findAllByRecapAndDivision(itemRecap11.toString(), divisionBean);
//			for (FtSaleshRekapTampungan arinvoiceRekapTampungan: listArinvoiceRekapTampungan){
//				sumAmountReturTampunganSelected += arinvoiceRekapTampungan.getAmountRetur();
//					
//			}
//		}		
//		sumAmountTotalSelected = sumAmount - sumAmountReturTampunganSelected - sumAmountDiskonKhususSelected;

		//BUAT SELECT ITEM READONLY
//		item.getSelected().setReadOnly(true);
		fieldSelectedCount.setReadOnly(false);
		fieldTunaiCount.setReadOnly(false);
		fieldKreditCount.setReadOnly(false);
		fieldTunaiSum.setReadOnly(false);
		fieldKreditSum.setReadOnly(false);
		fieldToCount.setReadOnly(false);
		fieldCanvasCount.setReadOnly(false);
		fieldToSum.setReadOnly(false);
		fieldCanvasSum.setReadOnly(false);
		fieldAmountPaySum.setReadOnly(false);

		fieldAmountSum.setReadOnly(false);
//		fieldAmountReturTampungan.setReadOnly(false);
//		fieldAmountDiskonKhusus.setReadOnly(false);
//		fieldAmountTotal.setReadOnly(false);
		
		fieldSelectedCount.setValue(nf.format(countSelected));
		fieldTunaiCount.setValue(nf.format(countTunaiSelected));
		fieldKreditCount.setValue(nf.format(countKreditSelected));
		fieldTunaiSum.setValue(nf.format(sumTunaiSelected));
		fieldKreditSum.setValue(nf.format(sumKreditSelected));
		fieldToCount.setValue(nf.format(countToSelected));
		fieldCanvasCount.setValue(nf.format(countCanvasSelected));
		fieldToSum.setValue(nf.format(sumToSelected));
		fieldCanvasSum.setValue(nf.format(sumCanvasSelected));
		fieldAmountPaySum.setValue(nf.format(sumAmountPaySelected));

		//FOOTER BAWAH
		fieldAmountSum.setValue(nf.format(sumAmountSelected));

		//DIFFERNT CONCEPT
//		fieldAmountPaySum.setValue(nf.format(sumAmountPaySelected));
		fieldAmountPaySum.setValue(nf.format(sumAmountSelectedPayNew));

		fieldAmountReturTampungan.setValue(nf.format(sumAmountReturTampunganSelected));
		fieldAmountReturTampunganFaktur.setValue(nf.format(sumAmountReturTampunganFakturSelected));
		
//		fieldAmountDiskonKhusus.setValue(nf.format(sumAmountDiskonKhususSelected));
		
		double total = sumAmountSelected - sumAmountSelectedPayNew - sumAmountReturTampunganSelected - sumAmountReturTampunganFakturSelected;
		fieldAmountTotal.setValue(nf.format(total));
		
		
		fieldSelectedCount.setReadOnly(true);
		fieldTunaiCount.setReadOnly(true);
		fieldKreditCount.setReadOnly(true);
		fieldTunaiSum.setReadOnly(true);
		fieldKreditSum.setReadOnly(true);
		fieldToCount.setReadOnly(true);
		fieldCanvasCount.setReadOnly(true);
		fieldToSum.setReadOnly(true);
		fieldCanvasSum.setReadOnly(true);
		fieldAmountSum.setReadOnly(true);
//		fieldAmountReturTampungan.setReadOnly(true);
//		fieldAmountDiskonKhusus.setReadOnly(true);
//		fieldAmountTotal.setReadOnly(true);
		
//		//DI AKTIFKAN TERUS KARENA BOLEH LEBIH BAYAR
//		fieldAmountPaySum.setReadOnly(true);
		
		table.setColumnFooter("amount", nf.format(sumAmount));
		table.setColumnFooter("amountpay", nf.format(sumAmountPay));
		
		table.setColumnFooter("recapno", "*Record: " + itemIds.size());
		table.setColumnFooter("terkirim",  nf.format(countTerkirim));
		table.setColumnFooter("lunas",  nf.format(countLunas));
		table.setColumnFooter("tertundacounter", nf.format(countTertunda));
		table.setColumnFooter("amountrevisi", nf.format(sumAmountRevisi));
		table.setColumnFooter("amountreturtampung", nf.format(sumAmountReturTampunganFaktur));
		
	}
	
	public void setFormButtonAndText(){
		if (operationStatus.equals(EnumOperationStatus.OPEN.getStrCode())){
			form.setVisible(false);
			table.setSelectable(true);
//			addButton.setEnabled(true);
//			deleteButton.setEnabled(true);						
		} else if (operationStatus.equals(EnumOperationStatus.ADDING.getStrCode())){
			form.setVisible(true);
			table.setSelectable(false);
//			addButton.setEnabled(false);
//			deleteButton.setEnabled(false);			
		}else if (operationStatus.equals(EnumOperationStatus.EDITING.getStrCode())){
			form.setVisible(true);
			table.setSelectable(true);
//			addButton.setEnabled(true);
//			deleteButton.setEnabled(true);			
		}		
		
	}

	public void resetFieldSearch(){
//		fieldSearchByInvoice.setValue("");
//		fieldSearchByIdCustomer.setValue("");
//		fieldSearchByNamaCustomer.setValue("");
//		
//		fieldSearchByIdSalesman.setValue("");
//		fieldSearchByNamaSalesman.setValue("");
		
		
	}
	
	private Button btnSave;
	private Button btnCancel;
		
	public void setComponentStyles(){
	//	if (! getUI().getTheme().equals("vaadin_theme")) {
	//		tableList.addStyleName("compact small");
	//		tableDetil.addStyleName("compact small");
	//		
	//		fieldOrderno.addStyleName("small");
	//		fieldInvoiceno.addStyleName("small");
	//		comboTipeJual.addStyleName("small");
	//		comboSalesman.addStyleName("small");
	//		comboCustomer.addStyleName("small");
	//		dateFieldOrderdate.addStyleName("small");
	//		dateFieldInvoicedate.addStyleName("small");
	//		comboTunaikredit.addStyleName("small");
	//		comboTop.addStyleName("small");
	//		dateFieldDuedate.addStyleName("small");
	//		comboWarehouse.addStyleName("small");
	//		checkEndofday.addStyleName("small");
	//		checkSaldo.addStyleName("small");
	//		checkSearch1.addStyleName("small");
		
			btnSearch.addStyleName("small");
//			btnNewForm.addStyleName("small");
//			btnEditForm.addStyleName("small");
//			btnDeleteForm.addStyleName("small");
			btnPrint.addStyleName("small");
			btnLunaskan.addStyleName("small");
			btnPay.addStyleName("small");
			
//			btnSaveForm.addStyleName("small");
//			btnCancelForm.addStyleName("small");
	//		btnUtility.addStyleName("small");
	
	//		btnAddItem.addStyleName("small");
	//		btnEditItem.addStyleName("small");
	//		btnRemoveItem.addStyleName("small");
			
	//		btnSeparator1.addStyleName("small");
	//		btnSeparator2.addStyleName("small");
		
		
	//	}
		
	//	tabSheet.addStyleName("framed compact-tabbar small");
	//	tabSheet.addStyleName(Reindeer.TABSHEET_BORDERLESS);
	//	tabSheet.addStyleName(Reindeer.TABSHEET_SMALL);
		
	}
	public LapSaldoHutangVendorModel getModel() {
		return model;
	}
	public VerticalLayout getContent() {
		return content;
	}
	public Table getTable() {
		return table;
	}
	public Form getForm() {
		return form;
	}
	public FieldFactory getFieldFactory() {
		return fieldFactory;
	}
	public Class<FtSalesh> getEntityClass() {
		return entityClass;
	}
	public Object[] getFormPropertyIds() {
		return formPropertyIds;
	}
	public String getOperationStatus() {
		return operationStatus;
	}
	public Label getLabelTanggalTransaksiDivisi() {
		return labelTanggalTransaksiDivisi;
	}
	public TextField getFieldSearchByRekap() {
		return fieldSearchByRekap;
	}
	public Button getBtnSelectRekapNo() {
		return btnSelectRekapNo;
	}
	public DateField getDateFieldDatePembayaranManual() {
		return dateFieldDatePembayaranManual;
	}
	public CheckBox getCheckBoxGunakanTanggalManual() {
		return checkBoxGunakanTanggalManual;
	}
	public TextField getFieldSearchById() {
		return fieldSearchById;
	}
	public ComboBox getFieldSearchComboDivisi() {
		return fieldSearchComboDivisi;
	}
	public TextField getFieldSearchByDesc() {
		return fieldSearchByDesc;
	}
	public TextField getFieldSearchByIdCustomer() {
		return fieldSearchByIdCustomer;
	}
	public TextField getFieldSearchByNamaCustomer() {
		return fieldSearchByNamaCustomer;
	}
	public TextField getFieldSearchByIdSalesman() {
		return fieldSearchByIdSalesman;
	}
	public TextField getFieldSearchByNamaSalesman() {
		return fieldSearchByNamaSalesman;
	}
	public TextField getFieldSearchByInvoice() {
		return fieldSearchByInvoice;
	}
	public TextField getFieldSearchBySJPenagihan() {
		return fieldSearchBySJPenagihan;
	}
	public DateField getFieldSearchByDateInvoiceFrom() {
		return fieldSearchByDateInvoiceFrom;
	}
	public DateField getFieldSearchByDateInvoiceTo() {
		return fieldSearchByDateInvoiceTo;
	}
	public ComboBox getFieldSearchComboLunas() {
		return fieldSearchComboLunas;
	}
	public ComboBox getFieldSearchComboToCanvas() {
		return fieldSearchComboToCanvas;
	}
	public ComboBox getFieldSearchComboVendor() {
		return fieldSearchComboVendor;
	}
	public DateField getFieldSearchByDateInvoiceKirimFrom() {
		return fieldSearchByDateInvoiceKirimFrom;
	}
	public DateField getFieldSearchByDateInvoiceKirimTo() {
		return fieldSearchByDateInvoiceKirimTo;
	}
	public ComboBox getFieldSearchComboTunaiKredit() {
		return fieldSearchComboTunaiKredit;
	}
	public CheckBox getCheckLihatSemua() {
		return checkLihatSemua;
	}
	public ComboBox getSearchComboVendor() {
		return searchComboVendor;
	}
	public ComboBox getSearchComboArea() {
		return searchComboArea;
	}
	public ComboBox getSearchComboSubArea() {
		return searchComboSubArea;
	}
	public Button getBtnSearch() {
		return btnSearch;
	}
	public Button getBtnReload() {
		return btnReload;
	}
	public Button getBtnPay() {
		return btnPay;
	}
	public Button getBtnLunaskan() {
		return btnLunaskan;
	}
	public Button getBtnSelisihPlusMinus() {
		return btnSelisihPlusMinus;
	}
	public Button getBtnTerbitkanSJ() {
		return btnTerbitkanSJ;
	}
	public Button getBtnPencabutanSJ() {
		return btnPencabutanSJ;
	}
	public Button getBtnPrint() {
		return btnPrint;
	}
	public Button getBtnHelp() {
		return btnHelp;
	}
	public Button getBtnSeparator1() {
		return btnSeparator1;
	}
	public Button getBtnSeparator2() {
		return btnSeparator2;
	}
	public Panel getPanelUtama() {
		return panelUtama;
	}
	public Panel getPanelTop() {
		return panelTop;
	}
	public Panel getPanelTabel() {
		return panelTabel;
	}
	public Panel getPanelForm() {
		return panelForm;
	}
	public VerticalLayout getLayoutTop() {
		return layoutTop;
	}
	public TextField getFieldSelectedCount() {
		return fieldSelectedCount;
	}
	public TextField getFieldTunaiCount() {
		return fieldTunaiCount;
	}
	public TextField getFieldKreditCount() {
		return fieldKreditCount;
	}
	public TextField getFieldTunaiSum() {
		return fieldTunaiSum;
	}
	public TextField getFieldKreditSum() {
		return fieldKreditSum;
	}
	public TextField getFieldToCount() {
		return fieldToCount;
	}
	public TextField getFieldCanvasCount() {
		return fieldCanvasCount;
	}
	public TextField getFieldToSum() {
		return fieldToSum;
	}
	public TextField getFieldCanvasSum() {
		return fieldCanvasSum;
	}
	public TextField getFieldAmountSum() {
		return fieldAmountSum;
	}
	public TextField getFieldAmountPaySum() {
		return fieldAmountPaySum;
	}
	public TextField getFieldAmountReturTampunganFaktur() {
		return fieldAmountReturTampunganFaktur;
	}
	public TextField getFieldAmountReturTampungan() {
		return fieldAmountReturTampungan;
	}
	public TextField getFieldAmountTotal() {
		return fieldAmountTotal;
	}
	public User getUserActive() {
		return userActive;
	}
	public Button getBtnSave() {
		return btnSave;
	}
	public Button getBtnCancel() {
		return btnCancel;
	}
	public void setModel(LapSaldoHutangVendorModel model) {
		this.model = model;
	}
	public void setContent(VerticalLayout content) {
		this.content = content;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public void setFieldFactory(FieldFactory fieldFactory) {
		this.fieldFactory = fieldFactory;
	}
	public void setEntityClass(Class<FtSalesh> entityClass) {
		this.entityClass = entityClass;
	}
	public void setFormPropertyIds(Object[] formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
	}
	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}
	public void setLabelTanggalTransaksiDivisi(Label labelTanggalTransaksiDivisi) {
		this.labelTanggalTransaksiDivisi = labelTanggalTransaksiDivisi;
	}
	public void setFieldSearchByRekap(TextField fieldSearchByRekap) {
		this.fieldSearchByRekap = fieldSearchByRekap;
	}
	public void setBtnSelectRekapNo(Button btnSelectRekapNo) {
		this.btnSelectRekapNo = btnSelectRekapNo;
	}
	public void setDateFieldDatePembayaranManual(
			DateField dateFieldDatePembayaranManual) {
		this.dateFieldDatePembayaranManual = dateFieldDatePembayaranManual;
	}
	public void setCheckBoxGunakanTanggalManual(
			CheckBox checkBoxGunakanTanggalManual) {
		this.checkBoxGunakanTanggalManual = checkBoxGunakanTanggalManual;
	}
	public void setFieldSearchById(TextField fieldSearchById) {
		this.fieldSearchById = fieldSearchById;
	}
	public void setFieldSearchComboDivisi(ComboBox fieldSearchComboDivisi) {
		this.fieldSearchComboDivisi = fieldSearchComboDivisi;
	}
	public void setFieldSearchByDesc(TextField fieldSearchByDesc) {
		this.fieldSearchByDesc = fieldSearchByDesc;
	}
	public void setFieldSearchByIdCustomer(TextField fieldSearchByIdCustomer) {
		this.fieldSearchByIdCustomer = fieldSearchByIdCustomer;
	}
	public void setFieldSearchByNamaCustomer(TextField fieldSearchByNamaCustomer) {
		this.fieldSearchByNamaCustomer = fieldSearchByNamaCustomer;
	}
	public void setFieldSearchByIdSalesman(TextField fieldSearchByIdSalesman) {
		this.fieldSearchByIdSalesman = fieldSearchByIdSalesman;
	}
	public void setFieldSearchByNamaSalesman(TextField fieldSearchByNamaSalesman) {
		this.fieldSearchByNamaSalesman = fieldSearchByNamaSalesman;
	}
	public void setFieldSearchByInvoice(TextField fieldSearchByInvoice) {
		this.fieldSearchByInvoice = fieldSearchByInvoice;
	}
	public void setFieldSearchBySJPenagihan(TextField fieldSearchBySJPenagihan) {
		this.fieldSearchBySJPenagihan = fieldSearchBySJPenagihan;
	}
	public void setFieldSearchByDateInvoiceFrom(
			DateField fieldSearchByDateInvoiceFrom) {
		this.fieldSearchByDateInvoiceFrom = fieldSearchByDateInvoiceFrom;
	}
	public void setFieldSearchByDateInvoiceTo(DateField fieldSearchByDateInvoiceTo) {
		this.fieldSearchByDateInvoiceTo = fieldSearchByDateInvoiceTo;
	}
	public void setFieldSearchComboLunas(ComboBox fieldSearchComboLunas) {
		this.fieldSearchComboLunas = fieldSearchComboLunas;
	}
	public void setFieldSearchComboToCanvas(ComboBox fieldSearchComboToCanvas) {
		this.fieldSearchComboToCanvas = fieldSearchComboToCanvas;
	}
	public void setFieldSearchComboVendor(ComboBox fieldSearchComboVendor) {
		this.fieldSearchComboVendor = fieldSearchComboVendor;
	}
	public void setFieldSearchByDateInvoiceKirimFrom(
			DateField fieldSearchByDateInvoiceKirimFrom) {
		this.fieldSearchByDateInvoiceKirimFrom = fieldSearchByDateInvoiceKirimFrom;
	}
	public void setFieldSearchByDateInvoiceKirimTo(
			DateField fieldSearchByDateInvoiceKirimTo) {
		this.fieldSearchByDateInvoiceKirimTo = fieldSearchByDateInvoiceKirimTo;
	}
	public void setFieldSearchComboTunaiKredit(ComboBox fieldSearchComboTunaiKredit) {
		this.fieldSearchComboTunaiKredit = fieldSearchComboTunaiKredit;
	}
	public void setCheckLihatSemua(CheckBox checkLihatSemua) {
		this.checkLihatSemua = checkLihatSemua;
	}
	public void setSearchComboVendor(ComboBox searchComboVendor) {
		this.searchComboVendor = searchComboVendor;
	}
	public void setSearchComboArea(ComboBox searchComboArea) {
		this.searchComboArea = searchComboArea;
	}
	public void setSearchComboSubArea(ComboBox searchComboSubArea) {
		this.searchComboSubArea = searchComboSubArea;
	}
	public void setBtnSearch(Button btnSearch) {
		this.btnSearch = btnSearch;
	}
	public void setBtnReload(Button btnReload) {
		this.btnReload = btnReload;
	}
	public void setBtnPay(Button btnPay) {
		this.btnPay = btnPay;
	}
	public void setBtnLunaskan(Button btnLunaskan) {
		this.btnLunaskan = btnLunaskan;
	}
	public void setBtnSelisihPlusMinus(Button btnSelisihPlusMinus) {
		this.btnSelisihPlusMinus = btnSelisihPlusMinus;
	}
	public void setBtnTerbitkanSJ(Button btnTerbitkanSJ) {
		this.btnTerbitkanSJ = btnTerbitkanSJ;
	}
	public void setBtnPencabutanSJ(Button btnPencabutanSJ) {
		this.btnPencabutanSJ = btnPencabutanSJ;
	}
	public void setBtnPrint(Button btnPrint) {
		this.btnPrint = btnPrint;
	}
	public void setBtnHelp(Button btnHelp) {
		this.btnHelp = btnHelp;
	}
	public void setBtnSeparator1(Button btnSeparator1) {
		this.btnSeparator1 = btnSeparator1;
	}
	public void setBtnSeparator2(Button btnSeparator2) {
		this.btnSeparator2 = btnSeparator2;
	}
	public void setPanelUtama(Panel panelUtama) {
		this.panelUtama = panelUtama;
	}
	public void setPanelTop(Panel panelTop) {
		this.panelTop = panelTop;
	}
	public void setPanelTabel(Panel panelTabel) {
		this.panelTabel = panelTabel;
	}
	public void setPanelForm(Panel panelForm) {
		this.panelForm = panelForm;
	}
	public void setLayoutTop(VerticalLayout layoutTop) {
		this.layoutTop = layoutTop;
	}
	public void setFieldSelectedCount(TextField fieldSelectedCount) {
		this.fieldSelectedCount = fieldSelectedCount;
	}
	public void setFieldTunaiCount(TextField fieldTunaiCount) {
		this.fieldTunaiCount = fieldTunaiCount;
	}
	public void setFieldKreditCount(TextField fieldKreditCount) {
		this.fieldKreditCount = fieldKreditCount;
	}
	public void setFieldTunaiSum(TextField fieldTunaiSum) {
		this.fieldTunaiSum = fieldTunaiSum;
	}
	public void setFieldKreditSum(TextField fieldKreditSum) {
		this.fieldKreditSum = fieldKreditSum;
	}
	public void setFieldToCount(TextField fieldToCount) {
		this.fieldToCount = fieldToCount;
	}
	public void setFieldCanvasCount(TextField fieldCanvasCount) {
		this.fieldCanvasCount = fieldCanvasCount;
	}
	public void setFieldToSum(TextField fieldToSum) {
		this.fieldToSum = fieldToSum;
	}
	public void setFieldCanvasSum(TextField fieldCanvasSum) {
		this.fieldCanvasSum = fieldCanvasSum;
	}
	public void setFieldAmountSum(TextField fieldAmountSum) {
		this.fieldAmountSum = fieldAmountSum;
	}
	public void setFieldAmountPaySum(TextField fieldAmountPaySum) {
		this.fieldAmountPaySum = fieldAmountPaySum;
	}
	public void setFieldAmountReturTampunganFaktur(
			TextField fieldAmountReturTampunganFaktur) {
		this.fieldAmountReturTampunganFaktur = fieldAmountReturTampunganFaktur;
	}
	public void setFieldAmountReturTampungan(TextField fieldAmountReturTampungan) {
		this.fieldAmountReturTampungan = fieldAmountReturTampungan;
	}
	public void setFieldAmountTotal(TextField fieldAmountTotal) {
		this.fieldAmountTotal = fieldAmountTotal;
	}
	public void setUserActive(User userActive) {
		this.userActive = userActive;
	}
	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}
	public void setBtnCancel(Button btnCancel) {
		this.btnCancel = btnCancel;
	}
	
	
	
	
}
