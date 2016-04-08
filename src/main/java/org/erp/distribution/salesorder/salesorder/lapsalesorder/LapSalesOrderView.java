package org.erp.distribution.salesorder.salesorder.lapsalesorder;

import groovy.swing.factory.LayoutFactory;

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

public class LapSalesOrderView extends CustomComponent{
	private LapSalesOrderModel model;

	private VerticalLayout content = new VerticalLayout();

	private TextField textField1= new TextField("KD. BRG");
	private TextField textField2= new TextField("NAMA BRG");
	private TextField textField3= new TextField("");
	
	private ComboBox comboGroup0= new ComboBox("SALESMAN");
	private ComboBox comboGroup1= new ComboBox("VENDOR/SUPPLIER");
	private ComboBox comboGroup2= new ComboBox("CUSTOMER/OUTLET");
	private ComboBox comboGroup3= new ComboBox("AREA");
	private ComboBox comboGroup4= new ComboBox("SUB AREA");
	private ComboBox comboGroup5= new ComboBox("PRODUCT GROUP");

	private DateField dateField1From = new DateField("TGL FAKTUR MULAI");
	private DateField dateField1To = new DateField("S.D");
	
	private CheckBox checkBox1= new CheckBox("DIPOTONG RETUR");
	private CheckBox checkBox2= new CheckBox("Format Lengkap");

	private CheckBox checkBoxFaktur= new CheckBox("Faktur", true);
	private CheckBox checkBoxRetur= new CheckBox("Retur", false);

	private CheckBox checkBoxOutput1= new CheckBox("LAP. SALES CUSTOMER PER NOTA - PER AREA");
	private CheckBox checkBoxOutput2= new CheckBox("LAP. SALES CUSTOMER PER NOTA - PER TIPE OUTLET");
	
	private CheckBox checkBoxOutput3= new CheckBox("LAP. SALES PER BARANG PER NOTA - PER SUPPLIER");
	private CheckBox checkBoxOutput4= new CheckBox("LAP. SALES PER BARANG PER NOTA - PER AREA");
	private CheckBox checkBoxOutput5= new CheckBox("LAP. SALES PER BARANG PER NOTA - PER TIPE OUTLET");
	private CheckBox checkBoxOutput6= new CheckBox("LAP. SALES PER BARANG PER NOTA - PER GRUP BARANG");
	private CheckBox checkBoxOutput7= new CheckBox("LAP.");
	
	private CheckBox checkBoxOutput10= new CheckBox("LAP. PENJUALAN SUPPLIER");
	private CheckBox checkBoxOutput11= new CheckBox("LAP. PENJUALAN CUSTOMER");
	private CheckBox checkBoxOutput12= new CheckBox("LAP. PENJUALAN BARANG");
	private CheckBox checkBoxOutput13= new CheckBox("LAP. PENJUALAN SALESMAN");
	
	private CheckBox checkBoxOutputPenjTotFormatTirta = new CheckBox("Lap. Penjualan Tototal Barang Format Tirta Raharja");
	

	private Button btnPreviewInExel = new Button("Preview In Exel");
	private Button btnPreview = new Button("Print");
	private Button btnClose = new Button("Close");
	
	private Panel panelUtama = new Panel();
	private Panel panelFilter = new Panel();
	private Panel panelOutput = new Panel();
	private Panel panelBottom = new Panel();
	
	public LapSalesOrderView(LapSalesOrderModel model){
		this.model = model;
		initComponent();
		buildView();
		
		setDisplay();	
		
	}

	public void initComponent(){
		comboGroup0.setWidth("300px");
		comboGroup1.setWidth("300px");
		comboGroup2.setWidth("300px");
		comboGroup3.setWidth("300px");
		comboGroup4.setWidth("300px");
		comboGroup5.setWidth("300px");

		textField1.setWidth("200px");
		textField2.setWidth("300px");
		
		dateField1From.setDateFormat("dd/MM/yyyy");
		dateField1To.setDateFormat("dd/MM/yyyy");
		
		panelFilter.setCaption("Filter Criteria");
		panelOutput.setCaption("Jenis Laporan");
		
	}

