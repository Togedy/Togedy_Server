package Togedy.server.Repository.Planner;

import Togedy.server.Entity.StudyPlanner.Planner;
import Togedy.server.Entity.StudyPlanner.StudyTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyTagRepository extends JpaRepository<StudyTag, Long> {

    boolean existsByNameAndPlanner(String name, Planner planner);

    List<StudyTag> findByPlanner(Planner planner);
}
