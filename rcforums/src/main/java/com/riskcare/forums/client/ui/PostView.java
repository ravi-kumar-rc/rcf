package com.riskcare.forums.client.ui;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;

import com.riskcare.forums.server.container.PostContainerController;
import com.riskcare.forums.server.vo.CategoryVO;
import com.riskcare.forums.server.vo.PostVO;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Runo;

@Scope("session")
public class PostView implements Button.ClickListener {

	private static final long serialVersionUID = 1L;
	
	//Components for the post management accordion
    //--------------------START----------------------------    
    private Accordion postAccordionCRUD = new Accordion();
    private GridLayout postLayout = new GridLayout(2,4);
    private final Label lblPostTitle = new Label("Title: ");
    private final TextField txtPostTitle = new TextField();
    private final Label lblPostDesc = new Label("Description: ");
    private final TextArea txtPostDesc = new TextArea();
    private final Label lblPostLabel = new Label("Labels: ");
    private final TextField txtPostLabel = new TextField();
//    private final Label lblCategory = new Label("Category: ");
//    private TextField txtCategory = new TextField();
    private Button btnCreate = new Button("Create");
    //---------------------END-----------------------------

    private Table tablePostView = new Table();
    
    private PostContainerController postContainerController;
    private BeanItemContainer<PostVO> postContainer;
    private CategoryVO selectedCategory;
    
    public PostView() {
    }
    
    public void initialize(CategoryVO selectedCategory) {
    	
    	this.selectedCategory = selectedCategory;

    	// txtCategory.setEnabled(false);
    	

    }

    public Accordion buildPostCRUDAccordion() {
    	postLayout.setSpacing(true);
    	postLayout.setHeight("100%");
    	postLayout.setWidth("100%");
    	postLayout.setMargin(true);
    	
    	postLayout.addComponent(lblPostTitle); postLayout.addComponent(txtPostTitle);
    	postLayout.addComponent(lblPostDesc); postLayout.addComponent(txtPostDesc);
    	postLayout.addComponent(lblPostLabel); postLayout.addComponent(txtPostLabel);
    	// postLayout.addComponent(lblCategory); postLayout.addComponent(txtCategory);
    	postLayout.addComponent(btnCreate);
    	
    	btnCreate.addClickListener(this);
    	
    	postAccordionCRUD.addTab(postLayout, "Create Post");
    	
    	return postAccordionCRUD;
    	
    }
    
    public Table buildPostViewGrid() {
    	tablePostView.setHeight("100%");
    	tablePostView.setWidth("100%");
    	tablePostView.setImmediate(true);
    	tablePostView.addStyleName(Runo.TABLE_SMALL);
    	
    	tablePostView.addContainerProperty("Title", String.class, null);
    	tablePostView.addContainerProperty("Description", String.class, null);
    	tablePostView.addContainerProperty("Created By", String.class, null);
    	tablePostView.addContainerProperty("Created Date", DateTime.class, null);
    	tablePostView.addContainerProperty("Tags", String.class, null);
    	tablePostView.addContainerProperty("Last Modified By", String.class, null);
    	tablePostView.addContainerProperty("Last Modified Date", DateTime.class, null);
    	tablePostView.addContainerProperty("Category", CategoryVO.class, null);
    	
    	refreshPostViewGrid();
    	return tablePostView;
    }
    
    public void refreshPostViewGrid() {
    	if(selectedCategory != null) {
	    	postContainer = postContainerController.getPostContainer(selectedCategory);
	    	List<PostVO> posts = postContainer.getItemIds();
	    	if(posts != null && posts.size() != 0) { 
				tablePostView.setContainerDataSource(postContainer);    	
	    	}
	    	tablePostView.setCaption("Posts in category: " + selectedCategory);
    	}
    }
    
	@Override
	public void buttonClick(ClickEvent event) {
		if(event.getSource() == btnCreate) {
			if(selectedCategory == null) {
				Notification.show("Error", "No category selected", Notification.Type.ERROR_MESSAGE);
			} else {
				String postTitle = txtPostTitle.getValue(); 
				String postDesc = txtPostDesc.getValue(); 
				String postLabel = txtPostLabel.getValue();
				CategoryVO postCategory = selectedCategory;				
				postContainerController.createPost(postTitle, postDesc, postLabel, postCategory);
				refreshPostViewGrid();
			}
		}
	}

	public PostContainerController getPostContainerController() {
		return postContainerController;
	}

	public void setPostContainerController(
			PostContainerController postContainerController) {
		this.postContainerController = postContainerController;
	}
    
}
