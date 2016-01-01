package org.erp.distribution.servicehp.penerimaanservice;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.erp.distribution.master.customer.utility.CustomerUtilityModel;
import org.erp.distribution.master.customer.utility.CustomerUtilityPresenter;
import org.erp.distribution.master.customer.utility.CustomerUtilityView;
import org.erp.distribution.model.modelenum.EnumOperationStatus;
import org.erp.distribution.model.modelenum.EnumStatusService;
import org.erp.distribution.model.modelenum.EnumUserOtorize;

import com.itextpdf.text.pdf.codec.TiffWriter.FieldImage;
import com.vaadin.addon.jpacontainer.EntityItem;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
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

public class PenerimaanServiceView extends CustomComponent{
	private PenerimaanServiceModel model;

	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	
	private TextField fieldId = new TextField("ID");
	private ComboBox comboGrup = new ComboBox("JENIS CUSTOMER");
	private TextField fieldDescription= new TextField("NAMA TEKNISI");
	private TextField fieldReportDesc= new TextField("REPORT DESC");
	private CheckBox checkStatusActive = new CheckBox("AKTIF", false);

	private DateField fieldTglMasuk = new DateField("TGL. MASUK");
	private TextField fieldNoReg = new TextField("NO. REG");
	private TextField fieldNoJob = new TextField("NO. JOB");
	private ComboBox comboCustomer = new ComboBox("CUSTOMER");	
	private ComboBox comboMerkHp = new ComboBox("MERK HP");	
	private TextField fieldTipeHp = new TextField("TIPE HP");	
	private TextField fieldImei = new TextField("IMEI");	
	private TextField fieldPin = new TextField("PIN");	
	private DateField fieldTglPengambilan = new DateField("TGL. PENGAMBILAN");
	private TextField fieldUangMuka = new TextField("UANG MUKA");

	CheckBox checkTelahDiambil = new CheckBox("Telah Diambil?");

	private TextField fieldKelengkapan = new TextField("KELENGKAPAN");
	CheckBox checkKelBattery = new CheckBox("BATTERY");
	CheckBox checkKelMemorycard = new CheckBox("MEMORY CARD");
	CheckBox checkKelSimcard = new CheckBox("SIMCARD");
	CheckBox checkKelCharger = new CheckBox("CHARGER");
	CheckBox checkKelDosbox = new CheckBox("DOS BOX");
	CheckBox checkKelLain = new CheckBox("LAIN-LAIN");
	
	private ComboBox comboStatusService = new ComboBox("STATUS SERVICE");

	
	private DateField fieldTglTelahDiambil = new DateField("TGL. TELAH DIAMBIL");
	private TextField fieldBiaya = new TextField("BIAYA SERVICE");
	private TextField fieldBiayaSparePart = new TextField("BIAYA SPARE PART");
	private TextField fieldLamaGaransi = new TextField("LAMA GARANSI");
	private TextField fieldYangMenyerahkan = new TextField("YANG MENYERAHKAN");
	
	
	private ComboBox comboTeknisi = new ComboBox("TEKNISI");
	private TextField fieldAnalisaKerusakan = new TextField("ANALISA KERUSAKAN");
	CheckBox checkStatusSelesaiService = new CheckBox("STATUS SELESAI SERVICE");

	private TextField fieldKeluhan = new TextField("KELUHAN");
	CheckBox checkKerIntLcd = new CheckBox("LCD");
	CheckBox checkKerIntKeypad = new CheckBox("KEYPAD");
	CheckBox checkKerIntJoystick = new CheckBox("JOYSTICK");
	CheckBox checkKerIntGetar = new CheckBox("GETAR");
	CheckBox checkKerIntLight = new CheckBox("LIGHT");
	CheckBox checkKerIntLain = new CheckBox("LAIN-LAIN");
	CheckBox checkKerAudMic = new CheckBox("MIC");
	CheckBox checkKerAudSpeaker = new CheckBox("SPEAKER");
	CheckBox checkKerAudDering = new CheckBox("DERING");
	CheckBox checkKerAudHandfreeError = new CheckBox("HAND FREE ERROR");
	CheckBox checkKerAudLain = new CheckBox("Lain-lain");

	CheckBox checkKerFuncNotCharg = new CheckBox("NOT CHARGING");
	CheckBox checkKerFuncBorosBatt = new CheckBox("BOROS BATTERY");
	CheckBox checkKerFuncNoSignal = new CheckBox("NO SIGNAL");
	CheckBox checkKerFuncSimError = new CheckBox("SIM ERROR");
	CheckBox checkKerFuncLain = new CheckBox("Lain-lain");
	CheckBox checkKerExtCamera = new CheckBox("CAMERA");
	CheckBox checkKerExtBluetooth = new CheckBox("BLUE TOOTH");
	CheckBox checkKerExtMmc = new CheckBox("MMC");
	CheckBox checkKerExtInfra = new CheckBox("INFRA RED");
	CheckBox checkKerExtRadio = new CheckBox("Radio");
	CheckBox checkKerExtLain = new CheckBox("Lain-lain");
	CheckBox checkKerSoftRbtHangFreeze = new CheckBox("RBT HANG FREEZE");
	CheckBox checkKerSoftSimLocked = new CheckBox("SIM LOCKED");
	CheckBox checkKerSoftCodes = new CheckBox("CODES");
	CheckBox checkKerSoftImeiDataCorupt = new CheckBox("DATA CORRUPT");
	CheckBox checkKerSoftSalahFlash = new CheckBox("SALAH FLASH");
	CheckBox checkKerSoftWrongSoftware = new CheckBox("WRONG SOFTWARE");
	CheckBox checkKerSoftLain = new CheckBox("Lain-lain");
	
	//TAMBAHAN
	private ComboBox comboGrup2 = new ComboBox("SUB AREA/PASAR");
	private ComboBox comboTunaiKredit = new ComboBox("TUNAI/KREDIT");
	
