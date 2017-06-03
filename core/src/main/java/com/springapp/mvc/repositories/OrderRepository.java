package com.springapp.mvc.repositories;

import com.springapp.mvc.common.hibernateEntity.Customer;
import com.springapp.mvc.common.hibernateEntity.Order;
import com.springapp.mvc.common.hibernateEntity.OrderItem;

import java.util.List;

public interface OrderRepository {
    void addOrder(Order order);

    void updateOrder(Order order);

    Order getOrderById(Long id);

    List<Order> getAllOrders();

    void deleteOrder(Order order);

    void addOrderItem(OrderItem orderItem);

    List<OrderItem> getAllOrderItemsByOrder(Order order);

    List<Order> getOrderByCustomer(Customer customer);
}
