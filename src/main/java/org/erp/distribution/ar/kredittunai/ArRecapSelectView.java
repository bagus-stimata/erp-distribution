package org.erp.distribution.ar.kredittunai;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.erp.distribution.model.FtSalesh;

import com.vaadin.data.Property;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Table.Align;
import com.vaadin.ui.themes.Reindeer;

public class ArRecapSelectView extends CustomComponent {
	private ArRecapSelectModel model;
	
	private VerticalLayout content = new VerticalLayout();
	
	private Table table;
	
	private TextField fieldSearchById = new TextField("NO. RECAP");
	private ComboBox comboSearchByDivision = new ComboBox("DIVISION");
	private DateField dateFieldSearchByTransdateFrom = new DateField("TGL TRANS FROM");
	private DateField dateFieldSearchByTransdateTo = new DateField("TO");
//	private TextField fieldSearchByTemplateName = new TextField("Template Name");
	private Button btnSearch = new Button("Search");
	private Button btnSelect = new Button("Select/Pilih");
	
	private Button btnSeparator1 = new Button(".");
	private Button btnSeparator2 = new Button(".");

	//LAYOUT
	private FormLayout formLayout = new FormLayout();
	
	//Panel
	private Panel panelUtama = new Panel();
	private Panel panelTop = new Panel();
	private Panel panelTabel = new Panel();
	private Panel panelForm = new Panel();

	//Help Manager	
	
	public ArRecapSelectView(ArRecapSelectModel model){
		this.model = model;
		initComponent();
		buildView();
		
		setDisplay();	
		
	}
	public void initComponent(){
		table = new Table() {
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
			        		return "CLOSE";
			        	} else {
			        		return "-";
			        	}
			        }
		        } catch(Exception ex){}
		        
		        return super.formatPropertyValue(rowId, colId, property);
		    }
		};		
                
 		//Inisialisasi Panel 
		setSizeFull();                
		content.setSizeFull();
                content.setMargin(true);
                
		//INIT COMPONENT TENGAH
		table.setSizeFull();
		table.setSelectable(true);
		table.setImmediate(true);
		table.setBuffered(false);
		table.setFooterVisible(true);
               
		//INIT COMPONENT ATAS
		btnSeparator1.setEnabled(false);
		btnSeparator2.setEnabled(false);
		
//		fieldSearchByRekap = new TextField("NO. REKAP");
		fieldSearchById = new TextField("NO. REKAP");
                fieldSearchById.setInputPrompt("NO. REKAP");
		fieldSearchById.setStyleName(Reindeer.TEXTFIELD_SMALL);
		fieldSearchById.setWidth("120px");
		
