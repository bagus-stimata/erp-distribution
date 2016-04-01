package org.erp.distribution.master.salesman;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.erp.distribution.kontrolstok.stockopname.windowitem.StockOpnameItemModel;
import org.erp.distribution.kontrolstok.stockopname.windowitem.StockOpnameItemPresenter;
import org.erp.distribution.kontrolstok.stockopname.windowitem.StockOpnameItemView;
import org.erp.distribution.master.salesman.areacovereditem.AreaCoveredItemModel;
import org.erp.distribution.master.salesman.areacovereditem.AreaCoveredItemPresenter;
import org.erp.distribution.master.salesman.areacovereditem.AreaCoveredItemView;
import org.erp.distribution.master.salesman.vendorcovereditem.VendorCoveredItemModel;
import org.erp.distribution.master.salesman.vendorcovereditem.VendorCoveredItemPresenter;
import org.erp.distribution.master.salesman.vendorcovereditem.VendorCoveredItemView;
import org.erp.distribution.model.modelenum.EnumOperationStatus;

import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.themes.Reindeer;

public class SalesmanView extends CustomComponent{	
	private SalesmanModel model;

	private VerticalLayout content = new VerticalLayout();
	
	private TabSheet tabSheet = new TabSheet();
	
	private Table table;
	
	private TextField fieldId = new TextField("ID");
	private ComboBox comboGrup = new ComboBox("TIPE JUAL");
	private TextField fieldDescription= new TextField("SALESMAN");
	private TextField fieldReportDesc= new TextField("REPORT DESC");
	private CheckBox checkStatusActive = new CheckBox("AKTIF", false);
	
	//TAMBAHAN	
	private ComboBox comboSalestype = new ComboBox("TIPE JUAL");
	private TextField fieldAddress1 = new TextField("ALAMAT");
	private TextField fieldCity1 = new TextField("KOTA");
	private TextField fieldState1 = new TextField("PROPINSI");
	private TextField fieldPhone = new TextField("TELP");
	private TextField fieldMobile = new TextField("HP");
	private TextField fieldEmail = new TextField("EMAIL");
	private ComboBox comboReligion = new ComboBox("AGAMA");

	private TextField fieldBornplace = new TextField("TEMPAT LAHIR");
	
	private DateField fieldJoindate = new DateField("JOIN DATE");
	private DateField fieldLasttrans = new DateField("LAST TRANS");
	private DateField fieldBorndate = new DateField("TGL LAHIR");
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel");
	private Button btnNewForm= new Button("New");
	private Button btnEditForm= new Button("Edit");
	private Button btnDeleteForm= new Button("Delete");

	//Additional Component
	private ComboBox comboSearchByGrup = new ComboBox("GRUP");
	private TextField fieldSearchById = new TextField("ID");
	private TextField fieldSearchByDesc = new TextField("SALESMAN");
	private Button btnSearch = new Button("Search & Reload");
	private Button btnPrint = new Button("Print");
	private Button btnHelp = new Button("Help");

	//##VENDOR COVERED##
	CheckBox checkVendorCovered = new CheckBox("Hanya Boleh Transaksi pada Supplier yang di Cover?");
	Table tableVendorCoverDetail = new Table();	
	Button btnAddVendor = new Button("Add");
	Button btnRemoveVendor = new Button("Rem");

	//##AREA COVERED##
	CheckBox checkAreaCovered = new CheckBox("Hanya Boleh Transaksi pada AREA yang di Cover?");
	Table tableAreaCoverDetail = new Table();	
	Button btnAddArea = new Button("Add");
	Button btnRemoveArea = new Button("Rem");
	
	//LAYOUT
	private FormLayout formLayout = new FormLayout();
	
	//Panel
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelTabel = new Panel();
	private Panel panelForm = new Panel();

	//Help Manager	
	
	public SalesmanView(SalesmanModel model){
		this.model = model;
		initComponent();
		buildView();
		initComponentState();
		
		setDisplay();
		
		setDisplayVendorCovered();
		setDisplayAreaCovered();
		
		setComponentStyles();
		
	}
	
