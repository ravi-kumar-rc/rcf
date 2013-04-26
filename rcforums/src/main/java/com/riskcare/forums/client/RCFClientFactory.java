package com.riskcare.forums.client;


import com.riskcare.forums.server.service.AuthenticationService;
import com.riskcare.forums.server.service.StatusService;

public interface RCFClientFactory {

	AuthenticationService getAuthenticationService();
	
	void setAuthenticationService(AuthenticationService authenticationService);
	
	StatusService getStatusService();
}
