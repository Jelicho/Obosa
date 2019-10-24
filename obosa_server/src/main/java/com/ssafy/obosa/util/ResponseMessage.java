package com.ssafy.obosa.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    //Login
    LOGIN_SUCCESS("로그인 성공"),
    LOGIN_FAIL("로그인 실패"),
    AUTH_FAIL("인증 실패"),
    AUTH_SUCCESS("인증 성공"),
    NOT_FOUND("조회 실패"),
    READ_USER("회원 정보 조회 성공"),
    ABLE_USER("id 생성 가능"),
    UNABLE_USER("이미 존재하는 id"),
    ABLE_EMAIL("email 사용 가능"),
    UNABLE_EMAIL("이미 존재하는 email"),
    EMAIL_CONFIRMED("email 인증 완료"),
    NOT_FOUND_USER("회원을 찾을 수 없습니다."),
    CREATED_USER("회원 가입 성공"),
    UPDATE_USER("회원 정보 수정 성공"),
    DELETE_USER("회원 탈퇴 성공"),
    //Product
    CREATED_PRODUCT("물품 등록 성공"),
    NOT_FOUND_PRODUCT("해당 물품을 찾을 수 없습니다."),
    NOT_MATCHED_USER_AND_PRODUCT("해당 유저의 물품이 아닙니다."),
    DELETED_PRODUCT("물품 삭제 성공"),
    UPDATED_PRODUCT("물품 수정 성공"),
    NOT_FOUND_PRODUCTS("등록된 상품이 없습니다."),
    READED_ALL_PRODUCTS("유저 등록 물품 탐색 성공"),
    READED_PRODUCT("물품 탐색 성공"),
    //Auction
    CREATED_AUCTION("경매 등록 성공"),
    NOT_FOUND_AUCTION("해당 경매를 찾을 수 없습니다."),
    DELETED_AUCTION("경매 삭제 성공"),
    UPDATED_AUCTION("경매 수정 성공"),
    //Common
    INTERNAL_SERVER_ERROR("서버 내부 에러"),
    DB_ERROR("데이터베이스 에러"),
    BAD_REQUEST("올바르지 않은 요청"),
    //My Page
    READ_MYINFO("마이페이지 정보 조회 성공"),
    //Email Verification
    TOKEN_INVALID("유효하지 않은 토큰입니다"),
    TOKEN_EXPIRED("토큰 유효 기간이 만료되었습니다"),
    TOKEN_VALID("이메일 인증 성공");

    private String message;
}
