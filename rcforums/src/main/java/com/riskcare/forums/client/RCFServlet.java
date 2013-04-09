package com.riskcare.forums.client;

import org.springframework.context.annotation.Scope;

import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;

@Scope("request")
public class RCFServlet extends VaadinServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected VaadinServletService getService() {
		final VaadinServletService service = super.getService();
		service.setSystemMessagesProvider(new ApplicationMessagesProvider());
		return service;
	}

}
