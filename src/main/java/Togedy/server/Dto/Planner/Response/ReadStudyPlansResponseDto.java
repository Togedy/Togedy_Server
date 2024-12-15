package Togedy.server.Dto.Planner.Response;

import Togedy.server.Entity.StudyPlanner.PlanStatus;
import Togedy.server.Entity.StudyPlanner.StudyPlan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadStudyPlansResponseDto {
    private Long studyPlanId;
    private String name;
    private PlanStatus planStatus;
    private Long studyTagId;
    private String studyTagColor;
    private List<List<String>> studyRecords;

    public static ReadStudyPlansResponseDto of (StudyPlan studyPlan, List<List<String>> studyRecord) {

        return ReadStudyPlansResponseDto.builder()
                .studyPlanId(studyPlan.getId())
                .name(studyPlan.getName())
                .planStatus(studyPlan.getStatus())
                .studyTagId(studyPlan.getStudyTag().getId())
                .studyTagColor(studyPlan.getStudyTag().getColor())
                .studyRecords(studyRecord)
                .build();
    }

}
