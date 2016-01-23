package org.erp.distribution.salesorder.salesorder.windowitem;

import net.sf.jasperreports.components.sort.FieldNumberComparator;

import org.erp.distribution.model.modelenum.EnumOperationStatus;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.themes.Reindeer;

public class SalesOrderItemView extends CustomComponent {
	private static final long serialVersionUID = 1L;

	private SalesOrderItemModel model;
	
	private VerticalLayout content = new VerticalLayout();

	private TextField fieldNomorUrut = new TextField("No)");
	private ComboBox comboProduct= new ComboBox("PRODUCT");
	private TextField fieldSprice = new TextField("HRG JUAL(-PPN)");
	private TextField fieldSpriceafterppn = new TextField("HRG JUAL+PPN");
	private CheckBox checkFreegood = new CheckBox("");

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
	
	private Button btnProductInfo = new Button("Info Product");
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
	private Panel panelCheckFreegood = new Panel("BNS");
	private Panel panelQty1 = new Panel("BES");
	private Panel panelQty2 = new Panel("SED");
	private Panel panelQty3 = new Panel("KEC");
	private Panel panelDisc1 = new Panel("DISC1");
	private Panel panelDisc2 = new Panel("+DISC2");
	private Panel panelSubtotalafterdiscafterppn = new Panel("TOTAL-DISC-PPN");
	
	public SalesOrderItemView(SalesOrderItemModel model){
		this.model = model;
		initComponent();
		buildView();
		
		setDisplay();	

		setParameterSystemRole();
		
	}
	
	public void initComponent(){

		comboProduct.setWidth("300px");

		fieldSprice.setWidth("100px");
		fieldSpriceafterppn.setWidth("100px");

		fieldQty1.setWidth("50px");
		fieldQty2.setWidth("50px");
		fieldQty3.setWidth("50px");
		fieldQty.setWidth("50px");
		
		fieldDisc1.setWidth("50px");
		fieldDisc2.setWidth("50px");
		fieldDisc1rp.setWidth("80px");
		fieldDisc2rp.setWidth("80px");
		
		fieldSubtotal.setWidth("120px");
		fieldSubtotalafterppn.setWidth("120px");
		fieldSubtotalafterdisc.setWidth("120px");
		fieldSubtotalafterdiscafterppn.setWidth("120px");
		
		//NULL REPRESENTATION
		fieldSprice.setNullRepresentation("");
		fieldSpriceafterppn.setNullRepresentation("");
		
		fieldQty1.setNullRepresentation("");
		fieldQty2.setNullRepresentation("");
		fieldQty3.setNullRepresentation("");
		fieldQty.setNullRepresentation("");
		
		fieldDisc1.setNullRepresentation("");
		fieldDisc2.setNullRepresentation("");
		fieldDisc1rp.setNullRepresentation("");
		fieldDisc2rp.setNullRepresentation("");
		
		fieldSubtotal.setNullRepresentation("");
		fieldSubtotalafterppn.setNullRepresentation("");
		fieldSubtotalafterdisc.setNullRepresentation("");
		fieldSubtotalafterdiscafterppn.setNullRepresentation("");
		
		//NOT NULLABLE
		comboProduct.setRequired(true);
		comboProduct.setFilteringMode(FilteringMode.CONTAINS);
		comboProduct.setNullSelectionAllowed(true);
		//COMBO ACCCONT
		comboProduct.setContainerDataSource(model.getBeanItemContainerProduct());
		ValueChangeListener valueListener = new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				// TODO Auto-generated method stub
				System.out.println(event.getProperty().getValue());
			}
		};
		comboProduct.addValueChangeListener(valueListener);
		
		fieldSprice.setRequired(true);
		fieldSpriceafterppn.setRequired(true);
		
		fieldQty1.setRequired(true);
		fieldQty2.setRequired(true);
		fieldQty3.setRequired(true);
		fieldQty.setRequired(true);
		
		fieldDisc1.setRequired(true);
		fieldDisc2.setRequired(true);
		
		fieldSubtotal.setRequired(true);	
		
		btnProductInfo.setWidth("600px");
		btnProductInfo.addStyleName(Reindeer.BUTTON_LINK);
		//ERROR HANDLER UI
		UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
		    @Override
		    public void error(com.vaadin.server.ErrorEvent event) {
		        // Find the final cause
//		        String cause = "<b>The click failed because:</b><br/>";
//		        for (Throwable t = event.getThrowable(); t != null;
//		             t = t.getCause())
//		            if (t.getCause() == null) // We're at final cause
//		                cause += t.getClass().getName() + "<br/>";
		        
		        // Display the error message in a custom fashion
//		        layout.addComponent(new Label(cause, ContentMode.HTML));
		           
		        // Do the default error handling (optional)
//		        doDefault(event);
		    } 
		});		
		
		
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
		panelComboProduct.setContent(comboProduct);
		layoutTop.addComponent(panelComboProduct);
		
		//HARGA SEBELUM PPN
		panelFieldSprice.setContent(fieldSprice);
		layoutTop.addComponent(panelFieldSprice);
		//SETELAH PPN
		panelFieldSpriceafterppn.setContent(fieldSpriceafterppn);
		layoutTop.addComponent(panelFieldSpriceafterppn);
		
//		layoutTop.addComponent(fieldSpriceafterppn);
		panelQty1.setContent(fieldQty1);
		layoutTop.addComponent(panelQty1);
		panelQty2.setContent(fieldQty2);
		layoutTop.addComponent(panelQty2);
		panelQty3.setContent(fieldQty3);
		layoutTop.addComponent(panelQty3);
