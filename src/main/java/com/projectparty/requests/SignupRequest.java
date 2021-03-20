package com.projectparty.requests;

import lombok.Data;

import javax.validation.constraints.Email;
import java.util.Set;

@Data
public class SignupRequest {

    private String username;

    private String password;

    @Email
    private String email;

    private Set<String> role;
}
