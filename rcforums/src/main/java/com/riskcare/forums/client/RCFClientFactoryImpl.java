package com.riskcare.forums.client;

import com.riskcare.forums.server.service.AuthenticationService;

public class RCFClientFactoryImpl implements RCFClientFactory {

	private AuthenticationService authenticationService;

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	@Override
	public void setAuthenticationService(
			AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	
}
