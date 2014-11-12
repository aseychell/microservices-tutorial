package edu.uom.healthservice;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class OverallHealthController {

	@RequestMapping("/all")
	public Map<String, String> aggregateAllHealth() throws RestClientException, URISyntaxException {
		
		Map<String, String> response = new HashMap<>();
		
		RestTemplate template = new RestTemplate();
		
		try {
			String response1 = template.getForObject(new URI("http://localhost:38080/health"), String.class);
			response.put("Health Service", "OK" + response1);
		} catch (Exception e) {
			response.put("Health Service", "DOWN " + e.getMessage());	
		}

		try {
			String response2 = template.getForObject(new URI("http://localhost:18080/health"), String.class);
			response.put("Registration", "OK" + response2);
		} catch (Exception e) {
			response.put("Registration", "DOWN " + e.getMessage());	
		}
		try {
			String response2 = template.getForObject(new URI("http://localhost:48080/health"), String.class);
			response.put("User Management", "OK" + response2);
		} catch (Exception e) {
			response.put("User Management", "DOWN " + e.getMessage());	
		}
		try {
			String response2 = template.getForObject(new URI("http://localhost:28080/health"), String.class);
			response.put("Email", "OK" + response2);
		} catch (Exception e) {
			response.put("Email", "DOWN " + e.getMessage());	
		}
		
		return response;
		
	}
}
