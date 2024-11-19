package Togedy.server.Controller;

import Togedy.server.Dto.Calendar.Request.CreatePersonalScheduleRequestDto;
import Togedy.server.Security.Auth.AuthMember;
import Togedy.server.Service.ScheduleService;
import Togedy.server.Util.BaseResponse;
import Togedy.server.Util.Exception.Validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendar")
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 개인 월별 캘린더 조회


    // 개인 날짜별 일정 조회


    // 개인 일정 추가
    @PostMapping("/schedule")
    public BaseResponse<Map<String, Long>> createPersonalSchedule(
            @Validated @RequestBody CreatePersonalScheduleRequestDto requestDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal AuthMember authMember) {

        // 유효성 검증 실패 시 처리
        if (bindingResult.hasErrors()) throw new ValidationException(bindingResult);

        Long scheduleId = scheduleService.save(authMember.getId(), requestDto);
        Map<String, Long> response = new HashMap<>();
        response.put("scheduleId", scheduleId);

        return new BaseResponse<>(response);
    }
}