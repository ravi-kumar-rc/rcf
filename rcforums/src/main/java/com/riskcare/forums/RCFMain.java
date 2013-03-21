/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.riskcare.forums;

import com.riskcare.forums.client.ui.RCFAdmin;
import com.riskcare.forums.server.service.AuthenticationServiceImpl;
import com.vaadin.Application;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.Reindeer;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class RCFMain extends Application
{
    private Window window;

    private AuthenticationServiceImpl authService;
    
    public RCFMain() {
        authService = new AuthenticationServiceImpl();
    }
    
    @Override
    public void init()
    {
        setTheme(Reindeer.THEME_NAME);
        
        window = new Window("Riskcare Forums");
        setMainWindow(window);
        Label lblCaption = new Label("<h1>Riskcare Forums</h1>", Label.CONTENT_XHTML);
        
        Label lblUser = new Label(authService.getUsername());
        
        TabSheet tabs = new TabSheet();
        tabs.addTab(new RCFAdmin(), "Administration");

        HorizontalLayout header = new HorizontalLayout();
        header.setSpacing(true);
        
        header.addComponent(lblCaption);
        header.addComponent(lblUser);
        header.setComponentAlignment(lblUser, Alignment.MIDDLE_RIGHT);
        
        window.addComponent(header);
        window.addComponent(tabs);
    }
}
