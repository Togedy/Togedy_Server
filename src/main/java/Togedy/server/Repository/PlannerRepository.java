package Togedy.server.Repository;

import Togedy.server.Entity.StudyPlanner.Planner;
import Togedy.server.Entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlannerRepository extends JpaRepository<Planner, Long> {
    Optional<Planner> findByUserId(Long userId);
    Optional<Planner> findByUser(User user);

}
