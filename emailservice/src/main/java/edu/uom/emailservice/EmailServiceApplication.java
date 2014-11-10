package edu.uom.emailservice;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class EmailServiceApplication {
	
	final static String queueName = "uom-users";

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Bean
	public Queue queue() {
		return new Queue(queueName, false);
	}
	
	@Bean
	public TopicExchange exchange() {
		return new TopicExchange("uom-users-exchange");
	}
	
	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}
	
	@Bean
    public NewUserReceiver receiver() {
        return new NewUserReceiver();
    }

	@Bean
	public MessageListenerAdapter listenerAdapter(NewUserReceiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
	
    public static void main(String[] args) {
        SpringApplication.run(EmailServiceApplication.class, args);
    }
}
