package com.riskcare.forums.client;

import com.vaadin.server.CustomizedSystemMessages;
import com.vaadin.server.SystemMessages;
import com.vaadin.server.SystemMessagesInfo;
import com.vaadin.server.SystemMessagesProvider;

public class ApplicationMessagesProvider implements SystemMessagesProvider {

	private static final long serialVersionUID = 1L;

	@Override
    public SystemMessages getSystemMessages(SystemMessagesInfo systemMessagesInfo) {
        final CustomizedSystemMessages customizedSystemMessages = new CustomizedSystemMessages();
        customizedSystemMessages.setSessionExpiredNotificationEnabled(false);
        customizedSystemMessages.setOutOfSyncNotificationEnabled(false);
        customizedSystemMessages.setCommunicationErrorCaption("Communication error");
        customizedSystemMessages.setCommunicationErrorMessage("Bad communication");
        customizedSystemMessages.setAuthenticationErrorNotificationEnabled(false);
        customizedSystemMessages.setCommunicationErrorNotificationEnabled(true);
        customizedSystemMessages.setCookiesDisabledNotificationEnabled(false);

        return customizedSystemMessages;
    }
}
