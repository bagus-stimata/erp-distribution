package org.erp.distribution.ap.kredittunai.paylist;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.vaadin.addon.jpacontainer.fieldfactory.FieldFactory;
import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

import org.erp.distribution.ap.kredittunai.paylist.payform.ApPaymentVendorPembayaranModel;
import org.erp.distribution.ap.kredittunai.paylist.payform.ApPaymentVendorPembayaranPresenter;
import org.erp.distribution.ap.kredittunai.paylist.payform.ApPaymentVendorPembayaranView;
import org.erp.distribution.ar.kredittunai.paylist.payform.ArPaymentCustPembayaranModel;
import org.erp.distribution.ar.kredittunai.paylist.payform.ArPaymentCustPembayaranPresenter;
import org.erp.distribution.ar.kredittunai.paylist.payform.ArPaymentCustPembayaranView;
import org.erp.distribution.model.FtAppaymentd;
import org.erp.distribution.model.FtArpaymentd;
import org.erp.distribution.model.FtArpaymenth;
import org.erp.distribution.model.FtPurchaseh;
import org.erp.distribution.model.FtSalesh;
import org.erp.distribution.model.modelenum.EnumOperationStatus;


public class ApPaymentVendorView extends CustomComponent {
	private ApPaymentVendorModel model;
	private VerticalLayout content = new VerticalLayout();

//	private JPAContainer<Arpaymentheader> container;
	
	private Table tableHeader;
	private Table tableDetail;
	
	private Form form;
//	private CustomFieldFactory customFieldFactory;
	private FieldFactory fieldFactory;

	private Class<FtArpaymenth> entityClass;
	
	private Button btnCommitHeader;
	private Button btnDiscardHeader;
	private Button btnAddHeader;
	private Button btnEditHeader;
	private Button btnDeleteHeader;
	private Button btnSearchHeader;
	private Button btnReloadHeader;
	
	private Button btnCommitDetail;
	private Button btnDiscardDetail;	
	private Button btnAddDetail;
	private Button btnEditDetail;
	private Button btnDeleteDetail;
	private Button btnSearchDetail;
	private Button btnReloadDetail;
	
	private Object[] formPropertyIds;
	
//	private final String persistenceUnit;
	
//	private AccountGrup accountGrup;
//	private BeanItem<AccountGrup> beanItemAccountGrup;
	
	private String operationStatus;

	//Additional Component
	private TextField fieldInvoiceNo;
	private TextField fieldIdCustomer;
	private TextField fieldNamaCustomer;
	private TextField fieldDivision;
//	private TextField fieldPO;
	private TextField fieldAmount;
	private TextField fieldAmountPay;
	
	
	//Panel
	private Panel panelUtama;
	private Panel panelTopHeader;
	private Panel panelTableHeader;
	private Panel panelTopDetail;
	private Panel panelTableDetail;

