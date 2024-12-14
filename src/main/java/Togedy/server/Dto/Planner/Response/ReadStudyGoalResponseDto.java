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
public class ReadStudyGoalResponseDto {
    private Long id;
    private LocalTime targetTime; // HH:mm
    private LocalTime actualTime; // HH:mm
    private int achievement;
    public static ReadStudyGoalResponseDto of(StudyGoal studyGoal) {
        return ReadStudyGoalResponseDto.builder()
                .id(studyGoal.getId())
                .targetTime(studyGoal.getTargetTime())
                .actualTime(studyGoal.getActualTime())
                .achievement(studyGoal.calculateAchievement())
                .build();
    }
}
