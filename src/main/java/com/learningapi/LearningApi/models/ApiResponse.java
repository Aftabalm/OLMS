package com.learningapi.LearningApi.models;

public class ApiResponse {
    boolean success;
    private String message;

    public ApiResponse(boolean success, String s) {
        this.message = s;
        this.success  = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
