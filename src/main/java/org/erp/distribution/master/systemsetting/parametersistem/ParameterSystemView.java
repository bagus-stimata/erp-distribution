package org.erp.distribution.master.systemsetting.parametersistem;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.erp.distribution.model.modelenum.EnumOperationStatus;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.themes.Reindeer;

public class ParameterSystemView extends CustomComponent {
	private ParameterSystemModel model;
	
	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	
	private TextField fieldIdSysvar = new TextField("ID SYSVAR");
	private ComboBox comboDivision = new ComboBox("DIVISI");
	private TextField fieldGroupSysvar = new TextField("GROUP");
	private TextField fieldDeskripsi = new TextField("DESCRIPSI");
	private TextField fieldNotes = new TextField("NOTES");

	private ComboBox comboTypeData = new ComboBox("TIPE DATA");
	private TextField fieldLenghtData = new TextField("PANJANG DATA");	
	private TextField fieldPrefix = new TextField("PREFIX");	
	
	private TextField fieldNilaiString1 = new TextField("NILAI 1(ALFABET)");
	private TextField fieldNilaiString2 = new TextField("NILAI 2(ALFABET)");
	private TextField fieldNilaiBol1 = new TextField("NILAI BOOLEAN 1 (TRUE/FALSE)");
	private TextField fieldNilaiBol2 = new TextField("NILAI BOOLEAN 2 (TRUE/FALSE)");
	private CheckBox checkNilaiBol1 = new CheckBox("NILAI BOOLEAN 1 (TRUE/FALSE)");
	private CheckBox checkNilaiBol2 = new CheckBox("NILAI BOOLEAN 2 (TRUE/FALSE)");
	
	private TextField fieldNilaiInt1 = new TextField("NILAI INTEGER 1 (BULAT)");
	private TextField fieldNilaiInt2 = new TextField("NILAI INTEGER 2 (BULAT)");
	private TextField fieldNilaiDouble1 = new TextField("NILAI DOUBLE (RUPIAH)");
	private TextField fieldNilaiDouble2 = new TextField("NILAI DOUBLE (RUPIAH)");

	private DateField dateFieldNilaiTime1 = new DateField("MULAI (TIME)");
	private DateField dateFieldNilaiTime2 = new DateField("S/D (TIME)");
	private DateField dateFieldNilaiDate1 = new DateField("MULAI (TANGGAL)");
	private DateField dateFieldNilaiDate2 = new DateField("S/D (TANGGAL)");
	
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel");
	private Button btnAddForm= new Button("Add New");
	private Button btnDeleteForm= new Button("Delete");

	//Additional Component
	private ComboBox comboSearchByDivision = new ComboBox("DIVISI");
	private TextField fieldSearchByGroup = new TextField("GROUP");
	private TextField fieldSearchByDescription = new TextField("DESC");
	private Button btnSearch = new Button("Search");
	private Button btnPemeliharaanParameter = new Button("Pemeliharaan Parameter");
	private Button btnPrint = new Button("Print");
	private Button btnHelp = new Button("Help");
	private Button btnSeparator1 = new Button(".");
	private Button btnSeparator2 = new Button(".");

	//LAYOUT
	private FormLayout formLayout = new FormLayout();
	
