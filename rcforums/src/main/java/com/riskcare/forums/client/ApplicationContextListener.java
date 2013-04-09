package com.riskcare.forums.client;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ApplicationContextListener implements ServletContextListener {
    /**
     * Initializes the application context by web flow xml configs.
     *
     * @param servletContextEvent the servlet context event.
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        final ServletContext servletContext = servletContextEvent.getServletContext();
        final WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        ApplicationContextLocator.setApplicationContext(context);
    }

    /**
     * Sets the application context to null.
     *
     * @param servletContextEvent the servlet context event.
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ApplicationContextLocator.setApplicationContext(null);
    }
}
