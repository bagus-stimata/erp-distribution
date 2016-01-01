package org.erp.distribution.master.producthargaalternatif.windowitem;

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

public class HargaAlternatifItemPresenter implements ClickListener, BlurListener, ValueChangeListener, Handler{
	private HargaAlternatifItemModel model;
	private HargaAlternatifItemView view;
	private HargaAlternatifItemHelper helper;
	
	public HargaAlternatifItemPresenter(HargaAlternatifItemModel model, HargaAlternatifItemView view){
		this.model = model;
		this.view = view;
		
		helper = new HargaAlternatifItemHelper(model, view);
		
		initListener();
	}
	
	public void initListener(){
		view.getBtnAddAndSave().addClickListener(this);
		view.getBtnReset().addClickListener(this);
		view.getBtnClose().addClickListener(this);
		
		//BLUR LISTENER
		view.getComboProduct().addBlurListener(this);		


		view.getFieldSprice().addBlurListener(this);
		view.getFieldSpriceafterppn().addBlurListener(this);
		view.getFieldSprice2().addBlurListener(this);
		view.getFieldSprice2afterppn().addBlurListener(this);
		
		view.getFieldSpricealt().addBlurListener(this);
		view.getFieldSpricealtafterppn().addBlurListener(this);
		view.getFieldSpricealt2().addBlurListener(this);
		view.getFieldSpricealt2afterppn().addBlurListener(this);
		//ACTION HANDLER LISTENER
		view.getPanelComboProduct().addActionHandler(this);
		
		view.getPanelFieldSprice().addActionHandler(this);
		view.getPanelFieldSpriceafterppn().addActionHandler(this);
		view.getPanelFieldSprice2().addActionHandler(this);
		view.getPanelFieldSprice2afterppn().addActionHandler(this);

		view.getPanelFieldSpricealt().addActionHandler(this);
		view.getPanelFieldSpricealtafterppn().addActionHandler(this);
		view.getPanelFieldSpricealt2().addActionHandler(this);
		view.getPanelFieldSpricealt2afterppn().addActionHandler(this);

		
		
		
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
		
		int convfact1 = (model.itemDetil.getFproductBean().getConvfact1()==null?1:model.itemDetil.getFproductBean().getConvfact1());
		
		if (event.getComponent()==view.getComboProduct()) {				
			helper.updateAndCalulateItemDetilProduct();
			helper.updateAndCalculateItemDetil();
			
		}else if (event.getComponent()==view.getFieldSprice()){
			helper.updateAndCalculateSPrice(model.getItemDetil().getSprice(), convfact1);
			
		}else if (event.getComponent()==view.getFieldSpriceafterppn()){
			helper.updateAndCalculateSPrice(helper.getPriceBeforePPN(model.getItemDetil().getSpriceafterppn()), convfact1);
			
		}else if (event.getComponent()==view.getFieldSprice2()){
			helper.updateAndCalculateSPrice(model.getItemDetil().getSprice2() * convfact1, convfact1);
			
		}else if (event.getComponent()==view.getFieldSprice2afterppn()){
			helper.updateAndCalculateSPrice(helper.getPriceBeforePPN(model.getItemDetil().getSprice2afterppn() * convfact1), convfact1);
			
		}else if (event.getComponent()==view.getFieldSpricealt()){
			helper.updateAndCalculateSPricealt(model.getItemDetil().getSpricealt(), convfact1);
		}else if (event.getComponent()==view.getFieldSpricealtafterppn()){
			helper.updateAndCalculateSPricealt(helper.getPriceBeforePPN(model.getItemDetil().getSpricealtafterppn()), convfact1);
			
		}else if (event.getComponent()==view.getFieldSpricealt2()){
			helper.updateAndCalculateSPricealt(model.getItemDetil().getSpricealt2() * convfact1, convfact1);
			
		}else if (event.getComponent()==view.getFieldSpricealt2afterppn()){
			helper.updateAndCalculateSPricealt(helper.getPriceBeforePPN(model.getItemDetil().getSpricealt2afterppn() * convfact1), convfact1);
			
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
		view.getComboProduct().setReadOnly(true);
		
	}
	
	public void resetItem(){

		model.itemDetil.setFproductBean(new FProduct());
		model.itemDetil.setFtpricealthBean(model.getItemHeader());

		model.itemDetil.setNourut(0);

		model.itemDetil.setSprice(0.0);
		model.itemDetil.setSprice2(0.0);
		model.itemDetil.setSprice2afterppn(0.0);		
		model.itemDetil.setSpriceafterppn(0.0);
		
		model.itemDetil.setSpricealt(0.0);
		model.itemDetil.setSpricealtafterppn(0.0);		
		model.itemDetil.setSpricealt2(0.0);
		model.itemDetil.setSpricealt2afterppn(0.0);		
		
		view.bindAndBuildFieldGroupComponent();		
	}
	
	public void selectForm(){
		
	}

	public void fixEntityBeforeUpdate(){
		
		model.itemDetilPK.setId(model.getItemHeader().getRefno());
		model.itemDetilPK.setId(((FProduct) view.getComboProduct().getValue()).getId());
		model.itemDetil.setId(model.itemDetilPK);
		
		model.itemDetil.setFproductBean((FProduct) view.getComboProduct().getValue());
		
	}

	private static final ShortcutAction ENTER_COMBOPRODUCT= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ENTER_FIELDSPRICE= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDSPRICE= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDSPRICE= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);

