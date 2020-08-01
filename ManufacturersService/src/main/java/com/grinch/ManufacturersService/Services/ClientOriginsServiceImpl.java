package com.grinch.ManufacturersService.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.grinch.ManufacturersService.BusinessLogic.Origin;
import com.grinch.ManufacturersService.Exceptions.StereoFiException;
import com.grinch.OriginsService.OriginRequest;
import com.grinch.OriginsService.OriginResponse;
import com.grinch.OriginsService.OriginsServiceGrpc;
import com.grinch.OriginsService.OriginsServiceGrpc.OriginsServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;

@Service
public class ClientOriginsServiceImpl {
	
	@Value("${Host}")
	private String HOST;
	@Value("${origins.grpc.server.port}")
	private int ORIGINS_GRPC_SERVER_PORT;
	
    public boolean validate(Origin origin) throws StereoFiException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(HOST, ORIGINS_GRPC_SERVER_PORT)
                .usePlaintext()
                .build();
        OriginsServiceBlockingStub stub = OriginsServiceGrpc.newBlockingStub(channel);
        OriginResponse helloResponse = null;
        try {
        	helloResponse = stub.validate(OriginRequest.newBuilder().setCountry(origin.getCountry())
        			.setCity(origin.getCity())
        			.build());
        	channel.shutdown();
        }
        catch(io.grpc.StatusRuntimeException e) {
        	if(e.getStatus()==Status.NOT_FOUND) {
        		throw new StereoFiException("Invalid country or city.",HttpStatus.NOT_FOUND);
        	}
        	else {
        		throw new StereoFiException("An error occurred when validating origin.",HttpStatus.NOT_FOUND);
        	}
        }
        return helloResponse.getResult();
    }

}
