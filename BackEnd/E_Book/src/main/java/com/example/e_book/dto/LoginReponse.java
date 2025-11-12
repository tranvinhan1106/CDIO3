package com.example.e_book.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginReponse {
    private String token ;
    private String role ;

    public LoginReponse(String token, String role) {
        this.token = token;
        this.role = role;
    }
}
