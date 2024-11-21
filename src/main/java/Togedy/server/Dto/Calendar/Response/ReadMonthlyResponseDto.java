package Togedy.server.Dto.Calendar.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReadMonthlyResponseDto {
    private String date;
    private String scheduleName;
    private String categoryColor;
}