	private TextField fieldTop = new TextField("TOP");
	private TextField fieldCreditlimit = new TextField("LIMIT KREDIT");
	private TextField fieldOpenlnvoice = new TextField("OPEN INVOICE");
	
	private ComboBox comboHargaAlternatif = new ComboBox("HRG. ALT");
	
	private TextField fieldCustno = new TextField("KODE");
	private TextField fieldCustname = new TextField("NAMA");
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
	
	private TextField fieldFeePerService = new TextField("FEE PER SERVICE");
	private TextField fieldPercentFeePerService = new TextField("PERCENT FEE");
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel Or Close");
	private Button btnNewForm= new Button("New");
	private Button btnEditForm= new Button("Edit");
	private Button btnDeleteForm= new Button("Delete");

	private Button btnAddCustomer= new Button();
	
	private Button btnSaveFormCustomer= new Button("Save");
	private Button btnCancelFormCustomer= new Button("Cancel");
	

	//Additional Component
	private ComboBox comboSearchByGrup = new ComboBox("JENIS CUSTOMER");
	
	private TextField fieldSearchById = new TextField("NO. REG");
	private TextField fieldSearchByDesc = new TextField("NAMA CUSTOMER");

	private TextField fieldSearchByNoreg = new TextField("NO. REG");
	private TextField fieldSearchByNojob = new TextField("NO. JOB");
	private TextField fieldSearchByNama = new TextField("NAMA CUST");
	private TextField fieldSearchByAlamat= new TextField("ALAMAT");
	
	private Button btnSearch = new Button("Search");
	private Button btnPrint = new Button("Print");
	private Button btnPrintPenerimaan = new Button("Penerimaan");
	private Button btnPrintPengambilan = new Button("Pengambilan");
	private Button btnHelp = new Button("Help");
	private Button btnUtility= new Button("Utilitas");

	//LAYOUT
	private VerticalLayout layoutForm = new VerticalLayout();
	private VerticalLayout layoutFormTop = new VerticalLayout();
	private VerticalLayout layoutFormButtom = new VerticalLayout();
	
	private FormLayout formLayout = new FormLayout();
	private FormLayout formLayoutKelengkapan = new FormLayout();
	private FormLayout formLayoutKeluhan = new FormLayout();
	
	private FormLayout formLayoutCustomer = new FormLayout();
	
	//Panel
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelTabel = new Panel();
	private Panel panelForm = new Panel();
	private Panel panelFormCustomer = new Panel();

	//Help Manager	
	
	public PenerimaanServiceView(PenerimaanServiceModel model){
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

		fieldSearchByNoreg.setWidth("100px");
		fieldSearchByNojob.setWidth("100px");
		fieldSearchByNama.setWidth("150px");
		fieldSearchByAlamat.setWidth("150px");
		
		//Init Field
		fieldId.setNullRepresentation("");
		fieldDescription.setNullRepresentation("");
		fieldReportDesc.setNullRepresentation("");
		
		fieldId.setWidth("100px");
		comboGrup.setWidth("200px");
		comboGrup.setFilteringMode(FilteringMode.CONTAINS);
		comboGrup2.setWidth("200px");
		comboGrup2.setFilteringMode(FilteringMode.CONTAINS);
		comboTunaiKredit.setWidth("200px");
		comboTunaiKredit.setFilteringMode(FilteringMode.CONTAINS);
		fieldDescription.setWidth("400px");

		btnSearch.setIcon(new ThemeResource("../images/navigation/12x12/Find.png"));

		btnNewForm.setIcon(new ThemeResource("../images/navigation/12x12/Create.png"));
		btnEditForm.setIcon(new ThemeResource("../images/navigation/12x12/Pencil.png"));
		btnDeleteForm.setIcon(new ThemeResource("../images/navigation/12x12/Erase.png"));
		
		btnPrint.setIcon(new ThemeResource("../images/navigation/12x12/Print.png"));
		btnPrintPenerimaan.setIcon(FontAwesome.PRINT);
		btnPrintPengambilan.setIcon(new ThemeResource("../images/navigation/12x12/Print.png"));
		
		btnSaveForm.setIcon(new ThemeResource("../images/navigation/12x12/Save.png"));
		btnCancelForm.setIcon(new ThemeResource("../images/navigation/12x12/Undo.png"));

//		btnAddItem.setIcon(new ThemeResource("../images/navigation/12x12/Create.png"));
		btnEditForm.setIcon(new ThemeResource("../images/navigation/12x12/Pencil.png"));
//		btnRemoveItem.setIcon(new ThemeResource("../images/navigation/12x12/Erase.png"));
		

		fieldTglMasuk.setWidth("200px");		
		fieldNoReg.setWidth("200px");		
		fieldNoJob.setWidth("200px");		
		comboCustomer.setWidth("400px");		
		comboMerkHp.setWidth("400px");		
		fieldTipeHp.setWidth("400px");		
		fieldImei.setWidth("400px");		
		fieldPin.setWidth("400px");		
		fieldTglPengambilan.setWidth("200px");		
		fieldUangMuka.setWidth("200px");		
		fieldBiaya.setWidth("200px");		
		fieldBiayaSparePart.setWidth("200px");		
		fieldTglTelahDiambil.setWidth("200px");		
		
		fieldTglPengambilan.setWidth("200px");		
		fieldYangMenyerahkan.setWidth("400px");
		fieldLamaGaransi.setWidth("200px");
		fieldKelengkapan.setWidth("600px");

		fieldKeluhan.setWidth("600px");
		fieldAnalisaKerusakan.setWidth("600px");
		
		comboTeknisi.setWidth("400px");
		
		//TAMBAHAN
		fieldTop.setWidth("200px");		
		fieldCreditlimit.setWidth("200px");		
		fieldOpenlnvoice.setWidth("200px");		

		comboHargaAlternatif.setWidth("200px");
		comboHargaAlternatif.setFilteringMode(FilteringMode.CONTAINS);
		comboHargaAlternatif.setNullSelectionAllowed(true);
		
		fieldAddress1.setWidth("400px");		
		fieldAddress2.setWidth("400px");		
		fieldCity1.setWidth("400px");		
		fieldCity2.setWidth("400px");		
		fieldState1.setWidth("400px");		
		fieldState2.setWidth("400px");		
		fieldPhone1.setWidth("400px");		
		fieldPhone2.setWidth("400px");		
		fieldNpwp.setWidth("400px");		
		fieldEmail.setWidth("400px");		

	

		btnAddCustomer.setIcon(FontAwesome.PLUS_CIRCLE);
		
		fieldTglMasuk.setDateFormat("dd/MM/yyyy");
		fieldTglPengambilan.setDateFormat("dd/MM/yyyy");
		fieldTglTelahDiambil.setDateFormat("dd/MM/yyyy");
		
		//VALIDATOR
		comboCustomer.setRequired(true);
		comboMerkHp.setRequired(true);
//		fieldDescription.setRequired(true);
//		comboGrup.setRequired(true);
//		comboGrup2.setRequired(true);
//		comboTunaiKredit.setRequired(true);
		
	}
	