	private static final ShortcutAction ENTER_FIELDQTY1= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDQTY1= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDQTY1= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);
	private static final ShortcutAction ENTER_FIELDQTY2= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDQTY2= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDQTY2= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);
	private static final ShortcutAction ENTER_FIELDQTY3= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDQTY3= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDQTY3= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);
	private static final ShortcutAction ENTER_FIELDDISC1= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDDISC1= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDDISC1= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);
	private static final ShortcutAction ENTER_FIELDDISC2= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDDISC2= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDDISC2= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);
	private static final ShortcutAction ENTER_FIELDSUBTOTALAFTERDISCAFTERPPN= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDSUBTOTALAFTERDISCAFTERPPN= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDSUBTOTALAFTERDISCAFTERPPN= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);

	private static final ShortcutAction ENTER_FIELDSPRICEALT= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDSPRICEALT= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDSPRICEALT= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);
	private static final ShortcutAction ENTER_FIELDSPRICEALT_AFTERPPN= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDSPRICEALT_AFTERPPN= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDSPRICEALT_AFTERPPN= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);


	private static final ShortcutAction ENTER_FIELDSPRICEALT2= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDSPRICEALT2= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDSPRICEALT2= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);
	private static final ShortcutAction ENTER_FIELDSPRICEALT2_AFTERPPN= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDSPRICEALT2_AFTERPPN= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDSPRICEALT2_AFTERPPN= new ShortcutAction("Enter",
			KeyCode.ARROW_DOWN, null);


	
	private static final Action[] ACTIONS = new Action[] {};
	private static final Action[] ACTIONS_PANELCOMBOPRODUCT = new Action[] { ENTER_COMBOPRODUCT };
	private static final Action[] ACTIONS_PANELFIELDSPRICE = new Action[] { ENTER_FIELDSPRICE, ARROW_DOWN_FIELDSPRICE, ARROW_UP_FIELDSPRICE};
	
	private static final Action[] ACTIONS_PANELFIELDQTY1 = new Action[] { ENTER_FIELDQTY1, ARROW_DOWN_FIELDQTY1, ARROW_UP_FIELDQTY1  };
	private static final Action[] ACTIONS_PANELFIELDQTY2 = new Action[] { ENTER_FIELDQTY2, ARROW_DOWN_FIELDQTY2, ARROW_UP_FIELDQTY2};
	private static final Action[] ACTIONS_PANELFIELDQTY3 = new Action[] { ENTER_FIELDQTY3, ARROW_DOWN_FIELDQTY3, ARROW_UP_FIELDQTY3};
	private static final Action[] ACTIONS_PANELFIELDDISC1 = new Action[] { ENTER_FIELDDISC1, ARROW_DOWN_FIELDDISC1, ARROW_UP_FIELDDISC1};
	private static final Action[] ACTIONS_PANELFIELDDISC2 = new Action[] { ENTER_FIELDDISC2, ARROW_DOWN_FIELDDISC2, ARROW_UP_FIELDDISC2};
	private static final Action[] ACTIONS_PANELFIELDSUBTOTALAFTERDISCAFTERPPN = new Action[] { ENTER_FIELDSUBTOTALAFTERDISCAFTERPPN, 
		ARROW_DOWN_FIELDSUBTOTALAFTERDISCAFTERPPN, ARROW_UP_FIELDSUBTOTALAFTERDISCAFTERPPN};

	private static final Action[] ACTIONS_PANELFIELDSPRICEALT = new Action[] { ENTER_FIELDSPRICEALT, ARROW_DOWN_FIELDSPRICEALT, ARROW_UP_FIELDSPRICEALT};
	private static final Action[] ACTIONS_PANELFIELDSPRICEALT_AFTERPPN = new Action[] { ENTER_FIELDSPRICEALT_AFTERPPN, ARROW_DOWN_FIELDSPRICEALT_AFTERPPN, ARROW_UP_FIELDSPRICEALT_AFTERPPN};
	private static final Action[] ACTIONS_PANELFIELDSPRICEALT2 = new Action[] { ENTER_FIELDSPRICEALT2, ARROW_DOWN_FIELDSPRICEALT2, ARROW_UP_FIELDSPRICEALT2};
	private static final Action[] ACTIONS_PANELFIELDSPRICEALT2_AFTERPPN = new Action[] { ENTER_FIELDSPRICEALT2_AFTERPPN, ARROW_DOWN_FIELDSPRICEALT2_AFTERPPN, ARROW_UP_FIELDSPRICEALT2_AFTERPPN};
	
	@Override
	public Action[] getActions(Object target, Object sender) {
		if (sender == view.getPanelComboProduct()) {
			return ACTIONS_PANELCOMBOPRODUCT;
		}else if (sender == view.getPanelFieldSprice()) {
			return ACTIONS_PANELFIELDSPRICE;			
		}else if (sender == view.getPanelQty1()) {
			return ACTIONS_PANELFIELDQTY1;			
		}else if (sender == view.getPanelQty2()) {
			return ACTIONS_PANELFIELDQTY2;			
		}else if (sender == view.getPanelQty3()) {
			return ACTIONS_PANELFIELDQTY3;			
		}else if (sender == view.getPanelDisc1()) {
			return ACTIONS_PANELFIELDDISC1;			
		}else if (sender == view.getPanelDisc2()) {
			return ACTIONS_PANELFIELDDISC2;			
		}else if (sender == view.getPanelSubtotalafterdiscafterppn()) {
			return ACTIONS_PANELFIELDSUBTOTALAFTERDISCAFTERPPN;			
		}else if (sender == view.getPanelFieldSpricealt()) {
			return ACTIONS_PANELFIELDSPRICEALT;			
		}else if (sender == view.getPanelFieldSpricealtafterppn()) {
			return ACTIONS_PANELFIELDSPRICEALT_AFTERPPN;			
		}else if (sender == view.getPanelFieldSpricealt2()) {
			return ACTIONS_PANELFIELDSPRICEALT2;			
		}else if (sender == view.getPanelFieldSpricealt2afterppn()) {
			return ACTIONS_PANELFIELDSPRICEALT2_AFTERPPN;			
		}
		return ACTIONS;
	}

	@Override
	public void handleAction(Action action, Object sender, Object target) {
		if (action==ENTER_COMBOPRODUCT){
			view.getFieldQty1().focus();

		}else if (action == ENTER_FIELDSPRICE || action == ARROW_DOWN_FIELDSPRICE) {
			view.getFieldQty1().focus();
		}else if (action == ARROW_UP_FIELDSPRICE) {
			view.getComboProduct().focus();			
		}else if (action == ENTER_FIELDQTY1 || action == ARROW_DOWN_FIELDQTY1) {
			view.getFieldQty2().focus();
		}else if (action == ARROW_UP_FIELDQTY1) {
			view.getComboProduct().focus();
			
			
		}else if (action == ENTER_FIELDQTY2 || action == ARROW_DOWN_FIELDQTY2) {
			view.getFieldQty3().focus();
		}else if (action == ARROW_UP_FIELDQTY2) {
			view.getFieldQty1().focus();;
			
		}else if (action == ENTER_FIELDQTY3 || action==ARROW_DOWN_FIELDQTY3) {
			view.getFieldSubtotalafterdisc().focus();
		}else if (action == ARROW_UP_FIELDQTY3) {
			view.getFieldQty2().focus();;
			
		}else if (action == ENTER_FIELDDISC1 || action==ARROW_DOWN_FIELDDISC1) {
			view.getFieldDisc2().focus();
		}else if (action == ARROW_UP_FIELDDISC1) {
			view.getFieldQty3().focus();;
			
		}else if (action == ENTER_FIELDDISC2 || action==ARROW_DOWN_FIELDDISC2) {
			view.getFieldSubtotalafterdisc().focus();
		}else if (action == ARROW_UP_FIELDDISC2) {
			view.getFieldDisc1().focus();;
			
		}else if (action == ENTER_FIELDSPRICEALT) {
			view.getFieldSpricealtafterppn().focus();
		}else if (action == ARROW_UP_FIELDSPRICEALT) {
			view.getComboProduct().focus();
		}else if (action == ARROW_DOWN_FIELDSPRICEALT) {
			view.getFieldSpricealtafterppn().focus();
			
		}else if (action == ENTER_FIELDSPRICEALT_AFTERPPN) {
			view.getFieldSpricealt2().focus();
		}else if (action == ARROW_UP_FIELDSPRICEALT_AFTERPPN) {
			view.getFieldSpricealt().focus();
		}else if (action == ARROW_DOWN_FIELDSPRICEALT_AFTERPPN) {
			view.getFieldSpricealt2().focus();

		}else if (action == ENTER_FIELDSPRICEALT2) {
			view.getFieldSpricealt2afterppn().focus();
		}else if (action == ARROW_UP_FIELDSPRICEALT2) {
			view.getFieldSpricealtafterppn().focus();
		}else if (action == ARROW_DOWN_FIELDSPRICEALT2) {
			view.getFieldSpricealt2afterppn().focus();

		}else if (action == ENTER_FIELDSPRICEALT2_AFTERPPN) {
			view.getBtnAddAndSave().focus();
		}else if (action == ARROW_UP_FIELDSPRICEALT2_AFTERPPN) {
			view.getFieldSpricealt2().focus();
		}else if (action == ARROW_DOWN_FIELDSPRICEALT2_AFTERPPN) {
			view.getBtnAddAndSave().focus();
			
		}
		
	}





	
	
}	
