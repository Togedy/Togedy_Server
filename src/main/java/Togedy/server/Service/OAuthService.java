package Togedy.server.Service;

import Togedy.server.Dto.User.OAuthSignupRequestDto;
import Togedy.server.Dto.User.Response.UserLoginResponseDto;
import Togedy.server.Entity.OAuth;
import Togedy.server.Entity.User.User;
import Togedy.server.Repository.OAuthRepository;
import Togedy.server.Repository.UserRepository;
import Togedy.server.Security.Jwt.JwtInfo;
import Togedy.server.Security.Jwt.JwtParser;
import Togedy.server.Security.Jwt.JwtTokenProvider;
import Togedy.server.Security.Jwt.OAuthTokenInfo;
import Togedy.server.Util.BaseResponseStatus;
import Togedy.server.Util.Exception.Domain.UserException;
import Togedy.server.Util.Exception.SignUpNotCompletedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OAuthService {

    private final UserRepository userRepository;
    private final OAuthRepository oauthRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtParser jwtParser;


    @Transactional
    public UserLoginResponseDto sdkLogin(OAuthTokenInfo oauthTokenInfo) {
        String idToken = oauthTokenInfo.getIdToken();
        String sub = jwtParser.parseSub(idToken);
        String email = jwtParser.parseEmail(idToken);

        // token의 sub로 OAuth 조회, 없으면 첫 로그인이므로 회원가입 마저 진행
        Long userId = checkAlreadySignUp(oauthTokenInfo, sub);

        JwtInfo jwtInfo = jwtTokenProvider.createToken(new UsernamePasswordAuthenticationToken(email, ""));

        return UserLoginResponseDto.of(userId, jwtInfo);
    }

    @Transactional
    public UserLoginResponseDto singup(OAuthSignupRequestDto requestDto) {
        String idToken = requestDto.getIdToken();
        String sub = jwtParser.parseSub(idToken);
        String email = jwtParser.parseEmail(idToken);
        String nickname = requestDto.getNickname();

        OAuth oauth = oauthRepository.findBySub(sub)
                .orElseThrow(() -> {throw new UserException(BaseResponseStatus.OAUTH_NOT_EXIST);});

        if (userRepository.existsByEmail(email)) throw new UserException(BaseResponseStatus.DUPLICATED_EMAIL);
        if (userRepository.existsByNickname(nickname)) throw new UserException(BaseResponseStatus.DUPLICATED_NICKNAME);

        userRepository.saveAndFlush(User.createUser(email, nickname));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> { throw new UserException(BaseResponseStatus.USER_NOT_EXIST);});
        user.createOauthAssociation(oauth);

        JwtInfo jwtInfo = jwtTokenProvider.createToken(new UsernamePasswordAuthenticationToken(email, ""));

        return UserLoginResponseDto.of(user.getId(), jwtInfo);
    }

    private Long checkAlreadySignUp(OAuthTokenInfo oAuthTokenInfo, String sub) {
        OAuth oauth = oauthRepository.findBySub(sub)
                .orElseThrow(() -> {
                    oauthRepository.save(OAuth.createOauth(sub, oAuthTokenInfo.getAccessToken(), oAuthTokenInfo.getRefreshToken()));
                    throw new SignUpNotCompletedException();
                });

        User user = userRepository.findByOauthId(oauth.getId())
                .orElseThrow(SignUpNotCompletedException::new);

        return user.getId();
    }
}
