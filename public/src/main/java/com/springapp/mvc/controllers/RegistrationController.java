package com.springapp.mvc.controllers;

import com.springapp.mvc.aspects.annotation.IncludeMenuInfo;
import com.springapp.mvc.common.hibernateEntity.Customer;
import com.springapp.mvc.form.RegistrationFormBean;
import com.springapp.mvc.sender.SendMailTLS;
import com.springapp.mvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Controller for registrating the user
 */
@Controller
@RequestMapping("/reg")
public class RegistrationController {

    public static final String ATTR_REGISTRATION_FORM = "regForm";

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CustomerService customerService;

    /**
     * Отображение страницы регистрации
     */
    @IncludeMenuInfo
    @RequestMapping(method = RequestMethod.GET)
    public String renderRegistrationPage() {
        request.setAttribute(ATTR_REGISTRATION_FORM, new RegistrationFormBean());
        return "registration/registrationPage";
    }

    /**
     * Обработка формы Регистрации
     */
    @IncludeMenuInfo
    @RequestMapping(method = RequestMethod.POST)
    public String registrationForm(
            @Valid @ModelAttribute(ATTR_REGISTRATION_FORM) RegistrationFormBean registrationFormBean,
            BindingResult bindingResult,
            String firstName,
            String email,
            String phone,
            String password) {
        if (!registrationFormBean.isValid() || bindingResult.hasErrors()) {
            return "registration/registrationPage";
        }
        try {
            customerService.add(new Customer(firstName, email, phone, md5Decoder(password), "ROLE_USER", false));
            SendMailTLS sender = new SendMailTLS("thelegendsleagueservice@gmail.com", "platok123");
            Customer customer = customerService.getCustomerByLogin(email);
            Long id = customer.getId();
            sender.send("LL_REGISTRATION", "Вы прошли успешную регистрацию на сайте thelegendsleague\n" +
                    "Для подтверждения регистрации пройдите по ссылке : http://localhost:8080/reg/activate?id=" + id + "\n" +
                    "Ваш логин : " + email, "thelegendsleagueservice@gmail.com", email);
        } catch (RuntimeException e) {
            throw e;
        }
        return "redirect:/";
    }

    /**
     * Activating the User on the site
     */
    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    public String activateUser(Long id) {
        Customer customer = customerService.getById(id);
        customer.setEnabled(true);
        customerService.updateEnabled(customer);
        return "redirect:/login";
    }

    private String md5Decoder(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(password.getBytes(), 0, password.length());
        String hashedPass = new BigInteger(1, messageDigest.digest()).toString(16);
        if (hashedPass.length() < 32) {
            hashedPass = "0" + hashedPass;
        }
        return hashedPass;
    }
}