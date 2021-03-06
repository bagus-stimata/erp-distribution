package org.erp.distribution.salesorder.salesorder.sales;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.erp.distribution.TestIcon;
import org.erp.distribution.model.FtSalesd;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.salesorder.salesorder.windowitem.SalesOrderItemModel;
import org.erp.distribution.salesorder.salesorder.windowitem.SalesOrderItemPresenter;
import org.erp.distribution.salesorder.salesorder.windowitem.SalesOrderItemView;

import com.vaadin.data.Property;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.themes.ChameleonTheme;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.Runo;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class SalesOrderView extends CustomComponent{
	private SalesOrderModel model;

	SalesOrderHelper helper;
	
	private VerticalLayout content = new VerticalLayout();
	
	private Table tableList;
	private Table tableDetil;
	//LIST
	private TextField fieldSearch1 = new TextField("NO. ORDER");
	private TextField fieldSearch2 = new TextField("NO. INVOICE");
	private TextField fieldSearch3 = new TextField("NO");
	private ComboBox comboSearch1 = new ComboBox("Salesman");
	private ComboBox comboSearch2 = new ComboBox("Customer");
	private CheckBox checkSearch1 = new CheckBox("BELUM TERBIT INVOICE SAJA");
	
	//DETIL
	private TextField fieldOrderno = new TextField("NO. ORDER");
	private TextField fieldInvoiceno= new TextField("NO. INVOICE");
	private ComboBox comboSalesman = new ComboBox("SALESMAN");
	private ComboBox comboTipeJual = new ComboBox("TO/C");
	private ComboBox comboCustomer = new ComboBox("CUSTOMER");
	private ComboBox comboWarehouse = new ComboBox("WAREHOUSE");
	
	private DateField dateFieldOrderdate = new DateField("ORDER. DATE");
	private DateField dateFieldInvoicedate = new DateField("INV. DATE");
	
	private ComboBox comboTunaikredit = new ComboBox("T/K");
	private ComboBox comboTop = new ComboBox("TOP");
	private DateField dateFieldDuedate = new DateField("DUE. DATE");
	
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
	private Button btnSeparator3= new Button("*");

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
	
	//Additional Component
	private Button btnSearch = new Button("Search & Reload");
	private Button btnPrint = new Button("Print & Invoice");
	private Button btnPrintRetail = new Button("Faktur Retail");
	private Button btnHelp = new Button("Help");

	
	//LAYOUT
	private TabSheet tabSheet = new TabSheet();
	private VerticalLayout layoutList = new VerticalLayout();
	private VerticalLayout layoutDetil = new VerticalLayout();
	
	//Panel
	private Panel panelTop = new Panel();
	private Panel panelUtamaList = new Panel();
	private Panel panelUtamaDetil = new Panel();
	private Panel panelTopDetil = new Panel();
	private Panel panelTabelDetil = new Panel();
	private Panel panelFormDetil = new Panel();
	
	
    TestIcon testIcon = new TestIcon(60);
    
	public SalesOrderView(SalesOrderModel model){
		this.model = model;
		helper = new SalesOrderHelper(model, this);
		
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
		        
		        try{
			        if (property.getType()==Boolean.class){
			        	if ((Boolean) property.getValue()==true) {
			        		return "FG";
			        	} else {
			        		return "";
			        	}
			        }
		        } catch(Exception ex){}
		        
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
//		tableDetil.setHeight("700px");
		
		fieldOrderno.setNullRepresentation("");
		fieldInvoiceno.setNullRepresentation("");
		
		fieldOrderno.setWidth("90px");
		fieldInvoiceno.setWidth("90px");
		comboSalesman.setWidth("100px");
		comboSalesman.setFilteringMode(FilteringMode.CONTAINS);
		comboCustomer.setWidth("150px");
		comboCustomer.setFilteringMode(FilteringMode.CONTAINS);
		comboWarehouse.setWidth("100px");
		comboWarehouse.setFilteringMode(FilteringMode.CONTAINS);
		comboTipeJual.setWidth("50px");
		comboTipeJual.setFilteringMode(FilteringMode.CONTAINS);
		
		comboTunaikredit.setWidth("60px");
		comboTunaikredit.setFilteringMode(FilteringMode.CONTAINS);
		comboTunaikredit.addItem("T");
		comboTunaikredit.setItemCaption("T", "Tunai");
		comboTunaikredit.addItem("K");
		comboTunaikredit.setItemCaption("K", "Kredit");
		comboTunaikredit.setNullSelectionAllowed(false);
		comboTunaikredit.setRequired(true);
		
		comboTipeJual.setWidth("50px");
		comboTipeJual.setFilteringMode(FilteringMode.CONTAINS);
		comboTipeJual.addItem("TO");
		comboTipeJual.setItemCaption("TO", "TO");
		comboTipeJual.addItem("C");
		comboTipeJual.setItemCaption("C", "C");		comboTipeJual.addItem("TF");
		comboTipeJual.setItemCaption("TF", "TF");
		comboTipeJual.setNullSelectionAllowed(false);
		comboTipeJual.setRequired(true);
		
		comboTop.setWidth("40px");
		comboTop.setFilteringMode(FilteringMode.CONTAINS);
		for (int i=0;i<=30;i++){
			comboTop.addItem(i);
		}
		comboTop.setNullSelectionAllowed(false);
		
		dateFieldOrderdate.setDateFormat("dd/MM/yyyy");
		dateFieldInvoicedate.setDateFormat("dd/MM/yyyy");
		dateFieldDuedate.setDateFormat("dd/MM/yyyy");
		dateFieldOrderdate.setWidth("100px");
		dateFieldInvoicedate.setWidth("100px");
		dateFieldDuedate.setWidth("100px");
		
		btnSearch.setIcon(new ThemeResource("../images/navigation/12x12/Find.png"));
		btnNewForm.setIcon(new ThemeResource("../images/navigation/12x12/Create.png"));
		btnEditForm.setIcon(new ThemeResource("../images/navigation/12x12/Pencil.png"));
		btnDeleteForm.setIcon(new ThemeResource("../images/navigation/12x12/Erase.png"));
		btnPrint.setIcon(new ThemeResource("../images/navigation/12x12/Print.png"));
		btnPrintRetail.setIcon(FontAwesome.PRINT);
		
		btnSaveForm.setIcon(new ThemeResource("../images/navigation/12x12/Save.png"));
		btnCancelForm.setIcon(new ThemeResource("../images/navigation/12x12/Undo.png"));

		btnAddItem.setIcon(new ThemeResource("../images/navigation/12x12/Create.png"));
		btnEditForm.setIcon(new ThemeResource("../images/navigation/12x12/Pencil.png"));
		btnRemoveItem.setIcon(new ThemeResource("../images/navigation/12x12/Erase.png"));
		
		tableList.setSelectable(true);
		tableList.setImmediate(true);
		tableList.setBuffered(false);
		tableList.setFooterVisible(true);
		
		tableDetil.setSelectable(true);
		tableDetil.setImmediate(true);
		tableDetil.setBuffered(false);
		tableDetil.setFooterVisible(true);
		
		//VALIDATOR
		fieldOrderno.setRequired(true);
		comboSalesman.setRequired(true);
		comboCustomer.setRequired(true);
		comboWarehouse.setRequired(true);
		dateFieldInvoicedate.setRequired(true);
//		fieldDescription.setRequired(true);
		
		btnSeparator1.setEnabled(false);
		btnSeparator2.setEnabled(false);
		btnSeparator3.setEnabled(false);
		
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

		comboSalesman.setContainerDataSource(model.getBeanItemContainerSalesman());
		comboSalesman.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboSalesman.setFilteringMode(FilteringMode.CONTAINS);
		comboSalesman.setNullSelectionAllowed(false);
		
		comboCustomer.setContainerDataSource(model.getBeanItemContainerCustomer());
		comboCustomer.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboCustomer.setFilteringMode(FilteringMode.CONTAINS);
		comboCustomer.setNullSelectionAllowed(false);

		comboWarehouse.setContainerDataSource(model.getBeanItemContainerWarehouse());
		comboWarehouse.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboWarehouse.setFilteringMode(FilteringMode.CONTAINS);
		comboWarehouse.setNullSelectionAllowed(false);
		
		comboSearch1.setContainerDataSource(model.getBeanItemContainerSalesman());
		comboSearch1.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboSearch1.setFilteringMode(FilteringMode.CONTAINS);
		comboSearch1.setNullSelectionAllowed(true);
		
		comboSearch2.setContainerDataSource(model.getBeanItemContainerCustomer());
		comboSearch2.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboSearch2.setFilteringMode(FilteringMode.CONTAINS);
		comboSearch2.setNullSelectionAllowed(true);
		
		
		
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
		
		panelTop.setContent(layoutTopList1);
		layoutTopList.addComponent(panelTop);
		
		layoutTopList1.addComponent(fieldSearch1);
		layoutTopList1.addComponent(fieldSearch2);
		layoutTopList1.addComponent(comboSearch1);
		layoutTopList1.addComponent(comboSearch2);
		layoutTopList1.addComponent(btnSearch);
		layoutTopList1.addComponent(checkSearch1);
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
		
		layoutTopDetil1.addComponent(fieldOrderno);
		layoutTopDetil1.addComponent(fieldInvoiceno);
		layoutTopDetil1.addComponent(comboTipeJual);
		layoutTopDetil1.addComponent(comboSalesman);
		layoutTopDetil1.addComponent(comboCustomer);
		layoutTopDetil1.addComponent(dateFieldOrderdate);
		layoutTopDetil1.addComponent(dateFieldInvoicedate);
		layoutTopDetil1.addComponent(comboTunaikredit);
		layoutTopDetil1.addComponent(comboTop);
		layoutTopDetil1.addComponent(dateFieldDuedate);
		layoutTopDetil1.addComponent(comboWarehouse);
		layoutTopDetil1.addComponent(checkSaldo);
		layoutTopDetil1.addComponent(checkEndofday);
		
		layoutTopDetil2.addComponent(btnNewForm);
		layoutTopDetil2.addComponent(btnEditForm);
		layoutTopDetil2.addComponent(btnDeleteForm);
		layoutTopDetil2.addComponent(btnPrint);
		layoutTopDetil2.addComponent(btnSeparator1);
		layoutTopDetil2.addComponent(btnPrintRetail);
	
		layoutTopDetil2.addComponent(btnSeparator2);
		
		layoutTopDetil2.addComponent(btnSaveForm);
		layoutTopDetil2.addComponent(btnCancelForm);

		layoutTopDetil2.addComponent(btnSeparator3);
		
		layoutTopDetil2.addComponent(btnAddItem);
		layoutTopDetil2.addComponent(btnEditItem);
		layoutTopDetil2.addComponent(btnRemoveItem);
		
		layoutTopDetil2.setComponentAlignment(btnNewForm, Alignment.BOTTOM_CENTER);
		layoutTopDetil2.setComponentAlignment(btnEditForm, Alignment.BOTTOM_CENTER);
		layoutTopDetil2.setComponentAlignment(btnDeleteForm, Alignment.BOTTOM_CENTER);
		layoutTopDetil2.setComponentAlignment(btnPrint, Alignment.BOTTOM_CENTER);
		layoutTopDetil2.setComponentAlignment(btnPrintRetail, Alignment.BOTTOM_CENTER);
		
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

		tabSheet.addTab(panelUtamaList, "LIST", FontAwesome.LIST);
		tabSheet.addTab(panelUtamaDetil, "DETIL", FontAwesome.EDIT);
		
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
			btnPrint.addStyleName("small");
			btnPrintRetail.addStyleName("small");
			
			btnSaveForm.addStyleName("small");
			btnCancelForm.addStyleName("small");

			btnAddItem.addStyleName("small");
			btnEditItem.addStyleName("small");
			btnRemoveItem.addStyleName("small");
			
			btnSeparator1.addStyleName("small");
			btnSeparator2.addStyleName("small");
			btnSeparator3.addStyleName("small");
		
		
//		}
		
//		tabSheet.addStyleName("framed compact-tabbar small");
		tabSheet.addStyleName(Reindeer.TABSHEET_BORDERLESS);
		tabSheet.addStyleName(Reindeer.TABSHEET_SMALL);
		
	}
	
	public void setDisplayList(){
		//::TABLE
		tableList.setContainerDataSource(model.getBeanItemContainerHeader());
		setTablePropertiesList();		
		setDisplayTableFooterList();		
		bindAndBuildFieldGroupComponentList();
		
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
		
		model.getBinderHeader().bind(fieldOrderno, "orderno");
		model.getBinderHeader().bind(fieldInvoiceno, "invoiceno");
		model.getBinderHeader().bind(comboTipeJual, "tipejual");
		model.getBinderHeader().bind(comboSalesman, "fsalesmanBean");
		model.getBinderHeader().bind(comboCustomer, "fcustomerBean");
		model.getBinderHeader().bind(dateFieldInvoicedate, "invoicedate");
		model.getBinderHeader().bind(dateFieldOrderdate, "orderdate");
		model.getBinderHeader().bind(comboWarehouse, "fwarehouseBean");

		model.getBinderHeader().bind(comboTunaikredit, "tunaikredit");
		model.getBinderHeader().bind(comboTop, "top");
		model.getBinderHeader().bind(dateFieldDuedate, "duedate");
		
		model.getBinderHeader().bind(checkEndofday, "endofday");
		model.getBinderHeader().bind(checkSaldo, "saldo");
		
		model.getBinderHeader().bind(fieldAmount, "amount");
		model.getBinderHeader().bind(fieldAmountafterppn, "amountafterppn");
		model.getBinderHeader().bind(fieldDisc1, "disc1");
		model.getBinderHeader().bind(fieldDisc1rp, "disc1rp");
		model.getBinderHeader().bind(fieldDisc1rpafterppn, "disc1rpafterppn");
		model.getBinderHeader().bind(fieldDisc2, "disc2");
		model.getBinderHeader().bind(fieldDisc2rp, "disc2rp");
		model.getBinderHeader().bind(fieldDisc2rpafterppn, "disc2rpafterppn");
		model.getBinderHeader().bind(fieldDisc, "disc");
		model.getBinderHeader().bind(fieldDiscrp, "discrp");
		model.getBinderHeader().bind(fieldPpnpercent, "ppnpercent");
		model.getBinderHeader().bind(fieldPpnrp, "ppnrp");
		model.getBinderHeader().bind(fieldDiscrpafterppn, "discrpafterppn");
		model.getBinderHeader().bind(fieldAmountafterdisc, "amountafterdisc");
		model.getBinderHeader().bind(fieldAmountafterdiscafterppn, "amountafterdiscafterppn");
		
	}
	
	public void fillComponentDetilItem(){
		//1. HAPUS CONTAINER DETIL::TABLE
		model.getBeanItemContainerDetil().removeAllItems();
		//2. AMBIL DETIL DENGAN MENGGUNAKAN KODE HEADER
		List<FtSalesd> list = new ArrayList<FtSalesd>();
		list = model.getFtSalesdJpaService().findAllDetilByRefno(model.getItemHeader().getRefno());
		//3. PERBAIKI CONTENT TRANSIENT
		List<FtSalesd> newList = new ArrayList<FtSalesd>();
		newList = helper.updateAndCalculateItemDetilFromList(list);
		//4. MASUKKAN KE DALAM LIST
		model.getBeanItemContainerDetil().addAll(newList);
		tableDetil.refreshRowCache();
	}
	
	public void setVisibleTablePropertiesList(Object... tablePropertyIds) {
		tableList.setVisibleColumns(tablePropertyIds);		
	}
	
	public void setTablePropertiesList(){

		setVisibleTablePropertiesList("orderno", "invoiceno", "orderdate", "invoicedate","fsalesmanBean.spcode", "fsalesmanBean.spname", 
				"fcustomerBean.custno", "fcustomerBean.custname", "fwarehouseBean.id", 
				"tunaikredit", "top", "duedate",
				"fwarehouseBean.description", 
				"amount", "amountafterdisc", "amountafterdiscafterppn", "amountpay");
		
		tableList.setColumnCollapsingAllowed(true);
		try{
			tableList.setColumnCollapsed("amount", true);
			tableList.setColumnCollapsed("fsalesmanBean.spcode", true);
			tableList.setColumnCollapsed("fcustomerBean.custno", true);
			tableList.setColumnCollapsed("fwarehouseBean.id", true);
			
		} catch(Exception ex){}
		
		//ALIGNMENT
//		tableList.setColumnAlignment("nopo", Align.RIGHT);
//		tableList.setColumnAlignment("invoiceno", Align.RIGHT);
		tableList.setColumnAlignment("invoicedate", Align.CENTER);
		tableList.setColumnAlignment("orderdate", Align.CENTER);
		tableList.setColumnAlignment("amount", Align.RIGHT);
		tableList.setColumnAlignment("amountafterdisc", Align.RIGHT);
		tableList.setColumnAlignment("amountafterdiscafterppn", Align.RIGHT);
		tableList.setColumnAlignment("amountpay", Align.RIGHT);
		
		//set header
		tableList.setColumnHeader("orderno", "NO. ORDER");
		tableList.setColumnHeader("fsalesmanBean.custname", "SALESMAN");
		tableList.setColumnHeader("fcustomerBean.custname", "CUSTOMER");
		tableList.setColumnHeader("fwarehouseBean.custname", "WAREHOUSE");
		tableList.setColumnHeader("invoiceno", "INV. NO");
		tableList.setColumnHeader("invoicedate", "INV. DATE");
		tableList.setColumnHeader("orderdate", "PO. DATE");
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

		setVisibleTablePropertiesDetil("nourut", "fproductBean.pcode", "fproductBean.pname", "fproductBean.packaging", 
				"sprice", "spriceafterppn", "id.freegood", "qty1", "qty2", "qty3", "qty", "subtotal", "subtotalafterppn",  
				"disc1", "disc1rp", "disc1rpafterppn", "disc2", "disc2rp", "disc2rpafterppn", 
				"subtotalafterdisc", "subtotalafterdiscafterppn");
		
		tableDetil.setColumnCollapsingAllowed(true);
		try{
			tableDetil.setColumnCollapsed("sprice", true);
			
//			tableDetil.setColumnCollapsed("qty1", true);
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
		tableDetil.setColumnAlignment("sprice", Align.RIGHT);
		tableDetil.setColumnAlignment("spriceafterppn", Align.RIGHT);
		tableDetil.setColumnAlignment("id.freegood", Align.CENTER);
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
		tableDetil.setColumnHeader("sprice", "HRG JUAL");
		tableDetil.setColumnHeader("id.freegood", "FG");
		tableDetil.setColumnHeader("spriceafterppn", "JUAL+PPN");
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
		tableDetil.setColumnExpandRatio("sprice", 5);
		tableDetil.setColumnExpandRatio("spriceafterppn", 5);
		tableDetil.setColumnExpandRatio("id.freegood", 1);
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
			btnPrint.setEnabled(true);
			btnPrintRetail.setEnabled(true);
			
			btnAddItem.setEnabled(false);
			btnEditItem.setEnabled(false);
			btnRemoveItem.setEnabled(false);
			
			btnSaveForm.setEnabled(false);
			btnCancelForm.setEnabled(false);
			
			tableDetil.setReadOnly(true);
			
			fieldOrderno.setReadOnly(true);
			fieldInvoiceno.setReadOnly(true);

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
			btnPrint.setEnabled(false);
			btnPrintRetail.setEnabled(false);

			
			btnAddItem.setEnabled(true);
			btnEditItem.setEnabled(true);
			btnRemoveItem.setEnabled(true);

			btnSaveForm.setEnabled(true);
			btnCancelForm.setEnabled(true);
			
			tableDetil.setReadOnly(false);
			
			fieldOrderno.setReadOnly(true);
			fieldInvoiceno.setReadOnly(true);

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
			btnPrint.setEnabled(false);
			btnPrintRetail.setEnabled(false);
			
			btnAddItem.setEnabled(true);
			btnEditItem.setEnabled(true);
			btnRemoveItem.setEnabled(true);
			
			btnSaveForm.setEnabled(true);
			btnCancelForm.setEnabled(true);
		
			tableDetil.setReadOnly(false);

			fieldOrderno.setReadOnly(false);
			fieldInvoiceno.setReadOnly(false);
			
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
		
		checkEndofday.setReadOnly(true);
		
	}
	
	private SalesOrderItemModel itemDetilModel;
	private SalesOrderItemView itemDetilView;
	private SalesOrderItemPresenter itemDetilPresenter;
	private Window windowForm = new Window();

	
	public void showWindowForm(){

		if (itemDetilModel==null){
			itemDetilModel = new SalesOrderItemModel();
			itemDetilModel.setItemHeader(model.getItemHeader());
			//OKE BOS
	//		itemDetilModel = new SalesOrderItemModel(model.itemHeader);

			itemDetilModel.getBeanItemContainerProduct().addAll(model.getBeanItemContainerProduct().getItemIds());
			
			itemDetilView = new SalesOrderItemView(itemDetilModel);
			itemDetilPresenter = new SalesOrderItemPresenter(itemDetilModel, itemDetilView);
			itemDetilView.setSizeFull();
			panelFormDetil.setContent(itemDetilView);
			
		}
		
		windowForm = new Window();
		windowForm.setModal(true);
		
		windowForm.center();
		
		windowForm.setWidth("1000px");
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
		if (fieldOrderno.isEnabled()){
			fieldOrderno.focus();
		} else {
			fieldInvoiceno.focus();		                    						
		}		
	}

	public SalesOrderModel getModel() {
		return model;
	}

	public SalesOrderHelper getHelper() {
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

	public TextField getFieldOrderno() {
		return fieldOrderno;
	}

	public TextField getFieldInvoiceno() {
		return fieldInvoiceno;
	}

	public ComboBox getComboSalesman() {
		return comboSalesman;
	}

	public ComboBox getComboCustomer() {
		return comboCustomer;
	}

	public ComboBox getComboWarehouse() {
		return comboWarehouse;
	}

	public DateField getDateFieldOrderdate() {
		return dateFieldOrderdate;
	}

	public DateField getDateFieldInvoicedate() {
		return dateFieldInvoicedate;
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

	public Button getBtnSearch() {
		return btnSearch;
	}

	public Button getBtnPrint() {
		return btnPrint;
	}

	public Button getBtnHelp() {
		return btnHelp;
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

	public SalesOrderItemModel getItemDetilModel() {
		return itemDetilModel;
	}

	public SalesOrderItemView getItemDetilView() {
		return itemDetilView;
	}

	public SalesOrderItemPresenter getItemDetilPresenter() {
		return itemDetilPresenter;
	}

	public Window getWindowForm() {
		return windowForm;
	}

	public void setModel(SalesOrderModel model) {
		this.model = model;
	}

	public void setHelper(SalesOrderHelper helper) {
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

	public void setFieldOrderno(TextField fieldOrderno) {
		this.fieldOrderno = fieldOrderno;
	}

	public void setFieldInvoiceno(TextField fieldInvoiceno) {
		this.fieldInvoiceno = fieldInvoiceno;
	}

	public void setComboSalesman(ComboBox comboSalesman) {
		this.comboSalesman = comboSalesman;
	}

	public void setComboCustomer(ComboBox comboCustomer) {
		this.comboCustomer = comboCustomer;
	}

	public void setComboWarehouse(ComboBox comboWarehouse) {
		this.comboWarehouse = comboWarehouse;
	}

	public void setDateFieldOrderdate(DateField dateFieldOrderdate) {
		this.dateFieldOrderdate = dateFieldOrderdate;
	}

	public void setDateFieldInvoicedate(DateField dateFieldInvoicedate) {
		this.dateFieldInvoicedate = dateFieldInvoicedate;
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

	public void setBtnSearch(Button btnSearch) {
		this.btnSearch = btnSearch;
	}

	public void setBtnPrint(Button btnPrint) {
		this.btnPrint = btnPrint;
	}

	public void setBtnHelp(Button btnHelp) {
		this.btnHelp = btnHelp;
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

	public void setItemDetilModel(SalesOrderItemModel itemDetilModel) {
		this.itemDetilModel = itemDetilModel;
	}

	public void setItemDetilView(SalesOrderItemView itemDetilView) {
		this.itemDetilView = itemDetilView;
	}

	public void setItemDetilPresenter(SalesOrderItemPresenter itemDetilPresenter) {
		this.itemDetilPresenter = itemDetilPresenter;
	}

	public void setWindowForm(Window windowForm) {
		this.windowForm = windowForm;
	}

	public CheckBox getCheckSearch1() {
		return checkSearch1;
	}

	public ComboBox getComboTunaikredit() {
		return comboTunaikredit;
	}

	public ComboBox getComboTop() {
		return comboTop;
	}

	public DateField getDateFieldDuedate() {
		return dateFieldDuedate;
	}

	public void setCheckSearch1(CheckBox checkSearch1) {
		this.checkSearch1 = checkSearch1;
	}

	public void setComboTunaikredit(ComboBox comboTunaikredit) {
		this.comboTunaikredit = comboTunaikredit;
	}

	public void setComboTop(ComboBox comboTop) {
		this.comboTop = comboTop;
	}

	public void setDateFieldDuedate(DateField dateFieldDuedate) {
		this.dateFieldDuedate = dateFieldDuedate;
	}

	public ComboBox getComboTipeJual() {
		return comboTipeJual;
	}

	public Button getBtnSeparator3() {
		return btnSeparator3;
	}

	public Button getBtnPrintRetail() {
		return btnPrintRetail;
	}

	public void setComboTipeJual(ComboBox comboTipeJual) {
		this.comboTipeJual = comboTipeJual;
	}

	public void setBtnSeparator3(Button btnSeparator3) {
		this.btnSeparator3 = btnSeparator3;
	}

	public void setBtnPrintRetail(Button btnPrintRetail) {
		this.btnPrintRetail = btnPrintRetail;
	}

	public Panel getPanelTop() {
		return panelTop;
	}

	public void setPanelTop(Panel panelTop) {
		this.panelTop = panelTop;
	}


	
}
