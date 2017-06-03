package com.springapp.mvc.form;

import com.springapp.mvc.common.hibernateEntity.Categories;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.math.BigDecimal;

public class GoodAddForm {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;

    @Valid
    private Categories category;


    private String imageUrl;

    @Valid
    private BigDecimal price;

    private Long storeId;

    @Valid
    private Integer count;

    public GoodAddForm() {

    }

    public GoodAddForm(String name, String description, Categories category, String imageUrl, BigDecimal price, Long storeId, Integer count) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.price = price;
        this.storeId = storeId;
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
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

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
