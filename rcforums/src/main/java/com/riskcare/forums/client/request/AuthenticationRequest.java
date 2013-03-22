package com.riskcare.forums.client.request;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;
import com.riskcare.forums.client.request.proxy.UserProxy;
import com.riskcare.forums.server.service.AuthenticationServiceImpl;
import com.riskcare.forums.server.util.SpringServiceLocator;

@Service(value = AuthenticationServiceImpl.class, locator = SpringServiceLocator.class)
public interface AuthenticationRequest extends RequestContext {
	Request<UserProxy> getUser();
}
