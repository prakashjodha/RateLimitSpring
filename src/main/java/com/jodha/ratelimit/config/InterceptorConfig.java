package com.jodha.ratelimit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jodha.ratelimit.interceptor.RateLimitHandler;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	RateLimitHandler rateLimitHandler;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(rateLimitHandler);
	}
}
