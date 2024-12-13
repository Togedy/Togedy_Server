package Togedy.server.Dto.Planner.Response;

import Togedy.server.Entity.StudyPlanner.StudyTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadStudyTagResponseDto {

    private Long id;
    private String name;
    private String color;

    public static ReadStudyTagResponseDto of (StudyTag studyTag) {
        return ReadStudyTagResponseDto.builder()
                .id(studyTag.getId())
                .name(studyTag.getName())
                .color(studyTag.getColor())
                .build();
    }
}
