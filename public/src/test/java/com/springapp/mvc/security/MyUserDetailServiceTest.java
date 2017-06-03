package com.springapp.mvc.security;

import com.springapp.mvc.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Лия on 02.06.2016.
 */
public class MyUserDetailServiceTest {

    private MyUserDetailService myUserDetailService;
    private CustomerService mockUserService;

    @Before
    public void setUp() throws Exception {
        mockUserService = mock(CustomerService.class);
        myUserDetailService = new MyUserDetailService();
        myUserDetailService.customerService = mockUserService;
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testException() throws Exception {
        when(mockUserService.getCustomerByLogin("userblabla")).thenReturn(null);
        myUserDetailService.loadUserByUsername("userblabla");
        fail();
    }

}