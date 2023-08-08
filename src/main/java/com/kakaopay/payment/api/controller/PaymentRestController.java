package com.kakaopay.payment.api.controller;

import com.kakaopay.payment.core.payment.dto.PaymentApproveRequest;
import com.kakaopay.payment.core.payment.dto.PaymentReadyRequest;
import com.kakaopay.payment.core.payment.response.PaymentApproveResponse;
import com.kakaopay.payment.core.payment.response.PaymentReadyResponse;
import com.kakaopay.payment.core.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentService paymentService;

    /**
     * 카카오페이 결제 준비
     * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare
     *
     * @param paymentReadyRequest the payment ready dto
     * @return PaymentReadyResponse
     */
    @PostMapping("/api/v1/kakaopay/payment/ready")
    public PaymentReadyResponse paymentReady(@RequestBody PaymentReadyRequest paymentReadyRequest) {
        return paymentService.requestPaymentReady(paymentReadyRequest);
    }

    /**
     * 카카오페이 결제 승인
     * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve
     * postman에서 테스트 시 결제준비 api 호출 응답 시 받은 tid, pg_token 사용하면 됨
     *
     * @param paymentApproveRequest the payment approve dto
     * @return PaymentApproveResponse
     */
    @PostMapping("/api/v1/kakaopay/payment/approve")
    public PaymentApproveResponse paymentApprove(@RequestBody PaymentApproveRequest paymentApproveRequest) {
        return paymentService.requestPaymentApprove(paymentApproveRequest);
    }

    @GetMapping("/api/v1/kakaopay/payment/ready/success")
    public String success(@RequestParam("pg_token") String pgToken) {
        return "pg_token ------>     " + pgToken;
    }

    @GetMapping("/api/v1/kakaopay/payment/ready/cancel")
    public String cancel() {
        log.info("kakaoPay cancel");
        return "cancel";
    }

    @GetMapping("/api/v1/kakaopay/payment/ready/fail")
    public String fail() {
        log.info("kakaoPay fail");
        return "fail";
    }

}
