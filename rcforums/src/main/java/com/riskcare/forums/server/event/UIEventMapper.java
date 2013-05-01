package com.riskcare.forums.server.event;

import org.joda.time.DateTime;

import com.riskcare.forums.server.vo.CategoryVO;
import com.riskcare.forums.server.vo.PostVO;

public class UIEventMapper {
    public static final long LIFETIME = 1000L;

    public UIEvent createCategoryUIEvent(CategoryVO vo) {
        vo.setTimeToDie(new DateTime(DateTime.now().getMillis() + LIFETIME));
        return new UIEvent(vo);
    }
    
    public UIEvent createPostUIEvent(PostVO vo) {
    	vo.setTimeToDie(new DateTime(DateTime.now().getMillis() + LIFETIME));
    	return new UIEvent(vo);
    }
}
