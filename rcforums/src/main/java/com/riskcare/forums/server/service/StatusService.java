package com.riskcare.forums.server.service;

import java.util.List;

import com.riskcare.forums.server.event.UIEvent;

public interface StatusService {
    List<UIEvent> getUIUpdates();
    
    public void setUpdates(String id, UIEvent value);
}
