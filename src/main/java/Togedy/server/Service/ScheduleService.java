package Togedy.server.Service;

import Togedy.server.Dto.Calendar.Request.CreatePersonalScheduleRequestDto;
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

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long save(Long userId, CreatePersonalScheduleRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(BaseResponseStatus.USER_NOT_EXIST));

        Category category = categoryRepository.findById(requestDto.getCategoryId())
                .orElseThrow(() -> new CalendarException(BaseResponseStatus.CATEGORY_NOT_EXIST));

        PersonalSchedule schedule = requestDto.toEntity(user, category);

        return scheduleRepository.save(schedule).getId();
    }
}
