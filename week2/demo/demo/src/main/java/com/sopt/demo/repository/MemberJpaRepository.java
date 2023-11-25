package com.sopt.demo.repository;

import com.sopt.demo.domain.member.Member;
import com.sopt.demo.exception.MemberNotExistException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    default Member findByIdOrThrow(Long memberId) {
        return findById(memberId)
                .orElseThrow(()-> new MemberNotExistException(memberId));
    }

}
/*
 MemberJpaRepository는 JpaRepository 인터페이스를 상속받고 있다.
 JPA Repository는 Spring JPA에서 제공하는 일번직 CRUD 메소드를 내장하고 있다.

MemberJpaRepository는 Member엔티티와 관련된 기본적 데이터 액세스 작업을 처리하기 위해 필요하다.
이 인터페이스에는 다음과 같은 메서드가 내장되어 있다.

[1] save: 엔티티를 저장(데이터베이스에 삽입 또는 업데이트)하는 메서드.
[2] findById: 엔티티를 식별자(ID)를 사용하여 검색하는 메서드.
[3] findAll: 모든 엔티티를 검색하는 메서드.
[4] count: 엔티티의 총 수를 세는 메서드.
[5] deleteById: 식별자(ID)를 사용하여 엔티티를 삭제하는 메서드.
 */

/*
default 키워드로 정의된 메소드 findByIdOrThrow는 memberId를 받아 Member 엔티티를 반환한다.
findById 메소드로 회원을 검색하고, 존재하지 않으면
EntityNotFoundException 예외를 던진다.
 */