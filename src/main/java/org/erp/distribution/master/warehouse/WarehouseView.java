package org.erp.distribution.master.warehouse;

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

public class WarehouseView extends CustomComponent{
	private WarehouseModel model;

	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	
	private TextField fieldId = new TextField("ID");
	private ComboBox comboGrup = new ComboBox("GROUP");
	private TextField fieldDescription= new TextField("NAMA GUDANG");
	private TextField fieldReportDesc= new TextField("REPORT DESC");
	private CheckBox checkStatusActive = new CheckBox("AKTIF", false);
	
	
	//TAMBAHAN
	private CheckBox checkGudangutama = new CheckBox("GUDANG UTAMA", false);
	private TextField fieldAddress1 = new TextField("ADDRESS/ALAMAT");
	private TextField fieldCity1 = new TextField("CITY/KOTA");
	private TextField fieldState1 = new TextField("STATE/PROPINSI");
	private TextField fieldPhone = new TextField("TELPON");
	
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel");
	private Button btnNewForm= new Button("New");
	private Button btnEditForm= new Button("Edit");
	private Button btnDeleteForm= new Button("Delete");

	//Additional Component
	private ComboBox comboSearchByGrup = new ComboBox("GRUP");
	private TextField fieldSearchById = new TextField("ID");
	private TextField fieldSearchByDesc = new TextField("NAMA GUDANG");
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
	
	public WarehouseView(WarehouseModel model){
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
		fieldAddress1.setWidth("400px");
		fieldCity1.setWidth("200px");
		fieldState1.setWidth("200px");
		fieldPhone.setWidth("200px");
		
		//VALIDATOR
		fieldId.setRequired(true);
		fieldDescription.setRequired(true);
		
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
		formLayout.setMargin(true);
		formLayout.addComponent(fieldId);
//		formLayout.addComponent(comboGrup);
		formLayout.addComponent(fieldDescription);
		
		formLayout.addComponent(fieldAddress1);
		formLayout.addComponent(fieldCity1);
		formLayout.addComponent(fieldState1);
		formLayout.addComponent(fieldPhone);
		formLayout.addComponent(checkGudangutama);
		formLayout.addComponent(checkStatusActive);
		
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
//		//Init isian combobox
//		comboGrup.setContainerDataSource(model.getBeanItemContainerKelompok());
//		comboGrup.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
//		comboGrup.setNullSelectionAllowed(false);
		
		model.getBinderHeader().bind(fieldId, "id");
		model.getBinderHeader().bind(fieldDescription, "description");
		
		model.getBinderHeader().bind(fieldAddress1, "address1");
		model.getBinderHeader().bind(fieldCity1, "city1");
		model.getBinderHeader().bind(fieldState1, "state1");
		model.getBinderHeader().bind(fieldPhone, "phone");
		
		model.getBinderHeader().bind(checkGudangutama, "gudangutama");
		model.getBinderHeader().bind(checkStatusActive, "statusactive");
		
		
	}
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){

		setVisibleTableProperties("id", "description", "gudangutama", "address1");
		
		table.setColumnCollapsingAllowed(true);
//		try{
//			table.setColumnCollapsed("refno", true);
//			table.setColumnCollapsed("refno", true);
//			
//		} catch(Exception ex){}
		
		//ALIGNMENT
//		table.setColumnAlignment("amount", Align.RIGHT);
		table.setColumnAlignment("gudangutama", Align.CENTER);
		
		//set header
		table.setColumnHeader("id", "ID");
		table.setColumnHeader("description", "NAMA GUDANG");
		table.setColumnHeader("gudangutama", "UTAMA");
		
		
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
		windowForm.setWidth("750px");
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

	public WarehouseModel getModel() {
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

	public ComboBox getComboGrup() {
		return comboGrup;
	}


	public TextField getFieldReportDesc() {
		return fieldReportDesc;
	}

	public CheckBox getCheckStatusActive() {
		return checkStatusActive;
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

	public Panel getPanelUtama() {
		return panelUtama;
	}

	public Window getWindowForm() {
		return windowForm;
	}

	public void setBtnNewForm(Button btnNewForm) {
		this.btnNewForm = btnNewForm;
	}

	public void setPanelUtama(Panel panelUtama) {
		this.panelUtama = panelUtama;
	}

	public void setWindowForm(Window windowForm) {
		this.windowForm = windowForm;
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

	public Panel getPanelTop() {
		return panelTop;
	}

	public Panel getPanelTabel() {
		return panelTabel;
	}

	public Panel getPanelForm() {
		return panelForm;
	}

	public void setModel(WarehouseModel model) {
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

	public void setComboGrup(ComboBox comboGrup) {
		this.comboGrup = comboGrup;
	}


	public TextField getFieldDescription() {
		return fieldDescription;
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

	public void setBtnSaveForm(Button btnSaveForm) {
		this.btnSaveForm = btnSaveForm;
	}

	public void setBtnCancelForm(Button btnCancelForm) {
		this.btnCancelForm = btnCancelForm;
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

	public void setPanelTop(Panel panelTop) {
		this.panelTop = panelTop;
	}

	public void setPanelTabel(Panel panelTabel) {
		this.panelTabel = panelTabel;
	}

	public void setPanelForm(Panel panelForm) {
		this.panelForm = panelForm;
	}

	public Button getBtnEditForm() {
		return btnEditForm;
	}

	public void setBtnEditForm(Button btnEditForm) {
		this.btnEditForm = btnEditForm;
	}

	

	
}
