package com.springapp.mvc.aspects;

import com.springapp.mvc.common.Cart;
import com.springapp.mvc.common.MenuInfo;
import com.springapp.mvc.common.hibernateEntity.Categories;
import com.springapp.mvc.common.hibernateEntity.Customer;
import com.springapp.mvc.common.hibernateEntity.Store;
import com.springapp.mvc.services.CartService;
import com.springapp.mvc.services.CustomerService;
import com.springapp.mvc.services.GoodService;
import com.springapp.mvc.services.MenuService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Aspect for adding information in the header menu
 */
@Aspect
@Component
public class MainMenuAspect {

    private static final String MAIN_MENU_LIST = "listMenu";
    private static final String CATEGORY = "categories";
    private static final String SESSION_CART = "cart";
    private static final String CUSTOMER = "customer";
    private static final String STORES = "stores";
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private MenuService menuService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CartService cartService;
    @Autowired
    private GoodService goodService;

    @Pointcut("@annotation(com.springapp.mvc.aspects.annotation.IncludeMenuInfo)")
    public void includeMenuInfoMethod() {
    }

    @Before("includeMenuInfoMethod()")
    public void includeMenuInfo() {
        List<MenuInfo> listMenu = menuService.getMainMenu();
        List<Categories> categories = menuService.getCategories();
        List<Store> stores = goodService.getAllStores();
        if (request.getUserPrincipal() != null) {
            String customerName = request.getUserPrincipal().getName();
            Cart cart = cartService.getCart(customerName);
            Customer customer = customerService.getCustomerByLogin(customerName);
            request.setAttribute(SESSION_CART, cart);
            request.setAttribute(CUSTOMER, customer);
        }
        request.setAttribute(MAIN_MENU_LIST, listMenu);
        request.setAttribute(CATEGORY, categories);

        request.setAttribute(STORES, stores);
    }

}
