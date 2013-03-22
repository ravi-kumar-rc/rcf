package com.riskcare.forums.server.service;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riskcare.forums.client.request.CategoryCreationRpc;
import com.riskcare.forums.server.dao.category.CategoryDAO;
import com.riskcare.forums.server.dao.category.CategoryMapper;
import com.riskcare.forums.server.entity.Category;
import com.riskcare.forums.server.vo.CategoryVO;
import com.vaadin.ui.AbstractComponent;

public class CategoryCreationService extends AbstractComponent implements CategoryCreationRpc {
	
	private static Logger LOG = LoggerFactory.getLogger(CategoryCreationService.class);
	
	private static final long serialVersionUID = 1L;
	
    private AuthenticationService authenticationService;
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
    private CategoryDAO categoryDAO;

    public CategoryCreationService() {
    	registerRpc(this);
    }
    
    public void loadCategory(CategoryVO categoryVO) {
        Transaction transaction = null;
        try {
            Session session = categoryDAO.getSession();
            transaction = categoryDAO.startTransaction(session);
            Category category = new CategoryMapper().voToEntity(categoryVO);
            categoryDAO.save(category, session);
            categoryDAO.commitTransaction(transaction);
        } catch(Exception e) {
            LOG.error(e.getMessage());
        }
    }
    
    public boolean rootAvailable() {
        
        boolean rootAvailable = true;
        try {
            if(categoryDAO.findRoot() == null) {
                rootAvailable = false;
            }
        } catch(Exception e) {
            LOG.error(e.getMessage());
        }
        return rootAvailable;
    }
	
    public void buildRoot() {
        LOG.info("Validating root category");    	
        if(!categoryService.rootAvailable()) {
            CategoryVO vo = new CategoryVO("rcf", "root", DateTime.now(),authenticationService.getUser().getUsername(),"null");
            categoryService.loadCategory(vo);
        }
        LOG.info("Building the category tree...");
       
    }
    
	public void buildTree(String categoryName, String categoryDesc, String parent) {
        CategoryVO vo = new CategoryVO("rcf", "root", DateTime.now(),authenticationService.getUser().getUsername(),"null");
        categoryService.loadCategory(vo);
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
    
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
    
    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }	
	
}
