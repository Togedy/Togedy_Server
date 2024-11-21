package Togedy.server.Dto.Calendar.Request;

import Togedy.server.Entity.Calendar.Category;
import Togedy.server.Entity.User.User;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequestDto {

    @NotBlank(message = "카테고리 이름은 필수 항목입니다.")
    private String name;

    @NotBlank(message = "카테고리 색깔은 필수 항목입니다.")
    private String color;

    public Category toEntity(User user) {
        return Category.builder()
                .user(user)
                .name(name)
                .color(color)
                .build();
    }
}
