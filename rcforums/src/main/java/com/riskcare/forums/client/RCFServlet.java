package com.riskcare.forums.client;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.client.Random;
import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.SessionDestroyEvent;
import com.vaadin.server.SessionDestroyListener;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;

public class RCFServlet extends VaadinServlet implements SessionInitListener, SessionDestroyListener {
	
	private static final long serialVersionUID = 1L;
	
	private static int count = 0;
	
	private Logger LOG = LoggerFactory.getLogger(RCFServlet.class);
	
	private transient ApplicationContext applicationContext;
	
	/*@Override
	protected VaadinServletService getService() {
		final VaadinServletService service = super.getService();
		service.setSystemMessagesProvider(new ApplicationMessagesProvider());
		return service;
	}*/

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		LOG.info("Initializing the servlet: " + count++ + " time");
		
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		
		if (SpringApplicationContext.getApplicationContext() == null)
        {
			LOG.info("Spring application context is null");
            SpringApplicationContext.setApplicationContext(applicationContext);
        }

		super.init(config);
		
		getService().addSessionInitListener(this);
		getService().addSessionDestroyListener(this);
	}
	
    @Override
    protected VaadinServletService createServletService(DeploymentConfiguration deploymentConfiguration)
    {
		LOG.info("Initializing the service: " + count + " time");
    	
        final VaadinServletService service = super.createServletService(deploymentConfiguration);

        // Custom system messages provider
        service.setSystemMessagesProvider(new ApplicationMessagesProvider());
            
        return service;
    }
    
    @Override
    public void sessionInit(SessionInitEvent sie) {
    	LOG.info("Session has been initialized zzzzzzzzz");
    }
    
    @Override
    public void sessionDestroy(SessionDestroyEvent sde) {
    	LOG.info("Session has been destroyed yyyyyyyyyyyyyyyy");
    }
}
