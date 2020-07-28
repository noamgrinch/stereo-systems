package com.grinch.OriginsService.Controllers.Services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grinch.OriginsService.BusinessLogic.Origin;
import com.grinch.OriginsService.Exceptions.ResourceNotFoundException;
import com.wirefreethought.geodb.client.GeoDbApi;
import com.wirefreethought.geodb.client.model.CountryResponse;
import com.wirefreethought.geodb.client.model.PopulatedPlacesResponse;
import com.wirefreethought.geodb.client.request.FindCountryRequest;
import com.wirefreethought.geodb.client.request.FindPlacesRequest;
import com.wirefreethought.geodb.client.request.PlaceRequestType;





@Service
public class OriginsService {
	
	@Autowired
	private GeoDbApi  geoDbApi;
	private static Logger logger = LogManager.getLogger(OriginsService.class);
	
	public boolean validateOrigin(Origin origin) throws Exception {
		logger.info("Validating origin" + origin.toString());
		CountryResponse countryResponse = null;
		PopulatedPlacesResponse cityResponse = null;
		try {
			countryResponse =  geoDbApi.findCountry(FindCountryRequest.builder().countryId(origin.getCountry()).build());
			if(countryResponse.getErrors()!=null&&countryResponse.getErrors().size()>0) {
				throw new Exception("There was an error validating the country code: " + countryResponse.getErrors().get(0).getMessage());
			}
			//Valid country code.
			Set<String> countrySet = new HashSet<String>();
			countrySet.add(origin.getCountry());
			cityResponse = geoDbApi.findPlaces(FindPlacesRequest.builder().
					countryIds(countrySet).
					namePrefix(origin.getCity()).
					types(Collections.singleton(PlaceRequestType.CITY)).build());
			//In case of zero matching cities, an empty Set is returned.
			if(cityResponse.getErrors()!=null&&cityResponse.getErrors().size()>0) {
				throw new Exception("There was an error validating the city: " + countryResponse.getErrors().get(0).getMessage());
			}
			//In case of a city matched, the first object in the set will be the target city(Set is ordered).
			if(cityResponse.getData().size()==0||(!cityResponse.getData().get(0).getCity().equals(origin.getCity()))) {
				throw new ResourceNotFoundException("City " + origin.getCity() + " was not found.");
			}
		}
		catch(com.wirefreethought.geodb.client.net.ApiException e) {
			throw new ResourceNotFoundException("Country " + origin.getCountry() + " was not found.");
		}
		
		return true;
	}
}
