package com.riskcare.forums.server.dao.category;

import com.riskcare.forums.server.entity.Category;
import com.riskcare.forums.server.vo.CategoryVO;

public class CategoryMapper {
    
    public CategoryVO entityToVo(Category entity) {
        return new CategoryVO(entity.getCategoryName(), entity.getCategoryDesc(), 
                entity.getCategoryCreateDate(), entity.getCategoryCreator(), entity.getCategoryParent(), entity.getCategoryModifiedDate());
    }
    
    public Category voToEntity(CategoryVO vo) {
        return new Category(vo.getCategoryName(), vo.getCategoryDesc(), vo.getCategoryCreateDate(),
                vo.getCategoryCreator(), vo.getCategoryParent(), vo.getCategoryModifiedDate());
    }
}
