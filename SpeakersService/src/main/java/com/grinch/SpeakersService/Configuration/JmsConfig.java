package com.grinch.SpeakersService.Configuration;

import java.util.HashMap;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.grinch.SpeakersService.BusinessLogic.Manufacturer;


@Configuration
@EnableAutoConfiguration
@EnableJms
public class JmsConfig {
	
	@Value("${activemq.topics.manufacturers}")
    public String MANUFACTURERS_TOPIC;


	 @Value("${activemq.topics.url}")
	  String brokerUrl;
	  
	  @Value("${activemq.topics.user}")
	  String userName;
	  
	  @Value("${activemq.topics.password}")
	  String password;
	 
	  /*
	   * Initial ConnectionFactory
	   */
	    @Bean
	    public ConnectionFactory connectionFactory(){
	        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	        connectionFactory.setBrokerURL(brokerUrl);
	        connectionFactory.setUserName(userName);
	        connectionFactory.setPassword(password);
	        connectionFactory.setTrustAllPackages(true);
	        return connectionFactory;
	    }
	    
	  @Bean // Serialize message content to json using TextMessage
	  public MessageConverter jacksonJmsMessageConverter() {
	        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
	        converter.setTargetType(MessageType.TEXT);
	        HashMap<String, Class<?>> typeIdMappings = new HashMap<>();
	        typeIdMappings.put(Manufacturer.class.getSimpleName(), Manufacturer.class);
	        converter.setTypeIdMappings(typeIdMappings);
	        converter.setTypeIdPropertyName("_type");
	        return converter;
	  }
	  
	  @Bean
	  public JmsListenerContainerFactory<?> topicListenerFactory(ConnectionFactory connectionFactory,
	                                                  DefaultJmsListenerContainerFactoryConfigurer configurer) {
	      DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	      factory.setMessageConverter(jacksonJmsMessageConverter());
	      configurer.configure(factory, connectionFactory);
	      factory.setPubSubDomain(true);
	      return factory;
	  }
	  
	    @Bean
	    public ObjectMapper objectMapper(){
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.registerModule(new JavaTimeModule());
	        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	        return mapper;
	    }

}
