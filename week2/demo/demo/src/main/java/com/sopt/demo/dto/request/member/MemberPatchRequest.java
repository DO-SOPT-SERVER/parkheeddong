package com.sopt.demo.dto.request.member;

import com.sopt.demo.domain.member.SOPT;

public record MemberPatchRequest(String nickname, int age, SOPT sopt) {
}
