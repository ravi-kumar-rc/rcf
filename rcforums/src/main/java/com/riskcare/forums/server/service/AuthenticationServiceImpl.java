package com.riskcare.forums.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.riskcare.forums.server.user.User;

public class AuthenticationServiceImpl implements AuthenticationService {

    Logger LOG = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    
    public User getUser() {
        
    	User user = new User();
    	
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if(authentication == null) {
            LOG.error("Not logged in");
            return user;
        }
        
        user.setUsername(authentication.getPrincipal().toString());
        return user;
    }
}
