package Togedy.server.Security.Jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class JwtInfo {
    private String accessToken;
    private String refreshToken;
}
