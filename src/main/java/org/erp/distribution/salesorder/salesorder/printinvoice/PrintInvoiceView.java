package org.erp.distribution.salesorder.salesorder.printinvoice;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.User;
import org.erp.distribution.model.modelenum.EnumOperationStatus;

import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.FooterRow;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Form;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.DateRenderer;
import com.vaadin.ui.themes.Reindeer;

public class PrintInvoiceView extends CustomComponent {
	
	private PrintInvoiceModel model;
	private VerticalLayout content = new VerticalLayout();

	private Grid grid1 = new Grid();
	private FooterRow footerRow;

	private Table table;
	private Form form;
	private FieldFactory fieldFactory;

	private Class<FtSalesh> entityClass;
	
	private Object[] formPropertyIds;
	
	private String operationStatus;
    	
	//Additional Component	private TextField fieldSearchByRekap;
	private TextField fieldSearchByRekap;
	private Button btnSelectRekapNo = new Button("F");	

	private TextField fieldSearchById;
	private ComboBox fieldSearchComboDivisi;
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
	private CheckBox checkBelumTerbitInvoice = new CheckBox("Belum Terbit Invoice", false);
	
	private Button btnSearch;
	private Button btnReload;
	private Button btnPay;
	private Button btnLunaskan;
	private Button btnSelisihPlusMinus;
	
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
    
	User userActive = new User();
	
