package edu.uom.userregistration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.uom.userregistration.models.RegistrationModel;
import edu.uom.userregistration.models.RegistrationResponse;
import edu.uom.userregistration.models.UserManagementRegistrationResponse;

@RestController
public class UserRegistrationController {
	
	@RequestMapping(value= "/register", method=RequestMethod.POST)
	public RegistrationResponse registerUser(RegistrationModel model) {
		
		final RegistrationResponse response = new RegistrationResponse();
		try {
			RestTemplate restTemplate = new RestTemplate();
			UserManagementRegistrationResponse managementResponse = restTemplate.postForObject("http://localhost:48080/addUser", model, UserManagementRegistrationResponse.class);
			if (managementResponse.getStatus() != 200) {
				response.setStatus("ERROR");
				response.setMessage("User was not created succesfully.");
				response.setErrorDetail(managementResponse.getErrorDetail());
				return response;
			} else {
				response.setStatus("OK");
				response.setMessage("User created successfully");
			}
		} catch (final Exception e) {
			response.setStatus("ERROR");
			response.setMessage("User was not created succesfully. Exception encountered.");
			e.printStackTrace();
			response.setErrorDetail(e.getMessage());
		}
		
		return response;
	}

}
