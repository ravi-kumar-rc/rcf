package com.riskcare.forums.client.ui;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.riskcare.forums.server.dao.category.CategoryMapper;
import com.riskcare.forums.server.service.AuthenticationService;
import com.riskcare.forums.server.service.CategoryCreationService;
import com.riskcare.forums.server.service.CategoryService;
import com.riskcare.forums.server.vo.CategoryVO;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;
import com.vaadin.ui.Button;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@Connect(CategoryCreationService.class)
public class CategoryView extends AbstractComponentConnector implements Button.ClickListener {

    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(CategoryView.class);
    
    private VerticalLayout layout = new VerticalLayout();
    
    private Tree categoryTree = new Tree();
    
    private AuthenticationService authenticationService;
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    private final Button btnCreate = new Button("Create");
    
    public CategoryView() {
    }
    
    public VerticalLayout initialize() {
    	
    	buildCategoryTree();
    	
    	return layout;
    }
    
    public void buildCategoryTree() {
        btnCreate.addClickListener(this);
        layout.addComponent(btnCreate);
        layout.addComponent(categoryTree);
    }
    
    public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
        LOG.info("Validating root category");
        if(!categoryService.rootAvailable()) {
            CategoryVO vo = new CategoryVO("rcf", "root", DateTime.now(),authenticationService.getUser().getUsername(),"");
            categoryService.loadCategory(vo);
        }
        LOG.info("Building the category tree...");
    }
    
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    public CategoryService getCategoryService() {
        return categoryService;
    }

    public void setCategoryMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }
    
    public CategoryMapper getCategoryMapper() {
        return categoryMapper;
    }
    
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    
    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

}
