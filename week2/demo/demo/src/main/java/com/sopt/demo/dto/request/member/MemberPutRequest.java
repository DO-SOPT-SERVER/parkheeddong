package com.sopt.demo.dto.request.member;

import com.sopt.demo.domain.member.SOPT;


public record MemberPutRequest(String nickname, int age, SOPT sopt) {
}