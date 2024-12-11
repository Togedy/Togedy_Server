package Togedy.server.Dto.Planner.Request;

import Togedy.server.Entity.StudyPlanner.Planner;
import Togedy.server.Entity.StudyPlanner.StudyTag;
import Togedy.server.Entity.User.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudyTagRequestDto {
    @NotBlank(message = "카테고리 이름은 필수 항목입니다.")
    private String name;

    @NotBlank(message = "카테고리 색깔은 필수 항목입니다.")
    private String color;

    public StudyTag toEntity(Planner planner) {
        return StudyTag.builder()
                .planner(planner)
                .name(name)
                .color(color)
                .build();
    }

}
