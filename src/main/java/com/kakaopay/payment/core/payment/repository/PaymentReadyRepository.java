package com.kakaopay.payment.core.payment.repository;

import com.kakaopay.payment.core.payment.entity.PaymentReady;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentReadyRepository extends JpaRepository<PaymentReady, Long> {
}
