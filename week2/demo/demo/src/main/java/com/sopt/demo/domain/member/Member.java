package com.sopt.demo.domain.member;

import com.sopt.demo.domain.BaseTimeEntity;
import com.sopt.demo.domain.post.Post;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String nickname;
    private int age;

    @Embedded
    private SOPT sopt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private final List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String name, String nickname, int age, SOPT sopt) {
        this.name = name;
        this.nickname = nickname;
        this.age = age;
        this.sopt = sopt;
    }

    public void updateNickName(String nickname) {
        this.nickname = nickname;
    }

    public void updateAge(int age) {
        this.age = age;
    }

    public void updateSopt(SOPT sopt) {
        this.sopt.setGeneration(sopt.getGeneration());
        this.sopt.setPart(sopt.getPart());
    }
}

/*
데이터베이스에서 사용되는 id는 레코드가 데이터베이스에 저장될 때 자동 생성된다
대부분 데이터베이스관리시스템은 레코드 추가 시 자체적으로 유일한 ID(대개 정수)를 할당하여 관리한다.
-> @GeneratedValue(strategy = GenerationType.IDENTITY) 어노테이션을 사용하여
데이터베이스에서 자동 증가로 ID 필드를 관리
 */

/* Annotation
 @Entity
 - Entity 를 나타내기 위한 JPA Annotation -> 클래스가 데이터베이스 테이블과 맵핑, 해당 테이블의 레코드를 나타내는 객체로 사용된다.
 @Getter
 - Getter 메서드를 제공하는 Lombok Annotation
 @NoArgsConstructor
 - 파라미터 없는 생성자를 자동 생성하는 Lombok Annotatio
 - access = AccessLevel.PROTECTED -> 생성자의 접근제어레벨. 외부에서 직접 인스턴스 생성 못하도록 함
 @ID
 - 엔티티 클래스의 식별자 필드를 나타내는 JPA Annotation
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 - GeneratedValue -> ID 필드값을 자동 생성하는 방법을 설정.
 - GenerationType.IDENTITY -> 자동증가하는 ID를 사용하는 전략
 @Embedded
 - 내장 (Embedded) 객체를 나타내는 필드를 정의하는 JPA Annotation
 (SOPT 객체는 Member 엔티티에 내장되어 있음)
 @Builder
 - 빌더패턴을 구현하기 위한 Lombok Annotation
 */

/*
@OneToMany : 일대다 관계를 정의하는 Annotation
- 하나의 회원 (Member)는 여러개의 게시물 (Post)를 가질 수 있다
- mappedBy = "member" -> 양방향 관계를 정의. member는 Post 엔티티 내 필드 이름으로, 이 필드를 통해 Member와 연결됨
- cascade = CascadeType.ALL -> 부모 엔티티에 대한 모든 변경사항 (생성, 수정, 삭제) 가 자식 엔티티에 적용됨
 */