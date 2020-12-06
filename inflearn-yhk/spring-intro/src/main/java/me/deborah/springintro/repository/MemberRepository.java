package me.deborah.springintro.repository;

import me.deborah.springintro.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // Optional Java 8 문법
    Optional<Member> findById(Long id);

    List<Member> findAll();

    Optional<Member> findByName(String name);
}
