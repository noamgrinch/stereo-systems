package com.grinch.ManufacturersService.Configuration;

import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class JmsConfig {

	@Value("${AcitveMQ.Topics.manufacturersTopic}")
	private String manufacturersTopic;
	
    @Bean
    public Topic queue(){
        return new ActiveMQTopic(manufacturersTopic);
    }


}
