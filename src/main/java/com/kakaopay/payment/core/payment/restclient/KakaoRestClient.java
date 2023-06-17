package com.kakaopay.payment.core.payment.restclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakaopay.payment.core.payment.dto.PaymentReadyDto;
import com.kakaopay.payment.core.payment.response.PaymentReadyResponse;
import com.kakaopay.payment.core.payment.enums.PaymentApiTypes;
import com.kakaopay.payment.core.payment.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoRestClient {

    public static final String ADMIN_KEY = "8a3d390b4aeeefa6411d87fd6102121e";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public PaymentResponse<PaymentReadyResponse> requestPaymentReady(PaymentReadyDto paymentReadyDto) {
        MultiValueMap<String, String> body = convertToSnakeCase(paymentReadyDto);
        return post(PaymentApiTypes.READY.getEndPoint(), body, PaymentReadyResponse.class);
    }

    public <T> PaymentResponse<T> post(String endPoint, MultiValueMap<String, String> body, Class<T> responseType) {
        PaymentResponse<T> paymentResponse = new PaymentResponse<>();

        HttpHeaders httpHeaders = getHttpHeaders();
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, httpHeaders);

        try {
            ResponseEntity<?> response = restTemplate.exchange(endPoint, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<>() {});
            if (isNotEmpty(response)) {
                paymentResponse.setData(objectMapper.convertValue(response.getBody(), responseType));
            }

        } catch (HttpStatusCodeException e) {
            log.error("[HttpStatusCodeException]: {} ", e.getMessage());
            try {
                paymentResponse.setError(objectMapper.readValue(e.getResponseBodyAsString(), PaymentResponse.Error.class));
            } catch (JsonProcessingException ex) {
                throw new RuntimeException(ex);
            }
        } catch (Exception e) {
            log.error("[Exception] ", e);
        }

        return paymentResponse;
    }

    private boolean isNotEmpty(ResponseEntity<?> response) {
        return response.getBody() != null && response.getStatusCode() == HttpStatus.OK;
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "KakaoAK " + ADMIN_KEY);
        return httpHeaders;
    }

    private MultiValueMap<String, String> convertToSnakeCase(PaymentReadyDto paymentReadyDto) {
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
                log.error("[convertToSnakeCase] IllegalAccessException", e);
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
