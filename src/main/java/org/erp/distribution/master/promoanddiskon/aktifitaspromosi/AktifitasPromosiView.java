package org.erp.distribution.master.promoanddiskon.aktifitaspromosi;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.erp.distribution.master.customer.utility.CustomerUtilityModel;
import org.erp.distribution.master.customer.utility.CustomerUtilityPresenter;
import org.erp.distribution.master.customer.utility.CustomerUtilityView;

import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

public class AktifitasPromosiView extends CustomComponent{
	private AktifitasPromosiModel model;

	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	
	private TextField fieldId = new TextField("ID");
	
	private ComboBox comboProduct = new ComboBox("Product");			
	private TextField fieldDescription= new TextField("Nama Akt. Promo");
	private TextField fieldReportDesc= new TextField("REPORT DESC");
	private DateField dateFieldperiodeDateFrom = new DateField("PERIODE MULAI");
	private DateField dateFieldperiodeDateTo = new DateField("S.D");
	
	private CheckBox checkStatusActive = new CheckBox("AKTIF", false);
	
	private ComboBox comboProductGrupKriteria = new ComboBox();
	private ComboBox comboProductGroup = new ComboBox("PRODUCT GRUP");
	private CheckBox checkProductGroupAkumulasi = new CheckBox("AKUMULASI?");

	private ComboBox comboJenisCustKrieria = new ComboBox();
	private ComboBox comboJenisCust = new ComboBox("JENIS CUSTOMER");

	private TextField fieldTarget = new TextField("Target");
	private TextField fieldTargetApplied = new TextField("Terealisasi");
	private CheckBox checkClaimPabrik = new CheckBox("CLAIM PABRIK?");
	
	//BONUS BARANG
	private ComboBox comboBonusProduct = new ComboBox("MENDAPAT PRODUK");		
	private TextField fieldFreeLebihDari1 = new TextField("> (Lebih dari in PCS)");
	private TextField fieldFreeGet1 = new TextField("Qty Pcs");
	private TextField fieldFreeLebihDari2 = new TextField("> (Lebih dari in PCS)");
	private TextField fieldFreeGet2 = new TextField("Qty Pcs");
	private TextField fieldFreeLebihDari3 = new TextField("> (Lebih dari in PCS)");
	private TextField fieldFreeGet3 = new TextField("Qty Pcs");
	private TextField fieldFreeLebihDari4 = new TextField("> (Lebih dari in PCS)");
	private TextField fieldFreeGet4 = new TextField("Qty Pcs");
	private CheckBox checkFreeKelipatan = new CheckBox("BERLAKU KELIPATAN?");

	//DISKON FROM SUBTOTAL
	private TextField fieldDiskonLebihDari1 = new TextField("> (Rupiah lebih dari)");
	private TextField fieldDiskonGet1 = new TextField("Persen disc");
	private TextField fieldDiskonLebihDari2 = new TextField("> (Rupiah lebih dari)");
	private TextField fieldDiskonGet2 = new TextField("Persen disc");
	private TextField fieldDiskonLebihDari3 = new TextField("> (Rupiah lebih dari)");
	private TextField fieldDiskonGet3 = new TextField("Persen disc");
	private TextField fieldDiskonLebihDari4 = new TextField("> (Rupiah lebih dari)");
	private TextField fieldDiskonGet4 = new TextField("Persen disc");
	private CheckBox checkDiscKelipatan = new CheckBox("BERLAKU KELIPATAN?");

	//DISKON FROM SUBTOTAL
	private TextField fieldDiscFromItemFreeQty1 = new TextField("> (Lebih dari in PCS)");
	private TextField fieldDiscFromItemdiscPercentGet1 = new TextField("Persen disc");
	private TextField fieldDiscFromItemFreeQty2 = new TextField("> (Lebih dari in PCS)");
	private TextField fieldDiscFromItemdiscPercentGet2 = new TextField("Persen disc");
	private TextField fieldDiscFromItemFreeQty3 = new TextField("> (Lebih dari in PCS)");
	private TextField fieldDiscFromItemdiscPercentGet3 = new TextField("Persen disc");
	private TextField fieldDiscFromItemFreeQty4 = new TextField("> (ILebih dari in PCS)");
	private TextField fieldDiscFromItemdiscPercentGet4 = new TextField("Persen disc");
	private CheckBox checkDiscFromItemKelipatan = new CheckBox("BERLAKU KELIPATAN?");
	
	//CASH BACK
	private TextField fieldCashBackLebihDari1 = new TextField("> (rupiah lebih dari)");
	private TextField fieldCashBackGet1 = new TextField("Cash Back");
	private TextField fieldCashBackLebihDari2 = new TextField("> (rupiah lebih dari)");
	private TextField fieldCashBackGet2 = new TextField("Cash Back");
	private TextField fieldCashBackLebihDari3 = new TextField("> (rupiah lebih dari)");
	private TextField fieldCashBackGet3 = new TextField("Cash Back");
	private TextField fieldCashBackLebihDari4 = new TextField("> (rupiah lebih)");
	private TextField fieldCashBackGet4 = new TextField("Cash Back");
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel");
	private Button btnNewForm= new Button("New");
	private Button btnEditForm= new Button("Edit");
	private Button btnDeleteForm= new Button("Delete");

	//Additional Component
	private ComboBox comboSearchByGrup = new ComboBox("JENIS CUSTOMER");
	private TextField fieldSearchById = new TextField("ID");
	private TextField fieldSearchByDesc = new TextField("AKTIFITAS PROMO");
	private Button btnSearch = new Button("Search & Reload");
	private Button btnPrint = new Button("Print");
	private Button btnHelp = new Button("Help");
	private Button btnUtility= new Button("Utilitas");

