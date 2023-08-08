package com.kakaopay.payment.core.payment.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment#approve-request-body
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PaymentApproveResponse {

    private String aid; // 요청 고유 번호, 20자
    private String tid; // 결제 고유 번호, 20자
    private String cid; // 가맹점 코드, 10자
    private String sid; // 정기 결제용 ID, 정기 결제 CID로 단건 결제 요청 시 발급
    private String partnerOrderId; // 가맹점 주문번호, 최대 100자
    private String partnerUserId; // 가맹점 회원 id, 최대 100자
    private String paymentMethodType; // 결제 수단, CARD 또는 MONEY 중 하나
    private Amount amount; // 결제 금액 정보
    private CardInfo cardInfo; // 결제 상세 정보(결제수단이 카드일 경우만 포함)
    private String itemCode; // 상품 코드, 최대 100자
    private String itemName; // 상품명, 최대 100자
    private Integer quantity; // 상품 수량
    private String payload; // 결제 승인 요청에 대해 저장한 값, 요청 시 전달된 내용

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt; // 결제 준비 요청 시각

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime approvedAt; // 결제 승인 시각

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static class Amount {
        private Integer total; // 전체 결제 금액
        private Integer taxFree; // 비과세 금액
        private Integer vat; // 부가세 금액
        private Integer point; // 사용한 포인트 금액
        private Integer discount; // 할인 금액
        private Integer green_deposit; // 컵 보증금
    }

    @Getter
    @Setter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private static class CardInfo {
        private String purchaseCorp; // 매입 카드사 한글명
        private String purchaseCorpCode; // 매입 카드사 코드
        private String issuerCorp; // 카드 발급사 한글명
        private String issuerCorpCode; // 카드 발급사 코드
        private String kakaopayPurchaseCorp; // 카카오페이 매입사명
        private String kakaopayPurchaseCorpCode; // 카카오페이 매입사 코드
        private String kakaopayIssuerCorp; // 카카오페이 발급사명
        private String kakaopayIssuerCorpCode; // 카카오페이 발급사 코드
        private String bin; // 카드 BIN
        private String cardType; // 카드 타입
        private String installMonth; // 할부 개월 수
        private String approvedId; // 카드사 승인번호
        private String cardMid; // 카드사 가맹점 번호
        private String interestFreeInstall; // 무이자 할부 여부(Y/N)
        private String cardItemCode; // 카드 상품 코드
    }
}
