package com.riskcare.forums.client;

import com.google.web.bindery.event.shared.EventBus;
import com.riskcare.forums.client.request.RCFRequestFactory;

public interface ClientFactory {
	
    RCFRequestFactory getRequestFactory();
    
    EventBus getEventBus();
 
    RCFMainView getRCFMainView();
    
}