	public void buildView(){
		//Inisialisasi Panel 
		setSizeFull();
//		content.setSizeFull();
		content.setMargin(true);
		panelFilter.setSizeFull();
		panelOutput.setSizeFull();

		HorizontalLayout layoutTop = new HorizontalLayout();		
			VerticalLayout layoutFilter = new VerticalLayout();
			layoutFilter.setMargin(true);
			VerticalLayout layoutOutput = new VerticalLayout();
			layoutOutput.setMargin(true);
//		layoutTop.setMargin(true);
		layoutTop.setSizeFull();
		
		HorizontalLayout layoutBottom = new HorizontalLayout();		
//		layoutBottom.setMargin(true);
//		layoutBottom.setSizeFull();
		
		layoutFilter.addComponent(comboGroup0);
		layoutFilter.addComponent(comboGroup1);
		layoutFilter.addComponent(comboGroup2);
		layoutFilter.addComponent(comboGroup3);
		layoutFilter.addComponent(comboGroup4);
		layoutFilter.addComponent(textField1);
		layoutFilter.addComponent(textField2);
		layoutFilter.addComponent(comboGroup5);
				
		
		HorizontalLayout layoutFakturRetur = new HorizontalLayout();
		layoutFakturRetur.addComponent(checkBoxFaktur);
		layoutFakturRetur.addComponent(checkBoxRetur);
		
		layoutFilter.addComponent(dateField1From);
		layoutFilter.addComponent(dateField1To);
		
//		layoutFilter.addComponent(checkBox1);
		layoutFilter.addComponent(layoutFakturRetur);
		layoutFilter.addComponent(checkBox2);
		
		Panel panelOutput1 = new Panel("Laporan Penjualan Customer Per Nota");
			VerticalLayout layoutOutput1 = new VerticalLayout();
			layoutOutput1.setMargin(true);
		panelOutput1.setContent(layoutOutput1);
	
		Panel panelOutput2 = new Panel("Laporan Penjualan Barang Per Nota");
			VerticalLayout layoutOutput2 = new VerticalLayout();
			layoutOutput2.setMargin(true);
		panelOutput2.setContent(layoutOutput2);

		Panel panelOutput3 = new Panel("Laporan Penjualan Total");
		VerticalLayout layoutOutput3 = new VerticalLayout();
		layoutOutput3.setMargin(true);
		panelOutput3.setContent(layoutOutput3);
		
		layoutOutput1.addComponent(checkBoxOutput1);
		layoutOutput1.addComponent(checkBoxOutput2);

		layoutOutput2.addComponent(checkBoxOutput3);
		layoutOutput2.addComponent(checkBoxOutput4);
		layoutOutput2.addComponent(checkBoxOutput5);
		layoutOutput2.addComponent(checkBoxOutput6);
		
		layoutOutput3.addComponent(checkBoxOutput10);
		layoutOutput3.addComponent(checkBoxOutput11);
		layoutOutput3.addComponent(checkBoxOutput12);
		layoutOutput3.addComponent(checkBoxOutput13);

		layoutOutput3.addComponent(checkBoxOutputPenjTotFormatTirta);
		
		layoutOutput.addComponent(panelOutput1);
		layoutOutput.addComponent(panelOutput2);
		layoutOutput.addComponent(panelOutput3);
		
		layoutBottom.addComponent(btnPreview);
		layoutBottom.addComponent(btnPreviewInExel);
		layoutBottom.addComponent(btnClose);
		
		panelFilter.setContent(layoutFilter);
		panelOutput.setContent(layoutOutput);
		layoutTop.addComponent(panelFilter);
		layoutTop.addComponent(panelOutput);
		
		content.addComponent(layoutTop);		
		content.addComponent(layoutBottom);
		
		setCompositionRoot(content);
		
		btnPreview.setIcon(FontAwesome.PRINT);
		
	}
	
