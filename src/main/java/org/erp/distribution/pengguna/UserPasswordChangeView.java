package org.erp.distribution.pengguna;

import org.eclipse.jdt.core.CheckDebugAttributes;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.Runo;

public class UserPasswordChangeView extends CustomComponent{
	private UserPasswordChangeModel model;
	
	private TextField fieldFullName = new TextField("Nama Lengkap");
	private TextField fieldUserId = new TextField("USER ID");

	private PasswordField fieldOldPassword = new PasswordField("Password Lama");
	private PasswordField fieldNewPassword = new PasswordField("Password Baru");
	private PasswordField fieldRetypePassword = new PasswordField("Tulis Ulang Password Baru");
	
	private Button btnSaveForm = new Button("Save/Rubah");
	private Button btnPrint = new Button("Print");
	private Button btnClose = new Button("Close");
	private Button btnCancelForm = new Button("Close");

	
	
	public UserPasswordChangeView(UserPasswordChangeModel model){
		this.model=model;

		initComponent();
		buildView();
		initComponentState();
		
		setDisplay();
		
	}
	public void initComponent(){
		fieldFullName.setWidth("300px");
		fieldUserId.setWidth("300px");
		fieldOldPassword.setWidth("300px");
		fieldNewPassword.setWidth("300px");
		fieldRetypePassword.setWidth("300px");
		
		
	}
	public void buildView(){
		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setMargin(true);
		
		//GENERAL
		VerticalLayout layoutGeneral = new VerticalLayout();
		layoutGeneral.setMargin(true);
		layoutGeneral.setSpacing(true);

		layoutGeneral.addComponent(fieldFullName);
		layoutGeneral.addComponent(fieldUserId);
		
		VerticalLayout layoutNewPassword = new VerticalLayout();
		layoutNewPassword.setMargin(true);
		layoutNewPassword.setStyleName(Reindeer.LAYOUT_WHITE);
		
		layoutNewPassword.addComponent(fieldOldPassword);
		
		layoutNewPassword.addComponent(fieldNewPassword);
		layoutNewPassword.addComponent(fieldRetypePassword);
		
		layoutGeneral.addComponent(layoutNewPassword);
		
		
		
		HorizontalLayout layoutButton = new HorizontalLayout();
		layoutButton.setSpacing(true);
		layoutButton.setMargin(true);
		layoutButton.addComponent(btnSaveForm);
		layoutButton.addComponent(btnCancelForm);
		
		content.addComponent(layoutGeneral);
		content.addComponent(layoutButton);
		content.setExpandRatio(layoutGeneral, 1);
		
		setCompositionRoot(content);
	}
	public void initComponentState(){
	}
	public void setDisplay(){
		bindAndBuildFieldGroupComponent();
		setDisplayProperties();
	}

	public void bindAndBuildFieldGroupComponent(){
		model.getBinderHeader().setItemDataSource(model.getItemHeader());

		model.getBinderHeader().bind(fieldFullName, "fullName");
		model.getBinderHeader().bind(fieldUserId, "userId");
		
	}
	public void setDisplayProperties(){
		fieldFullName.setReadOnly(true);
		fieldUserId.setReadOnly(true);
	}
	
	public UserPasswordChangeModel getModel() {
		return model;
	}
	public void setModel(UserPasswordChangeModel model) {
		this.model = model;
	}
	public TextField getFieldFullName() {
		return fieldFullName;
	}
	public void setFieldFullName(TextField fieldFullName) {
		this.fieldFullName = fieldFullName;
	}
	public TextField getFieldUserId() {
		return fieldUserId;
	}
	public void setFieldUserId(TextField fieldUserId) {
		this.fieldUserId = fieldUserId;
	}
	public PasswordField getFieldOldPassword() {
		return fieldOldPassword;
	}
	public void setFieldOldPassword(PasswordField fieldOldPassword) {
		this.fieldOldPassword = fieldOldPassword;
	}
	public PasswordField getFieldNewPassword() {
		return fieldNewPassword;
	}
	public void setFieldNewPassword(PasswordField fieldNewPassword) {
		this.fieldNewPassword = fieldNewPassword;
	}
	public PasswordField getFieldRetypePassword() {
		return fieldRetypePassword;
	}
	public void setFieldRetypePassword(PasswordField fieldRetypePassword) {
		this.fieldRetypePassword = fieldRetypePassword;
	}
	public Button getBtnSaveForm() {
		return btnSaveForm;
	}
	public void setBtnSaveForm(Button btnSaveForm) {
		this.btnSaveForm = btnSaveForm;
	}
	public Button getBtnPrint() {
		return btnPrint;
	}
	public void setBtnPrint(Button btnPrint) {
		this.btnPrint = btnPrint;
	}
	public Button getBtnClose() {
		return btnClose;
	}
	public void setBtnClose(Button btnClose) {
		this.btnClose = btnClose;
	}
	public Button getBtnCancelForm() {
		return btnCancelForm;
	}
	public void setBtnCancelForm(Button btnCancelForm) {
		this.btnCancelForm = btnCancelForm;
	}
	
	
}
