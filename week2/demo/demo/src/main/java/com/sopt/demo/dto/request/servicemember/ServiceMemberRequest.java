package com.sopt.demo.dto.request.servicemember;

public record ServiceMemberRequest(
        String nickname,
        String password
) {
}