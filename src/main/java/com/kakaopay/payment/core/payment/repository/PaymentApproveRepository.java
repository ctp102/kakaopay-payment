package com.kakaopay.payment.core.payment.repository;

import com.kakaopay.payment.core.payment.entity.PaymentApprove;
import com.kakaopay.payment.core.payment.entity.PaymentReady;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentApproveRepository extends JpaRepository<PaymentApprove, Long> {
}
