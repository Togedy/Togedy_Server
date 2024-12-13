package Togedy.server.Controller.Planner;

import Togedy.server.Dto.Planner.Request.CreateStudyGoalRequestDto;
import Togedy.server.Dto.Planner.Response.CreateStudyGoalResponseDto;
import Togedy.server.Security.Auth.AuthMember;
import Togedy.server.Service.Planner.StudyGoalService;
import Togedy.server.Util.BaseResponse;
import Togedy.server.Util.Exception.Validation.FieldValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/studyGoal")
public class StudyGoalController {
    private final StudyGoalService studyGoalService;

    // 목표 공부량 설정
    @PostMapping("")
    public BaseResponse<CreateStudyGoalResponseDto> setStudyGoal(
            @RequestBody @Validated CreateStudyGoalRequestDto requestDto,
            @AuthenticationPrincipal AuthMember authMember,
            BindingResult bindingResult) {

        // 유효성 검증 실패 시 처리
        if (bindingResult.hasErrors()) throw new FieldValidationException(bindingResult);

        CreateStudyGoalResponseDto responseDto = studyGoalService.setStudyGoal(authMember.getId(), requestDto);

        return new BaseResponse<>(responseDto);
    }

    // 목표 공부량 수정
    @PutMapping("/{goalId}")
    public BaseResponse<Map<String, Long>> updateStudyGoal(
            @RequestBody @Validated CreateStudyGoalRequestDto requestDto,
            @PathVariable Long goalId,
            @AuthenticationPrincipal AuthMember authMember,
            BindingResult bindingResult) {
        // 유효성 검증 실패 시 처리
        if (bindingResult.hasErrors()) throw new FieldValidationException(bindingResult);

        Long studyGoalId = studyGoalService.updateStudyGoal(authMember.getId(), goalId, requestDto);

        Map<String, Long> response = new HashMap<>();
        response.put("studyGoalId", studyGoalId);

        return new BaseResponse<>(response);
    }
}
