package org.erp.distribution.master.systemsetting.parameterdiskonitemvendor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

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
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Table.Align;

public class ParamDiskonItemVendorView extends CustomComponent{
	private ParamDiskonItemVendorModel model;

	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	
	private TextField fieldId = new TextField("ID");
	private TextField fieldDescription= new TextField("DESKRIPSI");
	private CheckBox checkStatusActive = new CheckBox("AKTIF", false);
	
	private CheckBox checkBox1 = new CheckBox("SEMUA JENIS CUSTOMER");
	private ComboBox comboGrup1 = new ComboBox("JENIS CUSTOMER");
	private CheckBox checkBox2		 = new CheckBox("SEMUA SUPPLIER", false);
	private ComboBox comboVendor = new ComboBox("VENDOR/SUPPLIER");
	private CheckBox checkBox3		 = new CheckBox("SEMUA GRUP PRODUK", false);
	private ComboBox comboProductGrup = new ComboBox("GRUP PRODUK");

	private TextField field1 = new TextField("Penjualan Rp >");
	private TextField field11 = new TextField("DISC1");
	private TextField field11plus = new TextField("+DISC2");
	
	private TextField field2 = new TextField("Penjualan Rp >");
	private TextField field22 = new TextField("DISC1");
	private TextField field22plus = new TextField("+DISC2");

	private TextField field3 = new TextField("Penjualan Rp >");
	private TextField field33 = new TextField("DISC1");
	private TextField field33plus = new TextField("");

	private TextField field4 = new TextField("Penjualan Rp >");
	private TextField field44 = new TextField("DISC1");
	private TextField field44plus = new TextField("+DISC2");

	private TextField field5 = new TextField("Penjualan Rp >");
	private TextField field55 = new TextField("DISC1");
	private TextField field55plus = new TextField("+DISC2");
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel");
	private Button btnNewForm= new Button("New");
	private Button btnEditForm= new Button("Edit");
	private Button btnDeleteForm= new Button("Delete");

	//Additional Component
	private ComboBox comboSearchByGrup = new ComboBox("DESKRIPSI");
	private TextField fieldSearchById = new TextField("ID");
	private TextField fieldSearchByDesc = new TextField("DESKRIPSI");
	private Button btnSearch = new Button("Search & Reload");
	private Button btnPrint = new Button("Print");
	private Button btnHelp = new Button("Help");

	//LAYOUT
	private FormLayout formLayout = new FormLayout();
	
	//Panel
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelTabel = new Panel();
	private Panel panelForm = new Panel();

	//Help Manager	
	
	public ParamDiskonItemVendorView(ParamDiskonItemVendorModel model){
		this.model = model;
		initComponent();
		buildView();
		initComponentState();
		
		setDisplay();
		
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
//			        	if ((Boolean) property.getValue()==true) {
//			        		return "Active";
//			        	} else {
//			        		return "-";
//			        	}
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
		
		fieldId.setWidth("100px");
		comboGrup1.setWidth("400px");
		comboGrup1.setFilteringMode(FilteringMode.CONTAINS);
		fieldDescription.setWidth("400px");

		comboVendor.setWidth("400px");
		comboVendor.setFilteringMode(FilteringMode.CONTAINS);
		comboVendor.setNullSelectionAllowed(true);

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
		
		//VALIDATION
//		fieldId.setRequired(true);
		fieldDescription.setRequired(true);
		
		field1.setNullRepresentation("0");
		field11.setNullRepresentation("0");
		field11plus.setNullRepresentation("0");
		
		field2.setNullRepresentation("0");
		field22.setNullRepresentation("0");
		field22plus.setNullRepresentation("0");
		
		field3.setNullRepresentation("0");
		field33.setNullRepresentation("0");
		field33plus.setNullRepresentation("0");
		
		field4.setNullRepresentation("0");
		field4.setNullRepresentation("0");
		field44plus.setNullRepresentation("0");

		field5.setNullRepresentation("0");
		field55.setNullRepresentation("0");
		field55plus.setNullRepresentation("0");

	}
	
