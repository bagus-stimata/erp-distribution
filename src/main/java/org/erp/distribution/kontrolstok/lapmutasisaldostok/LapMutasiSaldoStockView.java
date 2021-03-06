package org.erp.distribution.kontrolstok.lapmutasisaldostok;

import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class LapMutasiSaldoStockView extends CustomComponent{
	private LapMutasiSaldoStockModel model;

	private VerticalLayout content = new VerticalLayout();
	private ComboBox comboGroup1= new ComboBox("WAREHOUSE/GUDANG");
	private ComboBox comboGroup2= new ComboBox("GRUP BARANG");
	private ComboBox comboGroup3= new ComboBox("DIVISI PRODUK");
	private ComboBox comboGroup4= new ComboBox("SUPPLIER");

	private CheckBox checkBox1 = new CheckBox("HANYA YANG ADA MUTASI");
	private CheckBox checkBox5= new CheckBox("HANYA YANG ADA STOK");
	private CheckBox checkBox2 = new CheckBox("Format Lengkap");
	private CheckBox checkBox3 = new CheckBox("Barang Aktif Saja");
	private CheckBox checkBox4 = new CheckBox("Validasi/Rekalkulasi Saldo Stok");

	private DateField dateField1From = new DateField("TANGGAL");
	private DateField dateField1To = new DateField("S/D");
	
	private Button btnExtractToExel = new Button("Extract To Exel");
	private Button btnPreview = new Button("Preview");
	private Button btnClose = new Button("Close");
	
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelBottom = new Panel();
	
	
	public LapMutasiSaldoStockView(LapMutasiSaldoStockModel model){
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

		dateField1From.setDateFormat("dd/MM/yyyy");
		dateField1To.setDateFormat("dd/MM/yyyy");

		checkBox3.setValue(true);
		checkBox4.setValue(true);
		
	}

	public void buildView(){
		//Inisialisasi Panel 
		setSizeFull();
		content.setMargin(true);

		VerticalLayout layoutTop = new VerticalLayout();		
		layoutTop.setMargin(true);
		HorizontalLayout layoutBottom = new HorizontalLayout();		
		layoutBottom.setMargin(true);
		
		layoutTop.addComponent(comboGroup1);
		layoutTop.addComponent(comboGroup2);
		layoutTop.addComponent(comboGroup3);
		layoutTop.addComponent(comboGroup4);
//		layoutTop.addComponent(dateField1From);
		layoutTop.addComponent(dateField1To);

//		layoutTop.addComponent(checkBox1);
		layoutTop.addComponent(checkBox5);
//		layoutTop.addComponent(checkBox2);
		layoutTop.addComponent(checkBox3);
		layoutTop.addComponent(checkBox4);
		
		layoutBottom.addComponent(btnPreview);
		layoutBottom.addComponent(btnExtractToExel);
		layoutBottom.addComponent(btnClose);
		

		
//		panelTop.setContent(layoutTop);
		content.addComponent(layoutTop);		
		content.addComponent(layoutBottom);
		
		setCompositionRoot(content);
		
	}
	
	public void setDisplay(){
		comboGroup1.setContainerDataSource(model.getBeanItemContainerWarehouse());
		comboGroup1.setNewItemsAllowed(false);
		comboGroup1.setFilteringMode(FilteringMode.CONTAINS);
		comboGroup1.setNullSelectionAllowed(true);

		comboGroup2.setContainerDataSource(model.getBeanItemContainerProductgroup());
		comboGroup2.setNewItemsAllowed(false);
		comboGroup2.setFilteringMode(FilteringMode.CONTAINS);
		comboGroup2.setNullSelectionAllowed(true);
		
		comboGroup3.setContainerDataSource(model.getBeanItemContainerProductDivisi());
		comboGroup3.setNewItemsAllowed(false);
		comboGroup3.setFilteringMode(FilteringMode.CONTAINS);
		comboGroup3.setNullSelectionAllowed(true);
		
		comboGroup4.setContainerDataSource(model.getBeanItemContainerVendor());
		comboGroup4.setNewItemsAllowed(false);
		comboGroup4.setFilteringMode(FilteringMode.CONTAINS);
		comboGroup4.setNullSelectionAllowed(true);
		
		
		
		dateField1From.setValue(model.getTransaksiHelper().getCurrentTransDate());
		dateField1To.setValue(model.getTransaksiHelper().getCurrentTransDate());
		
		checkBox2.setValue(true);
		checkBox3.setValue(true);
		checkBox4.setValue(true);
		
		
	}

	public LapMutasiSaldoStockModel getModel() {
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

	public void setModel(LapMutasiSaldoStockModel model) {
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

	public CheckBox getCheckBox3() {
		return checkBox3;
	}

	public void setCheckBox3(CheckBox checkBox3) {
		this.checkBox3 = checkBox3;
	}

	public Button getBtnExtractToExel() {
		return btnExtractToExel;
	}

	public void setBtnExtractToExel(Button btnExtractToExel) {
		this.btnExtractToExel = btnExtractToExel;
	}

	public CheckBox getCheckBox4() {
		return checkBox4;
	}

	public void setCheckBox4(CheckBox checkBox4) {
		this.checkBox4 = checkBox4;
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

	public CheckBox getCheckBox5() {
		return checkBox5;
	}

	public void setCheckBox5(CheckBox checkBox5) {
		this.checkBox5 = checkBox5;
	}
	
	
}
