package com.sopt.demo.domain.post;

import com.sopt.demo.domain.BaseTimeEntity;
import com.sopt.demo.domain.category.CategoryId;
import com.sopt.demo.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "post")
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "category_id")
    private CategoryId categoryId;
    // @ManyToOne 사용 없이, 논리적으로 관계만 맺음

    @Builder
    public Post(String title, String content, Member member, CategoryId categoryId) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.categoryId = categoryId;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}

/* 게시글을 의미하는 Entity. ( 연관관계의 주인은 외래키를 가지고 있는 Post 이다 )
 @Table(name = "post")
 - 엔티티 클래스가 post 테이블과 맵핑되도록 지정
 @Column(columnDefinition = "TEXT")
 - 데이터베이스 열에 대한 추가적인 설정을 제공하는 JPA 엔티티 Annotation
 - columnDefinition = "TEXT" -> 해당 열의 데이터 유형을 TEXT로 지정 (긴 텍스트 데이터 저장에 적합 )
 @ManyToOne(fetch = FetchType.LAZY)
 - Member 엔티티와의 관계를 나타내는 Annotation : 다대일(N:1) 관계를 표현
 - fetch = FetchType.LAZY -> 지연 로딩을 사용함을 의미
 @JoinColumn(name = "member_id")
 - 외래 키(Foreign Key)를 정의하는 Annotation
 - name = "member_id" -> DB에 어떤 이름으로 저장될지 지정. member_id 로 지정함을 의미
*/
