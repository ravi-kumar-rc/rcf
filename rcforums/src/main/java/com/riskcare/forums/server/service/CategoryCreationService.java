package com.riskcare.forums.server.service;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riskcare.forums.client.request.CategoryCreationRpc;
import com.riskcare.forums.server.dao.category.CategoryMapper;
import com.riskcare.forums.server.vo.CategoryVO;
import com.vaadin.ui.AbstractComponent;

public class CategoryCreationService extends AbstractComponent implements CategoryCreationRpc {
	
	private static Logger LOG = LoggerFactory.getLogger(CategoryCreationService.class);
	
	private static final long serialVersionUID = 1L;
	
    private AuthenticationService authenticationService;
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
	
    public CategoryCreationService() {
    	registerRpc(this);
    }
    
	public void buildTree(String categoryName, String categoryDesc, DateTime creationDate, String creator, String parent) {
        LOG.info("Validating root category");
        if(!categoryService.rootAvailable()) {
            CategoryVO vo = new CategoryVO("rcf", "root", DateTime.now(),authenticationService.getUser().getUsername(),"");
            categoryService.loadCategory(vo);
        }
        LOG.info("Building the category tree...");
	}
	
	public void setAuthenticationService(AuthenticationServiceImpl authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public CategoryService getCategoryService() {
		return categoryService;
	}
	
	public void setCategoryMapper(CategoryMapper categoryMapper) {
		this.categoryMapper = categoryMapper;
	}
	
	public CategoryMapper getCategoryMapper() {
		return categoryMapper;
	}
	
}
