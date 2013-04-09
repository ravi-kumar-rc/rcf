package com.riskcare.forums.server.container;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.riskcare.forums.client.RCFClientFactory;
import com.riskcare.forums.server.service.CategoryService;
import com.riskcare.forums.server.vo.CategoryVO;
import com.vaadin.data.util.HierarchicalContainer;


public class CategoryContainerController {

	private HierarchicalContainer categoryContainer = new HierarchicalContainer();
	
	private CategoryService categoryService;
	private RCFClientFactory clientFactory;
	private List<CategoryVO> categories = new ArrayList<CategoryVO>();
	
	public CategoryContainerController() {
		
	}
	
	public boolean setRootCategory(CategoryVO category) {
		categoryContainer.addItem(category);
		return categoryContainer.setParent(category, null);
	}
	
	public void addCategory(String name, String desc, String parent) {
		CategoryVO newCategory = new CategoryVO(name, desc, DateTime.now(), clientFactory.getAuthenticationService().getUser().getUsername(), parent);
		categoryService.loadCategory(newCategory);
	}
	
	public void removeCategory(String name, String desc, String parent) {
		//TODO: Implement this functionality at a later stage
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
	
	public HierarchicalContainer getCategoryContainer() {

		if(categoryService.rootAvailable()) {
			categories = categoryService.findAll();
		} else {
			CategoryVO rootCategory = new CategoryVO("RCF","Root Category",DateTime.now(),
					clientFactory.getAuthenticationService().getUser().getUsername(),null);
			categoryService.loadCategory(rootCategory);
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
					setRootCategory(temp);
				}
				temp = null;
			}
		}
		
		return categoryContainer;
	}

	public boolean attachToParent(CategoryVO categoryItem) {
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
	
	
}