	private Window windowHeader = new Window();
	private Window windowItem = new Window();
	
	
	public ApPaymentVendorView(ApPaymentVendorModel model){
		this.model = model;		
		
		initContainer();
		initFieldFactory();
		
		buildView();
		
		//Set Awal Status form
		operationStatus = EnumOperationStatus.OPEN.getStrCode();
		
	}
	public void initContainer(){
		tableHeader = new Table("Payment Header:");		
		tableHeader = new Table("Payment Header: ") {
		    @Override
		    protected String formatPropertyValue(Object rowId,
		            Object colId, Property property) {
		        // Format by property type
		    	try{
			        if (property.getType() == Date.class && property.getValue() != null) {
			            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			            return df.format((Date)property.getValue());
			        }
		    	} catch(Exception ex){}
		    	try{
			        if (property.getType()==Boolean.class){
			        	if ((Boolean) property.getValue()==true) {
			        		return "Closed";
			        	} else {
			        		return "Open";
			        	}
			        }
		    	} catch(Exception ex){}
		        return super.formatPropertyValue(rowId, colId, property);
		    }
		};		
		
		tableHeader.setContainerDataSource(model.getBeanItemContainerHeader());
		tableDetail = new Table("Payment Detail:");
		tableDetail.setContainerDataSource(model.getBeanItemContainerDetail());

		setSizeFull();
		content.setSizeFull();
                content.setMargin(true);
                
		panelUtama = new Panel(getCaption());
		panelUtama.setSizeFull();

		panelTopHeader = new Panel();
		panelTableHeader = new Panel();
		panelTableHeader.setSizeFull();
		panelTopDetail = new Panel();
		panelTableDetail = new Panel();
		panelTableDetail.setSizeFull();
		
		//Init Komponen atas
//		fieldPO = new TextField("No PO");
//		fieldPO.setStyleName(Reindeer.TEXTFIELD_SMALL);
//		fieldPO.setWidth("80px");
		fieldDivision = new TextField("Divisi");
		fieldDivision.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldDivision.setWidth("150px");
		
		fieldInvoiceNo = new TextField("NO INVOICE");
		fieldInvoiceNo.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldInvoiceNo.setWidth("100px");
//		fieldInvoiceNo.setReadOnly(true);
		fieldIdCustomer = new TextField("Id Cust");
		fieldIdCustomer.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldIdCustomer.setWidth("80px");
//		fieldIdCustomer.setReadOnly(true);
		fieldNamaCustomer = new TextField("Customer");
		fieldNamaCustomer.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldNamaCustomer.setWidth("200px");
//		fieldNamaCustomer.setReadOnly(true);
		
		fieldAmount = new TextField("Nilai Faktur/Retur");
		fieldAmount.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldAmount.setWidth("100px");
		fieldAmountPay = new TextField("Terbayar");
		fieldAmountPay.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldAmountPay.setWidth("80px");
                
		//Init komponen tengah
		tableHeader.setSizeFull();
		tableHeader.setSelectable(true);
//		table.addValueChangeListener(this);
		tableHeader.setImmediate(true);
		tableHeader.setBuffered(false);
//		table.addActionHandler(this);		
		tableHeader.setFooterVisible(true);
		
		//Deklarasi Button 
		btnAddHeader = new Button("Add New");	
		btnEditHeader = new Button("Edit");
		btnDeleteHeader = new Button("Delete");
		btnCommitHeader = new Button("Save");		
		btnDiscardHeader = new Button("Cancel");
		btnReloadHeader = new Button("Reload");
		btnSearchHeader = new Button("Search");
		
		btnAddDetail = new Button("Add");		
		btnEditDetail = new Button("Edit");
		btnDeleteDetail = new Button("Remove");
		btnCommitDetail = new Button("Save");		
		btnDiscardDetail = new Button("Cancel");
		btnReloadDetail = new Button("Reload");
		btnSearchDetail = new Button("Search");
		
		//Init komponen bawah
		tableDetail.setSizeFull();
		tableDetail.setSelectable(true);
		tableDetail.setImmediate(true);
		tableDetail.setBuffered(false);
		tableDetail.setFooterVisible(true);
                
        btnAddHeader.setIcon(new ThemeResource("../images/navigation/12x12/Create.png") );
        btnEditHeader.setIcon(new ThemeResource("../images/navigation/12x12/Pencil.png") );
        btnDeleteHeader.setIcon(new ThemeResource("../images/navigation/12x12/Erase.png") );
        btnSearchHeader.setIcon(new ThemeResource("../images/navigation/12x12/Find.png") );
        btnReloadHeader.setIcon(new ThemeResource("../images/navigation/12x12/Sync.png") );
			

        btnAddDetail.setIcon(new ThemeResource("../images/navigation/12x12/Create.png") );
        btnEditDetail.setIcon(new ThemeResource("../images/navigation/12x12/Pencil.png") );
        btnDeleteDetail.setIcon(new ThemeResource("../images/navigation/12x12/Erase.png") );
        btnSearchDetail.setIcon(new ThemeResource("../images/navigation/12x12/Find.png") );
        btnReloadDetail.setIcon(new ThemeResource("../images/navigation/12x12/Sync.png") );
                
	}
	public void initFieldFactory(){
		fieldFactory = new FieldFactory();
	}
	public void buildView(){
		//KOMPONEN ATAS
		HorizontalLayout layoutTop = new HorizontalLayout();		
	
		//KOMPONEN TENGAH
		VerticalLayout middleLayout = new VerticalLayout();
		VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel();
		verticalSplitPanel.setSizeFull();		
		verticalSplitPanel.setSplitPosition(40);
		
//		layoutTop.addComponent(fieldPO);
		layoutTop.addComponent(fieldDivision);		
		layoutTop.addComponent(fieldInvoiceNo);
		layoutTop.addComponent(fieldIdCustomer);
		layoutTop.addComponent(fieldNamaCustomer);		
		layoutTop.addComponent(fieldAmount);
		layoutTop.addComponent(fieldAmountPay);
		layoutTop.setMargin(true);
		panelTopHeader.setContent(layoutTop);
		
		panelTableHeader.setContent(tableHeader);
		
		panelTableDetail.setContent(tableDetail);
		
		HorizontalLayout layoutTopDetail = new HorizontalLayout();
		layoutTopDetail.addComponent(btnAddDetail);
		layoutTopDetail.addComponent(btnEditDetail);
		layoutTopDetail.addComponent(btnDeleteDetail);
		layoutTopDetail.addComponent(btnReloadDetail);
		layoutTopDetail.setMargin(true);
		layoutTopDetail.setSpacing(true);
		panelTopDetail.setContent(layoutTopDetail);
		
		verticalSplitPanel.setFirstComponent(panelTableHeader);		
		VerticalLayout layoutContentDetail = new VerticalLayout();
		layoutContentDetail.setSizeFull();
		layoutContentDetail.addComponent(panelTopDetail);
		layoutContentDetail.addComponent(panelTableDetail);
		layoutContentDetail.setExpandRatio(panelTableDetail, 1);
		verticalSplitPanel.setSecondComponent(layoutContentDetail);
		
		content.addComponent(panelTopHeader);
		content.addComponent(verticalSplitPanel);
		
		panelUtama.setContent(content);
		panelUtama.setSizeFull();
		setCompositionRoot(panelUtama);	
		content.setExpandRatio(verticalSplitPanel, 1);
		
	}
	public void setVisibleTableHeaderProperties(Object... tablePropertyIds) {
		tableHeader.setVisibleColumns(tablePropertyIds);		
	}
	public void setVisibleTableDetailProperties(Object... tablePropertyIds) {
		tableDetail.setVisibleColumns(tablePropertyIds);		
	}
	public void setVisibleFormProperties(Object... formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
		form.setVisibleItemProperties(formPropertyIds);
	}
	public void setTablePropertiesHeader(){
		setVisibleTableHeaderProperties("refno", "norek", "trdate", "entrydate", "notes", "closing");	
		//HEADER
		tableHeader.setColumnHeader("id", "REFNO-INVOICE-DIV");
		tableHeader.setColumnHeader("transdate", "TGL TRANS");
		tableHeader.setColumnHeader("entrydate", "TGL ENTRY");
		
	}
	
