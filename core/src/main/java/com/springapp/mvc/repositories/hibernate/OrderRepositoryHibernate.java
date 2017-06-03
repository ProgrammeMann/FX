package com.springapp.mvc.repositories.hibernate;

import com.springapp.mvc.common.hibernateEntity.Customer;
import com.springapp.mvc.common.hibernateEntity.Order;
import com.springapp.mvc.common.hibernateEntity.OrderItem;
import com.springapp.mvc.repositories.OrderRepository;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepositoryHibernate implements OrderRepository {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @return current Session from Session factory
     */
    private Session curSession() {
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    @Override
    public void addOrder(Order order) {
        curSession().save(order);
    }

    @Transactional
    @Override
    public void addOrderItem(OrderItem orderItem) {
        curSession().save(orderItem);
    }

    @Override
    public void updateOrder(Order order) {
        Session session = null;
        try {
            session = curSession();
            session.beginTransaction();
            session.update(order);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Error in updateOrder(): " + e.getMessage());
            if (session != null && session.getTransaction() != null)
                session.getTransaction().rollback();
        }
    }

    @Override
    public Order getOrderById(Long id) {
        Session session = null;
        Order order = null;
        try {
            session = curSession();
            Criteria crit = session.createCriteria(Order.class); // создаем критерий запроса
            crit.add(Restrictions.idEq(id));                     // добавляем условие на id
            order = (Order) crit.uniqueResult();                 // получаем единственный результат
        } catch (Exception e) {
            System.err.println("Error in getOrderById(): " + e.getMessage());
        }
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        Session session = null;
        List<Order> orders = new ArrayList<Order>();
        try {
            session = curSession();
            Criteria crit = session.createCriteria(Order.class);
            crit.setFirstResult(0).setMaxResults(50);
            orders = crit.list();
        } catch (Exception e) {
            System.err.println("Error in getAllOrders(): " + e.getMessage());
        }
        return orders;
    }

    @Override
    public List<Order> getOrderByCustomer(Customer customer) {
        List<Order> orders = null;
        try {
            Criteria crit = curSession().createCriteria(Order.class); // создаем критерий запроса
            crit.add(Restrictions.eq("customer", customer));                     // добавляем условие на id
            orders = (List<Order>) crit.list();                 // получаем единственный результат
        } catch (Exception e) {
            System.err.println("Error in getOrderByCustomer(): " + e.getMessage());
        }
        return orders;
    }

    @Override
    public List<OrderItem> getAllOrderItemsByOrder(Order order) {
        List<OrderItem> orderItems = new ArrayList<>();
        try {
            Criteria crit = curSession().createCriteria(OrderItem.class); // создаем критерий запроса
            crit.add(Restrictions.eq("order", order));                     // добавляем условие на id
            orderItems = crit.list();                 // получаем единственный результат
        } catch (Exception e) {
            System.err.println("Error in getOrderByCustomer(): " + e.getMessage());
        }
        return orderItems;
    }

    @Override
    public void deleteOrder(Order order) {

    }
}
