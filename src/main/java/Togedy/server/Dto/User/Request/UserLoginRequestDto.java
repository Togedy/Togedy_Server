package Togedy.server.Dto.User.Request;

import Togedy.server.Entity.User.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestDto {

    @Pattern(regexp = "[a-zA-Zㄱ-ㅎㅏ-ㅣ가-힣]+", message = "영문 또는 한글 1 ~ 10자리 이내")
    @Length(min = 1, max = 10, message = "영문 또는 한글 1 ~ 10자리 이내")
    private String nickname;

    @Email(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}", message = "이메일 형식 확인")
    @Length(min = 5 ,max = 320, message = "5 ~ 320자리 이내")
    private String email;


}
