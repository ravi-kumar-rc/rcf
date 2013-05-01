package com.riskcare.forums.client.ui;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.github.wolfie.refresher.Refresher;
import com.github.wolfie.refresher.Refresher.RefreshListener;
import com.riskcare.forums.client.RCFClientFactory;
import com.riskcare.forums.server.container.CategoryContainerController;
import com.riskcare.forums.server.event.UIEvent;
import com.riskcare.forums.server.vo.CategoryVO;
import com.vaadin.data.util.HierarchicalContainer;
import com.vaadin.ui.Tree;

@Scope("session")
public class CategoryTree implements RefreshListener {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(CategoryTree.class);
	
	private RCFClientFactory clientFactory;
	private CategoryContainerController categoryContainerController;
	
	private final Tree tree = new Tree();
	private HierarchicalContainer categoryContainer;
	private HierarchicalContainer updatedCategoryContainer;
	
	public CategoryTree() {
	}
	
	public Tree initialize() {

		tree.setImmediate(true);
		LOG.debug("Category container controller : " + categoryContainerController);
		categoryContainer = categoryContainerController.getCategoryContainer();
		expandTree();
		return tree;
	}
	
	public void addItem(String name, String desc, String parent) {
		categoryContainerController.addCategory(name, desc, parent);
	}
	
	public void updateItem(String name, String desc, CategoryVO actualCategory) {
		categoryContainerController.updateCategory(name, desc, actualCategory);	
	}
	
	public void deleteItem(CategoryVO category) {
		categoryContainerController.deleteCategory(category);
	}
	
	public CategoryContainerController getCategoryContainerController() {
		return categoryContainerController;
	}

	public void setCategoryContainerController(CategoryContainerController categoryContainerController) {
		this.categoryContainerController = categoryContainerController;
	}

	public void expandTree() {
		tree.setContainerDataSource(categoryContainer);
		CategoryVO rootCategory = categoryContainerController.rootItemIds().get(0);
		tree.expandItemsRecursively(rootCategory);
	}
	
	@Override
	public void refresh(Refresher source) {
		updatedCategoryContainer = categoryContainerController.getCategoryContainer();
		List<UIEvent> events = clientFactory.getStatusService().getUIUpdates(); 
		//Tree refresh is triggered only when there is an update available on any of the categories
		if(events.size() != 0) {
			for(UIEvent event: events) {
				if(event.getCategoryVO() != null) {
					categoryContainer = updatedCategoryContainer;
					expandTree();
					break;
				}
			}
		}
	}

	public RCFClientFactory getClientFactory() {
		return clientFactory;
	}

	public void setClientFactory(RCFClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
}