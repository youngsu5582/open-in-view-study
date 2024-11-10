package joyson.openinviewtest.auth;


import joyson.openinviewtest.member.Member;
import joyson.openinviewtest.member.MemberRepository;
import joyson.openinviewtest.util.SessionUtil;
import joyson.openinviewtest.util.TransactionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final MemberRepository memberRepository;
    private final SessionUtil sessionUtil;

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(LoginMember.class);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthInfo resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
                                    final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        final Long id = Long.parseLong(webRequest.getHeader("Authorization"));
        System.out.println("==================ARGUMENT RESOLVER==================");
        TransactionUtil.logTransactionStatus();
        sessionUtil.logSessionStatus();

        final Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Not Exist Member : %s", id)));
//        final Member member = memberRepository.findById(1L)
//                .orElseThrow(() -> new IllegalArgumentException(String.format("Not Exist Member : %s", name)));
        TransactionUtil.logTransactionStatus();
        sessionUtil.logSessionStatus();
        System.out.println("\n\n\n\n\n\n");
        return AuthInfo.from(member);
    }
}
