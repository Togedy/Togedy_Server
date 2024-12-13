package Togedy.server.Controller;

import Togedy.server.Dto.Calendar.Request.CreateCategoryRequestDto;
import Togedy.server.Dto.Calendar.Response.ReadCategoryResponseDto;
import Togedy.server.Security.Auth.AuthMember;
import Togedy.server.Service.CategoryService;
import Togedy.server.Util.BaseResponse;
import Togedy.server.Util.Exception.Validation.FieldValidationException;
import Togedy.server.Util.Exception.Validation.ValidationException;
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
@RequestMapping("/calendar")
public class CategoryController {

    private final CategoryService categoryService;

    // 카테고리 추가
    @PostMapping("/category")
    public BaseResponse<Map<String, Long>> createCategory(
            @Validated @RequestBody CreateCategoryRequestDto requestDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal AuthMember authMember) {

        // 유효성 검증 실패 시 처리
        if (bindingResult.hasErrors()) throw new FieldValidationException(bindingResult);

        Long categoryId = categoryService.createCategory(authMember.getId(), requestDto);
        Map<String, Long> response = new HashMap<>();
        response.put("categoryId", categoryId);

        return new BaseResponse<>(response);
    }

    // 카테고리 조회
    @GetMapping("/category")
    public BaseResponse<List<ReadCategoryResponseDto>> getUserCategories(
            @AuthenticationPrincipal AuthMember authMember) {

        List<ReadCategoryResponseDto> response = categoryService.getUserCategories(authMember.getId());
        return new BaseResponse<>(response);
    }
}
