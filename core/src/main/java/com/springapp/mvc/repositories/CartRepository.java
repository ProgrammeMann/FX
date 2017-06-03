package com.springapp.mvc.repositories;


import com.springapp.mvc.common.hibernateEntity.CartRow;
import com.springapp.mvc.common.hibernateEntity.Customer;

import java.util.List;

public interface CartRepository {
    List<CartRow> getAll();

    void addCartRow(CartRow cartRow);

    List<CartRow> getCartsByUser(Customer customer);

    void updateCartRow(Long cartRowId, Integer count);

    void removeCartRowById(Long cartRowId);

    CartRow getCartRowById(Long cartRowId);
}
