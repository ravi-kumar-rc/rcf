package com.riskcare.forums.shared.util;

import com.vaadin.terminal.gwt.server.WebApplicationContext;

public class RCFUtils {
    
    private static RCFUtils utils;
    
    private RCFUtils() {
    }
    
    public static synchronized RCFUtils getRCFUtils() {
        if(utils == null) {
            return new RCFUtils();
        } 
        return utils;
    }
    
    public int getScreenWidth(WebApplicationContext context) {
        return context.getBrowser().getScreenWidth();
    }
    
    public int getScreenHeight(WebApplicationContext context) {
        return context.getBrowser().getScreenHeight();
    }
}
