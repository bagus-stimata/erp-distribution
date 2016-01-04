package org.erp.distribution.ap.kredittunai.paylist.payform;

import org.erp.distribution.ar.kredittunai.info.InfoReturTransferGiroModel;
import org.erp.distribution.ar.kredittunai.info.InfoReturTransferGiroPresenter;
import org.erp.distribution.ar.kredittunai.info.InfoReturTransferGiroView;
import org.erp.distribution.model.Bukugiro;
import org.erp.distribution.model.Bukutransfer;
import org.erp.distribution.model.FtAppaymentd;
import org.erp.distribution.model.FtArpaymentd;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesh;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class ApPaymentVendorPembayaranView extends CustomComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ApPaymentVendorPembayaranModel model;
	
	//COMPONENT WINDOW
	private TextField fieldInvoice = new TextField("INVOICE:");
	private TextField fieldInvoiceAmount = new TextField("NILAI INVOICE:");
	private TextField fieldInvoiceAmountRevisi = new TextField("+/-");
	private TextField fieldInvoiceAmountPaid = new TextField("INVOICE TERBAYAR:");
	private TextField fieldSubTotalAmountPaid = new TextField("SUB TOTAL:");
	
	private TextField fieldCashPay = new TextField("CASH:");
	private Button btnEqualCash = new Button("=");

	private TextField fieldReturPay = new TextField("MRV:");
	private Button btnEqualRetur = new Button("=");
	private ComboBox comboRetur = new ComboBox("INV MRV");
	private Button btnReturBrowse = new Button("...");
	
	private TextField fieldGiroPay = new TextField("GIRO:");
	private Button btnEqualGiro = new Button("=");
	private ComboBox comboGiro = new ComboBox("NO GIRO");
	private Button btnGiroBrowse = new Button("...");

	private TextField fieldTransferPay = new TextField("TRANSFER:");
	private Button btnEqualTransfer = new Button("=");
	private ComboBox comboTransfer = new ComboBox("NO TRANSFER");
	private Button btnTransferBrowse = new Button("...");
