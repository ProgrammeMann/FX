package com.springapp.mvc.repositories.hibernate;

import com.springapp.mvc.common.hibernateEntity.CartRow;
import com.springapp.mvc.common.hibernateEntity.Customer;
import com.springapp.mvc.common.hibernateEntity.GoodInfo;
import com.springapp.mvc.repositories.CartRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration({"classpath*:spring-core.xml"})
@WebAppConfiguration
public class CartRepositoryHibernateTest {
    private static List<CartRow> rows;

    @Autowired
    private CartRepository cartRepositoryHibernate;

    @BeforeClass
    public static void setUp() throws Exception {
        rows = new ArrayList<CartRow>();
        CartRow cartRow1 = new CartRow(new GoodInfo(), 1, new Customer(1L));
        CartRow cartRow2 = new CartRow(new GoodInfo(), 1, new Customer(2L));
        CartRow cartRow3 = new CartRow(new GoodInfo(), 1, new Customer(3L));
        CartRow cartRow4 = new CartRow(new GoodInfo(), 1, new Customer(4L));
        rows.add(cartRow1);
        rows.add(cartRow2);
        rows.add(cartRow3);
        rows.add(cartRow4);
    }


    @Transactional
    @Test
    public void testAddCartRow() throws Exception {
        for (CartRow cartRow : rows
                ) {
            cartRepositoryHibernate.addCartRow(cartRow);
            assertTrue(cartRow.getId() > 0);
        }
    }

    public void testGetAll() throws Exception {

    }

    public void testGetCartsByUser() throws Exception {

    }

    public void testGetCartRowById() throws Exception {

    }

    public void testRemoveCartRowById() throws Exception {

    }


}