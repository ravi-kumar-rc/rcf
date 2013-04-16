package com.riskcare.forums.client.ui;

import com.vaadin.ui.VerticalLayout;

public class RCFCategoryAndPostManagement {

    private CategoryAndPostView categoryAndPostView;
    
    public RCFCategoryAndPostManagement() {
    }
    
    public VerticalLayout build() {
    	VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setHeight("99%");
    	
        layout.addComponent(categoryAndPostView.initialize());
        
        return layout;
    }

	public CategoryAndPostView getCategoryAndPostView() {
		return categoryAndPostView;
	}

	public void setCategoryAndPostView(CategoryAndPostView categoryAndPostView) {
		this.categoryAndPostView = categoryAndPostView;
	}
    
}
