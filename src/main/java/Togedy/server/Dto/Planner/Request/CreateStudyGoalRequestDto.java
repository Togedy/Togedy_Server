package Togedy.server.Dto.Planner.Request;

import Togedy.server.Entity.StudyPlanner.Planner;
import Togedy.server.Entity.StudyPlanner.StudyGoal;
import Togedy.server.Entity.StudyPlanner.StudyPlan;
import Togedy.server.Entity.StudyPlanner.StudyTag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudyGoalRequestDto {

    @NotNull(message = "날짜는 필수 항목입니다.")
    private LocalDate date;

    @NotNull(message = "목표 시간은 필수 항목입니다.")
    private LocalTime targetTime;

    public StudyGoal toEntity(Planner planner, LocalTime existingActualTime) {
        return StudyGoal.builder()
                .planner(planner)
                .date(date)
                .targetTime(targetTime)
                .actualTime(existingActualTime)
                .build();
    }
}
