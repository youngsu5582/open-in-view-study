package joyson.openinviewtest.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Profile {
    @Id
    @GeneratedValue
    private Long id;

    String nickname;

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                '}';
    }
    public void changeNickname(final String nickname) {
        this.nickname = nickname;
    }
}
