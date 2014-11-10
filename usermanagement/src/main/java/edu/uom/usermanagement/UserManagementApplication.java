package edu.uom.usermanagement;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableWebMvc
public class UserManagementApplication {

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
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(queueName);
	}
	
    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }
}
