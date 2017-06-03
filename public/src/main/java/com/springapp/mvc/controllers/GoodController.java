package com.springapp.mvc.controllers;

import com.springapp.mvc.aspects.annotation.IncludeMenuInfo;
import com.springapp.mvc.common.hibernateEntity.GoodInStore;
import com.springapp.mvc.common.hibernateEntity.GoodInfo;
import com.springapp.mvc.common.hibernateEntity.Store;
import com.springapp.mvc.form.GoodAddForm;
import com.springapp.mvc.form.StoreAddForm;
import com.springapp.mvc.services.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Контроллер для работы с карточкой товара
 */
@Controller
@RequestMapping(value = "/good")
public class GoodController {

    public static final String ATTR_GOOD_ADD_FORM = "goodAddForm";
    public static final String ATTR_STORE_ADD_FORM = "storeAddForm";
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private GoodService goodService;

    /**
     * Отображение карточки товара
     *
     * @param goodId id товара
     */
    @IncludeMenuInfo
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String renderGoodPage(@PathVariable("id") Long goodId) {
        request.setAttribute("good", goodService.getGoodById(goodId));
        return "good/goodPage";
    }

    @IncludeMenuInfo
    @RequestMapping(value = "/add-page", method = RequestMethod.GET)
    public String renderGoodPage() {
        request.setAttribute(ATTR_GOOD_ADD_FORM, new GoodAddForm());
        request.setAttribute(ATTR_STORE_ADD_FORM, new StoreAddForm());
        return "admin/addGoods";
    }

    @RequestMapping(value = "/add-store", method = RequestMethod.POST)
    public String addStore(@Valid @ModelAttribute(ATTR_STORE_ADD_FORM) StoreAddForm storeAddForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/good/add-page";
        } else {
            Store store = new Store(storeAddForm.getCity(), storeAddForm.getAddress());
            goodService.addStore(store);
        }
        return "redirect:/good/add-page";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@Valid @ModelAttribute(ATTR_GOOD_ADD_FORM) GoodAddForm goodAddForm,
                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "redirect:/good/add-page";
        } else {
            GoodInfo goodInfo = new GoodInfo(goodAddForm.getName(), goodAddForm.getDescription(), goodAddForm.getImageUrl(), goodAddForm.getCategory(), goodAddForm.getPrice());
            Store store = goodService.getStoreById(goodAddForm.getStoreId());
            GoodInStore goodInStore = new GoodInStore(goodInfo, store, goodAddForm.getCount());
            goodService.addGood(goodInfo);
            goodService.addGoodInStore(goodInStore);
        }
        return "redirect:/catalog";
    }
}
