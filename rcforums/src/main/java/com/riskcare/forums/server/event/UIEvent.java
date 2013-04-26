package com.riskcare.forums.server.event;

import java.io.Serializable;

import com.riskcare.forums.server.vo.CategoryVO;
import com.riskcare.forums.server.vo.PostVO;

public class UIEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private CategoryVO categoryVO;
	private PostVO postVO;
	
	public UIEvent(CategoryVO categoryVO) {
		this.categoryVO = categoryVO;
	}
	
	public UIEvent(PostVO postVO) {
		this.postVO = postVO;
	}

	public CategoryVO getCategoryVO() {
		return categoryVO;
	}

	public PostVO getPostVO() {
		return postVO;
	}
}
