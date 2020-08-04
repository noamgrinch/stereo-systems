package com.grinch.ManufacturersService.Services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.grinch.ManufacturersService.ManufacturersGrpcServiceGrpc.ManufacturersGrpcServiceImplBase;
import com.grinch.ManufacturersService.ValidateManufacturerRequest;
import com.grinch.ManufacturersService.ValidateManufacturerResponse;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
@GrpcService
public class ManufacturersGrpcServiceImpl extends ManufacturersGrpcServiceImplBase{
	private Logger logger = LogManager.getLogger(ManufacturersGrpcServiceImpl.class);
	@Autowired
	private ManufacturersService manuService;
	
    @Override
    public void validate(ValidateManufacturerRequest request, StreamObserver<ValidateManufacturerResponse> responseObserver) {
    	logger.info("Validating manufacturer with id "+ request.getManufacturerId());
    	ValidateManufacturerResponse response = ValidateManufacturerResponse.newBuilder().setResult(manuService.exists(request.getManufacturerId())).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

