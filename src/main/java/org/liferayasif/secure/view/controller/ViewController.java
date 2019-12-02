package org.liferayasif.secure.view.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.liferayasif.secure.view.client.AuthenticationRequest;
import org.liferayasif.secure.view.client.AuthenticationResponse;
import org.liferayasif.secure.view.client.CurdClient;
import org.liferayasif.secure.view.client.RestTemplateFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController extends CurdClient{

	@Autowired
	private RestTemplateFactory restTemplateFactory;
	
	@RequestMapping({"/", "/login"})
	public ModelAndView view() {
		ModelAndView mav = new ModelAndView("login");
		
		URL url;
		
		try {
			AuthenticationRequest req = new AuthenticationRequest("asif", "pass");
			AuthenticationResponse jwt = getCurdClient().postForObject(reportsURL+"/authenticate",req, AuthenticationResponse.class);
			
			System.out.println(jwt);
			
			url = new URL(reportsURL+"/hello");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization","Bearer "+jwt.getJwt());
			conn.setRequestProperty("Content-Type","application/json");
	        conn.setRequestMethod("GET");
	        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        
	        String output;

	        StringBuffer response = new StringBuffer();
	        while ((output = in.readLine()) != null) {
	            response.append(output);
	        }
	        
	        in.close();
	        // printing result from response
	        System.out.println("Response:-" + response.toString());
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
        
        
		return mav;
	}
}
