package com.ihours.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	 
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
	return new Class[]{DataBaseConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
	return new Class[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
	return new String[]{"/"};

	}

	}