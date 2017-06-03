package com.springapp.mvc.repositories;


import com.springapp.mvc.common.hibernateEntity.Categories;
import com.springapp.mvc.common.hibernateEntity.GoodInfo;

import java.util.List;

public interface GoodRepository {

    void addGood(GoodInfo goodInfo);

    GoodInfo getGoodById(Long goodId);

    List<GoodInfo> getAll();

    List<GoodInfo> getGoodsByCategory(Categories categoryInfo);
}
