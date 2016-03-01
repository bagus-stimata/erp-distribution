package org.erp.distribution.kontrolstok.stockopname.formopname;

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
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FtArpaymentd;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.FtSaleshRekapTampungan;
import org.erp.distribution.model.User;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.model.modelenum.EnumUserOtorize;

import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
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

public class FormOpnameView extends CustomComponent {
	
	private FormOpnameModel model;
	private VerticalLayout content = new VerticalLayout();

	private Table table;
	private Form form;
	private FieldFactory fieldFactory;

	private Class<FtSalesh> entityClass;
	
	private Object[] formPropertyIds;
	
	private String operationStatus;

    private Label labelTanggalTransaksiDivisi = new Label("(TANGGAL SJ: )");
    	
	//Additional Component	private TextField fieldSearchByRekap;
	private TextField fieldSearchByRekap;
	private Button btnSelectRekapNo = new Button("F");	

	private DateField dateFieldDatePembayaranManual = new DateField();
	private CheckBox checkBoxGunakanTanggalManual = new CheckBox("GUNAKAN TANGGAL SURAT JALAN MANUAL");
	
	private TextField fieldSearchByPcode;
	private TextField fieldSearchByPname;
	private ComboBox fieldSearchComboProductDivision;
	private ComboBox fieldSearchComboSupplier;
	
	private TextField fieldSearchByDesc;
	private TextField fieldSearchByIdCustomer;	
	private TextField fieldSearchByNamaCustomer;
	private TextField fieldSearchByIdSalesman;
	private TextField fieldSearchByNamaSalesman;
	private TextField fieldSearchByInvoice;
	private DateField fieldSearchByDateInvoiceFrom;
	private DateField fieldSearchByDateInvoiceTo;
	private ComboBox fieldSearchComboLunas;
	private ComboBox fieldSearchComboToCanvas;
	
	private DateField fieldSearchByDateInvoiceKirimFrom;
	private DateField fieldSearchByDateInvoiceKirimTo;
	
	private ComboBox fieldSearchComboTunaiKredit = new ComboBox("T/K");
	
	private CheckBox checkLihatSemua = new CheckBox("Lihat Semua(Kirim dan Belum)", false);
	
	private Button btnSearch;
	private Button btnReload;
	private Button btnTerbitkanSJ;
	private Button btnPencabutanSJ;
	
	private Button btnPrint;
	private Button btnHelp;	
	private Button btnSeparator1;
	private Button btnSeparator2;

	private Button btnExportToExel = new Button("Export to Exel");
	
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
	
	public FormOpnameView(FormOpnameModel model){
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
		fieldSearchByPcode = new TextField("KODE");
		fieldSearchByPcode.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByPcode.setWidth("100px");
		fieldSearchByPname = new TextField("NAMA BARANG");
		fieldSearchByPname.setWidth("200px");
		fieldSearchByPname.setStyleName(Reindeer.TEXTFIELD_SMALL);

		fieldSearchComboSupplier = new ComboBox("SUPPLIER");
		fieldSearchComboSupplier.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboSupplier.setWidth("120px");
		fieldSearchComboSupplier.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);				
		fieldSearchComboSupplier.setNullSelectionAllowed(true);

		fieldSearchComboProductDivision = new ComboBox("DIVISI PROD");
		fieldSearchComboProductDivision.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchComboProductDivision.setWidth("120px");
		fieldSearchComboProductDivision.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);				
		fieldSearchComboProductDivision.setNullSelectionAllowed(true);
		
		fieldSearchByInvoice = new TextField();
		fieldSearchByInvoice.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchByInvoice.setWidth("100px");
		fieldSearchByInvoice.setInputPrompt("NO. INVOICE");
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
		
		fieldSearchByDateInvoiceFrom = new DateField("Tgl Faktur Mulai");
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
		fieldSearchComboLunas.select("S");
		
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

		//DEFAULT VIEW
		fieldSearchComboTunaiKredit.select("S");
		fieldSearchComboTunaiKredit.setEnabled(true);
		fieldSearchComboTunaiKredit.setNullSelectionAllowed(false);
		
		
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
		btnTerbitkanSJ = new Button("Terbitkan Surat Jalan");
		btnTerbitkanSJ.setStyleName(Reindeer.BUTTON_SMALL);
		
		btnPencabutanSJ = new Button("Pencabutan SJ");
		btnPencabutanSJ.setStyleName(Reindeer.BUTTON_SMALL);
		
		btnPrint = new Button("Print Form Opname");
		btnPrint.setStyleName(Reindeer.BUTTON_SMALL);
		
		btnHelp = new Button("Help");
		
		btnSeparator1 = new Button("");
		btnSeparator1.setEnabled(false);
		btnSeparator2 = new Button("::");
		btnSeparator2.setEnabled(false);
                
        btnSearch.setIcon(new ThemeResource("../images/navigation/12x12/Find.png") );
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
		layoutTop.addComponent(layoutTopInner0);
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
//		layoutBottom.addComponent(layoutFooter2);
		
        //LAYOUT UTAMA
        content.addComponent(panelTop);
        content.addComponent(layoutTable);
        content.addComponent(layoutBottom);
        setCompositionRoot(content);
        
        //Extended Konfigurasi Size
        table.setSizeFull();
        content.setExpandRatio(layoutTable, 1);
		
