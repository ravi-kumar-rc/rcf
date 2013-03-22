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
package com.riskcare.forums.client;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.event.shared.EventBus;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;


/**
 * The Application's "main" class
 */

@SuppressWarnings("serial")
public class RCF extends UI
{
	final ClientFactory clientFactory = GWT.create(ClientFactory.class);
	final EventBus eventBus = clientFactory.getEventBus();
	
    public RCF() {
    }
    
	@Override
	protected void init(VaadinRequest request) {
    	final RCFMainView mainView = clientFactory.getRCFMainView();
    	setContent(mainView);
	}
}
