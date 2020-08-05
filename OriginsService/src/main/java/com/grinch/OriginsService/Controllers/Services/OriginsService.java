package com.grinch.OriginsService.Controllers.Services;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Throwables;
import com.grinch.OriginsService.OriginRequest;
import com.grinch.OriginsService.OriginResponse;
import com.grinch.OriginsService.OriginsServiceGrpc.OriginsServiceImplBase;
import com.grinch.OriginsService.BusinessLogic.Origin;
import com.grinch.OriginsService.Exceptions.ResourceNotFoundException;
import com.grinch.OriginsService.Exceptions.StereoFiException;
import com.wirefreethought.geodb.client.GeoDbApi;
import com.wirefreethought.geodb.client.model.CountryResponse;
import com.wirefreethought.geodb.client.model.PopulatedPlacesResponse;
import com.wirefreethought.geodb.client.request.FindCountryRequest;
import com.wirefreethought.geodb.client.request.FindPlacesRequest;
import com.wirefreethought.geodb.client.request.PlaceRequestType;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;





@GrpcService
public class OriginsService extends OriginsServiceImplBase{
	
	@Autowired
	private GeoDbApi  geoDbApi;
	private static Logger logger = LogManager.getLogger(OriginsService.class);
	
    @Override
    public void validate(OriginRequest request, StreamObserver<OriginResponse> responseObserver){
        Origin origin = new Origin();
        origin.setCountry(request.getCountry());
        origin.setCity(request.getCity());
        OriginResponse response = null;
		try {
			response = OriginResponse.newBuilder().setResult(this.validateOrigin(origin)).build();
	        responseObserver.onNext(response);
	        responseObserver.onCompleted();       
		} 
		catch(com.wirefreethought.geodb.client.net.ApiException e) {
			logger.warn("Oirigin does not exists: " + e.getMessage());
			response = OriginResponse.newBuilder().setResult(false).build();
			responseObserver.onError(new StatusRuntimeException(Status.NOT_FOUND));
		}
		catch (Exception e) {
			logger.error(Throwables.getStackTraceAsString(e));
			response = OriginResponse.newBuilder().setResult(false).build();
			responseObserver.onError(new StatusRuntimeException(Status.INTERNAL));
		}

    }
	
	public boolean validateOrigin(Origin origin) throws Exception {
		logger.info("Validating origin" + origin.toString());
		CountryResponse countryResponse = null;
		PopulatedPlacesResponse cityResponse = null;
		// Calls geoDB services to get data.
		countryResponse =  geoDbApi.findCountry(FindCountryRequest.builder().countryId(origin.getCountry()).build());
		if(countryResponse.getErrors()!=null&&countryResponse.getErrors().size()>0) {
			throw new StereoFiException("There was an error validating the country code: " + countryResponse.getErrors().get(0).getMessage());
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
			throw new com.wirefreethought.geodb.client.net.ApiException("There was an error validating the city: " + countryResponse.getErrors().get(0).getMessage());
		}
		//In case of a city matched, the first object in the set will be the target city(Set is ordered).
		if(cityResponse.getData().size()==0||(!cityResponse.getData().get(0).getCity().equals(origin.getCity()))) {
			throw new com.wirefreethought.geodb.client.net.ApiException("City " + origin.getCity() + " was not found.");
		}	
		return true;
	}
}