//		fieldSearchByInvoice = new TextField("INVOICE");
		comboSearchByDivision = new ComboBox("DIVISION");
                comboSearchByDivision.setInputPrompt("DIVISION");
		comboSearchByDivision.setStyleName(Reindeer.TEXTFIELD_SMALL);
		comboSearchByDivision.setWidth("120px");
                
		dateFieldSearchByTransdateFrom = new DateField("INV FROM");
		dateFieldSearchByTransdateFrom.setStyleName(Reindeer.TEXTFIELD_SMALL);
		dateFieldSearchByTransdateFrom.setWidth("120px");

                dateFieldSearchByTransdateTo = new DateField("INV TO");
		dateFieldSearchByTransdateTo.setStyleName(Reindeer.TEXTFIELD_SMALL);
		dateFieldSearchByTransdateTo.setWidth("120px");
                
                btnSearch.setStyleName(Reindeer.BUTTON_SMALL);
                
                btnSearch.setIcon(new ThemeResource("images/navigation/12x12/Find.png") );
                btnSelect.setIcon(new ThemeResource("images/navigation/16x16/OK.png") );
               
		
	}
	
	public void buildView(){
                TabSheet tabSheet = new TabSheet();
                tabSheet.setSizeFull();
                VerticalLayout layoutUtama = new VerticalLayout();
                layoutUtama.setSizeFull();
                layoutUtama.setMargin(true);
                
		//LAYOUT TOP
		VerticalLayout layoutTop = new VerticalLayout();
                layoutTop.setMargin(true);
		HorizontalLayout layoutTopInner1 = new HorizontalLayout();	
		HorizontalLayout layoutTopInner2 = new HorizontalLayout();					
		layoutTop.addComponent(layoutTopInner1);
		layoutTop.addComponent(layoutTopInner2);
                
                //LAYOUT TABLE
		VerticalLayout layoutTable = new VerticalLayout();
		layoutTable.setSizeFull();
                layoutTable.addComponent(table);
                table.setSizeFull();
                
                //LAYOUT BOTTOM
		VerticalLayout layoutBottom = new VerticalLayout();
		HorizontalLayout layoutFooter1 = new HorizontalLayout();
		HorizontalLayout layoutFooter2 = new HorizontalLayout();
		layoutBottom.addComponent(layoutFooter1);
		layoutBottom.addComponent(layoutFooter2);
		
                //LAYOUT UTAMA
                layoutUtama.addComponent(layoutTop);
                layoutUtama.addComponent(layoutTable);
                layoutUtama.addComponent(layoutBottom);

                tabSheet.addComponent(layoutUtama);
                content.addComponent(tabSheet);
                setCompositionRoot(content);
                
                //Extended Konfigurasi Size
                layoutUtama.setExpandRatio(layoutTable, 1);
		
		layoutTopInner1.addComponent(fieldSearchById);
		layoutTopInner1.setComponentAlignment(fieldSearchById, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(comboSearchByDivision);
		layoutTopInner1.setComponentAlignment(comboSearchByDivision, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(dateFieldSearchByTransdateFrom);
		layoutTopInner1.setComponentAlignment(dateFieldSearchByTransdateFrom, Alignment.BOTTOM_CENTER);
		layoutTopInner1.addComponent(dateFieldSearchByTransdateTo);
		layoutTopInner1.setComponentAlignment(dateFieldSearchByTransdateTo, Alignment.BOTTOM_CENTER);
		
		layoutTopInner1.addComponent(btnSearch);
		layoutTopInner1.setComponentAlignment(btnSearch, Alignment.BOTTOM_CENTER);
		
                layoutBottom.addComponent(btnSelect);
		
		//init
		formLayout.setVisible(true);
		
		
	}
	
	public void setDisplay(){
		//TABLE
		table.setContainerDataSource(model.getBeanItemContainerItemHeader());
		
		setTableProperties();
		
		setDisplayTableFooter();
		
		bindAndBuildFieldGroupComponent();
	}
	public void setDisplayTableFooter(){
		Collection itemIds =  model.getBeanItemContainerItemHeader().getItemIds();
		int selectedRow = 0;
		for (Object itemId: itemIds){
			FtSalesh itemArinvoice = new FtSalesh();
			itemArinvoice = model.getBeanItemContainerItemHeader().getItem(itemId).getBean();
			if (itemArinvoice.getSelected().getValue()==true){
				selectedRow++;
			}
		}
		
		table.setColumnFooter("recapno", "*Jumlah Record: " + itemIds.size());
		
	}

	public void bindAndBuildFieldGroupComponent(){
		comboSearchByDivision.setContainerDataSource(model.getBeanItemContainerDivision());
	}
	
	public void setVisibleTableProperties(Object... tablePropertyIds) {
		table.setVisibleColumns(tablePropertyIds);		
	}
	public void setTableProperties(){

		setVisibleTableProperties("selected", "recapno", "divisionBean", "invoicedate");
		
//		table.setColumnCollapsingAllowed(true);
//		try{
//			table.setColumnCollapsed("state", true);
//			table.setColumnCollapsed("birthDate", true);
//			table.setColumnCollapsed("joinDate", true);
//			
//		} catch(Exception ex){}
		
		//ALIGNMENT
		table.setColumnAlignment("id", Align.CENTER);
		table.setColumnAlignment("invoicedate", Align.CENTER);
		table.setColumnAlignment("closing", Align.CENTER);
		table.setColumnAlignment("entrydate", Align.CENTER);
		
		//SET HEADER
		table.setColumnHeader("selected", "<input type='checkbox'/>");
		table.setColumnHeader("id", "DIV-REFNO");
		table.setColumnHeader("divisionBean", "DIVISI");
		table.setColumnHeader("invoicedate", "RECAP/INV. DATE");
		table.setColumnHeader("recapno", "NO. REKAP");
		
//		table.setColumnExpandRatio("selected", 2);
//		table.setColumnExpandRatio("recapno", 3);
				
	}
	
	
	public void focustIdOrDesc(){
		
	}
	public ArRecapSelectModel getModel() {
		return model;
	}
	public VerticalLayout getContent() {
		return content;
	}
	public Table getTable() {
		return table;
	}
	public TextField getFieldSearchById() {
		return fieldSearchById;
	}
	public ComboBox getComboSearchByDivision() {
		return comboSearchByDivision;
	}
	public DateField getDateFieldSearchByTransdateFrom() {
		return dateFieldSearchByTransdateFrom;
	}
	public DateField getDateFieldSearchByTransdateTo() {
		return dateFieldSearchByTransdateTo;
	}
	public Button getBtnSearch() {
		return btnSearch;
	}
	public Button getBtnSelect() {
		return btnSelect;
	}
	public Button getBtnSeparator1() {
		return btnSeparator1;
	}
	public Button getBtnSeparator2() {
		return btnSeparator2;
	}
	public FormLayout getFormLayout() {
		return formLayout;
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
	public void setModel(ArRecapSelectModel model) {
		this.model = model;
	}
	public void setContent(VerticalLayout content) {
		this.content = content;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public void setFieldSearchById(TextField fieldSearchById) {
		this.fieldSearchById = fieldSearchById;
	}
	public void setComboSearchByDivision(ComboBox comboSearchByDivision) {
		this.comboSearchByDivision = comboSearchByDivision;
	}
	public void setDateFieldSearchByTransdateFrom(
			DateField dateFieldSearchByTransdateFrom) {
		this.dateFieldSearchByTransdateFrom = dateFieldSearchByTransdateFrom;
	}
	public void setDateFieldSearchByTransdateTo(
			DateField dateFieldSearchByTransdateTo) {
		this.dateFieldSearchByTransdateTo = dateFieldSearchByTransdateTo;
	}
	public void setBtnSearch(Button btnSearch) {
		this.btnSearch = btnSearch;
	}
	public void setBtnSelect(Button btnSelect) {
		this.btnSelect = btnSelect;
	}
	public void setBtnSeparator1(Button btnSeparator1) {
		this.btnSeparator1 = btnSeparator1;
	}
	public void setBtnSeparator2(Button btnSeparator2) {
		this.btnSeparator2 = btnSeparator2;
	}
	public void setFormLayout(FormLayout formLayout) {
		this.formLayout = formLayout;
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
	


	
}
