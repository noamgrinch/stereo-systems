package com.grinch.SpeakersService.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.grinch.SpeakersService.Controllers.Handlers.ExceptionsHandler;

@Configuration
public class HTTPConfiguration {

	@Bean
	public ExceptionsHandler getExceptionsHandler() {
		return new ExceptionsHandler();
	}
}
