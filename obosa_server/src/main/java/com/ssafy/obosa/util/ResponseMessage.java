package com.ssafy.obosa.util;

public class ResponseMessage
{
    //Login
    public static final String LOGIN_SUCCESS = "로그인 성공";
    public static final String LOGIN_FAIL = "로그인 실패";
    public static final String AUTH_FAIL = "인증 실패";
    public static final String AUTH_SUCCESS = "인증 성공";

    public static final String NOT_FOUND = "조회 실패";

    //Signup
    public static final String READ_USER = "회원 정보 조회 성공";
    public static final String ABLE_USER = "id 생성 가능";
    public static final String UNABLE_USER = "이미 존재하는 id";
    public static final String ABLE_EMAIL = "email 사용 가능";
    public static final String UNABLE_EMAIL = "이미 존재하는 email";
    public static final String NOT_FOUND_USER = "회원을 찾을 수 없습니다.";
    public static final String CREATED_USER = "회원 가입 성공";
    public static final String UPDATE_USER = "회원 정보 수정 성공";
    public static final String DELETE_USER = "회원 탈퇴 성공";

    //Common
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
    public static final String BAD_REQUEST = "올바르지 않은 요청";

    //Product
    public static final String CREATED_PRODUCT = "물품 등록 성공";
    public static final String NOT_FOUND_PRODUCT = "물품을 찾을 수 없습니다.";
    public static final String NOT_MATCHED_USER_AND_PRODUCT = "해당 유저의 물품이 아닙니다.";
    public static final String DELETED_PRODUCT = "물품 삭제 성공";
    public static final String UPDATED_PRODUCT = "물품 수정 성공";
}
