package com.riskcare.forums.client.ui;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class RCFCategoryManagement extends VerticalLayout {

    private static final long serialVersionUID = 1L;
    
    private final CategoryView categoryView = new CategoryView();
    
    public RCFCategoryManagement() {
        initLayout();
    }
    
    public void initLayout() {
        
        HorizontalLayout subLayout = new HorizontalLayout();
        subLayout.setMargin(true,true,true,true);
        
        subLayout.addComponent(categoryView);
        addComponent(subLayout);
    }
    
}
