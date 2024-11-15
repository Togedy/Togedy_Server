package Togedy.server.Repository;

import Togedy.server.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    @Query("SELECT u FROM User u WHERE u.oauth.id = :oauthId")
    Optional<User> findByOauthId(@Param("oauthId") Long oauthId);
}
