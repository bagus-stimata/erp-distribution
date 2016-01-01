package org.erp.distribution.salesorder.salesorder.retur.windowitem;

import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FtSalesdPK;

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

public class SalesOrderReturItemPresenter implements ClickListener, BlurListener, ValueChangeListener, Handler{
	private SalesOrderReturItemModel model;
	private SalesOrderReturItemView view;
	private SalesOrderReturItemHelper helper;
	
	public SalesOrderReturItemPresenter(SalesOrderReturItemModel model, SalesOrderReturItemView view){
		this.model = model;
		this.view = view;
		
		helper = new SalesOrderReturItemHelper(model, view);
		
		initListener();
	}
	
	public void initListener(){
		view.getBtnAddAndSave().addClickListener(this);
		view.getBtnReset().addClickListener(this);
		view.getBtnClose().addClickListener(this);
		
		view.getComboProduct().addBlurListener(this);
		view.getFieldSprice().addBlurListener(this);
		view.getFieldSpriceafterppn().addBlurListener(this);
		view.getFieldQty1().addBlurListener(this);
		view.getFieldQty2().addBlurListener(this);
		view.getFieldQty3().addBlurListener(this);
		view.getFieldQty().addBlurListener(this);
		view.getFieldDisc1().addBlurListener(this);
		view.getFieldDisc1rp().addBlurListener(this);
		view.getFieldDisc2().addBlurListener(this);
		view.getFieldDisc2rp().addBlurListener(this);
		view.getFieldSubtotal().addBlurListener(this);
		view.getFieldSubtotalafterppn().addBlurListener(this);
		
		view.getPanelComboProduct().addActionHandler(this);
		view.getPanelFieldSprice().addActionHandler(this);
		view.getPanelFieldSpriceafterppn().addActionHandler(this);
		view.getPanelQty1().addActionHandler(this);
		view.getPanelQty2().addActionHandler(this);
		view.getPanelQty3().addActionHandler(this);
		
		view.getPanelDisc1().addActionHandler(this);
		view.getPanelDisc2().addActionHandler(this);
		
		view.getPanelSubtotalafterdiscafterppn().addActionHandler(this);
		
	
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

		if (event.getComponent()==view.getComboProduct()) {				
			helper.updateAndCalulateItemDetilProduct();
			helper.updateAndCalculateItemDetil();
		}else if (event.getComponent()==view.getFieldSprice()){
			helper.updateAndCalculateItemDetil();
			if (Integer.parseInt(view.getFieldQty1().getValue())<=0){
				view.getFieldQty1().setValue("");			
			}
		}else if (event.getComponent()==view.getFieldSpriceafterppn()){
			helper.updateAndCalculateItemDetil();
			if (Integer.parseInt(view.getFieldQty1().getValue())<=0){
				view.getFieldQty1().setValue("");			
			}
		}else if (event.getComponent()==view.getFieldQty1()){
			helper.updateAndCalculateItemDetil();
			if (Integer.parseInt(view.getFieldQty2().getValue())<=0){
				view.getFieldQty2().setValue("");			
			}
		}else if (event.getComponent()==view.getFieldQty2()){
			helper.updateAndCalculateItemDetil();
			if (Integer.parseInt(view.getFieldQty3().getValue())<=0){
				view.getFieldQty3().setValue("");			
			}
		}else if (event.getComponent()==view.getFieldQty3()){
			helper.updateAndCalculateItemDetil();
		}else if (event.getComponent()==view.getFieldQty()){
			helper.updateAndCalculateItemDetil();
		}else if (event.getComponent()==view.getFieldDisc1()){
			helper.updateAndCalculateItemDetil();
		}else if (event.getComponent()==view.getFieldDisc1rp()){
			helper.updateAndCalculateItemDetil();
		}else if (event.getComponent()==view.getFieldDisc2()){
			helper.updateAndCalculateItemDetil();
		}else if (event.getComponent()==view.getFieldDisc2rp()){
			helper.updateAndCalculateItemDetil();
		}else if (event.getComponent()==view.getFieldSubtotal()){
			helper.updateAndCalculateItemDetil();
		}else if (event.getComponent()==view.getFieldSubtotalafterppn()){
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
		view.getComboProduct().setReadOnly(true);
		
	}
	
	public void resetItem(){
		FtSalesdPK id = new FtSalesdPK();
		id.setId(model.itemDetil.getId().getId());

		id.setRefno(model.getItemDetil().getId().getRefno());
		//## KADANG NULL ##
		id.setRefno(model.getItemDetil().getId().getRefno());
		if (id.getRefno()==null) {
			id.setRefno(model.getItemHeader().getRefno());
		}
		if (id.getRefno()==null){
			id.setRefno((long) 0);
		}
		
		id.setFreegood(false);
		model.itemDetil.setId(id);
		model.itemDetil.setPromo(false);
		
		
		model.itemDetil.setFproductBean(new FProduct());
		model.itemDetil.setFtsaleshBean(model.getItemHeader());
		
		model.itemDetil.setDisc1(0.0);
		model.itemDetil.setDisc1rp(0.0);
		model.itemDetil.setDisc2(0.0);
		model.itemDetil.setDisc2rp(0.0);
		
		model.itemDetil.setNourut(0);
		model.itemDetil.setSprice(0.0);
		model.itemDetil.setSpriceafterppn(0.0);
		model.itemDetil.setQty(0);
		model.itemDetil.setQty1(0);
		model.itemDetil.setQty2(0);
		model.itemDetil.setQty3(0);
		model.itemDetil.setSubtotal(0.0);
		model.itemDetil.setSubtotalafterppn(0.0);
		
		view.bindAndBuildFieldGroupComponent();		
	}
	
	public void selectForm(){
		
	}

	public void fixEntityBeforeUpdate(){
		model.ftPK.setId(model.getItemHeader().getRefno());
		model.ftPK.setId(((FProduct) view.getComboProduct().getValue()).getId());
		model.ftPK.setFreegood(view.getCheckFreegood().getValue());
		model.itemDetil.setId(model.ftPK);
		
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
	private static final ShortcutAction ENTER_FIELDSPRICE_AFTERPPN= new ShortcutAction("Enter",
			KeyCode.ENTER, null);
	private static final ShortcutAction ARROW_UP_FIELDSPRICE_AFTERPPN= new ShortcutAction("Enter",
			KeyCode.ARROW_UP, null);
	private static final ShortcutAction ARROW_DOWN_FIELDSPRICE_AFTERPPN= new ShortcutAction("Enter",
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
	
	private static final Action[] ACTIONS = new Action[] {};
	private static final Action[] ACTIONS_PANELCOMBOPRODUCT = new Action[] { ENTER_COMBOPRODUCT };
	private static final Action[] ACTIONS_PANELFIELDSPRICE = new Action[] { ENTER_FIELDSPRICE, ARROW_DOWN_FIELDSPRICE, ARROW_UP_FIELDSPRICE};
	private static final Action[] ACTIONS_PANELFIELDSPRICE_AFTERPPN = new Action[] { ENTER_FIELDSPRICE_AFTERPPN, ARROW_DOWN_FIELDSPRICE_AFTERPPN, ARROW_UP_FIELDSPRICE_AFTERPPN};
	private static final Action[] ACTIONS_PANELFIELDQTY1 = new Action[] { ENTER_FIELDQTY1, ARROW_DOWN_FIELDQTY1, ARROW_UP_FIELDQTY1  };
	private static final Action[] ACTIONS_PANELFIELDQTY2 = new Action[] { ENTER_FIELDQTY2, ARROW_DOWN_FIELDQTY2, ARROW_UP_FIELDQTY2};
	private static final Action[] ACTIONS_PANELFIELDQTY3 = new Action[] { ENTER_FIELDQTY3, ARROW_DOWN_FIELDQTY3, ARROW_UP_FIELDQTY3};
	private static final Action[] ACTIONS_PANELFIELDDISC1 = new Action[] { ENTER_FIELDDISC1, ARROW_DOWN_FIELDDISC1, ARROW_UP_FIELDDISC1};
	private static final Action[] ACTIONS_PANELFIELDDISC2 = new Action[] { ENTER_FIELDDISC2, ARROW_DOWN_FIELDDISC2, ARROW_UP_FIELDDISC2};
	private static final Action[] ACTIONS_PANELFIELDSUBTOTALAFTERDISCAFTERPPN = new Action[] { ENTER_FIELDSUBTOTALAFTERDISCAFTERPPN, 
		ARROW_DOWN_FIELDSUBTOTALAFTERDISCAFTERPPN, ARROW_UP_FIELDSUBTOTALAFTERDISCAFTERPPN};

	@Override
	public Action[] getActions(Object target, Object sender) {
		if (sender == view.getPanelComboProduct()) {
			return ACTIONS_PANELCOMBOPRODUCT;
		}else if (sender == view.getPanelFieldSprice()) {
			return ACTIONS_PANELFIELDSPRICE;			
		}else if (sender == view.getPanelFieldSpriceafterppn()) {
			return ACTIONS_PANELFIELDSPRICE_AFTERPPN;			
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
		}
		return ACTIONS;
	}

	@Override
	public void handleAction(Action action, Object sender, Object target) {
		if (action==ENTER_COMBOPRODUCT){
				if (view.getFieldSprice().isVisible() && view.getFieldSprice().isEnabled()) {
					view.getFieldSprice().focus();
				} else if(view.getFieldSpriceafterppn().isVisible() && view.getFieldSpriceafterppn().isEnabled()){
					view.getFieldSpriceafterppn().focus();				
				} else if (view.getFieldQty1().isVisible() && view.getFieldQty1().isEnabled()) {
					view.getFieldQty1().focus();
				}		
		}else if (action == ENTER_FIELDSPRICE || action == ARROW_DOWN_FIELDSPRICE) {
			view.getFieldQty1().focus();
		}else if (action == ARROW_UP_FIELDSPRICE) {
			view.getComboProduct().focus();
			
		}else if (action == ENTER_FIELDSPRICE_AFTERPPN || action == ARROW_DOWN_FIELDSPRICE_AFTERPPN) {
			view.getFieldQty1().focus();
		}else if (action == ARROW_UP_FIELDSPRICE_AFTERPPN) {
			view.getComboProduct().focus();
			
		}else if (action == ENTER_FIELDQTY1 || action == ARROW_DOWN_FIELDQTY1) {
			view.getFieldQty2().focus();
		}else if (action == ARROW_UP_FIELDQTY1) {
			view.getFieldSprice().focus();
			
		}else if (action == ENTER_FIELDQTY2 || action == ARROW_DOWN_FIELDQTY2) {
			view.getFieldQty3().focus();
		}else if (action == ARROW_UP_FIELDQTY2) {
			view.getFieldQty1().focus();;
			
		}else if (action == ENTER_FIELDQTY3 || action==ARROW_DOWN_FIELDQTY3) {
			view.getFieldDisc1().focus();
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
			
		}else if (action == ENTER_FIELDSUBTOTALAFTERDISCAFTERPPN) {
			view.getBtnAddAndSave().click();
			view.getComboProduct().focus();
		}else if (action == ARROW_UP_FIELDSUBTOTALAFTERDISCAFTERPPN) {
			view.getFieldDisc2().focus();;
		}
		
	}


	
}	