package org.erp.distribution.master.productperubahanharga.windowitem;

import org.erp.distribution.model.FProduct;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.event.Action;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.Action.Handler;
import com.vaadin.event.FieldEvents.BlurEvent;
import com.vaadin.event.FieldEvents.BlurListener;
import com.vaadin.event.FieldEvents.FocusEvent;
import com.vaadin.event.FieldEvents.FocusListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class PerubahanHargaItemPresenter implements ClickListener, BlurListener, ValueChangeListener, Handler, FocusListener{
	private PerubahanHargaItemModel model;
	private PerubahanHargaItemView view;
	private PerubahanHargaItemHelper helper;
	
	public PerubahanHargaItemPresenter(PerubahanHargaItemModel model, PerubahanHargaItemView view){
		this.model = model;
		this.view = view;
		
		helper = new PerubahanHargaItemHelper(model, view);
		
		initListener();
	}
	
	public void initListener(){
		view.getBtnAddAndSave().addClickListener(this);
		view.getBtnReset().addClickListener(this);
		view.getBtnClose().addClickListener(this);
		
		//BLUR LISTENER
		view.getComboProduct().addBlurListener(this);		

		view.getFieldPprice().addBlurListener(this);
		view.getFieldPpriceafterppn().addBlurListener(this);
		view.getFieldPprice2().addBlurListener(this);
		view.getFieldPprice2afterppn().addBlurListener(this);

		view.getFieldSprice().addBlurListener(this);
		view.getFieldSpriceafterppn().addBlurListener(this);
		view.getFieldSprice2().addBlurListener(this);
		view.getFieldSprice2afterppn().addBlurListener(this);
		
		//ACTION HANDLER LISTENER
		view.getPanelComboProduct().addActionHandler(this);
		
		view.getPanelFieldPprice().addActionHandler(this);
		view.getPanelFieldPpriceafterppn().addActionHandler(this);
		view.getPanelFieldPprice2().addActionHandler(this);
		view.getPanelFieldPprice2afterppn().addActionHandler(this);
		
		view.getPanelFieldSprice().addActionHandler(this);
		view.getPanelFieldSpriceafterppn().addActionHandler(this);
		view.getPanelFieldSprice2().addActionHandler(this);
		view.getPanelFieldSprice2afterppn().addActionHandler(this);

		//ON FOCUS EVENT
		view.getFieldSubtotal().addFocusListener(this);
		view.getFieldSubtotalafterppn().addFocusListener(this);
		view.getFieldSubtotalafterdisc().addFocusListener(this);
		view.getFieldSubtotalafterdiscafterppn().addFocusListener(this);
		
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
	public void focus(FocusEvent event) {
		if (event.getComponent()==view.getFieldSubtotal()){
			if (view.getFieldSubtotal().getValue()==null) {
				if (view.getComboProduct().getValue() !=null) {					
					helper.updateAndCalculateItemDetil();
				}
			}
		}else if (event.getComponent()==view.getFieldSubtotalafterppn()){
			if (view.getFieldSubtotalafterppn().getValue()==null) {
				if (view.getComboProduct().getValue() !=null) {					
					helper.updateAndCalculateItemDetil();
				}
			}
		}else if (event.getComponent()==view.getFieldSubtotalafterdisc()){
			if (view.getFieldSubtotalafterdisc().getValue()==null) {
				if (view.getComboProduct().getValue() !=null) {					
					helper.updateAndCalculateItemDetil();
				}
			}			
		}else if (event.getComponent()==view.getFieldSubtotalafterdiscafterppn()){
			if (view.getFieldSubtotalafterdiscafterppn().getValue()==null) {
				if (view.getComboProduct().getValue() !=null) {					
					helper.updateAndCalculateItemDetil();
				}
			}
		}		
		
	}

	
	@Override
	public void blur(BlurEvent event) {
		
		int convfact1 = (model.itemDetil.getFproductBean().getConvfact1()==null?1:model.itemDetil.getFproductBean().getConvfact1());
		
		if (event.getComponent()==view.getComboProduct()) {				
			if (view.getComboProduct().getValue() !=null){
				helper.updateAndCalulateItemDetilProduct();
				helper.updateAndCalculateItemDetil();
			}
		}else if (event.getComponent()==view.getFieldPprice()){
			if (view.getFieldPprice().getValue()==null) {				
				view.getFieldPprice().setValue("0");
			}
			if (! view.getFieldPprice().getValue().equals("0") || ! view.getFieldPprice().getValue().trim().equals("")){
				helper.updateAndCalculatePPrice(model.getItemDetil().getPprice(), convfact1);
			}
		}else if (event.getComponent()==view.getFieldPpriceafterppn()){
//			helper.validasiHargaBelidanHargaJual();
			if (view.getFieldPpriceafterppn().getValue()==null) {				
				view.getFieldPpriceafterppn().setValue("0");
			}
			
			if (! view.getFieldPpriceafterppn().getValue().equals("0") || ! view.getFieldPpriceafterppn().getValue().trim().equals("")){
				helper.updateAndCalculatePPrice(helper.getPriceBeforePPN(model.getItemDetil().getPpriceafterppn()), convfact1);
			}
			
		}else if (event.getComponent()==view.getFieldPprice2()){
			if (view.getFieldPprice2().getValue()==null) {				
				view.getFieldPprice2().setValue("0");
			}
			
			if (! view.getFieldPprice2().getValue().equals("0") || ! view.getFieldPprice2().getValue().trim().equals("")){
				helper.updateAndCalculatePPrice(model.getItemDetil().getPprice2() * convfact1, convfact1);
			}
			
		}else if (event.getComponent()==view.getFieldPprice2afterppn()){
			if (view.getFieldPprice2afterppn().getValue()==null) {				
				view.getFieldPprice2afterppn().setValue("0");
			}
			
			if (! view.getFieldPprice2afterppn().getValue().equals("0") || ! view.getFieldPprice2afterppn().getValue().trim().equals("")){
				helper.updateAndCalculatePPrice(helper.getPriceBeforePPN(model.getItemDetil().getPprice2afterppn() * convfact1), convfact1);
			}
		}else if (event.getComponent()==view.getFieldSprice()){
			if (view.getFieldSprice().getValue()==null) {				
				view.getFieldSprice().setValue("0");
			}
			
			if (! view.getFieldSprice().getValue().equals("0") || ! view.getFieldSprice().getValue().trim().equals("")){
				helper.updateAndCalculateSPrice(model.getItemDetil().getSprice(), convfact1);
			}
		}else if (event.getComponent()==view.getFieldSpriceafterppn()){
			if (view.getFieldSpriceafterppn().getValue()==null) {				
				view.getFieldSpriceafterppn().setValue("0");
			}
			
			if (! view.getFieldSpriceafterppn().getValue().equals("0") || ! view.getFieldSpriceafterppn().getValue().trim().equals("")){
				helper.updateAndCalculateSPrice(helper.getPriceBeforePPN(model.getItemDetil().getSpriceafterppn()), convfact1);
			}
		}else if (event.getComponent()==view.getFieldSprice2()){
			if (view.getFieldSprice2().getValue()==null) {				
				view.getFieldSprice2().setValue("0");
			}
			
			if (! view.getFieldSprice2().getValue().equals("0") || ! view.getFieldSprice2().getValue().trim().equals("")){
				helper.updateAndCalculateSPrice(model.getItemDetil().getSprice2() * convfact1, convfact1);
			}
		}else if (event.getComponent()==view.getFieldSprice2afterppn()){
			if (view.getFieldSprice2afterppn().getValue()==null) {				
				view.getFieldSprice2afterppn().setValue("0");
			}
			
			if (! view.getFieldSprice2afterppn().getValue().equals("0") || ! view.getFieldSprice2afterppn().getValue().trim().equals("")){
			helper.updateAndCalculateSPrice(helper.getPriceBeforePPN(model.getItemDetil().getSprice2afterppn() * convfact1), convfact1);
			}
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
		model.itemDetil.setFtpricehBean(model.getItemHeader());

		model.itemDetil.setPpricebefore(0.0);
		model.itemDetil.setPprice2before(0.0);

		model.itemDetil.setSpricebefore(0.0);
		model.itemDetil.setSprice2before(0.0);
		
		model.itemDetil.setPprice(0.0);
		model.itemDetil.setPpriceafterppn(0.0);
		model.itemDetil.setPprice2(0.0);
		model.itemDetil.setPprice2afterppn(0.0);
		
		model.itemDetil.setSprice(0.0);
		model.itemDetil.setSpriceafterppn(0.0);
		model.itemDetil.setSprice2(0.0);
		model.itemDetil.setSprice2afterppn(0.0);

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
			
		}else if (action == ENTER_FIELDSUBTOTALAFTERDISCAFTERPPN) {
			view.getBtnAddAndSave().click();
			view.getComboProduct().focus();
		}else if (action == ARROW_UP_FIELDSUBTOTALAFTERDISCAFTERPPN) {
			view.getFieldQty3().focus();
		}
		
	}





	
	
}	