	public void buildView(){
//		layoutForm.setSizeFull();
//		layoutFormTop.setSizeFull();
//		layoutFormButtom.setSizeFull();
		
		
		TabSheet tabSheet1 = new TabSheet();
		
		tabSheet1.setSizeFull();
		//PANEL
		panelUtama.setSizeFull();
		panelForm.setSizeFull();
		panelFormCustomer.setSizeFull();
		content.setSizeFull();
		content.setMargin(true);
		
		//KOMPONEN ATAS
		VerticalLayout layoutTop = new VerticalLayout();
//		layoutTop.setMargin(true);
		HorizontalLayout layoutTop1 = new HorizontalLayout();		
		layoutTop1.addComponent(fieldSearchByNoreg);
		layoutTop1.addComponent(fieldSearchByNojob);
		layoutTop1.addComponent(fieldSearchByNama);
		layoutTop1.addComponent(fieldSearchByAlamat);
		
		layoutTop1.addComponent(btnSearch);
		layoutTop1.addComponent(btnNewForm);
		layoutTop1.addComponent(btnEditForm);
		layoutTop1.addComponent(btnDeleteForm);
//		layoutTop1.addComponent(btnPrint);
//		layoutTop1.addComponent(btnUtility);
//		layoutTop1.addComponent(btnHelp);		
		
		layoutTop1.setComponentAlignment(fieldSearchByNoreg, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(fieldSearchByNojob, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(fieldSearchByNama, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(fieldSearchByAlamat, Alignment.BOTTOM_CENTER);

		layoutTop1.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnNewForm, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnEditForm, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnDeleteForm, Alignment.BOTTOM_CENTER);
//		layoutTop1.setComponentAlignment(btnPrint, Alignment.BOTTOM_CENTER);
//		layoutTop1.setComponentAlignment(btnUtility, Alignment.BOTTOM_CENTER);
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
		formLayout.addComponent(fieldNoReg);
		
		HorizontalLayout horLayoutCustomer = new HorizontalLayout();
		horLayoutCustomer.addComponent(comboCustomer);
		horLayoutCustomer.addComponent(btnAddCustomer);
		horLayoutCustomer.setComponentAlignment(btnAddCustomer, Alignment.BOTTOM_CENTER);
		
		formLayout.addComponent(horLayoutCustomer);
		
		
		formLayout.addComponent(comboMerkHp);
		formLayout.addComponent(fieldTipeHp);
		formLayout.addComponent(fieldImei);
		formLayout.addComponent(fieldPin);
		formLayout.addComponent(fieldTglMasuk);
		formLayout.addComponent(fieldTglPengambilan);
		formLayout.addComponent(fieldUangMuka);
		formLayout.addComponent(fieldBiaya);
		formLayout.addComponent(fieldBiayaSparePart);

		
		formLayout.addComponent(checkTelahDiambil);
		formLayout.addComponent(fieldTglTelahDiambil);
		formLayout.addComponent(fieldLamaGaransi);
//		formLayout.addComponent(fieldYangMenyerahkan);
		

		//FORM LAYOUT		
		formLayoutKelengkapan.setMargin(true);
		formLayoutKelengkapan.addComponent(fieldKelengkapan);
		formLayoutKelengkapan.addComponent(checkKelBattery);
		formLayoutKelengkapan.addComponent(checkKelMemorycard);
		formLayoutKelengkapan.addComponent(checkKelSimcard);
		formLayoutKelengkapan.addComponent(checkKelCharger);
		formLayoutKelengkapan.addComponent(checkKelDosbox);
		formLayoutKelengkapan.addComponent(checkKelLain);

		//FORM LAYOUT		
		formLayoutKeluhan.setMargin(true);
		formLayoutKeluhan.addComponent(fieldNoJob);
		formLayoutKeluhan.addComponent(fieldKeluhan);
		formLayoutKeluhan.addComponent(fieldAnalisaKerusakan);
		formLayoutKeluhan.addComponent(comboStatusService);
		formLayoutKeluhan.addComponent(checkStatusSelesaiService);
		formLayoutKeluhan.addComponent(comboTeknisi);
		
		
		tabSheet1.addTab(formLayout, "Info Umum");
		tabSheet1.addTab(formLayoutKelengkapan, "Kelengkapan");
		tabSheet1.addTab(formLayoutKeluhan, "Keluhan & Service");
		
		
		HorizontalLayout formLayoutHorizontalButton = new HorizontalLayout();
		formLayoutHorizontalButton.setSpacing(true);
		formLayoutHorizontalButton.addComponent(btnSaveForm);
		formLayoutHorizontalButton.addComponent(btnCancelForm);
		formLayoutHorizontalButton.addComponent(btnPrintPenerimaan);
		formLayoutHorizontalButton.addComponent(btnPrintPengambilan);
		layoutFormButtom.addComponent(formLayoutHorizontalButton);
		
		layoutForm.addComponent(layoutFormTop);
		layoutForm.addComponent(layoutFormButtom);
		layoutFormTop.addComponent(tabSheet1);
		panelForm.setContent(layoutForm);
//		layoutForm.setExpandRatio(layoutFormTop, 1);

		//MASUKKAN KE ROOT
		layoutTop.addComponent(layoutTop1);
		
		content.addComponent(layoutTop);
		content.addComponent(layoutTable);
		content.setExpandRatio(layoutTable, 1.0f);
		
		panelUtama.setContent(content);		
		setCompositionRoot(panelUtama);	
		
		//LAYOUT CUSTOMER
		formLayoutCustomer.setMargin(true);
		formLayoutCustomer.addComponent(fieldCustno);
		formLayoutCustomer.addComponent(fieldCustname);
		
		formLayoutCustomer.addComponent(fieldAddress1);
		formLayoutCustomer.addComponent(fieldCity1);
		formLayoutCustomer.addComponent(fieldState1);
		formLayoutCustomer.addComponent(fieldPhone1);
		formLayoutCustomer.addComponent(fieldNpwp);
		formLayoutCustomer.addComponent(fieldEmail);

		formLayoutCustomer.addComponent(checkStatusActive);
		
		HorizontalLayout formLayoutHorizontalCustomer = new HorizontalLayout();
		formLayoutHorizontalCustomer.setSpacing(true);
		formLayoutHorizontalCustomer.addComponent(btnSaveFormCustomer);
		formLayoutHorizontalCustomer.addComponent(btnCancelFormCustomer);
		formLayoutCustomer.addComponent(formLayoutHorizontalCustomer);
		
		panelFormCustomer.setContent(formLayoutCustomer);
		
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

			btnPrintPenerimaan.addStyleName("small");
			btnPrintPengambilan.addStyleName("small");
			
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
		comboStatusService.addItem(EnumStatusService.BELUM.getIntCode());
		comboStatusService.setItemCaption(EnumStatusService.BELUM.getIntCode(),EnumStatusService.BELUM.getDescription());
		comboStatusService.addItem(EnumStatusService.PROSES.getIntCode());
		comboStatusService.setItemCaption(EnumStatusService.PROSES.getIntCode(),EnumStatusService.PROSES.getDescription());
		comboStatusService.addItem(EnumStatusService.SELESAI.getIntCode());
		comboStatusService.setItemCaption(EnumStatusService.SELESAI.getIntCode(),EnumStatusService.SELESAI.getDescription());
		comboStatusService.addItem(EnumStatusService.CANCEL.getIntCode());
		comboStatusService.setItemCaption(EnumStatusService.CANCEL.getIntCode(),EnumStatusService.CANCEL.getDescription());
		comboStatusService.setNullSelectionAllowed(false);
		
		//Init isian combobox
		comboTeknisi.setContainerDataSource(model.getBeanItemContainerTeknisi());
		comboTeknisi.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboTeknisi.setFilteringMode(FilteringMode.CONTAINS);
		comboTeknisi.setNullSelectionAllowed(false);

		comboMerkHp.setContainerDataSource(model.getBeanItemContainerMerkHp());
		comboMerkHp.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboMerkHp.setFilteringMode(FilteringMode.CONTAINS);
		comboMerkHp.setNullSelectionAllowed(false);
		
		comboCustomer.setContainerDataSource(model.getBeanItemContainerCustomer());
		comboCustomer.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		comboCustomer.setFilteringMode(FilteringMode.CONTAINS);
		comboCustomer.setNullSelectionAllowed(false);

		
		model.getBinderHeader().bind(comboCustomer, "scustomerBean");
		model.getBinderHeader().bind(fieldNoReg, "noreg");
		model.getBinderHeader().bind(fieldNoJob, "nojob");
		
		model.getBinderHeader().bind(comboMerkHp, "smerkBean");
		model.getBinderHeader().bind(fieldTipeHp, "tipeHp");
		model.getBinderHeader().bind(fieldImei, "imei");
		model.getBinderHeader().bind(fieldPin, "pin");
		model.getBinderHeader().bind(fieldTglMasuk, "tanggalmasuk");
		model.getBinderHeader().bind(fieldTglPengambilan, "tanggalpengambilan");
		model.getBinderHeader().bind(fieldUangMuka, "uangMuka");
		model.getBinderHeader().bind(fieldBiaya, "biaya");
		model.getBinderHeader().bind(fieldBiayaSparePart, "biayaSparePart");
		model.getBinderHeader().bind(checkTelahDiambil, "telahDiambil");

		model.getBinderHeader().bind(comboTeknisi, "steknisiBean");
		model.getBinderHeader().bind(fieldTglTelahDiambil, "telahDiambilTanggal");
		model.getBinderHeader().bind(fieldYangMenyerahkan, "yangMenyerahkan");
		model.getBinderHeader().bind(fieldBiaya, "biaya");
		model.getBinderHeader().bind(fieldLamaGaransi, "lamaGaransi");
		
		
		model.getBinderHeader().bind(fieldKelengkapan, "kelengkapan");
		model.getBinderHeader().bind(checkKelBattery, "kelBattery");
		model.getBinderHeader().bind(checkKelMemorycard, "kelMemorycard");
		model.getBinderHeader().bind(checkKelSimcard, "kelSimcard");
		model.getBinderHeader().bind(checkKelCharger, "kelCharger");
		model.getBinderHeader().bind(checkKelDosbox, "kelDosbox");
		model.getBinderHeader().bind(checkKelLain, "kelLain");
		
		model.getBinderHeader().bind(fieldKeluhan, "keluhan");
		model.getBinderHeader().bind(fieldAnalisaKerusakan, "analisaKerusakan");
		
		model.getBinderHeader().bind(checkStatusSelesaiService, "statusSelesaiService");
		model.getBinderHeader().bind(comboStatusService, "statusService");

		
		
	}
	public void bindAndBuildFieldGroupComponentCustomer(){
		//customer
		model.getBinderHeaderCustomer().bind(fieldCustno, "custno");
		model.getBinderHeaderCustomer().bind(fieldCustname, "custname");
		model.getBinderHeaderCustomer().bind(fieldAddress1, "address1");
		model.getBinderHeaderCustomer().bind(fieldAddress2, "address2");
		model.getBinderHeaderCustomer().bind(fieldCity1, "city1");
		model.getBinderHeaderCustomer().bind(fieldState1, "state1");
		model.getBinderHeaderCustomer().bind(fieldPhone1, "phone1");
		model.getBinderHeaderCustomer().bind(fieldEmail, "email");
		model.getBinderHeaderCustomer().bind(checkStatusActive, "statusactive");
		
	}
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){

//		setVisibleTableProperties("refno", "noreg", "nojob", "tanggalmasuk", 
//				"sCustomerBean.custno", "sCustomerBean.custname");
		setVisibleTableProperties("refno", "noreg", "nojob", "scustomerBean.custname", "scustomerBean.address1", 
				"smerkBean.description", "tipeHp", "tanggalmasuk", "tanggalpengambilan" );
		
		table.setColumnCollapsingAllowed(true);
		try{
			table.setColumnCollapsed("refno", true);
//			table.setColumnCollapsed("noreg", true);
		} catch(Exception ex){}
		
		//ALIGNMENT
//		table.setColumnAlignment("amount", Align.RIGHT);
//		table.setColumnAlignment("amountused", Align.RIGHT);
		
		//set header
		table.setColumnHeader("refno", "REFNO");
		table.setColumnHeader("noreg", "NO. REG");
		table.setColumnHeader("nojob", "NO. JOB");
		
		
		
//		table.setColumnExpandRatio("id", 2);
//		table.setColumnExpandRatio("description", 5);
				
	}
	
	public void setFormButtonAndTextState(){
		//KODE REFNO SELALU READ ONLY KARENA OTOMATIS
		
		if (model.getOperationStatus().equals(EnumOperationStatus.OPEN.getStrCode())){
//			fieldId.setReadOnly(true);
//			formLayout.setVisible(false);
//			table.setSelectable(true);
//			btnAddForm.setEnabled(true);
//			btnDeleteForm.setEnabled(true);			
//			btnSearch.setEnabled(true);
			btnPrintPenerimaan.setEnabled(true);
			btnPrintPengambilan.setEnabled(true);
		} else if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
//			fieldId.setReadOnly(false);
//			formLayout.setVisible(true);
//			table.setSelectable(false);
//			btnAddForm.setEnabled(false);
//			btnDeleteForm.setEnabled(false);
//			btnSearch.setEnabled(false);
			btnPrintPenerimaan.setEnabled(false);
			btnPrintPengambilan.setEnabled(false);
		}else if (model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
//			fieldId.setReadOnly(true);
//			formLayout.setVisible(true);
//			table.setSelectable(true);
//			btnAddForm.setEnabled(true);
//			btnDeleteForm.setEnabled(true);			
//			btnSearch.setEnabled(true);
			btnPrintPenerimaan.setEnabled(true);
			btnPrintPengambilan.setEnabled(true);
		}		
		if (model.getParamAdminOrTeknisi().equalsIgnoreCase("ADMIN")) {
			
			fieldAnalisaKerusakan.setEnabled(false);
			checkStatusSelesaiService.setEnabled(false);
//			comboTeknisi.setEnabled(false);
		} else if (model.getParamAdminOrTeknisi().equalsIgnoreCase("TEKNISI")) {
			btnNewForm.setVisible(false);
			btnDeleteForm.setVisible(false);
			btnAddCustomer.setEnabled(false);
			
			comboCustomer.setEnabled(false);
			comboMerkHp.setEnabled(false);
			fieldTipeHp.setEnabled(false);
			fieldBiaya.setEnabled(false);
			fieldImei.setEnabled(false);
			fieldPin.setEnabled(false);
			fieldTglMasuk.setEnabled(false);
			fieldTglPengambilan.setEnabled(false);
			fieldTglTelahDiambil.setEnabled(false);
			fieldYangMenyerahkan.setEnabled(false);
			
			checkKelBattery.setEnabled(false);
			checkKelMemorycard.setEnabled(false);
			checkKelSimcard.setEnabled(false);
			checkKelCharger.setEnabled(false);
			checkKelDosbox.setEnabled(false);
			checkKelLain.setEnabled(false);
			
			fieldKelengkapan.setEnabled(false);
			fieldKeluhan.setEnabled(false);
		}
		
	}
	
