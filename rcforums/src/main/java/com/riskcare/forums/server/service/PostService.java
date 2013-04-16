package com.riskcare.forums.server.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riskcare.forums.server.dao.category.CategoryDAO;
import com.riskcare.forums.server.dao.category.CategoryMapper;
import com.riskcare.forums.server.dao.post.PostDAO;
import com.riskcare.forums.server.dao.post.PostMapper;
import com.riskcare.forums.server.entity.Category;
import com.riskcare.forums.server.entity.Post;
import com.riskcare.forums.server.vo.CategoryVO;
import com.riskcare.forums.server.vo.PostVO;

public class PostService {
	
	private Logger LOG = LoggerFactory.getLogger(PostService.class);
	
	private CategoryDAO categoryDAO;
	private PostDAO postDAO;
	
	public void createPost(PostVO postVO) {
        Transaction transaction = null;
        
        try {
            Session session = postDAO.getSession();
            transaction = postDAO.startTransaction(session);
            Post post = new PostMapper().voToEntity(postVO);
            Criteria criteria = session.createCriteria(Category.class);
            criteria.add(Restrictions.eq("categoryName", post.getPostCategory().getCategoryName()));
            post.setPostCategory((Category)criteria.uniqueResult());
            postDAO.save(post, session);
            postDAO.commitTransaction(transaction);
        } catch(Exception e) {
        	if(postDAO == null) {
        		LOG.error("Category DAO is null");
        		LOG.error(e.getMessage());
        	}
        } finally {
        	postDAO.closeSession();
        }		
	}
	
	public List<PostVO> findAll(CategoryVO categoryVO) {
		List<PostVO> posts = new ArrayList<PostVO>();
		List<Post> records = null;
		Category category = new CategoryMapper().voToEntity(categoryVO);
		if(categoryDAO.isRoot(category)) {
			records = postDAO.findAll();
		} else {
			records = postDAO.findAll(category);
		}
		if(records == null) {
			LOG.error("Error in retrieving posts from database");
		} else {
			PostVO post;
			for(Post record: records) {
				post = new PostVO();
				post.setPostTitle(record.getPostTitle());
				post.setPostDesc(record.getPostDesc());
				post.setPostCreator(record.getPostCreator());
				post.setPostCreatedDate(record.getPostCreatedDate());
				post.setPostLabel(record.getPostLabel());
				post.setLastModifiedBy(record.getLastModifiedBy());
				post.setPostModifiedDate(record.getPostModifiedDate());
				post.setPostCategory(record.getPostCategory());
				posts.add(post);
			}
		}
		return posts;
	}

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public PostDAO getPostDAO() {
		return postDAO;
	}

	public void setPostDAO(PostDAO postDAO) {
		this.postDAO = postDAO;
	}
	
}
