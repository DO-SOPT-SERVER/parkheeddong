package com.sopt.demo.domain;


import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Embeddable
@Getter
@EqualsAndHashCode
public class CategoryId implements Serializable {
    private String categoryId;
}

/* CategoryId = 복합 키를 표현하기 위한 클래스
 @Embeddable
 - 클래스가 다른 엔터티에 내장될 수 있다는 것을 나타내는 Annotation
 - 즉, 이 클래스는 다른 엔터티의 일부로 사용될 수 있고, 그 엔터티와 함께 데이터베이스 테이블에 매핑
 Serializable 인터페이스
 - 자바 인터페이스로, Serializable을 구현한 클래스는 객체의 상태를 직렬화(Serialization)할 수 있다는 것을 의미
 - 직렬화는 객체를 바이트 스트림으로 변환하는 과정을 의미함
 */