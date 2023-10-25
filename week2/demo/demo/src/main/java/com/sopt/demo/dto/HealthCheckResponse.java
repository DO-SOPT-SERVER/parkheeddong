package com.sopt.demo.dto;

public class HealthCheckResponse {
    private static final String STATUS = "OK";
    private String status;
    public HealthCheckResponse() {
        this.status = STATUS;
    }
}
