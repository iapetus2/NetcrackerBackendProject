package com.projectparty.requests;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class SignupRequest {

    private String username;

    private String password;

    @Email
    private String email;

}
