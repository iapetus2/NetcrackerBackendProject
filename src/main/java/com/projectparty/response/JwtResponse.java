package com.projectparty.response;

import com.projectparty.entities.RoleType;
import lombok.Data;
@Data
public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private int id;
	private String username;
	private String email;
	private RoleType role;
	private long cash;

	public JwtResponse(String accessToken, int id, String username, String email, RoleType role, long cash) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.role = role;
		this.cash = cash;
	}
}
