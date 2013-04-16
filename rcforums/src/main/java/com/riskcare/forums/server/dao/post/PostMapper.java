package com.riskcare.forums.server.dao.post;

import com.riskcare.forums.server.entity.Post;
import com.riskcare.forums.server.vo.PostVO;


public class PostMapper {
	
	public PostVO entityToVO(Post entity) {
		return new PostVO(entity.getPostTitle(), entity.getPostDesc(), entity.getPostCreator(), entity.getPostCreatedDate(), 
				entity.getPostLabel(), entity.getLastModifiedBy(), entity.getPostModifiedDate(), entity.getPostCategory());
	}
	
	public Post voToEntity(PostVO vo) {
		return new Post(vo.getPostTitle(), vo.getPostDesc(), vo.getPostCreator(), vo.getPostCreatedDate(), vo.getPostLabel(),
				vo.getLastModifiedBy(), vo.getPostModifiedDate(), vo.getPostCategory());
	}
}
