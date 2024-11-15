package Togedy.server.Service;

import Togedy.server.Dto.User.Request.UserLoginRequestDto;
import Togedy.server.Dto.User.Response.UserLoginResponseDto;
import Togedy.server.Entity.User.User;
import Togedy.server.Repository.UserRepository;
import Togedy.server.Security.Auth.AuthMember;
import Togedy.server.Security.Jwt.JwtInfo;
import Togedy.server.Security.Jwt.JwtTokenProvider;
import Togedy.server.Util.BaseResponseStatus;
import Togedy.server.Util.Exception.Domain.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserLoginResponseDto createUserForTest(UserLoginRequestDto requestDto) {
        // 이메일 중복 체크
        if(userRepository.existsByEmail(requestDto.getEmail())) throw new UserException(BaseResponseStatus.DUPLICATED_EMAIL);
        // 닉네임 중복 체크
        if(userRepository.existsByNickname(requestDto.getNickname())) throw new UserException(BaseResponseStatus.DUPLICATED_NICKNAME);

        User user = userRepository.saveAndFlush(
                User.createUser(
                        requestDto.getEmail(),
                        requestDto.getNickname()));

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(user.getNickname(), user.getEmail());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        JwtInfo jwtInfo = jwtTokenProvider.createToken(authentication);
        AuthMember principal = (AuthMember) authentication.getPrincipal();

        return UserLoginResponseDto.of(principal.getId(), jwtInfo);
    }
}
