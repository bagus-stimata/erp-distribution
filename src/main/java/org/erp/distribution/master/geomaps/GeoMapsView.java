package org.erp.distribution.master.geomaps;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
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

public class GeoMapsView extends CustomComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GeoMapsModel model;
	
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
	
	public GeoMapsView(GeoMapsModel model){
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
		
		setCompositionRoot(content);	
		

		GoogleMap googleMap = new GoogleMap("AIzaSyBKZ_U8aTm4IiTFOBZflSDJSIqllFlsk-M", null, "english");
		googleMap.setSizeFull();
		googleMap.addMarker("DRAGGABLE: Paavo Nurmi Stadion", new LatLon(
		        60.442423, 22.26044), true, "VAADIN/1377279006_stadium.png");
		
//		kakolaMarker = googleMap.addMarker("DRAGGABLE: Kakolan vankila",
//		        new LatLon(60.44291, 22.242415), true, null);
		
		googleMap.addMarker("NOT DRAGGABLE: Iso-Heikkilä", new LatLon(
		        60.450403, 22.230399), false, null);
		googleMap.setMinZoom(4);
		googleMap.setMaxZoom(16);		
		
		content.addComponent(googleMap);
		
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
	public GeoMapsModel getModel() {
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
	public void setModel(GeoMapsModel model) {
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
