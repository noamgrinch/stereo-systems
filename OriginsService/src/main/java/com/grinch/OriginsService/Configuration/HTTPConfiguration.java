package com.grinch.OriginsService.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HTTPConfiguration {
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
