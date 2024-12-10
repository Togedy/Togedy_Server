package Togedy.server.Service;

import Togedy.server.Dto.Calendar.Request.CreatePersonalScheduleRequestDto;
import Togedy.server.Dto.Calendar.Response.ReadCategoryResponseDto;
import Togedy.server.Dto.Calendar.Response.ReadDailyResponseDto;
import Togedy.server.Dto.Calendar.Response.ReadMonthlyResponseDto;
import Togedy.server.Entity.Calendar.Category;
import Togedy.server.Entity.Calendar.PersonalSchedule;
import Togedy.server.Entity.User.User;
import Togedy.server.Repository.CategoryRepository;
import Togedy.server.Repository.ScheduleRepository;
import Togedy.server.Repository.UserRepository;
import Togedy.server.Util.BaseResponseStatus;
import Togedy.server.Util.Exception.Domain.CalendarException;
import Togedy.server.Util.Exception.Domain.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    // 개인 일정 추가
    @Transactional
    public Long save(Long userId, CreatePersonalScheduleRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(BaseResponseStatus.USER_NOT_EXIST));

        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new CalendarException(BaseResponseStatus.CATEGORY_NOT_EXIST));

        PersonalSchedule schedule = requestDto.toEntity(user, category);

        return scheduleRepository.save(schedule).getId();
    }

    // 개인 일정 월별 조회
    public List<ReadMonthlyResponseDto> getMonthlyCalendar(Long userId, LocalDate date) {
        // 월의 시작과 끝 계산
        YearMonth yearMonth = YearMonth.from(date);
        LocalDate startOfMonth = yearMonth.atDay(1);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();

        // 해당 월의 일정 조회
        List<PersonalSchedule> schedules = scheduleRepository.findSchedulesByMonth(userId, startOfMonth, endOfMonth);

        // 날짜별로 그룹화
        Map<LocalDate, List<PersonalSchedule>> groupedByDate = schedules.stream()
                .flatMap(schedule -> {
                    LocalDate start = schedule.getStartDate().isBefore(startOfMonth) ? startOfMonth : schedule.getStartDate();
                    LocalDate end = schedule.getEndDate() == null || schedule.getEndDate().isBefore(start) ? start : schedule.getEndDate();

                    List<LocalDate> range = new ArrayList<>();
                    for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {
                        range.add(d);
                    }
                    return range.stream().map(d -> Map.entry(d, schedule));
                })
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

        List<ReadMonthlyResponseDto> response = new ArrayList<>();
        groupedByDate.forEach((dateKey, schedulesForDate) -> {
            schedulesForDate.forEach(schedule -> {
                response.add(ReadMonthlyResponseDto.builder()
                        .date(dateKey.toString())
                        .scheduleName(schedule.getName())
                        .categoryColor(schedule.getCategory().getColor())
                        .build());
            });
        });

        return response;
    }

    // 개인 날짜별 일정 조회
    public List<ReadDailyResponseDto> getDailyCalendar(Long userId, LocalDate date) {
        // 해당 날짜의 일정 조회
        List<PersonalSchedule> schedules = scheduleRepository.findSchedulesByDate(userId, date);

        return schedules.stream()
                .map(schedule -> {
                    ReadCategoryResponseDto categoryDto = ReadCategoryResponseDto.of(schedule.getCategory());
                    return ReadDailyResponseDto.of(schedule, categoryDto);
                })
                .collect(Collectors.toList());
    }
}
