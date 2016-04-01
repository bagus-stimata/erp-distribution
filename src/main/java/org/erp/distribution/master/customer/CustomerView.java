package org.erp.distribution.master.customer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.erp.distribution.master.customer.utility.CustomerUtilityModel;
import org.erp.distribution.master.customer.utility.CustomerUtilityPresenter;
import org.erp.distribution.master.customer.utility.CustomerUtilityView;
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
import com.vaadin.ui.themes.Reindeer;

public class CustomerView extends CustomComponent{
	private CustomerModel model;

	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	
	private TextField fieldId = new TextField("CUSTNO");
	private CheckBox checkOutletActive = new CheckBox("Outlet Active", false);
	private ComboBox comboGrup = new ComboBox("JENIS CUSTOMER");
	private TextField fieldDescription= new TextField("NAMA CUSTOMER");
	private TextField fieldNamaPadaFakturPajak= new TextField("Nama pada F. Pajak");
	private TextField fieldReportDesc= new TextField("REPORT DESC");
	private CheckBox checkStatusActive = new CheckBox("AKTIF", false);
	private CheckBox checkNoEffCall = new CheckBox("Tidak dihitung Eff.Call", false);

	private ComboBox comboChannel = new ComboBox("CHANNEL CUSTOMER");
	private ComboBox comboTerritory = new ComboBox("TERRITORY");
	
	//TAMBAHAN
	private ComboBox comboGrup2 = new ComboBox("SUB AREA/PASAR");
	private ComboBox comboTunaiKredit = new ComboBox("TUNAI/KREDIT");
	
	private TextField fieldTop = new TextField("TOP");
	private TextField fieldCreditlimit = new TextField("CR.LIMIT");
	private TextField fieldOpenlnvoice = new TextField("OPEN INV");
	
	private ComboBox comboHargaAlternatif = new ComboBox("HRG. ALT");
	
	private TextField fieldAddress1 = new TextField("ALAMAT");
	private TextField fieldCity1 = new TextField("KOTA");
	private TextField fieldState1 = new TextField("PROPINSI");	
	private TextField fieldPhone1 = new TextField("TELP");
	
	private TextField fieldAddress2 = new TextField("ALAMAT 2");
	private TextField fieldCity2 = new TextField("KOTA 2");
	private TextField fieldState2 = new TextField("PROPINSI 2");
	private TextField fieldPhone2 = new TextField("TELP 2");

	private TextField fieldEmail = new TextField("EMAIL");
	private TextField fieldNpwp = new TextField("NPWP");
	
	private TextField fieldHariKunjungan = new TextField("HARI KUNJUNGAN(1-7)");
	private TextField fieldPekanKunjungan = new TextField("PEKAN KUNJUNGAN(1-5)");

	private TextField fieldLatitude = new TextField("LATITUDE");
	private TextField fieldLongitude = new TextField("LONGITUDE");
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel");
	private Button btnNewForm= new Button("New");
	private Button btnEditForm= new Button("Edit");
	private Button btnDeleteForm= new Button("Delete");

	//Additional Component
	private ComboBox comboSearchByGrup = new ComboBox("JENIS CUSTOMER");
	private TextField fieldSearchById = new TextField("ID");
	private TextField fieldSearchByDesc = new TextField("NAMA CUSTOMER");
	private Button btnSearch = new Button("Search & Reload");
	private Button btnPrint = new Button("Print");
	private Button btnHelp = new Button("Help");
	private Button btnUtility= new Button("Utilitas");

	//LAYOUT
	private FormLayout formLayout = new FormLayout();
	
	//Panel
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelTabel = new Panel();
	private Panel panelForm = new Panel();

	//Help Manager	
	
