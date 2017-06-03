package com.springapp.mvc.controllers;

import com.springapp.mvc.aspects.annotation.IncludeMenuInfo;
import com.springapp.mvc.common.Cart;
import com.springapp.mvc.common.hibernateEntity.*;
import com.springapp.mvc.form.CheckoutForm;
import com.springapp.mvc.security.MyUserDetail;
import com.springapp.mvc.services.CartService;
import com.springapp.mvc.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for arranging an order
 */
@Controller
@RequestMapping(value = "/checkout")
public class CheckController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CartService cartService;


    @IncludeMenuInfo
    @RequestMapping(method = RequestMethod.GET)
    public String renderCheckout(Model model) {
        model.addAttribute("checkoutForm", new CheckoutForm());
        return "checkout/checkout";
    }

    @IncludeMenuInfo
    @RequestMapping(value = "/add")
    public String checkout(Model model,
                           @Valid @ModelAttribute("checkoutForm") CheckoutForm checkoutForm,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "checkout/checkout";
        }
        Cart cart = (Cart) request.getAttribute("cart");
        Customer customer = (Customer) request.getAttribute("customer");
        List<CartRow> cartRows = cart.getCartRows();

        for (CartRow cartRow : cartRows) {
            OrderItem orderItem = new OrderItem(cartRow.getGood(), cartRow.getCount());
            orderService.addOrderItem(orderItem);
            Order order = new Order(customer, orderItem, checkoutForm.getCity(), checkoutForm.getPostCode(), checkoutForm.getAddress(), OrderStates.RECRUITING.toString(), checkoutForm.getComments());
            orderService.addOrder(order);
        }


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.getPrincipal() instanceof MyUserDetail) {
            MyUserDetail myUserDetail = (MyUserDetail) auth.getPrincipal();
            cartService.clear(myUserDetail.getUsername());
        }
        model.addAttribute("cart", new Cart(new ArrayList<>(), BigDecimal.ZERO, 0));
        /*model.addAttribute("order", order);*/
        return "checkout/success";
    }
}
