package com.riskcare.forums.server.util;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.riskcare.forums.server.event.CategoryEvent;

public class CategoryCreationPublisher implements ApplicationEventPublisherAware {

    //--------------------------- FIELDS ---------------------------------    
    private ApplicationEventPublisher applicationEventPublisher;

    //--------------------------- METHODS --------------------------------
    
    /**
     * Sets the application event publisher
     */
    @Override
    public void setApplicationEventPublisher(
            ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * Publishes the file upload event
     * @param event
     */
    public void publish(CategoryEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

}
