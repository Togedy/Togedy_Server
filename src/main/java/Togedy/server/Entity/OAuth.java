package Togedy.server.Entity;

import Togedy.server.Entity.User.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "oauth")
public class OAuth extends BaseEntity {
    @Id @Column(name = "oauth_id")
    private Long id;
    private String sub;
    private String accessToken;
    private String refreshToken;

    @Enumerated(EnumType.STRING)
    @Column(name = "base_status")
    private BaseStatus baseStatus = BaseStatus.ACTIVE;

    @OneToOne(mappedBy = "oauth")
    private User user;

    public static OAuth createOauth(String sub, String accessToken, String refreshToken) {
        OAuth oauth = new OAuth();
        oauth.sub = sub;
        oauth.accessToken = accessToken;
        oauth.refreshToken = refreshToken;
        oauth.baseStatus = BaseStatus.ACTIVE;
        return oauth;
    }


}
