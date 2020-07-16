package com.grinch.SpeakersService.Configuration;

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
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.grinch.SpeakersService.Consumers.Consumer;

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
	      converter.setTypeIdPropertyName("_type");
	      return converter;
	  }
	  
	  @Bean
	  public JmsListenerContainerFactory<?> jsaFactory(ConnectionFactory connectionFactory,
	                                                  DefaultJmsListenerContainerFactoryConfigurer configurer) {
	      DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
	      factory.setPubSubDomain(true);
	      factory.setMessageConverter(jacksonJmsMessageConverter());
	      configurer.configure(factory, connectionFactory);
	      return factory;
	  }

}
