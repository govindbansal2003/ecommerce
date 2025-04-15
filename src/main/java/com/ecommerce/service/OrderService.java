package com.ecommerce.service;

import com.ecommerce.Entity.Address;
import com.ecommerce.Entity.Order;
import com.ecommerce.Entity.User;
import com.ecommerce.exception.OrderException;

import java.util.List;

public interface OrderService {
    Order findOrderById(Long orderId)throws OrderException;
    Order createOrder(User user, Address shippingAddress)throws OrderException;
    List<Order> userOrderHistory(Long userId);


    Order placedOrder(Long orderId) throws OrderException;

    Order confirmedOrder(Long orderId) throws OrderException;
    Order shippedOrder(Long orderId) throws OrderException;
    Order deliveredOrder(Long orderId) throws OrderException;
    Order cancelOrder(Long orderId) throws OrderException;
    List<Order> getAllOrders();
    void deleteOrder(Long orderId) throws OrderException;
}
