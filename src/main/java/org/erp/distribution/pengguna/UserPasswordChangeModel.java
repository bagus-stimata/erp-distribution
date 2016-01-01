package org.erp.distribution.pengguna;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservice.UserJpaService;
import org.erp.distribution.model.User;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.CustomComponent;

public class UserPasswordChangeModel extends CustomComponent{
	private SysvarJpaService sysvarService;	
	private UserJpaService userService;
	
	protected User itemHeader = new User();
	
	private BeanFieldGroup<User> binderHeader = 
			new BeanFieldGroup<User>(User.class);
	
	
	
	public UserPasswordChangeModel(){
		initVariable();
		initVariableData();
	}
	public void initVariable(){
		setSysvarService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
		setUserService((((DashboardUI) getUI().getCurrent()).getUserJpaService()));
	}
	public void initVariableData(){
		
	}
	public SysvarJpaService getSysvarService() {
		return sysvarService;
	}
	public void setSysvarService(SysvarJpaService sysvarService) {
		this.sysvarService = sysvarService;
	}
	public UserJpaService getUserService() {
		return userService;
	}
	public void setUserService(UserJpaService userService) {
		this.userService = userService;
	}
	public User getItemHeader() {
		return itemHeader;
	}
	public void setItemHeader(User itemHeader) {
		this.itemHeader = itemHeader;
	}
	public BeanFieldGroup<User> getBinderHeader() {
		return binderHeader;
	}
	public void setBinderHeader(BeanFieldGroup<User> binderHeader) {
		this.binderHeader = binderHeader;
	}
	
	
	
	
	
}
