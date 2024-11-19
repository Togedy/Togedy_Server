package Togedy.server.Repository;

import Togedy.server.Entity.Calendar.PersonalSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PersonalScheduleRepository extends JpaRepository<PersonalSchedule, Long> {


}
