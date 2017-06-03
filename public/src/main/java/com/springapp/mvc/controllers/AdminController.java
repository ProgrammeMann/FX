package com.springapp.mvc.controllers;

import com.springapp.mvc.aspects.annotation.IncludeMenuInfo;
import com.springapp.mvc.common.hibernateEntity.Customer;
import com.springapp.mvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CustomerService customerService;

    @IncludeMenuInfo
    @RequestMapping(method = RequestMethod.GET)
    public String renderAdminPage() {
        List<Customer> customers = customerService.getAll();
        request.setAttribute("customers", customers);
        return "admin/adminPage";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String update(@RequestParam(value = "customer_id", required = true) Long customerId) {
        System.out.println(customerId);
        customerService.updateEnabled(customerId);
        return "redirect:/admin";
    }

    @RequestMapping(value = "/user/remove", method = RequestMethod.GET)
    public String remove(@RequestParam(value = "customer_id", required = true) Long customerId) {
        customerService.delete(customerId);
        return "redirect:/admin";
    }
}
