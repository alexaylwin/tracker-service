package com.nova.services.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Override
	  public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**")
	    .allowCredentials(true)
	    .allowedOrigins("http://localhost:4200")
	    .allowedHeaders("*")
	    .allowedMethods("GET", "POST", "OPTIONS");
	  }
	
}
