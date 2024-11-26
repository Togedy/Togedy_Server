package Togedy.server.Dto.Calendar.Response;

import Togedy.server.Entity.Calendar.Category;
import Togedy.server.Entity.Calendar.Schedule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadDailyResponseDto {
    private Long id;
    private String name;
    private String memo;
    private LocalDate startDate;
    private LocalDate endDate;
    private Category category;

    public static ReadDailyResponseDto of(Schedule schedule) {
        return ReadDailyResponseDto.builder()
                .id(schedule.getId())
                .name(schedule.getName())
                .memo(schedule.getMemo())
                .startDate(schedule.getStartDate())
                .endDate(schedule.getEndDate())
                .category(schedule.getCategory())
                .build();
    }
}
