package org.erp.distribution.utility.recalculatesaldostock;

import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class RecalculateSaldoStockView extends CustomComponent{
	private RecalculateSaldoStockModel model;

	private VerticalLayout content = new VerticalLayout();
	private ComboBox comboGroup1= new ComboBox("WAREHOUSE/GUDANG");
	private ComboBox comboGroup2= new ComboBox("GRUP BARANG");

	private DateField dateField1From = new DateField("TANGGAL TRANSAKSI");
	private DateField dateField1To = new DateField("S.D");
	
	private Button btnProcess = new Button("Proses");
	private Button btnClose = new Button("Close");
	
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelBottom = new Panel();
	
	
	public RecalculateSaldoStockView(RecalculateSaldoStockModel model){
		this.model = model;
		initComponent();
		buildView();
		
		setDisplay();	
		
	}

	public void initComponent(){
		comboGroup1.setWidth("300px");
		comboGroup2.setWidth("300px");

		dateField1From.setDateFormat("dd/MM/yyyy");
		dateField1To.setDateFormat("dd/MM/yyyy");
		
	}

	public void buildView(){
		//Inisialisasi Panel 
		setSizeFull();
		content.setSizeFull();
		content.setMargin(true);
		panelTop.setSizeFull();
		panelBottom.setSizeFull();

		VerticalLayout layoutTop = new VerticalLayout();		
		layoutTop.setMargin(true);
		HorizontalLayout layoutBottom = new HorizontalLayout();		
		layoutBottom.setMargin(true);
		
		layoutTop.addComponent(comboGroup1);
//		layoutTop.addComponent(comboGroup2);
		layoutTop.addComponent(dateField1From);
		layoutTop.addComponent(dateField1To);
		
		layoutBottom.addComponent(btnProcess);
		layoutBottom.addComponent(btnClose);
		
		
		panelTop.setContent(layoutTop);
		content.addComponent(panelTop);		
		content.addComponent(layoutBottom);
		
		setCompositionRoot(content);
		
	}
	
	public void setDisplay(){
		comboGroup1.setContainerDataSource(model.getBeanItemContainerWarehouse());
		comboGroup1.setNewItemsAllowed(false);
		comboGroup1.setFilteringMode(FilteringMode.CONTAINS);
		comboGroup1.setNullSelectionAllowed(false);

		comboGroup2.setContainerDataSource(model.getBeanItemContainerProductgroup());
		comboGroup2.setNewItemsAllowed(false);
		comboGroup2.setFilteringMode(FilteringMode.CONTAINS);
		comboGroup2.setNullSelectionAllowed(false);
		
		dateField1From.setValue(model.getTransaksiHelper().getCurrentTransDate());
		dateField1To.setValue(model.getTransaksiHelper().getCurrentTransDate());
		
	}

	public RecalculateSaldoStockModel getModel() {
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

	public Button getBtnProcess() {
		return btnProcess;
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

	public void setModel(RecalculateSaldoStockModel model) {
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

	public void setBtnProcess(Button btnProcess) {
		this.btnProcess = btnProcess;
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
