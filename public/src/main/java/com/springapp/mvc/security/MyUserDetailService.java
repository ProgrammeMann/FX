package com.springapp.mvc.security;

import com.springapp.mvc.common.hibernateEntity.Customer;
import com.springapp.mvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    CustomerService customerService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Customer customer = customerService.getCustomerByLogin(login);
        if (customer == null) throw new UsernameNotFoundException("User with name " + login + " not found");
        return new MyUserDetail(customer);
    }

}
