package com.springapp.mvc.services;

import com.springapp.mvc.common.hibernateEntity.CartRow;
import com.springapp.mvc.common.hibernateEntity.Customer;
import com.springapp.mvc.common.hibernateEntity.GoodInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration({"classpath*:spring-core.xml"})
public class CartServiceTest {

    private static List<CartRow> rows;
    @Autowired
    private CartService cartService;

    @Before
    public void setUp() throws Exception {
        rows = new ArrayList<CartRow>();
        CartRow cartRow1 = new CartRow(new GoodInfo(), 1, new Customer(1L));
        CartRow cartRow2 = new CartRow(new GoodInfo(), 1, new Customer(1L));
        CartRow cartRow3 = new CartRow(new GoodInfo(), 1, new Customer(1L));
        CartRow cartRow4 = new CartRow(new GoodInfo(), 1, new Customer(1L));
        rows.add(cartRow1);
        rows.add(cartRow2);
        rows.add(cartRow3);
        rows.add(cartRow4);
    }

    @Transactional
    @Test
    public void testAddInCart() throws Exception {
        cartService.addInCart(1L, 1, 1L);
    }

    @Transactional
    @Test
    public void testGetCart() throws Exception {
        assertNotNull(cartService.getCart("Liya"));
    }

}