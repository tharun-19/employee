package com.tharun.employee.crud.dto;

public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";

    public AuthResponse(String token) {
        this.token = token;
    }

    // Must have getters for serialization
    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }

    // Optional: setter for token if you want
    public void setToken(String token) { this.token = token; }
}

