package com.riskcare.forums.client.ui;

import com.riskcare.forums.server.vo.CategoryVO;
import com.vaadin.data.Container.ItemSetChangeEvent;
import com.vaadin.data.Container.ItemSetChangeListener;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

public class CategoryAndPostView implements Button.ClickListener, ItemClickListener, ItemSetChangeListener {

    private static final long serialVersionUID = 1L;

    //Top layout
    GridLayout topLayout = new GridLayout(3,2);
    
    //Components for the Category CRUD accordion
    //--------------------START----------------------------
    private Accordion categoryAccordionCRUD = new Accordion();
    private GridLayout createGridLayout = new GridLayout(2,4);
    private final Label lblParent = new Label("Parent category selected: ");
    private final TextField txtParent = new TextField();
    private final Label lblName = new Label("New category name <font color=\"red\"><b>*</b></font>: ", ContentMode.HTML);
    private final TextField txtName = new TextField();
    private final Label lblDesc = new Label("New category description: ");
    private final TextField txtDesc = new TextField();
    private final Button btnCreate = new Button("Create");

    private GridLayout updateGridLayout = new GridLayout(2,7);
    private final Label lblUpdName = new Label("Name: ");
    private final TextField txtUpdName = new TextField();
    private final Label lblUpdDesc = new Label("Description: ");
    private final TextField txtUpdDesc = new TextField();
    private final Label lblUpdCreDate = new Label("Creation Date: ");
    private final TextField txtUpdCreDate = new TextField();
    private final Label lblUpdCreator = new Label("Created by: ");
    private final TextField txtUpdCreator = new TextField();
    private final Label lblUpdParent = new Label("Parent: ");
    private final TextField txtUpdParent = new TextField();
    private final Label lblUpdModDate = new Label("Last Modified date: ");
    private final TextField txtUpdModDate = new TextField();
    private final Button btnUpdate = new Button("Update");
    
    private GridLayout deleteGridLayout = new GridLayout(2,7);
    private final Label lblDelName = new Label("Name: ");
    private final TextField txtDelName = new TextField();
    private final Label lblDelDesc = new Label("Description: ");
    private final TextField txtDelDesc = new TextField();
    private final Label lblDelCreDate = new Label("Creation date: ");
    private final TextField txtDelCreDate = new TextField();
    private final Label lblDelCreator = new Label("Created by: ");
    private final TextField txtDelCreator = new TextField();
    private final Label lblDelParent = new Label("Parent: ");
    private final TextField txtDelParent = new TextField();
    private final Label lblDelModDate = new Label("Last Modified date: ");
    private final TextField txtDelModDate = new TextField();
    private final Button btnDelete = new Button("Delete");
    //--------------------END----------------------------
    
    //Layout for the category tree
    private final VerticalLayout treeLayout = new VerticalLayout();

    //Category tree component
    private CategoryTree categoryTree;
    
    //Post view components
    private PostView postView;
    private Accordion postCRUDAccordion;
    private Table postViewGrid;
    
    //Local category tree
    private Tree tree; 
    
    //Category object for the selected node in the tree
    private CategoryVO selectedCategory = null;
    
    
    public CategoryAndPostView() {
    }
    
    public GridLayout initialize() {
    	
    	buildCategoryTree();
    	buildCategoryCRUDAccordion();
    	
    	postCRUDAccordion = postView.buildPostCRUDAccordion();
    	postViewGrid = postView.buildPostViewGrid();
    	
    	topLayout.setHeight("100%");
    	topLayout.setWidth("100%");
    	topLayout.setMargin(true);
    	
    	//first row of the grid layout
    	topLayout.addComponent(categoryAccordionCRUD); 
    	topLayout.addComponent(treeLayout); 
    	topLayout.addComponent(postCRUDAccordion);	//builds the CRUD accordion for Posts management
    	
    	//second row of the grid layout
    	topLayout.addComponent(postViewGrid, 0, 1, 2, 1);		//builds the Grid for the Posts view which spans over 3 columns of the row
    	
    	return topLayout;
    }
    
    private void buildCategoryTree() {
    	tree = categoryTree.initialize();
    	treeLayout.setMargin(true);
    	treeLayout.setHeight("60%");
    	treeLayout.addComponent(tree);
    	tree.addItemClickListener(this);
    	btnCreate.addClickListener(this);
    	btnUpdate.addClickListener(this);
    	btnDelete.addClickListener(this);
    }

