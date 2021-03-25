package com.projectparty.response;

import com.projectparty.entities.UserRoleEnum;
import lombok.Data;
@Data
public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private int id;
	private String username;
	private String email;
	private UserRoleEnum role;

	public JwtResponse(String accessToken, int id, String username, String email, UserRoleEnum role) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.role = role;
	}
}
