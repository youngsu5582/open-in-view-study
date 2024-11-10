package joyson.openinviewtest.member;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Cacheable
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private long viewCount;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Profile profile;

    public Member(final String name) {
        this(null, name, 0,new Profile());
    }

    public void changeName(final String newName) {
        this.name = newName;
    }

    public void view() {
        this.viewCount += 1;
    }

    public Profile getProfile() {
        return profile;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", viewCount=" + viewCount +
                '}';
    }
}
