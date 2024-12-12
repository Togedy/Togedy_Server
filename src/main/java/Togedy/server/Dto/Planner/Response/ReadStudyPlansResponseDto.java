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
    private Long id;
    private String name;
    private String studyTagColor;
    private PlanStatus planStatus;
    private List<List<String>> studyRecords;

    public static ReadStudyPlansResponseDto of (StudyPlan studyPlan, List<List<String>> studyRecord) {

        return ReadStudyPlansResponseDto.builder()
                .id(studyPlan.getId())
                .name(studyPlan.getName())
                .studyTagColor(studyPlan.getStudyTag().getColor())
                .planStatus(studyPlan.getStatus())
                .studyRecords(studyRecord)
                .build();
    }

}
