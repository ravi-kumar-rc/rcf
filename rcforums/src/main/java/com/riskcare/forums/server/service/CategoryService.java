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
    
    public synchronized void createCategory(CategoryVO categoryVO) {
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
        } finally {
        	categoryDAO.closeSession();
        }
    }
    
    public synchronized void updateCategory(CategoryVO actualCategory, CategoryVO updatedCategory) {
        Transaction transaction = null;
        
        try {
            Session session = categoryDAO.getSession();
            transaction = categoryDAO.startTransaction(session);
            Category actCategory = new CategoryMapper().voToEntity(actualCategory);
            Category updCategory = new CategoryMapper().voToEntity(updatedCategory);
            categoryDAO.updateCategory(actCategory, updCategory);
            categoryDAO.commitTransaction(transaction);
        } catch(Exception e) {
        	if(categoryDAO == null) {
        		LOG.error("Category DAO is null");
        		LOG.error(e.getMessage());
        	}
        } finally {
        	categoryDAO.closeSession();
        }
    }
    
    public synchronized void deleteCategory(CategoryVO category) {
        Transaction transaction = null;
        
        try {
            Session session = categoryDAO.getSession();
            transaction = categoryDAO.startTransaction(session);    		
            Category delCategory = new CategoryMapper().voToEntity(category);
    		categoryDAO.deleteCategory(delCategory);
    		categoryDAO.commitTransaction(transaction);
    	} catch(Exception e) {
    		if(categoryDAO == null) {
    			LOG.error("Category DAO is null");
    			LOG.error(e.getMessage());
    		}
    	} finally {
        	categoryDAO.closeSession();
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
    		categoryVO.setId(category.getId());
    		categoryVO.setCategoryName(category.getCategoryName());
    		categoryVO.setCategoryDesc(category.getCategoryDesc());
    		categoryVO.setCategoryCreateDate(category.getCategoryCreateDate());
    		categoryVO.setCategoryCreator(category.getCategoryCreator());
    		categoryVO.setCategoryParent(category.getCategoryParent());
    		categoryVO.setCategoryModifiedDate(category.getCategoryModifiedDate());
    		categories.add(categoryVO);
    	}
    	
    	return categories;
    }
    
    public synchronized List<CategoryVO> findChildCategories(CategoryVO category) {
    	List<CategoryVO> categories = new ArrayList<CategoryVO>();
    	Category parentCategory =  new CategoryMapper().voToEntity(category);
    	List<Category> records = categoryDAO.findChildCategories(parentCategory);
    	
    	CategoryVO categoryVO;
    	for(Category record: records) {
    		categoryVO = new CategoryVO();
    		categoryVO.setId(record.getId().longValue());
    		categoryVO.setCategoryName(record.getCategoryName());
    		categoryVO.setCategoryDesc(record.getCategoryDesc());
    		categoryVO.setCategoryCreateDate(record.getCategoryCreateDate());
    		categoryVO.setCategoryCreator(record.getCategoryCreator());
    		categoryVO.setCategoryParent(record.getCategoryParent());
    		categoryVO.setCategoryModifiedDate(record.getCategoryModifiedDate());
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
