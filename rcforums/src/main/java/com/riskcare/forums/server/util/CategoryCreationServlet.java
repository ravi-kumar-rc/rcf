package com.riskcare.forums.server.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class CategoryCreationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        final ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());

//        CategoryEvent event = new CategoryEvent(this, );
//        CategoryCreationPublisher publisher = (CategoryCreationPublisher) context.getBean("fileUploadPublisher");
//        publisher.publish(event);
        
    }
}
