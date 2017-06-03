package com.springapp.mvc.controllers.rest;

import com.springapp.mvc.common.hibernateEntity.*;
import com.springapp.mvc.sender.SendMailTLS;
import com.springapp.mvc.services.GoodService;
import com.springapp.mvc.services.OrderService;
import com.springapp.mvc.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MyRestController {

    @Autowired
    OrderService orderService;

    @Autowired
    StoreService storeService;

    @Autowired
    GoodService goodService;

    @RequestMapping(value = "/rest/stores", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

    @RequestMapping(value = "/rest/store/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GoodInStore> getGoodsInStore(@PathVariable("id") Long id) {
        return storeService.getAllGoodInStore(id);
    }

    @RequestMapping(value = "/rest/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateAmount(@RequestParam long goodId, @RequestParam int count) {
        storeService.updateGoodCount(goodId ,count);
        return "success";
    }

    @RequestMapping(value = "/rest/migrateGood", produces = MediaType.APPLICATION_JSON_VALUE)
    public String moveProduct(@RequestParam long from, @RequestParam long to, @RequestParam long goodId) {
        storeService.migrateGoods(goodId, from, to);
        return "success";
    }

    @RequestMapping(value = "/rest/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @RequestMapping(value = "/rest/changeState", method = RequestMethod.GET)
    public void changeOrderStatus(@RequestParam Long id, @RequestParam String state) {
        orderService.changeOrderState(id, state);
        sendState(id, state);
    }

    private void sendState(Long id, String state){
        try {
            SendMailTLS sender = new SendMailTLS("thelegendsleagueservice@gmail.com", "platok123");
            String email = orderService.getOrderById(id).getCustomer().getEmail();
            sender.send("LL_ORDERSTATE", "Ваш заказ : " + state, "thelegendsleagueservice@gmail.com", email);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}
