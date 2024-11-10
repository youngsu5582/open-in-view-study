package joyson.openinviewtest.member;

public record MemberResponse(
        long id,
        String name,
        long viewCount,
        String nickname
) {

    public static MemberResponse from(final Member member) {
        return new MemberResponse(member.getId(), member.getName(),member.getViewCount(),member.getProfile().getNickname());
    }
}
