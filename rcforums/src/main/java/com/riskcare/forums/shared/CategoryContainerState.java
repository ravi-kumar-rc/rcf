package com.riskcare.forums.shared;

import com.vaadin.shared.AbstractComponentState;


public class CategoryContainerState extends AbstractComponentState {

	private static final long serialVersionUID = 1L;
	
	private CategoryContainer categoryContainer;
	
	public CategoryContainerState() {
	}

	public CategoryContainer getCategoryContainer() {
		return categoryContainer;
	}
	
	public void setCategoryContainer(CategoryContainer categoryContainer) {
		this.categoryContainer = categoryContainer;
	}
}
