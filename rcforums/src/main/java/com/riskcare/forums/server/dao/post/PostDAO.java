package com.riskcare.forums.server.dao.post;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riskcare.forums.server.dao.GenericDAO;
import com.riskcare.forums.server.entity.Category;
import com.riskcare.forums.server.entity.Post;

public class PostDAO  extends GenericDAO<Post> {
	
	private static Logger LOG = LoggerFactory.getLogger(PostMapper.class);
	
	public PostDAO() {
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> findAll() {
		List<Post> posts = null;
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Post.class);
			posts = (List<Post>) criteria.list();
		} catch(Exception e) {
			LOG.error(e.getMessage(), e);
		}
		
		return posts;
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> findAll(Category category) {
		List<Post> posts = null;
		try {
			Session session = getSession();
			Criteria criteria = session.createCriteria(Post.class);
			criteria.add(Restrictions.eq("postCategory", category.getCategoryName()));
			posts = (List<Post>) criteria.list();			
		}catch(Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return posts;
	}
	
}
