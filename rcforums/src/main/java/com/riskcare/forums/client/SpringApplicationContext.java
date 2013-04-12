package com.riskcare.forums.client;

import java.io.Serializable;

import org.springframework.context.ApplicationContext;

public class SpringApplicationContext implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static transient ApplicationContext applicationContext;
	
	public static void setApplicationContext(ApplicationContext applicationContext) {
		SpringApplicationContext.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
}
