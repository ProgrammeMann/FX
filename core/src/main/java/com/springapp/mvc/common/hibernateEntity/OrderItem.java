package com.springapp.mvc.common.hibernateEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "h_order_item")
public class OrderItem implements Serializable {

    @Id                    // т.е. данное поле является id (уникальным)
    @Column(name = "id")   // обозначает имя колонки, соответствующей данному полю
    @GeneratedValue(strategy = GenerationType.AUTO)  // определяет способ генерации
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "good_id")
    private GoodInfo good;

    private Integer count;

    public OrderItem() {

    }

    public OrderItem(GoodInfo good, Integer count) {
        this.good = good;
        this.count = count;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
