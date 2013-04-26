package com.riskcare.forums.server.handler;

import com.google.gwt.event.shared.EventHandler;
import com.riskcare.forums.server.event.CategoryCRUDEvent;

public interface CategoryCRUDHandler extends EventHandler {
    void onCategoryCRUD(CategoryCRUDEvent categoryCRUDEvent);
}
