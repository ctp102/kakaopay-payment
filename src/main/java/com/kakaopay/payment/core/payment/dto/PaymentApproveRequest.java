package com.kakaopay.payment.core.payment.dto;

import lombok.Getter;
import lombok.Setter;

// https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve-request-body
@Getter
@Setter
public class PaymentApproveRequest {

    private String cid; // 가맹점 코드 10자(테스트 코드는 TC0ONETIME)
    private String cidSecret; // 가맹점 코드 인증키, 24자, 숫자와 영문 소문자 조합
    private String tid; // 결제 고유 번호, 결제 준비 API 응답에 포함
    private String partnerOrderId; // 가맹점 주문번호, 결제 준비 API 요청과 일치해야 함
    private String partnerUserId; // 가맹점 회원 id, 결제 준비 API 요청과 일치해야 함
    private String pgToken; // 결제 승인 요청에 필요한 값, 결제 준비 API 응답에 포함
    private String payload; // 결제 승인 요청에 대해 저장하고 싶은 값, 최대 200자
    private Integer totalAmount; // 상품 총액, 결제 준비 API 요청과 일치해야 함

}
