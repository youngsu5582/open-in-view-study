package joyson.openinviewtest.member;

import joyson.openinviewtest.util.SessionUtil;
import joyson.openinviewtest.util.TransactionUtil;
import joyson.openinviewtest.view.TodayViewCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.Repository;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private static final Supplier<IllegalArgumentException> EXCEPTION = () -> new IllegalArgumentException("Not Found Member");
    private final MemberRepository memberRepository;
    private final SessionUtil sessionUtil;
    private final TodayViewCounter todayViewCounter;
    private final DataSource dataSource;

    public MemberResponse getMember(final String name) {
        System.out.println("========================GET MEMBER========================");
        TransactionUtil.logTransactionStatus();
        sessionUtil.logSessionStatus();
        final Member member = memberRepository.findByName(name)
                .orElseThrow(EXCEPTION::get);
        System.out.println(member);
        TransactionUtil.logTransactionStatus();
        sessionUtil.logSessionStatus();
        return MemberResponse.from(member);
    }

    @Transactional
    public void increaseViewWithNewTransactional(final long memberId) {

        final Member member = memberRepository.findById(memberId)
                .orElseThrow(EXCEPTION::get);
//        TransactionUtil.logTransactionStatus();
//        sessionUtil.logSessionStatus();

        todayViewCounter.increase(member);
//        sessionUtil.logSessionStatus();

//        totalViewCounter.withLoginMember(member);
//        gradeQualifier.reflect(member,ReflectType.view);
    }

    @Transactional
    public MemberResponse changeNickname(final Member member, final String newNickname) {
        sessionUtil.logSessionStatus();
        sessionUtil.close();
        DataSourceUtils.getConnection(dataSource);

        TransactionUtil.logTransactionStatus();
        final Profile profile = member.getProfile();
        profile.changeNickname(newNickname);
        return MemberResponse.from(member);
    }


    public Member getMemberDomain(final String name) {

        System.out.println("========================GET MEMBER DOMAIN========================");
        TransactionUtil.logTransactionStatus();
        sessionUtil.logSessionStatus();
        return memberRepository.findByName(name)
                .orElseThrow(EXCEPTION::get);
    }

    @Transactional
    public void increaseView(final long memberId) {

        final Member member = memberRepository.findById(memberId)
                .orElseThrow(EXCEPTION::get);
        member.view();
        TransactionUtil.logTransactionStatus();
        sessionUtil.logSessionStatus("Increase view");
    }

    @Transactional
    public MemberResponse updateMember(final String name, final String updateName) {
        final Member member = memberRepository.findByName(name)
                .orElseThrow(EXCEPTION::get);
        member.changeName(updateName);
        return MemberResponse.from(member);
    }

    public void check() {
        TransactionUtil.checkTransactional(Repository.class);
    }
}
