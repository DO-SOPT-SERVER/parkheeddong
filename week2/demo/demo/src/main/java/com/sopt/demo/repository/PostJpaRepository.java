package com.sopt.demo.repository;

import com.sopt.demo.domain.member.Member;
import com.sopt.demo.domain.post.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByMemberId(Long memberId);
    List<Post> findAllByMember(Member member);
}
/*
 JpaRepository
 - 스프링 JPA에서 제공하는 인터페이스
 - 데이터베이스 접근에 필요한 CRUD(Create, Read, Update, Delete) 기능의 메소드를 자동 생성해준다
 - <Post, Long> -> Post는 엔티티 클래스, Long은 엔티티의 ID 필드 타입 (Post클래스의 ID를 Long 타입으로 지정)

 => 따라서 PostJpaRepository는 Post 엔티티와 관련된 Jpa Repository 인터페이스로서,
 save, findById, findAll, deleteById등의 기본적 메소드들이 이미 포함 되어 있다!
 */