	TabSheet tabSheet = new TabSheet();
	
	//LAYOUT
	private FormLayout headerFormLayoutPromo = new FormLayout();
	
	private FormLayout kriteria1FormLayout = new FormLayout();	
	private FormLayout formLayoutBonusBarang = new FormLayout();
	private FormLayout formLayoutDiscount = new FormLayout();
	private FormLayout formLayoutDiscountFromItem = new FormLayout();
	private FormLayout formLayoutCashBack = new FormLayout();

	
	private Panel panelBudget = new Panel("BUDGET");
	private VerticalLayout layoutBudget = new VerticalLayout();

	private Panel panelBonusBarang = new Panel("BONUS BARANG/TPRB");
	private VerticalLayout layoutBonusBarang = new VerticalLayout();
	private Panel panelDiskonFromSubtotal = new Panel("DISKON FROM SUBTOTAL");
	private VerticalLayout layoutDiskonFromSubtotal = new VerticalLayout();
	private Panel panelDiskonFromItem = new Panel("DISKON FROM ITEM");
	private VerticalLayout layoutDiskonFromItem = new VerticalLayout();
	private Panel panelCashBack = new Panel("CASH BACK");
	private VerticalLayout layoutCashBack = new VerticalLayout();

	
	private Panel panelCustomerCriteria = new Panel("FOR CUSTOMER");
	private VerticalLayout layoutCustomerCriteria = new VerticalLayout();
	
	
	//LAYOUT
//	private FormLayout headerFormLayout = new FormLayout();
//	private FormLayout kriteria1FormLayout = new FormLayout();
//	private FormLayout kriteria2FormLayout = new FormLayout();
//	private FormLayout bonusBarangFormLayout = new FormLayout();
//	private FormLayout subtotalDiskonFormLayout = new FormLayout();
//	private FormLayout JumlahItemDiskonFormLayout = new FormLayout();
//	private FormLayout cashBackFormLayout = new FormLayout();
	
	HorizontalLayout layoutButtonHorizontal = new HorizontalLayout();

	//Panel
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelTabel = new Panel();

	private Panel panelFormKriteria1 = new Panel();
	private Panel panelFormKriteria2 = new Panel();
	private Panel panelFormBonusBarang = new Panel();
	private Panel panelFormSubtotalDiskon = new Panel();
	private Panel panelFormJumlahItemDiskon = new Panel();
	private Panel panelFormCashBack = new Panel();
	
	//Help Manager	
	
	public AktifitasPromosiView(AktifitasPromosiModel model){
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
		
		fieldId.setWidth("200px");
		comboBonusProduct.setWidth("300px");
		comboBonusProduct.setFilteringMode(FilteringMode.CONTAINS);
		comboProductGroup.setWidth("300px");
		comboProductGroup.setFilteringMode(FilteringMode.CONTAINS);
		fieldDescription.setWidth("550px");
		

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
		fieldFreeGet1.setWidth("100px");		
		fieldFreeGet2.setWidth("100px");		
		fieldFreeGet3.setWidth("100px");		
		fieldFreeGet4.setWidth("100px");		

		fieldFreeLebihDari1.setWidth("200px");		
		fieldFreeLebihDari2.setWidth("200px");		
		fieldFreeLebihDari3.setWidth("200px");		
		fieldFreeLebihDari4.setWidth("200px");		

		comboProduct.setWidth("300px");
		comboProduct.setFilteringMode(FilteringMode.CONTAINS);
		comboProduct.setNullSelectionAllowed(true);

		comboBonusProduct.setWidth("300px");
		comboBonusProduct.setFilteringMode(FilteringMode.CONTAINS);
		comboBonusProduct.setNullSelectionAllowed(true);

		comboJenisCustKrieria.setWidth("100px");
		comboJenisCustKrieria.addItem("ALL");
		comboJenisCustKrieria.setItemCaption("ALL", "All");
		comboJenisCustKrieria.addItem("IN");
		comboJenisCustKrieria.setItemCaption("IN", "Include");
		comboJenisCustKrieria.addItem("EX");
		comboJenisCustKrieria.setItemCaption("EX", "Exclude");
		comboJenisCustKrieria.setNullSelectionAllowed(false);

		comboJenisCust.setWidth("250px");
		comboJenisCust.setFilteringMode(FilteringMode.CONTAINS);
		comboJenisCust.setNullSelectionAllowed(true);

		comboProductGrupKriteria.addItem("ALL");
		comboProductGrupKriteria.setItemCaption("ALL", "All");
		comboProductGrupKriteria.addItem("IN");
		comboProductGrupKriteria.setItemCaption("IN", "Include");
		comboProductGrupKriteria.addItem("EX");
		comboProductGrupKriteria.setItemCaption("EX", "Exclude");
		comboProductGrupKriteria.setNullSelectionAllowed(false);

		comboProductGroup.setWidth("300px");
		comboProductGroup.setFilteringMode(FilteringMode.CONTAINS);
		comboProductGroup.setNullSelectionAllowed(true);

		dateFieldperiodeDateFrom.setDateFormat("dd/MM/yyyy");
		dateFieldperiodeDateTo.setDateFormat("dd/MM/yyyy");
		
		//VALIDATOR
		fieldId.setRequired(true);
		//KARENA BISA MILIH ANTARA PRODUK ATAU GROUP PRODUK
//		comboProduct.setRequired(true);
		fieldDescription.setRequired(true);
		dateFieldperiodeDateFrom.setRequired(true);
		dateFieldperiodeDateTo.setRequired(true);		
		comboJenisCustKrieria.setRequired(true);
		
		
		
	}
	
