package com.sopt.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PreSignedUrlVO {
    private String fileName;
    private String url;

    public static PreSignedUrlVO of(String fileName, String url) {
        return new PreSignedUrlVO(fileName, url);
    }
}
