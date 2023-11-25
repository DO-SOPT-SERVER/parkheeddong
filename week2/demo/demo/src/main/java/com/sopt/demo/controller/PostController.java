package com.sopt.demo.controller;

import com.sopt.demo.dto.request.post.PostCreateRequest;
import com.sopt.demo.dto.request.post.PostUpdateRequest;
import com.sopt.demo.dto.response.post.PostGetResponse;
import com.sopt.demo.service.PostService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private static final String CUSTOM_AUTH_ID = "X-Auth-Id";
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestHeader(CUSTOM_AUTH_ID) Long memberId,
                                           @RequestBody PostCreateRequest request) {
        URI location = URI.create("/api/post/" + postService.create(request, memberId));
        return ResponseEntity.created(location).build();
    }
    /*
    [1] @RequestHeader(CUSTOM_AUTH_ID) Long memberId
    - HTTP 헤더에서 X-Auth-Id 값을 추출하여 memberId 변수에 저장한다
    - 게시물 작성자의 식별을 위한 값
    [2] @RequestBody PostCreateRequest request
    - HTTP 요청의 본문을 PostCreateRequest 객체로 변환해서 받아 들인다
    [3] URI.create("/api/post/" + postService.create(request, memberId))
    - postService의 create 메소드를 이용하여, 생성된 게시물의 ID를 이용하여 URI를 만든다
    [4] return ResponseEntity.created(location).build()
    - 클라이언트에게 HTTP 201 Created 상태코드와 함께 새로운 리소스의 URI 응답을 보내준다
     */

    @GetMapping("{postId}")
    public ResponseEntity<PostGetResponse> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getById(postId));
    }

    @GetMapping
    public ResponseEntity<List<PostGetResponse>> getPosts(@RequestHeader(CUSTOM_AUTH_ID) Long memberId) {
        return ResponseEntity.ok(postService.getPosts(memberId));
    }

    @PatchMapping("{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest request) {
        postService.editContent(postId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deleteById(postId);
        return ResponseEntity.noContent().build();
    }

}

/*
 스프링의 RestController, RequestMapping Annotation을 사용하여 웹 API 를 처리하는 컨트롤러 클래스

 @RestController
 - 해당 클래스가 RESTful 기반의 웹 서비스 컨트롤러임을 의미하는 Annotation
 - 각 메서드의 반환값은 HTTP 응답으로 자동 변환되어 JSON 형태로 전송되어 클라와 통신함

 @RequestMapping('/api/posts')
 - 클래스 레벨에서 URI 일부를 지정하는 Annotation
 - 위 클래스의 모든 메서드에 대한 기본 URI는 'api/posts'로 설정된다

*/