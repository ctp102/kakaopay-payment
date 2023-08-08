package com.kakaopay.payment.core.payment.enums;

import lombok.Getter;

@Getter
public enum PaymentReadyRedirectUrlTypes {

    SUCCESS("/api/v1/kakaopay/payment/ready/success", "결제 준비 성공"),
    FAIL("/api/v1/kakaopay/payment/ready/fail", "결제 준비 실패"),
    CANCEL("/api/v1/kakaopay/payment/ready/cancel", "결제 준비 취소")
    ;

    private final String redirectUrl;
    private final String description;

    PaymentReadyRedirectUrlTypes(String redirectUrl, String description) {
        this.redirectUrl = redirectUrl;
        this.description = description;
    }

}
