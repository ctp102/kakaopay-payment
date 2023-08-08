package com.kakaopay.payment.core.payment.dto;

import lombok.Getter;
import lombok.Setter;

// https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-request-body
@Getter
@Setter
public class PaymentReadyRequest {

    private String cid; // 가맹점 코드 10자(테스트 코드는 TC0ONETIME)
    private String cidSecret; // 가맹점 코드 인증키, 24자, 숫자와 영문 소문자 조합
    private String partnerOrderId; // 가맹점 주문번호
    private String partnerUserId; // 가맹점 회원 id
    private String itemName; // 상품명
    private String itemCode; // 상품코드
    private Integer quantity; // 상품 수량
    private Integer totalAmount; // 상품 총액
    private Integer vatAmount; // 상품 부가세
    private Integer taxFreeAmount; // 상품 비과세 금액
    private Integer greenDeposit; // 컵 보증금
    private String approvalUrl; // 결제 성공 시 redirect url
    private String cancelUrl; // 결제 취소 시 redirect url
    private String failUrl; // 결제 실패 시 redirect url

}