	public void initComponent(){
		table = new Table() {
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
		
		//TOP
		comboSearchByGrup.setWidth("200px");
		comboSearchByGrup.setFilteringMode(FilteringMode.CONTAINS);
		fieldSearchById.setWidth("100px");
		fieldSearchByDesc.setWidth("200px");
		
		//Init Field
		fieldId.setNullRepresentation("");
		fieldDescription.setNullRepresentation("");
		fieldReportDesc.setNullRepresentation("");
		
		fieldId.setWidth("100px");
		comboGrup.setWidth("400px");
		comboGrup.setFilteringMode(FilteringMode.CONTAINS);
		fieldDescription.setWidth("400px");

		btnSearch.setIcon(new ThemeResource("../images/navigation/12x12/Find.png"));

		btnNewForm.setIcon(new ThemeResource("../images/navigation/12x12/Create.png"));
		btnEditForm.setIcon(new ThemeResource("../images/navigation/12x12/Pencil.png"));
		btnDeleteForm.setIcon(new ThemeResource("../images/navigation/12x12/Erase.png"));
		btnPrint.setIcon(new ThemeResource("../images/navigation/12x12/Print.png"));
		
		btnSaveForm.setIcon(new ThemeResource("../images/navigation/12x12/Save.png"));
		btnCancelForm.setIcon(new ThemeResource("../images/navigation/12x12/Undo.png"));

//		btnAddItem.setIcon(new ThemeResource("../images/navigation/12x12/Create.png"));
		btnEditForm.setIcon(new ThemeResource("../images/navigation/12x12/Pencil.png"));
//		btnRemoveItem.setIcon(new ThemeResource("../images/navigation/12x12/Erase.png"));
			
//TAMBAHAN
		comboSalestype.setWidth("300px");
		fieldAddress1.setWidth("400px");
		fieldCity1.setWidth("400px");
		fieldState1.setWidth("400px");
		fieldPhone.setWidth("400px");
		fieldMobile.setWidth("400px");
		fieldEmail.setWidth("400px");
		fieldBornplace.setWidth("200px");
		fieldJoindate.setWidth("200px");
		fieldLasttrans.setWidth("200px");
		fieldBorndate.setWidth("200px");
		
		fieldJoindate.setDateFormat("dd/MM/yyyy");
		fieldLasttrans.setDateFormat("dd/MM/yyyy");
		fieldBorndate.setDateFormat("dd/MM/yyyy");
		
		//VALIDATOR
		fieldId.setRequired(true);
		fieldDescription.setRequired(true);
		comboSalestype.setRequired(true);
		
		//VENDOR COVERED
		tableVendorCoverDetail.setSelectable(true);
		tableAreaCoverDetail.setSelectable(true);
		
		tabSheet.setSizeFull();
		tabSheet.addStyleName(Reindeer.TABSHEET_SMALL);
		tabSheet.addStyleName(Reindeer.TABSHEET_BORDERLESS);
		
	}
	
	public void buildView(){
		
		//PANEL
		panelUtama.setSizeFull();
		panelForm.setSizeFull();
		content.setSizeFull();
		content.setMargin(true);
		
		
//***** KOMPONEN ATAS
		VerticalLayout layoutTop = new VerticalLayout();
		
//		layoutTop.setMargin(true);
		HorizontalLayout layoutTop1 = new HorizontalLayout();		
//		layoutTop.addComponent(comboSearchByGrup);
		layoutTop1.addComponent(fieldSearchById);
		layoutTop1.addComponent(fieldSearchByDesc);
		
		layoutTop1.addComponent(btnSearch);
		layoutTop1.addComponent(btnNewForm);
		layoutTop1.addComponent(btnEditForm);
		layoutTop1.addComponent(btnDeleteForm);
		layoutTop1.addComponent(btnPrint);
//		layoutTop1.addComponent(btnHelp);		
		
		layoutTop1.setComponentAlignment(fieldSearchByDesc, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnNewForm, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnEditForm, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnDeleteForm, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnPrint, Alignment.BOTTOM_CENTER);
//		layoutTop1.setComponentAlignment(btnHelp, Alignment.BOTTOM_CENTER);
		
		//KOMPONEN TENGAH
		//INIT COMPONENT TENGAH
		table.setSizeFull();
		table.setSelectable(true);
		table.setImmediate(true);
		table.setBuffered(false);
		table.setFooterVisible(true);
		
		VerticalLayout layoutTable = new VerticalLayout();
//		layoutTable.setMargin(true);
		layoutTable.setSizeFull();
		layoutTable.addComponent(table);

		//##e FORM LAYOUT UNTUK EDITING
		VerticalLayout editingForm = new VerticalLayout();
		editingForm.setMargin(true);
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
			
		VerticalLayout layoutIdentitas = new VerticalLayout();
		VerticalLayout layoutVendorCovered = new VerticalLayout();
		VerticalLayout layoutAreaCovered = new VerticalLayout();
		
		tabSheet.addTab(formLayout, "Identitas");
		tabSheet.addTab(layoutVendorCovered, "Covered Supplier");
		tabSheet.addTab(layoutAreaCovered, "Covered Area");
		
		editingForm.addComponent(tabSheet);
		editingForm.addComponent(buttonLayout);	
		
		formLayout.setMargin(true);
		formLayout.addComponent(fieldId);
//		formLayout.addComponent(comboGrup);
		formLayout.addComponent(fieldDescription);
//		formLayout.addComponent(fieldReportDesc);
		formLayout.addComponent(comboSalestype);
		
		formLayout.addComponent(fieldAddress1);
		formLayout.addComponent(fieldCity1);
		formLayout.addComponent(fieldState1);
		formLayout.addComponent(fieldPhone);
		formLayout.addComponent(fieldMobile);
		formLayout.addComponent(fieldEmail);
		formLayout.addComponent(fieldBornplace);
		formLayout.addComponent(fieldBorndate);
		formLayout.addComponent(fieldJoindate);
		formLayout.addComponent(fieldLasttrans);		
		formLayout.addComponent(checkStatusActive);
		
		HorizontalLayout formLayoutHorizontal = new HorizontalLayout();
		formLayoutHorizontal.setSpacing(true);
		formLayout.addComponent(formLayoutHorizontal);
//### TAB VENDOR COVERED
		layoutVendorCovered.setSizeFull();
		VerticalLayout layoutVendorCoveredTop = new VerticalLayout();
			HorizontalLayout layoutVendorCoveredTopLeft = new HorizontalLayout();
			HorizontalLayout layoutVendorCoveredTopRight = new HorizontalLayout();
		VerticalLayout layoutVendorCoveredDown = new VerticalLayout();

		layoutVendorCovered.addComponent(layoutVendorCoveredTop);
			layoutVendorCoveredTop.addComponent(layoutVendorCoveredTopLeft);
			layoutVendorCoveredTop.addComponent(layoutVendorCoveredTopRight);
				layoutVendorCovered.addComponent(layoutVendorCoveredDown);
			
		layoutVendorCoveredTop.setSizeFull();
		layoutVendorCoveredDown.setSizeFull();

		tableVendorCoverDetail.setSizeFull();

			layoutVendorCoveredTopLeft.addComponent(checkVendorCovered);
			layoutVendorCoveredTopRight.addComponent(btnAddVendor);
			layoutVendorCoveredTopRight.addComponent(btnRemoveVendor);
			layoutVendorCoveredTop.setComponentAlignment(layoutVendorCoveredTopLeft, Alignment.BOTTOM_LEFT);
		layoutVendorCoveredTop.setComponentAlignment(layoutVendorCoveredTopRight, Alignment.BOTTOM_RIGHT);
		
		layoutVendorCoveredDown.addComponent(tableVendorCoverDetail);
		
//### TAB AREA COVERED
		layoutAreaCovered.setSizeFull();
		VerticalLayout layoutAreaCoveredTop = new VerticalLayout();
			HorizontalLayout layoutAreaCoveredTopLeft = new HorizontalLayout();
			HorizontalLayout layoutAreaCoveredTopRight = new HorizontalLayout();
		VerticalLayout layoutAreaCoveredDown = new VerticalLayout();

		layoutAreaCovered.addComponent(layoutAreaCoveredTop);
			layoutAreaCoveredTop.addComponent(layoutAreaCoveredTopLeft);
			layoutAreaCoveredTop.addComponent(layoutAreaCoveredTopRight);
				layoutAreaCovered.addComponent(layoutAreaCoveredDown);
			
		layoutAreaCoveredTop.setSizeFull();
		layoutAreaCoveredDown.setSizeFull();

		tableAreaCoverDetail.setSizeFull();

			layoutAreaCoveredTopLeft.addComponent(checkAreaCovered);
			layoutAreaCoveredTopRight.addComponent(btnAddArea);
			layoutAreaCoveredTopRight.addComponent(btnRemoveArea);
			layoutAreaCoveredTop.setComponentAlignment(layoutAreaCoveredTopLeft, Alignment.BOTTOM_LEFT);
		layoutAreaCoveredTop.setComponentAlignment(layoutAreaCoveredTopRight, Alignment.BOTTOM_RIGHT);
		
		layoutAreaCoveredDown.addComponent(tableAreaCoverDetail);
		
		
		buttonLayout.setMargin(true);
		buttonLayout.addComponent(btnSaveForm);
		buttonLayout.addComponent(btnCancelForm);

		panelForm.setContent(editingForm);

		
		//MASUKKAN KE ROOT
		layoutTop.addComponent(layoutTop1);
		
		content.addComponent(layoutTop);
		content.addComponent(layoutTable);
		content.setExpandRatio(layoutTable, 1);
		
		panelUtama.setContent(content);		
		setCompositionRoot(panelUtama);	
		
		
	}
	public void initComponentState(){
//		formLayout.setVisible(false);
//		btnHelp.setVisible(false);
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
		btnNewForm.addStyleName("small");
		btnEditForm.addStyleName("small");
		btnDeleteForm.addStyleName("small");
		btnPrint.addStyleName("small");
		
		btnSaveForm.addStyleName("small");
		btnCancelForm.addStyleName("small");
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
	
		btnAddVendor.addStyleName(Reindeer.BUTTON_SMALL);
		btnRemoveVendor.addStyleName(Reindeer.BUTTON_SMALL);
		
		btnAddArea.addStyleName(Reindeer.BUTTON_SMALL);
		btnRemoveArea.addStyleName(Reindeer.BUTTON_SMALL);
		
		
}

	
	public void setDisplay(){
		//TABLE
		table.setContainerDataSource(model.getBeanItemContainerHeader());
		
		setTableProperties();
		
		setDisplayTableFooter();
		
		bindAndBuildFieldGroupComponent();
		
		setFormButtonAndTextState();
		
	}
	public void setDisplayVendorCovered(){
		tableVendorCoverDetail.setContainerDataSource(model.getBeanItemContainerVendorCovered());
		setTablePropertiesVendorCovered();
		
	}
	public void setDisplayAreaCovered(){
		tableAreaCoverDetail.setContainerDataSource(model.getBeanItemContainerAreaCovered());
		setTablePropertiesAreaCovered();
		
	}
	
	public void setDisplayTableFooter(){
		Collection items =  model.getBeanItemContainerHeader().getItemIds();
		table.setColumnFooter("spcode", "*Record: " + items.size());
		
	}
	
	public void bindAndBuildFieldGroupComponent(){
//		comboSearchByGrup.setContainerDataSource(model.getBeanItemContainerKelompok());
//		comboSearchByGrup.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
//		comboSearchByGrup.setNullSelectionAllowed(true);
//		
//		//Init isian combobox
//		comboGrup.setContainerDataSource(model.getBeanItemContainerKelompok());
//		comboGrup.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
//		comboGrup.setNullSelectionAllowed(false);
		
		//Init isian combobox
//		comboSalestype.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboSalestype.addItem("SHOPSALE");		
		comboSalestype.addItem("TO");
		comboSalestype.setItemCaption("TO", "TO - Taking Order");
		comboSalestype.addItem("C");
		comboSalestype.setItemCaption("C", "C - Canvas");
		comboSalestype.addItem("TF");
		comboSalestype.setItemCaption("TF", "TF - Task Force");
		comboSalestype.addItem("D");
		comboSalestype.setItemCaption("D", "D - Dented");
		comboSalestype.addItem("BS");
		comboSalestype.setNullSelectionAllowed(false);

		model.getBinderHeader().bind(fieldId, "spcode");
		model.getBinderHeader().bind(fieldDescription, "spname");

		model.getBinderHeader().bind(comboSalestype, "salestype");
		model.getBinderHeader().bind(fieldAddress1, "address1");
		model.getBinderHeader().bind(fieldCity1, "city1");
		model.getBinderHeader().bind(fieldState1, "state1");
		model.getBinderHeader().bind(fieldPhone, "phone");
		model.getBinderHeader().bind(fieldMobile, "mobile");
		model.getBinderHeader().bind(fieldEmail, "email");
		model.getBinderHeader().bind(fieldBornplace, "bornplace");
		model.getBinderHeader().bind(fieldBorndate, "borndate");
		model.getBinderHeader().bind(fieldJoindate, "joindate");
		model.getBinderHeader().bind(fieldLasttrans, "lasttrans");		
		model.getBinderHeader().bind(checkStatusActive, "statusactive");

		model.getBinderHeader().bind(checkVendorCovered, "vendorcovered");
		
	}
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	
	public void setTableProperties(){

		setVisibleTableProperties("spcode", "spname", "salestype");
		
		table.setColumnCollapsingAllowed(true);
//		try{
//			table.setColumnCollapsed("refno", true);
//			table.setColumnCollapsed("refno", true);
//			
//		} catch(Exception ex){}
		
		//ALIGNMENT
//		table.setColumnAlignment("amount", Align.RIGHT);
//		table.setColumnAlignment("amountused", Align.RIGHT);
		
		//set header
		table.setColumnHeader("spcode", "ID");
		table.setColumnHeader("spname", "SALESMAN");
		table.setColumnHeader("salestype", "TIPE JUAL");
		
//		table.setColumnExpandRatio("spcode", 2);
//		table.setColumnExpandRatio("spname", 5);
				
	}
	
	public void setVisibleTablePropertiesVendorCovered(Object... tablePropertyIds) {
		tableVendorCoverDetail.setVisibleColumns(tablePropertyIds);		
	}
	public void setTablePropertiesVendorCovered(){
		setVisibleTablePropertiesVendorCovered("vcode", "vname");		
	}
	public void setVisibleTablePropertiesAreaCovered(Object... tablePropertyIds) {
		tableAreaCoverDetail.setVisibleColumns(tablePropertyIds);		
	}
	public void setTablePropertiesAreaCovered(){
		setVisibleTablePropertiesAreaCovered("id", "description");		
	}
	
	public void setFormButtonAndTextState(){
		//KODE REFNO SELALU READ ONLY KARENA OTOMATIS
		
//		if (model.getOperationStatus().equals(EnumOperationStatus.OPEN.getStrCode())){
//			fieldId.setReadOnly(true);
//			formLayout.setVisible(false);
//			table.setSelectable(true);
//			btnAddForm.setEnabled(true);
//			btnDeleteForm.setEnabled(true);			
//			btnSearch.setEnabled(true);
//		} else if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
//			fieldId.setReadOnly(false);
//			formLayout.setVisible(true);
//			table.setSelectable(false);
//			btnAddForm.setEnabled(false);
//			btnDeleteForm.setEnabled(false);
//			btnSearch.setEnabled(false);
//		}else if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
//			fieldId.setReadOnly(true);
//			formLayout.setVisible(true);
//			table.setSelectable(true);
//			btnAddForm.setEnabled(true);
//			btnDeleteForm.setEnabled(true);			
//			btnSearch.setEnabled(true);
//		}		
		
	}
	
	private Window windowForm = new Window();
	
	public void showWindowForm(){
		windowForm = new Window();
		windowForm.setModal(true);
		windowForm.center();
//		windowForm.setStyleName("login-layout");
		windowForm.setWidth("800px");
		windowForm.setHeight("650px");
		windowForm.setClosable(true);	
		windowForm.setResizable(false);
		
		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSizeFull();
		content.addComponent(panelForm);
		

		windowForm.setContent(content);
		windowForm.center();
		getUI().addWindow(windowForm);
		
	}
	public void closeWindowForm(){
		windowForm.close();
		getUI().removeWindow(windowForm);
		
	}
	private VendorCoveredItemModel itemVendorCoveredModel;
	private VendorCoveredItemView itemVendorCoveredView;
	private VendorCoveredItemPresenter itemVendorCoveredPresenter;
	private Window windowVendorCovered = new Window();
	
	public void showWindowVendorCovered(){
		Panel panelFormVendorCovered = new Panel();
		
		itemVendorCoveredModel = new VendorCoveredItemModel();
		itemVendorCoveredView = new VendorCoveredItemView(itemVendorCoveredModel);
		itemVendorCoveredPresenter = new VendorCoveredItemPresenter(itemVendorCoveredModel, itemVendorCoveredView);
		
		itemVendorCoveredView.setSizeFull();
		panelFormVendorCovered.setContent(itemVendorCoveredView);
		
		windowVendorCovered = new Window();
		windowVendorCovered.setModal(true);
		
		windowVendorCovered.center();
		
		windowVendorCovered.setWidth("950px");
		windowVendorCovered.setHeight("200px");
		windowVendorCovered.setClosable(true);	
		windowVendorCovered.setResizable(false);

		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSizeFull();
		content.addComponent(panelFormVendorCovered);		
		windowVendorCovered.setContent(content);
		
		getUI().addWindow(windowVendorCovered);
		
	}
	public void closeWindowVendorCovered(){
		windowVendorCovered.close();
		
	}
	
	private AreaCoveredItemModel itemAreaCoveredModel;
	private AreaCoveredItemView itemAreaCoveredView;
	private AreaCoveredItemPresenter itemAreaCoveredPresenter;
	private Window windowAreaCovered = new Window();
	
	public void showWindowAreaCovered(){
		Panel panelFormAreaCovered = new Panel();
		
		itemAreaCoveredModel = new AreaCoveredItemModel();
		itemAreaCoveredView = new AreaCoveredItemView(itemAreaCoveredModel);
		itemAreaCoveredPresenter = new AreaCoveredItemPresenter(itemAreaCoveredModel, itemAreaCoveredView);
		
		itemAreaCoveredView.setSizeFull();
		panelFormAreaCovered.setContent(itemAreaCoveredView);
		
		windowAreaCovered = new Window();
		windowAreaCovered.setModal(true);
		
		windowAreaCovered.center();
		
		windowAreaCovered.setWidth("950px");
		windowAreaCovered.setHeight("200px");
		windowAreaCovered.setClosable(true);	
		windowAreaCovered.setResizable(false);

		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSizeFull();
		content.addComponent(panelFormAreaCovered);		
		windowAreaCovered.setContent(content);
		
		getUI().addWindow(windowAreaCovered);
		
	}
	public void closeWindowAreaCovered(){
		windowAreaCovered.close();
		
	}
	
	
	public void focustIdOrDesc(){
		if (fieldId.isEnabled()){
			fieldId.focus();
		} else {
			fieldDescription.focus();		                    						
		}		
	}

	public SalesmanModel getModel() {
		return model;
	}

	public VerticalLayout getContent() {
		return content;
	}

	public TabSheet getTabSheet() {
		return tabSheet;
	}

	public Table getTable() {
		return table;
	}

	public TextField getFieldId() {
		return fieldId;
	}

	public ComboBox getComboGrup() {
		return comboGrup;
	}

	public TextField getFieldDescription() {
		return fieldDescription;
	}

	public TextField getFieldReportDesc() {
		return fieldReportDesc;
	}

	public CheckBox getCheckStatusActive() {
		return checkStatusActive;
	}

	public ComboBox getComboSalestype() {
		return comboSalestype;
	}

	public TextField getFieldAddress1() {
		return fieldAddress1;
	}

	public TextField getFieldCity1() {
		return fieldCity1;
	}

	public TextField getFieldState1() {
		return fieldState1;
	}

	public TextField getFieldPhone() {
		return fieldPhone;
	}

	public TextField getFieldMobile() {
		return fieldMobile;
	}

	public TextField getFieldEmail() {
		return fieldEmail;
	}

	public ComboBox getComboReligion() {
		return comboReligion;
	}

	public TextField getFieldBornplace() {
		return fieldBornplace;
	}

	public DateField getFieldJoindate() {
		return fieldJoindate;
	}

	public DateField getFieldLasttrans() {
		return fieldLasttrans;
	}

	public DateField getFieldBorndate() {
		return fieldBorndate;
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

	public ComboBox getComboSearchByGrup() {
		return comboSearchByGrup;
	}

	public TextField getFieldSearchById() {
		return fieldSearchById;
	}

	public TextField getFieldSearchByDesc() {
		return fieldSearchByDesc;
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

	public CheckBox getCheckVendorCovered() {
		return checkVendorCovered;
	}

	public Table getTableVendorCoverDetail() {
		return tableVendorCoverDetail;
	}

	public Button getBtnAddVendor() {
		return btnAddVendor;
	}

	public Button getBtnRemoveVendor() {
		return btnRemoveVendor;
	}

	public CheckBox getCheckAreaCovered() {
		return checkAreaCovered;
	}

	public Table getTableAreaCoverDetail() {
		return tableAreaCoverDetail;
	}

	public Button getBtnAddArea() {
		return btnAddArea;
	}

	public Button getBtnRemoveArea() {
		return btnRemoveArea;
	}

	public FormLayout getFormLayout() {
		return formLayout;
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

	public Window getWindowForm() {
		return windowForm;
	}

	public VendorCoveredItemModel getItemVendorCoveredModel() {
		return itemVendorCoveredModel;
	}

	public VendorCoveredItemView getItemVendorCoveredView() {
		return itemVendorCoveredView;
	}

	public VendorCoveredItemPresenter getItemVendorCoveredPresenter() {
		return itemVendorCoveredPresenter;
	}

	public Window getWindowVendorCovered() {
		return windowVendorCovered;
	}

	public AreaCoveredItemModel getItemAreaCoveredModel() {
		return itemAreaCoveredModel;
	}

	public AreaCoveredItemView getItemAreaCoveredView() {
		return itemAreaCoveredView;
	}

	public AreaCoveredItemPresenter getItemAreaCoveredPresenter() {
		return itemAreaCoveredPresenter;
	}

	public Window getWindowAreaCovered() {
		return windowAreaCovered;
	}

	public void setModel(SalesmanModel model) {
		this.model = model;
	}

	public void setContent(VerticalLayout content) {
		this.content = content;
	}

	public void setTabSheet(TabSheet tabSheet) {
		this.tabSheet = tabSheet;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void setFieldId(TextField fieldId) {
		this.fieldId = fieldId;
	}

	public void setComboGrup(ComboBox comboGrup) {
		this.comboGrup = comboGrup;
	}

	public void setFieldDescription(TextField fieldDescription) {
		this.fieldDescription = fieldDescription;
	}

	public void setFieldReportDesc(TextField fieldReportDesc) {
		this.fieldReportDesc = fieldReportDesc;
	}

	public void setCheckStatusActive(CheckBox checkStatusActive) {
		this.checkStatusActive = checkStatusActive;
	}

	public void setComboSalestype(ComboBox comboSalestype) {
		this.comboSalestype = comboSalestype;
	}

	public void setFieldAddress1(TextField fieldAddress1) {
		this.fieldAddress1 = fieldAddress1;
	}

	public void setFieldCity1(TextField fieldCity1) {
		this.fieldCity1 = fieldCity1;
	}

	public void setFieldState1(TextField fieldState1) {
		this.fieldState1 = fieldState1;
	}

	public void setFieldPhone(TextField fieldPhone) {
		this.fieldPhone = fieldPhone;
	}

	public void setFieldMobile(TextField fieldMobile) {
		this.fieldMobile = fieldMobile;
	}

	public void setFieldEmail(TextField fieldEmail) {
		this.fieldEmail = fieldEmail;
	}

	public void setComboReligion(ComboBox comboReligion) {
		this.comboReligion = comboReligion;
	}

	public void setFieldBornplace(TextField fieldBornplace) {
		this.fieldBornplace = fieldBornplace;
	}

	public void setFieldJoindate(DateField fieldJoindate) {
		this.fieldJoindate = fieldJoindate;
	}

	public void setFieldLasttrans(DateField fieldLasttrans) {
		this.fieldLasttrans = fieldLasttrans;
	}

	public void setFieldBorndate(DateField fieldBorndate) {
		this.fieldBorndate = fieldBorndate;
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

	public void setComboSearchByGrup(ComboBox comboSearchByGrup) {
		this.comboSearchByGrup = comboSearchByGrup;
	}

	public void setFieldSearchById(TextField fieldSearchById) {
		this.fieldSearchById = fieldSearchById;
	}

	public void setFieldSearchByDesc(TextField fieldSearchByDesc) {
		this.fieldSearchByDesc = fieldSearchByDesc;
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

	public void setCheckVendorCovered(CheckBox checkVendorCovered) {
		this.checkVendorCovered = checkVendorCovered;
	}

	public void setTableVendorCoverDetail(Table tableVendorCoverDetail) {
		this.tableVendorCoverDetail = tableVendorCoverDetail;
	}

	public void setBtnAddVendor(Button btnAddVendor) {
		this.btnAddVendor = btnAddVendor;
	}

	public void setBtnRemoveVendor(Button btnRemoveVendor) {
		this.btnRemoveVendor = btnRemoveVendor;
	}

	public void setCheckAreaCovered(CheckBox checkAreaCovered) {
		this.checkAreaCovered = checkAreaCovered;
	}

	public void setTableAreaCoverDetail(Table tableAreaCoverDetail) {
		this.tableAreaCoverDetail = tableAreaCoverDetail;
	}

	public void setBtnAddArea(Button btnAddArea) {
		this.btnAddArea = btnAddArea;
	}

	public void setBtnRemoveArea(Button btnRemoveArea) {
		this.btnRemoveArea = btnRemoveArea;
	}

	public void setFormLayout(FormLayout formLayout) {
		this.formLayout = formLayout;
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

	public void setWindowForm(Window windowForm) {
		this.windowForm = windowForm;
	}

	public void setItemVendorCoveredModel(
			VendorCoveredItemModel itemVendorCoveredModel) {
		this.itemVendorCoveredModel = itemVendorCoveredModel;
	}

	public void setItemVendorCoveredView(VendorCoveredItemView itemVendorCoveredView) {
		this.itemVendorCoveredView = itemVendorCoveredView;
	}

	public void setItemVendorCoveredPresenter(
			VendorCoveredItemPresenter itemVendorCoveredPresenter) {
		this.itemVendorCoveredPresenter = itemVendorCoveredPresenter;
	}

	public void setWindowVendorCovered(Window windowVendorCovered) {
		this.windowVendorCovered = windowVendorCovered;
	}

	public void setItemAreaCoveredModel(AreaCoveredItemModel itemAreaCoveredModel) {
		this.itemAreaCoveredModel = itemAreaCoveredModel;
	}

	public void setItemAreaCoveredView(AreaCoveredItemView itemAreaCoveredView) {
		this.itemAreaCoveredView = itemAreaCoveredView;
	}

	public void setItemAreaCoveredPresenter(
			AreaCoveredItemPresenter itemAreaCoveredPresenter) {
		this.itemAreaCoveredPresenter = itemAreaCoveredPresenter;
	}

	public void setWindowAreaCovered(Window windowAreaCovered) {
		this.windowAreaCovered = windowAreaCovered;
	}


	
}
