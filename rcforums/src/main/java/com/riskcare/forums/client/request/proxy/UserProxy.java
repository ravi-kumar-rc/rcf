package com.riskcare.forums.client.request.proxy;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;
import com.riskcare.forums.server.user.User;

@ProxyFor(User.class)
public interface UserProxy extends ValueProxy {
	String getUsername();
}
