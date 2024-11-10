package joyson.openinviewtest.view;

import jakarta.persistence.EntityManager;
import joyson.openinviewtest.member.Member;
import joyson.openinviewtest.member.MemberRepository;
import joyson.openinviewtest.util.SessionUtil;
import joyson.openinviewtest.util.TransactionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
public class TodayViewCounter {

    private final SessionUtil sessionUtil;
    private final MemberRepository memberRepository;

    private final EntityManager entityManager;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void increase(final Member member) {
        final boolean isInPersistenceContext = entityManager.contains(member);
        sessionUtil.logSessionStatus();
        final var newMember = memberRepository.findById(member.getId())
                .get();
        System.out.println(newMember);
        System.out.println(newMember.getProfile());
        sessionUtil.logSessionStatus();
        TransactionUtil.logTransactionStatus();
    }
}
