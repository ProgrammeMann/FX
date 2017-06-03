package com.springapp.mvc.services;

import com.springapp.mvc.common.hibernateEntity.Categories;
import com.springapp.mvc.common.hibernateEntity.GoodInfo;
import com.springapp.mvc.repositories.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for working with the goods in the category
 */
@Service
public class CatalogService {

    @Qualifier("goodRepositoryHibernate")
    @Autowired
    protected GoodRepository goodRepository;

    /**
     * Получение товаров по категории
     *
     * @return список товаров
     */
    public List<GoodInfo> getGoodsByCategory(Categories category) {
        List<GoodInfo> goods;
        if (category.equals(Categories.All)) {
            goods = goodRepository.getAll();
        } else {
            goods = goodRepository.getGoodsByCategory(category);
        }
        return goods;
    }

}