	public CustomerView(CustomerModel model){
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
		fieldNamaPadaFakturPajak.setNullRepresentation("");
		fieldReportDesc.setNullRepresentation("");
		
		fieldId.setWidth("100px");
		comboGrup.setWidth("200px");
		comboGrup.setFilteringMode(FilteringMode.CONTAINS);
		comboGrup2.setWidth("200px");
		comboGrup2.setFilteringMode(FilteringMode.CONTAINS);
		comboTunaiKredit.setWidth("200px");
		comboTunaiKredit.setFilteringMode(FilteringMode.CONTAINS);
		fieldDescription.setWidth("400px");
		fieldNamaPadaFakturPajak.setWidth("400px");

		comboChannel.setWidth("200px");
		comboChannel.setFilteringMode(FilteringMode.CONTAINS);
		comboTerritory.setWidth("200px");
		comboTerritory.setFilteringMode(FilteringMode.CONTAINS);
		
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
		fieldTop.setWidth("100px");		
		fieldCreditlimit.setWidth("100px");		
		fieldOpenlnvoice.setWidth("100px");		

		comboHargaAlternatif.setWidth("200px");
		comboHargaAlternatif.setFilteringMode(FilteringMode.CONTAINS);
		comboHargaAlternatif.setNullSelectionAllowed(true);
		
		fieldAddress1.setWidth("400px");		
		fieldAddress2.setWidth("400px");		
		fieldCity1.setWidth("200px");		
		fieldCity2.setWidth("200px");		
		fieldState1.setWidth("200px");		
		fieldState2.setWidth("200px");		
		fieldPhone1.setWidth("400px");		
		fieldPhone2.setWidth("400px");		
		fieldNpwp.setWidth("400px");		
		fieldEmail.setWidth("400px");		

		fieldHariKunjungan.setWidth("200px");		
		fieldPekanKunjungan.setWidth("200px");		

		fieldLatitude.setWidth("200px");		
		fieldLongitude.setWidth("200px");		
		
		//VALIDATOR
		fieldId.setRequired(true);
		fieldDescription.setRequired(true);
		comboGrup.setRequired(true);
		comboGrup2.setRequired(true);
		comboTunaiKredit.setRequired(true);
		
//		comboChannel.setRequired(true);
//		comboTerritory.setRequired(true);
		
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
		layoutTop1.addComponent(btnUtility);
//		layoutTop1.addComponent(btnHelp);		
		
		layoutTop1.setComponentAlignment(fieldSearchByDesc, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnNewForm, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnEditForm, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnDeleteForm, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnPrint, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnUtility, Alignment.BOTTOM_CENTER);
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
	
		HorizontalLayout outletActiveLayout = new HorizontalLayout();
		outletActiveLayout.setCaption("CUSTNO-OA");
		fieldId.setCaption(null);
		outletActiveLayout.addComponent(fieldId);
		outletActiveLayout.addComponent(checkStatusActive);
		outletActiveLayout.addComponent(checkOutletActive);
		formLayout.addComponent(outletActiveLayout);
		
		formLayout.addComponent(fieldDescription);
		formLayout.addComponent(fieldNamaPadaFakturPajak);
		HorizontalLayout tunaiKreditAndTipeCustLayout = new HorizontalLayout();
		tunaiKreditAndTipeCustLayout.setCaption("T/K-TIPE CUSTOMER");
		comboTunaiKredit.setCaption(null);comboGrup.setCaption(null);
		tunaiKreditAndTipeCustLayout.addComponent(comboTunaiKredit);
		tunaiKreditAndTipeCustLayout.addComponent(comboGrup);
		formLayout.addComponent(tunaiKreditAndTipeCustLayout);
		
		formLayout.addComponent(comboGrup2);
		
		HorizontalLayout channelAndTerritoryLayout = new HorizontalLayout();
		channelAndTerritoryLayout.setCaption("CHANNEL-TERRITORY");
		comboChannel.setCaption(null);comboTerritory.setCaption(null);
		channelAndTerritoryLayout.addComponent(comboChannel);
		channelAndTerritoryLayout.addComponent(comboTerritory);
		formLayout.addComponent(channelAndTerritoryLayout);
		


		HorizontalLayout topCreditLimitLayout = new HorizontalLayout();
		topCreditLimitLayout.setCaption("TOP/CR.LIMIT/OPEN INV");
		topCreditLimitLayout.addComponent(fieldTop);
		topCreditLimitLayout.addComponent(fieldCreditlimit);		
		topCreditLimitLayout.addComponent(fieldOpenlnvoice);		
		formLayout.addComponent(topCreditLimitLayout);
		
		formLayout.addComponent(comboHargaAlternatif);
		
		formLayout.addComponent(fieldAddress1);

		HorizontalLayout cityAndStateLayout = new HorizontalLayout();
		cityAndStateLayout.setCaption("KOTA-PROPINSI");
		fieldCity1.setCaption(null);fieldState1.setCaption(null);
		cityAndStateLayout.addComponent(fieldCity1);
		cityAndStateLayout.addComponent(fieldState1);
		formLayout.addComponent(cityAndStateLayout);
		
		formLayout.addComponent(fieldPhone1);
		formLayout.addComponent(fieldNpwp);
		formLayout.addComponent(fieldEmail);

		HorizontalLayout kunjunganLayout = new HorizontalLayout();
		kunjunganLayout.setCaption("JADWAL KUNJUNGAN");
		kunjunganLayout.addComponent(fieldHariKunjungan);
		kunjunganLayout.addComponent(fieldPekanKunjungan);		
		formLayout.addComponent(kunjunganLayout);

		HorizontalLayout spasialLayout = new HorizontalLayout();
		spasialLayout.setCaption("Geo Tag");
		spasialLayout.addComponent(fieldLatitude);		
		spasialLayout.addComponent(fieldLongitude);
		formLayout.addComponent(spasialLayout);
		
		
		formLayout.addComponent(checkNoEffCall);
		
		HorizontalLayout formLayoutHorizontal = new HorizontalLayout();
		formLayoutHorizontal.setSpacing(true);
		formLayoutHorizontal.addComponent(btnSaveForm);
		formLayoutHorizontal.addComponent(btnCancelForm);
		formLayout.addComponent(formLayoutHorizontal);
		
		panelForm.setContent(formLayout);
		
		//MASUKKAN KE ROOT
		panelTop.setContent(layoutTop1);
		layoutTop.addComponent(panelTop);
		
		content.addComponent(layoutTop);
		content.addComponent(layoutTable);
		content.setExpandRatio(layoutTable, 1.0f);
		
		panelUtama.setContent(content);		
		setCompositionRoot(panelUtama);	
		
	}
	public void initComponentState(){
//		formLayout.setVisible(false);
//		btnHelp.setVisible(false);
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
			
			btnSaveForm.addStyleName("small");
			btnCancelForm.addStyleName("small");
			btnUtility.addStyleName("small");

//			btnAddItem.addStyleName("small");
//			btnEditItem.addStyleName("small");
//			btnRemoveItem.addStyleName("small");
			
//			btnSeparator1.addStyleName("small");
//			btnSeparator2.addStyleName("small");
		
		
//		}
		
//		tabSheet.addStyleName("framed compact-tabbar small");
//		tabSheet.addStyleName(Reindeer.TABSHEET_BORDERLESS);
//		tabSheet.addStyleName(Reindeer.TABSHEET_SMALL);
		
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
		table.setColumnFooter("custno", "*Record: " + items.size());
		
	}
	