	public void buildView(){
		
		//PANEL
		panelUtama.setSizeFull();
		
		panelFormKriteria1.setSizeFull();
		panelFormKriteria2.setSizeFull();
		panelFormBonusBarang.setSizeFull();
		panelFormSubtotalDiskon.setSizeFull();
		panelFormJumlahItemDiskon.setSizeFull();
		panelFormCashBack.setSizeFull();
		
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
//		layoutTop1.addComponent(btnUtility);
//		layoutTop1.addComponent(btnHelp);		
		
		layoutTop1.setComponentAlignment(fieldSearchByDesc, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnNewForm, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnEditForm, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnDeleteForm, Alignment.BOTTOM_CENTER);
		layoutTop1.setComponentAlignment(btnPrint, Alignment.BOTTOM_CENTER);
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

		//***#####FORM LAYOUT####				
		tabSheet.setSizeFull();

		headerFormLayoutPromo.setMargin(true);
		
		formLayoutBonusBarang.setMargin(true);
		formLayoutDiscount.setMargin(true);
		formLayoutDiscountFromItem.setMargin(true);
		formLayoutCashBack.setMargin(true);
		//##HEADER FORM
		HorizontalLayout idAndActiveLayout = new HorizontalLayout();
		idAndActiveLayout.setCaption("PROMO ID");
		fieldId.setCaption(null);
		idAndActiveLayout.addComponent(fieldId);
		idAndActiveLayout.addComponent(checkStatusActive);
		headerFormLayoutPromo.addComponent(idAndActiveLayout);
		headerFormLayoutPromo.addComponent(fieldDescription);
		
		//##KRITERIA FORM
		Panel panelCriteria = new Panel("KRITERIA");
		FormLayout layoutCriteria = new FormLayout();
		layoutCriteria.setSizeFull();
		layoutCriteria.setMargin(true);
		panelCriteria.setContent(layoutCriteria);		
		kriteria1FormLayout.addComponent(panelCriteria);
		
		layoutCriteria.addComponent(comboProduct);

		HorizontalLayout productGroupLayout = new HorizontalLayout();
		productGroupLayout.setCaption("Atau Product Group");
		comboProductGroup.setCaption(null);
		productGroupLayout.addComponent(comboProductGroup);
		productGroupLayout.addComponent(checkProductGroupAkumulasi);
		layoutCriteria.addComponent(productGroupLayout);
		
		
		HorizontalLayout periodeMulaiLayout = new HorizontalLayout();
		periodeMulaiLayout.setCaption("Periode Mulai-S.D");
		dateFieldperiodeDateFrom.setCaption(null);
		dateFieldperiodeDateTo.setCaption(null);
		periodeMulaiLayout.addComponent(dateFieldperiodeDateFrom);
		periodeMulaiLayout.addComponent(new Label("S.D"));
		periodeMulaiLayout.addComponent(dateFieldperiodeDateTo);
		layoutCriteria.addComponent(periodeMulaiLayout);
		
		HorizontalLayout customerCriteriaLayout = new HorizontalLayout();
		customerCriteriaLayout.setCaption("Untuk Jns.Customer");
		comboJenisCustKrieria.setCaption(null);
		comboJenisCust.setCaption(null);
		customerCriteriaLayout.addComponent(comboJenisCustKrieria);
		customerCriteriaLayout.addComponent(comboJenisCust);
		layoutCriteria.addComponent(customerCriteriaLayout);
		
		
		//BUDGET
//		panelBudget.setSizeFull();
		layoutBudget.setSizeFull();
		layoutBudget.setMargin(true);
		panelBudget.setContent(layoutBudget);
		
		layoutBudget.addComponent(checkClaimPabrik);
		HorizontalLayout budgetTargetAndAppliedLayout = new HorizontalLayout();
//		fieldTarget.setCaption(null);fieldTargetApplied.setCaption(null);
		budgetTargetAndAppliedLayout.addComponent(fieldTarget);
		budgetTargetAndAppliedLayout.addComponent(fieldTargetApplied);
		layoutBudget.addComponent(budgetTargetAndAppliedLayout);
		
		headerFormLayoutPromo.addComponent(panelBudget);

		
		//BONUS BARANG
//		panelBonusBarang.setSizeFull();
		layoutBonusBarang.setSizeFull();
		layoutBonusBarang.setMargin(true);
		panelBonusBarang.setContent(layoutBonusBarang);
		
		HorizontalLayout layoutFreeLebihDari1 = new HorizontalLayout();
		HorizontalLayout layoutFreeLebihDari2 = new HorizontalLayout();
		HorizontalLayout layoutFreeLebihDari3 = new HorizontalLayout();
		HorizontalLayout layoutFreeLebihDari4 = new HorizontalLayout();
		
		HorizontalLayout bonusProductAndFreeKelipatanLayout = new HorizontalLayout();
		bonusProductAndFreeKelipatanLayout.addComponent(comboBonusProduct);
		bonusProductAndFreeKelipatanLayout.addComponent(checkFreeKelipatan);
		layoutBonusBarang.addComponent(bonusProductAndFreeKelipatanLayout);
		
		layoutFreeLebihDari1.addComponent(fieldFreeLebihDari1);
		layoutFreeLebihDari1.addComponent(fieldFreeGet1);
		layoutFreeLebihDari2.addComponent(fieldFreeLebihDari2);
		layoutFreeLebihDari2.addComponent(fieldFreeGet2);
		layoutFreeLebihDari3.addComponent(fieldFreeLebihDari3);
		layoutFreeLebihDari3.addComponent(fieldFreeGet3);
		layoutFreeLebihDari4.addComponent(fieldFreeLebihDari4);
		layoutFreeLebihDari4.addComponent(fieldFreeGet4);
		
		formLayoutBonusBarang.addComponent(panelBonusBarang);
		formLayoutBonusBarang.addComponent(layoutFreeLebihDari1);
		formLayoutBonusBarang.addComponent(layoutFreeLebihDari2);
		formLayoutBonusBarang.addComponent(layoutFreeLebihDari3);
		formLayoutBonusBarang.addComponent(layoutFreeLebihDari4);

		//DISKON from subtotal
//		panelBonusBarang.setSizeFull();
		layoutDiskonFromSubtotal.setSizeFull();
		layoutDiskonFromSubtotal.setMargin(true);
		panelDiskonFromSubtotal.setContent(layoutDiskonFromSubtotal);
		
		HorizontalLayout layoutDiskonLebihDari1 = new HorizontalLayout();
		HorizontalLayout layoutDiskonLebihDari2 = new HorizontalLayout();
		HorizontalLayout layoutDiskonLebihDari3 = new HorizontalLayout();
		HorizontalLayout layoutDiskonLebihDari4 = new HorizontalLayout();
		
//		layoutDiskon.addComponent(comboBonusProduct);		
//		formLayoutDiscount.addComponent(checkDiscKelipatan);
		
		layoutDiskonLebihDari1.addComponent(fieldDiskonLebihDari1);
		layoutDiskonLebihDari1.addComponent(fieldDiskonGet1);
		layoutDiskonLebihDari2.addComponent(fieldDiskonLebihDari2);
		layoutDiskonLebihDari2.addComponent(fieldDiskonGet2);
		layoutDiskonLebihDari3.addComponent(fieldDiskonLebihDari3);
		layoutDiskonLebihDari3.addComponent(fieldDiskonGet3);
		layoutDiskonLebihDari4.addComponent(fieldDiskonLebihDari4);
		layoutDiskonLebihDari4.addComponent(fieldDiskonGet4);
		
		formLayoutDiscount.addComponent(layoutDiskonLebihDari1);
		formLayoutDiscount.addComponent(layoutDiskonLebihDari2);
		formLayoutDiscount.addComponent(layoutDiskonLebihDari3);
		formLayoutDiscount.addComponent(layoutDiskonLebihDari4);

		//DISKON from Item
//		panelBonusBarang.setSizeFull();
		layoutDiskonFromItem.setSizeFull();
		layoutDiskonFromItem.setMargin(true);
		panelDiskonFromItem.setContent(layoutDiskonFromItem);
		
		HorizontalLayout layoutDiskonFromItemLebihDari1 = new HorizontalLayout();
		HorizontalLayout layoutDiskonFromItemLebihDari2 = new HorizontalLayout();
		HorizontalLayout layoutDiskonFromItemLebihDari3 = new HorizontalLayout();
		HorizontalLayout layoutDiskonFromItemLebihDari4 = new HorizontalLayout();
		
//		layoutDiskon.addComponent(comboBonusProduct);		
//		formLayoutDiscount.addComponent(checkDiscKelipatan);
		
		layoutDiskonFromItemLebihDari1.addComponent(fieldDiscFromItemFreeQty1);
		layoutDiskonFromItemLebihDari1.addComponent(fieldDiscFromItemdiscPercentGet1);
		layoutDiskonFromItemLebihDari2.addComponent(fieldDiscFromItemFreeQty2);
		layoutDiskonFromItemLebihDari2.addComponent(fieldDiscFromItemdiscPercentGet2);
		layoutDiskonFromItemLebihDari3.addComponent(fieldDiscFromItemFreeQty3);
		layoutDiskonFromItemLebihDari3.addComponent(fieldDiscFromItemdiscPercentGet3);
		layoutDiskonFromItemLebihDari4.addComponent(fieldDiscFromItemFreeQty4);
		layoutDiskonFromItemLebihDari4.addComponent(fieldDiscFromItemdiscPercentGet4);
		
		formLayoutDiscountFromItem.addComponent(layoutDiskonFromItemLebihDari1);
		formLayoutDiscountFromItem.addComponent(layoutDiskonFromItemLebihDari2);
		formLayoutDiscountFromItem.addComponent(layoutDiskonFromItemLebihDari3);
		formLayoutDiscountFromItem.addComponent(layoutDiskonFromItemLebihDari4);
		
		
		//CASH BACK
//		panelBonusBarang.setSizeFull();
		layoutCashBack.setSizeFull();
		layoutCashBack.setMargin(true);
		panelCashBack.setContent(layoutCashBack);
		
		HorizontalLayout layoutCashBackLebihDari1 = new HorizontalLayout();
		HorizontalLayout layoutCashBackLebihDari2 = new HorizontalLayout();
		HorizontalLayout layoutCashBackLebihDari3 = new HorizontalLayout();
		HorizontalLayout layoutCashBackLebihDari4 = new HorizontalLayout();
		
//		layoutDiskon.addComponent(comboBonusProduct);
		
		layoutCashBackLebihDari1.addComponent(fieldCashBackLebihDari1);
		layoutCashBackLebihDari1.addComponent(fieldCashBackGet1);
		layoutCashBackLebihDari2.addComponent(fieldCashBackLebihDari2);
		layoutCashBackLebihDari2.addComponent(fieldCashBackGet2);
		layoutCashBackLebihDari3.addComponent(fieldCashBackLebihDari3);
		layoutCashBackLebihDari3.addComponent(fieldCashBackGet3);
		layoutCashBackLebihDari4.addComponent(fieldCashBackLebihDari4);
		layoutCashBackLebihDari4.addComponent(fieldCashBackGet4);
		
		formLayoutCashBack.addComponent(layoutCashBackLebihDari1);
//		formLayoutCashBack.addComponent(layoutCashBackLebihDari2);
//		formLayoutCashBack.addComponent(layoutCashBackLebihDari3);
//		formLayoutCashBack.addComponent(layoutCashBackLebihDari4);
		
		tabSheet.addTab(kriteria1FormLayout, "Kriteria 1");
		tabSheet.addTab(formLayoutBonusBarang, "Bonus Barang");
		tabSheet.addTab(formLayoutDiscount, "Discount from Value");
		tabSheet.addTab(formLayoutDiscountFromItem, "Discount from Qty Item *NA");
		tabSheet.addTab(formLayoutCashBack, "Cash Back *NA");
		
		//###TOMBOL
		layoutButtonHorizontal.setSpacing(true);
		layoutButtonHorizontal.addComponent(btnSaveForm);
		layoutButtonHorizontal.addComponent(btnCancelForm);
		
//		panelForm.setContent(formLayoutPromo);
		
		//MASUKKAN KE ROOT
		layoutTop.addComponent(layoutTop1);
		
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

			tabSheet.addStyleName(Reindeer.TABSHEET_BORDERLESS);
			tabSheet.addStyleName(Reindeer.TABSHEET_SMALL);
			
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
		comboProduct.setContainerDataSource(model.getBeanItemContainerFproduct());

		comboBonusProduct.setContainerDataSource(model.getBeanItemContainerFproduct());
		comboProductGroup.setContainerDataSource(model.getBeanItemContainerFproductgroup());
		comboJenisCust.setContainerDataSource(model.getBeanItemContainerFcustomersubgroup());
		
		model.getBinderHeader().bind(fieldId, "norek");
		model.getBinderHeader().bind(comboProduct, "fProductBean");
		model.getBinderHeader().bind(comboProductGroup, "fproductgroupBean");
		model.getBinderHeader().bind(checkProductGroupAkumulasi, "forFproductGroupAkumulasi");
		model.getBinderHeader().bind(fieldDescription, "description");
		model.getBinderHeader().bind(dateFieldperiodeDateFrom, "periodeFrom");
		model.getBinderHeader().bind(dateFieldperiodeDateTo, "periodeTo");
		model.getBinderHeader().bind(checkStatusActive, "statusAktifPromo");
		
		model.getBinderHeader().bind(checkClaimPabrik, "claimPabrik");
		model.getBinderHeader().bind(fieldTarget, "target");
		model.getBinderHeader().bind(fieldTargetApplied, "targetApplied");

		model.getBinderHeader().bind(comboJenisCustKrieria, "forFcustomersubgroup");
		model.getBinderHeader().bind(comboJenisCust, "fcustomersubgroupBean");
		
		model.getBinderHeader().bind(comboBonusProduct, "freeFproductBean");
		model.getBinderHeader().bind(checkFreeKelipatan, "freeKelipatan");
		model.getBinderHeader().bind(fieldFreeLebihDari1, "freeQty1");
		model.getBinderHeader().bind(fieldFreeGet1, "freeQtyGet1");
		model.getBinderHeader().bind(fieldFreeLebihDari2, "freeQty2");
		model.getBinderHeader().bind(fieldFreeGet2, "freeQtyGet2");
		model.getBinderHeader().bind(fieldFreeLebihDari3, "freeQty3");
		model.getBinderHeader().bind(fieldFreeGet3, "freeQtyGet3");
		model.getBinderHeader().bind(fieldFreeLebihDari4, "freeQty4");
		model.getBinderHeader().bind(fieldFreeGet4, "freeQtyGet4");

		model.getBinderHeader().bind(checkDiscKelipatan, "discKelipatan");
		model.getBinderHeader().bind(fieldDiskonLebihDari1, "discValue1");
		model.getBinderHeader().bind(fieldDiskonGet1, "discPercentGet1");
		model.getBinderHeader().bind(fieldDiskonLebihDari2, "discValue2");
		model.getBinderHeader().bind(fieldDiskonGet2, "discPercentGet2");
		model.getBinderHeader().bind(fieldDiskonLebihDari3, "discValue3");
		model.getBinderHeader().bind(fieldDiskonGet3, "discPercentGet3");
		model.getBinderHeader().bind(fieldDiskonLebihDari4, "discValue4");
		model.getBinderHeader().bind(fieldDiskonGet4, "discPercentGet4");

		model.getBinderHeader().bind(checkDiscFromItemKelipatan, "discFromItemKelipatan");
		model.getBinderHeader().bind(fieldDiscFromItemFreeQty1, "discFromItemFreeQty1");
		model.getBinderHeader().bind(fieldDiscFromItemdiscPercentGet1, "discFromItemdiscPercentGet1");
		model.getBinderHeader().bind(fieldDiscFromItemFreeQty2, "discFromItemFreeQty2");
		model.getBinderHeader().bind(fieldDiscFromItemdiscPercentGet2, "discFromItemdiscPercentGet2");
		model.getBinderHeader().bind(fieldDiscFromItemFreeQty3, "discFromItemFreeQty3");
		model.getBinderHeader().bind(fieldDiscFromItemdiscPercentGet3, "discFromItemdiscPercentGet3");
		model.getBinderHeader().bind(fieldDiscFromItemFreeQty4, "discFromItemFreeQty4");
		model.getBinderHeader().bind(fieldDiscFromItemdiscPercentGet4, "discFromItemdiscPercentGet4");
		
		model.getBinderHeader().bind(fieldCashBackLebihDari1, "cashBackValue1");
		model.getBinderHeader().bind(fieldCashBackGet1, "cashBackGet1");
		model.getBinderHeader().bind(fieldCashBackLebihDari2, "cashBackValue2");
		model.getBinderHeader().bind(fieldCashBackGet2, "cashBackGet2");
		model.getBinderHeader().bind(fieldCashBackLebihDari3, "cashBackValue3");
		model.getBinderHeader().bind(fieldCashBackGet3, "cashBackGet3");
		model.getBinderHeader().bind(fieldCashBackLebihDari4, "cashBackValue4");
		model.getBinderHeader().bind(fieldCashBackGet4, "cashBackGet4");
		
		
	}
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){

//		setVisibleTableProperties("id", "description","periodeFrom" , "fcustomersubgroupBean");
		setVisibleTableProperties("norek", "description", "fProductBean", "fproductgroupBean", "periodeFrom" , "periodeTo");
		
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
		table.setColumnHeader("norek", "ID");
		table.setColumnHeader("description", "DESKRIPSI");
		table.setColumnHeader("periodeFrom", "PERIODE MULAI");
		table.setColumnHeader("periodeTo", "S.D");
		
		
		
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
		windowForm.setHeight("640px");
		windowForm.setClosable(true);	
		windowForm.setResizable(false);

		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSizeFull();
		
		content.addComponent(headerFormLayoutPromo);
		content.addComponent(tabSheet);
		content.addComponent(layoutButtonHorizontal);
		content.setExpandRatio(tabSheet, 1);
		
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

	public AktifitasPromosiModel getModel() {
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

	public TextField getFieldReportDesc() {
		return fieldReportDesc;
	}

	public DateField getDateFieldperiodeDateFrom() {
		return dateFieldperiodeDateFrom;
	}

	public DateField getDateFieldperiodeDateTo() {
		return dateFieldperiodeDateTo;
	}

	public CheckBox getCheckStatusActive() {
		return checkStatusActive;
	}

	public ComboBox getComboProductGrupKriteria() {
		return comboProductGrupKriteria;
	}

	public ComboBox getComboProductGroup() {
		return comboProductGroup;
	}

	public ComboBox getComboJenisCustKrieria() {
		return comboJenisCustKrieria;
	}

	public ComboBox getComboJenisCust() {
		return comboJenisCust;
	}

	public TextField getFieldTarget() {
		return fieldTarget;
	}

	public TextField getFieldTargetApplied() {
		return fieldTargetApplied;
	}

	public ComboBox getComboGetProduct() {
		return comboBonusProduct;
	}

	public TextField getFieldFreeLebihDari1() {
		return fieldFreeLebihDari1;
	}

	public TextField getFieldFreeGet1() {
		return fieldFreeGet1;
	}

	public TextField getFieldFreeLebihDari2() {
		return fieldFreeLebihDari2;
	}

	public TextField getFieldFreeGet2() {
		return fieldFreeGet2;
	}

	public TextField getFieldFreeLebihDari3() {
		return fieldFreeLebihDari3;
	}

	public TextField getFieldFreeGet3() {
		return fieldFreeGet3;
	}

	public TextField getFieldFreeLebihDari4() {
		return fieldFreeLebihDari4;
	}

	public TextField getFieldFreeGet4() {
		return fieldFreeGet4;
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

	public Button getBtnUtility() {
		return btnUtility;
	}

	public FormLayout getFormLayout() {
		return headerFormLayoutPromo;
	}

	public Panel getPanelBudget() {
		return panelBudget;
	}

	public VerticalLayout getLayoutBudget() {
		return layoutBudget;
	}

	public Panel getPanelCustomerCriteria() {
		return panelCustomerCriteria;
	}

	public VerticalLayout getLayoutCustomerCriteria() {
		return layoutCustomerCriteria;
	}

	public Panel getPanelBonusBarang() {
		return panelBonusBarang;
	}

	public VerticalLayout getLayoutBonusBarang() {
		return layoutBonusBarang;
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

	public Window getWindowForm() {
		return windowForm;
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

	public void setModel(AktifitasPromosiModel model) {
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

	public void setFieldReportDesc(TextField fieldReportDesc) {
		this.fieldReportDesc = fieldReportDesc;
	}

	public void setDateFieldperiodeDateFrom(DateField dateFieldperiodeDateFrom) {
		this.dateFieldperiodeDateFrom = dateFieldperiodeDateFrom;
	}

	public void setDateFieldperiodeDateTo(DateField dateFieldperiodeDateTo) {
		this.dateFieldperiodeDateTo = dateFieldperiodeDateTo;
	}

	public void setCheckStatusActive(CheckBox checkStatusActive) {
		this.checkStatusActive = checkStatusActive;
	}

	public void setComboProductGrupKriteria(ComboBox comboProductGrupKriteria) {
		this.comboProductGrupKriteria = comboProductGrupKriteria;
	}

	public void setComboProductGroup(ComboBox comboProductGroup) {
		this.comboProductGroup = comboProductGroup;
	}

	public void setComboJenisCustKrieria(ComboBox comboJenisCustKrieria) {
		this.comboJenisCustKrieria = comboJenisCustKrieria;
	}

	public void setComboJenisCust(ComboBox comboJenisCust) {
		this.comboJenisCust = comboJenisCust;
	}

	public void setFieldTarget(TextField fieldTarget) {
		this.fieldTarget = fieldTarget;
	}

	public void setFieldTargetApplied(TextField fieldTargetApplied) {
		this.fieldTargetApplied = fieldTargetApplied;
	}

	public ComboBox getComboProduct() {
		return comboProduct;
	}

	public CheckBox getCheckClaimPabrik() {
		return checkClaimPabrik;
	}

	public ComboBox getComboBonusProduct() {
		return comboBonusProduct;
	}

	public CheckBox getCheckFreeKelipatan() {
		return checkFreeKelipatan;
	}

	public TextField getFieldDiskonLebihDari1() {
		return fieldDiskonLebihDari1;
	}

	public TextField getFieldDiskonGet1() {
		return fieldDiskonGet1;
	}

	public TextField getFieldDiskonLebihDari2() {
		return fieldDiskonLebihDari2;
	}

	public TextField getFieldDiskonGet2() {
		return fieldDiskonGet2;
	}

	public TextField getFieldDiskonLebihDari3() {
		return fieldDiskonLebihDari3;
	}

	public TextField getFieldDiskonGet3() {
		return fieldDiskonGet3;
	}

	public TextField getFieldDiskonLebihDari4() {
		return fieldDiskonLebihDari4;
	}

	public TextField getFieldDiskonGet4() {
		return fieldDiskonGet4;
	}

	public CheckBox getCheckDiscKelipatan() {
		return checkDiscKelipatan;
	}

	public TextField getFieldCashBackLebihDari1() {
		return fieldCashBackLebihDari1;
	}

	public TextField getFieldCashBackGet1() {
		return fieldCashBackGet1;
	}

	public TextField getFieldCashBackLebihDari2() {
		return fieldCashBackLebihDari2;
	}

	public TextField getFieldCashBackGet2() {
		return fieldCashBackGet2;
	}

	public TextField getFieldCashBackLebihDari3() {
		return fieldCashBackLebihDari3;
	}

	public TextField getFieldCashBackGet3() {
		return fieldCashBackGet3;
	}

	public TextField getFieldCashBackLebihDari4() {
		return fieldCashBackLebihDari4;
	}

	public TextField getFieldCashBackGet4() {
		return fieldCashBackGet4;
	}

	public TabSheet getTabSheet() {
		return tabSheet;
	}

	public FormLayout getFormLayoutPromo() {
		return headerFormLayoutPromo;
	}

	public FormLayout getFormLayoutBonusBarang() {
		return formLayoutBonusBarang;
	}

	public FormLayout getFormLayoutDiscount() {
		return formLayoutDiscount;
	}

	public FormLayout getFormLayoutCashBack() {
		return formLayoutCashBack;
	}

	public Panel getPanelDiskon() {
		return panelDiskonFromSubtotal;
	}

	public VerticalLayout getLayoutDiskon() {
		return layoutDiskonFromSubtotal;
	}

	public Panel getPanelCashBack() {
		return panelCashBack;
	}

	public VerticalLayout getLayoutCashBack() {
		return layoutCashBack;
	}

	public void setComboProduct(ComboBox comboProduct) {
		this.comboProduct = comboProduct;
	}

	public void setCheckClaimPabrik(CheckBox checkClaimPabrik) {
		this.checkClaimPabrik = checkClaimPabrik;
	}

	public void setComboBonusProduct(ComboBox comboBonusProduct) {
		this.comboBonusProduct = comboBonusProduct;
	}

	public void setCheckFreeKelipatan(CheckBox checkFreeKelipatan) {
		this.checkFreeKelipatan = checkFreeKelipatan;
	}

	public void setFieldDiskonLebihDari1(TextField fieldDiskonLebihDari1) {
		this.fieldDiskonLebihDari1 = fieldDiskonLebihDari1;
	}

	public void setFieldDiskonGet1(TextField fieldDiskonGet1) {
		this.fieldDiskonGet1 = fieldDiskonGet1;
	}

	public void setFieldDiskonLebihDari2(TextField fieldDiskonLebihDari2) {
		this.fieldDiskonLebihDari2 = fieldDiskonLebihDari2;
	}

	public void setFieldDiskonGet2(TextField fieldDiskonGet2) {
		this.fieldDiskonGet2 = fieldDiskonGet2;
	}

	public void setFieldDiskonLebihDari3(TextField fieldDiskonLebihDari3) {
		this.fieldDiskonLebihDari3 = fieldDiskonLebihDari3;
	}

	public void setFieldDiskonGet3(TextField fieldDiskonGet3) {
		this.fieldDiskonGet3 = fieldDiskonGet3;
	}

	public void setFieldDiskonLebihDari4(TextField fieldDiskonLebihDari4) {
		this.fieldDiskonLebihDari4 = fieldDiskonLebihDari4;
	}

	public void setFieldDiskonGet4(TextField fieldDiskonGet4) {
		this.fieldDiskonGet4 = fieldDiskonGet4;
	}

	public void setCheckDiscKelipatan(CheckBox checkDiscKelipatan) {
		this.checkDiscKelipatan = checkDiscKelipatan;
	}

	public void setFieldCashBackLebihDari1(TextField fieldCashBackLebihDari1) {
		this.fieldCashBackLebihDari1 = fieldCashBackLebihDari1;
	}

	public void setFieldCashBackGet1(TextField fieldCashBackGet1) {
		this.fieldCashBackGet1 = fieldCashBackGet1;
	}

	public void setFieldCashBackLebihDari2(TextField fieldCashBackLebihDari2) {
		this.fieldCashBackLebihDari2 = fieldCashBackLebihDari2;
	}

	public void setFieldCashBackGet2(TextField fieldCashBackGet2) {
		this.fieldCashBackGet2 = fieldCashBackGet2;
	}

	public void setFieldCashBackLebihDari3(TextField fieldCashBackLebihDari3) {
		this.fieldCashBackLebihDari3 = fieldCashBackLebihDari3;
	}

	public void setFieldCashBackGet3(TextField fieldCashBackGet3) {
		this.fieldCashBackGet3 = fieldCashBackGet3;
	}

	public void setFieldCashBackLebihDari4(TextField fieldCashBackLebihDari4) {
		this.fieldCashBackLebihDari4 = fieldCashBackLebihDari4;
	}

	public void setFieldCashBackGet4(TextField fieldCashBackGet4) {
		this.fieldCashBackGet4 = fieldCashBackGet4;
	}

	public void setTabSheet(TabSheet tabSheet) {
		this.tabSheet = tabSheet;
	}

	public void setFormLayoutPromo(FormLayout formLayoutPromo) {
		this.headerFormLayoutPromo = formLayoutPromo;
	}

	public void setFormLayoutBonusBarang(FormLayout formLayoutBonusBarang) {
		this.formLayoutBonusBarang = formLayoutBonusBarang;
	}

	public void setFormLayoutDiscount(FormLayout formLayoutDiscount) {
		this.formLayoutDiscount = formLayoutDiscount;
	}

	public void setFormLayoutCashBack(FormLayout formLayoutCashBack) {
		this.formLayoutCashBack = formLayoutCashBack;
	}

	public void setPanelDiskon(Panel panelDiskon) {
		this.panelDiskonFromSubtotal = panelDiskon;
	}

	public void setLayoutDiskon(VerticalLayout layoutDiskon) {
		this.layoutDiskonFromSubtotal = layoutDiskon;
	}

	public void setPanelCashBack(Panel panelCashBack) {
		this.panelCashBack = panelCashBack;
	}

	public void setLayoutCashBack(VerticalLayout layoutCashBack) {
		this.layoutCashBack = layoutCashBack;
	}

	public void setComboGetProduct(ComboBox comboGetProduct) {
		this.comboBonusProduct = comboGetProduct;
	}

	public void setFieldFreeLebihDari1(TextField fieldFreeLebihDari1) {
		this.fieldFreeLebihDari1 = fieldFreeLebihDari1;
	}

	public void setFieldFreeGet1(TextField fieldFreeGet1) {
		this.fieldFreeGet1 = fieldFreeGet1;
	}

	public void setFieldFreeLebihDari2(TextField fieldFreeLebihDari2) {
		this.fieldFreeLebihDari2 = fieldFreeLebihDari2;
	}

	public void setFieldFreeGet2(TextField fieldFreeGet2) {
		this.fieldFreeGet2 = fieldFreeGet2;
	}

	public void setFieldFreeLebihDari3(TextField fieldFreeLebihDari3) {
		this.fieldFreeLebihDari3 = fieldFreeLebihDari3;
	}

	public void setFieldFreeGet3(TextField fieldFreeGet3) {
		this.fieldFreeGet3 = fieldFreeGet3;
	}

	public void setFieldFreeLebihDari4(TextField fieldFreeLebihDari4) {
		this.fieldFreeLebihDari4 = fieldFreeLebihDari4;
	}

	public void setFieldFreeGet4(TextField fieldFreeGet4) {
		this.fieldFreeGet4 = fieldFreeGet4;
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

	public void setBtnUtility(Button btnUtility) {
		this.btnUtility = btnUtility;
	}

	public void setFormLayout(FormLayout formLayout) {
		this.headerFormLayoutPromo = formLayout;
	}

	public void setPanelBudget(Panel panelBudget) {
		this.panelBudget = panelBudget;
	}

	public void setLayoutBudget(VerticalLayout layoutBudget) {
		this.layoutBudget = layoutBudget;
	}

	public void setPanelCustomerCriteria(Panel panelCustomerCriteria) {
		this.panelCustomerCriteria = panelCustomerCriteria;
	}

	public void setLayoutCustomerCriteria(VerticalLayout layoutCustomerCriteria) {
		this.layoutCustomerCriteria = layoutCustomerCriteria;
	}

	public void setPanelBonusBarang(Panel panelBonusBarang) {
		this.panelBonusBarang = panelBonusBarang;
	}

	public void setLayoutBonusBarang(VerticalLayout layoutBonusBarang) {
		this.layoutBonusBarang = layoutBonusBarang;
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
	public void setWindowForm(Window windowForm) {
		this.windowForm = windowForm;
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

	public CheckBox getCheckProductGroupAkumulasi() {
		return checkProductGroupAkumulasi;
	}

	public void setCheckProductGroupAkumulasi(CheckBox checkProductGroupAkumulasi) {
		this.checkProductGroupAkumulasi = checkProductGroupAkumulasi;
	}


	
}
