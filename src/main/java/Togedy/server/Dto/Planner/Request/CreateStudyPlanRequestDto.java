package Togedy.server.Dto.Planner.Request;

import Togedy.server.Entity.StudyPlanner.Planner;
import Togedy.server.Entity.StudyPlanner.StudyPlan;
import Togedy.server.Entity.StudyPlanner.StudyTag;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudyPlanRequestDto {

    @NotBlank(message = "내용은 필수 항목입니다.")
    private String name;

    @NotBlank(message = "날짜는 필수 항목입니다.")
    private LocalDate date;

    @NotBlank(message = "스터디태그는 필수 항목입니다.")
    private Long studyTagId;

    public StudyPlan toEntity(Planner planner, StudyTag studyTag) {
        return StudyPlan.builder()
                .planner(planner)
                .name(name)
                .date(date)
                .studyTag(studyTag)
                .build();
    }
}
