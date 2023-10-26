package com.sopt.demo.dto.request;

import com.sopt.demo.domain.SOPT;
import lombok.Data;


/*
@Data
public class MemberCreateRequest {
    private String name;
    private String nickname;
    private int age;
    private SOPT sopt;
}
*/

public record MemberCreateRequest(String name, String nickname, int age, SOPT sopt) {
}