	public void setTablePropertiesDetail(){

//		setVisibleTableDetailProperties("id", "cashamountpay", "returamountpay",
//		"giroamountpay", "returBean", "bukugiroBean", "transferamountpay", "bukutransferBean",
//		"potonganamount","kelebihanbayaramount");

		setVisibleTableDetailProperties("id", "cashamountpay", "mrvamountpay",
				"dcvamountpay", "giroamountpay","transferamountpay", "subtotalpay",
				"ftappaymenthBean", "ftpurchasehBean");
		
		//Collapsing Column
		tableDetail.setColumnCollapsingAllowed(true);
		try{
			tableDetail.setColumnCollapsed("divisionBean", true);
			tableDetail.setColumnCollapsed("invoiceretur", true);
			tableDetail.setColumnCollapsed("arpaymentheaderBean", true);
			tableDetail.setColumnCollapsed("invoicetype", true);
			tableDetail.setColumnCollapsed("subtotalpay", true);
			tableDetail.setColumnCollapsed("returBean", true);
			tableDetail.setColumnCollapsed("bukugiroBean", true);
			tableDetail.setColumnCollapsed("bukutransferBean", true);
			
		}catch(Exception ex){}
		
		//Set Caption Header
		tableDetail.setColumnHeader("id", "URUT-REFNO-INVOICE-DIV");
		tableDetail.setColumnHeader("cashamountpay", "CASH");
		tableDetail.setColumnHeader("returamountpay", "RETUR");
		tableDetail.setColumnHeader("giroamountpay", "GIRO");
		tableDetail.setColumnHeader("transferamountpay", "TRANSFER");
		tableDetail.setColumnHeader("potonganamount", "POT LAIN-LAIN");
		tableDetail.setColumnHeader("kelebihanbayaramount", "LEBIH SETOR");
		
		//Set size header
		tableDetail.setColumnExpandRatio("id", 2);
		tableDetail.setColumnExpandRatio("cashamountpay", 1);
		tableDetail.setColumnExpandRatio("returamountpay", 1);
		tableDetail.setColumnExpandRatio("giroamountpay", 1);
		tableDetail.setColumnExpandRatio("transferamountpay", 1);
		tableDetail.setColumnExpandRatio("potonganamount", 1);
		tableDetail.setColumnExpandRatio("kelebihanbayaramount", 1);
		
		//Set alignment each column
		tableDetail.setColumnAlignment("cashamountpay", Align.RIGHT);
		tableDetail.setColumnAlignment("returamountpay", Align.RIGHT);
		tableDetail.setColumnAlignment("giroamountpay", Align.RIGHT);
		tableDetail.setColumnAlignment("transferamountpay", Align.RIGHT);
		tableDetail.setColumnAlignment("potonganamount", Align.RIGHT);
		tableDetail.setColumnAlignment("kelebihanbayaramount", Align.RIGHT);
		
	}
	
