package Togedy.server.Controller;

import Togedy.server.Dto.User.OAuthSignupRequestDto;
import Togedy.server.Dto.User.Response.UserLoginResponseDto;
import Togedy.server.Security.Jwt.OAuthTokenInfo;
import Togedy.server.Service.OAuthService;
import Togedy.server.Util.BaseResponse;
import Togedy.server.Util.Exception.FieldValidationException;
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
@RequestMapping("/oauth/sdk")
public class OAuthSdkController {

    private final OAuthService oauthService;

    @PostMapping("/login")
    public BaseResponse<UserLoginResponseDto> sdkLogin(@RequestBody OAuthTokenInfo oauthTokenInfo) {
        UserLoginResponseDto userLoginResponseDto = oauthService.sdkLogin(oauthTokenInfo);
        return new BaseResponse<>(userLoginResponseDto);
    }

    @PostMapping("/signup")
    public BaseResponse<UserLoginResponseDto> singup(@RequestBody @Validated OAuthSignupRequestDto requestDto,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) throw new FieldValidationException(bindingResult);

        UserLoginResponseDto responseDto = oauthService.singup(requestDto);
        return new BaseResponse<>(responseDto);
    }


}
