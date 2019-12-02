package org.liferayasif.secure.view.client;

public class AuthenticationResponse {

	private String jwt;

	public AuthenticationResponse() {}
	
	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}
	
	public String getJwt() {
		return jwt;
	}
	
	@Override
	public String toString() {
		return "jwt : "+jwt;
	}
	
}
