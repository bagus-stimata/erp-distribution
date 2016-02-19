package org.erp.distribution.salesorder.salesorder.lapsalesvendorperbarang;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LapSalesVendorPerBarangView extends CustomComponent{
	private LapSalesVendorPerBarangModel model;

	private VerticalLayout content = new VerticalLayout();

	private TextField textField1= new TextField("KD. BRG");
	private TextField textField2= new TextField("NAMA BRG");
	private TextField textField3= new TextField("");
	
	private ComboBox comboGroup1= new ComboBox("VENDOR");
	private ComboBox comboGroup2= new ComboBox("CUSTOMER/OUTLET");
	private ComboBox comboGroup3= new ComboBox("AREA");
	private ComboBox comboGroup4= new ComboBox("SUB AREA");
	private ComboBox comboGroup5= new ComboBox("PRODUCT GROUP");

	private DateField dateField1From = new DateField("TGL FAKTUR MULAI");
	private DateField dateField1To = new DateField("S.D");
	
	private CheckBox checkBox1= new CheckBox("DIPOTONG RETUR");
	private CheckBox checkBox2= new CheckBox("Format Lengkap");
	
	private Button btnPreview = new Button("Print");
	private Button btnClose = new Button("Close");
	
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelBottom = new Panel();
	
	
	public LapSalesVendorPerBarangView(LapSalesVendorPerBarangModel model){
		this.model = model;
		initComponent();
		buildView();
		
		setDisplay();	
		
	}

	public void initComponent(){
		comboGroup1.setWidth("300px");
		comboGroup2.setWidth("300px");
		comboGroup3.setWidth("300px");
		comboGroup4.setWidth("300px");
		comboGroup5.setWidth("300px");

		textField1.setWidth("300px");
		textField2.setWidth("500px");
		
		dateField1From.setDateFormat("dd/MM/yyyy");
		dateField1To.setDateFormat("dd/MM/yyyy");
		
	}

	public void buildView(){
		//Inisialisasi Panel 
		setSizeFull();
//		content.setSizeFull();
		content.setMargin(true);
//		panelTop.setSizeFull();
//		panelBottom.setSizeFull();

		VerticalLayout layoutTop = new VerticalLayout();		
		layoutTop.setMargin(true);
		HorizontalLayout layoutBottom = new HorizontalLayout();		
		layoutBottom.setMargin(true);
		
		layoutTop.addComponent(comboGroup1);
		layoutTop.addComponent(comboGroup2);
		layoutTop.addComponent(comboGroup3);
		layoutTop.addComponent(comboGroup4);
		layoutTop.addComponent(textField1);
		layoutTop.addComponent(textField2);
		layoutTop.addComponent(comboGroup5);
				
		
		layoutTop.addComponent(dateField1From);
		layoutTop.addComponent(dateField1To);

		layoutTop.addComponent(checkBox1);
		layoutTop.addComponent(checkBox2);
		
		layoutBottom.addComponent(btnPreview);
		layoutBottom.addComponent(btnClose);
		
		
		panelTop.setContent(layoutTop);
		content.addComponent(panelTop);		
		content.addComponent(layoutBottom);
		
		setCompositionRoot(content);
		
		btnPreview.setIcon(FontAwesome.PRINT);
		
	}
	
	public void setDisplay(){
		comboGroup1.setContainerDataSource(model.getBeanItemContainerFVendor());
		comboGroup1.setNewItemsAllowed(false);
		comboGroup1.setFilteringMode(FilteringMode.CONTAINS);
		comboGroup1.setNullSelectionAllowed(true);

		comboGroup2.setContainerDataSource(model.getBeanItemContainerFCustomer());
		comboGroup2.setNewItemsAllowed(false);
		comboGroup2.setFilteringMode(FilteringMode.CONTAINS);
		comboGroup2.setNullSelectionAllowed(true);
		
		comboGroup3.setContainerDataSource(model.getBeanItemContainerFArea());
		comboGroup3.setNewItemsAllowed(false);
		comboGroup3.setFilteringMode(FilteringMode.CONTAINS);
		comboGroup3.setNullSelectionAllowed(true);
		
		comboGroup4.setContainerDataSource(model.getBeanItemContainerFSubArea());
		comboGroup4.setNewItemsAllowed(false);
		comboGroup4.setFilteringMode(FilteringMode.CONTAINS);
		comboGroup4.setNullSelectionAllowed(true);
		
		comboGroup5.setContainerDataSource(model.getBeanItemContainerFProductGroup());
		comboGroup5.setNewItemsAllowed(false);
		comboGroup5.setFilteringMode(FilteringMode.CONTAINS);
		comboGroup5.setNullSelectionAllowed(true);
		
		dateField1From.setValue(model.getTransaksiHelper().getCurrentTransDate());
		dateField1To.setValue(model.getTransaksiHelper().getCurrentTransDate());
		
	}

	public LapSalesVendorPerBarangModel getModel() {
		return model;
	}

	public VerticalLayout getContent() {
		return content;
	}

	public ComboBox getComboGroup1() {
		return comboGroup1;
	}

	public ComboBox getComboGroup2() {
		return comboGroup2;
	}

	public DateField getDateField1From() {
		return dateField1From;
	}

	public DateField getDateField1To() {
		return dateField1To;
	}

	public Button getBtnPreview() {
		return btnPreview;
	}

	public Button getBtnClose() {
		return btnClose;
	}

	public Panel getPanelUtama() {
		return panelUtama;
	}

	public Panel getPanelTop() {
		return panelTop;
	}

	public Panel getPanelBottom() {
		return panelBottom;
	}

	public void setModel(LapSalesVendorPerBarangModel model) {
		this.model = model;
	}

	public void setContent(VerticalLayout content) {
		this.content = content;
	}

	public void setComboGroup1(ComboBox comboGroup1) {
		this.comboGroup1 = comboGroup1;
	}

	public void setComboGroup2(ComboBox comboGroup2) {
		this.comboGroup2 = comboGroup2;
	}

	public void setDateField1From(DateField dateField1From) {
		this.dateField1From = dateField1From;
	}

	public void setDateField1To(DateField dateField1To) {
		this.dateField1To = dateField1To;
	}

	public void setBtnPreview(Button btnPreview) {
		this.btnPreview = btnPreview;
	}

	public void setBtnClose(Button btnClose) {
		this.btnClose = btnClose;
	}

	public void setPanelUtama(Panel panelUtama) {
		this.panelUtama = panelUtama;
	}

	public void setPanelTop(Panel panelTop) {
		this.panelTop = panelTop;
	}

	public void setPanelBottom(Panel panelBottom) {
		this.panelBottom = panelBottom;
	}

	public CheckBox getCheckBox1() {
		return checkBox1;
	}

	public CheckBox getCheckBox2() {
		return checkBox2;
	}

	public void setCheckBox1(CheckBox checkBox1) {
		this.checkBox1 = checkBox1;
	}

	public void setCheckBox2(CheckBox checkBox2) {
		this.checkBox2 = checkBox2;
	}

	public ComboBox getComboGroup3() {
		return comboGroup3;
	}

	public ComboBox getComboGroup4() {
		return comboGroup4;
	}

	public void setComboGroup3(ComboBox comboGroup3) {
		this.comboGroup3 = comboGroup3;
	}

	public void setComboGroup4(ComboBox comboGroup4) {
		this.comboGroup4 = comboGroup4;
	}

	public TextField getTextField1() {
		return textField1;
	}

	public TextField getTextField2() {
		return textField2;
	}

	public TextField getTextField3() {
		return textField3;
	}

	public ComboBox getComboGroup5() {
		return comboGroup5;
	}

	public void setTextField1(TextField textField1) {
		this.textField1 = textField1;
	}

	public void setTextField2(TextField textField2) {
		this.textField2 = textField2;
	}

	public void setTextField3(TextField textField3) {
		this.textField3 = textField3;
	}

	public void setComboGroup5(ComboBox comboGroup5) {
		this.comboGroup5 = comboGroup5;
	}
	
	
	
	
	
	
	
}
