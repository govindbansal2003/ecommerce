package com.ecommerce.Entity;

import com.ecommerce.user.domain.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDate  orderDate = LocalDate.now();
    private LocalDate deliveryDate;

    @OneToOne
    private Address shippingAddress;

    @Embedded
    private PaymentDetails  paymentDetails = new PaymentDetails();

    private double totalPrice;
    private  Integer discount;

    private Integer totalDiscountedPrice;

    private OrderStatus orderStatus;
    private int totalItem;
    private LocalDateTime createdAt;


}