	public void setFormProperties(){
//		setVisibleFormProperties("refno", "userid");
	}
	
	public void setDisplayHeader(){
		
		if (model.getItemInvoice() !=null){		
			//MAKE READONLY FALSE KARENA MANUAL
			fieldDivision.setReadOnly(false);
			fieldInvoiceNo.setReadOnly(false);
			fieldIdCustomer.setReadOnly(false);
			fieldNamaCustomer.setReadOnly(false);			
			fieldAmount.setReadOnly(false);
			fieldAmountPay.setReadOnly(false);
			
//			fieldDivision.setValue(model.getItemInvoice().getDivisionBean().getDivisi());
			fieldInvoiceNo.setValue(model.getItemInvoice().getInvoiceno());
//			fieldIdCustomer.setValue(model.getItemInvoice().getFcustomerBean().getCustno());
//			fieldNamaCustomer.setValue(model.getItemInvoice().getFcustomerBean().getCustname());
			
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumFractionDigits(0); //jumlah pecahan dibelakang koma
            nf.setMaximumFractionDigits(0); //jumlah pecahan dibelakang koma
            
			String strAmount = "0";
			try{
				strAmount = String.valueOf(nf.format(model.getItemInvoice().getAmountafterdiscafterppn()));
			} catch(Exception ex){}
			fieldAmount.setValue(strAmount);
			String strAmountPay = "0";
			try{
				strAmountPay = String.valueOf(nf.format(model.getItemInvoice().getAmountpay()));
			} catch(Exception ex){}
			fieldAmountPay.setValue(strAmountPay);
			
			tableHeader.setContainerDataSource(model.beanItemContainerHeader);
			
			//MAKE READONLY AGAIN
			fieldDivision.setReadOnly(true);
			fieldInvoiceNo.setReadOnly(true);
			fieldIdCustomer.setReadOnly(true);
			fieldNamaCustomer.setReadOnly(true);
			
			fieldAmount.setReadOnly(true);
			fieldAmountPay.setReadOnly(true);
			
		}	
				
		setTablePropertiesHeader();
		
		//3. Hitung Total & Jumlah Record dll
//		Collection items =  model.getTableJpaContainer().getItemIds();
//		tableHeader.setColumnFooter("id", "*Jumlah Record: " + items.size());
		
	}
	
