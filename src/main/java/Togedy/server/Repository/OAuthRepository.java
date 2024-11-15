package Togedy.server.Repository;

import Togedy.server.Entity.OAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OAuthRepository extends JpaRepository<OAuth, Long> {
    Optional<OAuth> findBySub(String sub);
}
