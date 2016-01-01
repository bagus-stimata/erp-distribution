package org.erp.distribution.master.salesman.areacovereditem;

import org.erp.distribution.model.FProduct;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.Action;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

public class AreaCoveredItemPresenter implements ClickListener, BlurListener, ValueChangeListener, Handler{
	private AreaCoveredItemModel model;
	private AreaCoveredItemView view;
	private AreaCoveredItemHelper helper;
	
	public AreaCoveredItemPresenter(AreaCoveredItemModel model, AreaCoveredItemView view){
		this.model = model;
		this.view = view;
		
		helper = new AreaCoveredItemHelper(model, view);
		
		initListener();
	}
	
	public void initListener(){
		view.getBtnAddAndSave().addClickListener(this);
		view.getBtnReset().addClickListener(this);
		view.getBtnClose().addClickListener(this);
		
		view.getComboArea().addBlurListener(this);
		view.getFieldKode().addBlurListener(this);
		view.getFieldNama().addBlurListener(this);
		view.getFieldAlamat().addBlurListener(this);
		
		ValueChangeListener comboAreaChangeListener = new ValueChangeListener() {
			
			@Override
			public void valueChange(ValueChangeEvent event) {
				helper.updateAndCalulateItemDetilProduct();
				helper.updateAndCalculateItemDetil();
			}
		};
		view.getComboArea().addValueChangeListener(comboAreaChangeListener);
		
	}
	
	
	public void initDisplay(){
		//SUDAH DIHANDLE OLEH VIEW
	}
	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton()==view.getBtnAddAndSave()){
			fixEntityBeforeUpdate();
		} else if (event.getButton()==view.getBtnReset()){
			resetItem();
		} else if (event.getButton()==view.getBtnClose()){
			
		}
		
	}
	@Override
	public void blur(BlurEvent event) {

		if (event.getComponent()==view.getComboArea()) {				
			helper.updateAndCalulateItemDetilProduct();
			helper.updateAndCalculateItemDetil();
			
		}else if (event.getComponent()==view.getFieldKode()){
			helper.updateAndCalculateItemDetil();
		}else if (event.getComponent()==view.getFieldNama()){
			helper.updateAndCalculateItemDetil();
		}else if (event.getComponent()==view.getFieldAlamat()){
			helper.updateAndCalculateItemDetil();
		}else if (event.getComponent()==view.getFieldKota()){
			helper.updateAndCalculateItemDetil();
		}
	}
	@Override
	public void valueChange(ValueChangeEvent event) {		
	}
	

	public void addAndSave(){
		
	}
	public void addItemDetil(){
		resetItem();
		view.bindAndBuildFieldGroupComponent();						
	}
	public void editItemdetil(){		
		view.bindAndBuildFieldGroupComponent();				
		view.getComboArea().setReadOnly(true);
		
	}
	
	public void resetItem(){

//		model.itemDetil.setFproductBean(new FProduct());
//		model.itemDetil.setFtopnamehBean(model.getItemHeader());
		
//		model.itemDetil.setVisible(true);
//		model.itemDetil.setQty(0);
//		model.itemDetil.setQty1(0);
//		model.itemDetil.setQty2(0);
//		model.itemDetil.setQty3(0);
		
		view.bindAndBuildFieldGroupComponent();		
	}
	
	public void selectForm(){		
	}

	public void fixEntityBeforeUpdate(){
		
//		model.ftOpnamedPK.setId(model.getItemHeader().getRefno());
//		model.ftOpnamedPK.setId(((FProduct) view.getComboProduct().getValue()).getId());
//		model.itemDetil.setId(model.ftOpnamedPK);
//		
//		model.itemDetil.setFproductBean((FProduct) view.getComboProduct().getValue());
		
	}

	private static final ShortcutAction ENTER_COMBOVENDOR= new ShortcutAction("Enter",
			KeyCode.ENTER, null);

	private static final ShortcutAction ENTER_FIELDKODE= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDKODE= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDKODE= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);
	private static final ShortcutAction ENTER_FIELDNAMA= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDNAMA= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDNAMA= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);
	private static final ShortcutAction ENTER_FIELDALAMAT= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDALAMAT= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDALAMAT= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);
	private static final ShortcutAction ENTER_FIELDKOTA= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDKOTA= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDKOTA= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);
	
	private static final Action[] ACTIONS = new Action[] {};
	private static final Action[] ACTIONS_PANELCOMBOVENDOR = new Action[] { ENTER_COMBOVENDOR };
	private static final Action[] ACTIONS_PANELFIELDKODE = new Action[] { ENTER_FIELDKODE, ARROW_DOWN_FIELDKODE, ARROW_UP_FIELDKODE };
	private static final Action[] ACTIONS_PANELFIELDNAMA = new Action[] { ENTER_FIELDNAMA, ARROW_DOWN_FIELDNAMA, ARROW_UP_FIELDNAMA };
	private static final Action[] ACTIONS_PANELFIELDALAMAT = new Action[] { ENTER_FIELDALAMAT, ARROW_DOWN_FIELDALAMAT, ARROW_UP_FIELDALAMAT};
	private static final Action[] ACTIONS_PANELFIELDKOTA = new Action[] { ENTER_FIELDKOTA, ARROW_DOWN_FIELDKOTA, ARROW_UP_FIELDKOTA};

	@Override
	public Action[] getActions(Object target, Object sender) {
		if (sender == view.getPanelComboArea()) {
			return ACTIONS_PANELCOMBOVENDOR;
		}else if (sender == view.getPanelKode()) {
			return ACTIONS_PANELFIELDKODE;			
		}else if (sender == view.getPanelNama()) {
			return ACTIONS_PANELFIELDNAMA;			
		}else if (sender == view.getPanelAlamat()) {
			return ACTIONS_PANELFIELDALAMAT;			
		}else if (sender == view.getPanelKota()) {
			return ACTIONS_PANELFIELDKOTA;			
		}
		return ACTIONS;
	}

	@Override
	public void handleAction(Action action, Object sender, Object target) {
		if (action==ENTER_COMBOVENDOR){
			view.getFieldKode().focus();

		}else if (action == ENTER_FIELDKODE || action == ARROW_DOWN_FIELDKODE) {
			view.getFieldNama().focus();
		}else if (action == ARROW_UP_FIELDKODE) {
			view.getComboArea().focus();			
		}else if (action == ENTER_FIELDNAMA || action == ARROW_DOWN_FIELDNAMA) {
			view.getFieldAlamat().focus();
		}else if (action == ARROW_UP_FIELDNAMA) {
			view.getFieldKode().focus();
			
		}else if (action == ENTER_FIELDALAMAT || action == ARROW_DOWN_FIELDALAMAT) {
			view.getFieldKota().focus();
		}else if (action == ARROW_UP_FIELDALAMAT ) {
			view.getFieldAlamat().focus();
			
		}else if (action == ENTER_FIELDKOTA || action==ARROW_DOWN_FIELDKOTA) {
			view.getBtnAddAndSave().focus();
		}else if (action == ARROW_UP_FIELDKOTA) {
			view.getFieldAlamat().focus();;
			
		}
		
	}





	
	
}	
