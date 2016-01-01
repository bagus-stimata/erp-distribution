package org.erp.distribution.master.systemsetting.parameterdiskon;

import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;

public class ParameterDiskonView extends CustomComponent{
	private ParameterDiskonModel model;

	private VerticalLayout content = new VerticalLayout();
	
	private CheckBox checkBox1 = new CheckBox("SEMUA JENIS CUSTOMER");
	private ComboBox comboGrup1 = new ComboBox("JENIS CUSTOMER");
	private ComboBox comboGrup2 = new ComboBox("TUNAI/KREDIT");

	private TextField field1 = new TextField("Pembelian >");
	private TextField field11 = new TextField("DISC1");
	private TextField field11plus = new TextField("+DISC2");
	
	private TextField field2 = new TextField("Pembelian >");
	private TextField field22 = new TextField("DISC1");
	private TextField field22plus = new TextField("+DISC2");

	private TextField field3 = new TextField("Pembelian >");
	private TextField field33 = new TextField("DISC1");
	private TextField field33plus = new TextField("");

	private TextField field4 = new TextField("Pembelian >");
	private TextField field44 = new TextField("DISC1");
	private TextField field44plus = new TextField("+DISC2");

	private TextField field5 = new TextField("Pembelian >");
	private TextField field55 = new TextField("DISC1");
	private TextField field55plus = new TextField("+DISC2");

	private Button btnSave = new Button("Save");
	private Button btnClose = new Button("Close");
	
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelMidle = new Panel();
	private Panel panelBottom = new Panel();
	
	
	public ParameterDiskonView(ParameterDiskonModel model){
		this.model = model;
		initComponent();
		buildView();
		
		setDisplay();	
		bindAndBuildFieldGroupComponent();
		
	}

	public void initComponent(){
		
	}

	public void buildView(){
		//Inisialisasi Panel 
		setSizeFull();
//		content.setSizeFull();
		content.setMargin(true);
//		panelTop.setSizeFull();
//		panelMidle.setSizeFull();
//		panelBottom.setSizeFull();
		

		VerticalLayout layoutTop = new VerticalLayout();		
		layoutTop.setMargin(true);
		VerticalLayout layoutMidle = new VerticalLayout();		
		layoutMidle.setMargin(true);
		HorizontalLayout layoutBottom = new HorizontalLayout();		
		layoutBottom.setMargin(true);

		VerticalLayout layoutTop1 = new VerticalLayout();
		layoutTop1.setMargin(true);

		layoutTop.addComponent(layoutTop1);
		
		HorizontalLayout layoutMidle1 = new HorizontalLayout();
//		layoutMidle1.setMargin(true);
		HorizontalLayout layoutMidle2 = new HorizontalLayout();
//		layoutMidle2.setMargin(true);
		HorizontalLayout layoutMidle3 = new HorizontalLayout();
//		layoutMidle3.setMargin(true);
		HorizontalLayout layoutMidle4 = new HorizontalLayout();
//		layoutMidle4.setMargin(true);
		HorizontalLayout layoutMidle5 = new HorizontalLayout();
//		layoutMidle5.setMargin(true);
		
		layoutMidle.addComponent(layoutMidle1);
		layoutMidle.addComponent(layoutMidle2);
		layoutMidle.addComponent(layoutMidle3);
		layoutMidle.addComponent(layoutMidle4);
		layoutMidle.addComponent(layoutMidle5);

		
		layoutTop1.addComponent(checkBox1);
		layoutTop1.addComponent(comboGrup1);
		layoutTop1.addComponent(comboGrup2);

		layoutMidle1.addComponent(field1);
		layoutMidle1.addComponent(field11);
		layoutMidle1.addComponent(field11plus);
		
		layoutMidle2.addComponent(field2);
		layoutMidle2.addComponent(field22);
		layoutMidle2.addComponent(field22plus);

		layoutMidle3.addComponent(field3);
		layoutMidle3.addComponent(field33);
		layoutMidle3.addComponent(field33plus);

		layoutMidle4.addComponent(field4);
		layoutMidle4.addComponent(field44);
		layoutMidle4.addComponent(field44plus);
		
		layoutMidle5.addComponent(field5);
		layoutMidle5.addComponent(field55);
		layoutMidle5.addComponent(field55plus);

		layoutBottom.addComponent(btnSave);
		layoutBottom.addComponent(btnClose);
		
		
		content.addComponent(layoutTop);		
		content.addComponent(layoutMidle);		
		content.addComponent(layoutBottom);
		
		setCompositionRoot(content);
		
	}
	
	public void setDisplay(){
		
		
	}
	
