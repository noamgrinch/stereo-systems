package com.grinch.ReceiversService.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.grinch.ReceiversService.Controllers.Handlers.ExceptionsHandler;



@Configuration
public class HTTPConfiguration {

	@Bean
	public ExceptionsHandler getExceptionsHandler() {
		return new ExceptionsHandler();
	}
}
