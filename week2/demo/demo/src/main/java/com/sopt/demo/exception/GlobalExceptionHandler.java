package com.sopt.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleIllegalArgumentException(final IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }
}

/* 전역적으로 예외를 처리하기 위한 클래스

 @RestControllerAdvice
 - @ControllerAdvice와 @ResponseBody의 조합으로, 전역적으로 예외를 처리하는 클래스임을 나타내는 Annoation
 - RESTful API에서 예외를 처리하고 응답으로 반환할 때 사용

 @ExceptionHandler(IllegalArgumentException.class)
 - 특정 예외 유형을 처리하는 메서드임을 나타내는 Annotation
 -> 지금은 IllegalArgumentException 을 처리하는 메서드

 public ResponseEntity<Void> handleIllegalArgumentException
 -> IllegalArgumentException 이 발생했을 때 실행되는 메서드

 ResponseEntity.badRequest().build()
 -> 예외가 발생했을 때 HTTP 응답 상태를 "Bad Request"로 설정하고, 별다른 메시지 없이 빈 응답을 반환
 */