//	private TextField fieldTransfer = new TextField("NO TRANSFER/KET");
	
	
	private TextField fieldPotLainPay = new TextField("POT LAIN/DCV:");
	private Button btnEqualPotLain = new Button("=");
	private ComboBox comboPotlainPayJenis= new ComboBox("JENIS DCV");

	private TextField fieldKelebihanBayarPay = new TextField("KELEBIHAN BAYAR:");
	private Button btnEqualKelebihanBayar = new Button("=");
	
	private Button btnSaveForm= new Button("Save");
	private Button btnCancelForm= new Button("Cancel & close");
        
        private VerticalLayout content = new VerticalLayout();
    	private FormLayout layoutRoot = new FormLayout();
	
	private BeanFieldGroup<FtPurchaseh> binderArinvoice = new BeanFieldGroup<FtPurchaseh>(FtPurchaseh.class);
	private BeanFieldGroup<FtAppaymentd> binderArpaymentDetail = new BeanFieldGroup<FtAppaymentd>(FtAppaymentd.class);
	
	public ApPaymentVendorPembayaranView(){
		initFieldFactory();		
		buildView();
		//USING FIELD GROUP
//		bindAndBuildFieldGroupComponent();
	} 
	public ApPaymentVendorPembayaranView(ApPaymentVendorPembayaranModel model){
		this.model = model;
		initComponent();
		initFieldFactory();		
		buildView();
                initComponentFirstState();
                       
		//USING FIELD GROUP
		bindAndBuildFieldGroupComponent();
			
	}
	public void initComponent(){
		comboRetur.setWidth("250px");
		comboTransfer.setWidth("250px");
		comboGiro.setWidth("250px");
	
		comboPotlainPayJenis.addItem(1);
		comboPotlainPayJenis.setItemCaption(1, "D/K Voucher");		
		comboPotlainPayJenis.addItem(2);
		comboPotlainPayJenis.setItemCaption(2, "D/K Claim");
		comboPotlainPayJenis.addItem(3);
		comboPotlainPayJenis.setItemCaption(3, "D/K Lain-lain");
		comboPotlainPayJenis.setNullSelectionAllowed(false);
		
		//Create content
		layoutRoot.setImmediate(false);
		
//		binderArinvoice.setBuffered(true);
		binderArpaymentDetail.setBuffered(true);
		
//		comboGiro.setImmediate(true);
//		comboTransfer.setImmediate(true);
//		comboRetur.setImmediate(true);
		fieldInvoiceAmountRevisi.setWidth("70px");
                
                btnSaveForm.setIcon(new ThemeResource("../images/navigation/12x12/Create.png") );
                btnCancelForm.setIcon(new ThemeResource("../images/navigation/12x12/Undo.png") );
                
                
	}
	public void initFieldFactory(){
		
	}
	
	public void buildView(){
		setSizeFull();
		content.setSizeFull();
        content.setMargin(true);

        TabSheet tabSheet = new TabSheet();
        tabSheet.setSizeFull();
        VerticalLayout layoutUtama = new VerticalLayout();
//                layoutUtama.setSizeFull();
        layoutUtama.setMargin(true);

		HorizontalLayout layoutInvoice = new HorizontalLayout();		
		HorizontalLayout layoutCash = new HorizontalLayout();				
		HorizontalLayout layoutGiro = new HorizontalLayout();		
		HorizontalLayout layoutTransfer = new HorizontalLayout();		
		HorizontalLayout layoutRetur = new HorizontalLayout();
		HorizontalLayout layoutPotLain = new HorizontalLayout();
		HorizontalLayout layoutKelebihanBayar = new HorizontalLayout();

        HorizontalLayout layoutTop = new HorizontalLayout();		
        VerticalLayout layoutMiddle = new VerticalLayout();
        HorizontalLayout layoutButtom = new HorizontalLayout();	
        layoutButtom.setMargin(true);
		
		layoutUtama.addComponent(layoutTop);
		layoutUtama.addComponent(layoutMiddle);
		layoutUtama.addComponent(layoutButtom);
		
        tabSheet.addTab(layoutUtama, "FORM PEMBAYARAN PER INVOICE", null);                
        content.addComponent(tabSheet);                
		
		setCompositionRoot(content);
                
                //TOP
		layoutInvoice.addComponent(fieldInvoice);
		layoutInvoice.addComponent(fieldInvoiceAmount);
		layoutInvoice.addComponent(fieldInvoiceAmountRevisi);
		layoutInvoice.addComponent(fieldInvoiceAmountPaid);
		layoutInvoice.addComponent(fieldSubTotalAmountPaid);
		layoutTop.addComponent(layoutInvoice);
		
                //MIDDLE
		layoutCash.addComponent(fieldCashPay);
		layoutCash.addComponent(btnEqualCash);
		layoutCash.setComponentAlignment(btnEqualCash, Alignment.BOTTOM_CENTER);
		layoutMiddle.addComponent(layoutCash);

		layoutRetur.addComponent(fieldReturPay);
		layoutRetur.addComponent(btnEqualRetur);
		layoutRetur.addComponent(comboRetur);
		layoutRetur.addComponent(btnReturBrowse);
		layoutRetur.setComponentAlignment(btnReturBrowse, Alignment.BOTTOM_CENTER);
		layoutRetur.setComponentAlignment(btnEqualRetur, Alignment.BOTTOM_CENTER);
		layoutMiddle.addComponent(layoutRetur);
		
		layoutGiro.addComponent(fieldGiroPay);
		layoutGiro.addComponent(btnEqualGiro);
		layoutGiro.addComponent(comboGiro);
		layoutGiro.addComponent(btnGiroBrowse);
		layoutGiro.setComponentAlignment(btnGiroBrowse, Alignment.BOTTOM_CENTER);
		layoutGiro.setComponentAlignment(btnEqualGiro, Alignment.BOTTOM_CENTER);
		layoutMiddle.addComponent(layoutGiro);
		
		layoutTransfer.addComponent(fieldTransferPay);
		layoutTransfer.addComponent(btnEqualTransfer);
		layoutTransfer.addComponent(comboTransfer);
		layoutTransfer.addComponent(btnTransferBrowse);
		layoutTransfer.setComponentAlignment(btnEqualTransfer, Alignment.BOTTOM_CENTER);
		layoutTransfer.setComponentAlignment(btnTransferBrowse, Alignment.BOTTOM_CENTER);
		layoutMiddle.addComponent(layoutTransfer);
		
		layoutPotLain.addComponent(fieldPotLainPay);
		layoutPotLain.addComponent(btnEqualPotLain);
		layoutPotLain.setComponentAlignment(btnEqualPotLain, Alignment.BOTTOM_CENTER);
		layoutPotLain.addComponent(comboPotlainPayJenis);
		layoutPotLain.setComponentAlignment(comboPotlainPayJenis, Alignment.BOTTOM_CENTER);
		layoutMiddle.addComponent(layoutPotLain);

		layoutKelebihanBayar.addComponent(fieldKelebihanBayarPay);
		layoutKelebihanBayar.addComponent(btnEqualKelebihanBayar);
		layoutKelebihanBayar.setComponentAlignment(btnEqualKelebihanBayar, Alignment.BOTTOM_CENTER);
		layoutMiddle.addComponent(layoutKelebihanBayar);
		
		//BUTTOM TOMBOL TOMBOL
		layoutButtom.addComponent(btnSaveForm);
		layoutButtom.addComponent(btnCancelForm);
		layoutButtom.setSpacing(true);
		
	}
	
    public void initComponentFirstState(){
    	comboPotlainPayJenis.select(1);
    }
    
	public void bindAndBuildFieldGroupComponent(){
		
		//Init isian combobox
		comboGiro.setContainerDataSource(model.getBeanItemContainerBukuGiro());
		comboGiro.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);	
		comboGiro.setItemCaptionPropertyId("gironumber");
