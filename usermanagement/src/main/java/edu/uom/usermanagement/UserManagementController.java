package edu.uom.usermanagement;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.uom.usermanagement.busobject.AddUserModel;
import edu.uom.usermanagement.busobject.AddUserModelResponse;

@RestController
public class UserManagementController {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Queue queue;
	
	private String users = "";
	
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	public AddUserModelResponse addUser(final @RequestBody AddUserModel model) {
		
		if (!StringUtils.hasText(model.getUsername()) || !StringUtils.hasText(model.getEmail())) {
			throw new IllegalArgumentException("Username and email are required!");
		}
		
		final AddUserModelResponse response = new AddUserModelResponse();
		if (!users.equals(model.getUsername())) {
			users = model.getUsername();
			
			// Broadcast 
			rabbitTemplate.convertAndSend(queue.getName(), "NEW USER|" + model.getUsername() + "|" + model.getEmail());
		}
		
		return response;
	}
}
