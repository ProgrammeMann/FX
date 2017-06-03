package com.springapp.mvc.repositories.hibernate;


import com.springapp.mvc.common.hibernateEntity.Customer;
import com.springapp.mvc.repositories.CustomerRepository;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryHibernate implements CustomerRepository {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * @return current Session from Session factory
     */
    private Session curSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * To get the customer by its id
     *
     * @param customerId of customer to be got
     * @return Customer
     */
    @Override
    public Customer getCustomerById(Long customerId) {
        return (Customer) curSession().get(Customer.class, customerId);
    }

    /**
     * To get the customer by its login(email)
     *
     * @param email of customer to be got
     * @return Customer
     */
    @Override
    public Customer getCustomerByLogin(String email) {
        Criteria crit = curSession().createCriteria(Customer.class);
        crit.add(Restrictions.eq("email", email));
        return (Customer) crit.uniqueResult();
    }

    /**
     * To add the customer into table customer
     *
     * @param customer
     */
    @Override
    public void add(Customer customer) {
        curSession().save(customer);
    }

    /**
     * To update customers action possibilities (setEnabled)
     *
     * @param customer to be updated
     */
    @Override
    public void updateEnabled(Customer customer) {
        try {
            Query query = curSession().createQuery("update Customer c set enabled = true where id = :ID");
            query.setParameter("ID", customer.getId());
            query.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error in updateEnabled(): " + e.getMessage());
        }
    }

    @Override
    public void updateEnabled(Long customerId) {
        Customer customer = getCustomerById(customerId);
        Boolean antiEnabled = !customer.getEnabled();
        try {
            Query query = curSession().createQuery("update Customer c set enabled = :ENABLED where id = :ID");

            query.setParameter("ENABLED", antiEnabled);
            query.setParameter("ID", customerId);
            query.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error in updateEnabled(): " + e.getMessage());
        }
    }

    @Override
    public void delete(Long customerId) {
        try {
            Query query = curSession().createQuery("delete Customer where id = :ID");
            query.setParameter("ID", customerId);
            query.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error in delete Customer(): " + e.getMessage());
        }
    }

    @Override
    public List<Customer> getAll() {
        return curSession().createCriteria(Customer.class).list();
    }
}
