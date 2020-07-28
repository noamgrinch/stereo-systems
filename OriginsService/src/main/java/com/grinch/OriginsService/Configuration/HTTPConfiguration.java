package com.grinch.OriginsService.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.wirefreethought.geodb.client.GeoDbApi;
import com.wirefreethought.geodb.client.model.GeoDbInstanceType;
import com.wirefreethought.geodb.client.net.GeoDbApiClient;

@Configuration
public class HTTPConfiguration {
	
	
	@Bean
	public GeoDbApiClient getGeoDbApiClient() {
		return new GeoDbApiClient(GeoDbInstanceType.FREE);
	}
	
	@Bean
	public GeoDbApi getGeoDbApi(GeoDbApiClient apiClient) {
		return new GeoDbApi(apiClient);
	}

}
