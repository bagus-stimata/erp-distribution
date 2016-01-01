package org.erp.distribution;

import com.vaadin.server.Resource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;

public class HomeView extends CustomComponent{
	public HomeView(){
		
		VerticalLayout content = new VerticalLayout();
		Resource res = new ThemeResource("../images/background/des-bg01.png");


		// Display the object
		Embedded object = new Embedded(null, res);
		object.setHeight("500px");
		object.setWidth("700px");
		
		content.addComponent(object);		
		content.setComponentAlignment(object, Alignment.MIDDLE_CENTER);
		setCompositionRoot(content);
		
	}

}