//		comboGiro.setNullSelectionAllowed(false);
	
		comboTransfer.setContainerDataSource(model.getBeanItemContainerBukuTransfer());
		comboTransfer.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);			
		comboTransfer.setItemCaptionPropertyId("transfernumber");
//		comboTransfer.setNullSelectionAllowed(false);
		
		comboRetur.setContainerDataSource(model.getBeanitemContainerReturBelumLunas());
		comboRetur.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);			
//		comboTransfer.setNullSelectionAllowed(false);
		

		
		binderArinvoice.bind(fieldInvoice, "invoiceno");
		binderArinvoice.bind(fieldInvoiceAmount, "amountafterdiscafterppn");
//		binderArinvoice.bind(fieldInvoiceAmountRevisi, "amountrevisi");
		binderArinvoice.bind(fieldInvoiceAmountPaid, "amountpay");
		
		binderArpaymentDetail.bind(fieldSubTotalAmountPaid, "subtotalpay");
		
		binderArpaymentDetail.bind(fieldCashPay, "cashamountpay");
		
		binderArpaymentDetail.bind(fieldReturPay, "mrvamountpay");
//		binderArpaymentDetail.bind(comboRetur, "returBean");
		
		binderArpaymentDetail.bind(fieldGiroPay, "giroamountpay");
//		binderArpaymentDetail.bind(comboGiro, "bukugiroBean");
		
		binderArpaymentDetail.bind(fieldTransferPay, "transferamountpay");		
//		binderArpaymentDetail.bind(comboTransfer, "bukutransferBean");
		
		binderArpaymentDetail.bind(fieldPotLainPay, "dcvamountpay");
