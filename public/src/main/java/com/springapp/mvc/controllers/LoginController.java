package com.springapp.mvc.controllers;

import com.springapp.mvc.aspects.annotation.IncludeMenuInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller for signing in the user
 */
@Controller
public class LoginController {

    @Autowired
    private HttpServletRequest request;

    @IncludeMenuInfo
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String renderLoginPage(Boolean error) {
        request.setAttribute("error", error);
        return "login/login";
    }
}
