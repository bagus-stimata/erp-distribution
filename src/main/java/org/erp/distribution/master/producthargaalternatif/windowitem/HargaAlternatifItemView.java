package org.erp.distribution.master.producthargaalternatif.windowitem;

import org.erp.distribution.model.modelenum.EnumOperationStatus;

import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class HargaAlternatifItemView extends CustomComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HargaAlternatifItemModel model;
	
	private VerticalLayout content = new VerticalLayout();

	private TextField fieldNomorUrut = new TextField("No)");
	private ComboBox comboProduct= new ComboBox("PRODUCT");

	private TextField fieldSprice = new TextField("HRG Jual(-PPN)");
	private TextField fieldSpriceafterppn = new TextField("HRG Jual+PPN");
	private TextField fieldSprice2 = new TextField("PCS Jual(-PPN)");
	private TextField fieldSprice2afterppn = new TextField("PCS Jual+PPN");

	private TextField fieldSpricealt = new TextField("HRG JUAL ALT(-PPN)");
	private TextField fieldSpricealtafterppn = new TextField("HRG JUAL ALT+PPN");
	private TextField fieldSpricealt2 = new TextField("PCS JUAL ALT(-PPN)");
	private TextField fieldSpricealt2afterppn = new TextField("PCS JUAL ALT+PPN");
	
	private TextField fieldQty1 = new TextField("Bes");
	private TextField fieldQty2 = new TextField("Sed");
	private TextField fieldQty3 = new TextField("Kec");
	private TextField fieldQty = new TextField("QTY PCS");
	
	private TextField fieldDisc1 = new TextField("DISC1 %");
	private TextField fieldDisc2 = new TextField("+DISC2 %");
	private TextField fieldDisc1rp = new TextField("DISC1 Rp");
	private TextField fieldDisc2rp = new TextField("DISC2 Rp");

	private TextField fieldSubtotal = new TextField("TOTAL(-PPN)");
	private TextField fieldSubtotalafterppn = new TextField("TOTAL+PPN");
	private TextField fieldSubtotalafterdisc = new TextField("TOTAL(-DISC-PPN)");
	private TextField fieldSubtotalafterdiscafterppn = new TextField("TOTAL-DISC+PPN");
	
	private Button btnAddAndSave = new Button("Add or Update");
	private Button btnReset = new Button("Reset");
	private Button btnClose = new Button("Close");
	
	private Button btnSeparator1 = new Button("*");
	private Button btnSeparator2 = new Button("*");
	private Button btnSeparator3 = new Button("*");

	//LAYOUT
	private FormLayout formLayout = new FormLayout();
	
	//Panel
	private Panel panelUtama = new Panel();
	private Panel panelForm = new Panel();

	private Panel panelComboProduct = new Panel("PRODUCT");
	
	private Panel panelFieldSprice = new Panel("H. JUAL-PPN");
	private Panel panelFieldSpriceafterppn = new Panel("H. JUAL+PPN");
	private Panel panelFieldSprice2 = new Panel("PCS.JUAL-PPN");
	private Panel panelFieldSprice2afterppn = new Panel("PCS.JUAL+PPN");
	
	private Panel panelFieldSpricealt = new Panel("H. JUAL ALT-PPN");
	private Panel panelFieldSpricealtafterppn = new Panel("H. JUAL ALT+PPN");
	private Panel panelFieldSpricealt2 = new Panel("PCS.JUAL ALT-PPN");
	private Panel panelFieldSpricealt2afterppn = new Panel("PCS.JUAL ALT+PPN");
	
	private Panel panelCheckFreegood = new Panel("BNS");
	private Panel panelQty1 = new Panel("BES");
	private Panel panelQty2 = new Panel("SED");
	private Panel panelQty3 = new Panel("KEC");
	private Panel panelDisc1 = new Panel("DISC1");
	private Panel panelDisc2 = new Panel("+DISC2");
	private Panel panelSubtotalafterdiscafterppn = new Panel("TOTAL-DISC-PPN");
	
	public HargaAlternatifItemView(HargaAlternatifItemModel model){
		this.model = model;
		initComponent();
		buildView();
		
		setDisplay();	
		initComponentState();
		
	}
	public void initComponent(){

		comboProduct.setWidth("300px");

		fieldSpricealt.setWidth("120px");
		fieldSpricealtafterppn.setWidth("120px");
		fieldSprice.setWidth("100px");
		fieldSpricealt2afterppn.setWidth("100px");
		
		fieldSprice.setWidth("120px");
		fieldSpriceafterppn.setWidth("120px");
		fieldSprice2.setWidth("100px");
		fieldSprice2afterppn.setWidth("100px");

		fieldQty1.setWidth("50px");
		fieldQty2.setWidth("50px");
		fieldQty3.setWidth("50px");
		fieldQty.setWidth("50px");
		
		fieldDisc1.setWidth("50px");
		fieldDisc2.setWidth("50px");
		fieldDisc1rp.setWidth("100px");
		fieldDisc2rp.setWidth("100px");
		
		fieldSubtotal.setWidth("120px");
		fieldSubtotalafterppn.setWidth("120px");
		fieldSubtotalafterdisc.setWidth("120px");
		fieldSubtotalafterdiscafterppn.setWidth("120px");
		
		//NULL REPRESENTATION
		fieldSpricealt.setNullRepresentation("");
		fieldSpricealtafterppn.setNullRepresentation("");
		fieldSpricealt2.setNullRepresentation("");
		fieldSpricealt2afterppn.setNullRepresentation("");
		
		fieldSprice.setNullRepresentation("");
		fieldSpriceafterppn.setNullRepresentation("");
		fieldSprice2.setNullRepresentation("");
		fieldSprice2afterppn.setNullRepresentation("");
		
		fieldSubtotal.setNullRepresentation("");
		fieldSubtotalafterppn.setNullRepresentation("");
		fieldSubtotalafterdisc.setNullRepresentation("");
		fieldSubtotalafterdiscafterppn.setNullRepresentation("");
		
		//NOT NULLABLE
		comboProduct.setRequired(true);
		fieldSprice.setRequired(true);
		fieldSpriceafterppn.setRequired(true);
		
		fieldQty1.setRequired(true);
		fieldQty2.setRequired(true);
		fieldQty3.setRequired(true);
		fieldQty.setRequired(true);
		
		fieldDisc1.setRequired(true);
		fieldDisc2.setRequired(true);
		
		fieldSubtotal.setRequired(true);
		
	}
	
	public void buildView(){
		
		//Inisialisasi Panel 
		setSizeFull();
		content.setSizeFull();
		content.setMargin(true);
		
		//PANEL
		panelUtama.setSizeFull();
		
		HorizontalLayout layoutTop1 = new HorizontalLayout();		
		HorizontalLayout layoutTop2 = new HorizontalLayout();		
		HorizontalLayout layoutTop3 = new HorizontalLayout();		
		HorizontalLayout layoutBottom = new HorizontalLayout();		
		//INIT COMPONENT ATAS
		btnSeparator1.setEnabled(false);
		btnSeparator2.setEnabled(false);
		btnSeparator3.setEnabled(false);

//		layoutTop.addComponent(fieldNomorUrut);
		panelComboProduct.setContent(comboProduct);
		layoutTop1.addComponent(panelComboProduct);
		
//		panelCheckFreegood.setContent(checkFreegood);
//		layoutTop.addComponent(panelCheckFreegood);
//		layoutTop.addComponent(btnSeparator2);
		
//		layoutTop3.addComponent(btnSeparator3);
		panelFieldSprice.setContent(fieldSprice);
		panelFieldSpriceafterppn.setContent(fieldSpriceafterppn);
		panelFieldSprice2.setContent(fieldSprice2);
		panelFieldSprice2afterppn.setContent(fieldSprice2afterppn);
		layoutTop1.addComponent(panelFieldSprice);
		layoutTop1.addComponent(panelFieldSpriceafterppn);
		layoutTop1.addComponent(panelFieldSprice2);
		layoutTop1.addComponent(panelFieldSprice2afterppn);	

		panelFieldSpricealt.setContent(fieldSpricealt);
		panelFieldSpricealtafterppn.setContent(fieldSpricealtafterppn);
		panelFieldSpricealt2.setContent(fieldSpricealt2);
		panelFieldSpricealt2afterppn.setContent(fieldSpricealt2afterppn);
		layoutTop1.addComponent(panelFieldSpricealt);
		layoutTop1.addComponent(panelFieldSpricealtafterppn);
		layoutTop1.addComponent(panelFieldSpricealt2);
		layoutTop1.addComponent(panelFieldSpricealt2afterppn);	
		
		layoutBottom.addComponent(btnAddAndSave);
		layoutBottom.addComponent(btnReset);
		layoutBottom.addComponent(btnClose);
		
		
		//MASUKKAN KE ROOT

		content.addComponent(layoutTop1);		
//		content.addComponent(layoutTop2);		
//		content.addComponent(layoutTop3);				
		content.addComponent(layoutBottom);		
		panelUtama.setContent(content);
		
		setCompositionRoot(panelUtama);	
		
		//finishing PERMAK 
//		content.setComponentAlignment(layoutBottom, Alignment.BOTTOM_RIGHT);
		
		
	}
	
	public void setDisplay(){
		
		bindAndBuildFieldGroupComponent();
		setFormButtonAndTextState();
		
	}
	public void initComponentState(){
		panelFieldSprice.setVisible(false);
		panelFieldSprice2.setVisible(false);
		panelFieldSprice2afterppn.setVisible(false);
		panelFieldSpriceafterppn.setVisible(false);
		
	}
	
	public void setDisplayTableFooter(){		
	}

	public void bindAndBuildFieldGroupComponent(){
		model.getBinderItemDetail().setItemDataSource(model.itemDetil);
		
		model.getBinderItemDetail().setBuffered(false);
		
		//COMBO ACCCONT
		comboProduct.setContainerDataSource(model.getBeanItemContainerProduct());
		comboProduct.setNewItemsAllowed(false);
		comboProduct.setFilteringMode(FilteringMode.CONTAINS);
		comboProduct.setNullSelectionAllowed(false);
		
//		model.getBinderItemDetail().bind(fieldNomorUrut, "nourut");
		model.getBinderItemDetail().bind(comboProduct, "fproductBean");

//		model.getBinderItemDetail().bind(fieldSpricebefore, "spricebefore");
		model.getBinderItemDetail().bind(fieldSprice, "sprice");
		model.getBinderItemDetail().bind(fieldSpriceafterppn, "spriceafterppn");
		model.getBinderItemDetail().bind(fieldSprice2, "sprice2");
		model.getBinderItemDetail().bind(fieldSprice2afterppn, "sprice2afterppn");
		
//		model.getBinderItemDetail().bind(fieldPpricebefore, "ppricebefore");
		model.getBinderItemDetail().bind(fieldSpricealt, "spricealt");
		model.getBinderItemDetail().bind(fieldSpricealtafterppn, "spricealtafterppn");
		model.getBinderItemDetail().bind(fieldSpricealt2, "spricealt2");
		model.getBinderItemDetail().bind(fieldSpricealt2afterppn, "spricealt2afterppn");

	}
	
	public void focustIdOrDesc(){
		comboProduct.focus();
	}
	
	public void setFormButtonAndTextState(){
		
		if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
			btnAddAndSave.setCaption("Add");
		} else if(model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
			btnAddAndSave.setCaption("Update & Close");
		}
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public HargaAlternatifItemModel getModel() {
		return model;
	}
	public VerticalLayout getContent() {
		return content;
	}
	public TextField getFieldNomorUrut() {
		return fieldNomorUrut;
	}
	public ComboBox getComboProduct() {
		return comboProduct;
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
	public TextField getFieldSprice2afterppn() {
		return fieldSprice2afterppn;
	}
	public TextField getFieldSpricealt() {
		return fieldSpricealt;
	}
	public TextField getFieldSpricealtafterppn() {
		return fieldSpricealtafterppn;
	}
	public TextField getFieldSpricealt2() {
		return fieldSpricealt2;
	}
	public TextField getFieldSpricealt2afterppn() {
		return fieldSpricealt2afterppn;
	}
	public TextField getFieldQty1() {
		return fieldQty1;
	}
	public TextField getFieldQty2() {
		return fieldQty2;
	}
	public TextField getFieldQty3() {
		return fieldQty3;
	}
	public TextField getFieldQty() {
		return fieldQty;
	}
	public TextField getFieldDisc1() {
		return fieldDisc1;
	}
	public TextField getFieldDisc2() {
		return fieldDisc2;
	}
	public TextField getFieldDisc1rp() {
		return fieldDisc1rp;
	}
	public TextField getFieldDisc2rp() {
		return fieldDisc2rp;
	}
	public TextField getFieldSubtotal() {
		return fieldSubtotal;
	}
	public TextField getFieldSubtotalafterppn() {
		return fieldSubtotalafterppn;
	}
	public TextField getFieldSubtotalafterdisc() {
		return fieldSubtotalafterdisc;
	}
	public TextField getFieldSubtotalafterdiscafterppn() {
		return fieldSubtotalafterdiscafterppn;
	}
	public Button getBtnAddAndSave() {
		return btnAddAndSave;
	}
	public Button getBtnReset() {
		return btnReset;
	}
	public Button getBtnClose() {
		return btnClose;
	}
	public Button getBtnSeparator1() {
		return btnSeparator1;
	}
	public Button getBtnSeparator2() {
		return btnSeparator2;
	}
	public Button getBtnSeparator3() {
		return btnSeparator3;
	}
	public FormLayout getFormLayout() {
		return formLayout;
	}
	public Panel getPanelUtama() {
		return panelUtama;
	}
	public Panel getPanelForm() {
		return panelForm;
	}
	public Panel getPanelComboProduct() {
		return panelComboProduct;
	}
	public Panel getPanelFieldSprice() {
		return panelFieldSprice;
	}
	public Panel getPanelFieldSpriceafterppn() {
		return panelFieldSpriceafterppn;
	}
	public Panel getPanelFieldSprice2() {
		return panelFieldSprice2;
	}
	public Panel getPanelFieldSprice2afterppn() {
		return panelFieldSprice2afterppn;
	}
	public Panel getPanelFieldSpricealt() {
		return panelFieldSpricealt;
	}
	public Panel getPanelFieldSpricealtafterppn() {
		return panelFieldSpricealtafterppn;
	}
	public Panel getPanelFieldSpricealt2() {
		return panelFieldSpricealt2;
	}
	public Panel getPanelFieldSpricealt2afterppn() {
		return panelFieldSpricealt2afterppn;
	}
	public Panel getPanelCheckFreegood() {
		return panelCheckFreegood;
	}
	public Panel getPanelQty1() {
		return panelQty1;
	}
	public Panel getPanelQty2() {
		return panelQty2;
	}
	public Panel getPanelQty3() {
		return panelQty3;
	}
	public Panel getPanelDisc1() {
		return panelDisc1;
	}
	public Panel getPanelDisc2() {
		return panelDisc2;
	}
	public Panel getPanelSubtotalafterdiscafterppn() {
		return panelSubtotalafterdiscafterppn;
	}
	public void setModel(HargaAlternatifItemModel model) {
		this.model = model;
	}
	public void setContent(VerticalLayout content) {
		this.content = content;
	}
	public void setFieldNomorUrut(TextField fieldNomorUrut) {
		this.fieldNomorUrut = fieldNomorUrut;
	}
	public void setComboProduct(ComboBox comboProduct) {
		this.comboProduct = comboProduct;
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
	public void setFieldSprice2afterppn(TextField fieldSprice2afterppn) {
		this.fieldSprice2afterppn = fieldSprice2afterppn;
	}
	public void setFieldSpricealt(TextField fieldSpricealt) {
		this.fieldSpricealt = fieldSpricealt;
	}
	public void setFieldSpricealtafterppn(TextField fieldSpricealtafterppn) {
		this.fieldSpricealtafterppn = fieldSpricealtafterppn;
	}
	public void setFieldSpricealt2(TextField fieldSpricealt2) {
		this.fieldSpricealt2 = fieldSpricealt2;
	}
	public void setFieldSpricealt2afterppn(TextField fieldSpricealt2afterppn) {
		this.fieldSpricealt2afterppn = fieldSpricealt2afterppn;
	}
	public void setFieldQty1(TextField fieldQty1) {
		this.fieldQty1 = fieldQty1;
	}
	public void setFieldQty2(TextField fieldQty2) {
		this.fieldQty2 = fieldQty2;
	}
	public void setFieldQty3(TextField fieldQty3) {
		this.fieldQty3 = fieldQty3;
	}
	public void setFieldQty(TextField fieldQty) {
		this.fieldQty = fieldQty;
	}
	public void setFieldDisc1(TextField fieldDisc1) {
		this.fieldDisc1 = fieldDisc1;
	}
	public void setFieldDisc2(TextField fieldDisc2) {
		this.fieldDisc2 = fieldDisc2;
	}
	public void setFieldDisc1rp(TextField fieldDisc1rp) {
		this.fieldDisc1rp = fieldDisc1rp;
	}
	public void setFieldDisc2rp(TextField fieldDisc2rp) {
		this.fieldDisc2rp = fieldDisc2rp;
	}
	public void setFieldSubtotal(TextField fieldSubtotal) {
		this.fieldSubtotal = fieldSubtotal;
	}
	public void setFieldSubtotalafterppn(TextField fieldSubtotalafterppn) {
		this.fieldSubtotalafterppn = fieldSubtotalafterppn;
	}
	public void setFieldSubtotalafterdisc(TextField fieldSubtotalafterdisc) {
		this.fieldSubtotalafterdisc = fieldSubtotalafterdisc;
	}
	public void setFieldSubtotalafterdiscafterppn(
			TextField fieldSubtotalafterdiscafterppn) {
		this.fieldSubtotalafterdiscafterppn = fieldSubtotalafterdiscafterppn;
	}
	public void setBtnAddAndSave(Button btnAddAndSave) {
		this.btnAddAndSave = btnAddAndSave;
	}
	public void setBtnReset(Button btnReset) {
		this.btnReset = btnReset;
	}
	public void setBtnClose(Button btnClose) {
		this.btnClose = btnClose;
	}
	public void setBtnSeparator1(Button btnSeparator1) {
		this.btnSeparator1 = btnSeparator1;
	}
	public void setBtnSeparator2(Button btnSeparator2) {
		this.btnSeparator2 = btnSeparator2;
	}
	public void setBtnSeparator3(Button btnSeparator3) {
		this.btnSeparator3 = btnSeparator3;
	}
	public void setFormLayout(FormLayout formLayout) {
		this.formLayout = formLayout;
	}
	public void setPanelUtama(Panel panelUtama) {
		this.panelUtama = panelUtama;
	}
	public void setPanelForm(Panel panelForm) {
		this.panelForm = panelForm;
	}
	public void setPanelComboProduct(Panel panelComboProduct) {
		this.panelComboProduct = panelComboProduct;
	}
	public void setPanelFieldSprice(Panel panelFieldSprice) {
		this.panelFieldSprice = panelFieldSprice;
	}
	public void setPanelFieldSpriceafterppn(Panel panelFieldSpriceafterppn) {
		this.panelFieldSpriceafterppn = panelFieldSpriceafterppn;
	}
	public void setPanelFieldSprice2(Panel panelFieldSprice2) {
		this.panelFieldSprice2 = panelFieldSprice2;
	}
	public void setPanelFieldSprice2afterppn(Panel panelFieldSprice2afterppn) {
		this.panelFieldSprice2afterppn = panelFieldSprice2afterppn;
	}
	public void setPanelFieldSpricealt(Panel panelFieldSpricealt) {
		this.panelFieldSpricealt = panelFieldSpricealt;
	}
	public void setPanelFieldSpricealtafterppn(Panel panelFieldSpricealtafterppn) {
		this.panelFieldSpricealtafterppn = panelFieldSpricealtafterppn;
	}
	public void setPanelFieldSpricealt2(Panel panelFieldSpricealt2) {
		this.panelFieldSpricealt2 = panelFieldSpricealt2;
	}
	public void setPanelFieldSpricealt2afterppn(Panel panelFieldSpricealt2afterppn) {
		this.panelFieldSpricealt2afterppn = panelFieldSpricealt2afterppn;
	}
	public void setPanelCheckFreegood(Panel panelCheckFreegood) {
		this.panelCheckFreegood = panelCheckFreegood;
	}
	public void setPanelQty1(Panel panelQty1) {
		this.panelQty1 = panelQty1;
	}
	public void setPanelQty2(Panel panelQty2) {
		this.panelQty2 = panelQty2;
	}
	public void setPanelQty3(Panel panelQty3) {
		this.panelQty3 = panelQty3;
	}
	public void setPanelDisc1(Panel panelDisc1) {
		this.panelDisc1 = panelDisc1;
	}
	public void setPanelDisc2(Panel panelDisc2) {
		this.panelDisc2 = panelDisc2;
	}
	public void setPanelSubtotalafterdiscafterppn(
			Panel panelSubtotalafterdiscafterppn) {
		this.panelSubtotalafterdiscafterppn = panelSubtotalafterdiscafterppn;
	}
	
	
	
}
