package com.riskcare.forums.shared;

import org.joda.time.DateTime;

import com.riskcare.forums.server.vo.CategoryVO;
import com.vaadin.data.util.HierarchicalContainer;

public class CategoryContainer extends HierarchicalContainer {

	private static final long serialVersionUID = 1L;

	public CategoryContainer() {
		addContainerProperty("Category Name",String.class,"");
		addContainerProperty("Category Description", String.class, "");
		addContainerProperty("Category Creation Date", DateTime.class, null);
		addContainerProperty("Category Created By", String.class, "");
		addContainerProperty("Category Parent", String.class, "");
	}
	
	public void addItem(CategoryVO categoryVO) {
		addItem(categoryVO);
	}
	
	public boolean removeItem(CategoryVO categoryVO) {
		return removeItem(categoryVO);
	}
	
}