package com.westernacher.demo.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DemoInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {
	
	@Override
	protected Class<?>[] getRootConfigClasses() {		
		return new Class<?>[] {};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {		
		return new Class<?>[] {DemoWebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
}