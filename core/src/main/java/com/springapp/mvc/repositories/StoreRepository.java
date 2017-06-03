package com.springapp.mvc.repositories;


import com.springapp.mvc.common.hibernateEntity.GoodInStore;
import com.springapp.mvc.common.hibernateEntity.GoodInfo;
import com.springapp.mvc.common.hibernateEntity.Store;

import java.util.List;

public interface StoreRepository {

    void add(Store store);

    void addGoodInStore(GoodInStore goodInStore);

    List<Store> getAll();

    Store getStoreById(Long storeId);

    GoodInStore getGoodInStoreByGood(GoodInfo goodInfo);

    GoodInStore getGoodInStoreByGoodIdAndStoreId(Long goodId, Long storeId);

    List<GoodInStore> getGoodInStoreByStore(Store store);

    void updateGoodInStore(GoodInStore goodInStore);

    GoodInStore getGoodInStoreById(Long goodInStoreId);
}
