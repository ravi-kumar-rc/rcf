package com.riskcare.forums.client.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riskcare.forums.client.request.CategoryCreationRpc;
import com.riskcare.forums.client.ui.dialog.InputDialog;
import com.riskcare.forums.server.service.CategoryCreationService;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.server.ClientConnector.AttachEvent;
import com.vaadin.server.ClientConnector.AttachListener;
import com.vaadin.shared.ui.Connect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@Connect(CategoryCreationService.class)
public class CategoryView extends AbstractComponentConnector implements Button.ClickListener {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(CategoryView.class);
    
    private VerticalLayout layout = new VerticalLayout();
    
    public CategoryCreationRpc categoryCreationRpc = RpcProxy.create(CategoryCreationRpc.class, this);
    
    private Tree categoryTree = new Tree();
    
    private final Button btnCreate = new Button("Create");
    
    public CategoryView() {
    }
    
    public VerticalLayout initialize() {
    	
    	categoryTree.setImmediate(true);
    	
    	buildCategoryTree();
    	
    	return layout;
    }
    
    public void buildCategoryTree() {
    	layout.addAttachListener(new AttachListener() {

			@Override
			public void attach(AttachEvent event) {
	        	categoryCreationRpc.buildRoot();				
			}
    		
    	});
        btnCreate.addClickListener(this);
        layout.addComponent(btnCreate);
        layout.addComponent(categoryTree);
    }
    
    public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
        LOG.info("Validating root category");
        String selectedNode = (String) categoryTree.getValue();
        if(selectedNode != null) {
        	InputDialog inputDialog = new InputDialog();
        	
        	categoryCreationRpc.buildTree(categoryTree.getValue().toString(),"", "null");
        }
        LOG.info("Building the category tree...");
    }

}
