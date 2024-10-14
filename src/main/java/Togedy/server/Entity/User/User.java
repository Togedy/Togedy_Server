package Togedy.server.Entity.User;

import Togedy.server.Entity.BaseEntity;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "image_url")
    private String imageUrl;

    private String goal;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus status = UserStatus.ACTIVE;
}
