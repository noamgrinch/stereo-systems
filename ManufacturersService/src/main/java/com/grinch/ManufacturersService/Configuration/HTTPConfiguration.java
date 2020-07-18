package com.grinch.ManufacturersService.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.grinch.ManufacturersService.Controllers.Handlers.ExceptionsHandler;

@Configuration
public class HTTPConfiguration {

	@Bean
	public ExceptionsHandler getExceptionsHandler() {
		return new ExceptionsHandler();
	}
	
	@Bean
	public RestTemplate getRestTemaplte() {
		return new RestTemplate();
	}
}
