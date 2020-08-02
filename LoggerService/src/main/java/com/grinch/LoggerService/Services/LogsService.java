package com.grinch.LoggerService.Services;

import java.util.regex.Pattern;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import com.grinch.LoggerService.BusinessLogic.Entities.Log;
import com.grinch.LoggerService.Repositories.LogsRepository;

@Service
public class LogsService {

	@Autowired
	private LogsRepository repository;
	@Autowired
	private ObjectMapper mapper;
	
	@JmsListener(destination = "LogQueue")
    public void receiveMessage(Message message) throws JMSException {
		TextMessage textMessage = (TextMessage) message;
		String payload = textMessage.getText();
		try {	
			Log log = mapper.readValue(payload, Log.class);
			if(log.getLogger()!=null) {
				String[] split = log.getLogger().split(Pattern.quote("."));
				log.setClassName(split[split.length-1]);
			}
			this.repository.save(log);
			System.out.println(payload);
		}
		catch(Exception e) {
			System.out.println("Failed to map message " + payload);
			System.out.println(Throwables.getStackTraceAsString(e));
		}
    }
}
