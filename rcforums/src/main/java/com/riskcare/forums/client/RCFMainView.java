package com.riskcare.forums.client;

import com.riskcare.forums.client.ui.RCFAdmin;
import com.vaadin.server.Page;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class RCFMainView {

	private RCFAdmin rcfAdmin;
	
    public RCFMainView() {
    }
    
    public VerticalLayout build()
    {
    	VerticalLayout layout = new VerticalLayout();
		layout.setHeight(""+Page.getCurrent().getBrowserWindowHeight());
    	layout.setMargin(true);
//        Label lblCaption = new Label("<h1>Riskcare Forums</h1>", ContentMode.HTML);
//        lblCaption.setSizeUndefined();
//        
//        Label lblUser = new Label(); //.getAuthenticationService().getUser().toString());

        TabSheet tabs = new TabSheet();
        tabs.setSizeFull();
        tabs.addStyleName(Reindeer.TABSHEET_BORDERLESS);
        tabs.addTab(rcfAdmin.build(), "Administration");

//        HorizontalLayout header = new HorizontalLayout();
//        header.setSpacing(true);
        
        //header.addComponent(lblCaption);
        //header.addComponent(lblUser);
        //header.setComponentAlignment(lblUser, Alignment.MIDDLE_RIGHT);
        
        layout.addComponent(tabs);
        
        return layout;
    }

	public RCFAdmin getRcfAdmin() {
		return rcfAdmin;
	}

	public void setRcfAdmin(RCFAdmin rcfAdmin) {
		this.rcfAdmin = rcfAdmin;
	}
    
}
