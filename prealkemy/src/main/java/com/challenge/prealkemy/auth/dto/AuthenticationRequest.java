package com.challenge.prealkemy.auth.dto;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author river
 */
@Getter
@Setter
public class AuthenticationRequest {

    private String username;
    private String password;
}

