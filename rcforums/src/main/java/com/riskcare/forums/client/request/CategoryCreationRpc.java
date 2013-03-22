package com.riskcare.forums.client.request;

import org.joda.time.DateTime;

import com.vaadin.shared.communication.ServerRpc;

public interface CategoryCreationRpc extends ServerRpc {
	
	public void buildTree(String categoryName, String categoryDesc, DateTime creationDate, String creator, String parent);
	
}