//        layoutTopInner0.addComponent(labelTanggalTransaksiDivisi);

        //HANYA ADMINISTRATOR YANG BISA
//        if (userActive.getUserOtorizeType().equals(EnumUserOtorize.ADMINISTRATOR.getStrCode())) {
//	        layoutTopInner0.addComponent(dateFieldDatePembayaranManual);
//			layoutTopInner0.setComponentAlignment(dateFieldDatePembayaranManual, Alignment.BOTTOM_CENTER);
//	        layoutTopInner0.addComponent(checkBoxGunakanTanggalManual);
//			layoutTopInner0.setComponentAlignment(checkBoxGunakanTanggalManual, Alignment.BOTTOM_CENTER);
//        }		
		layoutTopInner1.addComponent(fieldSearchByPcode);
		layoutTopInner1.setComponentAlignment(fieldSearchByPcode, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(fieldSearchByPname);
		layoutTopInner1.setComponentAlignment(fieldSearchByPname, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(fieldSearchComboProductDivision);
		layoutTopInner1.setComponentAlignment(fieldSearchComboProductDivision, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(fieldSearchComboSupplier);
		layoutTopInner1.setComponentAlignment(fieldSearchComboSupplier, Alignment.BOTTOM_CENTER);

//		layoutTopInner1.addComponent(fieldSearchByInvoice);
//		layoutTopInner1.setComponentAlignment(fieldSearchByInvoice, Alignment.BOTTOM_CENTER);
//		layoutTopInner1.addComponent(fieldSearchComboLunas);
//		layoutTopInner1.setComponentAlignment(fieldSearchComboLunas, Alignment.BOTTOM_CENTER);
//		layoutTopInner1.addComponent(fieldSearchComboToCanvas);
//		layoutTopInner1.setComponentAlignment(fieldSearchComboToCanvas, Alignment.BOTTOM_CENTER);
//		layoutTopInner1.addComponent(fieldSearchComboTunaiKredit);
//		layoutTopInner1.setComponentAlignment(fieldSearchComboTunaiKredit, Alignment.BOTTOM_CENTER);
//		layoutTopInner1.addComponent(fieldSearchByDateInvoiceFrom);
//		layoutTopInner1.setComponentAlignment(fieldSearchByDateInvoiceFrom, Alignment.BOTTOM_CENTER);
//		layoutTopInner1.addComponent(fieldSearchByDateInvoiceTo);
//		layoutTopInner1.setComponentAlignment(fieldSearchByDateInvoiceTo, Alignment.BOTTOM_CENTER);
		
		
		layoutTopInner2.addComponent(fieldSearchByIdSalesman);
		layoutTopInner2.setComponentAlignment(fieldSearchByIdSalesman, Alignment.BOTTOM_CENTER);
		layoutTopInner2.addComponent(fieldSearchByNamaSalesman);
		layoutTopInner2.setComponentAlignment(fieldSearchByNamaSalesman, Alignment.BOTTOM_CENTER);
		layoutTopInner2.addComponent(fieldSearchByIdCustomer);
		layoutTopInner2.setComponentAlignment(fieldSearchByIdCustomer, Alignment.BOTTOM_CENTER);
//		layoutTopInner2.addComponent(fieldSearchByNamaCustomer);
//		layoutTopInner2.setComponentAlignment(fieldSearchByNamaCustomer, Alignment.BOTTOM_CENTER);
//		layoutTopInner2.addComponent(fieldSearchByDateInvoiceFrom);
//		layoutTopInner2.setComponentAlignment(fieldSearchByDateInvoiceFrom, Alignment.BOTTOM_CENTER);
//		layoutTopInner2.addComponent(fieldSearchByDateInvoiceTo);
//		layoutTopInner2.setComponentAlignment(fieldSearchByDateInvoiceTo, Alignment.BOTTOM_CENTER);
		
		layoutTopInner2.addComponent(fieldSearchByDateInvoiceKirimFrom);
		layoutTopInner2.setComponentAlignment(fieldSearchByDateInvoiceKirimFrom, Alignment.BOTTOM_CENTER);
//		layoutTopInner2.addComponent(fieldSearchByDateInvoiceKirimTo);
//		layoutTopInner2.setComponentAlignment(fieldSearchByDateInvoiceKirimTo, Alignment.BOTTOM_CENTER);
		
		layoutTopInner2.addComponent(checkLihatSemua);
		layoutTopInner2.setComponentAlignment(checkLihatSemua, Alignment.MIDDLE_CENTER);
		
		layoutTopInner1.addComponent(btnSearch);
		layoutTopInner1.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
//		layoutTop.addComponent(btnReload);
//		layoutTop.setComponentAlignment(btnReload, Alignment.BOTTOM_CENTER);
//		layoutTopInner1.addComponent(btnSeparator1);
//		layoutTopInner1.setComponentAlignment(btnSeparator1, Alignment.BOTTOM_CENTER);
//		layoutTopInner1.addComponent(btnTerbitkanSJ);
//		layoutTopInner1.setComponentAlignment(btnTerbitkanSJ, Alignment.BOTTOM_CENTER);
//		layoutTopInner1.addComponent(btnPencabutanSJ);
//		layoutTopInner1.setComponentAlignment(btnPencabutanSJ, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(btnPrint);
		layoutTopInner1.setComponentAlignment(btnPrint, Alignment.BOTTOM_CENTER);
//		layoutTopInner1.addComponent(btnExportToExel);
//		layoutTopInner1.setComponentAlignment(btnExportToExel, Alignment.BOTTOM_CENTER);
		
//		layoutFooter1.addComponent(fieldSelectedCount);
//		layoutFooter1.addComponent(fieldTunaiCount);
//		layoutFooter1.addComponent(fieldKreditCount);
//		layoutFooter1.addComponent(fieldTunaiSum);
//		layoutFooter1.addComponent(fieldKreditSum);
//		layoutFooter1.addComponent(fieldToCount);
//		layoutFooter1.addComponent(fieldCanvasCount);
//		layoutFooter1.addComponent(fieldToSum);
//		layoutFooter1.addComponent(fieldCanvasSum);
//		
//		layoutFooter2.addComponent(fieldAmountSum);
//		layoutFooter2.addComponent(fieldAmountPaySum);
//		layoutFooter2.addComponent(fieldAmountReturTampungan);
//		layoutFooter2.addComponent(fieldAmountReturTampunganFaktur);
//		layoutFooter2.addComponent(fieldAmountTotal);
		
	}
	public void initComponentFirst(){
		fieldSearchByRekap.setVisible(false);
		btnSelectRekapNo.setVisible(false);
		btnPencabutanSJ.setVisible(false);
		
		fieldSearchByInvoice.setVisible(false);
		fieldSearchComboLunas.setVisible(false);
		fieldSearchByIdSalesman.setVisible(false);
		fieldSearchByNamaSalesman.setVisible(false);
		fieldSearchByIdCustomer.setVisible(false);
		fieldSearchByNamaCustomer.setVisible(false);
		fieldSearchByDateInvoiceKirimFrom.setVisible(false);
		fieldSearchByDateInvoiceKirimTo.setVisible(false);
		
		
		checkLihatSemua.setVisible(false);
		
		checkBoxGunakanTanggalManual.setValue(true);
		dateFieldDatePembayaranManual.setValue(model.getTransaksiHelper().getCurrentTransDate());
	}
	
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
			btnExportToExel.addStyleName("small");
			
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

	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setVisibleFormProperties(Object... formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
		form.setVisibleItemProperties(formPropertyIds);
	}
	public void setTableProperties(){
		
//		setVisibleTableProperties("selected", "recapno", "id", "tunaikredit", "salesmanBean", 
//				"amount", "amountrevisi",  "amountpay",  "lunas", "amountreturtampung","returtampunglunas", "nopo",  
//				"invoicedate", "invoicedatekirim", "term","duedate" , "terkirim", "tertundacounter", 
//				"actualduedate",  "ppn", "disc1", "disc2",
//				"spname", "custname", "divisionBean", "customerBean", 
//				"disc3", "lockupdate", "orderdate");
		
		setVisibleTableProperties("selected", "id", "pcode", "pname",  "packaging", "fvendorBean", "shortcode", "shortname", 
				"uom1", "uom2", "uom3", "convfact1", "convfact2");
		
		table.setColumnCollapsingAllowed(true);
		try{
			table.setColumnCollapsed("id", true);
			table.setColumnCollapsed("refno", true);
			table.setColumnCollapsed("recapno", true);
			table.setColumnCollapsed("duedate", true);
			table.setColumnCollapsed("sjdate", true);
			table.setColumnCollapsed("amount", true);
			
		} catch(Exception ex){}
		//ALIGNMENT
		table.setColumnAlignment("selected", Align.CENTER);
		table.setColumnAlignment("tunaikredit", Align.CENTER);
		table.setColumnAlignment("top", Align.CENTER);
		table.setColumnAlignment("amount", Align.RIGHT);
		table.setColumnAlignment("amountafterdiscafterppn", Align.RIGHT);
		table.setColumnAlignment("amountpay", Align.RIGHT);
		table.setColumnAlignment("lunas", Align.CENTER);
		
		//set header
		table.setColumnHeader("selected", "<input type='checkbox'/>");
		table.setColumnHeader("recapno", "REKAP");
		table.setColumnHeader("id", "ID");
		table.setColumnHeader("custname", "CUSTOMER TRANS");
		table.setColumnHeader("spname", "SALESMAN TRANS");
		table.setColumnHeader("amountafterdiscafterppn", "NOMINAL+PPN");
		table.setColumnHeader("amountpay", "TERBAYAR");
		table.setColumnHeader("salesmanBean", "SALES ACTUAL");
		table.setColumnHeader("customerBean", "CUST ACTUAL");
		table.setColumnHeader("tertundacounter", "TT");
		table.setColumnHeader("terkirim", "KIRIM");
		table.setColumnHeader("tunaikredit", "T/K");
		table.setColumnHeader("lunas", "LNS");
		table.setColumnHeader("term", "TOP");
		table.setColumnHeader("returtampunglunas", "lns");
		
		
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
		getFieldSearchComboSupplier().setContainerDataSource(model.getBeanItemContainerVendor());
		getFieldSearchComboSupplier().setNullSelectionAllowed(true);		
		//KALAU KOSONG DIA AKAN MENAMPILKAN ERROR
		try{
			getFieldSearchComboSupplier().select(model.getBeanItemContainerDivision().getIdByIndex(0));
		} catch(Exception ex){}

		getFieldSearchComboProductDivision().setContainerDataSource(model.getBeanItemContainerProductDivisi());
		getFieldSearchComboProductDivision().setNullSelectionAllowed(true);		
		//KALAU KOSONG DIA AKAN MENAMPILKAN ERROR
		try{
			getFieldSearchComboProductDivision().select(model.getBeanItemContainerDivision().getIdByIndex(0));
		} catch(Exception ex){}
		
		
	}
	public void setDisplay(){

		//1. Refresh Table displa
		table.setContainerDataSource(model.getTableBeanItemContainer());
		
		setTableProperties();
		setDisplayFooter();
	}
	public void setDisplayFooter(){
		
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
	public FormOpnameModel getModel() {
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
	public TextField getFieldSearchByPcode() {
		return fieldSearchByPcode;
	}
	public TextField getFieldSearchByPname() {
		return fieldSearchByPname;
	}
	public ComboBox getFieldSearchComboProductDivision() {
		return fieldSearchComboProductDivision;
	}
	public ComboBox getFieldSearchComboSupplier() {
		return fieldSearchComboSupplier;
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
	public Button getBtnSearch() {
		return btnSearch;
	}
	public Button getBtnReload() {
		return btnReload;
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
	public Button getBtnExportToExel() {
		return btnExportToExel;
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
	public void setModel(FormOpnameModel model) {
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
	public void setFieldSearchByPcode(TextField fieldSearchByPcode) {
		this.fieldSearchByPcode = fieldSearchByPcode;
	}
	public void setFieldSearchByPname(TextField fieldSearchByPname) {
		this.fieldSearchByPname = fieldSearchByPname;
	}
	public void setFieldSearchComboProductDivision(
			ComboBox fieldSearchComboProductDivision) {
		this.fieldSearchComboProductDivision = fieldSearchComboProductDivision;
	}
	public void setFieldSearchComboSupplier(ComboBox fieldSearchComboSupplier) {
		this.fieldSearchComboSupplier = fieldSearchComboSupplier;
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
	public void setBtnSearch(Button btnSearch) {
		this.btnSearch = btnSearch;
	}
	public void setBtnReload(Button btnReload) {
		this.btnReload = btnReload;
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
	public void setBtnExportToExel(Button btnExportToExel) {
		this.btnExportToExel = btnExportToExel;
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
	
	
	
}
