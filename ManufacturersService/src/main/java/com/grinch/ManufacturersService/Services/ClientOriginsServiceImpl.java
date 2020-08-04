package com.grinch.ManufacturersService.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.common.base.Throwables;
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
	private Logger logger = LogManager.getLogger(ClientOriginsServiceImpl.class);
	@Value("${origins.grpc.server.host}")
	private String ORIGINS_GRPC_SERVER_HOST;
	@Value("${origins.grpc.server.port}")
	private int ORIGINS_GRPC_SERVER_PORT;
	
    public boolean validate(Origin origin) throws StereoFiException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ORIGINS_GRPC_SERVER_HOST, ORIGINS_GRPC_SERVER_PORT)
                .usePlaintext()
                .build();
        OriginsServiceBlockingStub stub = OriginsServiceGrpc.newBlockingStub(channel);
        OriginResponse helloResponse = null;
        try {
        	helloResponse = stub.validate(OriginRequest.newBuilder().setCountry(origin.getCountry())
        			.setCity(origin.getCity())
        			.build());
        }
        catch(io.grpc.StatusRuntimeException e) {
        	if(e.getStatus()==Status.NOT_FOUND) {
        		logger.warn("Invalid country or city: " + e.getMessage());
        		throw new StereoFiException("Invalid country or city.",HttpStatus.NOT_FOUND);
        	}
        	else {
        		logger.error(Throwables.getStackTraceAsString(e));
        		throw new StereoFiException("An error occurred when validating origin.",HttpStatus.NOT_FOUND);
        	}
        }
        finally {
        	channel.shutdown();
        }
        return helloResponse.getResult();
    }

}
