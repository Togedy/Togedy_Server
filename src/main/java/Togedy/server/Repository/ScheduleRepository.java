package Togedy.server.Repository;

import Togedy.server.Entity.Calendar.PersonalSchedule;
import Togedy.server.Entity.Calendar.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // 개인 일정 월별 조회
    @Query("SELECT ps FROM PersonalSchedule ps " +
            "JOIN FETCH ps.category c " +
            "WHERE ps.user.id = :userId " +
            "AND ps.startDate <= :endOfMonth " +
            "AND (ps.endDate IS NULL OR ps.endDate >= :startOfMonth)")
    List<PersonalSchedule> findSchedulesByMonth(
            @Param("userId") Long userId,
            @Param("startOfMonth") LocalDate startOfMonth,
            @Param("endOfMonth") LocalDate endOfMonth);

    // 개인 일정 날짜별 조회
    @Query("SELECT ps FROM PersonalSchedule ps " +
            "JOIN FETCH ps.category c " +
            "WHERE ps.user.id = :userId " +
            "AND ps.startDate <= :date " +
            "AND (ps.endDate IS NULL OR ps.endDate >= :date)")
    List<PersonalSchedule> findSchedulesByDate(
            @Param("userId") Long userId,
            @Param("date") LocalDate date);

}