	private Window windowForm = new Window();
	
	public void showWindowForm(){
		windowForm = new Window();
		windowForm.setModal(true);
		windowForm.center();
//		windowForm.setStyleName("login-layout");
		windowForm.setWidth("900px");
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

	
	private Window windowCustomer = new Window();
	
	public void showWindowCustomer(){
		windowCustomer = new Window();
		windowCustomer.setModal(true);
		windowForm.center();
//		windowForm.setStyleName("login-layout");
		windowCustomer.setWidth("620px");
		windowCustomer.setHeight("500px");
		windowCustomer.setClosable(true);	
		windowCustomer.setResizable(false);

		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSizeFull();
		content.addComponent(panelFormCustomer);
		
		windowCustomer.setContent(content);
		windowCustomer.center();
		getUI().addWindow(windowCustomer);
		
	}
	public void closeWindowCustomer(){
		windowCustomer.close();
		getUI().removeWindow(windowCustomer);
		
	}
	
	public void focustIdOrDesc(){
		if (fieldId.isEnabled()){
			fieldId.focus();
		} else {
			fieldDescription.focus();		                    						
		}		
	}

	public PenerimaanServiceModel getModel() {
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

	public void setModel(PenerimaanServiceModel model) {
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

	public ComboBox getComboHargaAlternatif() {
		return comboHargaAlternatif;
	}

	public TextField getFieldFeePerService() {
		return fieldFeePerService;
	}

	public TextField getFieldPercentFeePerService() {
		return fieldPercentFeePerService;
	}

	public void setComboHargaAlternatif(ComboBox comboHargaAlternatif) {
		this.comboHargaAlternatif = comboHargaAlternatif;
	}

	public void setFieldFeePerService(TextField fieldFeePerService) {
		this.fieldFeePerService = fieldFeePerService;
	}

	public void setFieldPercentFeePerService(TextField fieldPercentFeePerService) {
		this.fieldPercentFeePerService = fieldPercentFeePerService;
	}

	public Button getBtnAddCustomer() {
		return btnAddCustomer;
	}

	public void setBtnAddCustomer(Button btnAddCustomer) {
		this.btnAddCustomer = btnAddCustomer;
	}

	public DateField getFieldTglMasuk() {
		return fieldTglMasuk;
	}

	public TextField getFieldNoReg() {
		return fieldNoReg;
	}

	public TextField getFieldNoJob() {
		return fieldNoJob;
	}

	public ComboBox getComboCustomer() {
		return comboCustomer;
	}

	public ComboBox getComboMerkHp() {
		return comboMerkHp;
	}

	public TextField getFieldTipeHp() {
		return fieldTipeHp;
	}

	public TextField getFieldImei() {
		return fieldImei;
	}

	public TextField getFieldPin() {
		return fieldPin;
	}

	public DateField getFieldTglPengambilan() {
		return fieldTglPengambilan;
	}

	public TextField getFieldUangMuka() {
		return fieldUangMuka;
	}

	public TextField getFieldKelengkapan() {
		return fieldKelengkapan;
	}

	public CheckBox getCheckKelBattery() {
		return checkKelBattery;
	}

	public CheckBox getCheckKelMemorycard() {
		return checkKelMemorycard;
	}

	public CheckBox getCheckKelSimcard() {
		return checkKelSimcard;
	}

	public CheckBox getCheckKelCharger() {
		return checkKelCharger;
	}

	public CheckBox getCheckKelDosbox() {
		return checkKelDosbox;
	}

	public CheckBox getCheckKelLain() {
		return checkKelLain;
	}

	public ComboBox getComboStatusService() {
		return comboStatusService;
	}

	public DateField getFieldTglTelahDiambil() {
		return fieldTglTelahDiambil;
	}

	public TextField getFieldBiaya() {
		return fieldBiaya;
	}

	public TextField getFieldLamaGaransi() {
		return fieldLamaGaransi;
	}

	public TextField getFieldYangMenyerahkan() {
		return fieldYangMenyerahkan;
	}

	public ComboBox getComboTeknisi() {
		return comboTeknisi;
	}

	public TextField getFieldAnalisaKerusakan() {
		return fieldAnalisaKerusakan;
	}

	public CheckBox getCheckStatusSelesaiService() {
		return checkStatusSelesaiService;
	}

	public TextField getFieldKeluhan() {
		return fieldKeluhan;
	}

	public CheckBox getCheckKerIntLcd() {
		return checkKerIntLcd;
	}

	public CheckBox getCheckKerIntKeypad() {
		return checkKerIntKeypad;
	}

	public CheckBox getCheckKerIntJoystick() {
		return checkKerIntJoystick;
	}

	public CheckBox getCheckKerIntGetar() {
		return checkKerIntGetar;
	}

	public CheckBox getCheckKerIntLight() {
		return checkKerIntLight;
	}

	public CheckBox getCheckKerIntLain() {
		return checkKerIntLain;
	}

	public CheckBox getCheckKerAudMic() {
		return checkKerAudMic;
	}

	public CheckBox getCheckKerAudSpeaker() {
		return checkKerAudSpeaker;
	}

	public CheckBox getCheckKerAudDering() {
		return checkKerAudDering;
	}

	public CheckBox getCheckKerAudHandfreeError() {
		return checkKerAudHandfreeError;
	}

	public CheckBox getCheckKerAudLain() {
		return checkKerAudLain;
	}

	public CheckBox getCheckKerFuncNotCharg() {
		return checkKerFuncNotCharg;
	}

	public CheckBox getCheckKerFuncBorosBatt() {
		return checkKerFuncBorosBatt;
	}

	public CheckBox getCheckKerFuncNoSignal() {
		return checkKerFuncNoSignal;
	}

	public CheckBox getCheckKerFuncSimError() {
		return checkKerFuncSimError;
	}

	public CheckBox getCheckKerFuncLain() {
		return checkKerFuncLain;
	}

	public CheckBox getCheckKerExtCamera() {
		return checkKerExtCamera;
	}

	public CheckBox getCheckKerExtBluetooth() {
		return checkKerExtBluetooth;
	}

	public CheckBox getCheckKerExtMmc() {
		return checkKerExtMmc;
	}

	public CheckBox getCheckKerExtInfra() {
		return checkKerExtInfra;
	}

	public CheckBox getCheckKerExtRadio() {
		return checkKerExtRadio;
	}

	public CheckBox getCheckKerExtLain() {
		return checkKerExtLain;
	}

	public CheckBox getCheckKerSoftRbtHangFreeze() {
		return checkKerSoftRbtHangFreeze;
	}

	public CheckBox getCheckKerSoftSimLocked() {
		return checkKerSoftSimLocked;
	}

	public CheckBox getCheckKerSoftCodes() {
		return checkKerSoftCodes;
	}

	public CheckBox getCheckKerSoftImeiDataCorupt() {
		return checkKerSoftImeiDataCorupt;
	}

	public CheckBox getCheckKerSoftSalahFlash() {
		return checkKerSoftSalahFlash;
	}

	public CheckBox getCheckKerSoftWrongSoftware() {
		return checkKerSoftWrongSoftware;
	}

	public CheckBox getCheckKerSoftLain() {
		return checkKerSoftLain;
	}

	public TextField getFieldCustno() {
		return fieldCustno;
	}

	public TextField getFieldCustname() {
		return fieldCustname;
	}

	public Button getBtnSaveFormCustomer() {
		return btnSaveFormCustomer;
	}

	public Button getBtnCancelFormCustomer() {
		return btnCancelFormCustomer;
	}

	public VerticalLayout getLayoutForm() {
		return layoutForm;
	}

	public VerticalLayout getLayoutFormTop() {
		return layoutFormTop;
	}

	public VerticalLayout getLayoutFormButtom() {
		return layoutFormButtom;
	}

	public FormLayout getFormLayoutKelengkapan() {
		return formLayoutKelengkapan;
	}

	public FormLayout getFormLayoutKeluhan() {
		return formLayoutKeluhan;
	}

	public FormLayout getFormLayoutCustomer() {
		return formLayoutCustomer;
	}

	public Panel getPanelFormCustomer() {
		return panelFormCustomer;
	}

	public Window getWindowCustomer() {
		return windowCustomer;
	}

	public void setFieldTglMasuk(DateField fieldTglMasuk) {
		this.fieldTglMasuk = fieldTglMasuk;
	}

	public void setFieldNoReg(TextField fieldNoReg) {
		this.fieldNoReg = fieldNoReg;
	}

	public void setFieldNoJob(TextField fieldNoJob) {
		this.fieldNoJob = fieldNoJob;
	}

	public void setComboCustomer(ComboBox comboCustomer) {
		this.comboCustomer = comboCustomer;
	}

	public void setComboMerkHp(ComboBox comboMerkHp) {
		this.comboMerkHp = comboMerkHp;
	}

	public void setFieldTipeHp(TextField fieldTipeHp) {
		this.fieldTipeHp = fieldTipeHp;
	}

	public void setFieldImei(TextField fieldImei) {
		this.fieldImei = fieldImei;
	}

	public void setFieldPin(TextField fieldPin) {
		this.fieldPin = fieldPin;
	}

	public void setFieldTglPengambilan(DateField fieldTglPengambilan) {
		this.fieldTglPengambilan = fieldTglPengambilan;
	}

	public void setFieldUangMuka(TextField fieldUangMuka) {
		this.fieldUangMuka = fieldUangMuka;
	}

	public void setFieldKelengkapan(TextField fieldKelengkapan) {
		this.fieldKelengkapan = fieldKelengkapan;
	}

	public void setCheckKelBattery(CheckBox checkKelBattery) {
		this.checkKelBattery = checkKelBattery;
	}

	public void setCheckKelMemorycard(CheckBox checkKelMemorycard) {
		this.checkKelMemorycard = checkKelMemorycard;
	}

	public void setCheckKelSimcard(CheckBox checkKelSimcard) {
		this.checkKelSimcard = checkKelSimcard;
	}

	public void setCheckKelCharger(CheckBox checkKelCharger) {
		this.checkKelCharger = checkKelCharger;
	}

	public void setCheckKelDosbox(CheckBox checkKelDosbox) {
		this.checkKelDosbox = checkKelDosbox;
	}

	public void setCheckKelLain(CheckBox checkKelLain) {
		this.checkKelLain = checkKelLain;
	}

	public void setComboStatusService(ComboBox comboStatusService) {
		this.comboStatusService = comboStatusService;
	}

	public void setFieldTglTelahDiambil(DateField fieldTglTelahDiambil) {
		this.fieldTglTelahDiambil = fieldTglTelahDiambil;
	}

	public void setFieldBiaya(TextField fieldBiaya) {
		this.fieldBiaya = fieldBiaya;
	}

	public void setFieldLamaGaransi(TextField fieldLamaGaransi) {
		this.fieldLamaGaransi = fieldLamaGaransi;
	}

	public void setFieldYangMenyerahkan(TextField fieldYangMenyerahkan) {
		this.fieldYangMenyerahkan = fieldYangMenyerahkan;
	}

	public void setComboTeknisi(ComboBox comboTeknisi) {
		this.comboTeknisi = comboTeknisi;
	}

	public void setFieldAnalisaKerusakan(TextField fieldAnalisaKerusakan) {
		this.fieldAnalisaKerusakan = fieldAnalisaKerusakan;
	}

	public void setCheckStatusSelesaiService(CheckBox checkStatusSelesaiService) {
		this.checkStatusSelesaiService = checkStatusSelesaiService;
	}

	public void setFieldKeluhan(TextField fieldKeluhan) {
		this.fieldKeluhan = fieldKeluhan;
	}

	public void setCheckKerIntLcd(CheckBox checkKerIntLcd) {
		this.checkKerIntLcd = checkKerIntLcd;
	}

	public void setCheckKerIntKeypad(CheckBox checkKerIntKeypad) {
		this.checkKerIntKeypad = checkKerIntKeypad;
	}

	public void setCheckKerIntJoystick(CheckBox checkKerIntJoystick) {
		this.checkKerIntJoystick = checkKerIntJoystick;
	}

	public void setCheckKerIntGetar(CheckBox checkKerIntGetar) {
		this.checkKerIntGetar = checkKerIntGetar;
	}

	public void setCheckKerIntLight(CheckBox checkKerIntLight) {
		this.checkKerIntLight = checkKerIntLight;
	}

	public void setCheckKerIntLain(CheckBox checkKerIntLain) {
		this.checkKerIntLain = checkKerIntLain;
	}

	public void setCheckKerAudMic(CheckBox checkKerAudMic) {
		this.checkKerAudMic = checkKerAudMic;
	}

	public void setCheckKerAudSpeaker(CheckBox checkKerAudSpeaker) {
		this.checkKerAudSpeaker = checkKerAudSpeaker;
	}

	public void setCheckKerAudDering(CheckBox checkKerAudDering) {
		this.checkKerAudDering = checkKerAudDering;
	}

	public void setCheckKerAudHandfreeError(CheckBox checkKerAudHandfreeError) {
		this.checkKerAudHandfreeError = checkKerAudHandfreeError;
	}

	public void setCheckKerAudLain(CheckBox checkKerAudLain) {
		this.checkKerAudLain = checkKerAudLain;
	}

	public void setCheckKerFuncNotCharg(CheckBox checkKerFuncNotCharg) {
		this.checkKerFuncNotCharg = checkKerFuncNotCharg;
	}

	public void setCheckKerFuncBorosBatt(CheckBox checkKerFuncBorosBatt) {
		this.checkKerFuncBorosBatt = checkKerFuncBorosBatt;
	}

	public void setCheckKerFuncNoSignal(CheckBox checkKerFuncNoSignal) {
		this.checkKerFuncNoSignal = checkKerFuncNoSignal;
	}

	public void setCheckKerFuncSimError(CheckBox checkKerFuncSimError) {
		this.checkKerFuncSimError = checkKerFuncSimError;
	}

	public void setCheckKerFuncLain(CheckBox checkKerFuncLain) {
		this.checkKerFuncLain = checkKerFuncLain;
	}

	public void setCheckKerExtCamera(CheckBox checkKerExtCamera) {
		this.checkKerExtCamera = checkKerExtCamera;
	}

	public void setCheckKerExtBluetooth(CheckBox checkKerExtBluetooth) {
		this.checkKerExtBluetooth = checkKerExtBluetooth;
	}

	public void setCheckKerExtMmc(CheckBox checkKerExtMmc) {
		this.checkKerExtMmc = checkKerExtMmc;
	}

	public void setCheckKerExtInfra(CheckBox checkKerExtInfra) {
		this.checkKerExtInfra = checkKerExtInfra;
	}

	public void setCheckKerExtRadio(CheckBox checkKerExtRadio) {
		this.checkKerExtRadio = checkKerExtRadio;
	}

	public void setCheckKerExtLain(CheckBox checkKerExtLain) {
		this.checkKerExtLain = checkKerExtLain;
	}

	public void setCheckKerSoftRbtHangFreeze(CheckBox checkKerSoftRbtHangFreeze) {
		this.checkKerSoftRbtHangFreeze = checkKerSoftRbtHangFreeze;
	}

	public void setCheckKerSoftSimLocked(CheckBox checkKerSoftSimLocked) {
		this.checkKerSoftSimLocked = checkKerSoftSimLocked;
	}

	public void setCheckKerSoftCodes(CheckBox checkKerSoftCodes) {
		this.checkKerSoftCodes = checkKerSoftCodes;
	}

	public void setCheckKerSoftImeiDataCorupt(CheckBox checkKerSoftImeiDataCorupt) {
		this.checkKerSoftImeiDataCorupt = checkKerSoftImeiDataCorupt;
	}

	public void setCheckKerSoftSalahFlash(CheckBox checkKerSoftSalahFlash) {
		this.checkKerSoftSalahFlash = checkKerSoftSalahFlash;
	}

	public void setCheckKerSoftWrongSoftware(CheckBox checkKerSoftWrongSoftware) {
		this.checkKerSoftWrongSoftware = checkKerSoftWrongSoftware;
	}

	public void setCheckKerSoftLain(CheckBox checkKerSoftLain) {
		this.checkKerSoftLain = checkKerSoftLain;
	}

	public void setFieldCustno(TextField fieldCustno) {
		this.fieldCustno = fieldCustno;
	}

	public void setFieldCustname(TextField fieldCustname) {
		this.fieldCustname = fieldCustname;
	}

	public void setBtnSaveFormCustomer(Button btnSaveFormCustomer) {
		this.btnSaveFormCustomer = btnSaveFormCustomer;
	}

	public void setBtnCancelFormCustomer(Button btnCancelFormCustomer) {
		this.btnCancelFormCustomer = btnCancelFormCustomer;
	}

	public void setLayoutForm(VerticalLayout layoutForm) {
		this.layoutForm = layoutForm;
	}

	public void setLayoutFormTop(VerticalLayout layoutFormTop) {
		this.layoutFormTop = layoutFormTop;
	}

	public void setLayoutFormButtom(VerticalLayout layoutFormButtom) {
		this.layoutFormButtom = layoutFormButtom;
	}

	public void setFormLayoutKelengkapan(FormLayout formLayoutKelengkapan) {
		this.formLayoutKelengkapan = formLayoutKelengkapan;
	}

	public void setFormLayoutKeluhan(FormLayout formLayoutKeluhan) {
		this.formLayoutKeluhan = formLayoutKeluhan;
	}

	public void setFormLayoutCustomer(FormLayout formLayoutCustomer) {
		this.formLayoutCustomer = formLayoutCustomer;
	}

	public void setPanelFormCustomer(Panel panelFormCustomer) {
		this.panelFormCustomer = panelFormCustomer;
	}

	public void setWindowCustomer(Window windowCustomer) {
		this.windowCustomer = windowCustomer;
	}

	public TextField getFieldSearchByNoreg() {
		return fieldSearchByNoreg;
	}

	public TextField getFieldSearchByNojob() {
		return fieldSearchByNojob;
	}

	public TextField getFieldSearchByNama() {
		return fieldSearchByNama;
	}

	public TextField getFieldSearchByAlamat() {
		return fieldSearchByAlamat;
	}

	public void setFieldSearchByNoreg(TextField fieldSearchByNoreg) {
		this.fieldSearchByNoreg = fieldSearchByNoreg;
	}

	public void setFieldSearchByNojob(TextField fieldSearchByNojob) {
		this.fieldSearchByNojob = fieldSearchByNojob;
	}

	public void setFieldSearchByNama(TextField fieldSearchByNama) {
		this.fieldSearchByNama = fieldSearchByNama;
	}

	public void setFieldSearchByAlamat(TextField fieldSearchByAlamat) {
		this.fieldSearchByAlamat = fieldSearchByAlamat;
	}

	public Button getBtnPrintPenerimaan() {
		return btnPrintPenerimaan;
	}

	public Button getBtnPrintPengambilan() {
		return btnPrintPengambilan;
	}

	public void setBtnPrintPenerimaan(Button btnPrintPenerimaan) {
		this.btnPrintPenerimaan = btnPrintPenerimaan;
	}

	public void setBtnPrintPengambilan(Button btnPrintPengambilan) {
		this.btnPrintPengambilan = btnPrintPengambilan;
	}

	

	
}
