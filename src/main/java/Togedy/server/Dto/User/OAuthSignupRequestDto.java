package Togedy.server.Dto.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OAuthSignupRequestDto {
    @Pattern(regexp = "[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]+", message = "영문 또는 한글 1 ~ 10자리 이내")
    @Length(min = 1, max = 10, message = "영문 또는 한글 1 ~ 10자리 이내")
    private String nickname;

    @NotBlank
    private String idToken;
}
