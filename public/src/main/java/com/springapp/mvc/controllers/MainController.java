package com.springapp.mvc.controllers;

import com.springapp.mvc.aspects.annotation.IncludeMenuInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Контроллер главной страницы
 */
@Controller
public class MainController {

    @IncludeMenuInfo
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderMain(ModelMap model) {
        return "main/main";
    }
}