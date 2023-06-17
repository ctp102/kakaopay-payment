package com.kakaopay.payment.core.payment.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

/**
 * https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#prepare-response-body
 */
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentReadyResponse {

    private String tid; // 결제 고유 번호, 20자
    private boolean tmsResult; // ?
    private String nextRedirectAppUrl; // 카카오톡 결제 화면으로 이동하는 Android 앱 스킴
    private String nextRedirectMobileUrl; // 카카오톡 결제 화면으로 이동하는 iOS 앱 스킴
    private String nextRedirectPcUrl; // 카카오톡 결제 화면으로 이동하는 웹 스킴
    private String androidAppScheme; // 카카오톡 결제 화면으로 이동하는 Android 앱 스킴
    private String iosAppScheme; // 카카오톡 결제 화면으로 이동하는 iOS 앱 스킴
    private String createdAt; // 결제 준비 요청 시간

}
