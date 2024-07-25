package com.Spring_Jwt_Training.SpringJwt.dto;

public class LoginResponse {

    private String token;

    private Long expiresIn;

    public String getToken(){
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
