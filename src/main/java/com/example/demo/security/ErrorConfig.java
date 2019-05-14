package com.example.demo.security;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

//@Configuration
public class ErrorConfig {
	
	/*@Bean
	public ConfigurableServletWebServerFactory customizer(){
		TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
		factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,"/err/400"));
		factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN,"/err/403"));
		factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/err/404"));
		factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/err/500"));
		return factory;
	}*/
	
}
