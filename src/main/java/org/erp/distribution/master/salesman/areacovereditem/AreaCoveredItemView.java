package org.erp.distribution.master.salesman.areacovereditem;

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

public class AreaCoveredItemView extends CustomComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AreaCoveredItemModel model;
	
	private VerticalLayout content = new VerticalLayout();

	private TextField fieldNomorUrut = new TextField("No)");
	private ComboBox comboArea= new ComboBox("Area");//		model.getBinderItemDetail().bind(fieldNomorUrut, "nourut");

	private TextField fieldKode = new TextField("KODE");
	private TextField fieldNama = new TextField("NAMA");

	private TextField fieldAlamat = new TextField("ALAMAT");
	private TextField fieldKota = new TextField("KOTA");
	
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

	private Panel panelComboArea = new Panel("AREA");
	private Panel panelKode = new Panel("KODE");
	private Panel panelNama = new Panel("NAMA");
	private Panel panelAlamat = new Panel("ALAMAT");
	private Panel panelKota = new Panel("KOTA");
	
	public AreaCoveredItemView(AreaCoveredItemModel model){
		this.model = model;
		initComponent();
		buildView();
		
		setDisplay();	
		
	}
	public void initComponent(){

		comboArea.setWidth("300px");

		fieldKode.setWidth("70px");
		fieldNama.setWidth("150px");

		fieldAlamat.setWidth("200px");
		fieldKota.setWidth("100px");
		
		//NULL REPRESENTATION
		fieldKode.setNullRepresentation("");
		fieldNama.setNullRepresentation("");
		fieldAlamat.setNullRepresentation("");
		fieldKota.setNullRepresentation("");
		
		//NOT NULLABLE//		model.getBinderItemDetail().bind(fieldNomorUrut, "nourut");

		comboArea.setRequired(true);
		fieldKode.setRequired(true);
		fieldNama.setRequired(true);
		
		fieldAlamat.setRequired(true);
		fieldKota.setRequired(true);
		
	}
	
	public void buildView(){
		
		//Inisialisasi Panel 
		setSizeFull();
		content.setSizeFull();
		
		//PANEL
		panelUtama.setSizeFull();

		HorizontalLayout layoutTop = new HorizontalLayout();		
		HorizontalLayout layoutBottom = new HorizontalLayout();		
		//INIT COMPONENT ATAS
		btnSeparator1.setEnabled(false);
		btnSeparator2.setEnabled(false);
		btnSeparator3.setEnabled(false);

//		layoutTop.addComponent(fieldNomorUrut);
		panelComboArea.setContent(comboArea);
		layoutTop.addComponent(panelComboArea);
		
//		panelCheckFreegood.setContent(checkFreegood);
//		layoutTop.addComponent(panelCheckFreegood);
//		panelFieldSprice.setContent(fieldSprice);
//		layoutTop.addComponent(panelFieldSprice);
		
//		layoutTop.addComponent(fieldSpriceafterppn);
		panelKode.setContent(fieldKode);
		layoutTop.addComponent(panelKode);
		panelNama.setContent(fieldNama);
		layoutTop.addComponent(panelNama);
//		panelAlamat.setContent(fieldAlamat);
//		layoutTop.addComponent(panelAlamat);
//		panelKota.setContent(fieldKota);
//		layoutTop.addComponent(panelKota);
		
		layoutBottom.addComponent(btnAddAndSave);
		layoutBottom.addComponent(btnReset);
		layoutBottom.addComponent(btnClose);
		
//		layoutTop.setComponentAlignment(btnAdd, Alignment.BOTTOM_CENTER);
//		layoutTop.setComponentAlignment(btnReset, Alignment.BOTTOM_CENTER);
//		layoutTop.setComponentAlignment(btnClose, Alignment.BOTTOM_CENTER);
		
		
		//MASUKKAN KE ROOT

		content.addComponent(layoutTop);		
		content.addComponent(layoutBottom);		
		panelUtama.setContent(content);
		panelUtama.setSizeFull();
		setCompositionRoot(panelUtama);	
		
		//finishing PERMAK 
		content.setComponentAlignment(layoutBottom, Alignment.BOTTOM_RIGHT);
		
		
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
		comboArea.setContainerDataSource(model.getBeanItemContainerArea());
		comboArea.setNewItemsAllowed(false);
		comboArea.setFilteringMode(FilteringMode.CONTAINS);
		comboArea.setNullSelectionAllowed(false);
		
//		model.getBinderItemDetail().bind(fieldNomorUrut, "nourut");
//		model.getBinderItemDetail().bind(comboProduct, "fproductBean");
		model.getBinderItemDetail().bind(fieldKode, "id");
		model.getBinderItemDetail().bind(fieldNama, "description");
		
		
	}
	public void setFormButtonAndTextState(){
		
		if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
			btnAddAndSave.setCaption("Add");
		} else if(model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
			btnAddAndSave.setCaption("Update & Close");
		}
	}
	
	public void focustIdOrDesc(){
		comboArea.focus();
	}
	public AreaCoveredItemModel getModel() {
		return model;
	}
	public VerticalLayout getContent() {
		return content;
	}
	public TextField getFieldNomorUrut() {
		return fieldNomorUrut;
	}
	public ComboBox getComboArea() {
		return comboArea;
	}
	public TextField getFieldKode() {
		return fieldKode;
	}
	public TextField getFieldNama() {
		return fieldNama;
	}
	public TextField getFieldAlamat() {
		return fieldAlamat;
	}
	public TextField getFieldKota() {
		return fieldKota;
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
	public Panel getPanelComboArea() {
		return panelComboArea;
	}
	public Panel getPanelKode() {
		return panelKode;
	}
	public Panel getPanelNama() {
		return panelNama;
	}
	public Panel getPanelAlamat() {
		return panelAlamat;
	}
	public Panel getPanelKota() {
		return panelKota;
	}
	public void setModel(AreaCoveredItemModel model) {
		this.model = model;
	}
	public void setContent(VerticalLayout content) {
		this.content = content;
	}
	public void setFieldNomorUrut(TextField fieldNomorUrut) {
		this.fieldNomorUrut = fieldNomorUrut;
	}
	public void setComboArea(ComboBox comboArea) {
		this.comboArea = comboArea;
	}
	public void setFieldKode(TextField fieldKode) {
		this.fieldKode = fieldKode;
	}
	public void setFieldNama(TextField fieldNama) {
		this.fieldNama = fieldNama;
	}
	public void setFieldAlamat(TextField fieldAlamat) {
		this.fieldAlamat = fieldAlamat;
	}
	public void setFieldKota(TextField fieldKota) {
		this.fieldKota = fieldKota;
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
	public void setPanelComboArea(Panel panelComboArea) {
		this.panelComboArea = panelComboArea;
	}
	public void setPanelKode(Panel panelKode) {
		this.panelKode = panelKode;
	}
	public void setPanelNama(Panel panelNama) {
		this.panelNama = panelNama;
	}
	public void setPanelAlamat(Panel panelAlamat) {
		this.panelAlamat = panelAlamat;
	}
	public void setPanelKota(Panel panelKota) {
		this.panelKota = panelKota;
	}
	
	
	
	
}