	public void setDisplayDetail(){
		//HITUNG SUBTOTAL DETAIL
		tableDetail.setContainerDataSource(model.getBeanItemContainerDetail());
		
		setTablePropertiesDetail();
		//Hitung Jumlah
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(0);
		nf.setMinimumFractionDigits(0);
		
		double sumCashAmount = 0;
		double sumGiroAmount = 0;
		double sumTransferAmount = 0;
		double sumReturAmount = 0;
		double sumPotonganAmount = 0;
		
		Collection itemIds = model.getBeanItemContainerDetail().getItemIds();
		for (Object itemId: itemIds){
//			EntityItem<Arpaymentdetail> entityItem = model.getTableDetailJpaContainer().getItem(object);
			FtAppaymentd item = new FtAppaymentd();
			item = model.getBeanItemContainerDetail().getItem(itemId).getBean();
//			item = entityItem.getEntity();
			sumCashAmount += item.getCashamountpay();
			sumGiroAmount += item.getGiroamountpay();
			sumTransferAmount += item.getTransferamountpay();
			sumReturAmount += item.getMrvamountpay();
			sumPotonganAmount += item.getDcvamountpay();
			
		}
		
		tableDetail.setColumnFooter("id", "*Jumlah Record: " + itemIds.size());
		tableDetail.setColumnFooter("cashamountpay", nf.format(sumCashAmount));
		tableDetail.setColumnFooter("giroamountpay", nf.format(sumGiroAmount));
		tableDetail.setColumnFooter("transferamountpay", nf.format(sumTransferAmount));
		tableDetail.setColumnFooter("returamountpay", nf.format(sumReturAmount));
		tableDetail.setColumnFooter("potonganamount", nf.format(sumPotonganAmount));
		
	}

	private Window windowFormPembayaran = new Window();	
	
	private ApPaymentVendorPembayaranModel formModel = new ApPaymentVendorPembayaranModel();
	private ApPaymentVendorPembayaranView formView = new ApPaymentVendorPembayaranView(formModel);
	
