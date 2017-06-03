package com.springapp.mvc.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationFormBean {

    @NotEmpty(message = "Поле обязательно для заполнения")
    private String firstName;

    @NotEmpty(message = "Поле обязательно для заполнения")
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
            message = "Неверный формат email")
    private String email;

    @Size(min = 3, max = 13, message = "Введите верный номер телефона")
    private String phone;

    @AssertTrue(message = "Примите условия договора")
    private Boolean signIn;

    @Size(min = 6, max = 20, message = "Пароль должен быть от 6 до 20 символов")
    private String password;

    @Size(min = 6, max = 20, message = "Пароль должен быть от 6 до 20 символов")
    private String confirmPassword;

    public RegistrationFormBean() {
    }

    public RegistrationFormBean(String firstName, String email, String phone, Boolean signIn, String password, String confirmPassword) {
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.signIn = signIn;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @AssertTrue(message = "Не подтвержден пароль. Введите снов")
    public boolean isValid() {
        return this.confirmPassword.equals(this.password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getSignIn() {
        return signIn;
    }

    public void setSignIn(Boolean signIn) {
        this.signIn = signIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "RegistrationFormBean{" +
                "firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", signIn=" + signIn +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
