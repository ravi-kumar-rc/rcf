package com.riskcare.forums.client.ui;

import com.vaadin.server.Page;
import com.vaadin.ui.VerticalLayout;

public class RCFCategoryManagement {

    private CategoryView categoryView;
    
    public RCFCategoryManagement() {
    }
    
    public VerticalLayout build() {
    	VerticalLayout layout = new VerticalLayout();
		layout.setHeight("" + (Page.getCurrent().getBrowserWindowHeight()-200));
		layout.setMargin(true);
    	
        layout.addComponent(categoryView.initialize());
        
        return layout;
    }

	public CategoryView getCategoryView() {
		return categoryView;
	}

	public void setCategoryView(CategoryView categoryView) {
		this.categoryView = categoryView;
	}
    
}
