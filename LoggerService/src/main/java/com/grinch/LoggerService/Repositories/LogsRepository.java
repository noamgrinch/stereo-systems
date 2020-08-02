package com.grinch.LoggerService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grinch.LoggerService.BusinessLogic.Entities.Log;

public interface LogsRepository extends JpaRepository<Log,Long>{

}
