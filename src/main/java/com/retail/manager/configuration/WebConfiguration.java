package com.retail.manager.configuration;

import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * This WebConfiguration class of project replacing web.xml as in case of spring boot
 * @author Pulkit garg
 *
 */
@Configuration
public class WebConfiguration {
	
	@Bean(name=DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME)
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}
	
	@Bean
	public ServletRegistrationBean dispatcherServletRegistration() {
		//registering dispatcher servlet
		ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet(), "/*");
		registration.addInitParameter("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
		registration.setLoadOnStartup(1);
		return registration;
	}
}
