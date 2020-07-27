package com.grinch.ManufacturersService.Configuration;

import java.util.HashMap;

import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.grinch.ManufacturersService.BusinessLogic.Entites.Manufacturer;



@Configuration
public class JmsConfig {

	@Value("${AcitveMQ.Topics.manufacturersTopic}")
	private String manufacturersTopic;
	
    @Bean
    public Topic queue(){
        return new ActiveMQTopic(manufacturersTopic);
    }
    
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        HashMap<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put(Manufacturer.class.getSimpleName(), Manufacturer.class);
        converter.setTypeIdMappings(typeIdMappings);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
    
    


}
