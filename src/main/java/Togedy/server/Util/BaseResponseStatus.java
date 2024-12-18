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

    // JWT 예외 - Filter에서 처리
    ACCESS_DENIED(false, 2001, "권한이 없는 유저의 접근입니다."),
    EMPTY_AUTHORIZATION_HEADER(false, 2002, "Authorization 헤더가 존재하지 않습니다."),
    EXPIRED_ACCESS_TOKEN(false, 2003, "이미 만료된 Access 토큰입니다."),
    UNSUPPORTED_TOKEN_TYPE(false, 2004, "지원되지 않는 토큰 형식입니다."),
    MALFORMED_TOKEN_TYPE(false, 2005, "인증 토큰이 올바르게 구성되지 않았습니다."),
    INVALID_SIGNATURE_JWT(false, 2006, "인증 시그니처가 올바르지 않습니다"),
    INVALID_TOKEN_TYPE(false, 2007, "잘못된 토큰입니다."),

    // Refresh Token 예외 - Exception Handler에서 처리
    EXPIRED_REFRESH_TOKEN(false, 2008, "Refresh 토큰이 만료되어 재로그인이 필요합니다."),
    INVALID_REFRESH_TOKEN(false, 2009, "잘못된 Refresh 토큰입니다."),

    // IdToken 예외
    EXPIRED_ID_TOKEN(false, 2020, "이미 만료된 ID 토큰입니다."),

    // UserException
    DUPLICATED_EMAIL(false, 2010, "중복된 이메일입니다."),
    DUPLICATED_NICKNAME(false, 2011, "중복된 닉네임입니다."),
    USER_NOT_EXIST(false, 2012, "존재하지 않는 회원입니다."),
    OAUTH_NOT_EXIST(false, 2013, "소셜 계정이 존재하지 않습니다."),
    INVALID_USER(false, 2013, "일치하지 않는 유저입니다."),

    INVALID_FIELD(false, 2030, "요청 값이 잘못되었습니다."),
    EMPTY_REQUEST_PARAMETER(false, 2031, "Request Parameter가 존재하지 않습니다."),
    METHOD_ARGUMENT_TYPE_MISMATCH(false, 2032, "Request Parameter나 Path Variable의 유형이 불일치합니다."),


    /**
     * 4000: Post 게시글 오류
     */
    POST_NOT_EXIST(false, 4000, "게시글이 존재하지 않습니다."),
    ALREADY_LIKED_THIS_POST(false, 4001, "이미 게시글에 좋아요를 눌렀습니다."),
    LIKE_NOT_EXIST(false, 4002, "좋아요가 존재하지 않습니다."),
    COMMENT_NOT_EXIST(false, 4003, "댓글이 존재하지 않습니다."),
    ALREADY_LIKED_THIS_COMMENT(false, 4004, "이미 댓글에 좋아요를 눌렀습니다."),
    COMMENT_NOT_BELONG_TO_POST(false, 4005, "댓글이 해당 게시글에 속하지 않습니다."),


    /**
     * 5000: Calendar 일정 오류
     */
    DUPLICATED_CATEGORYNAME(false, 5001, "중복된 카테고리 이름입니다."),
    CATEGORY_NOT_EXIST(false, 5002, "존재하지 않는 카테고리입니다."),
    INVALID_DATE_RANGE(false, 5003, "잘못된 날짜 범위입니다."),

    /**
     * 6000: Planner 플래너 오류
     */
    DUPLICATED_TAG_NAME(false, 6001, "중복된 스터디 태그 이름입니다."),
    TAG_NOT_EXIST(false, 6002, "존재하지 않는 스터디 태그입니다."),
    PLANNER_NOT_EXIST(false, 6003, "존재하지 않는 플래너입니다."),
    GOAL_NOT_EXIST(false, 6004, "존재하지 않는 스터디 목표입니다."),
    PLAN_NOT_EXIST(false, 6005, "존재하지 않는 스터디 플랜입니다."),



    /**
     * 9000 : MultipartFile 오류
     */

    IS_NOT_IMAGE_FILE(false, 9000, "지원되는 이미지 파일의 형식이 아닙니다."),
    MULTIPARTFILE_CONVERT_FAIL_IN_MEMORY(false, 9001,"multipartFile memory 변환 과정에서 문제가 생겼습니다."),
    FAILED_TO_UPLOAD_FILE(false, 9002, "이미지 업로드에 실패했습니다."),
    ;

    private final boolean isSuccess;
    private final int responseCode;
    private final String responseMessage;

}
