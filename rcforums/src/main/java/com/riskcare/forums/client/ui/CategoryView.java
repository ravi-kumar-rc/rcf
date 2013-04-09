package com.riskcare.forums.client.ui;

import com.riskcare.forums.server.vo.CategoryVO;
import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;

public class CategoryView implements Button.ClickListener, ItemClickListener, ItemSetChangeListener {

    private static final long serialVersionUID = 1L;

    private HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
	private GridLayout gridLayout = new GridLayout(2,4);
    
    private Label lblParent = new Label("Parent category: ");
    private TextField txtParent = new TextField();
    private Label lblName = new Label("New category name <font color=\"red\"><b>*</b></font>: ", ContentMode.HTML);
    private TextField txtName = new TextField();
    private Label lblDesc = new Label("New category description: ");
    private TextField txtDesc = new TextField();
    
    private CategoryTree categoryTree;
    private Tree tree; 
    private CategoryVO selectedCategory;
    
    private final Button btnCreate = new Button("Create");
    
    public CategoryView() {
    }
    
    public HorizontalSplitPanel initialize() {
    	
    	buildCategoryTree();
    	buildCategoryPanel();
    	splitPanel.setSizeFull();
    	splitPanel.setFirstComponent(gridLayout);
    	splitPanel.setSecondComponent(tree);
    	
    	
    	return splitPanel;
    }
    
    private void buildCategoryTree() {
    	tree = categoryTree.initialize();
    	tree.addItemClickListener(this);
    	btnCreate.addClickListener(this);
    }

    private void buildCategoryPanel() {
    	
    	txtParent.setEnabled(false);
    	
    	gridLayout.setMargin(true);
    	gridLayout.addComponent(lblParent);
    	gridLayout.addComponent(txtParent);
    	gridLayout.addComponent(lblName);
    	gridLayout.addComponent(txtName);
    	gridLayout.addComponent(lblDesc);
    	gridLayout.addComponent(txtDesc);
    	gridLayout.addComponent(btnCreate);
    }
    
	@Override
	public void itemClick(ItemClickEvent event) {
		selectedCategory = (CategoryVO) event.getItemId();
		txtParent.setValue(selectedCategory.getCategoryName());
	}
    
    @Override
    public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
    	
    	if(selectedCategory == null) {
    		Notification.show("Error", "Please select a parent category from the tree", Notification.Type.ERROR_MESSAGE);
    	} else {
    		if(txtName.getValue().trim().length() == 0) {
    			Notification.show("Error", "Please enter a valid category name", Notification.Type.ERROR_MESSAGE);
    		} else {
    			btnCreate.setEnabled(false);
    			resetFields();
    			String catName = txtName.getValue();
    			String catDesc = txtDesc.getValue();
    			String catParent = txtParent.getValue();
    			categoryTree.addItem(catName, catDesc, catParent);
    			tree=categoryTree.initialize();
    			Notification.show("Info", "The category is now added",Notification.Type.HUMANIZED_MESSAGE);
    			btnCreate.setEnabled(true);
    		}
    	}
    }

    public void resetFields() {
    	txtName.setValue("");
    	txtDesc.setValue("");
    	txtParent.setValue("");
    }
    
	public CategoryTree getCategoryTree() {
		return categoryTree;
	}

	public void setCategoryTree(CategoryTree categoryTree) {
		this.categoryTree = categoryTree;
	}

	@Override
	public void containerItemSetChange(ItemSetChangeEvent event) {
	}

}
