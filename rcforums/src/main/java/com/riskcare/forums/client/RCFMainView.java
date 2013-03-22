package com.riskcare.forums.client;

import com.google.web.bindery.requestfactory.shared.Receiver;
import com.riskcare.forums.client.request.proxy.UserProxy;
import com.riskcare.forums.client.ui.RCFAdmin;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class RCFMainView extends VerticalLayout {

	private static final long serialVersionUID = 1L;
	
	private final ClientFactory clientFactory;
    
    public RCFMainView(final ClientFactory clientFactory) {
    	
    	this.clientFactory = clientFactory;
    	
    	init();
    }
    
    public void init()
    {
        final Label lblCaption = new Label("<h1>Riskcare Forums</h1>", ContentMode.HTML);
        
        final Label lblUser = new Label();

    	clientFactory.getRequestFactory().getAuthenticationRequest().getUser().fire(new Receiver<UserProxy>() {
    		@Override
    		public void onSuccess(UserProxy response) {
    			lblUser.setValue(response.getUsername());
    		}
    	});
        
        
        TabSheet tabs = new TabSheet();
        tabs.addTab(new RCFAdmin(), "Administration");

        HorizontalLayout header = new HorizontalLayout();
        header.setSpacing(true);
        
        header.addComponent(lblCaption);
        header.addComponent(lblUser);
        header.setComponentAlignment(lblUser, Alignment.MIDDLE_RIGHT);
        
        addComponent(header);
        addComponent(tabs);
    }	
}
