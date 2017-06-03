package com.springapp.mvc.services;

import com.springapp.mvc.common.hibernateEntity.GoodInStore;
import com.springapp.mvc.common.hibernateEntity.GoodInfo;
import com.springapp.mvc.common.hibernateEntity.Store;
import com.springapp.mvc.repositories.GoodRepository;
import com.springapp.mvc.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for working with goods
 */
@Service
public class GoodService {

    @Qualifier("goodRepositoryHibernate")
    @Autowired
    private GoodRepository goodRepository;

    @Autowired
    private StoreRepository storeRepository;

    public List<GoodInfo> getAllGoods() {
        return goodRepository.getAll();
    }

    public String getGoodNameById(Long id) {
        return goodRepository.getGoodById(id).getName();
    }

    public GoodInfo getGoodById(Long goodId) {
        return goodRepository.getGoodById(goodId);
    }

    public void addGood(GoodInfo good) {
        goodRepository.addGood(good);
    }

    public void addGoodInStore(GoodInStore goodInStore) {
        storeRepository.addGoodInStore(goodInStore);
    }

    public void addStore(Store store) {
        storeRepository.add(store);
    }

    public List<Store> getAllStores() {
        return storeRepository.getAll();
    }

    public Store getStoreById(Long storeId) {
        return storeRepository.getStoreById(storeId);
    }
}
