package org.erp.distribution.proses.akhirhari;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.Reindeer;

public class ProsesAkhirHariView extends CustomComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProsesAkhirHariModel model;
	
	private VerticalLayout content = new VerticalLayout();
	
	private Label labelTanggalTransaksiDivisi = new Label("(TGL TRANS BERJALAN: ");
	private ComboBox comboDivision = new ComboBox("DIVISION");
	private Button btnProsesAkhirHari = new Button("PROSES");
	
	private DateField dateRekalkulasiFrom = new DateField("Rekalkulasi Mulai");
	private DateField dateRekalkulasiTo = new DateField("S.D");
	
	//Panel
	private Panel panelUtama;
	private Panel panelTop;
	private Panel panelTabel;
	private Panel panelForm;
	
	public ProsesAkhirHariView(ProsesAkhirHariModel model){
		this.model = model;
		initComponent();
		buildView();
		initFirstState();
		setDisplay();
		
	}
	public void initComponent(){
		
		content.setSizeFull();
		content.setMargin(true);
		//Inisialisasi Panel 
		setSizeFull();
		panelUtama = new Panel(getCaption());
		panelUtama.setSizeFull();

		panelTop = new Panel();
		panelTabel = new Panel();
		panelTabel.setSizeFull();
		
		labelTanggalTransaksiDivisi.setContentMode(ContentMode.HTML);
		labelTanggalTransaksiDivisi.setValue("<h3><font color='BLUE'> (TRANSAKSI)</font></h3>");
		
		btnProsesAkhirHari.setIcon(new ThemeResource("../images/navigation/16x16/Sync.png") );
		
		
		Date trDate = model.getTransaksiHelper().getCurrentTransDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(trDate);
		
		Calendar calAfterNow = Calendar.getInstance();
		calAfterNow.setTime(trDate);
		calAfterNow.add(Calendar.DATE, 2);
		
		dateRekalkulasiFrom.setDateFormat("dd/MM/yyyy");
		dateRekalkulasiTo.setDateFormat("dd/MM/yyyy");
		dateRekalkulasiFrom.setValue(cal.getTime());
		dateRekalkulasiTo.setValue(calAfterNow.getTime());
//		dateRekalkulasiFrom.setWidth("100px");
//		dateRekalkulasiTo.setWidth("100px");
		dateRekalkulasiFrom.setEnabled(false);
	}
	public void buildView(){
		VerticalLayout content = new VerticalLayout();
		content.setMargin(true);
		content.setSizeFull();
		TabSheet tabSheet = new TabSheet();
		tabSheet.setSizeFull();
		
		
		VerticalLayout layoutTopProsesAkhirHari = new VerticalLayout();	
		layoutTopProsesAkhirHari.setMargin(true);
		
		
		HorizontalLayout layoutTop1 = new HorizontalLayout();		
		layoutTop1.setMargin(true);
		layoutTop1.setSpacing(true);
		HorizontalLayout layoutTop2 = new HorizontalLayout();		
		layoutTop2.setMargin(true);
		layoutTop2.setSpacing(true);
		HorizontalLayout layoutTop3 = new HorizontalLayout();		
		layoutTop3.setMargin(true);
		layoutTop3.setSpacing(true);
		
		Panel panelTop1 = new Panel("TANGGAL TRANSAKSI BERJALAN");
		panelTop1.setContent(layoutTop1);
		layoutTopProsesAkhirHari.addComponent(panelTop1);
		
		Panel panelTop2 = new Panel("Kriteria");
		panelTop2.setContent(layoutTop2);
		layoutTopProsesAkhirHari.addComponent(panelTop2);
		
		layoutTopProsesAkhirHari.addComponent(layoutTop3);
		
		tabSheet.addTab(layoutTopProsesAkhirHari, "PROSES AKHIR HARI", null);
//		tabSheet.addTab(layoutTopPelunasan, "Pelunasan Report", null);
		
		content.addComponent(tabSheet);
		
		setCompositionRoot(content);	
		
		layoutTop1.addComponent(labelTanggalTransaksiDivisi);	

		
		layoutTop2.setCaption("Kriteria Tanggal Rekalkulasi Stok");
		layoutTop2.addComponent(dateRekalkulasiFrom);
		layoutTop2.addComponent(dateRekalkulasiTo);
		
		layoutTop3.addComponent(btnProsesAkhirHari);
		
		
		
		
	}
	public void initFirstState(){
	}
	
	public void setDisplay(){
		bindAndBuildFieldGroupComponent();
	}

	public void bindAndBuildFieldGroupComponent(){
//		comboDivision.setContainerDataSource(model.getBeanItemContainerDivision());
		
		
//		model.getBinderHeader().setItemDataSource(model.getItemHeader());
		
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ProsesAkhirHariModel getModel() {
		return model;
	}
	public VerticalLayout getContent() {
		return content;
	}
	public Label getLabelTanggalTransaksiDivisi() {
		return labelTanggalTransaksiDivisi;
	}
	public ComboBox getComboDivision() {
		return comboDivision;
	}
	public Panel getPanelUtama() {
		return panelUtama;
	}
	public Panel getPanelTop() {
		return panelTop;
	}
	public Panel getPanelTabel() {
		return panelTabel;
	}
	public Panel getPanelForm() {
		return panelForm;
	}
	public void setModel(ProsesAkhirHariModel model) {
		this.model = model;
	}
	public void setContent(VerticalLayout content) {
		this.content = content;
	}
	public void setLabelTanggalTransaksiDivisi(Label labelTanggalTransaksiDivisi) {
		this.labelTanggalTransaksiDivisi = labelTanggalTransaksiDivisi;
	}
	public void setComboDivision(ComboBox comboDivision) {
		this.comboDivision = comboDivision;
	}
	public void setPanelUtama(Panel panelUtama) {
		this.panelUtama = panelUtama;
	}
	public void setPanelTop(Panel panelTop) {
		this.panelTop = panelTop;
	}
	public void setPanelTabel(Panel panelTabel) {
		this.panelTabel = panelTabel;
	}
	public void setPanelForm(Panel panelForm) {
		this.panelForm = panelForm;
	}
	public Button getBtnProsesAkhirHari() {
		return btnProsesAkhirHari;
	}
	public void setBtnProsesAkhirHari(Button btnProsesAkhirHari) {
		this.btnProsesAkhirHari = btnProsesAkhirHari;
	}
	public DateField getDateRekalkulasiFrom() {
		return dateRekalkulasiFrom;
	}
	public DateField getDateRekalkulasiTo() {
		return dateRekalkulasiTo;
	}
	public void setDateRekalkulasiFrom(DateField dateRekalkulasiFrom) {
		this.dateRekalkulasiFrom = dateRekalkulasiFrom;
	}
	public void setDateRekalkulasiTo(DateField dateRekalkulasiTo) {
		this.dateRekalkulasiTo = dateRekalkulasiTo;
	}
	
	
}
