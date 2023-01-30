package com.learningapi.LearningApi.models;

public class AuthenticationResponse {
    private String jwt;

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
