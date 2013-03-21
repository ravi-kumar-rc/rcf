package com.riskcare.forums.client.ui;

import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Runo;

public class RCFAdmin extends VerticalLayout {

    private static final long serialVersionUID = 1L;

    private final TabSheet tabs = new TabSheet();
    
    public RCFAdmin() {
        
        tabs.addTab(new RCFCategoryManagement(), "Category Management");
        tabs.addTab(new RCFPostManagement(), "Post Management");
        
        setMargin(true,true,true,true);
        setStyleName(Runo.PANEL_LIGHT);
        addComponent(tabs);
    }
    
}
