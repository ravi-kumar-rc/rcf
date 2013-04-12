package com.riskcare.forums.client.ui;

import com.vaadin.server.Page;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

public class RCFAdmin {

    private final TabSheet tabs = new TabSheet();
    
    private RCFCategoryManagement categoryManagement;
    private RCFPostManagement postManagement;
    
    public RCFAdmin() {
        
    }
    
    public VerticalLayout build() {
    	VerticalLayout layout = new VerticalLayout();
		layout.setHeight(""+ (Page.getCurrent().getBrowserWindowHeight() - 110));
		layout.setMargin(true);
    	
		tabs.setSizeFull();
        tabs.addStyleName(Runo.TABSHEET_SMALL);
        tabs.addTab(categoryManagement.build(), "Category Management");
        tabs.addTab(postManagement, "Post Management");
        
        layout.setStyleName(Runo.LAYOUT_DARKER);
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
