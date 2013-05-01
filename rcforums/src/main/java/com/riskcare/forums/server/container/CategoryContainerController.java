package com.riskcare.forums.server.container;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.riskcare.forums.client.RCFClientFactory;
import com.riskcare.forums.server.event.UIEventMapper;
import com.riskcare.forums.server.service.CategoryService;
import com.riskcare.forums.server.service.StatusService;
import com.riskcare.forums.server.vo.CategoryVO;
import com.vaadin.data.util.HierarchicalContainer;

@Scope("session")
public class CategoryContainerController {

	private static final Logger LOG = LoggerFactory.getLogger(CategoryContainerController.class);
	
	private HierarchicalContainer categoryContainer;

	private CategoryService categoryService;
	private RCFClientFactory clientFactory;
	private StatusService statusService;
	private List<CategoryVO> categories = new ArrayList<CategoryVO>();
	
	public CategoryContainerController() {
		
	}
	
	
	public synchronized void addCategory(String name, String desc, String parent) {
		LOG.debug("Adding a new category with name: " + name);
		CategoryVO newCategory = new CategoryVO(name, desc, DateTime.now(), clientFactory.getAuthenticationService().getUser().getUsername(), 
				parent, DateTime.now());
		categoryService.createCategory(newCategory);
		LOG.debug("Added new category with name: " + name);
		statusService.setUpdates(name, new UIEventMapper().createCategoryUIEvent(newCategory));
		LOG.debug("Added new category event: " + name);
	}
	
	public synchronized void updateCategory(String name, String desc, CategoryVO actualCategory) {
		LOG.debug("Updating category with name: " + actualCategory.getCategoryName());
		CategoryVO updatedCategory = new CategoryVO(name, desc, actualCategory.getCategoryCreateDate(), actualCategory.getCategoryCreator(), 
				actualCategory.getCategoryParent(), DateTime.now());
		categoryService.updateCategory(actualCategory, updatedCategory);
		LOG.debug("Updated category with name: " + actualCategory.getCategoryName() + " to " + name + " and its description to " + desc);
		statusService.setUpdates(name, new UIEventMapper().createCategoryUIEvent(updatedCategory));
		LOG.debug("Added update category event: " + name);		
	}
	
	public synchronized void deleteCategory(CategoryVO category) {
		LOG.debug("Deleting category " + category.getCategoryName());
		categoryService.deleteCategory(category);
		LOG.debug("Deleted category " + category.getCategoryName());
		statusService.setUpdates(category.getCategoryName(), new UIEventMapper().createCategoryUIEvent(category));
		LOG.debug("Added new category event: " + category.getCategoryName());		
	}
	
	public List<CategoryVO> rootItemIds()  {
		
		List<CategoryVO> root = new ArrayList<CategoryVO>();
		for(CategoryVO category : categories) {
			if(category.getCategoryParent() == null) {
				root.add(category);
			}
		}
		
		return root;
	}
	
	public synchronized HierarchicalContainer getCategoryContainer() {

		categoryContainer = new HierarchicalContainer();
		
		if(categoryService.rootAvailable()) {
			categories = categoryService.findAll();
		} else {
			CategoryVO rootCategory = new CategoryVO("RCF","Root Category",DateTime.now(),
					clientFactory.getAuthenticationService().getUser().getUsername(),null, DateTime.now());
			categoryService.createCategory(rootCategory);
			categories = categoryService.findAll();
		}		
		
		categoryContainer.removeAllItems();
		categoryContainer.addContainerProperty("caption", CategoryVO.class, "");
		CategoryVO temp = null;

		while(categoryContainer.size() != categories.size()) {
			for(CategoryVO category : categories) {
				temp=category;
				if(temp.getCategoryParent() != null) {
					attachToParent(temp);	//attaches the specified category to its parent
				} else {
					categoryContainer.addItem(temp);
					categoryContainer.setParent(temp, null);
				}
				temp = null;
			}
		}
		
		return categoryContainer;
	}

	public synchronized boolean attachToParent(CategoryVO categoryItem) {
		CategoryVO temp = null;
		
		for(CategoryVO category : categories) {
			temp = category;
			if(categoryItem.getCategoryParent().equals(temp.getCategoryName())) {
				categoryContainer.addItem(categoryItem);
				categoryContainer.setParent(categoryItem, temp);
			}
			
			temp = null;
		}
		return false;
	}

	public synchronized List<CategoryVO> getChildCategories(CategoryVO parentCategory) {

		List<CategoryVO> childCategories = new ArrayList<CategoryVO>();
		List<CategoryVO> interChildCategories = categoryService.findChildCategories(parentCategory);
		
		childCategories.add(parentCategory);
		
		if(interChildCategories.size() > 0 && interChildCategories != null) {
			childCategories.addAll(interChildCategories);
			
			List<CategoryVO> temp;
			while(interChildCategories.size() > 0 && interChildCategories != null) {
				temp = new ArrayList<CategoryVO>();
				for(CategoryVO childCategory: interChildCategories) {
					temp.addAll(categoryService.findChildCategories(childCategory));
					if(temp != null || temp.size() > 0) {
						childCategories.addAll(temp);
					}
				}
				interChildCategories = temp;
				temp = null;
			}
		}
		return childCategories;
		
	}
	
	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public RCFClientFactory getClientFactory() {
		return clientFactory;
	}

	public void setClientFactory(RCFClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
	public StatusService getStatusService() {
		return statusService;
	}

	public void setStatusService(StatusService statusService) {
		this.statusService = statusService;
	}
}
