package org.erp.distribution.kontrolstok.stockopname;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.erp.distribution.kontrolstok.stockopname.windowitem.StockOpnameItemModel;
import org.erp.distribution.kontrolstok.stockopname.windowitem.StockOpnameItemPresenter;
import org.erp.distribution.kontrolstok.stockopname.windowitem.StockOpnameItemView;
import org.erp.distribution.model.FtOpnamed;
import org.erp.distribution.model.modelenum.EnumOperationStatus;

import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class StockOpnameView extends CustomComponent{
	private StockOpnameModel model;
	
	StockOpnameHelper helper;
	
	private VerticalLayout content = new VerticalLayout();
	
	private Table tableList;
	private Table tableDetil;
	//LIST
	private TextField fieldSearch1 = new TextField("NO. DOK");
	private TextField fieldSearch2 = new TextField("NO. INVOICE");
	private TextField fieldSearch3 = new TextField("NO");
	private ComboBox comboSearch1 = new ComboBox("SUPPLIER");
	private ComboBox comboSearch2 = new ComboBox("SUPPLIER");
	
	//DETIL
	private TextField fieldNomor = new TextField("NO DOK");
	private TextField fieldInvoiceno= new TextField("NO. INVOICE");
	private ComboBox comboTipeopname = new ComboBox("TIPE OPNAME");
	private ComboBox comboWarehouse = new ComboBox("WAREHOUSE OPNAME");
	
	private DateField dateFieldTrdate = new DateField("TGL. TRANS");
	private DateField dateFieldEntrydate = new DateField("TGL ENTRY");
	
	//BUTTON HEADER
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel");
	private Button btnNewForm= new Button("New");
	private Button btnEditForm= new Button("Edit");
	private Button btnDeleteForm= new Button("Delete");
	
	//BUTTON DETIL	
	private Button btnAddItem= new Button("Add");
	private Button btnEditItem= new Button("Edit");
	private Button btnRemoveItem= new Button("Rem");
	
	private Button btnSeparator1= new Button("*");
	private Button btnSeparator2= new Button("*");

	//FIELD HEADER
	private TextField fieldAmount= new TextField("SUB TOTAL");
	private TextField fieldAmountafterppn= new TextField("SUB TOTAL+PPN");
	private TextField fieldDisc1= new TextField("Diskon1");
	private TextField fieldDisc1rp= new TextField("Diskon1 Rp");
	private TextField fieldDisc1rpafterppn= new TextField("Diskon1 Rp+PPN");
	private TextField fieldDisc2= new TextField("Diskon2");
	private TextField fieldDisc2rp= new TextField("Diskon2 Rp");
	private TextField fieldDisc2rpafterppn= new TextField("Diskon2 Rp+PPN");
	private TextField fieldDisc= new TextField("+Diskon");
	private TextField fieldDiscrp= new TextField("+Diskon Rp");
	private TextField fieldDiscrpafterppn= new TextField("+Diskon Rp+PPN");
	private TextField fieldPpnpercent= new TextField("Ppn");
	private TextField fieldPpnrp= new TextField("Ppn Rp");
	private TextField fieldAmountafterdisc= new TextField("TOTAL DPP");
	private TextField fieldAmountafterdiscafterppn= new TextField("TOTAL+PPN");
	private TextField fieldAmountpay= new TextField("TERBAYAR");
	private TextField fieldAmountpayfaterppn= new TextField("TERBAYAR+PPN");
	private CheckBox checkSaldo = new CheckBox("SALDO");
	private CheckBox checkEndofday = new CheckBox("CLOSING");
	private CheckBox checkPosting = new CheckBox("POSTING");
	
	//Additional Component
	private Button btnSearch = new Button("Search & Reload");
	private Button btnPosting = new Button("Posting");
	private Button btnPostingBatal = new Button("Pembatalan Posting");
	private Button btnHelp = new Button("Help");
	private Button btnLapselisih = new Button("Print Selisih");
	
	//LAYOUT
	private TabSheet tabSheet = new TabSheet();
	private VerticalLayout layoutList = new VerticalLayout();
	private VerticalLayout layoutDetil = new VerticalLayout();
	
	//Panel
	private Panel panelUtamaList = new Panel();
	private Panel panelUtamaDetil = new Panel();
	private Panel panelTopDetil = new Panel();
	private Panel panelTabelDetil = new Panel();
	private Panel panelFormDetil = new Panel();
	
	
	
	public StockOpnameView(StockOpnameModel model){
		this.model = model;
		helper = new StockOpnameHelper(model, this);
		
		initComponent();
		buildView();
		initComponentState();
		
		setDisplayList();;
		setDisplayDetil();
		
		setComponentStyles();
		
	
	}
	
	public void initComponent(){
		
		tableList = new Table() {
		    @Override
		    protected String formatPropertyValue(Object rowId,
		            Object colId, Property property) {
		        // Format by property type
		        if (property.getType() == Date.class && property.getValue() != null) {
		            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		            return df.format((Date)property.getValue());
		        }
		        
//		        if (property.getType()==Boolean.class){
//		        	if ((Boolean) property.getValue()==true) {
//		        		return "Active";
//		        	} else {
//		        		return "-";
//		        	}
//		        }
		        
		        return super.formatPropertyValue(rowId, colId, property);
		    }
		};		
		tableDetil = new Table() {
		    @Override
		    protected String formatPropertyValue(Object rowId,
		            Object colId, Property property) {
		        // Format by property type
		        if (property.getType() == Date.class && property.getValue() != null) {
		            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		            return df.format((Date)property.getValue());
		        }
		        
//		        if (property.getType()==Boolean.class){
//		        	if ((Boolean) property.getValue()==true) {
//		        		return "Active";
//		        	} else {
//		        		return "-";
//		        	}
//		        }
		        
		        return super.formatPropertyValue(rowId, colId, property);
		    }
		};		
		
		//::LIST
		fieldSearch1.setWidth("100px");
		fieldSearch2.setWidth("100px");
		fieldSearch2.setWidth("100px");
		comboSearch1.setWidth("200px");
		comboSearch2.setWidth("200px");
		
		//::DETIL
		fieldNomor.setNullRepresentation("");
		fieldInvoiceno.setNullRepresentation("");
		
		fieldNomor.setWidth("100px");
		fieldInvoiceno.setWidth("100px");
		comboTipeopname.setWidth("200px");
		comboTipeopname.setFilteringMode(FilteringMode.CONTAINS);
		comboWarehouse.setWidth("200px");
		comboWarehouse.setFilteringMode(FilteringMode.CONTAINS);
		
		dateFieldTrdate.setDateFormat("dd/MM/yyyy");
		dateFieldEntrydate.setDateFormat("dd/MM/yyyy");
		dateFieldTrdate.setWidth("100px");
		dateFieldEntrydate.setWidth("100px");
		
		btnSearch.setIcon(new ThemeResource("../images/navigation/12x12/Find.png"));
		btnNewForm.setIcon(new ThemeResource("../images/navigation/12x12/Create.png"));
		btnEditForm.setIcon(new ThemeResource("../images/navigation/12x12/Pencil.png"));
		btnDeleteForm.setIcon(new ThemeResource("../images/navigation/12x12/Erase.png"));
//		btnPrint.setIcon(new ThemeResource("../images/navigation/12x12/Print.png"));
		
		btnSaveForm.setIcon(new ThemeResource("../images/navigation/12x12/Save.png"));
		btnCancelForm.setIcon(new ThemeResource("../images/navigation/12x12/Undo.png"));

		btnAddItem.setIcon(new ThemeResource("../images/navigation/12x12/Create.png"));
		btnEditForm.setIcon(new ThemeResource("../images/navigation/12x12/Pencil.png"));
		btnRemoveItem.setIcon(new ThemeResource("../images/navigation/12x12/Erase.png"));

		btnPosting.setIcon(new ThemeResource("../images/navigation/12x12/OK.png"));
//		btnPostingBatal.setIcon(new ThemeResource("../images/navigation/12x12/OK.png"));
		btnLapselisih.setIcon(new ThemeResource("../images/navigation/12x12/Print.png"));
		
		tableList.setSelectable(true);
		tableList.setImmediate(true);
		tableList.setBuffered(false);
		tableList.setFooterVisible(true);
		
		tableDetil.setSelectable(true);
		tableDetil.setImmediate(true);
		tableDetil.setBuffered(false);
		tableDetil.setFooterVisible(true);
		
		//VALIDATOR
		fieldNomor.setRequired(true);
		comboTipeopname.setRequired(true);
		comboWarehouse.setRequired(true);
		dateFieldTrdate.setRequired(true);
//		fieldDescription.setRequired(true);
		
		btnSeparator1.setEnabled(false);
		btnSeparator2.setEnabled(false);
		
		checkPosting.setImmediate(true);
		checkEndofday.setImmediate(true);
		
		//FIELD FOOTER
		fieldDisc1= new TextField();
		fieldDisc1rp= new TextField();
		fieldDisc1rpafterppn= new TextField();	
		fieldDisc1.setWidth("50px");
		fieldDisc1rp.setWidth("50px");
		fieldDisc1rpafterppn.setWidth("150px");
		fieldDisc1rpafterppn.addStyleName("numerical");
		fieldDisc1.setNullRepresentation("");
		fieldDisc1rp.setNullRepresentation("");
		fieldDisc1rpafterppn.setNullRepresentation("");
		
		fieldDisc2= new TextField();
		fieldDisc2rp= new TextField();
		fieldDisc2rpafterppn= new TextField();	
		fieldDisc2.setWidth("50px");
		fieldDisc2rp.setWidth("50px");
		fieldDisc2rpafterppn.setWidth("150px");
		fieldDisc2rpafterppn.addStyleName("numerical");
		fieldDisc2.setNullRepresentation("");
		fieldDisc2rp.setNullRepresentation("");
		fieldDisc2rpafterppn.setNullRepresentation("");
		
		fieldDisc= new TextField();
		fieldDiscrp= new TextField();
		fieldDiscrpafterppn= new TextField();	
		fieldDisc.setWidth("50px");
		fieldDiscrp.setWidth("50px");
		fieldDiscrpafterppn.setWidth("150px");
		fieldDiscrpafterppn.addStyleName("numerical");
		fieldDisc.setNullRepresentation("");
		fieldDiscrp.setNullRepresentation("");
		fieldDiscrpafterppn.setNullRepresentation("");
		
		fieldAmount.setWidth("200px");
		fieldAmount.addStyleName("numerical");
		fieldAmountafterdisc.setWidth("200px");
		fieldAmountafterdisc.addStyleName("numerical");
		fieldAmountafterdiscafterppn.setWidth("200px");
		fieldAmountafterdiscafterppn.addStyleName("numerical");
		fieldAmountafterppn.setWidth("200px");
		fieldAmountafterppn.addStyleName("numerical");
		fieldAmountpay.setWidth("200px");
		fieldAmountpay.addStyleName("numerical");
		fieldAmountpayfaterppn.setWidth("200px");
		fieldAmountpayfaterppn.addStyleName("numerical");
		fieldPpnrp.setWidth("200px");
		fieldPpnrp.addStyleName("numerical");

		fieldPpnpercent.setWidth("50px");
		
		fieldAmount.setNullRepresentation("");
		fieldAmountafterdisc.setNullRepresentation("");
		fieldAmountafterdiscafterppn.setNullRepresentation("");
		fieldAmountafterppn.setNullRepresentation("");
		fieldAmountpay.setNullRepresentation("");
		fieldAmountpayfaterppn.setNullRepresentation("");
		fieldPpnrp.setNullRepresentation("");

		
	}
	
	public void buildView(){
		
		//PANEL
		panelUtamaList.setSizeFull();
		panelUtamaDetil.setSizeFull();
		panelFormDetil.setSizeFull();
		content.setSizeFull();

		tabSheet.setSizeFull();
		
		//::LIST
		layoutList.setSizeFull();
		layoutList.setMargin(true);
		VerticalLayout layoutTopList = new VerticalLayout();
		HorizontalLayout layoutTopList1 = new HorizontalLayout();		
		layoutTopList.addComponent(layoutTopList1);
		
		layoutTopList1.addComponent(fieldSearch1);
//		layoutTopList1.addComponent(fieldSearch2);
		layoutTopList1.addComponent(btnSearch);
		layoutTopList1.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
		
		tableList.setSizeFull();
	
		VerticalLayout layoutMiddleList = new VerticalLayout();
		layoutMiddleList.setSizeFull();
		layoutMiddleList.addComponent(tableList);
		
		layoutList.addComponent(layoutTopList);
		layoutList.addComponent(layoutMiddleList);
		layoutList.setExpandRatio(layoutMiddleList, 1);		
		
		//:::DETIL
		layoutDetil.setSizeFull();
		layoutDetil.setMargin(true);
		//KOMPONEN ATAS
		VerticalLayout layoutTopDetil = new VerticalLayout();
		HorizontalLayout layoutTopDetil1 = new HorizontalLayout();		
		HorizontalLayout layoutTopDetil2 = new HorizontalLayout();		
		layoutTopDetil.addComponent(layoutTopDetil1);
		layoutTopDetil.addComponent(layoutTopDetil2);
		layoutTopDetil.setComponentAlignment(layoutTopDetil2, Alignment.MIDDLE_RIGHT);
		
		layoutTopDetil1.addComponent(fieldNomor);
//		layoutTopDetil1.addComponent(fieldInvoiceno);
		layoutTopDetil1.addComponent(comboTipeopname);
		layoutTopDetil1.addComponent(dateFieldTrdate);
		layoutTopDetil1.addComponent(dateFieldEntrydate);
		layoutTopDetil1.addComponent(comboWarehouse);
//		layoutTopDetil1.addComponent(comboWarehouseTo);
		layoutTopDetil1.addComponent(checkSaldo);
		layoutTopDetil1.addComponent(checkPosting);
		layoutTopDetil1.addComponent(checkEndofday);
		
		layoutTopDetil2.addComponent(btnNewForm);
		layoutTopDetil2.addComponent(btnEditForm);
		layoutTopDetil2.addComponent(btnDeleteForm);
		layoutTopDetil2.addComponent(btnPosting);
		layoutTopDetil2.addComponent(btnLapselisih);
		layoutTopDetil2.addComponent(btnPostingBatal);
	
		layoutTopDetil2.addComponent(btnSeparator1);
		
		layoutTopDetil2.addComponent(btnSaveForm);
		layoutTopDetil2.addComponent(btnCancelForm);

		layoutTopDetil2.addComponent(btnSeparator2);
		
		layoutTopDetil2.addComponent(btnAddItem);
		layoutTopDetil2.addComponent(btnEditItem);
		layoutTopDetil2.addComponent(btnRemoveItem);
		
		layoutTopDetil2.setComponentAlignment(btnNewForm, Alignment.BOTTOM_CENTER);
		layoutTopDetil2.setComponentAlignment(btnEditForm, Alignment.BOTTOM_CENTER);
		layoutTopDetil2.setComponentAlignment(btnDeleteForm, Alignment.BOTTOM_CENTER);
		layoutTopDetil2.setComponentAlignment(btnPosting, Alignment.BOTTOM_CENTER);
		layoutTopDetil2.setComponentAlignment(btnLapselisih, Alignment.BOTTOM_CENTER);
		
		//KOMPONEN TENGAH
		//INIT COMPONENT TENGAH
		tableDetil.setSizeFull();
		
		VerticalLayout layoutMiddleDetil = new VerticalLayout();
		layoutMiddleDetil.setSizeFull();
		layoutMiddleDetil.addComponent(tableDetil);
		
		HorizontalLayout layoutBottomDetil = new HorizontalLayout();
		FormLayout layoutBottomDetil1 = new FormLayout();
//		layoutBottomDetil1.setSizeFull();
		layoutBottomDetil1.addComponent(fieldAmount);
		layoutBottomDetil1.addComponent(fieldAmountafterppn);

		HorizontalLayout layoutDisc1 = new HorizontalLayout();
		layoutDisc1.setCaption("Diskon1");		
		layoutDisc1.addComponent(fieldDisc1);
		layoutDisc1.addComponent(fieldDisc1rp);
		layoutDisc1.addComponent(fieldDisc1rpafterppn);
		layoutBottomDetil1.addComponent(layoutDisc1);
		
		HorizontalLayout layoutDisc2 = new HorizontalLayout();
//		layoutDisc2.setCaption("Diskon2");		
		layoutDisc2.addComponent(fieldDisc2);
		layoutDisc2.addComponent(fieldDisc2rp);
		layoutDisc2.addComponent(fieldDisc2rpafterppn);
		
		
		HorizontalLayout layoutDisc = new HorizontalLayout();
		layoutDisc.setCaption("+Disc");
		layoutDisc.addComponent(fieldDisc);
		layoutDisc.addComponent(fieldDiscrp);
		layoutDisc.addComponent(fieldDiscrpafterppn);
		layoutBottomDetil1.addComponent(layoutDisc);
		
		layoutBottomDetil1.addComponent(fieldAmountafterdisc);		
		layoutBottomDetil1.addComponent(fieldPpnpercent);
		layoutBottomDetil1.addComponent(fieldPpnrp);		
		layoutBottomDetil1.addComponent(fieldAmountafterdiscafterppn);
		layoutBottomDetil1.addComponent(fieldAmountpay);		
		layoutBottomDetil1.addComponent(fieldAmountpayfaterppn);
		
		layoutBottomDetil.addComponent(layoutBottomDetil1);
		
		layoutDetil.addComponent(layoutTopDetil);
		layoutDetil.addComponent(layoutMiddleDetil);
		layoutDetil.addComponent(layoutBottomDetil);
		layoutDetil.setExpandRatio(layoutMiddleDetil, 1);
		layoutDetil.setExpandRatio(layoutMiddleDetil, 1);		
		layoutDetil.setComponentAlignment(layoutBottomDetil, Alignment.TOP_RIGHT);
		
		panelUtamaList.setContent(layoutList);		
		panelUtamaDetil.setContent(layoutDetil);		

		tabSheet.addTab(panelUtamaList, "LIST");
		tabSheet.addTab(panelUtamaDetil, "DETIL");
		
		content.addComponent(tabSheet);		
		setCompositionRoot(content);	
		
	}
	
	public void initComponentState(){
		fieldAmount.setVisible(false);
		fieldAmountafterppn.setVisible(false);
		
		fieldDisc1rp.setVisible(false);
//		fieldDisc1rpafterppn.setVisible(false);
	
		fieldDisc2.setVisible(false);
		fieldDisc2rp.setVisible(false);
		fieldDisc2rpafterppn.setVisible(false);
		
		fieldDiscrp.setVisible(false);
//		fieldDiscrpafterppn.setVisible(false);

		fieldPpnpercent.setVisible(false);
		
		fieldAmountpay.setVisible(false);
		fieldAmountpayfaterppn.setVisible(false);
		
		checkSaldo.setVisible(false);
		
		fieldAmount.setVisible(false);
		fieldPpnrp.setVisible(false);
		fieldAmountafterdisc.setVisible(false);
		fieldAmountafterdiscafterppn.setVisible(false);
		
//		btnLapselisih.setVisible(false);
	}
	
	public void setDisplayList(){
		//::TABLE
		tableList.setContainerDataSource(model.getBeanItemContainerHeader());
		setTablePropertiesList();		
		setDisplayTableFooterList();		
		bindAndBuildFieldGroupComponentList();
		
	}
	
	public void setComponentStyles(){
//		if (! getUI().getTheme().equals("vaadin_theme")) {
//			tableList.addStyleName("compact small");
//			tableDetil.addStyleName("compact small");
//			
//			fieldOrderno.addStyleName("small");
//			fieldInvoiceno.addStyleName("small");
//			comboTipeJual.addStyleName("small");
//			comboSalesman.addStyleName("small");
//			comboCustomer.addStyleName("small");
//			dateFieldOrderdate.addStyleName("small");
//			dateFieldInvoicedate.addStyleName("small");
//			comboTunaikredit.addStyleName("small");
//			comboTop.addStyleName("small");
//			dateFieldDuedate.addStyleName("small");
//			comboWarehouse.addStyleName("small");
//			checkEndofday.addStyleName("small");
//			checkSaldo.addStyleName("small");
//			checkSearch1.addStyleName("small");
		
			btnSearch.addStyleName("small");
			btnNewForm.addStyleName("small");
			btnEditForm.addStyleName("small");
			btnDeleteForm.addStyleName("small");
//			btnPrint.addStyleName("small");
			
			btnSaveForm.addStyleName("small");
			btnCancelForm.addStyleName("small");

			btnAddItem.addStyleName("small");
			btnEditItem.addStyleName("small");
			btnRemoveItem.addStyleName("small");

			btnPosting.addStyleName("small");
			btnLapselisih.addStyleName("small");
			btnPostingBatal.addStyleName("small");
			
			btnSeparator1.addStyleName("small");
			btnSeparator2.addStyleName("small");
		
		
//		}
		
//		tabSheet.addStyleName("framed compact-tabbar small");
		tabSheet.addStyleName(Reindeer.TABSHEET_BORDERLESS);
		tabSheet.addStyleName(Reindeer.TABSHEET_SMALL);
		
	}
	
	
	public void setDisplayDetil(){
		//::TABLE
		tableDetil.setContainerDataSource(model.getBeanItemContainerDetil());		
		setTablePropertiesDetil();		
		setDisplayTableFooterDetil();;		
		bindAndBuildFieldGroupComponentDetilHeader();		
		setFormButtonAndTextState();
		
	}
	
	public void setDisplayTableFooterList(){
		Collection itemIds =  model.getBeanItemContainerHeader().getItemIds();
		tableList.setColumnFooter("invoiceno", "*Record: " + itemIds.size());
	}
	
	public void setDisplayTableFooterDetil(){
		Collection itemIds =  model.getBeanItemContainerDetil().getItemIds();
		tableDetil.setColumnFooter("fproductBean.pcode", "*Record: " + itemIds.size());
		double sumTotalNoPpn =0;
		double sumTotalWithPpn = 0;		
	}
	
	public void bindAndBuildFieldGroupComponentList(){				
	}
	
	public void bindAndBuildFieldGroupComponentDetilHeader(){
		model.getBinderHeader().setBuffered(false);

		comboTipeopname.addItem(0);
		comboTipeopname.setItemCaption(0, "-- Pilih --");
		comboTipeopname.addItem(1);
		comboTipeopname.setItemCaption(1, "Semua Item");
		comboTipeopname.addItem(2);		
		comboTipeopname.setItemCaption(2, "Parsial");		
		comboTipeopname.select(2);
		
		comboTipeopname.setFilteringMode(FilteringMode.CONTAINS);
		comboTipeopname.setNullSelectionAllowed(false);

		comboWarehouse.setContainerDataSource(model.getBeanItemContainerWarehouseFrom());
		comboWarehouse.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboWarehouse.setFilteringMode(FilteringMode.CONTAINS);
		comboWarehouse.setNullSelectionAllowed(false);
	
		model.getBinderHeader().bind(fieldNomor, "norek");
//		model.getBinderHeader().bind(fieldInvoiceno, "invoiceno");
//		model.getBinderHeader().bind(comboVendor, "fvendorBean");
		model.getBinderHeader().bind(dateFieldTrdate, "trdate");
		model.getBinderHeader().bind(dateFieldEntrydate, "entrydate");
		model.getBinderHeader().bind(comboTipeopname, "tipeopname");
		model.getBinderHeader().bind(comboWarehouse, "fwarehouseBean");
		
		model.getBinderHeader().bind(checkPosting, "posting");
		model.getBinderHeader().bind(checkEndofday, "endofday");
	}
	
	public void fillComponentDetilItem(){
		//1. HAPUS CONTAINER DETIL::TABLE
		model.getBeanItemContainerDetil().removeAllItems();
		//2. AMBIL DETIL DENGAN MENGGUNAKAN KODE HEADER
		List<FtOpnamed> list = new ArrayList<FtOpnamed>();
		list = model.getFtOpnamedJpaService().findAllDetilByRefno(model.getItemHeader().getRefno());
		
		//3. PERBAIKI CONTENT TRANSIENT
		List<FtOpnamed> newList = new ArrayList<FtOpnamed>();
		newList = helper.updateAndCalculateItemDetilFromList(list);
		//4. MASUKKAN KE DALAM LIST
		model.getBeanItemContainerDetil().addAll(newList);
		tableDetil.refreshRowCache();
	}
	
	public void setVisibleTablePropertiesList(Object... tablePropertyIds) {
		tableList.setVisibleColumns(tablePropertyIds);		
	}
	
	public void setTablePropertiesList(){

		setVisibleTablePropertiesList("refno", "norek", "tipeopname", "trdate", "entrydate",
				"fwarehouseBean.id", "fwarehouseBean.description", "posting");
		
		tableList.setColumnCollapsingAllowed(true);
		try{
			tableList.setColumnCollapsed("refno", true);
			tableList.setColumnCollapsed("amount", true);
			tableList.setColumnCollapsed("fvendorBean.vcode", true);
			tableList.setColumnCollapsed("fwarehouseBean.id", true);
			
		} catch(Exception ex){}
		
		//ALIGNMENT
		tableList.setColumnAlignment("invoicedate", Align.CENTER);
		tableList.setColumnAlignment("podate", Align.CENTER);
		tableList.setColumnAlignment("amount", Align.RIGHT);
		tableList.setColumnAlignment("amountafterdisc", Align.RIGHT);
		tableList.setColumnAlignment("amountafterdiscafterppn", Align.RIGHT);
		tableList.setColumnAlignment("amountpay", Align.RIGHT);

		
		//set header
		tableList.setColumnHeader("nopo", "NO. PO");
		tableList.setColumnHeader("fvendorBean.vname", "SUPPLIER");
		tableList.setColumnHeader("invoiceno", "INV. NO");
		tableList.setColumnHeader("invoicedate", "INV. DATE");
		tableList.setColumnHeader("podate", "PO. DATE");
		tableList.setColumnHeader("amount", "AMOUNT");
		tableList.setColumnHeader("amountafterdisc", "DPP");
		tableList.setColumnHeader("amountafterdiscafterppn", "DPP+PPN");
		tableList.setColumnHeader("amountpay", "AMOUNTPAY");
		
		
//		tableList.setColumnExpandRatio("nopo", 1);
//		tableList.setColumnExpandRatio("invoiceno", 4);
//		tableList.setColumnExpandRatio("invoicedate", 10);
//		tableList.setColumnExpandRatio("pprice", 5);
//		tableList.setColumnExpandRatio("podate", 4);
//		tableList.setColumnExpandRatio("amount", 4);
//		tableList.setColumnExpandRatio("amountpay", 4);
				
	}
	
	public void setVisibleTablePropertiesDetil(Object... tablePropertyIds) {
		tableDetil.setVisibleColumns(tablePropertyIds);		
	}
	
	public void setTablePropertiesDetil(){

//		setVisibleTablePropertiesDetil("nourut", "fproductBean.pcode", "fproductBean.pname", "fproductBean.packaging", 
//				"pprice", "ppriceafterppn", "qty1", "qty2", "qty3", "qty",  
//				"subtotal", "subtotalafterppn", "disc1", "disc1rp", "disc1rpafterppn", 
//				"disc2", "disc2rp", "disc2rpafterppn", 
//				"subtotalafterdisc", "subtotalafterdiscafterppn");
		setVisibleTablePropertiesDetil("id", "nourut", "fproductBean.pcode", "fproductBean.pname", "fproductBean.packaging", "qty1", "qty2", "qty3", "qty");
		
		tableDetil.setColumnCollapsingAllowed(true);
		try{
			tableDetil.setColumnCollapsed("pprice", true);
			tableDetil.setColumnCollapsed("id", true);
			
			tableDetil.setColumnCollapsed("nourut", true);
//			tableDetil.setColumnCollapsed("qty2", true);
//			tableDetil.setColumnCollapsed("qty3", true);
			tableDetil.setColumnCollapsed("qty", true);

			tableDetil.setColumnCollapsed("subtotal", true);
//			tableDetil.setColumnCollapsed("subtotalafterppn", true);
			
			tableDetil.setColumnCollapsed("disc1rp", true);
//			tableDetil.setColumnCollapsed("disc1rpafterppn", true);
			tableDetil.setColumnCollapsed("disc2rp", true);
//			tableDetil.setColumnCollapsed("disc2rpafterppn", true);
			
			tableDetil.setColumnCollapsed("subtotalafterdisc", true);
//			tableDetil.setColumnCollapsed("subtotalafterdiscafterppn", true);
			
		} catch(Exception ex){}
		
		//ALIGNMENT
		tableDetil.setColumnAlignment("pprice", Align.RIGHT);
		tableDetil.setColumnAlignment("ppriceafterppn", Align.RIGHT);
		tableDetil.setColumnAlignment("qty1", Align.CENTER);
		tableDetil.setColumnAlignment("qty2", Align.CENTER);
		tableDetil.setColumnAlignment("qty3", Align.CENTER);
		tableDetil.setColumnAlignment("qty", Align.CENTER);
		tableDetil.setColumnAlignment("disc1", Align.RIGHT);
		tableDetil.setColumnAlignment("disc1rp", Align.RIGHT);
		tableDetil.setColumnAlignment("disc1rpafterppn", Align.RIGHT);
		tableDetil.setColumnAlignment("disc2", Align.RIGHT);
		tableDetil.setColumnAlignment("disc2rp", Align.RIGHT);	
		tableDetil.setColumnAlignment("disc2rpafterppn", Align.RIGHT);	
		tableDetil.setColumnAlignment("subtotal", Align.RIGHT);
		tableDetil.setColumnAlignment("subtotalafterppn", Align.RIGHT);
		tableDetil.setColumnAlignment("subtotalafterdisc", Align.RIGHT);
		tableDetil.setColumnAlignment("subtotalafterdiscafterppn", Align.RIGHT);
		
		//set header
		tableDetil.setColumnHeader("nourut", "NO");
		tableDetil.setColumnHeader("fproductBean.pcode", "PCODE");
		tableDetil.setColumnHeader("fproductBean.pname", "NAMA PRODUK");
		tableDetil.setColumnHeader("fproductBean.packaging", "PACKAGING");
		tableDetil.setColumnHeader("pprice", "HRG BELI");
		tableDetil.setColumnHeader("ppriceafterppn", "BELI+PPN");
		tableDetil.setColumnHeader("qty1", "BES");
		tableDetil.setColumnHeader("qty2", "SED");
		tableDetil.setColumnHeader("qty3", "KEC");
		tableDetil.setColumnHeader("qty", "QTY");
		tableDetil.setColumnHeader("disc1", "DISC1");
		tableDetil.setColumnHeader("disc1rp", "DISC1 Rp");
		tableDetil.setColumnHeader("disc2", "+DISC2");
		tableDetil.setColumnHeader("disc2rp", "+DISC2 Rp");
		tableDetil.setColumnHeader("subtotal", "TOTAL");
		tableDetil.setColumnHeader("subtotalafterppn", "TOTAL+PPN");
		tableDetil.setColumnHeader("subtotalafterdisc", "TOTAL-DISC");
		tableDetil.setColumnHeader("subtotalafterdiscafterppn", "TOTAL-DISC+PPN");
		
		
		tableDetil.setColumnExpandRatio("nourut", 1);
		tableDetil.setColumnExpandRatio("fproductBean.pcode", 4);
		tableDetil.setColumnExpandRatio("fproductBean.pname", 10);
		tableDetil.setColumnExpandRatio("pprice", 5);
		tableDetil.setColumnExpandRatio("ppriceafterppn", 5);
		tableDetil.setColumnExpandRatio("qty1", 2);
		tableDetil.setColumnExpandRatio("qty2", 2);
		tableDetil.setColumnExpandRatio("qty3", 2);
		tableDetil.setColumnExpandRatio("qty", 4);
		tableDetil.setColumnExpandRatio("disc1", 4);
		tableDetil.setColumnExpandRatio("disc2", 4);
		tableDetil.setColumnExpandRatio("subtotal", 5);
		tableDetil.setColumnExpandRatio("subtotalafterppn", 5);
		tableDetil.setColumnExpandRatio("subtotalafterdisc", 5);
		tableDetil.setColumnExpandRatio("subtotalafterdiscafterppn", 5);
				
	}
	
	public void setFormButtonAndTextState(){
		
		//KODE REFNO SELALU READ ONLY KARENA OTOMATIS		
		if (model.getOperationStatus().equals(EnumOperationStatus.OPEN.getStrCode())){
			tabSheet.setSelectedTab(panelUtamaDetil);
			tabSheet.getTab(panelUtamaList).setEnabled(true);
			
			btnNewForm.setEnabled(true);
			btnEditForm.setEnabled(true);
			btnDeleteForm.setEnabled(true);
			btnSearch.setEnabled(true);
			btnPosting.setEnabled(true);
			
			btnAddItem.setEnabled(false);
			btnEditItem.setEnabled(false);
			btnRemoveItem.setEnabled(false);
			
			btnSaveForm.setEnabled(false);
			btnCancelForm.setEnabled(false);
			
			tableDetil.setReadOnly(true);
			
			fieldNomor.setReadOnly(true);

			fieldAmount.setReadOnly(true);
			fieldAmountafterppn.setReadOnly(true);
			fieldDisc1.setReadOnly(true);
			fieldDisc1rp.setReadOnly(true);
			fieldDisc1rpafterppn.setReadOnly(true);
			fieldDisc2.setReadOnly(true);
			fieldDisc2rp.setReadOnly(true);
			fieldDisc2rpafterppn.setReadOnly(true);
			fieldDisc.setReadOnly(true);
			fieldDiscrp.setReadOnly(true);
			fieldDiscrpafterppn.setReadOnly(true);
			
			fieldPpnpercent.setReadOnly(true);
			fieldPpnrp.setReadOnly(true);
			fieldAmountafterdisc.setReadOnly(true);
			fieldAmountafterdiscafterppn.setReadOnly(true);
			
		} else if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
			tabSheet.setSelectedTab(panelUtamaDetil);
			tabSheet.getTab(panelUtamaList).setEnabled(false);
			btnNewForm.setEnabled(false);
			btnEditForm.setEnabled(false);
			btnDeleteForm.setEnabled(false);
			btnSearch.setEnabled(false);
			btnPosting.setEnabled(false);

			
			btnAddItem.setEnabled(true);
			btnEditItem.setEnabled(true);
			btnRemoveItem.setEnabled(true);

			btnSaveForm.setEnabled(true);
			btnCancelForm.setEnabled(true);
			
			tableDetil.setReadOnly(false);
			
			fieldNomor.setReadOnly(true);

			fieldAmount.setReadOnly(false);
			fieldAmountafterppn.setReadOnly(false);
			fieldDisc1.setReadOnly(false);
			fieldDisc1rp.setReadOnly(false);
			fieldDisc1rpafterppn.setReadOnly(false);
			fieldDisc2.setReadOnly(false);
			fieldDisc2rp.setReadOnly(false);
			fieldDisc2rpafterppn.setReadOnly(false);
			fieldDisc.setReadOnly(false);
			fieldDiscrp.setReadOnly(false);
			fieldDiscrpafterppn.setReadOnly(false);
			
			fieldPpnpercent.setReadOnly(false);
			fieldPpnrp.setReadOnly(false);
			fieldAmountafterdisc.setReadOnly(false);
			fieldAmountafterdiscafterppn.setReadOnly(false);
			
		}else if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){			
			tabSheet.setSelectedTab(panelUtamaDetil);
			tabSheet.getTab(panelUtamaList).setEnabled(false);

			btnNewForm.setEnabled(false);
			btnEditForm.setEnabled(false);
			btnDeleteForm.setEnabled(false);
			btnSearch.setEnabled(false);
			btnPosting.setEnabled(false);
			
			btnAddItem.setEnabled(true);
			btnEditItem.setEnabled(true);
			btnRemoveItem.setEnabled(true);
			
			btnSaveForm.setEnabled(true);
			btnCancelForm.setEnabled(true);
		
			tableDetil.setReadOnly(false);

			fieldNomor.setReadOnly(false);
			
			fieldAmount.setReadOnly(false);
			fieldAmountafterppn.setReadOnly(false);
			fieldDisc1.setReadOnly(false);
			fieldDisc1rp.setReadOnly(false);
			fieldDisc1rpafterppn.setReadOnly(false);
			fieldDisc2.setReadOnly(false);
			fieldDisc2rp.setReadOnly(false);
			fieldDisc2rpafterppn.setReadOnly(false);
			fieldDisc.setReadOnly(false);
			fieldDiscrp.setReadOnly(false);
			fieldDiscrpafterppn.setReadOnly(false);
			
			fieldPpnpercent.setReadOnly(false);
			fieldPpnrp.setReadOnly(false);
			fieldAmountafterdisc.setReadOnly(false);
			fieldAmountafterdiscafterppn.setReadOnly(false);
			
		}		
		
		if (! model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode()) && 
				! model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
			
			if (checkPosting.getValue()==true) {
				btnPosting.setEnabled(false);
				btnPostingBatal.setEnabled(true);
			}else {
				btnPosting.setEnabled(true);
				btnPostingBatal.setEnabled(false);			
			}		
		}
		
		checkEndofday.setReadOnly(true);
		checkPosting.setReadOnly(true);
		
	}
	
	private StockOpnameItemModel itemDetilModel;
	private StockOpnameItemView itemDetilView;
	private StockOpnameItemPresenter itemDetilPresenter;
	private Window windowForm = new Window();
	
	public void showWindowForm(){
		
		if (itemDetilModel==null){
			itemDetilModel = new StockOpnameItemModel();
			itemDetilModel.setItemHeader(model.getItemHeader());
	//		itemDetilModel = new SalesOrderItemModel(model.itemHeader);
			itemDetilModel.getBeanItemContainerProduct().addAll(model.getBeanItemContainerProduct().getItemIds());
		
			itemDetilView = new StockOpnameItemView(itemDetilModel);
			itemDetilPresenter = new StockOpnameItemPresenter(itemDetilModel, itemDetilView);			
			itemDetilView.setSizeFull();
			panelFormDetil.setContent(itemDetilView);
		}
		windowForm = new Window();
		windowForm.setModal(true);
		
		windowForm.center();
		
		windowForm.setWidth("950px");
		windowForm.setHeight("200px");
		windowForm.setClosable(true);	
		windowForm.setResizable(false);

		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSizeFull();
		content.addComponent(panelFormDetil);		
		windowForm.setContent(content);
		
		getUI().addWindow(windowForm);
		
	}
	public void closeWindowForm(){
		windowForm.close();
		
	}
	
	public void focustIdOrDesc(){
		if (fieldNomor.isEnabled()){
			fieldNomor
			.focus();
		} else {
			fieldInvoiceno.focus();		                    						
		}		
	}

	public StockOpnameModel getModel() {
		return model;
	}

	public StockOpnameHelper getHelper() {
		return helper;
	}

	public VerticalLayout getContent() {
		return content;
	}

	public Table getTableList() {
		return tableList;
	}

	public Table getTableDetil() {
		return tableDetil;
	}

	public TextField getFieldSearch1() {
		return fieldSearch1;
	}

	public TextField getFieldSearch2() {
		return fieldSearch2;
	}

	public TextField getFieldSearch3() {
		return fieldSearch3;
	}

	public ComboBox getComboSearch1() {
		return comboSearch1;
	}

	public ComboBox getComboSearch2() {
		return comboSearch2;
	}

	public TextField getFieldNomor() {
		return fieldNomor;
	}

	public TextField getFieldInvoiceno() {
		return fieldInvoiceno;
	}

	public ComboBox getComboTipeopname() {
		return comboTipeopname;
	}

	public ComboBox getComboWarehouse() {
		return comboWarehouse;
	}

	public DateField getDateFieldTrdate() {
		return dateFieldTrdate;
	}

	public DateField getDateFieldEntrydate() {
		return dateFieldEntrydate;
	}

	public Button getBtnSaveForm() {
		return btnSaveForm;
	}

	public Button getBtnCancelForm() {
		return btnCancelForm;
	}

	public Button getBtnNewForm() {
		return btnNewForm;
	}

	public Button getBtnEditForm() {
		return btnEditForm;
	}

	public Button getBtnDeleteForm() {
		return btnDeleteForm;
	}

	public Button getBtnAddItem() {
		return btnAddItem;
	}

	public Button getBtnEditItem() {
		return btnEditItem;
	}

	public Button getBtnRemoveItem() {
		return btnRemoveItem;
	}

	public Button getBtnSeparator1() {
		return btnSeparator1;
	}

	public Button getBtnSeparator2() {
		return btnSeparator2;
	}

	public TextField getFieldAmount() {
		return fieldAmount;
	}

	public TextField getFieldAmountafterppn() {
		return fieldAmountafterppn;
	}

	public TextField getFieldDisc1() {
		return fieldDisc1;
	}

	public TextField getFieldDisc1rp() {
		return fieldDisc1rp;
	}

	public TextField getFieldDisc1rpafterppn() {
		return fieldDisc1rpafterppn;
	}

	public TextField getFieldDisc2() {
		return fieldDisc2;
	}

	public TextField getFieldDisc2rp() {
		return fieldDisc2rp;
	}

	public TextField getFieldDisc2rpafterppn() {
		return fieldDisc2rpafterppn;
	}

	public TextField getFieldDisc() {
		return fieldDisc;
	}

	public TextField getFieldDiscrp() {
		return fieldDiscrp;
	}

	public TextField getFieldDiscrpafterppn() {
		return fieldDiscrpafterppn;
	}

	public TextField getFieldPpnpercent() {
		return fieldPpnpercent;
	}

	public TextField getFieldPpnrp() {
		return fieldPpnrp;
	}

	public TextField getFieldAmountafterdisc() {
		return fieldAmountafterdisc;
	}

	public TextField getFieldAmountafterdiscafterppn() {
		return fieldAmountafterdiscafterppn;
	}

	public TextField getFieldAmountpay() {
		return fieldAmountpay;
	}

	public TextField getFieldAmountpayfaterppn() {
		return fieldAmountpayfaterppn;
	}

	public CheckBox getCheckSaldo() {
		return checkSaldo;
	}

	public CheckBox getCheckEndofday() {
		return checkEndofday;
	}

	public CheckBox getCheckPosting() {
		return checkPosting;
	}

	public Button getBtnSearch() {
		return btnSearch;
	}

	public Button getBtnPosting() {
		return btnPosting;
	}

	public Button getBtnHelp() {
		return btnHelp;
	}

	public Button getBtnLapselisih() {
		return btnLapselisih;
	}

	public TabSheet getTabSheet() {
		return tabSheet;
	}

	public VerticalLayout getLayoutList() {
		return layoutList;
	}

	public VerticalLayout getLayoutDetil() {
		return layoutDetil;
	}

	public Panel getPanelUtamaList() {
		return panelUtamaList;
	}

	public Panel getPanelUtamaDetil() {
		return panelUtamaDetil;
	}

	public Panel getPanelTopDetil() {
		return panelTopDetil;
	}

	public Panel getPanelTabelDetil() {
		return panelTabelDetil;
	}

	public Panel getPanelFormDetil() {
		return panelFormDetil;
	}

	public StockOpnameItemModel getItemDetilModel() {
		return itemDetilModel;
	}

	public StockOpnameItemView getItemDetilView() {
		return itemDetilView;
	}

	public StockOpnameItemPresenter getItemDetilPresenter() {
		return itemDetilPresenter;
	}

	public Window getWindowForm() {
		return windowForm;
	}

	public void setModel(StockOpnameModel model) {
		this.model = model;
	}

	public void setHelper(StockOpnameHelper helper) {
		this.helper = helper;
	}

	public void setContent(VerticalLayout content) {
		this.content = content;
	}

	public void setTableList(Table tableList) {
		this.tableList = tableList;
	}

	public void setTableDetil(Table tableDetil) {
		this.tableDetil = tableDetil;
	}

	public void setFieldSearch1(TextField fieldSearch1) {
		this.fieldSearch1 = fieldSearch1;
	}

	public void setFieldSearch2(TextField fieldSearch2) {
		this.fieldSearch2 = fieldSearch2;
	}

	public void setFieldSearch3(TextField fieldSearch3) {
		this.fieldSearch3 = fieldSearch3;
	}

	public void setComboSearch1(ComboBox comboSearch1) {
		this.comboSearch1 = comboSearch1;
	}

	public void setComboSearch2(ComboBox comboSearch2) {
		this.comboSearch2 = comboSearch2;
	}

	public void setFieldNomor(TextField fieldNomor) {
		this.fieldNomor = fieldNomor;
	}

	public void setFieldInvoiceno(TextField fieldInvoiceno) {
		this.fieldInvoiceno = fieldInvoiceno;
	}

	public void setComboTipeopname(ComboBox comboTipeopname) {
		this.comboTipeopname = comboTipeopname;
	}

	public void setComboWarehouse(ComboBox comboWarehouse) {
		this.comboWarehouse = comboWarehouse;
	}

	public void setDateFieldTrdate(DateField dateFieldTrdate) {
		this.dateFieldTrdate = dateFieldTrdate;
	}

	public void setDateFieldEntrydate(DateField dateFieldEntrydate) {
		this.dateFieldEntrydate = dateFieldEntrydate;
	}

	public void setBtnSaveForm(Button btnSaveForm) {
		this.btnSaveForm = btnSaveForm;
	}

	public void setBtnCancelForm(Button btnCancelForm) {
		this.btnCancelForm = btnCancelForm;
	}

	public void setBtnNewForm(Button btnNewForm) {
		this.btnNewForm = btnNewForm;
	}

	public void setBtnEditForm(Button btnEditForm) {
		this.btnEditForm = btnEditForm;
	}

	public void setBtnDeleteForm(Button btnDeleteForm) {
		this.btnDeleteForm = btnDeleteForm;
	}

	public void setBtnAddItem(Button btnAddItem) {
		this.btnAddItem = btnAddItem;
	}

	public void setBtnEditItem(Button btnEditItem) {
		this.btnEditItem = btnEditItem;
	}

	public void setBtnRemoveItem(Button btnRemoveItem) {
		this.btnRemoveItem = btnRemoveItem;
	}

	public void setBtnSeparator1(Button btnSeparator1) {
		this.btnSeparator1 = btnSeparator1;
	}

	public void setBtnSeparator2(Button btnSeparator2) {
		this.btnSeparator2 = btnSeparator2;
	}

	public void setFieldAmount(TextField fieldAmount) {
		this.fieldAmount = fieldAmount;
	}

	public void setFieldAmountafterppn(TextField fieldAmountafterppn) {
		this.fieldAmountafterppn = fieldAmountafterppn;
	}

	public void setFieldDisc1(TextField fieldDisc1) {
		this.fieldDisc1 = fieldDisc1;
	}

	public void setFieldDisc1rp(TextField fieldDisc1rp) {
		this.fieldDisc1rp = fieldDisc1rp;
	}

	public void setFieldDisc1rpafterppn(TextField fieldDisc1rpafterppn) {
		this.fieldDisc1rpafterppn = fieldDisc1rpafterppn;
	}

	public void setFieldDisc2(TextField fieldDisc2) {
		this.fieldDisc2 = fieldDisc2;
	}

	public void setFieldDisc2rp(TextField fieldDisc2rp) {
		this.fieldDisc2rp = fieldDisc2rp;
	}

	public void setFieldDisc2rpafterppn(TextField fieldDisc2rpafterppn) {
		this.fieldDisc2rpafterppn = fieldDisc2rpafterppn;
	}

	public void setFieldDisc(TextField fieldDisc) {
		this.fieldDisc = fieldDisc;
	}

	public void setFieldDiscrp(TextField fieldDiscrp) {
		this.fieldDiscrp = fieldDiscrp;
	}

	public void setFieldDiscrpafterppn(TextField fieldDiscrpafterppn) {
		this.fieldDiscrpafterppn = fieldDiscrpafterppn;
	}

	public void setFieldPpnpercent(TextField fieldPpnpercent) {
		this.fieldPpnpercent = fieldPpnpercent;
	}

	public void setFieldPpnrp(TextField fieldPpnrp) {
		this.fieldPpnrp = fieldPpnrp;
	}

	public void setFieldAmountafterdisc(TextField fieldAmountafterdisc) {
		this.fieldAmountafterdisc = fieldAmountafterdisc;
	}

	public void setFieldAmountafterdiscafterppn(
			TextField fieldAmountafterdiscafterppn) {
		this.fieldAmountafterdiscafterppn = fieldAmountafterdiscafterppn;
	}

	public void setFieldAmountpay(TextField fieldAmountpay) {
		this.fieldAmountpay = fieldAmountpay;
	}

	public void setFieldAmountpayfaterppn(TextField fieldAmountpayfaterppn) {
		this.fieldAmountpayfaterppn = fieldAmountpayfaterppn;
	}

	public void setCheckSaldo(CheckBox checkSaldo) {
		this.checkSaldo = checkSaldo;
	}

	public void setCheckEndofday(CheckBox checkEndofday) {
		this.checkEndofday = checkEndofday;
	}

	public void setCheckPosting(CheckBox checkPosting) {
		this.checkPosting = checkPosting;
	}

	public void setBtnSearch(Button btnSearch) {
		this.btnSearch = btnSearch;
	}

	public void setBtnPosting(Button btnPosting) {
		this.btnPosting = btnPosting;
	}

	public void setBtnHelp(Button btnHelp) {
		this.btnHelp = btnHelp;
	}

	public void setBtnLapselisih(Button btnLapselisih) {
		this.btnLapselisih = btnLapselisih;
	}

	public void setTabSheet(TabSheet tabSheet) {
		this.tabSheet = tabSheet;
	}

	public void setLayoutList(VerticalLayout layoutList) {
		this.layoutList = layoutList;
	}

	public void setLayoutDetil(VerticalLayout layoutDetil) {
		this.layoutDetil = layoutDetil;
	}

	public void setPanelUtamaList(Panel panelUtamaList) {
		this.panelUtamaList = panelUtamaList;
	}

	public void setPanelUtamaDetil(Panel panelUtamaDetil) {
		this.panelUtamaDetil = panelUtamaDetil;
	}

	public void setPanelTopDetil(Panel panelTopDetil) {
		this.panelTopDetil = panelTopDetil;
	}

	public void setPanelTabelDetil(Panel panelTabelDetil) {
		this.panelTabelDetil = panelTabelDetil;
	}

	public void setPanelFormDetil(Panel panelFormDetil) {
		this.panelFormDetil = panelFormDetil;
	}

	public void setItemDetilModel(StockOpnameItemModel itemDetilModel) {
		this.itemDetilModel = itemDetilModel;
	}

	public void setItemDetilView(StockOpnameItemView itemDetilView) {
		this.itemDetilView = itemDetilView;
	}

	public void setItemDetilPresenter(StockOpnameItemPresenter itemDetilPresenter) {
		this.itemDetilPresenter = itemDetilPresenter;
	}

	public void setWindowForm(Window windowForm) {
		this.windowForm = windowForm;
	}

	public Button getBtnPostingBatal() {
		return btnPostingBatal;
	}

	public void setBtnPostingBatal(Button btnPostingBatal) {
		this.btnPostingBatal = btnPostingBatal;
	}


	
}
