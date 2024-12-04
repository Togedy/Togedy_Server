package Togedy.server.Controller;

import Togedy.server.Dto.User.Request.UserLoginRequestDto;
import Togedy.server.Dto.User.Response.UserLoginResponseDto;
import Togedy.server.Service.UserService;
import Togedy.server.Util.BaseResponse;
import Togedy.server.Util.Exception.Validation.FieldValidationException;
import Togedy.server.Util.Exception.Validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public BaseResponse<UserLoginResponseDto> createUserForTest(@Validated @RequestBody UserLoginRequestDto requestDto, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) throw new FieldValidationException(bindingResult);
        UserLoginResponseDto responseDto = userService.createUserForTest(requestDto);
        return new BaseResponse<>(responseDto);
    }

}
