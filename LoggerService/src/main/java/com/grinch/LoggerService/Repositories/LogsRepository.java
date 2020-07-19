package com.grinch.LoggerService.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grinch.LoggerService.BusinessLogic.Entities.Log;

public interface LogsRepository extends MongoRepository<Log,Long>{

}
