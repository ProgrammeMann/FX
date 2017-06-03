package com.springapp.mvc.security;

import com.springapp.mvc.common.hibernateEntity.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static org.junit.Assert.*;

/**
 * Created by Лия on 02.06.2016.
 */
public class MyUserDetailTest {

    private MyUserDetail myUserDetail;

    private Customer customer;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("name", "test@example.com", "890000000", "4dfa5426", "ROLE_USER", true);
        myUserDetail = new MyUserDetail(customer);
    }

    @Test
    public void getAuthorities() throws Exception {
        assertEquals(1, myUserDetail.getAuthorities().size());
        assertTrue(myUserDetail.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Test
    public void getPassword() throws Exception {
        assertEquals(customer.getPassword(), myUserDetail.getPassword());
    }

    @Test
    public void getUsername() throws Exception {
        assertEquals(customer.getEmail(), myUserDetail.getUsername());
    }

    @Test
    public void isAccountNonExpired() throws Exception {
        assertTrue(myUserDetail.isAccountNonExpired());
    }

    @Test
    public void isAccountNonLocked() throws Exception {
        assertTrue(myUserDetail.isAccountNonLocked());
    }

    @Test
    public void isCredentialsNonExpired() throws Exception {
        assertTrue(myUserDetail.isCredentialsNonExpired());
    }

    @Test
    public void isEnabled() throws Exception {
        customer.setEnabled(false);
        assertFalse(myUserDetail.isEnabled());
        customer.setEnabled(true);
        assertTrue(myUserDetail.isEnabled());
    }

    @Test
    public void getUser() throws Exception {
        assertEquals(customer, myUserDetail.getCustomer());
    }

}