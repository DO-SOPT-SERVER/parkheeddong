package com.sopt.demo.domain;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}

/*  BaseTimeEntity 클래스는 추상 클래스 -> 직접적으로 인스턴스를 생성할 수 없고, 상속받아서 사용
 @MappedSuperclass
 - 데이터베이스에 직접적으로 매핑되지 않고, 상속 관계에서 자식 엔터티들이 공통 필드를 상속받도록 하는 Annotation
 - 즉, 부모 클래스인 BaseTimeEntity가 테이블로 매핑되지 않고, 자식 엔터티 클래스들에게 속성만을 제공한다
 @EntityListeners(AuditingEntityListener.class)
 - 엔터티에 대한 이벤트(생성, 수정 등)를 감지하고 처리하는 리스너를 지정
 -> AuditingEntityListener.class를 사용하여 생성 및 수정 이벤트에 대한 자동 감사(Auditing)를 활성화
 @CreatedDate
 - JPA Auditing 기능을 활성화하여 엔터티가 생성될 때 자동으로 날짜 및 시간을 기록하는 Annotation
 @LastModifiedDate
 - 엔터티가 수정될 때 자동으로 날짜 및 시간을 갱신하는 Annotaiton
 */