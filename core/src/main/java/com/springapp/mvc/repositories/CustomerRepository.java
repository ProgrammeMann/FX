package com.springapp.mvc.repositories;


import com.springapp.mvc.common.hibernateEntity.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer getCustomerById(Long customerId);

    Customer getCustomerByLogin(String login);

    void add(Customer customer);

    void updateEnabled(Customer customer);

    void updateEnabled(Long customerId);

    void delete(Long customerId);

    List<Customer> getAll();
}
