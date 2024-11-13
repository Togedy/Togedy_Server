package Togedy.server.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BaseResponseStatus {
    /**
     * 1000: 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),

    /**
     * 2000: Request 오류 (BAD_REQUEST)
     */
    BAD_REQUEST(false, 2000, "잘못된 요청입니다."),

    // UserException
    DUPLICATED_EMAIL(false, 2001, "중복된 이메일입니다."),


    /**
     * 4000: Post 게시글 오류
     */
    POST_NOT_EXIST(false, 4000, "게시글이 존재하지 않습니다."),

    /**
     * 9000 : MultipartFile 오류
     */

    IS_NOT_IMAGE_FILE(false, 9000, "지원되는 이미지 파일의 형식이 아닙니다."),
    MULTIPARTFILE_CONVERT_FAIL_IN_MEMORY(false, 9001,"multipartFile memory 변환 과정에서 문제가 생겼습니다.")

    ;

    private final boolean isSuccess;
    private final int responseCode;
    private final String responseMessage;

}
