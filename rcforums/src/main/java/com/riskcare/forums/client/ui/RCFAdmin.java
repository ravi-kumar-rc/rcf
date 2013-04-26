package com.riskcare.forums.client.ui;

import org.springframework.context.annotation.Scope;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

@Scope("session")
public class RCFAdmin {

    private final TabSheet tabs = new TabSheet();
    
    private RCFCategoryAndPostManagement categoryAndPostManagement;
    private RCFUserManagement userManagement;
    
    public RCFAdmin() {
        
    }
    
    public VerticalLayout build() {
    	VerticalLayout layout = new VerticalLayout();
		layout.setHeight("99%");
    	layout.setMargin(true);
		
		tabs.setHeight("99%");
        tabs.addStyleName(Runo.TABSHEET_SMALL);
        tabs.addTab(categoryAndPostManagement.build(), "Category & Post Management");
        tabs.addTab(userManagement, "User Management");
        
        layout.setStyleName(Runo.LAYOUT_DARKER);
        layout.addComponent(tabs);
    	
        return layout;
    }

	public RCFCategoryAndPostManagement getCategoryAndPostManagement() {
		return categoryAndPostManagement;
	}

	public void setCategoryAndPostManagement(RCFCategoryAndPostManagement categoryAndPostManagement) {
		this.categoryAndPostManagement = categoryAndPostManagement;
	}

	public RCFUserManagement getUserManagement() {
		return userManagement;
	}

	public void setUserManagement(RCFUserManagement userManagement) {
		this.userManagement = userManagement;
	}
    
}
