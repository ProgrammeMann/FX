package com.springapp.mvc.common.hibernateEntity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Основная информация о товаре
 */
@Entity
@Table(name = "h_goods")
public class GoodInfo {

    /**
     * id товара
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Название товара
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * Описание товара
     */
    @Column(name = "description")
    private String description;

    /**
     * URL изображения
     */
    @Column(name = "image")
    private String imageUrl;

    /**
     * Категория товара
     */
    @Enumerated
    private Categories category;

    /**
     * Цена
     */
    @Column(name = "price")
    private BigDecimal price;

    public GoodInfo() {
    }

    public GoodInfo(String name, Categories category, BigDecimal price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public GoodInfo(String name, String imageUrl, Categories category, BigDecimal price) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.category = category;
        this.price = price;
    }

    public GoodInfo(String name, String description, String imageUrl, Categories category, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.category = category;
        this.price = price;
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
