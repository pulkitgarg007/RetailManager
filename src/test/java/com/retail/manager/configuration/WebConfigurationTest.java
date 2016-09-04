package com.retail.manager.configuration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.web.servlet.DispatcherServlet;

@RunWith(MockitoJUnitRunner.class)
public class WebConfigurationTest {
    
    @InjectMocks
    private WebConfiguration webConfiguration;
    
    @Test
    public void shouldReturnDispatcherServletRegistration(){
        assertTrue(webConfiguration.dispatcherServletRegistration() instanceof ServletRegistrationBean);
        assertEquals("org.springframework.web.context.support.AnnotationConfigWebApplicationContext", 
                webConfiguration.dispatcherServletRegistration().getInitParameters().get("contextClass"));
    } 
    
    @Test
    public void shouldReturnDispatcherServlet(){
        assertTrue(webConfiguration.dispatcherServlet() instanceof DispatcherServlet);
    }

}
