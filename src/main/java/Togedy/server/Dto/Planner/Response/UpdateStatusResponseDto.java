package Togedy.server.Dto.Planner.Response;

import Togedy.server.Entity.StudyPlanner.PlanStatus;
import Togedy.server.Entity.StudyPlanner.StudyPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusResponseDto {

    private Long id;
    private PlanStatus status;

    public static UpdateStatusResponseDto of(StudyPlan studyPlan) {
        return UpdateStatusResponseDto.builder()
                .id(studyPlan.getId())
                .status(studyPlan.getStatus())
                .build();
    }

}