	public PrintInvoiceView(PrintInvoiceModel model){
		this.model = model;		
		
		userActive = ((DashboardUI) getUI().getCurrent()).getUserActive();
		
		initComponent();
		initFieldFactory();
		buildView();
		
		setComponentStyles();
		
		initComponentFirst();
		
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
		
//		dateFieldDatePembayaranManual.setDateFormat("dd/MM/yyyy");
//		dateFieldDatePembayaranManual.setValue(new Date());
		
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
		
		fieldSearchByDateInvoiceFrom = new DateField("TGL INV Mulai");
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
		
		fieldSearchComboToCanvas = new ComboBox("TO/C/R/S");
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
		
		btnPrint = new Button("Print");
		btnHelp = new Button("Help");
		
		btnSeparator1 = new Button("");
		btnSeparator1.setEnabled(false);
		btnSeparator2 = new Button("::");
		btnSeparator2.setEnabled(false);
                
        btnSearch.setIcon(new ThemeResource("../images/navigation/12x12/Find.png") );
        btnLunaskan.setIcon(new ThemeResource("../images/navigation/12x12/OK.png") );
        btnPay.setIcon(new ThemeResource("../images/navigation/12x12/Accounting.png") );
        
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
		
		
		grid1.setContainerDataSource(model.getTableBeanItemContainer());
		grid1.setSizeFull();
		grid1.setSelectionMode(SelectionMode.MULTI);
		
		footerRow = grid1.appendFooterRow();
		
//		HeaderRow headerRow = grid1.appendHeaderRow();
//		HeaderCell headerCellOrderNo = headerRow.getCell("orderno");
//		HeaderCell headerCellInvoiceNo = headerRow.getCell("invoiceno");
//		HeaderCell headerCellSjPenagihanNo = headerRow.getCell("sjpenagihanno");
//		
//		TextField fieldOrderNoFilter = new TextField();
//		fieldOrderNoFilter.setImmediate(true);
//		fieldOrderNoFilter.setSizeFull();
//		TextField fieldInvoiceNoFilter = new TextField();
//		fieldInvoiceNoFilter.setImmediate(true);
//		fieldInvoiceNoFilter.setSizeFull();
//		TextField fieldSjPenagihanNoFilter = new TextField();
//		fieldSjPenagihanNoFilter.setImmediate(true);
//		fieldSjPenagihanNoFilter.setSizeFull();
//		
//		
//		fieldOrderNoFilter.addTextChangeListener(getOrderNoFilter());
//		fieldInvoiceNoFilter.addTextChangeListener(getInvoiceNoFilter());
//		fieldSjPenagihanNoFilter.addTextChangeListener(getSjPenagihanNoFilter());
//		headerCellOrderNo.setComponent(fieldOrderNoFilter);		
//		headerCellInvoiceNo.setComponent(fieldInvoiceNoFilter);		
//		headerCellSjPenagihanNo.setComponent(fieldSjPenagihanNoFilter);		
		
                
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
		
//        layoutTable.addComponent(table);
      layoutTable.addComponent(grid1);
		
                //LAYOUT BOTTOM
		VerticalLayout layoutBottom = new VerticalLayout();
		HorizontalLayout layoutFooter1 = new HorizontalLayout();
		HorizontalLayout layoutFooter2 = new HorizontalLayout();
		layoutBottom.addComponent(layoutFooter1);
		layoutBottom.addComponent(layoutFooter2);
		
        //LAYOUT UTAMA
        content.addComponent(panelTop);
        content.addComponent(layoutTable);
//        content.addComponent(layoutBottom);
        setCompositionRoot(content);
        
        //Extended Konfigurasi Size
        table.setSizeFull();
        content.setExpandRatio(layoutTable, 1);

        //HANYA ADMINISTRATOR YANG BISA
////        if (userActive.getUserOtorizeType().equals(EnumUserOtorize.ADMINISTRATOR.getStrCode())) {
//	        layoutTopInner0.addComponent(dateFieldDatePembayaranManual);
//			layoutTopInner0.setComponentAlignment(dateFieldDatePembayaranManual, Alignment.BOTTOM_CENTER);
//	        layoutTopInner0.addComponent(checkBoxGunakanTanggalManual);
////			layoutTopInner0.setComponentAlignment(checkBoxGunakanTanggalManual, Alignment.BOTTOM_CENTER);
////        }		
		layoutTopInner1.addComponent(fieldSearchByRekap);
		layoutTopInner1.setComponentAlignment(fieldSearchByRekap, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(btnSelectRekapNo);
		layoutTopInner1.setComponentAlignment(btnSelectRekapNo, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(fieldSearchComboDivisi);
		layoutTopInner1.setComponentAlignment(fieldSearchComboDivisi, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(fieldSearchByInvoice);
		layoutTopInner1.setComponentAlignment(fieldSearchByInvoice, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(fieldSearchComboLunas);
		layoutTopInner1.setComponentAlignment(fieldSearchComboLunas, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(fieldSearchComboToCanvas);
		layoutTopInner1.setComponentAlignment(fieldSearchComboToCanvas, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(fieldSearchComboTunaiKredit);
		layoutTopInner1.setComponentAlignment(fieldSearchComboTunaiKredit, Alignment.BOTTOM_CENTER);
		
		layoutTopInner2.addComponent(fieldSearchByIdSalesman);
		layoutTopInner2.setComponentAlignment(fieldSearchByIdSalesman, Alignment.BOTTOM_CENTER);
		layoutTopInner2.addComponent(fieldSearchByNamaSalesman);
		layoutTopInner2.setComponentAlignment(fieldSearchByNamaSalesman, Alignment.BOTTOM_CENTER);
		layoutTopInner2.addComponent(fieldSearchByIdCustomer);
		layoutTopInner2.setComponentAlignment(fieldSearchByIdCustomer, Alignment.BOTTOM_CENTER);
		layoutTopInner2.addComponent(fieldSearchByNamaCustomer);
		layoutTopInner2.setComponentAlignment(fieldSearchByNamaCustomer, Alignment.BOTTOM_CENTER);
		layoutTopInner2.addComponent(fieldSearchByDateInvoiceFrom);
		layoutTopInner2.setComponentAlignment(fieldSearchByDateInvoiceFrom, Alignment.BOTTOM_CENTER);
		layoutTopInner2.addComponent(fieldSearchByDateInvoiceTo);
		layoutTopInner2.setComponentAlignment(fieldSearchByDateInvoiceTo, Alignment.BOTTOM_CENTER);
		
		layoutTopInner2.addComponent(fieldSearchByDateInvoiceKirimFrom);
		layoutTopInner2.setComponentAlignment(fieldSearchByDateInvoiceKirimFrom, Alignment.BOTTOM_CENTER);
//		layoutTopInner2.addComponent(fieldSearchByDateInvoiceKirimTo);
//		layoutTopInner2.setComponentAlignment(fieldSearchByDateInvoiceKirimTo, Alignment.BOTTOM_CENTER);
		
		layoutTopInner2.addComponent(checkLihatSemua);
		layoutTopInner2.setComponentAlignment(checkLihatSemua, Alignment.MIDDLE_CENTER);
		layoutTopInner2.addComponent(checkBelumTerbitInvoice);
		layoutTopInner2.setComponentAlignment(checkBelumTerbitInvoice, Alignment.MIDDLE_CENTER);
		
		layoutTopInner1.addComponent(btnSearch);
		layoutTopInner1.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
//		layoutTop.addComponent(btnReload);
//		layoutTop.setComponentAlignment(btnReload, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(btnSeparator1);
		layoutTopInner1.setComponentAlignment(btnSeparator1, Alignment.BOTTOM_CENTER);
		
		layoutTopInner1.addComponent(btnPrint);
		layoutTopInner1.setComponentAlignment(btnPrint, Alignment.BOTTOM_CENTER);

		layoutTopInner1.addComponent(btnPay);
		layoutTopInner1.setComponentAlignment(btnPay, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(btnLunaskan);
		layoutTopInner1.setComponentAlignment(btnLunaskan, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(btnSelisihPlusMinus);
		layoutTopInner1.setComponentAlignment(btnSelisihPlusMinus, Alignment.BOTTOM_CENTER);
		
	}
	public void initComponentFirst(){
		fieldSearchComboDivisi.setVisible(false);
		fieldSearchByRekap.setVisible(false);
		btnSelectRekapNo.setVisible(false);
		btnSelisihPlusMinus.setVisible(false);
		
		fieldSearchComboLunas.setVisible(false);
		fieldSearchByIdSalesman.setVisible(false);
		fieldSearchByNamaSalesman.setVisible(false);
		fieldSearchByIdCustomer.setVisible(false);
		fieldSearchByNamaCustomer.setVisible(false);
		fieldSearchByDateInvoiceKirimFrom.setVisible(false);
		fieldSearchByDateInvoiceKirimTo.setVisible(false);
		checkLihatSemua.setVisible(false);
		
		btnPay.setVisible(false);
		btnLunaskan.setVisible(false);
//		checkBoxGunakanTanggalManual.setValue(true);
//		dateFieldDatePembayaranManual.setValue(model.getTransaksiHelper().getCurrentTransDate());
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
		
		
		setVisibleTableProperties("selected", "orderno", "invoiceno", "orderdate", "invoicedate","fsalesmanBean.spcode", "fsalesmanBean.spname", 
				"fcustomerBean.custno", "fcustomerBean.custname", "fwarehouseBean.id", 
				"tunaikredit", "top", "duedate",
				"fwarehouseBean.description", 
				"amount", "amountafterdisc", "amountafterdiscafterppn", "amountpay");
		
		table.setColumnCollapsingAllowed(true);
		try{
			table.setColumnCollapsed("amount", true);
			table.setColumnCollapsed("fsalesmanBean.spcode", true);
			table.setColumnCollapsed("fcustomerBean.custno", true);
			table.setColumnCollapsed("fwarehouseBean.id", true);
			
		} catch(Exception ex){}
		//ALIGNMENT
		table.setColumnAlignment("selected", Align.CENTER);
		//ALIGNMENT
//		table.setColumnAlignment("nopo", Align.RIGHT);
//		table.setColumnAlignment("invoiceno", Align.RIGHT);
		table.setColumnAlignment("invoicedate", Align.CENTER);
		table.setColumnAlignment("orderdate", Align.CENTER);
		table.setColumnAlignment("amount", Align.RIGHT);
		table.setColumnAlignment("amountafterdisc", Align.RIGHT);
		table.setColumnAlignment("amountafterdiscafterppn", Align.RIGHT);
		table.setColumnAlignment("amountpay", Align.RIGHT);
		
		//set header
		table.setColumnHeader("selected", "<input type='checkbox'/>");
		table.setColumnHeader("orderno", "NO. ORDER");
		table.setColumnHeader("fsalesmanBean.custname", "SALESMAN");
		table.setColumnHeader("fcustomerBean.custname", "CUSTOMER");
		table.setColumnHeader("fwarehouseBean.custname", "WAREHOUSE");
		table.setColumnHeader("invoiceno", "INV. NO");
		table.setColumnHeader("invoicedate", "INV. DATE");
		table.setColumnHeader("orderdate", "PO. DATE");
		table.setColumnHeader("amount", "AMOUNT");
		table.setColumnHeader("amountafterdisc", "DPP");
		table.setColumnHeader("amountafterdiscafterppn", "DPP+PPN");
		table.setColumnHeader("amountpay", "AMOUNTPAY");
		
//		table.setColumnExpandRatio("nopo", 1);
//		table.setColumnExpandRatio("invoiceno", 4);
//		table.setColumnExpandRatio("invoicedate", 10);
//		table.setColumnExpandRatio("pprice", 5);
//		table.setColumnExpandRatio("podate", 4);
//		table.setColumnExpandRatio("amount", 4);
//		table.setColumnExpandRatio("amountpay", 4);
		
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
		
//		//1. Refresh Table displa
//		table.setContainerDataSource(model.getTableBeanItemContainer());
//		
//		setTableProperties();
//		setDisplayFooter();
		
		grid1.setContainerDataSource(model.getTableBeanItemContainer());
		setGridProperties();
		
	}
	public void setGridProperties(){
		for (Column c : grid1.getColumns()) {
        	c.setHidable(true);
        	c.setHidden(true);
        }
		
		grid1.getColumn("orderno").setHidden(false);
		grid1.getColumn("invoiceno").setHidden(false);
		grid1.getColumn("orderdate").setHidden(false);
		grid1.getColumn("invoicedate").setHidden(false);
		grid1.getColumn("fsalesmanBean.spcode").setHidden(false);
		grid1.getColumn("fcustomerBean.custname").setHidden(false);
		grid1.getColumn("fwarehouseBean.id").setHidden(false);
		grid1.getColumn("tunaikredit").setHidden(false);
		grid1.getColumn("top").setHidden(false);
		grid1.getColumn("duedate").setHidden(false);
		grid1.getColumn("amountafterdiscafterppn").setHidden(false);
		
		grid1.getColumn("orderno").setHeaderCaption("NO.ORDER");
		grid1.getColumn("invoiceno").setHeaderCaption("NO.INV");
		grid1.getColumn("orderdate").setHeaderCaption("TGL.ORDER");
		grid1.getColumn("invoicedate").setHeaderCaption("TGL.INV");
		grid1.getColumn("fsalesmanBean.spcode").setHeaderCaption("SALESMAN");
		grid1.getColumn("fcustomerBean.custname").setHeaderCaption("CUSTOMER");
		grid1.getColumn("fwarehouseBean.id").setHeaderCaption("GDG");
		grid1.getColumn("tunaikredit").setHeaderCaption("T/K");
		grid1.getColumn("top").setHeaderCaption("TOP");
		grid1.getColumn("duedate").setHeaderCaption("JTH.TEMPO");	
		grid1.getColumn("amountafterdiscafterppn").setHeaderCaption("AMOUNT+PPN");
		
		grid1.setColumnOrder("orderno", "invoiceno", "tunaikredit","orderdate", "invoicedate",
				"fsalesmanBean.spcode", "fcustomerBean.custname", "fwarehouseBean.id",
				"top", "duedate", "amountafterdiscafterppn");
		
		grid1.getColumn("orderno").setExpandRatio(3);
		grid1.getColumn("invoiceno").setExpandRatio(3);
		grid1.getColumn("orderdate").setExpandRatio(3);
		grid1.getColumn("invoicedate").setExpandRatio(2);
		grid1.getColumn("fsalesmanBean.spcode").setExpandRatio(2);
		grid1.getColumn("fcustomerBean.custname").setExpandRatio(4);
		grid1.getColumn("fwarehouseBean.id").setExpandRatio(2);
		grid1.getColumn("tunaikredit").setExpandRatio(1);
		grid1.getColumn("top").setExpandRatio(1);
		grid1.getColumn("duedate").setExpandRatio(2);
		grid1.getColumn("amountafterdiscafterppn").setExpandRatio(3);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		grid1.getColumn("orderdate").setRenderer(new DateRenderer(sdf));
		grid1.getColumn("invoicedate").setRenderer(new DateRenderer(sdf));
		grid1.getColumn("duedate").setRenderer(new DateRenderer(sdf));
		
		grid1.setCellStyleGenerator(new Grid.CellStyleGenerator() {
            @Override
            public String getStyle(Grid.CellReference cellReference) {
                if ("amountafterdiscafterppn".equals(cellReference.getPropertyId())) {
                    // when the current cell is number such as age, align text to right
                    return "rightAligned";
                } else if ("amountpay".equals(cellReference.getPropertyId())){                	
                	return "rightAligned";
                } else if ("orderdate".equals(cellReference.getPropertyId())){                	
                	return "centerAligned";
                } else if ("invoicedate".equals(cellReference.getPropertyId())){                	
                	return "centerAligned";
                } else if ("duedate".equals(cellReference.getPropertyId())){                	
                	return "centerAligned";
                } else if ("top".equals(cellReference.getPropertyId())){                	
                	return "centerAligned";
                } else if ("tunaikredit".equals(cellReference.getPropertyId())){                	
                	return "centerAligned";
                } else {
                    // otherwise, align text to left
                    return "leftAligned";
                }
            }
        });			

	}
	
	public void setDisplayFooter(){
		
	}

	public void resetFieldSearch(){
//		fieldSearchByInvoice.setValue("");
//		fieldSearchByIdCustomer.setValue("");
//		fieldSearchByNamaCustomer.setValue("");
//		
//		fieldSearchByIdSalesman.setValue("");
//		fieldSearchByNamaSalesman.setValue("");
	}
	public PrintInvoiceModel getModel() {
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
	public TextField getFieldSearchByRekap() {
		return fieldSearchByRekap;
	}
	public Button getBtnSelectRekapNo() {
		return btnSelectRekapNo;
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
	public Button getBtnPay() {
		return btnPay;
	}
	public Button getBtnLunaskan() {
		return btnLunaskan;
	}
	public Button getBtnSelisihPlusMinus() {
		return btnSelisihPlusMinus;
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
	public User getUserActive() {
		return userActive;
	}
	public void setModel(PrintInvoiceModel model) {
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
	public void setFieldSearchByRekap(TextField fieldSearchByRekap) {
		this.fieldSearchByRekap = fieldSearchByRekap;
	}
	public void setBtnSelectRekapNo(Button btnSelectRekapNo) {
		this.btnSelectRekapNo = btnSelectRekapNo;
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
	public void setBtnPay(Button btnPay) {
		this.btnPay = btnPay;
	}
	public void setBtnLunaskan(Button btnLunaskan) {
		this.btnLunaskan = btnLunaskan;
	}
	public void setBtnSelisihPlusMinus(Button btnSelisihPlusMinus) {
		this.btnSelisihPlusMinus = btnSelisihPlusMinus;
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
	public void setUserActive(User userActive) {
		this.userActive = userActive;
	}
	public CheckBox getCheckBelumTerbitInvoice() {
		return checkBelumTerbitInvoice;
	}
	public void setCheckBelumTerbitInvoice(CheckBox checkBelumTerbitInvoice) {
		this.checkBelumTerbitInvoice = checkBelumTerbitInvoice;
	}
	public Grid getGrid1() {
		return grid1;
	}
	public FooterRow getFooterRow() {
		return footerRow;
	}
	public void setGrid1(Grid grid1) {
		this.grid1 = grid1;
	}
	public void setFooterRow(FooterRow footerRow) {
		this.footerRow = footerRow;
	}

	
	
	
}
