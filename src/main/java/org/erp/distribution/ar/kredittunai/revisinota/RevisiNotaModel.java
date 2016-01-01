package org.erp.distribution.ar.kredittunai.revisinota;

import org.erp.distribution.DashboardUI;
import org.erp.distribution.jpaservice.FtSaleshJpaService;
import org.erp.distribution.model.FtSalesh;

import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.CustomComponent;

public class RevisiNotaModel extends CustomComponent {
	private FtSaleshJpaService ftSaleshJpaService;

	public FtSalesh ftSalesh;

	public RevisiNotaModel(){
		initVariable();
	}
	public RevisiNotaModel(FtSalesh ftSalesh){
		initVariable();
		this.ftSalesh = new FtSalesh();		
		this.ftSalesh = ftSalesh;
		
	}
	
	public void initVariable(){		
		setFtSaleshJpaService(((DashboardUI) getUI().getCurrent()).getFtSaleshJpaService());
		
	}
	public void initVariableData(){
		
	}
	public FtSaleshJpaService getFtSaleshJpaService() {
		return ftSaleshJpaService;
	}
	public FtSalesh getFtSalesh() {
		return ftSalesh;
	}
	public void setFtSaleshJpaService(FtSaleshJpaService ftSaleshJpaService) {
		this.ftSaleshJpaService = ftSaleshJpaService;
	}
	public void setFtSalesh(FtSalesh ftSalesh) {
		this.ftSalesh = ftSalesh;
	}
	
	
}
