package com.springapp.mvc.services;

import com.springapp.mvc.common.hibernateEntity.Customer;
import com.springapp.mvc.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for working with the customers information
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepositoryHibernate;

    public Customer getCustomerByLogin(String login) {
        return customerRepositoryHibernate.getCustomerByLogin(login);
    }

    public Customer getById(Long customerId) {
        return customerRepositoryHibernate.getCustomerById(customerId);
    }

    public List<Customer> getAll() {
        return customerRepositoryHibernate.getAll();
    }

    @Transactional
    public void add(Customer customer) {
        customerRepositoryHibernate.add(customer);
    }

    @Transactional
    public void updateEnabled(Customer customer) {
        customerRepositoryHibernate.updateEnabled(customer);
    }

    @Transactional
    public void updateEnabled(Long customerId) {
        customerRepositoryHibernate.updateEnabled(customerId);
    }

    @Transactional
    public void delete(Long customerId) {
        customerRepositoryHibernate.delete(customerId);
    }


}
