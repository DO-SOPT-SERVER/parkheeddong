package com.sopt.demo.dto.request;

import com.sopt.demo.domain.SOPT;


public record MemberPutRequest(String nickname, int age, SOPT sopt) {
}