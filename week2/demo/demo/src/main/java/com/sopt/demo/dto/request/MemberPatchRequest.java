package com.sopt.demo.dto.request;

import com.sopt.demo.domain.Part;
import com.sopt.demo.domain.SOPT;

public record MemberPatchRequest(String nickname, int age, SOPT sopt) {
}
