package com.ecommerce.Entity;
import jakarta.persistence.Embeddable;
import lombok.*;

import com.ecommerce.user.domain.PaymentMethod;
import com.ecommerce.user.domain.PaymentStatus;

@Data
public class PaymentDetails {

    private String paymentMethod;
    private PaymentStatus status;
    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentReferenceName;
    public String razorpayPaymentLinkStatus;
    private String razorpayPaymentId;

}