	public void buildView(){
		
		//PANEL
		panelUtama.setSizeFull();
		panelForm.setSizeFull();
		content.setSizeFull();
		content.setMargin(true);
		
		//KOMPONEN ATAS
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

		//FORM LAYOUT		
//		formLayout.setMargin(true);

		HorizontalLayout layoutMidle1 = new HorizontalLayout();
//		layoutMidle1.setMargin(true);
		HorizontalLayout layoutMidle2 = new HorizontalLayout();
//		layoutMidle2.setMargin(true);
		HorizontalLayout layoutMidle3 = new HorizontalLayout();
//		layoutMidle3.setMargin(true);
		HorizontalLayout layoutMidle4 = new HorizontalLayout();
//		layoutMidle4.setMargin(true);
		HorizontalLayout layoutMidle5 = new HorizontalLayout();
//		layoutMidle5.setMargin(true);
		
//		formLayout.addComponent(fieldId);
		formLayout.addComponent(fieldDescription);
//		formLayout.addComponent(checkBox2);
		formLayout.addComponent(comboVendor);
//		formLayout.addComponent(checkBox3);
//		formLayout.addComponent(comboProductGrup);
//		formLayout.addComponent(checkBox1);
//		formLayout.addComponent(comboGrup1);
		formLayout.addComponent(checkStatusActive);

		formLayout.addComponent(layoutMidle1);
		formLayout.addComponent(layoutMidle2);
		formLayout.addComponent(layoutMidle3);
		formLayout.addComponent(layoutMidle4);
		formLayout.addComponent(layoutMidle5);

		


		layoutMidle1.addComponent(field1);
		layoutMidle1.addComponent(field11);
//		layoutMidle1.addComponent(field11plus);
		
		layoutMidle2.addComponent(field2);
		layoutMidle2.addComponent(field22);
//		layoutMidle2.addComponent(field22plus);

		layoutMidle3.addComponent(field3);
		layoutMidle3.addComponent(field33);
//		layoutMidle3.addComponent(field33plus);

		layoutMidle4.addComponent(field4);
		layoutMidle4.addComponent(field44);
//		layoutMidle4.addComponent(field44plus);
		
		layoutMidle5.addComponent(field5);
		layoutMidle5.addComponent(field55);
//		layoutMidle5.addComponent(field55plus);
		
		HorizontalLayout formLayoutHorizontal = new HorizontalLayout();
		formLayoutHorizontal.setSpacing(true);
		formLayoutHorizontal.addComponent(btnSaveForm);
		formLayoutHorizontal.addComponent(btnCancelForm);
		formLayout.addComponent(formLayoutHorizontal);
		
		panelForm.setContent(formLayout);

		
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
	
}

	
	public void setDisplay(){
		//TABLE
		table.setContainerDataSource(model.getBeanItemContainerHeader());
		
		setTableProperties();
		
		setDisplayTableFooter();
		
		bindAndBuildFieldGroupComponent();
		
		setFormButtonAndTextState();
		
	}
	public void setDisplayTableFooter(){
		Collection items =  model.getBeanItemContainerHeader().getItemIds();
		table.setColumnFooter("id", "*Record: " + items.size());
		
	}
	
