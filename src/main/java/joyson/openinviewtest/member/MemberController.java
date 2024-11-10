package joyson.openinviewtest.member;

import jakarta.servlet.http.HttpServletRequest;
import joyson.openinviewtest.auth.AuthInfo;
import joyson.openinviewtest.auth.LoginMember;
import joyson.openinviewtest.util.ClearUtil;
import joyson.openinviewtest.util.ConnectionUtil;
import joyson.openinviewtest.util.SessionUtil;
import joyson.openinviewtest.util.TransactionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ConnectionUtil connectionUtil;
    private final SessionUtil sessionUtil;

    @GetMapping("/index")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }

    @GetMapping("/login")
    public ResponseEntity<MemberResponse> login(@LoginMember final AuthInfo authInfo, HttpServletRequest httpServletRequest) {

        ClearUtil.clear();
        //        TransactionUtil.logTransactionStatus();
//        sessionUtil.logSessionStatus();
//        final MemberResponse response = memberService.getMember(authInfo.name());

//        connectionUtil.logConnections();
        memberService.increaseView(authInfo.id());

//        connectionUtil.logConnections();
        memberService.increaseView(authInfo.id());
//        connectionUtil.logConnections();
        memberService.increaseView(authInfo.id());

        return ResponseEntity.ok(memberService.getMember(authInfo.name()));
//        return ResponseEntity.ok(MemberResponse.from(new));
    }

    @GetMapping("/login-new")
    public ResponseEntity<MemberResponse> loginWithNewTransactional(@LoginMember final AuthInfo authInfo, HttpServletRequest httpServletRequest) {

        ClearUtil.clear();
        //        TransactionUtil.logTransactionStatus();
//        sessionUtil.logSessionStatus();
//        final MemberResponse response = memberService.getMember(authInfo.name());

//        connectionUtil.logConnections();
        memberService.increaseViewWithNewTransactional(authInfo.id());

//        connectionUtil.logConnections();
        memberService.increaseViewWithNewTransactional(authInfo.id());
//        connectionUtil.logConnections();
        memberService.increaseViewWithNewTransactional(authInfo.id());

        return ResponseEntity.ok(memberService.getMember(authInfo.name()));
//        return ResponseEntity.ok(MemberResponse.from(new));
    }

    @GetMapping("/domain")
    public ResponseEntity<MemberResponse> domain(@LoginMember final AuthInfo authInfo) {
        final Member member = memberService.getMemberDomain(authInfo.name());
        return ResponseEntity.ok(MemberResponse.from(member));
    }

    @PostMapping("/update")
    public ResponseEntity<MemberResponse> update(@LoginMember final AuthInfo authInfo, @RequestParam final String newName) {

        final MemberResponse response = memberService.updateMember(authInfo.name(), newName);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/check")
    public ResponseEntity<Void> check() {
        memberService.check();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update-nickname")
    public ResponseEntity<MemberResponse> updateNickname(@LoginMember final AuthInfo authInfo, @RequestParam final String newNickname) {

        final Member member = memberService.getMemberDomain(authInfo.name());
        sessionUtil.close();
        final MemberResponse response = memberService.changeNickname(member, newNickname);
        return ResponseEntity.ok(response);
    }
}