//		binderArpaymentDetail.bind(comboPotlainPayJenis, "potonganamountjenis");
		
		binderArpaymentDetail.bind(fieldKelebihanBayarPay, "kelebihanbayaramount");
		
	}
	
	public void refreshData(){
		binderArinvoice.setItemDataSource(model.getApInvoice());
		binderArpaymentDetail.setItemDataSource(model.getApPaymentDetail());

		
		//SEMUA FIELD SETELAH DIREFRESH DATA AKAN READONLY(FALSE) SEHINGGA
		//HARUS DIBALIKKAN READ ONLY LAGI SETELAH REFRESH DATA
		fieldInvoice.setReadOnly(true);		
		fieldInvoiceAmount.setReadOnly(true);		
		fieldInvoiceAmountRevisi.setReadOnly(true);		
		fieldInvoiceAmountPaid.setReadOnly(true);
		
		//JIKA RETUR MAKA TIDAK BISA CASH, GIRO, RETUR
		if (model.getApInvoice().getTipefaktur().equals("R")){
			fieldGiroPay.setReadOnly(true);
			fieldTransferPay.setReadOnly(true);
			fieldReturPay.setReadOnly(true);
			comboGiro.setReadOnly(true);
			comboTransfer.setReadOnly(true);
			comboRetur.setReadOnly(true);
		}
		
	}
	
	private Window windowInfoReturTransferGiro = new Window();
	private InfoReturTransferGiroModel infoReturTransferGiroModel;	
	private InfoReturTransferGiroView infoReturTransferGiroView;	
	private InfoReturTransferGiroPresenter infoReturTransferGiroPresenter;
	
	public void buildWindowInfoReturTransferGiro(FtSalesh arinvoiceRetur, Bukugiro bukuGiro, Bukutransfer bukuTransfer){
		VerticalLayout layout = new VerticalLayout();
		
		if (arinvoiceRetur !=null){
			infoReturTransferGiroModel = new InfoReturTransferGiroModel(arinvoiceRetur);
		} else if (bukuGiro != null){
			infoReturTransferGiroModel = new InfoReturTransferGiroModel(bukuGiro);			
		} else if (bukuTransfer !=null){
			infoReturTransferGiroModel = new InfoReturTransferGiroModel(bukuTransfer);			
		} else {
			infoReturTransferGiroModel = new InfoReturTransferGiroModel();			
		}
		infoReturTransferGiroView = new InfoReturTransferGiroView(infoReturTransferGiroModel);
		infoReturTransferGiroPresenter = new InfoReturTransferGiroPresenter(infoReturTransferGiroModel, infoReturTransferGiroView);
		infoReturTransferGiroView.setSizeFull();
		layout.addComponent(infoReturTransferGiroView);
		
		windowInfoReturTransferGiro.setModal(true);
		windowInfoReturTransferGiro.center();
		windowInfoReturTransferGiro.setStyleName("login-layout");
		windowInfoReturTransferGiro.setWidth("850px");
		windowInfoReturTransferGiro.setHeight("400px");

		windowInfoReturTransferGiro.setContent(layout);
		
		
		getUI().addWindow(windowInfoReturTransferGiro);
		
	}
	public void destroyWindowInfoReturTransferGiro(){
		windowInfoReturTransferGiro.close();
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ApPaymentVendorPembayaranModel getModel() {
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
	public TextField getFieldInvoiceAmountPaid() {
		return fieldInvoiceAmountPaid;
	}
	public TextField getFieldSubTotalAmountPaid() {
		return fieldSubTotalAmountPaid;
	}
	public TextField getFieldCashPay() {
		return fieldCashPay;
	}
	public Button getBtnEqualCash() {
		return btnEqualCash;
	}
	public TextField getFieldReturPay() {
		return fieldReturPay;
	}
	public Button getBtnEqualRetur() {
		return btnEqualRetur;
	}
	public ComboBox getComboRetur() {
		return comboRetur;
	}
	public Button getBtnReturBrowse() {
		return btnReturBrowse;
	}
	public TextField getFieldGiroPay() {
		return fieldGiroPay;
	}
	public Button getBtnEqualGiro() {
		return btnEqualGiro;
	}
	public ComboBox getComboGiro() {
		return comboGiro;
	}
	public Button getBtnGiroBrowse() {
		return btnGiroBrowse;
	}
	public TextField getFieldTransferPay() {
		return fieldTransferPay;
	}
	public Button getBtnEqualTransfer() {
		return btnEqualTransfer;
	}
	public ComboBox getComboTransfer() {
		return comboTransfer;
	}
	public Button getBtnTransferBrowse() {
		return btnTransferBrowse;
	}
	public TextField getFieldPotLainPay() {
		return fieldPotLainPay;
	}
	public Button getBtnEqualPotLain() {
		return btnEqualPotLain;
	}
	public ComboBox getComboPotlainPayJenis() {
		return comboPotlainPayJenis;
	}
	public TextField getFieldKelebihanBayarPay() {
		return fieldKelebihanBayarPay;
	}
	public Button getBtnEqualKelebihanBayar() {
		return btnEqualKelebihanBayar;
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
	public FormLayout getLayoutRoot() {
		return layoutRoot;
	}
	public BeanFieldGroup<FtPurchaseh> getBinderArinvoice() {
		return binderArinvoice;
	}
	public BeanFieldGroup<FtAppaymentd> getBinderArpaymentDetail() {
		return binderArpaymentDetail;
	}
	public Window getWindowInfoReturTransferGiro() {
		return windowInfoReturTransferGiro;
	}
	public InfoReturTransferGiroModel getInfoReturTransferGiroModel() {
		return infoReturTransferGiroModel;
	}
	public InfoReturTransferGiroView getInfoReturTransferGiroView() {
		return infoReturTransferGiroView;
	}
	public InfoReturTransferGiroPresenter getInfoReturTransferGiroPresenter() {
		return infoReturTransferGiroPresenter;
	}
	public void setModel(ApPaymentVendorPembayaranModel model) {
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
	public void setFieldInvoiceAmountPaid(TextField fieldInvoiceAmountPaid) {
		this.fieldInvoiceAmountPaid = fieldInvoiceAmountPaid;
	}
	public void setFieldSubTotalAmountPaid(TextField fieldSubTotalAmountPaid) {
		this.fieldSubTotalAmountPaid = fieldSubTotalAmountPaid;
	}
	public void setFieldCashPay(TextField fieldCashPay) {
		this.fieldCashPay = fieldCashPay;
	}
	public void setBtnEqualCash(Button btnEqualCash) {
		this.btnEqualCash = btnEqualCash;
	}
	public void setFieldReturPay(TextField fieldReturPay) {
		this.fieldReturPay = fieldReturPay;
	}
	public void setBtnEqualRetur(Button btnEqualRetur) {
		this.btnEqualRetur = btnEqualRetur;
	}
	public void setComboRetur(ComboBox comboRetur) {
		this.comboRetur = comboRetur;
	}
	public void setBtnReturBrowse(Button btnReturBrowse) {
		this.btnReturBrowse = btnReturBrowse;
	}
	public void setFieldGiroPay(TextField fieldGiroPay) {
		this.fieldGiroPay = fieldGiroPay;
	}
	public void setBtnEqualGiro(Button btnEqualGiro) {
		this.btnEqualGiro = btnEqualGiro;
	}
	public void setComboGiro(ComboBox comboGiro) {
		this.comboGiro = comboGiro;
	}
	public void setBtnGiroBrowse(Button btnGiroBrowse) {
		this.btnGiroBrowse = btnGiroBrowse;
	}
	public void setFieldTransferPay(TextField fieldTransferPay) {
		this.fieldTransferPay = fieldTransferPay;
	}
	public void setBtnEqualTransfer(Button btnEqualTransfer) {
		this.btnEqualTransfer = btnEqualTransfer;
	}
	public void setComboTransfer(ComboBox comboTransfer) {
		this.comboTransfer = comboTransfer;
	}
	public void setBtnTransferBrowse(Button btnTransferBrowse) {
		this.btnTransferBrowse = btnTransferBrowse;
	}
	public void setFieldPotLainPay(TextField fieldPotLainPay) {
		this.fieldPotLainPay = fieldPotLainPay;
	}
	public void setBtnEqualPotLain(Button btnEqualPotLain) {
		this.btnEqualPotLain = btnEqualPotLain;
	}
	public void setComboPotlainPayJenis(ComboBox comboPotlainPayJenis) {
		this.comboPotlainPayJenis = comboPotlainPayJenis;
	}
	public void setFieldKelebihanBayarPay(TextField fieldKelebihanBayarPay) {
		this.fieldKelebihanBayarPay = fieldKelebihanBayarPay;
	}
	public void setBtnEqualKelebihanBayar(Button btnEqualKelebihanBayar) {
		this.btnEqualKelebihanBayar = btnEqualKelebihanBayar;
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
	public void setLayoutRoot(FormLayout layoutRoot) {
		this.layoutRoot = layoutRoot;
	}
	public void setBinderArinvoice(BeanFieldGroup<FtPurchaseh> binderArinvoice) {
		this.binderArinvoice = binderArinvoice;
	}
	public void setBinderArpaymentDetail(
			BeanFieldGroup<FtAppaymentd> binderArpaymentDetail) {
		this.binderArpaymentDetail = binderArpaymentDetail;
	}
	public void setWindowInfoReturTransferGiro(Window windowInfoReturTransferGiro) {
		this.windowInfoReturTransferGiro = windowInfoReturTransferGiro;
	}
	public void setInfoReturTransferGiroModel(
			InfoReturTransferGiroModel infoReturTransferGiroModel) {
		this.infoReturTransferGiroModel = infoReturTransferGiroModel;
	}
	public void setInfoReturTransferGiroView(
			InfoReturTransferGiroView infoReturTransferGiroView) {
		this.infoReturTransferGiroView = infoReturTransferGiroView;
	}
	public void setInfoReturTransferGiroPresenter(
			InfoReturTransferGiroPresenter infoReturTransferGiroPresenter) {
		this.infoReturTransferGiroPresenter = infoReturTransferGiroPresenter;
	}
	
	
	
}
