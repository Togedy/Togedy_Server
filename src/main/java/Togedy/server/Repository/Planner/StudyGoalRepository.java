package Togedy.server.Repository.Planner;

import Togedy.server.Entity.StudyPlanner.Planner;
import Togedy.server.Entity.StudyPlanner.StudyGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface StudyGoalRepository extends JpaRepository<StudyGoal, Long> {
    Optional<StudyGoal> findByPlannerAndDate(Planner planner, LocalDate date);
}