	public void bindAndBuildFieldGroupComponent(){
		//Init isian combobox
		comboGrup.setContainerDataSource(model.getBeanItemContainerCustomergroup());
		comboGrup.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboGrup.setFilteringMode(FilteringMode.CONTAINS);
		comboGrup.setNullSelectionAllowed(false);

		comboGrup2.setContainerDataSource(model.getBeanItemContainerSubarea());
		comboGrup2.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboGrup2.setFilteringMode(FilteringMode.CONTAINS);
		comboGrup2.setNullSelectionAllowed(false);

		
		comboChannel.setContainerDataSource(model.getBeanItemContainerFChannel());
		comboChannel.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboChannel.setFilteringMode(FilteringMode.CONTAINS);
		comboChannel.setNullSelectionAllowed(false);

		comboTerritory.setContainerDataSource(model.getBeanItemContainerPTerritory());
		comboTerritory.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboTerritory.setFilteringMode(FilteringMode.CONTAINS);
		comboTerritory.setNullSelectionAllowed(false);
		
		comboTunaiKredit.addItem("T");
		comboTunaiKredit.setItemCaption("T", "T - Tunai");
		comboTunaiKredit.addItem("K");
		comboTunaiKredit.setItemCaption("K", "K - Kredit");
		comboTunaiKredit.setNullSelectionAllowed(false);

		comboHargaAlternatif.setContainerDataSource(model.getBeanItemContainerFtPriceAlth());
//		comboHargaAlternatif.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboHargaAlternatif.setItemCaptionPropertyId("description");
		comboHargaAlternatif.setFilteringMode(FilteringMode.CONTAINS);
		comboHargaAlternatif.setNullSelectionAllowed(true);
		
		model.getBinderHeader().bind(fieldId, "custno");
		model.getBinderHeader().bind(checkOutletActive, "outletActive");
		model.getBinderHeader().bind(fieldDescription, "custname");
		model.getBinderHeader().bind(fieldNamaPadaFakturPajak, "namaPadaFakturPajak");
		model.getBinderHeader().bind(comboGrup, "fcustomersubgroupBean");
		model.getBinderHeader().bind(comboGrup2, "fsubareaBean");
		model.getBinderHeader().bind(comboTunaiKredit, "tunaikredit");
		
		model.getBinderHeader().bind(fieldTop, "top");
		model.getBinderHeader().bind(fieldCreditlimit, "creditlimit");
		model.getBinderHeader().bind(fieldOpenlnvoice, "openinvoice");
		model.getBinderHeader().bind(comboHargaAlternatif, "ftPriceAlthBean");
		model.getBinderHeader().bind(fieldAddress1, "address1");
		model.getBinderHeader().bind(fieldAddress2, "address2");
		model.getBinderHeader().bind(fieldCity1, "city1");
		model.getBinderHeader().bind(fieldCity2, "city2");
		model.getBinderHeader().bind(fieldState1, "state1");
		model.getBinderHeader().bind(fieldState2, "state2");
		model.getBinderHeader().bind(fieldPhone1, "phone1");
		model.getBinderHeader().bind(fieldPhone2, "phone2");
		model.getBinderHeader().bind(fieldNpwp, "npwp");
		model.getBinderHeader().bind(fieldEmail, "email");
		
		model.getBinderHeader().bind(fieldHariKunjungan, "harikunjungan");
		model.getBinderHeader().bind(fieldPekanKunjungan, "pekankunjungan");
		model.getBinderHeader().bind(fieldLatitude, "latitude");
		model.getBinderHeader().bind(fieldLongitude, "longitude");
		
		model.getBinderHeader().bind(checkStatusActive, "statusactive");
		model.getBinderHeader().bind(checkNoEffCall, "noeffcall");
		
	}
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){

		setVisibleTableProperties("custno", "custname","tunaikredit" , "fcustomersubgroupBean", "fsubareaBean");
		
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
		table.setColumnHeader("custno", "ID");
		table.setColumnHeader("custname", "NAMA CUSTOMER");
		table.setColumnHeader("tunaikredit", "T/K");
		table.setColumnHeader("fcustomersubgroupBean", "JENIS");
		table.setColumnHeader("fareaBean", "SUB AREA/PASAR");
		
		
		
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
		windowForm.setWidth("800px");
		windowForm.setHeight("750px");
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

	private Window windowUtility = new Window();
	
	private CustomerUtilityModel productUtilityModel;
	private CustomerUtilityView productUtilityView;
	private CustomerUtilityPresenter productUtilityPresenter;
	
	public void showWindowUtility(){
		windowForm = new Window();
		windowForm.setModal(true);
		windowForm.center();
		
//		windowForm.setStyleName("login-layout");
		windowForm.setWidth("750px");
		windowForm.setHeight("500px");
		windowForm.setClosable(true);	
		windowForm.setResizable(false);

		productUtilityModel = new CustomerUtilityModel();
		productUtilityView = new CustomerUtilityView(productUtilityModel);		
		productUtilityPresenter = new CustomerUtilityPresenter(productUtilityModel, productUtilityView);
		productUtilityView.setSizeFull();
		
		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSizeFull();
		content.addComponent(productUtilityView);
		
		windowForm.setContent(content);
		windowForm.center();
		getUI().addWindow(windowForm);
		
	}
	public void closeWindowUtility(){
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

	public CustomerModel getModel() {
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

	public void setModel(CustomerModel model) {
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

	public ComboBox getComboGrup2() {
		return comboGrup2;
	}

	public ComboBox getComboTunaiKredit() {
		return comboTunaiKredit;
	}

	public TextField getFieldTop() {
		return fieldTop;
	}

	public TextField getFieldCreditlimit() {
		return fieldCreditlimit;
	}

	public TextField getFieldOpenlnvoice() {
		return fieldOpenlnvoice;
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

	public TextField getFieldPhone1() {
		return fieldPhone1;
	}

	public TextField getFieldAddress2() {
		return fieldAddress2;
	}

	public TextField getFieldCity2() {
		return fieldCity2;
	}

	public TextField getFieldState2() {
		return fieldState2;
	}

	public TextField getFieldPhone2() {
		return fieldPhone2;
	}

	public TextField getFieldEmail() {
		return fieldEmail;
	}

	public TextField getFieldNpwp() {
		return fieldNpwp;
	}

	public Button getBtnUtility() {
		return btnUtility;
	}

	public Window getWindowUtility() {
		return windowUtility;
	}

	public CustomerUtilityModel getProductUtilityModel() {
		return productUtilityModel;
	}

	public CustomerUtilityView getProductUtilityView() {
		return productUtilityView;
	}

	public CustomerUtilityPresenter getProductUtilityPresenter() {
		return productUtilityPresenter;
	}

	public void setComboGrup2(ComboBox comboGrup2) {
		this.comboGrup2 = comboGrup2;
	}

	public void setComboTunaiKredit(ComboBox comboTunaiKredit) {
		this.comboTunaiKredit = comboTunaiKredit;
	}

	public void setFieldTop(TextField fieldTop) {
		this.fieldTop = fieldTop;
	}

	public void setFieldCreditlimit(TextField fieldCreditlimit) {
		this.fieldCreditlimit = fieldCreditlimit;
	}

	public void setFieldOpenlnvoice(TextField fieldOpenlnvoice) {
		this.fieldOpenlnvoice = fieldOpenlnvoice;
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

	public void setFieldPhone1(TextField fieldPhone1) {
		this.fieldPhone1 = fieldPhone1;
	}

	public void setFieldAddress2(TextField fieldAddress2) {
		this.fieldAddress2 = fieldAddress2;
	}

	public void setFieldCity2(TextField fieldCity2) {
		this.fieldCity2 = fieldCity2;
	}

	public void setFieldState2(TextField fieldState2) {
		this.fieldState2 = fieldState2;
	}

	public void setFieldPhone2(TextField fieldPhone2) {
		this.fieldPhone2 = fieldPhone2;
	}

	public void setFieldEmail(TextField fieldEmail) {
		this.fieldEmail = fieldEmail;
	}

	public void setFieldNpwp(TextField fieldNpwp) {
		this.fieldNpwp = fieldNpwp;
	}

	public void setBtnUtility(Button btnUtility) {
		this.btnUtility = btnUtility;
	}

	public void setWindowUtility(Window windowUtility) {
		this.windowUtility = windowUtility;
	}

	public void setProductUtilityModel(CustomerUtilityModel productUtilityModel) {
		this.productUtilityModel = productUtilityModel;
	}

	public void setProductUtilityView(CustomerUtilityView productUtilityView) {
		this.productUtilityView = productUtilityView;
	}

	public void setProductUtilityPresenter(
			CustomerUtilityPresenter productUtilityPresenter) {
		this.productUtilityPresenter = productUtilityPresenter;
	}

	

	
}
