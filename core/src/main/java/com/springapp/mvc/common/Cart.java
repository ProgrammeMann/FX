package com.springapp.mvc.common;


import com.springapp.mvc.common.hibernateEntity.CartRow;

import java.math.BigDecimal;
import java.util.List;

public class Cart {

    private List<CartRow> cartRows;
    private BigDecimal totalPrice = BigDecimal.ZERO;
    private Integer totalCount = 0;

    public Cart(List<CartRow> cartRows, BigDecimal totalPrice, Integer totalCount) {
        this.cartRows = cartRows;
        this.totalPrice = totalPrice;
        this.totalCount = totalCount;
    }

    public List<CartRow> getCartRows() {
        return cartRows;
    }

    public void setCartRows(List<CartRow> cartRows) {
        this.cartRows = cartRows;
    }

    public boolean containsGoodId(Long goodId) {
        if (cartRows == null || goodId == null)
            return false;
        for (CartRow cr : cartRows) {
            if (cr.getGood().getId().equals(goodId)) return true;
        }
        return false;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
