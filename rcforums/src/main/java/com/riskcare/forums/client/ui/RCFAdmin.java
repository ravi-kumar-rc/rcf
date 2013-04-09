package com.riskcare.forums.client.ui;

import com.vaadin.server.Page;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.Runo;

public class RCFAdmin {

    private final TabSheet tabs = new TabSheet();
    
    private RCFCategoryManagement categoryManagement;
    private RCFPostManagement postManagement;
    
    public RCFAdmin() {
        
    }
    
    public VerticalLayout build() {
    	VerticalLayout layout = new VerticalLayout();
		layout.setHeight(""+ (Page.getCurrent().getBrowserWindowHeight() - 80));
		layout.setMargin(true);
    	
		tabs.setSizeFull();
		tabs.addStyleName(Reindeer.TABSHEET_BORDERLESS);
        tabs.addTab(categoryManagement.build(), "Category Management");
        tabs.addTab(postManagement, "Post Management");
        
        layout.setStyleName(Runo.PANEL_LIGHT);
        layout.addComponent(tabs);
    	
        return layout;
    }

	public RCFCategoryManagement getCategoryManagement() {
		return categoryManagement;
	}

	public void setCategoryManagement(RCFCategoryManagement categoryManagement) {
		this.categoryManagement = categoryManagement;
	}

	public RCFPostManagement getPostManagement() {
		return postManagement;
	}

	public void setPostManagement(RCFPostManagement postManagement) {
		this.postManagement = postManagement;
	}
    
}
