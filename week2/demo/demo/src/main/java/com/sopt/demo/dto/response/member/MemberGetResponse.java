package com.sopt.demo.dto.response.member;

import com.sopt.demo.domain.member.SOPT;


public record MemberGetResponse(String name, String nickname, int age, SOPT sopt) {
}

/* record로 바꾸기 전
@Data // 클래스의 일반적 메소드 (toString, Getter, Setter 등) 생성하는 Lombok Annotation
@AllArgsConstructor // 클래스의 모든 필드에 대해 생성자를 자동으로 생성하는 Lombok Annotation
public class MemberGetResponse {
    private String name;
    private String nickname;
    private int age;
    private SOPT sopt;

    public static MemberGetResponse of(Member member) {
        return new MemberGetResponse(
                member.getName(),
                member.getNickname(),
                member.getAge(),
                member.getSopt()
        );
    }
}
*/
/*
    of 메소드 : Member 엔티티를 입력으로 받아서, MemberGetResponse를 반환하는 메소드이다.
    Member 객체에 대해서 MemberGetResponse 객체를 생성하여 반환한다.
    static 키워드로 선언된 이유는, 해당 메소드는 클래스 레벨에서 사용되며
    객체 인스턴스와 무관하게 호출될 수 있기 때문이다.
*/