package Togedy.server.Dto.User.Response;

import Togedy.server.Security.Jwt.JwtInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserLoginResponseDto {
    private Long userId;
    private JwtInfo jwtInfo;

    public static UserLoginResponseDto of (Long userId, JwtInfo jwtInfo) {
        return UserLoginResponseDto.builder()
                .userId(userId)
                .jwtInfo(jwtInfo)
                .build();
    }
}