	//Panel
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelTabel = new Panel();
	private Panel panelForm = new Panel();

	
	public ParameterSystemView(ParameterSystemModel model){
		this.model = model;
		
		initComponent();
		buildView();
		
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
			        		return "OK";
			        	} else {
			        		return "-";
			        	}
			        }
		        } catch(Exception ex){}
		        
		        return super.formatPropertyValue(rowId, colId, property);
		    }
		};		
		

		fieldIdSysvar.setWidth("200px");
		comboDivision.setWidth("200px");
		fieldGroupSysvar.setWidth("200px");
		fieldDeskripsi.setWidth("400px");
		fieldNotes.setWidth("400px");

		comboTypeData.setWidth("200px");
		fieldLenghtData.setWidth("200px");
		fieldPrefix.setWidth("200px");
		
		fieldNilaiString1.setWidth("400px");
		fieldNilaiString2.setWidth("400px");
		fieldNilaiBol1.setWidth("200px");
		fieldNilaiBol2.setWidth("200px");
		checkNilaiBol1.setWidth("200px");
		checkNilaiBol2.setWidth("200px");
		
		fieldNilaiInt1.setWidth("200px");
		fieldNilaiInt2.setWidth("200px");
		fieldNilaiDouble1.setWidth("200px");
		fieldNilaiDouble2.setWidth("200px");

		dateFieldNilaiTime1.setWidth("200px");
		dateFieldNilaiTime2.setWidth("200px");
		dateFieldNilaiDate1.setWidth("200px");
		dateFieldNilaiDate1.setDateFormat("dd/MM/yyyy");
		dateFieldNilaiDate2.setWidth("200px");
		dateFieldNilaiDate2.setDateFormat("dd/MM/yyyy");

		btnSearch.setIcon(new ThemeResource("../images/navigation/12x12/Find.png"));

		btnAddForm.setIcon(new ThemeResource("../images/navigation/12x12/Create.png"));
//		btnEditForm.setIcon(new ThemeResource("../images/navigation/12x12/Pencil.png"));
		btnDeleteForm.setIcon(new ThemeResource("../images/navigation/12x12/Erase.png"));
		btnPrint.setIcon(new ThemeResource("../images/navigation/12x12/Print.png"));
		
		btnSaveForm.setIcon(new ThemeResource("../images/navigation/12x12/Save.png"));
		btnCancelForm.setIcon(new ThemeResource("../images/navigation/12x12/Undo.png"));

