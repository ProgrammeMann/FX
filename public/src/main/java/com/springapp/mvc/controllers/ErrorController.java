package com.springapp.mvc.controllers;

import com.springapp.mvc.aspects.annotation.IncludeMenuInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    @IncludeMenuInfo
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String renderErrorPage(HttpServletRequest request) {
        String errorMsg = "";
        int httpErrorCode = getErrorCode(request);
        switch (httpErrorCode) {
            case 400:
                errorMsg = "Http Error Code : 400. Bad Request";
                break;
            case 401:
                errorMsg = "Http Error Code : 401. Unauthorizes";
                break;
            case 404:
                errorMsg = "Http Error Code : 404. Resource not found";
                break;
            case 500:
                errorMsg = "Http Error Code : 500. Internal Server Error";
                break;
        }
        request.setAttribute("errorMsg", errorMsg);
        return "error/errorPage";
    }

    private int getErrorCode(HttpServletRequest httpServletRequest) {
        return (int) httpServletRequest.getAttribute("javax.servlet.error.status_code");
    }
}