	//FORM WINDOW PEMBAYARAN
	public void buildFormPembayaran(){
		//Create window
		windowFormPembayaran = new Window();
		windowFormPembayaran.setModal(true);
		windowFormPembayaran.center();
		windowFormPembayaran.setStyleName("login-layout");
		windowFormPembayaran.setWidth("800px");
		windowFormPembayaran.setHeight("600px");
		
		//INISIALISASI DATA
		FtPurchaseh invoice=new FtPurchaseh();
		invoice = model.getItemInvoice();
		FtAppaymentd detail=new FtAppaymentd();
		detail = model.getItemDetail();
		
		formModel = new ApPaymentVendorPembayaranModel(invoice, detail);
		if (model.getTanggalPembayaranManual() != null){
			formModel.setTanggalPembayaranManual(model.getTanggalPembayaranManual());
		}
		formModel.setAmountForThisDetail(detail.getSubtotalpay());
		formModel.setAmountForOtherDetail(model.getHitungAmountDetailAllFromDatabase());
		
		formView = new ApPaymentVendorPembayaranView(formModel);
		ApPaymentVendorPembayaranPresenter formPresenter = new ApPaymentVendorPembayaranPresenter(formModel, formView);		
		formView.setSizeFull();		
		
		windowFormPembayaran.setContent(formView);
		
		getUI().addWindow(windowFormPembayaran);
		
		
	}
	public void destroyFormPembayaran(){
		
//		getUI().removeWindow(windowFormPembayaran);
		windowFormPembayaran.close();
	}
	public ApPaymentVendorModel getModel() {
		return model;
	}
	public VerticalLayout getContent() {
		return content;
	}
	public Table getTableHeader() {
		return tableHeader;
	}
	public Table getTableDetail() {
		return tableDetail;
	}
	public Form getForm() {
		return form;
	}
	public FieldFactory getFieldFactory() {
		return fieldFactory;
	}
	public Class<FtArpaymenth> getEntityClass() {
		return entityClass;
	}
	public Button getBtnCommitHeader() {
		return btnCommitHeader;
	}
	public Button getBtnDiscardHeader() {
		return btnDiscardHeader;
	}
	public Button getBtnAddHeader() {
		return btnAddHeader;
	}
	public Button getBtnEditHeader() {
		return btnEditHeader;
	}
	public Button getBtnDeleteHeader() {
		return btnDeleteHeader;
	}
	public Button getBtnSearchHeader() {
		return btnSearchHeader;
	}
	public Button getBtnReloadHeader() {
		return btnReloadHeader;
	}
	public Button getBtnCommitDetail() {
		return btnCommitDetail;
	}
	public Button getBtnDiscardDetail() {
		return btnDiscardDetail;
	}
	public Button getBtnAddDetail() {
		return btnAddDetail;
	}
	public Button getBtnEditDetail() {
		return btnEditDetail;
	}
	public Button getBtnDeleteDetail() {
		return btnDeleteDetail;
	}
	public Button getBtnSearchDetail() {
		return btnSearchDetail;
	}
	public Button getBtnReloadDetail() {
		return btnReloadDetail;
	}
	public Object[] getFormPropertyIds() {
		return formPropertyIds;
	}
	public String getOperationStatus() {
		return operationStatus;
	}
	public TextField getFieldInvoiceNo() {
		return fieldInvoiceNo;
	}
	public TextField getFieldIdCustomer() {
		return fieldIdCustomer;
	}
	public TextField getFieldNamaCustomer() {
		return fieldNamaCustomer;
	}
	public TextField getFieldDivision() {
		return fieldDivision;
	}
	public TextField getFieldAmount() {
		return fieldAmount;
	}
	public TextField getFieldAmountPay() {
		return fieldAmountPay;
	}
	public Panel getPanelUtama() {
		return panelUtama;
	}
	public Panel getPanelTopHeader() {
		return panelTopHeader;
	}
	public Panel getPanelTableHeader() {
		return panelTableHeader;
	}
	public Panel getPanelTopDetail() {
		return panelTopDetail;
	}
	public Panel getPanelTableDetail() {
		return panelTableDetail;
	}
	public Window getWindowHeader() {
		return windowHeader;
	}
	public Window getWindowItem() {
		return windowItem;
	}
	public Window getWindowFormPembayaran() {
		return windowFormPembayaran;
	}
	public ApPaymentVendorPembayaranModel getFormModel() {
		return formModel;
	}
	public ApPaymentVendorPembayaranView getFormView() {
		return formView;
	}
	public void setModel(ApPaymentVendorModel model) {
		this.model = model;
	}
	public void setContent(VerticalLayout content) {
		this.content = content;
	}
	public void setTableHeader(Table tableHeader) {
		this.tableHeader = tableHeader;
	}
	public void setTableDetail(Table tableDetail) {
		this.tableDetail = tableDetail;
	}
	public void setForm(Form form) {
		this.form = form;
	}
	public void setFieldFactory(FieldFactory fieldFactory) {
		this.fieldFactory = fieldFactory;
	}
	public void setEntityClass(Class<FtArpaymenth> entityClass) {
		this.entityClass = entityClass;
	}
	public void setBtnCommitHeader(Button btnCommitHeader) {
		this.btnCommitHeader = btnCommitHeader;
	}
	public void setBtnDiscardHeader(Button btnDiscardHeader) {
		this.btnDiscardHeader = btnDiscardHeader;
	}
	public void setBtnAddHeader(Button btnAddHeader) {
		this.btnAddHeader = btnAddHeader;
	}
	public void setBtnEditHeader(Button btnEditHeader) {
		this.btnEditHeader = btnEditHeader;
	}
	public void setBtnDeleteHeader(Button btnDeleteHeader) {
		this.btnDeleteHeader = btnDeleteHeader;
	}
	public void setBtnSearchHeader(Button btnSearchHeader) {
		this.btnSearchHeader = btnSearchHeader;
	}
	public void setBtnReloadHeader(Button btnReloadHeader) {
		this.btnReloadHeader = btnReloadHeader;
	}
	public void setBtnCommitDetail(Button btnCommitDetail) {
		this.btnCommitDetail = btnCommitDetail;
	}
	public void setBtnDiscardDetail(Button btnDiscardDetail) {
		this.btnDiscardDetail = btnDiscardDetail;
	}
	public void setBtnAddDetail(Button btnAddDetail) {
		this.btnAddDetail = btnAddDetail;
	}
	public void setBtnEditDetail(Button btnEditDetail) {
		this.btnEditDetail = btnEditDetail;
	}
	public void setBtnDeleteDetail(Button btnDeleteDetail) {
		this.btnDeleteDetail = btnDeleteDetail;
	}
	public void setBtnSearchDetail(Button btnSearchDetail) {
		this.btnSearchDetail = btnSearchDetail;
	}
	public void setBtnReloadDetail(Button btnReloadDetail) {
		this.btnReloadDetail = btnReloadDetail;
	}
	public void setFormPropertyIds(Object[] formPropertyIds) {
		this.formPropertyIds = formPropertyIds;
	}
	public void setOperationStatus(String operationStatus) {
		this.operationStatus = operationStatus;
	}
	public void setFieldInvoiceNo(TextField fieldInvoiceNo) {
		this.fieldInvoiceNo = fieldInvoiceNo;
	}
	public void setFieldIdCustomer(TextField fieldIdCustomer) {
		this.fieldIdCustomer = fieldIdCustomer;
	}
	public void setFieldNamaCustomer(TextField fieldNamaCustomer) {
		this.fieldNamaCustomer = fieldNamaCustomer;
	}
	public void setFieldDivision(TextField fieldDivision) {
		this.fieldDivision = fieldDivision;
	}
	public void setFieldAmount(TextField fieldAmount) {
		this.fieldAmount = fieldAmount;
	}
	public void setFieldAmountPay(TextField fieldAmountPay) {
		this.fieldAmountPay = fieldAmountPay;
	}
	public void setPanelUtama(Panel panelUtama) {
		this.panelUtama = panelUtama;
	}
	public void setPanelTopHeader(Panel panelTopHeader) {
		this.panelTopHeader = panelTopHeader;
	}
	public void setPanelTableHeader(Panel panelTableHeader) {
		this.panelTableHeader = panelTableHeader;
	}
	public void setPanelTopDetail(Panel panelTopDetail) {
		this.panelTopDetail = panelTopDetail;
	}
	public void setPanelTableDetail(Panel panelTableDetail) {
		this.panelTableDetail = panelTableDetail;
	}
	public void setWindowHeader(Window windowHeader) {
		this.windowHeader = windowHeader;
	}
	public void setWindowItem(Window windowItem) {
		this.windowItem = windowItem;
	}
	public void setWindowFormPembayaran(Window windowFormPembayaran) {
		this.windowFormPembayaran = windowFormPembayaran;
	}
	public void setFormModel(ApPaymentVendorPembayaranModel formModel) {
		this.formModel = formModel;
	}
	public void setFormView(ApPaymentVendorPembayaranView formView) {
		this.formView = formView;
	}
	
	
}
