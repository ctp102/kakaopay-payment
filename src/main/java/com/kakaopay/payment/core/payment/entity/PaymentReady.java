package com.kakaopay.payment.core.payment.entity;

import com.kakaopay.payment.core.commons.entity.BaseTimeEntity;
import com.kakaopay.payment.core.payment.dto.PaymentReadyRequest;
import com.kakaopay.payment.core.payment.response.PaymentReadyResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "PAYMENT_READY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentReady extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentReadyId;

    @Column
    private String cid; // 가맹점 코드 10자(테스트 코드는 TC0ONETIME)

    @Column
    private String cidSecret; // 가맹점 코드 인증키, 24자, 숫자와 영문 소문자 조합

    @Column
    private String tid; // 결제 고유 번호, 20자

    @Column
    private String partnerOrderId; // 가맹점 주문번호

    @Column
    private String partnerUserId; // 가맹점 회원 id

    @Column
    private String itemName; // 상품명

    @Column
    private String itemCode; // 상품코드

    @Column
    private Integer quantity; // 상품 수량

    @Column
    private Integer totalAmount; // 상품 총액

    @Column
    private Integer vatAmount; // 상품 부가세

    @Column
    private Integer taxFreeAmount; // 상품 비과세 금액

    @Column
    private Integer greenDeposit; // 컵 보증금

    @Column
    private LocalDateTime readyRequestedDate; // 결제 준비 요청 시각

    @Builder
    public PaymentReady(Long paymentReadyId, String tid, String cid, String cidSecret, String partnerOrderId, String partnerUserId, String itemName, String itemCode, Integer quantity, Integer totalAmount, Integer vatAmount, Integer taxFreeAmount, Integer greenDeposit, LocalDateTime readyRequestedDate) {
        this.paymentReadyId = paymentReadyId;
        this.cid = cid;
        this.cidSecret = cidSecret;
        this.tid = tid;
        this.partnerOrderId = partnerOrderId;
        this.partnerUserId = partnerUserId;
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.vatAmount = vatAmount;
        this.taxFreeAmount = taxFreeAmount;
        this.greenDeposit = greenDeposit;
        this.readyRequestedDate = readyRequestedDate;
    }

    public static PaymentReady of(PaymentReadyRequest request, PaymentReadyResponse response) {
        return PaymentReady.builder()
                .cid(request.getCid())
                .cidSecret(request.getCidSecret())
                .tid(response.getTid())
                .partnerOrderId(request.getPartnerOrderId())
                .partnerUserId(request.getPartnerUserId())
                .itemName(request.getItemName())
                .itemCode(request.getItemCode())
                .quantity(request.getQuantity())
                .totalAmount(request.getTotalAmount())
                .vatAmount(request.getVatAmount())
                .taxFreeAmount(request.getTaxFreeAmount())
                .greenDeposit(request.getGreenDeposit())
                .readyRequestedDate(response.getCreatedAt())
                .build();
    }

}
