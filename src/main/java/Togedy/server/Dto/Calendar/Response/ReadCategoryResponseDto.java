package Togedy.server.Dto.Calendar.Response;

import Togedy.server.Entity.Calendar.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadCategoryResponseDto {
    private Long id;
    private String name;
    private String color;

    public static ReadCategoryResponseDto of(Category category) {
        return ReadCategoryResponseDto.builder()
                .id(category.getId())
                .name(category.getName())
                .color(category.getColor())
                .build();
    }
}
