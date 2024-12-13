package Togedy.server.Controller.Planner;

import Togedy.server.Dto.FindByDateRequestDto;
import Togedy.server.Dto.Planner.Request.CreateStudyPlanRequestDto;
import Togedy.server.Dto.Planner.Response.ReadStudyPlansResponseDto;
import Togedy.server.Security.Auth.AuthMember;
import Togedy.server.Service.Planner.StudyPlanService;
import Togedy.server.Util.BaseResponse;
import Togedy.server.Util.Exception.Validation.FieldValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/studyPlan")
public class StudyPlanController {

    private final StudyPlanService studyPlanService;

    // 스터디 플랜 생성
    @PostMapping("/create")
    public BaseResponse<Map<String, Long>> createStudyPlan(
            @Validated @RequestBody CreateStudyPlanRequestDto requestDto,
            @AuthenticationPrincipal AuthMember authMember,
            BindingResult bindingResult) {
        // 유효성 검증 실패 시 처리
        if (bindingResult.hasErrors()) throw new FieldValidationException(bindingResult);

        Long studyPlanId = studyPlanService.save(authMember.getId(), requestDto);
        Map<String, Long> response = new HashMap<>();
        response.put("studyPlanId", studyPlanId);

        return new BaseResponse<>(response);
    }

    // 날짜별 스터디 플랜 조회
    @PostMapping("")
    public BaseResponse<List<ReadStudyPlansResponseDto>> getStudyPlansByDate(
            @RequestBody @Validated FindByDateRequestDto requestDto,
            @AuthenticationPrincipal AuthMember authMember,
            BindingResult bindingResult) {
        // 유효성 검증 실패 시 처리
        if (bindingResult.hasErrors()) throw new FieldValidationException(bindingResult);

        List<ReadStudyPlansResponseDto> response = studyPlanService.getStudyPlansByDate(authMember.getId(), requestDto);

        return new BaseResponse<>(response);
    }



}
