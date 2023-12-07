package com.sopt.demo.repository;

import com.sopt.demo.domain.ServiceMember;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceMemberJpaRepository extends JpaRepository<ServiceMember, Long> {
    Optional<ServiceMember> findByNickname(String nickname);
}