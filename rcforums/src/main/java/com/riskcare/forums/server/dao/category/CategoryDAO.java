package com.riskcare.forums.server.dao.category;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riskcare.forums.server.dao.GenericDAO;
import com.riskcare.forums.server.entity.Category;

public class CategoryDAO extends GenericDAO<Category> {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryDAO.class);
    
    public CategoryDAO() {
    }
    
	@SuppressWarnings("unchecked")
	public List<Category> findAll() {
        
        List<Category> categories = null;
        try {
            Session session = getSession();
            Criteria crit = session.createCriteria(Category.class);
            categories = (List<Category>)crit.list();
        } catch(Exception e) {
            LOG.error(e.getMessage());
        } finally {
        	closeSession();
        }
        return categories;
    }
    
    public Object findRoot() {
        Object root = null;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(Category.class);
            criteria.add(Restrictions.isNull("categoryParent"));
            root = criteria.uniqueResult(); 
        } catch(Exception e) {
            LOG.error(e.getMessage());
        } finally {
        	closeSession();
        }
        return root;
    }    
    
    public boolean isRoot(Category category) {
        Object root = null;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(Category.class);
            criteria.add(Restrictions.isNull("categoryParent"));
            criteria.add(Restrictions.eq("categoryName", category.getCategoryName()));
            root = criteria.uniqueResult();
            if(root != null) {
            	return true;
            }
        } catch(Exception e) {
            LOG.error(e.getMessage());
        } finally {
        	closeSession();
        }
    	return false;
    }
    
    /**
     * Updates a Category
     * @param item
     * @param session
     * 
     */
    public void updateCategory(Category actCategory, Category updCategory) throws Exception {
    	String actualCategoryName = actCategory.getCategoryName();
    	String updatedCategoryName = updCategory.getCategoryName();
    	String updatedCategoryDesc = updCategory.getCategoryDesc();
        Session session = getSession();
        List<Category> items = null;
        Category categoryToBeUpdated = null;
        try {
            Criteria criteria = session.createCriteria(Category.class);
            criteria.add(Restrictions.eq("categoryName",actualCategoryName));
            categoryToBeUpdated = (Category) criteria.uniqueResult();
            categoryToBeUpdated.setCategoryName(updatedCategoryName);
            categoryToBeUpdated.setCategoryDesc(updatedCategoryDesc);
            categoryToBeUpdated.setCategoryModifiedDate(DateTime.now());
            Criteria criteria1 = session.createCriteria(Category.class);
            criteria1.add(Restrictions.eq("categoryParent",actualCategoryName));
            items = (List<Category>) criteria1.list();
            if(items != null && items.size() > 0) { //category with children is being updated
            	for(Category temp : items) {
            		temp.setCategoryParent(updatedCategoryName);
            		temp.setCategoryModifiedDate(DateTime.now());
            	}
            	items.add(categoryToBeUpdated);
	            updateAll(items, session);
            } else { //category at leaf node is being updated and doesn't have any children
            	update(categoryToBeUpdated, session);
            }            
        } catch(Exception e) {
            LOG.error(e.getMessage());
        }
    }

    /**
     * Deletes a Category
     * @param item
     * @param session
     * 
     */
    @SuppressWarnings("unchecked")
	public void deleteCategory(Category category) {
    	String categoryName = category.getCategoryName();
        List<Category> items = null;
        Category categoryToBeDeleted = null;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(Category.class);
            criteria.add(Restrictions.eq("categoryName",categoryName));
            categoryToBeDeleted = (Category) criteria.uniqueResult();
            Criteria criteria1 = session.createCriteria(Category.class);
            criteria1.add(Restrictions.eq("categoryParent",categoryName));
            items = (List<Category>) criteria1.list();
            if(items != null && items.size() > 0) { //category with children is being deleted
	            items.add(categoryToBeDeleted);
	            deleteAll(items, session);
            } else { //category at leaf node is being deleted and doesn't have any children
            	delete(categoryToBeDeleted, session);
            }
        } catch(Exception e) {
            LOG.error(e.getMessage());
        }
    }
    
    public Category findCategory(Category category) {
    	Category foundCategory = null;
        try {
            Session session = getSession();
            Criteria criteria = session.createCriteria(Category.class);
            criteria.add(Restrictions.eq("categoryName",category.getCategoryName()));
            foundCategory = (Category) criteria.uniqueResult();
        } catch(Exception e) {
            LOG.error(e.getMessage());
        }
        return foundCategory;
    }
    
}
