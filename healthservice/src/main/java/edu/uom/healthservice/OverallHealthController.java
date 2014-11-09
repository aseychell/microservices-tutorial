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
			String response1 = template.getForObject(new URI("http://localhost:8080/health"), String.class);
			response.put("Service1", "OK" + response1);
		} catch (Exception e) {
			response.put("Service1", "DOWN " + e.getMessage());	
		}

		try {
			String response2 = template.getForObject(new URI("http://localhost:18080/health"), String.class);
			response.put("Service2", "OK" + response2);
		} catch (Exception e) {
			response.put("Service2", "DOWN " + e.getMessage());	
		}
		
		return response;
		
	}
}
