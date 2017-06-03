package com.springapp.mvc.common.hibernateEntity;

import javax.persistence.*;


@Entity
@Table(name = "h_goods_in_store")
public class GoodInStore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private GoodInfo goodInfo;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Store store;

    private int count;

    public GoodInStore(GoodInfo goodInfo, Store store, int count) {
        this.goodInfo = goodInfo;
        this.store = store;
        this.count = count;
    }

    public GoodInStore() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GoodInfo getGoodInfo() {
        return goodInfo;
    }

    public void setGoodInfo(GoodInfo goodInfo) {
        this.goodInfo = goodInfo;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
