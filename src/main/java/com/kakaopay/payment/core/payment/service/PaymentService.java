package com.kakaopay.payment.core.payment.service;

import com.kakaopay.payment.core.payment.dto.PaymentReadyDto;
import com.kakaopay.payment.core.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

//    private final PaymentRepository paymentRepository;
    private final KakaoRestClient kakaoRestClient;

    public void requestPaymentReady(PaymentReadyDto paymentReadyDto) {
        kakaoRestClient.requestPaymentReady(paymentReadyDto);
    }

}
