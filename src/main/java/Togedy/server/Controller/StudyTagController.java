package Togedy.server.Controller;

import Togedy.server.Dto.Planner.Request.CreateStudyTagRequestDto;
import Togedy.server.Security.Auth.AuthMember;
import Togedy.server.Service.StudyTagService;
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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/planner/studytag")
public class StudyTagController {

    private final StudyTagService studyTagService;

    @PostMapping("")
    public BaseResponse<Map<String, Long>> createStudyTag(
            @Validated @RequestBody CreateStudyTagRequestDto requestDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal AuthMember authMember) {

        // 유효성 검증 실패 시 처리
        if (bindingResult.hasErrors()) throw new FieldValidationException(bindingResult);

        Long studyTagId = studyTagService.createStudyTag(authMember.getId(), requestDto);
        Map<String, Long> response = new HashMap<>();
        response.put("studyTagId", studyTagId);

        return new BaseResponse<>(response);

    }

}
