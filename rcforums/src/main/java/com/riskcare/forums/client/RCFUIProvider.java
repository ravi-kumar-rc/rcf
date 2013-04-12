package com.riskcare.forums.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Scope("request")
public class RCFUIProvider extends UIProvider {

	private static final long serialVersionUID = 1L;

	protected static final String BEAN_NAME_PARAMETER = "UIBean";
	
	public RCFUIProvider() {
	}
	
	
	@Override
	public UI createInstance(UICreateEvent event) {
		return (UI) SpringApplicationContext.getApplicationContext().getBean(getUIBeanName(event.getRequest()));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
		ApplicationContext context = SpringApplicationContext.getApplicationContext();
		if(context != null) {
			return (Class<? extends UI>) context.getType(getUIBeanName(event.getRequest()));
		}
		else {
			return null;
		}
	}
	
	@Override
	public boolean isPreservedOnRefresh(UICreateEvent event) {
		if(!SpringApplicationContext.getApplicationContext().isPrototype(getUIBeanName(event.getRequest()))) {
			return true;
		}
		
		return super.isPreservedOnRefresh(event);
	}
	
	protected String getUIBeanName(VaadinRequest request) {
		String vaadinBeanName = "UI";
		
		Object uiBeanName = request.getService().getDeploymentConfiguration().getApplicationOrSystemProperty(BEAN_NAME_PARAMETER, null);
		if(uiBeanName != null && uiBeanName instanceof String) {
			vaadinBeanName = uiBeanName.toString();
		}
		
		return vaadinBeanName;
	}
	/*
	@Override
	public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
		VaadinRequest request = event.getRequest();
		Object uiBeanNameObj = request.getService().getDeploymentConfiguration().getApplicationOrSystemProperty("UIBean", null);
		if(uiBeanNameObj instanceof String) {
			String uiBeanName = uiBeanNameObj.toString();
			final ApplicationContext applicationContext = ApplicationContextLocator.getApplicationContext();
			final Class<? extends UI> bean = (Class<? extends UI>) applicationContext.getType(uiBeanName);
			if(bean != null) {
				return bean;
			} else {
				ClassLoader loader = request.getService().getClassLoader();
				try {
					Class<? extends UI> uiClass = Class.forName(uiBeanName, true, loader).asSubclass(UI.class);
					return uiClass;
				} catch(ClassNotFoundException e) {
					throw new RuntimeException("Could not find UI class");
				}
			}
		}
		return null;
	}

	@Override
	public UI createInstance(UICreateEvent event) {
        VaadinRequest request = event.getRequest();
        Object uiBeanNameObj = request
                .getService()
                .getDeploymentConfiguration()
                .getApplicationOrSystemProperty("UIBean",
                        null);

        //Stored in VaadinSession to use it in
        // the ApplicationScope later to initialize vaadin application scope beans
        final Integer uiId = event.getUiId();
        VaadinSession.getCurrent().setAttribute("sessionScope.UiId", uiId);

        if (uiBeanNameObj instanceof String) {
            String uiBeanName = uiBeanNameObj.toString();
            return (UI) ApplicationContextLocator.getBean(uiBeanName);
        }
        return super.createInstance(event);
	}*/
}
