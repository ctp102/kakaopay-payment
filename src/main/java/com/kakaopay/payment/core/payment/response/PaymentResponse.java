package com.kakaopay.payment.core.payment.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
public class PaymentResponse<T> {

    private T data;
    private Error error;

    @Data
    public static class Error {
        private int code;
        private String msg;
        private Extras extras;
    }

    @Data
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class Extras {
        private String methodResultCode;
        private String methodResultMessage;
    }

}
