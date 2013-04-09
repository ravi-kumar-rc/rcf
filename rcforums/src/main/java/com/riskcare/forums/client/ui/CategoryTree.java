package com.riskcare.forums.client.ui;

import com.riskcare.forums.server.container.CategoryContainerController;
import com.riskcare.forums.server.vo.CategoryVO;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.Tree;

public class CategoryTree {

	private CategoryContainerController categoryContainerController;
	
	private final Tree tree = new Tree();
	private HierarchicalContainer categoryContainer;
	
	public CategoryTree() {
	}
	
	public Tree initialize() {

		tree.setImmediate(true);
		categoryContainer = categoryContainerController.getCategoryContainer();
		CategoryVO rootCategory = categoryContainerController.rootItemIds().get(0);
		tree.setContainerDataSource(categoryContainer);
		tree.expandItemsRecursively(rootCategory);
		return tree;
	}
	
	public void addItem(String name, String desc, String parent) {
		categoryContainerController.addCategory(name, desc, parent);
	}
	
	public CategoryContainerController getCategoryContainerController() {
		return categoryContainerController;
	}

	public void setCategoryContainerController(CategoryContainerController categoryContainerController) {
		this.categoryContainerController = categoryContainerController;
	}

}
