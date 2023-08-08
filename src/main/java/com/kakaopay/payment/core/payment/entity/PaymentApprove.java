package com.kakaopay.payment.core.payment.entity;

import com.kakaopay.payment.core.commons.entity.BaseTimeEntity;
import com.kakaopay.payment.core.payment.dto.PaymentApproveRequest;
import com.kakaopay.payment.core.payment.response.PaymentApproveResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "PAYMENT_APPROVE")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentApprove extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentApproveId;

    @Column
    private String cid; // 가맹점 코드 10자(테스트 코드는 TC0ONETIME)

    @Column
    private String cidSecret; // 가맹점 코드 인증키, 24자, 숫자와 영문 소문자 조합

    @Column
    private String tid; // 결제 고유 번호, 결제 준비 API 응답에 포함

    @Column
    private String partnerOrderId; // 가맹점 주문번호, 결제 준비 API 요청과 일치해야 함

    @Column
    private String partnerUserId; // 가맹점 회원 id, 결제 준비 API 요청과 일치해야 함

    @Column
    private String payload; // 결제 승인 요청에 대해 저장하고 싶은 값, 최대 200자

    @Column
    private Integer totalAmount; // 상품 총액, 결제 준비 API 요청과 일치해야 함

    @Builder
    public PaymentApprove(Long paymentApproveId, String cid, String cidSecret, String tid, String partnerOrderId, String partnerUserId, String payload, Integer totalAmount) {
        this.paymentApproveId = paymentApproveId;
        this.cid = cid;
        this.cidSecret = cidSecret;
        this.tid = tid;
        this.partnerOrderId = partnerOrderId;
        this.partnerUserId = partnerUserId;
        this.payload = payload;
        this.totalAmount = totalAmount;
    }

    public static PaymentApprove of(PaymentApproveRequest request, PaymentApproveResponse response) {
        return PaymentApprove.builder()
                .cid(request.getCid())
                .cidSecret(request.getCidSecret())
                .tid(response.getTid())
                .partnerOrderId(request.getPartnerOrderId())
                .partnerUserId(request.getPartnerUserId())
                .payload(request.getPayload())
                .totalAmount(request.getTotalAmount())
                .build();
    }
}
