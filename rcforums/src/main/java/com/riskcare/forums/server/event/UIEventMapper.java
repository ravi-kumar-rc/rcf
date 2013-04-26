package com.riskcare.forums.server.event;

import org.joda.time.DateTime;

import com.riskcare.forums.server.vo.CategoryVO;

public class UIEventMapper {
    public static final long LIFETIME = 1000L;

    //--------------------------- METHODS --------------------------------
    /**
     * Gets the file information for the specified snapshot and load task
     * @param vo
     * @param loadVO
     * @return UIEvent
     */
    public UIEvent createCategoryUIEvent(CategoryVO vo) {
        vo.setTimeToDie(new DateTime(DateTime.now().getMillis() + LIFETIME));
        return new UIEvent(vo);
    }
}