    private void buildCategoryCRUDAccordion() {
    	
    	//Setting editable properties of various fields
    	txtParent.setEnabled(false);
    	txtUpdCreDate.setEnabled(false);
    	txtUpdCreator.setEnabled(false);
    	txtUpdParent.setEnabled(false);
    	txtUpdModDate.setEnabled(false);
    	txtDelName.setEnabled(false);
    	txtDelDesc.setEnabled(false);
    	txtDelCreDate.setEnabled(false);
    	txtDelCreator.setEnabled(false);
    	txtDelParent.setEnabled(false);
    	txtDelModDate.setEnabled(false);

    	//"Create category" fields added
    	createGridLayout.addComponent(lblParent);
    	createGridLayout.addComponent(txtParent);
    	createGridLayout.addComponent(lblName);
    	createGridLayout.addComponent(txtName);
    	createGridLayout.addComponent(lblDesc);
    	createGridLayout.addComponent(txtDesc);
    	createGridLayout.addComponent(btnCreate);
    	
    	//"Update category" fields added
    	updateGridLayout.addComponent(lblUpdName);
    	updateGridLayout.addComponent(txtUpdName);
    	updateGridLayout.addComponent(lblUpdDesc);
    	updateGridLayout.addComponent(txtUpdDesc);
    	updateGridLayout.addComponent(lblUpdCreDate);
    	updateGridLayout.addComponent(txtUpdCreDate);
    	updateGridLayout.addComponent(lblUpdCreator);
    	updateGridLayout.addComponent(txtUpdCreator);
    	updateGridLayout.addComponent(lblUpdParent);
    	updateGridLayout.addComponent(txtUpdParent);
    	updateGridLayout.addComponent(lblUpdModDate);
    	updateGridLayout.addComponent(txtUpdModDate);
    	updateGridLayout.addComponent(btnUpdate);
    	
    	//"Delete category" fields added
    	deleteGridLayout.addComponent(lblDelName);
    	deleteGridLayout.addComponent(txtDelName);
    	deleteGridLayout.addComponent(lblDelDesc);
    	deleteGridLayout.addComponent(txtDelDesc);
    	deleteGridLayout.addComponent(lblDelCreDate);
    	deleteGridLayout.addComponent(txtDelCreDate);
    	deleteGridLayout.addComponent(lblDelCreator);
    	deleteGridLayout.addComponent(txtDelCreator);
    	deleteGridLayout.addComponent(lblDelParent);
    	deleteGridLayout.addComponent(txtDelParent);
    	deleteGridLayout.addComponent(lblDelModDate);
    	deleteGridLayout.addComponent(txtDelModDate);    	
    	deleteGridLayout.addComponent(btnDelete);    	

    	createGridLayout.setSizeFull();
    	createGridLayout.setMargin(true);
    	updateGridLayout.setSizeFull();
    	updateGridLayout.setMargin(true);
    	deleteGridLayout.setSizeFull();
    	deleteGridLayout.setMargin(true);
    	
    	categoryAccordionCRUD.setSizeFull();
    	categoryAccordionCRUD.setHeight("60%");
    	categoryAccordionCRUD.addTab(createGridLayout, "Create category");
    	categoryAccordionCRUD.addTab(updateGridLayout, "Update category");
    	categoryAccordionCRUD.addTab(deleteGridLayout, "Delete category");
    	
    }
    
	@Override
	public void itemClick(ItemClickEvent event) {
		
		selectedCategory = (CategoryVO) event.getItemId();
		populateCategoryCRUDFields();
		refreshPostView();
	}

