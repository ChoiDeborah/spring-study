package me.deborah.springintro.repository;

import com.sun.source.tree.NewArrayTree;
import me.deborah.springintro.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    // 키 중복 문제가 있을 수 있으나 예제니까
    private static Map<Long, Member> store = new HashMap<>();
    //  0, 1, 2... key value 생성해 줄 변수
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
       member.setId(++sequence);
       store.put(member.getId(), member);
       return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter((member -> member.getName().equals(name)))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
