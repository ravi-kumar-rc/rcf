package com.riskcare.forums.server.service;

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
    
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
    
    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }
}
