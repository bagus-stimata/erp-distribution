package org.erp.distribution.master.productperubahanharga.windowitem;

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

public class PerubahanHargaItemView extends CustomComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PerubahanHargaItemModel model;
	
	private VerticalLayout content = new VerticalLayout();

	private TextField fieldNomorUrut = new TextField("No)");
	private ComboBox comboProduct= new ComboBox("PRODUCT");
	private TextField fieldPprice = new TextField("HRG BELI(-PPN)");
	private TextField fieldPpriceafterppn = new TextField("HRG BELI+PPN");
	private TextField fieldPprice2 = new TextField("PCS BELI(-PPN)");
	private TextField fieldPprice2afterppn = new TextField("PCS BELI+PPN");

	private TextField fieldSprice = new TextField("HRG Jual(-PPN)");
	private TextField fieldSpriceafterppn = new TextField("HRG Jual+PPN");
	private TextField fieldSprice2 = new TextField("PCS Jual(-PPN)");
	private TextField fieldSprice2afterppn = new TextField("PCS Jual+PPN");

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
	
	private Panel panelFieldPprice = new Panel("H. BELI-PPN");
	private Panel panelFieldPpriceafterppn = new Panel("H. BELI+PPN");
	private Panel panelFieldPprice2 = new Panel("PCS.BELI-PPN");
	private Panel panelFieldPprice2afterppn = new Panel("PCS.BELI+PPN");
	
	private Panel panelFieldSprice = new Panel("H. JUAL-PPN");
	private Panel panelFieldSpriceafterppn = new Panel("H. JUAL+PPN");
	private Panel panelFieldSprice2 = new Panel("PCS.JUAL-PPN");
	private Panel panelFieldSprice2afterppn = new Panel("PCS.JUAL+PPN");
	
	private Panel panelCheckFreegood = new Panel("BNS");
	private Panel panelQty1 = new Panel("BES");
	private Panel panelQty2 = new Panel("SED");
	private Panel panelQty3 = new Panel("KEC");
	private Panel panelDisc1 = new Panel("DISC1");
	private Panel panelDisc2 = new Panel("+DISC2");
	private Panel panelSubtotalafterdiscafterppn = new Panel("TOTAL-DISC-PPN");
	
	public PerubahanHargaItemView(PerubahanHargaItemModel model){
		this.model = model;
		initComponent();
		buildView();
		
		setDisplay();	
		
	}
	public void initComponent(){

		comboProduct.setWidth("300px");

		fieldPprice.setWidth("120px");
		fieldPpriceafterppn.setWidth("120px");
		fieldPprice2.setWidth("100px");
		fieldPprice2afterppn.setWidth("100px");
		
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
		fieldPprice.setNullRepresentation("");
		fieldPpriceafterppn.setNullRepresentation("");
		fieldPprice2.setNullRepresentation("");
		fieldPprice2afterppn.setNullRepresentation("");
		
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
		fieldPprice.setRequired(true);
		fieldPpriceafterppn.setRequired(true);
		
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
		panelFieldPprice.setContent(fieldPprice);
		panelFieldPpriceafterppn.setContent(fieldPpriceafterppn);
		panelFieldPprice2.setContent(fieldPprice2);
		panelFieldPprice2afterppn.setContent(fieldPprice2afterppn);		
		layoutTop2.addComponent(panelFieldPprice);
		layoutTop2.addComponent(panelFieldPpriceafterppn);
		layoutTop2.addComponent(panelFieldPprice2);
		layoutTop2.addComponent(panelFieldPprice2afterppn);
		
//		layoutTop3.addComponent(btnSeparator3);
		panelFieldSprice.setContent(fieldSprice);
		panelFieldSpriceafterppn.setContent(fieldSpriceafterppn);
		panelFieldSprice2.setContent(fieldSprice2);
		panelFieldSprice2afterppn.setContent(fieldSprice2afterppn);
		layoutTop3.addComponent(panelFieldSprice);
		layoutTop3.addComponent(panelFieldSpriceafterppn);
		layoutTop3.addComponent(panelFieldSprice2);
		layoutTop3.addComponent(panelFieldSprice2afterppn);	
		
		layoutBottom.addComponent(btnAddAndSave);
		layoutBottom.addComponent(btnReset);
		layoutBottom.addComponent(btnClose);
		
		
		//MASUKKAN KE ROOT

		content.addComponent(layoutTop1);		
		content.addComponent(layoutTop2);		
		content.addComponent(layoutTop3);				
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

//		model.getBinderItemDetail().bind(fieldPpricebefore, "ppricebefore");
		model.getBinderItemDetail().bind(fieldPprice, "pprice");
		model.getBinderItemDetail().bind(fieldPpriceafterppn, "ppriceafterppn");
		model.getBinderItemDetail().bind(fieldPprice2, "pprice2");
		model.getBinderItemDetail().bind(fieldPprice2afterppn, "pprice2afterppn");

//		model.getBinderItemDetail().bind(fieldSpricebefore, "spricebefore");
		model.getBinderItemDetail().bind(fieldSprice, "sprice");
		model.getBinderItemDetail().bind(fieldSpriceafterppn, "spriceafterppn");
		model.getBinderItemDetail().bind(fieldSprice2, "sprice2");
		model.getBinderItemDetail().bind(fieldSprice2afterppn, "sprice2afterppn");
		
		
		
	}
	public void setFormButtonAndTextState(){
		
		if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
			btnAddAndSave.setCaption("Add");
		} else if(model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
			btnAddAndSave.setCaption("Update & Close");
		}
	}
	
	public void focustIdOrDesc(){
		comboProduct.focus();
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public PerubahanHargaItemModel getModel() {
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
	public TextField getFieldPprice() {
		return fieldPprice;
	}
	public TextField getFieldPpriceafterppn() {
		return fieldPpriceafterppn;
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
	public void setModel(PerubahanHargaItemModel model) {
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
	public void setFieldPprice(TextField fieldPprice) {
		this.fieldPprice = fieldPprice;
	}
	public void setFieldPpriceafterppn(TextField fieldPpriceafterppn) {
		this.fieldPpriceafterppn = fieldPpriceafterppn;
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
	public Panel getPanelComboProduct() {
		return panelComboProduct;
	}
	public Panel getPanelFieldSprice() {
		return panelFieldSprice;
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
	public void setPanelComboProduct(Panel panelComboProduct) {
		this.panelComboProduct = panelComboProduct;
	}
	public void setPanelFieldSprice(Panel panelFieldSprice) {
		this.panelFieldSprice = panelFieldSprice;
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
	public TextField getFieldSprice() {
		return fieldSprice;
	}
	public TextField getFieldSpriceafterppn() {
		return fieldSpriceafterppn;
	}
	public Panel getPanelFieldPprice() {
		return panelFieldPprice;
	}
	public Panel getPanelFieldPpriceafterppn() {
		return panelFieldPpriceafterppn;
	}
	public Panel getPanelFieldSpriceafterppn() {
		return panelFieldSpriceafterppn;
	}
	public void setFieldSprice(TextField fieldSprice) {
		this.fieldSprice = fieldSprice;
	}
	public void setFieldSpriceafterppn(TextField fieldSpriceafterppn) {
		this.fieldSpriceafterppn = fieldSpriceafterppn;
	}
	public void setPanelFieldPprice(Panel panelFieldPprice) {
		this.panelFieldPprice = panelFieldPprice;
	}
	public void setPanelFieldPpriceafterppn(Panel panelFieldPpriceafterppn) {
		this.panelFieldPpriceafterppn = panelFieldPpriceafterppn;
	}
	public void setPanelFieldSpriceafterppn(Panel panelFieldSpriceafterppn) {
		this.panelFieldSpriceafterppn = panelFieldSpriceafterppn;
	}
	public TextField getFieldPprice2() {
		return fieldPprice2;
	}
	public TextField getFieldPprice2afterppn() {
		return fieldPprice2afterppn;
	}
	public TextField getFieldSprice2() {
		return fieldSprice2;
	}
	public TextField getFieldSprice2afterppn() {
		return fieldSprice2afterppn;
	}
	public Panel getPanelFieldPprice2() {
		return panelFieldPprice2;
	}
	public Panel getPanelFieldPprice2afterppn() {
		return panelFieldPprice2afterppn;
	}
	public Panel getPanelFieldSprice2() {
		return panelFieldSprice2;
	}
	public Panel getPanelFieldSprice2afterppn() {
		return panelFieldSprice2afterppn;
	}
	public void setFieldPprice2(TextField fieldPprice2) {
		this.fieldPprice2 = fieldPprice2;
	}
	public void setFieldPprice2afterppn(TextField fieldPprice2afterppn) {
		this.fieldPprice2afterppn = fieldPprice2afterppn;
	}
	public void setFieldSprice2(TextField fieldSprice2) {
		this.fieldSprice2 = fieldSprice2;
	}
	public void setFieldSprice2afterppn(TextField fieldSprice2afterppn) {
		this.fieldSprice2afterppn = fieldSprice2afterppn;
	}
	public void setPanelFieldPprice2(Panel panelFieldPprice2) {
		this.panelFieldPprice2 = panelFieldPprice2;
	}
	public void setPanelFieldPprice2afterppn(Panel panelFieldPprice2afterppn) {
		this.panelFieldPprice2afterppn = panelFieldPprice2afterppn;
	}
	public void setPanelFieldSprice2(Panel panelFieldSprice2) {
		this.panelFieldSprice2 = panelFieldSprice2;
	}
	public void setPanelFieldSprice2afterppn(Panel panelFieldSprice2afterppn) {
		this.panelFieldSprice2afterppn = panelFieldSprice2afterppn;
	}
	

	
	
}
