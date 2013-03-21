package com.riskcare.forums.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationServiceImpl implements AuthenticationService {

    Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    
    public String getUsername() {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(authentication == null) {
            LOG.error("Not logged in");
            return null;
        }
        
        return authentication.getName();
    }
}
