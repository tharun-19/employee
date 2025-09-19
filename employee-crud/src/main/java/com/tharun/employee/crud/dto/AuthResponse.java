package com.tharun.employee.crud.dto;

public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";

    public AuthResponse(String token) {
        this.token = token;
    }

    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }

    public void setToken(String token) { this.token = token; }
}

