package com.riskcare.forums.client.request;

import com.vaadin.shared.communication.ServerRpc;

public interface CategoryCreationRpc extends ServerRpc {
	
	public void buildRoot();
	
	public void buildTree(String categoryName, String categoryDesc, String parent);
	
}