	public void bindAndBuildFieldGroupComponent(){
//		comboSearchByGrup.setContainerDataSource(model.getBeanItemContainerKelompok());
//		comboSearchByGrup.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
//		comboSearchByGrup.setNullSelectionAllowed(true);
//		
		//Init isian combobox
		comboGrup1.setContainerDataSource(model.getBeanItemContainerSubgroup());
		comboGrup1.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboGrup1.setFilteringMode(FilteringMode.CONTAINS);
		comboGrup1.setNullSelectionAllowed(false);
		

		comboVendor.setContainerDataSource(model.getBeanItemContainerVendor());
		comboVendor.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
//		comboVendor.setItemCaptionPropertyId("description");
		comboVendor.setFilteringMode(FilteringMode.CONTAINS);
		comboVendor.setNullSelectionAllowed(false);

		comboProductGrup.setContainerDataSource(model.getBeanItemContainerProductGroup());
		comboProductGrup.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
//		comboProductGrup.setItemCaptionPropertyId("description");
		comboProductGrup.setFilteringMode(FilteringMode.CONTAINS);
		comboProductGrup.setNullSelectionAllowed(false);
		
//		model.getBinderHeader().bind(fieldId, "id");
		model.getBinderHeader().bind(fieldDescription, "description");
		model.getBinderHeader().bind(checkBox2, "allvendor");
		model.getBinderHeader().bind(comboVendor, "fvendorBean");
		model.getBinderHeader().bind(checkStatusActive, "statusActive");
	
		model.getBinderHeader().bind(field1, "nominal1");
		model.getBinderHeader().bind(field11, "diskon1");
		model.getBinderHeader().bind(field11plus, "diskon1plus");

		model.getBinderHeader().bind(field2, "nominal2");
		model.getBinderHeader().bind(field22, "diskon2");
		model.getBinderHeader().bind(field22plus, "diskon2plus");

		model.getBinderHeader().bind(field3, "nominal3");
		model.getBinderHeader().bind(field33, "diskon3");
		model.getBinderHeader().bind(field33plus, "diskon3plus");

		model.getBinderHeader().bind(field4, "nominal4");
		model.getBinderHeader().bind(field44, "diskon4");
		model.getBinderHeader().bind(field44plus, "diskon4plus");

		model.getBinderHeader().bind(field5, "nominal5");
		model.getBinderHeader().bind(field55, "diskon5");
		model.getBinderHeader().bind(field55plus, "diskon5plus");
		
		
	}
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){

		setVisibleTableProperties("id",  "description", "fvendorBean", "statusActive");
		
		table.setColumnCollapsingAllowed(true);
		try{
			table.setColumnCollapsed("id", true);
			
		} catch(Exception ex){}
		
		//ALIGNMENT
//		table.setColumnAlignment("amount", Align.RIGHT);
//		table.setColumnAlignment("amountused", Align.RIGHT);
		
		//set header
		table.setColumnHeader("id", "ID");
		table.setColumnHeader("description", "DESKRIPSI");
		table.setColumnHeader("fvendorBean", "VENDOR");
		table.setColumnHeader("statusActive", "AKTIF");
		
		
//		table.setColumnExpandRatio("id", 2);
//		table.setColumnExpandRatio("description", 5);
				
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
		windowForm.setWidth("700px");
		windowForm.setHeight("600px");
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
	
	public void focustIdOrDesc(){
		if (fieldId.isEnabled()){
			fieldId.focus();
		} else {
			fieldDescription.focus();		                    						
		}		
	}

	public ParamDiskonItemVendorModel getModel() {
		return model;
	}

	public VerticalLayout getContent() {
		return content;
	}

	public Table getTable() {
		return table;
	}

	public TextField getFieldId() {
		return fieldId;
	}

	public TextField getFieldDescription() {
		return fieldDescription;
	}

	public CheckBox getCheckStatusActive() {
		return checkStatusActive;
	}

	public CheckBox getCheckBox1() {
		return checkBox1;
	}

	public ComboBox getComboGrup1() {
		return comboGrup1;
	}

	public CheckBox getCheckBox2() {
		return checkBox2;
	}

	public ComboBox getComboVendor() {
		return comboVendor;
	}

	public TextField getField1() {
		return field1;
	}

	public TextField getField11() {
		return field11;
	}

	public TextField getField11plus() {
		return field11plus;
	}

	public TextField getField2() {
		return field2;
	}

	public TextField getField22() {
		return field22;
	}

	public TextField getField22plus() {
		return field22plus;
	}

	public TextField getField3() {
		return field3;
	}

	public TextField getField33() {
		return field33;
	}

	public TextField getField33plus() {
		return field33plus;
	}

	public TextField getField4() {
		return field4;
	}

	public TextField getField44() {
		return field44;
	}

	public TextField getField44plus() {
		return field44plus;
	}

	public TextField getField5() {
		return field5;
	}

	public TextField getField55() {
		return field55;
	}

	public TextField getField55plus() {
		return field55plus;
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

	public void setModel(ParamDiskonItemVendorModel model) {
		this.model = model;
	}

	public void setContent(VerticalLayout content) {
		this.content = content;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void setFieldId(TextField fieldId) {
		this.fieldId = fieldId;
	}

	public void setFieldDescription(TextField fieldDescription) {
		this.fieldDescription = fieldDescription;
	}

	public void setCheckStatusActive(CheckBox checkStatusActive) {
		this.checkStatusActive = checkStatusActive;
	}

	public void setCheckBox1(CheckBox checkBox1) {
		this.checkBox1 = checkBox1;
	}

	public void setComboGrup1(ComboBox comboGrup1) {
		this.comboGrup1 = comboGrup1;
	}

	public void setCheckBox2(CheckBox checkBox2) {
		this.checkBox2 = checkBox2;
	}

	public void setComboVendor(ComboBox comboVendor) {
		this.comboVendor = comboVendor;
	}

	public void setField1(TextField field1) {
		this.field1 = field1;
	}

	public void setField11(TextField field11) {
		this.field11 = field11;
	}

	public void setField11plus(TextField field11plus) {
		this.field11plus = field11plus;
	}

	public void setField2(TextField field2) {
		this.field2 = field2;
	}

	public void setField22(TextField field22) {
		this.field22 = field22;
	}

	public void setField22plus(TextField field22plus) {
		this.field22plus = field22plus;
	}

	public void setField3(TextField field3) {
		this.field3 = field3;
	}

	public void setField33(TextField field33) {
		this.field33 = field33;
	}

	public void setField33plus(TextField field33plus) {
		this.field33plus = field33plus;
	}

	public void setField4(TextField field4) {
		this.field4 = field4;
	}

	public void setField44(TextField field44) {
		this.field44 = field44;
	}

	public void setField44plus(TextField field44plus) {
		this.field44plus = field44plus;
	}

	public void setField5(TextField field5) {
		this.field5 = field5;
	}

	public void setField55(TextField field55) {
		this.field55 = field55;
	}

	public void setField55plus(TextField field55plus) {
		this.field55plus = field55plus;
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

	

	
}
