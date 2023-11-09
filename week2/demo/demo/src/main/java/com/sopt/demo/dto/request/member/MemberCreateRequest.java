package com.sopt.demo.dto.request.member;

import com.sopt.demo.domain.member.SOPT;

public record MemberCreateRequest(String name, String nickname, int age, SOPT sopt) {
}

/* record로 바꾸기 전
@Data
public class MemberCreateRequest {
    private String name;
    private String nickname;
    private int age;
    private SOPT sopt;
}
*/
