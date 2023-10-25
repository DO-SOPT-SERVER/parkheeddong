package com.sopt.demo.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class SOPT {
    private int generation;

    @Enumerated(value = EnumType.STRING)
    private Part part;
}

/* Annotation
 @Embeddable
 - 내장 (Embedded) 객체를 정의하기 위한 JPA Annotation
 @Enumerated(value = EnumType.STRING)
 - 열거형 (Enum) 타입의 속성을 데이터베이스에 맵핑할 때 사용
 - value = EnumType.STRING -> 문자열 값을 저장함을 의미
 */