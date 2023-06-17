package com.kakaopay.payment.core.payment.enums;

import lombok.Getter;

@Getter
public enum PaymentApiTypes {

    READY("https://kapi.kakao.com/v1/payment/ready", "결제 준비"),
    APPROVE("https://kapi.kakao.com/v1/payment/approve", "결제 승인"),
    CANCEL("https://kapi.kakao.com/v1/payment/cancel", "결제 취소"),
    INFO("https://kapi.kakao.com/v1/payment/order", "결제 조회")
    ;

    PaymentApiTypes(String url, String description) {
        this.url = url;
        this.description = description;
    }

    private String url;
    private String description;

}
