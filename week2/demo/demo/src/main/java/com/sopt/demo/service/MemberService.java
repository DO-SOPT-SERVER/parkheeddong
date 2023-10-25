package com.sopt.demo.service;

import com.sopt.demo.domain.Member;
import com.sopt.demo.dto.request.MemberCreateRequest;
import com.sopt.demo.dto.response.MemberGetResponse;
import com.sopt.demo.repository.MemberJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberJpaRepository memberJpaRepository;

    public MemberGetResponse getByIdV1(Long memberId) {
        Member member = memberJpaRepository.findById(memberId).get();
        MemberGetResponse response = MemberGetResponse.of(member);
        return response;
    }
    /*
    memberId를 입력받아 특정 회원의 프로필 정보를 조회하는 메서드
    - memberJpaRepository.findById로 회원을 찾고,
    - MemberGetResponse.of() 로 해당 정보를 MemberGetResponse 객체로 변환하여 리턴

    but, 이 경우 NullPointException이 발생할 수 있다.
    존재하지 않는 memberId가 들어올 경우, get()은 null을 가져와서 Member 객체에 null이 들어오면 예외발생
     */

    public MemberGetResponse getByIdV2(Long id) {
        return MemberGetResponse.of(memberJpaRepository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("해당하는 회원이 없습니다")));
    }

    /*
    ID를 입력받아, 특정 회원의 프로필 정보를 조회하는 메서드
    - 회원이 존재할 경우, MemberGetResponsse.of 로 MemberGetResponse로 변환하여 반환
    - 회원이 조재하지 않은 경우, EntityNotFoundExecption 예외를 던지고 예외 메시지를 반환
     */

    public MemberGetResponse getById3(Long id) {
        return MemberGetResponse.of(memberJpaRepository.findByIdOrThrow(id));
    }

    /*
    MemberJpaRepository에 findByIdOrThrow 메소드를 만들고 위와 같이 작성할 수 있다.
     */

    public List<MemberGetResponse> getMembers() {
        List<MemberGetResponse> members = memberJpaRepository.findAll()
                .stream()
                .map(member -> MemberGetResponse.of(member))
                .collect(Collectors.toList());
        return members;
    }

    /*
    findAll : memberJpaRepository에 있는 모든 회원 정보를 조회하고, List<Member>형태로 반환된다
    stream : Stream<Member> 형태로 반환된다
    map : 각 회원정보 (member)에 대해서 MemberGetResponse.of 함수를 호출하여 MemberGetResponse 객체로 변환
          모두 변환된 후, Stream<MemberGetResponse> 형태로 반환된다
    collect : 스트림 결과를 List<MemberGetResponse> 형태로 수집하여 반환한다.
     */

    @Transactional
    public String create(MemberCreateRequest request) {

        Member member =  Member.builder()
                .name(request.getName())
                .nickname(request.getNickname())
                .age(request.getAge())
                .sopt(request.getSopt())
                .build();

        memberJpaRepository.save(member);

        return member.getId().toString();
    }
    /*
    [1] 빌더 패턴을 사용하여 객체를 생성한다.
    - builder() : Member 클래스에 정의된 빌더 객체 MemberBuilder를 생성한다.
    - .name() : name 필드를 request로부터 가져온 이름으로 설정 (Member @Data를 했기 때문에 getter 존재)
    - .build() : 위 필드들을 기반으로 Member객체 생성
    [2] memberJpaRepository.save
    - 해당 멤버를 데이터베이스에 저장한다.
    [3] member.getId().toString();
    - 멤버의 고유한 id를 문자열 형식으로 반환한다.
     */


}

/* Annotation
 @Transactional
- 트랜잭션 관리를 지원하는 Spring Annotation
- 해당 annotation이 적용된 메서드나 클래스가 하나의 트랜잭션 내에서 실행되도록 보장
- 메서드 실행 중 예외가 발생하면 트랜잭션은 롤백되어 모든 변경사항이 취소된다
- 이를 통해 데이터베이스 작업을 논리적으로 그룹화하여 일관성 유지 가능
- readOnly = true -> 트랜잭션을 읽기전용 모드로 설정하여 해당 트랜잭션 내 읽기작업만 수행함을 나타낸다.
 @Service
 - 스프링빈에 등록할 서비스 클래스를 나타내기 위한 Spring Annotation
 */