package com.riskcare.forums.server.container;

import java.util.List;

import org.joda.time.DateTime;

import com.riskcare.forums.client.RCFClientFactory;
import com.riskcare.forums.server.dao.category.CategoryMapper;
import com.riskcare.forums.server.entity.Category;
import com.riskcare.forums.server.service.PostService;
import com.riskcare.forums.server.vo.CategoryVO;
import com.riskcare.forums.server.vo.PostVO;
import com.vaadin.data.util.BeanItemContainer;

public class PostContainerController {

	private final BeanItemContainer<PostVO> postContainer = new BeanItemContainer<PostVO>(PostVO.class);
	
	private PostService postService;
	private RCFClientFactory clientFactory;
	
	private List<PostVO> posts = null;
	
	public PostContainerController() {
		
	}
	
	public void createPost(String postTitle, String postDesc, String postLabel, CategoryVO categoryVO) {

		String username = clientFactory.getAuthenticationService().getUser().getUsername();
		
		Category category = new CategoryMapper().voToEntity(categoryVO);
		
		PostVO post = new PostVO(postTitle, postDesc, username, DateTime.now(), postLabel, username, DateTime.now(), category);
		postService.createPost(post);
	}

	public BeanItemContainer<PostVO> getPostContainer(CategoryVO categoryVO) {
		postContainer.removeAllItems();
		posts = postService.findAll(categoryVO);
		postContainer.addAll(posts);
		return postContainer;
	}

	public PostService getPostService() {
		return postService;
	}

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public RCFClientFactory getClientFactory() {
		return clientFactory;
	}

	public void setClientFactory(RCFClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}
	
}


