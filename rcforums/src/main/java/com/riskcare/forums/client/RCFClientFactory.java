package com.riskcare.forums.client;

import com.riskcare.forums.server.service.AuthenticationService;

public interface RCFClientFactory {
	
	AuthenticationService getAuthenticationService();
	
	void setAuthenticationService(AuthenticationService authenticationService);
	
}
