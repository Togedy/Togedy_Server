package Togedy.server.Dto.Planner.Response;

import Togedy.server.Entity.StudyPlanner.StudyGoal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudyGoalResponseDto {
    private Long id;
    private LocalTime targetTime; // HH:mm
    private LocalTime actualTime; // HH:mm
    private int achievement;

    public static CreateStudyGoalResponseDto of(StudyGoal studyGoal) {
        return CreateStudyGoalResponseDto.builder()
                .id(studyGoal.getId())
                .targetTime(studyGoal.getTargetTime())
                .targetTime(studyGoal.getActualTime())
                .achievement(studyGoal.calculateAchievement())
                .build();
    }
}
