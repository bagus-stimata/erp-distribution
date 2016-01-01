package org.erp.distribution.master.product;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.erp.distribution.master.customer.utility.CustomerUtilityModel;
import org.erp.distribution.master.customer.utility.CustomerUtilityPresenter;
import org.erp.distribution.master.customer.utility.CustomerUtilityView;

import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ProductView extends CustomComponent{
	private ProductModel model;

	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	
	private TextField fieldId = new TextField("ID");
	private ComboBox comboGrup = new ComboBox("GROUP");
	private TextField fieldDescription= new TextField("PRODUCT NAME");
	private TextField fieldReportDesc= new TextField("REPORT DESC");
	private CheckBox checkStatusActive = new CheckBox("AKTIF", false);
	
	//TAMBAHAN
	private TextField fieldShortcode = new TextField("SHORT CODE");
	private TextField fieldDistcode = new TextField("DIST CODE");
	private TextField fieldBarcode = new TextField("BAR CODE");
	private TextField fieldProdclass = new TextField("CLASS");
	private TextField fieldPackaging = new TextField("PACKAGING");
	private TextField fieldShortname = new TextField("SHORT NAME");
	private TextField fieldShortpackaging = new TextField("SHORT PACKAGING");
	private TextField fieldUom1 = new TextField("SAT BESAR");
	private TextField fieldUom2 = new TextField("SAT SEDANG");
	private TextField fieldUom3 = new TextField("SAT KECIL");
	private TextField fieldConvfact1 = new TextField("BESAR -> KECIL");
	private TextField fieldConvfact2 = new TextField("SEDANG -> KECIL");
	private TextField fieldPprice = new TextField("HRG. BELI BESAR");
	private TextField fieldPpriceafterppn = new TextField("HRG. BELI BESAR+PPN");
	private TextField fieldPprice2 = new TextField("HRG. BELI KECIL");
	private TextField fieldSprice = new TextField("HRG. JUAL BESAR");
	private TextField fieldSpriceafterppn = new TextField("HRG. JUAL BESAR+PPN");
	private TextField fieldSprice2 = new TextField("HRG. JUAL KECIL");
	private TextField fieldWeight = new TextField("BERAT");
	private TextField fieldSupplier = new TextField("SUPPLIER");
	private ComboBox comboSupplier = new ComboBox("Supplier");
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel");
	private Button btnNewForm= new Button("New");
	private Button btnEditForm= new Button("Edit");
	private Button btnDeleteForm= new Button("Delete");

	//Additional Component
	private ComboBox comboSearchByGrup = new ComboBox("GROUP");
	private TextField fieldSearchById = new TextField("ID");
	private TextField fieldSearchByDesc = new TextField("PRODUCT NAME");
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
	
	public ProductView(ProductModel model){
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
		        try{
			        if (property.getType()==Boolean.class){
			        	if ((Boolean) property.getValue()==true) {
			        		return "Active";
			        	} else {
			        		return "-";
			        	}
			        }
		        } catch(Exception ex){}
		        
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
		comboGrup.setWidth("200px");
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
		fieldShortcode.setWidth("200px");
		fieldDistcode.setWidth("200px");
		fieldBarcode.setWidth("400px");
		fieldProdclass.setWidth("200px");
		fieldPackaging.setWidth("200px");
		fieldShortname.setWidth("200px");
		fieldShortpackaging.setWidth("200px");
		fieldUom1.setWidth("100px");
		fieldUom2.setWidth("100px");
		fieldUom3.setWidth("100px");
		fieldConvfact1.setWidth("100px");
		fieldConvfact2.setWidth("100px");
		fieldPprice.setWidth("200px");
		fieldPpriceafterppn.setWidth("200px");
		fieldPprice2.setWidth("200px");
		fieldSprice.setWidth("200px");
		fieldSpriceafterppn.setWidth("200px");
		fieldSprice2.setWidth("200px");
		fieldWeight.setWidth("200px");
		
		//VALIDATOR
		fieldId.setRequired(true);
		fieldDescription.setRequired(true);
		comboGrup.setRequired(true);
		
		fieldConvfact1.setRequired(true);
		fieldConvfact2.setRequired(true);
		fieldUom1.setRequired(true);
		fieldUom2.setRequired(true);
		fieldUom3.setRequired(true);
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
		formLayout.addComponent(fieldDescription);
		formLayout.addComponent(fieldPackaging);
		formLayout.addComponent(comboGrup);
		formLayout.addComponent(fieldSupplier);
		formLayout.addComponent(comboSupplier);

		formLayout.addComponent(fieldShortcode);
		formLayout.addComponent(fieldDistcode);
		formLayout.addComponent(fieldBarcode);
		formLayout.addComponent(fieldProdclass);
		formLayout.addComponent(fieldShortname);
		formLayout.addComponent(fieldShortname);
		formLayout.addComponent(fieldShortpackaging);
		
		HorizontalLayout layoutUom = new HorizontalLayout();
		layoutUom.addComponent(fieldUom1);
		layoutUom.addComponent(fieldUom2);
		layoutUom.addComponent(fieldUom3);
		formLayout.addComponent(layoutUom);

		formLayout.addComponent(fieldConvfact1);
		formLayout.addComponent(fieldConvfact2);
		
		HorizontalLayout layoutPprice = new HorizontalLayout();
		layoutPprice.addComponent(fieldPprice);
		layoutPprice.addComponent(fieldPpriceafterppn);
		formLayout.addComponent(layoutPprice);		
//		formLayout.addComponent(fieldPprice2);
		
		HorizontalLayout layoutSprice = new HorizontalLayout();
		layoutSprice.addComponent(fieldSprice);
		layoutSprice.addComponent(fieldSpriceafterppn);
		formLayout.addComponent(layoutSprice);
//		formLayout.addComponent(fieldSprice2);
		
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
		fieldSupplier.setVisible(false);
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
		table.setColumnFooter("pname", "*Record: " + items.size());
		
	}
	
	public void bindAndBuildFieldGroupComponent(){
		model.getBinderHeader().setBuffered(false);
		
//		comboSearchByGrup.setContainerDataSource(model.getBeanItemContainerKelompok());
//		comboSearchByGrup.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
//		comboSearchByGrup.setNullSelectionAllowed(true);
//		
		//Init isian combobox
		comboGrup.setContainerDataSource(model.getBeanItemContainerGroup());
		comboGrup.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboGrup.setFilteringMode(FilteringMode.CONTAINS);
		comboGrup.setNullSelectionAllowed(false);

		comboSupplier.setContainerDataSource(model.getBeanItemContainerSupplier());
		comboSupplier.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboSupplier.setFilteringMode(FilteringMode.CONTAINS);
		comboSupplier.setNullSelectionAllowed(true);
		
		model.getBinderHeader().bind(fieldId, "pcode");
		model.getBinderHeader().bind(fieldDescription, "pname");
		model.getBinderHeader().bind(comboGrup, "fproductgroupBean");
		
		model.getBinderHeader().bind(fieldShortcode, "shortcode");
		model.getBinderHeader().bind(fieldDistcode, "distcode");
		model.getBinderHeader().bind(fieldBarcode, "barcode");
		model.getBinderHeader().bind(fieldProdclass, "prodclass");
		model.getBinderHeader().bind(fieldPackaging, "packaging");
		model.getBinderHeader().bind(fieldShortname, "shortname");
		model.getBinderHeader().bind(fieldShortpackaging, "shortpackaging");
		model.getBinderHeader().bind(fieldUom1, "uom1");
		model.getBinderHeader().bind(fieldUom2, "uom2");
		model.getBinderHeader().bind(fieldUom3, "uom3");
		model.getBinderHeader().bind(fieldConvfact1, "convfact1");
		model.getBinderHeader().bind(fieldConvfact2, "convfact2");
		model.getBinderHeader().bind(fieldPprice, "pprice");
		model.getBinderHeader().bind(fieldPpriceafterppn, "ppriceafterppn");
		model.getBinderHeader().bind(fieldPprice2, "pprice2");
		model.getBinderHeader().bind(fieldSprice, "sprice");
		model.getBinderHeader().bind(fieldSpriceafterppn, "spriceafterppn");
		model.getBinderHeader().bind(fieldSprice2, "sprice2");
		model.getBinderHeader().bind(fieldWeight, "weight");
		model.getBinderHeader().bind(fieldSupplier, "supplier");
		model.getBinderHeader().bind(comboSupplier, "fvendorBean");
		
		model.getBinderHeader().bind(checkStatusActive, "statusactive");
		
	}
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){

		setVisibleTableProperties("pcode",  "pname", "packaging", "fproductgroupBean", "pprice", "sprice", "shortname", "shortpackaging");
		
		table.setColumnCollapsingAllowed(true);
		try{
			table.setColumnCollapsed("shortname", true);
			table.setColumnCollapsed("shortpackaging", true);
			
		} catch(Exception ex){}
		
		//ALIGNMENT
		table.setColumnAlignment("pprice", Align.RIGHT);
		table.setColumnAlignment("sprice", Align.RIGHT);
		
		//set header
		table.setColumnHeader("pcode", "PCODE");
		table.setColumnHeader("pname", "PRODUCT NAME");
		table.setColumnHeader("packaging", "PACKAGING");
		table.setColumnHeader("fproductgroupBean", "GROUP");
		table.setColumnHeader("pprice", "HRG. BELI No Ppn");
		table.setColumnHeader("sprice", "HRG. JUAL No Ppn");
		
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
		windowForm.setHeight("770px");
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

	public ProductModel getModel() {
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

	public TextField getFieldDescription() {
		return fieldDescription;
	}

	public TextField getFieldReportDesc() {
		return fieldReportDesc;
	}

	public CheckBox getCheckStatusActive() {
		return checkStatusActive;
	}

	public TextField getFieldShortcode() {
		return fieldShortcode;
	}

	public TextField getFieldDistcode() {
		return fieldDistcode;
	}

	public TextField getFieldBarcode() {
		return fieldBarcode;
	}

	public TextField getFieldProdclass() {
		return fieldProdclass;
	}

	public TextField getFieldPackaging() {
		return fieldPackaging;
	}

	public TextField getFieldShortname() {
		return fieldShortname;
	}

	public TextField getFieldShortpackaging() {
		return fieldShortpackaging;
	}

	public TextField getFieldUom1() {
		return fieldUom1;
	}

	public TextField getFieldUom2() {
		return fieldUom2;
	}

	public TextField getFieldUom3() {
		return fieldUom3;
	}

	public TextField getFieldConvfact1() {
		return fieldConvfact1;
	}

	public TextField getFieldConvfact2() {
		return fieldConvfact2;
	}

	public TextField getFieldPprice() {
		return fieldPprice;
	}

	public TextField getFieldPpriceafterppn() {
		return fieldPpriceafterppn;
	}

	public TextField getFieldPprice2() {
		return fieldPprice2;
	}

	public TextField getFieldSprice() {
		return fieldSprice;
	}

	public TextField getFieldSpriceafterppn() {
		return fieldSpriceafterppn;
	}

	public TextField getFieldSprice2() {
		return fieldSprice2;
	}

	public TextField getFieldWeight() {
		return fieldWeight;
	}

	public TextField getFieldSupplier() {
		return fieldSupplier;
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

	public void setModel(ProductModel model) {
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

	public void setFieldDescription(TextField fieldDescription) {
		this.fieldDescription = fieldDescription;
	}

	public void setFieldReportDesc(TextField fieldReportDesc) {
		this.fieldReportDesc = fieldReportDesc;
	}

	public void setCheckStatusActive(CheckBox checkStatusActive) {
		this.checkStatusActive = checkStatusActive;
	}

	public void setFieldShortcode(TextField fieldShortcode) {
		this.fieldShortcode = fieldShortcode;
	}

	public void setFieldDistcode(TextField fieldDistcode) {
		this.fieldDistcode = fieldDistcode;
	}

	public void setFieldBarcode(TextField fieldBarcode) {
		this.fieldBarcode = fieldBarcode;
	}

	public void setFieldProdclass(TextField fieldProdclass) {
		this.fieldProdclass = fieldProdclass;
	}

	public void setFieldPackaging(TextField fieldPackaging) {
		this.fieldPackaging = fieldPackaging;
	}

	public void setFieldShortname(TextField fieldShortname) {
		this.fieldShortname = fieldShortname;
	}

	public void setFieldShortpackaging(TextField fieldShortpackaging) {
		this.fieldShortpackaging = fieldShortpackaging;
	}

	public void setFieldUom1(TextField fieldUom1) {
		this.fieldUom1 = fieldUom1;
	}

	public void setFieldUom2(TextField fieldUom2) {
		this.fieldUom2 = fieldUom2;
	}

	public void setFieldUom3(TextField fieldUom3) {
		this.fieldUom3 = fieldUom3;
	}

	public void setFieldConvfact1(TextField fieldConvfact1) {
		this.fieldConvfact1 = fieldConvfact1;
	}

	public void setFieldConvfact2(TextField fieldConvfact2) {
		this.fieldConvfact2 = fieldConvfact2;
	}

	public void setFieldPprice(TextField fieldPprice) {
		this.fieldPprice = fieldPprice;
	}

	public void setFieldPpriceafterppn(TextField fieldPpriceafterppn) {
		this.fieldPpriceafterppn = fieldPpriceafterppn;
	}

	public void setFieldPprice2(TextField fieldPprice2) {
		this.fieldPprice2 = fieldPprice2;
	}

	public void setFieldSprice(TextField fieldSprice) {
		this.fieldSprice = fieldSprice;
	}

	public void setFieldSpriceafterppn(TextField fieldSpriceafterppn) {
		this.fieldSpriceafterppn = fieldSpriceafterppn;
	}

	public void setFieldSprice2(TextField fieldSprice2) {
		this.fieldSprice2 = fieldSprice2;
	}

	public void setFieldWeight(TextField fieldWeight) {
		this.fieldWeight = fieldWeight;
	}

	public void setFieldSupplier(TextField fieldSupplier) {
		this.fieldSupplier = fieldSupplier;
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