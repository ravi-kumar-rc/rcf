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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Runo;


/**
 * The Application's "main" class
 */

@SuppressWarnings("serial")
@Theme(Runo.THEME_NAME)
@Component("rcf")
@Scope("session")
public class RCF extends UI
{
	private RCFMainView mainView;
	
	@Override
	protected void init(VaadinRequest request) {
		setContent(mainView.build());
	}

	
	public RCFMainView getMainView() {
		return mainView;
	}

	public void setMainView(RCFMainView mainView) {
		this.mainView = mainView;
	}
	
	@Override
	public void close() {
		
	}
}
