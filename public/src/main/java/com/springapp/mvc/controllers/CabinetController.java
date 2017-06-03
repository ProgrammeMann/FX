package com.springapp.mvc.controllers;

import com.springapp.mvc.aspects.annotation.IncludeMenuInfo;
import com.springapp.mvc.common.hibernateEntity.Customer;
import com.springapp.mvc.common.hibernateEntity.Order;
import com.springapp.mvc.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controller for personal cabinet of User
 */
@Controller
public class CabinetController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderService orderService;

    @IncludeMenuInfo
    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String renderCabinetPage() {
        Customer customer = (Customer) request.getAttribute("customer");
        List<Order> orders = (List<Order>) orderService.getOrderByCustomer(customer);
        request.setAttribute("orders", orders);
        return "cabinet/cabinetPage";
    }

}
