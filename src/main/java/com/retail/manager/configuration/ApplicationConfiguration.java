package com.retail.manager.configuration;

import java.util.List;

import javax.xml.transform.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Import({ WebConfiguration.class })
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.retail.manager.*",}, useDefaultFilters = false, 
		excludeFilters = { @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = ApplicationConfiguration.class) }, 
		includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {
		Controller.class, Component.class, Repository.class }) })
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private ObjectMapper harnessObjectMapper;

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(harnessObjectMapper);

		converters.add(converter);

		addDefaultHttpMessageConverters(converters);
	}

	private void addDefaultHttpMessageConverters(
			List<HttpMessageConverter<?>> messageConverters) {
		// as per WebMvcConfigurationSupport.addDefaultHttpMessageConverters()

		StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		stringConverter.setWriteAcceptCharset(false);

		messageConverters.add(new ByteArrayHttpMessageConverter());
		messageConverters.add(stringConverter);
		messageConverters.add(new ResourceHttpMessageConverter());
		messageConverters.add(new SourceHttpMessageConverter<Source>());
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());
	}
	
	@Bean
    public RestTemplate restTemplate() { 
        return new RestTemplate();
    }
}
