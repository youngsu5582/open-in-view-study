package joyson.openinviewtest.member;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {

    @Transactional(readOnly = true)
    Optional<Member> findByName(String name);
}
