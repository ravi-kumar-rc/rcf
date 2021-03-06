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


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.github.wolfie.refresher.Refresher;
import com.google.common.eventbus.EventBus;
import com.riskcare.forums.client.ui.CategoryTree;
import com.riskcare.forums.client.ui.PostView;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.Runo;


/**
 * The Application's "main" class
 */

@SuppressWarnings("serial")
@Theme(Runo.THEME_NAME)
@Scope("session")
public class RCF extends UI
{
	private static final Logger LOG = LoggerFactory.getLogger(RCF.class);
	
	private RCFMainView mainView;
	// private CategoryAndPostView categoryAndPostView;
	private CategoryTree categoryTree;
	private PostView postView;
	private RCFClientFactory clientFactory;
	
	private EventBus eventBus;
	private final Refresher refresher = new Refresher();
	
	@Override
	protected void init(VaadinRequest request) {
		LOG.debug("Initializing the UI");
		LOG.debug("Session : " + getSession());
		setContent(mainView.build());
		eventBus = new EventBus();
		LOG.debug("Category tree instantiated is : " + categoryTree);
		eventBus.register(categoryTree);
		LOG.debug("Category tree registered");
		//eventBus.register(postView);
		
		refresher.addListener(categoryTree);
		refresher.setRefreshInterval(2000);
		addExtension(refresher);
	}
	
	public RCFMainView getMainView() {
		return mainView;
	}

	public void setMainView(RCFMainView mainView) {
		this.mainView = mainView;
	}

	public CategoryTree getCategoryTree() {
		return categoryTree;
	}
	
	public void setCategoryTree(CategoryTree categoryTree) { 
		this.categoryTree = categoryTree;
	}

	public PostView getPostView() {
		return postView;
	}

	public void setPostView(PostView postView) {
		this.postView = postView;
	}

	public RCFClientFactory getClientFactory() {
		return clientFactory;
	}

	public void setClientFactory(RCFClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
}
