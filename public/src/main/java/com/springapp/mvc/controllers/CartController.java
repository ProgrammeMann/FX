package com.springapp.mvc.controllers;

import com.springapp.mvc.aspects.annotation.IncludeMenuInfo;
import com.springapp.mvc.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for the cart
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CartService cartService;

    /**
     * Отображение содержимого корзины
     */
    @IncludeMenuInfo
    @RequestMapping
    public String renderCart() {
        return "cart/cartPage";
    }

    /**
     * Добавление товара в корзину
     *
     * @param goodId id товара
     */

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addInCart(Long goodId, Long customerId) {
        cartService.addInCart(goodId, 1, customerId);
        return "redirect:/cart";
    }

    /**
     * Updating of count in the cart row
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateCartRow(@RequestParam(value = "cart_row_id", required = true) Long cartRowId, @RequestParam(value = "count", required = true) Integer count) {
        boolean updated = cartService.updateCartRow(cartRowId, count);
        if (!updated) {
            request.setAttribute("error", true);
        }
        return "redirect:/cart";
    }

    /**
     * Removing the item from the cart
     */
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    public String removeFromCart(@RequestParam(value = "cart_row_id", required = true) Long cartRowId) {
        cartService.removeFromCart(cartRowId);
        return "redirect:/cart";
    }
}
