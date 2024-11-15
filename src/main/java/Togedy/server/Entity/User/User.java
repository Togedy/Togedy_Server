package Togedy.server.Entity.User;

import Togedy.server.Entity.BaseEntity;
import Togedy.server.Entity.OAuth;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "Users")
@Getter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    private String goal;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus status = UserStatus.ACTIVE;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oauth_id")
    private OAuth oauth;

    public static User createUser(String email, String nickname) {
        User user = new User();
        user.email = email;
        user.nickname = nickname;
        user.status = UserStatus.ACTIVE;
        return user;
    }
    public void createOauthAssociation(OAuth oAuth) {
        this.oauth = oAuth;
    }
}
