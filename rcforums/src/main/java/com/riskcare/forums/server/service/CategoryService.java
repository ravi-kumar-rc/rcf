package com.riskcare.forums.server.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riskcare.forums.server.dao.category.CategoryDAO;
import com.riskcare.forums.server.dao.category.CategoryMapper;
import com.riskcare.forums.server.entity.Category;
import com.riskcare.forums.server.vo.CategoryVO;

public class CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);
    
    private CategoryDAO categoryDAO;
    
    public synchronized void loadCategory(CategoryVO categoryVO) {
        Transaction transaction = null;
        
        try {
            Session session = categoryDAO.getSession();
            transaction = categoryDAO.startTransaction(session);
            Category category = new CategoryMapper().voToEntity(categoryVO);
            categoryDAO.save(category, session);
            categoryDAO.commitTransaction(transaction);
        } catch(Exception e) {
        	if(categoryDAO == null) {
        		LOG.error("Category DAO is null");
        		LOG.error(e.getMessage());
        	}
        }
    }
    
    public boolean rootAvailable() {
        
        boolean rootAvailable = false;
        try {
            if(categoryDAO.findRoot() != null) {
                rootAvailable = true;
            }
        } catch(Exception e) {
        	if(categoryDAO == null) {
        		LOG.error("Category DAO is null");
        	}
        }
        return rootAvailable;
    }
    
    public synchronized List<CategoryVO> findAll() {
    	List<CategoryVO> categories = new ArrayList<CategoryVO>();
    	List<Category> records = categoryDAO.findAll();
    	if(records == null) {
    		LOG.info("Categories returned is null..");
    	}
    	
    	CategoryVO categoryVO;
    	for(Category category: records) {
    		categoryVO = new CategoryVO();
    		categoryVO.setCategoryName(category.getCategoryName());
    		categoryVO.setCategoryDesc(category.getCategoryDesc());
    		categoryVO.setCategoryDate(category.getCategoryDate());
    		categoryVO.setCategoryCreator(category.getCategoryCreator());
    		categoryVO.setCategoryParent(category.getCategoryParent());
    		categories.add(categoryVO);
    	}
    	
    	return categories;
    }
    
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
    
    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }
}
