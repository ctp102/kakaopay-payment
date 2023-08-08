package com.kakaopay.payment.core.payment.service;

import com.kakaopay.payment.core.payment.dto.PaymentApproveRequest;
import com.kakaopay.payment.core.payment.dto.PaymentReadyRequest;
import com.kakaopay.payment.core.payment.entity.PaymentApprove;
import com.kakaopay.payment.core.payment.entity.PaymentReady;
import com.kakaopay.payment.core.payment.repository.PaymentApproveRepository;
import com.kakaopay.payment.core.payment.repository.PaymentReadyRepository;
import com.kakaopay.payment.core.payment.response.PaymentApproveResponse;
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

    private final PaymentReadyRepository paymentReadyRepository;
    private final PaymentApproveRepository paymentApproveRepository;
    private final KakaoRestClient kakaoRestClient;

    /**
     * 카카오페이 결제 준비 API 호출
     * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-info
     *
     * @param paymentReadyRequest the payment ready dto
     * @return the payment ready response
     */
    public PaymentReadyResponse requestPaymentReady(PaymentReadyRequest paymentReadyRequest) {
        PaymentResponse<PaymentReadyResponse> paymentResponse = kakaoRestClient.requestPaymentReady(paymentReadyRequest);

        PaymentReadyResponse paymentReadyResponse = paymentResponse.getData();
        if (paymentReadyResponse == null) {
            throw new RuntimeException("결제 준비 응답이 없습니다.");
        }

        PaymentReady paymentReady = PaymentReady.of(paymentReadyRequest, paymentReadyResponse);

        // 결제 준비 내역 저장
        paymentReadyRepository.save(paymentReady);

        return paymentReadyResponse;
    }


    /**
     * 카카오페이 결제 승인 API 호출
     *
     * @param paymentApproveRequest the payment approve dto
     * @return the payment approve response
     */
    public PaymentApproveResponse requestPaymentApprove(PaymentApproveRequest paymentApproveRequest) {
        PaymentResponse<PaymentApproveResponse> paymentResponse = kakaoRestClient.requestPaymentApprove(paymentApproveRequest);

        PaymentApproveResponse paymentResponseData = paymentResponse.getData();
        if (paymentResponseData == null) {
            throw new RuntimeException("결제 승인 응답이 없습니다.");
        }

        PaymentApprove paymentApprove = PaymentApprove.of(paymentApproveRequest, paymentResponseData);

        // 결제 승인 내역 저장
        paymentApproveRepository.save(paymentApprove);

        return paymentResponse.getData();
    }

}