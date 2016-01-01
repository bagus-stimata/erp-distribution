package org.erp.distribution.ar.kredittunai.info;

import org.erp.distribution.model.Bukugiro;
import org.erp.distribution.model.Bukutransfer;
import org.erp.distribution.model.FtSalesh;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class InfoReturTransferGiroView extends CustomComponent{
	private InfoReturTransferGiroModel model;
	
	private TextField fieldId = new TextField("ID");
	private TextField fieldNomor = new TextField("RETUR/GIRO/TRANSFER");
	private TextField fieldAmount = new TextField("NILAI");
	private TextField fieldAmountUsed = new TextField("NILAI TERPAKAI");
	private TextField fieldAmountSisa = new TextField("NILAI SISA");
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel & close");
	private Button btnClose= new Button("Close");
	
	private Table table = new Table();
	
	private FormLayout layoutRoot = new FormLayout();
	private VerticalLayout layoutContent = new VerticalLayout();
	
	private BeanFieldGroup<FtSalesh> binderArinvoiceRetur = new BeanFieldGroup<FtSalesh>(FtSalesh.class);
	private BeanFieldGroup<Bukugiro> binderBukugiro = new BeanFieldGroup<Bukugiro>(Bukugiro.class);
	private BeanFieldGroup<Bukutransfer> binderBukutransfer = new BeanFieldGroup<Bukutransfer>(Bukutransfer.class);
	
	public InfoReturTransferGiroView(InfoReturTransferGiroModel model){
		this.model = model;
		initComponent();
		buildView();
		
		if (model.arinvoiceRetur !=null){
			bindAndBuildFieldGroupComponentRetur();
		} else if (model.bukuGiro !=null){
			bindAndBuildFieldGroupComponentGiro();;			
		} else if (model.bukuTransfer !=null){
			bindAndBuildFieldGroupComponentTransfer();			
		}
		setDisplay();
	}	
	public void initComponent(){
		table = new Table("Riwayat Pembayaran:");
	}
	public void buildView(){
		setSizeFull();
		layoutContent.setSizeFull();
		
		HorizontalLayout layoutInvoice = new HorizontalLayout();
		VerticalLayout layoutTable = new VerticalLayout();	
		layoutTable.setHeight("200px");
		HorizontalLayout layoutButton = new HorizontalLayout();		
		
		layoutInvoice.addComponent(fieldId);
		layoutInvoice.addComponent(fieldNomor);
		layoutInvoice.addComponent(fieldAmount);
		layoutInvoice.addComponent(fieldAmountUsed);
		layoutInvoice.addComponent(fieldAmountSisa);

		table.setSizeFull();
		layoutTable.addComponent(table);
//		layoutButton.addComponent(btnSaveForm);
//		layoutButton.addComponent(btnCancelForm);
		layoutButton.addComponent(btnClose);
		
		layoutContent.addComponent(layoutInvoice);	
		layoutContent.addComponent(layoutTable);
		layoutContent.addComponent(layoutButton);

		layoutButton.setCaption("");
		layoutButton.setSpacing(true);
		
//		layoutContent.setComponentAlignment(layoutInvoice, Alignment.BOTTOM_CENTER);
//		layoutContent.setComponentAlignment(layoutButton, Alignment.BOTTOM_CENTER);
		
		layoutRoot.addComponent(layoutContent);		
		
		setCompositionRoot(layoutRoot);
		
		
	}
	public void setDisplay(){
		reloadMainTable();
	}
		
	public void reloadMainTable(){
		table.setContainerDataSource(model.getBeanItemContainerArpaymentdetail());
		setTableProperties();
		reloadMainTableFooter();
	}
	public void reloadMainTableFooter(){
		
	}
	public void bindAndBuildFieldGroupComponentRetur(){
		try{
			binderArinvoiceRetur.setItemDataSource(model.getArinvoiceRetur());
		} catch(Exception ex){}
		binderArinvoiceRetur.bind(fieldId, "id.invoiceno");
		binderArinvoiceRetur.bind(fieldAmount, "amount");
		binderArinvoiceRetur.bind(fieldAmountUsed, "amountrevisi");
		
		fieldId.setReadOnly(true);
		fieldNomor.setReadOnly(true);
		fieldAmount.setReadOnly(true);
		fieldAmountUsed.setReadOnly(true);
		fieldAmountSisa.setReadOnly(true);
		
	}	
	public void bindAndBuildFieldGroupComponentGiro(){
		try{
			binderBukugiro.setItemDataSource(model.getBukuGiro());
		} catch(Exception ex){}
		binderArinvoiceRetur.bind(fieldId, "id");
		binderArinvoiceRetur.bind(fieldNomor, "id.invoiceno");
		binderArinvoiceRetur.bind(fieldAmount, "amount");
		binderArinvoiceRetur.bind(fieldAmountUsed, "amountpay");
		
		fieldId.setReadOnly(true);
		fieldNomor.setReadOnly(true);
		fieldAmount.setReadOnly(true);
		fieldAmountUsed.setReadOnly(true);
		fieldAmountSisa.setReadOnly(true);
		
	}	
	public void bindAndBuildFieldGroupComponentTransfer(){
		try{
			binderBukutransfer.setItemDataSource(model.getBukuTransfer());
		} catch(Exception ex){}
		binderArinvoiceRetur.bind(fieldId, "id.invoiceno");
		binderArinvoiceRetur.bind(fieldAmount, "amount");
		binderArinvoiceRetur.bind(fieldAmountUsed, "amountrevisi");
		
		fieldId.setReadOnly(true);
		fieldNomor.setReadOnly(true);
		fieldAmount.setReadOnly(true);
		fieldAmountUsed.setReadOnly(true);
		fieldAmountSisa.setReadOnly(true);
		
	}	
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){
		setVisibleTableProperties("id.refno", "id.number", "id.invoiceno", "id.division", "giroamountpay", 
				"returamountpay",   "transferamountpay");
		
		
		table.setColumnCollapsingAllowed(true);
		try{
			table.setColumnCollapsed("nopo", true);
			table.setColumnCollapsed("lockupdate", true);
			
		} catch(Exception ex){}
		
		//ALIGNMENT
		table.setColumnAlignment("giroamountpay", Align.RIGHT);
		table.setColumnAlignment("returamountpay", Align.RIGHT);
		table.setColumnAlignment("transferamountpay", Align.RIGHT);
		
		//set header
		table.setColumnHeader("id.refno", "NO.REF");
		table.setColumnHeader("id.number", "NOMOR");
		table.setColumnHeader("id.invoiceno", "NO. INVOICE");
		table.setColumnHeader("id.division", "DIVISI");
		table.setColumnHeader("returamountpay", "BYR RETUR");
		table.setColumnHeader("giroamountpay", "BYR GIRO");
		table.setColumnHeader("transferamountpay", "BYR TRASNFER");
		
		
//		table.setColumnExpandRatio("selected", 2);
//		table.setColumnExpandRatio("recapno", 3);
//		table.setColumnExpandRatio("id", 5);
				
	}
	public InfoReturTransferGiroModel getModel() {
		return model;
	}
	public TextField getFieldId() {
		return fieldId;
	}
	public TextField getFieldNomor() {
		return fieldNomor;
	}
	public TextField getFieldAmount() {
		return fieldAmount;
	}
	public TextField getFieldAmountUsed() {
		return fieldAmountUsed;
	}
	public TextField getFieldAmountSisa() {
		return fieldAmountSisa;
	}
	public Button getBtnSaveForm() {
		return btnSaveForm;
	}
	public Button getBtnCancelForm() {
		return btnCancelForm;
	}
	public Button getBtnClose() {
		return btnClose;
	}
	public Table getTable() {
		return table;
	}
	public FormLayout getLayoutRoot() {
		return layoutRoot;
	}
	public VerticalLayout getLayoutContent() {
		return layoutContent;
	}
	public BeanFieldGroup<FtSalesh> getBinderArinvoiceRetur() {
		return binderArinvoiceRetur;
	}
	public BeanFieldGroup<Bukugiro> getBinderBukugiro() {
		return binderBukugiro;
	}
	public BeanFieldGroup<Bukutransfer> getBinderBukutransfer() {
		return binderBukutransfer;
	}
	public void setModel(InfoReturTransferGiroModel model) {
		this.model = model;
	}
	public void setFieldId(TextField fieldId) {
		this.fieldId = fieldId;
	}
	public void setFieldNomor(TextField fieldNomor) {
		this.fieldNomor = fieldNomor;
	}
	public void setFieldAmount(TextField fieldAmount) {
		this.fieldAmount = fieldAmount;
	}
	public void setFieldAmountUsed(TextField fieldAmountUsed) {
		this.fieldAmountUsed = fieldAmountUsed;
	}
	public void setFieldAmountSisa(TextField fieldAmountSisa) {
		this.fieldAmountSisa = fieldAmountSisa;
	}
	public void setBtnSaveForm(Button btnSaveForm) {
		this.btnSaveForm = btnSaveForm;
	}
	public void setBtnCancelForm(Button btnCancelForm) {
		this.btnCancelForm = btnCancelForm;
	}
	public void setBtnClose(Button btnClose) {
		this.btnClose = btnClose;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public void setLayoutRoot(FormLayout layoutRoot) {
		this.layoutRoot = layoutRoot;
	}
	public void setLayoutContent(VerticalLayout layoutContent) {
		this.layoutContent = layoutContent;
	}
	public void setBinderArinvoiceRetur(
			BeanFieldGroup<FtSalesh> binderArinvoiceRetur) {
		this.binderArinvoiceRetur = binderArinvoiceRetur;
	}
	public void setBinderBukugiro(BeanFieldGroup<Bukugiro> binderBukugiro) {
		this.binderBukugiro = binderBukugiro;
	}
	public void setBinderBukutransfer(
			BeanFieldGroup<Bukutransfer> binderBukutransfer) {
		this.binderBukutransfer = binderBukutransfer;
	}
	
	
	
}
