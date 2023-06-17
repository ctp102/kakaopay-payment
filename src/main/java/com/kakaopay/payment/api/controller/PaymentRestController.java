package com.kakaopay.payment.api.controller;

import com.kakaopay.payment.core.payment.dto.PaymentReadyDto;
import com.kakaopay.payment.core.payment.response.PaymentReadyResponse;
import com.kakaopay.payment.core.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentService paymentService;

    @PostMapping("/api/v1/payment/ready")
    public PaymentReadyResponse paymentReady(@RequestBody PaymentReadyDto paymentReadyDto) {
        log.info("paymentReady: {}", paymentReadyDto);

        PaymentReadyResponse paymentReadyResponse = paymentService.requestPaymentReady(paymentReadyDto);

        return paymentReadyResponse;
    }

    @GetMapping("/api/kakaopay/payment/success")
    public String success() {
        log.info("kakaoPay");
        return "success";
    }

    @GetMapping("/api/kakaopay/payment/cancel")
    public String cancel() {
        log.info("kakaoPay");
        return "cancel";
    }

    @GetMapping("/api/kakaopay/payment/fail")
    public String fail() {
        log.info("kakaoPay");
        return "fail";
    }

}
