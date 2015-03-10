package com.esa.infocontrol.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan(basePackages = "com.esa.infocontrol.spring.configuration")
public class AppConfig {
	
	@Bean
	public MultipartResolver multipartResolver(){
		MultipartResolver r = new CommonsMultipartResolver();
		return r;
	}

}
