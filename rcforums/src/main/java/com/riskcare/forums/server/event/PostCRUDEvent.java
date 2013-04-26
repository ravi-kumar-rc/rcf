package com.riskcare.forums.server.event;

import com.vaadin.data.Container;

public class PostCRUDEvent implements CRUDEvent {
	private Container container;
	
	public PostCRUDEvent(Container container) {
		this.container = container;
	}
	
	@Override
	public Container getUpdatedContainer() {
		return container;
	}
}
