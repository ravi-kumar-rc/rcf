package com.riskcare.forums.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.web.bindery.event.shared.EventBus;
import com.riskcare.forums.client.request.RCFRequestFactory;

public class ClientFactoryImpl implements ClientFactory {

	private final EventBus eventBus;
	private final RCFRequestFactory requestFactory;
	private final RCFMainView mainView;
	
	public ClientFactoryImpl() {
		
		eventBus = new SimpleEventBus();
		requestFactory = GWT.create(RCFRequestFactory.class);
		requestFactory.initialize(eventBus);
		
		mainView = new RCFMainView(this);
	}
	
	public RCFRequestFactory getRequestFactory() {
		return requestFactory;
	}

	public EventBus getEventBus() {
		return eventBus;
	}

	public RCFMainView getRCFMainView() {
		return mainView;
	}
	
}