//		layoutTop.addComponent(fieldQty);
		layoutTop.addComponent(btnSeparator2);
		panelDisc1.setContent(fieldDisc1);
		layoutTop.addComponent(panelDisc1);
//		layoutTop.addComponent(fieldDisc1rp);
		panelDisc2.setContent(fieldDisc2);
		layoutTop.addComponent(panelDisc2);
//		layoutTop.addComponent(fieldDisc2rp);
		layoutTop.addComponent(btnSeparator3);
//		layoutTop.addComponent(fieldSubtotal);
//		layoutTop.addComponent(fieldSubtotalafterppn); 
		panelSubtotalafterdiscafterppn.setContent(fieldSubtotalafterdisc);
		layoutTop.addComponent(panelSubtotalafterdiscafterppn);
//		layoutTop.addComponent(fieldSubtotalafterdiscafterppn);
		
		panelCheckFreegood.setContent(checkFreegood);
		layoutTop.addComponent(panelCheckFreegood);
		
		layoutBottom.addComponent(btnProductInfo);
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
		
		
		model.getBinderItemDetail().bind(fieldNomorUrut, "nourut");
		model.getBinderItemDetail().bind(comboProduct, "fproductBean");
		model.getBinderItemDetail().bind(fieldSprice, "sprice");
		model.getBinderItemDetail().bind(checkFreegood, "id.freegood");
		model.getBinderItemDetail().bind(fieldSpriceafterppn, "spriceafterppn");
		model.getBinderItemDetail().bind(fieldQty1, "qty1");
		model.getBinderItemDetail().bind(fieldQty2, "qty2");
		model.getBinderItemDetail().bind(fieldQty3, "qty3");
		model.getBinderItemDetail().bind(fieldQty, "qty");
		model.getBinderItemDetail().bind(fieldDisc1, "disc1");
		model.getBinderItemDetail().bind(fieldDisc2, "disc2");
		model.getBinderItemDetail().bind(fieldDisc1rp, "disc1rp");
		model.getBinderItemDetail().bind(fieldDisc2rp, "disc2rp");
		model.getBinderItemDetail().bind(fieldSubtotal, "subtotal");
		model.getBinderItemDetail().bind(fieldSubtotalafterppn, "subtotalafterppn");
		model.getBinderItemDetail().bind(fieldSubtotalafterdisc, "subtotalafterdisc");
		model.getBinderItemDetail().bind(fieldSubtotalafterdiscafterppn, "subtotalafterdiscafterppn");
		
		
	}
	public void setFormButtonAndTextState(){
		
		if (model.getOperationStatus().equals(EnumOperationStatus.ADDING.getStrCode())){
			btnAddAndSave.setCaption("Add");
			checkFreegood.setEnabled(true); 
		} else if(model.getOperationStatus().equals(EnumOperationStatus.EDITING.getStrCode())){
			btnAddAndSave.setCaption("Update & Close");
			checkFreegood.setEnabled(false); //EDITING TIDAK BOLEH EDIT FREEGOOD BISANYA HAPUS DULU
		}
		
		if (model.getSysvarHelper().isLockHJualFJual()==true) {
			fieldSprice.setEnabled(false);
			fieldSpriceafterppn.setEnabled(false);			
		}else {
			fieldSprice.setEnabled(true);
			fieldSpriceafterppn.setEnabled(true);			
			
		}
	}
	public void focustIdOrDesc(){	
		comboProduct.focus();
	}
	//###****SET PARAMETER SYSTEM ROLE****#####
	public void setParameterSystemRole(){
		if (model.getSysvarHelper().isEntryItemPenjualanHargaSelelahPPN()==true){
			fieldSprice.setVisible(false);
			panelFieldSprice.setVisible(false);
			fieldSpriceafterppn.setVisible(true);			
			panelFieldSpriceafterppn.setVisible(true);
		}else {
			fieldSprice.setVisible(true);
			panelFieldSprice.setVisible(true);
			fieldSpriceafterppn.setVisible(false);			
			panelFieldSpriceafterppn.setVisible(false);
		}
		
		if (model.getSysvarHelper().isLockHJualFJual()==true){
			fieldSprice.setReadOnly(true);
			fieldSpriceafterppn.setReadOnly(true);
		}else {
			fieldSprice.setReadOnly(false);
			fieldSpriceafterppn.setReadOnly(false);
			
		}
	}
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public SalesOrderItemModel getModel() {
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
	public void setModel(SalesOrderItemModel model) {
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
	public void setPanelComboProduct(Panel panelComboProduct) {
		this.panelComboProduct = panelComboProduct;
	}
	public Panel getPanelFieldSprice() {
		return panelFieldSprice;
	}
	public void setPanelFieldSprice(Panel panelFieldSprice) {
		this.panelFieldSprice = panelFieldSprice;
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
	public CheckBox getCheckFreegood() {
		return checkFreegood;
	}
	public Panel getPanelCheckFreegood() {
		return panelCheckFreegood;
	}
	public void setCheckFreegood(CheckBox checkFreegood) {
		this.checkFreegood = checkFreegood;
	}
	public void setPanelCheckFreegood(Panel panelCheckFreegood) {
		this.panelCheckFreegood = panelCheckFreegood;
	}

	public Panel getPanelFieldSpriceafterppn() {
		return panelFieldSpriceafterppn;
	}

	public void setPanelFieldSpriceafterppn(Panel panelFieldSpriceafterppn) {
		this.panelFieldSpriceafterppn = panelFieldSpriceafterppn;
	}

	public Button getBtnProductInfo() {
		return btnProductInfo;
	}

	public void setBtnProductInfo(Button btnProductInfo) {
		this.btnProductInfo = btnProductInfo;
	}
	

	
}
