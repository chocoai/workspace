package com.whty.wfd.main;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.whty.wfd.common.interceptor.MyInterceptor;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;

@Configuration
@ImportResource(locations = { "classpath:application-bean.xml" })
@SpringBootConfiguration
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class ApplicationConfig extends WebMvcConfigurerAdapter {

	@Bean
	public MyInterceptor myInterceptor() {
		return new MyInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor()).excludePathPatterns("/init").excludePathPatterns("/page/test/webTest").excludePathPatterns("/error.html")
				.excludePathPatterns("/static/*").excludePathPatterns("/error").addPathPatterns("/**");
		
		
		super.addInterceptors(registry);
	}

}