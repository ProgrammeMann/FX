package com.springapp.mvc.common.hibernateEntity;

import javax.persistence.*;

/**
 */
@Entity                    // указывает на то, что данный класс является сущностью
@Table(name = "Customer")    // задает имя таблицы, в которой будут храниться объекты класса
public class Customer implements Comparable {

    @Id                    // т.е. данное поле является id (уникальным)
    @Column(name = "id")   // обозначает имя колонки, соответствующей данному полю
    @GeneratedValue(strategy = GenerationType.AUTO)  // определяет способ генерации
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)  // указываем, что email должен быть уникальным
    private String email;

    /**
     * Права доступа пользователя (возможные значения 'ROLE_USER', 'ROLE_ADMIN')
     */
    private String role;

    /**
     * Флаг, что пользователь подтвержден и активен.
     */
    @Column(nullable = false)
    private Boolean enabled;

    private String phone;

    public Customer() {

    }

    public Customer(Long id) {
        this.id = id;
    }

    public Customer(String name, String email, String phone, String password, String role, Boolean enabled) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int compareTo(Object o) {
        Customer c = (Customer) o;
        System.out.println("Customer compareTo method for " + id + " and " + c.getId());
        return (int) (id - c.getId());
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isAdmin() {
        return role.equals("ROLE_ADMIN");
    }
}
