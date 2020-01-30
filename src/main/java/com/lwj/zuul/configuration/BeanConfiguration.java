package com.lwj.zuul.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lwj.zuul.filter.DemoFilter;
import com.lwj.zuul.filter.ErrorFilter;
import com.lwj.zuul.filter.IpFilter;

@Configuration
public class BeanConfiguration {

	@Bean
	public IpFilter ipFilter() {
		return new IpFilter();
	}
	
	@Bean
	public DemoFilter demoFilter() {
		return new DemoFilter();
	}
	
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	
}