	public void bindAndBuildFieldGroupComponent(){
		model.getBinderFParamDiskonNota().setItemDataSource(model.itemHeader);
		
		comboGrup1.setContainerDataSource(model.getBeanItemContainerCustomersubgroup());
		comboGrup1.setNewItemsAllowed(false);
		comboGrup1.setFilteringMode(FilteringMode.CONTAINS);
		comboGrup1.setNullSelectionAllowed(true);

		comboGrup2.setNewItemsAllowed(false);
		comboGrup2.setFilteringMode(FilteringMode.CONTAINS);
		comboGrup2.setNullSelectionAllowed(false);
		
		comboGrup2.addItem("A");
		comboGrup2.setItemCaption("A", "Semua");
		comboGrup2.addItem("T");
		comboGrup2.setItemCaption("T", "Tunai");
		comboGrup2.addItem("K");
		comboGrup2.setItemCaption("K", "Kredit");
		
		model.getBinderFParamDiskonNota().bind(field1, "nominal1");
		model.getBinderFParamDiskonNota().bind(field11, "diskon1");
		model.getBinderFParamDiskonNota().bind(field11plus, "diskon1plus");

		model.getBinderFParamDiskonNota().bind(field2, "nominal2");
		model.getBinderFParamDiskonNota().bind(field22, "diskon2");
		model.getBinderFParamDiskonNota().bind(field22plus, "diskon2plus");

		model.getBinderFParamDiskonNota().bind(field3, "nominal3");
		model.getBinderFParamDiskonNota().bind(field33, "diskon3");
		model.getBinderFParamDiskonNota().bind(field33plus, "diskon3plus");

		model.getBinderFParamDiskonNota().bind(field4, "nominal4");
		model.getBinderFParamDiskonNota().bind(field44, "diskon4");
		model.getBinderFParamDiskonNota().bind(field44plus, "diskon4plus");

		model.getBinderFParamDiskonNota().bind(field5, "nominal5");
		model.getBinderFParamDiskonNota().bind(field55, "diskon5");
		model.getBinderFParamDiskonNota().bind(field55plus, "diskon5plus");
		

		model.getBinderFParamDiskonNota().bind(checkBox1, "allsubgrup");
		model.getBinderFParamDiskonNota().bind(comboGrup1, "fcustomersubgroupBean");
		model.getBinderFParamDiskonNota().bind(comboGrup2, "tunaikredit");
		
	}
	

	public ParameterDiskonModel getModel() {
		return model;
	}

	public VerticalLayout getContent() {
		return content;
	}

	public ComboBox getComboGrup1() {
		return comboGrup1;
	}

	public ComboBox getComboGrup2() {
		return comboGrup2;
	}

	public TextField getField1() {
		return field1;
	}

	public TextField getField11() {
		return field11;
	}

	public TextField getField11plus() {
		return field11plus;
	}

	public TextField getField2() {
		return field2;
	}

	public TextField getField22() {
		return field22;
	}

	public TextField getField22plus() {
		return field22plus;
	}

	public TextField getField3() {
		return field3;
	}

	public TextField getField33() {
		return field33;
	}

	public TextField getField33plus() {
		return field33plus;
	}

	public TextField getField4() {
		return field4;
	}

	public TextField getField44() {
		return field44;
	}

	public TextField getField44plus() {
		return field44plus;
	}

	public TextField getField5() {
		return field5;
	}

	public TextField getField55() {
		return field55;
	}

	public TextField getField55plus() {
		return field55plus;
	}

	public Button getBtnSave() {
		return btnSave;
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

	public void setModel(ParameterDiskonModel model) {
		this.model = model;
	}

	public void setContent(VerticalLayout content) {
		this.content = content;
	}

	public void setComboGrup1(ComboBox comboGrup1) {
		this.comboGrup1 = comboGrup1;
	}

	public void setComboGrup2(ComboBox comboGrup2) {
		this.comboGrup2 = comboGrup2;
	}

	public void setField1(TextField field1) {
		this.field1 = field1;
	}

	public void setField11(TextField field11) {
		this.field11 = field11;
	}

	public void setField11plus(TextField field11plus) {
		this.field11plus = field11plus;
	}

	public void setField2(TextField field2) {
		this.field2 = field2;
	}

	public void setField22(TextField field22) {
		this.field22 = field22;
	}

	public void setField22plus(TextField field22plus) {
		this.field22plus = field22plus;
	}

	public void setField3(TextField field3) {
		this.field3 = field3;
	}

	public void setField33(TextField field33) {
		this.field33 = field33;
	}

	public void setField33plus(TextField field33plus) {
		this.field33plus = field33plus;
	}

	public void setField4(TextField field4) {
		this.field4 = field4;
	}

	public void setField44(TextField field44) {
		this.field44 = field44;
	}

	public void setField44plus(TextField field44plus) {
		this.field44plus = field44plus;
	}

	public void setField5(TextField field5) {
		this.field5 = field5;
	}

	public void setField55(TextField field55) {
		this.field55 = field55;
	}

	public void setField55plus(TextField field55plus) {
		this.field55plus = field55plus;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
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

	
	
	
	
	
}
