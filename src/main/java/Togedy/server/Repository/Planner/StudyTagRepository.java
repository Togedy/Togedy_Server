package Togedy.server.Repository.Planner;

import Togedy.server.Entity.StudyPlanner.Planner;
import Togedy.server.Entity.StudyPlanner.StudyTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyTagRepository extends JpaRepository<StudyTag, Long> {

    // 스터디 태그 이름 중복 확인
    boolean existsByNameAndPlanner(String name, Planner planner);

    // 스터디플랜별 태그 조회
    List<StudyTag> findByPlanner(Planner planner);

    // 스터디플랜과 id로 태그 조회
    Optional<StudyTag> findByIdAndPlanner(Long id, Planner planner);
}
