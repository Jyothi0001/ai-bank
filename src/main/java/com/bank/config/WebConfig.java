package com.bank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	

	

	
	
//	    @Override
//	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	        registry.addResourceHandler("/images/**")
//	                .addResourceLocations("file:C:/images/")
		@Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	                 .allowedOrigins("http://localhost:4200")
	                //.allowedOrigins("https://your-frontend.onrender.com") https://vs-laundryservices-websit.onrender.com/
	                .allowedOriginPatterns("https://ai-bank-tw27.onrender.com") 
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	                .allowCredentials(true);
	    }
		
		




}
