package com.riskcare.forums.client.request;

import com.google.web.bindery.requestfactory.shared.RequestFactory;

public interface RCFRequestFactory extends RequestFactory {
	
	AuthenticationRequest getAuthenticationRequest();
	
}
