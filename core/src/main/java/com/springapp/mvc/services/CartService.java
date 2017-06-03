package com.springapp.mvc.services;

import com.springapp.mvc.common.Cart;
import com.springapp.mvc.common.hibernateEntity.CartRow;
import com.springapp.mvc.common.hibernateEntity.GoodInStore;
import com.springapp.mvc.common.hibernateEntity.GoodInfo;
import com.springapp.mvc.repositories.CartRepository;
import com.springapp.mvc.repositories.CustomerRepository;
import com.springapp.mvc.repositories.GoodRepository;
import com.springapp.mvc.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service for worhing with the goods in the cart
 */
@Service
public class CartService {

    @Qualifier("cartRepositoryHibernate")
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * The adding of Good in a cart
     *
     * @param goodId     of good to be bought
     * @param count      how many goods to be bought
     * @param customerId current user, who is doing the buyings
     */
    public void addInCart(Long goodId, Integer count, Long customerId) {
        CartRow cartRow = new CartRow(goodRepository.getGoodById(goodId), count, customerRepository.getCustomerById(customerId));
        cartRepository.addCartRow(cartRow);
    }

    /**
     * Return and update cart information
     *
     * @param customerLogin current user's Login(email)
     * @return Cart
     */
    public Cart getCart(String customerLogin) {
        List<CartRow> rows = cartRepository.getCartsByUser(customerRepository.getCustomerByLogin(customerLogin));
        BigDecimal totalPrice = getTotalPrice(rows);
        Integer totalCount = getTotalCount(rows);
        return new Cart(rows, totalPrice, totalCount);
    }

    /**
     * Counts total count of items in the cart
     *
     * @param cartRows items in the cart
     * @return number of items
     */
    public Integer getTotalCount(List<CartRow> cartRows) {
        Integer count = 0;
        for (CartRow cartRow : cartRows) {
            count += cartRow.getCount();
        }
        return count;
    }

    /**
     * Counts total price of items in the cart
     *
     * @param cartRows items in the cart
     * @return total price of items
     */
    public BigDecimal getTotalPrice(List<CartRow> cartRows) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (CartRow cartRow : cartRows) {
            totalPrice = totalPrice.add(cartRow.getGood().getPrice().multiply(new BigDecimal(cartRow.getCount())));
        }
        return totalPrice;
    }

    /**
     * To delete the cart row from the cart
     *
     * @param cartRowId of cart row to be deleted
     */
    public void removeFromCart(Long cartRowId) {
        cartRepository.removeCartRowById(cartRowId);
    }

    /**
     * To update the column Count in exact cart row
     *
     * @param cartRowId exact cart row id
     * @param count     to increase or decrease count column (1/-1)
     */
    public boolean updateCartRow(Long cartRowId, Integer count) {
        CartRow cartRow = cartRepository.getCartRowById(cartRowId);
        GoodInfo goodInfo = cartRow.getGood();
        GoodInStore goodInStore = storeRepository.getGoodInStoreByGood(goodInfo);
        if (count.equals(-1)) {
            cartRepository.updateCartRow(cartRowId, count);
            return true;
        } else {
            if (goodInStore.getCount() > cartRow.getCount()) {
                cartRepository.updateCartRow(cartRowId, count);
                return true;
            } else return false;
        }

    }

    public void clear(String customerLogin) {
        List<CartRow> rows = cartRepository.getCartsByUser(customerRepository.getCustomerByLogin(customerLogin));
        rows.forEach(CartRow -> removeFromCart(CartRow.getId()));
    }
}