	public void setDisplay(){
		comboGroup0.setContainerDataSource(model.getBeanItemContainerFSalesman());
		comboGroup0.setNewItemsAllowed(false);
		comboGroup0.setFilteringMode(FilteringMode.CONTAINS);
		comboGroup0.setNullSelectionAllowed(true);
		
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

	public LapSalesOrderModel getModel() {
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
		return panelFilter;
	}

	public Panel getPanelBottom() {
		return panelBottom;
	}

	public void setModel(LapSalesOrderModel model) {
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
		this.panelFilter = panelTop;
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

	public CheckBox getCheckBoxFaktur() {
		return checkBoxFaktur;
	}

	public CheckBox getCheckBoxRetur() {
		return checkBoxRetur;
	}

	public CheckBox getCheckBoxOutput1() {
		return checkBoxOutput1;
	}

	public CheckBox getCheckBoxOutput2() {
		return checkBoxOutput2;
	}

	public CheckBox getCheckBoxOutput3() {
		return checkBoxOutput3;
	}

	public CheckBox getCheckBoxOutput4() {
		return checkBoxOutput4;
	}

	public CheckBox getCheckBoxOutput5() {
		return checkBoxOutput5;
	}

	public CheckBox getCheckBoxOutput6() {
		return checkBoxOutput6;
	}

	public CheckBox getCheckBoxOutput7() {
		return checkBoxOutput7;
	}

	public Panel getPanelFilter() {
		return panelFilter;
	}

	public Panel getPanelOutput() {
		return panelOutput;
	}

	public void setCheckBoxFaktur(CheckBox checkBoxFaktur) {
		this.checkBoxFaktur = checkBoxFaktur;
	}

	public void setCheckBoxRetur(CheckBox checkBoxRetur) {
		this.checkBoxRetur = checkBoxRetur;
	}

	public void setCheckBoxOutput1(CheckBox checkBoxOutput1) {
		this.checkBoxOutput1 = checkBoxOutput1;
	}

	public void setCheckBoxOutput2(CheckBox checkBoxOutput2) {
		this.checkBoxOutput2 = checkBoxOutput2;
	}

	public void setCheckBoxOutput3(CheckBox checkBoxOutput3) {
		this.checkBoxOutput3 = checkBoxOutput3;
	}

	public void setCheckBoxOutput4(CheckBox checkBoxOutput4) {
		this.checkBoxOutput4 = checkBoxOutput4;
	}

	public void setCheckBoxOutput5(CheckBox checkBoxOutput5) {
		this.checkBoxOutput5 = checkBoxOutput5;
	}

	public void setCheckBoxOutput6(CheckBox checkBoxOutput6) {
		this.checkBoxOutput6 = checkBoxOutput6;
	}

	public void setCheckBoxOutput7(CheckBox checkBoxOutput7) {
		this.checkBoxOutput7 = checkBoxOutput7;
	}

	public void setPanelFilter(Panel panelFilter) {
		this.panelFilter = panelFilter;
	}

	public void setPanelOutput(Panel panelOutput) {
		this.panelOutput = panelOutput;
	}

	public ComboBox getComboGroup0() {
		return comboGroup0;
	}

	public CheckBox getCheckBoxOutput10() {
		return checkBoxOutput10;
	}

	public CheckBox getCheckBoxOutput11() {
		return checkBoxOutput11;
	}

	public CheckBox getCheckBoxOutput12() {
		return checkBoxOutput12;
	}

	public CheckBox getCheckBoxOutput13() {
		return checkBoxOutput13;
	}

	public void setComboGroup0(ComboBox comboGroup0) {
		this.comboGroup0 = comboGroup0;
	}

	public void setCheckBoxOutput10(CheckBox checkBoxOutput10) {
		this.checkBoxOutput10 = checkBoxOutput10;
	}

	public void setCheckBoxOutput11(CheckBox checkBoxOutput11) {
		this.checkBoxOutput11 = checkBoxOutput11;
	}

	public void setCheckBoxOutput12(CheckBox checkBoxOutput12) {
		this.checkBoxOutput12 = checkBoxOutput12;
	}

	public void setCheckBoxOutput13(CheckBox checkBoxOutput13) {
		this.checkBoxOutput13 = checkBoxOutput13;
	}

	public Button getBtnPreviewInExel() {
		return btnPreviewInExel;
	}

	public void setBtnPreviewInExel(Button btnPreviewInExel) {
		this.btnPreviewInExel = btnPreviewInExel;
	}

	public CheckBox getCheckBoxOutputPenjTotFormatTirta() {
		return checkBoxOutputPenjTotFormatTirta;
	}

	public void setCheckBoxOutputPenjTotFormatTirta(
			CheckBox checkBoxOutputPenjTotFormatTirta) {
		this.checkBoxOutputPenjTotFormatTirta = checkBoxOutputPenjTotFormatTirta;
	}


	
	
}
