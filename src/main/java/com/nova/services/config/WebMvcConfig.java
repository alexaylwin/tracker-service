package com.nova.services.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nova.services.SecurityHandlerInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	//@Autowired
	SecurityHandlerInterceptor securityInterceptor;
	
	@Override
	  public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**")
	    .allowCredentials(true)
	    .allowedOrigins("http://localhost:4200")
	    .allowedHeaders("*")
	    .allowedMethods("GET", "POST", "OPTIONS");
	  }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//registry.addInterceptor(securityInterceptor);
	}

}
