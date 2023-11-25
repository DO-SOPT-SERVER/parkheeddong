package com.sopt.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}

/*
 스프링부트 어플리케이션에서 JPA Auditing을 활성화하기 위한 설정 파일
 -> DemoApplication에서 어노테이션을 달아도 되지만, 별도의 클래스로 분리함으로써 추후 유지보수 편리

 @Configuration
 - 스프링에게 이 클래스가 설정을 담는 클래스임을 알려주는 Annotation

 @EnableJpaAuditing
 - JPA Auditing을 활성화하는 역할을 하는 Annotation
 - 이 설정을 통해 엔티티의 생성, 수정 일시를 자동으로 기록 가능하다.

 */