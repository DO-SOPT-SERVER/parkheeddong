package com.sopt.demo.exception;

public class MemberNotExistException extends RuntimeException{
    public MemberNotExistException(Long memberId) {
        super("ID가" + memberId + "인 회원은 존재하지 않습니다.");
        // 예외 메세지 설정
    }
}
