package com.kakaopay.payment.core.payment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.payment.core.payment.dto.PaymentReadyDto;
import com.kakaopay.payment.core.payment.dto.PaymentReadyResponse;
import com.kakaopay.payment.core.payment.enums.PaymentApiTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoRestClient {

    public static final String ADMIN_KEY = "8a3d390b4aeeefa6411d87fd6102121e";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public void requestPaymentReady(PaymentReadyDto paymentReadyDto) {
        HttpHeaders httpHeaders = getHttpHeaders();

        MultiValueMap<String, String> body = convertToSnakeCase(paymentReadyDto);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, httpHeaders);

//        PaymentReadyResponse paymentReadyResponse = restTemplate.postForObject(PaymentApiTypes.READY.getUrl(), requestEntity, PaymentReadyResponse.class);

        PaymentReadyResponse paymentReadyResponse = null;

        try {
            ResponseEntity<Map> response = restTemplate.exchange(PaymentApiTypes.READY.getUrl(), HttpMethod.POST, requestEntity, Map.class);
            if (response.getBody() != null && response.getStatusCode() == HttpStatus.OK) {
                paymentReadyResponse = objectMapper.convertValue(response.getBody(), PaymentReadyResponse.class);
            }

        } catch (HttpStatusCodeException e) {
            log.error("[HttpStatusCodeException]: {} ", e.getMessage());
        } catch (Exception e) {
            log.error("[Exception] ", e);
        }

//        Map map = restTemplate.postForObject(PaymentApiTypes.READY.getUrl(), requestEntity, Map.class);

        log.info("paymentReadyResponse: {}", paymentReadyResponse);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "KakaoAK " + ADMIN_KEY);
        return httpHeaders;
    }

    public MultiValueMap<String, String> convertToSnakeCase(PaymentReadyDto paymentReadyDto) {
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();

        Field[] fields = paymentReadyDto.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            String snakeCaseFieldName = convertCamelToSnakeCase(fieldName);

            try {
                Object fieldValue = field.get(paymentReadyDto);
                if (fieldValue != null) {
                    multiValueMap.add(snakeCaseFieldName, fieldValue.toString());
                }
            } catch (IllegalAccessException e) {
                // Handle exception if needed
            }
        }

        return multiValueMap;
    }

    private String convertCamelToSnakeCase(String camelCase) {
        StringBuilder snakeCase = new StringBuilder();
        for (char c : camelCase.toCharArray()) {
            if (Character.isUpperCase(c)) {
                snakeCase.append('_').append(Character.toLowerCase(c));
            } else {
                snakeCase.append(c);
            }
        }
        return snakeCase.toString();
    }

}
