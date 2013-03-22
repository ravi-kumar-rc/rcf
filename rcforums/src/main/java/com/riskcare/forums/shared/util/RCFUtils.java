package com.riskcare.forums.shared.util;


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
   
}
