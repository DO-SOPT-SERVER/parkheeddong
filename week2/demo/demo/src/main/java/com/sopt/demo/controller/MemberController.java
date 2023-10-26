package com.sopt.demo.controller;

import com.sopt.demo.dto.request.MemberCreateRequest;
import com.sopt.demo.dto.request.MemberPatchRequest;
import com.sopt.demo.dto.request.MemberPutRequest;
import com.sopt.demo.dto.response.MemberGetResponse;
import com.sopt.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor   // 클래스의 final 필드를 초기화하는 생성자를 만들어주는 Lombok Annotaton
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService; // 의존성 주입

    @GetMapping("{memberId}")
    public ResponseEntity<MemberGetResponse> getMemberProfileV1(@PathVariable("memberId") Long memberId) {
        MemberGetResponse response = memberService.getByIdV1(memberId);
        return ResponseEntity.ok(response);
    }
    /*
    [1] @GetMapping("{memberId}")
    /api/member/{memberId} 경로로 들어오는 GET 요청을 이 메소드와 맵핑한다
    [2] @PathVariable("memberId")
    위 어노테이션은, 경로변수로부터 memberId 값을 추출한다.
    [3] memberService.getByIdV1(memberId)
    memberService의 getByIdV1 함수를 호출하여, MemberGetResponse 형태로 멤버 정보를 반환받는다.
    [4] ResponseEntity.ok
    ResponseEntity는 Spring에서 제공하는 클래스로, HTTP 응답을 다룰 때 사용한다
    ResponseEntity.ok 코드는
    - ResponseEntity를 생성하며, HTTP 응답코드는 200 OK 로 설정된다
    - 응답 바디로 response 객체를 설정한다.
     */

    @GetMapping(value = "/{memberId}/v2", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getByIdV2(memberId));
    }

    /*
    [1] @GetMapping(value = "/{memberId}/v2", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    - value : /api/member/{memberId}/v2 경로의 GET 요청과 맵핑한다.
    - consumes : HTTP 요청에 대한 미디어타입을 지정한다. JSON 형태
    - produces : HTTP 응답에 대한 미디어타입을 지정한다. JSON 형태 ("application/json"으로 하면 오타 날 수 있으므로 Spring에서 제공하는 MediaType 이용해 안전히 가져온다)
    [2] memberService.getByIdV2(memberId)
    memberService의 getByIdV2 함수를 호출하여, MemberGetResponse 형태로 멤버 정보를 반환받는다.
    [3] ResponseEntity.ok
    - ResponseEntity 를 생성하여 HTTP 응답 코드 200 OK와 응답 객체를 반환한다.
     */

    @GetMapping
    public ResponseEntity<List<MemberGetResponse>> getMembersProfile() {
        return ResponseEntity.ok(memberService.getMembers());
    }
    /*
    모든 회원들의 프로필 정보를 조회하는 메서드
    - "/api/member" 에 맵핑된다
    - ResponseEntity 를 생성하여 HTTP 응답 코드 200 OK와  List<MemberGetResonse> 를 반환한다.
     */

    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request) {
        URI location =  URI.create(memberService.create(request));
        return ResponseEntity.created(location).build();
    }
    /*
    회원 객체를 생성하는 메서드
    - "/api/member"에 맵핑된다
    [1] @RequestBody MemberCreateRequest request 매개변수
    - request body에 있는 데이터를 MemberCreateRequest 객체로 변환하고, 이 객체를 이용한다
    [2] 반환값 ResponseEntity<Void>
    - 생성 이후 클라이언트의 요청이 없는 이상, response body가 비어도 되므로 Void로 반환
    [3] memberService.create(request)
    - 클라이언트가 제공한 MemberCreateRequest 객체를 사용하여 멤버를 생성하고, 멤버 식별자 (id)를 반환받는다.
    [4] URI.create
    - 새로운 멤버 자원의 위치를 나타내는 URI location을 생성하여 반환한다. ex) /api/member/{id} 와 같은 형태로
    [5] ResponseEntity.created(location)
    - ResponseEntity를 생성하여 HTTP 응답코드 201 Created와 location URI (헤더에 포함)를 반환한다.
    [6] .build()
    - 실제 응답을 생성하고 반환한다.
     */


    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updatePatchMember(@PathVariable Long memberId, @RequestBody MemberPatchRequest request) {
        memberService.patchMember(memberId, request);
        return ResponseEntity.noContent().build();
    }
    /*
    ResponseEntity.noContent().build()
    -> http 응답코드가 204 No Content 인 응답을 생성한다
    -> 요청이 성공적으로 처리되었으나 클라에 반환 데이터가 없는 경우 사용
     */


    @PutMapping("/{memberId}")
    public ResponseEntity<Void> updatePutMember(@PathVariable Long memberId, @RequestBody MemberPutRequest request) {
        memberService.putMember(memberId, request);
        return ResponseEntity.noContent().build();
    }


}

