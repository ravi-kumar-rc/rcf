package com.riskcare.forums.client;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinServletService;

public class RCFServlet extends VaadinServlet {
	
	private static final long serialVersionUID = 1L;
	
	private transient ApplicationContext applicationContext;
	
	/*@Override
	protected VaadinServletService getService() {
		final VaadinServletService service = super.getService();
		service.setSystemMessagesProvider(new ApplicationMessagesProvider());
		return service;
	}*/

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
		
		if (SpringApplicationContext.getApplicationContext() == null)
        {
            SpringApplicationContext.setApplicationContext(applicationContext);
        }

		super.init(config);
	}
	
    @Override
    protected VaadinServletService createServletService(DeploymentConfiguration deploymentConfiguration)
    {
        final VaadinServletService service = super.createServletService(deploymentConfiguration);

        // Custom system messages provider
        service.setSystemMessagesProvider(new ApplicationMessagesProvider());
            
        return service;
    }
}
