package joyson.openinviewtest.auth;

import joyson.openinviewtest.member.Member;

public record AuthInfo(Long id, String name) {
    public static AuthInfo from(final Member member) {
        return new AuthInfo(member.getId(), member.getName());
    }
}
