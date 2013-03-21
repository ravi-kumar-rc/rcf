package com.riskcare.forums.server.dao.category;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riskcare.forums.server.dao.GenericDAO;
import com.riskcare.forums.server.entity.Category;

public class CategoryDAO extends GenericDAO<Category> {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryDAO.class);
    private final String RCF_CATEGORY_ROOT = "rcf";
    
    public List<Category> findAll() {
        
        List<Category> categories = null;
        try {
            Session session = getSession();
            Criteria crit = session.createCriteria(Category.class);
            categories = crit.list();
        } catch(Exception e) {
            LOG.error(e.getMessage());
        }
        return categories;
    }
    
    public Object findRoot() throws Exception {
        Session session = getSession();
        Object root = null;
        try {
            Criteria criteria = session.createCriteria(Category.class);
            criteria.add(Restrictions.eq("categoryName",RCF_CATEGORY_ROOT));
            root = criteria.uniqueResult(); 
        } catch(Exception e) {
            LOG.error(e.getMessage());
        }
        return root;
    }    
}
