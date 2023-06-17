package com.kakaopay.payment.api.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class KaokaoPayRestController {

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
