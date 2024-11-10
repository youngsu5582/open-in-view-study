package joyson.openinviewtest;

import jakarta.persistence.EntityManager;
import joyson.openinviewtest.member.Member;
import joyson.openinviewtest.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ApplicationListener implements ApplicationRunner {

    private final MemberRepository memberRepository;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=================================================================");
        System.out.println("=================================================================");
        memberRepository.save(new Member("joyson"));
        System.out.println("=================================================================");
        System.out.println("=================================================================");

        System.out.println("==============================COMPLETED===================================");
    }
}
