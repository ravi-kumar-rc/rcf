package com.riskcare.forums.client;

import java.net.URL;
import java.util.Collection;

import org.springframework.context.ApplicationContext;

public class ApplicationContextLocator {
    private static final Object monitor = new Object();
    protected static ApplicationContext applicationContext;
    protected static Collection<URL> webFlowConfiguration;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextLocator.applicationContext = applicationContext;
        synchronized (monitor) {
            monitor.notifyAll();
        }
    }

    public static ApplicationContext getApplicationContext() {
        try {
            synchronized (monitor) {
                while (ApplicationContextLocator.applicationContext == null) {
                    monitor.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ApplicationContextLocator.applicationContext;
    }

    public static void setWebFlowConfiguration(Collection<URL> webFlowConfiguration) {
        ApplicationContextLocator.webFlowConfiguration = webFlowConfiguration;
        synchronized (monitor) {
            monitor.notifyAll();
        }
    }

    public static Collection<URL> getWebFlowConfiguration() {
        try {
            synchronized (monitor) {
                while (ApplicationContextLocator.webFlowConfiguration == null) {
                    monitor.wait();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ApplicationContextLocator.webFlowConfiguration;
    }

    public static <T extends Object> T getBean(String beanId) {
        return (T) getApplicationContext().getBean(beanId);
    }

    public static ApplicationContext getApplicationContextOrNull() {
        return ApplicationContextLocator.applicationContext;
    }
}
