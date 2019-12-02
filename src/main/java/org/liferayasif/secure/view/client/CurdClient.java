package org.liferayasif.secure.view.client;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class CurdClient extends RestTemplate  {

	@Value("${service.url.crud}")
	protected String reportsURL;
	
	public CurdClient() {
		super();
	}
	
	public CurdClient getCurdClient() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "eltabo");
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		ResponseEntity<Map> response = restTemplate.exchange("https://httpbin.org/user-agent", HttpMethod.GET, entity, Map.class);        
        System.out.println(response.getBody());
		
        return new CurdClient();
	}
	
	@Override
	public <T> T postForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
		System.out.println("---------------------------------------post for object -------------------------------------------");
		return super.postForObject(url, request, responseType);
	}
	
	
}