//		btnAddItem.setIcon(new ThemeResource("../images/navigation/12x12/Create.png"));
//		btnEditForm.setIcon(new ThemeResource("../images/navigation/12x12/Pencil.png"));
//		btnRemoveItem.setIcon(new ThemeResource("../images/navigation/12x12/Erase.png"));
		
		
		
	}
	public void buildView(){
		
		//Inisialisasi Panel 
		setSizeFull();
		content.setSizeFull();
		content.setMargin(true);
		
		//PANEL
		panelUtama.setSizeFull();
		panelTabel.setSizeFull();
		
		//INIT COMPONENT ATAS
		btnSeparator1.setEnabled(false);
		btnSeparator2.setEnabled(false);
		
		//INIT COMPONENT TENGAH
		table.setSizeFull();
		table.setSelectable(true);
		table.setImmediate(true);
		table.setBuffered(false);
		table.setFooterVisible(true);
		
		//INIT COMPONENT BAWAH	
		
		//DEKLARASI LAYOUT
		//KOMPONEN ATAS
		HorizontalLayout layoutTop = new HorizontalLayout();		
//		layoutTop.addComponent(fieldSearchById);
//		layoutTop.addComponent(comboSearchByDivision);		
//		layoutTop.addComponent(fieldSearchByGroup);
		layoutTop.addComponent(fieldSearchByDescription);
		
		layoutTop.addComponent(btnSearch);
		layoutTop.addComponent(btnSeparator1);
		layoutTop.addComponent(btnAddForm);
		layoutTop.addComponent(btnDeleteForm);
		layoutTop.addComponent(btnSeparator2);
		layoutTop.addComponent(btnPemeliharaanParameter);
		layoutTop.addComponent(btnPrint);
		layoutTop.addComponent(btnHelp);		
		
		layoutTop.setComponentAlignment(fieldSearchByDescription, Alignment.BOTTOM_CENTER);
//		layoutTop.setComponentAlignment(fieldSearchByGroup, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnSeparator1, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnAddForm, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnDeleteForm, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnSeparator2, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnPemeliharaanParameter, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnPrint, Alignment.BOTTOM_CENTER);
		layoutTop.setComponentAlignment(btnHelp, Alignment.BOTTOM_CENTER);
		
		//KOMPONEN TENGAH
		VerticalLayout middleLayout = new VerticalLayout();
		middleLayout.setSizeFull();
		middleLayout.addComponent(table);

		//KOMPONEN BAWAH
		formLayout.addComponent(fieldIdSysvar);
//		formLayout.addComponent(comboDivision);
		formLayout.addComponent(fieldGroupSysvar);
		formLayout.addComponent(fieldDeskripsi);
//		formLayout.addComponent(fieldNotes);

		formLayout.addComponent(comboTypeData);
		formLayout.addComponent(fieldLenghtData);		
		formLayout.addComponent(fieldPrefix);
		
		formLayout.addComponent(fieldNilaiString1);
		formLayout.addComponent(fieldNilaiString2);
		
		formLayout.addComponent(fieldNilaiBol1);
		formLayout.addComponent(fieldNilaiBol2);
		formLayout.addComponent(checkNilaiBol1);
		formLayout.addComponent(checkNilaiBol2);
		
		formLayout.addComponent(fieldNilaiInt1);
		formLayout.addComponent(fieldNilaiInt2);
		formLayout.addComponent(fieldNilaiDouble1);
		formLayout.addComponent(fieldNilaiDouble2);
		formLayout.addComponent(dateFieldNilaiDate1);
		formLayout.addComponent(dateFieldNilaiDate2);
		formLayout.addComponent(dateFieldNilaiTime1);
		formLayout.addComponent(dateFieldNilaiTime2);
		
		HorizontalLayout formLayoutHorizontal = new HorizontalLayout();
		formLayoutHorizontal.setSpacing(true);
		formLayoutHorizontal.setMargin(true);
		formLayoutHorizontal.addComponent(btnSaveForm);
		formLayoutHorizontal.setComponentAlignment(btnSaveForm, Alignment.BOTTOM_LEFT);
		formLayoutHorizontal.addComponent(btnCancelForm);
		formLayoutHorizontal.setComponentAlignment(btnCancelForm, Alignment.BOTTOM_LEFT);
		
		formLayout.addComponent(formLayoutHorizontal);
		
		//MASUKKAN KE PANEL
		panelTop.setContent(layoutTop);		
		panelTabel.setContent(middleLayout);
		panelForm.setContent(formLayout);

		//VERTICAL SPLIT PANE
		VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
		verticalSplitPanel.setSizeFull();		
		verticalSplitPanel.setSplitPosition(40);
		
		verticalSplitPanel.setFirstComponent(panelTabel);		
		verticalSplitPanel.setSecondComponent(panelForm);
		
		//MASUKKAN KE ROOT
		content.addComponent(panelTop);
		content.addComponent(verticalSplitPanel);
		
		panelUtama.setContent(content);
		panelUtama.setSizeFull();
		setCompositionRoot(panelUtama);	
		content.setExpandRatio(verticalSplitPanel, 1);
		
		//init
		formLayout.setVisible(false);
		
	}
	public void setDisplaySearchComponent(){
//		getComboSearchByDivision().setContainerDataSource(model.getBeanItemContainerDivision());
		
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
		btnAddForm.addStyleName("small");
		btnHelp.addStyleName("small");
		btnDeleteForm.addStyleName("small");
		btnPrint.addStyleName("small");
		
		btnPemeliharaanParameter.addStyleName("small");
		
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

	
	public void setDisplayHeader(){
		
	}
	public void setDisplay(){
		//TABLE
		table.setContainerDataSource(model.getBeanItemContainerSysvar());
		
		setDisplaySearchComponent();
		
		setTableProperties();
		
		setDisplayTableFooter();
		
		bindAndBuildFieldGroupComponent();
		
	}
	public void setDisplayTableFooter(){
		Collection items =  model.getBeanItemContainerSysvar().getItemIds();
		table.setColumnFooter("id", "*Jumlah Record: " + items.size());
		
	}
	
	public void bindAndBuildFieldGroupComponent(){
//		comboSearchByDivision.setContainerDataSource(model.getBeanItemContainerDivision());
//		comboSearchByDivision.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
//		comboDivision.setNullSelectionAllowed(true);
//		
//		comboDivision.setContainerDataSource(model.getBeanItemContainerDivision());
//		comboDivision.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
//		comboDivision.setNullSelectionAllowed(false);
		
		//Init isian combobox
		String dataTypes[] = {"STRING1", "STRING2", "BOL1", "BOL2", "INT1", "INT2", 
				"DOUBLE1", "DOUBLE2", "DATE1", "DATE2", "TIME1", "TIME2"};
		for (int i=0; i<dataTypes.length; i++)
			comboTypeData.addItem(dataTypes[i]);
		
		comboTypeData.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);			
		comboTypeData.setNullSelectionAllowed(false);
		
		model.getBinderSysvar().bind(fieldIdSysvar, "id");
//		model.getBinderSysvar().bind(comboDivision, "divisionBean");		
		model.getBinderSysvar().bind(fieldGroupSysvar, "groupSysvar");
		model.getBinderSysvar().bind(fieldDeskripsi, "deskripsi");
		model.getBinderSysvar().bind(fieldNotes, "notes");
		
		model.getBinderSysvar().bind(comboTypeData, "tipeData");		
		model.getBinderSysvar().bind(fieldLenghtData, "lenghtData");
		
		model.getBinderSysvar().bind(fieldNilaiString1, "nilaiString1");
		model.getBinderSysvar().bind(fieldNilaiString2, "nilaiString2");
		
//		model.getBinderSysvar().bind(fieldNilaiBol1, "nilaiBol1");
//		model.getBinderSysvar().bind(fieldNilaiBol2, "nilaiBol2");
		model.getBinderSysvar().bind(checkNilaiBol1, "nilaiBol1");
		model.getBinderSysvar().bind(checkNilaiBol2, "nilaiBol2");
		
		model.getBinderSysvar().bind(fieldNilaiInt1, "nilaiInt1");
		model.getBinderSysvar().bind(fieldNilaiInt2, "nilaiInt2");
		model.getBinderSysvar().bind(fieldNilaiDouble1, "nilaiDouble1");
		model.getBinderSysvar().bind(fieldNilaiDouble2, "nilaiDouble2");
		
		model.getBinderSysvar().bind(dateFieldNilaiDate1, "nilaiDate1");
		model.getBinderSysvar().bind(dateFieldNilaiDate2, "nilaiDate2");
		model.getBinderSysvar().bind(dateFieldNilaiTime1, "nilaiTime1");
		model.getBinderSysvar().bind(dateFieldNilaiTime2, "nilaiTime2");
		
	}
	
	public void bindAndBuildFieldGroupComponentVisibility(){
		//DI VISIBLE FALSE DULU
		fieldNilaiString1.setVisible(false);
		fieldNilaiString2.setVisible(false);
		
		fieldNilaiBol1.setVisible(false);
		fieldNilaiBol2.setVisible(false);
		checkNilaiBol1.setVisible(false);
		checkNilaiBol2.setVisible(false);
		
		fieldNilaiInt1.setVisible(false);
		fieldNilaiInt2.setVisible(false);
		fieldNilaiDouble1.setVisible(false);
		fieldNilaiDouble2.setVisible(false);
		dateFieldNilaiDate1.setVisible(false);
		dateFieldNilaiDate2.setVisible(false);
		dateFieldNilaiTime1.setVisible(false);
		dateFieldNilaiTime2.setVisible(false);
		
		fieldLenghtData.setVisible(false);
		
		if (comboTypeData.getValue().toString().contains("STRING")) {
			fieldNilaiString1.setVisible(true);
			fieldLenghtData.setVisible(true);
		}
		if (comboTypeData.getValue().toString().equals("STRING2"))
			fieldNilaiString2.setVisible(true);
		
//		if (comboTypeData.getValue().toString().contains("BOL")) 
//			fieldNilaiBol1.setVisible(true);
//		if (comboTypeData.getValue().toString().equals("BOL2"))
//			fieldNilaiBol2.setVisible(true);
		if (comboTypeData.getValue().toString().contains("BOL")) 
			checkNilaiBol1.setVisible(true);
		if (comboTypeData.getValue().toString().equals("BOL2"))
			checkNilaiBol2.setVisible(true);
		
		if (comboTypeData.getValue().toString().contains("INT")) 
			fieldNilaiInt1.setVisible(true);
		if (comboTypeData.getValue().toString().equals("INT2"))
			fieldNilaiInt2.setVisible(true);
		if (comboTypeData.getValue().toString().contains("DOUBLE")) 
			fieldNilaiDouble1.setVisible(true);
		if (comboTypeData.getValue().toString().equals("DOUBLE2"))
			fieldNilaiDouble2.setVisible(true);
		if (comboTypeData.getValue().toString().contains("DATE")) 
			dateFieldNilaiDate1.setVisible(true);
		if (comboTypeData.getValue().toString().equals("DATE2"))
			dateFieldNilaiDate2.setVisible(true);
		if (comboTypeData.getValue().toString().contains("TIME")) 
			dateFieldNilaiTime1.setVisible(true);
		if (comboTypeData.getValue().toString().equals("TIME2"))
			dateFieldNilaiTime2.setVisible(true);
		
	}
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){

		setVisibleTableProperties("id", "groupSysvar", "deskripsi", 
				"notes", "tipeData", "lenghtData", "prefix", 
				"nilaiString1", "nilaiString2", "nilaiBol1", "nilaiBol2", 
				"nilaiInt1", "nilaiInt2", "nilaiDouble1", "nilaiDouble2", 
				"nilaiDate1", "nilaiDate2", "nilaiTime1", "nilaiTime2" );
		
		table.setColumnCollapsingAllowed(true);
		try{
			table.setColumnCollapsed("notes", true);
			table.setColumnCollapsed("nilaiString1", true);
			table.setColumnCollapsed("nilaiString2", true);
			table.setColumnCollapsed("nilaiBol1", true);
			table.setColumnCollapsed("nilaiBol2", true);
			table.setColumnCollapsed("nilaiInt1", true);
			table.setColumnCollapsed("nilaiInt2", true);
			table.setColumnCollapsed("nilaiDouble1", true);
			table.setColumnCollapsed("nilaiDouble2", true);
			table.setColumnCollapsed("nilaiDate1", true);
			table.setColumnCollapsed("nilaiDate2", true);
			table.setColumnCollapsed("nilaiTime1", true);
			table.setColumnCollapsed("nilaiTime2", true);
			
		} catch(Exception ex){}
		
		//ALIGNMENT
		table.setColumnAlignment("nilaiDouble1", Align.RIGHT);
		table.setColumnAlignment("nilaiDouble2", Align.RIGHT);
		table.setColumnAlignment("nilaiInt1", Align.RIGHT);
		table.setColumnAlignment("nilaiInt2", Align.RIGHT);
		
		table.setColumnAlignment("lenghtData", Align.RIGHT);
		table.setColumnAlignment("tipeData", Align.CENTER);
		
		table.setColumnAlignment("nilaiDate1", Align.CENTER);
		table.setColumnAlignment("nilaiDate2", Align.CENTER);
		table.setColumnAlignment("nilaiTime1", Align.CENTER);
		table.setColumnAlignment("nilaiTime2", Align.CENTER);
		
		//set header
		table.setColumnHeader("id", "DIV-ID");
		table.setColumnHeader("groupSysvar", "GROUP");
		table.setColumnHeader("deskripsi", "DESC");
		table.setColumnHeader("notes", "NOTES");
		table.setColumnHeader("tipeData", "TIPE DATA");
		table.setColumnHeader("lenghtData", "PANJANG DATA");
		table.setColumnHeader("prefix", "PREFIX");
		
		table.setColumnHeader("nilaiString1", "NILAI STRING 1");
		table.setColumnHeader("nilaiString2", "NIALI STRING 2");
		table.setColumnHeader("nilaiBol1", "NILAI BOOLEAN 1");
		table.setColumnHeader("nilaiBol2", "NILAI BOOLEAN 2");
		table.setColumnHeader("nilaiInt1", "NILAI INTEGER 1");
		table.setColumnHeader("nilaiInt2", "NILAI INTEGER 2");
		table.setColumnHeader("nilaiDouble1", "NILAI DOUBLE 1");
		table.setColumnHeader("nilaiDouble2", "NILAI DOUBLE 2");
		table.setColumnHeader("nilaiDate1", "NILAI TANGGAL 1");
		table.setColumnHeader("nilaiDate2", "NILAI TANGGAL 2");
		table.setColumnHeader("nilaiTime1", "NILAI TIME 1");
		table.setColumnHeader("nilaiTime2", "NILAI TIME 2");
		
		
//		table.setColumnExpandRatio("selected", 2);
//		table.setColumnExpandRatio("recapno", 3);
				
	}
	
	public void setFormButtonAndTextState(){
		//KODE REFNO SELALU READ ONLY KARENA OTOMATIS
//		fieldIdSysvar.setReadOnly(true);
		
		if (model.getOperationStatus().equals(EnumOperationStatus.OPEN.getStrCode())){
			fieldIdSysvar.setReadOnly(true);
			
			formLayout.setVisible(false);
			table.setSelectable(true);
			btnAddForm.setEnabled(true);
			btnDeleteForm.setEnabled(true);			
			btnSearch.setEnabled(true);
		} else if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
			fieldIdSysvar.setReadOnly(false);

			formLayout.setVisible(true);
			table.setSelectable(false);
			btnAddForm.setEnabled(false);
			btnDeleteForm.setEnabled(false);
			btnSearch.setEnabled(false);
		}else if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
			fieldIdSysvar.setReadOnly(true);
			
			formLayout.setVisible(true);
			table.setSelectable(true);
			btnAddForm.setEnabled(true);
			btnDeleteForm.setEnabled(true);			
			btnSearch.setEnabled(true);
		}		
		
	}
	
	public void focustIdOrDesc(){
		if (fieldIdSysvar.isEnabled()){
			fieldIdSysvar.focus();
		} else {
			fieldGroupSysvar.focus();		                    						
		}
		
	}

	public ParameterSystemModel getModel() {
		return model;
	}

	public void setModel(ParameterSystemModel model) {
		this.model = model;
	}

	public VerticalLayout getContent() {
		return content;
	}

	public void setContent(VerticalLayout content) {
		this.content = content;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public TextField getFieldIdSysvar() {
		return fieldIdSysvar;
	}

	public void setFieldIdSysvar(TextField fieldIdSysvar) {
		this.fieldIdSysvar = fieldIdSysvar;
	}

	public TextField getFieldGroupSysvar() {
		return fieldGroupSysvar;
	}

	public void setFieldGroupSysvar(TextField fieldGroupSysvar) {
		this.fieldGroupSysvar = fieldGroupSysvar;
	}

	public TextField getFieldDeskripsi() {
		return fieldDeskripsi;
	}

	public void setFieldDeskripsi(TextField fieldDeskripsi) {
		this.fieldDeskripsi = fieldDeskripsi;
	}

	public TextField getFieldNotes() {
		return fieldNotes;
	}

	public void setFieldNotes(TextField fieldNotes) {
		this.fieldNotes = fieldNotes;
	}

	public ComboBox getComboTypeData() {
		return comboTypeData;
	}

	public void setComboTypeData(ComboBox comboTypeData) {
		this.comboTypeData = comboTypeData;
	}

	public TextField getFieldLenghtData() {
		return fieldLenghtData;
	}

	public void setFieldLenghtData(TextField fieldLenghtData) {
		this.fieldLenghtData = fieldLenghtData;
	}

	public TextField getFieldPrefix() {
		return fieldPrefix;
	}

	public void setFieldPrefix(TextField fieldPrefix) {
		this.fieldPrefix = fieldPrefix;
	}

	public TextField getFieldNilaiString1() {
		return fieldNilaiString1;
	}

	public void setFieldNilaiString1(TextField fieldNilaiString1) {
		this.fieldNilaiString1 = fieldNilaiString1;
	}

	public TextField getFieldNilaiString2() {
		return fieldNilaiString2;
	}

	public void setFieldNilaiString2(TextField fieldNilaiString2) {
		this.fieldNilaiString2 = fieldNilaiString2;
	}

	public TextField getFieldNilaiBol1() {
		return fieldNilaiBol1;
	}

	public void setFieldNilaiBol1(TextField fieldNilaiBol1) {
		this.fieldNilaiBol1 = fieldNilaiBol1;
	}

	public TextField getFieldNilaiBol2() {
		return fieldNilaiBol2;
	}

	public void setFieldNilaiBol2(TextField fieldNilaiBol2) {
		this.fieldNilaiBol2 = fieldNilaiBol2;
	}

	public TextField getFieldNilaiInt1() {
		return fieldNilaiInt1;
	}

	public void setFieldNilaiInt1(TextField fieldNilaiInt1) {
		this.fieldNilaiInt1 = fieldNilaiInt1;
	}

	public TextField getFieldNilaiInt2() {
		return fieldNilaiInt2;
	}

	public void setFieldNilaiInt2(TextField fieldNilaiInt2) {
		this.fieldNilaiInt2 = fieldNilaiInt2;
	}

	public TextField getFieldNilaiDouble1() {
		return fieldNilaiDouble1;
	}

	public void setFieldNilaiDouble1(TextField fieldNilaiDouble1) {
		this.fieldNilaiDouble1 = fieldNilaiDouble1;
	}

	public TextField getFieldNilaiDouble2() {
		return fieldNilaiDouble2;
	}

	public void setFieldNilaiDouble2(TextField fieldNilaiDouble2) {
		this.fieldNilaiDouble2 = fieldNilaiDouble2;
	}

	public DateField getDateFieldNilaiTime1() {
		return dateFieldNilaiTime1;
	}

	public void setDateFieldNilaiTime1(DateField dateFieldNilaiTime1) {
		this.dateFieldNilaiTime1 = dateFieldNilaiTime1;
	}

	public DateField getDateFieldNilaiTime2() {
		return dateFieldNilaiTime2;
	}

	public void setDateFieldNilaiTime2(DateField dateFieldNilaiTime2) {
		this.dateFieldNilaiTime2 = dateFieldNilaiTime2;
	}

	public DateField getDateFieldNilaiDate1() {
		return dateFieldNilaiDate1;
	}

	public void setDateFieldNilaiDate1(DateField dateFieldNilaiDate1) {
		this.dateFieldNilaiDate1 = dateFieldNilaiDate1;
	}

	public DateField getDateFieldNilaiDate2() {
		return dateFieldNilaiDate2;
	}

	public void setDateFieldNilaiDate2(DateField dateFieldNilaiDate2) {
		this.dateFieldNilaiDate2 = dateFieldNilaiDate2;
	}

	public Button getBtnSaveForm() {
		return btnSaveForm;
	}

	public void setBtnSaveForm(Button btnSaveForm) {
		this.btnSaveForm = btnSaveForm;
	}

	public Button getBtnCancelForm() {
		return btnCancelForm;
	}

	public void setBtnCancelForm(Button btnCancelForm) {
		this.btnCancelForm = btnCancelForm;
	}

	public Button getBtnAddForm() {
		return btnAddForm;
	}

	public void setBtnAddForm(Button btnAddForm) {
		this.btnAddForm = btnAddForm;
	}

	public Button getBtnDeleteForm() {
		return btnDeleteForm;
	}

	public void setBtnDeleteForm(Button btnDeleteForm) {
		this.btnDeleteForm = btnDeleteForm;
	}


	public TextField getFieldSearchByDescription() {
		return fieldSearchByDescription;
	}

	public void setFieldSearchByDescription(TextField fieldSearchByDescription) {
		this.fieldSearchByDescription = fieldSearchByDescription;
	}

	public TextField getFieldSearchByGroup() {
		return fieldSearchByGroup;
	}

	public void setFieldSearchByGroup(TextField fieldSearchByGroup) {
		this.fieldSearchByGroup = fieldSearchByGroup;
	}

	public Button getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(Button btnSearch) {
		this.btnSearch = btnSearch;
	}

	public Button getBtnPrint() {
		return btnPrint;
	}

	public void setBtnPrint(Button btnPrint) {
		this.btnPrint = btnPrint;
	}

	public Button getBtnHelp() {
		return btnHelp;
	}

	public void setBtnHelp(Button btnHelp) {
		this.btnHelp = btnHelp;
	}

	public Button getBtnSeparator1() {
		return btnSeparator1;
	}

	public void setBtnSeparator1(Button btnSeparator1) {
		this.btnSeparator1 = btnSeparator1;
	}

	public Button getBtnSeparator2() {
		return btnSeparator2;
	}

	public void setBtnSeparator2(Button btnSeparator2) {
		this.btnSeparator2 = btnSeparator2;
	}

	public FormLayout getFormLayout() {
		return formLayout;
	}

	public void setFormLayout(FormLayout formLayout) {
		this.formLayout = formLayout;
	}

	public Panel getPanelUtama() {
		return panelUtama;
	}

	public void setPanelUtama(Panel panelUtama) {
		this.panelUtama = panelUtama;
	}

	public Panel getPanelTop() {
		return panelTop;
	}

	public void setPanelTop(Panel panelTop) {
		this.panelTop = panelTop;
	}

	public Panel getPanelTabel() {
		return panelTabel;
	}

	public void setPanelTabel(Panel panelTabel) {
		this.panelTabel = panelTabel;
	}

	public Panel getPanelForm() {
		return panelForm;
	}

	public void setPanelForm(Panel panelForm) {
		this.panelForm = panelForm;
	}


	public ComboBox getComboDivision() {
		return comboDivision;
	}

	public void setComboDivision(ComboBox comboDivision) {
		this.comboDivision = comboDivision;
	}

	public CheckBox getCheckNilaiBol1() {
		return checkNilaiBol1;
	}

	public void setCheckNilaiBol1(CheckBox checkNilaiBol1) {
		this.checkNilaiBol1 = checkNilaiBol1;
	}

	public CheckBox getCheckNilaiBol2() {
		return checkNilaiBol2;
	}

	public void setCheckNilaiBol2(CheckBox checkNilaiBol2) {
		this.checkNilaiBol2 = checkNilaiBol2;
	}

	public ComboBox getComboSearchByDivision() {
		return comboSearchByDivision;
	}

	public void setComboSearchByDivision(ComboBox comboSearchByDivision) {
		this.comboSearchByDivision = comboSearchByDivision;
	}

	public Button getBtnPemeliharaanParameter() {
		return btnPemeliharaanParameter;
	}

	public void setBtnPemeliharaanParameter(Button btnPemeliharaanParameter) {
		this.btnPemeliharaanParameter = btnPemeliharaanParameter;
	}
	
	
	
}
