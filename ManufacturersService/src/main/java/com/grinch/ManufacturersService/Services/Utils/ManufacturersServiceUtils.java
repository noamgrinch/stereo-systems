package com.grinch.ManufacturersService.Services.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ManufacturersServiceUtils {

		@Autowired
		private RestTemplate restTemplate;
}
