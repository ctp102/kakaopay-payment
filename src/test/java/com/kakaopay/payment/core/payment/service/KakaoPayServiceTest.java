package com.kakaopay.payment.core.payment.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KakaoPayServiceTest {

    @Autowired
    private KakaoPayService kakaoPayService;

    @Test
    void requestPaymentReady() {
        kakaoPayService.requestPaymentReady2();
    }

}