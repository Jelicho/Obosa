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
    NOT_PERMISSION_ACCESS("접근 권한이 없습니다."),
    //Product
    CREATED_PRODUCT("물품 등록 성공"),
    NOT_FOUND_PRODUCT("해당 물품을 찾을 수 없습니다."),
    NOT_MATCHED_USER_AND_PRODUCT("해당 유저의 물품이 아닙니다."),
    DELETED_PRODUCT("물품 삭제 성공"),
    UPDATED_PRODUCT("물품 수정 성공"),
    NOT_FOUND_PRODUCTS("등록된 상품이 없습니다."),
    READ_ALL_PRODUCTS("유저 등록 물품 탐색 성공"),
    READ_PRODUCT("물품 탐색 성공"),
    //Auction
    CREATED_AUCTION("경매 등록 성공"),
    NOT_FOUND_AUCTION("해당 경매를 찾을 수 없습니다."),
    DELETED_AUCTION("경매 삭제 성공"),
    UPDATED_AUCTION("경매 수정 성공"),
    READ_ALL_AUCTIONS("경매 전체 탐색 성공"),
    READ_AUCTION("경매 탐색 성공"),
    TYPE_ERROR("지원하는 않는 Type입니다."),
    READ_SEARCH_AUCTIONS("경매 검색 성공"),
    NOT_FOUND_SEARCH("경매 검색 해당 항목이 없습니다."),
    BID_SUCCESS("경매 입찰 성공"),
    EXPIRED_AUCTION("경매 시간 만료"),
    BID_LOWER_THAN_CURRENT_HIGHEST("현재 입찰금보다 적은 액수로 입찰할 수 없습니다."),
    //WinningBid
    NOT_FOUND_WINNINGBID("해당 결제는 존재하지 않습니다."),
    DELETED_WINNINGBID_AND_AUCTION("결제내역과 경매 삭제 성공"),
    UPDATED_WINNINGBID_STATE("결제내역의 상태가 변경완료됐습니다."),
    UPDATED_WINNINGBID_ADDRESS("결제내역의 주소가 변경완료됐습니다."),
    READ_WINNINGBID("결제 상세 내역 조회 성공"),
    READ_WINNINGBID_SELLER("판매자 결제 내역 조회 성공"),
    READ_WINNINGBID_WINNER("구매자 결제 내역 조회 성공"),
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