	public void populateCategoryCRUDFields() {
		txtParent.setValue(selectedCategory.getCategoryName());

		txtUpdName.setValue(selectedCategory.getCategoryName());
		txtUpdDesc.setValue(selectedCategory.getCategoryDesc());
		txtUpdCreDate.setValue(selectedCategory.getCategoryCreateDate().toString());
		txtUpdCreator.setValue(selectedCategory.getCategoryCreator());
		txtUpdParent.setValue(selectedCategory.getCategoryParent());
		txtUpdModDate.setValue(selectedCategory.getCategoryModifiedDate().toString());
		
		txtDelName.setValue(selectedCategory.getCategoryName());
		txtDelDesc.setValue(selectedCategory.getCategoryDesc());
		txtDelCreDate.setValue(selectedCategory.getCategoryCreateDate().toString());
		txtDelCreator.setValue(selectedCategory.getCategoryCreator());
		txtDelParent.setValue(selectedCategory.getCategoryParent());
		txtDelModDate.setValue(selectedCategory.getCategoryModifiedDate().toString());
	}
	
    @Override
    public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
    	if(event.getSource() == btnCreate) {
    		createCategory();
    	} else if(event.getSource() == btnUpdate) {
    		updateCategory();
    	} else if(event.getSource() == btnDelete) {
    		deleteCategory();
    	}
    }
    public void createCategory() {
		if (txtParent.getValue().trim().length() == 0) {
			Notification.show("Error", "Please select a parent category from the tree", Notification.Type.ERROR_MESSAGE);
		} else if(txtName.getValue().trim().length() == 0) {
			Notification.show("Error", "Please enter a valid category name", Notification.Type.ERROR_MESSAGE);
		} else {
			btnCreate.setEnabled(false);
			String catName = txtName.getValue();
			String catDesc = txtDesc.getValue();
			String catParent = txtParent.getValue();
			categoryTree.addItem(catName, catDesc, catParent);
			resetFields();   			
			repaintTree();
			Notification.show("Info", "The category is now created",Notification.Type.HUMANIZED_MESSAGE);
			btnCreate.setEnabled(true);
		}
    }

    public void updateCategory() {
    	if(txtUpdName.getValue().trim().equals(selectedCategory.getCategoryName()) && 
    			txtUpdDesc.getValue().trim().equals(selectedCategory.getCategoryDesc())) {
    		Notification.show("No change", "No changes had been made to the selected category",Notification.Type.WARNING_MESSAGE);
    	} else if(txtUpdName.getValue().trim().equals("")){
    		Notification.show("No change", "Category name cannot be empty",Notification.Type.WARNING_MESSAGE);
    	} else {
    		String catUpdName = txtUpdName.getValue().trim();
    		String catUpdDesc = txtUpdDesc.getValue().trim();
    		categoryTree.updateItem(catUpdName, catUpdDesc, selectedCategory);
    		resetFields();
    		repaintTree();
    		Notification.show("Info", "The category is now updated",Notification.Type.HUMANIZED_MESSAGE);
    	}
    }
    
    public void deleteCategory() {
    	if(selectedCategory == null) {
    		Notification.show("Error", "Please select a category from the tree to be deleted", Notification.Type.ERROR_MESSAGE);
    	} else if(selectedCategory.getCategoryParent() == null) {
    		Notification.show("Error", "Root category cannot be deleted", Notification.Type.ERROR_MESSAGE);
    	} else {
    		categoryTree.deleteItem(selectedCategory);
    		repaintTree();
    		Notification.show("Info", "The category is now deleted",Notification.Type.HUMANIZED_MESSAGE);
    	}
    }
    
    public void repaintTree() {
    	tree=categoryTree.initialize();
    }
    
    public void refreshPostView() {
    	postView.initialize(selectedCategory);
    	postView.refreshPostViewGrid();
    }
    
    public void resetFields() {
    	
    	//Clears the "create category" fields
    	txtName.setValue("");
    	txtDesc.setValue("");
    	txtParent.setValue("");
    	
    	//Clears the "update category" fields
    	txtUpdName.setValue("");
    	txtUpdDesc.setValue("");
    	txtUpdCreDate.setValue("");
    	txtUpdCreator.setValue("");
    	txtUpdParent.setValue("");
    	txtUpdModDate.setValue("");
    	
    	//Clears the "delete category" fields
    	txtDelName.setValue("");
    	txtDelDesc.setValue("");
    	txtDelCreDate.setValue("");
    	txtDelCreator.setValue("");
    	txtDelParent.setValue("");
    	txtDelModDate.setValue("");
    	
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

	public PostView getPostView() {
		return postView;
	}

	public void setPostView(PostView postView) {
		this.postView = postView;
	}
	
}
