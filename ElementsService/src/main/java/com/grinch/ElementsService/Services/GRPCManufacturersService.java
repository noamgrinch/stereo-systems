package com.grinch.ElementsService.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.common.base.Throwables;
import com.grinch.ElementsService.Exceptions.StereoFiException;
import com.grinch.ManufacturersService.ManufacturersGrpcServiceGrpc;
import com.grinch.ManufacturersService.ValidateManufacturerRequest;
import com.grinch.ManufacturersService.ManufacturersGrpcServiceGrpc.ManufacturersGrpcServiceBlockingStub;
import com.grinch.ManufacturersService.ValidateManufacturerResponse;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;

@Service
public class GRPCManufacturersService {
	private Logger logger = LogManager.getLogger(GRPCManufacturersService.class);
	
	@Value("${manufacturers.grpc.server.host}")
	private String MANUFACTURERS_GRPC_SERVER_HOST;
	@Value("${manufacturers.grpc.server.port}")
	private int MANUFACTURERS_GRPC_SERVER_PORT;
	
    public Boolean validate(Long id) throws StereoFiException {
    	logger.info("Creating gRPC call to manufacturers service to validate id " + id);
        ManagedChannel channel = ManagedChannelBuilder.forAddress(MANUFACTURERS_GRPC_SERVER_HOST, MANUFACTURERS_GRPC_SERVER_PORT)
                .usePlaintext()
                .build();
        ManufacturersGrpcServiceBlockingStub stub = ManufacturersGrpcServiceGrpc.newBlockingStub(channel);
        ValidateManufacturerResponse response = null;
        try {
        	response = stub.validate(ValidateManufacturerRequest.newBuilder().setManufacturerId(id).build());
        }
        catch(io.grpc.StatusRuntimeException e) {
        	if(e.getStatus()==Status.NOT_FOUND) {
        		logger.warn("Invalid Manufacturer ID " + id);
        		throw new StereoFiException("Invalid manufacturer reference id.",HttpStatus.NOT_FOUND);
        	}
        	else {
        		logger.error(Throwables.getStackTraceAsString(e));
        		throw new StereoFiException("An error occurred when validating manufacturer reference.",HttpStatus.NOT_FOUND);
        	}
        }
        finally {
        	channel.shutdown();
        }
        return response.getResult();
    }
}
