package edu.uom.userregistration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
			
			Map<String, String> req = new HashMap<>();
			req.put("username", model.getUsername());
			req.put("email", model.getEmail());
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentType(MediaType.APPLICATION_JSON);
			
			HttpEntity<Map<String, String>> requestEntity = new HttpEntity<Map<String, String>>(req, httpHeaders);
			
			ResponseEntity<UserManagementRegistrationResponse> managementResponse = restTemplate.postForEntity("http://localhost:48080/addUser",
					requestEntity, 
					UserManagementRegistrationResponse.class);
			
			if (!managementResponse.getStatusCode().is2xxSuccessful()) {
				response.setStatus("ERROR");
				response.setMessage("User was not created succesfully." + managementResponse.getStatusCode());
				response.setErrorDetail(managementResponse.getBody().getErrorDetail());
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
