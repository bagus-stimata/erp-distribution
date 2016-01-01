package org.erp.distribution.pengguna;



import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FDivisionJpaService;
import org.erp.distribution.jpaservice.SysvarJpaService;
import org.erp.distribution.jpaservice.UserJpaService;
import org.erp.distribution.jpaservice.UserJpaServiceImpl;
import org.erp.distribution.model.FDivision;
import org.erp.distribution.model.User;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.CustomComponent;

public class UserAccountModel extends CustomComponent {
	
	//1. DAO SERVICE
		private UserJpaService userService;
//		private MenuAccessTempJpaService menuAccessTempService;
		private SysvarJpaService sysvarJpaService;
		private FDivisionJpaService fDivisionJpaService;
		
		
	//2. ENTITY
		protected User user = new User();
		protected User newUser = new User();
//		protected MenuAccessTemp menuAccessTemp;
	//3. LIST >> JIKA PERLU
	//4. BeanItemContainer, Jpa Container
		private BeanItemContainer<User> beanItemContainerUser = new BeanItemContainer<User>(User.class);
		private BeanItemContainer<User> beanItemContainerUserSearch  = new BeanItemContainer<User>(User.class);
		
		private BeanItemContainer<FDivision> beanItemContainerDivision = new BeanItemContainer<FDivision>(FDivision.class);
		
//		private BeanItemContainer<Bank> beanItemContainerBank;
	//5. Binder (BeanFieldGroup)
		private BeanFieldGroup<User> binderUser = new BeanFieldGroup<User>(User.class);
				
	//OTHERS
	protected String OperationStatus = "OPEN";

	public UserAccountModel(){
		initVariable();
		initVariableData();
		
	}
	
	public void initVariable(){
//		userService = new UserJpaServiceImpl();		
		
		setSysvarJpaService((((DashboardUI) getUI().getCurrent()).getSysvarJpaService()));
		setUserService((((DashboardUI) getUI().getCurrent()).getUserJpaService()));
		setfDivisionJpaService((((DashboardUI) getUI().getCurrent()).getfDivisionJpaService()));

		
		
	}
	public void initVariableData(){
		reload();
		
		
	}
	public void reload(){
		beanItemContainerUser.removeAllContainerFilters();
		beanItemContainerUser.removeAllItems();
		
		beanItemContainerUser.addAll(userService.findAll());
		beanItemContainerDivision.addAll(fDivisionJpaService.findAll());
	}

	public UserJpaService getUserService() {
		return userService;
	}

	public SysvarJpaService getSysvarJpaService() {
		return sysvarJpaService;
	}

	public FDivisionJpaService getfDivisionJpaService() {
		return fDivisionJpaService;
	}

	public User getUser() {
		return user;
	}

	public User getNewUser() {
		return newUser;
	}

	public BeanItemContainer<User> getBeanItemContainerUser() {
		return beanItemContainerUser;
	}

	public BeanItemContainer<User> getBeanItemContainerUserSearch() {
		return beanItemContainerUserSearch;
	}

	public BeanItemContainer<FDivision> getBeanItemContainerDivision() {
		return beanItemContainerDivision;
	}

	public BeanFieldGroup<User> getBinderUser() {
		return binderUser;
	}

	public String getOperationStatus() {
		return OperationStatus;
	}

	public void setUserService(UserJpaService userService) {
		this.userService = userService;
	}

	public void setSysvarJpaService(SysvarJpaService sysvarJpaService) {
		this.sysvarJpaService = sysvarJpaService;
	}

	public void setfDivisionJpaService(FDivisionJpaService fDivisionJpaService) {
		this.fDivisionJpaService = fDivisionJpaService;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public void setBeanItemContainerUser(
			BeanItemContainer<User> beanItemContainerUser) {
		this.beanItemContainerUser = beanItemContainerUser;
	}

	public void setBeanItemContainerUserSearch(
			BeanItemContainer<User> beanItemContainerUserSearch) {
		this.beanItemContainerUserSearch = beanItemContainerUserSearch;
	}

	public void setBeanItemContainerDivision(
			BeanItemContainer<FDivision> beanItemContainerDivision) {
		this.beanItemContainerDivision = beanItemContainerDivision;
	}

	public void setBinderUser(BeanFieldGroup<User> binderUser) {
		this.binderUser = binderUser;
	}

	public void setOperationStatus(String operationStatus) {
		OperationStatus = operationStatus;
	}

	
	
	
}
