package com.springapp.mvc.services;

import com.springapp.mvc.common.MenuInfo;
import com.springapp.mvc.common.hibernateEntity.Categories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * Service for working with the menu navigation
 */

@Service
public class MenuService {
    /**
     * Получаем основное меню сайта
     * HOME
     * STORE
     * NEWS
     * BLOG
     * ABOUT
     * LOCATION
     * CONTACT
     */

    public List<MenuInfo> getMainMenu() {
        List<MenuInfo> listMenu = new ArrayList<MenuInfo>();
        listMenu.add(new MenuInfo(1L, "Home", "/"));
        listMenu.add(new MenuInfo(2L, "CATALOG", "/catalog"));
        return listMenu;
    }

    /**
     * Получаем все категории
     * Например:
     * All /  Accessories /  Artwork /  Headwear /  Outerwear /  Pants /  Shirts /  Sweatshirts /  T-Shirts /
     */

    public List<Categories> getCategories() {
        List<Categories> categories = new ArrayList<>();

        EnumSet<Categories> all = EnumSet.allOf(Categories.class);
        for (Categories category : all) {
            categories.add(category);
        }
        return categories;
    }
}
