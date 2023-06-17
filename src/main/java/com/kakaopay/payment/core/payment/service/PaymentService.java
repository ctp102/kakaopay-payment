package com.kakaopay.payment.core.payment.service;

import com.kakaopay.payment.core.payment.dto.PaymentReadyDto;
import com.kakaopay.payment.core.payment.response.PaymentReadyResponse;
import com.kakaopay.payment.core.payment.response.PaymentResponse;
import com.kakaopay.payment.core.payment.restclient.KakaoRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentService {

    //    private final PaymentRepository paymentRepository;
    private final KakaoRestClient kakaoRestClient;

    /**
     * 카카오페이 결제 준비 API 호출
     * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-info
     *
     * @param paymentReadyDto
     */
    public PaymentReadyResponse requestPaymentReady(PaymentReadyDto paymentReadyDto) {
        PaymentResponse<PaymentReadyResponse> paymentResponse = kakaoRestClient.requestPaymentReady(paymentReadyDto);
        return paymentResponse.getData();
    }

}