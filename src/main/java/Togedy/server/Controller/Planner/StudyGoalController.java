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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/studyGoal")
public class StudyGoalController {
    private final StudyGoalService studyGoalService;

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
}
