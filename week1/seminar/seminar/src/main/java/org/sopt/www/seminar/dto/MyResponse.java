package org.sopt.www.seminar.dto;

import lombok.Data;

@Data
public class MyResponse {
    private Number code;
    private String status;
    private boolean success;

    public MyResponse(Number code, String status, boolean success) {
        this.code = code;
        this.status = status;
        this.success = success;
    }

}
