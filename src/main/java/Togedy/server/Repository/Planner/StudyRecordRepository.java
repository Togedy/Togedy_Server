package Togedy.server.Repository.Planner;

import Togedy.server.Entity.StudyPlanner.StudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudyRecordRepository extends JpaRepository<StudyRecord, Long> {
    List<StudyRecord> findAllByDate(LocalDate date);
}
