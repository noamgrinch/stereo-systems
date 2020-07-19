package com.grinch.LoggerService.Services;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.grinch.LoggerService.BusinessLogic.Entities.Log;
import com.grinch.LoggerService.Repositories.LogsRepository;

@Service
public class LogsService {

	@Autowired
	private LogsRepository repository;
	
	@JmsListener(destination = "LogQueue")
    public void receiveMessage(Log log) throws JMSException {
		this.repository.save(log);
    }
}
