package com.whty.mxbj.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.whty.mxbj.api.interceptor.RequestInterceptor;

@Configuration
@ImportResource(locations = { "classpath:application-bean.xml" })
public class ApplicationConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/api/**");
		super.addInterceptors(registry);
	}
}
