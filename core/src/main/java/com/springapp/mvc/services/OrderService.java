package com.springapp.mvc.services;

import com.springapp.mvc.common.hibernateEntity.Customer;
import com.springapp.mvc.common.hibernateEntity.Order;
import com.springapp.mvc.common.hibernateEntity.OrderItem;
import com.springapp.mvc.common.hibernateEntity.OrderStates;
import com.springapp.mvc.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервис для запросов к заказам
 */

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepositoryHibernate;


    public void updateOrder(Order order) {
        orderRepositoryHibernate.updateOrder(order);
    }

    public void addOrder(Order order) {
        orderRepositoryHibernate.addOrder(order);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderRepositoryHibernate.addOrderItem(orderItem);
    }

    public Order getOrderById(Long id) {
        return orderRepositoryHibernate.getOrderById(id);
    }

    public List<Order> getAllOrders() {
        return orderRepositoryHibernate.getAllOrders();
    }


    public void deleteOrder(Order order) {
        orderRepositoryHibernate.deleteOrder(order);
    }

    public List<Order> getOrderByCustomer(Customer customer) {
        return orderRepositoryHibernate.getOrderByCustomer(customer);
    }

    public void changeOrderState(Long id, String orderState) {
        Order order = orderRepositoryHibernate.getOrderById(id);
        order.setOrderStates(orderState);
        orderRepositoryHibernate.updateOrder(order);

    }
}
