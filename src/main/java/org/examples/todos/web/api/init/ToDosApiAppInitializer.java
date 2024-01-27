package org.examples.todos.web.api.init;

import java.util.Objects;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

public class ToDosApiAppInitializer implements WebApplicationInitializer
{
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();

		applicationContext.setConfigLocation("classpath:org/examples/todos/web/api/config/applicationContext.xml");
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
		
		ServletRegistration.Dynamic registeredServlet = 
				servletContext.addServlet("ToDosDispatchServlet", dispatcherServlet);
		
		if (Objects.isNull(registeredServlet))
			registeredServlet = (ServletRegistration.Dynamic)servletContext.getServletRegistration("ToDosDispatchServlet");
		
		registeredServlet.addMapping("/main/*");
		registeredServlet.setLoadOnStartup(1);
		registeredServlet.setInitParameter("spring.profiles.active", "dev");
	}
}