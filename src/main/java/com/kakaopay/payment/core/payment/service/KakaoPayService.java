package com.kakaopay.payment.core.payment.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoPayService {

    public static final String ADMIN_KEY = "8a3d390b4aeeefa6411d87fd6102121e";

    private final RestTemplate restTemplate;

    public void requestPaymentReady() {
        URI uri = UriComponentsBuilder
                .fromUriString("https://kapi.kakao.com")
                .path("/v1/payment/ready")
                .encode()
                .build()
                .toUri();

        HttpHeaders httpHeaders = getHttpHeaders();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("cid", "TC0ONETIME");
        body.add("partner_order_id", "151665416351");
        body.add("partner_user_id", "10");
        body.add("item_name", "아메리카노");
        body.add("quantity", "1");
        body.add("total_amount", "5000");
        body.add("vat_amount", "200");
        body.add("tax_free_amount", "0");
        body.add("approval_url", "http://localhost:8080/api/kakaopay/payment/success");
        body.add("fail_url", "http://localhost:8080/api/kakaopay/payment/fail");
        body.add("cancel_url", "http://localhost:8080/api/kakaopay/payment/cancel");

        RequestEntity<MultiValueMap<String, String>> requestEntity = new RequestEntity<>(body, httpHeaders, HttpMethod.POST, uri);

        ResponseEntity<Map> responseEntity = restTemplate.exchange(requestEntity, Map.class);
        log.info("responseEntity: {}", responseEntity);
    }

    public void requestPaymentReady2() {
        HttpHeaders httpHeaders = getHttpHeaders();

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("cid", "TC0ONETIME");
        body.add("partner_order_id", "151665416351");
        body.add("partner_user_id", "10");
        body.add("item_name", "아메리카노");
        body.add("quantity", "1");
        body.add("total_amount", "5000");
        body.add("vat_amount", "200");
        body.add("tax_free_amount", "0");
        body.add("approval_url", "http://localhost:8080/api/kakaopay/payment/success");
        body.add("fail_url", "http://localhost:8080/api/kakaopay/payment/fail");
        body.add("cancel_url", "http://localhost:8080/api/kakaopay/payment/cancel");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, httpHeaders);

        String url = "https://kapi.kakao.com/v1/payment/ready";

//        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);

        Map map = restTemplate.postForObject(url, requestEntity, Map.class);

        log.info("map: {}", map);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "KakaoAK " + ADMIN_KEY);
        return httpHeaders;
    }

}
