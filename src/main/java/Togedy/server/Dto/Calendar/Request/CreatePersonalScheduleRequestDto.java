package Togedy.server.Dto.Calendar.Request;

import Togedy.server.Entity.Calendar.Category;
import Togedy.server.Entity.Calendar.PersonalSchedule;
import Togedy.server.Entity.User.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePersonalScheduleRequestDto {
    private String name;
    private String memo;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long categoryId;

    public PersonalSchedule toEntity(User user, Category category) {
        return PersonalSchedule.builder()
                .user(user)
                .name(name)
                .memo(memo)
                .startDate(startDate)
                .endDate(endDate)
                .category(category)
                .build();
    }
}
