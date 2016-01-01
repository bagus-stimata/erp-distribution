package org.erp.distribution.ar.kredittunai.revisinota;


import org.erp.distribution.model.FtSalesh;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class RevisiNotaView extends CustomComponent{
	private RevisiNotaModel model;
	
	private TextField fieldInvoice = new TextField("INVOICE:");
	private TextField fieldInvoiceAmount = new TextField("NILAI INVOICE:");
	private TextField fieldInvoiceAmountRevisi = new TextField("REV SELISIH +/-");
	private TextField fieldInvoiceAmountReturTampung = new TextField("RETUR TAMP");
	private CheckBox checkReturTampungLunas = new CheckBox("Lunas");
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel & close");
	
	private VerticalLayout content = new VerticalLayout();
	
	private BeanFieldGroup<FtSalesh> binderArinvoice = new BeanFieldGroup<FtSalesh>(FtSalesh.class);
	
	public RevisiNotaView(RevisiNotaModel model){
		this.model = model;
		initComponent();
		buildView();
		bindAndBuildFieldGroupComponent();
	}
	public void initComponent(){
                btnSaveForm.setIcon(new ThemeResource("images/navigation/12x12/Save.png") );
                btnCancelForm.setIcon(new ThemeResource("images/navigation/12x12/Undo.png") );
		
	}
	public void buildView(){
		setSizeFull();
		content.setSizeFull();
                content.setMargin(true);
		
                TabSheet tabSheet = new TabSheet();
                tabSheet.setSizeFull();
                VerticalLayout layoutUtama = new VerticalLayout();
                layoutUtama.setSizeFull();
                layoutUtama.setMargin(true);

                HorizontalLayout layoutInvoice = new HorizontalLayout();
		HorizontalLayout layoutButton = new HorizontalLayout();		
                layoutUtama.addComponent(layoutInvoice);
                layoutUtama.addComponent(layoutButton);
                
                tabSheet.addTab(layoutUtama, "Revisi (+/-) Nota", null);                
                content.addComponent(tabSheet);                
		
		setCompositionRoot(content);
                
		
		layoutInvoice.addComponent(fieldInvoice);
		layoutInvoice.addComponent(fieldInvoiceAmount);
		layoutInvoice.addComponent(fieldInvoiceAmountRevisi);
		layoutInvoice.addComponent(fieldInvoiceAmountReturTampung);
		layoutInvoice.addComponent(checkReturTampungLunas);

		layoutButton.addComponent(btnSaveForm);
		layoutButton.addComponent(btnCancelForm);
		

		layoutButton.setCaption("");
		layoutButton.setSpacing(true);
//		layoutContent.setComponentAlignment(layoutInvoice, Alignment.BOTTOM_CENTER);
//		layoutContent.setComponentAlignment(layoutButton, Alignment.BOTTOM_CENTER);
		
		
		
	}
	public void setDisplay(){
		
	}
	
	public void bindAndBuildFieldGroupComponent(){
		binderArinvoice.setItemDataSource(model.getFtSalesh());
		
		binderArinvoice.bind(fieldInvoice, "id.invoiceno");
		binderArinvoice.bind(fieldInvoiceAmount, "amount");
		binderArinvoice.bind(fieldInvoiceAmountRevisi, "amountrevisi");
		binderArinvoice.bind(fieldInvoiceAmountReturTampung, "amountreturtampung");
		binderArinvoice.bind(checkReturTampungLunas, "returtampunglunas");
		
		fieldInvoice.setReadOnly(true);
		fieldInvoiceAmount.setReadOnly(true);
		
		
	}
	public RevisiNotaModel getModel() {
		return model;
	}
	public TextField getFieldInvoice() {
		return fieldInvoice;
	}
	public TextField getFieldInvoiceAmount() {
		return fieldInvoiceAmount;
	}
	public TextField getFieldInvoiceAmountRevisi() {
		return fieldInvoiceAmountRevisi;
	}
	public TextField getFieldInvoiceAmountReturTampung() {
		return fieldInvoiceAmountReturTampung;
	}
	public CheckBox getCheckReturTampungLunas() {
		return checkReturTampungLunas;
	}
	public Button getBtnSaveForm() {
		return btnSaveForm;
	}
	public Button getBtnCancelForm() {
		return btnCancelForm;
	}
	public VerticalLayout getContent() {
		return content;
	}
	public BeanFieldGroup<FtSalesh> getBinderArinvoice() {
		return binderArinvoice;
	}
	public void setModel(RevisiNotaModel model) {
		this.model = model;
	}
	public void setFieldInvoice(TextField fieldInvoice) {
		this.fieldInvoice = fieldInvoice;
	}
	public void setFieldInvoiceAmount(TextField fieldInvoiceAmount) {
		this.fieldInvoiceAmount = fieldInvoiceAmount;
	}
	public void setFieldInvoiceAmountRevisi(TextField fieldInvoiceAmountRevisi) {
		this.fieldInvoiceAmountRevisi = fieldInvoiceAmountRevisi;
	}
	public void setFieldInvoiceAmountReturTampung(
			TextField fieldInvoiceAmountReturTampung) {
		this.fieldInvoiceAmountReturTampung = fieldInvoiceAmountReturTampung;
	}
	public void setCheckReturTampungLunas(CheckBox checkReturTampungLunas) {
		this.checkReturTampungLunas = checkReturTampungLunas;
	}
	public void setBtnSaveForm(Button btnSaveForm) {
		this.btnSaveForm = btnSaveForm;
	}
	public void setBtnCancelForm(Button btnCancelForm) {
		this.btnCancelForm = btnCancelForm;
	}
	public void setContent(VerticalLayout content) {
		this.content = content;
	}
	public void setBinderArinvoice(BeanFieldGroup<FtSalesh> binderArinvoice) {
		this.binderArinvoice = binderArinvoice;
	}	
	
	
	
}
