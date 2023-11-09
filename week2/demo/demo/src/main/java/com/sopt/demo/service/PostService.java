package com.sopt.demo.service;

import com.sopt.demo.domain.Member;
import com.sopt.demo.domain.Post;
import com.sopt.demo.dto.request.post.PostCreateRequest;
import com.sopt.demo.dto.request.post.PostUpdateRequest;
import com.sopt.demo.dto.response.post.PostGetResponse;
import com.sopt.demo.repository.MemberJpaRepository;
import com.sopt.demo.repository.PostJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostJpaRepository postJpaRepository;
    private final MemberJpaRepository memberJpaRepository;

    @Transactional
    public String create(PostCreateRequest request, Long memberId) {
        Member member = memberJpaRepository.findByIdOrThrow(memberId);
        Post post = postJpaRepository.save(
                Post.builder()
                        .member(member)
                        .title(request.title())
                        .content(request.content()).build());
        return post.getPostId().toString();
    }

    /*
    [1] memberJpaRepository.findByIdOrThrow(memberId);
    -> 게시물 작성자 ID를 이용해서 해당 멤버를 조회하고, 없을 경우엔 예외를 던진다
    [2] Post.builder().member(member).title(request.title()).content(request.content()).build())
    -> 빌더 디자인 패턴을 이용하여 Post 엔티티 객체를 생성한다
    [3] Post post = postJpaRepository.save
    -> 생성된 Post 엔티티를 Jpa Repository에 저장한다
    [4] return post.getPostId().toString();
    -> 생성된 Post의 Id를 문자열로 반환한다
     */

    public PostGetResponse getById(Long postId) {
        Post post = postJpaRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("해당하는 게시글이 없습니다."));
        return PostGetResponse.of(post);
    }
    public List<PostGetResponse> getPosts(Long memberId) {
        return postJpaRepository.findAllByMemberId(memberId)
                .stream()
                .map(post -> PostGetResponse.of(post))
                .toList();
    }

    @Transactional
    public void editContent(Long postId, PostUpdateRequest request) {
        Post post = postJpaRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 게시글이 없습니다."));
        post.updateContent(request.content());
    }

    @Transactional
    public void deleteById(Long postId) {
        Post post = postJpaRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("해당하는 게시글이 없습니다."));
        postJpaRepository.delete(post);
    }

}

