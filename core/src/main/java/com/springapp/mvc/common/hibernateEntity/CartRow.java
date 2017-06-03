package com.springapp.mvc.common.hibernateEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "h_cart")
public class CartRow implements Serializable {

    @Id                    // т.е. данное поле является id (уникальным)
    @Column(name = "id")   // обозначает имя колонки, соответствующей данному полю
    @GeneratedValue(strategy = GenerationType.AUTO)  // определяет способ генерации
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "good_id")
    private GoodInfo good;

    private Integer count;
    @ManyToOne                                  // определяет отношение многие к одному
            (cascade = {CascadeType.REFRESH},
                    fetch = FetchType.LAZY)    // подгрузка объектов списка сразу
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public CartRow(GoodInfo good, Integer count, Customer customer) {
        this.good = good;
        this.count = count;
        this.customer = customer;
    }

    public CartRow() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoodInfo getGood() {
        return good;
    }

    public void setGood(GoodInfo good) {
        this.good = good;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
