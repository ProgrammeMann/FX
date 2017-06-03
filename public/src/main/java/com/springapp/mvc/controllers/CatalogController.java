package com.springapp.mvc.controllers;

import com.springapp.mvc.aspects.annotation.IncludeMenuInfo;
import com.springapp.mvc.common.hibernateEntity.Categories;
import com.springapp.mvc.common.hibernateEntity.GoodInfo;
import com.springapp.mvc.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Контроллер отвечающий за каталог
 */
@Controller
@RequestMapping("/catalog")
public class CatalogController {

    private static final Integer TEST_GOODS_COUNT = 16;
    private static final Integer TEST_LIMIT = 6;

    @Autowired
    private CatalogService catalogService;

    /**
     * Отображение каталога
     */
    @IncludeMenuInfo
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String renderCatalog(@PathVariable("name") String category,
                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                Long limit,
                                Model model) {
        List<GoodInfo> goods = catalogService.getGoodsByCategory(Categories.valueOf(category));
        model.addAttribute("goods", goods);
        model.addAttribute("category", category);
        model.addAttribute("page", page);
        model.addAttribute("limit", limit == null ? TEST_LIMIT : limit);
        model.addAttribute("goodsCount", TEST_GOODS_COUNT);
        return "catalog/catalogPage";
    }

    /**
     * Показать еще товары
     */
    @RequestMapping(value = "/showMore", method = RequestMethod.POST)
    public String showMoreGoods(String name, Integer page, Integer limit, Model model) {
        List<GoodInfo> goods = catalogService.getGoodsByCategory(Categories.valueOf(name));
        if (TEST_GOODS_COUNT + limit > page * limit)
            model.addAttribute("goods", (TEST_GOODS_COUNT > page * limit) ? goods : goods.subList(0, TEST_GOODS_COUNT + limit - page * limit));
        return "catalog/ajaxGoods";
    }

    /**
     * Отображение главной страницы каталога
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String mainCatalog(HttpServletRequest request) {
        return "redirect:/catalog/" + Categories.All;
    }
}
